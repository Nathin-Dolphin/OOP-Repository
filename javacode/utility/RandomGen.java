
/**
 * @author Nathin Wascher
 * @version 1.1.1
 * @since Februrary 27, 2020
 */

// public void longGen(long min, long max) {} [planned feature]
// [!] use 'intGen' instead of 'gen.nextInt()' in all 'colorGen' methods [!]
// [!] 'analagousColor' attempts to create a color with invalid values
// [!] 'complementColor' returns a gray if Color 'c' is gray [!]
// [?] change 'monoColor' to 'lighterColor' and create another method called
// 'darkerColor' [?]

package utility;

import java.awt.Color;

import java.util.Random;

public class RandomGen {
   public Random gen = new Random();
   private char[] characters;
   private int r, g, b;

   // initializes the 'characters' array
   public RandomGen() {
      characters = new char[52];
      for (int i = 0; i < 52; i++) {
         characters[i] = (char) ('a' + i);
      }
   }

   // generates a random lower case letter
   public char letterGen() {
      return characters[gen.nextInt(26)];
   }

   // generates a random RGB color
   public Color colorGen() {
      r = gen.nextInt(256);
      g = gen.nextInt(256);
      b = gen.nextInt(256);
      return new Color(r, g, b);
   }

   // creates a random color within the specified values (in RGB format)
   public Color colorGen(int rMin, int rMax, int gMin, int gMax, int bMin, int bMax) {
      r = gen.nextInt(rMax - rMin + 1) + rMin;
      g = gen.nextInt(gMax - gMin + 1) + gMin;
      b = gen.nextInt(bMax - bMin + 1) + bMin;
      return new Color(r, g, b);
   }

   // generates the opposite color of 'c'
   public Color complementColor(Color c) {
      r = 255 - c.getRed();
      g = 255 - c.getGreen();
      b = 255 - c.getBlue();

      if (r > 80 & r < 180)
         if (g > 80 & g < 180)
            if (g > 80 & g < 180)
               r = g = b = 0;

      return new Color(r, g, b);
   }

   // generates a color that is slightly lighter than the specified color 'c'
   public Color monoColor(Color c) {
      r = 3 * c.getRed() / 5;
      g = 3 * c.getGreen() / 5;
      b = 3 * c.getBlue() / 5;
      return new Color(r, g, b);
   }

   // generates a color similar to color 'c'
   public Color analogousColor(Color c, int range) {
      if (booleanGen())
         r = intGen(c.getRed() - range, c.getRed() - range / 2) % 255;
      else
         r = intGen(c.getRed() + range / 2, c.getRed() + range) % 255;

      if (booleanGen())
         g = intGen(c.getGreen() - range, c.getGreen() - range / 2) % 255;
      else
         g = intGen(c.getGreen() + range / 2, c.getGreen() + range) % 255;

      if (booleanGen())
         b = intGen(c.getBlue() - range, c.getBlue() - range / 2) % 255;
      else
         b = intGen(c.getBlue() + range / 2, c.getBlue() + range) % 255;

      while (r < 0)
         r++;
      while (g < 0)
         g++;
      while (b < 0)
         b++;

      return new Color(r, g, b);
   }

   // generates a random boolean value
   public boolean booleanGen() {
      if (gen.nextInt(2) == 0)
         return true;
      else
         return false;
   }

   // calculates an byte value between min and max, including min and max
   public byte byteGen(byte min, byte max) {
      return (byte) (gen.nextInt(max - min + 1) + min);
   }

   // calculates an short value between min and max, including min and max
   public short shortGen(short min, short max) {
      return (short) (gen.nextInt(max - min + 1) + min);
   }

   // calculates an int value between min and max, including min and max
   public int intGen(int min, int max) {
      return gen.nextInt(max - min + 1) + min;
   }
}
