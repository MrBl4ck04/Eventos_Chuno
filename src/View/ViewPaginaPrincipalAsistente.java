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
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;

public class ViewPaginaPrincipalAsistente extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

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

        JButton btnNotificacion = new JButton("");
        btnNotificacion.setBounds(479, 6, 44, 41);
        setButtonIcon(btnNotificacion, "/View/notificacion.png");
        contentPane.add(btnNotificacion);

        JButton btnUsuario = new JButton("");
        btnUsuario.setBounds(533, 6, 44, 41);
        setButtonIcon(btnUsuario, "/View/usuarioPrincipal.png");
        contentPane.add(btnUsuario);

        JButton btnHistorial = new JButton("");
        btnHistorial.setBounds(57, 107, 118, 105);
        setButtonIcon(btnHistorial, "/View/expediente.png");
        contentPane.add(btnHistorial);

        JButton btnProximos = new JButton("");
        btnProximos.setBounds(247, 107, 118, 105);
        setButtonIcon(btnProximos, "/View/mas-reciente.png");
        contentPane.add(btnProximos);

        JButton btnNuevaReunion = new JButton("");
        btnNuevaReunion.setBounds(427, 107, 118, 105);
        setButtonIcon(btnNuevaReunion, "/View/ingresarcon.png");
        contentPane.add(btnNuevaReunion);

        JLabel lblHistorial = new JLabel("Historial de Conferencias");
        lblHistorial.setFont(new Font("Dialog", Font.BOLD, 12));
        lblHistorial.setHorizontalAlignment(SwingConstants.CENTER);
        lblHistorial.setBounds(50, 223, 143, 22);
        contentPane.add(lblHistorial);

        JLabel lblProximos = new JLabel("Próximas Conferencias");
        lblProximos.setFont(new Font("Dialog", Font.BOLD, 12));
        lblProximos.setHorizontalAlignment(SwingConstants.CENTER);
        lblProximos.setBounds(243, 223, 134, 22);
        contentPane.add(lblProximos);

        JLabel lblNuevaReunion = new JLabel("Nueva Conferencia");
        lblNuevaReunion.setFont(new Font("Dialog", Font.BOLD, 12));
        lblNuevaReunion.setHorizontalAlignment(SwingConstants.CENTER);
        lblNuevaReunion.setBounds(427, 218, 121, 22);
        contentPane.add(lblNuevaReunion);
    }

    private void setButtonIcon(JButton button, String resourcePath) {
        ImageIcon originalIcon = new ImageIcon(ViewPaginaPrincipalAsistente.class.getResource(resourcePath));
        Image scaledImage = originalIcon.getImage().getScaledInstance(button.getWidth(), button.getHeight(), Image.SCALE_SMOOTH);
        button.setIcon(new ImageIcon(scaledImage));
        button.setBorderPainted(false);
        button.setFocusPainted(false);
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
        // Escalar la imagen al tamaño del panel
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}
