package xgxt.dtjs.zjjj.server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.dtjs.dao.DtjsDAO;
import xgxt.dtjs.zjjj.model.TxgzModel;
import xgxt.szdw.utils.ModelToStrings;
import xgxt.utils.SearchUtils;

public class TxgzService {
	DtjsDAO dao = new DtjsDAO();
	public ArrayList<String[]> getTzzgbList(TxgzModel myModel) {
		// 团组织干部查询
		String tableName     = "view_tzzgb";
		String xydm         = DealString.toGBK(myModel.getXydm());
		String xm           = DealString.toGBK(myModel.getXm());
		String zydm         = DealString.toGBK(myModel.getZydm());
		String bjdm         = DealString.toGBK(myModel.getBjdm());
		String nj         = DealString.toGBK(myModel.getNj());
		String xh         = DealString.toGBK(myModel.getXh());
		String zwdm         = DealString.toGBK(myModel.getZwdm());
		StringBuffer query = new StringBuffer(SearchUtils.makeQueryCondition(xydm,zydm,bjdm,"",xh,xm,nj,"","",""));
		if(zwdm != null && !("".equalsIgnoreCase(zwdm.trim()))){
			query.append(" and zwdm='");
			query.append(zwdm);
			query.append("' ");
		}
		String [] colList = new String []{"pk","xh","xm","xymc","zymc","bjmc","zwmc","lxdh"}; 
		ArrayList<String[]> rs = dao.sxjyQuery(tableName, query.toString(), new String []{}, colList, "");
		return rs;
	}
	
	public List getTzzgbTopTr() {
		// 团组织干部表头
	    DAO dao = DAO.getInstance();
		String tableName     = "view_tzzgb";
		String [] colList = new String []{"pk","xh","xm","xymc","zymc","bjmc","zwmc","lxdh"}; 
		String[] colListCN     = dao.getColumnNameCN(colList, tableName);
		List topTr = dao.arrayToList(colList, colListCN);//表头 
		
		return topTr;
	}
	
	public HashMap<String, String> getTzzgbOne (String pk, String xh) {
		// 得到单个团组织干部详细信息
		String tableName    = "view_tzzgb";
		String [] colList = new String []{"pk","xh","xm","xb","nj","xydm","xymc","zydm","zymc","bjdm","bjmc","zwdm","zwmc","ksrzsj","xydj","bz","lxdh"};
		HashMap<String, String> rs = dao.sxjyQueryOne(tableName, colList, "pk", pk);
		colList = new String []{"xh","xm","xb","nj","xydm","xymc","zydm","zymc","bjdm","bjmc"};
		if(!xh.equalsIgnoreCase("")) {
			rs = dao.sxjyQueryOne3("view_xsjbxx", colList, "xh", xh, rs, "");
		}
		return rs;
	}

	public boolean updataTzzgb(TxgzModel myModel, String pk, HttpServletRequest request) throws Exception {
		//	    保存单个团组织干部
		String tableName       = "tzzgbb";
		String pkComment       = "xh";
		String [] colList      = new String []{"xh","xydj","ksrzsj","lxdh","bz","zwdm"}; 
		String [] inputList    = ModelToStrings.modelToStrings(colList, myModel);
		boolean inserted = StandardOperation.delete(tableName, pkComment,pk, request);
		if(inserted){
			inserted = StandardOperation.insert(tableName, colList, inputList, request);
		}
		return inserted;
	}
	
	public boolean deleteTzzgbOne(String pk, HttpServletRequest request)
		throws Exception {
			//    删除团组织干部
			String tableName       = "tzzgbb";
			String pkComment       = "xh";
			boolean del = StandardOperation.delete(tableName, pkComment,pk, request);
			return del;
	}

	public Object getZwList() {
		//　团组织干部职务列表
		DAO commonDao = DAO.getInstance();
		String sql = "select dm zwdm,cm zwmc from tzzzwdmb";
		List<HashMap<String, String>> zwList = commonDao.getList(sql, new String[]{}, new String[]{"zwdm","zwmc"});
		return zwList;
	}

	public List getSbSearchTitle() {
		DAO dao = DAO.getInstance();
		String tableName     = "view_tytysb";
		String [] colList = new String []{"xh","xm","xymc","zymc","bjmc","rtrq","xysh","xxsh"}; 
		String[] colListCN     = dao.getColumnNameCN(colList, tableName);
		List topTr = dao.arrayToList(colList, colListCN);//表头 
		return topTr;
	}

	public ArrayList<String[]> getXySbSearchResult(TxgzModel myModel) {
		String xn = myModel.getXn();
		String sql = "select '"+xn+"'||a.xh pk,'"+xn+"' xn,a.xh,a.xm,a.rtrq,a.xydm,a.xymc,a.zydm,a.zymc,a.bjdm,a.bjmc,nvl(b.xysh,'未提交') xysh,nvl(b.xxsh,'未提交') xxsh from view_TYXXB a left join (select c.xn,c.xh,c.tjly,c.xxsh,c.xysh from tytysbb c where c.xn = '"+xn+"' ) b on a.xh = b.xh";
		String xydm         = DealString.toGBK(myModel.getXydm());
		String zydm         = DealString.toGBK(myModel.getZydm());
		String bjdm         = DealString.toGBK(myModel.getBjdm());
		String nj         = DealString.toGBK(myModel.getNj());
		StringBuffer query = new StringBuffer(SearchUtils.makeQueryCondition(xydm,zydm,bjdm,"","","",nj,"","",""));
		String [] colList = new String []{"pk","xh","xm","xymc","zymc","bjmc","rtrq","xysh","xxsh"}; 
		ArrayList<String[]> rs = dao.sxjyQuery("", query.toString(), new String []{}, colList, sql);
		return rs;
	}

	public ArrayList<String[]> getXxShSearchResult(TxgzModel myModel) {
		String tableName       = "view_tytysb";
		String xydm         = DealString.toGBK(myModel.getXydm());
		String zydm         = DealString.toGBK(myModel.getZydm());
		String bjdm         = DealString.toGBK(myModel.getBjdm());
		String nj         = DealString.toGBK(myModel.getNj());
		StringBuffer query = new StringBuffer(SearchUtils.makeQueryCondition(xydm,zydm,bjdm,"","","",nj,"","",""));
		String [] colList = new String []{"pk","xh","xm","xymc","zymc","bjmc","rtrq","xysh","xxsh"}; 
		ArrayList<String[]> rs = dao.sxjyQuery(tableName, query.toString(), new String []{}, colList, "");
		return rs;
	}
}
	