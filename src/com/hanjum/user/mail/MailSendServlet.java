package com.hanjum.user.mail;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;
import java.util.Random;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hanjum.user.service.UserProService;

/**
 * 
 * Servlet implementation class MailSendServlet
 *
 */

@WebServlet("/mailSend")
public class MailSendServlet extends HttpServlet {
	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String command = request.getServletPath();
		System.out.println("에이젝스로 메일에 왔따!!");
		System.out.println("요청 서블릿 주소 : " + command);
		System.out.println(request.getParameter("receiver"));
		
		// 파라미터값 받아오기
		String user_id = request.getParameter("user_id");
		String email = request.getParameter("receiver");
		int mailType = 0;
		
		// 비밀번호 찾기 일때(Type 1)(id값은 비밀번호 찾기일때만 받아옴)
		if(user_id != null) {
			UserProService userCheckEmail = new UserProService();
			mailType = userCheckEmail.checkEmail(user_id,email);
		} 
		// 회원가입 메일인증일때(Type 2)는 id값이 필요없음
		else {
			mailType = 2;
		}
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter(); 
		// 메일 타입이 1이상 일때만 아래코드 실행
		if(mailType > 0) {
			String sender = "gkdl5559@gmail.com";
			String subject = "";
			String content = "";
			String receiver = email;
			Random rand = new Random();
			String checkCode = ""; //난수가 저장될 변수
			for(int i=0;i<6;i++) {
				//0~9 까지 난수 생성 총6번 = 6자리난수
				String ran = Integer.toString(rand.nextInt(10));
				checkCode += ran;
			}// 추후에 메일보내는게 더 있으면 케이스 번호 추가하면됨
			switch (mailType) {
			case 1:
				subject = "한줌에디터 비밀번호 찾기 본인인증을 위한 메일인증 코드입니다.";
				content = "<b>본인인증을 위해 인증코드를 입력해 주시기 바랍니다 <br> 인증코드 : " + checkCode + "</b>";
				break;
			case 2:
				subject = "한줌에디터 회원가입을 위한 메일인증 코드입니다.";
				content = "<b>인증코드를 입력하셔서 회원가입을 완료해 주시기 바랍니다 <br> 인증코드 : " + checkCode + "</b>";
				break;
			} 
			UserProService emailCheckCodeServicde = new UserProService();
			boolean insertCheck = emailCheckCodeServicde.userEmailCheckCode(email,checkCode);
			// 인증코드 생성에 성공했을때만 메일전송
			if(insertCheck) {
				try {
					// 이메일 코드 발생 하는거
					response.setContentType("text/html;charset=UTF-8");
					Properties properties = System.getProperties();
					properties.put("mail.smtp.starttls.enable", "true");
					properties.put("mail.smtp.host", "smtp.gmail.com");
					properties.put("mail.smtp.auth", "true");
					properties.put("mail.smtp.port", "587");
					Authenticator auth = new GoogleAuthentication();
					Session s = Session.getDefaultInstance(properties, auth);
					Message message = new MimeMessage(s);
					Address sender_address = new InternetAddress(sender);
					Address receiver_address = new InternetAddress(receiver);
					message.setHeader("content-type", "text/html;charset=UTF-8");
					message.setFrom(sender_address);
					message.addRecipient(Message.RecipientType.TO, receiver_address);
					message.setSubject(subject);
					message.setContent(content,"text/html;charset=UTF-8");
					message.setSentDate(new java.util.Date());
					Transport.send(message);
					out.println("1"); 
					out.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		// mailType 0일때 (비밀번호 찾기 실패햇을때)
		else {
			// 0 출력하고 실행 폼에서 제어할게요
			out.println("0"); 
			out.close();
		}
		
		
	
	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response); // doProcess로
	}

	/**
	 *  @see HttpSerblet#HttpSerblet()
	 */
	public MailSendServlet() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 *  @see HttpServlet#doPost (HttpServletRequest request, HttpSerbletResponse response)
	 */
	
	protected void doPost(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException{
		doProcess(request, response); // doProcess로
	}
}
