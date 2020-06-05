package service;

import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.DAO;

public class ScheduleDeleteService {

	public int scheduleDelete(String tLocation, String house, String startTime) {
		DAO dao = DAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		int result = dao.scheduleDelete(tLocation, house,startTime);
		
		if(result==1) {
			commit(con);
		}else {
			rollback(con);
		}
		
		close(con);
		return result;
	}

}
