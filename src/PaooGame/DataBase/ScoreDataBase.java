package PaooGame.DataBase;

import java.awt.*;
import java.sql.*;

public class ScoreDataBase {
    public static void createDatabase() { //pentru crearea bazei de date pentru tinerea primelor celor mai bune 5 scoruri
        Connection connection = null;
        Statement statement = null;

        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:scores.db");
            statement = connection.createStatement();

            String sql = "CREATE TABLE IF NOT EXISTS Scores (ID INTEGER PRIMARY KEY AUTOINCREMENT, Score INTEGER)";
            statement.executeUpdate(sql);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void insertScore(int score) { //pentru adaugarea unor noi valori in tabela
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            //conexiune
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:scores.db");
            //statement, adaugarea elementului
            String sql = "INSERT INTO Scores (Score) VALUES (?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, score);
            preparedStatement.executeUpdate();

            // Verificam daca exista mai mult de 5 scoruri in tabel
            if (countScores() > 5) {
                // Ștergem cel mai lente inregistrari (cele mai mici scoruri)
                deleteHighestScore();
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void DrawTopScores(Graphics g) { //afisarea celor mai bune 5 scoruri(cele mai putine secunde)
        int topScores;
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:scores.db");

            String sql = "SELECT Score FROM Scores ORDER BY Score ASC LIMIT 5";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            int i=0; // pentru a scrie mai jos
            while (resultSet.next()) {

                //setari pentru afisare
                Font fnt = new Font("DialogInput", Font.BOLD, 40);
                g.setFont(fnt);
                g.setColor(Color.white);
                //desenarea propriu-zisa
                g.drawString(Integer.toString(resultSet.getInt("Score"))+"s", 460, 230+i);  i+=40;
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private static int countScores() { //numararea inregistrarilor
        int count = 0;
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:scores.db");

            String sql = "SELECT COUNT(*) AS Count FROM Scores";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            if (resultSet.next()) {
                count = resultSet.getInt("Count");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return count;
    }

    private static void deleteHighestScore() { //stergerea celor mai mari scoruri(cele mai slabe)
        Connection connection = null;
        Statement statement = null;

        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:scores.db");

            String sql = "DELETE FROM Scores WHERE ID IN (SELECT ID FROM Scores ORDER BY Score DESC LIMIT 1)";
            statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        catch ( SQLException e)
        {
            e.printStackTrace();
        }
        finally {
            try {
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
