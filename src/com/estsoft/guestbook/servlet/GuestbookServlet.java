package com.estsoft.guestbook.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.estsoft.db.MySQLWebDBConnection;
import com.estsoft.guestbook.dao.GuestbookDao;
import com.estsoft.guestbook.vo.GuestbookVO;
import com.estsoft.web.WebUtil;

@WebServlet("/gb")
public class GuestbookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// post 방식의 한글(UTF-8) 데이터 처리
		request.setCharacterEncoding("UTF-8");

		// 요청분석
		String actionName = request.getParameter("a");

		if ("add".equals(actionName)) {

			String name = request.getParameter("name");
			String password = request.getParameter("password");
			String message = request.getParameter("content");

			GuestbookVO vo = new GuestbookVO();
			vo.setName(name);
			vo.setPassword(password);
			vo.setMessage(message);

			GuestbookDao dao = new GuestbookDao(new MySQLWebDBConnection());
			dao.insert(vo);

			WebUtil.redirect(request, response, "/guestbook2/gb");

		} else if ("delete".equals(actionName)) {

			Long no = Long.parseLong(request.getParameter("no"));
			String password = request.getParameter("password");

			GuestbookVO vo = new GuestbookVO();
			vo.setNo(no);
			vo.setPassword(password);

			GuestbookDao dao = new GuestbookDao(new MySQLWebDBConnection());
			dao.delete(vo);

			WebUtil.redirect(request, response, "/guestbook2/gb");

		} else if ("deleteform".equals(actionName)) {
		
			WebUtil.forward(request,response,"/WEB-INF/views/deleteform.jsp");

		} else {
			// default action ( list, index )
			GuestbookDao dao = new GuestbookDao(new MySQLWebDBConnection());
			List<GuestbookVO> list = dao.getList();

			// 포워딩전에 JSP로 보낼 데이터를 request범위(scope)에 저장한다.
			request.setAttribute("list", list);

			// forwarding (request 확장, request dispatcher )
			WebUtil.forward(request,response,"/WEB-INF/views/index.jsp");
		}

	}

}
