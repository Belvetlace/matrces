import cs_1c.FHarrayList;
import cs_1c.FHlinkedList;

import java.util.Iterator;

public class SparseMat<E> implements Cloneable
{
    protected int numRows, numCols;
    protected E defaultVal;
    protected FHarrayList<FHlinkedList< MatNode >> rows;

    //row size and column size both >= 1
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
    public E get(int r, int c) throws IndexOutOfBoundsException
    {
        if (r < 0 || r > numRows || c < 0 || c > numCols)
        { throw new IndexOutOfBoundsException(); }
        if (rows.get(r).isEmpty())
        {
            return null;
        } else {
            return (E)rows.get(r).get(c).getData();
        }
    }

    // places x in row r and column c if x is not default
    // if x is the default value it will remove any existing node
    // returns false without an exception if bounds are violated
    public boolean set(int r, int c, E x)
    {
        // check the bounds
        if (r < 0 || r > numRows || c < 0 || c > numCols){ return false; }
        // check if row is empty
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
        while (iter.hasNext())
        {
            if (c == iter.next().getCol()){ colunmExist = true; }
        }
        if (colunmExist)
        {
            if ( x.equals( defaultVal) ) {
                iter.remove();
                return true;
            }
            //set new value
            try{
                matNodes.set(c-1, new MatNode(c, x)); // sets it if x is not default
                return true;
            } catch (IndexOutOfBoundsException e){
                return false;
            }
        } else { // col does not exist //todo: put them in order
            if(!x.equals( defaultVal )){
                matNodes.add(new MatNode(c, x));
                return true;
            }
        }
        return true;
    }


    public void showSubSquare(int start, int size) {
        Iterator<MatNode> iter;
        int msize = (start+size);
        //check start for bounds
        System.out.println(String.format("start %d msize %d", start, msize));
        if (start < 0 || start > numRows || start > numCols
                || msize > numCols || msize > numRows)
        {
            System.out.println("start or size out of range");
            return;
        }

        for (int j = start; j < msize; j++)
        {
            StringBuilder rStr = new StringBuilder();
            iter = rows.get(j).iterator();
            if (rows.get(j).isEmpty())
            {
                for (int i = start; i < msize; i++)
                {
                    rStr.append(String.format("%5.1f ", defaultVal));
                }
            } else {
                int q = 0;
                while (iter.hasNext())
                {
                    MatNode<E> temp = iter.next();
                    while(q < temp.getCol())
                    {
                        rStr.append(String.format("%5.1f ", defaultVal));
                        q++;
                    }
                    rStr.append(String.format("%5.1f ", temp.getData()));
                    q++;
                }
                if (q < msize){
                    while (q < msize){
                        rStr.append(String.format("%5.1f ", defaultVal));
                        q++;
                    }
                }
            }
            System.out.println(rStr);
        }
    }

    // clears all the rows, effectively setting all values to the defaultVal
    // (leaves the matrix size unchanged).
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

        E getData()
        {
            return data;
        }

        void setData(E data)
        {
            this.data = data;
        }

        int getCol()
        {
            return col;
        }

        public Object clone() throws CloneNotSupportedException
        {
            // shallow copy
            MatNode newObject = (MatNode)super.clone();
            return (Object) newObject;
        }
    }
}



