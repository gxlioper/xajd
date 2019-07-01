package xgxt.szdw.server.zjjj;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.szdw.dao.common.CommonDAO;
import xgxt.szdw.model.zjjj.XsgzglModel;
import xgxt.szdw.server.common.CommonService;
import xgxt.szdw.utils.MakeQuery;
import xgxt.szdw.utils.ModelToStrings;
import xgxt.utils.SearchUtils;

public class XsgzdwService extends CommonService{
	
	/**
	* <p>Title: 学工管理系统</p>
	* <p>Description: 学生信息管理思政队伍-浙江经济-学生工作队伍service类</p>
	* <p>Copyright: Copyright (c) 2008</p>
	* <p>Company: zfsoft</p>
	* @author 鲁宁
	* @version 1.0
	*/
	
	CommonDAO dao = new CommonDAO();
	DAO basicDao = DAO.getInstance();
	public ArrayList<String[]> getXsgzdwList(XsgzglModel myModel) {
		//	    浙江经济-学生工作队伍-分管领导信息
		String tableName     = "view_fgldxx";
		String zwdm         = DealString.toGBK(myModel.getZwdm());
		StringBuffer query = new StringBuffer();
		if(!("".equalsIgnoreCase(zwdm.trim()))){
			query.append(" where zwdm='");
			query.append(zwdm);
			query.append("' ");
		}
		String [] colList = new String []{"pk","zgh","xm","zwmc","lxdh"}; 
		ArrayList<String[]> rs = dao.sxjyQuery(tableName, query.toString(), new String []{}, colList, "");
		return rs;
	}
	
	public List getXsgzdwTopTr() {
		//	   浙江经济-学生工作队伍-分管领导信息
	    DAO dao = DAO.getInstance();
	    String tableName     = "view_fgldxx";
	    String [] colList = new String []{"pk","zgh","xm","zwmc","lxdh"}; 
		String[] colListCN     = dao.getColumnNameCN(colList, tableName);
		List topTr = dao.arrayToList(colList, colListCN);//表头 
		
		return topTr;
	}

	public HashMap<String, String> getXsgzdwOne(String pk) {
		// 浙江经济-学生工作队伍-分管领导信息单个
		String tableName     = "view_fgldxx";
		String [] colList = new String []{"pk","zgh","xm","lxdh","fgsw","bz","zwdm"}; 
		HashMap<String, String> rs = dao.sxjyQueryOne(tableName, colList, "pk", pk);
		return rs;
	}

	public boolean updataFgldxx(XsgzglModel myModel, String pk, HttpServletRequest request) throws Exception {
		// 浙江经济-学生工作队伍-分管领导信息单个保存
		String tableName     = "fgldxx";
		String pkComment       = "zgh";
		String [] colList      = new String []{"zgh","xm","lxdh","fgsw","bz","zwdm"}; 
		String [] inputList    = ModelToStrings.modelToStrings(colList, myModel);
		boolean inserted = StandardOperation.delete(tableName, pkComment,pk, request);
		if(inserted){
			inserted = StandardOperation.insert(tableName, colList, inputList, request);
		}
		return inserted;
	}

	public boolean deleteFgldxxOne(String pk, HttpServletRequest request) throws Exception {
		//		 浙江经济-学生工作队伍-分管领导信息单个删除
		String tableName     = "fgldxx";
		String pkComment       = "zgh";
		boolean del = StandardOperation.delete(tableName, pkComment,pk, request);
		return del;
	}
	
	public ArrayList<String[]> getXgbryxxList(XsgzglModel myModel) {
		//	    浙江经济-学生工作队伍-学工部人员信息
		String tableName     = "view_xgbryxx";
		String zwdm         = DealString.toGBK(myModel.getZwdm());
		StringBuffer query = new StringBuffer();
		if(!("".equalsIgnoreCase(zwdm.trim()))){
			query.append(" where zwdm='");
			query.append(zwdm);
			query.append("' ");
		}
		String [] colList = new String []{"pk","zgh","xm","zwmc","lxdh"}; 
		ArrayList<String[]> rs = dao.sxjyQuery(tableName, query.toString(), new String []{}, colList, "");
		return rs;
	}
	
