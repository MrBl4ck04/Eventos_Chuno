package View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import Model.Conferencia;
import Model.ConferenciaDAO;
import Model.Sesion;

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

        setBounds(150, 150, 700, 600);  
        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 255, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout());  
        setContentPane(contentPane);

        JPanel cardsPanel = new JPanel();
        cardsPanel.setBackground(new Color(64, 0, 128));
        cardsPanel.setLayout(new GridLayout(0, 1, 10, 10));
        contentPane.add(cardsPanel, BorderLayout.CENTER); 
        
        JScrollPane scrollPane = new JScrollPane(cardsPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        contentPane.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(64, 0, 128));
        buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT));  
        contentPane.add(buttonPanel, BorderLayout.SOUTH); 

        JButton btnVolver = new JButton("Volver");
        btnVolver.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 16));
        btnVolver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ViewPaginaPrincipalOrador brz = new ViewPaginaPrincipalOrador(getDefaultCloseOperation());
                brz.setVisible(true);
                dispose(); 
            }
        });
        buttonPanel.add(btnVolver);  

        mostrarConferencias(cardsPanel);
        
        JLabel lblNewLabel = new JLabel("Historial de conferencias");
        lblNewLabel.setBackground(new Color(255, 255, 255));
        lblNewLabel.setForeground(new Color(64, 0, 128));
        lblNewLabel.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 26));
        contentPane.add(lblNewLabel, BorderLayout.NORTH);
    }

    private void mostrarConferencias(JPanel cardsPanel) {
        ConferenciaDAO conferenciaDAO = new ConferenciaDAO();
        List<Conferencia> conferencias = conferenciaDAO.obtenerConferencias();

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

            JButton btnEliminar = new JButton("Eliminar");
            btnEliminar.setFont(cardFont);
            btnEliminar.setBackground(new Color(64, 0, 128));
            btnEliminar.setForeground(Color.WHITE);

            btnEliminar.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    int respuesta = JOptionPane.showOptionDialog(
                        ViewHistorialOrador.this,
                        "¿Estás seguro de que quieres eliminar esta conferencia?",
                        "Confirmar eliminación",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        new Object[] {"Sí", "No"},
                        "No"
                    );

                    if (respuesta == 0) {
                        if (conferenciaDAO.eliminarConferencia(conferencia.getIdConferencia())) {
                            JOptionPane.showMessageDialog(ViewHistorialOrador.this, "Conferencia eliminada exitosamente.");
                            cardsPanel.remove(cardPanel);
                            cardsPanel.revalidate();
                            cardsPanel.repaint();
                        } else {
                            JOptionPane.showMessageDialog(ViewHistorialOrador.this, "Error al eliminar la conferencia.");
                        }
                    }
                }
            });

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
                        JOptionPane.showMessageDialog(ViewHistorialOrador.this, sesionesInfo.toString(), "Sesiones", JOptionPane.INFORMATION_MESSAGE);
                    }
                });

                cardPanel.add(btnSesiones);
            }

            cardPanel.add(lblTitulo);
            cardPanel.add(lblDescripcion);
            cardPanel.add(lblFecha);
            cardPanel.add(lblTema);
            cardPanel.add(lblMarca);
            cardPanel.add(btnEliminar);

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