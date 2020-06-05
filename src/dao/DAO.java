package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import dto.BoardDTO;
import dto.HouseDTO;
import dto.MemberDTO;
import dto.MovieDTO;
import dto.MovieJoinDTO;
import dto.ScheduleDTO;
import dto.ScheduleJoinDTO;
import dto.StarRateDTO;
import dto.TheaterDTO;
import dto.TheaterJoinDTO;
import dto.TicketDTO;

import static db.JdbcUtil.*;


public class DAO {
	private static DAO dao;
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	
	public static DAO getInstance() { //�떛�겢�넠 媛앹껜 �깮�꽦諛⑹떇 InputService�겢�옒�뒪�뿉�꽌 getInstance硫붿냼�뱶瑜� �샇異쒗븯�뿬 DAO媛앹껜瑜� �깮�꽦�븿
		if(dao == null) {
			dao = new DAO();
		}
		return dao;
	}
	
	public void setConnection(Connection con) {
		this.con = con;
	}

	public boolean idOverlap(String id) {
		String sql = "SELECT * FROM MEMBERS WHERE MID=?";
		boolean overlap = false;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			overlap = !rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return overlap;
	}

	public int memberJoin(MemberDTO member) {
		String sql = "INSERT INTO MEMBERS VALUES(?,?,?,?,?,?,?,'BRONZE',0)";
		int result = 0;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member.getmId());
			pstmt.setString(2, member.getPw());
			pstmt.setString(3, member.getmName());
			pstmt.setString(4, member.getBirth());
			pstmt.setString(5, member.getTel());
			pstmt.setString(6, member.getAddress());
			pstmt.setString(7, member.getEmail());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public boolean memberLogin(String mId, String pw) {
		String sql = "SELECT * FROM MEMBERS WHERE MID=? AND PW=?";
		boolean result = false;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, mId);
			pstmt.setString(2, pw);
			rs = pstmt.executeQuery();
			result = rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return result;
	}

	public List<BoardDTO> boardHistory(String mId) {
		String sql = "SELECT * FROM BOARD WHERE MID=?";
		List<BoardDTO> boardList = new ArrayList<BoardDTO>();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, mId);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				BoardDTO board = new BoardDTO();
				board.setbNum(rs.getInt(1));
				board.setmId(rs.getString(2));
				board.setComments(rs.getString(3));
				board.setbFiles(rs.getString(4));
				board.setWriteDate(rs.getString(5));
				board.setmTitle(rs.getString(6));
				board.setBoTitle(rs.getNString(7));
				board.setbHit(rs.getInt(8));
				boardList.add(board);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return boardList;
	}

	public MemberDTO memberView(String mId) {
		String sql = "SELECT * FROM MEMBERS WHERE MID=?";
		MemberDTO member = new MemberDTO();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,mId);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				member.setmId(rs.getString(1));
				member.setPw(rs.getString(2));
				member.setmName(rs.getString(3));
				member.setBirth(rs.getString(4));
				member.setTel(rs.getString(5));
				member.setAddress(rs.getString(6));
				member.setEmail(rs.getString(7));
				member.setMembership(rs.getString(8));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return member;
	}

	public List<TicketDTO> ticketHistory(String mId) {
		String sql ="SELECT * FROM TICKETS WHERE MID=?";
		List<TicketDTO> ticketList = new ArrayList<TicketDTO>();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, mId);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				TicketDTO ticket = new TicketDTO();
				ticket.settId(rs.getString(1));
				ticket.setmTitle(rs.getString(2));
				ticket.setSeat(rs.getString(3));
				ticket.settLocation(rs.getString(4));
				ticket.setStartTime(rs.getString(5));
				ticket.setEndTime(rs.getString(6));
				ticket.setYouth(rs.getInt(7));
				ticket.setAdult(rs.getInt(8));
				ticket.setmId(rs.getString(9));
				ticket.setHouse(rs.getString(10));
				ticket.setArea(rs.getString(11));
				ticket.setTotalPrice(rs.getInt(12));
				ticketList.add(ticket);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return ticketList;
	}

	public int memberModifyProcess(MemberDTO member) {
		String sql = "UPDATE MEMBERS SET PW=?,MNAME=?,TEL=?,ADDRESS=?,EMAIL=? WHERE MID=?";
		int result = 0;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member.getPw());
			pstmt.setString(2, member.getmName());
			pstmt.setString(3, member.getTel());
			pstmt.setString(4, member.getAddress());
			pstmt.setString(5, member.getEmail());
			pstmt.setString(6, member.getmId());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}

	public List<MemberDTO> memberList() {
		String sql = "SELECT * FROM MEMBERS";
		List<MemberDTO> memberList = new ArrayList<MemberDTO>();
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				MemberDTO member = new MemberDTO();
				member.setmId(rs.getString(1));
				member.setPw(rs.getString(2));
				member.setmName(rs.getString(3));
				member.setBirth(rs.getString(4).substring(0,10));
				member.setTel(rs.getString(5));
				member.setAddress(rs.getString(6).replace(".", " "));
				member.setEmail(rs.getString(7));
				member.setMembership(rs.getString(8));
				memberList.add(member);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(rs);
		}
		return memberList;
	}

	public int listCount() {
		String sql = "SELECT COUNT(*) FROM BOARD";
		int listCount = 0;
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				listCount = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(rs);
		}
		return listCount;
	}

	public List<BoardDTO> boardList(int startRow, int endRow) {
		String sql = "SELECT * FROM BOARDLIST WHERE RN BETWEEN ? AND ?";
		List<BoardDTO> boardList = new ArrayList<BoardDTO>();	
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				BoardDTO board = new BoardDTO();
				board.setbNum(rs.getInt(1));
				board.setmId(rs.getString(2));
				board.setComments(rs.getString(3));
				board.setbFiles(rs.getString(4));
				board.setWriteDate(rs.getString(5).substring(0, 10));
				board.setmTitle(rs.getString(6));
				board.setBoTitle(rs.getString(7));
				board.setbHit(rs.getInt(8));
				System.out.println(rs.getInt(8));
				boardList.add(board);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return boardList;
	}	
	
	public List<BoardDTO> boardHit(int startRow, int endRow) {
		String sql = "SELECT * FROM BOARDHIT WHERE RN BETWEEN ? AND ?";
		List<BoardDTO> boardList = new ArrayList<BoardDTO>();	
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				BoardDTO board = new BoardDTO();
				board.setbNum(rs.getInt(1));
				board.setmId(rs.getString(2));
				board.setComments(rs.getString(3));
				board.setbFiles(rs.getString(4));
				board.setWriteDate(rs.getString(5).substring(0, 10));
				board.setmTitle(rs.getString(6));
				board.setBoTitle(rs.getString(7));
				board.setbHit(rs.getInt(8));
				boardList.add(board);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return boardList;
	}

	public List<String> getmTitle() {
		String sql = "SELECT MTITLE FROM MOVIES";
		List<String> mTitleList = new ArrayList<String>(); 
		try {
			pstmt = con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				mTitleList.add(rs.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return mTitleList;
	}

	public int writeBoard(BoardDTO board) {
		String sql = "INSERT INTO BOARD VALUES(BOARD_SEQ.NEXTVAL,?,?,?,SYSDATE,?,?,0)";
		int result = 0;	
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, board.getmId());
			pstmt.setString(2, board.getComments());
			pstmt.setString(3, board.getbFiles());
			pstmt.setString(4, board.getmTitle());
			pstmt.setString(5, board.getBoTitle());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}

	public int memberDeleteProcess(String mId) {
		String sql = "DELETE FROM MEMBERS WHERE MID=?";
		int result = 0;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, mId);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}

	public BoardDTO boardView(int bNum) {
		String sql = "SELECT * FROM BOARD WHERE BNUM=?";
		BoardDTO board = new BoardDTO();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bNum);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				board.setbNum(rs.getInt(1));
				board.setmId(rs.getString(2));
				board.setComments(rs.getString(3));
				board.setbFiles(rs.getString(4));
				board.setWriteDate(rs.getString(5));
				board.setmTitle(rs.getString(6));
				board.setBoTitle(rs.getString(7));
				board.setbHit(rs.getInt(8));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(rs);
		}
		return board;
	}

	public int setHit(int bNum) {
		String sql = "UPDATE BOARD SET BHIT = BHIT+1 WHERE BNUM=?";
		int result = 0;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bNum);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}

	public int boardDelete(int bNum) {
		String sql = "DELETE FROM BOARD WHERE BNUM = ?";
		int result = 0;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bNum);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}

	public List<MovieJoinDTO> nowMovieList() {
		String sql = "SELECT * FROM MOVIES JOIN STARRATE USING(MTITLE) WHERE RELEASEDATE <= SYSDATE";
		List<MovieJoinDTO> movieList = new ArrayList<MovieJoinDTO>();
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				MovieJoinDTO movie = new MovieJoinDTO();
				movie.setmTitle(rs.getString(1));
				movie.setPoster(rs.getString(2));
				movie.setDirector(rs.getString(3));
				movie.setActors(rs.getString(4));
				movie.setmHit(rs.getInt(5));
				movie.setRunningTime(rs.getInt(6));
				movie.setReleaseDate(rs.getString(7));
				movie.setEndDate(rs.getString(8));
				movie.setRate(rs.getInt(9));
				movie.setrHit(rs.getInt(10));
				movieList.add(movie);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(rs);
		}
		return movieList;
	}

	public int movieInsert(MovieDTO movie) {
		String sql = "INSERT INTO MOVIES VALUES(?,?,?,?,0,?,?,?)";
		int result = 0;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,movie.getmTitle());
			pstmt.setString(2,movie.getPoster());
			pstmt.setString(3,movie.getDirector());
			pstmt.setString(4,movie.getActors());
			pstmt.setInt(5,movie.getRunningTime());
			pstmt.setString(6,movie.getReleaseDate());
			pstmt.setString(7,movie.getEndDate());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}

	public List<MovieJoinDTO> soonMovieList() {
		String sql = "SELECT * FROM MOVIES JOIN STARRATE USING(MTITLE) WHERE RELEASEDATE > SYSDATE";
		List<MovieJoinDTO> movieList = new ArrayList<MovieJoinDTO>();
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				MovieJoinDTO movie = new MovieJoinDTO();
				movie.setmTitle(rs.getString(1));
				movie.setPoster(rs.getString(2));
				movie.setDirector(rs.getString(3));
				movie.setActors(rs.getString(4));
				movie.setmHit(rs.getInt(5));
				movie.setRunningTime(rs.getInt(6));
				movie.setReleaseDate(rs.getString(7));
				movie.setEndDate(rs.getString(8));
				movie.setRate(rs.getInt(9));
				movie.setrHit(rs.getInt(10));
				movieList.add(movie);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(rs);
		}
		return movieList;
	}

	public int setStarRate(String mTitle) {
		String sql = "INSERT INTO STARRATE VALUES(?,0,0)";
		int result = 0;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, mTitle);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}

	public int theaterInsert(TheaterDTO theater) {
		String sql = "INSERT INTO THEATERS VALUES(?,?)";
		int result = 0;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,theater.gettLocation());
			pstmt.setString(2,theater.getArea());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}

	public List<TheaterDTO> theaterGet() {
		String sql = "SELECT * FROM THEATERS";
		List<TheaterDTO> theaterList=  new ArrayList<TheaterDTO>();
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				TheaterDTO theater = new TheaterDTO();
				theater.settLocation(rs.getString(1));
				theater.setArea(rs.getString(2));
				theaterList.add(theater);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(rs);
		}
		return theaterList;
	}

	public int houseInsert(HouseDTO houses) {
		String sql = "INSERT INTO HOUSES VALUES(?,?,?,?,?)";
		int result = 0;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, houses.gettLocation());
			pstmt.setString(2, houses.getHouse());
			pstmt.setInt(3, houses.getTotalSeat());
			pstmt.setInt(4, houses.getWidth());
			pstmt.setInt(5, houses.getHeight());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}

	public List<TheaterDTO> theaterList() {
		String sql = "SELECT * FROM THEATERS";
		List<TheaterDTO> theaterList = new ArrayList<TheaterDTO>();
		try {
			pstmt=con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				TheaterDTO theater = new TheaterDTO();
				theater.settLocation(rs.getString(1));
				theater.setArea(rs.getString(2));
				theaterList.add(theater);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return theaterList;
	}

	public List<TheaterJoinDTO> theaterHouseJoin() {
		String sql = "SELECT * FROM THEATERS JOIN HOUSES USING(TLOCATION)";
		List<TheaterJoinDTO> theaterJoinList = new ArrayList<TheaterJoinDTO>();
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				TheaterJoinDTO theaterJoin = new TheaterJoinDTO();
				theaterJoin.settLocation(rs.getString(1));
				theaterJoin.setArea(rs.getString(2));
				theaterJoin.setHouse(rs.getString(3));
				theaterJoin.setTotalSeat(rs.getInt(4));
				theaterJoin.setWidth(rs.getInt(5));
				theaterJoin.setHeight(rs.getInt(6));
				theaterJoinList.add(theaterJoin);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(rs);
		}
		return theaterJoinList;
	}

	public List<MovieDTO> allMovieList() {
		String sql = "SELECT * FROM MOVIES";
		List<MovieDTO> movieList = new ArrayList<MovieDTO>();
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				MovieDTO movie = new MovieDTO();
				movie.setmTitle(rs.getString(1));
				movie.setPoster(rs.getString(2));
				movie.setDirector(rs.getString(3));
				movie.setActors(rs.getString(4));
				movie.setmHit(rs.getInt(5));
				movie.setRunningTime(rs.getInt(6));
				movie.setReleaseDate(rs.getString(7));
				movie.setEndDate(rs.getString(8));
				movieList.add(movie);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(rs);
		}
		return movieList;
	}

	public int scheduleInsert(ScheduleDTO schedule) {
		String sql = "INSERT INTO SCHEDULE VALUES(?,?,TO_DATE(?,'YYYY-MM-DD HH24:MI'),TO_DATE(?,'YYYY-MM-DD HH24:MI'),?,?)";
		int result = 0;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, schedule.gettLocation());
			pstmt.setString(2, schedule.getHouse());
			pstmt.setString(3, schedule.getStartTime());
			pstmt.setString(4, schedule.getEndTime());
			pstmt.setString(5, schedule.getmTitle());
			pstmt.setInt(6, schedule.getTotalSeat());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}

	public List<ScheduleDTO> scheduleGet() {
		String sql ="SELECT TLOCATION, HOUSE, TO_CHAR(STARTTIME,'YYYY-MM-DD HH24:MI'),TO_CHAR( ENDTIME,'YYYY-MM-DD HH24:MI'), MTITLE, TOTALSEAT FROM SCHEDULE";
		List<ScheduleDTO> scheduleList = new ArrayList<ScheduleDTO>();
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ScheduleDTO schedule = new ScheduleDTO();
				schedule.settLocation(rs.getString(1));
				schedule.setHouse(rs.getString(2));
				schedule.setStartTime(rs.getString(3));
				schedule.setEndTime(rs.getString(4));
				schedule.setmTitle(rs.getString(5));
				schedule.setTotalSeat(rs.getInt(6));
				scheduleList.add(schedule);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(rs);
		}
		return scheduleList;
	}

	public List<ScheduleJoinDTO> scheduleJoinGet() {
		String sql ="SELECT TLOCATION, HOUSE, TO_CHAR(STARTTIME,'YYYY-MM-DD HH24:MI'),TO_CHAR( ENDTIME,'YYYY-MM-DD HH24:MI'), MTITLE, TOTALSEAT,AREA FROM"
				+ " SCHEDULE JOIN THEATERS USING(TLOCATION) WHERE STARTTIME > SYSDATE ORDER BY STARTTIME ASC";
		List<ScheduleJoinDTO> scheduleList = new ArrayList<ScheduleJoinDTO>();
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ScheduleJoinDTO schedule = new ScheduleJoinDTO();
				schedule.settLocation(rs.getString(1));
				schedule.setHouse(rs.getString(2));
				schedule.setStartTime(rs.getString(3));
				schedule.setEndTime(rs.getString(4));
				schedule.setmTitle(rs.getString(5));
				schedule.setTotalSeat(rs.getInt(6));
				schedule.setArea(rs.getString(7));
				scheduleList.add(schedule);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(rs);
		}
		return scheduleList;
	}

	public List<HouseDTO> houseList() {
		String sql = "SELECT * FROM HOUSES";
		List<HouseDTO> houseList = new ArrayList<HouseDTO>();
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				HouseDTO house = new HouseDTO();
				house.settLocation(rs.getString(1));
				house.setHouse(rs.getString(2));
				house.setTotalSeat(rs.getInt(3));
				house.setWidth(rs.getInt(4));
				house.setHeight(rs.getInt(5));
				houseList.add(house);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);	
		}
		
		return houseList;
	}

	public int ticketReservation(TicketDTO ticket) {
		String sql = "INSERT INTO TICKETS VALUES(TICKETS_SEQ.NEXTVAL,?,?,?,TO_DATE(?,'YYYY-MM-DD HH24:MI'),TO_DATE(?,'YYYY-MM-DD HH24:MI'),?,?,?,?,?,?)";
		
		int result =0;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, ticket.getmTitle());
			pstmt.setString(2, ticket.getSeat());
			pstmt.setString(3, ticket.gettLocation());
			pstmt.setString(4, ticket.getStartTime());
			pstmt.setString(5, ticket.getEndTime());
			pstmt.setInt(6, ticket.getYouth());
			pstmt.setInt(7, ticket.getAdult());
			pstmt.setString(8, ticket.getmId());
			pstmt.setString(9, ticket.getHouse());
			pstmt.setString(10, ticket.getArea());
			pstmt.setInt(11, ticket.getTotalPrice());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}

	public List<TicketDTO> ticketHistory2() {
		String sql ="SELECT TID,MTITLE,SEAT,TLOCATION,TO_CHAR(STARTTIME,'YYYY-MM-DD HH24:MI'),TO_CHAR(ENDTIME,'YYYY-MM-DD HH24:MI'),YOUTH,ADULT,MID,HOUSE,AREA,TOTALPRICE FROM TICKETS";
		List<TicketDTO> ticketList = new ArrayList<TicketDTO>();
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				TicketDTO ticket = new TicketDTO();
				ticket.settId(rs.getString(1));
				ticket.setmTitle(rs.getString(2));
				ticket.setSeat(rs.getString(3));
				ticket.settLocation(rs.getString(4));
				ticket.setStartTime(rs.getString(5));
				ticket.setEndTime(rs.getString(6));
				ticket.setYouth(rs.getInt(7));
				ticket.setAdult(rs.getInt(8));
				ticket.setmId(rs.getString(9));
				ticket.setHouse(rs.getString(10));
				ticket.setArea(rs.getString(11));
				ticket.setTotalPrice(rs.getInt(12));
				ticketList.add(ticket);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return ticketList;
	}

	public int setTotalSeat(TicketDTO ticket) {
		String sql = "UPDATE SCHEDULE SET TOTALSEAT = TOTALSEAT-1 WHERE TLOCATION=? AND HOUSE=? AND STARTTIME = TO_DATE(?,'YYYY-MM-DD HH24:MI')";
		int result=0;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, ticket.gettLocation());
			pstmt.setString(2, ticket.getHouse());
			pstmt.setString(3, ticket.getStartTime());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	public int setMovieHit(TicketDTO ticket) {
		String sql = "UPDATE MOVIES SET MHIT = MHIT+1 WHERE MTITLE=?";
		int result = 0;
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, ticket.getmTitle());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}
	
	public List<MovieJoinDTO> rankMovieList() {
		String sql = "SELECT * FROM MOVIES JOIN STARRATE USING(MTITLE) ORDER BY MHIT DESC";
		List<MovieJoinDTO> movieList = new ArrayList<MovieJoinDTO>();
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				MovieJoinDTO movie = new MovieJoinDTO();
				movie.setmTitle(rs.getString(1));
				movie.setPoster(rs.getString(2));
				movie.setDirector(rs.getString(3));
				movie.setActors(rs.getString(4));
				movie.setmHit(rs.getInt(5));
				movie.setRunningTime(rs.getInt(6));
				movie.setReleaseDate(rs.getString(7));
				movie.setEndDate(rs.getString(8));
				movie.setRate(rs.getInt(9));
				movie.setrHit(rs.getInt(10));
				movieList.add(movie);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(rs);
		}
		return movieList;
		
	}

	public int setTotalAmount(int totalPrice, String mId) {
		String sql = "UPDATE MEMBERS SET TOTALAMOUNT = TOTALAMOUNT+? WHERE MID=?";
		int result = 0;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, totalPrice);
			pstmt.setString(2, mId);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}

	public int setMemberShip(String mId) {
		String sql = "SELECT TOTALAMOUNT FROM MEMBERS WHERE MID=?";
		int totalAmount=0;
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, mId);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				totalAmount=rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(rs);
		}
		
		return totalAmount;
		
	}

	public int setMemberShipProcess(String mId, String membership) {
		String sql = "UPDATE MEMBERS SET MEMBERSHIP = ? WHERE MID=?";
		int result =0;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, membership);
			pstmt.setString(2, mId);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();	
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	public BoardDTO boardModify(int bNum) {
		String sql = "SELECT * FROM BOARD WHERE BNUM=?";
		BoardDTO board = new BoardDTO();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bNum);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				board.setbNum(rs.getInt(1));
				board.setmId(rs.getString(2));
				board.setComments(rs.getString(3));
				board.setbFiles(rs.getString(4));
				board.setWriteDate(rs.getString(5));
				board.setmTitle(rs.getString(6));
				board.setBoTitle(rs.getString(7));
				board.setbHit(rs.getInt(8));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return board;
	}

	public int boardModifyProcess(BoardDTO board) {
		String sql = "UPDATE BOARD SET MTITLE=?,BOTITLE=?,COMMENTS=?,BFILES=? WHERE BNUM=?";
		int result=0;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, board.getmTitle());
			pstmt.setString(2, board.getBoTitle());
			pstmt.setString(3, board.getComments());
			pstmt.setString(4, board.getbFiles());
			pstmt.setInt(5, board.getbNum());
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}

	public int scheduleDelete(String tLocation, String house, String startTime) {
		String sql = "DELETE FROM SCHEDULE WHERE TLOCATION=? AND HOUSE=? AND STARTTIME = TO_DATE(?,'YYYY-MM-DD HH24:MI')";
		int result = 0;
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, tLocation);
			pstmt.setString(2, house);
			pstmt.setString(3, startTime);
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}

		return result;
	}

	public List<StarRateDTO> starRateList() {
		String sql = "SELECT * FROM STARATE";
		List<StarRateDTO> starRateList = new ArrayList<StarRateDTO>();
		try {
			pstmt=con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				StarRateDTO starRate = new StarRateDTO();
				starRate.setmTitle(rs.getString(1));
				starRate.setRate(rs.getInt(2));
				starRate.setrHit(rs.getInt(3));
				starRateList.add(starRate);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(rs);
		}
		return starRateList;
	}

	public MovieJoinDTO movieDetail(String mTitle) {
		String sql = "SELECT * FROM MOVIES JOIN STARRATE USING(MTITLE) WHERE MTITLE=?";
		MovieJoinDTO movie= new MovieJoinDTO();
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, mTitle);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				movie.setmTitle(rs.getString(1));
				movie.setPoster(rs.getString(2));
				movie.setDirector(rs.getString(3));
				movie.setActors(rs.getString(4));
				movie.setmHit(rs.getInt(5));
				movie.setRunningTime(rs.getInt(6));
				movie.setReleaseDate(rs.getString(7));
				movie.setEndDate(rs.getString(8));
				movie.setRate(rs.getInt(9));
				movie.setrHit(rs.getInt(10));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(rs);
		}
		
		return movie;
	}

	public int setStarRates(String mTitle, int rate) {
		String sql = "UPDATE STARRATE SET RATE=RATE+?, RHIT=RHIT+1 WHERE MTITLE=?";
		int result = 0;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, rate);
			pstmt.setString(2, mTitle);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			close(pstmt);
		}
		return result;
	}
	
}