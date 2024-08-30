package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.Coordinador;
import javax.swing.JButton;
import javax.swing.JLabel;
import com.toedter.calendar.JDateChooser;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class VentanaAlumno extends JFrame {

	private JPanel contentPane;
	Coordinador miCoordinador;
	int accion;
	private JTextField txtDni;
	private JTextField txtApe;
	private JButton btnCancelar;
	private JButton btnBuscar;
	private JButton btnModificar;
	private JCheckBox chkDoc;
	private JButton btnGuardar;
	private JButton btnAgregar;
	private JDateChooser selectorFecha;
	private JButton btnEliminar;
	

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
					VentanaAlumno frame = new VentanaAlumno();
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
	public VentanaAlumno() {
		setTitle("                                                            Ventana Alumno");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 612, 355);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panelSur = new JPanel();
		contentPane.add(panelSur, BorderLayout.SOUTH);
		
		btnGuardar = new JButton("Guardar");
		panelSur.add(btnGuardar);
		
		btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				accion = 1;
				
			}
		});
		panelSur.add(btnAgregar);
		
		btnModificar = new JButton("Modificar");
		panelSur.add(btnModificar);
		
		btnEliminar = new JButton("Eliminar");
		panelSur.add(btnEliminar);
		
		btnBuscar = new JButton("Buscar");
		panelSur.add(btnBuscar);
		
		btnCancelar = new JButton("Cancelar");
		panelSur.add(btnCancelar);
		
		JPanel panelCentro = new JPanel();
		contentPane.add(panelCentro, BorderLayout.CENTER);
		panelCentro.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Dni:");
		lblNewLabel.setBounds(34, 44, 46, 14);
		panelCentro.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Apellido y nombre:");
		lblNewLabel_1.setBounds(34, 82, 102, 14);
		panelCentro.add(lblNewLabel_1);
		
		selectorFecha = new JDateChooser();
		selectorFecha.setBounds(159, 114, 70, 20);
		panelCentro.add(selectorFecha);
		
		JLabel lblNewLabel_2 = new JLabel("Fecha de nacimiento:");
		lblNewLabel_2.setBounds(34, 120, 115, 14);
		panelCentro.add(lblNewLabel_2);
		
		chkDoc = new JCheckBox("Documentaci\u00F3n");
		chkDoc.setBounds(34, 180, 115, 23);
		panelCentro.add(chkDoc);
		
		txtDni = new JTextField();
		txtDni.setBounds(90, 41, 86, 20);
		panelCentro.add(txtDni);
		txtDni.setColumns(10);
		
		txtApe = new JTextField();
		txtApe.setBounds(143, 79, 351, 20);
		panelCentro.add(txtApe);
		txtApe.setColumns(10);
		
		habilita(true, false, false, false, false, true, false, false, true, true);
	}
	public void habilita(boolean dni,boolean ape,boolean fecha,boolean doc,boolean bGuardar,boolean bAgregar,
			boolean bModificar, boolean bEliminar, boolean bBuscar, boolean bCancelar) {
		
		txtDni.setEditable(dni);
		txtApe.setEditable(ape);
		selectorFecha.setEnabled(fecha);
		chkDoc.setEnabled(doc);
		btnGuardar.setVisible(bGuardar);
		btnAgregar.setEnabled(bAgregar);
		btnModificar.setEnabled(bModificar);
		btnEliminar.setEnabled(bEliminar);
		btnBuscar.setEnabled(bBuscar);
		btnCancelar.setEnabled(bCancelar);
	}
	
}
