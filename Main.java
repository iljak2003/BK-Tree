public class Main{
    public static void main(String[] args){
        
        int[][] d = new int[10 + 1][11 + 1];


        for(int i = 0; i < 10 + 1; i++){
            d[i][0] = i;
        }

        for(int j = 0; j < 11 + 1; j++){
            d[0][j] = j;
        }

        for(int i = 0; i < 11; i++){
            for(int j = 0; j < 12; j++){
                System.out.print(d[i][j]);
            }
            System.out.println();
        }
    }

}