package Histogramy;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Grapher2 extends JPanel implements ActionListener  {
  static final int windoww = 1200;
  static final int windowh = 700;
  static ArrayList bars = new ArrayList(Datay2.binSize);
  static double wd;
  static int w;
  static int h;
  static int hM;
  static int wr;
  static int peak;
  static int floor;
  static int inc = 20;
  static int current = 20;
  static JTextField T = new JTextField("20", 2);
  static JTextField widthgetter = new JTextField("0", 5);
  static JTextField widthshifter = new JTextField("0", 2);
  static JTextField framewidth = new JTextField("1200", 5);
  static JTextField frameheight = new JTextField("525", 5);
  static int fw;
  static int fh;
  JButton button1 = new JButton("Increase");
  JButton button2 = new JButton("Decrease");
  static int widthSub = 0;
  static int widthShift = 0;

  public Grapher2(int width, int height, int floorValue) {
    setOpaque(true);
    setBackground(Color.CYAN);
    this.button1.addActionListener(this);
    this.button2.addActionListener(this);
    widthgetter.addActionListener(this);
    widthshifter.addActionListener(this);
    framewidth.addActionListener(this);
    frameheight.addActionListener(this);

    add(this.button1);
    add(this.button2);

    widthSub = Integer.parseInt(widthgetter.getText());
    widthShift = Integer.parseInt(widthshifter.getText());
    fw = Integer.parseInt(framewidth.getText());
    fh = Integer.parseInt(frameheight.getText());

    w = width;
    h = height;
    floor = floorValue;
    peak = (int)(0.85D * h);
    hM = (peak - floor) / inc;
    wr = (int)(0.1D * w);
    wd = (int)(0.8D * width / Datay2.binSize) - widthSub;
    if (wd <= 5.0D) {
      wd += 1.0D;
    }
    if (widthSub >= wd) {
      widthSub = (int)(wd - 5.0D);
    }
    for (int i = 0; i < Datay2.binSize; i++) {
      bars.add(new Bary((int)(wr + wd * i) + widthShift, peak - Datay2.heights[i] * hM - 1, wd - 1.0D, Datay2.heights[i] * hM + 1, Datay2.heights[i]));
    }
    for (int i = bars.size() - Datay2.binSize; i > 0; i--) {
      if (i > 1) {
        bars.remove(i - 2);
      }
      if (i == bars.size() - Datay2.binSize)
        bars.remove(i - 1);
    }
  }

  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D)g;
    g2.setColor(new Color(0, 128, 0));
    g2.setStroke(new BasicStroke(3.0F));
    g2.drawLine(wr - 2, floor, wr - 2, peak + 2);
    g2.drawLine(wr, peak + 2, w - wr, peak + 2);

    g.setColor(new Color(0, 128, 0));
    g.setColor(Color.BLUE);
    for (int n = 0; n < bars.size(); n++) {
      Bary object = (Bary)bars.get(n);
      object.paint(g);
    }

    g.setColor(Color.BLACK);
    for (int i = 0; i <= Datay2.binSize; i++) {
      g.drawString(Datay2.boundLocations[i], (int)(0.9D * wr + wd * i) - 5 + widthShift, peak + 20);
    }
    for (int m = 0; m <= inc; m += 5) {
      g.drawString(inc - m, wr - 25, (peak - floor) * m / inc + floor + 3);
    }
    for (int j = 0; j <= inc; j++) {
      if (j % 5 == 0) {
        g.setColor(Color.BLACK);
      }
      else
        g.setColor(Color.GRAY);
      g.drawString("-", (int)(0.9D * wr), (peak - floor) * j / inc + floor + 3);
    }
  }

  public void actionPerformed(ActionEvent a) {
    if (a.getSource().equals(this.button1)) {
      current += 5;
      inc = current;
      T.setText(inc);
    }
    if (a.getSource().equals(this.button2)) {
      current -= 5;
      inc = current;
      T.setText(inc);
    }
    Grapher2 G2 = new Grapher2(fw, 700, peak - fh);
    repaint();
  }

  public static void main(String[] args)  {
    Grapher2 G = new Grapher2(1200, 700, 70);

    JPanel one = new JPanel();
    one.add(new JLabel("Ticks/Width/Shift"));
    one.add(T);
    one.add(widthgetter);
    one.add(widthshifter);
    one.add(framewidth);
    one.add(frameheight);
    one.setBackground(Color.CYAN);

    JFrame frame = new JFrame("Histogram");
    frame.setSize(w, h);
    frame.setDefaultCloseOperation(3);
    frame.setContentPane(G);
    frame.getContentPane().add(one);
    frame.setVisible(true);
  }
}
