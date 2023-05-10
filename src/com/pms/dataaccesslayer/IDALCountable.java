package com.pms.dataaccesslayer;

import java.sql.SQLException;

public interface IDALCountable {

	int getCount() throws SQLException;
}
