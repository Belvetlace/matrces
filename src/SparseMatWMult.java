import cs_1c.FHarrayList;
import cs_1c.FHlinkedList;

import java.util.Iterator;

class SparseMatWMult extends SparseMat<Double>
{


    public SparseMatWMult(int numRows, int numCols)
    {
        super(numRows, numCols, .0);
    }


    // sets the this object to the product of the passed parameters
    // It does full error testing for dimension compatibility,
    // and insists that the matrices all have sizes 1×1 or larger.
    // It returns false if any of this fails.
    // Otherwise, it does the multiplication and returns true.
    public boolean matMult(SparseMatWMult matA, SparseMatWMult matB)
    {
        if(matA.rows.isEmpty() || matB.rows.isEmpty()
                ||matA.getRowSize() != matB.getRowSize()) //size is 0 or different size
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
                // todo: check for identity matrix later
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
                            // value is 0 do nothing
                        }
                    }
                    this.set(rowA, colB, temp);
                }
            }
        }
        return true;
    }

    private void setSize(int numRows, int numCols){
        this.numRows = numRows;
        this.numCols = numCols;
        rows = new FHarrayList<FHlinkedList< MatNode >>(numRows);
        for(int i = 0; i< numRows; i ++)
        {
            rows.add(new FHlinkedList<MatNode>());
        }
    }
}