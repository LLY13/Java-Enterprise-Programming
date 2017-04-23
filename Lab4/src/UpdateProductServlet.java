

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UpdateProductServlet
 */
@WebServlet("/UpdateProductServlet")
public class UpdateProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateProductServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		String sql = "UPDATE Product SET PRD_NAME=?, PRD_PRICE=?, PRD_STOCK=?, PRD_COMMENTS=? WHERE PRD_ID=?";
		try {
			Connection cn = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/");
			PreparedStatement pstmt = cn.prepareStatement(sql);
			pstmt.setString(1, request.getParameter("ProductName"));
			pstmt.setString(2, request.getParameter("ProductPrice"));
			pstmt.setString(3, request.getParameter("ProductStock"));
			pstmt.setString(4, request.getParameter("ProductComments"));
			pstmt.setString(5, request.getParameter("ProductID"));
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
