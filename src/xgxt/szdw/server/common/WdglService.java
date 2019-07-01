package xgxt.szdw.server.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.szdw.dao.common.CommonDAO;

public class WdglService {

	public ArrayList<String[]> getWdxx() {
		// 返回一个月内所有的文档信息列表
		CommonDAO myDAO = new CommonDAO();
		ArrayList<String[]> rs = new ArrayList<String[]>();
		
		String tableName = "szdw_wdffb";
		String [] colList = new String []{"wdzldm","wdmc","scbt","sclj","scsj"};
		
		rs = myDAO.sxjyQuery(tableName, " where scsj>to_char(add_months(sysdate,-1),'yyyymmddHH24MISS') order by  scsj desc", new String []{}, colList, "");
		return rs;
	}
	
	public List<HashMap<String, String>> getWdlxList() {
		// 获得文档类型列表
		DAO dao = DAO.getInstance();
		String sql = "select wdzldm,wdzlmc from szdw_wdzlb order by wdzldm";
		return dao.getList(sql, new String[] {}, new String[] { "wdzldm","wdzlmc" });
	}

	public ArrayList<String[]> getWdxxList(String wdzl) {
		// 根据传入文档类型返回所有该类文档文件
		CommonDAO myDAO = new CommonDAO();
		String tableName = "szdw_wdffb";
		String query = " where wdzldm = ? order by  scsj desc";
		String [] colList = new String []{"wdzldm","wdmc","scbt","sclj","scsj"};
		ArrayList<String[]> rs = myDAO.sxjyQuery(tableName, query, new String []{wdzl}, colList, "");
		return rs;
	}

	public boolean saveWdff(String title, String wjlx, String filePath, String dateStr, String name, HttpServletRequest request) {
		// TODO 自动生成方法存根
		boolean inserted = StandardOperation.insert("szdw_wdffb", new String[]{"scbt","wdzldm","sclj","scsj","wdmc"}, new String []{title,wjlx,filePath,dateStr,name},request);
		return inserted;
	}

}
