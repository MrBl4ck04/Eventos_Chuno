package View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import Model.Conferencia;
import Model.ConferenciaDAO;

import java.awt.*;
import java.util.List;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ViewHistorialOrador extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ViewHistorialOrador frame = new ViewHistorialOrador();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public ViewHistorialOrador() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 548, 470);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout());  // Usar BorderLayout en lugar de GridLayout
        setContentPane(contentPane);

        JPanel cardsPanel = new JPanel();
        cardsPanel.setLayout(new GridLayout(0, 1, 10, 10));
        contentPane.add(cardsPanel, BorderLayout.CENTER);  // Añadir las tarjetas al centro

        // Crear un panel para el botón en la parte inferior
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT));  // Posicionar a la izquierda
        contentPane.add(buttonPanel, BorderLayout.SOUTH);  // Añadir el panel del botón al sur

        JButton btnVolver = new JButton("Volver");
        btnVolver.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		ViewPaginaPrincipalOrador brz = new ViewPaginaPrincipalOrador(getDefaultCloseOperation());
                brz.setVisible(true);
                dispose(); // Cierra la ventana actual
        	}
        });
        buttonPanel.add(btnVolver);  // Añadir el botón al panel del botón

        mostrarConferencias(cardsPanel);
    }

    private void mostrarConferencias(JPanel cardsPanel) {
        ConferenciaDAO conferenciaDAO = new ConferenciaDAO();
        List<Conferencia> conferencias = conferenciaDAO.obtenerConferencias();

        for (Conferencia conferencia : conferencias) {
            JPanel cardPanel = new JPanel();
            cardPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            cardPanel.setLayout(new BoxLayout(cardPanel, BoxLayout.Y_AXIS));

            JLabel lblTitulo = new JLabel("Titulo: " + conferencia.getTitulo());
            JLabel lblDescripcion = new JLabel("Descripción: " + conferencia.getDescripcion());
            JLabel lblFecha = new JLabel("Fecha: " + conferencia.getFechaInicio() + " - " + conferencia.getFechaFin());
            JLabel lblTema = new JLabel("Tema: " + conferencia.getTema());
            JLabel lblMarca = new JLabel("Marcas: " + conferencia.getMarca());

            cardPanel.add(lblTitulo);
            cardPanel.add(lblDescripcion);
            cardPanel.add(lblFecha);
            cardPanel.add(lblTema);
            cardPanel.add(lblMarca);

            cardsPanel.add(cardPanel);
        }

        contentPane.revalidate();
        contentPane.repaint();
    }
}
