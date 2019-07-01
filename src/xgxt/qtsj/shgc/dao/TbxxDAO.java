package xgxt.qtsj.shgc.dao;

import java.util.List;

import xgxt.DAO.DAO;
import xgxt.qtsj.shgc.form.ShgcTbxxForm;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 上海工程保险管理模块DAO</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 李容</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-06-17</p>
 */
public class TbxxDAO {
	DAO dao = DAO.getInstance();
	
	/**
	 * Method getBxxzList获取保险险种列表
	 * @return List
	 * */	
	public List getBxxzList(){		
		List bxxzList = null;
		String sql = "select distinct bxxzdm,bxxzmc from bxxzb order by bxxzdm";
		
		bxxzList = dao.getList(sql, new String[]{}, new String[]{"bxxzdm","bxxzmc"});
		return bxxzList;
	}
	
	/**
	 * Method getBxgsList获取保险公司列表
	 * @return List
	 * */
	public List getBxgsList(){
		List bxgsList = null;
		String sql = "select distinct bxgsdm,bxgsmc from bxgsdmb order by bxgsdm";
		
		bxgsList = dao.getList(sql, new String[]{}, new String[]{"bxgsdm","bxgsmc"});
		return bxgsList;
	}
	
	/**
	 * Method isExists 判断保险信息是否存在
	 * */
	public boolean isExists(String pkValue){
		boolean flag = false;		
		String sql = "select count(*)count from xsbxb where xh||nd=?";
		String sCount = dao.getOneRs(sql, new String[]{pkValue}, "count");
		flag = (Integer.parseInt(sCount)>0)? true : false;
		return flag;
	}
	
	/**
	 * 获取投保险种列表
	 * @param model
	 * */
	public List getTbxzList(ShgcTbxxForm model){		
		List tbxzList = null;
		String xh = model.getXh();
		String nd = model.getNd();
		
		String sql = "select tbxzdm,bf,bxpzh from view_xsbxxx where xh=? and nd=?";
		tbxzList = dao.getList(sql, new String[]{xh,nd}, new String[]{"bxpzh","tbxzdm","bf"});
		
		return tbxzList;
	}
}
