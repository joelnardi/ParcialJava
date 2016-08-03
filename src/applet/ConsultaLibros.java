package applet;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

import objetos.Libro;
import objetos.Usuario;

public class ConsultaLibros extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = 1L;

	private JButton btnListaLibros, btnReservar, btnAgregar, btnEliminar;
	private JLabel lblUsuario, lblClave, lblUsrAct;
	private JTextField txtUsuario, txtClave;
	private Container container;
	private BorderLayout layout;
	JTextArea salida = new JTextArea();
	
	private static ArrayList<Libro> libs = new ArrayList<>();
	public static Usuario[] usuarios = new Usuario[5];
				
	public ConsultaLibros() {
		super( "Biblioteca" );
		layout = new BorderLayout();
		container = getContentPane();
		container.setLayout( layout );
		
		final JPanel eastPanel = new JPanel();
		eastPanel.setLayout(new FlowLayout());
		
		JPanel westPanel = new JPanel();
		westPanel.setLayout( new GridLayout(4,1) );
		
		eastPanel.add(salida);
		
//Panel Norte
		JPanel northPanel = new JPanel();
		northPanel.setLayout( new GridLayout(2,1) );
		
		JPanel p5=new JPanel();
	    p5.setLayout(new GridLayout(1,4));
	    
	    lblUsuario = new JLabel("Usuario");
		p5.add(lblUsuario);
		
		txtUsuario = new JTextField();
		p5.add(txtUsuario);

		lblClave = new JLabel("Clave");
		p5.add(lblClave);
		
		txtClave = new JPasswordField();
		p5.add(txtClave);
		
		txtClave.addFocusListener(new FocusListener() {
			public void focusLost(FocusEvent e) {
				String cad1=txtUsuario.getText();
	            String cad2=txtClave.getText(); 	        
		            if (cad1.equals("admin")==true && cad2.equals("admin")==true) {
		            	btnReservar.setVisible(true);
		    			btnAgregar.setVisible(true);
		    			btnEliminar.setVisible(true);
		    			lblUsrAct.setText("Usuario activo: "+ txtUsuario.getText());
		    			lblUsrAct.setVisible(true);
		            } else if (cad1.equals("usuario")==true && cad2.equals("usuario")==true) {
		            	btnReservar.setVisible(true);
		            	btnAgregar.setVisible(false);
		    			btnEliminar.setVisible(false);
		    			lblUsrAct.setText("Usuario activo: "+ txtUsuario.getText());
		    			lblUsrAct.setVisible(true);
		            } else {
		            	btnReservar.setVisible(false);
		            	btnAgregar.setVisible(false);
		    			btnEliminar.setVisible(false);
		    			lblUsrAct.setVisible(false);
		            }
			}
		   public void focusGained(FocusEvent e) {
		      
		   }
		});
				
		JPanel p6=new JPanel();
	    p6.setLayout(new BorderLayout());
		
	    lblUsrAct = new JLabel("Usuario activo: "+ txtUsuario.getText());
		lblUsrAct.setVisible(false);
		p6.add(lblUsrAct, BorderLayout.EAST);
		
		northPanel.add(p5);
		northPanel.add(p6);

//Panel Oeste
		
		btnListaLibros = new JButton( "Lista de libros" );
		btnListaLibros.addActionListener(
			new ActionListener() {
				public void actionPerformed( ActionEvent event ){
					salida.removeAll();
					salida.setText("NumeroID - Titulo - Autor - Reservado? - UsuarioReserva");
					for (Libro n: libs) {
						String str1 = Integer.toString(n.getNumero_id());
						String str2 = n.getTitulo();
						String str3 = n.getAutor();
						String str4, str5;
						if (n.isReservado()){
							str4 = "Reservado";
							str5 = n.getUsReserva();
						} else {
							str4 = "Disponible";
							str5 = " ";
						}
						salida.append("\n " + str1 + " - "+ str2 + " - "+ str3 + " - "+ str4 + " - "+ str5);
					}
					salida.setVisible(true);
				}
			}
		);
		JPanel p1=new JPanel();
	    p1.setLayout(new FlowLayout());
	    p1.add(btnListaLibros);
	    westPanel.add(p1);
				
		btnReservar = new JButton( "Reservar" );
		btnReservar.setVisible(false);
		btnReservar.addActionListener(
			new ActionListener() {
				public void actionPerformed( ActionEvent event ){
					int numero = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese codigo de libro a reservar"));
					for (Libro n: libs) {
					    int num = n.getNumero_id();
					    	if (num == numero) {
					    	n.setReservado(true);
					    	n.setUsReserva(txtUsuario.getText());
					    } 
					}
					JOptionPane.showMessageDialog(null, "Se reservo el libro, se sugiere listar nuevamente.");
				}
			}
		);
		JPanel p2=new JPanel();
	    p2.setLayout(new FlowLayout());
	    p2.add(btnReservar);
	    westPanel.add(p2);
		
		btnAgregar = new JButton( "Agregar" );
		btnAgregar.setVisible(false);
		btnAgregar.addActionListener(
			new ActionListener() {
				public void actionPerformed( ActionEvent event ){
					try {
						Libro n = new Libro();
						int numero = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese codigo de libro a ingresar"));
						    n.setNumero_id(numero);
						String titu = JOptionPane.showInputDialog(null, "Ingrese titulo de libro a ingresar");	
						    n.setTitulo(titu);
						String auto = JOptionPane.showInputDialog(null, "Ingrese autor de libro a ingresar");	
						    n.setAutor(auto);
						    n.setReservado(false);
						    n.setUsReserva(null);
						libs.add(n);
						JOptionPane.showMessageDialog(null, "Se agrego el libro, se sugiere listar nuevamente.");
					} catch (NumberFormatException e) {
						e.printStackTrace();
						JOptionPane.showMessageDialog(null, "Ingreso de datos invalidos");
					} catch (HeadlessException e) {
						e.printStackTrace();
						JOptionPane.showMessageDialog(null, "Ingreso de datos invalidos");
					}
				}
			}
		);
		JPanel p3=new JPanel();
	    p3.setLayout(new FlowLayout());
	    p3.add(btnAgregar);
	    westPanel.add(p3);
		
		btnEliminar = new JButton( "Eliminar" );
		btnEliminar.setVisible(false);
		btnEliminar.addActionListener(
			new ActionListener() {
				public void actionPerformed( ActionEvent event ){
					try {
						int numero = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese codigo de libro a eliminar"));
						for (Libro n: libs) {
							int num = n.getNumero_id();
						    if (num == numero) {
						    		libs.remove(n);			    		
						    } 
						}
						JOptionPane.showMessageDialog(null, "Se elimino el libro, se sugiere listar nuevamente.");
					} catch (NumberFormatException e) {
						e.printStackTrace();
						JOptionPane.showMessageDialog(null, "Error de formato del numero, ingrese un codigo existente");
					} catch (HeadlessException e) {
						e.printStackTrace();
						JOptionPane.showMessageDialog(null, "Error de formato del numero, ingrese un codigo existente");
					}
				}
			}
		);
		JPanel p4=new JPanel();
	    p4.setLayout(new FlowLayout());
	    p4.add(btnEliminar);
	    westPanel.add(p4);
		
		

//Panel Este
		
		
		container.add(eastPanel, BorderLayout.EAST);
		container.add(northPanel, BorderLayout.NORTH);
		container.add(westPanel, BorderLayout.WEST);
		container.add(eastPanel, BorderLayout.EAST);
		
		setMinimumSize(new Dimension(500,300));
		setSize( 500, 300 );
		setVisible( true );
		
	}
	
	public static void inicializar () {
		libs.add(new Libro(1,"El retorno del Rey","Tolkien",false,null));
		libs.add(new Libro(2,"La comunidad del anillo","Tolkien",false,null));
		libs.add(new Libro(3,"Las dos torres","Tolkien",false,null));
		libs.add(new Libro(4,"El silmarillion","Tolkien",false,null));
		libs.add(new Libro(5,"El hobbit","Tolkien",false,null));
		
		usuarios[0]= new Usuario("admin","admin");
		usuarios[1]= new Usuario("usuario","usuario");
		usuarios[2]= new Usuario("juan","juan");
		usuarios[3]= new Usuario("coco","coco");
		usuarios[4]= new Usuario("pepe","pepe");
		
	}
	
	public static void main (String args[]) {
		ConsultaLibros application = new ConsultaLibros();
		ConsultaLibros.inicializar();
		application.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
