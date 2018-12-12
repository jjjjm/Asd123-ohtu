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

    private final ConnectionHandler conHandler;

    private final int PRDSTM_INDEX_1 = 1;   // constant for preparedstatement variable placement
    private final int PRDSTM_INDEX_2 = 2;   // constant for preparedstatement variable placement

    /**
     * Default constructor.
     */
    public TagDaoPG() {
        conHandler = new ConnectionHandler(System.getenv("JDBC_DATABASE_URL"), System.getenv("DB_USER"), System.getenv("DB_PASSWORD"));
    }

    /**
     * Constructor which takes TagDaoPG as parameter.
     *
     * @param conHandler
     */
    public TagDaoPG(ConnectionHandler conHandler) {
        this.conHandler = conHandler;
    }

    /**
     * Add new tag to database.
     *
     * @param tag
     * @return true if operation was successfull
     */
    @Override
    public boolean add(Tag tag) {
        boolean success = false;
        Connection connection = conHandler.getDatabaseConnection();
        try {
            addTagToDatabase(connection, tag);
            success = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        conHandler.closeDatabaseConnection(connection);
        return success;
    }

    /**
     * List all tags in database.
     *
     * @return list of all tags in database
     */
    @Override
    public List<Tag> list() {
        List<Tag> tags = new ArrayList<>();
        Connection connection = conHandler.getDatabaseConnection();
        try {
            tags = fetchAllTags(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        conHandler.closeDatabaseConnection(connection);
        return tags;
    }

    @Override
    public Tag getTag(int id) {
        Tag tag = null;
        Connection connection = conHandler.getDatabaseConnection();
        try {
            tag = getTagById(connection, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        conHandler.closeDatabaseConnection(connection);
        return tag;
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

    private Tag createTagFromResultSet(ResultSet resultSet) throws Exception {
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
}
