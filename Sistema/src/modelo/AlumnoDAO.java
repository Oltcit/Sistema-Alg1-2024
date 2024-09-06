package modelo;

import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class AlumnoDAO {

	/**
	 * Registra un alumno nuevo
	 * @param miAlumno
	 */
	public void registrarAlumno(AlumnoVO miAlumno)   {
		
	try{	
		Conexion conex=new Conexion();
		Statement estatuto=conex.getConnection().createStatement();
		estatuto.executeUpdate("INSERT INTO alumno VALUES ('"+miAlumno.getDni()+"','"+miAlumno.getApe()+"','"+miAlumno.getFecha()+
				"','"+miAlumno.getDoc()+"')");
		JOptionPane.showMessageDialog(null, "Se ha registrado exitosamente","Información",JOptionPane.INFORMATION_MESSAGE);
		estatuto.close();
		conex.desconectar();
	} catch (SQLException e){
		JOptionPane.showMessageDialog(null, "No se registró","Error",JOptionPane.ERROR_MESSAGE);
		e.printStackTrace();
	}
	}
	
}
