package com.entertain.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.entertain.entities.Video;
import com.entertain.services.IVideoService;
import com.entertain.services.impl.VideoServiceImpl;

@WebServlet(urlPatterns = "/index")
public class HomeController extends HttpServlet{

	private static final long serialVersionUID = -7131209992553490556L;
	private IVideoService videoService = new VideoServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		List<Video> videosList = videoService.findAll();
		req.setAttribute("videos", videosList);
		
		RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/user/index.jsp");
		requestDispatcher.forward(req, resp);
	}

}
