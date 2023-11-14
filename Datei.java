import java.io.*;


/**
 * Eine Klasse für den vereinfachten Zugriff auf Textdateien.
 *
 * @author Dop
 * @version $Revision: 1.3 $
 */
public class Datei {
    //~ Instance variables ---------------------------------------------------------------

    private BufferedReader reader;
    private BufferedWriter writer;
    private boolean modusLesen;
    private boolean isOpen;

    //~ Constructors ---------------------------------------------------------------------

    /**
     * Creates a new Datei object.
     *
     * @param pDateiname DOCUMENT ME!
     */
    public Datei(
        String dateiname,
        boolean modusLesen) {
        this.modusLesen = modusLesen;

        try {
            if (modusLesen)
                reader = new BufferedReader(new FileReader(dateiname));
            else
                writer = new BufferedWriter(new FileWriter(dateiname));
            isOpen = true;
        }
        catch (Exception e) {
            System.out.println("Dateifehler: " + e.getMessage());
            isOpen = false;
        }
    }

    //~ Methods --------------------------------------------------------------------------

    public boolean isOpen() {
      return this.isOpen;
    }
    
    /**
     * DOCUMENT ME!
     */
    public void close() {
        try {
            if (reader != null)
                reader.close();

            if (writer != null) {
                writer.flush();
                writer.close();
            }
            isOpen = false;
        }
        catch (Exception e) {;
        }
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String readLine() {
        if (!isOpen) {
            System.out.println("Datei: Die Datei konnte nicht ge�ffnet werden!");

            return null;
        }

        if (!modusLesen) {
            System.out.println("Datei: readLine im Modus 'Schreiben' nicht m�glich");

            return null;
        }

        try {
            return reader.readLine();
        }
        catch (Exception e) {
            System.out.println("Dateifehler: " + e.getMessage());
            return null;
        }
    }


    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public double readLineAsDouble() {
        if (!isOpen) {
            System.out.println("Datei: Die Datei konnte nicht ge�ffnet werden!");

            return -1;
        }

        if (!modusLesen) {
            System.out.println(
                "Datei: readLineAsDouble im Modus 'Schreiben' nicht m�glich");

            return -1;
        }

        try {
            return Double.parseDouble(reader.readLine());
        }
        catch (Exception e) {
            System.out.println("Dateifehler: " + e.getMessage());

            return -1;
        }
    }


    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public int readLineAsInt() {
        if (!isOpen) {
            System.out.println("Datei: Die Datei konnte nicht ge�ffnet werden!");

            return -1;
        }

        if (!modusLesen) {
            System.out.println("Datei: readLineAsInt im Modus 'Schreiben' nicht m�glich");

            return -1;
        }

        try {
            return Integer.parseInt(reader.readLine());
        }
        catch (Exception e) {
            System.out.println("Dateifehler: " + e.getMessage());

            return -1;
        }
    }


// =======================================================================
    /**
     * DOCUMENT ME!
     *
     * @param text DOCUMENT ME!
     */
    public void write(String text) {
        if (!isOpen) {
            System.out.println("Datei: Die Datei konnte nicht ge�ffnet werden!");

            return;
        }

        if (modusLesen) {
            System.out.println("Datei: write im Modus 'Lesen' nicht m�glich");

            return;
        }

        try {
            writer.write(text);
            writer.flush();
        }
        catch (Exception e) {
            System.out.println("Dateifehler: " + e.getMessage());
        }
    }


    /**
     * DOCUMENT ME!
     *
     * @param zeile DOCUMENT ME!
     */
    public void writeLine(String zeile) {
        if (!isOpen) {
            System.out.println("Datei: Die Datei konnte nicht ge�ffnet werden!");

            return;
        }

        if (modusLesen) {
            System.out.println("Datei: writeLine im Modus 'Lesen' nicht m�glich");

            return;
        }

        try {
            writer.write(zeile);
            writer.newLine();
            writer.flush();
        }
        catch (Exception e) {
            System.out.println("Dateifehler: " + e.getMessage());
        }
    }
}