	public List getXgbryxxTopTr() {
		//	   浙江经济-学生工作队伍-学工部人员信息
	    DAO dao = DAO.getInstance();
	    String tableName     = "view_xgbryxx";
	    String [] colList = new String []{"pk","zgh","xm","zwmc","lxdh"}; 
		String[] colListCN     = dao.getColumnNameCN(colList, tableName);
		List topTr = dao.arrayToList(colList, colListCN);//表头 
		
		return topTr;
	}

	public HashMap<String, String> getXgbryxxOne(String pk) {
		// 浙江经济-学生工作队伍-学工部人员信息单个
		String tableName     = "view_xgbryxx";
		String [] colList = new String []{"pk","zgh","xm","lxdh","fgsw","bz","zwdm"}; 
		HashMap<String, String> rs = dao.sxjyQueryOne(tableName, colList, "pk", pk);
		return rs;
	}

	public boolean updataXgbryxx(XsgzglModel myModel, String pk, HttpServletRequest request) throws Exception {
		// 浙江经济-学生工作队伍-学工部人员信息单个保存
		String tableName     = "xgbryxx";
		String pkComment       = "zgh";
		String [] colList      = new String []{"zgh","xm","lxdh","fgsw","bz","zwdm"}; 
		String [] inputList    = ModelToStrings.modelToStrings(colList, myModel);
		boolean inserted = StandardOperation.delete(tableName, pkComment,pk, request);
		if(inserted){
			inserted = StandardOperation.insert(tableName, colList, inputList, request);
		}
		return inserted;
	}

	public boolean deleteXgbryxxOne(String pk, HttpServletRequest request) throws Exception {
		//		 浙江经济-学生工作队伍-学工部人员信息单个删除
		String tableName     = "xgbryxx";
		String pkComment       = "zgh";
		boolean del = StandardOperation.delete(tableName, pkComment,pk, request);
		return del;
	}
	
	public List getZwList() throws SQLException {
		// 得到职务列表
		CommonDAO dao = new CommonDAO();
		return dao.getZwList();
	}

	public Object getbjxsList(String bj) {
		// 该班级学生列表
		CommonDAO dao = new CommonDAO();
		return dao.getBjxsList(bj);
	}

	public ArrayList<String[]> getZlbzrxxList(XsgzglModel myModel) {
		// 助理班主任信息
		String tableName     = "view_zlbzrxx";
		String xydm         = DealString.toGBK(myModel.getXydm());
		String xm           = DealString.toGBK(myModel.getXm());
		String zydm         = DealString.toGBK(myModel.getZydm());
		String bjdm         = DealString.toGBK(myModel.getBjdm());
		String nj         = DealString.toGBK(myModel.getNj());
		StringBuffer query = new StringBuffer(SearchUtils.makeQueryCondition(xydm,zydm,bjdm,"","",xm,nj,"","",""));
		String [] colList = new String []{"pk","bjmc","xm","xymc","zymc","bjmc","zlsf"}; 
		ArrayList<String[]> rs = dao.sxjyQuery(tableName, query.toString(), new String []{}, colList, "");
		return rs;
	}

	public List getZlbzrxxTopTr() {
		// 助理班主任信息
		DAO dao = DAO.getInstance();
		String tableName     = "view_zlbzrxx";
		String [] colList = new String []{"pk","bjmc","xm","xymc","zymc","bjmc","zlsf"}; 
		String[] colListCN     = dao.getColumnNameCN(colList, tableName);
		List topTr = dao.arrayToList(colList, colListCN);//表头 
		return topTr;
	}

	public HashMap<String, String> getZlbzrxxOne(String pk) {
		String tableName     = "view_zlbzrxx";
		String [] colList = new String []{"pk","bjdm","xm","lxdh","fgsw","bz","zlsf","xydm","zydm","nj"}; 
		HashMap<String, String> rs = dao.sxjyQueryOne(tableName, colList, "pk", pk);
		return rs;
	}

