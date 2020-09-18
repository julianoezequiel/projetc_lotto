package br.com.ot.service.ms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

@Service
public class Gerador {

	public Integer[] Valor2() throws InterruptedException {
		// ArrayList s = new ArrayList();
		List list = new ArrayList();
		Integer[] saida = { 0, 0, 0, 0, 0, 0 };
		Integer[] novo = null;
		Random ger = new Random();

		for (int z = 0; z < 1; z++) {

			for (int i = 0; i < 6; i++) {
				int num = ger.nextInt(61);
				list.add(num);
			}
			Collections.sort(list);

			for (int s = 0; s < 5; s++) {
				if (list.get(s).equals(list.get(s + 1))) {
					list.clear();
					novo = Valor2();
					return novo;
				}
			}
		}

		if (!list.get(0).equals(0)) {

			saida[0] = (Integer) list.get(0);
			saida[1] = (Integer) list.get(1);
			saida[2] = (Integer) list.get(2);
			saida[3] = (Integer) list.get(3);
			saida[4] = (Integer) list.get(4);
			saida[5] = (Integer) list.get(5);
			list.clear();
			return saida;
		}
		list.clear();
		novo = Valor2();
		return novo;
	}

}
