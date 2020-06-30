package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.text.DefaultEditorKit.BeepAction;

import conexaojdbc.SingleConnection;
import model.BeanUsertel;
import model.Userposjava;
import model.Usertelefone;

public class UserPosDao {

	private Connection connection;

	public UserPosDao() {
		connection = SingleConnection.getConnection();
	}

	public void salvar(Userposjava userposjava) {

		String sql;

		sql = "INSERT INTO userposjava (nome,email)VALUES(?,?);";
		try {
			PreparedStatement insert = connection.prepareStatement(sql);
			insert.setString(1, userposjava.getNome());
			insert.setString(2, userposjava.getEmail());
			insert.execute();
			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Userposjava> listar() throws SQLException {
		List<Userposjava> list = new ArrayList<Userposjava>();

		String sql = "select * from userposjava;";
		PreparedStatement listar = connection.prepareStatement(sql);
		ResultSet resultSet = listar.executeQuery();

		while (resultSet.next()) {
			Userposjava userposjava = new Userposjava();
			userposjava.setId(resultSet.getInt("id"));
			userposjava.setNome(resultSet.getNString("nome"));
			userposjava.setEmail(resultSet.getString("email"));
			list.add(userposjava);
		}
		return list;

	}

	public Userposjava Buscar(long id) throws SQLException {

		Userposjava userposjava = new Userposjava();
		String sql = "select * from userposjava where userposjava.id =  " + id;
		PreparedStatement listar = connection.prepareStatement(sql);
		ResultSet resultSet = listar.executeQuery();

		while (resultSet.next()) {
			userposjava.setId(resultSet.getInt("id"));
			userposjava.setNome(resultSet.getNString("nome"));
			userposjava.setEmail(resultSet.getString("email"));
		}
		return userposjava;
	}

	public void Atualizar(Userposjava user) throws SQLException {

		String sql = "update userposjava set nome = ? where id = " + user.getId();

		try {
			PreparedStatement atualizar = connection.prepareStatement(sql);
			atualizar.setString(1, user.getNome());
			atualizar.execute();
			connection.commit();

		} catch (Exception e) {
			connection.rollback();
			e.printStackTrace();
		}

	}

	public void deletar(long id) {
		String sql = "delete from userposjava where userposjava.id = " + id;
		try {
			PreparedStatement delete = connection.prepareStatement(sql);
			delete.execute();
			connection.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void salvartelefone(Usertelefone usertelefone) {

		String sql;

		sql = "INSERT INTO usertelefone (numero, tipo, id_userposjava)VALUES(?,?,?);";
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, usertelefone.getnumero());
			statement.setString(2, usertelefone.getTipo());
			statement.setInt(3, usertelefone.getId_userposjava());
			statement.execute();
			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public List<BeanUsertel> BuscarJoinn(int id) {
		
		List<BeanUsertel> listaBean = new ArrayList<BeanUsertel>();
		String SQL =  "select userposjava.*, usertelefone.numero\r\n" + 
				      "from userposjava\r\n" + 
				      "inner join usertelefone on usertelefone.id_userposjava = " + id;
		try {
			PreparedStatement statement = connection.prepareStatement(SQL);            	
            ResultSet resultSet = statement.executeQuery();          
            
            while (resultSet.next()) {            	
            	BeanUsertel beanUsertel = new BeanUsertel();         	
            	beanUsertel.setNome(resultSet.getString("email"));
            	beanUsertel.setNome(resultSet.getString("nome"));
            	beanUsertel.setNome(resultSet.getString("numero"));          	            	
            	listaBean.add(beanUsertel); 
            }   
            
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return listaBean; 
	}
	
	public void deleteFones() {
		String Sql = "delete from usertelefone";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(Sql);
			preparedStatement.execute();
			connection.commit();		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();			
		}		
	}
	public void deleteUser(int id) {
		String Sql = "delete from userposjava where userposjava.id = " + id;
		try {
			deleteFones();
			PreparedStatement preparedStatement = connection.prepareStatement(Sql);
			preparedStatement.execute();
			connection.commit();		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();			
		}
	}
	
	
	
	
	
	

}
