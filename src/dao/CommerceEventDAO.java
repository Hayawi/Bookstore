package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import bean.CommerceEventBean;

public class CommerceEventDAO {

	public CommerceEventDAO(){
	}
	
	public List<CommerceEventBean> retrieve(int isbn) throws SQLException{
		String query = "select * from CommerceEvent where isbn = ?";
		List<CommerceEventBean> rv = new ArrayList<CommerceEventBean>();
		Connection con = MySQLConnector.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		p.setInt(1, isbn);
		ResultSet r = p.executeQuery();
		while(r.next()) {
			String i = r.getString("ISBN");
			String timestamp = r.getString("TIMESTAMP");
			String eventType = r.getString("EVENTTYPE");
			rv.add(new CommerceEventBean(i,timestamp,eventType));
		}
		r.close();
		p.close();
		con.close();
		return rv;
	}
	
	public void create(String isbn, String timestamp, String eventType) throws SQLException {
		String update = "INSERT INTO CommerceEvent (isbn, timestamp, eventType) VALUES (?,?,?)";
		Connection con = MySQLConnector.getConnection();
		PreparedStatement p = con.prepareStatement(update);
		p.setString(1, isbn);
		p.setString(2, timestamp);
		p.setString(3, eventType);
		p.executeUpdate();
		p.close();
		con.close();
	}
}