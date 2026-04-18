import java.util.HashMap;
import java.util.Map;

public class Node {
    private Map<Integer, Node> childNodes;
    private String word;


    public Node(String word){
        this.word = word;
        this.childNodes = new HashMap<>();
    } 
    
    public String getWord(){
        return word;
    }

    public void setChildNode(int distance, Node child){
        childNodes.put(distance, child);
    }

    public Map<Integer, Node> getChildes(){
        return childNodes;
    }
}