	public boolean updataZlbzrxx(XsgzglModel myModel, String pk, HttpServletRequest request) throws Exception {
		// TODO 自动生成方法存根
		String tableName     = "zlbzrxx";
		String pkComment       = "bjdm||xm";
		String [] colList = new String []{"bjdm","xm","lxdh","fgsw","bz","zlsf"};
		String [] inputList    = ModelToStrings.modelToStrings(colList, myModel);
		boolean inserted = StandardOperation.delete(tableName, pkComment,pk, request);
		if(inserted){
			inserted = StandardOperation.insert(tableName, colList, inputList, request);
		}
		return inserted;
	}

	public boolean deleteZlbzrxxOne(String pk, HttpServletRequest request) throws Exception {
		String tableName     = "zlbzrxx";
		String pkComment       = "bjdm||xm";
		boolean del = StandardOperation.delete(tableName, pkComment,pk, request);
		return del;
	}

	public ArrayList<String[]> getDzzsjList(XsgzglModel myModel) {
		String tableName     = "view_dzzsj";
		String xydm         = DealString.toGBK(myModel.getXydm());
		StringBuffer query = new StringBuffer();
		if(!("".equalsIgnoreCase(xydm.trim()))){
			query.append(" where xydm='");
			query.append(xydm);
			query.append("' ");
		}
		String [] colList = new String []{"pk","zgh","xm","xymc","lxdh"}; 
		ArrayList<String[]> rs = dao.sxjyQuery(tableName, query.toString(), new String []{}, colList, "");
		return rs;
	}

	public List getDzzsjTopTr() {
		DAO dao = DAO.getInstance();
	    String tableName     = "view_dzzsj";
	    String [] colList = new String []{"pk","zgh","xm","xymc","lxdh"}; 
		String[] colListCN     = dao.getColumnNameCN(colList, tableName);
		List topTr = dao.arrayToList(colList, colListCN);//表头 
		
		return topTr;
	}

	public HashMap<String, String> getDzzsjOne(String pk) {
		   String tableName     = "view_dzzsj";
		String [] colList = new String []{"pk","zgh","xm","xydm","lxdh","bz","fgsw"}; 
		HashMap<String, String> rs = dao.sxjyQueryOne(tableName, colList, "pk", pk);
		return rs;
	}

	public boolean updataDzzsj(XsgzglModel myModel, String pk, HttpServletRequest request) throws Exception {
		String tableName     = "dzzsjb";
		String pkComment       = "zgh";
		String [] colList      = new String []{"zgh","xm","lxdh","fgsw","bz","xydm"}; 
		String [] inputList    = ModelToStrings.modelToStrings(colList, myModel);
		boolean inserted = StandardOperation.delete(tableName, pkComment,pk, request);
		if(inserted){
			inserted = StandardOperation.insert(tableName, colList, inputList, request);
		}
		return inserted;
	}

	public boolean deleteDzzsjOne(String pk, HttpServletRequest request) throws Exception {
		String tableName     = "dzzsjb";
		String pkComment       = "zgh";
		boolean del = StandardOperation.delete(tableName, pkComment,pk, request);
		return del;
	}

	public ArrayList<String[]> getXshzzxxList(XsgzglModel myModel) {
		String tableName     = "view_xshzzxx";
		String xydm         = DealString.toGBK(myModel.getXydm());
		String xm           = DealString.toGBK(myModel.getXm());
		String zydm         = DealString.toGBK(myModel.getZydm());
		String bjdm         = DealString.toGBK(myModel.getBjdm());
		String nj         = DealString.toGBK(myModel.getNj());
		String xh         = DealString.toGBK(myModel.getXh());
		StringBuffer query = new StringBuffer(SearchUtils.makeQueryCondition(xydm,zydm,bjdm,"",xh,xm,nj,"","",""));
		String [] colList = new String []{"pk","xh","xm","xymc","zymc","bjmc","jb"}; 
		ArrayList<String[]> rs = dao.sxjyQuery(tableName, query.toString(), new String []{}, colList, "");
		return rs;
	}

