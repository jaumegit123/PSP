# Actividad 3. Herencia básica
Actividad con varias clases java usando el principio de herencia:

## Empleado
```java
public class Empleado {

	private String nombre;
	
	public Empleado(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "Empleado " + nombre;
	}
	
}
```

## Operario
```java
public class Operario extends Empleado {

	public Operario(String nombre) {
		super(nombre);
	}

	@Override
	public String toString() {
		return super.toString() + " -> Operario";
	}

}
```

## Directivo
```java
public class Directivo extends Empleado {

	public Directivo(String nombre) {
		super(nombre);
	}
	
	@Override
	public String toString() {
		return super.toString() + " -> Directivo";
	}

}
```

## Oficial
```java
public class Oficial extends Operario {

	public Oficial(String nombre) {
		super(nombre);
	}
	
	@Override
	public String toString() {
		return super.toString() + " -> Oficial";
	}

}
```

## Tecnico
```java
public class Tecnico extends Operario {

	public Tecnico(String nombre) {
		super(nombre);
	}
	
	@Override
	public String toString() {
		return super.toString() + " -> Técnico";
	}

}
```
