package com.zfsoft.database.interfaces;

import java.util.List;

import com.zfsoft.basic.BasicDAO;
import com.zfsoft.database.model.ColumnModel;

public interface DatabaseTableManipulate {
	/**
	 * 获取表的字段信息
	 */
	public List<ColumnModel> getTableColumns(BasicDAO dao,String tableName);
}
