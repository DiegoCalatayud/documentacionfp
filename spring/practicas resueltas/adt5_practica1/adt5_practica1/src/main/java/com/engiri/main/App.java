package com.engiri.main;

import com.engiri.controlador.ControladorEmpleado;
import com.engiri.modelo.EmpleadoDAO;
import com.engiri.vista.JFEmpleados;

public class App {
    public static void main(String[] args) {

        JFEmpleados vista = new JFEmpleados();
        EmpleadoDAO modelo = new EmpleadoDAO();

        ControladorEmpleado ce = new ControladorEmpleado(vista, modelo);

        // Centrar en el centro de la pantalla
        vista.setLocationRelativeTo(null);
        vista.setVisible(true);
    }
}