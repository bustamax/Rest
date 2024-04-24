package tests.board.DB;

import org.testng.annotations.Test;

import java.sql.*;
public class DBTest {

    static String sqlSelect = "SELECT * FROM users";

    static String connectionUrl = "jdbc:mysql://99.76.150.229/db_ann_test?serverTimeZone=UTC";

    public static String getUser(){

        try {

            Connection conn = DriverManager.getConnection(connectionUrl,"db_ann_test", "NIad27D6SYlc29484zzzz");
            PreparedStatement ps = conn.prepareStatement(sqlSelect);

            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                String name = rs.getString("name");
                String lastname = rs.getString("surname");

                return name + lastname;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
           return "error" + throwables.getMessage();
        }
        return "null";

    }

    @Test
    public void test(){
       String name =  getUser();
        System.out.println(name);
    }
}
