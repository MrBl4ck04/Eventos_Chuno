package View;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ViewRoles extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewRoles frame = new ViewRoles();
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
	public ViewRoles() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 196, 217);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnOrador = new JButton("Orador");
		btnOrador.setBounds(32, 115, 125, 52);
		contentPane.add(btnOrador);
		
		JButton btnAsistente = new JButton("Asistente");
		btnAsistente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewPaginaPrincipalAsistente br1 = new ViewPaginaPrincipalAsistente();
                br1.setVisible(true);
			}
		});
		btnAsistente.setBounds(32, 52, 125, 52);
		contentPane.add(btnAsistente);
		
		JLabel lblNewLabel = new JLabel("Con que rol desea ingresar?");
		lblNewLabel.setBounds(10, 11, 170, 30);
		contentPane.add(lblNewLabel);
		 btnOrador.addMouseListener(new MouseAdapter() {
	            @Override
	            public void mouseClicked(MouseEvent e) {	             
	            		ViewPaginaPrincipalOrador br = new ViewPaginaPrincipalOrador();
	                    br.setVisible(true);	              
	            }
	        });
	}
}
