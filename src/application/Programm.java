package application;

import db.DB;

import java.sql.*;
import java.text.SimpleDateFormat;

public class Programm {
    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Connection conn = DB.getConnection();
        PreparedStatement st = null;

        try {
            conn = DB.getConnection();

            /*st = conn.prepareStatement(
                    "INSERT INTO seller (Name, Email, BirthDate, BaseSalary, DepartmentId)"
                    + "VALUES"
                    + "(?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);

            st.setString(1, "Maria Purple");
            st.setString(2, "Maria@gmail.com");
            st.setDate(3, new java.sql.Date(sdf.parse("22/04/1990").getTime()));
            st.setDouble(4, 4000);
            st.setInt(5, 4);
            */

            st = conn.prepareStatement("insert into department (Name) values ('D1'),('D2')",
                    Statement.RETURN_GENERATED_KEYS);

            int rowsAffect =  st.executeUpdate(); //executa o insert e retorna a quantidade de linhas alteradas

            //pegando o id da tabela que foram alteradas/adicionadas.
            if (rowsAffect > 0 ){
                ResultSet rs = st.getGeneratedKeys();
                while (rs.next()){
                    int id = rs.getInt(1);
                    System.out.println("Done! id = " + id);
                }
            } else {
                System.out.println("No rows a affected!");
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            DB.closeStatement(st);
            DB.closeConnection(); // sempre fechar a conecção por último
        }


    }


}
