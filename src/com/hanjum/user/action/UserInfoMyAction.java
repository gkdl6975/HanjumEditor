package com.hanjum.user.action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hanjum.action.Action;
import com.hanjum.board.service.EditorProService;
import com.hanjum.contract.service.ContractGetSuccessCount;
import com.hanjum.notice.service.NoticeProService;
import com.hanjum.user.service.UserProService;
import com.hanjum.user.vo.EditorBean;
import com.hanjum.user.vo.UserBean;
import com.hanjum.vo.ActionForward;

public class UserInfoMyAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		HttpSession session = request.getSession();
		UserBean userSession = (UserBean)session.getAttribute("userBean");
		String user_id = userSession.getUser_id();
		UserProService userInfoService = new UserProService();
		UserBean userBean = userInfoService.getUserInfo(user_id);
		request.setAttribute("userBean", userBean);
		userInfoService = new UserProService();
		EditorBean editorBean = userInfoService.getEditorInfo(user_id);
		
		EditorProService editorProService = new EditorProService();
		boolean isEditorInfo = editorProService.checkEditorInfo(user_id);
		
		String editor_program = "", editor_solution = "", editor_inventory = "", 
				editor_upload = "", editor_work = "", editor_meeting = "", editor_fort = "",
						editor_sample = "", editor_ed_min_price = "", editor_ed_max_price = "",
								editor_address = "";
		HashMap<String, String> editorInfo = null;
		if(editorBean != null) {
			editorInfo = new HashMap<String, String>();
			
			editor_program = editorBean.getEditor_program().replace("1", "프리미어").replace("2", "파이널컷")
					.replace("3", "베가스").replace("4", "에프터 이펙트").replace("5", "기타");
			editor_solution = editorBean.getEditor_solution().replace("1", "HD").replace("2", "FHD")
					.replace("3", "UHD").replace("4", "맞춤 가능");
			editor_inventory = editorBean.getEditor_inventory().replace("1", "컷 편집").replace("2", "오디오 싱크")
					.replace("3", "BGM 삽입").replace("4", "효과음 삽입").replace("5", "모션 그래픽").replace("6", "템플릿 작업");
			
			switch (editorBean.getEditor_upload()) {
			case 1: editor_upload = "이메일"; break;
			case 2: editor_upload = "NAS"; break;
			case 3: editor_upload = "웹하드"; break;
			case 4: editor_upload = "모두 가능"; break;
			}
			
			switch (editorBean.getEditor_work()) {
			case 1: editor_work = "1캠"; break;
			case 2: editor_work = "2캠"; break;
			case 3: editor_work = "3캠"; break;
			case 4: editor_work = "4캠 이상"; break;
			}
			
			switch (editorBean.getEditor_meeting()) {
			case 1: editor_meeting = "가능"; break;
			case 2: editor_meeting = "불가능"; break;
			}
			
			switch (editorBean.getEditor_fort()) {
			case 1: editor_fort = "건당 계약"; break;
			case 2: editor_fort = "단기 계약"; break;
			case 3: editor_fort = "장기 계약"; break;
			}
			
			switch (editorBean.getEditor_sample()) {
			case 1: editor_sample = "가능"; break;
			case 2: editor_sample = "불가능"; break;
			}
			if(editorBean.getEditor_ed_min_price() != 0) {
				if((editorBean.getEditor_ed_min_price() / 1000) < 10){
					editor_ed_min_price = (editorBean.getEditor_ed_min_price() / 1000)+"천원";
				} else {
					if(editorBean.getEditor_ed_min_price() % 10000 > 0){
						editor_ed_min_price = (editorBean.getEditor_ed_min_price() / 10000.0)+"만원";
					} else {
						editor_ed_min_price = (editorBean.getEditor_ed_min_price() / 10000)+"만원";	
					}
				}
			} else {
				editor_ed_min_price = "0원";
			}
			if(editorBean.getEditor_ed_max_price() != 0) {
				if((editorBean.getEditor_ed_max_price() / 1000) < 10){
					editor_ed_max_price = (editorBean.getEditor_ed_max_price() / 1000)+"천원";
				} else {
					if(editorBean.getEditor_ed_max_price() % 10000 > 0){
						editor_ed_max_price = (editorBean.getEditor_ed_max_price() / 10000.0)+"만원";
					} else {
						editor_ed_max_price = (editorBean.getEditor_ed_max_price() / 10000)+"만원";	
					}
				}
			} else {
				editor_ed_max_price = "0원";
			}
			editor_address = editorBean.getEditor_address();
			
			editorInfo.put("program",editor_program);
			editorInfo.put("solution",editor_solution);
			editorInfo.put("inventory",editor_inventory);
			editorInfo.put("upload",editor_upload);
			editorInfo.put("work",editor_work);
			editorInfo.put("meeting",editor_meeting);
			editorInfo.put("fort",editor_fort);
			editorInfo.put("sample",editor_sample);
			editorInfo.put("min_price",editor_ed_min_price);
			editorInfo.put("min_price_val",editorBean.getEditor_ed_min_price()+"");
			editorInfo.put("max_price",editor_ed_max_price);
			editorInfo.put("max_price_val",editorBean.getEditor_ed_max_price()+"");
			editorInfo.put("address",editor_address);
			editorInfo.put("status", editorBean.getEditor_status()+"");
			editorInfo.put("des", editorBean.getEditor_des());
			editorInfo.put("profile", editorBean.getEditor_profile());
			editorInfo.put("photo", editorBean.getEditor_photo());
			editorInfo.put("follower", editorBean.getEditor_like()+"");
		}
		
		request.setAttribute("editorInfo", editorInfo);
		
		int readyStatus = 0;
		if(isEditorInfo == true) {
			readyStatus = 1;
			request.setAttribute("readyStatus", readyStatus);
		} else {
			request.setAttribute("readyStatus", readyStatus);
		}
		
		NoticeProService noticeProService = new NoticeProService();
		int noticeCount = noticeProService.getNoticeCount(user_id);
		request.setAttribute("noticeCount", noticeCount);
		
		ContractGetSuccessCount getSuccessService = new ContractGetSuccessCount();
		int successCount = getSuccessService.getSuccessCount(user_id);
		request.setAttribute("successCount", successCount);
		
		if(userBean != null) {
			forward = new ActionForward();
			forward.setPath("user/userInfo.jsp");
		}
		return forward;
	}

}
