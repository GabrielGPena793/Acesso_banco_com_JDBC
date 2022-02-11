package application;

import db.DB;
import db.DbException;

import java.sql.*;

public class Programm {
    public static void main(String[] args) {

        Connection conn = null;
        Statement st = null;

        try {
            conn = DB.getConnection(); // iniciando conecção

            conn.setAutoCommit(false); // setando para não confirmar as operações automaticamente

            st = conn.createStatement();

            int rows1 = st.executeUpdate("UPDATE seller SET BaseSalary = 2090 WHERE DepartmentId = 1");

            /*  int x = 1;
            if(x < 2){
                throw new SQLException("Fake error");
            }*/

            int rows2 = st.executeUpdate("UPDATE seller SET BaseSalary = 3090 WHERE DepartmentId = 2");

            conn.commit(); // agora vai liberar o commit, protegendo de falhas

            System.out.println("rows1 " + rows1);
            System.out.println("rows2 " + rows2);

        }catch (SQLException e){
            try {
                //volta a transação caso ela tenha falhado no meio.
                conn.rollback();
                throw new DbException("Transaction rolled back! Caused by: " + e.getMessage());
            } catch (SQLException ex) {
                throw new DbException("Error trying to rollback! Caused by: " + ex.getMessage());
            }
        }
        finally {
            DB.closeStatement(st);
            DB.closeConnection();
        }

    }


}
