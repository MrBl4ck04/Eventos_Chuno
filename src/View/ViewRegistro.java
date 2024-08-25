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
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import controller.Registro;

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
        txtNombre.setBounds(431, 127, 218, 28);
        contentPane.add(txtNombre);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(353, 127, 68, 33);
        lblNombre.setForeground(new Color(244, 241, 222));
        lblNombre.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 18));
        lblNombre.setBackground(new Color(244, 241, 222));
        contentPane.add(lblNombre);

        JTextArea txtApellido = new JTextArea();
        txtApellido.setBounds(431, 184, 218, 28);
        contentPane.add(txtApellido);

        JLabel lblApellido = new JLabel("Apellido:");
        lblApellido.setBounds(353, 179, 68, 33);
        lblApellido.setForeground(new Color(244, 241, 222));
        lblApellido.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 18));
        lblApellido.setBackground(new Color(244, 241, 222));
        contentPane.add(lblApellido);

        JTextArea txtEmail = new JTextArea();
        txtEmail.setBounds(431, 237, 218, 28);
        contentPane.add(txtEmail);

        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setBounds(353, 232, 68, 33);
        lblEmail.setForeground(new Color(244, 241, 222));
        lblEmail.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 18));
        lblEmail.setBackground(new Color(244, 241, 222));
        contentPane.add(lblEmail);

        pswd = new JPasswordField();
        pswd.setBounds(440, 286, 209, 27);
        contentPane.add(pswd);

        JLabel lblContraseña = new JLabel("Contraseña:");
        lblContraseña.setBounds(353, 286, 88, 33);
        lblContraseña.setForeground(new Color(244, 241, 222));
        lblContraseña.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 18));
        lblContraseña.setBackground(new Color(244, 241, 222));
        contentPane.add(lblContraseña);

        JTextArea txtTelefono = new JTextArea();
        txtTelefono.setBounds(431, 334, 218, 28);
        contentPane.add(txtTelefono);

        JLabel lblTelefono = new JLabel("Telefono:");
        lblTelefono.setBounds(353, 329, 88, 33);
        lblTelefono.setForeground(new Color(244, 241, 222));
        lblTelefono.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 18));
        lblTelefono.setBackground(new Color(244, 241, 222));
        contentPane.add(lblTelefono);

        JButton btnRegistrar = new JButton("REGISTRAR");
        btnRegistrar.setBounds(455, 388, 130, 41);
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

                // Realizar la inserción en la base de datos (sin el campo 'rol')
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
}
