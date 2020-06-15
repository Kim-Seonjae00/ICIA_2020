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
			System.out.println("0.DB���� | 1.����� ���� | 2.�������� | 3.ȸ��Ż�� | 4.������ ");
			int act = sc.nextInt();
			switch (act) {
			case 0 :
				sql.dbConnection();
				break;
			case 1 :
				System.out.print("���̵� : ");
				String id = sc.next();
				System.out.print("��й�ȣ : ");
				String pw = sc.next();
				System.out.print("�̸� : ");
				String name = sc.next();
				System.out.print("��ȭ��ȣ : ");
				String tel = sc.next();
				sql.sign(id, pw, name, tel);
				break;
			case 2 : 
				System.out.print("���̵� : ");
				id = sc.next();
				System.out.print("��й�ȣ : ");
				pw = sc.next();
				if(sql.checkId(id, pw)) {
					System.out.println("������ ����");
					System.out.println("1. ���̵� | 2.��й�ȣ | 3.��ȭ��ȣ");
					System.out.print("����> ");
					act = sc.nextInt();
					System.out.print("������ ���� : ");
					String changeVal = sc.next();
					sql.modifyMember(id, pw, act, changeVal);
				}else System.out.println("���̵� �Ǵ� ��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
				break;
			case 3 :
				System.out.print("���̵� : ");
				id = sc.next();
				System.out.print("��й�ȣ : ");
				pw = sc.next();
				if(sql.checkId(id, pw)) {
					sql.withdrawMember(id);
				}else System.out.println("���̵�Ǵ� ��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
				break;
			case 4 :
				System.out.print("���̵� : ");
				id = sc.next();
				System.out.print("��й�ȣ : ");
				pw = sc.next();
				admin = sql.adminCheck(id, pw);
				if(admin) {
					sql.warning();
					while(true) {
						System.out.println("1.��� Ȯ�� | 2.��ǰ �԰� | 3.����� ȸ����� | 4.���� | 5.����");
						act = sc.nextInt();
						if(act == 5) {
							break;
						}
						if(act == 1) {
							System.out.print("Ȯ���Ϸ��� ǰ��(*�� ��� ǰ��) : ");
							String iName = sc.next();
							sql.selectItem(iName);
						}else if(act == 2) {
							System.out.print("ǰ�� : ");
							String iName = sc.next();
							System.out.print("���� : ");
							int price = sc.nextInt();
							System.out.print("���� : ");
							int stock = sc.nextInt();
							System.out.print("�������(YYYYMMDD) : ");
							String iexpire = sc.next();
							sql.wareHousing(iName, price, stock, iexpire);
						}else if(act == 3) {
							sql.selectMember();
						}else if(act == 4) {
							sql.calculate();
						}
					}
				}else System.out.println("������ ������ �ƴմϴ�.");
				break;
			case 22 :
				sql.selectMember();
				break;
			}

		}
	}

}


