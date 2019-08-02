package colegio;

public abstract class Persona {
	
	private String nombre;
	private String apellidos;
	private String email;
	private String dni;
	
	Persona() // Constructor por defecto
	{
		setNombre("Por defecto");
		setApellidos("Por defecto");
		setEmail("correo@pordefecto.es");
		setDni("00000000X");
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public boolean comprobarDNI(String dniac) {
		// Array con las letras posibles del dni en su posición
        char[] letraDni = {
            'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D',  'X',  'B', 'N', 'J', 'Z', 'S', 'Q', 'V', 'H', 'L', 'C', 'K', 'E'
        };  
  
        String num= "";
        int ind = 0;  
  
        // compruebo su longitud que sea 9
        if (dniac.length() != 9){   
            return false;
        }  
        
        // compruebo que el 9º digito es una letra
        if (!Character.isLetter(dniac.charAt(8))) {
             return false;  
        }
  
        // Compruebo que lo 8 primeros digitos sean numeros
        for (int i=0; i<8; i++) {
   
             if(!Character.isDigit(dniac.charAt(i))){
                   return false;    
             }
             // si es numero, lo recojo en un String
             num += dniac.charAt(i);     
        }
  
        // paso a int el string donde tengo el numero del dni
        ind = Integer.parseInt(num);
  
        // calculo la posición de la letra en el array que corresponde a este dni
        ind %= 23;
  
        // verifico que la letra del dni corresponde con la del array
        if ((Character.toUpperCase(dniac.charAt(8))) != letraDni[ind]){
             return false;
       }  
       // si el flujo de la funcion llega aquí, es que el dni es correcto
       return true;
	}
}