package com.company;

import java.sql.*;

public class Main {

    public static void main(String[] args) {




        Connection conDB=null;
        Statement statement =null;
        ResultSet query=null;

        try{
            conDB=DriverManager.getConnection("jdbc:mysql://localhost:3306/Goose?useUnicode=true&serverTimezone=UTC","root","");

            statement=conDB.createStatement();
            query=statement.executeQuery("Select * from UserPass");

            while(query.next()){
                System.out.println(query.getString("User"));
            }


        }catch (SQLException ex){
            ex.printStackTrace();
        }
        finally{
            try{query.close();}
            catch(Exception e){}

            try{statement.close();}
            catch(Exception e){}

            try{conDB.close();}
            catch(Exception e){}

        }

        final Runner comandCatcher = new Runner(13);
        // comandCatcher.Start();
        System.out.println("start2");
        new Thread(comandCatcher).start();



     //   System.out.print("goodboy");

    }
}
