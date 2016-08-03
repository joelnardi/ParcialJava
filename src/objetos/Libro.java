package objetos;

public class Libro {

	int numero_id;
	String titulo;
	String autor;
	boolean reservado;
	String usReserva;
	
	public Libro (int i, String tit, String aut, boolean res, String usRes){
		this.setNumero_id(i);
		this.setTitulo(tit);
		this.setAutor(aut);
		this.setReservado(res);
		this.setUsReserva(usRes);
	}
	
	public Libro() {
		
	}

	public int getNumero_id() {
		return numero_id;
	}
	
	public void setNumero_id(int numero_id) {
		this.numero_id = numero_id;
	}
	
	public String getTitulo() {
		return titulo;
	}
	
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public String getAutor() {
		return autor;
	}
	
	public void setAutor(String autor) {
		this.autor = autor;
	}
	
	public boolean isReservado() {
		return reservado;
	}
	
	public void setReservado(boolean reservado) {
		this.reservado = reservado;
	}
	
	public String getUsReserva() {
		return usReserva;
	}
	
	public void setUsReserva(String usReserva) {
		this.usReserva = usReserva;
	}
	
}
