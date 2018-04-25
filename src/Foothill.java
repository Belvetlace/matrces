// CIS 1C Assignment #2 
// Instructor Solution Featuring clone()

// client -----------------------------------------------------
import cs_1c.*;
import java.util.*;

//------------------------------------------------------
public class Foothill
{
   final static int MAT_SIZE = 100000;
   // -------  main --------------
   public static void main(String[] args) throws Exception
   {
      // 100000 x 100000 filled with 0
      int k;
      SparseMat mat 
         = new SparseMat(MAT_SIZE, MAT_SIZE, 0.); 
      
      // test constructor error
      System.out.println("Test constructor");
      try
      {
         SparseMat matBad 
            = new SparseMat(MAT_SIZE, -MAT_SIZE, 0.);
      }
      catch( IllegalArgumentException e)
      {
         System.out.println("oops - bad arg in constructor");
      }

      // test mutators
      for (k = 0; k < 10; k++)
      {
         mat.set(k, k, k * 1.);
         mat.set(4, k, k * 10.);
         mat.set(k, 4, -k * 10.);
      }
      
      // test accessors and exceptions
      System.out.println();
      System.out.println("Test get()");
      try
      {
         System.out.println( mat.get(7, 8) );
         System.out.println(  mat.get(4, 3) );
         System.out.println( mat.get(9, 9) );

         // should throw an exception
         System.out.println(  mat.get(-4, 7) );
      }
      catch( IndexOutOfBoundsException e)
      {
         System.out.println("oops - bounds in get()");
      }
      System.out.println();
      System.out.println("First 12x12 subsquare of original");
      mat.showSubSquare(0, 12);
      System.out.println();
      
      SparseMat mat2 = (SparseMat)mat.clone();
      
      for (k = 0; k < 10; k++)
      {
         mat.set(k, k, 1.);
         mat.set(4, k, 10.);
         mat.set(k, 4, -10.);
      }
      
      System.out.println("First 12x12, 1st 10 of diagonal & 4th r/c changed:");
      mat.showSubSquare(0, 12);
      System.out.println();
      System.out.println("Cloned 12x12  UN-changed:");
      mat2.showSubSquare(0, 12);
   }
}