package service;

import static db.JdbcUtil.*;

import java.sql.Connection;
import java.util.*;

import dao.DAO;

public class GetmTitleService {

	public List<String> getmTitle() {
		DAO dao = DAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		List<String> mTitleList = dao.getmTitle();
		
		close(con);
		return mTitleList;
	}

}
