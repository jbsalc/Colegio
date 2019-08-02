package colegio;

public class Asignatura implements Comparable <Asignatura>{

	private String nombre;
	private String curso;
	private int inicio;
	private int fin;
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	public int getInicio() {
		return inicio;
	}

	public void setInicio(int inicio) {
		this.inicio = inicio;
	}

	public int getFin() {
		return fin;
	}

	public void setFin(int fin) {
		this.fin = fin;
	}
	
	Asignatura() // Constructor por defecto
	{
		nombre="Por defecto";
		curso="Por defecto";
		inicio=2000;
		fin=2000;
	}
	
	Asignatura (String nombre, String curso, int inicio, int fin) // Constructor sobrecargado
	{
		this.setNombre(nombre);
		this.setCurso(curso);
		this.setInicio(inicio);
		this.setFin(fin);
	}
	
	public void visualizar() // Muestra todos los datos de una asignatura
	{
		System.out.println("Nombre: "+nombre+"\t Curso: "+curso+"\tInicio: "+inicio+"\tFin: "+fin);
	}

	@Override
	public int compareTo(Asignatura otro)
	{		
		if (otro!=null)
		{
		return (this.getInicio()-otro.getInicio());// Devuelve el valor del resultado hacia el método .sort
		}else return -1;
	}
}