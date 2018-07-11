package neuralnetwork2;

public class Matrix {

   
    int rows;
    int cols;
    
    float data[][];
    
    public Matrix(int rows,int cols)
    {
        this.rows = rows;
        this.cols = cols;
        
        data = new float[rows][cols];
       for(int i=0; i< this.rows; i++)
       {
            for(int j=0; j< this.cols; j++)
            {
                this.data[i][j] = 0;
            }
       }        
    }
    public void randomize()
    {
        for(int i=0; i< this.rows; i++)
        {
            for(int j=0; j< this.cols; j++)
            {
                this.data[i][j] = (float) ((Math.random() * 2) - 1);
            }
        }
    }
    public void add(Matrix a)
    {
        for(int i=0; i< a.rows; i++)
        {
            for(int j=0; j< a.cols; j++)
            {
                this.data[i][j] += a.data[i][j];
            }
        }
    }
    public void HMultiply(Matrix a)
    {
        for(int i=0; i< a.rows; i++)
        {
            for(int j=0; j< a.cols; j++)
            {
                this.data[i][j] *= a.data[i][j];
            }
        }
    }
    public void SMultiply(float a)
    {
        for(int i=0; i< this.rows; i++)
        {
            for(int j=0; j< this.cols; j++)
            {
                this.data[i][j] *= a;
            }
        }
    }
    
    public static Matrix subtract(Matrix a, Matrix b)
    {
        Matrix t = new Matrix(a.rows, a.cols);
        for(int i=0; i< t.rows; i++)
        {
            for(int j=0; j< t.cols; j++)
            {
                t.data[i][j] = a.data[i][j] - b.data[i][j];
            }
        }
        return t;
    }
    public static Matrix fromArray(float input_array[])
    {
        int row = input_array.length;
        Matrix m = new Matrix(row, 1);
        
        for(int i=0; i< m.rows; i++)
        {           
               m.data[i][0] = input_array[i];           
        }
        return m;          
    }
    public float[] toArray()
    {
        float[] result = new float[this.rows];
        
        for(int i=0; i < result.length; i++)
        {
            result[i] = this.data[i][0];
        }
        
        return result;         
    }
    
    public static Matrix Multiply(Matrix a, Matrix b)
    {
       Matrix m = new Matrix(a.rows,b.cols);
       for(int i=0; i< m.rows; i++)
       {
           for(int j=0; j< m.cols; j++)
           {
               for(int k=0; k < b.rows; k++)
               {
                   m.data[i][j] += a.data[i][k] * b.data[k][j];
               }
           }
       }
       return m;
    }
    public static Matrix Transpose(Matrix a)
    {
       Matrix m = new Matrix(a.cols,a.rows);
       for(int i=0; i< m.rows; i++)
       {
           for(int j=0; j< m.cols; j++)
           {
               m.data[i][j] = a.data[j][i];
           }
       }
       return m;
    }
    
    
    public void print()
    {
        for(int i=0; i< this.rows; i++)
        {
            for(int j=0; j< this.cols; j++)
            {
                System.out.print(this.data[i][j] + " ");
            }
            System.out.println();
            
        }
         System.out.println();
    }
   
    
    
}
