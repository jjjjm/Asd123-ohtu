/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import ohtu.handlers.ConnectionHandler;
import ohtu.model.Tag;
import org.springframework.stereotype.Component;

/**
 *
 * @author inkeriv
 */

@Component
public class TagDaoPG implements TagDao {
    
    private final int PRDSTM_INDEX_1 = 1;   // constant for preparedstatement variable placement
    private final int PRDSTM_INDEX_2 = 2;   // constant for preparedstatement variable placement
    
    /**Add new tag to database.
     * 
     * @param tag
     */
    @Override
    public void add(Tag tag) {
        ConnectionHandler conHandler = new ConnectionHandler(System.getenv("JDBC_DATABASE_URL"), System.getenv("DB_USER"), System.getenv("DB_PASSWORD"));
        Connection connection = conHandler.getDatabaseConnection();
        if (connection != null) {
            try {
                addTagToDatabase(connection, tag);
            } catch (Exception e) {
                e.printStackTrace();
            }
            conHandler.closeDatabaseConnection(connection);
        }
    }
    
    /**List all tags in database.
     * 
     * @return list of all tags in database
     */
    @Override
    public List<Tag> list() {
        List<Tag> tags = new ArrayList<>();
        ConnectionHandler conHandler = new ConnectionHandler(System.getenv("JDBC_DATABASE_URL"), System.getenv("DB_USER"), System.getenv("DB_PASSWORD"));
        Connection connection = conHandler.getDatabaseConnection();
        if (connection != null) {
            try {
                tags = fetchAllTags(connection);
            } catch (Exception e) {
                e.printStackTrace();
            }
            conHandler.closeDatabaseConnection(connection);
        }
        return tags;
    }
    
    @Override
    public Tag getTag(int id) {
        Tag tag = null;
        ConnectionHandler conHandler = new ConnectionHandler(System.getenv("JDBC_DATABASE_URL"), System.getenv("DB_USER"), System.getenv("DB_PASSWORD"));
        Connection connection = conHandler.getDatabaseConnection();
        if (connection != null) {
            try {
                tag = getTagById(connection, id);
            } catch (Exception e) {
                e.printStackTrace();
            }
            conHandler.closeDatabaseConnection(connection);
        }
        return tag;
    }
    
    @Override
    public boolean tagAlreadyExistsWithName(String name){
        ConnectionHandler conHandler = new ConnectionHandler(System.getenv("JDBC_DATABASE_URL"), System.getenv("DB_USER"), System.getenv("DB_PASSWORD"));
        Connection connection = conHandler.getDatabaseConnection();
        Tag tag = null;
        if (connection != null) {
            try {
                tag = getTagByName(connection, name);
            } catch (Exception e) {
                e.printStackTrace();
            }
            conHandler.closeDatabaseConnection(connection);
        }
        
        return tag == null;
    }

    private void addTagToDatabase(Connection connection, Tag tag) throws Exception {
        String statement = "INSERT INTO TAG (NAME, DATE_CREATED) VALUES (?, ?);";
        PreparedStatement prdstm = connection.prepareStatement(statement);
        prdstm.setString(PRDSTM_INDEX_1, tag.getName());
        Date uDate = new Date();
        Timestamp sDate = new java.sql.Timestamp(uDate.getTime());
        prdstm.setTimestamp(PRDSTM_INDEX_2, sDate);
        prdstm.execute();
    }

    private List<Tag> fetchAllTags(Connection connection) throws Exception {
        String query = "SELECT * FROM TAG;";
        PreparedStatement prdstm = connection.prepareStatement(query);
        ResultSet resultSet = prdstm.executeQuery();
        List<Tag> tags = new ArrayList<>();
        while (resultSet.next()) {
            Tag tag = createTagFromResultSet(resultSet);
            if (tag != null) {
                tags.add(tag);
            }
        }
        resultSet.close();
        return tags;
    }

    private Tag createTagFromResultSet(ResultSet resultSet) throws Exception{
        int id = resultSet.getInt("ID");
        String tag_name = resultSet.getString("NAME");
        Timestamp created = resultSet.getTimestamp("DATE_CREATED");
        Tag tag = new Tag(id, tag_name, created);
        return tag;
    }  

    public Tag getTagById(Connection connection, int id) throws Exception {
        String query = "SELECT * FROM TAG WHERE ID = ?;";
        PreparedStatement prdstm = connection.prepareStatement(query);
        prdstm.setInt(PRDSTM_INDEX_1, id);
        ResultSet resultSet = prdstm.executeQuery();
        Tag tag = null;
        if (resultSet.next()) {
            tag = createTagFromResultSet(resultSet);
        }
        resultSet.close();
        return tag;
    }
    
    public Tag getTagByName(Connection connection, String name) throws Exception {
        String query = "SELECT * FROM TAG WHERE NAME = ?;";
        PreparedStatement prdstm = connection.prepareStatement(query);
        prdstm.setString(PRDSTM_INDEX_1, name);
        ResultSet resultSet = prdstm.executeQuery();
        Tag tag = null;
        if (resultSet.next()) {
            tag = createTagFromResultSet(resultSet);
        }
        resultSet.close();
        return tag;
    }
    
    
}
