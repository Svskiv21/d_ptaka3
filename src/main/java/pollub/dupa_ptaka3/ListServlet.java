package pollub.dupa_ptaka3;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ListServlet", urlPatterns = {"/ListServlet"})
public class ListServlet extends HttpServlet{
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        //pobranie sterownika do MySQL:
        Class.forName("com.mysql.cj.jdbc.Driver");
        //utworzenie obiektu połączenia do bazy danych MySQL:
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/world? serverTimezone=UTC", "root", "Student");
        //utworzenie obiektu do wykonywania zapytań do bd:
        Statement st = conn.createStatement();
        String query="SELECT * FROM Country WHERE Continent = 'Europe'";
        //wykonanie zapytania SQL:
        ResultSet rs = st.executeQuery(query);
        while (rs.next()) {
            try ( PrintWriter out = response.getWriter()) {
                //pobierz i wyświetl dane z odpowiedniej kolumny
                out.println(rs.getString("name"));
                //out.println ...
            }
        }

        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ListServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ListServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.lang.ClassNotFoundException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        try{
            processRequest(request, response);
        }
        catch(ClassNotFoundException|SQLException ex){
            try {
                throw ex;
            } catch (ClassNotFoundException ex1) {
                Logger.getLogger(ListServlet.class.getName()).log(Level.SEVERE, null, ex1);
            } catch (SQLException ex1) {
                Logger.getLogger(ListServlet.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try{
            processRequest(request, response);
        }
        catch(ClassNotFoundException|SQLException ex){
            try {
                throw ex;
            } catch (ClassNotFoundException ex1) {
                Logger.getLogger(ListServlet.class.getName()).log(Level.SEVERE, null, ex1);
            } catch (SQLException ex1) {
                Logger.getLogger(ListServlet.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
