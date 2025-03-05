package ejerciciografico1;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;

public class EjercicioGrafico1 {

    private static final int MAX_PASAJE = 100;

    private static String[] nombres = new String[MAX_PASAJE];
    private static String[] identificaciones = new String[MAX_PASAJE];
    private static String[] origenes = new String[MAX_PASAJE];
    private static String[] destinos = new String[MAX_PASAJE];
    private static String[] fechasViajes = new String[MAX_PASAJE];
    private static String[] horasSalida = new String[MAX_PASAJE];
    private static int[] numeroAsientos = new int[MAX_PASAJE];
    private static String[] tiposPasajero = new String[MAX_PASAJE]; //Regular //Estudiente //Adulto Mayor
    private static double[] precios = new double[MAX_PASAJE];
    private static boolean[] reservados = new boolean[MAX_PASAJE];

    private static int cantidadPasajeros = 0;

    public static void main(String[] args) {

        agregarPasajeDemo("cris", "3423423", "floresta", "sogamoso", "2052-02-01", "12:30", 12, "Regular", 9500, false);
        agregarPasajeDemo("sdasd", "5453", "fsdfs", "sadasd", "2052-02-01", "12:30", 12, "Regular", 3500, false);
        agregarPasajeDemo("julian", "454562", "Aquitania", "Usbekistan", "2052-02-01", "12:30", 12, "Regular", 500, false);
        agregarPasajeDemo("Gabriel", "6546345", "Tunja", "Amazonas", "2052-02-01", "12:30", 12, "Adulto Mayor", 3500000, false);
        agregarPasajeDemo("Tatiana", "164234", "Duitama", "Mendoza", "2052-02-01", "12:30", 12, "Regular", 100500, false);
        agregarPasajeDemo("Parra", "1233443", "Miraflores", "Tunja", "2052-02-01", "12:30", 12, "Regular", 3500, false);

        boolean continuar = true;

        JOptionPane.showMessageDialog(null, "Bienvenidos al sistema de gestion de pasajeros de COOFLONORTE",
                "60 Años en tu camino", JOptionPane.INFORMATION_MESSAGE);

        while (continuar) {
            String[] opciones = {
                "Registrar nuevo Pasaje",
                "Reservar pasaje",
                "Confirmar Reserva",
                "Ver todos los pasajes",
                "Modificar Pasaje",
                "Cancelar Pasaje",
                "Imprimir ticket de pasaje",
                "Salir"
            };

            int opcionSeleccionada = JOptionPane.showOptionDialog(
                    null,
                    "Seleccione una operacion", "Sistema de transportes COFLONORTE",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    opciones,
                    opciones[0]);

            switch (opcionSeleccionada) {
                case 0:
                    registrarPasaje(false);
                    break;

                case 1:
                    reservarPasaje(true);
                    break;

                case 2:
                    confirmarReserva();
                    break;

                case 3:
                    verTodosLosPasajes();
                    break;
                case 4:
                    buscarPasaje();
                    break;
                case 5:
                    modificarPasaje();
                    break;

                case 6:
                    cancelarPasaje();
                    break;

                case 7:
                    imprimirTicketPasaje();
                    break;

                case 8:
                case -1:
                    continuar = false;
                    JOptionPane.showMessageDialog(null,
                            "Gracias por usar el sistema de trasportes de COFLONORTE ", "Hata pronto", JOptionPane.INFORMATION_MESSAGE);
            }
        }

    }

    private static void agregarPasajeDemo(String nombre, String identificacion,
            String origen, String destino, String fechaViaje,
            String horaSalida, int numeroAsiento, String tipoPasajero,
            double precio, boolean esReserva) {

        if (cantidadPasajeros < MAX_PASAJE) {
            nombres[cantidadPasajeros] = nombre;
            identificaciones[cantidadPasajeros] = identificacion;
            origenes[cantidadPasajeros] = origen;
            destinos[cantidadPasajeros] = destino;
            fechasViajes[cantidadPasajeros] = fechaViaje;
            horasSalida[cantidadPasajeros] = horaSalida;
            numeroAsientos[cantidadPasajeros] = numeroAsiento;
            tiposPasajero[cantidadPasajeros] = tipoPasajero;
            precios[cantidadPasajeros] = precio;
            reservados[cantidadPasajeros] = esReserva;
            cantidadPasajeros++;
        }
    }

