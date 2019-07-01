package xgxt.shgz.server;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.shgz.model.XsgbForNblgModel;
import xgxt.szdw.dao.common.CommonDAO;
import xgxt.szdw.utils.MakeQuery;
import xgxt.szdw.utils.ModelToStrings;
import xgxt.utils.GetTime;
import xgxt.utils.SearchUtils;

public class XsgbForNblgService {
	
	CommonDAO dao = new CommonDAO();
	DAO basicDao = DAO.getInstance();
	public List<HashMap<String, String>> getBmList() {
		DAO dao = DAO.getInstance();
		return dao.getBmList();
	}

	public ArrayList<String[]> getXshzzList(XsgbForNblgModel myModel) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
			String tableName     = "view_xshzzsb";
			StringBuffer query = MakeQuery.makeQuery(new String []{"bmdm"},myModel);
			String [] colList = new String []{"pk","zzdm","zzmc","zyfzr","zdls"}; 
			ArrayList<String[]> rs = dao.sxjyQuery(tableName, query.toString(), new String []{}, colList, "");
			return rs;
	}

	public List getXshzzTopTr() {
		 	DAO dao = DAO.getInstance();
		 	String tableName     = "view_xshzzsb";
		 	String [] colList = new String []{"pk","zzdm","zzmc","zyfzr","zdls"}; 
			String[] colListCN     = dao.getColumnNameCN(colList, tableName);
			List topTr = dao.arrayToList(colList, colListCN);//表头 
			
			return topTr;
	}

	public HashMap<String, String> getXshzzxxOne(String pk) {
		// 获得学生会组织单个信息
		String tableName    = "view_xshzzsb";
		String [] colList = new String []{"pk","zzdm","zzmc","xn","xq","zyfzr","fzrlxfs","zdls","lslxfs","xmjb","xmdm","zzcys","bmdm","xxgblb","xmmc","bmmc"};
		HashMap<String, String> rs = dao.sxjyQueryOne(tableName, colList, "pk", pk);
		return rs;
	}

	public Object getSukmList() {
		// 获得所属科目列表
		DAO dao = DAO.getInstance();
		String sql = "select xmdm,xmmc from nblg_sskmdmb";
		return dao.getList(sql, new String[] {}, new String[] { "xmdm", "xmmc" });
	}
	
	public Object getXsgbzlList(String zzdm) {
		//获得学生干部种类表
		String sql = "";
		if(zzdm.equalsIgnoreCase("")){
			sql = "select a.bgbdm,a.bgbmc,'' checks from nblg_xsgbzldmb a order by a.bgbdm";
		}else{
			sql = "select a.bgbdm,a.bgbmc,nvl(b.checks,'') checks from nblg_xsgbzldmb a left join (select bgbdm,'checked' checks from nblg_xszzsybgbdmb where zzdm = '"+zzdm
			+"') b on a.bgbdm = b.bgbdm order by a.bgbdm";
		}
		DAO dao = DAO.getInstance();
		return dao.getList(sql, new String[] {}, new String[] { "bgbdm", "bgbmc","checks"});
	}

	public boolean updataXshzzxxOne(XsgbForNblgModel myModel, String pk, HttpServletRequest request) throws Exception {
		String tableName       = "nblg_xshzzsbb";
		String pkComment       = "zzdm"; 
		String [] colList = new String []{"zzdm","zzmc","xn","xq","zyfzr","fzrlxfs","zdls","lslxfs","xmjb","xmdm","zzcys","bmdm","xxgblb"}; 
		String [] inputList    = ModelToStrings.modelToStrings(colList, myModel);
		String [] bgbdms = myModel.getBgbdmList();
		String zzdm = myModel.getZzdm();
		boolean inserted  =  StandardOperation.delete("nblg_xszzsybgbdmb", pkComment,pk, request);
		if(inserted){
				String[] insertSql = new String[bgbdms.length];
				for(int i=0;i<bgbdms.length;i++){
					insertSql[i] = StandardOperation.insertSql("nblg_xszzsybgbdmb", new String []{"zzdm","bgbdm"}, new String []{zzdm,bgbdms[i]}, request);
				}
				DAO dao = DAO.getInstance();
				int[] res = dao.runBatch(insertSql);
				for(int i=0;i<res.length;i++){
					inserted = (res[i]==Statement.EXECUTE_FAILED)?false:true;
					if(!inserted) break;
				}
					
			}
		if(inserted){
			inserted = StandardOperation.delete(tableName, pkComment,pk, request);
		}
		if(inserted){
			inserted = StandardOperation.insert(tableName, colList, inputList, request);
		}
		return inserted;
	}

	public boolean deleteXshzzxxOne(String pk, HttpServletRequest request) throws Exception {
		String tableName       = "nblg_xshzzsbb";
		String pkComment       = "zzdm";
		boolean del  = StandardOperation.delete(tableName, pkComment,pk, request);
		return del;
	}
	
	public Object getXszzsybgbList(String zzdm) {
		// 获得学生干部种类表
		DAO dao = DAO.getInstance();
		String sql = "select zzdm,bgbdm from nblg_xszzsybgbdmb where zzdm = ?";
		return dao.getList(sql, new String[] {zzdm}, new String[] { "zzdm", "gbzlmc" });
	}

	public HashMap<String, String> getXsgbsq(String xh) {
		String tableName    = "view_xsjbxx";
		String [] colList = new String []{"xh","xm","xb","nj","xydm","xymc","zydm","zymc","bjdm","bjmc"};
		HashMap<String, String> rs = dao.sxjyQueryOne(tableName, colList, "xh", xh);
		return rs;
	}

	public String[] getYrxsgbList(String xh) throws SQLException {
		DAO dao = DAO.getInstance();
		String sql = "select bmmc||'-'||zzmc||'-'||bgbmc yrxsgb from view_xszzgbsq where xh = ? and shzt = '通过'";
		return dao.getArray(sql, new String []{xh}, "yrxsgb");
	}

	public Object getXszzList(String bmdm) {
		// 获得学生组织列表
		DAO dao = DAO.getInstance();
		if(!bmdm.equalsIgnoreCase("")){
			String sql = "select zzdm,zzmc from nblg_xshzzsbb where bmdm = ?";
			return dao.getList(sql, new String[] {bmdm}, new String[] { "zzdm", "zzmc" });
		}else{
			String sql = "select zzdm,zzmc from nblg_xshzzsbb";
			return dao.getList(sql, new String[] {}, new String[] { "zzdm", "zzmc" });
		}
	}

	public Object getXsgbList(String zzdm) {
		// 获得学生组织可申请干部列表
		DAO dao = DAO.getInstance();
		if(!zzdm.equalsIgnoreCase("")){
			String sql = "select bgbdm,bgbmc from view_nblgXszzsybgb where zzdm = ?";
			return dao.getList(sql, new String[] {zzdm}, new String[] { "bgbdm", "bgbmc" });
		}else{
			String sql = "select bgbdm,bgbmc from view_nblgXszzsybgb";
			return dao.getList(sql, new String[] {}, new String[] { "bgbdm", "bgbmc" });
		}
	}

	public boolean saveXsgbsqOne(XsgbForNblgModel myModel, HttpServletRequest request) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		//　保存学生干部申请
		String tableName       = "nblg_xszzgbsqb";
		String [] colList = new String []{"xh","zzdm","bgbdm"}; 
		String [] inputList    = ModelToStrings.modelToStrings(colList, myModel);
		boolean inserted = StandardOperation.insert(tableName, colList, inputList, request);
		return inserted;
	}
	
	public boolean saveXsgbshOne(XsgbForNblgModel myModel, String pk,HttpServletRequest request) throws Exception {
		//　保存学生干部申请
		String tableName       = "nblg_xszzgbsqb";
		String pkComment       = "xh||zzdm||bgbdm"; 
		boolean inserted  = StandardOperation.delete(tableName, pkComment,pk, request);
		String [] colList = new String []{"xh","zzdm","bgbdm","rzkssj","rzjssj","shzt","khdj"}; 
		String [] inputList    = ModelToStrings.modelToStrings(colList, myModel);
		if(inserted){
			inserted = StandardOperation.insert(tableName, colList, inputList, request);
		}
		return inserted;
	}

	public String getBmdmForZzdm(String zzdm) {
		// 通过组织代码获取部门代码
		DAO dao = DAO.getInstance();
		String sql = "select bmdm from nblg_xshzzsbb where zzdm = ?";
		return dao.getOneRs(sql, new String []{zzdm}, "bmdm");
	}

	public ArrayList<String[]> getXsgbList(XsgbForNblgModel myModel) {
		String tableName     = "view_xszzgbsq";
		String xydm         = DealString.toGBK(myModel.getXydm());
		String xm           = DealString.toGBK(myModel.getXm());
		String zydm         = DealString.toGBK(myModel.getZydm());
		String bjdm         = DealString.toGBK(myModel.getBjdm());
		String nj         = DealString.toGBK(myModel.getNj());
		String xh         = DealString.toGBK(myModel.getXh());
		String bmdm         = DealString.toGBK(myModel.getBmdm());
		String zzdm         = DealString.toGBK(myModel.getZzdm());
		StringBuffer query = new StringBuffer(SearchUtils.makeQueryCondition(xydm,zydm,bjdm,"",xh,xm,nj,"","",""));
		if(bmdm != null && !("".equalsIgnoreCase(bmdm.trim()))){
			query.append(" and bmdm='");
			query.append(bmdm);
			query.append("' ");
		}
		if(zzdm != null && !("".equalsIgnoreCase(zzdm.trim()))){
			query.append(" and zzdm='");
			query.append(zzdm);
			query.append("' ");
		}
		String [] colList = new String []{"pk","xh","xm","xymc","bjmc","bmmc","zzmc","bgbmc","shzt"}; 
		ArrayList<String[]> rs = dao.sxjyQuery(tableName, query.toString(), new String []{}, colList, "");
		return rs;
	}
	
	public List getXsgbTopTr() {
		// 学生干部申请审核表头
	    DAO dao = DAO.getInstance();
		String tableName     = "view_xszzgbsq";
		String [] colList = new String []{"pk","xh","xm","xymc","bjmc","bmmc","zzmc","bgbmc","shzt"}; 
		String[] colListCN     = dao.getColumnNameCN(colList, tableName);
		List topTr = dao.arrayToList(colList, colListCN);//表头 
		
		return topTr;
	}
	
	public boolean delXsgbOne(String pk, HttpServletRequest request) throws Exception {
		String tableName       = "nblg_xszzgbsqb";
		String pkComment       = "xh||zzdm||bgbdm";
		boolean del  = StandardOperation.delete(tableName, pkComment,pk, request);
		return del;
	}

	public HashMap<String, String> getXsgbshOne(String pk, String xh) {
		String tableName     = "view_xszzgbsq";
		String [] colList = new String []{"pk","xh","xm","xb","nj","xydm","xymc","zydm","zymc","bjdm","bjmc","zzdm","bmdm","bgbdm","rzkssj","rzjssj","shzt","khdj"};
		HashMap<String, String> rs = dao.sxjyQueryOne(tableName, colList, "pk", pk);
		tableName    = "view_xsjbxx";
		colList = new String []{"xh","xm","xb","nj","xydm","xymc","zydm","zymc","bjdm","bjmc"};
		if(!xh.equalsIgnoreCase("")) {
			rs = dao.sxjyQueryOne3("view_xsjbxx", colList, "xh", xh, rs, "");
		}
		return rs;
	}

	public boolean xsgbsqsj() {
		// TODO 自动生成方法存根
		StglService sjService = new StglService();
		HashMap<String, String> szsj = sjService.getSjszList();
		String sqkssj =  szsj.get("xssqkssj");
		String sqjssj =  szsj.get("xssqjssj");
		String dqsj = GetTime.getNowTime2();
		if(Integer.parseInt(sqkssj)<=Integer.parseInt(dqsj)&&Integer.parseInt(dqsj)<=Integer.parseInt(sqjssj)){
			return true;
		}else{
			return false;
		}
	}
	
	public boolean xszzsbsj() {
		// TODO 自动生成方法存根
		StglService sjService = new StglService();
		HashMap<String, String> szsj = sjService.getSjszList();
		String kssj =  szsj.get("xmsbkssj");
		String jssj =  szsj.get("xmsbjssj");
		String dqsj = GetTime.getNowTime2();
		if(Integer.parseInt(kssj)<=Integer.parseInt(dqsj)&&Integer.parseInt(dqsj)<=Integer.parseInt(jssj)){
			return true;
		}else{
			return false;
		}
	}
}
