package xgxt.pjpy.comm.pjpy.sjsz;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import xgxt.DAO.DAO;
import xgxt.pjpy.comm.pjpy.PjpyCommService;
import xgxt.pjpy.comm.pjpy.PjxtszModel;
import xgxt.utils.CommonQueryDAO;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 评奖评优_评奖设置_时间设置_Service类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @version 1.0
 */
public class PjpySjszService extends PjpyCommService{

	
	
	/**
	 * 时间设置查询
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> querySjsz(PjpySjszForm model) throws Exception{
		
		String[] queryArr = new String[]{"pjxn","pjxq","pjnd"};
		String[] queryLikeArr = new String[]{"xmmc"};
		Map<String,Object> map = CommonQueryDAO.getQuerySQL(model, queryArr, queryLikeArr);
		
		String[] colList = new String[]{"pkValue","xmmc","sqkssj",
					"sqjssj","sqkzkg","shkssj","shjssj","shkzkg"};
		StringBuilder sql = new StringBuilder();
		sql.append("select rownum r,a.xmdm pkValue,")
		   .append("a.pjxn,a.pjxq,a.pjnd,a.xmdm,")
		   .append("a.xmmc,a.xmlx,b.sqkssj,b.sqjssj,b.sqkzkg,")
		   .append("b.shkssj,b.shjssj,b.shkzkg from ")
		   .append("(select pjxn,pjxq,pjnd,xmdm,xmmc,xmlx from xg_pjpy_pjxmwhb")
		   .append(" where 1=1 ")
		   .append(map.get("sql"))
		   .append(") a left join xg_pjpy_sjszb b ")
		   .append(" on a.pjxn=b.pjxn and a.pjxq=b.pjxq")
		   .append(" and a.pjnd=b.pjnd and a.xmdm=b.xmdm ");
		
		return CommonQueryDAO.commonPageQuery(model.getPages(), sql.toString(), (String[])map.get("input"), colList);
	}
	
	
	
	/**
	 * 时间设置批量保存
	 * @param pkValues
	 * @param model
	 * @return
	 * @throws SQLException
	 */
	public boolean sjszSave(String[] pkValues,PjpySjszForm model) throws SQLException{
		
		int n = 0;
		String[] sqlArr = new String[pkValues.length*2];
		
		for (int i = 0 ; i < pkValues.length ; i++){
			
			StringBuilder delSQL = new StringBuilder();
			StringBuilder insertSQL = new StringBuilder();
			
			delSQL.append("delete from xg_pjpy_sjszb where pjxn||pjxq||pjnd||xmdm = '")
			      .append(PjxtszModel.pjxtszModel.getPjxn())
			      .append(PjxtszModel.pjxtszModel.getPjxq())
			      .append(PjxtszModel.pjxtszModel.getPjnd())
			      .append(pkValues[i])
			      .append("'");
			sqlArr[n++] = delSQL.toString();
			
			insertSQL.append("insert into xg_pjpy_sjszb (pjxn,pjxq,pjnd,xmdm,")
			         .append("sqkssj,sqjssj,sqkzkg,shkssj,shjssj,shkzkg) values ('")
			         .append(PjxtszModel.pjxtszModel.getPjxn())
			         .append("','")
			         .append(PjxtszModel.pjxtszModel.getPjxq())
			         .append("','")
			         .append(PjxtszModel.pjxtszModel.getPjnd())
			         .append("','")
			         .append(pkValues[i])
			         .append("','")
			         .append(model.getSqkssj()[i])
			         .append("','")
			         .append(model.getSqjssj()[i])
			         .append("','")
			         .append(model.getSqkzkg()[i])
			         .append("','")
			         .append(model.getShkssj()[i])
			         .append("','")
			         .append(model.getShjssj()[i])
			         .append("','")
			         .append(model.getShkzkg()[i])
			         .append("')");
			sqlArr[n++] = insertSQL.toString();
		}
		
		DAO dao = DAO.getInstance();
		
		int[] result = dao.runBatch(sqlArr);
		return dao.checkBatch(result);
	}
}
