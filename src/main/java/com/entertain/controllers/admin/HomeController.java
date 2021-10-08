package com.entertain.controllers.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.entertain.constants.SessionAttr;
import com.entertain.dto.VideoLikedInfo;
import com.entertain.entities.User;
import com.entertain.services.IStatsService;
import com.entertain.services.impl.StatsServiceImpl;

@WebServlet(urlPatterns = "/admin", name = "HomeControllerOfAdmin")
public class HomeController extends HttpServlet{

	private static final long serialVersionUID = -7658727081433078640L;
	
	private IStatsService statsService = new StatsServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// check login chua
		HttpSession session = req.getSession();
		
		//admin moi dc truy cap
		User currentUser = (User) session.getAttribute(SessionAttr.CURRENT_USER);
		if(currentUser != null && currentUser.getIsAdmin() == Boolean.TRUE) {
			
			List<VideoLikedInfo> videos = statsService.findVideoLikedInfo();
			req.setAttribute("video", videos);
			
			RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/admin/home.jsp");
			requestDispatcher.forward(req, resp);
		} else {
			resp.sendRedirect("index");
		}
		
		
	}

}
