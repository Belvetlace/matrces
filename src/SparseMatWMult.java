class SparseMatWMult extends SparseMat<Double>
{


    public SparseMatWMult(int numRows, int numCols)
    {
        super(numRows, numCols, .0);
    }


    // sets the this object to the product of the passed parameters
    // note:  as usual it must  not do any output.
    // It does full error testing for dimension compatibility,
    // and insists that the matrices all have sizes 1×1 or larger.
    // It returns false if any of this fails.
    // Otherwise, it does the multiplication and returns true.
    public boolean matMult(SparseMatWMult matA, SparseMatWMult matB)
    {

        // ??
        return true;
    }
}