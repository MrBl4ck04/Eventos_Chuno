package View;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import controller.Registro;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ViewRegistro extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JPasswordField pswd;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ViewRegistro frame = new ViewRegistro();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public ViewRegistro() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 700, 500);
        contentPane = new JPanel();
        contentPane.setForeground(new Color(244, 241, 222));
        contentPane.setBackground(new Color(61, 64, 91));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblRegistro = new JLabel("¡Registrate!");
        lblRegistro.setBounds(468, 33, 117, 89);
        lblRegistro.setForeground(new Color(244, 241, 222));
        lblRegistro.setBackground(new Color(244, 241, 222));
        lblRegistro.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 26));
        contentPane.add(lblRegistro);

        JTextArea txtNombre = new JTextArea();
        txtNombre.setBackground(new Color(244, 241, 222));
        txtNombre.setBounds(440, 127, 209, 28);
        contentPane.add(txtNombre);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(353, 127, 68, 33);
        lblNombre.setForeground(new Color(244, 241, 222));
        lblNombre.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 18));
        lblNombre.setBackground(new Color(244, 241, 222));
        contentPane.add(lblNombre);

        JTextArea txtApellido = new JTextArea();
        txtApellido.setBackground(new Color(244, 241, 222));
        txtApellido.setBounds(440, 184, 209, 28);
        contentPane.add(txtApellido);

        JLabel lblApellido = new JLabel("Apellido:");
        lblApellido.setBounds(353, 179, 68, 33);
        lblApellido.setForeground(new Color(244, 241, 222));
        lblApellido.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 18));
        lblApellido.setBackground(new Color(244, 241, 222));
        contentPane.add(lblApellido);

        JTextArea txtEmail = new JTextArea();
        txtEmail.setBackground(new Color(244, 241, 222));
        txtEmail.setBounds(440, 237, 209, 28);
        contentPane.add(txtEmail);

        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setBounds(353, 232, 68, 33);
        lblEmail.setForeground(new Color(244, 241, 222));
        lblEmail.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 18));
        lblEmail.setBackground(new Color(244, 241, 222));
        contentPane.add(lblEmail);

        pswd = new JPasswordField();
        pswd.setBackground(new Color(244, 241, 222));
        pswd.setBounds(440, 286, 209, 27);
        contentPane.add(pswd);

        JLabel lblContraseña = new JLabel("Contraseña:");
        lblContraseña.setBounds(353, 286, 88, 33);
        lblContraseña.setForeground(new Color(244, 241, 222));
        lblContraseña.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 18));
        lblContraseña.setBackground(new Color(244, 241, 222));
        contentPane.add(lblContraseña);

        JTextArea txtTelefono = new JTextArea();
        txtTelefono.setBackground(new Color(244, 241, 222));
        txtTelefono.setBounds(440, 334, 209, 28);
        contentPane.add(txtTelefono);

        JLabel lblTelefono = new JLabel("Telefono:");
        lblTelefono.setBounds(353, 329, 88, 33);
        lblTelefono.setForeground(new Color(244, 241, 222));
        lblTelefono.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 18));
        lblTelefono.setBackground(new Color(244, 241, 222));
        contentPane.add(lblTelefono);

        JButton btnRegistrar = new JButton("");
        btnRegistrar.setBackground(Color.WHITE);
        btnRegistrar.setBorderPainted(false);
        btnRegistrar.setFocusPainted(false);
        btnRegistrar.setContentAreaFilled(false);

        try {
            ImageIcon icono = loadTransparentIcon("/View/comprobar.png", 79, 73);
            btnRegistrar.setIcon(new ImageIcon(ViewRegistro.class.getResource("/View/comprobar.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        btnRegistrar.setBounds(504, 380, 79, 73);
        btnRegistrar.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 16));
        contentPane.add(btnRegistrar);

        JPanel pnRegistro = new JPanel();
        pnRegistro.setBounds(0, 0, 327, 463);
        contentPane.add(pnRegistro);
        pnRegistro.setLayout(null);

        ImageIcon iconRegistro = new ImageIcon(getClass().getResource("/View/ChunoRegistro.png"));
        Image imgRegistro = iconRegistro.getImage().getScaledInstance(pnRegistro.getWidth(), pnRegistro.getHeight(), Image.SCALE_DEFAULT);
        iconRegistro = new ImageIcon(imgRegistro);

        JLabel lblRegistroIcon = new JLabel(iconRegistro);
        lblRegistroIcon.setBounds(0, 0, pnRegistro.getWidth(), pnRegistro.getHeight());
        pnRegistro.add(lblRegistroIcon);
        
        JButton btnAtras = new JButton("");
        btnAtras.setBackground(Color.WHITE);
        btnAtras.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ViewLogin br3 = new ViewLogin();
                br3.setVisible(true);
                dispose(); // Cierra la ventana actual
            }
        });
        btnAtras.setBorderPainted(false);
        btnAtras.setFocusPainted(false);
        btnAtras.setContentAreaFilled(false);
        btnAtras.setIcon(new ImageIcon(ViewRegistro.class.getResource("/View/anterior (1).png")));
        btnAtras.setBounds(612, 11, 62, 50);
        contentPane.add(btnAtras);

        // Crear instancia de la clase Registro
        Registro registro = new Registro();

        btnRegistrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Capturar datos del formulario
                String nombre = txtNombre.getText();
                String apellido = txtApellido.getText();
                String email = txtEmail.getText();
                String password = new String(pswd.getPassword()); // Convierte el JPasswordField a String
                String telefono = txtTelefono.getText();

                // Validación: Verificar si algún campo está vacío
                if (nombre.isEmpty() || apellido.isEmpty() || email.isEmpty() || password.isEmpty() || telefono.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Debe llenar todos los campos por favor.", "Error", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                // Validación: Verificar las restricciones de la contraseña
                if (!validarContraseña(password)) {
                    JOptionPane.showMessageDialog(null, "La contraseña debe tener al menos 8 caracteres y contener al menos un número.", "Error", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                // Realizar la inserción en la base de datos
                boolean exito = registro.registrarUsuario(nombre, apellido, email, password, telefono);

                if (exito) {
                    JOptionPane.showMessageDialog(null, "Usuario registrado con éxito.");
                    // Aquí podrías limpiar los campos o redirigir al usuario a otra ventana
                } else {
                    JOptionPane.showMessageDialog(null, "Error al registrar el usuario.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    // Método para validar la contraseña
    private boolean validarContraseña(String password) {
        // Verificar que la contraseña tenga al menos 8 caracteres
        if (password.length() < 8) {
            return false;
        }
        
        // Verificar que contenga al menos un número
        if (!Pattern.compile("[0-9]").matcher(password).find()) {
            return false;
        }

        return true;
    }

    private ImageIcon loadTransparentIcon(String path, int width, int height) throws IOException {
        BufferedImage originalImage = ImageIO.read(getClass().getResource(path));
        BufferedImage transparentImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = transparentImage.createGraphics();

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.drawImage(originalImage, 0, 0, width, height, null);
        g2d.dispose();

        return new ImageIcon(transparentImage);
    }
}
