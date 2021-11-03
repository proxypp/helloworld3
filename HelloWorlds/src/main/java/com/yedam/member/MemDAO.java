package com.yedam.member;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemDAO extends DAO {
	
	//한건 조회.
	public MemberVO getMember(String id) {
		MemberVO vo = null;
		String sql = "select * from member where user_id= ?";
		connect();
		try {
			vo = new MemberVO();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			rs = psmt.executeQuery();
			if(rs.next()) {
				vo.setAddress(rs.getString("address"));
				vo.setBirth_date(rs.getDate("birth_date"));
				vo.setGender(rs.getString("gender"));
				vo.setPhone(rs.getString("phone"));
				vo.setUserId(rs.getString("user_id"));
				vo.setUserName(rs.getString("user_name"));
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return vo;
	}
	
	
	// 한건 입력.
	public void insertMember(MemberVO vo) {
		String sql = "insert into member(user_id, user_name, address) values(?,?,?)";
		connect();
		
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getUserId());
			psmt.setString(2, vo.getUserName());
			psmt.setString(3, vo.getAddress());
			int r = psmt.executeUpdate();
			System.out.println(r + "건 입력됨. ");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			disconnect();
		}
		
	}
	
	//전체 리스트.
	public List<MemberVO> getMemberList() {
		String sql = "select * from member order by 1";
		connect();
		List<MemberVO> memberList = new ArrayList<MemberVO>();

		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				MemberVO vo = new MemberVO();
				vo.setAddress(rs.getString("address"));
				vo.setBirth_date(rs.getDate("birth_date"));
				vo.setGender(rs.getString("gender"));
				vo.setPhone(rs.getString("phone"));
				vo.setUserId(rs.getString("user_id"));
				vo.setUserName(rs.getString("user_name"));
				memberList.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}

		return memberList;
	}
}
