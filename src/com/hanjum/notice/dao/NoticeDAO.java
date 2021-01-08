package com.hanjum.notice.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.json.simple.JSONArray;

import com.hanjum.notice.vo.NoticeBean;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.REUtil;

import static com.hanjum.db.JdbcUtil.*;

public class NoticeDAO {

	// **Singleton Pattern

	// 외부에서 인스턴트 생성 불가능 하도록 private
	private NoticeDAO() {
	};

	// private 으로 설정해서 외부에서 접근 불가능
	// 생성자를 리턴하는 getInstance()에서 멤버변수에 접근 가능하도록 static으로 지정
	private static NoticeDAO instance = new NoticeDAO();

	// 생성된 instance를 외부로 리턴하기위해 Getter -> getInstance() 정의
	// 외부에 인스턴스 생성없이 접근 가능하도록 static으로 정의
	public static NoticeDAO getInstance() {
		return instance;
	}

	/*
	 * notice_content (int) 종류 1 : user_id 님과 notice_from_id 님의 프로젝트(num?)가 매치되었습니다
	 * 2 : user_id 님이 프로젝트를 거절~~~~ 3 : 프로젝트가 진행중???입니다?????????????????? (1번이랑
	 * 같이??????????????????????????????????????????????????)
	 */

	//---------------------------------------------------------------------------

	Connection con; // Connection 객체를 전달받아 저장할 멤버변수

	// Service 클래스로부터 Connection 객체를 전달받아 멤버변수에 저장하는 메서드
	public void setConnection(Connection con) {
		this.con = con;
	}

	//---------------------------------------------------------------------------

	// ---------------GET---------------

