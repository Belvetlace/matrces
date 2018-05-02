import cs_1c.FHarrayList;
import cs_1c.FHlinkedList;

import java.text.NumberFormat;
import java.util.Iterator;
import java.util.Locale;
import java.util.Random;

//------------------------------------------------------
public class Foothill
{
    final static int MAT_SIZE = 800;
    final static int MAT_SIZE_S = 800;

    // -------  proof of correctness --------------
    public static void main(String[] args) throws Exception
    {
        int r, randRow, randCol;
        long startTime, stopTime;
        double randFrac;
        double smallPercent;
        NumberFormat tidy = NumberFormat.getInstance(Locale.US);
        tidy.setMaximumFractionDigits(4);

        Random rand = new Random();
        // non-sparse matrices
        double[][] mat, matAns;

        // allocate matrices
        mat = new double[MAT_SIZE][MAT_SIZE];
        matAns = new double[MAT_SIZE][MAT_SIZE];
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
        System.out.println("Double Array Matrix 1");
        matShow(mat1, 0, 5);
        System.out.println("Double Array Matrix 2");
        matShow(mat2, 0, 5);
        matMult(mat1, mat2, matAns);
        System.out.println("Result of Matrix 1 X Matrix 2");
        matShow(matAns, 0, 5);

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
        //matShow(mat, MAT_SIZE - 10, 10);
        startTime = System.nanoTime();
        matMult(mat, mat, matAns);
        stopTime = System.nanoTime();
        //matShow(matAns, MAT_SIZE - 10, 10);

        System.out.println("\nSize = " + MAT_SIZE + " Mat. Mult. Elapsed Time: "
                + tidy.format( (stopTime - startTime) / 1e9)
                + " seconds.\n");

        // sparse matrices: test 5X5
        System.out.println("Sparse matrices 5X5(same input as double array mat)");

        SparseMatWMult mSparseMat, nSparseMat, matAnsS;
        mSparseMat = new SparseMatWMult(5, 5);
        nSparseMat = new SparseMatWMult(5, 5);
        matAnsS = new SparseMatWMult(5, 5);

        for (int row = 0; row < 5; row++)
            for (int col = 0; col < 5; col++)
            {
                mSparseMat.set(row, col, mat1[row][col]);
                nSparseMat.set(row, col, mat2[row][col]);
            }

        matAnsS.matMult(mSparseMat, nSparseMat);

        mSparseMat.showSubSquare(0, 5);
        nSparseMat.showSubSquare(0, 5);
        matAnsS.showSubSquare(0, 5);

        System.out.println("Sparse Matrices 0.2%:");
        //SparseMatWMult mSparseMat, nSparseMat, matAnsS;
        mSparseMat = new SparseMatWMult(MAT_SIZE_S, MAT_SIZE_S);
        nSparseMat = new SparseMatWMult(MAT_SIZE_S, MAT_SIZE_S);
        matAnsS = new SparseMatWMult(MAT_SIZE_S, MAT_SIZE_S);

        smallPercent = MAT_SIZE_S/500. * MAT_SIZE_S;
        for (r = 0; r < smallPercent; r++)
        {
            randRow = rand.nextInt(MAT_SIZE_S);
            randCol = rand.nextInt(MAT_SIZE_S);
            randFrac = Math.random();
            mSparseMat.set(randRow, randCol, randFrac);
            nSparseMat.set(randRow, randCol, randFrac);
        }

        System.out.println("Sparse matA");
        mSparseMat.showSubSquare(0, 10);
        System.out.println("Sparse matB");
        nSparseMat.showSubSquare(0, 10);

        startTime = System.nanoTime();
        matAnsS.matMult(mSparseMat, nSparseMat);
        stopTime = System.nanoTime();

        System.out.println("Sparse matC result");
        matAnsS.showSubSquare(0, 10);

        System.out.println("Size = " + MAT_SIZE_S + " Mat. Mult. Elapsed Time: "
                + tidy.format( (stopTime - startTime) / 1e9)
                + " seconds.");

        System.out.println("Sparse Matrices 1%:");
        mSparseMat = new SparseMatWMult(MAT_SIZE_S, MAT_SIZE_S);
        nSparseMat = new SparseMatWMult(MAT_SIZE_S, MAT_SIZE_S);
        matAnsS = new SparseMatWMult(MAT_SIZE_S, MAT_SIZE_S);

        smallPercent = MAT_SIZE_S * MAT_SIZE_S / 100.;
        for (r = 0; r < smallPercent; r++)
        {
            randRow = rand.nextInt(MAT_SIZE_S);
            randCol = rand.nextInt(MAT_SIZE_S);
            randFrac = Math.random();
            mSparseMat.set(randRow, randCol, randFrac);
            nSparseMat.set(randRow, randCol, randFrac);
        }
        startTime = System.nanoTime();
        matAnsS.matMult(mSparseMat, nSparseMat);
        stopTime = System.nanoTime();
        System.out.println("Size = " + MAT_SIZE_S + " Mat. Mult. Elapsed Time: "
                + tidy.format( (stopTime - startTime) / 1e9)
                + " seconds.");

        System.out.println("Sparse Matrices 5%:");
        mSparseMat = new SparseMatWMult(MAT_SIZE_S, MAT_SIZE_S);
        nSparseMat = new SparseMatWMult(MAT_SIZE_S, MAT_SIZE_S);
        matAnsS = new SparseMatWMult(MAT_SIZE_S, MAT_SIZE_S);

        smallPercent = MAT_SIZE_S * MAT_SIZE_S / 20.;
        for (r = 0; r < smallPercent; r++)
        {
            randRow = rand.nextInt(MAT_SIZE_S);
            randCol = rand.nextInt(MAT_SIZE_S);
            randFrac = Math.random();
            mSparseMat.set(randRow, randCol, randFrac);
            nSparseMat.set(randRow, randCol, randFrac);
        }
        startTime = System.nanoTime();
        matAnsS.matMult(mSparseMat, nSparseMat);
        stopTime = System.nanoTime();
        System.out.println("Size = " + MAT_SIZE_S + " Mat. Mult. Elapsed Time: "
                + tidy.format( (stopTime - startTime) / 1e9)
                + " seconds.");

        System.out.println("Sparse Matrices 10%:");
        mSparseMat = new SparseMatWMult(MAT_SIZE_S, MAT_SIZE_S);
        nSparseMat = new SparseMatWMult(MAT_SIZE_S, MAT_SIZE_S);
        matAnsS = new SparseMatWMult(MAT_SIZE_S, MAT_SIZE_S);

        smallPercent = MAT_SIZE_S * MAT_SIZE_S / 10.;
        for (r = 0; r < smallPercent; r++)
        {
            randRow = rand.nextInt(MAT_SIZE_S);
            randCol = rand.nextInt(MAT_SIZE_S);
            randFrac = Math.random();
            mSparseMat.set(randRow, randCol, randFrac);
            nSparseMat.set(randRow, randCol, randFrac);
        }
        startTime = System.nanoTime();
        matAnsS.matMult(mSparseMat, nSparseMat);
        stopTime = System.nanoTime();
        System.out.println("Size = " + MAT_SIZE_S + " Mat. Mult. Elapsed Time: "
                + tidy.format( (stopTime - startTime) / 1e9)
                + " seconds.");
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
        for (int rowA = 0; rowA < matA.length; rowA++){
            for (int colB = 0; colB < matA.length; colB++){
                temp = .0;
                for (int rowB = 0; rowB < matA.length; rowB++){
                    temp += matA[rowA][rowB] * matB[rowB][colB];
                }
                matC [rowA][colB] = temp;
            }
        }
    }

