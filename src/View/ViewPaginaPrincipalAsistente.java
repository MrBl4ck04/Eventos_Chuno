package View;

import java.awt.EventQueue;
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

public class ViewPaginaPrincipalAsistente extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    private JButton btnNotificacion;
    private JButton btnUsuario;
    private JButton btnHistorial;
    private JButton btnProximos;
    private JButton btnNuevaReunion;

    private JLabel lblHistorial;
    private JLabel lblProximos;
    private JLabel lblNuevaReunion;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ViewPaginaPrincipalAsistente frame = new ViewPaginaPrincipalAsistente();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public ViewPaginaPrincipalAsistente() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 618, 339);

        contentPane = new BackgroundPanel("/View/Fondo.jpeg");
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTitulo = new JLabel("EVENTOS CHUNO (ASISTENTE)");
        lblTitulo.setFont(new Font("Lucida Sans Typewriter", Font.BOLD, 18));
        lblTitulo.setBounds(10, 6, 291, 27);
        contentPane.add(lblTitulo);

        btnNotificacion = new JButton("");
        btnNotificacion.setBounds(479, 6, 44, 41);
        setButtonIcon(btnNotificacion, "/View/notificacion.png");
        contentPane.add(btnNotificacion);

        btnUsuario = new JButton("");
        btnUsuario.setBounds(533, 6, 44, 41);
        setButtonIcon(btnUsuario, "/View/usuarioPrincipal.png");
        contentPane.add(btnUsuario);

        btnHistorial = new JButton("");
        btnHistorial.setBounds(57, 107, 118, 105);
        setButtonIcon(btnHistorial, "/View/expediente.png");
        contentPane.add(btnHistorial);

        btnProximos = new JButton("");
        btnProximos.setBounds(247, 107, 118, 105);
        setButtonIcon(btnProximos, "/View/mas-reciente.png");
        contentPane.add(btnProximos);

        btnNuevaReunion = new JButton("");
        btnNuevaReunion.setBounds(427, 107, 118, 105);
        setButtonIcon(btnNuevaReunion, "/View/ingresarcon.png");
        contentPane.add(btnNuevaReunion);

        lblHistorial = new JLabel("Historial de Conferencias");
        lblHistorial.setFont(new Font("Dialog", Font.BOLD, 12));
        lblHistorial.setHorizontalAlignment(SwingConstants.CENTER);
        lblHistorial.setBounds(50, 223, 143, 22);
        contentPane.add(lblHistorial);

        lblProximos = new JLabel("Próximas Conferencias");
        lblProximos.setFont(new Font("Dialog", Font.BOLD, 12));
        lblProximos.setHorizontalAlignment(SwingConstants.CENTER);
        lblProximos.setBounds(243, 223, 134, 22);
        contentPane.add(lblProximos);

        lblNuevaReunion = new JLabel("Nueva Conferencia");
        lblNuevaReunion.setFont(new Font("Dialog", Font.BOLD, 12));
        lblNuevaReunion.setHorizontalAlignment(SwingConstants.CENTER);
        lblNuevaReunion.setBounds(427, 218, 121, 22);
        contentPane.add(lblNuevaReunion);

        // Añadir ComponentListener para ajustar los componentes al cambiar el tamaño
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                resizeComponents();
            }
        });
    }

    private void setButtonIcon(JButton button, String resourcePath) {
        ImageIcon originalIcon = new ImageIcon(ViewPaginaPrincipalAsistente.class.getResource(resourcePath));
        Image scaledImage = originalIcon.getImage().getScaledInstance(button.getWidth(), button.getHeight(), Image.SCALE_SMOOTH);
        button.setIcon(new ImageIcon(scaledImage));
        button.setBorderPainted(false);
        button.setFocusPainted(false);
    }

    private void resizeComponents() {
        int width = getWidth();
        int height = getHeight();

        // Ajustar la posición y el tamaño de los botones y etiquetas en base al tamaño de la ventana
        btnNotificacion.setBounds(width - 139, 6, 44, 41);
        btnUsuario.setBounds(width - 85, 6, 44, 41);

        btnHistorial.setBounds((int) (width * 0.1), (int) (height * 0.3), 118, 105);
        btnProximos.setBounds((int) (width * 0.4), (int) (height * 0.3), 118, 105);
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
