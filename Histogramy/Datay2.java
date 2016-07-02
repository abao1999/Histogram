/*     */ package Histogramy;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Scanner;
/*     */ import javax.accessibility.Accessible;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JOptionPane;
/*     */ 
/*     */ public class Datay2 extends JComponent
/*     */   implements Accessible
/*     */ {
/* 122 */   static double[] input = setArray();
/* 123 */   static double min = getmin(input);
/* 124 */   static double max = getmax(input);
/* 125 */   static int binSize = setBin();
/* 126 */   static int round = roundBin();
/* 127 */   static double[] boundLocations = setBounds(input, binSize, round);
/* 128 */   static int[] heights = setHeights(boundLocations, input);
/*     */ 
/*     */   public Datay2()
/*     */   {
/*  13 */     double[] input = setArray();
/*  14 */     int binSize = setBin();
/*  15 */     int round = roundBin();
/*  16 */     double[] boundLocations = setBounds(input, binSize, round);
/*  17 */     int[] heights = setHeights(boundLocations, input);
/*     */   }
/*     */   public static double[] setArray() {
/*  20 */     ArrayList commaLocations = new ArrayList();
/*  21 */     commaLocations.add(Integer.valueOf(-1));
/*  22 */     Scanner scan = new Scanner(System.in);
/*  23 */     String input = JOptionPane.showInputDialog("Enter the data, with each element separated by a comma ");
/*  24 */     input = input.replace(" ", "");
/*  25 */     for (int i = 0; i < input.length(); i++) {
/*  26 */       if (input.charAt(i) == ',') {
/*  27 */         commaLocations.add(Integer.valueOf(i));
/*     */       }
/*     */     }
/*  30 */     commaLocations.add(Integer.valueOf(input.length()));
/*  31 */     double[] output = new double[commaLocations.size() - 1];
/*  32 */     for (int i = 0; i < commaLocations.size() - 1; i++) {
/*  33 */       int lower = ((Integer)commaLocations.get(i)).intValue() + 1;
/*  34 */       int higher = ((Integer)commaLocations.get(i + 1)).intValue();
/*  35 */       output[i] = Double.parseDouble(input.substring(lower, higher));
/*     */     }
/*  37 */     return output;
/*     */   }
/*     */   public static int setBin() {
/*  40 */     Scanner scnr = new Scanner(System.in);
/*  41 */     return Integer.parseInt(JOptionPane.showInputDialog("How many data bins do you want to display? "));
/*     */   }
/*     */   public static double getmin(double[] array) {
/*  44 */     double min = array[0];
/*  45 */     for (int i = 0; i < array.length; i++) {
/*  46 */       if (array[i] < min) {
/*  47 */         min = array[i];
/*     */       }
/*     */     }
/*  50 */     return min;
/*     */   }
/*     */   public static double getmax(double[] array) {
/*  53 */     double max = array[0];
/*  54 */     for (int i = 0; i < array.length; i++) {
/*  55 */       if (array[i] > max) {
/*  56 */         max = array[i];
/*     */       }
/*     */     }
/*  59 */     return max;
/*     */   }
/*     */   public static int roundBin() {
/*  62 */     return Integer.parseInt(JOptionPane.showInputDialog("Enter the denomination that you want to round the range to\n1. the nearest 100th\n2. the nearest 10th\n3. the nearest 5th\n4. the nearest whole number"));
/*     */   }
/*     */ 
/*     */   public static double[] setBounds(double[] array, int bin, int answer) {
/*  66 */     double[] bounds = new double[bin + 1];
/*     */ 
/*  70 */     switch (answer) {
/*     */     case 1:
/*  72 */       double floor = getmin(array) - getmin(array) % 100.0D;
/*  73 */       double ceiling = getmax(array) + 100.0D - getmax(array) % 100.0D;
/*  74 */       double increment = (ceiling - floor) / bin;
/*  75 */       for (int i = 0; i <= bin; i++) {
/*  76 */         bounds[i] = (floor + i * increment);
/*     */       }
/*     */ 
/*  79 */       break;
/*     */     case 2:
/*  81 */       double floor = getmin(array) - getmin(array) % 10.0D;
/*  82 */       double ceiling = getmax(array) + 10.0D - getmax(array) % 10.0D;
/*  83 */       double increment = (ceiling - floor) / bin;
/*  84 */       for (int i = 0; i <= bin; i++) {
/*  85 */         bounds[i] = (floor + i * increment);
/*     */       }
/*     */ 
/*  88 */       break;
/*     */     case 3:
/*  90 */       double floor = getmin(array) - getmin(array) % 5.0D;
/*  91 */       double ceiling = getmax(array) + 5.0D - getmax(array) % 5.0D;
/*  92 */       double increment = (ceiling - floor) / bin;
/*  93 */       for (int i = 0; i <= bin; i++) {
/*  94 */         bounds[i] = (floor + i * increment);
/*     */       }
/*     */ 
/*  97 */       break;
/*     */     case 4:
/*  99 */       double floor = getmin(array) - getmin(array) % 1.0D;
/* 100 */       double ceiling = getmax(array) + 1.0D - getmax(array) % 1.0D;
/* 101 */       double increment = (ceiling - floor) / bin;
/* 102 */       for (int i = 0; i <= bin; i++) {
/* 103 */         bounds[i] = (floor + i * increment);
/*     */       }
/*     */     }
/*     */ 
/* 107 */     return bounds;
/*     */   }
/*     */   public static int[] setHeights(double[] bounds, double[] array) {
/* 110 */     int[] quant = new int[bounds.length - 1];
/* 111 */     for (int n = 0; n < bounds.length - 1; n++) {
/* 112 */       quant[n] = 0;
/* 113 */       for (int m = 0; m < array.length; m++) {
/* 114 */         if ((array[m] >= bounds[n]) && (array[m] < bounds[(n + 1)])) {
/* 115 */           quant[n] += 1;
/*     */         }
/*     */       }
/*     */     }
/* 119 */     return quant;
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/* 130 */     double[] input = setArray();
/* 131 */     int binSize = setBin();
/* 132 */     int round = roundBin();
/* 133 */     double[] boundLocations = setBounds(input, binSize, round);
/* 134 */     int[] heights = setHeights(boundLocations, input);
/*     */   }
/*     */ }

/* Location:           /Users/timcoker/Desktop/Histogram.jar
 * Qualified Name:     Histogramy.Datay2
 * JD-Core Version:    0.6.0
 */