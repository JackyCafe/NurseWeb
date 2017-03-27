package controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.SessionFactory;

import model.NurseBean;
import model.OperBean;
import model.dao.NurseBeanDAO;
import model.dao.OperBeanDAO;
import model.misc.HibernateUtil;
import model.service.NurseService;
import model.service.OperService;

/**
 * Servlet implementation class NurseServlet
 */
@WebServlet("/pages/oper.controller")
public class OperServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	OperService operService;
	String command;

	public OperServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		command = request.getParameter("oper_action");
		operService = new OperService(new OperBeanDAO(HibernateUtil.getSessionFactory()));

		if (command.equals("list")) {
			List<OperBean> select = operService.select();
			request.setAttribute("select", select);
			request.getRequestDispatcher("oper_list.jsp").forward(request, response);
		}
		if (command.equals("delete")) {
			String oper_id = request.getParameter("operid");
			int id = Integer.valueOf(oper_id);
			System.out.println(command + "," + oper_id);

			Boolean delete = operService.delete(id);
			if (delete) {
				List<OperBean> select = operService.select();
				if (select != null) {
					request.setAttribute("select", select);
				}
				request.getRequestDispatcher("oper_list.jsp").forward(request, response);
			}

		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String name = null, nurseId = null;
		String command;

		operService = new OperService(new OperBeanDAO(HibernateUtil.getSessionFactory()));

		name = request.getParameter("oper_name");
		Date updateTime = new Date();
		OperBean bean = new OperBean(name, updateTime);
		bean.setOperId(0);
		command = request.getParameter("oper_action");
		System.out.println("action" + command);
		try {
			if (command.equals("Insert")) {
				OperBean insert = operService.insert(bean);
				if (insert != null) {
					request.setAttribute("select", insert);
					request.getRequestDispatcher("index.jsp").forward(request, response);
				}
			}
		} catch (Exception e) {
			request.setAttribute("error", "資料重複輸入");
			request.getRequestDispatcher("error.jsp").forward(request, response);

		}
		doGet(request, response);
	}

}
