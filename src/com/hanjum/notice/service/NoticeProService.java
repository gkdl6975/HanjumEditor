package com.hanjum.notice.service;

import java.io.IOException;
import java.net.URI;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Set;



import com.hanjum.notice.dao.NoticeDAO;
import com.hanjum.notice.vo.NoticeBean;
import static com.hanjum.db.JdbcUtil.*;

public class NoticeProService {
	// Action 클래스로부터 요청받은 작업에 대한 데이터등을 전달받아
	// Model(DAO)를 통해 실제 작업처리를 요청하고
	// 처리결과를 리턴받아 해당 결과에 대한 판별을 통해
	// 결과값으로 처리할 데이터를 리턴
	private NoticeDAO noticeDAO;
	private Connection con;
	public NoticeProService() {
		noticeDAO = NoticeDAO.getInstance();
		con = getConnection();
		noticeDAO.setConnection(con);
	}
	
	
//	----------------DAO호출-------------------
	
	
	// 새로운 알람 가져오기
		public ArrayList<NoticeBean> getNewNotice(String user_id) {
			System.out.println("svc - getNewNotice()");
			ArrayList<NoticeBean> list = noticeDAO.getNewNotice(user_id);
			close(con);
			return list;
			
		}
	
	 
	// 모든 알람 리스트 가져오기
	public ArrayList<NoticeBean> getNoticeList(String user_id){
		System.out.println("svc - getNoticeList()");
		ArrayList<NoticeBean> list = noticeDAO.getNoticeList(user_id);
		close(con);
		return list;		
	}
	
	
	// 지난(읽은) 알람 5개 가져오기
	public ArrayList<NoticeBean> getOldNotice(String user_id){
		System.out.println("svc - getOldNotice()");
		ArrayList<NoticeBean> list = noticeDAO.getOldNotice(user_id);
		close(con);
		return list;
	}
	
	
	// 신청된 정보들을 담아서 insertNotice에 입력하러감 룰루
	public void insertNotice(NoticeBean noticeBean) {
		System.out.println("svc - insertNotice()");
		
		int noticeSuccess = noticeDAO.insertNotice(noticeBean);
		
		if(noticeSuccess > 0) {
			commit(con);
		}else {
			rollback(con);
		}
		
		close(con);
	}
	
	
	// 클릭한(읽은) 알람상태를 바꿔주는 메서드 호출
	public void updateStatus(int notice_id) {
		System.out.println("svc - updateStatus()");
		int noticeRead = noticeDAO.updateStatus(notice_id);
		
		if(noticeRead > 0) {
			commit(con);
		}else {
			rollback(con);
		}
		close(con);
	}
	
	
	// 해당알람 삭제하기
	public void deleteNotice(int notice_id) {
		int deleteSuccess = noticeDAO.deleteNotice(notice_id);
		
		if(deleteSuccess > 0) {
			commit(con);
		}else {
			rollback(con);
		}
		
		close(con);
	}
	
	
	// 갯수 띄우기
	public int getNoticeCount(String user_id) {
		int count = noticeDAO.getNoticeCount(user_id);
		close(con);
		return count;
	}
	
	// 알림 정보 가져오기
	public NoticeBean getNotice(int notice_id) {
		System.out.println("NoticeProService - getNotice()");
		NoticeBean noticeBean = noticeDAO.getNoticeInfo(notice_id);
		close(con);
		return noticeBean;
	}


	public void updateStatus(String user_id) { // 유저아이디 기준 read
		System.out.println("svc - updateStatus()");
		int noticeRead = noticeDAO.updateStatus(user_id);
		
		if(noticeRead > 0) {
			commit(con);
		}else {
			rollback(con);
		}
		close(con);
	}
	
	
}
















































