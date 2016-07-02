/*    */ package Histogramy;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import java.awt.Graphics;
/*    */ 
/*    */ public class Bary
/*    */ {
/*    */   int altitude;
/*    */   double xCoord;
/*    */   double yCoord;
/*    */   double width;
/*    */   String number;
/*    */ 
/*    */   public Bary(double x, double y, double w, int a, String num)
/*    */   {
/* 12 */     this.altitude = a;
/* 13 */     this.xCoord = x;
/* 14 */     this.yCoord = y;
/* 15 */     this.width = w;
/* 16 */     this.number = num;
/*    */   }
/*    */   public void paint(Graphics g) {
/* 19 */     g.setColor(Color.BLACK);
/* 20 */     if (this.altitude != 0) {
/* 21 */       g.drawRect((int)this.xCoord, (int)this.yCoord, (int)this.width, this.altitude);
/*    */     }
/* 23 */     g.setColor(Color.BLUE);
/* 24 */     g.fillRect((int)this.xCoord, (int)this.yCoord, (int)this.width, this.altitude);
/* 25 */     g.setColor(Color.CYAN);
/* 26 */     g.drawString(this.number, (int)(this.xCoord + this.width / 2.0D), (int)(this.yCoord + this.altitude / 2));
/*    */   }
/*    */ }

/* Location:           /Users/timcoker/Desktop/Histogram.jar
 * Qualified Name:     Histogramy.Bary
 * JD-Core Version:    0.6.0
 */