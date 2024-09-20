package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

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

	public void buscarAlumnos(DefaultTableModel modelo) {
		try {
			Conexion conex=new Conexion();
			String consulta="SELECT * FROM alumno order by alapynom";
			PreparedStatement estatuto = conex.getConnection().prepareStatement(consulta);
			ResultSet res = estatuto.executeQuery();
			
			while (res.next()) {
				Object fila[]= new Object[4];
				for (int i=0; i<4; i++)
					fila[i]=res.getObject(i+1);
				modelo.addRow(fila);
			}
			res.close();
			estatuto.close();
			conex.desconectar();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error al consultar","Error",JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
}
