import org.junit.*;
import org.junit.tools.configuration.base.MethodRef;

//import javax.annotation.Generated;
import javax.annotation.processing.Generated;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.logging.Logger;


@Generated(value = "org.junit-tools-1.0.6")
public class SparseMatTest
{

   @Generated(value = "org.junit-tools-1.0.6")
   private Logger logger = Logger.getLogger(SparseMatTest.class.toString());
   private static final int DEFAULT_SIZE = 100000;
   private static final Double DEFAULT_VAL = 0.0;
   private static final Double PI = new Double(Math.PI);
   private static final int MIN_SIZE = 1;
   private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
   private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
   private final PrintStream out = System.out;
   private final PrintStream err = System.err;
   /**
    * Sequence used by operating system to separate lines in text files
    */
   public static String lineSeparator = System.getProperty("line.separator");

   @Before
   public void setUp() throws Exception
   {
      System.setOut(new PrintStream(outContent));
      System.setErr(new PrintStream(errContent));
   }

   @BeforeClass
   public static void setUpBeforeClass() throws Exception
   {

   }

   @After
   public void tearDown() throws Exception
   {
      System.setOut(out);
      System.setErr(err);
   }

   @AfterClass
   public static void tearDownAfterClass() throws Exception
   {

   }

   private SparseMat<Double> createTestSubject()
   {
      return new SparseMat<Double>(DEFAULT_SIZE, DEFAULT_SIZE, DEFAULT_VAL);
   }

   @MethodRef(name = "SparseMat", signature = "()I")
   @Test
   public void testSparseMat() throws Exception
   {
      SparseMat<Double> testSubject;

      // tests of bad col values
      try
      {
         testSubject = new SparseMat<Double>(1, MIN_SIZE - 1, DEFAULT_VAL);
      } catch (IllegalArgumentException e)
      {
         Assert.assertNotNull(e);
      }
      try
      {
         testSubject = new SparseMat<Double>(1, -1, DEFAULT_VAL);
      } catch (IllegalArgumentException e)
      {
         Assert.assertNotNull(e);
      }

      // tests of bad row values
      try
      {
         testSubject = new SparseMat<Double>(MIN_SIZE - 1, 1, DEFAULT_VAL);
      } catch (IllegalArgumentException e)
      {
         Assert.assertNotNull(e);
      }
      try
      {
         testSubject = new SparseMat<Double>(-1, 1, DEFAULT_VAL);
      } catch (IllegalArgumentException e)
      {
         Assert.assertNotNull(e);
      }

   }

   @MethodRef(name = "getRowSize", signature = "()I")
   @Test
   public void testGetRowSize() throws Exception
   {
      SparseMat<Double> testSubject;
      int result;

      // default test
      testSubject = createTestSubject();
      result = testSubject.getRowSize();
      Assert.assertEquals(DEFAULT_SIZE, result);

      // test of 1
      testSubject = new SparseMat<Double>(MIN_SIZE, 1, DEFAULT_VAL);
      result = testSubject.getRowSize();
      Assert.assertEquals(1, result);
   }

   @MethodRef(name = "getColSize", signature = "()I")
   @Test
   public void testGetColSize() throws Exception
   {
      SparseMat<Double> testSubject;
      int result;

      // default test
      testSubject = createTestSubject();
      result = testSubject.getColSize();
      Assert.assertEquals(DEFAULT_SIZE, result);

      // test of 1
      testSubject = new SparseMat<Double>(1, MIN_SIZE, DEFAULT_VAL);
      result = testSubject.getColSize();
      Assert.assertEquals(1, result);
   }

