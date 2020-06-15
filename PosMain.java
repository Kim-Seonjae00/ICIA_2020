package project;

import java.util.*;
import java.sql.*;

public class PosMain {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		DBsql sql = new DBsql();
		ResultSet rs = null;
		boolean admin = false;
		boolean run = true;
		while(run) {
			System.out.println("0.DB연결 | 1.멤버십 가입 | 2.정보수정 | 3.회원탈퇴 | 4.관리자 ");
			int act = sc.nextInt();
			switch (act) {
			case 0 :
				sql.dbConnection();
				break;
			case 1 :
				System.out.print("아이디 : ");
				String id = sc.next();
				System.out.print("비밀번호 : ");
				String pw = sc.next();
				System.out.print("이름 : ");
				String name = sc.next();
				System.out.print("전화번호 : ");
				String tel = sc.next();
				sql.sign(id, pw, name, tel);
				break;
			case 2 : 
				System.out.print("아이디 : ");
				id = sc.next();
				System.out.print("비밀번호 : ");
				pw = sc.next();
				if(sql.checkId(id, pw)) {
					System.out.println("수정할 정보");
					System.out.println("1. 아이디 | 2.비밀번호 | 3.전화번호");
					System.out.print("선택> ");
					act = sc.nextInt();
					System.out.print("수정할 내용 : ");
					String changeVal = sc.next();
					sql.modifyMember(id, pw, act, changeVal);
				}else System.out.println("아이디 또는 비밀번호가 일치하지 않습니다.");
				break;
			case 3 :
				System.out.print("아이디 : ");
				id = sc.next();
				System.out.print("비밀번호 : ");
				pw = sc.next();
				if(sql.checkId(id, pw)) {
					sql.withdrawMember(id);
				}else System.out.println("아이디또는 비밀번호가 일치하지 않습니다.");
				break;
			case 4 :
				System.out.print("아이디 : ");
				id = sc.next();
				System.out.print("비밀번호 : ");
				pw = sc.next();
				admin = sql.adminCheck(id, pw);
				if(admin) {
					sql.warning();
					while(true) {
						System.out.println("1.재고량 확인 | 2.제품 입고 | 3.멤버십 회원목록 | 4.정산 | 5.종료");
						act = sc.nextInt();
						if(act == 5) {
							break;
						}
						if(act == 1) {
							System.out.print("확인하려는 품목(*은 모든 품목) : ");
							String iName = sc.next();
							sql.selectItem(iName);
						}else if(act == 2) {
							System.out.print("품명 : ");
							String iName = sc.next();
							System.out.print("가격 : ");
							int price = sc.nextInt();
							System.out.print("수량 : ");
							int stock = sc.nextInt();
							System.out.print("유통기한(YYYYMMDD) : ");
							String iexpire = sc.next();
							sql.wareHousing(iName, price, stock, iexpire);
						}else if(act == 3) {
							sql.selectMember();
						}else if(act == 4) {
							sql.calculate();
						}
					}
				}else System.out.println("관리자 계정이 아닙니다.");
				break;
			case 22 :
				sql.selectMember();
				break;
			}

		}
	}

}


