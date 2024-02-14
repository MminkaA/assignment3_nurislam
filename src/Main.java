import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception{
        String URL = "jdbc:postgresql://localhost:5432/nurissss";
        String username = "postgres";
        String password = "12345";
        Connection connection = DriverManager.getConnection(URL , username , password);
        Statement stmt = connection.createStatement();
        Scanner scan =new Scanner(System.in);
        createtable(connection);
        while(true) {
            System.out.println("---MENU---");
            System.out.println("1. View all Smartphones");
            System.out.println("2. Add Smartphone");
            System.out.println("3. Delete Smartphone");
            System.out.println("4. Update Smartphone");
            System.out.println("5. EXIT");

            int choose = scan.nextInt();

            if(choose == 1) {
                allSmart(connection);
            }

            else if(choose == 2) {
                String s = scan.next();
                addSmart(connection , s);
            }

            else if(choose == 3) {
                int id = scan.nextInt();
                delSmart(connection , id);
            }
            else if(choose == 4) {
                String s = scan.next();
                int id = scan.nextInt();
                updSmart(connection,id,s);
            }
            else if(choose == 5) {
                break;
            }

        }

    }
    public static void allSmart(Connection connection) throws Exception {
        Statement stmt = connection.createStatement();
        String q = "select * from users order by id asc";
        ResultSet results = stmt.executeQuery(q);

        while (results.next() == true) {
            System.out.print("Id: " + results.getInt(1));
            System.out.print("Name: " + results.getString(2));
            System.out.println();

        }
    }
    public static void createtable(Connection connection) throws Exception {
        Statement stmt = connection.createStatement();
        String q = "create table if not exists users (id serial primary key,name varchar(20),sname varchar(20),gender varchar(20));";
        ResultSet results = stmt.executeQuery(q);


    }

    public static void addSmart(Connection connection , String Name) throws Exception{
        PreparedStatement pst = connection.prepareStatement("insert into users(name) values (?)");
        pst.setString(1, Name);
        pst.executeUpdate();
    }

    public static void delSmart(Connection connection , int Id) throws Exception{
        PreparedStatement pst = connection.prepareStatement("delete from users where id = ?");
        pst.setInt(1, Id);
        pst.executeUpdate();
    }

    public static void updSmart(Connection connection , int Id , String Name) throws Exception{
        PreparedStatement pst = connection.prepareStatement("update users set name = ? where id = ?");
        pst.setInt(2, Id);
        pst.setString(1,Name);
        pst.executeUpdate();
    }


}