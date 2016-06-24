package ru.coutvv.neyronnet;

/**
 * 
 * @author lomovtsevrs
 */
public class Neyron extends AbstractNeyron {
	
	private String name;
	
	private int[][] memory;
	

	public Neyron(String name, int[][] data) {
		this.name = name;
		memory = new int[MAX_X][MAX_Y];
		for(int i = 0; i < MAX_X; i++ ) {
			for(int j = 0; j< MAX_Y; j++) {
				memory[i][j] = data[i][j];
			}
		}
	}
	

	@Override
	public int getWeight(int[][] data) {
		int result = 0;
		for(int i = 0; i < MAX_X; i++) {
			for(int j = 0;j < MAX_Y; j++) {
				int mem = memory[i][j],
					in  = data[i][j];
				if(Math.abs(mem - in) < 120 && in < 250)
					result++;
				if((in != 0 && in < 250) || ( mem != 0 && in <250)) { //магические условия
					this.memory[i][j] = Math.round((mem+(mem+in)/2)/2);
				} 
			}
		}
		return result;
	}


	public String getName() {
		return name;
	}

	public int[][] getMemory() {
		return memory;
	}
	
}
