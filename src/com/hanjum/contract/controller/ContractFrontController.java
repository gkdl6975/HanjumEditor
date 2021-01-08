package com.hanjum.contract.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hanjum.action.Action;
import com.hanjum.contract.action.ContractInsertProAction;
import com.hanjum.contract.action.ContractListAction;
import com.hanjum.contract.action.ContractSearchAction;
import com.hanjum.contract.service.ContractInsertProService;
import com.hanjum.vo.ActionForward;


/**
 * Servlet implementation class ContractFrontController
 */
@WebServlet("*.ct")
public class ContractFrontController extends HttpServlet {
	
       
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String command = request.getServletPath();
		System.out.println("요청 서블릿 주소 : " + command);
		
		Action action = null;
		ActionForward forward = null;
		
	
		if(command.equals("/ContractList.ct")) {
			action = new ContractListAction();
			
			try {
				forward = action.execute(request, response);
												
			} catch (Exception e) {
				// TODO: handle exception
			}
			

		}else if(command.equals("/ContractInsertPro.ct")) {
			action = new ContractInsertProAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/ContractSeartch.ct")) {
			action = new ContractSearchAction();

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