	// 알림 정보 가져오기
	public NoticeBean getNoticeInfo(int notice_id) {
		System.out.println("DAO - getNoticeInfo()");
		NoticeBean noticeBean = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT * FROM notice WHERE notice_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, notice_id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				noticeBean = new NoticeBean();
				noticeBean.setBoard_id(rs.getInt("board_id"));
				noticeBean.setUser_id(rs.getString("user_id"));
				noticeBean.setNotice_content(rs.getInt("notice_content"));
				noticeBean.setNotice_date(rs.getTimestamp("notice_date"));
				noticeBean.setNotice_from_id(rs.getString("notice_from_id"));
				noticeBean.setNotice_id(notice_id);
				noticeBean.setNotice_read(rs.getInt("board_id"));
				noticeBean.setNotice_url(rs.getString("notice_url"));
			}
		} catch (Exception e) {
			System.out.println("getNoticeInfo() 오류 - "+e.getMessage());
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return noticeBean;
	}

	// 사용자에 해당하는 모든 알람 리스트 다 가져옴 
	public ArrayList<NoticeBean> getNoticeList(String user_id) {
		System.out.println("DAO - getNoticeList()");

		ArrayList<NoticeBean> list = new ArrayList<NoticeBean>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;

		try {

			sql = "select * from notice where user_id=? order by notice_id desc";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				NoticeBean notice = new NoticeBean();
				notice.setBoard_id(rs.getInt("board_id"));
				notice.setNotice_content(rs.getInt("notice_content"));
				notice.setNotice_date(rs.getTimestamp("notice_date"));
				notice.setNotice_from_id(rs.getString("notice_from_id"));
				notice.setNotice_id(rs.getInt("notice_id"));
				notice.setNotice_read(rs.getInt("notice_read"));
				notice.setNotice_url(rs.getString("notice_url"));
				notice.setUser_id(user_id);

				list.add(notice);

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return list;
	}

	// overloading :  notice_id 찾기  
	public ArrayList<NoticeBean> getNoticeList(NoticeBean noticeBean) {
		System.out.println("DAO - getNoticeList()");

		ArrayList<NoticeBean> list = new ArrayList<NoticeBean>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;

		try {

			sql = "select * from notice where notice_id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, noticeBean.getNotice_id());
			rs = pstmt.executeQuery();

			while (rs.next()) {
				NoticeBean notice = new NoticeBean();
				notice.setBoard_id(rs.getInt("board_id"));
				notice.setNotice_content(rs.getInt("notice_content"));
				notice.setNotice_date(rs.getTimestamp("notice_date"));
				notice.setNotice_from_id(rs.getString("notice_from_id"));
				notice.setNotice_id(rs.getInt("notice_id"));
				notice.setNotice_read(rs.getInt("notice_read"));
				notice.setNotice_url(rs.getString("notice_url"));
				notice.setUser_id(rs.getString("user_id"));

				list.add(notice);

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return list;
	}

	// 사용자에 해당하는 새로운 알람 가져옴
	public ArrayList<NoticeBean> getNewNotice(String user_id) {
		System.out.println("DAO - getNewNotice()");

		ArrayList<NoticeBean> list = new ArrayList<NoticeBean>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;

		try {

			sql = "select * from notice where user_id=? and notice_read=0 order by notice_id desc ";

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				NoticeBean notice = new NoticeBean();
				notice.setBoard_id(rs.getInt("board_id"));
				notice.setNotice_content(rs.getInt("notice_content"));
				notice.setNotice_date(rs.getTimestamp("notice_date"));
				notice.setNotice_from_id(rs.getString("notice_from_id"));
				notice.setNotice_id(rs.getInt("notice_id"));
				notice.setNotice_read(rs.getInt("notice_read"));
				notice.setNotice_url(rs.getString("notice_url"));
				notice.setUser_id(user_id);

				list.add(notice);

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return list;
	}

	// 사용자에 해당하는 오래된 알람출력(commit / rollback 은 service에서 + commit / rollback 여기서 바로) 
	public ArrayList<NoticeBean> getOldNotice(String user_id) {
		System.out.println("DAO - getOldNotice()");

		ArrayList<NoticeBean> list = new ArrayList<NoticeBean>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int delete = 0;

		try {

			sql = "select * from notice where user_id=? and notice_read=1 order by notice_id desc";

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user_id);
			rs = pstmt.executeQuery();

			NoticeBean notice = new NoticeBean();
			while (rs.next()) {
				notice.setBoard_id(rs.getInt("board_id"));
				notice.setNotice_content(rs.getInt("notice_content"));
				notice.setNotice_date(rs.getTimestamp("notice_date"));
				notice.setNotice_from_id(rs.getString("notice_from_id"));
				notice.setNotice_id(rs.getInt("notice_id"));
				notice.setNotice_read(rs.getInt("notice_read"));
				notice.setNotice_url(rs.getString("notice_url"));
				notice.setUser_id(rs.getString("user_id"));

				list.add(notice);
				System.out.println("DAO-oldNotice 2");

				// 14일 경과한 알람 같이 지우기 - commit / rollback DAO 에서
				if (rs.next()) {
					sql = "delete from notice where notice_date > curdate() - interval 14 day and user_id=?";
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, user_id);
					delete = pstmt.executeUpdate();
					System.out.println("DAO-oldNotice 3");
					// 여기서 commit 해도 되는지? 
					// 리턴값이 list
					if (delete == 1) {
						System.out.println("DAO-oldNotice 4");
						commit(con); // 성공시 14일 경과한 알람에 대해서만 commit
					} else {
						rollback(con);
					}
				} // if 저장된 데이터 있으면

			} // while

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return list;
	}

	// match 됐을 때
	public void getMatchNotification(NoticeBean noticeBean) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;

		try {

			// match O ->  user_id  에게 insertNotice() 호출??
			// match X -> user_id 엑 insert? 
			sql = "";
			pstmt = con.prepareStatement(sql);
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}

	}

	// 새로운 알람 갯수
	public int getNoticeCount(String user_id) {
		int count = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			String sql = "select count(notice_id) from notice where user_id=? and notice_read=0";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user_id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				count = rs.getInt(1);
			}
			System.out.println("확인하지 않은 해당 유저의 알림 갯수 : " + count);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}

		return count;
	} // 알람갯수가 0보다 크면 빨간색으로 종표시 보기에하기?

	// ---------------INSERT--------------- 각 insert마다 까먹지말고 notice_id 부여하기 !
	// insertNotice 알람정보들 저장 - commit, rollback -> service에서 ! (성공하면 알람 보내기 !)
	public int insertNotice(NoticeBean noticeBean) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int notice_id = 0;
		int noticeSuccess = 0;

		try {
			System.out.println("DAO - insertNotice.java");

			// notice_id 번호 부여
			sql = "select max(notice_id) from notice";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				notice_id = rs.getInt("max(notice_id)") + 1;
			}

			// 모든 값 다 저장 (parameter로 notice_content 값 전달받기!)
			// notice_content 는 front에서 notice_content 따라서 뿌려줌 (DB not null  옵션 해제!)
			sql = "insert into notice(notice_date, notice_id, notice_content, notice_read, notice_url , board_id, user_id, notice_from_id) values(now(), ?, ?, 0, ?, ?, ?, ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, notice_id);
			pstmt.setInt(2, noticeBean.getNotice_content());
			pstmt.setString(3, noticeBean.getNotice_url());
			//			pstmt.setInt(4, noticeBean.getNotice_read()); // 처음에 notice_read는 무조건 0 
			pstmt.setInt(4, noticeBean.getBoard_id());
			pstmt.setString(5, noticeBean.getUser_id());
			pstmt.setString(6, noticeBean.getNotice_from_id());

			noticeSuccess = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return noticeSuccess;
	}

	// ---------------UPDATE---------------
	// user가 클릭한 알람 notice_read = 1로 바꿔줌 (바꿔졌으면 1 - commit 하기, 아니면 0 - rollback 하기)
	public int updateStatus(int notice_id) {
		System.out.println("DAO - updateStatus()");
		PreparedStatement pstmt = null;
		int noticeRead = 0;
		String sql = null;
		try {
			sql = "update notice set notice_read=notice_read+1 where notice_id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, notice_id);
			noticeRead = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return noticeRead;
	}

	// ---------------DELETE---------------
	// 알람 삭제 버튼 눌렀을 때 -> 매개변수 : notice_id
	public int deleteNotice(int notice_id) {
		System.out.println("DAO - deleteNotice");

		int deleteSuccess = 0;
		String sql = null;
		PreparedStatement pstmt = null;

		try {

			sql = "delete from notice where notice_id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, notice_id);
			deleteSuccess = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		System.out.println("성공여부 : " + deleteSuccess);
		return deleteSuccess;

	}

	public int updateStatus(String user_id) {
		System.out.println("DAO - updateStatus()");
		PreparedStatement pstmt = null;
		int noticeRead = 0;
		String sql = null;
		try {
			sql = "update notice set notice_read=notice_read+1 where user_id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user_id);
			noticeRead = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return noticeRead;
	}

}
