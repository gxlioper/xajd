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
		// ����һ���������е��ĵ���Ϣ�б�
		CommonDAO myDAO = new CommonDAO();
		ArrayList<String[]> rs = new ArrayList<String[]>();
		
		String tableName = "szdw_wdffb";
		String [] colList = new String []{"wdzldm","wdmc","scbt","sclj","scsj"};
		
		rs = myDAO.sxjyQuery(tableName, " where scsj>to_char(add_months(sysdate,-1),'yyyymmddHH24MISS') order by  scsj desc", new String []{}, colList, "");
		return rs;
	}
	
	public List<HashMap<String, String>> getWdlxList() {
		// ����ĵ������б�
		DAO dao = DAO.getInstance();
		String sql = "select wdzldm,wdzlmc from szdw_wdzlb order by wdzldm";
		return dao.getList(sql, new String[] {}, new String[] { "wdzldm","wdzlmc" });
	}

	public ArrayList<String[]> getWdxxList(String wdzl) {
		// ���ݴ����ĵ����ͷ������и����ĵ��ļ�
		CommonDAO myDAO = new CommonDAO();
		String tableName = "szdw_wdffb";
		String query = " where wdzldm = ? order by  scsj desc";
		String [] colList = new String []{"wdzldm","wdmc","scbt","sclj","scsj"};
		ArrayList<String[]> rs = myDAO.sxjyQuery(tableName, query, new String []{wdzl}, colList, "");
		return rs;
	}

	public boolean saveWdff(String title, String wjlx, String filePath, String dateStr, String name, HttpServletRequest request) {
		// TODO �Զ����ɷ������
		boolean inserted = StandardOperation.insert("szdw_wdffb", new String[]{"scbt","wdzldm","sclj","scsj","wdmc"}, new String []{title,wjlx,filePath,dateStr,name},request);
		return inserted;
	}

}
