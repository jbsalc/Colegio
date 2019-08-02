package colegio;

public class Alumno extends Persona implements Comparable <Alumno> {//La implementación de Comparable es necesaria para la tarea de ordenación de este objeto

	Asignatura[] matriculado;
	
	int cuenta=0;
	
	Alumno() // Constructor por defecto
	{
		/*
		setNombre("Por defecto");
		setApellidos("Por defecto");
		setEmail("correo@pordefecto.es");
		setDni("00000000X");
		*/
		super();
		matriculado=new Asignatura[50];
	}
	
	Alumno (String nombre, String apellidos, String email, String dni) // Constructor sobrecargado
	{
		this.setNombre(nombre);
		this.setApellidos(apellidos);
		this.setEmail(email);
		this.setDni(dni);
		matriculado=new Asignatura[50];
	}
	
	
	public void visualizar()
	{
		System.out.println("\tNombre: "+this.getNombre()+"\t Apellidos: "+this.getApellidos()+"\tEmail: "+this.getEmail()+"\t DNI: "+this.getDni());
	}
	
	public boolean matricular(Asignatura asig)
	{
		boolean matricula=false;
		
		while (cuenta<matriculado.length && matriculado[cuenta]!=null)
		{
			cuenta++;  // Contador para controlar la siguiente posición libre del array
		}
//		Guarda el valor en el array si no se ha superado el rango
		if (cuenta!=matriculado.length)
		{
			matriculado[cuenta]=asig;
			matricula=true;
		}
//		Si se supera el rango muestra mensaje 
		else System.out.println("NÚMERO MÁXIMO DE ASIGNATURAS ALCANZADO");
		
		return matricula;
	}

	public void visualizarAsig() {
		
		int contador=0;
		System.out.println("\n"+getNombre()+" "+getApellidos());
		System.out.println("\nMatriculado en:");
			
		for (int i=0; i<matriculado.length; i++)
		{
			if (matriculado[i]!=null)
			{
				matriculado[i].visualizar();
				contador++;
			}
		}
		if (contador==0) System.out.println("Este Alumno no está matriculado en niguna asigantura.");
	}

	public boolean estaMatriculado (Asignatura asignatura) {
		
		boolean salida=false;
		
		for (int i=0; i<matriculado.length; i++) {
			
			if (matriculado[i]==asignatura) {
				salida=true;
			}
		} return salida;
	}

	public void visualizarAsig(int a) {
		
		int contador=0;
		System.out.println("\n"+getNombre()+" "+getApellidos());
		System.out.println("\nMatriculado en:");
			
		for (int i=0; i<matriculado.length; i++) {
			
			if (matriculado[i]!=null && a>matriculado[i].getInicio() && a<=matriculado[i].getFin())
			{
				matriculado[i].visualizar();
				contador++;
			}
		}
		if (contador==0) System.out.println("Este Alumno no está matriculado en niguna asigantura del año indicado.");
	}

	@Override //ESTE METODO ESTABLECE EL MODO DE ORDENACIÓN QUE IMPLEMENTARA .sort PARA EL OBJETO ALUMNO
	public int compareTo(Alumno otro) {
		int dev=0;
		dev=this.getNombre().compareTo(otro.getNombre());//Compara y ordena por Nombre
		if (dev==0) dev=this.getApellidos().compareTo(otro.getApellidos());//Si los nombres son iguales, compara y ordena por Apellidos
		return dev;// Devuelve el valor del resultado hacia el método .sort
	}
}