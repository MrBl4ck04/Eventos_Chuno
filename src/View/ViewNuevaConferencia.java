package View;

import Model.Conferencia;
import Model.ConferenciaDAO;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
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

        // Crear el panel con imagen de fondo
        contentPane = new BackgroundPanel("/View/backNuevaC.png");
        contentPane.setBorder(new EmptyBorder(70, 5, 5, 5));
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        setContentPane(contentPane);

        contentPane.add(Box.createRigidArea(new Dimension(0, 20)));

        ConferenciaDAO conferenciaDAO = new ConferenciaDAO();
        List<Conferencia> conferencias = conferenciaDAO.obtenerConferencias();

        for (Conferencia conferencia : conferencias) {
            JPanel card = createCard(conferencia);
            contentPane.add(card);
            contentPane.add(Box.createRigidArea(new Dimension(0, 10)));
        }

        JButton btnVolver = new JButton("Volver");
        btnVolver.addActionListener(e -> {
            ViewPaginaPrincipalAsistente mainView = new ViewPaginaPrincipalAsistente(idUsuario);
            mainView.setVisible(true);
            dispose();
        });
        contentPane.add(btnVolver);
    }

    private JPanel createCard(Conferencia conferencia) {
        JPanel card = new JPanel();
        card.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        card.setLayout(new BorderLayout());

        JLabel lblTitulo = new JLabel(conferencia.getTitulo());
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        card.add(lblTitulo, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

        JButton btnInfo = new JButton("Información");
        btnInfo.addActionListener(e -> mostrarInformacionConferencia(conferencia));

        JButton btnUnirse = new JButton("Unirse");
        btnUnirse.addActionListener(e -> unirseConferencia(conferencia.getIdConferencia()));

        buttonPanel.add(btnInfo);
        buttonPanel.add(btnUnirse);

        card.add(buttonPanel, BorderLayout.SOUTH);

        return card;
    }

    private void mostrarInformacionConferencia(Conferencia conferencia) {
        JPanel infoPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);            }
        };
        infoPanel.setOpaque(false);
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));

        JPanel textPanel = new JPanel();
        textPanel.setOpaque(true);
        textPanel.setBackground(new Color(255, 255, 255, 180)); // Semi-transparent white
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        textPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        Font customFont = new Font("Tw Cen MT Condensed", Font.PLAIN, 10);
        textPanel.add(createInfoLabel("Título: ", conferencia.getTitulo()));
        textPanel.add(createInfoLabel("Descripción: ", conferencia.getDescripcion()));
        textPanel.add(createInfoLabel("Fecha de Inicio: ", conferencia.getFechaInicio().toString()));
        textPanel.add(createInfoLabel("Fecha de Fin: ", conferencia.getFechaFin().toString()));
        textPanel.add(createInfoLabel("Tema: ", conferencia.getTema()));
        textPanel.add(createInfoLabel("Marca: ", conferencia.getMarca()));
        textPanel.add(createInfoLabel("ID de Usuario: ", String.valueOf(conferencia.getIdUsuario())));
        textPanel.add(createInfoLabel("Recursos: ", conferencia.getRecursos()));
        textPanel.add(createInfoLabel("ID de Sala: ", String.valueOf(conferencia.getIdSala())));
        textPanel.add(createInfoLabel("Disponibilidad: ", conferencia.getDisponibilidad() == 1 ? "Disponible" : "No disponible"));
        textPanel.add(createInfoLabel("Cupos: ", String.valueOf(conferencia.getCupos())));

        infoPanel.add(textPanel);

        JOptionPane.showMessageDialog(this, infoPanel, "Información de la Conferencia", JOptionPane.INFORMATION_MESSAGE);
    }

    private JLabel createInfoLabel(String label, String value) {
        JLabel lbl = new JLabel(label + value);
        lbl.setFont(new Font("Arial", Font.PLAIN, 14));
        lbl.setForeground(Color.BLACK); 
        return lbl;
    }

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