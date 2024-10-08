package View;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import controller.login;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ViewLogin extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JPasswordField psFldPassword;
    private login login;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ViewLogin frame = new ViewLogin();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public ViewLogin() {
        login = new login();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 700, 500);
        contentPane = new JPanel();
        contentPane.setForeground(new Color(244, 241, 222));
        contentPane.setBackground(new Color(61, 64, 91));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        ImageIcon gifIcon = new ImageIcon(getClass().getResource("/View/ChunoLn.gif"));
        Image img = gifIcon.getImage().getScaledInstance(343, 463, Image.SCALE_DEFAULT);
        gifIcon = new ImageIcon(img);

        JLabel lblGif = new JLabel(gifIcon);
        lblGif.setBackground(new Color(0, 0, 128));
        lblGif.setBounds(0, 0, 343, 463);
        contentPane.add(lblGif);

        JLabel lblBienvenido = new JLabel("BIENVENIDO USUARIO");
        lblBienvenido.setForeground(new Color(244, 241, 222));
        lblBienvenido.setBackground(new Color(244, 241, 222));
        lblBienvenido.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 26));
        lblBienvenido.setBounds(408, 87, 218, 67);
        contentPane.add(lblBienvenido);

        JTextArea txtUsuario = new JTextArea();
        txtUsuario.setBackground(new Color(244, 237, 193));
        txtUsuario.setBounds(408, 216, 218, 33);
        contentPane.add(txtUsuario);

        psFldPassword = new JPasswordField();
        psFldPassword.setBackground(new Color(244, 237, 193));
        psFldPassword.setBounds(408, 309, 218, 33);
        contentPane.add(psFldPassword);

        JLabel lblUsuario = new JLabel("USUARIO:");
        lblUsuario.setForeground(new Color(244, 241, 222));
        lblUsuario.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 18));
        lblUsuario.setBackground(new Color(244, 241, 222));
        lblUsuario.setBounds(408, 184, 68, 33);
        contentPane.add(lblUsuario);

        JLabel lblContraseña = new JLabel("CONTRASEÑA:");
        lblContraseña.setForeground(new Color(244, 241, 222));
        lblContraseña.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 18));
        lblContraseña.setBackground(new Color(244, 241, 222));
        lblContraseña.setBounds(408, 277, 91, 33);
        contentPane.add(lblContraseña);

        JButton btnIngresar = new JButton("");
        btnIngresar.setBackground(Color.WHITE);
        btnIngresar.setBorderPainted(false);
        btnIngresar.setFocusPainted(false);
        btnIngresar.setContentAreaFilled(false);

        try {
            ImageIcon icono = loadTransparentIcon("/View/comprobar.png", 78, 67);
            btnIngresar.setIcon(new ImageIcon(ViewLogin.class.getResource("/View/comprobar.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        btnIngresar.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 16));
        btnIngresar.setBounds(482, 365, 78, 67);
        contentPane.add(btnIngresar);

        JPanel pnPass = new JPanel();
        pnPass.setBackground(new Color(61, 64, 91));
        pnPass.setBounds(501, 277, 40, 33);
        contentPane.add(pnPass);
        pnPass.setLayout(null);

        ImageIcon iconPass = new ImageIcon(getClass().getResource("/View/pass.png"));
        Image imgPass = iconPass.getImage().getScaledInstance(pnPass.getWidth(), pnPass.getHeight(), Image.SCALE_DEFAULT);
        iconPass = new ImageIcon(imgPass);

        JPanel pnUsuario = new JPanel();
        pnUsuario.setLayout(null);
        pnUsuario.setBackground(new Color(61, 64, 91));
        pnUsuario.setBounds(473, 184, 40, 33);
        contentPane.add(pnUsuario);

        ImageIcon iconUser = new ImageIcon(getClass().getResource("/View/usuario.png"));
        Image imgUser = iconUser.getImage().getScaledInstance(pnUsuario.getWidth(), pnUsuario.getHeight(), Image.SCALE_DEFAULT);
        iconUser = new ImageIcon(imgUser);

        JLabel lblUserIcon = new JLabel(iconUser);
        lblUserIcon.setBounds(358, 216, 40, 33);
        contentPane.add(lblUserIcon);

        JLabel lblPassIcon = new JLabel(iconPass);
        lblPassIcon.setBounds(358, 309, 40, 33);
        contentPane.add(lblPassIcon);

        JLabel lblOlvidar = new JLabel("¿Olvidaste tu contraseña?");
        lblOlvidar.setForeground(new Color(244, 241, 222));
        lblOlvidar.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 12));
        lblOlvidar.setBackground(new Color(244, 241, 222));
        lblOlvidar.setBounds(458, 342, 122, 33);
        contentPane.add(lblOlvidar);

        JLabel lblRegistro = new JLabel("¿Aun no tienes una cuenta? Registrate");
        lblRegistro.setForeground(new Color(244, 241, 222));
        lblRegistro.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 12));
        lblRegistro.setBackground(new Color(244, 241, 222));
        lblRegistro.setBounds(426, 424, 177, 33);
        contentPane.add(lblRegistro);

        btnIngresar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String email = txtUsuario.getText();
                String password = new String(psFldPassword.getPassword());

                int idUsuario = login.autenticar(email, password);

                if (idUsuario != -1) {
                    ViewRoles br = new ViewRoles(idUsuario);  // Pasar el id_usuario a ViewRoles
                    br.setVisible(true);
                    dispose();  // Cierra la ventana actual si el login es exitoso
                } else {
                    JOptionPane.showMessageDialog(null, "Email o contraseña incorrectos");
                }
            }
        });

        lblRegistro.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ViewRegistro registroFrame = new ViewRegistro();
                registroFrame.setVisible(true);
                dispose();
            }
        });
    }

    private ImageIcon loadTransparentIcon(String path, int width, int height) throws IOException {
        BufferedImage originalImage = ImageIO.read(getClass().getResource(path));
        BufferedImage transparentImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = transparentImage.createGraphics();

        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.drawImage(originalImage, 0, 0, width, height, null);
        g2d.dispose();

        return new ImageIcon(transparentImage);
    }
}
