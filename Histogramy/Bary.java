package Histogramy;

import java.awt.Color;
import java.awt.Graphics;

public class Bary {
  int altitude;
  double xCoord;
  double yCoord;
  double width;
  String number;

  public Bary(double x, double y, double w, int a, String num)  {
    this.altitude = a;
    this.xCoord = x;
    this.yCoord = y;
    this.width = w;
    this.number = num;
  }
  public void paint(Graphics g) {
    g.setColor(Color.BLACK);
    if (this.altitude != 0) {
      g.drawRect((int)this.xCoord, (int)this.yCoord, (int)this.width, this.altitude);
    }
    g.setColor(Color.BLUE);
    g.fillRect((int)this.xCoord, (int)this.yCoord, (int)this.width, this.altitude);
    g.setColor(Color.CYAN);
    g.drawString(this.number, (int)(this.xCoord + this.width / 2.0D), (int)(this.yCoord + this.altitude / 2));
  }
}
