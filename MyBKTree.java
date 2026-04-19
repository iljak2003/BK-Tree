public class MyBKTree {
    private int levenstein(String word1, String word2){
        int length1 = word1.length() + 1;
        int length2 = word2.length() + 1;

        int[][] levensteinArray = new int[length1][length2];
        int cost = 0;
        for(int i = 0; i < length1; i++){
            for(int j = 0; j < length2; j++){
                if (word1.charAt(i - 1) != word2.charAt(j - 1)) {
                    cost = 1;
                }else{
                    cost = 0;

                }
                if (i == 0 && j == 0) {
                    levensteinArray[i][j] = 0;
                }
                else if(j == 0 && i > 0){
                    levensteinArray[i][j] = i;
                }
                else if(j > 0 && i == 0){
                    levensteinArray[i][j] = j;
                }else{
                    levensteinArray[i][j] = min(
                        levensteinArray[i][j - 1] + 1,
                        levensteinArray[i - 1][j] + 1,
                        levensteinArray[i - 1][j - 1] + cost
                    );
                }
            }
        }



        return levensteinArray[length1][length2];
    }

    private int min(int first, int second, int third){
        return Math.min(Math.min(first, second), third);
    }
}
