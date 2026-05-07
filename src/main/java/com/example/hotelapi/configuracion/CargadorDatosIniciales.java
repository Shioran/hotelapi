package com.example.hotelapi.configuracion;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.hotelapi.modelos.Cliente;
import com.example.hotelapi.modelos.Habitacion;
import com.example.hotelapi.modelos.Reserva;
import com.example.hotelapi.modelos.TipoHabitacion;
import com.example.hotelapi.repositorios.IClienteRepositorio;
import com.example.hotelapi.repositorios.IHabitacionRepositorio;
import com.example.hotelapi.repositorios.IReservaRepositorio;
import com.example.hotelapi.repositorios.ITipoHabitacionRepositorio;

//Carga datos iniciales al arrancar la aplicacion,
//asi el CRUD del front ya tiene con que jugar sin capturar nada a mano.
@Component
public class CargadorDatosIniciales implements CommandLineRunner {

    @Autowired
    private ITipoHabitacionRepositorio tipoHabitacionRepositorio;

    @Autowired
    private IHabitacionRepositorio habitacionRepositorio;

    @Autowired
    private IClienteRepositorio clienteRepositorio;

    @Autowired
    private IReservaRepositorio reservaRepositorio;

    @Override
    public void run(String... args) throws Exception {

        if (tipoHabitacionRepositorio.count() > 0) {
            return;
        }

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

        //habitaciones
        Habitacion h101 = new Habitacion();
        h101.setNumero("101");
        h101.setPiso(1);
        h101.setEstado("disponible");
        h101.setTipoHabitacionId(sencilla.getId());
        habitacionRepositorio.save(h101);

        Habitacion h102 = new Habitacion();
        h102.setNumero("102");
        h102.setPiso(1);
        h102.setEstado("disponible");
        h102.setTipoHabitacionId(doble.getId());
        habitacionRepositorio.save(h102);

        Habitacion h201 = new Habitacion();
        h201.setNumero("201");
        h201.setPiso(2);
        h201.setEstado("mantenimiento");
        h201.setTipoHabitacionId(suite.getId());
        habitacionRepositorio.save(h201);

        Habitacion h202 = new Habitacion();
        h202.setNumero("202");
        h202.setPiso(2);
        h202.setEstado("disponible");
        h202.setTipoHabitacionId(doble.getId());
        habitacionRepositorio.save(h202);

        //clientes
        Cliente cliente1 = new Cliente();
        cliente1.setNombre("Carlos");
        cliente1.setApellido("Ramirez");
        cliente1.setDocumento("1234567890");
        cliente1.setEmail("carlos.ramirez@email.com");
        cliente1.setTelefono("3101234567");
        clienteRepositorio.save(cliente1);

        Cliente cliente2 = new Cliente();
        cliente2.setNombre("Maria");
        cliente2.setApellido("Lopez");
        cliente2.setDocumento("0987654321");
        cliente2.setEmail("maria.lopez@email.com");
        cliente2.setTelefono("3209876543");
        clienteRepositorio.save(cliente2);

        //reservas
        Reserva reserva1 = new Reserva();
        reserva1.setFechaEntrada(LocalDate.of(2026, 5, 10));
        reserva1.setFechaSalida(LocalDate.of(2026, 5, 13));
        reserva1.setPrecioTotal(450000.0);
        reserva1.setEstado("confirmada");
        reserva1.setClienteId(cliente1.getId());
        reserva1.setHabitacionId(h101.getId());
        reservaRepositorio.save(reserva1);

        Reserva reserva2 = new Reserva();
        reserva2.setFechaEntrada(LocalDate.of(2026, 5, 15));
        reserva2.setFechaSalida(LocalDate.of(2026, 5, 18));
        reserva2.setPrecioTotal(750000.0);
        reserva2.setEstado("confirmada");
        reserva2.setClienteId(cliente2.getId());
        reserva2.setHabitacionId(h102.getId());
        reservaRepositorio.save(reserva2);

        Reserva reserva3 = new Reserva();
        reserva3.setFechaEntrada(LocalDate.of(2026, 6, 1));
        reserva3.setFechaSalida(LocalDate.of(2026, 6, 5));
        reserva3.setPrecioTotal(1000000.0);
        reserva3.setEstado("cancelada");
        reserva3.setClienteId(cliente1.getId());
        reserva3.setHabitacionId(h202.getId());
        reservaRepositorio.save(reserva3);

        System.out.println(">>> Datos iniciales de hotelapi cargados correctamente");
    }

}
