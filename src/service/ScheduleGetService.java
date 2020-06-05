package service;

import static db.JdbcUtil.*;

import java.sql.Connection;
import java.util.List;

import dao.DAO;
import dto.ScheduleDTO;

public class ScheduleGetService {

	public List<ScheduleDTO> scheduleGet() {
		DAO dao = DAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		List<ScheduleDTO> scheduleList = dao.scheduleGet();
		
		close(con);
		return scheduleList;
	}

}
