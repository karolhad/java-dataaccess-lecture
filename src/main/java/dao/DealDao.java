package dao;

import dto.Deal;

import java.util.List;

public interface DealDao {

   List<Deal> find(String instrumentName, String customerLastName, String accountName);
}
