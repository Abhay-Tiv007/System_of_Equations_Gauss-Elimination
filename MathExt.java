import java.io.FileReader;
import java.util.*;

public class MathExt {
    public static void main(String[]args) throws Exception{

        //Take input of the matrices from the file

        //Format of Input ---->
        //First line - an integer n
        //Following n lines - Cofficient Matrix A
        //Next n lines - RHS Matrix B

        Scanner sc=new Scanner(System.in);


        //Takes Matrices as input from file
        System.out.println("Enter the input file name:");
        String inpFile = sc.nextLine();     

        //Default File is Test InputData in case if user does not inputs any file name
        inpFile = inpFile.equals("")?"InputData":inpFile; 
        
        //Read the file data from the file into FileReader buffer
        FileReader fr = new FileReader(inpFile+".txt");
        Scanner frInp=new Scanner(fr);

        //O(n^2) space complexity
        float a[][],b[],X[];
        int n,i,j;

        //an integer n from Input File
        n = frInp.nextInt();

        a = new float[n][n];
        b = new float[n];

        //Cofficient Matrix A from Input File
        for(i = 0; i < n; ++i){
            for(j = 0; j < n; ++j){
                a[i][j] = frInp.nextFloat();
            }
        }

        //RHS Matrix B from Input File
        for(i = 0; i < n; ++i){
            b[i] = frInp.nextFloat();
        }


        //Function Call to Solve the System of Equation X = A^(-1) * B
        X = new MathExt().GaussElim(a, b, n);

        
        //Output ---->  Resultant Matrix/Vector of Solution Set
        DisplayMatrix(X);



        //Close all the Buffer Systems to prevent Data Loss
        frInp.close();
        sc.close();
        fr.close();
    }

    float[] GaussElim(float[][] A, float[] B,int N){
        float x[],mult = 0;
        int i,j,k;
        

        //Initialize solution Matrix(size N) of zeros
        x=new float[N];


        //Gauss Elimination Part
        for(i = 0; i< N-1; ++i){
            for(j = i+1; j < N; ++j){
                if(A[i][i] == 0)
                    exchangeRows(j, i, A, N);
                mult = A[j][i]/A[i][i];
                A[j][i] = 0;
                for(k = i+1; k < N; ++k){
                    A[j][k] -= A[i][k]*mult;
                }
                B[j] -= B[i]*mult;
            }
        }// O(n^3) time complexity


        //Back-Substitution Part
        x[N-1] = B[N-1]/A[N-1][N-1];

        for(i = N-2; i >= 0; --i){
            float sum = 0;
            for(j = i+1; j < N; ++j){
                sum += A[i][j]*x[j];
            }
            sum = B[i] - sum;
            sum /= A[i][i];
            x[i] = sum;
        }

        DisplayCoefficientMatrix(A);
        DisplayMatrix(B);

        return x;
    }



    //helper methods

    static void exchangeRows(int row, int col, float arr[][], int m){
        float max = arr[row][col];
        int max_row = 0;

        for(int i = row; i < m; ++i){
            if(arr[i][col] > max){
                max = arr[i][col];
                max_row = i;
            }
        }

        float temp[] = arr[row];
        arr[row] = arr[max_row];
        arr[max_row] = temp;

    }

    static void DisplayCoefficientMatrix(float[][] x){
        int i,j,n=x.length;

        System.out.println();
        for(i = 0; i < n; ++i){
            for(j = 0; j < n; ++j){
                System.out.print(x[i][j]+"\t");
            }
            System.out.println();
        }
        System.out.println();
    }

    static void DisplayMatrix(float[] x){
        int i,n=x.length;

        System.out.println();
        for(i = 0; i < n; ++i){
            System.out.println(x[i]+" ");
        }
        System.out.println();
    }
}
