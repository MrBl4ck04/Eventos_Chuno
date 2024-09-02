package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Model.Conferencia;
import Model.ConferenciaDAO;

public class ViewEditarConferenciaForm extends JFrame {
    private static final long serialVersionUID = 1L;

    private JTextField txtTitulo;
    private JTextField txtDescripcion;
    private JTextField txtFechaInicio;
    private JTextField txtFechaFin;
    private JTextField txtTema;
    private JTextField txtMarca;
    private Conferencia conferencia;

    public ViewEditarConferenciaForm(Conferencia conferencia) {
        this.conferencia = conferencia;
        
        setTitle("Editar Conferencia");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        JPanel panel = new JPanel(new GridLayout(7, 2, 10, 10));
        getContentPane().add(panel, BorderLayout.CENTER);
        
        panel.add(new JLabel("Título:"));
        txtTitulo = new JTextField(conferencia.getTitulo());
        panel.add(txtTitulo);
        
        panel.add(new JLabel("Descripción:"));
        txtDescripcion = new JTextField(conferencia.getDescripcion());
        panel.add(txtDescripcion);
        
        panel.add(new JLabel("Fecha Inicio:"));
        txtFechaInicio = new JTextField(conferencia.getFechaInicio());
        panel.add(txtFechaInicio);
        
        panel.add(new JLabel("Fecha Fin:"));
        txtFechaFin = new JTextField(conferencia.getFechaFin());
        panel.add(txtFechaFin);
        
        panel.add(new JLabel("Tema:"));
        txtTema = new JTextField(conferencia.getTema());
        panel.add(txtTema);
        
        panel.add(new JLabel("Marca:"));
        txtMarca = new JTextField(conferencia.getMarca());
        panel.add(txtMarca);

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarCambios();
            }
        });

        panel.add(btnGuardar);
    }

    private void guardarCambios() {
        if (conferencia != null) {  // Verifica que el objeto no sea null
            conferencia.setTitulo(txtTitulo.getText());
            conferencia.setDescripcion(txtDescripcion.getText());
            conferencia.setFechaInicio(txtFechaInicio.getText());
            conferencia.setFechaFin(txtFechaFin.getText());
            conferencia.setTema(txtTema.getText());
            conferencia.setMarca(txtMarca.getText());

            ConferenciaDAO conferenciaDAO = new ConferenciaDAO();
            if (conferenciaDAO.actualizarConferencia(conferencia)) {
                JOptionPane.showMessageDialog(this, "Conferencia actualizada exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Error al actualizar la conferencia.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Error: La conferencia no está inicializada.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}