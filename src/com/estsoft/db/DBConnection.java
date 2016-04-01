package com.estsoft.db;

import java.sql.Connection;
import java.sql.SQLException;

public interface DBConnection {
	public Connection getConnection() throws SQLException;
	//데이터 베이스 오류
}

