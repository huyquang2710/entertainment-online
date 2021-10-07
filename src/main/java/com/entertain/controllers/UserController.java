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
import com.entertain.entities.User;
import com.entertain.services.IEmailService;
import com.entertain.services.IUserService;
import com.entertain.services.impl.EmailServiceImpl;
import com.entertain.services.impl.UserServiceImpl;

@WebServlet(urlPatterns = { "/login", "/logout", "/register","/forgotPass" })
public class UserController extends HttpServlet {

	private static final long serialVersionUID = -8150742089910480458L;

	private IUserService userService = new UserServiceImpl();
	
	private IEmailService emailService = new EmailServiceImpl();

	// do get
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(); // luu thong tin user
		String path = req.getServletPath(); // localhost:8080/login --> path = /login

		switch (path) {
		case "/login": {
			doGetLogin(req, resp);
			break;
		}
		case "/register": {
			doGetRegister(req, resp);
			break;
		}
		case "/logout": {
			doGetLoout(session, req, resp);
			break;
		}
		case "/forgotPass": {
			doGetForgotPass(req, resp);
			break;
		}
		}
	}

	// get login
	private void doGetLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/user/login.jsp");
		requestDispatcher.forward(req, resp);
	}

	// get register
	private void doGetRegister(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/user/register.jsp");
		requestDispatcher.forward(req, resp);
	}

	// get logout
	private void doGetLoout(HttpSession session, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		session.removeAttribute(SessionAttr.CURRENT_USER);
		resp.sendRedirect("index");
	}
	// get forgot pass
	private void doGetForgotPass(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/user/forgot-pass.jsp");
		requestDispatcher.forward(req, resp);
	}

	// do post
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(); // luu thong tin user
		String path = req.getServletPath(); // localhost:8080/login --> path = /login

		switch (path) {
		case "/login": {
			doPostLogin(session, req, resp);
			break;
		}
		case "/register": {
			doPostRegister(session, req, resp);
			break;
		}
		case "/forgotpass": {
			doPostForgotPass(req, resp);
			break;
		}
		default:
			break;
		}
	}

	// post login
	private void doPostLogin(HttpSession session, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// lay params username & password tu login form
		String username = req.getParameter("username");
		String password = req.getParameter("password");

		// truyen username & password vao login method
		User user = userService.login(username, password);

		if (user != null) {
			session.setAttribute(SessionAttr.CURRENT_USER, user);
			resp.sendRedirect("index");
		} else {
			resp.sendRedirect("login");
		}
	}

	// post register
	private void doPostRegister(HttpSession session, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// lay params username & password & email tu register form
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String email = req.getParameter("email");

		// truyen username & password & email vao register method
		User user = userService.create(username, password, email);

		if (user != null) {
			
			// send mail when dang ky thanh cong
			emailService.sendMail(getServletContext(), user, "welcome");
			
			session.setAttribute(SessionAttr.CURRENT_USER, user);
			resp.sendRedirect("index");
		} else {
			resp.sendRedirect("register");
		}
	}
	// post forgot pass
		private void doPostForgotPass(HttpServletRequest req, HttpServletResponse resp)
				throws ServletException, IOException {
			resp.setContentType("application/json");
			String email = req.getParameter("email");
			
			User userPassRandom = userService.resetPassword(email);
			if(userPassRandom != null) {
				emailService.sendMail(getServletContext(), userPassRandom, "forgot");
				resp.setStatus(204);
			} else {
				resp.setStatus(400);
			}
		}
}
