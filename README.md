# Programación de Servicios y Procesos
![portada](https://cdn-images-1.medium.com/max/1200/0*ngXgBNNdx6iiWP8q.png)

La programación de servicios y procesos es una de las importantes ramas del desarrollo de aplicaciones multiplataforma, donde se desempeñan los siguientes objetivos de aprendizaje:

- La utilización de las capacidades ofrecidas por el sistema operativo para desarrollar aplicaciones.
- La programación de aplicaciones compuestas por varios procesos e hilos maximizando así su eficiencia.
- El desarrollo de aplicaciones con capacidades para comunicarse y ofrecer servicios a través de una red.
- La utilización de mecanismos de seguridad en el desarrollo de aplicaciones.

Una vez finalizado el breve resumen de la asignatura, a continuación voy a listar las actividades que he realizado durante el curso.

## ÍNDICE DE ACTIVIDADES
<details open>
<summary>Primera evaluación</summary>
  
### [Actividad 0 - Quiniela](Actividad%200/Act0_Quiniela.md)

### [Actividad 1 - Definiciones básicas](Actividad%201/Definiciones%2basicas.md)

### [Actividad 2 - Verificación DNI](Actividad%202/Act2_VerificarDNI.md)

### [Actividad 3 - Herencia básica](Actividad%203/Act3_Herencia%20básica.md)

### [Actividad 4 - Aeropuertos](Actividad%204/Act4_Aeropuertos.md)

### [Actividad 5 - Definiciones pt 2](Actividad%205/Definiciones%20pt%202.md)

### [Actividad 6 - Barra de aplicaciones preferidas](Actividad%206/Act6_AplicacionesPreferidas.md)

### [Actividad 7 - Aplicaciones del SO en procesos](Actividad%207/Act7_AplicacionesSO.md)
</details>
<br>
<details open>
<summary>Segunda evaluación</summary>
  
### [Proyecto API League Of Legends](Actividad%200/Act0_Quiniela.md)
Diría que este es mi proyecto estrella respecto a esta asignatura. Como he puesto tanto esfuerzo en él he decidido que voy a detallar aquí abajo como lo he hecho y el funcionamiento de algunas clases.

Esta aplicación se basa en extraer el historial de partidas de un jugador mediante la API que nos proporciona Riot y mostrarlo gráficamente en pantalla:

![Muestra](Proyecto%20API%20League%20Of%20Legends/muestra.gif)

Como se puede observar, he optado por un diseño simple y elegante, he rediseñado el típico TextField con unos bordes ovalados para una barra de búsqueda más bonita y he sacado partido de la animación de componentes en tiempo real mediante hilos. La interfaz gráfica es la de Swing, y para construir las ventanas y los paneles he usado el plugin de [WindowBuilder](Proyecto%20API%20League%20Of%20Legends/windowbuilder.png).

## Clase PanelPartida
[![Image from Gyazo](https://i.gyazo.com/b1afb9d9c04a80bfe3c670f5d9222206.png)](https://gyazo.com/b1afb9d9c04a80bfe3c670f5d9222206)

Aquí es donde construyo el panel de la partida que mostrará prácticamente toda la información de manera visual (como el campeón jugado, la fecha, duración de la partida, asesinatos, muertes, asistencias, cs obtenidos, objetos comprados, campeones del equipo aliado y enemigo y sus usuarios, etc). Este panel es el que se reutiliza dinámicamente para construir una lista que será el historial (clase PanelHistorial).

## Clase ApiRequest
En esta clase hago uso de la librería riot-api para facilitar la conexión y la extracción de datos a través de los métodos que he creado **requestSummoner()**, **requestMatchList()** y **requestMatch()**. Para recuperar la información de todos los campeones en forma de lista uso **getAllChampions()**, donde recorro el json "champion.json" y paso los datos a un objeto de la clase Campeon.

> **Nota:** Para poder conectar con la API de Riot, me he tenido que registrar como desarrollador en su página y hacer uso de la key que se genera automáticamente. Esta [expira cada 24h](https://i.gyazo.com/8599d585b89e8e020cd609f3905f773d.png), y si se usa la aplicación con la key expirada mostrará un error Forbbiden. Por tanto iré actualizándola cada poco tiempo hasta la corrección (se encuentra en la clase ApiRequest).

```java
public class ApiRequest {

	private static final String API_KEY = "XXXXX-XXXXX-XXXXX-XXXXX"; // EXPIRA EN 24 H
	private static final ApiConfig config = new ApiConfig().setKey(API_KEY);
	private static final RiotApi api = new RiotApi(config);
  
  ...
```

> **Nota 2:** El resto de las explicaciones están comentadas en el propio código.

### [Aplicación Cliente-Servidor](Actividad%200/Act0_Quiniela.md)

### [Substracción de información web con JavaFX](Actividad%200/Act0_Quiniela.md)
</details>
