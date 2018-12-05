# Threads
Código y clases java.

## Clase NumberThread
```java
public class NumberThread extends Thread { //Clase d'ejemple

	int num;
	
	public NumberThread(int n) {
		num = n;
	}
	
	public void run() {
		for (int i = 0; i < 10; i++) {
			System.out.println(num);
		}
	}
	
}
```

## Clase NumberRunnable
```java
public class NumberRunnable implements Runnable { //Més útil

	int num;
	
	public NumberRunnable(int n) {
		num = n;
	}
	
	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			System.out.println(num);
		}	
	}
	
}
```

## Clase Numbers
```java
public class Numbers {

	public static void main(String[] args) {
		NumberThread n1, n2, n3, n4, n5, n6;
		
		n1 = new NumberThread(1); n1.start();
		n2 = new NumberThread(2); n2.start();
		n3 = new NumberThread(3); n3.start();
		n4 = new NumberThread(4); n4.start();
		n5 = new NumberThread(5); n5.start();
		n6 = new NumberThread(6); n6.start();
		
		//Més útil
		Thread nt1, nt2, nt3, nt4, nt5, nt6;
		
		nt1 = new Thread(new NumberRunnable(71)); nt1.start();
		nt2 = new Thread(new NumberRunnable(72)); nt2.start();
		nt3 = new Thread(new NumberRunnable(73)); nt3.start();
		nt4 = new Thread(new NumberRunnable(74)); nt4.start();
		nt5 = new Thread(new NumberRunnable(75)); nt5.start();
		nt6 = new Thread(new NumberRunnable(76)); nt6.start();
	}

}
```