   @MethodRef(name = "clear", signature = "()V")
   @Test
   public void testClear() throws Exception
   {
      SparseMat<Double> testSubject;

      // default test
      testSubject = createTestSubject();
      int row = DEFAULT_SIZE - 1;
      int col = DEFAULT_SIZE - 1;
      int rowSize;
      int colSize;
      Double result;
      Assert.assertNotNull(testSubject);
      for (int k = 0; k < DEFAULT_SIZE / 10; k++)
      {
         testSubject.set(k, k, k * 1.);
         testSubject.set(4, k, k * 10.);
         testSubject.set(k, 4, -k * 10.);
      }
      // test accessor to prove there is data in the testSubject
      Assert.assertEquals(new Double(0.0), (Double) testSubject.get(7, 8));
      Assert.assertEquals(new Double(30.0), (Double) testSubject.get(4, 3));
      Assert.assertEquals(new Double(9.0), (Double) testSubject.get(9, 9));
      testSubject.clear();
      result = (Double) testSubject.get(4, 3);
      Assert.assertEquals(DEFAULT_VAL, result);
      rowSize = testSubject.getRowSize();
      Assert.assertEquals(DEFAULT_SIZE, rowSize);
      colSize = testSubject.getColSize();
      Assert.assertEquals(DEFAULT_SIZE, colSize);
      // Only test cells where data was set before
      for (int k = 0; k < DEFAULT_SIZE / 10; k++)
      {
         // testSubject.set(k, k, k * 1.);
         result = (Double) testSubject.get(k, k);
         Assert.assertEquals(DEFAULT_VAL, result);
         // testSubject.set(4, k, k * 10.);
         result = (Double) testSubject.get(4, k);
         Assert.assertEquals(DEFAULT_VAL, result);
         // testSubject.set(k, 4, -k * 10.);
         result = (Double) testSubject.get(k, 4);
         Assert.assertEquals(DEFAULT_VAL, result);
      }
   }

   @MethodRef(name = "clone", signature = "()QObject;")
   @Test
   public void testClone() throws Exception
   {
      SparseMat<Double> testSubject;
      SparseMat<Double> result;

      // default test
      testSubject = createTestSubject();
      Assert.assertNotNull(testSubject);
      final int startOfChanges = 7;
      final int sizeOfChanges = 10;
      for (int k = startOfChanges; k < startOfChanges + sizeOfChanges; k++)
      {
         testSubject.set(k, k, k * 1.);
         testSubject.set(startOfChanges, k, k * 10.);
         testSubject.set(k, startOfChanges, -k * 10.);
      }
      result = (SparseMat<Double>) testSubject.clone();
      Assert.assertNotNull(result);
      // x.clone() != x
      Assert.assertTrue(result != testSubject);
      // x.clone().getClass() == x.getClass()
      Assert.assertTrue(result.getClass() == testSubject.getClass());
      // x.clone().equals(x)
      try
      {
         Assert.assertTrue(result.equals(testSubject));
      } catch (AssertionError e)
      {
         Assert.assertNotNull(e);
         // Student did not write an equals method for SparseMat,
         // which is best software engineering pratice, but not required by the
         // assignment.
      }
      // Test for equality of content (in case the student wrote equals method wrong
      // or not at all)
      // First make sure they are the same sizes
      Assert.assertTrue(result.getRowSize() == testSubject.getRowSize());
      Assert.assertTrue(result.getColSize() == testSubject.getColSize());
      // Then check that their outputs are the same
      outContent.reset();
      testSubject.showSubSquare(startOfChanges, sizeOfChanges);
      final String testSubjectOutput = outContent.toString();
      outContent.reset();
      result.showSubSquare(startOfChanges, sizeOfChanges);
      final String resultSubjectOutput = outContent.toString();
      Assert.assertEquals(resultSubjectOutput, testSubjectOutput);
      // Make sure outputs were not null, were not empty, & don't have unexpected data
      // inside them
      Assert.assertEquals("-70.0  80.0  90.0 100.0 110.0 120.0 130.0 140.0 150.0 160.0 " + lineSeparator
            + "-80.0   8.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0 " + lineSeparator
            + "-90.0   0.0   9.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0 " + lineSeparator
            + "-100.0   0.0   0.0  10.0   0.0   0.0   0.0   0.0   0.0   0.0 " + lineSeparator
            + "-110.0   0.0   0.0   0.0  11.0   0.0   0.0   0.0   0.0   0.0 " + lineSeparator
            + "-120.0   0.0   0.0   0.0   0.0  12.0   0.0   0.0   0.0   0.0 " + lineSeparator
            + "-130.0   0.0   0.0   0.0   0.0   0.0  13.0   0.0   0.0   0.0 " + lineSeparator
            + "-140.0   0.0   0.0   0.0   0.0   0.0   0.0  14.0   0.0   0.0 " + lineSeparator
            + "-150.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0  15.0   0.0 " + lineSeparator
            + "-160.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0  16.0 " + lineSeparator + lineSeparator + "",
            testSubjectOutput);
   }

