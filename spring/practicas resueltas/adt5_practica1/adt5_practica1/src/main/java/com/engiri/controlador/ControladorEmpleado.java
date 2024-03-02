package com.engiri.controlador;

import com.engiri.modelo.Empleado;
import com.engiri.modelo.EmpleadoDAO;
import com.engiri.vista.JFEmpleados;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ControladorEmpleado implements ActionListener {

    JFEmpleados vista;
    EmpleadoDAO modelo;

    public ControladorEmpleado(JFEmpleados vistaEmpleado, EmpleadoDAO modeloEmpleado) {
        this.vista = vistaEmpleado;
        this.modelo = modeloEmpleado;

        this.vista.btnInsertar.addActionListener(this);
        this.vista.btnBorrar.addActionListener(this);
        this.vista.btnListar.addActionListener(this);

        //inicializarVista();
    }

    public void inicializarVista() {
        limpiarElementos();
        llenarTabla(vista.jtEmpleados);
    }

    public void limpiarElementos() {
        vista.txtDni.setText("");
        vista.txtNombre.setText("");
        vista.txtApellidos.setText("");
        vista.txtEdad.setText("");
        vista.txtSalario.setText("");
    }

    public void llenarTabla(JTable tablaD) {
        DefaultTableModel modeloT = new DefaultTableModel();
        tablaD.setModel(modeloT);

        modeloT.addColumn("DNI");
        modeloT.addColumn("NOMBRE");
        modeloT.addColumn("APELLIDOS");
        modeloT.addColumn("EDAD");
        modeloT.addColumn("SALARIO");

        Object[] columna = new Object[5];

        ArrayList lista = modelo.getAll();
        //ArrayList lista = modelo.getAllByName("pep");
        int numRegistros = lista.size();

        for (int i = 0; i < numRegistros; i++) {
            Empleado e = (Empleado) lista.get(i);
            columna[0] = e.getDni();
            columna[1] = e.getNombre();
            columna[2] = e.getApellidos();
            columna[3] = e.getEdad();
            columna[4] = e.getSalario();
            modeloT.addRow(columna);
        }

        // Propiedades
        //tablaD.setEnabled(false);
        tablaD.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        tablaD.getTableHeader().setReorderingAllowed(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == vista.btnInsertar) {

            String dni = vista.txtDni.getText();
            String nombre = vista.txtNombre.getText();
            String apellidos = vista.txtApellidos.getText();
            int edad = Integer.parseInt(vista.txtEdad.getText());
            Float salario = Float.parseFloat(vista.txtSalario.getText());

            Empleado empleado = new Empleado(dni, nombre, apellidos, edad, salario);

            int numRegistros = 0;

            numRegistros = modelo.insert(empleado);

            if (numRegistros != 0) {
                JOptionPane.showMessageDialog(null, "Actualizados o guardados: " + numRegistros);
            } else {
                JOptionPane.showMessageDialog(null, "Error: no se han guardado los datos.");
            }

            inicializarVista();
        }// Botón Guardar

        if (e.getSource() == this.vista.btnBorrar) {

            String dni = vista.txtDni.getText();
            Empleado empleado = new Empleado();
            empleado.setDni(dni);

            int numRegistros = 0;
            numRegistros = modelo.delete(empleado);

            if (numRegistros != 0) {
                JOptionPane.showMessageDialog(null, "Borrado: " + numRegistros);
            } else {
                JOptionPane.showMessageDialog(null, "Error: no se han borrado los datos.");
            }

            inicializarVista();
        } // Botón Elimina

        if (e.getSource() == this.vista.btnListar) {
            inicializarVista();
        }
    }

}

