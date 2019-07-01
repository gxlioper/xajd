package xgxt.comm.xmzdsz;

import java.sql.SQLException;
import java.util.*;

import xgxt.action.Base;

public class XmzdszService {

	XmzdszDAO dao = new XmzdszDAO();
	
	/**
	 * 获取各模块项目表、项目字段表
	 * @return
	 */
	public List<HashMap<String,String>> getMkccb(){
		
		List<HashMap<String,String>> mkccList = new ArrayList<HashMap<String,String>>();
		
		//学生资助
		HashMap<String,String> xszzMap = new HashMap<String, String>();
		xszzMap.put("mkmc", "xszz");//模块名称
		xszzMap.put("xmdmb", "xszz_zzxmb");//项目代码表
		xszzMap.put("xmbzd", "xmb");//项目表字段
		xszzMap.put("xmdmzd", "xmdm");//项目代码字段
		xszzMap.put("xmmczd", "xmmc");//项目名称字段
		xszzMap.put("xmzdb", "xszz_xmnrzdb");//项目内容字段表
		xszzMap.put("xmzdView", "xg_view_xszz_xmnrzdb");//项目内容字段表
		xszzMap.put("xmzdLybzd", "lyb");//项目内容字段来源表字段名
		mkccList.add(xszzMap);
		
		//-------新加模块请在下面新建个Map 并添加到-- mkccList里，具体键同上！----------
		
		
		return mkccList;
	}
	
	
	
	/**
	 * 获取指定模块项目表、项目字段表
	 * @param mkmc
	 * @return
	 */
	public HashMap<String,String> getMkccb(String mkmc){
		
		List<HashMap<String,String>> mkccList = getMkccb();
		
		for (int i = 0 ; i < mkccList.size() ; i++) {
			if (mkmc.equals(mkccList.get(i).get("mkmc"))) {
				return mkccList.get(i);
			}
		}
		
		return null;
	}
	
	
	
	/**
	 * 返回项目列表
	 * @param mkmc
	 * @return
	 */
	public List<HashMap<String,String>> szxmList(String mkmc){
		
		HashMap<String,String> mkccMap = getMkccb(mkmc);
		
		if (null != mkccMap) {
			return dao.getXmList(mkccMap);
		}
		
		return null;
	}
	
	
	
	/**
	 * 获取模块项目保存字段表
	 * @param mkmc
	 * @return
	 */
	public List<HashMap<String,String>> getMkxmbzzdList(String mkmc){
		return dao.getMkxmbzzdList(mkmc);
	}
	
	
	
	/**
	 * 获取模块项目保存字段
	 * @param mkmc
	 * @return
	 */
/*	public String[] getMkxmbzzd(String mkmc){
		try {
			return dao.getMkxmbzzd(mkmc);
		} catch (SQLException e) {
			e.printStackTrace();
			return new String[] {};
		}
	}*/
	
	
	
	/**
	 * 返回数据源
	 * @param tableName
	 * @param lybzd
	 * @return
	 * @throws SQLException
	 */
	public String[] getSjy(String xmdm,String mkmc) {
		HashMap<String,String> map = getMkccb(mkmc);
		
		String tableName = map.get("xmzdb");
		String lybzd = map.get("xmzdLybzd");
		
		try {
			return dao.getSjy(xmdm,tableName, lybzd);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	
	/**
	 * 返回查询显示字段
	 * @param xmdm
	 * @param mkmc
	 * @param model
	 * @param sjy
	 * @return
	 */
	public List<HashMap<String,String>> getZdList(String xmdm,String mkmc,XmzdszForm model,String sjy){
		
		HashMap<String,String> map = getMkccb(mkmc);//模块存储信息
		//String[] bzzdArr = getMkxmbzzd(mkmc);//模块需要保存显示的字段
		//String[] colList = new String[] {};
		String tableName = map.get("xmzdView");//项目内容字段表
		//String xmdmb = map.get("xmdmb");//项目代码表
		//String xmbzd = map.get("xmb");//项目表字段
		String[] sjyArr = null;
		
		if (!Base.isNull(sjy)) {
			sjyArr = sjy.split(",");
		} else {
			sjyArr = getSjy(xmdm, mkmc);
		}
		
		
		//String xmbmc = dao.getXmbmc(xmdm, xmdmb, xmbzd);
		String[] column = new String[] {"pkValue","checked","lyb","lybmc","zd","zdmc","zdm","bxlr","zdlx","lrxz","zdsx"};
		
		//项目中已包含字段列表
		List<HashMap<String,String>> xmzdList = dao.getXmzdList(tableName,sjyArr, xmdm,column ,model);
		
		return xmzdList;
	}
	
	
	
	/**
	 * 保存设置字段
	 * @param xmdm
	 * @param xmnrzdb
	 * @param pkValue
	 * @param flg
	 * @param lyb
	 * @param zd
	 * @param zdm
	 * @param bxlr
	 * @param zdlx
	 * @param lrxz
	 * @param zdsx
	 * @param sjy
	 * @return
	 */
	public boolean bcszzd(String xmdm,String xmnrzdb, String[] pkValue, String[] flg,
			String[] lyb, String[] zd, String[] zdm, String[] bxlr,
			String[] zdlx, String[] lrxz, String[] zdsx,String sjy) {
		
		if (Base.isNull(xmdm) || Base.isNull(xmnrzdb) || null == pkValue 
				|| null == flg || null == lyb || null == zd || null == zdm 
				|| null == bxlr|| null == zdlx || null == lrxz || null == zdsx) {
			return false;
		}
		
		
		
		
		try {
			return dao.bcszzd(sjy.split(","),xmdm, xmnrzdb, pkValue, flg, lyb, zd, zdm, bxlr, zdlx, lrxz, zdsx);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
	
	
	/**
	 * 返回项目表名称
	 * @param xmdm
	 * @param xmdmb
	 * @param xmbzd
	 * @return
	 */
	public String getXmbmc(String xmdm,String xmdmb,String xmbzd) {
		
		
		return dao.getXmbmc(xmdm, xmdmb, xmbzd);
	}
	



	/**
	 * 返回某项目是否默认项目
	 * @param xmdm
	 * @return
	 */
	public String getIsMrxm(String xmdm) {
		
		String sql = "select mrxm from xszz_zzxmb where xmdm=?";
		
		return dao.getIsMrxm(xmdm);
	}

}
