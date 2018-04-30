import java.text.NumberFormat;
import java.util.Locale;
import java.util.Random;

//------------------------------------------------------
public class Foothill1
{
    final static int MAT_SIZE = 500;

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
        if (matA.length != matB.length){
            throw new IllegalArgumentException("answer is not defined");
        }
        double temp;
        for (int rowA = 0; rowA < MAT_SIZE; rowA++){
            for (int colB = 0; colB < MAT_SIZE; colB++){
                temp = .0;
                for (int rowB = 0; rowB < MAT_SIZE; rowB++){
                    temp += matA[rowA][rowB] * matB[rowB][rowA];
                }
                matC [rowA][colB] = temp;
            }
        }
    }

    public static void matMult2( double[][] matA,  double[][] matB,
                                double[][] matC)
    {
        for (int i = 0; i < matA.length; i++)
        {
            double temp = (matA[i][i] * matB[i][i]) + (matA[i][i + 1] * matB[i + 1][i]);
            matC[i][i] = temp;
            temp = (matA[i][i] * matB[i][i + 1]) + (matA[i][i + 1] * matB[i + 1][i + 1]);
            matC[i][i + 1] = temp;
            temp = (matA[i + 1][i] * matB[i][i]) + (matA[i + 1][i + 1] * matB[i + 1][i]);
            matC[i + 1][i] = temp;
            temp = (matA[i + 1][i] * matB[i][i + 1]) + (matA[i + 1][i + 1] * matB[i + 1][i + 1]);
            matC[i + 1][i + 1] = temp;
        }
    }

    public static void matShow(double[][] matA, int start, int size)
    {
        int msize = (start+size);
        //check for bounds
        if (start < 0 || start > MAT_SIZE || msize > MAT_SIZE )
        {
            throw new IllegalArgumentException("out of bounds");
        }
        if (size == 0)
        {
            throw new IllegalArgumentException("size is 0");
        }
        String lineSeparator = System.getProperty("line.separator");
        StringBuilder rStr = new StringBuilder();
        for (int r = start; r < msize; r++){
            for (int c = start; c < msize; c++){
                rStr.append(String.format("%5.1f", matA[r][c])).append(" ");
            }
            rStr.append(lineSeparator);
        }
        System.out.println(rStr);
    }
}
/*
Size = 50  Mat. Mult. Elapsed Time: 0.0032 seconds.
Size = 100 Mat. Mult. Elapsed Time: 0.0099 seconds.
Size = 200 Mat. Mult. Elapsed Time: 0.0318 seconds.
Size = 400 Mat. Mult. Elapsed Time: 0.2544 seconds.
Size = 800 Mat. Mult. Elapsed Time: 7.0093 seconds.
 */