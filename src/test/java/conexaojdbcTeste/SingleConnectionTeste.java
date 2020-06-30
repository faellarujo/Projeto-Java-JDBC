package conexaojdbcTeste;

import java.sql.SQLException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import dao.UserPosDao;
import model.BeanUsertel;
import model.Userposjava;
import model.Usertelefone;

public class SingleConnectionTeste {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		Userposjava userposjava = new Userposjava();
		UserPosDao userPosDao = new UserPosDao();

		userposjava.setNome("Gomes Pereira");
		userposjava.setEmail("gomes@gmail.com");
		userPosDao.salvar(userposjava);

	}

	@Test
	public void initlistar() throws SQLException {
		UserPosDao userPosDao = new UserPosDao();
		try {
			List<Userposjava> lista = userPosDao.listar();
			for (Userposjava user : lista) {
				System.out.println(user.toString());
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	@Test
	public void buscar() throws SQLException {
		UserPosDao userPosDao = new UserPosDao();
		System.out.println(userPosDao.Buscar(6));

	}

	@Test
	public void atualizar() throws SQLException {
		UserPosDao userPosDao = new UserPosDao();
		Userposjava userposjava = userPosDao.Buscar(5);
		userposjava.setNome("Maycon da silva Diniz");
		userPosDao.Atualizar(userposjava);
	}
	
	@Test
	public void Deletar() throws SQLException {
		UserPosDao userPosDao = new UserPosDao();
		Userposjava userposjava = userPosDao.Buscar(5);
		userPosDao.deletar(userposjava.getId());
	}
	@Test
	public void salvartelefone() {
		UserPosDao userPosDao = new UserPosDao();
		Usertelefone usertelefone = new Usertelefone();
		
		usertelefone.setnumero("(31)986618798");
		usertelefone.setTipo("Celular");
		usertelefone.setId_userposjava(6);		
		userPosDao.salvartelefone(usertelefone);
	}
	@Test
	public void BuscarUsertelJoinn() {		 
		UserPosDao userPosDao = new UserPosDao();				
		List<BeanUsertel> listTeste = userPosDao.BuscarJoinn(6);		
		for (BeanUsertel beanUsertel : listTeste) {
			System.out.println(beanUsertel);
		}	
		
	}
	@Test
	public void DeletaGeral() {
		UserPosDao userPosDao = new UserPosDao();	
		userPosDao.deleteUser(7);
	}
	
}