   @MethodRef(name = "set", signature = "(IIQE;)Z")
   @Test
   public void testSet() throws Exception
   {
      SparseMat<Double> testSubject;
      int row = 0;
      int col = 0;
      Double x = null;
      boolean result;

      // default test
      testSubject = createTestSubject();
      try
      {
         result = testSubject.set(row, col, x);
      } catch (NullPointerException e)
      {
         // NullPointerException thrown was expected
         e.printStackTrace();
         Assert.assertNotNull(e);
      }

      // test mutators
      for (int k = 0; k < testSubject.getRowSize() / 5; k++)
      {
         testSubject.set(k * 5, k, k * 1.0);
         testSubject.set(k * 5, k * 2, k * PI);
         testSubject.set(k * 5, k * 3, -k * 10.0);
      }

      // test accessors and exceptions
      Assert.assertEquals(new Double(0.0), (Double) testSubject.get(7, 8));
      Assert.assertEquals(new Double(314.1592653589793), (Double) testSubject.get(500, 200));
      Assert.assertEquals(new Double(-10000.0), (Double) testSubject.get(5000, 3000));
      try
      {
         // should throw an exception
         Assert.assertNull(testSubject.get(-4, 7));
         Assert.fail();
      } catch (IndexOutOfBoundsException e)
      {
         Assert.assertNotNull(e);
      }

      // test bad null data
      try
      {
         // should throw an exception
         testSubject.set(0, 0, null);
         Assert.fail("The previous line should have caused a NullPointerException");
      } catch (NullPointerException e)
      {
         Assert.assertNotNull(e);
      }

      // test rows first
      result = testSubject.set(DEFAULT_SIZE-1, 0, PI);
      Assert.assertEquals(true, result);
      result = testSubject.set(DEFAULT_SIZE, 0, PI);
      Assert.assertEquals(false, result);
      result = testSubject.set(DEFAULT_SIZE+1, 0, PI);
      Assert.assertEquals(false, result);
      result = testSubject.set(-1, 0, PI);
      Assert.assertEquals(false, result);
      // then test columns
      result = testSubject.set(0, DEFAULT_SIZE-1, PI);
      Assert.assertEquals(true, result);
      result = testSubject.set(0, DEFAULT_SIZE, PI);
      Assert.assertEquals(false, result);
      result = testSubject.set(0, DEFAULT_SIZE+1, PI);
      Assert.assertEquals(false, result);
      result = testSubject.set(0, -1, PI);
      Assert.assertEquals(false, result);
   }

   @MethodRef(name = "get", signature = "(II)QE;")
   @Test
   public void testGet() throws Exception
   {
      SparseMat<Double> testSubject;
      int row = 0;
      int col = 0;
      Double result;

      // default test
      testSubject = createTestSubject();
      result = testSubject.get(row, col);
      Assert.assertEquals(DEFAULT_VAL, result);
      row = DEFAULT_SIZE / 2;
      col = DEFAULT_SIZE / 2;
      result = testSubject.get(row, col);
      Assert.assertEquals(DEFAULT_VAL, result);
      try
      {
         row = DEFAULT_SIZE;
         col = DEFAULT_SIZE;
         result = testSubject.get(row, col);
         Assert.assertEquals(DEFAULT_VAL, result);
      } catch (IndexOutOfBoundsException e)
      {
         Assert.assertNotNull(e);
      }
      row = DEFAULT_SIZE - 1;
      col = DEFAULT_SIZE - 1;
      result = testSubject.get(row, col);
      Assert.assertEquals(DEFAULT_VAL, result);

      // test mutators
      for (int k = 0; k < 10; k++)
      {
         testSubject.set(k, k, k * 1.);
         testSubject.set(4, k, k * 10.);
         testSubject.set(k, 4, -k * 10.);
      }

      // test accessors and exceptions
      try
      {
         Assert.assertEquals(new Double(0.0), (Double) testSubject.get(7, 8));
         Assert.assertEquals(new Double(30.0), (Double) testSubject.get(4, 3));
         Assert.assertEquals(new Double(9.0), (Double) testSubject.get(9, 9));

         // should throw an exception
         Assert.assertNull(testSubject.get(-4, 7));
      } catch (IndexOutOfBoundsException e)
      {
         Assert.assertNotNull(e);
      }

      row = DEFAULT_SIZE - 1;
      col = DEFAULT_SIZE - 1;
      Assert.assertTrue(testSubject.set(row, col, PI));
      result = testSubject.get(row, col);
      Assert.assertEquals(PI, result);
   }

