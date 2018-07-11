package neuralnetwork2;

public class NeuralNetwork2 {

    int input_nodes;
    int hidden_nodes;
    int output_nodes;
    
    float learn_rate = (float) 0.1;
    
    Matrix weights_ih;
    Matrix weights_ho;
    
    Matrix bias_ih;
    Matrix bias_ho;
    
    public NeuralNetwork2(int in,int hide,int out)
    {
        this.input_nodes = in;
        this.hidden_nodes = hide;
        this.output_nodes = out;     
   
        
        this.weights_ih = new Matrix(this.hidden_nodes, this.input_nodes);
        this.weights_ho = new Matrix(this.output_nodes, this.hidden_nodes);
        
        
        this.weights_ih.randomize();
        this.weights_ho.randomize();
        
        
        this.bias_ih = new Matrix(this.hidden_nodes, 1);
        this.bias_ho = new Matrix(this.output_nodes, 1);
        
        this.bias_ih.randomize();
        this.bias_ho.randomize();
    }
    
    public void setLearningRate(float lr)
    {
        this.learn_rate = lr;
    }
    
    public Matrix activate(Matrix a)
    {
        Matrix temp = new Matrix(a.rows, a.cols);
        for(int i=0; i < a.rows; i++)
        {
            for(int j=0; j < a.cols; j++)
            {
                temp.data[i][j] = sigmoid(a.data[i][j]);
            }
        }
        return temp;
    }
    
    public float  sigmoid(float value)
    {
       float t;
       t = (float) (1 / ( 1 + Math.exp(- value )));
       return t; 
    }
    
    public Matrix differentiate(Matrix a)
    {
        Matrix temp = new Matrix(a.rows, a.cols);
        for(int i=0; i < a.rows; i++)
        {
            for(int j=0; j < a.cols; j++)
            {
                temp.data[i][j] = dsigmoid(a.data[i][j]);
            }
        }
        return temp;
    }
    
    public float  dsigmoid(float value)
    {
       float t;
       t= value * ( 1 - value);
       return t; 
    }
    
    public float[] feedforward(float input_array[])
    {
        Matrix input = Matrix.fromArray(input_array);   
        
        Matrix hidden= Matrix.Multiply(this.weights_ih, input);
        hidden.add(this.bias_ih);
        hidden = this.activate(hidden);
 
        Matrix output = Matrix.Multiply(this.weights_ho, hidden);
        output.add(this.bias_ho);       
        output = this.activate(output);

        float result[] = output.toArray();
        return result;
    }
    
    public void train(float input_array[], float target_array[])
    {
        Matrix input = Matrix.fromArray(input_array);   
        
        Matrix hidden= Matrix.Multiply(this.weights_ih, input);
        hidden.add(this.bias_ih);
        hidden = this.activate(hidden);
        
       
        Matrix output = Matrix.Multiply(this.weights_ho, hidden);
        output.add(this.bias_ho);       
        output = this.activate(output);
        
        Matrix target = Matrix.fromArray(target_array);
        
        Matrix output_error = Matrix.subtract(target, output);
        
        Matrix output_gradient = this.differentiate(output);
        output_gradient.HMultiply(output_error);
        output_gradient.SMultiply(this.learn_rate);
        
        
        Matrix hidden_T = Matrix.Transpose(hidden);
        Matrix delta_weights_ho = Matrix.Multiply(output_gradient, hidden_T);
        
        this.weights_ho.add(delta_weights_ho);
        this.bias_ho.add(output_gradient);
        
        Matrix weights_ho_T = Matrix.Transpose(this.weights_ho);
        Matrix hidden_error = Matrix.Multiply(weights_ho_T, output_error);
        
        Matrix hidden_gradient = this.differentiate(hidden);
        hidden_gradient.HMultiply(hidden_error);
        hidden_gradient.SMultiply(learn_rate);
        
        Matrix input_T = Matrix.Transpose(input);
        Matrix delta_weights_ih = Matrix.Multiply(hidden_gradient, input_T);
        
        this.weights_ih.add(delta_weights_ih);
        this.bias_ih.add(hidden_gradient);  
  
    }
    
 
}
