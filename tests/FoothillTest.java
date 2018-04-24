import org.junit.*;
import org.junit.tools.configuration.base.MethodRef;

//import javax.annotation.Generated;
import javax.annotation.processing.Generated;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.logging.Logger;


@Generated(value = "org.junit-tools-1.0.6")
public class FoothillTest
{

   @Generated(value = "org.junit-tools-1.0.6")
   private Logger logger = Logger.getLogger(FoothillTest.class.toString());
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

   private Foothill createTestSubject()
   {
      return new Foothill();
   }

   @MethodRef(name = "main", signature = "([QString;)V")
   @Test
   public void testMain() throws Exception
   {
      String[] args = new String[]
      { "" };

      // default test
      Foothill.main(args);

      Assert.assertEquals("", errContent.toString());
      Assert.assertEquals("Test constructor" + lineSeparator + 
            "oops - bad arg in constructor" + lineSeparator + 
            lineSeparator + 
            "Test get()" + lineSeparator + 
            "0.0" + lineSeparator + 
            "30.0" + lineSeparator + 
            "9.0" + lineSeparator + 
            "oops - bounds in get()" + lineSeparator + 
            lineSeparator + 
            "First 12x12 subsquare of original" + lineSeparator + 
            "  0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0 " + lineSeparator + 
            "  0.0   1.0   0.0   0.0 -10.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0 " + lineSeparator + 
            "  0.0   0.0   2.0   0.0 -20.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0 " + lineSeparator + 
            "  0.0   0.0   0.0   3.0 -30.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0 " + lineSeparator + 
            "  0.0  10.0  20.0  30.0 -40.0  50.0  60.0  70.0  80.0  90.0   0.0   0.0 " + lineSeparator + 
            "  0.0   0.0   0.0   0.0 -50.0   5.0   0.0   0.0   0.0   0.0   0.0   0.0 " + lineSeparator + 
            "  0.0   0.0   0.0   0.0 -60.0   0.0   6.0   0.0   0.0   0.0   0.0   0.0 " + lineSeparator + 
            "  0.0   0.0   0.0   0.0 -70.0   0.0   0.0   7.0   0.0   0.0   0.0   0.0 " + lineSeparator + 
            "  0.0   0.0   0.0   0.0 -80.0   0.0   0.0   0.0   8.0   0.0   0.0   0.0 " + lineSeparator + 
            "  0.0   0.0   0.0   0.0 -90.0   0.0   0.0   0.0   0.0   9.0   0.0   0.0 " + lineSeparator + 
            "  0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0 " + lineSeparator + 
            "  0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0 " + lineSeparator + 
            lineSeparator + 
            lineSeparator + 
            "First 12x12, 1st 10 of diagonal & 4th r/c changed:" + lineSeparator + 
            "  1.0   0.0   0.0   0.0 -10.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0 " + lineSeparator + 
            "  0.0   1.0   0.0   0.0 -10.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0 " + lineSeparator + 
            "  0.0   0.0   1.0   0.0 -10.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0 " + lineSeparator + 
            "  0.0   0.0   0.0   1.0 -10.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0 " + lineSeparator + 
            " 10.0  10.0  10.0  10.0 -10.0  10.0  10.0  10.0  10.0  10.0   0.0   0.0 " + lineSeparator + 
            "  0.0   0.0   0.0   0.0 -10.0   1.0   0.0   0.0   0.0   0.0   0.0   0.0 " + lineSeparator + 
            "  0.0   0.0   0.0   0.0 -10.0   0.0   1.0   0.0   0.0   0.0   0.0   0.0 " + lineSeparator + 
            "  0.0   0.0   0.0   0.0 -10.0   0.0   0.0   1.0   0.0   0.0   0.0   0.0 " + lineSeparator + 
            "  0.0   0.0   0.0   0.0 -10.0   0.0   0.0   0.0   1.0   0.0   0.0   0.0 " + lineSeparator + 
            "  0.0   0.0   0.0   0.0 -10.0   0.0   0.0   0.0   0.0   1.0   0.0   0.0 " + lineSeparator + 
            "  0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0 " + lineSeparator + 
            "  0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0 " + lineSeparator + 
            lineSeparator + 
            lineSeparator + 
            "Cloned 12x12  UN-changed:" + lineSeparator + 
            "  0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0 " + lineSeparator + 
            "  0.0   1.0   0.0   0.0 -10.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0 " + lineSeparator + 
            "  0.0   0.0   2.0   0.0 -20.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0 " + lineSeparator + 
            "  0.0   0.0   0.0   3.0 -30.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0 " + lineSeparator + 
            "  0.0  10.0  20.0  30.0 -40.0  50.0  60.0  70.0  80.0  90.0   0.0   0.0 " + lineSeparator + 
            "  0.0   0.0   0.0   0.0 -50.0   5.0   0.0   0.0   0.0   0.0   0.0   0.0 " + lineSeparator + 
            "  0.0   0.0   0.0   0.0 -60.0   0.0   6.0   0.0   0.0   0.0   0.0   0.0 " + lineSeparator + 
            "  0.0   0.0   0.0   0.0 -70.0   0.0   0.0   7.0   0.0   0.0   0.0   0.0 " + lineSeparator + 
            "  0.0   0.0   0.0   0.0 -80.0   0.0   0.0   0.0   8.0   0.0   0.0   0.0 " + lineSeparator + 
            "  0.0   0.0   0.0   0.0 -90.0   0.0   0.0   0.0   0.0   9.0   0.0   0.0 " + lineSeparator + 
            "  0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0 " + lineSeparator + 
            "  0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0 " + lineSeparator + 
            lineSeparator,
            outContent.toString());
   }
}