package xgxt.shgz.server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;


import xgxt.DAO.DAO;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.shgz.dao.StglDAO;
import xgxt.shgz.model.StglModel;
import xgxt.shgz.model.XjqnzyzModel;
import xgxt.shgz.server.common.CommonService;
import xgxt.szdw.utils.ModelToStrings;
import xgxt.utils.SearchUtils;

public class StglService extends CommonService{
	StglDAO dao = new StglDAO();
	public HashMap<String, String> getStsqOne(String pk, String xh) {
		// 得到单个学生申请社团详细信息
		String tableName    = "view_rssq";
		String [] colList = new String []{"pk","xh","xm","xb","nj","xydm","xymc","zydm","zymc","bjdm","bjmc","sqly","sqsj","stdm","shzt"};
		HashMap<String, String> rs = dao.sxjyQueryOne(tableName, colList, "pk", pk);
		colList = new String []{"xh","xm","xb","nj","xydm","xymc","zydm","zymc","bjdm","bjmc"};
		if(!xh.equalsIgnoreCase("")) {
			rs = dao.sxjyQueryOne3("view_xsjbxx", colList, "xh", xh, rs, "");
		}
		return rs;
}
	public List<HashMap<String, String>> getXtList() {
		// TODO 自动生成方法存根
		return dao.getComboList();
	}
	
	public List<HashMap<String, String>> getXtList2(String xydm) {
		// TODO 自动生成方法存根
		if(xydm.equalsIgnoreCase("")) {
			return dao.getComboList();
		}else {
			return dao.getComboList2(xydm);
		}
	}
	
	public boolean updataRssq(StglModel myModel,String pk, HttpServletRequest request) 
	throws Exception {
		//    保存单个新生体检
		String tableName       = "rtsqdjb";
		String pkComment       = "xh||stdm";
		String [] colList      = new String []{"xh","stdm","sqly","shzt"}; 
		String [] inputList    = ModelToStrings.modelToStrings(colList, myModel);
		boolean inserted = StandardOperation.delete(tableName, pkComment,pk, request);
		if(inserted){
			inserted = StandardOperation.insert(tableName, colList, inputList, request);
		}
		return inserted;
	}
	
	public ArrayList<String[]> getRssqList(StglModel myModel) {
		// 得到入社申请列表
		String tableName       = "view_rssq";
		String xydm         = DealString.toGBK(myModel.getXydm());
		String xm           = DealString.toGBK(myModel.getXm());
		String zydm         = DealString.toGBK(myModel.getZydm());
		String bjdm         = DealString.toGBK(myModel.getBjdm());
		String nj         = DealString.toGBK(myModel.getNj());
		String xh         = DealString.toGBK(myModel.getXh());
		String stdm         = DealString.toGBK(myModel.getStdm());
		String shzt         = DealString.toGBK(myModel.getShzt());
		StringBuffer query = new StringBuffer(SearchUtils.makeQueryCondition(xydm,zydm,bjdm,"",xh,xm,nj,"","",""));
		if(stdm!= null && !("".equalsIgnoreCase(stdm.trim()))){
			query.append(" and stdm='");
			query.append(stdm);
			query.append("' ");
		}
		if(shzt!= null && !("".equalsIgnoreCase(shzt.trim()))){
			query.append(" and shzt='");
			query.append(shzt);
			query.append("' ");
		}
		String [] colList      = new String []{"pk","xh","xm","stmc","sqsj","shzt"}; 
		ArrayList<String[]> rs = dao.sxjyQuery(tableName, query.toString(), new String []{}, colList, "");
		return rs;
	}
	
	public List getRssqTopTr() {
	    DAO dao = DAO.getInstance();
		String tableName     = "view_rssq";
		String [] colList      = new String []{"pk","xh","xm","stmc","sqsj","shzt"}; 
		String[] colListCN     = dao.getColumnNameCN(colList, tableName);
		List topTr = dao.arrayToList(colList, colListCN);//表头 
		
		return topTr;
	}
	
	public boolean deleteRssqOne(String pk, HttpServletRequest request) 
		throws Exception {
		String tableName       = "rtsqdjb";
		String pkComment       = "xh||stdm";
		boolean del = StandardOperation.delete(tableName, pkComment,pk, request);
		return del;
	}
	public HashMap<String, String> getSjszList() {
		String tableName = "nblg_xsgbsjszb";
		String[] colList = new String[] { "xmsbkssj", "xmsbjssj", "xssqkssj",
				"xssqjssj" };
		HashMap<String, String> rs = dao.sxjyQueryOne(tableName, colList, "1",
				"1");
		return rs;
	}
	public boolean saveSjcssz(StglModel myModel, HttpServletRequest request) throws Exception {
//		 保存设置时间
		String tableName = "nblg_xsgbsjszb";
		String[] colList = new String[] { "xmsbkssj", "xmsbjssj", "xssqkssj",
			"xssqjssj" };
		String[] inputList = ModelToStrings.modelToStrings(colList, myModel);
		boolean inserted = StandardOperation.delete(tableName, "1", "1",
				request);
		if (inserted) {
			inserted = StandardOperation.insert(tableName, colList, inputList,
					request);
		}
		return inserted;
	}
	public ArrayList<String[]>serv_xjzyzQuerry(XjqnzyzModel model) {
		return dao.xjzyzQuerry(model);
	}
	
	public List<HashMap<String, String>> getXjzyzTopTr() {
		DAO dao = DAO.getInstance();
		String[] colList = null;
		String[] colListCN = null;
		colList = new String[]{"xh","xm","xb","xymc","bjmc","cjsj","cout","dj"}; 
		colListCN =  new String[]{"学号","姓名","性别","院系","班级","加入志愿服务日期","服务总时(小时)","评级"}; 		
		List<HashMap<String, String>> topTr = dao.arrayToList(colList,colListCN);
		return topTr;
	}
}
