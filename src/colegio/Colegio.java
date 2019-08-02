package colegio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Colegio {
	
	Scanner t=new Scanner(System.in);
	
	ArrayList <Alumno> estudiantes = new ArrayList<Alumno>();
	ArrayList <Profesor> profesores = new ArrayList<Profesor>();
	Asignatura [] asignaturas;

	Colegio()  // Constructor por defecto
	{
		asignaturas=new Asignatura[10];
	}
	
	public int pedirNumero(String texto) {
		
		int num = 0;
		boolean ok= false;
		
		do {
			System.out.print(texto);
			String numero=t.nextLine();
			try{
				num = Integer.parseInt(numero);
				ok=true;
				}catch(Exception e)	{
					System.out.println("\nEl dato introducido debe ser un numero\n");
					}
		}while(!ok);
		return num;
	}
	
	public boolean pedirLetras(String cadena)
	{
		boolean ok=true;
				
		for (int i = 0; i < cadena.length(); i++)
		{
			if (Character.isDigit(cadena.toUpperCase().charAt(i))) ok=false; //Se ha encontrado un caracter que no es letra
		}
		if (ok==false) System.out.println("Debe introducir únicamente LETRAS");
		return ok;
	}
	
	public boolean checkEmail(String email, int cont) {
		
		boolean ok=false; 
        // Patrón para validar el email
        Pattern pattern = Pattern
                .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
 
        Matcher mather = pattern.matcher(email);
 
        if (mather.find() == true) {
            ok=true;
        } else {
        	if (cont<3) 
        	{
            System.out.println("\nEl email ingresado NO es válido.");
            System.out.println("Escriba uno valido.\n");
        	}
            ok=false;
        }
        
        return ok;
    }
	
	public void checkAsig(int asig) {
		//Controla que el número introducido sea mayor que 0
		if (asig<1) System.out.println("Introduzca un número de asignatura del listado");
		//Si la posición del Array apunta a Null, muestra el mensaje
		else if (asignaturas[asig-1]==null)	System.out.println("No existe ninguna asignatura con ese número");
	}
	
	public void checkAlu(int alu) {
		//Controla que el número introducido sea mayor que 0
		if (alu<1) System.out.println("Introduzca un número de alumno del listado");
		//Si la posición del Array apunta a Null, muestra el mensaje
		else if (alu>estudiantes.size()) System.out.println("No existe ningún alumno con ese número");
	}
	
	public void checkProfe(int profe) {
		//Controla que el número introducido sea mayor que 0
		if (profe<1) System.out.println("Introduzca un número de profesor del listado");
		//Si la posición del Array apunta a Null, muestra el mensaje
		else if (profe>profesores.size()) System.out.println("No existe ningún profesor con ese número");
	}
	
	public void crearAsignatura()	{
		
		Asignatura auxasig;
		String nombre;
		String curso;
		int inicio=0;
		int fin=0;
		
		// Solicitamos los datos de una asignatura por teclado
		System.out.println("\n- AÑADIR NUEVA ASIGNATURA -\n");
		System.out.println("Nombre de la asignatura: ");
		nombre=t.nextLine();
		System.out.println("Nombre del curso: ");
		curso=t.nextLine();
		
		inicio=pedirNumero("Año de Inicio: ");
				
		fin=pedirNumero("Año de Finalización: ");
				
		auxasig= new Asignatura (nombre, curso, inicio, fin);
		
		if (!existeAsig(auxasig)) {
			
			int cuentasig=0;  // Contador de Asignaturas creadas
			
			// Comprueba las posiciones de array que contienen datos (!=null) y aumenta el contador si es necesario
			while (cuentasig<asignaturas.length && asignaturas[cuentasig]!=null)
			{
				cuentasig++;
			}			
			
			// Si existen posiciones vacias en el array, asigna los datos solicitados en la posición qeu indique el contador
			if (cuentasig!=asignaturas.length)
			{
				asignaturas[cuentasig]=new Asignatura(nombre, curso, inicio, fin);
			}
			// Si el array está lleno, muestra mensaje
			else {
				System.out.println("CREANDO NUEVA ASIGNATURA");
				int k=asignaturas.length+1;
				Asignatura[] arrayaux = new Asignatura[k];
				
				for (int i=0; i<asignaturas.length; i++) {
					arrayaux[i]=asignaturas[i];
				}
				asignaturas=arrayaux;
				asignaturas[cuentasig]=new Asignatura(nombre, curso, inicio, fin);
			}
		}
	}
		
	public void visualizarTAsig() {
		
		int cont=0;
		for (int i=0; i<asignaturas.length; i++)
		{
			if (asignaturas[i]!=null) // Muestra los datos hasta que encuentre una posición que apunte a Null
				{
				System.out.print("\n"+(i+1)+" -> ");	
				asignaturas[i].visualizar();// llama a la función visualizar dentro de la clase Asignatura
				cont++;
				}
		} if (cont==0) System.out.println("NO HAY NINGUNA ASIGNATURA DEFINIDA");
	}
	
	public void eliminarAsig() {
		
		int asig=0;
		
		visualizarTAsig();

		do{
			asig=pedirNumero("\nIndique el nº de la ASIGNATURA: ");
			checkAsig(asig);
			}while (asig<1 || asignaturas[asig-1]==null);
		
			asignaturas[asig-1]=null;
			
			int j=0;
			
			Asignatura[] arrayaux = new Asignatura[asignaturas.length];
			
			for (int i=0; i<asignaturas.length; i++) {
				
				if (asignaturas[i]!=null) {
					arrayaux[j]=asignaturas[i];
					j++;
				}
			}asignaturas=arrayaux;
			
			System.out.println("\n*** ASIGNATURA ELIMINADA ***");
			visualizarTAsig();
	}
	
	public void crearAlumno()	{
		// Solicitamos los datos de una asignatura por teclado
		String dni="";
		String email="";
		String nombre="";
		String apellidos;
		Alumno aux= new Alumno();
		int contemail=0;
		int contdni=0;
		boolean dniok=true;
		
		System.out.println("\n- AÑADIR NUEVO ALUMNO -\n");
				
		do {
			System.out.print("\nDNI del Alumno: ");
			dni=t.nextLine().toUpperCase();
			contdni++;
			
			if (contdni==3)
			{
				dniok=false;
				dni=" ";
				System.out.println("Ha introducido un dato no valido 3 veces.");
				System.out.println("No se guardaran los datos del Alumno.");
			}
			
			if (!aux.comprobarDNI(dni) && contdni<3) System.out.println("El DNI introducido no es válido");
		
		} while (!aux.comprobarDNI(dni) && contdni<3);//Llama al método para comprobar el DNI
		
				
		if (existeAlu(dni))
		{
			dniok=false;
			System.out.println("El DNI introducido pertenece a otro Alumno.\nRevise sus datos y el listado de Alumnos.");
		}
		
		if (dniok) {
		
			do
			{
				System.out.print("\nNombre del Alumno: ");
				nombre=t.nextLine();
			}while(pedirLetras(nombre)==false);
			
			do
			{
				System.out.print("\nApellidos del Alumno: ");
				apellidos=t.nextLine();
			}while(pedirLetras(apellidos)==false);
		
			do {
				System.out.print("\nEmail del Alumno: ");
				email=t.nextLine();
				contemail++;
				if (contemail==3)
				{
					email=" ";
					System.out.println("Ha introducido un dato no valido 3 veces.");
					System.out.println("El campo Email se dejará en blanco.");
				}
			}while (!checkEmail(email,contemail) && contemail<3);

			if(!existeAlu(dni) && dniok==true) {
				aux=new Alumno(nombre, apellidos, email, dni);
				estudiantes.add(aux);
				
			}else if (existeAlu(dni)) System.out.println("El DNI introducido pertenece a otro Alumno. El alumno no se ha añadido.\nRevise sus datos y el listado de alumnos.");
		}
	}

	public void eliminarAlumno() {
		
		int alu=0;
		
		visualizarTalu();
		
		do {
			alu=pedirNumero("\nIndique el nº del ALUMNO: ");
			checkAlu(alu);
		}while (alu<1 || alu>estudiantes.size());
		
		estudiantes.remove(alu-1);
		
		System.out.println("\n** ELIMINADO -- LISTADO DE ALUMNOS ACTUALIZADO **\n");
		visualizarTalu();
	}
	
	public void visualizarTalu() {
		
		for (int i=0; i<estudiantes.size(); i++)
		{
			System.out.print("\n"+(i+1)+" -> ");
			estudiantes.get(i).visualizar();// llama a la función visualizar dentro de la clase Alumnos
		}
		if (estudiantes.size()==0) System.out.println("NO EXISTEN ALUMNOS MATRICULADOS");
	}
	
	public void matricularAlumno() {
		
		int alu=-1, asig=-1;

		visualizarTalu();
		
		do {
			alu=pedirNumero("\nIndique el nº del ALUMNO: ");
			checkAlu(alu);
		}while (alu<1 || alu>estudiantes.size());
		
		visualizarTAsig();
		
		do{
			asig=pedirNumero("\nIndique el nº de la ASIGNATURA: ");
			checkAsig(asig);
			}while (asig<1 || asignaturas[asig-1]==null);
		
		if (estudiantes.get(alu-1).estaMatriculado(asignaturas[asig-1])) {
			System.out.println("\nEl alumno ya está matriculado en esta asignatura");
			
		}else if (estudiantes.get(alu-1).matricular(asignaturas[asig-1])) {
			System.out.println("\n*** ALUMNO MATRICULADO CORRECTAMENTE ***");	
			
		} else System.out.println("\n*** ERROR AL INTENTAR MATRICULAR ALUMNO ***");
	}	
	
	public void asignarProfesor() {
		
		int profe=-1, asig=-1;

		visualizarTprofes();
		
		do {
			profe=pedirNumero("\nIndique el nº del PROFESOR: ");
			checkProfe(profe);
		}while (profe<1 || profe>profesores.size());
		
		visualizarTAsig();
		
		do{
			asig=pedirNumero("\nIndique el nº de la ASIGNATURA: ");
			checkAsig(asig);
		}while (asig<1 || asignaturas[asig-1]==null);
		
		if (profesores.get(profe-1).imparteAsig(asignaturas[asig-1])) {
			System.out.println("\nEl Profeso ya tiene asignada esta asignatura");
			
		}else if (profesores.get(profe-1).asignar(asignaturas[asig-1])) {
			
			System.out.println("\n*** DOCENCIA ASIGNADA CORRECTAMENTE ***");
			
		}else System.out.println("\n*** ERROR AL INTENTAR ASIGNAR DOCENCIA ***");
	}
	
	public void verAsigAlu() // Muestra las asignaturas de un alumno
	{		
		int alu=-1;
		
		visualizarTalu();  //Muestra el listado de Alumnos
		
		do {	
			alu=pedirNumero("\nIndique el nº del ALUMNO: ");
			checkAlu(alu);
		}while (alu<1 || alu>estudiantes.size());
		
		estudiantes.get(alu-1).visualizarAsig();
	}
	
	public void verAsigAluAnyo() // Muestra las asignaturas de un alumno
	{		
		int alu=-1;
		int anyo=0;
		
		visualizarTalu();  //Muestra el listado de Alumnos
		
		do {	
			alu=pedirNumero("\nIndique el nº del ALUMNO: ");
			checkAlu(alu);
		}while (alu<1 || alu>estudiantes.size());
		
		anyo=pedirNumero("\nIndique el Año: ");
		
		estudiantes.get(alu-1).visualizarAsig(anyo);
	}
	
	public void verAsigProfeAnyo() // Muestra las asignaturas de un Profesor
	{		
		int profe=-1;
		int anyo=0;
		
		visualizarTprofes();  //Muestra el listado de Profesores
		
		do {	
			profe=pedirNumero("\nIndique el nº del PROFESOR: ");
			checkProfe(profe);
		}while (profe<1 || profe>profesores.size());
		
		anyo=pedirNumero("\nIndique el nº del AÑO: ");
		
		profesores.get(profe-1).visualizarAsig(anyo);
	}
	
	public void crearProfesor()	{
		
		String dni="";
		String email="";
		String nombre="";
		String apellidos="";
		int contemail=0;
		int contdni=0;
		boolean dniok=true;
		Profesor aux= new Profesor();
		
		// Solicitamos los datos de una asignatura por teclado
		System.out.println("\n- AÑADIR NUEVO PROFESOR -\n");
		
		do {
			System.out.print("\nDNI del Profesor: ");
			dni=t.nextLine().toUpperCase();
			contdni++;
			
			if (contdni==3)
			{
				dniok=false;
				dni=" ";
				System.out.println("Ha introducido un dato no valido 3 veces.");
				System.out.println("No se guardaran los datos del Profesor.");
			}
			
			if (!aux.comprobarDNI(dni) && contdni<3) System.out.println("El DNI introducido no es válido");
		
		} while (!aux.comprobarDNI(dni) && contdni<3);//Llama al método para comprobar el DNI
		
		if (existeProfe(dni))
		{
			dniok=false;
			System.out.println("El DNI introducido pertenece a otro Profesor.\nRevise sus datos y el listado de Profesores.");
		}
		
		if(dniok)
		{
			do
			{
				System.out.print("\nNombre del Profesor: ");
				nombre=t.nextLine();
			}while(pedirLetras(nombre)==false);
			
			do
			{
				System.out.print("\nApellidos del Profesor: ");
				apellidos=t.nextLine();
			}while(pedirLetras(apellidos)==false);
			
			System.out.print("\nDirección del Profesor: ");
			String direccion=t.nextLine();
			
			do {
				System.out.print("\nEmail del Profesor: ");
				email=t.nextLine();
				contemail++;
				
				if (contemail==3)
				{
					email=" ";
					System.out.println("Ha introducido un dato no valido 3 veces.");
					System.out.println("El campo Email se dejará en blanco.");
				}
				
			}while (!checkEmail(email,contemail) && contemail<3);
			
			if(!existeProfe(dni) && dniok==true)
			{
				Profesor profeaux =new Profesor(nombre, apellidos, email, dni, direccion);
				profesores.add(profeaux);
			}
		}
	}
	
	public void eliminarProfesor() {
		
		int profe=0;
		
		visualizarTprofes();
		
		do {
			profe=pedirNumero("\nIndique el nº del PROFESOR: ");
			checkProfe(profe);
		}while (profe<1 || profe>profesores.size());
		
		profesores.remove(profe-1);
		
		System.out.println("\n** ELIMINADO -- LISTADO DE PROFESORES ACTUALIZADO **\n");
		visualizarTprofes();
	}
	
	public void visualizarTprofes() {
		
		for (int i=0; i<profesores.size(); i++)
		{
			System.out.print("\n"+(i+1)+" -> ");
			profesores.get(i).visualizar();// llama a la función visualizar dentro de la clase Profesor
		}
		if (profesores.size()==0) System.out.println("NO EXISTEN PROFESORES EN EL LISTADO");
	}
	
	public void verAsigProfe() // Muestra las asignaturas de un profesor
	{		
		int profe=-1;
		
		visualizarTprofes();  //Muestra el listado de profesores
		
		do {	
			profe=pedirNumero("\nIndique el nº del PROFESOR: ");
			checkProfe(profe);
		}while (profe<1 ||profe>profesores.size());
		profesores.get(profe-1).visualizarAsig();
		}

	public void verAlumnosAsig() // Muestra los alumnos de una asignatura
	{
		int asig=-1;
		
		visualizarTAsig();
		
		do{
			asig=pedirNumero("\nIndique el nº de la ASIGNATURA: ");
			checkAsig(asig);
		}while (asig<1 || asignaturas[asig-1]==null);
		
		//Recorre el array y muestra los datos de los estudiantes que contengan la asignatura		
		for (int i=0; i<estudiantes.size(); i++)
		{
			if (estudiantes.get(i).estaMatriculado(asignaturas[asig-1])) {
						System.out.println();
						estudiantes.get(i).visualizar();
					}
				}
		}
	
	public void verProfesAsig() // Muestra los profesores de una asignatura
	{
		int asig=-1;
		
		visualizarTAsig();
		
		do{
			asig=pedirNumero("\nIndique el nº de la ASIGNATURA: ");
			checkAsig(asig);
		}while (asig<1 || asignaturas[asig-1]==null);
		
		//Recorre el array y muestra los datos de los estudiantes que contengan la asignatura		
		for (int i=0; i<profesores.size(); i++)
		{
						if (profesores.get(i).imparteAsig(asignaturas[asig-1])) {
						System.out.println();
						profesores.get(i).visualizar();
				}
			}
		}

	public boolean existeAsig(Asignatura asig) {
		
		boolean existe=false;
		
		for (int i=0; i<asignaturas.length;i++) {
			
			if (asignaturas[i]!=null) {
				if (asignaturas[i].getNombre().equals(asig.getNombre()) && asignaturas[i].getCurso().equals(asig.getCurso()) && asignaturas[i].getInicio()==asig.getInicio() && asignaturas[i].getFin()==asig.getFin())	
				{
					existe=true;
					System.out.println("\n*** LA ASIGNATURA YA EXISTE ***");
				}
			}
		}
		return existe;
	}
	
	public boolean existeAlu(String dni) {
		
		boolean existe=false;
		
		for (int i=0; i<estudiantes.size(); i++) {
			if (estudiantes.get(i).getDni().equals(dni)) {
				existe=true;
				System.out.println("\n*** EL ALUMNO YA EXISTE ***");
			}
		}
		return existe;
	}
	
	public boolean existeProfe(String dni) {
		
		boolean existe=false;
		
		for (int i=0; i<profesores.size(); i++) {
			if (profesores.get(i).getDni().equals(dni)) {
				existe=true;
				System.out.println("\n*** EL PROFESOR YA EXISTE ***");
			}
		}
		return existe;
	}
	
	public void guardarAsig() {
		
		File f=new File("asignaturas.txt");
		FileWriter fw=null;
		BufferedWriter bw=null;
		try
		{
			fw=new FileWriter(f);
			bw=new BufferedWriter(fw);
			
			for (int i=0; i<asignaturas.length; i++) {
				
				if (asignaturas[i]!=null) {
				bw.write(asignaturas[i].getNombre()+"##"+asignaturas[i].getCurso()+"##"+asignaturas[i].getInicio()+"##"+asignaturas[i].getFin());
				bw.newLine();
				}
			}
			System.out.println("*** LISTADO DE ASIGNATURAS GUARDADO CORRECTAMENTE ***");
			
		}catch (Exception e) {
			System.out.println("Ha ocurrido un error "+e.toString());
		}finally {
			try 
			{
				if(bw!=null)
				{
					bw.close();
				}
				if(fw!=null)
				{
					fw.close();
				}
			}catch (Exception e) {}
		}
	}
	
	public void leerAsig() {
		
		File f= new File("asignaturas.txt");
		int lineas=0;
		if(f.exists())
		{
			if(f.canRead())
			{
				FileReader fr=null;
				BufferedReader br=null;
				try
				{
					fr= new FileReader(f);
					br=new BufferedReader(fr);
					String linea="";
					linea=br.readLine();

					while(linea!=null)
					{
						String []trozos=linea.split("##");
						
						asignaturas[lineas]= new Asignatura (trozos[0],trozos[1],Integer.parseInt(trozos[2]),Integer.parseInt(trozos[3]));
						
						
						linea=br.readLine();
						lineas++;
					}
					System.out.println("*** LISTADO DE ASIGNATURAS LEIDO CORRECTAMENTE ***   "+f.getPath()+" "+f.length()+" bytes");
					
				}catch (Exception e) {
					System.out.println("Ha ocurrido un error "+e.toString());
					}finally 
				{
					try
					{
						if(fr!=null)
						{
							fr.close();
							if(br!=null)
							{
								br.close();
							}
						}
					}catch (Exception e) {}
				}
			}else System.out.println("El fichero no se puede leer");
		}else System.out.println("El fichero no existe");
	}
	
	public void guardarAlu() {
		
		File f=new File("alumnos.txt");
		FileWriter fw=null;
		BufferedWriter bw=null;
		try
		{
			fw=new FileWriter(f);
			bw=new BufferedWriter(fw);
			
			for (int i=0; i<estudiantes.size(); i++) {
				
				bw.write(estudiantes.get(i).getNombre()+"##"+estudiantes.get(i).getApellidos()+"##"+estudiantes.get(i).getEmail()+"##"+estudiantes.get(i).getDni()+"##");
				bw.newLine();
			}

			System.out.println("*** LISTADO DE ALUMNOS GUARDADO CORRECTAMENTE ***");
			
		}catch (Exception e) {
			System.out.println("Ha ocurrido un error "+e.toString());
		}finally
		{
			try 
			{
				if(bw!=null)
				{
					bw.close();
				}
				if(fw!=null)
				{
					fw.close();
				}
			}catch (Exception e) {}
		}
	}
	
	public void leerAlu() {
		
		File f= new File("alumnos.txt");
	
		if(f.exists())
		{
			if(f.canRead())
			{
				FileReader fr=null;
				BufferedReader br=null;
				try
				{
					fr= new FileReader(f);
					br=new BufferedReader(fr);
					String linea="";
					linea=br.readLine();

					while(linea!=null)
					{
						Alumno aux= new Alumno();
						String []trozos=linea.split("##");
						String nombre=trozos[0];
						String apellidos=trozos[1];
						String email=trozos[2];
						String dni=trozos[3];
						
						if (!existeAlu(dni)) {
							aux = new Alumno (nombre,apellidos,email,dni);
							estudiantes.add(aux);					
						}
						linea=br.readLine();
					}
				
					System.out.println("*** LISTADO DE ALUMNOS LEIDO CORRECTAMENTE ***   "+f.getPath()+" "+f.length()+" bytes");
					
				}catch (Exception e) {
					System.out.println("Ha ocurrido un error "+e.toString());
					}finally 
				{
					try
					{
						if(fr!=null)
						{
							fr.close();
							if(br!=null)
							{
								br.close();
							}
						}
					}catch (Exception e) {}
				}
			}else System.out.println("El fichero no se puede leer");
			
		}else System.out.println("El fichero no existe");
	}
	
	public void leerMatriculas() {
		File f= new File("matriculas.txt");
		
		if(f.exists())
		{
			if(f.canRead())
			{
				FileReader fr=null;
				BufferedReader br=null;
				try
				{
					fr= new FileReader(f);
					br=new BufferedReader(fr);
					String linea="";
					linea=br.readLine();

					while(linea!=null)
					{
						String []trozos=linea.split("##");
						String dni=trozos[0];
						String nombre=trozos[1];
						String curso=trozos[2];
						
						int inicio=Integer.parseInt(trozos[3]);
						int fin=Integer.parseInt(trozos[4]);
						int alu=-1;
						int asig=-1;
	
						for (int i=0; i<estudiantes.size(); i++)
						{
							if (dni.equals(estudiantes.get(i).getDni())) alu=i;
						}
						
						for (int j=0; j<asignaturas.length; j++)
						{
							if(asignaturas[j]!=null)
							{
								if (nombre.equals(asignaturas[j].getNombre()) && curso.equals(asignaturas[j].getCurso()) && inicio==asignaturas[j].getInicio() && fin==asignaturas[j].getFin())
								{
									asig=j;
								}
							}
						}
						estudiantes.get(alu).matricular(asignaturas[asig]);
						linea=br.readLine();
					}
				
					System.out.println("*** LISTADO DE MATRICULAS LEIDO CORRECTAMENTE ***   "+f.getPath()+" "+f.length()+" bytes");
					
				}catch (Exception e) {
					System.out.println("Ha ocurrido un error "+e.toString());
					e.printStackTrace();
					}finally 
				{
					try
					{
						if(fr!=null)
						{
							fr.close();
							if(br!=null)
							{
								br.close();
							}
						}
					}catch (Exception e) {}
				}
			}else System.out.println("El fichero no se puede leer");
			
		}else System.out.println("El fichero no existe");
	}
	
	public void guardarMatriculas() {
		
		File f=new File("matriculas.txt");
		FileWriter fw=null;
		BufferedWriter bw=null;
		try
		{
			fw=new FileWriter(f);
			bw=new BufferedWriter(fw);
			
			for (int i=0; i<estudiantes.size(); i++)
			{
				if (estudiantes.get(i).matriculado!=null)
				{
					for (int j=0; j<estudiantes.get(i).matriculado.length; j++)
					{
						if (estudiantes.get(i).matriculado[j]!=null)
						{
							bw.write(estudiantes.get(i).getDni()+"##");
							bw.write(estudiantes.get(i).matriculado[j].getNombre()+"##"+estudiantes.get(i).matriculado[j].getCurso()+"##"+estudiantes.get(i).matriculado[j].getInicio()+"##"+estudiantes.get(i).matriculado[j].getFin()+"##");
							bw.newLine();
						}
					}
				}
			}
			System.out.println("*** LISTADO DE MATRICULAS GUARDADO CORRECTAMENTE ***");
			
		}catch (Exception e) {
			System.out.println("Ha ocurrido un error "+e.toString());
		}finally
		{
			try 
			{
				if(bw!=null)
				{
					bw.close();
				}
				if(fw!=null)
				{
					fw.close();
				}
			}catch (Exception e) {}
		}
	}
	
	public void guardarProfe() {
		
		File f=new File("profesores.txt");
		FileWriter fw=null;
		BufferedWriter bw=null;
		try
		{
			fw=new FileWriter(f);
			bw=new BufferedWriter(fw);
			
			for (int i=0; i<profesores.size(); i++) {
				
				bw.write(profesores.get(i).getNombre()+"##"+profesores.get(i).getApellidos()+"##"+profesores.get(i).getEmail()+"##"+profesores.get(i).getDni()+"##"+profesores.get(i).getDireccion()+"##");
				bw.newLine();
			}
			System.out.println("*** LISTADO DE PROFESORES GUARDADO CORRECTAMENTE ***");
			
		}catch (Exception e) {
			System.out.println("Ha ocurrido un error "+e.toString());
		}finally
		{
			try 
			{
				if(bw!=null)
				{
					bw.close();
				}
				if(fw!=null)
				{
					fw.close();
				}
			}catch (Exception e) {}
		}
	}
	
	public void guardarDocencias() {
		File f=new File("docencias.txt");
		FileWriter fw=null;
		BufferedWriter bw=null;
		try
		{
			fw=new FileWriter(f);
			bw=new BufferedWriter(fw);
			
			for (int i=0; i<profesores.size(); i++) {
				if (profesores.get(i).imparte!=null) {
					for (int j=0; j<profesores.get(i).imparte.length; j++) {
						if (profesores.get(i).imparte[j]!=null) {
							bw.write(profesores.get(i).getDni()+"##");
							bw.write(profesores.get(i).imparte[j].getNombre()+"##"+profesores.get(i).imparte[j].getCurso()+"##"+profesores.get(i).imparte[j].getInicio()+"##"+profesores.get(i).imparte[j].getFin()+"##");
							bw.newLine();
						}
					}
				}
				
			}
			System.out.println("*** LISTADO DE DOCENCIAS GUARDADO CORRECTAMENTE ***");
			
		}catch (Exception e) {
			System.out.println("Ha ocurrido un error "+e.toString());
		}finally
		{
			try 
			{
				if(bw!=null)
				{
					bw.close();
				}
				if(fw!=null)
				{
					fw.close();
				}
			}catch (Exception e) {}
		}
	}
	
	public void leerProfe() {
		
		File f= new File("profesores.txt");

		if(f.exists())
		{
			if(f.canRead())
			{
				FileReader fr=null;
				BufferedReader br=null;
				try
				{
					fr= new FileReader(f);
					br=new BufferedReader(fr);
					String linea="";
					linea=br.readLine();

					while(linea!=null)
					{
						Profesor aux= new Profesor();
						
						String []trozos=linea.split("##");	
						String nombre=trozos[0];
						String apellidos=trozos[1];
						String email=trozos[2];
						String dni=trozos[3];
						String direccion=trozos[4];
						
						if(!existeProfe(dni)) {
							aux = new Profesor (nombre, apellidos,email,dni,direccion);
							profesores.add(aux);
						}
						
						linea=br.readLine();
					}
					System.out.println("*** LISTADO DE PROFESORES LEIDO CORRECTAMENTE ***   "+f.getPath()+" "+f.length()+" bytes");
					
				}catch (Exception e) {
					System.out.println("Ha ocurrido un error "+e.toString());
					}finally 
				{
					try
					{
						if(fr!=null)
						{
							fr.close();
							if(br!=null)
							{
								br.close();
							}
						}
					}catch (Exception e) {}
				}
			}else System.out.println("El fichero no se puede leer");
			
		}else System.out.println("El fichero no existe");		
	}
	
	public void leerDocencias() {
		
		File f= new File("docencias.txt");
		
		if(f.exists())
		{
			if(f.canRead())
			{
				FileReader fr=null;
				BufferedReader br=null;
				try
				{
					fr= new FileReader(f);
					br=new BufferedReader(fr);
					String linea="";
					linea=br.readLine();

					while(linea!=null)
					{
						String []trozos=linea.split("##");
						String dni=trozos[0];
						String nombre=trozos[1];
						String curso=trozos[2];
						
						int inicio=Integer.parseInt(trozos[3]);
						int fin=Integer.parseInt(trozos[4]);
						int profe=-1;
						int asig=-1;
	
						for (int i=0; i<profesores.size(); i++)
						{
							if (dni.equals(profesores.get(i).getDni())) profe=i;
						}
						
						for (int j=0; j<asignaturas.length; j++)
						{
							if(asignaturas[j]!=null)
							{
								if (nombre.equals(asignaturas[j].getNombre()) && curso.equals(asignaturas[j].getCurso()) && inicio==asignaturas[j].getInicio() && fin==asignaturas[j].getFin())
								{
									asig=j;
								}
							}
						}
						profesores.get(profe).asignar(asignaturas[asig]);
						linea=br.readLine();
					}
				
					System.out.println("*** LISTADO DE DOCENCIAS LEIDO CORRECTAMENTE ***   "+f.getPath()+" "+f.length()+" bytes");
					
				}catch (Exception e) {
					System.out.println("Ha ocurrido un error "+e.toString());
					e.printStackTrace();
					}finally 
				{
					try
					{
						if(fr!=null)
						{
							fr.close();
							if(br!=null)
							{
								br.close();
							}
						}
					}catch (Exception e) {}
				}
			}else System.out.println("El fichero no se puede leer");
			
		}else System.out.println("El fichero no existe");
	}
	
	public void ordenAlu() {
		
		Collections.sort(estudiantes);
	}
	
	public void ordenProfes() {
		
		Collections.sort(profesores);
	}
	
	public void ordenAsig() {
		Arrays.sort(asignaturas, new Comparator<Asignatura>() {

			@Override
			public int compare(Asignatura o1, Asignatura o2) {
				if (o1==null) return 1;
				return o1.compareTo(o2);
			}});
	}
}