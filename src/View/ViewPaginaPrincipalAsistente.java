package View;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.Color;

public class ViewPaginaPrincipalAsistente extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    private JButton btnNotificacion;
    private JButton btnUsuario;
    private JButton btnHistorial;
    private JButton btnProximasConferencias;
    private JButton btnNuevaReunion;

    private JLabel lblHistorial;
    private JLabel lblProximos;
    private JLabel lblNuevaReunion;
    private int idUsuario;

    public ViewPaginaPrincipalAsistente(int idUsuario) {
        this.idUsuario = idUsuario;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 661, 432);

        contentPane = new BackgroundPanel("/View/backInicio.gif");
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTitulo = new JLabel("Bienvenido usuario estas como:");
        lblTitulo.setForeground(new Color(255, 255, 255));
        lblTitulo.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 18));
        lblTitulo.setBounds(10, 6, 291, 27);
        contentPane.add(lblTitulo);

        btnNotificacion = new JButton("");
        btnNotificacion.setBounds(539, 6, 44, 41);
        setButtonIcon(btnNotificacion, "/View/notificacion.png");
        contentPane.add(btnNotificacion);
        btnNotificacion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Crear y mostrar la ventana de notificaciones
                ViewNotificacion ventanaNotificaciones = new ViewNotificacion();
                ventanaNotificaciones.setVisible(true);
                dispose();
            }
        });

        btnUsuario = new JButton("");
        btnUsuario.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        btnUsuario.setFocusable(false);
        btnUsuario.setFocusTraversalKeysEnabled(false);
        btnUsuario.setFocusPainted(false);
        btnUsuario.setContentAreaFilled(false); // Sin fondo
        btnUsuario.setBorderPainted(false); // Sin borde
        btnUsuario.setIcon(new ImageIcon(ViewPaginaPrincipalAsistente.class.getResource("/View/usuario (1).png")));
        btnUsuario.setBounds(593, 6, 44, 41);
        setButtonIcon(btnUsuario, "/View/usuario (1).png");
        contentPane.add(btnUsuario);

        btnHistorial = new JButton("");
        btnHistorial.setIcon(new ImageIcon(ViewPaginaPrincipalAsistente.class.getResource("/View/lista-de-verificacion.png")));
        btnHistorial.setBounds(57, 81, 143, 131);
        setButtonIcon(btnHistorial, "/View/lista-de-verificacion.png");
        contentPane.add(btnHistorial);

        btnProximasConferencias = new JButton("");
        btnProximasConferencias.setIcon(new ImageIcon(ViewPaginaPrincipalAsistente.class.getResource("/View/calendario.png")));
        btnProximasConferencias.setBounds(234, 81, 153, 137);
        setButtonIcon(btnProximasConferencias, "/View/calendario.png");
        contentPane.add(btnProximasConferencias);

        btnNuevaReunion = new JButton("");
        btnNuevaReunion.setForeground(new Color(255, 255, 255));
        btnNuevaReunion.setBackground(new Color(255, 255, 255));
        btnNuevaReunion.setIcon(new ImageIcon(ViewPaginaPrincipalAsistente.class.getResource("/View/agregar-archivo.png")));
        btnNuevaReunion.setBounds(424, 81, 134, 137);
        setButtonIcon(btnNuevaReunion, "/View/agregar-archivo.png");
        contentPane.add(btnNuevaReunion);

        lblHistorial = new JLabel("Historial de Conferencias");
        lblHistorial.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 15));
        lblHistorial.setHorizontalAlignment(SwingConstants.CENTER);
        lblHistorial.setBounds(50, 223, 143, 22);
        contentPane.add(lblHistorial);

        lblProximos = new JLabel("Próximas Conferencias");
        lblProximos.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 15));
        lblProximos.setHorizontalAlignment(SwingConstants.CENTER);
        lblProximos.setBounds(243, 223, 134, 22);
        contentPane.add(lblProximos);

        lblNuevaReunion = new JLabel("Nueva Conferencia");
        lblNuevaReunion.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 15));
        lblNuevaReunion.setHorizontalAlignment(SwingConstants.CENTER);
        lblNuevaReunion.setBounds(424, 223, 121, 22);
        contentPane.add(lblNuevaReunion);

        JLabel lblAsistente = new JLabel("ASISTENTE");
        lblAsistente.setForeground(Color.WHITE);
        lblAsistente.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 30));
        lblAsistente.setBounds(243, 22, 144, 27);
        contentPane.add(lblAsistente);

        // Añadir ComponentListener para ajustar los componentes al cambiar el tamaño
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                resizeComponents();
            }
        });

        // Acción para abrir la ventana de "Nueva Conferencia"
        btnNuevaReunion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ViewNuevaConferencia nuevaConferencia = new ViewNuevaConferencia(idUsuario);
                nuevaConferencia.setVisible(true);
                dispose(); // Cierra la ventana actual
            }
        });
        
        btnProximasConferencias.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ViewProximosAsistente viewp = new ViewProximosAsistente(idUsuario);
                viewp.setVisible(true);
                dispose();
            }
        });

        // Acción para abrir la ventana de "Historial de Asistencias"
        btnHistorial.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	ViewHistorialAsistente historialAsistencias = new ViewHistorialAsistente(idUsuario);
                historialAsistencias.setVisible(true);
                dispose(); // Cierra la ventana actual
            }
        });
    }

    private void setButtonIcon(JButton button, String resourcePath) {
        ImageIcon originalIcon = new ImageIcon(ViewPaginaPrincipalAsistente.class.getResource(resourcePath));
        Image scaledImage = originalIcon.getImage().getScaledInstance(button.getWidth(), button.getHeight(), Image.SCALE_SMOOTH);
        button.setIcon(new ImageIcon(scaledImage));
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
    }

    private void resizeComponents() {
        int width = getWidth();
        int height = getHeight();

        // Ajustar la posición y el tamaño de los botones y etiquetas en base al tamaño de la ventana
        btnNotificacion.setBounds(width - 139, 6, 44, 41);
        btnUsuario.setBounds(width - 85, 6, 44, 41);

        btnHistorial.setBounds((int) (width * 0.1), (int) (height * 0.3), 118, 105);
        btnProximasConferencias.setBounds((int) (width * 0.4), (int) (height * 0.3), 118, 105);
        btnNuevaReunion.setBounds((int) (width * 0.7), (int) (height * 0.3), 118, 105);

        lblHistorial.setBounds((int) (width * 0.1 - 20), (int) (height * 0.65), 143, 22);
        lblProximos.setBounds((int) (width * 0.4 - 20), (int) (height * 0.65), 134, 22);
        lblNuevaReunion.setBounds((int) (width * 0.7 - 20), (int) (height * 0.65), 121, 22);
    }
}

class BackgroundPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    private Image backgroundImage;

    public BackgroundPanel(String fileName) {
        backgroundImage = new ImageIcon(ViewPaginaPrincipalAsistente.class.getResource(fileName)).getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}
