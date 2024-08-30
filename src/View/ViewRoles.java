package View;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Image;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Color;

public class ViewRoles extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private int idUsuario;

    public ViewRoles(int idUsuario) {  // Constructor que recibe el id_usuario
        this.idUsuario = idUsuario;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 263, 278);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);
        
                JButton btnOrador = new JButton("Orador");
                btnOrador.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 11));
                btnOrador.setBounds(87, 158, 100, 39);  // Ajustar la posición para no superponer con btnAsistente
                contentPane.add(btnOrador);
                
                        btnOrador.addMouseListener(new MouseAdapter() {
                            @Override
                            public void mouseClicked(MouseEvent e) {                 
                                ViewPaginaPrincipalOrador br = new ViewPaginaPrincipalOrador(idUsuario);  // Pasar el id_usuario
                                br.setVisible(true);                  
                            }
                        });

        JLabel lblNewLabel = new JLabel("¿Con qué rol desea ingresar?");
        lblNewLabel.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 20));
        lblNewLabel.setBounds(10, 11, 229, 30);
        contentPane.add(lblNewLabel);

        JPanel pnBack = new JPanel();
        pnBack.setBounds(0, 0, 249, 251);
        contentPane.add(pnBack);
        pnBack.setLayout(null);
        
        // Cargar y redimensionar la imagen
        ImageIcon originalIcon = new ImageIcon(getClass().getResource("/View/backroles.png"));
        Image scaledImage = originalIcon.getImage().getScaledInstance(pnBack.getWidth(), pnBack.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        
                // Añadir los botones al contentPane para que queden sobre la imagen
                JButton btnAsistente = new JButton("Asistente");
                btnAsistente.setBounds(87, 84, 100, 39);
                pnBack.add(btnAsistente);
                btnAsistente.setBackground(new Color(244, 241, 222));
                btnAsistente.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 12));
                btnAsistente.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        ViewPaginaPrincipalAsistente br1 = new ViewPaginaPrincipalAsistente(idUsuario);  // Pasar el id_usuario
                        br1.setVisible(true);
                    }
                });

        // Añadir la imagen redimensionada al panel pnBack
        JLabel background = new JLabel(scaledIcon);
        background.setBounds(0, 0, pnBack.getWidth(), pnBack.getHeight());
        pnBack.add(background);
    }
}
