/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neuralnetwork2;

/**
 *
 * @author user
 */
public class XOR {
    
    public static void main(String args[])
    {
        NeuralNetwork2  nn = new NeuralNetwork2(2,2,1);
        float in[][]={{0,0},{0,1},{1,0},{1,1}};
        float out[][] ={{0},{1},{1},{0}};
        
        for(int i=0; i < 50000; i++)
        {
            int t= (int) (Math.random() * 4);
            nn.train(in[t],out[t]);  
        }
        
        float test_data1[] = {0,0};
        float test_out1[] = nn.feedforward(test_data1);
        
        for(int i=0; i<test_out1.length; i++ )
        {
            System.out.println("Input: "+ test_data1[0] + " ," + test_data1[1]);
            System.out.println("Output: "+ test_out1[0]);
        }
        
        float test_data2[] = {1,0};
        float test_out2[] = nn.feedforward(test_data2);
        
        for(int i=0; i<test_out2.length; i++ )
        {
            System.out.println("Input: "+ test_data2[0] + " ," + test_data2[1]);
            System.out.println("Output: "+ test_out2[0]);
        }
        
        float test_data3[] = {0,1};
        float test_out3[] = nn.feedforward(test_data3);
        
        for(int i=0; i<test_out3.length; i++ )
        {
            System.out.println("Input: "+ test_data3[0] + " ," + test_data3[1]);
            System.out.println("Output: "+ test_out3[0]);
        }
        
        float test_data4[] = {1,1};
        float test_out4[] = nn.feedforward(test_data4);
        
        for(int i=0; i<test_out4.length; i++ )
        {
            System.out.println("Input: "+ test_data4[0] + " ," + test_data4[1]);
            System.out.println("Output: "+ test_out4[0]);
        }
       
        
    }
}
