package com.example.hotelapi.configuracion;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.hotelapi.modelos.Habitacion;
import com.example.hotelapi.modelos.Reserva;
import com.example.hotelapi.modelos.TipoHabitacion;
import com.example.hotelapi.modelos.Usuario;
import com.example.hotelapi.repositorios.IHabitacionRepositorio;
import com.example.hotelapi.repositorios.IReservaRepositorio;
import com.example.hotelapi.repositorios.ITipoHabitacionRepositorio;
import com.example.hotelapi.repositorios.IUsuarioRepositorio;

@Component
public class CargadorDatosIniciales implements CommandLineRunner {

    @Autowired
    private ITipoHabitacionRepositorio tipoHabitacionRepositorio;

    @Autowired
    private IHabitacionRepositorio habitacionRepositorio;

    @Autowired
    private IUsuarioRepositorio usuarioRepositorio;

    @Autowired
    private IReservaRepositorio reservaRepositorio;

    @Override
    public void run(String... args) throws Exception {

        //si ya hay datos no volvemos a cargar
        if (usuarioRepositorio.count() > 0) {
            return;
        }

        //usuarios del sistema
        Usuario admin = new Usuario();
        admin.setNombre("Juan");
        admin.setApellido("Gomez");
        admin.setDocumento("1000000001");
        admin.setEmail("admin@hotel.com");
        admin.setTelefono("3001234567");
        admin.setUsername("admin");
        admin.setPassword("admin123");
        admin.setRol("ADMIN");
        admin.setEstado("activo");
        usuarioRepositorio.save(admin);

        Usuario cliente = new Usuario();
        cliente.setNombre("Maria");
        cliente.setApellido("Lopez");
        cliente.setDocumento("1000000002");
        cliente.setEmail("maria@email.com");
        cliente.setTelefono("3109876543");
        cliente.setUsername("cliente");
        cliente.setPassword("cliente123");
        cliente.setRol("CLIENTE");
        cliente.setEstado("activo");
        usuarioRepositorio.save(cliente);

        //tipos de habitacion
        TipoHabitacion sencilla = new TipoHabitacion();
        sencilla.setNombre("sencilla");
        sencilla.setDescripcion("habitacion basica para una persona");
        sencilla.setPrecioPorNoche(150000.0);
        sencilla.setCapacidad(1);
        tipoHabitacionRepositorio.save(sencilla);

        TipoHabitacion doble = new TipoHabitacion();
        doble.setNombre("doble");
        doble.setDescripcion("habitacion con dos camas para dos personas");
        doble.setPrecioPorNoche(250000.0);
        doble.setCapacidad(2);
        tipoHabitacionRepositorio.save(doble);

        TipoHabitacion suite = new TipoHabitacion();
        suite.setNombre("suite");
        suite.setDescripcion("suite de lujo con sala y jacuzzi");
        suite.setPrecioPorNoche(500000.0);
        suite.setCapacidad(3);
        tipoHabitacionRepositorio.save(suite);

        //40 habitaciones: 4 pisos x 10 habitaciones
        String[] estados = {
                "disponible", "disponible", "disponible", "disponible",
                "disponible", "disponible", "disponible", "disponible",
                "ocupada", "mantenimiento"
        };

        TipoHabitacion[] tipos = {
                sencilla, sencilla, doble, doble, doble,
                doble, suite, suite, sencilla, doble
        };

        for (int piso = 1; piso <= 4; piso++) {
            for (int num = 1; num <= 10; num++) {
                Habitacion h = new Habitacion();
                h.setNumero(piso + "" + String.format("%02d", num));
                h.setPiso(piso);
                h.setEstado(estados[num - 1]);
                h.setTipoHabitacionId(tipos[num - 1].getId());
                habitacionRepositorio.save(h);
            }
        }

        //reservas de ejemplo
        Habitacion hab101 = habitacionRepositorio.findAll().get(0);
        Habitacion hab102 = habitacionRepositorio.findAll().get(1);

        Reserva reserva1 = new Reserva();
        reserva1.setFechaEntrada(LocalDate.of(2026, 5, 20));
        reserva1.setFechaSalida(LocalDate.of(2026, 5, 23));
        reserva1.setPrecioTotal(450000.0);
        reserva1.setEstado("confirmada");
        reserva1.setClienteId(cliente.getId());
        reserva1.setHabitacionId(hab101.getId());
        reserva1.setNumeroNoches(3);
        Reserva r1guardada = reservaRepositorio.save(reserva1);
        r1guardada.setCodigoReserva("RES-2026-" + String.format("%03d", r1guardada.getId()));
        reservaRepositorio.save(r1guardada);

        Reserva reserva2 = new Reserva();
        reserva2.setFechaEntrada(LocalDate.of(2026, 6, 1));
        reserva2.setFechaSalida(LocalDate.of(2026, 6, 5));
        reserva2.setPrecioTotal(1000000.0);
        reserva2.setEstado("confirmada");
        reserva2.setClienteId(admin.getId());
        reserva2.setHabitacionId(hab102.getId());
        reserva2.setNumeroNoches(4);
        Reserva r2guardada = reservaRepositorio.save(reserva2);
        r2guardada.setCodigoReserva("RES-2026-" + String.format("%03d", r2guardada.getId()));
        reservaRepositorio.save(r2guardada);

        System.out.println(">>> Datos iniciales de hotelapi cargados correctamente");
    }

}