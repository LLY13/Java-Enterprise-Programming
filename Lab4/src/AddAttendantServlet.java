

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
 * Servlet implementation class AddAttendantServlet
 */
@WebServlet("/AddAttendantServlet")
public class AddAttendantServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddAttendantServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		String sql = "INSERT INTO ATTENDANT (ATT_ID, ATT_NAME,ATT_ADDRESS, ATT_MOBILE,ATT_COMMENTS) VALUES (?, ?, ?, ?, ?)";
		try {
			Connection cn = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/");
			PreparedStatement pstmt = cn.prepareStatement(sql);
			pstmt.setString(1, request.getParameter("ID"));
			pstmt.setString(2, request.getParameter("Name"));
			pstmt.setString(3, request.getParameter("Address"));
			pstmt.setString(4, request.getParameter("Mobile"));
			pstmt.setString(5, request.getParameter("Comments"));
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.sendRedirect("Attendant.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
