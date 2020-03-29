
/**
 * @author Nathin Wascher
 * @version 1.2
 * @since Februrary 27, 2020
 */

// public void longGen(long min, long max) {} [planned feature]
// [!] 'analagousColor' attempts to create a color with invalid values
// [!] 'complementColor' returns a gray if Color 'c' is gray [!]

package utility;

import java.awt.Color;

import java.util.Random;

public class RandomGen {
   public Random gen = new Random();
   private char[] characters;
   private char letter;
   private int r, g, b;

   // initializes the 'characters' array
   public RandomGen() {
      characters = new char[26];
      for (int i = 0; i < 26; i++) {
         characters[i] = (char) ('a' + i);
      }
   }

   // generates a random lower case letter
   public char letterGen() {
      return characters[intGen(0, 25)];
   }

   // generates a random lower case letter
   public char fullLetterGen() {
      letter = characters[intGen(0, 25)];
      if (boolGen())
         return Character.toUpperCase(letter);
      else
         return letter;
   }

   // generates a random RGB color
   public Color colorGen() {
      r = intGen(0, 255);
      g = intGen(0, 255);
      b = intGen(0, 255);
      return new Color(r, g, b);
   }

   // creates a random color within the specified values (in RGB format)
   public Color colorGen(int rMin, int rMax, int gMin, int gMax, int bMin, int bMax) {
      r = intGen(rMin, rMax);
      g = intGen(gMin, gMax);
      b = intGen(bMin, bMax);
      return new Color(r, g, b);
   }

   // generates the opposite color of 'c'
   // WORK IN PROGRESS
   public Color complementColor(Color c) {
      r = 255 - c.getRed();
      g = 255 - c.getGreen();
      b = 255 - c.getBlue();

      // temporary fix
      if (r > 80 & r < 180)
         if (g > 80 & g < 180)
            if (g > 80 & g < 180)
               r = g = b = 0;

      return new Color(r, g, b);
   }

   // generates a color that is slightly lighter than the specified color 'c'
   public Color lighterColor(Color c) {
      r = c.getRed() * 3 / 5;
      g = c.getGreen() * 3 / 5;
      b = c.getBlue() * 3 / 5;
      return new Color(r, g, b);
   }

   // generates a color that is slightly darker than the specified color 'c'
   // WORK IN PROGRESS
   public Color darkerColor(Color c) {
      r = c.getRed() * 7 / 5;
      g = c.getGreen() * 7 / 5;
      b = c.getBlue() * 7 / 5;
      return new Color(r, g, b);
   }

   // generates a color similar to color 'c'
   // WORK IN PROGRESS
   public Color analogousColor(Color c, int range) {
      if (boolGen())
         r = intGen(c.getRed() - range, c.getRed() - range / 2) % 255;
      else
         r = intGen(c.getRed() + range / 2, c.getRed() + range) % 255;

      if (boolGen())
         g = intGen(c.getGreen() - range, c.getGreen() - range / 2) % 255;
      else
         g = intGen(c.getGreen() + range / 2, c.getGreen() + range) % 255;

      if (boolGen())
         b = intGen(c.getBlue() - range, c.getBlue() - range / 2) % 255;
      else
         b = intGen(c.getBlue() + range / 2, c.getBlue() + range) % 255;

      // temporary fix
      while (r < 0)
         r = +255;
      while (g < 0)
         g = +255;
      while (b < 0)
         b = +255;

      return new Color(r, g, b);
   }

   // generates a random boolean value
   public boolean boolGen() {
      if (intGen(0, 1) == 0)
         return true;
      else
         return false;
   }

   // calculates an byte value between min and max, including min and max
   public byte byteGen(byte min, byte max) {
      return (byte) (intGen(min, max));
   }

   // calculates an short value between min and max, including min and max
   public short shortGen(short min, short max) {
      return (short) (intGen(min, max));
   }

   // calculates an int value between min and max, including min and max
   public int intGen(int min, int max) {
      return gen.nextInt(max - min + 1) + min;
   }
}
