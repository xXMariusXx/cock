package hello;

import javax.json.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Controller {

    public ArrayList gibZutatenliste()
    {
        MySQLAccess mySQLAccess = new MySQLAccess();

        String sql = "SELECT * FROM zutaten";

        ArrayList zutaten = new ArrayList();

        try {
            PreparedStatement statement = mySQLAccess.connect().prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next())
            {
                boolean b = false;
                if (resultSet.getInt("alkohol") > 0)
                    b = true;
                zutaten.add(new Zutat(resultSet.getInt("id"),resultSet.getString("name"),b));

            }
            System.out.println(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            mySQLAccess.disconnect();
        }

        return zutaten;
    }
}
