import cs_1c.FHarrayList;
import cs_1c.FHlinkedList;

public class SparseMat<E> implements Cloneable
{
    protected int numRows, numCols;
    protected E defaultVal;
    protected FHarrayList<FHlinkedList< MatNode >> rows;

    public SparseMat(int numRows, int numCols, E defaultVal)
    {
        this.numRows = numRows;
        this.numCols = numCols;
        this.defaultVal = defaultVal;
        allocateEmptyMatrix();
    }

    //allocates the memory of an empty matrix
    private void allocateEmptyMatrix()
    {
        rows = new FHarrayList<FHlinkedList< MatNode >>(numRows);
        //filling array list with blank linked lists
        for(int i = 0; i< numRows; i ++)
        {
            rows.add(new FHlinkedList<MatNode>());
        }
    }

    // returns the object stored in row r and column c.
    // throws an IndexOutOfBoundsException if matrix bounds (row and/or column) are violated.
    E get(int r, int c) throws IndexOutOfBoundsException
    {

        return null;
    }

    //  places x in row r and column c if x is not default
    // if x is the default value it will remove any existing node
    // returns false without an exception if bounds are violated
    public boolean set(int row, int column, E value)
    {
        return true;
    }

    // a display method that will show a square sub-matrix anchored at (start, start) and whose size is size x size.
    // it will show the rows from start to start + size -1 and the columns from start to start + size - 1.
    public void showSubSquare(int start, int size)
    {

    }

    // clears all the rows, effectively setting all values to the defaultVal
    // (leaves the matrix size unchanged).
    public void clear()
    {

    }
    @Override
    protected Object clone() throws CloneNotSupportedException
    {
        return super.clone();

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

        public Object clone() throws CloneNotSupportedException
        {
            // shallow copy
            MatNode newObject = (MatNode)super.clone();
            return (Object) newObject;
        }
    }
}



