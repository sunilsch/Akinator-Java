/* Variante: schwer */

import database.DatabaseConnector;
import database.QueryResult;

import java.util.Arrays;
import java.util.Comparator;

public class Ratespiel {

    private BinaryTree<String> wissensbaum;
    private DatabaseConnector connector;
    private int highestId;
    
    private void starteRateRunde() {
        BinaryTree<String> aktuell = wissensbaum;
        boolean zustimmung;

        while (!istBlatt(aktuell)) {
            zustimmung = InOut.readBoolean(aktuell.getContent() + " (j/n): ");      
            if (zustimmung) {
                aktuell = aktuell.getLeftTree();
            }
            else {
                aktuell = aktuell.getRightTree();
            }
        }

        zustimmung = InOut.readBoolean("Ist die gesuchte Person: " + aktuell.getContent() + " (j/n): ");      
        if (zustimmung) {
            System.out.println("Fertig!!");
        }
        else {
            String neuePerson = InOut.readString("Wie heisst die neue Person? ");
            boolean finished = false;
            String aktuellePerson = aktuell.getContent();
            while(!finished){
                String neueFrage = InOut.readString("Gib eine Ja-Frage für die neue Person ein: ");
                boolean auch = InOut.readBoolean("Gilt dies auch für diese Person: "+aktuellePerson+ " (j/n)");
                if(!auch){
                    aktuell.setContent(neueFrage);
                    aktuell.setLeftTree(new BinaryTree(neuePerson));
                    aktuell.setRightTree(new BinaryTree(aktuellePerson));
                    finished = true;
                }
            }
            System.out.println("Die Wissensbasis wurde ergänzt!");
        }
    }

    /*private void trageGrundwissenEin() {
        BinaryTree<String> b1 = new BinaryTree("Niklas Weiser");
        BinaryTree<String> b2 = new BinaryTree("Julian");
        BinaryTree<String> b3 = new BinaryTree("Lehrer?", b1, b2);
        BinaryTree<String> b4 = new BinaryTree("Angela Merkel");
        BinaryTree<String> b5 = new BinaryTree("Männlich?", b3, b4);
        BinaryTree<String> b6 = new BinaryTree("Homer");
        wissensbaum = new BinaryTree("Reale Person",b5,b6);
    }*/
    
    private void ladeGrundwissenAusDatenbank(){
        connector.executeStatement("SELECT * FROM wissensbasis");
        
        QueryResult result = connector.getCurrentQueryResult();
        String[][] data = result.getData();
        System.out.println(connector.getErrorMessage());
        Node[] nodes = new Node[data.length];
        for(int i = 0; i < data.length; i++)
            nodes[i] = new Node(Integer.valueOf(data[i][0]), Integer.valueOf(data[i][1]), Integer.valueOf(data[i][2]), data[i][3]);
        Arrays.sort(nodes, new Comparator<Node>() {
            public int compare(Node b1, Node b2) {
                return Integer.compare(b1.getId(), b2.getId());
            }
        });
        highestId = nodes[nodes.length-1].getId();
        for(int i = 0; i < nodes.length; i++){
            // set parent
            int parentId = nodes[i].getParentId();
            if(parentId == -1) continue;
            boolean left = nodes[i].isLeft();
            if(left) nodes[parentId].getTree().setLeftTree(nodes[i].getTree());
            else nodes[parentId].getTree().setRightTree(nodes[i].getTree());
        }
        wissensbaum = nodes[0].getTree();
    }
    
    private void connectToDatabase() { 
        connector = new DatabaseConnector("meineseite.ddns.net", "3306", "akinator", "akinator", "rotanika");
    }

    private boolean istBlatt(BinaryTree<String> b) {
        return b.getLeftTree().isEmpty() && b.getRightTree().isEmpty();
    }

    public static void main (String[] args) {
        System.out.println();
        Ratespiel r = new Ratespiel();
        r.connectToDatabase();
        r.ladeGrundwissenAusDatenbank();
        r.connectToDatabase();
        boolean weiter = true;
        while (weiter) { 
            r.starteRateRunde();
            weiter = InOut.readBoolean("\nNeue Runde? (j/n) ");
        }
    }        
}

