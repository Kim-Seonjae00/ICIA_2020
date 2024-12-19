package service;

import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.DAO;
import dto.ScheduleDTO;

public class ScheduleInsertService {

	public int scheduleInsert(ScheduleDTO schedule) {
		DAO dao = DAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		int result = dao.scheduleInsert(schedule);
		if(result==1) {
			commit(con);
		}else {
			rollback(con);
		}
		close(con);
		
		return result;
	}

}
