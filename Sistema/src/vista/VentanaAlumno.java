package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import controlador.Coordinador;
import modelo.AlumnoDAO;
import modelo.AlumnoVO;

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
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guardarAlumno();
			}
		});
		panelSur.add(btnGuardar);
		
		btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				accion = 1;
				habilita(true, true, true, true, true, false, false, false, false, true);
			}
		});
		panelSur.add(btnAgregar);
		
		btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modificarAlumno();
			}
		});
		panelSur.add(btnModificar);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eliminarAlumno();
			}
		});
		panelSur.add(btnEliminar);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscarAlumnos();
			}
		});
		panelSur.add(btnBuscar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiar();
			}
		});
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
		selectorFecha.setDateFormatString("d/M/yyyy");
		selectorFecha.setBounds(159, 114, 115, 20);
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
	protected void eliminarAlumno() {
		int res = JOptionPane.showConfirmDialog(null, "Est� seguro de eliminar ese Alumno?","Confirmaci�n",JOptionPane.YES_NO_OPTION);
		if (res == JOptionPane.YES_NO_OPTION) {
			AlumnoDAO miAlumnoDAO = new AlumnoDAO();
			miAlumnoDAO.eliminarAlumno(Integer.valueOf(txtDni.getText()));
			limpiar();
		}
		
	}

	protected void modificarAlumno() {
		accion=2;
		habilita(false, true, true, true, true, false, false, false, false, true);
	}

	protected void buscarAlumnos() {
		int dni=0;
		if (!txtDni.getText().isEmpty())
			dni = Integer.valueOf(txtDni.getText());
		miCoordinador.mostrarVentanaAlumnoBuscar(dni);
		
	}

	protected void guardarAlumno() {
		try {
			Date date=selectorFecha.getDate();
			SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
			AlumnoVO miAlumnoVO = new AlumnoVO();
			miAlumnoVO.setDni(Integer.valueOf(txtDni.getText()));
			miAlumnoVO.setApe(txtApe.getText());
			miAlumnoVO.setFecha(sdf.format(date));
			if (chkDoc.isSelected())
				miAlumnoVO.setDoc((byte) 1);
			else
				miAlumnoVO.setDoc((byte) 0);
			
			AlumnoDAO miAlumnoDAO= new AlumnoDAO();
			
			if (accion==1){
				miAlumnoDAO.registrarAlumno(miAlumnoVO);	
			}else{
				miAlumnoDAO.modificarAlumno(miAlumnoVO);
			}
			limpiar();
		} catch (Exception e){
			JOptionPane.showMessageDialog(null, "Error en el ingreso de datos","Error",JOptionPane.ERROR_MESSAGE);
			limpiar();
		}
		
	}

	private void limpiar() {
			txtDni.setText("");
			txtApe.setText("");
			selectorFecha.setCalendar(null);
			chkDoc.setSelected(false);
			
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

	public void mostrarDatos(AlumnoVO miAlumnoVO) {
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		Date miDia = new Date();
		GregorianCalendar miGCalendar = new GregorianCalendar();
		try {
			miDia = formato.parse(miAlumnoVO.getFecha());
		}catch (ParseException e) {
			e.printStackTrace();
		}
		miGCalendar.setTime(miDia);
		txtDni.setText(String.valueOf(miAlumnoVO.getDni()));
		txtApe.setText(miAlumnoVO.getApe());
		selectorFecha.setCalendar(miGCalendar);
		
		if (miAlumnoVO.getDoc()==0)
			chkDoc.setSelected(false);
		else
			chkDoc.setSelected(true);
		
		habilita(false, false, false, false, false, false, true, true, false, true);
	}
	
}
