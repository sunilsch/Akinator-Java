
/**
 * Beschreiben Sie hier die Klasse Node.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class Node
{
    // Instanzvariablen - ersetzen Sie das folgende Beispiel mit Ihren Variablen
    private int id;
    private int parentId;
    private boolean left;
    private String data;
    private BinaryTree tree;

    /**
     * Konstruktor für Objekte der Klasse Node
     */
    public Node(int id, int parentId, int left, String data)
    {
        this.id = id;
        this.parentId = parentId;
        this.left = (left == 1);
        this.data = data;
        tree = new BinaryTree(data);
    }
    public int getId(){
        return this.id;
    }
    public int getParentId(){
        return this.parentId;
    }
    public boolean isLeft(){
        return left;
    }
    public String getData(){
        return data;
    }
    public BinaryTree getTree(){
        return tree;
    }
}
