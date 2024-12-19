package project;

import java.sql.*;

public class DBsql {
	Connection con =  null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	public void dbConnection() {
		con = DBConnection.makeConnection();
	}

	public void sign(String id, String pw, String name, String tel) {
		String sql = "SELECT * FROM MEMBER1 WHERE ID = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				System.out.println("중복된 아이디입니다.");
			}else {
				sql = "INSERT INTO MEMBER1 VALUES(MEMBER1_seq.NextVal,?,?,?,?,'BRONZE',0)";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, id);
				pstmt.setString(2, pw);
				pstmt.setString(3, name);
				pstmt.setString(4, tel);
				int result = pstmt.executeUpdate();
				if(result == 1) {
					System.out.println("가입이 완료되었습니다.");
				}else System.out.println("가입에 실패하였습니다.");
			}
		} catch (SQLException e1) {
			System.out.println("가입에 실패하였습니다.");
			e1.printStackTrace();
		}
	}

	public boolean checkId(String id, String pw) {
		boolean check = false;
		String sql = "SELECT * FROM MEMBER1 WHERE ID = ? AND PASSWORD = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				check = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return check;
	}

	public void modifyMember(String id, String pw, int act, String changeVal) {
		String change = null;
		String sql = "SELECT * FROM MEMBER1 WHERE ID = ?";
		try {
			String checkId = rs.getString("ID");
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, changeVal);
			rs = pstmt.executeQuery();
			if(rs.next() && act == 1) {
				System.out.println("중복된 아이디 입니다.");
			}else {
				switch(act) {
				case 1:
					change = "ID";
					break;
				case 2:
					change = "PASSWORD";
					break;
				case 3:
					change = "TEL";
					break;
				}
				sql = "UPDATE MEMBER1 SET "+change+" = ? WHERE ID = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1,changeVal);
				pstmt.setString(2,checkId);
				int result = pstmt.executeUpdate();
				if(result == 1) {
					System.out.println("수정이 완료되었습니다.");
				}else System.out.println("수정에 실패하였습니다.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void selectMember() {
		String sql = "SELECT * FROM MEMBER1";
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				System.out.print("멤버십 번호 : "+rs.getString(1));
				System.out.print(" 아이디 : "+rs.getString(2));
				System.out.print(" 비밀번호 : "+rs.getString(3));
				System.out.print(" 이름 : "+rs.getString(4));
				System.out.print(" 전화번호 : "+rs.getString(5));
				System.out.print(" 멤버십 등급 : "+rs.getString(6));
				System.out.println(" 포인트 : "+rs.getString(7));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void withdrawMember(String id) {
		String sql = "DELETE FROM MEMBER1 WHERE ID = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			int result = pstmt.executeUpdate();
			if(result == 1) {
				System.out.println("탈퇴가 정상적으로 완료되었습니다.");
			}else System.out.println("탈퇴가 정상적으로 이루어지지 않았습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean adminCheck(String id, String pw) {
		String sql = "SELECT * FROM MEMBER1 WHERE ID = 'admin'";
		boolean check = false;
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				if(rs.getString("PASSWORD").equals(pw)){
					System.out.println("로그인 되었습니다.");
					check = true;
				}else System.out.println("아이디 또는 비밀번호가 일치하지 않습니다.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return check;
	}


	public void selectItem(String iName) {
		String str = "WHERE INAME = '"+iName+"'";
		String sql = "SELECT INUM,INAME,STOCK,IEXPIRE,DEADLINE FROM POS ";
		if(!iName.equals("*")) {
			sql = sql+str;
		}
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			int next = 0;
			while(true) {
				if(rs.next()) {
					System.out.println("제품번호 : "+rs.getInt(1));
					System.out.println("제품명 : "+rs.getString(2));
					System.out.println("재고량 : "+rs.getInt(3));
					System.out.println("유통기한 : "+ rs.getString(4));
					System.out.println("남은 기한 : "+rs.getInt(5));
					System.out.println();
					next = 1;
				}else {
					break;
				}
			}
			if(next==0) {
				System.out.println("존재하지 않는 품목입니다.");				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void wareHousing(String iName, int price, int stock, String iexpire) {
		String sql = "SELECT * FROM POS WHERE INAME = ? AND IEXPIRE = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, iName);
			pstmt.setString(2, iexpire);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				System.out.println(iName + "은(는) 이미 존재하는 항목입니다.\n재고 개수를 추가하겠습니다.");
				stock += rs.getInt("STOCK");
				sql = "UPDATE POS SET STOCK = ? WHERE INAME = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, stock);
				pstmt.setString(2, iName);
				int result = pstmt.executeUpdate();
				if(result == 1) {
					System.out.println("입고되었습니다.");
				}else System.out.println("입고에 실패하였습니다.");
			}else {
				sql = "INSERT INTO POS VALUES(POS_seq.NextVal,?,?,?,?,TO_DATE(?) - TO_DATE(TO_CHAR(SYSDATE,'YYYYMMDD')))";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, iName);
				pstmt.setInt(2, price);
				pstmt.setInt(3, stock);
				pstmt.setString(4, iexpire);
				pstmt.setString(5, iexpire);
				int result = pstmt.executeUpdate();
				if(result == 1) {
					System.out.println("입고되었습니다.");
				}else System.out.println("입고에 실패하였습니다.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void warning() {
		String sql = "SELECT * FROM POS";
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				if(1>=rs.getInt("DEADLINE")) {
					System.out.println(rs.getString("INAME")+"의 유통기한이 얼마 남지 않았습니다.");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void calculate() {
		String sql = "SELECT POINT FROM MEMBER1 WHERE ID = 'admin'";
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()){
				System.out.println("매출액은 "+ rs.getInt(1)+"원입니다.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
