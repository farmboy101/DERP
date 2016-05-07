package edu.ycp.cs320.derp.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs320.derp.controller.MainContentController;
import edu.ycp.cs320.derp.model.Poll;
import edu.ycp.cs320.derp.model.User;

public class UserHomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MainContentController controller = new MainContentController();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		User thisUser = (User) req.getSession().getAttribute("user");
		List<Poll> pollList = controller.FindAllPolls();
		Poll poll1=pollList.get(0);
		Poll poll2=pollList.get(1);
		Poll poll3=pollList.get(2);
		
		req.setAttribute("fullname1", poll1.getTitle());
		req.setAttribute("fullname2", poll2.getTitle());
		req.setAttribute("fullname3", poll3.getTitle());
		int dis =poll1.getTotalVotes() - poll1.getYesVotes();
		req.setAttribute("info1", "Agree:" + poll1.getYesVotes() + " DisAgree:" + dis);
		dis =poll2.getTotalVotes() - poll2.getYesVotes();
		req.setAttribute("info2", "Agree:" + poll2.getYesVotes() + " DisAgree:" + dis);
		dis =poll3.getTotalVotes() - poll3.getYesVotes();
		req.setAttribute("info3", "Agree:" + poll3.getYesVotes() + " DisAgree:" + dis);
		req.getRequestDispatcher("/_view/userHome.jsp").forward(req, resp);
		
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.getSession().setAttribute("search", req.getAttribute("search"));
		req.getRequestDispatcher("/_view/searchresults.jsp").forward(req, resp);
		
	}
}
