package com.hanjum.user.action;

import java.io.File;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hanjum.action.Action;
import com.hanjum.user.service.UserProService;
import com.hanjum.vo.ActionForward;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class UserPhotoUploadAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("UserPhotoUploadAction");
		String user_id = request.getParameter("user_id");
		ServletContext context = request.getServletContext();
		String saveFolder = "/editorUserPhotoUpload/"+user_id;
		String realFolder = context.getRealPath(saveFolder);
		File fileDir = new File(realFolder);
		if(!fileDir.exists()) { // 폴더가 없으면 만들기
			fileDir.mkdir();
		} else {
			File[] photoFile = fileDir.listFiles(); // 폴더 존재하면 옛날 사진 삭제후 폴더 삭제
			for(int i = 0; i < photoFile.length; i++) {
				photoFile[i].delete();
			}
			if(photoFile.length == 0 && fileDir.isDirectory()) {
				fileDir.delete();
			}
			if(!fileDir.exists()) { // 폴더 새로 만들기
				fileDir.mkdir();
			}
		}
		int fileSize = 1024 * 1024 * 10; // 10MB
		MultipartRequest multi = new MultipartRequest(
				request, // HttpServletRequest(request) 객체 
				realFolder, // 실제 업로드 폴더
				fileSize, // 한번에 업로드 가능한 1개파일 최대 크기
				"UTF-8", // 파일명에 대한 인코딩 방식
				new DefaultFileRenamePolicy() // 파일명 중복 시 중복 처리 객체
		);
		String editor_photo = user_id+"/"+multi.getOriginalFileName("editor_photo");
		String photo_ex = editor_photo.substring(editor_photo.lastIndexOf("."));
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter(); 

		if(photo_ex.contains("jpg") || photo_ex.contains("jpeg") || photo_ex.contains("png") || photo_ex.contains("gif")) {
			UserProService userProService = new UserProService();
			boolean isSuccess = userProService.updatePhoto(user_id, editor_photo);
			if(isSuccess) {
				out.println(editor_photo);
			}
		} else {
			out.println("isNotPhoto");
		}
		
		
		return null;
	}

}
