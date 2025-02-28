package com.ahsan.start;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.odftoolkit.odfdom.doc.OdfDocument;
import org.odftoolkit.odfdom.doc.table.OdfTable;
import org.odftoolkit.odfdom.doc.table.OdfTableCell;
import org.odftoolkit.odfdom.doc.table.OdfTableRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ahsan.start.config.DatabaseConfig;
import com.ahsan.start.entities.CitizenClass;
import com.ahsan.start.entities.IdentityCards;

@SpringBootApplication
public class SpringBootProject1Application {
	@Autowired
	public DatabaseConfig databaseConfig;
	// Access the DatabaseConfig bean

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(SpringBootProject1Application.class, args);
		SpringBootProject1Application app = context.getBean(SpringBootProject1Application.class);
		// app.addCitizenInDBUsingConnectionPooling();
//        List<CitizenClass>list=app.getAllCitizensFromDBUsingConnectionPooling();
//        for(CitizenClass s:list) {
//        	System.out.println(s);
//        }
//        app.findByIdFromDBUsingConnectionPooling(103);
//        app.deleteByIdFromDBUsingConnectionPooling(102);

	}
	
	

	public void addCitizenInDBUsingConnectionPooling() {
		// SpringBootProject1Application.getAllCitizen();
		List<IdentityCards> identityCardList = new ArrayList<>();
		IdentityCards identityCards = new IdentityCards();
		identityCards.setId(101);
		identityCards.setIdentityType("Aadhaar Card");
		IdentityCards identityCardsObj = new IdentityCards();
		identityCardsObj.setId(102);
		identityCardsObj.setIdentityType("Aadhaar Card");
		identityCardList.add(identityCards);
		identityCardList.add(identityCardsObj);
		CitizenClass citizenClass = new CitizenClass();
		citizenClass.setId(102);
		citizenClass.setAddress("UP,INDIA");
		citizenClass.setCity("Kanpur");
		citizenClass.setFirstName("Aseer");
		citizenClass.setLastName("Hasan");
		citizenClass.setIdentityCards(identityCardList);
		System.out.println(citizenClass);

		String url = databaseConfig.getUrl();
		String username = databaseConfig.getUsername();
		String password = databaseConfig.getPassword();

		// Check if the values are null
		System.out.println("Database URL: " + url);
		System.out.println("Database Username: " + username);
		System.out.println("Database Password: " + password);

		// Use DriverManager to connect to the database
		try {
			Connection con = DriverManager.getConnection(url, username, password);
			String sql = "INSERT INTO citizens (CitizenID, LastName, FirstName, Address, City) VALUES (?, ?, ?, ?, ?)";
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			preparedStatement.setLong(1, citizenClass.getId());
			preparedStatement.setString(2, citizenClass.getLastName());
			preparedStatement.setString(3, citizenClass.getFirstName());
			preparedStatement.setString(4, citizenClass.getAddress());
			preparedStatement.setString(5, citizenClass.getCity());
			int rows = preparedStatement.executeUpdate();
			System.out.println("Number of rows updated - " + rows);

			// Insert identity cards
			for (IdentityCards idCard : citizenClass.getIdentityCards()) {
				String idCardSql = "INSERT INTO IdentificationCards (IdentityCardID, IdentityCardType, CitizenID) VALUES (?, ?, ?)";
				try (PreparedStatement idCardPreparedStatement = con.prepareStatement(idCardSql)) {
					idCardPreparedStatement.setLong(1, idCard.getId());
					idCardPreparedStatement.setString(2, idCard.getIdentityType());
					idCardPreparedStatement.setLong(3, citizenClass.getId()); // Set the foreign key
					int idCardRows = idCardPreparedStatement.executeUpdate();
					System.out.println("Number of identity card rows updated - " + idCardRows);
				}
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();

		}
	}

	public List<CitizenClass> getAllCitizensFromDBUsingConnectionPooling() {
		System.out.println("List of citizens are - ");

		List<CitizenClass> list = new ArrayList<>();
		ResultSet resultSet = null;
		String url = databaseConfig.getUrl();
		String username = databaseConfig.getUsername();
		String password = databaseConfig.getPassword();
		try {

			Connection con = DriverManager.getConnection(url, username, password);
			String sql = "SELECT * FROM CITIZENS";
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery(sql);
			while (resultSet.next()) {
				// Create a new CitizenClass object for each row
				CitizenClass citizen = new CitizenClass();
				citizen.setId(resultSet.getLong("CitizenID")); // Assuming CitizenID is of type long
				citizen.setFirstName(resultSet.getString("FirstName"));
				citizen.setLastName(resultSet.getString("LastName"));
				citizen.setAddress(resultSet.getString("Address"));
				citizen.setCity(resultSet.getString("City"));

				// Add the citizen object to the list
				list.add(citizen);
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			// Close resources
			try {
				if (resultSet != null) {
					resultSet.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	public void findByIdFromDBUsingConnectionPooling(long id) {
		System.out.println("findByIdFromDBUsingConnectionPooling method is executed for id - " + id);
		CitizenClass citizenClassObj = new CitizenClass();
		ResultSet resultSet = null;
		String url = databaseConfig.getUrl();
		String username = databaseConfig.getUsername();
		String password = databaseConfig.getPassword();
		try {

			Connection con = DriverManager.getConnection(url, username, password);
			String sql = "SELECT * FROM CITIZENS WHERE CITIZENID =" + id;
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			// preparedStatement.setLong(1, id);
			resultSet = preparedStatement.executeQuery(sql);
			while (resultSet.next()) {
				// Create a new CitizenClass object for each row
				citizenClassObj.setId(resultSet.getLong("CitizenID")); // Assuming CitizenID is of type long
				citizenClassObj.setFirstName(resultSet.getString("FirstName"));
				citizenClassObj.setLastName(resultSet.getString("LastName"));
				citizenClassObj.setAddress(resultSet.getString("Address"));
				citizenClassObj.setCity(resultSet.getString("City"));

			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			// Close resources
			try {
				if (resultSet != null) {
					resultSet.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		System.out.println(citizenClassObj);
	}

	public int deleteByIdFromDBUsingConnectionPooling(long id) {
		System.out.println("deleteByIdFromDBUsingConnectionPooling method is executed for id - " + id);
		System.out.println("Citizen object deleted was - ");
		findByIdFromDBUsingConnectionPooling(id);
		String url = databaseConfig.getUrl();
		String username = databaseConfig.getUsername();
		String password = databaseConfig.getPassword();
		int rows = 0;
		try {

			Connection con = DriverManager.getConnection(url, username, password);
			String sql = "DELETE FROM CITIZENS WHERE CITIZENID =" + id;
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			// preparedStatement.setLong(1, id);
			rows = preparedStatement.executeUpdate(sql);
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();

		}
		System.out.print("Number of rows deleted - " + rows);

		return rows;
	}

	public void saveObjectFromFileInDb(File file) throws IOException {
//		String fileLocation = "src/main/resources/SampleData.ods";
//		if (!file.exists()) {
//			throw new IOException("File not found: " + file.getAbsolutePath());
//		}
//
//		// Load the ODS document
//		OdfDocument document = OdfDocument.loadDocument(file);
//
//		// Get the first table in the document
//		OdfTable table = document.getTableList().get(0); // Adjust index if needed
//
//		// Iterate through the rows and cells
//		
//		for (OdfTableRow row : table.getRowList()) {
//			for (OdfTableCell cell : row.getCellList()) {
//				String cellValue = cell.getStringValue();
//				System.out.print(cellValue + "\t"); // Print cell value
//			}
//			System.out.println(); // New line for each row
//		}
	}
}