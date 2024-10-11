package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controlador.Coordinador;
import modelo.AlumnoDAO;
import modelo.AlumnoVO;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaAlumnoBuscar extends JFrame {

	private JPanel contentPane;
	Coordinador miCoordinador;
	JScrollPane scrollPane;
	JTable tabla;

	public Coordinador getMiCoordinador() {
		return miCoordinador;
	}

	public void setMiCoordinador(Coordinador miCoordinador) {
		this.miCoordinador = miCoordinador;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaAlumnoBuscar frame = new VentanaAlumnoBuscar();
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
	public VentanaAlumnoBuscar() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		panel.add(btnVolver);
		
		scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
	}

	public void cargarDatos(int dni) {
		DefaultTableModel modelo = new DefaultTableModel();
		tabla = new JTable();
		tabla.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				miCoordinador.pasarDatosAlumno(pasarDatos(e));
			}
		});
		
		tabla.setModel(modelo);
		modelo.addColumn("Dni");
		modelo.addColumn("Apellido");
		modelo.addColumn("Fecha de nacimiento");
		modelo.addColumn("Documentación");
		
		AlumnoDAO miAlumnoDAO = new AlumnoDAO();
		miAlumnoDAO.buscarAlumnos(modelo);
		
		//miAlumnoDAO.buscarParcialDni(modelo,dni);
		
		scrollPane.setViewportView(tabla);
	}

	protected AlumnoVO pasarDatos(MouseEvent e) {
		AlumnoVO miAlumnoVO = new AlumnoVO();
		int fila=tabla.rowAtPoint(e.getPoint());
		miAlumnoVO.setDni(Integer.valueOf(tabla.getValueAt(fila, 0).toString()));
		miAlumnoVO.setApe(tabla.getValueAt(fila, 1).toString());
		miAlumnoVO.setFecha(tabla.getValueAt(fila, 2).toString());
		String estado = tabla.getValueAt(fila, 3).toString();
		//miAlumnoVO.setDoc(Byte.valueOf(tabla.getValueAt(fila, 3).toString()));
		if (estado.equals("false")) {
			miAlumnoVO.setDoc((byte) 0);
		}else {
			miAlumnoVO.setDoc((byte) 1);
		}
		return miAlumnoVO;
	}

}
