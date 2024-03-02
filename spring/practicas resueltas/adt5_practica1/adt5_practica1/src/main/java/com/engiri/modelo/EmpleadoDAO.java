package com.engiri.modelo;

import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;

public class EmpleadoDAO implements InterfaceDAO {

    Session session = null;

    public EmpleadoDAO() {

        session = HibernateUtil.getSession();
    }

    @Override
    public int insert(Object obj) {
        Empleado emp = (Empleado) obj;
        int numFAfectadas = 1;

        session = HibernateUtil.getSession();
        session.beginTransaction();
        session.persist(emp);
        session.getTransaction().commit();
        session.close();

        return numFAfectadas;
    }

    @Override
    public int update(Object obj) {
        Empleado emp = (Empleado) obj;
        int numFAfectadas = 0;

        session = HibernateUtil.getSession();
        session.beginTransaction();
        session.merge(emp);
        session.getTransaction().commit();
        session.close();

        return numFAfectadas;
    }

    @Override
    public int delete(Object obj) {
        Empleado emp = (Empleado) obj;
        int numFAfectadas = 1;

        session = HibernateUtil.getSession();
        session.beginTransaction();
        session.remove(emp);
        session.getTransaction().commit();
        session.close();

        return numFAfectadas;
    }

    @Override
    public Object search(Object obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList getAll() {
        ArrayList<Empleado> lista;

        session = HibernateUtil.getSession();
        session.beginTransaction();

        Query<Empleado> query = session.createQuery("SELECT e FROM Empleado e", Empleado.class);
        lista = (ArrayList<Empleado>) query.list();

        for (Empleado empleado : lista) {
            System.out.println(empleado);
        }

        session.getTransaction().commit();
        session.close();


        return lista;
    }

    @Override
    public ArrayList getAllByName(Object obj) {
        String busqueda = (String) obj;
        ArrayList<Empleado> lista;

        session = HibernateUtil.getSession();
        session.beginTransaction();

        Query<Empleado> query = session.createQuery("FROM Empleado e WHERE e.nombre LIKE :nombre", Empleado.class);
        query.setParameter("nombre", '%' + busqueda + '%');
        lista = (ArrayList<Empleado>) query.list();

        session.getTransaction().commit();
        session.close();

        return lista;
    }

    @Override
    public int getCount() {
        Long total = 0L;

        session = HibernateUtil.getSession();
        session.beginTransaction();

        Query<Long> query = session.createQuery("SELECT COUNT(*) FROM Empleado", Long.class);
        total = query.uniqueResult();

        session.getTransaction().commit();
        session.close();

        return Integer.parseInt(total.toString());
    }
}
