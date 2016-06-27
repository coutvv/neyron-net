package ru.coutvv.neyronnet;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Класс отвечающий за создание, сохраненине и так далее изображений
 * 
 * @author lomovtsevrs
 *
 */
public class ImageController {
	
	private static volatile ImageController INSTANCE = new ImageController();
	
	public static ImageController getInstance() {//синглтон, да
		return INSTANCE;
	}
	
	public void createImage(int[][] data, String name) throws IOException {
		BufferedImage img = new BufferedImage(data.length, data[0].length, BufferedImage.TYPE_INT_RGB);
		//рисуем
		Graphics gr = img.getGraphics();
		gr.setColor(new Color(0, 0, 200));
		for(int i = 0; i< img.getWidth(); i++) {
			for(int j = 0;j <img.getHeight(); j++) {
				
				int r = data[i][j],
					g = data[i][j],
					b = data[i][j];
				gr.setColor(new Color(r,g,b));
				gr.drawRect(i, j, 1, 1);
			}
		}
		//сохранение
		File file = new File(name);
		if(!file.getParentFile().exists())
			file.getParentFile().mkdirs();
		ImageIO.write(img, "png", file);
	}
	
	public int[][] getDataFromImage(String filename) throws IOException { 
		File imgFile = new File(filename);
		if(!imgFile.exists()) {
			return voidMas(); //криво, но хочется пока так
		}
		BufferedImage img = ImageIO.read(imgFile);
		
		int[][] data = new int[img.getHeight()][img.getWidth()];
		for(int i = 0; i <img.getHeight(); i++) {
			for(int j = 0; j< img.getWidth(); j++ ) {
				Color c = new Color(img.getRGB(i, j));
				data[i][j] = (c.getBlue() + c.getGreen() + c.getRed()) / 3;
			}
		}
		return data;
	}
	
	/**
	 * Пустая матрица 30х30
	 * @return
	 */
	private int[][] voidMas() {
		int[][] result = new int[30][30];
		for(int i = 0; i< 30; i++) {
			for(int j = 0; j< 30; j++) {
				result[i][j] = 0;
			}
		}
		return result;
	}
}
