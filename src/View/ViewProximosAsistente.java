package View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import Model.Conferencia;
import Model.ConferenciaDAO;
import Model.Sesion;
import Model.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ViewProximosAsistente extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    public ViewProximosAsistente(int idUsuario) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(150, 150, 700, 600);  
        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 255, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout());  
        setContentPane(contentPane);

        JPanel cardsPanel = new JPanel();
        cardsPanel.setBackground(new Color(64, 0, 128));
        cardsPanel.setLayout(new GridLayout(0, 1, 10, 10));

        JScrollPane scrollPane = new JScrollPane(cardsPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); 
        contentPane.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(64, 0, 128));
        buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT));  
        contentPane.add(buttonPanel, BorderLayout.SOUTH); 

        JButton btnVolver = new JButton("Volver");
        btnVolver.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 16));
        btnVolver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ViewPaginaPrincipalAsistente brz = new ViewPaginaPrincipalAsistente(idUsuario);
                brz.setVisible(true);
                dispose(); 
            }
        });
        buttonPanel.add(btnVolver);  

        mostrarProximasConferencias(cardsPanel, idUsuario);
        
        JLabel lblNewLabel = new JLabel("Próximas conferencias");
        lblNewLabel.setBackground(new Color(255, 255, 255));
        lblNewLabel.setForeground(new Color(64, 0, 128));
        lblNewLabel.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 26));
        contentPane.add(lblNewLabel, BorderLayout.NORTH);
    }

    private void mostrarProximasConferencias(JPanel cardsPanel, int idUsuario) {
        ConferenciaDAO conferenciaDAO = new ConferenciaDAO();
        List<Conferencia> conferencias = conferenciaDAO.obtenerProximasConferenciasPorAsistente(idUsuario);

        for (Conferencia conferencia : conferencias) {
            BackgroundPanel cardPanel = new BackgroundPanel("/View/backTarjetas.png");
            cardPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            cardPanel.setLayout(new BoxLayout(cardPanel, BoxLayout.Y_AXIS));
            cardPanel.setBorder(new EmptyBorder(10, 10, 10, 10)); 

            Font cardFont = new Font("Tw Cen MT Condensed", Font.BOLD, 16);

            JLabel lblTitulo = new JLabel("Título: " + conferencia.getTitulo());
            lblTitulo.setFont(cardFont);

            JLabel lblDescripcion = new JLabel("Descripción: " + conferencia.getDescripcion());
            lblDescripcion.setFont(cardFont);

            JLabel lblFecha = new JLabel("Fecha: " + conferencia.getFechaInicio() + " - " + conferencia.getFechaFin());
            lblFecha.setFont(cardFont);

            JLabel lblTema = new JLabel("Tema: " + conferencia.getTema());
            lblTema.setFont(cardFont);

            JLabel lblMarca = new JLabel("Marca: " + conferencia.getMarca());
            lblMarca.setFont(cardFont);

            cardPanel.add(lblTitulo);
            cardPanel.add(lblDescripcion);
            cardPanel.add(lblFecha);
            cardPanel.add(lblTema);
            cardPanel.add(lblMarca);

            // Verificar si existen sesiones
            List<Sesion> sesiones = conferenciaDAO.obtenerSesionesPorConferencia(conferencia.getIdConferencia());
            if (sesiones.isEmpty()) {
                // Mostrar la sala solo si no hay sesiones
                String numeroSala = obtenerNumeroSala(conferencia.getIdSala());
                JLabel lblSala = new JLabel("Sala: " + (numeroSala != null ? numeroSala : "Por asignar"));
                lblSala.setFont(cardFont);
                cardPanel.add(lblSala);
            } else {
                // Agregar botón de sesiones si existen
                JButton btnSesiones = new JButton("Ver Sesiones");
                btnSesiones.setFont(cardFont);
                btnSesiones.setBackground(new Color(64, 0, 128));
                btnSesiones.setForeground(Color.WHITE);

                btnSesiones.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        mostrarSesionesDialog(sesiones);
                    }
                });

                cardPanel.add(btnSesiones);
            }

            cardsPanel.add(cardPanel);
        }

        contentPane.revalidate();
        contentPane.repaint();
    }

    private void mostrarSesionesDialog(List<Sesion> sesiones) {
        JPanel panelSesiones = new JPanel();
        panelSesiones.setLayout(new BoxLayout(panelSesiones, BoxLayout.Y_AXIS));

        for (Sesion sesion : sesiones) {
            JPanel sesionPanel = new JPanel();
            sesionPanel.setLayout(new GridLayout(0, 1));
            sesionPanel.setBorder(BorderFactory.createTitledBorder("Sesión ID: " + sesion.getIdSesion()));

            JLabel lblFecha = new JLabel("Fecha: " + sesion.getFecha());
            JLabel lblHoraInicio = new JLabel("Hora de Inicio: " + sesion.getHoraInicio());
            JLabel lblHoraFin = new JLabel("Hora de Fin: " + sesion.getHoraFin());

            // Obtener información del número de la sala
            String numeroSala = obtenerNumeroSala(sesion.getIdSala());
            JLabel lblSala = new JLabel("Sala: " + (numeroSala != null ? numeroSala : "Por asignar"));

            sesionPanel.add(lblFecha);
            sesionPanel.add(lblHoraInicio);
            sesionPanel.add(lblHoraFin);
            sesionPanel.add(lblSala);

            panelSesiones.add(sesionPanel);
        }

        JScrollPane scrollPane = new JScrollPane(panelSesiones);
        scrollPane.setPreferredSize(new Dimension(500, 300));

        JOptionPane.showMessageDialog(this, scrollPane, "Sesiones", JOptionPane.INFORMATION_MESSAGE);
    }

    private String obtenerNumeroSala(int idSala) {
        if (idSala == 0) {
            return null;
        }
        String numeroSala = null;
        String query = "SELECT numero FROM sala WHERE id_sala = ?";
        try (Connection conn = new ConexionBD().getConexion();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
             
            pstmt.setInt(1, idSala); // Establecer el valor del parámetro antes de ejecutar la consulta

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    numeroSala = rs.getString("numero"); // Obtener el número de la sala
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener el número de la sala: " + e.getMessage());
        }
        return numeroSala;
    }

    private class BackgroundPanel extends JPanel {
        private Image backgroundImage;

        public BackgroundPanel(String fileName) {
            try {
                backgroundImage = new ImageIcon(getClass().getResource(fileName)).getImage();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (backgroundImage != null) {
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        }
    }
}