package com.hanjum.help.dao;

import static com.hanjum.db.JdbcUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.hanjum.help.vo.HelpBean;

public class HelpDAO {
	
	private HelpDAO() {}
	
	private static HelpDAO instance =new HelpDAO();
	
	public static HelpDAO getInstance() {
		return instance;
		
	}
	Connection con ;
	
	public void setConnection(Connection con) {
		this.con = con;
	}
	public int insertHelp(HelpBean helpBean) {
		System.out.println("HelpBean - insertHelp()");
		int insertCount = 0;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int num = 1 ;
		
		try {
			String sql ="SELECT MAX(help_id) FROM help";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			
			if(rs.next()) {
				num =rs.getInt(1) + 1;
			}
			sql = "insert into help values(?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, helpBean.getHelp_question());
			pstmt.setString(3, helpBean.getHelp_answer());
			
			insertCount = pstmt.executeUpdate();
			
		}catch(SQLException e) {
			System.out.println("insertHelp() 오류 ! - " +e.getMessage());
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return insertCount;
		
	}

	public ArrayList<HelpBean> selectHelpList(int page, int limit) {
		// TODO Auto-generated method stub
		ArrayList<HelpBean> list = null;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int startRow = (page-1) * limit;
		
		
		try {
			String sql = "SELECT help_id, help_question, help_answer FROM help limit ?,?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, limit);
			rs = pstmt.executeQuery();
			
			list = new ArrayList<HelpBean>();
			
			while(rs.next()) {
				HelpBean temp = new HelpBean();
				temp.setHelp_id(rs.getInt("help_id"));
				temp.setHelp_question(rs.getString("help_question"));
				temp.setHelp_answer(rs.getString("help_answer"));
				
				list.add(temp);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return list;
	}
	public ArrayList<HelpBean> selectSearchHelpList(int page, int limit, String search) {
		// TODO Auto-generated method stub
		ArrayList<HelpBean> list = null;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int startRow = (page-1) * limit;
		System.out.println(search);
		System.out.println(startRow);
		try {
			String sql = "SELECT * FROM help where help_question like ? limit ?,?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%"+search+"%");
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, limit);
			rs = pstmt.executeQuery();
			
			list = new ArrayList<HelpBean>();
			
			while(rs.next()) {
				HelpBean temp = new HelpBean();
				temp.setHelp_id(rs.getInt("help_id"));
				temp.setHelp_question(rs.getString("help_question"));
				temp.setHelp_answer(rs.getString("help_answer"));
				
				list.add(temp);
				System.out.println(rs.getString("help_question"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return list;
	}
	
	
	public int selectListCount() {
		int listCount = 0;
		
		PreparedStatement pstmt= null;
		ResultSet rs = null;
		try {
			String sql ="SELECT COUNT(help_id) FROM help";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				listCount = rs.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println("selectListCount() 오류! - " + e.getMessage());
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return listCount;
	}
	public int updateHelp(HelpBean helpBean) {
		int updateCount = 0;
		PreparedStatement pstmt = null;
		
		String sql="update help set help_question=?, help_answer=? where help_id=?";
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, helpBean.getHelp_question());
			pstmt.setString(2, helpBean.getHelp_answer());
			pstmt.setInt(3, helpBean.getHelp_id());
			updateCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("updateHelp sql 오류!"+ e.getMessage());
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return updateCount;
	}
	public int delectHelp(HelpBean helpBean) {
		int delectCount = 0;
		PreparedStatement pstmt = null;
		
		String sql="delete from help where help_id=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, helpBean.getHelp_id());
			delectCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("delect sql 오류 !");
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
	
		return delectCount;
	}
	public int selectSearchListCount(String search) {
	int listCount = 0;
		
		PreparedStatement pstmt= null;
		ResultSet rs = null;
		try {
			String sql ="SELECT COUNT(help_id) FROM help where help_question like ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%"+search+"%");
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				listCount = rs.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println("selectListCount() 오류! - " + e.getMessage());
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return listCount;
	}
}