   @MethodRef(name = "showSubSquare", signature = "(II)V")
   @Test
   public void testShowSubSquare() throws Exception
   {
      SparseMat<Double> testSubject;
      int start = 0;
      int size = 0;

      // test 1
      testSubject = createTestSubject();
      start = 0;
      size = 0;
      outContent.reset();
      testSubject.showSubSquare(start, size);
      Assert.assertEquals(lineSeparator, outContent.toString());

      // test 2
      testSubject = createTestSubject();
      start = -1;
      size = 0;
      outContent.reset();
      testSubject.showSubSquare(start, size);
      Assert.assertEquals("", outContent.toString());

      // test 3
      testSubject = createTestSubject();
      start = 1;
      size = 0;
      outContent.reset();
      testSubject.showSubSquare(start, size);
      Assert.assertEquals(lineSeparator, outContent.toString());

      // test 4
      testSubject = createTestSubject();
      size = 0;
      start = 0;
      outContent.reset();
      testSubject.showSubSquare(start, size);
      Assert.assertEquals(lineSeparator, outContent.toString());

      // test 5
      testSubject = createTestSubject();
      size = -1;
      start = 0;
      outContent.reset();
      testSubject.showSubSquare(start, size);
      Assert.assertEquals("", outContent.toString());

      // test 6
      testSubject = createTestSubject();
      size = 1;
      start = 0;
      outContent.reset();
      testSubject.showSubSquare(start, size);
      Assert.assertEquals("  0.0 " + lineSeparator + lineSeparator, outContent.toString());

      // test a larger portion
      testSubject = createTestSubject();
      Assert.assertNotNull(testSubject);
      final int startOfChanges = 7;
      final int sizeOfChanges = 10;
      for (int k = startOfChanges; k < startOfChanges + sizeOfChanges; k++)
      {
         testSubject.set(k, k, k * 1.);
         testSubject.set(startOfChanges, k, k * 10.);
         testSubject.set(k, startOfChanges, -k * 10.);
      }
      outContent.reset();
      testSubject.showSubSquare(startOfChanges, sizeOfChanges);
      Assert.assertEquals("-70.0  80.0  90.0 100.0 110.0 120.0 130.0 140.0 150.0 160.0 " + lineSeparator
            + "-80.0   8.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0 " + lineSeparator
            + "-90.0   0.0   9.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0 " + lineSeparator
            + "-100.0   0.0   0.0  10.0   0.0   0.0   0.0   0.0   0.0   0.0 " + lineSeparator
            + "-110.0   0.0   0.0   0.0  11.0   0.0   0.0   0.0   0.0   0.0 " + lineSeparator
            + "-120.0   0.0   0.0   0.0   0.0  12.0   0.0   0.0   0.0   0.0 " + lineSeparator
            + "-130.0   0.0   0.0   0.0   0.0   0.0  13.0   0.0   0.0   0.0 " + lineSeparator
            + "-140.0   0.0   0.0   0.0   0.0   0.0   0.0  14.0   0.0   0.0 " + lineSeparator
            + "-150.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0  15.0   0.0 " + lineSeparator
            + "-160.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0  16.0 " + lineSeparator + lineSeparator + "",
            outContent.toString());
   }
}