package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

import bean.POItemBean;

public class POItemDAO {

	public POItemDAO() {
	}

	public List<POItemBean> retrieve(int id) throws SQLException {
		String query = "select * from POItem where id = ?";
		List<POItemBean> rv = new ArrayList<POItemBean>();
		Connection con = MySQLConnector.getConnection();
		PreparedStatement p = con.prepareStatement(query);
    p.setInt(1, id);
		ResultSet r = p.executeQuery();
		while (r.next()) {
			int i = r.getInt("ID");
			String isbn = r.getString("BID");
			int price = r.getInt("PRICE");
			rv.add(new POItemBean(i,isbn,price, 1));
		}
		r.close();
		p.close();
		con.close();
		return rv;
	}
	
	public void create(int id, String bid, int price) throws SQLException {
		String update = "INSERT INTO POItem (id, bid, price) VALUES (?,?,?)";
		Connection con = MySQLConnector.getConnection();
		PreparedStatement p = con.prepareStatement(update);
		p.setInt(1, id);
		p.setString(2, bid);
		p.setInt(3, price);
		p.executeUpdate();
		p.close();
		con.close();
	}
	
	public void delete(int id, String bid) throws SQLException {
		String update = "DELETE FROM POItem WHERE 'id' = ? AND bid = ?";
		Connection con = MySQLConnector.getConnection();
		PreparedStatement p = con.prepareStatement(update);
		p.setInt(1, id);
		p.setString(2, bid);
		p.executeUpdate();
		p.close();
		con.close();
	}

	public Map<String, Integer> retrieveOrderCount() {
		Map<String, Integer> rv = new HashMap<String, Integer>();
		try {
			String query = "select bid, count(*) from POItem group by bid";
			Connection con = MySQLConnector.getConnection();
			PreparedStatement p = con.prepareStatement(query);
			ResultSet r = p.executeQuery();
			int i = 0;
			while (r.next() && i < 10) {
				rv.put(r.getString(1), r.getInt(2));
				i++;
			}
		} catch (Exception e) {
			
		}
		return rv;
	}
}