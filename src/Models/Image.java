package Models;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Image {
	BufferedImage pic;
	
	public Image(String name){
		try {
			BufferedImage image = ImageIO.read(new File(name));
			int w = image.getWidth();
			int h = image.getHeight();
			pic = image;
			System.out.println(w+" "+h);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
