package com.hanjum.review.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hanjum.action.Action;
import com.hanjum.review.action.ReviewListAction;
import com.hanjum.review.action.ReviewUpdateForm;
import com.hanjum.review.action.ReviewUpdatePro;
import com.hanjum.review.action.ReviewWriteProAction;
import com.hanjum.review.action.reviewDeleteProAction;
import com.hanjum.vo.ActionForward;


@WebServlet("*.rv")
public class ReviewFrontController extends HttpServlet {
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		
		String command = request.getServletPath();
		System.out.println("요청 서블릿 주소 : " + command);
		
		
		Action action = null;
		ActionForward forward = null;
		
		if(command.equals("/Editor.bo")) {
			System.out.println("editor.bo");
			forward = new ActionForward();
			forward.setPath("/editor/reviewcontent.jsp");
		}else if(command.equals("/ReviewWritePro.rv")) {
			
			action = new ReviewWriteProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}else if(command.equals("/ReviewList.rv")) {
			action = new ReviewListAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/ReviewUpdateForm.rv")) {
			action = new ReviewUpdateForm();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/ReviewUpdatePro.rv")) {
			action = new ReviewUpdatePro();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/ReviewDeleteForm.rv")) {
			forward = new ActionForward();
			forward.setPath("/editor/reviewdelete.jsp");
		} else if(command.equals("/ReviewDeletePro.rv")) {
			action = new reviewDeleteProAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		if(forward != null) {
			
			if(forward.isRedirect()) {
			
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
 