    private static void registrarPasaje(boolean esReserva) {
        if (cantidadPasajeros >= MAX_PASAJE) {
            JOptionPane.showMessageDialog(null,
                    "No se puede registrar mas pasajer, Limite alcanzado",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String nombre = JOptionPane.showInputDialog(null,
                "Ingrese el nombre completo de pasajeros",
                (esReserva ? "Reserva" : "Registro") + " de Pasaje",
                JOptionPane.QUESTION_MESSAGE);

        if (nombre == null || nombre.trim().isEmpty()) {
            return;
        }

        String identificacion = JOptionPane.showInputDialog(null,
                "Ingrese el numero de identificacion del pasajero: ",
                (esReserva ? "Reserva" : "registro") + " de pasaje",
                JOptionPane.QUESTION_MESSAGE);

        if (identificacion == null || identificacion.trim().isEmpty()) {
            return;
        }

        for (int i = 0; i < cantidadPasajeros; i++) {
            int confirmarNuevo = JOptionPane.showConfirmDialog(null,
                    "Ya existe un pasaje registrado con esta identificacion\n"
                    + "desea" + (esReserva ? "reservar" : "registrar") + " un pasaje adicional?",
                    "Pasaje existente", JOptionPane.YES_OPTION);

            if (confirmarNuevo != JOptionPane.YES_OPTION) {
                return;
            }
            break;
        }

        String[] opcionesCiudades = {
            "Tunja", "Duitama", "Sogamoso", "Paipa", "Chiquinquirá",
            "Moniquirá", "Villa de Leyva", "Aquitania", "Tibasosa",
            "Bogotá", "Bucaramanga", "Villavicencio", "Otro"
        };

        String origen = (String) JOptionPane.showInputDialog(null,
                "Seleccione la ciudad de origen: ",
                (esReserva ? "Reserva" : "Registro") + "de pasaje",
                JOptionPane.QUESTION_MESSAGE,
                null, opcionesCiudades,
                opcionesCiudades[0]);

        if (origen == null) {
            return;
        }

        if (origen.equals("otro")) {
            origen = (String) JOptionPane.showInputDialog(null,
                    "Especifique la ciudad de origen: ",
                    (esReserva ? "Reserva" : "Registro") + "de pasaje",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    opcionesCiudades,
                    opcionesCiudades[0]);

            if (origen == null || origen.trim().isEmpty()) {
                return;
            }
        }

        String destino = (String) JOptionPane.showInputDialog(null,
                "Seleccione la ciudad de destino: ",
                (esReserva ? "Reserva" : "Registro") + "de pasaje",
                JOptionPane.QUESTION_MESSAGE,
                null, opcionesCiudades,
                opcionesCiudades[0]);

        if (destino == null) {
            return;
        }

        if (destino.equals("otro")) {
            destino = (String) JOptionPane.showInputDialog(null,
                    "Especifique la ciudad de destino: ",
                    (esReserva ? "Reserva" : "Registro") + "de pasaje",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    opcionesCiudades,
                    opcionesCiudades[0]);

            if (destino == null || destino.trim().isEmpty()) {
                return;
            }
        }

        if (origen.equals(destino)) {
            JOptionPane.showMessageDialog(null,
                    "El origen y destino no pueden ser iguales",
                    "error",
                    JOptionPane.ERROR_MESSAGE);

            return;
        }

        String fechaViaje = JOptionPane.showInputDialog(null,
                "Ingrese la fecha del viaje (AAAA-MM-DD)",
                (esReserva ? "Reserva" : "Registro") + " de pasaje",
                JOptionPane.QUESTION_MESSAGE);

        if (fechaViaje == null || fechaViaje.trim().isEmpty()) {
            return;
        }

        try {
            LocalDate.parse(fechaViaje);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Formato de fecha incorrecto, use AAAA-MM-DD",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String[] opcionesHorarios = {
            "06:00", "07:00", "08:00", "09:00", "10:00", "11:00",
            "12:00", "13:00", "14:00", "15:00", "16:00", "17:00",
            "18:00", "19:00", "20:00", "21:00"
        };

        String horaSalida = (String) JOptionPane.showInputDialog(null, "Especifique la ciudad de destino: ",
                (esReserva ? "Reserva" : "Registro") + "de pasaje",
                JOptionPane.QUESTION_MESSAGE,
                null,
                opcionesCiudades,
                opcionesCiudades[0]);

        if (horaSalida == null) {
            return;
        }

        int numeroAsiento = 0;
        try {
            String asientoStr = JOptionPane.showInputDialog(null,
                    "Ingrese el numero de asiento (1-40)",
                    (esReserva ? "Reserva" : "Registro") + " de pasaje");

            if (asientoStr == null || asientoStr.trim().isEmpty()) {
                return;
            }

            numeroAsiento = Integer.parseInt(asientoStr);

            if (numeroAsiento < 1 || numeroAsiento > 40) {
                JOptionPane.showMessageDialog(null,
                        "Numero de asientos invalido. debe estar entre 1 y 40.",
                        "error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            for (int i = 0; i < cantidadPasajeros; i++) {
                if (fechasViajes[i].equals(fechaViaje)
                        && horasSalida[i].equals(horaSalida)
                        && origenes[i].equals(origen)
                        && destinos[i].equals(destino)
                        && numeroAsientos[i] == numeroAsiento) {

                    JOptionPane.showMessageDialog(null, "El asiento seleccionado ya esta ocupado paara este viaje",
                            "Error", JOptionPane.ERROR_MESSAGE);

                    return;
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Numero de asiento invalido. Ingrese un valor numerico",
                    "Error", JOptionPane.ERROR_MESSAGE);

            return;
        }

        String[] opcionesTipoPasajero = {
            "Regular", "Estudiante", "Adulto Mayor"
        };

        String tipoPasajero = (String) JOptionPane.showInputDialog(null, "Seleccione el tipo de pasajero",
                (esReserva ? "Reserva" : "Registro") + " de pasaje",
                JOptionPane.QUESTION_MESSAGE, null,
                opcionesTipoPasajero,
                opcionesTipoPasajero[0]);

        if (tipoPasajero == null) {
            return;
        }

        double precioBase = calcularPrecioBase(origen, destino);
        double precio = precioBase;
        if (tipoPasajero.equals(("Estudiante"))) {
            precio = precioBase * 0.8;
        } else if (tipoPasajero.equals(("Adulto Mayor"))) {
            precio = precio * 0.7;
        }

        nombres[cantidadPasajeros] = nombre;
        identificaciones[cantidadPasajeros] = identificacion;
        origenes[cantidadPasajeros] = origen;
        destinos[cantidadPasajeros] = destino;
        fechasViajes[cantidadPasajeros] = fechaViaje;
        horasSalida[cantidadPasajeros] = horaSalida;
        numeroAsientos[cantidadPasajeros] = numeroAsiento;
        tiposPasajero[cantidadPasajeros] = tipoPasajero;
        precios[cantidadPasajeros] = precio;
        reservados[cantidadPasajeros] = esReserva;

        String codigoPasaje = generarCodigoPasaje(origen, destino, cantidadPasajeros);

        cantidadPasajeros++;

        JOptionPane.showConfirmDialog(null, (esReserva ? "Reserva" : "Pasaje")
                + " Registrado exitosamente:\n " + "Codigo: " + codigoPasaje + "\n"
                + "Pasajero: " + nombre + "\n"
                + "Fecha y hora: " + fechaViaje + " " + horaSalida + "\n"
                + "Asiento: " + numeroAsiento + " " + "Tipo de pasajero: " + tipoPasajero + "\n"
                + "Precio: $" + String.format("%,.2f", precio) + "\n"
                + (esReserva ? "Estado: RESERVADO (Pendiente de pago)" : "Estado: CONFIRMADO "),
                (esReserva ? "Reserva" : "Pasaje") + "Registrado",
                JOptionPane.INFORMATION_MESSAGE
        );

        if (!esReserva) {
            int imprimirTicket = JOptionPane.showConfirmDialog(null,
                    "Deseas imprimir el ticket e pasaje ahora?",
                    "Imprimir Ticket",
                    JOptionPane.YES_NO_OPTION);

            if (imprimirTicket == JOptionPane.YES_OPTION) {
                imprimirTicketPorIndice(cantidadPasajeros--);
            }
        }

    }

    private static void reservarPasaje(boolean b) {

    }

    private static void confirmarReserva() {

        if (cantidadPasajeros == 0) {
            JOptionPane.showMessageDialog(null, "No hay pasajeros registrados para confirmar la reserva",
                    "Informacion",
                    JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        boolean hayReservas = false;

        for (int i = 0; i < cantidadPasajeros; i++) {
            if (reservados[i]) {
                hayReservas = true;
                break;
            }
        }

        if (!hayReservas) {
            JOptionPane.showMessageDialog(null, "No hay reservas pendientes par aconfirmar",
                    "Informacion",
                    JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        int contadorReservas = 0;
        for (int i = 0; i < cantidadPasajeros; i++) {
            if (reservados[i]) {
                contadorReservas++;
            }
        }

        String[] opcionesReservas = new String[contadorReservas];
        int[] indicesReservas = new int[contadorReservas];

        int j = 0;

        for (int i = 0; i < cantidadPasajeros; i++) {
            if (reservados[i]) {
                opcionesReservas[j] = (j + 1) + ". " + nombres[i] + " " + origenes[i] + " --> "
                        + destinos[i] + "(" + fechasViajes[i] + " " + horasSalida[i] + ") ";
                indicesReservas[j] = i;
                j++;
            }
        }

        String seleccion = (String) JOptionPane.showInputDialog(null,
                "Seleccione la reserva a confirmar: ",
                "Confirmar Reserva",
                JOptionPane.QUESTION_MESSAGE,
                null,
                opcionesReservas,
                opcionesReservas[0]);

        if (seleccion == null) {
            return;
        }

        int indiceSeleccion = Integer.parseInt(seleccion.split("\\.")[0]) - 1;
        int indiceReserva = indicesReservas[indiceSeleccion];

        int Confirmar = JOptionPane.showConfirmDialog(null,
                " Detalles de la reserva a continuar:\n\n "
                + "Pasajero: " + nombres[indiceReserva] + "\n"
                + "Identificacion: " + identificaciones[indiceReserva] + "\n" + "\n"
                + "Ruta: " + origenes[indiceReserva] + " --> " + destinos[indiceReserva] + "\n"
                + "Fecha Y hora: " + fechasViajes[indiceReserva] + " " + horasSalida[indiceReserva] + "\n"
                + "Asiento: " + numeroAsientos[indiceReserva] + "\n"
                + "Tipo de pasajero: " + tiposPasajero[indiceReserva] + "\n"
                + "Precio a pagar: $" + String.format("%,.2F", precios[indiceReserva]) + "\n\n"
                + "Desea confirmar esta reserva y proceder con el pago? ",
                "Confirmar Reserva",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );

        if (Confirmar != JOptionPane.YES_OPTION) {
            return;
        }

        reservados[indiceReserva] = false;

        JOptionPane.showMessageDialog(null,
                "Reserva confirmada exitosamente.\n"
                + "El pasaje ha  sido registrado como CONFIRMADO.",
                "Confirmacion Exitosa",
                JOptionPane.INFORMATION_MESSAGE);

        int imprimirTicket = JOptionPane.showConfirmDialog(null,
                "¿Desea imprimir el ticket de pasaje ahora?",
                "Imprimir Ticket",
                JOptionPane.YES_NO_OPTION);

        if (imprimirTicket == JOptionPane.YES_OPTION) {
            imprimirTicketPorIndice(indiceReserva);
        }

    }

    private static void verTodosLosPasajes() {

        if (cantidadPasajeros == 0) {
            JOptionPane.showMessageDialog(null,
                    "No hay pasajes registrados. ",
                    "Informacion",
                    JOptionPane.INFORMATION_MESSAGE);

            return;
        }

        StringBuilder tabla = new StringBuilder();
        tabla.append("PASAJES REGISTRADOS - TRANSPORTES COFLONORTE\n\n");
        tabla.append(String.format("%-5s %-25s %-15s %-12s %-8s %-8s %-15s %-10s %-10s\n",
                "ID", "Pasajero", "Origen", "Destino", "Fecha", "Hora", "Asiento", "Tipo", "Precio", "Ëstado"));

        tabla.append("-----------------------------------------------------------------------------------------");
        for (int i = 0; i < cantidadPasajeros; i++) {
            tabla.append(String.format("%-5s %-25s %-15s %-12s %-8s %-8s %-15s %-10s %-10s\n",
                    i + 1,
                    nombres[i],
                    origenes[i],
                    destinos[i],
                    fechasViajes[i],
                    horasSalida[i],
                    numeroAsientos[i],
                    tiposPasajero[i],
                    String.format("%,.2f", precios[i]),
                    reservados[i] ? "RESERVADO" : "CONFIRMADO"));
        }

        double totalventas = 0;
        int pasajesConfirmados = 0;
        int pasajesReservados = 0;

        for (int i = 0; i < cantidadPasajeros; i++) {
            if (!reservados[i]) {
                totalventas += precios[i];
                pasajesConfirmados++;
            } else {
                pasajesReservados++;
            }
        }

        tabla.append("-----------------------------------------------------------------------------------------");
        tabla.append("Tala de pasajes: " + cantidadPasajeros + "  ( Confirmados: " + pasajesConfirmados + ", Reservados: " + pasajesReservados + ")\n");
        tabla.append("Total ventas confirmadas: $" + String.format("%2,.f", totalventas) + "\n");

        JOptionPane.showMessageDialog(null,
                new javax.swing.JScrollPane(new javax.swing.JTextArea(tabla.toString())),
                "Lista de pasajes",
                JOptionPane.PLAIN_MESSAGE);
    }

    private static void buscarPasaje() {

        if (cantidadPasajeros == 0) {
            JOptionPane.showMessageDialog(null, "No hay pasajes registrados para buscar.",
                    "Informacion",
                    JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        String[] opciones = {"Por identificacion", "Por nombre", "Por fecha", "Por ruta", "Solo reservas"};
        int opcionesBuusqueda = JOptionPane.showOptionDialog(null, "Seleccione los criterios de busqueda",
                "Buscar pasaje",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                opciones,
                opciones[0]
        );

        if (opcionesBuusqueda == -1) {
            return;
        }

        String criterioBusqueda = "";
        String origen = "";
        String destino = "";

        if (opcionesBuusqueda == 3) {
            String[] opcionesCiudades = {"Tunja", "Duitama", "Sogamoso", "Paipa", "Chiquinquirá",
                "Moniquirá", "Villa de Leyva", "Aquitania", "Tibasosa",
                "Bogotá", "Bucaramanga", "Villavicencio", "Otro"
            };

            origen = (String) JOptionPane.showInputDialog(null,
                    "Seleccione la ciudad de origen: ",
                    "Buscar pasaje",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    opcionesCiudades,
                    opcionesCiudades[0]);

            if (origen == null) {
                return;
            }

            destino = (String) JOptionPane.showInputDialog(null,
                    "Seleccione la ciudad de destino: ",
                    "Buscar pasaje",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    opcionesCiudades,
                    opcionesCiudades[0]);

            if (destino == null) {
                return;
            }
        } else if (opcionesBuusqueda != 4) {
            criterioBusqueda = JOptionPane.showInputDialog(null,
                    "Ingrese el " + (opcionesBuusqueda == 0 ? "Numero de identificacion"
                            : opcionesBuusqueda == 1 ? "Nombre" : "Fecha (AAAA,MM,DD)")
                    + "a buscar: ",
                    "Buscar Pasaje",
                    JOptionPane.QUESTION_MESSAGE
            );

            if (criterioBusqueda == null || criterioBusqueda.trim().isEmpty()) {
                return;
            }
        }

        StringBuilder resultados = new StringBuilder();
        resultados.append("PASAJES REGISTRADOS - TRANSPORTES COFLONORTE\n\n");
        resultados.append(String.format("%-5s %-25s %-15s %-12s %-8s %-8s %-15s %-10s %-10s\n",
                "ID", "Pasajero", "Origen", "Destino", "Fecha", "Hora", "Asiento", "Tipo", "Precio", "Ëstado"));

        resultados.append("-----------------------------------------------------------------------------------------");

        boolean encontrado = false;

        for (int i = 0; i < cantidadPasajeros; i++) {
            boolean coincide = false;

            switch (opcionesBuusqueda) {

                case 0:
                    coincide = identificaciones[i].toLowerCase().contains(criterioBusqueda.toLowerCase());
                    break;

                case 1:
                    coincide = nombres[i].toLowerCase().contains(criterioBusqueda.toLowerCase());
                    break;

                case 2:
                    coincide = fechasViajes[i].toLowerCase().contains(criterioBusqueda.toLowerCase());
                    break;

                case 3:
                    coincide = origenes[i].toLowerCase().contains(criterioBusqueda.toLowerCase());
                    break;

                case 4:
                    coincide = reservados[i];
                    break;

                case 5:
                    coincide = identificaciones[i].toLowerCase().contains(criterioBusqueda.toLowerCase());
                    break;

                default:
                    throw new AssertionError();
            }
            if (coincide) {
                resultados.append(String.format("%-5s %-25s %-15s %-12s %-8s %-8s %-15s %-10s %-10s\n",
                        i + 1,
                        nombres[i],
                        origenes[i],
                        destinos[i],
                        fechasViajes[i],
                        horasSalida[i],
                        numeroAsientos[i],
                        tiposPasajero[i],
                        String.format("%,.2f", precios[i]),
                        reservados[i] ? "RESERVADO" : "CONFIRMADO"));
                encontrado = true;
            }
        }

        if (encontrado) {

            JOptionPane.showMessageDialog(null,
                    new javax.swing.JScrollPane(new javax.swing.JTextArea(resultados.toString())),
                    "Lista pasajes ",
                    JOptionPane.PLAIN_MESSAGE);

        } else {
            JOptionPane.showMessageDialog(null, "No se encontraron pasajes que coincidan con la busqueda",
                    "Sin resultados",
                    JOptionPane.INFORMATION_MESSAGE);

        }

    }

    private static void modificarPasaje() {
        if (cantidadPasajeros == 0) {
            JOptionPane.showMessageDialog(null,
                    "No hay pasajes Registrados",
                    "Informacion",
                    JOptionPane.INFORMATION_MESSAGE);

            return;
        }

        String[] opcionesPasajes = new String[cantidadPasajeros];
        for (int i = 0; i < cantidadPasajeros; i++) {
            opcionesPasajes[i] = (i + i) + ". " + nombres[i] + " - " + origenes[i] + " → "
                    + destinos[i] + " (" + fechasViajes[i] + " " + horasSalida[i] + ") -"
                    + (reservados[i] ? "Reservado" : "Confirmado");
        }

        String seleccion = (String) JOptionPane.showInputDialog(null,
                "Seleccione el pasaje a modificar",
                "Modificar pasaje",
                JOptionPane.QUESTION_MESSAGE,
                null,
                opcionesPasajes,
                opcionesPasajes[0]);
        if (seleccion == null) {
            return;
        }
        int index = Integer.parseInt(seleccion.split("\\.")[0]) - 1;
        String infoActual = "Informacion actual del pasaje:\n"
                + "Pasajero: " + nombres[index] + "\n"
                + "Identificacion: " + identificaciones[index] + "\n"
                + "Ruta: " + origenes[index] + " → " + destinos[index] + "\n"
                + "Fecha y hora: " + fechasViajes[index] + " " + horasSalida[index] + "\n"
                + "Asiento: " + numeroAsientos[index] + "\n"
                + "Tipo pasajero " + tiposPasajero[index] + "\n"
                + "Estado: " + (reservados[index] ? "Reservado" : "Confirmado") + "\n"
                + "Precio: $" + String.format(",.2f", precios[index]);

        JOptionPane.showMessageDialog(null,
                infoActual,
                "Informacion Actual",
                JOptionPane.INFORMATION_MESSAGE);

        String[] opcionesModificacion = {
            "Nombre del pasajero",
            "Fecha de viaje",
            "Hora de salida",
            "Numero de asiento",
            "Tipo de pasajero",
            "Estado (Reserva/Confirmado)"
        };

        String selecionModificacion = (String) JOptionPane.showInputDialog(null,
                "Seleccione el campo a modificar",
                "Modificar pasaje",
                JOptionPane.QUESTION_MESSAGE,
                null,
                opcionesModificacion,
                opcionesModificacion[0]);

        if (selecionModificacion == null) {
            return;
        }

        switch (selecionModificacion) {

            case "Nombre Pasajero: ":
                String nuevoNombre = JOptionPane.showInputDialog(null,
                        "Ingrese el nuevo nombre del pasajero:",
                        "Modificar Pasaje",
                        JOptionPane.QUESTION_MESSAGE);
                if (nuevoNombre != null && !nuevoNombre.trim().isEmpty()) {
                    nombres[index] = nuevoNombre;
                }

                break;

            case "Fecha viaje: ":
                String nuevaFecha = JOptionPane.showInputDialog(null,
                        "Ingrese la nueva fecha del viaje (AAAA-MM-DD):",
                        "Modificar Pasaje",
                        JOptionPane.QUESTION_MESSAGE);
                if (nuevaFecha != null && !nuevaFecha.trim().isEmpty()) {
                    try {
                        LocalDate.parse(nuevaFecha);
                        fechasViajes[index] = nuevaFecha;
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null,
                                "Formato de fecha incorrecto.Use el siguiente formato (AAAA-MM-DD):",
                                "Error",
                                JOptionPane.QUESTION_MESSAGE);

                    }
                }

                break;

            case "Hora de salida ":
                String[] opcionesHorarios = {
                    "06:00", "07:00", "08:00", "09:00", "10:00", "11:00", "12:00",
                    "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00",
                    "20:00", "21:00"
                };

                String nuevaHora = (String) JOptionPane.showInputDialog(null,
                        "Seleccione la nueva hora de salida",
                        "Modificar pasaje",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        opcionesHorarios,
                        horasSalida[index]
                );

                if (nuevaHora != null) {
                    horasSalida[index] = nuevaHora;
                }

                break;

            case "Numero de asiento":
                try {
                    String nuevoAsientoStr = JOptionPane.showInputDialog(null,
                            "Ingrese el nuevo numeo de asiento (1-40)",
                            "Modificar Pasaje",
                            JOptionPane.QUESTION_MESSAGE);

                    int nuevoAsiento = Integer.parseInt(nuevoAsientoStr);

                    if (nuevoAsiento < 1 || nuevoAsiento > 40) {
                        JOptionPane.showMessageDialog(null,
                                "Numero de asiento Invalido debe de estar entre 1 y 40",
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                    } else {
                        boolean asientoOcupado = false;

                        for (int i = 0; i < cantidadPasajeros; i++) {
                            if (i != index
                                    && fechasViajes[i].equals(fechasViajes[index])
                                    && horasSalida[i].equals(horasSalida[index])
                                    && origenes[i].equals(origenes[index])
                                    && destinos[i].equals(destinos[index])
                                    && numeroAsientos[i] == nuevoAsiento) {

                                asientoOcupado = true;
                                break;
                            }

                        }

                        if (asientoOcupado) {
                            JOptionPane.showMessageDialog(null,
                                    "El asiento seleccionado ya esta ocupado para este viaje",
                                    "Error",
                                    JOptionPane.ERROR_MESSAGE);
                        } else {
                            numeroAsientos[index] = nuevoAsiento;
                        }
                    }
                } catch (Exception e) {

                    JOptionPane.showMessageDialog(null,
                            "Numero de asiento invalido. Ingrese un valor valido numerico.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);

                }
                break;

            case "Tipo de pasajero":
                String[] opcionesTipoPasajero = {
                    "Regular", "Estudiante", "Adulto Mayor"
                };

                String nuevoTipo = (String) JOptionPane.showInputDialog(
                        null,
                        "Seleccione el nuevo tipo de pasajero:",
                        "Modificar Pasaje",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        opcionesTipoPasajero,
                        tiposPasajero[index]
                );

                if (nuevoTipo != null) {
                    tiposPasajero[index] = nuevoTipo;

                    double precioBase = calcularPrecioBase(origenes[index], destinos[index]);

                    if (nuevoTipo.equals("Estudiante")) {
                        precios[index] = precioBase * 0.8;
                    } else if (nuevoTipo.equals("Adulto Mayor")) {
                        precios[index] = precioBase * 0.7;
                    } else {
                        precios[index] = precioBase;
                    }
                }
                break;

            case "Estado (Reservado/Confirmado)":
                String[] opcionesEstado = {"RESERVADO", "CONFIRMADO"
                };

                String nuevoEstado = (String) JOptionPane.showInputDialog(null,
                        "Seleccion el nuevo estado del pasaje",
                        "Modificar pasaje",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        opcionesEstado,
                        reservados[index] ? "RESERVADO" : "CONFIRMADO");

                if (nuevoEstado != null) {
                    reservados[index] = nuevoEstado.equals("RESERVADO");
                }

                break;

        }

        String infoNueva = "Información actualizada del pasaje:\n"
                + "Pasajero: " + nombres[index] + "\n\n"
                + "Identificación: " + identificaciones[index] + "\n"
                + "Ruta: " + origenes[index] + " → " + destinos[index] + "\n"
                + "Fecha y hora: " + fechasViajes[index] + " " + horasSalida[index] + "\n"
                + "Asiento: " + numeroAsientos[index] + "\n"
                + "Tipo de pasajero: " + tiposPasajero[index] + "\n"
                + "Estado: " + (reservados[index] ? "RESERVADO" : "CONFIRMADO") + "\n"
                + "Precio: $" + String.format("%.2f", precios[index]);

        JOptionPane.showMessageDialog(
                null,
                infoNueva,
                "Pasaje Actualizado",
                JOptionPane.INFORMATION_MESSAGE );

    }

    private static void cancelarPasaje() {

    }

    private static void imprimirTicketPasaje() {

    }

    // Generar 
    private static String generarCodigoPasaje(String origen, String destino, int indice) {

        String prefijo = origen.substring(0, Math.min(3, origen.length())).toUpperCase()
                + destino.substring(0, Math.min(3, destino.length())).toUpperCase();

        String fechaActual = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        return prefijo + fechaActual + String.format("%03d", indice + 1);

    }

    //Precio Base 
    private static double calcularPrecioBase(String origen, String destino) {
        if ((origen.equals("Tunja") && destino.equals("Bogotá")
                || origen.equals("Bogotá") && destino.equals("Tunja"))) {

            return 35000.0;

        } else if ((origen.equals("Duitama") && destino.equals("Bogotá")
                || origen.equals("Bogotá") && destino.equals("Duitama"))) {
            return 42000.0;

        } else if ((origen.equals("Sogamoso") && destino.equals("Bogotá")
                || origen.equals("Bogotá") && destino.equals("Sogamoso"))) {
            return 42000.0;
        } else if ((origen.equals("Tunja") && destino.equals("Duitama")
                || origen.equals("Duitama") && destino.equals("Tunja"))) {
            return 15000.0;

        } else if ((origen.equals("Duitama") && destino.equals("Sogamoso")
                || origen.equals("Sogamoso") && destino.equals("Duitama"))) {
            return 12000.0;
        } else if ((origen.equals("Tunja") && destino.equals("Sogamoso")
                || origen.equals("Sogamoso") && destino.equals("Tunja"))) {
            return 22000.0;
        } else if ((origen.equals("Paipa") && destino.equals("Tunja")
                || origen.equals("Tunja") && destino.equals("Paipa"))) {
            return 18000.0;
        }

        return 25000;
    }

    private static void imprimirTicketPorIndice(int indiceReserva) {
        throw new UnsupportedOperationException("Not supported yet."); // GenodBody
    }

}
