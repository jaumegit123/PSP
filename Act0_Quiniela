# Actividad 0. Quiniela
## Se muestran todas las combinaciones posibles de la quiniela, menos las más irreales
Este es el código realizado en Java.
```java

public class quiniela {

	public static void main(String[] args) {

		char[] c = { '1', 'x', '2' };

		int cont = 0, cont1, contx, cont2;

		for (int i = 0; i < 3; i++) {
			for (int i2 = 0; i2 < 3; i2++) {
				for (int i3 = 0; i3 < 3; i3++) {
					for (int i4 = 0; i4 < 3; i4++) {
						for (int i5 = 0; i5 < 3; i5++) {
							for (int i6 = 0; i6 < 3; i6++) {
								for (int i7 = 0; i7 < 3; i7++) {
									for (int i8 = 0; i8 < 3; i8++) {
										for (int i9 = 0; i9 < 3; i9++) {
											for (int i10 = 0; i10 < 3; i10++) {
												for (int i11 = 0; i11 < 3; i11++) {
													for (int i12 = 0; i12 < 3; i12++) {
														for (int i13 = 0; i13 < 3; i13++) {
															for (int i14 = 0; i14 < 3; i14++) {
																String s = c[i] + " " + c[i2] + " " + c[i3] + " "
																		+ c[i4] + " " + c[i5] + " " + c[i6] + " "
																		+ c[i7] + " " + c[i8] + " " + c[i9] + " "
																		+ c[i10] + " " + c[i11] + " " + c[i12] + " "
																		+ c[i13] + " " + c[i14] + "\t" + cont++;

																s = s.replace(" ", "");
																cont1 = 0;
																contx = 0;
																cont2 = 0;
																for (int j = 0; j < 14; j++) {
																	if (s.charAt(j) == '1')
																		cont1++;
																	else if (s.charAt(j) == 'x')
																		contx++;
																	else if (s.charAt(j) == '2')
																		cont2++;
																}

																if ((cont1 < 4 || cont1 > 7) || (contx < 3 || contx > 5) || (cont2 < 2 || cont2 > 5))
																	continue;
																else
																	System.out.println(s);
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		
	}

}


```
