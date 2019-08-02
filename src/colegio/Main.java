package colegio;

import java.util.Scanner;

public class Main {

	static Colegio cole=new Colegio();
	static Scanner t=new Scanner(System.in);
	static int op=-1;
	
	public static void cargarDatos() {
		cole.leerAsig();
		cole.leerAlu();
		cole.leerProfe();
		cole.leerMatriculas();
		cole.leerDocencias();
	}
	
	public static void guardarDatos() {
//		cole.ordenAlu();
//		cole.ordenAsig();
//		cole.ordenProfes();
		cole.guardarAlu();
		cole.guardarAsig();
		cole.guardarProfe();
		cole.guardarMatriculas();
		cole.guardarDocencias();
	}
	
	public static void mostrarMenup () {
		
		do {
			
			System.out.println();
			System.out.println("--- MENU PRINCIPAL ---\n");
			System.out.println("1.-  Menu Alumno");
			System.out.println("2.-  Menu Profesor");
			System.out.println("3.-  Menu Asignaturas\n");
			System.out.println("\n0.- SALIR");
			
			do {
				op=cole.pedirNumero("\nSeleccione una opción [0-9]:  ");
			}while (op<0 | op>3);
			
			switch (op) {
			
			case 1:
				mostrarMenuAlu();
				break;
				
			case 2:
				mostrarMenuPro();
				break;
			
			case 3:
				mostrarMenuAsig();
				break;
				
			case 0:
				System.out.println("\n++ CERRANDO PROGRAMA. HASTA LA PRÓXIMA ++\n");;
				break;
			}
			
		}while (op!=0);

	}

	public static void mostrarMenuAlu () {
		
		do {
			
			System.out.println();
			System.out.println("--- MENU DE ALUMNOS ---\n");
			System.out.println("1.-  Mostrar Alumnos");
			System.out.println("2.-  Añadir Alumnos");
			System.out.println("3.-  Mostrar asignaturas del alumno");
			System.out.println("4.-  Mostrar asignaturas del alumno para el año ");
			System.out.println("5.-  Matricular alumno a la asignatura");
			System.out.println("6.-  Guardar a fichero");
			System.out.println("7.-  Cargar de fichero");
			System.out.println("8.-  Ordenar listado");
			System.out.println("9.-  Eliminar Alumno");
			System.out.println("0.-  SALIR");
			
			do {
				op=cole.pedirNumero("\nSeleccione una opción [0-9]:  ");
			}while (op<0 | op>9);
			
			switch (op) {
			
			case 1:
				cole.visualizarTalu();
				break;
				
			case 2:
				cole.crearAlumno();
				break;
			
			case 3:
				cole.verAsigAlu();
				break;
				
			case 4:
				cole.verAsigAluAnyo();
				break;
				
			case 5:
				cole.matricularAlumno();
				break;
				
			case 6:
				cole.guardarAlu();
				break;
				
			case 7:
				cole.leerAlu();
				break;
				
			case 8:
				System.out.println("\n*** LISTADO ANTERIOR ***\n");
				cole.visualizarTalu();
				cole.ordenAlu();
				System.out.println("\n*** LISTADO ORDENADO ***\n");
				cole.visualizarTalu();
				break;
				
			case 9:
				cole.eliminarAlumno();
				break;
			
			case 0:
				mostrarMenup();
				break;
				
			default:
				System.out.println("*** OPCIÓN INCORRECTA ***");
			}
		}while (op!=0);

	}

	public static void mostrarMenuPro () {
		
		do {
			
			System.out.println();
			System.out.println("--- MENU DE PROFESORES ---\n");
			System.out.println("1.-  Mostrar Profesores");
			System.out.println("2.-  Añadir profesores");
			System.out.println("3.-  Mostrar asignaturas que imparte el Profesor");
			System.out.println("4.-  Mostrar asignaturas del año que imparte el Profesor");
			System.out.println("5.-  Asignar docencia al Profesor");
			System.out.println("6.-  Guardar a fichero");
			System.out.println("7.-  Cargar de fichero");
			System.out.println("8.-  Ordenar Listado (Ascendente, Por DNI)");
			System.out.println("9.-  Eliminar Profesor");
			System.out.println("0.-  SALIR");
			
			do {
				op=cole.pedirNumero("\nSeleccione una opción [0-9]:  ");
			}while (op<0 | op>9);
			
			switch (op) {
			
			case 1:
				cole.visualizarTprofes();
				break;
				
			case 2:
				cole.crearProfesor();
				break;
			
			case 3:
				cole.verAsigProfe();
				break;
				
			case 4:
				cole.verAsigProfeAnyo();
				break;
				
			case 5:
				cole.asignarProfesor();
				break;
				
			case 6:
				cole.guardarProfe();
				break;
				
			case 7:
				cole.leerProfe();
				break;
				
			case 8:
				System.out.println("\n*** LISTADO ANTERIOR ***\n");
				cole.visualizarTprofes();
				cole.ordenProfes();
				System.out.println("\n*** LISTADO ORDENADO ***\n");
				cole.visualizarTprofes();
				break;
				
			case 9:
				cole.eliminarProfesor();
				break;
			
			case 0:
				mostrarMenup();
				break;
				
			default:
				System.out.println("*** OPCIÓN INCORRECTA ***");
			}
		}while (op!=0);
	}

	public static void mostrarMenuAsig () {
		
		do {
			
			System.out.println();
			System.out.println("\n--- MENU DE ASIGNATURAS ---\n");
			System.out.println("1.-  Mostrar Asignaturas");
			System.out.println("2.-  Añadir Asignaturas");
			System.out.println("3.-  Mostrar Alumnos de una Asignatura");
			System.out.println("4.-  Mostrar profesores de una Asignatura");
			System.out.println("5.-  Guardar a fichero");
			System.out.println("6.-  Cargar de fichero");
			System.out.println("7.-  Ordenar Listado (Ascendente, Año de Inicio, Curso, Nombre).");
			System.out.println("8.-  Eliminar Asignatura");
			System.out.println("\n0.-  SALIR");
			
			do {
			
				System.out.print("\nSeleccione una opción [0-8]:  ");
				op=t.nextInt();
				t.nextLine();
			
			}while (op<0 | op>9);
			
			switch (op) {
			
			case 1:
				cole.visualizarTAsig();
				break;
				
			case 2:
				cole.crearAsignatura();
				break;
			
			case 3:
				cole.verAlumnosAsig();
				break;
				
			case 4:
				cole.verProfesAsig();
				break;
			
			case 5:
				cole.guardarAsig();
				break;
				
			case 6:
				cole.leerAsig();
				break;
				
			case 7:
				cole.ordenAsig();
				break;
				
			case 8:
				cole.eliminarAsig();
				break;
			
			case 0:
				mostrarMenup();
				break;
				
			default:
				System.out.println("*** OPCIÓN INCORRECTA ***");
			}
		}while (op!=0);
	}

	public static void main(String[] args) {
		cargarDatos();
		mostrarMenup();
		guardarDatos();
	}
}