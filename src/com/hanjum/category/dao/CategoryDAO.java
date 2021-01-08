package com.hanjum.category.dao;

import static com.hanjum.db.JdbcUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.hanjum.category.vo.CategoryBean;

public class CategoryDAO {
	private CategoryDAO() {};
	private static CategoryDAO instance = new CategoryDAO();
	
	public static CategoryDAO getInstance() {
		return instance;
	}
	
	Connection con;
	
	public void setConnection(Connection con) {
		this.con=con;
	}
	
	public int insertCategory(CategoryBean cb) {
		System.out.println("insertCategory");
		int insertCount = 0;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int num = 0;
		try {
			String sql="select max(category_id) from category";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				num=rs.getInt(1)+1;
			}
			sql = "insert into category values(?,?)";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1,num);
			pstmt.setString(2,cb.getCategory_content());
			insertCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(rs);
		}
		
		
		
		return insertCount;
	}

	public int categoryListCount() {
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql="select count(category_id) from category";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				listCount = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		
		
		return listCount;
	}

	public ArrayList<CategoryBean> selectCategoryList() {
		System.out.println("selectCategoryList");
		ArrayList<CategoryBean> categoryList=null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql="select * from category order by category_id ";
			pstmt=con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			categoryList = new ArrayList<CategoryBean>();
			while(rs.next()) {
				CategoryBean categoryBean = new CategoryBean();
				categoryBean.setCategory_id(rs.getInt("category_id"));
				categoryBean.setCategory_content(rs.getString("category_content"));
				categoryList.add(categoryBean);
			}
		} catch (SQLException e) {
			System.out.println("오류 : " +  e);
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		
		
		return categoryList;
	}

	public int deleteCategory(CategoryBean cb) {
		int deleteCount = 0;
		PreparedStatement pstmt=null;
		try {
			String sql="delete from category where category_id=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1,cb.getCategory_id());
			deleteCount = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		
		
		return deleteCount;
	}

	public int updateCategory(CategoryBean cb) {
		int updateCount = 0;
		PreparedStatement pstmt=null;
		try {
			String sql="update category set category_content=? where category_id=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, cb.getCategory_content());
			pstmt.setInt(2,cb.getCategory_id());
			updateCount = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return updateCount;
	}

	public boolean getContent(String category_content) {
		boolean isContent = false;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql="select category_content from category";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				if(category_content.equals(rs.getString(1))) {
					System.out.println("찾은 카테고리 : "+ rs.getString(1));
					isContent = true;
				}
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return isContent;
	}
	
	
}
