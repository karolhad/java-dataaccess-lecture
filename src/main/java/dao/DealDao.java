package dao;

import dto.Deal;

import java.sql.SQLException;
import java.util.List;

public interface DealDao {

   List<Deal> find(String instrumentName, String customerLastName, String accountName) throws SQLException;
}
