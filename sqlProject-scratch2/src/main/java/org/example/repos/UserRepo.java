package org.example.repos;

import org.example.utils.CRUDDaoInterface;
import org.example.models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepo implements CRUDDaoInterface<User> {
   public Connection connection;

    public UserRepo(){


                String url = "jdbc:postgresql://localhost:5432/postgres?currentSchema=flashcard";
                String username = "postgres";
                String password = "Ac1bd2ce3@212";

                try {
                  connection = DriverManager.getConnection(url, username, password);
                    System.out.println(connection.getSchema());
                }
                catch(SQLException sqlException){
                    sqlException.printStackTrace();
                    System.out.println(sqlException.getMessage());
                }
            }




    @Override
    public int create(User user) {
        //inserting a new user in the table
        String sql = "INSERT INTO users (id, first_name, last_name, email, pass_word) VALUES(default,?,?,?,?); ";
        try{
            PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, user.getFirstName());
            pstmt.setString(2,user.getLastName());
            pstmt.setString(3, user.getEmail());
            pstmt.setString(4, user.getPassword());

            // execute the SQL statement
            pstmt.executeUpdate();

            //
            ResultSet rs = pstmt.getGeneratedKeys();
            //place the cursor on the next set
            rs.next();

            return rs.getInt("id");
        }
        catch(SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }


        return 0;
    }

    @Override
    public List<User> getAll() {

        List<User> users = new ArrayList<>();

        try{
            String sql = "SELECT * FROM users";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            while(rs.next()){

                User user = new User();
                user.setId(rs.getInt("id)"));
                user.setFirstName(rs.getString("first_name"));
                user.setLastName(rs.getString("last_name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("pass_word"));

                users.add(user);

            }
        return users;
        }
        catch(SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }

        return null;
    }

    @Override
    public User getById(int id) {

        try{
            String sql = "SELECT * FROM users WHERE id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt( 1,id);
            ResultSet rs = pstmt.executeQuery();

            User user = new User();
            while(rs.next()){

                user.setId(rs.getInt("id)"));
                user.setFirstName(rs.getString("first_name"));
                user.setLastName(rs.getString("last_name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("pass_word"));


            }
        return user;
        }
        catch(SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }

        return null;
    }

    @Override
    public User update(User user) {

        try{
            String sql = "UPDATE users users SET email = ? WHERE id = ?";

            PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = pstmt.executeQuery();
            pstmt.setString(1,user.getEmail());
            pstmt.setInt(2,user.getId());


            while(rs.next()){

                user.setEmail(rs.getString("email"));

            }
            return user;

        }
        catch(SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }


        return null;


    }

    @Override
    public boolean delete(User user) {
        return false;
    }
}