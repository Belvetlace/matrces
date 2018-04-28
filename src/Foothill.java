import java.text.NumberFormat;
import java.util.Locale;
import java.util.Random;

//------------------------------------------------------
public class Foothill
{
    final static int MAT_SIZE = 50;

    // -------  proof of correctness --------------
    public static void main(String[] args) throws Exception
    {
        int r, randRow, randCol;
        long startTime, stopTime;
        double randFrac;
        double smallPercent;
        NumberFormat tidy = NumberFormat.getInstance(Locale.US);
        tidy.setMaximumFractionDigits(4);

        // non-sparse matrices
        double[][] mat, matAns;

        // allocate matrices
        mat = new double[MAT_SIZE][MAT_SIZE];
        matAns = new double[MAT_SIZE][MAT_SIZE];

        Random rand = new Random();

        // generate small% of non-default values bet 0 and 1
        smallPercent = MAT_SIZE/10. * MAT_SIZE;
        for (r = 0; r < smallPercent; r++)
        {
            randRow = rand.nextInt(MAT_SIZE);
            randCol = rand.nextInt(MAT_SIZE);
            randFrac = Math.random();
            mat[randRow][randCol] = randFrac;
        }

        // 10x10 submatrix in lower right
        matShow(mat, MAT_SIZE - 10, 10);

        startTime = System.nanoTime();
        matMult(mat, mat, matAns);
        stopTime = System.nanoTime();

        matShow(matAns, MAT_SIZE - 10, 10);

        System.out.println("\nSize = " + MAT_SIZE + " Mat. Mult. Elapsed Time: "
                + tidy.format( (stopTime - startTime) / 1e9)
                + " seconds.");
    }

    // check that the first rows of each matrix are the same size.
    // let Java throw exceptions if the client passed bad matrices.

    //takes two input matrices, and the third a return (reference) product matrix:
    public static void matMult( double[][] matA,  double[][] matB,
                                double[][] matC)

    {

    }


    public static void matShow(double[][] matA, int start, int size)
    {

    }
}