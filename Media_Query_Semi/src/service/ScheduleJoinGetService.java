package service;

import static db.JdbcUtil.*;

import java.sql.Connection;
import java.util.List;

import dao.DAO;
import dto.ScheduleJoinDTO;

public class ScheduleJoinGetService {

	public List<ScheduleJoinDTO> scheduleJoinGet() {
		DAO dao = DAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);	
		
		List<ScheduleJoinDTO> scheduleJoinList = dao.scheduleJoinGet();
		
		close(con);
		return scheduleJoinList;
	}

}
