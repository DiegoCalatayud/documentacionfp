package com.engiri;

import org.hibernate.Session;
import org.hibernate.query.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class App {

    public static void menu() {
        System.out.println("\n");
        System.out.println("==========================================================");
        System.out.println("========================== MENÚ ==========================");
        System.out.println("1.- Ejercicio1 - Insertar en la entidad Seguro.");
        System.out.println("2.- Ejercicio1 - Actualizar en la entidad Seguro.");
        System.out.println("3.- Ejercicio1 - Borrar en la entidad Seguro.");
        System.out.println("4.- Ejercicio1 - Leer de la entidad Seguro.");
        System.out.println("5.- Ejercicio2 - Creación entidad AsistenciaMedica.");
        System.out.println("6.- Ejercicio3 - Consultas a la base de datos.");
        System.out.println("7.- Salir.");
        System.out.println("==========================================================");
        System.out.println("==========================================================");
        System.out.print("Opción? ");
    }

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        boolean salir = false;

        while (!salir) {

            menu();
            String opcion = in.nextLine();

            switch (opcion) {
                case "1":
                    System.out.println("\t1.- Ejercicio1 - Insertar en la entidad Seguro.");

                    Seguro seguro1_1 = new Seguro(1, "12345678Z", "Carlos", "Perez", "Olmo", 54, 1, LocalDate.now());
                    Seguro seguro1_2 = new Seguro(2, "87654321A", "Pepe", "Lopez", "Rubio", 24, 3, LocalDate.now());

                    // Da de alta el objeto en la tabla de datos
                    Session session1 = HibernateUtil.getSession();
                    session1.beginTransaction();

                    session1.persist(seguro1_1);
                    session1.persist(seguro1_2);

                    session1.getTransaction().commit();
                    session1.close();

                    break;
                case "2":
                    System.out.println("\t2.- Ejercicio1 - Actualizar en la entidad Seguro.");
                    Seguro seguro2 = new Seguro(1, "12345678Z", "Cales", "Perez", "Olmo", 54, 1, LocalDate.now());

                    // Actualiza el objeto en la tabla de datos
                    Session session2 = HibernateUtil.getSession();
                    session2.beginTransaction();

                    session2.merge(seguro2);

                    session2.getTransaction().commit();
                    session2.close();
                    break;

                case "3":
                    System.out.println("\t3.- Ejercicio1 - Borrar en la entidad Seguro.");
                    Seguro seguro3 = new Seguro();
                    seguro3.setIdSeguro(1);

                    // Borra el objeto en la tabla de datos
                    Session session3 = HibernateUtil.getSession();
                    session3.beginTransaction();

                    session3.remove(seguro3);

                    session3.getTransaction().commit();
                    session3.close();
                    break;

                case "4":
                    System.out.println("\t4.- Ejercicio1 - Leer de la entidad Seguro.");

                    Query<Seguro> query = HibernateUtil.getSession().createQuery("FROM Seguro", Seguro.class);
                    List<Seguro> seguros = query.list();

                    for (Seguro seguroItem : seguros) {
                        System.out.println(seguroItem);
                    }

                    break;

                case "5":
                    System.out.println("\t5.- Ejercicio2 - Creación entidad AsistenciaMedica.");

                    Seguro seguro5_1 = new Seguro(321, "12345678Z", "Juan", "Cháfer", "Bellver", 66, 3, LocalDate.now());
                    AsistenciaMedica asistenciaMedica1 = new AsistenciaMedica(1, "Médico de cabecera", "Mislata", 800, seguro5_1);
                    AsistenciaMedica asistenciaMedica2 = new AsistenciaMedica(2, "Operación de bypass", "Sevilla", 4500, seguro5_1);

                    Seguro seguro5_2 = new Seguro(654, "48573562T", "Amparo", "Martínez", "López", 26, 0, LocalDate.now());
                    AsistenciaMedica asistenciaMedica3 = new AsistenciaMedica(3, "Médico de cabecera", "Valencia", 700, seguro5_2);
                    AsistenciaMedica asistenciaMedica4 = new AsistenciaMedica(4, "Operación de miopía", "Valencia", 4500, seguro5_2);
                    AsistenciaMedica asistenciaMedica5 = new AsistenciaMedica(5, "Operación estética", "Madrid", 14500, seguro5_2);

                    // Da de alta el objeto en la tabla de datos
                    Session session5 = HibernateUtil.getSession();
                    session5.beginTransaction();

                    session5.persist(asistenciaMedica1);
                    session5.persist(asistenciaMedica2);
                    session5.persist(asistenciaMedica3);
                    session5.persist(asistenciaMedica4);
                    session5.persist(asistenciaMedica5);

                    session5.getTransaction().commit();
                    session5.close();
                    break;

                case "6":
                    System.out.println("\t6.- Ejercicio3 - Consultas a la base de datos.");

                    Session session6 = HibernateUtil.getSession();

                    // Todos los seguros que hay en la base de datos.
                    System.out.println("6.1.- Todos los seguros que hay en la base de datos.");

                    Query<Seguro> query6_1 = session6.createQuery("FROM Seguro", Seguro.class);
                    List<Seguro> lista6_1 = query6_1.list();

                    for (Seguro seguroItem : lista6_1) {
                        System.out.println(seguroItem);
                    }

                    System.out.println("================================================================");

                    // Sólo las columnas NIF y Nombre de todos los seguros que hay en la base de datos.
                    System.out.println("6.2.- Sólo las columnas NIF y Nombre de todos los seguros que hay en la BD.");

                    Query<Object[]> query6_2 = session6.createQuery("SELECT nif, nombre FROM Seguro", Object[].class);
                    List<Object[]> lista6_2 = query6_2.list();

                    for (Object[] resultado : lista6_2) {
                        System.out.println("NIF: " + resultado[0] + " - Nombre: " + resultado[1]);
                    }

                    System.out.println("================================================================");

                    // Sólo el NIF para el seguro con nombre 'Juan Chafer Bellver'.
                    // Usa el método uniqueResult() y 3 parámetros con nombre para el nombre y los apellidos.
                    System.out.println("6.3.- Sólo el NIF para el seguro con nombre 'Juan Chafer Bellver'.");

                    Query<String> query6_3 = session6.createQuery("SELECT s.nif FROM Seguro AS s " +
                            "WHERE s.nombre = :nombre AND s.ape1 = :ape1 AND s.ape2 = :ape2", String.class);
                    query6_3.setParameter("nombre", "Juan");
                    query6_3.setParameter("ape1", "Chafer");
                    query6_3.setParameter("ape2", "Bellver");
                    String resultado6_3 = query6_3.uniqueResult();

                    System.out.println("NIF: " + resultado6_3);

                    System.out.println("================================================================");

                    // El idAsistenciaMedica de todas las asistencias médicas cuyo saldo esté entre 2.000 y 5.000 euros
                    // (utilizando parámetros por posición para los valores).
                    System.out.println("6.4.- El idAsistenciaMedica de todas las asistencias médicas cuyo saldo esté entre 2.000 y 5.000 euros.");

                    Query<Integer> query6_4 = session6.createQuery("SELECT idAsistenciaMedica FROM AsistenciaMedica AS s " +
                            "WHERE importe >= ?1 AND importe <= ?2", Integer.class);
                    query6_4.setParameter(1, 2000);
                    query6_4.setParameter(2, 5000);
                    List<Integer> lista6_4 = query6_4.list();

                    for (Integer numeroItem : lista6_4) {
                        System.out.println("AsistenciaMedica: (id) - " + numeroItem);
                    }

                    System.out.println("================================================================");

                    // Calcule la suma de todos los importes de todas las asistencias médicas.
                    System.out.println("6.5.- Calcule la suma de todos los importes de todas las asistencias médicas.");

                    Query<Double> query6_5 = session6.createQuery("SELECT SUM(importe) FROM AsistenciaMedica", Double.class);
                    Double resultado6_5 = query6_5.uniqueResult();

                    System.out.println("Importe total de todas las asistencias: " + resultado6_5);

                    System.out.println("================================================================");

                    // Calcule el saldo medio de todas las asistencias médicas.
                    System.out.println("6.6.- Calcule el saldo medio de todas las asistencias médicas.");

                    Query<Double> query6_6 = session6.createQuery("SELECT AVG(importe) FROM AsistenciaMedica", Double.class);
                    Double resultado6_6 = query6_6.uniqueResult();

                    System.out.println("Saldo medio de todas las asistencias: " + resultado6_6);

                    System.out.println("================================================================");

                    // Calcule cuántos seguros hay.
                    System.out.println("6.7.- Calcule cuántos seguros hay.");

                    Query<Long> query6_7 = session6.createQuery("SELECT COUNT(*) FROM Seguro", Long.class);
                    Long resultado6_7 = query6_7.uniqueResult();

                    System.out.println("El número de seguros es: " + resultado6_7);

                    System.out.println("================================================================");

                    // Muestre para cada seguro cuántas asistencias médicas posee.
                    System.out.println("6.8.- Muestre para cada seguro cuántas asistencias médicas posee.");

                    Query<Object[]> query6_8 = session6.createQuery("SELECT a.seguro.idSeguro, COUNT(*) " +
                            "FROM AsistenciaMedica AS a GROUP BY a.seguro.idSeguro", Object[].class);
                    List<Object[]> lista6_8 = query6_8.list();

                    for (Object[] resultado : lista6_8) {
                        System.out.println("Seguro ID: " + resultado[0] + " - Núm. asistencias: " + resultado[1]);
                    }

                    System.out.println("================================================================");

                    // Consulta sobre la tabla seguro pero usando una SQL Nativa de MySQL.
                    System.out.println("6.9.- Consulta sobre la tabla seguro pero usando una SQL Nativa de MySQL.");

                    Query<Object[]> query6_9 = session6.createNativeQuery("SELECT nif, fechaCreacion " +
                            "FROM seguro", Object[].class);
                    List<Object[]> lista6_9 = query6_9.list();

                    for (Object[] resultado : lista6_9) {
                        System.out.println("Seguro NIF: " + resultado[0] + " - Fecha creación: " + resultado[1]);
                    }

                    System.out.println("================================================================");

                    break;

                case "7":
                    System.out.println("Has salido de la aplicación.");
                    salir = true;
                    break;
            } // Fin del swicth

        } // Fin del while

        in.close();

    }// Fin del main

}