	public List getXshzzxxTopTr() {
		DAO dao = DAO.getInstance();
		String tableName     = "view_xshzzxx";
		String [] colList = new String []{"pk","xh","xm","xymc","zymc","bjmc","jb"}; 
		String[] colListCN     = dao.getColumnNameCN(colList, tableName);
		List topTr = dao.arrayToList(colList, colListCN);//表头 
		
		return topTr;
	}

	public HashMap<String, String> getXshzzxxOne(String pk, String xh) {
		String tableName     = "view_xshzzxx";
		String [] colList = new String []{"pk","xh","xm","xb","xydm","xymc","zymc","bjmc","lxdh","bz","zwdm","nj","jb","zydm","bjdm"}; 
		HashMap<String, String> rs = dao.sxjyQueryOne(tableName, colList, "pk", pk);
		colList = new String []{"xh","xm","xb","nj","xydm","xymc","zydm","zymc","bjdm","bjmc"};
		if(!xh.equalsIgnoreCase("")) {
			rs = dao.sxjyQueryOne3("view_xsjbxx", colList, "xh", xh, rs, "");
		}
		return rs;
	}

	public boolean updataXshzzxx(XsgzglModel myModel, String pk, HttpServletRequest request) throws Exception {
		String tableName     = "xshzzxxb";
		String pkComment       = "xh";
		String [] colList      = new String []{"xh","lxdh","zwdm","jb","bz"}; 
		String [] inputList    = ModelToStrings.modelToStrings(colList, myModel);
		boolean inserted = StandardOperation.delete(tableName, pkComment,pk, request);
		if(inserted){
			inserted = StandardOperation.insert(tableName, colList, inputList, request);
		}
		return inserted;
	}

	public boolean deleteXshzzxxOne(String pk, HttpServletRequest request) throws Exception {
		String tableName     = "xshzzxxb";
		String pkComment       = "xh";
		boolean del = StandardOperation.delete(tableName, pkComment,pk, request);
		return del;
	}

	public List<HashMap<String, String>> getXshzw() {
		// 学生会职务列表
		DAO dao = DAO.getInstance();
		String sql = "select zwdm,zwmc from xshzwb";
		return dao.getList(sql, new String[] {}, new String[] { "zwdm", "zwmc" });
	}
	
	public List<HashMap<String, String>> getSzdwrylb() {
		// 宁波理工思政队伍人员类别表
		DAO dao = DAO.getInstance();
		String sql = "select rylbdm,rylbmc from nblg_szdwrylbb";
		return dao.getList(sql, new String[] {}, new String[] { "rylbdm", "rylbmc" });
	}
	
	public List<HashMap<String, String>> getSzdwzyjsdjb() {
		// 宁波理工单独思政队伍专业技术等级表
		DAO dao = DAO.getInstance();
		String sql = "select djdm,djmc from nblg_szdwzyjsdjb";
		return dao.getList(sql, new String[] {}, new String[] { "djdm", "djmc" });
	}
	
	public List<HashMap<String, String>> getSzdwzwdmb() {
		// 宁波理工单独思政队伍专业技术等级表
		DAO dao = DAO.getInstance();
		String sql = "select zwdm,zwmc from nblg_szdwzwdmb";
		return dao.getList(sql, new String[] {}, new String[] { "zwdm", "zwmc" });
	}
	
	public List<HashMap<String, String>> getBmList() {
		//宁波理工思政队伍
		DAO dao = DAO.getInstance();
		return dao.getBmList();
	}

	public boolean deleteNblgSzdwxxOne(String pk, HttpServletRequest request) throws Exception {
		//宁波理工思政队伍
		String tableName     = "nblg_szdwxxb";
		String pkComment       = "zgh";
		boolean del = StandardOperation.delete(tableName, pkComment,pk, request);
		return del;
	}

