package com.company;

import java.sql.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
         mainMenu();
    }

    public static void mainMenu(){
        System.out.println("Select below number to perform the action");
        System.out.println("1. Create new user\n2. View existing users\n3. Update existing user\n4. Delete user");

        Scanner scan = new Scanner(System.in);

        int selection = scan.nextInt();

        if(selection == 1){
            createUser();
        }if(selection == 2){
            viewUser();
        }if (selection == 3){
            updateUser2();
        }if (selection == 4){
            deleteUser();
        }
    }

    public static void createUser(){
        String JDBCURL = "jdbc:mysql://localhost:3308/ABCCollege";
        String DBUserName = "root";
        String DBPass = "";

        try {
            Connection connection = DriverManager.getConnection(JDBCURL,DBUserName,DBPass);
            Scanner scan = new Scanner(System.in);

            System.out.print("Enter new user's name? ");
            String studentName = scan.nextLine();

            System.out.print("Enter "+studentName+"'s email? ");
            String email = scan.nextLine();

            System.out.print("Enter "+studentName+"'s address? ");
            String address = scan.nextLine();

            String sql = "INSERT INTO student(studentName,email,address)"
                    + "VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,studentName);
            statement.setString(2,email);
            statement.setString(3,address);

            int rows = statement.executeUpdate();

            if(rows > 0){
                System.out.println("New record has been added successfully!");
            }
            connection.close();

        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    public static void createUser2(){
        String JDBCURL = "jdbc:mysql://localhost:3308/ABCCollege";
        String DBUserName = "root";
        String DBPass = "";

        try {
            Connection connection = DriverManager.getConnection(JDBCURL,DBUserName,DBPass);
            Scanner scan = new Scanner(System.in);

            System.out.print("Enter new user's name? ");
            String studentName = scan.nextLine();

            System.out.print("Enter "+studentName+"'s email? ");
            String email = scan.nextLine();

            System.out.print("Enter "+studentName+"'s address? ");
            String address = scan.nextLine();

            String sql = "INSERT INTO student(studentName,email,address) VALUES ('"+studentName+"', '"+email+"', '"+address+"')";

            Statement statement = connection.createStatement();

            int rows = statement.executeUpdate(sql);

            if (rows > 0) {
                System.out.println("Added!");
            }

            connection.close();

        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    public static void viewUser(){
        String JDBCURL = "jdbc:mysql://localhost:3308/ABCCollege";
        String DBUserName = "root";
        String DBPass = "";

        try {
            Connection connection = DriverManager.getConnection(JDBCURL,DBUserName,DBPass);
            String sql = "SELECT * FROM student";
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);

            while (result.next()){
                String id = result.getString("id");
                String studentName = result.getString("studentName");
                String email = result.getString("email");
                String address = result.getString("address");

                System.out.println(id+". "+studentName+" "+email+" "+address);
            }
            connection.close();

        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }



    public static void deleteUser(){
        String JDBCURL = "jdbc:mysql://localhost:3308/ABCCollege";
        String DBUserName = "root";
        String DBPass = "";

        try {
            Connection connection = DriverManager.getConnection(JDBCURL,DBUserName,DBPass);

            Scanner scan = new Scanner(System.in);
            viewUser();
            System.out.println("Select the id of the user to delete? ");
            int id = scan.nextInt();

            String sql = "DELETE FROM student WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1,id);

            int rows = statement.executeUpdate();

            if(rows > 0){
                System.out.println("id "+id+" has been deleted!");
            }else {
                System.out.println("Table already empty!");
            }

            connection.close();
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    public static void updateUser(){
        String JDBCURL = "jdbc:mysql://localhost:3308/ABCCollege";
        String DBUserName = "root";
        String DBPass = "";

        try {
            Connection connection = DriverManager.getConnection(JDBCURL,DBUserName,DBPass);

            String studentName = "Peter";
            String email = "peter@gmail.com";
            String address = "Kandy";
            int userID = 1;

            String sql = "UPDATE student SET studentName=?,email=?,address=? WHERE id=?";

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1,studentName);
            statement.setString(2,email);
            statement.setString(3,address);
            statement.setInt(4,userID);
            int rows = statement.executeUpdate();

            if (rows > 0){
                System.out.println(studentName+"'s record has been updated!");
            }

            connection.close();
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    public static void updateUser2(){
        String JDBCURL = "jdbc:mysql://localhost:3308/ABCCollege";
        String DBUserName = "root";
        String DBPass = "";

        try {
            Connection connection = DriverManager.getConnection(JDBCURL,DBUserName,DBPass);

            viewUser();

            Scanner scan = new Scanner(System.in);
            System.out.println("Select the id to update? ");
            int id = scan.nextInt();

            System.out.println("Enter new email? ");
            String email = scan.nextLine();
            String email2 = scan.nextLine();

            System.out.println("Enter new name? ");
            String studentName = scan.nextLine();

            System.out.println("Enter new address");
            String address = scan.nextLine();

            String sql = "UPDATE student SET studentName='"+studentName+"',email='"+email2+"',address='"+address+"' WHERE id="+id+"";

            Statement statement = connection.createStatement();

            int rows = statement.executeUpdate(sql);

            if (rows > 0) {
                System.out.println(studentName+"'s record has been updated!");
            }
            connection.close();

        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }
}
