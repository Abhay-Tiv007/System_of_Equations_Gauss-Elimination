import java.io.FileReader;
import java.util.*;

public class MathExt {
    public static void main(String[]args) throws Exception{
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the input file name:");
        String inpFile = sc.nextLine();
        inpFile = inpFile.equals("")?"InputData":inpFile;
        FileReader fr = new FileReader(inpFile+".txt");
        Scanner frInp=new Scanner(fr);

        float a[][],b[];
        int n,i,j;

        n = frInp.nextInt();

        a = new float[n][n];
        b = new float[n];

        for(i = 0; i < n; ++i){
            for(j = 0; j < n; ++j){
                a[i][j] = frInp.nextFloat();
            }
        }

        for(i = 0; i < n; ++i){
            b[i] = frInp.nextFloat();
        }


        new MathExt().GaussElim(a, b, n);


        frInp.close();
        sc.close();
        fr.close();
    }

    float[] GaussElim(float[][] A, float[] B,int N){
        float x[],mult = 0;
        int i,j,k;
        
        x=new float[N];


        //Gauss Elimination Part
        for(i = 0; i< N-1; ++i){
            for(j = i+1; j < N; ++j){
                mult = A[j][i]/A[i][i];
                A[j][i] = 0;
                for(k = i+1; k < N; ++k){
                    A[j][k] -= A[i][k]*mult;
                }
                B[j] -= B[i]*mult;
            }
        }


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
        DisplayMatrix(x);

        return x;
    }



    //helper methods

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
