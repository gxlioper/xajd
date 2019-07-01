package xgxt.xsgygl.bjlh.sjwh;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.GetTime;
import xgxt.utils.MakeQuery;
import xgxt.xsgygl.bjlh.BjlhGyglDAO;

public class SjwhDAO extends BjlhGyglDAO {
	
	//	=======================万恶的分割线（以下luojw）===================================
	
	DAO dao = DAO.getInstance();

	/**
	 * 获得公寓管理相关列表
	 * 
	 * @author luojw
	 */
	public ArrayList<String[]> getGyglList(String tableName, SjwhModel model,
			String[] colList,String querry) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		String[] queryList = new String[] { "xb", "xydm", "zydm", "bjdm","nj",
				"sfbj", "xqdm", "lddm", "cs", "qsh", "fbbj","lx","zzbj" };
		String[] queryLikeList = new String[] { "xh", "xm", "fqrzxh" };
		MakeQuery myQuery = new MakeQuery();
		myQuery.makeQuery(queryList, queryLikeList, model);
		String query = myQuery.getQueryString() + querry;

		return CommonQueryDAO.commonQuery(tableName, query, myQuery
				.getInputList(), colList, "", model);
	}
	
	/**
	 * 获得学生住宿信息ID
	 * 
	 * @author luojw
	 */
	public String getId(String xh, String lx){
		String sql = "select id from bjlh_xszsxxb where xh = ? and lx = ? and zzbj = 'yes'";
		return dao.getOneRs(sql, new String[]{xh,lx}, "id");
	}
	
	/**
	 * 修改退房信息
	 * 
	 * @author luojw
	 * @throws Exception 
	 */
	public Boolean updateTfxx(SjwhModel model) throws Exception{
		boolean flag = false;
		String xh = model.getXh();
		String tfrq = GetTime.getSystemTime().replace("-", "");
		String sql = "update bjlh_xszsxxb set zzbj = 'no',tfrq = ? where xh = ? and zzbj <> 'no'";
		flag = dao.runProcuder(sql, new String[]{tfrq,xh});
		return flag;
	}

	/**
	 * 执行整体退房
	 * 
	 * @author luojw
	 * @throws Exception 
	 */
	public Boolean excuteZttf(SjwhModel model) throws Exception{
		
		boolean flag = false;
		String lx = model.getLx();
		String nj = model.getNj();
		String xydm = model.getXydm();
		String zydm = model.getZydm();
		String bjdm = model.getBjdm();
		
		nj = Base.isNull(nj) ? "%" : nj;
		xydm = Base.isNull(xydm) ? "%" : xydm;
		zydm = Base.isNull(zydm) ? "%" : zydm;
		bjdm = Base.isNull(bjdm) ? "%" : bjdm;
		
		StringBuffer sql = new StringBuffer();
		sql.append("update bjlh_xszsxxb set zzbj = 'no', tfrq = to_char(sysdate, 'YYYYMMDD') ");
		sql.append("where xh in (select xh from bjlh_xszsxxb a where exists (select 1 ");
		sql.append("from view_bjlh_xsxx b where a.xh = b.xh and a.lx = b.lx ");
		sql.append("and b.nj like ? and b.xydm like ? and b.zydm like ? ");
		sql.append("and b.bjdm like ? and b.lx = ? ))and zzbj <> 'no' ");
		flag = dao.runProcuder(sql.toString(), new String[]{nj,xydm,zydm,bjdm,lx});
		
		return flag;
	}
	
	//	=======================万恶的分割线（以上luojw）===================================
	
	public List<HashMap<String, String>> getList(String[] en, String[] cn) {
		return dao.arrayToList(en, cn);
	}
}