    public static void matShow(double[][] matA, int start, int size)
    {
        int msize = (start+size);
        if (start < 0 || start > matA.length || msize > matA.length )
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

class SparseMatWMult extends SparseMat<Double>
{

    public SparseMatWMult(int numRows, int numCols)
    {
        super(numRows, numCols, .0);
    }

    public boolean matMult(SparseMatWMult matA, SparseMatWMult matB)
    {
        if(matA.rows.isEmpty() || matB.rows.isEmpty()
                ||matA.getRowSize() != matB.getRowSize())
        {
            return false;
        }
        double temp;
        Iterator<MatNode> iter;
        MatNode<Double> nodeA;
        int col;
        if (this.numRows != matA.getRowSize()) //set correct size if needed
        {
            setSize(matA.getRowSize(), matA.getColSize());
        }
        for (int rowA = 0; rowA < numRows; rowA++)
        {
            FHlinkedList<MatNode> matNodesA = matA.rows.get(rowA);
            if (!matNodesA.isEmpty()){
                for (int colB = 0; colB < numCols; colB++)
                {
                    iter = matNodesA.iterator();
                    temp = .0;
                    while (iter.hasNext())
                    {
                        nodeA = iter.next();
                        col = nodeA.getCol();
                        try
                        {
                            temp += matB.get(col, colB) * nodeA.getData();
                        } catch (IndexOutOfBoundsException e)
                        {
                            // value is 0, do nothing
                        }
                    }
                    this.set(rowA, colB, temp);
                }
            }
        }
        return true;
    }

    private boolean setSize(int numRows, int numCols){
        this.numRows = numRows;
        this.numCols = numCols;
        rows = new FHarrayList<FHlinkedList< MatNode >>(numRows);
        for(int i = 0; i< numRows; i ++)
        {
            rows.add(new FHlinkedList<MatNode>());
        }
        return true;
    }
}


class SparseMat<E> implements Cloneable
{
    protected int numRows, numCols;
    protected E defaultVal;
    protected FHarrayList<FHlinkedList< MatNode >> rows;
    private static String lineSeparator = System.getProperty("line.separator");

    public SparseMat(int numRows, int numCols, E defaultVal)
    {
        if(numRows < 1 || numCols < 1)
        {
            throw new IllegalArgumentException();
        }
        this.numRows = numRows;
        this.numCols = numCols;
        this.defaultVal = defaultVal;
        allocateEmptyMatrix();
    }

    private void allocateEmptyMatrix()
    {
        rows = new FHarrayList<FHlinkedList< MatNode >>(numRows);
        for(int i = 0; i< numRows; i ++)
        {
            rows.add(new FHlinkedList<MatNode>());
        }
    }

    public E get(int r, int c) throws IndexOutOfBoundsException
    {
        if (r < 0 || r >= numRows || c < 0 || c >= numCols)
        { throw new IndexOutOfBoundsException(); }

        FHlinkedList<MatNode> matNodes = rows.get(r);
        if (!matNodes.isEmpty())
        {
            Iterator<MatNode> iter = matNodes.iterator();
            MatNode<E> temp;
            while (iter.hasNext())
            {
                temp = iter.next();
                if (c == temp.getCol())
                {
                    return temp.getData();
                }
            }
        }
        return defaultVal;

    }

    public boolean set(int r, int c, E x)
    {
        if (r < 0 || r >= numRows || c < 0 || c >= numCols){ return false; }
        FHlinkedList<MatNode> matNodes = rows.get(r);
        if (matNodes.isEmpty())
        {
            if ( !x.equals( defaultVal )) {
                matNodes.add(new MatNode(c, x));
            }
            return true;
        }

        Iterator<MatNode> iter = matNodes.iterator();
        boolean colunmExist = false;
        MatNode<E> temp = null;
        while (iter.hasNext())
        {
            temp = iter.next();
            if (c == temp.getCol())
            {
                colunmExist = true;
                break;
            }
        }
        if (colunmExist)
        {
            if ( x.equals( defaultVal) ) {
                iter.remove();
                return true;
            }
            try{
                matNodes.set(matNodes.indexOf(temp), new MatNode(c, x));
                return true;
            } catch (IndexOutOfBoundsException e){
                return false;
            }
        } else {
            if(!x.equals( defaultVal )){
                iter = matNodes.iterator();
                if (iter.hasNext())
                {
                    temp = iter.next();
                    if(temp.getCol() > c)
                    {
                        matNodes.add(matNodes.indexOf(temp), new MatNode(c, x));
                        return true;
                    }
                    else {
                        matNodes.add(new MatNode(c, x));
                        return true;
                    }
                }
            }
        }
        return true;
    }


    public void showSubSquare(int start, int size) {
        int msize = (start+size);
        StringBuilder rStr = new StringBuilder();
        if (start < 0 || start > numRows || start > numCols
                || msize > numCols || msize > numRows)
        {
            System.out.print("");
            return;
        }
        if (size == 0)
        {
            System.out.println("");
            return;
        }
        for (int r = start; r < msize; r++)
        {
            if (rows.get(r).isEmpty())
            {
                for (int i = start; i < msize; i++)
                {
                    rStr.append(String.format("%5.2f", defaultVal));
                    rStr.append(" ");
                }
            } else {
                for (int c = start; c < msize; c++)
                {
                    try
                    {
                        rStr.append(String.format("%5.2f", this.get(r,c)));
                        rStr.append(" ");
                    }
                    catch (IndexOutOfBoundsException e)
                    {
                        rStr.append(String.format("%5.2f", defaultVal));
                        rStr.append(" ");
                    }
                }
            }
            rStr.append(lineSeparator);
        }

        System.out.println(rStr);
    }

    public void clear()
    {
        for(int i = 0; i < rows.size(); i++)
        {
            if (!rows.get(i).isEmpty())
            {
                rows.get(i).clear();
            }
        }
    }

    @Override
    protected Object clone() throws CloneNotSupportedException
    {
        SparseMat<MatNode> newObject = (SparseMat<MatNode>)super.clone();
        newObject.rows = (FHarrayList<FHlinkedList< MatNode >>)rows.clone();
        for(int i = 0; i< numRows; i ++)
        {
            newObject.rows.set(i,(FHlinkedList<MatNode>)rows.get(i).clone());
        }
        return newObject;
    }

    public int getRowSize()
    {
        return numRows;
    }

    public int getColSize()
    {
        return numCols;
    }

    protected class MatNode<E> implements Cloneable
    {
        public int col;
        public E data;

        MatNode()
        {
            col = 0;
            data = null;
        }

        MatNode(int cl, E dt)
        {
            col = cl;
            data = dt;
        }

        E getData()
        {
            return data;
        }

        boolean setData(E data)
        {
            this.data = data;
            return true;
        }

        int getCol()
        {
            return col;
        }

        public Object clone() throws CloneNotSupportedException
        {
            MatNode newObject = (MatNode)super.clone();
            return (Object) newObject;
        }
    }
}

/*
Double Array Matrix 1
  1.0   2.0   3.0   4.0   5.0
 -1.0  -2.0  -3.0  -4.0  -5.0
  1.0   3.0   1.0   3.0   1.0
  0.0   1.0   0.0   1.0   0.0
 -1.0  -1.0  -1.0  -1.0  -1.0

Double Array Matrix 2
  2.0   1.0   5.0   0.0   2.0
  1.0   4.0   3.0   2.0   7.0
  4.0   4.0   4.0   4.0   4.0
  7.0   1.0  -1.0  -1.0  -1.0
  0.0   0.0   8.0  -1.0  -6.0

Result of Matrix 1 X Matrix 2
 44.0  25.0  59.0   7.0  -6.0
-44.0 -25.0 -59.0  -7.0   6.0
 30.0  20.0  23.0   6.0  18.0
  8.0   5.0   2.0   1.0   6.0
-14.0 -10.0 -19.0  -4.0  -6.0


Size = 800 Mat. Mult. Elapsed Time: 8.0351 seconds.

Sparse matrices 5X5(same input as double array mat)
 1.00  2.00  3.00  4.00  5.00
-1.00 -2.00 -3.00 -4.00 -5.00
 1.00  3.00  1.00  3.00  1.00
 0.00  1.00  0.00  1.00  0.00
-1.00 -1.00 -1.00 -1.00 -1.00

 2.00  1.00  5.00  0.00  2.00
 1.00  4.00  3.00  2.00  7.00
 4.00  4.00  4.00  4.00  4.00
 7.00  1.00 -1.00 -1.00 -1.00
 0.00  0.00  8.00 -1.00 -6.00

44.00 25.00 59.00  7.00 -6.00
-44.00 -25.00 -59.00 -7.00  6.00
30.00 20.00 23.00  6.00 18.00
 8.00  5.00  2.00  1.00  6.00
-14.00 -10.00 -19.00 -4.00 -6.00

Sparse Matrices 0.2%:
Sparse matA
 0.00  0.00  0.00  0.00  0.00  0.00  0.00  0.00  0.00  0.00
 0.00  0.00  0.00  0.00  0.00  0.00  0.00  0.00  0.00  0.00
 0.00  0.00  0.00  0.00  0.00  0.00  0.00  0.00  0.00  0.00
 0.00  0.00  0.00  0.00  0.00  0.00  0.00  0.00  0.00  0.00
 0.00  0.00  0.00  0.00  0.00  0.00  0.00  0.00  0.00  0.00
 0.00  0.00  0.00  0.00  0.00  0.00  0.00  0.00  0.00  0.00
 0.00  0.00  0.00  0.00  0.00  0.00  0.00  0.00  0.00  0.00
 0.00  0.00  0.00  0.00  0.00  0.00  0.00  0.00  0.00  0.00
 0.00  0.00  0.00  0.00  0.00  0.00  0.00  0.00  0.00  0.00
 0.00  0.00  0.00  0.00  0.00  0.00  0.00  0.00  0.00  0.00

Sparse matB
 0.00  0.00  0.00  0.00  0.00  0.00  0.00  0.00  0.00  0.00
 0.00  0.00  0.00  0.00  0.00  0.00  0.00  0.00  0.00  0.00
 0.00  0.00  0.00  0.00  0.00  0.00  0.00  0.00  0.00  0.00
 0.00  0.00  0.00  0.00  0.00  0.00  0.00  0.00  0.00  0.00
 0.00  0.00  0.00  0.00  0.00  0.00  0.00  0.00  0.00  0.00
 0.00  0.00  0.00  0.00  0.00  0.00  0.00  0.00  0.00  0.00
 0.00  0.00  0.00  0.00  0.00  0.00  0.00  0.00  0.00  0.00
 0.00  0.00  0.00  0.00  0.00  0.00  0.00  0.00  0.00  0.00
 0.00  0.00  0.00  0.00  0.00  0.00  0.00  0.00  0.00  0.00
 0.00  0.00  0.00  0.00  0.00  0.00  0.00  0.00  0.00  0.00

Sparse matC result
 0.00  0.00  0.00  0.00  0.00  0.00  0.00  0.00  0.00  0.00
 0.00  0.00  0.00  0.00  0.00  0.00  0.00  0.00  0.00  0.00
 0.00  0.00  0.00  0.00  0.00  0.00  0.00  0.00  0.00  0.00
 0.00  0.19  0.00  0.00  0.00  0.00  0.00  0.00  0.00  0.00
 0.00  0.00  0.00  0.00  0.00  0.00  0.00  0.00  0.00  0.00
 0.00  0.00  0.00  0.00  0.00  0.00  0.00  0.00  0.00  0.00
 0.00  0.00  0.00  0.00  0.00  0.00  0.00  0.00  0.00  0.00
 0.00  0.00  0.00  0.00  0.00  0.00  0.00  0.00  0.00  0.00
 0.00  0.00  0.00  0.00  0.00  0.00  0.00  0.00  0.00  0.00
 0.00  0.00  0.00  0.00  0.00  0.00  0.00  0.00  0.00  0.00

Size = 800 Mat. Mult. Elapsed Time: 0.1327 seconds.
Sparse Matrices 1%:
Size = 800 Mat. Mult. Elapsed Time: 0.422 seconds.
Sparse Matrices 5%:
Size = 800 Mat. Mult. Elapsed Time: 12.8505 seconds.
Sparse Matrices 10%:
Size = 800 Mat. Mult. Elapsed Time: 28.8006 seconds.

*/

/*
-----Double Array Matrices Time:----------

Size = 100 Mat. Mult. Elapsed Time: 0.0099 seconds.
Size = 200 Mat. Mult. Elapsed Time: 0.0318 seconds.
Size = 400 Mat. Mult. Elapsed Time: 0.2544 seconds.
Size = 800 Mat. Mult. Elapsed Time: 7.0093 seconds.

-----Sparse Matrices Times:----------

Size = 3200 Mat. Mult. Elapsed Time: 76.5753 seconds.

 */