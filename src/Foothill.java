public class Foothill
{
    final static int MAT_SIZE = 5;

    // -------  proof of correctness --------------
    public static void main(String[] args) throws Exception
    {
        int row, col;

        // non-sparse matrices
        double[][] matAns = new double[MAT_SIZE][MAT_SIZE];
        double[][]
                mat1 =
                {
                        {1, 2, 3, 4, 5},
                        {-1, -2, -3, -4, -5},
                        {1, 3, 1, 3, 1},
                        {0, 1, 0, 1, 0},
                        {-1, -1, -1, -1, -1}
                };
        double[][] mat2 =
                {
                        {2, 1, 5, 0, 2},
                        {1, 4, 3, 2, 7},
                        {4, 4, 4, 4, 4},
                        {7, 1, -1, -1, -1},
                        {0, 0, 8, -1, -6}
                };

        matShow(mat1, 0, 5);
        matShow(mat2, 0, 5);
        matMult(mat1, mat2, matAns);

        matShow(matAns, 0, 5);

        // sparse matrices
        SparseMatWMult mSparseMat, nSparseMat, matAnsS;
        mSparseMat = new SparseMatWMult(MAT_SIZE, MAT_SIZE);
        nSparseMat = new SparseMatWMult(MAT_SIZE, MAT_SIZE);
        matAnsS = new SparseMatWMult(MAT_SIZE, MAT_SIZE);

        for (row = 0; row < MAT_SIZE; row++)
            for (col = 0; col < MAT_SIZE; col++)
            {
                mSparseMat.set(row, col, mat1[row][col]);
                nSparseMat.set(row, col, mat2[row][col]);
            }
        matAnsS.matMult(mSparseMat, nSparseMat);

        mSparseMat.showSubSquare(0, 5);
        nSparseMat.showSubSquare(0, 5);
        matAnsS.showSubSquare(0, 5);
    }

    // check that the first rows of each matrix are the same size.
    // let Java throw exceptions if the client passed bad matrices.

    //takes two input matrices, and the third a return (reference) product matrix:
    public static void matMult( double[][] matA,  double[][] matB,
                                double[][] matC)
    {
        if (matA.length != matB.length || matA.length == 0){
            throw new IllegalArgumentException("answer is not defined");
        }
        double temp;
        for (int rowA = 0; rowA < MAT_SIZE; rowA++){
            for (int colB = 0; colB < MAT_SIZE; colB++){
                temp = .0;
                for (int rowB = 0; rowB < MAT_SIZE; rowB++){
                    temp += matA[rowA][rowB] * matB[rowB][colB];
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
            System.out.println("");
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