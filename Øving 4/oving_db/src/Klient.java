/**
 * Klient.java
 *
 * Øving 4, vår 2014
 */

import java.sql.*;
import static javax.swing.JOptionPane.*;
class Klient {
	public static void main(String[] args) throws Exception {
		String databasedriver = "org.apache.derby.jdbc.ClientDriver"; // Kopler til tjener, ligger i derbyclient.jar
		Class.forName(databasedriver); // Laster inn driverklassen
		
		String databasenavn = "jdbc:derby://localhost:1527/oving_db;user=db;password=db;create=true";
		Connection forbindelse = DriverManager.getConnection(databasenavn);
		Statement setning = forbindelse.createStatement();
		
		String ISBN = showInputDialog("Hva er bokas ISBN?");
                
                ResultSet res = setning.executeQuery("select forfatter, tittel from boktittel where isbn = " + "'" + ISBN + "'");
                
                res.next();
                String forfatter = res.getString("forfatter");
                String tittel = res.getString("tittel");
                res.close();
                
                ResultSet res2 = setning.executeQuery("select count(*) antall from eksemplar where isbn = " + "'" + ISBN + "'");
                
                res2.next();
                int num = res2.getInt("antall");
                res2.close();
                
                showMessageDialog(null, "Forfatter: " + forfatter + " Tittel: " + tittel + " Antall eksemplarer: " + num);
                res.close();
                res2.close();
                setning.close();
                forbindelse.close();
	}
}