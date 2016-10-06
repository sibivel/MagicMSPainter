import java.awt.AWTException;
import java.awt.Color;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class MagicPainter extends JFrame {
	private Robot r;
	private BufferedImage img;
	private int height;
	private int width = 200;
	private double stepX;
	private double stepY;
	private static int originX = 5;//5
	private static int originY = 144;//144
	private static int sleep = 15;
	private boolean enterPressed = false;
	private static int margin = 85;
	private Color current = null;
	
	public MagicPainter(String fileName){
		try {
			r = new Robot();
			img = ImageIO.read(new File(fileName));
			int trueHeight = img.getHeight();
			int trueWidth = img.getWidth();
			double ratio = (double)trueWidth/(double)trueHeight;
			height = (int)(width/ratio);
			stepX = (double)(trueWidth)/width;
			stepY = (double)(trueHeight)/height;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.addKeyListener(new KeyListener(){

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER)
					enterPressed = true;
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		
		}
				);
	}
	
	
	public void paintRGB() throws InterruptedException{
		r.mouseMove(250, 69);
		r.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		Thread.sleep(sleep);
		r.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
		r.mouseMove(originX, originY);
		int x = 0;
		int y = 0;
		for(int i = 0; i < height; i++){
			if(enterPressed)
				return;
			for(int j = 0; j < width; j++){
				Color c = new Color(img.getRGB(x, y));
				pickColor(c);
				r.mouseMove(originX+j, originY+i);
				if(!current.equals(Color.WHITE)){
					r.mousePress(InputEvent.BUTTON1_DOWN_MASK);
					Thread.sleep(sleep);
					r.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
				}
				x = (int)(j * stepX);
				y = (int)(i * stepY);
				if (x >= img.getWidth())
					x = img.getWidth()-10;
				if(y >= img.getHeight())
					y = img.getHeight()-10;
			}
		}
	}
	
	//white 766 106
	//black 785 106
	//red 807 106
	//blue 829 106
	//green 850 106
	//magenta 877 106
	//yellow 900 106
	//cyan 918 106
	public void pickColor(Color c) throws InterruptedException{
		if(c.getRed() > margin){
			if(c.getGreen() > margin){
				if(c.getBlue() > margin){
					if (!Color.WHITE.equals(current)){
						System.out.println("WHITE");
						/*r.mouseMove(765, 106);
						r.mousePress(InputEvent.BUTTON1_DOWN_MASK);
						Thread.sleep(sleep);
						r.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
						*/
						current = Color.WHITE;
						return;
					}else{
						return;
					}
				}else{
					if(!Color.YELLOW.equals(current)){
						System.out.println("YELLOW");
						r.mouseMove(900, 106);
						r.mousePress(InputEvent.BUTTON1_DOWN_MASK);
						Thread.sleep(sleep);
						r.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
						current = Color.YELLOW;
						return;
					}else{
						return;
					}
				}
			}else{
				if(c.getBlue() > margin){
					if(!Color.MAGENTA.equals(current)){
						System.out.println("MAGENTA");
						r.mouseMove( 877, 106);
						r.mousePress(InputEvent.BUTTON1_DOWN_MASK);
						Thread.sleep(sleep);
						r.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
						current = Color.MAGENTA;
						return;
					}else{
						return;
					}
				}else
					if(!Color.RED.equals(current)){
						System.out.println("RED");
						r.mouseMove(807, 106);
						r.mousePress(InputEvent.BUTTON1_DOWN_MASK);
						Thread.sleep(sleep);
						r.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
						current = Color.RED;
						return;
					}else{
						return;
					}	
			}
		}else
			if(c.getGreen() > margin){
				if(c.getBlue() > margin){
					//CYAN
					if (!Color.CYAN.equals(current)){
						System.out.println("CYAN");
						r.mouseMove(918, 106);
						r.mousePress(InputEvent.BUTTON1_DOWN_MASK);
						Thread.sleep(sleep);
						r.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
						current = Color.CYAN;
						return;
					}else{
						return;
					}
				}else{
					//green
					if (!Color.GREEN.equals(current)){
						System.out.println("GREEN");
						r.mouseMove(850, 106);
						r.mousePress(InputEvent.BUTTON1_DOWN_MASK);
						Thread.sleep(sleep);
						r.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
						current = Color.GREEN;
						return;
					}else{
						return;
					}
				}
			}else{
				if(c.getBlue() > margin){
					//blue
					if (!Color.BLUE.equals(current)){
						System.out.println("BLUE");
						r.mouseMove(829, 106);
						r.mousePress(InputEvent.BUTTON1_DOWN_MASK);
						Thread.sleep(sleep);
						r.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
						current = Color.BLUE;
						return;
					}else{
						return;
					}
				}else{
					//black
					if (!Color.BLACK.equals(current)){
						System.out.println("BLACK");
						r.mouseMove(785, 106);
						r.mousePress(InputEvent.BUTTON1_DOWN_MASK);
						Thread.sleep(sleep);
						r.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
						current = Color.BLACK;
						return;
					}else{
						return;
					}
				}
			}
			
	}
	
	private Color[] colors;
	private int currentIndex;
	private boolean white = false;
	public void pickColor30(Color c) throws InterruptedException{
		int min = -1;
		int mindex = 0;
		for(int i = 0; i<30; i++){
			int num = colorCompare(c, colors[i]);
			if (num < min || min == -1){
				min = num;
				mindex = i;
			}
		}
		if(currentIndex == mindex)
			return;
		
		int xCount = mindex % 10;
		int yCount = mindex / 10;
		
		int x = 765 + xCount*22;
		int y = 61 + yCount*20;
		if (colors[mindex].getBlue() == 255 && colors[mindex].getRed() == 255 && colors[mindex].getGreen() == 255){
			;//r.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
		}else{
			r.mouseMove(x, y);
			r.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			Thread.sleep(sleep);
			r.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
		}
		if(colors[mindex].equals(Color.WHITE))
			white = true;
		else{
			white = false;
			currentIndex = mindex;
			current = colors[currentIndex];
		}
		
		
		
	}
	
	public void makeColors(){
		colors = new Color[30];
		int x = 765;
		int y = 61;
		int count = 0;
		while(y < 110){
			while(x < 970){
				colors[count] = r.getPixelColor(x, y);
				System.out.println(x +", "+y + ": "+ colors[count].getRed() + "," + colors[count].getGreen() + "," + colors[count].getBlue() + ",");
				count++;
				
				x += 22;
			}
			System.out.println("OOOO");
			y += 20;
			x = 765;
		}
	}
	
	public void makeColors2(){
		colors = new Color[30];
		int x = 765;
		int y = 61;
		int count = 0;
		while(y < 110){
			while(x < 970){
				if(x > 765 && y < 90){
					colors[count] = Color.WHITE;
					count++;
				}else{
					colors[count] = r.getPixelColor(x, y);
					if(colors[count].equals(new Color(245,246,247)))
						colors[count] = Color.WHITE;
					System.out.println(x + ", " +y + ": " + colors[count].getRed() + "," + colors[count].getGreen() + "," + colors[count].getBlue() + ",");
					count++;
				}
				
				x += 22;
			}
			System.out.println("OOOO");
			y += 20;
			x = 765;
		}
	}
	
	public void paint30() throws InterruptedException{
		makeColors();
		int x = 0;
		int y = 0;
		for(int i = 0; i < height; i++){
			if(enterPressed)
				return;
			for(int j = 0; j < width; j++){
				Color c = new Color(img.getRGB(x, y));
				
				pickColor30(c);
				r.mouseMove(originX+j, originY+i);
				if(current != null && (!white)){
					r.mousePress(InputEvent.BUTTON1_DOWN_MASK);
					Thread.sleep(sleep);
					r.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
				}else{
					r.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
					Thread.sleep(0, 1);
				}
				x = (int)(j * stepX);
				y = (int)(i * stepY);
				if (x >= img.getWidth())
					x = img.getWidth()-1;
				if(y >= img.getHeight())
					y = img.getHeight()-1;
			}
		}
		
		
	}
	
	public int colorCompare(Color c1, Color c2){
		int red = Math.abs(c1.getRed() - c2.getRed());
		int green = Math.abs(c1.getGreen()-c2.getGreen());
		int blue = Math.abs(c1.getBlue()-c2.getBlue());
		
		return (red + green + blue);
	}
	
	public static void main(String[] args){
		//MagicPainter p = new MagicPainter("D:/Google Drive/The Best and Brightest Wallpapers/maxresdefault.jpg");
		//family
		MagicPainter p = new MagicPainter("D:/Pictures/Family 2014/DCT_7148.JPG");
		//MagicPainter p = new MagicPainter("D:/Google Drive/The Best and Brightest Wallpapers/012 - G3vLC.jpg");
		//MagicPainter p = new MagicPainter("D:/Google Drive/The Best and Brightest Wallpapers/pokemon-latias_00315157.jpg");
		//kirby 
		//MagicPainter p = new MagicPainter("D:/Google Drive/The Best and Brightest Wallpapers/431278.png");
		//sibi
		//MagicPainter p = new MagicPainter("picture.jpg");
		//old
		//MagicPainter p = new MagicPainter("Old.jpg");
		//squirtle
		//MagicPainter p = new MagicPainter("squirtle.jpeg");
		//fox kick
		//MagicPainter p = new MagicPainter("D:/Google Drive/The Best and Brightest Wallpapers/FoxSSB4DashAttack.png");
		//pikachu
		//MagicPainter p = new MagicPainter("D:/Google Drive/The Best and Brightest Wallpapers/CNyt2sH.png");
		//brawl
		//MagicPainter p = new MagicPainter("D:/Google Drive/The Best and Brightest Wallpapers/ZXpSH6E.png");
		//cap and tony
		//MagicPainter p = new MagicPainter("D:/Google Drive/The Best and Brightest Wallpapers/2015_Avengers_2-wallpaper-10357012.jpg");
		p.setVisible(true);
		System.out.println(p.img.getWidth());
		System.out.println(p.width);
		System.out.println(p.stepX);
		System.out.println(p.stepX*p.width);
		
		
		try {
			Thread.sleep(3000);
			p.paint30();
			//p.makeColors();
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
	}
	
}
