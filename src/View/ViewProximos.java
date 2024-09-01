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

public class ViewProximos extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    public ViewProximos(int idUsuario) {
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

        // Envolver cardsPanel en un JScrollPane
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
                ViewPaginaPrincipalOrador brz = new ViewPaginaPrincipalOrador(idUsuario);
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
        List<Conferencia> conferencias = conferenciaDAO.obtenerProximasConferenciasPorOrador(idUsuario);

        for (Conferencia conferencia : conferencias) {
            BackgroundPanel cardPanel = new BackgroundPanel("/View/backTarjetas.png");
            cardPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            cardPanel.setLayout(new BoxLayout(cardPanel, BoxLayout.Y_AXIS));
            cardPanel.setBorder(new EmptyBorder(10, 10, 10, 10)); 

            Font cardFont = new Font("Tw Cen MT Condensed", Font.BOLD, 16);

            JLabel lblTitulo = new JLabel("Titulo: " + conferencia.getTitulo());
            lblTitulo.setFont(cardFont);

            JLabel lblDescripcion = new JLabel("Descripción: " + conferencia.getDescripcion());
            lblDescripcion.setFont(cardFont);

            JLabel lblFecha = new JLabel("Fecha: " + conferencia.getFechaInicio() + " - " + conferencia.getFechaFin());
            lblFecha.setFont(cardFont);

            JLabel lblTema = new JLabel("Tema: " + conferencia.getTema());
            lblTema.setFont(cardFont);

            JLabel lblMarca = new JLabel("Marcas: " + conferencia.getMarca());
            lblMarca.setFont(cardFont);

            cardPanel.add(lblTitulo);
            cardPanel.add(lblDescripcion);
            cardPanel.add(lblFecha);
            cardPanel.add(lblTema);
            cardPanel.add(lblMarca);

            // Agregar botón de sesiones si existen
            List<Sesion> sesiones = conferenciaDAO.obtenerSesionesPorConferencia(conferencia.getIdConferencia());
            if (!sesiones.isEmpty()) {
                JButton btnSesiones = new JButton("Ver Sesiones");
                btnSesiones.setFont(cardFont);
                btnSesiones.setBackground(new Color(64, 0, 128));
                btnSesiones.setForeground(Color.WHITE);

                btnSesiones.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        StringBuilder sesionesInfo = new StringBuilder();
                        for (Sesion sesion : sesiones) {
                            sesionesInfo.append("Sesión ID: ").append(sesion.getIdSesion())
                                        .append(", Fecha: ").append(sesion.getFecha())
                                        .append(", Inicio: ").append(sesion.getHoraInicio())
                                        .append(", Fin: ").append(sesion.getHoraFin())
                                        .append("\n");
                        }
                        JOptionPane.showMessageDialog(ViewProximos.this, sesionesInfo.toString(), "Sesiones", JOptionPane.INFORMATION_MESSAGE);
                    }
                });

                cardPanel.add(btnSesiones);
            }

            cardsPanel.add(cardPanel);
        }

        contentPane.revalidate();
        contentPane.repaint();
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
