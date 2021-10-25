//DNA_Footprint_Plotter_ACat.java was written by Sammy during spring break 2020 when taking the AP java class at BB&N.

package covid19;

import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;

@SuppressWarnings("serial")
public class DNA_Footprint_Plotter_ACat extends JFrame {
	public static final int xRightbound = 1430;
	public static final int xLeftbound = 10;
	public static final int yLowerbound = 890;
	public static final int yUpperbound = 10;
	public static final int increment = 1;

	public DNA_Footprint_Plotter_ACat() {
		super();
		setTitle("ACat");
		setSize(1440, 900);
		this.setBackground(Color.BLACK);
		setVisible(true);
	}

	public static void main(String[] args) {
		DNA_Footprint_Plotter_ACat plotter = new DNA_Footprint_Plotter_ACat();
		plotter.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void paint(Graphics g) {
		String DNA = getFile();
		ArrayList<String> strand = read(DNA);
		int x0 = 1800, y0 = 2000;
		g.setColor(Color.BLUE);
		g.drawRect(10, 10, 1430, 890);
		drawLine(x0, y0, strand, g);
	}

	private static ArrayList<String> read(String strand) {
		ArrayList<String> sequence = new ArrayList<String>();
		for (int i = 0; i < strand.length(); i++) {
			sequence.add(strand.substring(i, i + 1));
		}
		return sequence;
	}

	private static String getFile() {
		String virus = null;
		File file = new File("C:/Users/Sammy/Documents/DNAfootprint/virus/MT192765_USA.dnaseq");
		try {
			FileInputStream in = new FileInputStream(file);
			byte byt[] = new byte[102400];
			int len = in.read(byt);
			virus = new String(byt, 0, len);
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return virus;
	}

	private static void drawLine(int x0, int y0, ArrayList<String> strand, Graphics g) {
		int x1 = x0;
		int y1 = y0;

		for (int i = 0; i < strand.size(); i++) {
			if (strand.get(i).equals("A") || strand.get(i).equals("T")) {
				x1 = xEndOfLine(strand, i, x0);
			} else if (strand.get(i).equals("G") || strand.get(i).equals("C")) {
				y1 = yEndOfLine(strand, i, y0);
			}
			g.drawLine(x0, y0, x1, y1);
			x0 = x1;
			y0 = y1;
		}
	}

	private static int xEndOfLine(ArrayList<String> strand, int pos, int x0) {
		if (strand.get(pos).equals("A")) {
			x0 += increment;
		} else if (strand.get(pos).equals("T")) {
			x0 -= increment;
		}
		if (x0 > xRightbound) // 1260
		{
			x0 = x0 - 720; // 1250
		}
		if (x0 < xLeftbound) //
		{
			x0 += 720; // 1250
		}
		return x0;
	}

	private static int yEndOfLine(ArrayList<String> strand, int pos, int y0) {
		if (strand.get(pos).equals("C")) {
			y0 += increment;
		} else if (strand.get(pos).equals("G")) {
			y0 -= increment;
		}
		if (y0 > yLowerbound) // 880 660
		{
			y0 -= 440; // 650
		}
		if (y0 < yUpperbound) {
			y0 += 440; // 650
		}
		return y0;
	}
}