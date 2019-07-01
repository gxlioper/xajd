package com.zfsoft.database.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import com.zfsoft.database.model.ColumnModel;
import com.zfsoft.database.model.TableModel;
import com.zfsoft.utils.StringUtil;


public class DatabaseTables implements Observer{
	private static HashMap<String, TableModel> tables = new HashMap<String, TableModel>();
	
	public static TableModel getTable(String tableName){
		if(tableName ==  null) return null;
		return tables.get(tableName.toLowerCase());
	}

	public void update(Observable o, Object tableInfo) {
		if( tableInfo instanceof List ){
			List l = (List)tableInfo;
			TableModel tModel = null;
			if(l != null && l.size() > 0 && (l.get(0) instanceof ColumnModel) ){
				tModel = new TableModel();
				StringBuilder primaryKey = new StringBuilder();
				StringBuilder uniqueIndex = new StringBuilder();
				for(int i = 0;i < l.size();i++){
					ColumnModel cModel = (ColumnModel) l.get(i);
					tModel.setName(cModel.getTableName());
					if(tables.containsKey(tModel.getName().toLowerCase())){
						return;
					}
					if(cModel.isPrimaryKey() && (!primaryKey.toString().contains(","+cModel.getName().toLowerCase())
							||!primaryKey.toString().contains(cModel.getName().toLowerCase()+","))){
						primaryKey.append(cModel.getName().toLowerCase());
						primaryKey.append(",");
					}
					if(cModel.isUniqueIndex()){
						uniqueIndex.append(cModel.getName().toLowerCase());
						uniqueIndex.append(",");
					}
					tModel.setComment(cModel.getTableComments());
					tModel.setPrimaryKey(cModel.isPrimaryKey() && (tModel.getPrimaryKey() == null) ? cModel.getName():tModel.getPrimaryKey());
					
				}
				if(!StringUtil.isNull(primaryKey.toString())){
					primaryKey.deleteCharAt(primaryKey.length()-1);
					tModel.setPrimaryKey(primaryKey.toString());
				}
				if(!StringUtil.isNull(uniqueIndex.toString())){
					uniqueIndex.deleteCharAt(uniqueIndex.length()-1);
					tModel.setUniqueIndex(uniqueIndex.toString());
				}
				tModel.setColumns((List)tableInfo);				
				tables.put(tModel.getName().toLowerCase(), tModel);
			}
		}
	}
	
	
}
