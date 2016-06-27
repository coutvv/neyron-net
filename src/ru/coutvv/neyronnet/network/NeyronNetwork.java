package ru.coutvv.neyronnet.network;

import java.io.IOException;

import ru.coutvv.neyronnet.ImageController;

/**
 * Типа сеть нейронов. Должна учить и отвечать что за хрень изображена
 * 
 * @author lomovtsevrs
 *
 */
public class NeyronNetwork {
	
	private int NUMBER_CHAR = 3;
	
	private Neyron[] net;
	
	private ImageController ctrl;
	
	public NeyronNetwork() throws IOException {
		ctrl = ImageController.getInstance();
		net = new Neyron[NUMBER_CHAR];
		
		for(int i = 0; i< NUMBER_CHAR; i++ ){
			char tmp = (char) ('А'+i);
			String name = tmp + "";
			int[][] data = ctrl.getDataFromImage("res/letters/" + name + ".png");
			net[i] = new Neyron(name, data);
		}
	}
	
	public String letIdentify(int[][] data, String answer) throws IOException {
		String result = ""; 
		int mostWeight = 0;
		for(Neyron n : net) {
			int neyronWeight = n.getWeight(data);
			if(mostWeight < neyronWeight) {
				result = n.getName();
				mostWeight = neyronWeight;
			}
		}
		saveMemory(answer);
		return result;
	}
	
	private void saveMemory(String name) throws IOException {
		for(Neyron n : net) {
			if(n.getName().equals(name)) {
				ctrl.createImage(n.getMemory(), "res/letters/" + name + ".png");
			} else {
				n.setMemory(ctrl.getDataFromImage("res/letters/" + n.getName() + ".png"));
			}
		}
	}
	
}
