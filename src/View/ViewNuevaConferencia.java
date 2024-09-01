package View;

import Model.Conferencia;
import Model.ConferenciaDAO;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

public class ViewNuevaConferencia extends JFrame {
    private static final long serialUID = 1L;
    private JPanel contentPane;
    private JPanel panelConferencias;
    private JTextField txtBuscar;
    private int idUsuario;
    private ConferenciaDAO conferenciaDAO;

    public ViewNuevaConferencia(int idUsuario) {
        this.idUsuario = idUsuario;
        this.conferenciaDAO = new ConferenciaDAO();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 600);

        // Crear el panel con imagen de fondo
        contentPane = new BackgroundPanel("/View/backNuevaC.png");
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        contentPane.setLayout(new BorderLayout());
        setContentPane(contentPane);

        // Crear el panel superior con el campo de búsqueda
        JPanel panelSuperior = new JPanel();
        panelSuperior.setOpaque(false);
        panelSuperior.setLayout(new FlowLayout(FlowLayout.LEFT));
        contentPane.add(panelSuperior, BorderLayout.NORTH);

        txtBuscar = new JTextField();
        txtBuscar.setPreferredSize(new Dimension(200, 30));
        panelSuperior.add(txtBuscar);

        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.setPreferredSize(new Dimension(100, 30));
        btnBuscar.addActionListener(e -> buscarConferencias());
        panelSuperior.add(btnBuscar);

        // Crear el panel principal que contendrá las conferencias
        panelConferencias = new JPanel();
        panelConferencias.setOpaque(false);
        panelConferencias.setLayout(new BoxLayout(panelConferencias, BoxLayout.Y_AXIS));
        panelConferencias.setBorder(new EmptyBorder(50, 10, 10, 10));

        // Crear el JScrollPane y configurarlo
        JScrollPane scrollPane = new JScrollPane(panelConferencias);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(null);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        contentPane.add(scrollPane, BorderLayout.CENTER);

        // Botón "Volver" en el panel inferior
        JPanel panelInferior = new JPanel();
        panelInferior.setOpaque(false);
        panelInferior.setLayout(new FlowLayout(FlowLayout.CENTER));
        contentPane.add(panelInferior, BorderLayout.SOUTH);

        JButton btnVolver = new JButton("Volver");
        btnVolver.setPreferredSize(new Dimension(100, 40));
        btnVolver.setFont(new Font("Arial", Font.PLAIN, 16));
        btnVolver.addActionListener(e -> {
            ViewPaginaPrincipalAsistente mainView = new ViewPaginaPrincipalAsistente(idUsuario);
            mainView.setVisible(true);
            dispose();
        });
        panelInferior.add(btnVolver);

