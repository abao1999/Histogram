/*     */ package Histogramy;
/*     */ 
/*     */ import java.awt.BasicStroke;
/*     */ import java.awt.Color;
/*     */ import java.awt.Container;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.util.ArrayList;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JFrame;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JTextField;
/*     */ 
/*     */ public class Grapher2 extends JPanel
/*     */   implements ActionListener
/*     */ {
/*     */   static final int windoww = 1200;
/*     */   static final int windowh = 700;
/*  14 */   static ArrayList bars = new ArrayList(Datay2.binSize);
/*     */   static double wd;
/*     */   static int w;
/*     */   static int h;
/*     */   static int hM;
/*     */   static int wr;
/*     */   static int peak;
/*     */   static int floor;
/*  22 */   static int inc = 20;
/*  23 */   static int current = 20;
/*  24 */   static JTextField T = new JTextField("20", 2);
/*  25 */   static JTextField widthgetter = new JTextField("0", 5);
/*  26 */   static JTextField widthshifter = new JTextField("0", 2);
/*  27 */   static JTextField framewidth = new JTextField("1200", 5);
/*  28 */   static JTextField frameheight = new JTextField("525", 5);
/*     */   static int fw;
/*     */   static int fh;
/*  31 */   JButton button1 = new JButton("Increase");
/*  32 */   JButton button2 = new JButton("Decrease");
/*  33 */   static int widthSub = 0;
/*  34 */   static int widthShift = 0;
/*     */ 
/*     */   public Grapher2(int width, int height, int floorValue) {
/*  37 */     setOpaque(true);
/*  38 */     setBackground(Color.CYAN);
/*  39 */     this.button1.addActionListener(this);
/*  40 */     this.button2.addActionListener(this);
/*  41 */     widthgetter.addActionListener(this);
/*  42 */     widthshifter.addActionListener(this);
/*  43 */     framewidth.addActionListener(this);
/*  44 */     frameheight.addActionListener(this);
/*     */ 
/*  46 */     add(this.button1);
/*  47 */     add(this.button2);
/*     */ 
/*  49 */     widthSub = Integer.parseInt(widthgetter.getText());
/*  50 */     widthShift = Integer.parseInt(widthshifter.getText());
/*  51 */     fw = Integer.parseInt(framewidth.getText());
/*  52 */     fh = Integer.parseInt(frameheight.getText());
/*     */ 
/*  54 */     w = width;
/*  55 */     h = height;
/*  56 */     floor = floorValue;
/*  57 */     peak = (int)(0.85D * h);
/*  58 */     hM = (peak - floor) / inc;
/*  59 */     wr = (int)(0.1D * w);
/*  60 */     wd = (int)(0.8D * width / Datay2.binSize) - widthSub;
/*  61 */     if (wd <= 5.0D) {
/*  62 */       wd += 1.0D;
/*     */     }
/*  64 */     if (widthSub >= wd) {
/*  65 */       widthSub = (int)(wd - 5.0D);
/*     */     }
/*  67 */     for (int i = 0; i < Datay2.binSize; i++) {
/*  68 */       bars.add(new Bary((int)(wr + wd * i) + widthShift, peak - Datay2.heights[i] * hM - 1, wd - 1.0D, Datay2.heights[i] * hM + 1, Datay2.heights[i]));
/*     */     }
/*  70 */     for (int i = bars.size() - Datay2.binSize; i > 0; i--) {
/*  71 */       if (i > 1) {
/*  72 */         bars.remove(i - 2);
/*     */       }
/*  74 */       if (i == bars.size() - Datay2.binSize)
/*  75 */         bars.remove(i - 1);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void paintComponent(Graphics g) {
/*  80 */     super.paintComponent(g);
/*  81 */     Graphics2D g2 = (Graphics2D)g;
/*  82 */     g2.setColor(new Color(0, 128, 0));
/*  83 */     g2.setStroke(new BasicStroke(3.0F));
/*  84 */     g2.drawLine(wr - 2, floor, wr - 2, peak + 2);
/*  85 */     g2.drawLine(wr, peak + 2, w - wr, peak + 2);
/*     */ 
/*  88 */     g.setColor(new Color(0, 128, 0));
/*  89 */     g.setColor(Color.BLUE);
/*  90 */     for (int n = 0; n < bars.size(); n++) {
/*  91 */       Bary object = (Bary)bars.get(n);
/*  92 */       object.paint(g);
/*     */     }
/*     */ 
/*  95 */     g.setColor(Color.BLACK);
/*  96 */     for (int i = 0; i <= Datay2.binSize; i++) {
/*  97 */       g.drawString(Datay2.boundLocations[i], (int)(0.9D * wr + wd * i) - 5 + widthShift, peak + 20);
/*     */     }
/*  99 */     for (int m = 0; m <= inc; m += 5) {
/* 100 */       g.drawString(inc - m, wr - 25, (peak - floor) * m / inc + floor + 3);
/*     */     }
/* 102 */     for (int j = 0; j <= inc; j++) {
/* 103 */       if (j % 5 == 0) {
/* 104 */         g.setColor(Color.BLACK);
/*     */       }
/*     */       else
/* 107 */         g.setColor(Color.GRAY);
/* 108 */       g.drawString("-", (int)(0.9D * wr), (peak - floor) * j / inc + floor + 3);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void actionPerformed(ActionEvent a) {
/* 112 */     if (a.getSource().equals(this.button1)) {
/* 113 */       current += 5;
/* 114 */       inc = current;
/* 115 */       T.setText(inc);
/*     */     }
/* 117 */     if (a.getSource().equals(this.button2)) {
/* 118 */       current -= 5;
/* 119 */       inc = current;
/* 120 */       T.setText(inc);
/*     */     }
/* 122 */     Grapher2 G2 = new Grapher2(fw, 700, peak - fh);
/* 123 */     repaint();
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/* 128 */     Grapher2 G = new Grapher2(1200, 700, 70);
/*     */ 
/* 130 */     JPanel one = new JPanel();
/* 131 */     one.add(new JLabel("Ticks/Width/Shift"));
/* 132 */     one.add(T);
/* 133 */     one.add(widthgetter);
/* 134 */     one.add(widthshifter);
/* 135 */     one.add(framewidth);
/* 136 */     one.add(frameheight);
/* 137 */     one.setBackground(Color.CYAN);
/*     */ 
/* 140 */     JFrame frame = new JFrame("Histogram");
/* 141 */     frame.setSize(w, h);
/* 142 */     frame.setDefaultCloseOperation(3);
/* 143 */     frame.setContentPane(G);
/* 144 */     frame.getContentPane().add(one);
/* 145 */     frame.setVisible(true);
/*     */   }
/*     */ }

/* Location:           /Users/timcoker/Desktop/Histogram.jar
 * Qualified Name:     Histogramy.Grapher2
 * JD-Core Version:    0.6.0
 */