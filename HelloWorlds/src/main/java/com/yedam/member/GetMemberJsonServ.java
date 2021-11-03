package com.yedam.member;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Servlet implementation class GetMemberjsonServ
 */
@WebServlet("/GetMemberJsonServ")
public class GetMemberJsonServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetMemberJsonServ() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		//{"name": "Hongkildong", "age":20, "phone" : "010-1234-5678"}
		// "{\"name":"hong"}";
		// "[{\"id\":\"?\", \"name\":?, birth:?, gender:?}, {}, {}]";
		MemDAO dao = new MemDAO();
		List<MemberVO> list = dao.getMemberList();
//		int cnt = list.size();
//		out.println("[");
//		for(int i = 0; i<cnt; i++) {
//			out.println("{\"id\":\""+list.get(i).getUserId() 
//					+"\", \"name\":\""+list.get(i).getUserName()
//					+ "\", \"birth\":\""+list.get(i).getBirth_date()
//					+ "\", \"gender\":\""+list.get(i).getGender()+ "\"}");
//			if(i != cnt-1) {
//				out.println(",");
//			}
//		}
//		out.println("]");
		Gson gson = new GsonBuilder().create();
		out.println(gson.toJson(list));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		MemDAO dao = new MemDAO();
		String userId = request.getParameter("id");
		String userName = request.getParameter("name");
		String address =  request.getParameter("addr");
		
		MemberVO vo = new MemberVO();
		vo.setUserId(userId);
		vo.setUserName(userName);
		vo.setAddress(address);
		
		dao.insertMember(vo);
		
		response.getWriter().println("ok");
	}

}
