/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

import javax.naming.spi.DirStateFactory;

/**
 *
 * @author Admin
 */
public class Matrix {
   private int row;
   private int col;
   private int data[][];

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public Matrix(int row, int col) throws Exception{
        if(row >= 0 && col >= 0){
            this.row = row;
            this.col = col;
            this.data = new int[row][col];
        }else{
            throw new Exception("Row and Col must be >= 0");
        }
        
    }

    public Matrix(int[][] data) throws Exception{
        if(data == null){
            throw new Exception("Matrix is null or Empty");
        }else{
        this.data = data;
        this.col = data[0].length;
        this.row = data.length;
        }
    }
    
    public Matrix add(Matrix other) throws Exception{
        if(row != other.row || col != other.col){
            throw new Exception("Row and Col of 2 Matrix not equal");
        }
        int dataResult[][] = new int[row][col];
        for(int i = 0; i < row; i++){
            for(int j = 0; j <col; j++){
                dataResult[i][j] = this.data[i][j] + other.data[i][j];
            }
        }
        Matrix result = new Matrix(dataResult);
        return result;
    }
   
    public Matrix sub(Matrix other) throws Exception{
        if(row != other.row || col != other.col){
            throw new Exception("Row and Col of 2 Matrix not equal");
        }
        int dataResult[][] = new int[row][col];
        for(int i = 0; i < row; i++){
            for(int j = 0; j <col; j++){
                dataResult[i][j] = this.data[i][j] - other.data[i][j];
            }
        }
        Matrix result = new Matrix(dataResult);
        return result;
    }
    
    public Matrix mul(Matrix other) throws Exception{
        if(col != other.row){
            throw new Exception(" Col of  Matrix1 not equal Row of Matrix2");
        }
        int data[][] = new int[row][other.col];
        for(int i = 0; i < row; i++){
            for(int j = 0; j <other.col; j++){
                for(int k = 0; k < col; k++){
                data[i][j] += this.data[i][k] * other.data[k][j];
            }
            }
        }
        Matrix result = new Matrix(data);
        return result;
    }

    @Override
    public String toString() {
        String str="";
        for(int i = 0; i< row; i++){
            for(int j = 0; j< col ; j++){
                str += String.format("[%d]", data[i][j]);
            }
            str+="\n";
        }
        return str;
    }
    
    
    
}