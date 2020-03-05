
/**
 * @author Nathin Wascher
 * @version 1.0.1
 * @since Februrary 27, 2020
 */

package utility;

// private void longGen(long min, long max) {} WIP

import java.awt.Color;

import java.util.Random;

public class RandomGen {
   public Random gen = new Random();
   private char[] characters;
   private int r, g, b;

   public RandomGen() {
      characters = new char[52];
      for (int i = 0; i < 52; i++) {
         characters[i] = (char) ('a' + i);
      }
   }

   public char letterGen() {
      return characters[gen.nextInt(26)];
   }

   public Color colorGen() {
      r = gen.nextInt(256);
      g = gen.nextInt(256);
      b = gen.nextInt(256);
      return new Color(r, g, b);
   }

   public Color colorGen(int rMin, int rMax, int gMin, int gMax, int bMin, int bMax) {
      r = gen.nextInt(rMax - rMin + 1) + rMin;
      g = gen.nextInt(gMax - gMin + 1) + gMin;
      b = gen.nextInt(bMax - bMin + 1) + bMin;
      return new Color(r, g, b);
   }

   public Color complementColor(Color c) {
      // add code to prevent gray on gray
      r = 255 - c.getRed();
      g = 255 - c.getGreen();
      b = 255 - c.getBlue();
      return new Color(r, g, b);
   }

   public Color monoColor(Color c) {
      r = 3 * c.getRed() / 5;
      g = 3 * c.getGreen() / 5;
      b = 3 * c.getBlue() / 5;
      return new Color(r, g, b);
   }

   public boolean booleanGen() {
      if (gen.nextInt(2) == 0)
         return true;
      else
         return false;
   }

   public byte byteGen(byte min, byte max) {
      return (byte) (gen.nextInt(max - min + 1) + min);
   }

   public short shortGen(short min, short max) {
      return (short) (gen.nextInt(max - min + 1) + min);
   }

   public int intGen(int min, int max) {
      return gen.nextInt(max - min + 1) + min;
   }
}
