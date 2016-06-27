package ru.coutvv.neyronnet.network;

public abstract class AbstractNeyron {
	
	protected final int MAX_X = 30;
	
	protected final int MAX_Y = 30;
	
	abstract public int getWeight(int[][] data);
}
