package ru.coutvv.neyronnet;

import java.io.IOException;

import ru.coutvv.neyronnet.network.NeyronNetwork;

/**
 * Точка входа
 * 
 * @author lomovtsevrs
 */
public class Main {

	public static void main(String[] args) throws IOException {
		ImageController ctrl = ImageController.getInstance();
		NeyronNetwork network = new NeyronNetwork();
		
		//experiment A
		int[][] aData = ctrl.getDataFromImage("res/это_а.png");
		int[][] bData = ctrl.getDataFromImage("res/это_б.png");
		int[][] vData = ctrl.getDataFromImage("res/это_в.png");

		int change = 0;
		for(int i = 0; i < 1000; i++) {
			change = itIs(aData, "А", network) ? change+1 : change;
			change = itIs(bData, "Б", network) ? change+1 : change;
			change = itIs(vData, "В", network) ? change+1 : change;
		}
		System.out.println("Процент угадываний: " + change/30);
		
		//experiment B
		int success = 0;
		int[][] data = ctrl.getDataFromImage("res/a/a1.png");
		success = itIs(data, "А", network) ? success + 1 : success;
		data = ctrl.getDataFromImage("res/a/a2.png");
		success = itIs(data, "А", network) ? success + 1 : success;
		data = ctrl.getDataFromImage("res/a/a3.png");
		success = itIs(data, "А", network)? success + 1 : success;
		
		success = itIs(bData, "Б", network)? success + 1 : success;
		
		System.out.println("Угадал: " + success + " раз из 4ёх");
		
		
	}

	public static boolean itIs(int[][] data, String answer, NeyronNetwork network) throws IOException {
		return answer.equals(network.letIdentify(data, answer));
	}
}
