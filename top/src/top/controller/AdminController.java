package top.controller;

import java.util.ArrayList;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import top.frame.Biz;
import top.vo.ChainVO;
import top.vo.HeadquarterVO;
import top.vo.NotiVO;
import top.vo.UserVO;

@Controller


public class AdminController {
	
	@Resource(name = "chainbiz")
	Biz<String, ChainVO> chainbiz;	

	@Resource(name = "notibiz")
	Biz<String, NotiVO> notibiz;

		
	// adminpage �ٽ� �����ϱ� !
	
	@RequestMapping("/admin.top")
	public ModelAndView admin(HttpServletRequest request) {
		System.out.println("enter into admin.top ");
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		String hqid = (String) session.getAttribute("loginId");
		ArrayList<ChainVO> chainvolist = chainbiz.getbyhq(hqid);
		System.out.println("chainvo" + chainvolist);
		ArrayList<String> chainlist = new ArrayList<String>();
		
		for(ChainVO element : chainvolist) {
			chainlist.add(element.getChainID());
		}
		
		//System.out.println("chainlist :" + chainlist);
		
		ArrayList<NotiVO> arr = notibiz.get();
		//System.out.println("arr :" + arr);
		ArrayList<NotiVO> notilist = new ArrayList<NotiVO>();
		
		for(int i=0; i<arr.size();i++) {
			for(String element : chainlist) {
				if(arr.get(i).getChainID().contains(element)) {
					notilist.add(arr.get(i));
				}
			}
		}
		
		System.out.println("success!");
			
		mv.addObject("center", "../user/admin");
		mv.addObject("notilist",notilist);
		mv.setViewName("main/main");
		return mv;
	}
	
	
	
	// admin ��û�Ϸ� page
	
	




}


