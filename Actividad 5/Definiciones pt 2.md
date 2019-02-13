# Actividad 5. Definiciones pt 2.
En esta actividad vamos a explicar algunos de los contenidos más importantes del temario.
## Aplicación
En general, una aplicación es un programa compilado (aunque a veces interpretado), escrito en cualquier lenguaje de programación, y se trata de un programa informático que permite a un usuario utilizarlo como herramienta para un fin específico. Las aplicaciones son parte del software de una computadora, y se ejecutan sobre el sistema operativo.

Una aplicación de software suele tener uno o varios objetivos: navegar en la web, revisar correo, explorar el disco duro, editar textos, jugar, etc.

Son ejemplos de aplicaciones Google Chrome, Word, Excel, WinRar, etc.

## Servicio
Un servicio es un proceso que no muestra ninguna ventana ni gráfico en pantalla porque no está pensado para que el usuario lo maneje directamente.

Habitualmente, un servicio es un programa que atiende a otro programa y se ejecuta en segundo plano.

## Proceso
Un proceso es una unidad de actividad que ejecuta una secuencia de instrucciones que ejecutará el procesador mientras lee un programa determinado. Esto también implica a la memoria reservada, al estado de ejecución en el momento determinado y al conjunto de recursos del sistema.

Están formados por:

- Las instrucciones de un programa destinadas a ser ejecutadas por el procesador.
- Su estado de ejecución en un momento dado, esto es, los valores de los registros de la CPU para dicho programa.
- Su memoria de trabajo, es decir, la memoria que ha reservado y sus contenidos.
- Información que permite al sistema operativo su planificación.

En los sistemas operativos multihilo es posible crear tanto hilos como procesos. Un proceso solamente puede crear hilos para sí mismo y dichos hilos comparten toda la memoria reservada para el proceso.

Algunos tipos de procesos
- Proceso distribuido: Es una forma de proceso en la que los datos y las funciones están distribuidos en los distintos elementos de una configuración o sistema.
- Proceso paralelo: Es un tipo de proceso asimilable a los grandes sistemas.
- Proceso cooperativo: Alude al hecho de que una única aplicación se gestiona desde dos (o más) diferentes configuraciones hardware.

## Hilo
Un hilo es simplemente una tarea que puede ser ejecutada al mismo tiempo que otra tarea.

Los hilos de ejecución que comparten los mismos recursos, sumados a estos recursos, son en conjunto conocidos como un proceso. El hecho de que los hilos de ejecución de un mismo proceso compartan los recursos hace que cualquiera de estos hilos pueda modificar estos recursos.

Lo que es propio de cada hilo es el contador de programa, la pila de ejecución y el estado de la CPU (incluyendo el valor de los registros).

El proceso sigue en ejecución mientras al menos uno de sus hilos de ejecución siga activo. Cuando el proceso finaliza, todos sus hilos de ejecución también han terminado. Asimismo en el momento en el que todos los hilos de ejecución finalizan, el proceso no existe más y todos sus recursos son liberados.

## Programación concurrente
Es la división de un problema en subproblemas que se solucionan de forma individual, para crear un programa o aplicación que obtenga un mayor rendimiento de los recursos disponibles.

Hace referencia a las técnicas de programación que son utilizadas para expresar la concurrencia entre tareas y solución de los problemas de comunicación y sincronización entre procesos. La programación concurrente es la ejecución simultánea de múltiples tareas interactivamente. Estas tareas pueden ser un conjunto de procesos o hilos de ejecución creados por un único programa. Las tareas se pueden ejecutar en una sola CPU, en varios procesadores, o en una red de computadores distribuidos.

## Programación paralela
La computación paralela es una forma de cómputo en la que muchas instrucciones se ejecutan simultáneamente, operando sobre el principio de que problemas grandes, a menudo se pueden dividir en unos más pequeños, que luego son resueltos simultáneamente en paralelo ganando rendimiento frente a una programación estándar.
Hay varias formas diferentes de computación paralela: paralelismo a nivel de bit, paralelismo a nivel de instrucción, paralelismo de datos y paralelismo de tareas. 

La máxima aceleración posible de un programa como resultado de la paralelización se conoce como la [ley de Amdahl](https://es.wikipedia.org/wiki/Ley_de_Amdahl).

## Programación distribuida
La computación distribuida es un modelo en el que los componentes de un sistema de software se comparten entre varias computadoras para mejorar la eficiencia y el rendimiento. Son computadoras conectadas en red, que luego se comunican y coordinan sus acciones al pasar mensajes entre sí. Los componentes interactúan entre sí para lograr un objetivo común. Las tres importantes características de los sistemas distribuidos son: la concurrencia de los componentes, existencia de un reloj global y la falla independiente de los componentes. Los ejemplos de sistemas distribuidos varían desde sistemas basados en SOA hasta juegos multijugador masivos en línea y aplicaciones peer-to-peer.

Un programa de computadora que se ejecuta dentro de un sistema distribuido se llama un programa distribuido (y la programación distribuida es el proceso de escribir dichos programas).
