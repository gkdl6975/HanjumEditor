package com.hanjum.chat.dao;

import static com.hanjum.db.JdbcUtil.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.hanjum.chat.vo.ChatBean;
import com.hanjum.chat.vo.ChatListBean;

public class ChatDAO {

	private static ChatDAO instance = new ChatDAO();

	private ChatDAO() {
	}

	public static ChatDAO getInstance() {
		return instance;
	}

	//===============================================================================	

	Connection con;

	public void setConnection(Connection con) {
		this.con = con;

	}

	//--------------------------------------INSERT----------------------------------
	// 채팅내용을 등록	
	public int insertArticle(ChatBean chatBean) {

		int insertCount = 0; //INSERT 작업수행결과를 저장할 변수

		PreparedStatement pstmt = null;

		ResultSet rs = null;

		int num = 1;

		//현재 게시물 번호(board_num)중 가장 큰 번호를 조회하여
		//해당 번호 + 1 값을 새글 번호 (num)으로 저장
		String sql = "SELECT MAX(chat_id) FROM chat";
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			//조회된 결과가 있을 경우 조회된 번호 +1 값을 num에 저장
			//=> 조회 결과가 없을경우 새글 번호는 1번이므로 기본값 그대로 사용
			if (rs.next()) {
				num = rs.getInt(1) + 1; //새글번호만들기
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			System.out.println(chatBean.getChat_content());
			sql = "INSERT INTO chat VALUES (?,?,?,?,NOW(),?)";
			pstmt = con.prepareStatement(sql);

			//		 pstmt.setInt(1, chatBean.getChat_id());
			pstmt.setInt(1, num);
			pstmt.setString(2, chatBean.getChat_to_id());
			pstmt.setString(3, chatBean.getChat_from_id());
			pstmt.setString(4, chatBean.getChat_content());
			pstmt.setInt(5, chatBean.getBoard_id());

			insertCount = pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("insertChat 오류!" + e.getMessage());
			e.printStackTrace();
		} finally {

			close(rs);
			close(pstmt);

		}

		return insertCount;
	}

	//===============================SELECT========================================
	//	chat 내용조회
	public ArrayList<ChatBean> selectListCount(int board_id) {
		System.out.println("selectListCount()");
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		ArrayList<ChatBean> list = new ArrayList<ChatBean>();

		try {
			String sql = "select * from chat where board_id=? ORDER BY chat_date";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, board_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ChatBean chatBean = new ChatBean();
				chatBean.setBoard_id(rs.getInt("board_id"));
				chatBean.setChat_content(rs.getString("chat_content"));
				chatBean.setChat_from_id(rs.getString("chat_from_id"));
				chatBean.setChat_to_id(rs.getString("chat_to_id"));
				chatBean.setChat_date(rs.getTimestamp("chat_date"));
				//레코드 저장 확인용 코드

				list.add(chatBean);
			}

		} catch (SQLException e) {
			System.out.println("selectListCount()오류-" + e.getMessage());
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);

		}

		return list;
	}

	//===============================SELECT===================================
	//채팅 작성자 확인
	public boolean isArticleChatWriter(String chat_to_id, String chat_from_id) {
		boolean isArticleWriter = false;

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			String sql = "select chat_to_id from chat where chat_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, chat_to_id);
			pstmt.setString(2, chat_from_id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				if (chat_to_id.equals(rs.getString("chat_to_id"))) {
					isArticleWriter = false;
				}
			}

		} catch (SQLException e) {
			System.out.println("isArticleChatWriter()오류 - " + e.getMessage());

			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}

		return isArticleWriter;
	}
	
	public ArrayList<ChatListBean> selectChatList(String user_id){
		ArrayList<ChatListBean> list = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT b.board_id, b.board_subject, c.chat_to_id, c.chat_from_id, c.chat_content, c.chat_date FROM board b "
					+ "JOIN  (SELECT * FROM chat WHERE chat_to_id = ? OR chat_from_id = ? ORDER BY chat_date DESC LIMIT 1000000) c "
					+ "ON c.board_id = b.board_id "
					+ "GROUP BY b.board_id"; 
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user_id);
			pstmt.setString(2, user_id);
			rs = pstmt.executeQuery();
			list = new ArrayList<ChatListBean>();
			ChatListBean chatListBean = null;
			while(rs.next()) {
				chatListBean = new ChatListBean();
				chatListBean.setBoard_id(rs.getInt("board_id"));
				chatListBean.setBoard_subject(rs.getString("board_subject"));
				chatListBean.setChat_content(rs.getString("chat_content"));
				chatListBean.setChat_date(rs.getTimestamp("chat_date"));
				chatListBean.setUser_id("chat_to_id");
				chatListBean.setFrom_id(rs.getString("chat_from_id"));
				list.add(chatListBean);
			}
		} catch (Exception e) {
			System.out.println("selectChatList() 오류 - "+ e.getMessage());
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return list;
	}
	
	public int deleteChat(int board_id) {
		System.out.println("ChatDAO - deleteChat()");
		int deleteCount = 0;
		PreparedStatement pstmt = null;
		
		try {
			String sql = "DELETE FROM chat WHERE board_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, board_id);
			deleteCount = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("deleteChat() 오류! - " + e.getMessage());
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return deleteCount;
	}
}
//===========================================================================
