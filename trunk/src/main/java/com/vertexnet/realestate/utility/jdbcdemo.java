package com.vertexnet.realestate.utility;
import java.sql.*;
import javax.sql.*;

public class jdbcdemo{

public static void main(String args[]){
String dbtime;
String dbUrl = "jdbc:mysql://localhost/real_estate_net";
String dbClass = "com.mysql.jdbc.Driver";
String query = "Select * FROM Customer";

try {

Class.forName("com.mysql.jdbc.Driver");
Connection con = DriverManager.getConnection (dbUrl, "root", "root");
Statement stmt = con.createStatement();
ResultSet rs = stmt.executeQuery(query);

while (rs.next()) {
dbtime = rs.getString(1);
System.out.println(dbtime);
} //end while

con.close();
} //end try

catch(ClassNotFoundException e) {
e.printStackTrace();
}

catch(SQLException e) {
e.printStackTrace();
}

}  //end main

}  //end class