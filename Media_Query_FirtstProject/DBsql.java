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
				System.out.println("�ߺ��� ���̵��Դϴ�.");
			}else {
				sql = "INSERT INTO MEMBER1 VALUES(MEMBER1_seq.NextVal,?,?,?,?,'BRONZE',0)";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, id);
				pstmt.setString(2, pw);
				pstmt.setString(3, name);
				pstmt.setString(4, tel);
				int result = pstmt.executeUpdate();
				if(result == 1) {
					System.out.println("������ �Ϸ�Ǿ����ϴ�.");
				}else System.out.println("���Կ� �����Ͽ����ϴ�.");
			}
		} catch (SQLException e1) {
			System.out.println("���Կ� �����Ͽ����ϴ�.");
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
				System.out.println("�ߺ��� ���̵� �Դϴ�.");
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
					System.out.println("������ �Ϸ�Ǿ����ϴ�.");
				}else System.out.println("������ �����Ͽ����ϴ�.");
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
				System.out.print("����� ��ȣ : "+rs.getString(1));
				System.out.print(" ���̵� : "+rs.getString(2));
				System.out.print(" ��й�ȣ : "+rs.getString(3));
				System.out.print(" �̸� : "+rs.getString(4));
				System.out.print(" ��ȭ��ȣ : "+rs.getString(5));
				System.out.print(" ����� ��� : "+rs.getString(6));
				System.out.println(" ����Ʈ : "+rs.getString(7));
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
				System.out.println("Ż�� ���������� �Ϸ�Ǿ����ϴ�.");
			}else System.out.println("Ż�� ���������� �̷������ �ʾҽ��ϴ�.");
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
					System.out.println("�α��� �Ǿ����ϴ�.");
					check = true;
				}else System.out.println("���̵� �Ǵ� ��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
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
					System.out.println("��ǰ��ȣ : "+rs.getInt(1));
					System.out.println("��ǰ�� : "+rs.getString(2));
					System.out.println("��� : "+rs.getInt(3));
					System.out.println("������� : "+ rs.getString(4));
					System.out.println("���� ���� : "+rs.getInt(5));
					System.out.println();
					next = 1;
				}else {
					break;
				}
			}
			if(next==0) {
				System.out.println("�������� �ʴ� ǰ���Դϴ�.");				
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
				System.out.println(iName + "��(��) �̹� �����ϴ� �׸��Դϴ�.\n��� ������ �߰��ϰڽ��ϴ�.");
				stock += rs.getInt("STOCK");
				sql = "UPDATE POS SET STOCK = ? WHERE INAME = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, stock);
				pstmt.setString(2, iName);
				int result = pstmt.executeUpdate();
				if(result == 1) {
					System.out.println("�԰�Ǿ����ϴ�.");
				}else System.out.println("�԰� �����Ͽ����ϴ�.");
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
					System.out.println("�԰�Ǿ����ϴ�.");
				}else System.out.println("�԰� �����Ͽ����ϴ�.");
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
					System.out.println(rs.getString("INAME")+"�� ��������� �� ���� �ʾҽ��ϴ�.");
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
				System.out.println("������� "+ rs.getInt(1)+"���Դϴ�.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
