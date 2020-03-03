
/**
 * @author Nathin
 * @version 0.1
 * @since Februrary 27, 2020
 */

import java.awt.Color;
import java.util.Random;

public class RandomGen {
   private static Random gen = new Random();

   public static Color colorGen() {
      int r = gen.nextInt(255) + 1;
      int g = gen.nextInt(255) + 1;
      int b = gen.nextInt(255) + 1;
      return new Color(r, g, b);
   }

   public static Color colorGen(int rMin, int rMax, int gMin, int gMax, int bMin, int bMax) {
      int r = gen.nextInt(rMax - rMin) + rMin + 1;
      int g = gen.nextInt(gMax - gMin) + gMin + 1;
      int b = gen.nextInt(bMax - bMin) + bMin + 1;
      return new Color(r, g, b);
   }

   public static boolean boolGen() {
      if (gen.nextInt(2) == 0)
         return true;
      else
         return false;
   }
}