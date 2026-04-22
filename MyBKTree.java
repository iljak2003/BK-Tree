import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MyBKTree {
    private Node root;

    public void add(String word) {
        if (word == null || word.isEmpty()) {
            return;
        }

        if (root == null) {
            root = new Node(word);
            return;
        }

        add(root, new Node(word));
    }

    private void add(Node current, Node newNode) {
        int dist = levenstein(newNode.getWord(), current.getWord());

        if (dist == 0) {
            return; 
        }

        Node child = current.getChildes().get(dist);

        if (child == null) {
            current.setChildNode(dist, newNode);
        } else {
            add(child, newNode);
        }
    }

    public List<String> getSimilarWords(String word, int tolerance) {
        List<String> result = new ArrayList<>();

        if (root == null || word == null || tolerance < 0) {
            return result;
        }

        getSimilarWords(root, word, tolerance, result);
        return result;
    }

    private void getSimilarWords(Node current, String word, int tolerance, List<String> result) {
        if (current == null) {
            return;
        }

        int dist = levenstein(current.getWord(), word);

        if (dist <= tolerance) {
            result.add(current.getWord());
        }

        int start = abs(dist - tolerance);
        int end = dist + tolerance;

        for (Map.Entry<Integer, Node> entry : current.getChildes().entrySet()) {
            int childDistance = entry.getKey();

            if (childDistance >= start && childDistance <= end) {
                getSimilarWords(entry.getValue(), word, tolerance, result);
            }
        }
    }

    private int levenstein(String word1, String word2){
        int length1 = word1.length() + 1;
        int length2 = word2.length() + 1;

        int[][] levensteinArray = new int[length1][length2];
        int cost = 0;
        for(int i = 0; i < length1; i++){
            for(int j = 0; j < length2; j++){
                if (i == 0 && j == 0) {
                    levensteinArray[i][j] = 0;
                }
                else if(j == 0 && i > 0){
                    levensteinArray[i][j] = i;
                }
                else if(j > 0 && i == 0){
                    levensteinArray[i][j] = j;
                }else{
                    if (word1.charAt(i - 1) != word2.charAt(j - 1)) {
                    cost = 1;
                    }else{
                        cost = 0;
                    }
                    
                    levensteinArray[i][j] = min(
                        levensteinArray[i][j - 1] + 1,
                        levensteinArray[i - 1][j] + 1,
                        levensteinArray[i - 1][j - 1] + cost
                    );
                }
            }
        }



        return levensteinArray[length1 - 1][length2 - 1];
    }

    private int min(int first, int second, int third){
        return Math.min(Math.min(first, second), third);
    }

    private int abs(int number){
        return Math.abs(number);
    }
}


