package Histogramy;

import java.util.ArrayList;
import java.util.Scanner;
import javax.accessibility.Accessible;
import javax.swing.JComponent;
import javax.swing.JOptionPane;

public class Datay2 extends JComponent implements Accessible {
  static double[] input = setArray();
  static double min = getmin(input);
  static double max = getmax(input);
  static int binSize = setBin();
  static int round = roundBin();
  static double[] boundLocations = setBounds(input, binSize, round);
  static int[] heights = setHeights(boundLocations, input);

  public Datay2() {
    double[] input = setArray();
    int binSize = setBin();
    int round = roundBin();
    double[] boundLocations = setBounds(input, binSize, round);
    int[] heights = setHeights(boundLocations, input);
  }
  public static double[] setArray() {
    ArrayList commaLocations = new ArrayList();
    commaLocations.add(Integer.valueOf(-1));
    Scanner scan = new Scanner(System.in);
    String input = JOptionPane.showInputDialog("Enter the data, with each element separated by a comma ");
    input = input.replace(" ", "");
    for (int i = 0; i < input.length(); i++) {
      if (input.charAt(i) == ',') {
        commaLocations.add(Integer.valueOf(i));
      }
    }
    commaLocations.add(Integer.valueOf(input.length()));
    double[] output = new double[commaLocations.size() - 1];
    for (int i = 0; i < commaLocations.size() - 1; i++) {
      int lower = ((Integer)commaLocations.get(i)).intValue() + 1;
      int higher = ((Integer)commaLocations.get(i + 1)).intValue();
      output[i] = Double.parseDouble(input.substring(lower, higher));
    }
    return output;
  }
  public static int setBin() {
    Scanner scnr = new Scanner(System.in);
    return Integer.parseInt(JOptionPane.showInputDialog("How many data bins do you want to display? "));
  }
  public static double getmin(double[] array) {
    double min = array[0];
    for (int i = 0; i < array.length; i++) {
      if (array[i] < min) {
        min = array[i];
      }
    }
    return min;
  }
  public static double getmax(double[] array) {
    double max = array[0];
    for (int i = 0; i < array.length; i++) {
      if (array[i] > max) {
        max = array[i];
      }
    }
    return max;
  }
  public static int roundBin() {
    return Integer.parseInt(JOptionPane.showInputDialog("Enter the denomination that you want to round the range to\n1. the nearest 100th\n2. the nearest 10th\n3. the nearest 5th\n4. the nearest whole number"));
  }

  public static double[] setBounds(double[] array, int bin, int answer) {
    double[] bounds = new double[bin + 1];

    switch (answer) {
    case 1:
      double floor = getmin(array) - getmin(array) % 100.0D;
      double ceiling = getmax(array) + 100.0D - getmax(array) % 100.0D;
      double increment = (ceiling - floor) / bin;
      for (int i = 0; i <= bin; i++) {
        bounds[i] = (floor + i * increment);
      }

      break;
    case 2:
      double floor = getmin(array) - getmin(array) % 10.0D;
      double ceiling = getmax(array) + 10.0D - getmax(array) % 10.0D;
      double increment = (ceiling - floor) / bin;
      for (int i = 0; i <= bin; i++) {
        bounds[i] = (floor + i * increment);
      }

      break;
    case 3:
      double floor = getmin(array) - getmin(array) % 5.0D;
      double ceiling = getmax(array) + 5.0D - getmax(array) % 5.0D;
      double increment = (ceiling - floor) / bin;
      for (int i = 0; i <= bin; i++) {
        bounds[i] = (floor + i * increment);
      }

      break;
    case 4:
      double floor = getmin(array) - getmin(array) % 1.0D;
      double ceiling = getmax(array) + 1.0D - getmax(array) % 1.0D;
      double increment = (ceiling - floor) / bin;
      for (int i = 0; i <= bin; i++) {
        bounds[i] = (floor + i * increment);
      }
    }

    return bounds;
  }
  public static int[] setHeights(double[] bounds, double[] array) {
    int[] quant = new int[bounds.length - 1];
    for (int n = 0; n < bounds.length - 1; n++) {
      quant[n] = 0;
      for (int m = 0; m < array.length; m++) {
        if ((array[m] >= bounds[n]) && (array[m] < bounds[(n + 1)])) {
          quant[n] += 1;
        }
      }
    }
    return quant;
  }

  public static void main(String[] args)  {
    double[] input = setArray();
    int binSize = setBin();
    int round = roundBin();
    double[] boundLocations = setBounds(input, binSize, round);
    int[] heights = setHeights(boundLocations, input);
  }
}
