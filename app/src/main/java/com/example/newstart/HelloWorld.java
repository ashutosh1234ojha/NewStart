package com.example.newstart;

/**
 * Created by Ashutosh Ojha on 29,January,2022
 */
public class HelloWorld {
//    public static void main(String[] args) {
//        //System.out.println("Hello, World!");
//        find(4);
//    }
    public static void find( int n){
        int count =0;
        while(count!=4){
            count=0;
            int [][] arr = new int [n][n];

            for(int i=0;i<n;i++){
                for(int j=0;j<n;j++){
                    if(arr[i][j]!=2&&arr[i][j]!=1){
                        arr[i][i]=2;
                        count=count+1;
                        mark(arr,i,j,n);
                    }
                }
            }
            if(count==4){
                for(int i=0;i<n;i++){
                    System.out.println();

                    for(int j=0;j<n;j++){

                        System.out.print(arr[i][j]+" ");

                    }}
            }
        }

    }
    private static void mark(int [][] arr,int i,int j,int n){
        for(int top=i-1;top>=0;top--){
            arr[top][j]=1;
        }
        for(int bottom=i+1;bottom<n;bottom++){
            arr[bottom][j]=1;
        }
        for(int left=j-1;left>=0;left--){
            arr[i][left]=1;
        }
        for(int right=i+1;right<n;right++){
            arr[i][right]=1;
        }
        for(int dr=i+1,dc=j+1;(dr<n&&dc<n);dr++,dc++){
            arr[dr][dc]=1;
        }
        for(int dr=i-1,dc=j-1;(dr>=0&&dc>=0);dr--,dc--){
            arr[dr][dc]=1;
        }
    }
}
