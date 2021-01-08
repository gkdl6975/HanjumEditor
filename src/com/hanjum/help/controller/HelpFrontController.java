package com.hanjum.help.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hanjum.action.Action;
import com.hanjum.help.action.CenterListAction;
import com.hanjum.help.action.CenterSearchListAction;
import com.hanjum.help.action.HelpDeleteAction;
import com.hanjum.help.action.HelpListProAction;
import com.hanjum.help.action.HelpUpdateAction;
import com.hanjum.help.action.HelpWriteProAction;
import com.hanjum.vo.ActionForward;

@WebServlet("*.hp")
public class HelpFrontController extends HttpServlet {

	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		String command = request.getServletPath();
		System.out.println("요청 서블릿 주소 : " + command);
		Action action = null;
		ActionForward forward = null;

		if (command.equals("/helpList.hp")) {

			action = new HelpListProAction();

			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if (command.equals("/HelpWriteForm.hp")) {
			System.out.println("HelpWriteForm 컨트롤러");
			forward = new ActionForward();
			forward.setPath("/admin/admin_help_write.jsp");

		} else if (command.equals("/HelpWritePro.hp")) {
			action = new HelpWriteProAction();

			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();

			}

		} else if (command.equals("/HelpUpdate.hp")) {
			action = new HelpUpdateAction();
			try {

				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if (command.equals("/HelpDelete.hp")) {
			action = new HelpDeleteAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/Center.hp")) {
			String search = request.getParameter("search");
			forward = new ActionForward();
			forward.setPath("center/center.jsp");
			forward.setRedirect(false);
		} else if (command.equals("/CenterHelp.hp")) {
			action = new CenterListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/CenterSearchHelp.hp")) {
			action = new CenterSearchListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/CenterReport.hp")) {
			forward = new ActionForward();
			forward.setPath("center/centerReport.jsp");
			forward.setRedirect(false);
		}

		if (forward != null) {
			if (forward.isRedirect()) {
				response.sendRedirect(forward.getPath());
			} else {
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}

		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}