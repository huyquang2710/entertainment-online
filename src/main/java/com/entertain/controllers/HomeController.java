package com.entertain.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

@WebServlet(urlPatterns = { "/index", "/favorites", "/history" })
public class HomeController extends HttpServlet {

	private static final long serialVersionUID = -7131209992553490556L;
	
	private static final int MAX_VIDEO_PAGE_SIZE = 4;
	
	private IVideoService videoService = new VideoServiceImpl();
	private IHistoryService historyService = new HistoryServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		
		String path = req.getServletPath();
		switch (path) {
		case "/index": {
			doGetIndex(req, resp);
			break;
			}
		case "/favorites": {
			doGetFavorites(session, req, resp);
			break;
			}
		case "/history": {
			doGetHistory(session, req, resp);
			break;
			}
		}
	}
	// trang chu
		// url phan trang /index?page={pageNumber}
		// 
	private void doGetIndex(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Video> countVideo = videoService.findAll();
		// max Page = 10 video.  moi trang co 4 video thi 10 / 4 = 2.5 -> 3
		int maxPage = (int) Math.ceil(countVideo.size() / (double) MAX_VIDEO_PAGE_SIZE);
		
		String pageNumber = req.getParameter("page");
		List<Video> videosList;
		
		// neu pageNo == null hoac lon hon maxPage thi tra ve default  = 1
		if(pageNumber == null || Integer.valueOf(pageNumber) > maxPage ) {
			// mac dinh bang 1 khi user voa trang dau
			videosList = videoService.findAll(1, MAX_VIDEO_PAGE_SIZE);
			
			// trang hien tai
			req.setAttribute("currentPage", 1);
		} else {
			videosList = videoService.findAll(Integer.valueOf(pageNumber), MAX_VIDEO_PAGE_SIZE);
			
			// trang hien tai
			req.setAttribute("currentPage", Integer.valueOf(pageNumber));
		}
		
		
		req.setAttribute("videos", videosList);
		req.setAttribute("maxPage", maxPage);

		RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/user/index.jsp");
		requestDispatcher.forward(req, resp);
	}
	/*
	 * private void doGetIndex(HttpServletRequest req, HttpServletResponse resp)
	 * throws ServletException, IOException {
	 * 
	 * List<Video> videosList = videoService.findAll(); req.setAttribute("videos",
	 * videosList);
	 * 
	 * RequestDispatcher requestDispatcher =
	 * req.getRequestDispatcher("/views/user/index.jsp");
	 * requestDispatcher.forward(req, resp); }
	 */
	
	
	// da like
	private void doGetFavorites(HttpSession session, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// check user ton tai chua
		User currentUser = (User) session.getAttribute(SessionAttr.CURRENT_USER);
		
		// lay list video ma user da xem thong qua History
		List<History> histories = historyService.findByUserAndIsLiked(currentUser.getUsername());

		// duyet list video
		List<Video> videosList = new ArrayList<>();
		histories.forEach(item -> videosList.add(item.getVideo()));
		/*
		 for(int i = 0; i < histories.size(); i++) {
		 	videosList.add(histories.get(i).getVideo());
		 }
		*/
		req.setAttribute("videos", videosList);

		RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/user/favorites.jsp");
		requestDispatcher.forward(req, resp);
	}
	
	// lich su
	private void doGetHistory(HttpSession session, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		User currentUser = (User) session.getAttribute(SessionAttr.CURRENT_USER);
		
		List<History> histories = historyService.findByUser(currentUser.getUsername());

		List<Video> videosList = new ArrayList<>();
		histories.forEach(item -> videosList.add(item.getVideo()));
		
		req.setAttribute("videos", videosList);

		RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/user/history.jsp");
		requestDispatcher.forward(req, resp);
	}

}
