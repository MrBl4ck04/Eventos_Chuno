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
import java.awt.Color;

public class ViewPaginaPrincipalOrador extends JFrame {

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
    private int idUsuario;
    private JLabel lblOrador;

    public ViewPaginaPrincipalOrador(int idUsuario) {
    	this.idUsuario = idUsuario;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 661, 432);

        contentPane = new BackgroundPanel("/View/backInicio.gif");
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTitulo = new JLabel("Bienvenido usuario esta como:");
        lblTitulo.setForeground(new Color(255, 255, 255));
        lblTitulo.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 18));
        lblTitulo.setBounds(10, 6, 291, 27);
        contentPane.add(lblTitulo);

        btnNotificacion = new JButton("");
        btnNotificacion.setBounds(539, 6, 44, 41);
        setButtonIcon(btnNotificacion, "/View/notificacion.png");
        contentPane.add(btnNotificacion);

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
        setButtonIcon(btnUsuario, "/View/usuarioPrincipal.png");
        contentPane.add(btnUsuario);

        btnHistorial = new JButton("");
        btnHistorial.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
          		ViewHistorialOrador brh = new ViewHistorialOrador();
                brh.setVisible(true);
                dispose(); // Cierra la ventana actual
        	}
        });
        btnHistorial.setIcon(new ImageIcon(ViewPaginaPrincipalOrador.class.getResource("/View/lista-de-verificacion.png")));
        btnHistorial.setBounds(50, 92, 134, 120);
        setButtonIcon(btnHistorial, "/View/expediente.png");
        contentPane.add(btnHistorial);

        btnProximos = new JButton("");
        btnProximos.setIcon(new ImageIcon(ViewPaginaPrincipalOrador.class.getResource("/View/calendario.png")));
        btnProximos.setBounds(232, 92, 137, 120);
        setButtonIcon(btnProximos, "/View/mas-reciente.png");
        contentPane.add(btnProximos);

        btnNuevaReunion = new JButton("");
        btnNuevaReunion.setIcon(new ImageIcon(ViewPaginaPrincipalOrador.class.getResource("/View/agregar-archivo.png")));
        btnNuevaReunion.setBounds(427, 92, 118, 120);
        setButtonIcon(btnNuevaReunion, "/View/mas.png");
        contentPane.add(btnNuevaReunion);

        lblHistorial = new JLabel("Historial de Conferencias");
        lblHistorial.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 15));
        lblHistorial.setHorizontalAlignment(SwingConstants.CENTER);
        lblHistorial.setBounds(39, 218, 152, 22);
        contentPane.add(lblHistorial);

        lblProximos = new JLabel("Próximas Conferencias");
        lblProximos.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 15));
        lblProximos.setHorizontalAlignment(SwingConstants.CENTER);
        lblProximos.setBounds(232, 218, 152, 22);
        contentPane.add(lblProximos);

        lblNuevaReunion = new JLabel("Nueva Conferencia");
        lblNuevaReunion.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 15));
        lblNuevaReunion.setHorizontalAlignment(SwingConstants.CENTER);
        lblNuevaReunion.setBounds(427, 218, 121, 22);
        contentPane.add(lblNuevaReunion);
        
        lblOrador = new JLabel("ORADOR");
        lblOrador.setForeground(Color.WHITE);
        lblOrador.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 30));
        lblOrador.setBounds(242, 20, 180, 27);
        contentPane.add(lblOrador);

        btnNuevaReunion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ViewNuevaReunion view = new ViewNuevaReunion(idUsuario);
                view.setVisible(true);
            }
        });

        btnProximos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ViewProximos viewp = new ViewProximos();
                viewp.setVisible(true);
            }
        });

        // Añadir ComponentListener para ajustar los componentes al cambiar el tamaño
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                resizeComponents();
            }
        });
    }

    private void setButtonIcon(JButton button, String resourcePath) {
        ImageIcon originalIcon = new ImageIcon(ViewPaginaPrincipalOrador.class.getResource(resourcePath));
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
        btnProximos.setBounds((int) (width * 0.4), (int) (height * 0.3), 118, 105);
        btnNuevaReunion.setBounds((int) (width * 0.7), (int) (height * 0.3), 118, 105);

        lblHistorial.setBounds((int) (width * 0.1 - 20), (int) (height * 0.65), 152, 22);
        lblProximos.setBounds((int) (width * 0.4 - 20), (int) (height * 0.65), 152, 22);
        lblNuevaReunion.setBounds((int) (width * 0.7 - 20), (int) (height * 0.65), 121, 22);
    }
}

