package Controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.P_QuanLyDAO;
import DAO.P_ThongBaoDAO;
import Admin_Model.AD_Account;
import Model.P_ThongBaoModel;

/**
 * Servlet implementation class GuiTB
 */
@WebServlet("/GuiTB")
public class GuiTB extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GuiTB() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session1 = request.getSession();
		AD_Account account1 = (AD_Account) session1.getAttribute("user_login");
		if (account1 == null) {
			request.getRequestDispatcher("/Login.jsp").forward(request, response);
		}
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String TieuDeGui = request.getParameter("TieuDe");
		String NoiDung = request.getParameter("NoiDung");
		String NguoiNhan = request.getParameter("NguoiNhan");

		String maTK = "";

		HttpSession session = request.getSession();
		AD_Account account = (AD_Account) session.getAttribute("user_login");
		if (account != null) {
			maTK = account.getUsername();
			System.out.println("Username" + maTK);

		}
		String NguoiGui = "";
		try {
			NguoiGui = P_QuanLyDAO.TimMaPQL(maTK);
			System.out.println("Ma pql " + NguoiGui);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("GuiThongbao");

		P_ThongBaoModel GuiTB = new P_ThongBaoModel(NguoiNhan, TieuDeGui, NoiDung, NguoiGui);
		RequestDispatcher dispatcher = request.getRequestDispatcher("P_ThongBao.jsp");
		try {
			P_ThongBaoDAO.GuiThongBao(GuiTB);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.sendRedirect("XemThongBao");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
