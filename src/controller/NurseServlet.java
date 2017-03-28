package controller;

import java.io.IOException;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import model.NurseBean;
import model.OperBean;
import model.dao.NurseBeanDAO;
import model.dao.OperBeanDAO;
import model.service.NurseService;
import model.service.OperService;

/**
 * Servlet implementation class NurseServlet
 */
@WebServlet("/pages/nurse.controller")
public class NurseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private NurseService nurseService;
	private OperService operService;
	String command;

	public NurseServlet() {
		super();
	}

	@Override
	public void init() throws ServletException {
 		super.init();
		ServletContext application = this.getServletContext();
		WebApplicationContext context =
				WebApplicationContextUtils.getWebApplicationContext(application);
		
		nurseService = (NurseService) context.getBean("nurseService");
		operService = (OperService) context.getBean("operService");
		
// 		ApplicationContext context =
//				new ClassPathXmlApplicationContext("beans.config.xml");
//		SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");
//		sessionFactory.getCurrentSession().beginTransaction();
// 		nurseService = new NurseService(new NurseBeanDAO(sessionFactory));
//		operService = new OperService(new OperBeanDAO(sessionFactory));
	}
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		command = request.getParameter("nurse_action");
		if(command.equals("list"))
		{
			List<NurseBean> nurses = nurseService.select();
			request.setAttribute("nurses", nurses);
			System.out.println(nurses);
			request.getRequestDispatcher("nurse_list.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String name = null, nurseId = null;
		String[] opers;
		
		NurseBean insert =null; 

		name = request.getParameter("nurse_name");
		nurseId = request.getParameter("nurse_id");
		opers = request.getParameterValues("from");
		for (String oper : opers) {
			Date updateTime = new Date();
			NurseBean nurseBean = new NurseBean(nurseId, name, updateTime);
			nurseBean.setId(0);
			command = request.getParameter("nurse_action");
			int id = Integer.valueOf(oper);
  			OperBean operBean = operService.select(id);

			if (command.equals("Insert")) {
				insert	= nurseService.insert(nurseBean, operBean);
				
			}
			
		}
		if (insert != null) {
			request.setAttribute("select", insert);
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
		doGet(request, response);
	}

}
