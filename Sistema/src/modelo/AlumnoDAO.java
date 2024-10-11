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

	public void buscarParcialDni(DefaultTableModel modelo, int dni) {
		
		try {
			Conexion conex=new Conexion();
			String consulta="SELECT * FROM alumno where aldni like ? order by alapynom";
			PreparedStatement estatuto = conex.getConnection().prepareStatement(consulta);
			estatuto.setString(1, dni+"%");
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

	public void modificarAlumno(AlumnoVO miAlumnoVO) {
		try {
			Conexion conex=new Conexion();
			String consulta="UPDATE alumno SET aldni=?, alapynom=?, alfnac=?, aldoc=? WHERE aldni=?";
			PreparedStatement estatuto = conex.getConnection().prepareStatement(consulta);
			
			estatuto.setInt(1, miAlumnoVO.getDni());
			estatuto.setString(2, miAlumnoVO.getApe());
			estatuto.setString(3, miAlumnoVO.getFecha());
			estatuto.setByte(4, miAlumnoVO.getDoc());
			estatuto.setInt(5, miAlumnoVO.getDni());
			estatuto.executeUpdate();
			
			JOptionPane.showMessageDialog(null, "Se ha modificado correctamente","Confirmación",JOptionPane.INFORMATION_MESSAGE);
		} catch(SQLException e) {
			JOptionPane.showMessageDialog(null, "Error al modificar","Error",JOptionPane.ERROR_MESSAGE);
		}
	}

	public void eliminarAlumno(Integer dni) {
		try {
			Conexion conex = new Conexion();
			Statement estatuto = conex.getConnection().createStatement();
			estatuto.executeUpdate("DELETE FROM alumno WHERE aldni='"+dni+"'");
			JOptionPane.showMessageDialog(null, "Se ha eliminado correctamente","información",JOptionPane.INFORMATION_MESSAGE);
			estatuto.close();
			conex.desconectar();
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "No se eliminó","información",JOptionPane.INFORMATION_MESSAGE);
		}
		
	}
	}
	

