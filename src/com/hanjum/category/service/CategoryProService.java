package com.hanjum.category.service;

import static com.hanjum.db.JdbcUtil.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import com.hanjum.category.dao.CategoryDAO;
import com.hanjum.category.vo.CategoryBean;

public class CategoryProService {
	private CategoryDAO categoryDAO;
	private Connection con;
	
	public CategoryProService() {
		categoryDAO = CategoryDAO.getInstance();
		con = getConnection(); 
		categoryDAO.setConnection(con);
	}
	
	public boolean insertCategory(CategoryBean cb) {
		System.out.println("CategoryWriteProService");
		boolean isWriteSuccess = false;
		
		int insertCount = categoryDAO.insertCategory(cb);
		System.out.println(insertCount);
		if(insertCount >0) {
				commit(con);
				isWriteSuccess=true;
		}else {
				rollback(con);
		}
		
		close(con);
		
		return isWriteSuccess;
	}

	public int getListCount() {
		int listCount = 0;
		
		listCount=categoryDAO.categoryListCount();
		
		close(con);
		
		return listCount;
	}

	public ArrayList<CategoryBean> getCategoryList() {
		System.out.println("getCategoryList");
		ArrayList<CategoryBean> categoryList = null;
		
		categoryList = categoryDAO.selectCategoryList();
		
		close(con);
		
		return categoryList;
	}

	public boolean deleteCategory(CategoryBean cb) {
		System.out.println("CategoryWriteProService");
		boolean isDeleteSuccess = false;
		
		int deleteCount = categoryDAO.deleteCategory(cb);
		System.out.println(deleteCount);
		if(deleteCount >0) {
				commit(con);
				isDeleteSuccess=true;
		}else {
				rollback(con);
		}
		
		close(con);
		
		return isDeleteSuccess;
	}

	public boolean updateCategory(CategoryBean cb) {
		boolean isUpdateSuccess = false;
		System.out.println("CategoryWriteProService");
		
		int updateCount = categoryDAO.updateCategory(cb);
		System.out.println(updateCount);
		if(updateCount >0) {
				commit(con);
				isUpdateSuccess=true;
		}else {
				rollback(con);
		}
		
		close(con);
		return isUpdateSuccess;
	}

	public boolean getContent(String category_content) {
		
		boolean isContent=categoryDAO.getContent(category_content);
		
		close(con);
		
		return isContent;
	}
	
	
}
