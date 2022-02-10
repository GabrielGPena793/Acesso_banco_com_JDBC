package application;

import db.DB;

import java.sql.*;
import java.text.SimpleDateFormat;

public class Programm {
    public static void main(String[] args) {

        Connection conn = null;
        PreparedStatement st = null;

        try {
            conn = DB.getConnection(); // iniciando conecção

            //preparando a declaração ao banco
            st = conn.prepareStatement(
                        "UPDATE seller " +
                            "SET BaseSalary = BaseSalary + ? " +
                            "WHERE " +
                            "(DepartmentId = ?)");

            //setando os valores a serem alterados no lugar das " ? "
            st.setDouble(1,  200.00);
            st.setInt(2, 2);

            int rowsAffected = st.executeUpdate(); // executando a consulta

            System.out.println("Done! Rows affected: " + rowsAffected);

        }catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            DB.closeStatement(st);
            DB.closeConnection();
        }

    }


}
