package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import bean.AddressBean;

public class AddressDAO {

	public AddressDAO() {

	}
	
	public AddressBean retrieve(int id) throws SQLException{
		String query = "select * from book where id = " + id;
		Connection con = MySQLConnector.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		ResultSet r = p.executeQuery();
		r.next();
		int i = r.getInt("ID");
		String street = r.getString("STREET");
		String province = r.getString("PROVINCE");
		String country = r.getString("COUNTRY");
		String zip = r.getString("ZIP");
		String phone = r.getString("PHONE");
		r.close();
		p.close();
		con.close();
		return new AddressBean(i,street,province,country,zip,phone);
	}
	
	public void create(int id, String street, String province, String country, String zip, String phone) throws SQLException {
		String update = "INSERT INTO address (id, street, province, country, zip, phone) VALUES ('" + id + "', " + street + "', " + province + "', " + country + "', " + zip + "', " + phone + "');";
		Connection con = MySQLConnector.getConnection();
		PreparedStatement p = con.prepareStatement(update);
		p.executeUpdate();
		p.close();
		con.close();
	}
	
	public void update(int id, String street, String province, String country, String zip, String phone) throws SQLException {
		String update = "UPDATE address SET street = '" + street + "', province = '" + province + "', country = '" + country + "', zip = '" + zip + "', phone = " + phone + "' WHERE id = " + id;
		Connection con = MySQLConnector.getConnection();
		PreparedStatement p = con.prepareStatement(update);
		p.executeUpdate();
		p.close();
		con.close();
	}
}
