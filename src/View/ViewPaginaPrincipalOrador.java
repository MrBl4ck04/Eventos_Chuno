package View;

import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Image;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.SwingConstants;

// Clase personalizada TransparentLabel para manejar transparencia
class TransparentLabel extends JLabel {
    private static final long serialVersionUID = 1L;
    private float transparency = 1.0f; // Transparencia completa por defecto

    public TransparentLabel(ImageIcon icon) {
        super(icon);
    }

    public void setTransparency(float transparency) {
        this.transparency = transparency;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setComposite(AlphaComposite.SrcOver.derive(transparency));
        super.paintComponent(g2d);
        g2d.dispose();
    }
}

public class ViewPaginaPrincipalOrador extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ViewPaginaPrincipalOrador frame = new ViewPaginaPrincipalOrador();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public ViewPaginaPrincipalOrador() {
        setBackground(new Color(255, 255, 255));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 618, 339);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 255, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JPanel POpanel = new JPanel();
        POpanel.setBackground(new Color(255, 255, 255));
        POpanel.setBounds(0, 0, 602, 300);
        contentPane.add(POpanel);
        POpanel.setLayout(null);
        
        // Cargar la imagen original para el fondo
        
        // Luego añadir los otros componentes
        JLabel lblTitulo = new JLabel("EVENTOS CHUNO (ORADOR)");
        lblTitulo.setBackground(new Color(255, 255, 255));
        lblTitulo.setFont(new Font("Lucida Sans Typewriter", Font.BOLD, 18));
        lblTitulo.setBounds(10, 6, 291, 27);
        POpanel.add(lblTitulo);
        
        // Configuración del botón de notificación
        JButton btnNotificacion = new JButton("");
        btnNotificacion.setBackground(new Color(255, 255, 255));
        btnNotificacion.setBounds(479, 6, 44, 41);
        
        // Redimensiona la imagen del botón
        ImageIcon originalButtonIcon = new ImageIcon(ViewPaginaPrincipalOrador.class.getResource("/View/notificacion.png"));
        Image scaledButtonImage = originalButtonIcon.getImage().getScaledInstance(btnNotificacion.getWidth(), btnNotificacion.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledButtonIcon = new ImageIcon(scaledButtonImage);
        btnNotificacion.setIcon(scaledButtonIcon);
        
        // Quitar borde del botón
        btnNotificacion.setBorderPainted(false);
        btnNotificacion.setFocusPainted(false);
        POpanel.add(btnNotificacion);
        
        // Configuración del botón de usuario
        JButton btnUsuario = new JButton("");
        btnUsuario.setBackground(new Color(255, 255, 255));
        btnUsuario.setBounds(533, 6, 44, 41);
        
        // Redimensiona la imagen del botón
        ImageIcon originalUserIcon = new ImageIcon(ViewPaginaPrincipalOrador.class.getResource("/View/usuarioPrincipal.png"));
        Image scaledUserImage = originalUserIcon.getImage().getScaledInstance(btnUsuario.getWidth(), btnUsuario.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledUserIcon = new ImageIcon(scaledUserImage);
        btnUsuario.setIcon(scaledUserIcon);
        
        // Quitar borde del botón
        btnUsuario.setBorderPainted(false);
        btnUsuario.setFocusPainted(false);
        POpanel.add(btnUsuario);
        
        // Configuración del botón de historial
        JButton btnHistorial = new JButton("");
        btnHistorial.setBackground(new Color(255, 255, 255));
        btnHistorial.setBounds(57, 107, 118, 105);

        // Redimensiona la imagen del botón
        ImageIcon originaHistorialIcon = new ImageIcon(ViewPaginaPrincipalOrador.class.getResource("/View/expediente.png"));
        Image scaledHistorialImage = originaHistorialIcon.getImage().getScaledInstance(btnHistorial.getWidth(), btnHistorial.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledHistorialIcon = new ImageIcon(scaledHistorialImage);
        btnHistorial.setIcon(scaledHistorialIcon);

        // Quitar borde del botón
        btnHistorial.setBorderPainted(false);
        btnHistorial.setFocusPainted(false);
        POpanel.add(btnHistorial);

        // Configuración del botón de próximos
        JButton btnProximos = new JButton("");
        btnProximos.setBackground(new Color(255, 255, 255));
        btnProximos.setBounds(247, 107, 118, 105);

        // Redimensiona la imagen del botón
        ImageIcon originaProximosIcon = new ImageIcon(ViewPaginaPrincipalOrador.class.getResource("/View/mas-reciente.png"));
        Image scaledProximosImage = originaProximosIcon.getImage().getScaledInstance(btnProximos.getWidth(), btnProximos.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledProximosIcon = new ImageIcon(scaledProximosImage);
        btnProximos.setIcon(scaledProximosIcon);

        // Quitar borde del botón
        btnProximos.setBorderPainted(false);
        btnProximos.setFocusPainted(false);
        POpanel.add(btnProximos);

        // Configuración del botón de nueva reunión
        JButton btnNuevaReunion = new JButton("");
        btnNuevaReunion.setBackground(new Color(255, 255, 255));
        btnNuevaReunion.setBounds(427, 107, 118, 105);

        // Redimensiona la imagen del botón
        ImageIcon originaNuevaReunionIcon = new ImageIcon(ViewPaginaPrincipalOrador.class.getResource("/View/mas.png"));
        Image scaledNuevaReunionImage = originaNuevaReunionIcon.getImage().getScaledInstance(btnNuevaReunion.getWidth(), btnNuevaReunion.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledNuevaReunionIcon = new ImageIcon(scaledNuevaReunionImage);
        btnNuevaReunion.setIcon(scaledNuevaReunionIcon);

        // Quitar borde del botón
        btnNuevaReunion.setBorderPainted(false);
        btnNuevaReunion.setFocusPainted(false);
        POpanel.add(btnNuevaReunion);
        
        JLabel lblHistorial = new JLabel("Historial de Eventos");
        lblHistorial.setFont(new Font("Dialog", Font.BOLD, 12));
        lblHistorial.setHorizontalAlignment(SwingConstants.CENTER);
        lblHistorial.setBounds(57, 218, 119, 22);
        POpanel.add(lblHistorial);

        JLabel lblProximos = new JLabel("Próximos Eventos");
        lblProximos.setFont(new Font("Dialog", Font.BOLD, 12));
        lblProximos.setHorizontalAlignment(SwingConstants.CENTER);
        lblProximos.setBounds(247, 218, 121, 22);
        POpanel.add(lblProximos);

        JLabel lblNuevaReunion = new JLabel("Nuevo Evento");
        lblNuevaReunion.setFont(new Font("Dialog", Font.BOLD, 12));
        lblNuevaReunion.setHorizontalAlignment(SwingConstants.CENTER);
        lblNuevaReunion.setBounds(427, 218, 121, 22);
        POpanel.add(lblNuevaReunion);
        
        ImageIcon originalIcon = new ImageIcon(ViewPaginaPrincipalOrador.class.getResource("/View/OperadorFondo.png"));
        
        // Redimensionar la imagen al tamaño deseado
        Image scaledImage = originalIcon.getImage().getScaledInstance(470, 300, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        
        
        // Usar TransparentLabel con la imagen redimensionada
        TransparentLabel lblImagenPrincipal = new TransparentLabel(scaledIcon);
        lblImagenPrincipal.setIcon(new ImageIcon(ViewPaginaPrincipalOrador.class.getResource("/View/Fondo.jpeg")));
        lblImagenPrincipal.setBackground(new Color(255, 255, 255));
        lblImagenPrincipal.setBounds(0, -14, 602, 314); // Ajusta las dimensiones del JLabel también
        lblImagenPrincipal.setTransparency(0.9f); // Establecer la transparencia deseada (0.5f es 50% transparente)
        POpanel.add(lblImagenPrincipal); // Añadir al panel primero
    }
}
