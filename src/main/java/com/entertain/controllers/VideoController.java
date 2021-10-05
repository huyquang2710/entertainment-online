package com.entertain.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.entertain.constants.SessionAttr;
import com.entertain.entities.History;
import com.entertain.entities.User;
import com.entertain.entities.Video;
import com.entertain.services.IHistoryService;
import com.entertain.services.IVideoService;
import com.entertain.services.impl.HistoryServiceImpl;
import com.entertain.services.impl.VideoServiceImpl;

@WebServlet(urlPatterns = "/video")
public class VideoController extends HttpServlet{
	private static final long serialVersionUID = 7165362432014282933L;
	private IVideoService videoService = new VideoServiceImpl();
	private IHistoryService historyService = new HistoryServiceImpl();
	
	// do get
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String actionParam = req.getParameter("action");
		String href = req.getParameter("id");
		HttpSession session = req.getSession();
		
		//xem video
			// /video?action=watch&id={href}
		switch (actionParam) {
		case "watch": {
			doGetWatch(session, href, req, resp);
			break;
			}
		case "like": {
			doGetLike(session, href, req, resp);
			break;
			}
		}
	}

	//xem video
			// /video?action=watch&id={href}
	private void doGetWatch(HttpSession session, String href, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// tra ve info 1 video khi user click
		Video video = videoService.findByHref(href);
		req.setAttribute("video", video);
		
		//check user dang nhap chua. de set history
		User currentUser = (User) session.getAttribute(SessionAttr.CURRENT_USER);
		if(currentUser != null) {
			History history = historyService.create(currentUser, video);
			
			//flag de check btn like
			req.setAttribute("flagLikedBtn", history.getIsLiked());
		}
		
		RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/user/video-detail.jsp");
		requestDispatcher.forward(req, resp);
	}
	//like
		// /video?action=like&id={href}
	private void doGetLike(HttpSession session, String href, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// tra ve info 1 video khi user click
		resp.setContentType("application/json");
		
		//check user login chua
		User currentUser = (User) session.getAttribute(SessionAttr.CURRENT_USER);
		
		//like
		boolean result = historyService.updateLikeOrUnlike(currentUser, href);
		if(result == true) {
			resp.setStatus(204);
		} else {
			resp.setStatus(400);
		}
	}
}
