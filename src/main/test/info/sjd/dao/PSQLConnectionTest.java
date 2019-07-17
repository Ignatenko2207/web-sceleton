package info.sjd.dao;

import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

class PSQLConnectionTest {

    @Test
    void getConnection() {
        try (Connection connection = PSQLConnection.getConnection()){
            assertNotNull(connection);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}