	public boolean updataNblgSzdwxxOne(XsgzglModel myModel, String pk, HttpServletRequest request) throws Exception {
		//宁波理工思政队伍
		String tableName     = "nblg_szdwxxb";
		String pkComment       = "zgh";
		String [] colList = new String []{"zgh","xm","bmdm","zwdm","gw","zyjsdj","rylb","sfzh"}; 
		String [] inputList    = ModelToStrings.modelToStrings(colList, myModel);
		boolean inserted = StandardOperation.delete(tableName, pkComment,pk, request);
		if(inserted){
			inserted = StandardOperation.insert(tableName, colList, inputList, request);
		}
		return inserted;
	}

	public HashMap<String, String> getNblgSzdwxxOne(String pk) {
		//宁波理工思政队伍
		String tableName     = "view_nblgSzdwxx";
		String [] colList = new String []{"pk","zgh","xm","bmdm","zwdm","zwmc","bmmc","gw","zyjsdjmc","zyjsdj","rylb","rylbmc","sfzh"}; 
		HashMap<String, String> rs = dao.sxjyQueryOne(tableName, colList, "pk", pk);
		return rs;
	}

	public ArrayList<String[]> getSzdwxxList(XsgzglModel myModel) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		//宁波理工思政队伍
		String tableName     = "view_nblgSzdwxx";
		StringBuffer query = MakeQuery.makeQuery(new String []{"bmdm","zwdm","zyjsdj","rylb"},myModel);
		String [] colList = new String []{"pk","zgh","xm","bmmc","zwmc","zyjsdjmc","rylbmc","sfzh"}; 
		ArrayList<String[]> rs = dao.sxjyQuery(tableName, query.toString(), new String []{}, colList, "");
		return rs;
	}

	public List getSzdwxxTopTr() {
		//宁波理工思政队伍
		DAO dao = DAO.getInstance();
		String tableName     = "view_nblgSzdwxx";
		String [] colList = new String []{"pk","zgh","xm","bmmc","zwmc","zyjsdjmc","rylbmc","sfzh"}; 
		String[] colListCN     = dao.getColumnNameCN(colList, tableName);
		List topTr = dao.arrayToList(colList, colListCN);//表头 
		return topTr;
	}

	public ArrayList<String[]> getBjxxqkList(XsgzglModel myModel) {
		//班级详细情况
		String tableName     = "view_bjxxxx";
		String xydm         = DealString.toGBK(myModel.getXydm());
		String sffp          = DealString.toGBK(myModel.getSffp());
		String zydm         = DealString.toGBK(myModel.getZydm());
		String bjdm         = DealString.toGBK(myModel.getBjdm());
		String nj         = DealString.toGBK(myModel.getNj());
		StringBuffer query = new StringBuffer(SearchUtils.makeQueryCondition(xydm,zydm,bjdm,"","","",nj,"","",""));
		if(!("".equalsIgnoreCase(sffp.trim()))&&sffp.equalsIgnoreCase("未分配")){
			query.append(" and fdyxm='未分配' ");
		}else if(!("".equalsIgnoreCase(sffp.trim()))&&!sffp.equalsIgnoreCase("未分配")){
			query.append(" and fdyxm!='未分配' ");
		}
		String [] colList = new String []{"bjdm","zymc","bjmc","bjrs","fdyxm","fdyyddh","bzrxm","zlbzrxm"}; 
		ArrayList<String[]> rs = dao.sxjyQuery(tableName, query.toString(), new String []{}, colList, "");
		return rs;
	}

	public List getBjxxqkTopTr() {
		//获得班级详细情况表头
		DAO dao = DAO.getInstance();
		String tableName     = "view_bjxxxx";
		String [] colList = new String []{"bjdm","zymc","bjmc","bjrs","fdyxm","fdyyddh","bzrxm","zlbzrxm"}; 
		String[] colListCN     = dao.getColumnNameCN(colList, tableName);
		List topTr = dao.arrayToList(colList, colListCN);//表头 
		return topTr;
	}
}
