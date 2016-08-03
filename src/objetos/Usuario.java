package objetos;

public class Usuario {
	
	String usuario;
	String clave;
	
	public Usuario(String usN, String usC){
		this.setUsuario(usN);
		this.setClave(usC);
	}
	
	public String getUsuario() {
		return usuario;
	}
	
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	public String getClave() {
		return clave;
	}
	
	public void setClave(String clave) {
		this.clave = clave;
	}
	
}
