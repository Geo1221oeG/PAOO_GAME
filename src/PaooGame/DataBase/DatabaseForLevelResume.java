package PaooGame.DataBase;

import java.io.File;
import java.sql.*;

public class DatabaseForLevelResume {
    public static void MakeDatabase() {//
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");

            // Verifica daca baza de date exista deja
            File dbFile = new File("lastSave.db");
            if (!dbFile.exists()) {
                // daca nu exista atunci se creaza tabela si fisierul
                c = DriverManager.getConnection("jdbc:sqlite:lastSave.db");
                stmt = c.createStatement();
                String sql = "CREATE TABLE IF NOT EXISTS SavedData " +
                        "(LEVEL INT," +
                        "   INT, " +
                        " PLAYER_Y INT, " +
                        " HP INT, " +
                        " NR_AFINE INT, " +
                        " TIME INT)";
                stmt.execute(sql);
                stmt.close();

                insertInitialData(c);
            } else {
                // Dacă fișierul există, nu facem nimic

            }
        } catch (Exception e) {
            System.err.println("Err Sql" + e.getMessage());
            System.exit(0);
        } finally {
            // inchiderea conexiunii si a instr statement
            try {
                if (stmt != null)
                    stmt.close();
                if (c != null)
                    c.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void insertData(int level, int playerX, int playerY, int hp, int nr_afine, int time) {
        deleteLastData();
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            // conctarea la baza de date
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:lastSave.db");

            // inserarea datelor in baza de date
            String sql = "INSERT INTO SavedData (LEVEL, PLAYER_X, PLAYER_Y, HP, NR_AFINE,TIME) VALUES (?, ?, ?, ?, ?,?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, level);
            preparedStatement.setInt(2, playerX);
            preparedStatement.setInt(3, playerY);
            preparedStatement.setInt(4, hp);
            preparedStatement.setInt(5, nr_afine);
            preparedStatement.setLong(6, time);

            preparedStatement.executeUpdate();


        } catch (ClassNotFoundException e) {
            // Tratare excepții
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private static void deleteLastData() {
        Connection connection = null;
        Statement statement = null;

        try {
            //conectarea la baza de date
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:lastSave.db");

            // stergerea datelor din tabela
            statement = connection.createStatement();
            String sql = "DELETE FROM SavedData";
            statement.executeUpdate(sql);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private static void insertInitialData(Connection connection) {
        deleteLastData();
        insertData(1, 2 * 64, 41 * 64, 600, 0, 0);
    }

    public static int getPlayerX() {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {

            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:lastSave.db");
            statement = connection.createStatement();
            String sql = "SELECT PLAYER_X FROM SavedData LIMIT 1"; //luarea x-ului personajului
            resultSet = statement.executeQuery(sql);

            // verifica daca existe rezultate in tabela
            if (resultSet.next()) {
                return resultSet.getInt("PLAYER_X");
            } else {
                return 2 * 64; //daca nu exista rezultate se returneaza pozitia de star
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {//inchiderea conexiunii,statemet-ului si rezultatelor
            try {
                if (resultSet != null)
                    resultSet.close();
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 2 * 64;
    }

    public static int getPlayerY() {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:lastSave.db");


            statement = connection.createStatement();


            String sql = "SELECT PLAYER_Y FROM SavedData LIMIT 1";
            resultSet = statement.executeQuery(sql);


            if (resultSet.next()) {
                return resultSet.getInt("PLAYER_Y");
            } else {
                return 41 * 64; //pozitia initiala in caz de nu se gaseste nicio valoarea in tabela
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {// inchiderea conexiunii si a resurselor
            try {
                if (resultSet != null)
                    resultSet.close();
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return 41 * 64;
    }

    public static int getLevel() {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            // conectare la baza de date
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:lastSave.db");

            // creare instructiune
            statement = connection.createStatement();

            //executarea inst pentru a cauta val lui level
            String sql = "SELECT LEVEL FROM SavedData LIMIT 1";
            resultSet = statement.executeQuery(sql);

            // verificare daca exista rezultate
            if (resultSet.next()) {
                // Extrage valoarea coloanei PLAYER_X
                int lv = resultSet.getInt("LEVEL");
                if (lv < 0 && lv > 3) //daca rezultatul nu este unul corect
                    return 1; //se ia de la inceput jocul
                else return lv;

            } else {
                return 1; //daca nu se gasesc rezultate se ia de la inceput jocul -> level 1
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {// inchiderea conexiunii si a resurselor
            try {
                if (resultSet != null)
                    resultSet.close();
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return 1;
    }

    public static int getHp() {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            // conectare la baza de date
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:lastSave.db");

            // creare instructiune
            statement = connection.createStatement();

            //executarea inst pentru a cauta val lui hp
            String sql = "SELECT HP FROM SavedData LIMIT 1";
            resultSet = statement.executeQuery(sql);

            // Verificare daca exista rezultate
            if (resultSet.next()) {
                return resultSet.getInt("HP");

            } else {
                return 100;
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {//inchidere conexiunii si a resurselor
            try {
                if (resultSet != null)
                    resultSet.close();
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return 100;
    }

    public static int getNrAfine() {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            // Conectare la baza de date
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:lastSave.db");

            // creare instructiune
            statement = connection.createStatement();

            // Executare interogare SQL pentru a selecta PLAYER_X din prima linie
            String sql = "SELECT NR_AFINE FROM SavedData LIMIT 1";
            resultSet = statement.executeQuery(sql);

            // vf daca exista rezultate
            if (resultSet.next()) {
                // Extrage val din coloana nr_afine
                return resultSet.getInt("NR_AFINE");
            } else {
                return 0;
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {// inchiderea conexiunii si a resurselor
            try {
                if (resultSet != null)
                    resultSet.close();
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return 0;
    }

    public static int getTime() { //acelas principiu ca la celelalte get-ere
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:lastSave.db");


            statement = connection.createStatement();


            String sql = "SELECT TIME FROM SavedData LIMIT 1";
            resultSet = statement.executeQuery(sql);


            if (resultSet.next()) {
                return resultSet.getInt("TIME");
            } else {
                return 0;
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null)
                    resultSet.close();
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return 0;
    }
}