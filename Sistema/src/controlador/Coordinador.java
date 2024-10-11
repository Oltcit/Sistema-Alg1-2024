package controlador;

import modelo.AlumnoVO;
import vista.VentanaAlumno;
import vista.VentanaAlumnoBuscar;
import vista.VentanaPrincipal;

public class Coordinador {

	VentanaPrincipal miVentanaPrincipal;
	VentanaAlumno miVentanaAlumno;
	VentanaAlumnoBuscar miVentanaAlumnoBuscar;
	
	public VentanaAlumnoBuscar getMiVentanaAlumnoBuscar() {
		return miVentanaAlumnoBuscar;
	}
	public void setMiVentanaAlumnoBuscar(VentanaAlumnoBuscar miVentanaAlumnoBuscar) {
		this.miVentanaAlumnoBuscar = miVentanaAlumnoBuscar;
	}
	public VentanaPrincipal getMiVentanaPrincipal() {
		return miVentanaPrincipal;
	}
	public void setMiVentanaPrincipal(VentanaPrincipal miVentanaPrincipal) {
		this.miVentanaPrincipal = miVentanaPrincipal;
	}
	public VentanaAlumno getMiVentanaAlumno() {
		return miVentanaAlumno;
	}
	public void setMiVentanaAlumno(VentanaAlumno miVentanaAlumno) {
		this.miVentanaAlumno = miVentanaAlumno;
	}
	
	//Mostrar Ventanas
	public void mostrarVentanaAlumno() {
		miVentanaAlumno.setVisible(true);
		
	}
	public void mostrarVentanaAlumnoBuscar(int dni) {
		miVentanaAlumnoBuscar.setVisible(true);
		miVentanaAlumnoBuscar.cargarDatos(dni);
	}
	public void pasarDatosAlumno(AlumnoVO miAlumnoVO) {
		miVentanaAlumno.mostrarDatos(miAlumnoVO);
		
	}
	
}
