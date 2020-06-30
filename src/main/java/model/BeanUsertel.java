package model;

public class BeanUsertel {
    String email;
    String nome;
    String numero;
	
    public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	@Override
	public String toString() {
		return "BeanUsertel [email=" + email + ", nome=" + nome + ", numero=" + numero + "]";
	}
}
