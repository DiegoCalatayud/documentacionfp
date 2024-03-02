package com.engiri.vista;

import javax.swing.*;

public class JFEmpleados extends JFrame{
    private JPanel pnSuperior;
    private JPanel pnCentral;
    private JPanel pnInferior;
    private JPanel pnPrincipal;
    private JPanel pnIzquierda;
    private JPanel pnDerecha;
    public JTextField txtDni;
    public JTextField txtNombre;
    public JTextField txtApellidos;
    public JTextField txtEdad;
    public JTextField txtSalario;
    public JButton btnInsertar;
    public JButton btnBorrar;
    public JButton btnListar;
    public JTable jtEmpleados;

    public JFEmpleados(){
        setContentPane(pnPrincipal);
        setTitle("ADT5 - Práctica 1");
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
