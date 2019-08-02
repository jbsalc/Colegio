package colegio;

public class Profesor extends Persona implements Comparable <Profesor> {//La implementación de Comparable es necesaria para la tarea de ordenación de este objeto

	private String direccion;
	Asignatura [] imparte;
	
	int cuenta=0;
	
	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	Profesor() // Constructor por defecto
	{
		super();
		setDireccion("Por defecto");
		imparte=new Asignatura[50];
	}
	
	Profesor(String nombre, String apellidos, String email, String dni, String direccion) // Constructor sobrecargado
	{
		this.setNombre(nombre);
		this.setApellidos(apellidos);
		this.setEmail(email);
		this.setDni(dni);
		this.setDireccion(direccion);
		imparte=new Asignatura[50];
	}
		
	
	public void visualizar()
	{
		System.out.println("\tNombre: "+this.getNombre()+"\t Apellidos: "+this.getApellidos()+"\tEmail: "+this.getEmail()+"\t DNI: "+this.getDni());
		System.out.println("\tDirección: "+this.getDireccion());
	}
	
	public boolean asignar(Asignatura asig)
	{
		boolean matricula=false;
		
		while (cuenta<imparte.length && imparte[cuenta]!=null)
		{
			cuenta++;  // Contador para controlar la siguiente posición libre del array
		}
//		Guarda el valor en el array si no se ha superado el rango
		if (cuenta!=imparte.length)
		{
			imparte[cuenta]=asig;
			matricula=true;
		}
//		Si se supera el rango muestra mensaje 
		else System.out.println("NÚMERO MÁXIMO DE ASIGNATURAS ALCANZADO");
		
		return matricula;
	}
	
	public void visualizarAsig() {
		int contador=0;
		//Muestra los datos del profesor
		System.out.println("\n"+getNombre()+" "+getApellidos());
		System.out.println("\nImparte:");
			
		for (int i=0; i<imparte.length; i++)
		{
			if (imparte[i]!=null)
			{
				imparte[i].visualizar();
			}
		}
		if (contador==0) System.out.println("Este Profesor no tiene asignada ninguna Docencia.");
	}
	
	public boolean imparteAsig (Asignatura asignatura) {
		
		boolean salida=false;
		
		for (int i=0; i<imparte.length; i++) {
			
			if (imparte[i]==asignatura) {
				salida=true;
			}
		}return salida;
	}
	
	public void visualizarAsig(int a) {
		
		System.out.println("\n"+getNombre()+" "+getApellidos());
		
		System.out.println("\nDocencias: ");
			
		for (int i=0; i<imparte.length; i++) {
			
			if (imparte[i]!=null && a>imparte[i].getInicio() && a<=imparte[i].getFin())
				
				imparte[i].visualizar();
			}
	}

	@Override//ESTE METODO ESTABLECE EL MODO DE ORDENACIÓN QUE IMPLEMENTARA .sort PARA EL OBJETO PROFESOR
	public int compareTo(Profesor otro) {
		int dev=0;
		dev=this.getDni().compareTo(otro.getDni());//En este caso se compara y ordena únicamente por el DNI
		return dev;// Devuelve el valor del resultado hacia el método .sort
	}

	
}