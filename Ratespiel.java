/* Variante: schwer */

public class Ratespiel {

   private BinaryTree<String> wissensbaum;

   public void starteRateRunde() {
      BinaryTree<String> aktuell = wissensbaum;
      boolean zustimmung;
      
      while (! istBlatt(aktuell)) {
         zustimmung = InOut.readBoolean(aktuell.getContent() + " (j/n): ");      
         if (zustimmung) {

         }
         else {

         }
      }
            
      zustimmung = InOut.readBoolean(aktuell.getContent() + " (j/n): ");      
      if (zustimmung) {
         System.out.println("Fertig!!");
      }
      else {
         String neuesTier = InOut.readString("Wie heisst die neue Person? ");
         String neueFrage = InOut.readString("Gib eine Ja-Frage für die neue Person ein: ");



         System.out.println("Die Wissensbasis wurde ergänzt!");
      }
   }

   public void trageGrundwissenEin() {
      BinaryTree<String> b1 = new BinaryTree("Homer Simpson");
      BinaryTree<String> b2 = new BinaryTree("Fabian Klos");
      BinaryTree<String> b3 = new BinaryTree("Fussballgott?", b1, b2);
      BinaryTree<String> b4 = new BinaryTree("Hermine Granger");
      wissensbaum = new BinaryTree<String>("Männlich?", b3, b4);
   }

   private boolean istBlatt(BinaryTree<String> b) {
      return (true);
   }
    
   public static void main (String[] args) {
      Ratespiel r = new Ratespiel();
      r.trageGrundwissenEin();
      
      boolean weiter = true;
      while (weiter) { 
         r.starteRateRunde();
         weiter = InOut.readBoolean("\nNeue Runde? (j/n) ");
      }
   }        
}

