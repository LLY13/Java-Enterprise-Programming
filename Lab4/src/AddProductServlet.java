
import java.sql.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AddProductServlet
 */
@WebServlet("/AddProductServlet")
public class AddProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddProductServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		String sql = "INSERT INTO PRODUCT (PRD_ID, PRD_NAME,PRD_PRICE, PRD_STOCK,PRD_COMMENTS) VALUES (?, ?, ?, ?, ?)";
		try {
			Connection cn = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/");
			PreparedStatement pstmt = cn.prepareStatement(sql);
			pstmt.setString(1, request.getParameter("ProductID"));
			pstmt.setString(2, request.getParameter("ProductName"));
			pstmt.setString(3, request.getParameter("ProductPrice"));
			pstmt.setString(4, request.getParameter("ProductStock"));
			pstmt.setString(5, request.getParameter("ProductComments"));
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.sendRedirect("Product.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
