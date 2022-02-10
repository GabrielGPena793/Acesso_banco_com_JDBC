package application;

import db.DB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Programm {
    public static void main(String[] args) {

        Connection conn = DB.getConnection();
        Statement st = null; // Statement serve para montar um comando sql para ser executado
        ResultSet rs = null; // ResultSet contem os dados armazenados em forma de uma tabela

        try {
            conn = DB.getConnection(); // faz a conecção com o banco

            st = conn.createStatement(); // instancia um objeto do tipo Statement

            rs = st.executeQuery("select * from department"); // faz a consulta sql

            //percorre o ResultSet imprimindo os valores da tabela
            while (rs.next()) {
                System.out.println(rs.getInt("Id") + ", " + rs.getString("Name"));
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
            DB.closeConnection();
        }

    }


}
