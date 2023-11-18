/* Variante: schwer */

import database.DatabaseConnector;
import database.QueryResult;

import java.util.Arrays;

public class Ratespiel {

    private BinaryTree<String> wissensbaum;
    private DatabaseConnector connector;
    
    
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

    private void trageGrundwissenEin() {
        BinaryTree<String> b1 = new BinaryTree("Niklas Weiser");
        BinaryTree<String> b2 = new BinaryTree("Julian");
        BinaryTree<String> b3 = new BinaryTree("Lehrer?", b1, b2);
        BinaryTree<String> b4 = new BinaryTree("Angela Merkel");
        BinaryTree<String> b5 = new BinaryTree("Männlich?", b3, b4);
        BinaryTree<String> b6 = new BinaryTree("Homer");
        wissensbaum = new BinaryTree("Reale Person",b5,b6);
    }
    
    private void ladeGrundwissenAusDatenbank(){
        connector.executeStatement("SELECT * FROM wissensbasis");
        
        QueryResult result = connector.getCurrentQueryResult();
        System.out.println(connector.getErrorMessage());
        System.out.println(Arrays.toString(result.getData()[0]));
    }
    
    private void connectToDatabase() { 
        connector = new DatabaseConnector("meineseite.ddns.net", "3306", "akinator", "akinator", "rotanika");
        ladeGrundwissenAusDatenbank();
    }

    private boolean istBlatt(BinaryTree<String> b) {
        return b.getLeftTree().isEmpty() && b.getRightTree().isEmpty();
    }

    public static void main (String[] args) {
        System.out.println();
        Ratespiel r = new Ratespiel();
        r.trageGrundwissenEin();
        r.connectToDatabase();
        boolean weiter = true;
        while (weiter) { 
            r.starteRateRunde();
            weiter = InOut.readBoolean("\nNeue Runde? (j/n) ");
        }
    }        
}

