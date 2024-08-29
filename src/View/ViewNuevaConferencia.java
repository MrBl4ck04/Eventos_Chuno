package View;

import Model.Conferencia;
import Model.ConferenciaDAO;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ViewNuevaConferencia extends JFrame {
    private static final long serialUID = 1L;
    private JPanel contentPane;
    private int idUsuario;

    public ViewNuevaConferencia(int idUsuario) {
        this.idUsuario = idUsuario;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 600);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS)); // Organizar las tarjetas en vertical
        setContentPane(contentPane);

        // Obtener conferencias desde la base de datos
        ConferenciaDAO conferenciaDAO = new ConferenciaDAO();
        List<Conferencia> conferencias = conferenciaDAO.obtenerConferencias();

        // Crear tarjetas para cada conferencia
        for (Conferencia conferencia : conferencias) {
            JPanel card = createCard(conferencia);
            contentPane.add(card);
            contentPane.add(Box.createRigidArea(new Dimension(0, 10))); // Espacio entre tarjetas
        }

        JButton btnVolver = new JButton("Volver");
        btnVolver.addActionListener(e -> {
            ViewPaginaPrincipalAsistente mainView = new ViewPaginaPrincipalAsistente(idUsuario);
            mainView.setVisible(true);
            dispose(); // Cierra la ventana actual
        });
        contentPane.add(btnVolver);
    }

    private JPanel createCard(Conferencia conferencia) {
        JPanel card = new JPanel();
        card.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Borde de la tarjeta
        card.setLayout(new BorderLayout());

        // Etiqueta para el título de la conferencia
        JLabel lblTitulo = new JLabel(conferencia.getTitulo());
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        card.add(lblTitulo, BorderLayout.NORTH);

        // Panel para los botones
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

        // Botón de Información
        JButton btnInfo = new JButton("Información");
        btnInfo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mostrarInformacionConferencia(conferencia);
            }
        });

        // Botón de Unirse
        JButton btnUnirse = new JButton("Unirse");
        btnUnirse.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                unirseConferencia(conferencia.getIdConferencia());
            }
        });

        buttonPanel.add(btnInfo);
        buttonPanel.add(btnUnirse);

        card.add(buttonPanel, BorderLayout.SOUTH);

        return card;
    }

    // Método para mostrar la información de la conferencia en un cuadro de diálogo
    private void mostrarInformacionConferencia(Conferencia conferencia) {
        StringBuilder info = new StringBuilder();
        info.append("Título: ").append(conferencia.getTitulo()).append("\n");
        info.append("Descripción: ").append(conferencia.getDescripcion()).append("\n");
        info.append("Fecha de Inicio: ").append(conferencia.getFechaInicio()).append("\n");
        info.append("Fecha de Fin: ").append(conferencia.getFechaFin()).append("\n");
        info.append("Tema: ").append(conferencia.getTema()).append("\n");
        info.append("Marca: ").append(conferencia.getMarca()).append("\n");
        info.append("ID de Usuario: ").append(conferencia.getIdUsuario()).append("\n");
        info.append("Recursos: ").append(conferencia.getRecursos()).append("\n");
        info.append("ID de Sala: ").append(conferencia.getIdSala()).append("\n");
        info.append("Disponibilidad: ").append(conferencia.getDisponibilidad() == 1 ? "Disponible" : "No disponible").append("\n");
        info.append("Cupos: ").append(conferencia.getCupos()).append("\n");

        JOptionPane.showMessageDialog(this, info.toString(), "Información de la Conferencia", JOptionPane.INFORMATION_MESSAGE);
    }

    // Método para registrar al usuario en la conferencia
    private void unirseConferencia(int idConferencia) {
        ConferenciaDAO conferenciaDAO = new ConferenciaDAO();
        boolean exito = conferenciaDAO.registrarAsistencia(idUsuario, idConferencia);

        if (exito) {
            JOptionPane.showMessageDialog(this, "Te has unido a la conferencia exitosamente.", "Unirse a Conferencia", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Hubo un error al unirse a la conferencia.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                ViewNuevaConferencia frame = new ViewNuevaConferencia(1); // Suponiendo que se pasa el idUsuario 1
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
