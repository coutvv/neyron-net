package ru.coutvv.neyronnet;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class Main {

	private static int MAX_COLOR = 255;
	private static int MIN_COLOR = 0;
	
	public static void main(String[] args) throws IOException {
//		BufferedImage img = ImageIO.read(new File("file"));
//		img.getGraphics().
		File f = new File("res/wft.png");
		System.out.println(f.exists());
		
		BufferedImage img = ImageIO.read(f);
		System.out.println(img.getHeight());
		System.out.println(img.getRGB(3, 4)); 
		for(int i = 0; i< img.getWidth(); i++) {
			for(int j = 0;j <img.getHeight(); j++) {
				Color c = new Color(img.getRGB(i, j));
				System.out.println(c.getBlue());
			}
		}
		
		Graphics gr = img.getGraphics();
		gr.setColor(new Color(0, 0, 200));
		for(int i = 0; i< img.getWidth(); i++) {
			for(int j = 0;j <img.getHeight(); j++) {
				int r = new Random().nextInt((MAX_COLOR - MIN_COLOR) + 1) + MIN_COLOR,
					g = new Random().nextInt((MAX_COLOR - MIN_COLOR) + 1) + MIN_COLOR,
					b = new Random().nextInt((MAX_COLOR - MIN_COLOR) + 1) + MIN_COLOR;
				gr.setColor(new Color(r,g,b));
				gr.drawRect(i, j, 1, 1);
			}
		}
		
		File out = new File("res/wft_out.png");
		ImageIO.write(img, "png", out);
	}

	
	public void test() {
		Neyron n = new Neyron("shit", new int[10][10]);

	}
}