        // Cargar todas las conferencias inicialmente
        cargarConferencias(conferenciaDAO.obtenerConferencias());
    }

    // Método para cargar conferencias en el panel
    private void cargarConferencias(List<Conferencia> conferencias) {
        panelConferencias.removeAll(); // Limpiar el panel antes de agregar nuevas tarjetas

        for (Conferencia conferencia : conferencias) {
            JPanel card = createCard(conferencia);
            panelConferencias.add(card);
            panelConferencias.add(Box.createRigidArea(new Dimension(0, 10)));
        }

        panelConferencias.revalidate(); // Refrescar el panel para mostrar los cambios
        panelConferencias.repaint();
    }

    // Método para realizar la búsqueda de conferencias
    private void buscarConferencias() {
        String terminoBusqueda = txtBuscar.getText().trim();

        if (!terminoBusqueda.isEmpty()) {
            List<Conferencia> conferencias = conferenciaDAO.buscarConferenciasPorTitulo(terminoBusqueda);
            cargarConferencias(conferencias);
        } else {
            // Si el campo de búsqueda está vacío, cargar todas las conferencias
            cargarConferencias(conferenciaDAO.obtenerConferencias());
        }
    }

    private JPanel createCard(Conferencia conferencia) {
        JPanel card = new JPanel();
        card.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        card.setBackground(new Color(255, 255, 255, 200)); // Fondo semi-transparente
        card.setLayout(new BorderLayout());
        card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 150));

        // Título de la conferencia
        JLabel lblTitulo = new JLabel(conferencia.getTitulo());
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        lblTitulo.setBorder(new EmptyBorder(10, 10, 10, 10));
        card.add(lblTitulo, BorderLayout.NORTH);

        // Panel de botones
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

        JButton btnInfo = new JButton("Información");
        btnInfo.setFont(new Font("Arial", Font.PLAIN, 14));
        btnInfo.addActionListener(e -> mostrarInformacionConferencia(conferencia));

        JButton btnUnirse = new JButton("Unirse");
        btnUnirse.setFont(new Font("Arial", Font.PLAIN, 14));
        btnUnirse.addActionListener(e -> unirseConferencia(conferencia.getIdConferencia()));

        // Nuevo botón para ver recursos
        JButton btnRecursos = new JButton("Recursos");
        btnRecursos.setFont(new Font("Arial", Font.PLAIN, 14));
        btnRecursos.addActionListener(e -> mostrarRecursosConferencia(conferencia.getRecursos()));

        buttonPanel.add(btnInfo);
        buttonPanel.add(btnUnirse);
        buttonPanel.add(btnRecursos);

        card.add(buttonPanel, BorderLayout.SOUTH);

        return card;
    }

    private void mostrarInformacionConferencia(Conferencia conferencia) {
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setOpaque(false);

        JPanel textPanel = new JPanel();
        textPanel.setOpaque(true);
        textPanel.setBackground(new Color(255, 255, 255, 180));
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        textPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Agregar información de la conferencia
        textPanel.add(createInfoLabel("Título: ", conferencia.getTitulo()));
        textPanel.add(createInfoLabel("Descripción: ", conferencia.getDescripcion()));
        textPanel.add(createInfoLabel("Fecha de Inicio: ", conferencia.getFechaInicio().toString()));
        textPanel.add(createInfoLabel("Fecha de Fin: ", conferencia.getFechaFin().toString()));
        textPanel.add(createInfoLabel("Tema: ", conferencia.getTema()));
        textPanel.add(createInfoLabel("Marca: ", conferencia.getMarca()));
        textPanel.add(createInfoLabel("ID de Usuario: ", String.valueOf(conferencia.getIdUsuario())));
        textPanel.add(createInfoLabel("Recursos: ", conferencia.getRecursos()));
        textPanel.add(createInfoLabel("ID de Sala: ", String.valueOf(conferencia.getIdSala())));
        textPanel.add(createInfoLabel("Disponibilidad: ", conferencia.getDisponibilidad() == 1 ? "Disponible" : "No disponible"));
        textPanel.add(createInfoLabel("Cupos: ", String.valueOf(conferencia.getCupos())));

        infoPanel.add(textPanel);

        JOptionPane.showMessageDialog(this, infoPanel, "Información de la Conferencia", JOptionPane.INFORMATION_MESSAGE);
    }

    private JLabel createInfoLabel(String label, String value) {
        JLabel lbl = new JLabel(label + value);
        lbl.setFont(new Font("Arial", Font.PLAIN, 14));
        lbl.setForeground(Color.BLACK);
        lbl.setBorder(new EmptyBorder(5, 0, 5, 0));
        return lbl;
    }

    private void unirseConferencia(int idConferencia) {
        // Verificar si el usuario ya está registrado en la conferencia
        boolean yaRegistrado = conferenciaDAO.isUsuarioRegistrado(idUsuario, idConferencia);

        if (yaRegistrado) {
            // Mostrar mensaje de que ya está registrado
            JOptionPane.showMessageDialog(this, "Ya registrado en esta conferencia.", "Registro Duplicado", JOptionPane.WARNING_MESSAGE);
        } else {
            // Intentar registrar la asistencia si no está registrado
            boolean exito = conferenciaDAO.registrarAsistencia(idUsuario, idConferencia, 0); // 0 como valor inicial para voto

            if (exito) {
                JOptionPane.showMessageDialog(this, "Te has unido a la conferencia exitosamente.", "Unirse a Conferencia", JOptionPane.INFORMATION_MESSAGE);
                actualizarInformacionConferencias(); // Actualizar la información de las conferencias
            } else {
                JOptionPane.showMessageDialog(this, "Hubo un error al unirse a la conferencia.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Método para actualizar la lista de conferencias después de una modificación
    private void actualizarInformacionConferencias() {
        List<Conferencia> conferenciasActualizadas = conferenciaDAO.obtenerConferencias();
        cargarConferencias(conferenciasActualizadas); // Recargar la interfaz con los datos actualizados
    }

    // Método para mostrar los recursos de la conferencia
    private void mostrarRecursosConferencia(String recursos) {
        if (recursos == null || recursos.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay recursos disponibles para esta conferencia.", "Recursos", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        // Asumimos que los recursos están separados por comas
        String[] recursosArray = recursos.split(",");

        JPanel recursosPanel = new JPanel();
        recursosPanel.setLayout(new BoxLayout(recursosPanel, BoxLayout.Y_AXIS));
        recursosPanel.setOpaque(false);

        for (String recurso : recursosArray) {
            String recursoTrimmed = recurso.trim();
            JButton btnRecurso = new JButton(recursoTrimmed);
            btnRecurso.setFont(new Font("Arial", Font.PLAIN, 14));
            btnRecurso.setAlignmentX(Component.LEFT_ALIGNMENT);
            btnRecurso.addActionListener(e -> {
                try {
                    Desktop.getDesktop().browse(new java.net.URI(recursoTrimmed));
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "No se pudo abrir el recurso.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            });
            recursosPanel.add(btnRecurso);
            recursosPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        }

        JScrollPane recursosScrollPane = new JScrollPane(recursosPanel);
        recursosScrollPane.setPreferredSize(new Dimension(400, 300));
        recursosScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        recursosScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        JOptionPane.showMessageDialog(this, recursosScrollPane, "Recursos de la Conferencia", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                ViewNuevaConferencia frame = new ViewNuevaConferencia(1); // Suponiendo que se pasa el idUsuario 1
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
