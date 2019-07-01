package com.zfsoft.database.imp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import org.apache.commons.beanutils.BeanUtils;

import com.zfsoft.basic.BasicDAO;
import com.zfsoft.database.interfaces.DatabaseTableManipulate;
import com.zfsoft.database.model.ColumnModel;
import com.zfsoft.database.model.TableModel;

public class DatabaseTableManipulateImpl extends Observable implements DatabaseTableManipulate{
	
//	private BasicDAO dao;
	
	public DatabaseTableManipulateImpl(){
		DatabaseTables dt = new DatabaseTables();
		addObserver(dt);
	}
	
	@Override
	public synchronized void addObserver(Observer o) {
		super.addObserver(o);
	}
	
	/**
	 * 查询表的字段信息
	 */
	public List<ColumnModel> getTableColumns(BasicDAO dao,String tableName) {
//		BasicDAO dao = new BasicDAO();
		HashMap<String, String> inputValueMap = new HashMap<String, String>();
		inputValueMap.put("table", tableName);
		List<HashMap<String, String>>   rs = dao.selectTableInfo(tableName);
		List<ColumnModel> result = new ArrayList<ColumnModel>();
		if(rs != null){
			for(int i=0; i<rs.size(); i++){
				ColumnModel cModel = new ColumnModel();
				HashMap<String, String> map = rs.get(i);
				try {
					BeanUtils.copyProperties(cModel, map);
				} catch (Exception e) {
					e.printStackTrace();
				}
				cModel.setNullable(map.get("nullable"));
				cModel.setPrimaryKey(map.get("primaryKey"));
				result.add(cModel);
			}
		}
		if(result != null && result.size()>0){
			this.setChanged();
			notifyObservers(result);
		}		
		return result;
	}
	
	public TableModel getTableInfo(String tableName) {
		
		return null;
	}
}
