package xgxt.szdw.zjlg;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.szdw.utils.ModelToStrings;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.MakeQuery;

public class XjchDAO {
	
	DAO dao = DAO.getInstance();

	/**
	 * 先进称号列表
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getXjchList() throws Exception {
		return dao.getList("select xjchdm,xjchmc from xjchdmb", new String[]{}, new String[]{"xjchdm", "xjchmc"});
	}

	public String[] getXjchXx(String xjchdm) {
		return dao.getOneRs("select xjchmc,xjchlb from xjchdmb where xjchdm = ?", new String[]{xjchdm}, new String[]{"xjchmc","xjchlb"});
	}

	public boolean xjchMffdySave(XjchModel myModel, HttpServletRequest request) throws Exception {
		String tableName = "zjlg_mffdysqb";
		String pkComment = "xn||zgh";
		String pk = DealString.toGBK(myModel.getPk());
		boolean updata = true;
		if("".equalsIgnoreCase(pk)){
			String[] colList = new String[] { "bz","hjqk","sdnjbjqk","xn","zgh","zysj"};
			String[] inputList = ModelToStrings.modelToStrings(colList, myModel);
			updata = StandardOperation.insert(tableName, colList, inputList,
					request);
		} else {
			String[] colList = new String[] { "bz","dwzjyj","hjqk","sdnjbjqk","shzt","xgbyj","xn","xxyj","zgh","zysj" };
			String[] inputList = ModelToStrings.modelToStrings(colList, myModel);
			updata = StandardOperation.update(tableName, colList, inputList,
					pkComment, pk, request);
		}
		return updata;
	}
	
	public boolean xjchYxbzrSave(XjchModel myModel, HttpServletRequest request) throws Exception {
		String tableName = "zjlg_yxbzrb";
		String pkComment = "xn||zgh";
		String pk = DealString.toGBK(myModel.getPk());
		boolean updata = true;
		if("".equalsIgnoreCase(pk)){
			String[] colList = new String[] {  "hjqk","sdbjxshjqk","xn","zgh"};
			String[] inputList = ModelToStrings.modelToStrings(colList, myModel);
			updata = StandardOperation.insert(tableName, colList, inputList,
					request);
		} else {
			String[] colList = new String[] { "hjqk","sdbjxshjqk","shzt","xn","xxyj","xyyj","zgh","gzzj" };
			String[] inputList = ModelToStrings.modelToStrings(colList, myModel);
			updata = StandardOperation.update(tableName, colList, inputList,
					pkComment, pk, request);
		}
		return updata;
	}
	
	/**
	 * 先进称号审核查询
	 * @param xjchdm 
	 * @param xjchdm 
	 */
	public  ArrayList<String[]> getXjchshList(XjchModel model, String xjchdm) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		MakeQuery mquery = new MakeQuery();
		mquery.makeQuery(new String []{"bmdm","xn","shzt"},new String []{"xm"},model);
		String tableName = "";
		String[] colList = new String[]{"xn||zgh","zgh","xm","bmmc","zwmc","lxdh","shzt"};
		if(xjchdm.equalsIgnoreCase("mffdy")){
			tableName = "view_mffdysqb";
		}else if(xjchdm.equalsIgnoreCase("yxbzr")){
			tableName = "view_yxbzrb";
		}
		return CommonQueryDAO.commonQuery(tableName, mquery.getQueryString(), mquery.getInputList(), colList,"", model);		
	}

	/**
	 * 获取审核下拉框信息
	 */
	public List<HashMap<String, String>>  dao_getChkList(){
		DAO dao = DAO.getInstance();		
		return dao.getChkList(3);
	}
//	
	/**
	 * 先进称号批量审核
	 */
	public boolean dao_XjchCk(String check,String xmk,String pkVStr) throws SQLException{
		DAO dao = DAO.getInstance();
		String pk = "";
		String shType = "shzt";
		String realTable= "";
		if(xmk.equalsIgnoreCase("mffdy")){
			realTable="zjlg_mffdysqb";
		}else{
			realTable="zjlg_yxbzrb";
		}
		pk="xn||zgh";
		check = "yes".equalsIgnoreCase(check)?"通过":"不通过";
		String[] pkValueA = pkVStr.split("!!");		
		String[] updPkSql  = new String[pkValueA.length];		
		for (int i = 0; i < pkValueA.length; i++) {
			updPkSql[i] = Base.isNull(pkValueA[i])?"":"update "+realTable+" set "+shType+"='"+check+"'  where "+pk+"= '"+pkValueA[i]+"'";							
		}              
		boolean doFlag = false;
		int[] array = dao.runBatch(updPkSql);
		doFlag = dao.checkBatch(array);   
		return doFlag;
	}
//
//	public ArrayList<String[]> getXjchCxList(String userType, String userName, XjchModel model, String xjchdm) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
//		// TODO 自动生成方法存根
////		 学院代码
//		String xydm = DealString.toGBK(model.getXydm());
//		// 专业代码
//		String zydm = DealString.toGBK(model.getZydm());
//		// 班级代码
//		String bjdm = DealString.toGBK(model.getBjdm());
//		// 年级
//		String nj = DealString.toGBK(model.getNj());
//		// 学年
//		String xn = DealString.toGBK(model.getXn());
//		// 学期
//		String xq =model.getXq();
//		// 学号
//		String xh =model.getXh();
//		xh = Base.isNull(xh) ? "%" : xh;
//		// 姓名
//		String xm = model.getXm();
//		xm = Base.isNull(xm) ? "%" : "%" + xm + "%";		
//		StringBuffer query = new StringBuffer();
//		query.append(" where 1=1");
//		query.append(Base.isNull(xjchdm) ? " and 1=1" : " and xjchdm='"+xjchdm+ "'");
//		query.append(Base.isNull(xq) ? " and 1=1" : " and xq='"+xq+ "'");
//		query.append(Base.isNull(xydm) ? " and 1=1" : " and xydm='"+xydm+ "'");
//		query.append(Base.isNull(zydm) ? " and 1=1" : " and zydm='"+zydm+ "'");
//		query.append(Base.isNull(bjdm) ? " and 1=1" : " and bjdm='"+bjdm+ "'");
//		query.append(Base.isNull(nj) ? " and 1=1" : " and nj='"+nj+ "'");
//		query.append(Base.isNull(xn) ? " and 1=1" : " and xn='"+xn+ "'");	
//		String fdyQuerry = "isFdy".equalsIgnoreCase(userType) ? " and bjdm in (select bjdm from fdybjb b where zgh like '"+ userName + "' )":"";
//		query.append(fdyQuerry);
//		query.append(" and xh like ?");
//		query.append(" and xm like ?");			
//		String[] colList = new String[]{"xh||xn||xq||xjchdm","xn","xh","xm","xymc","bjmc","fdysh","xysh","xxsh"}; 		
//		return CommonQueryDAO.commonQuery("view_xsxjchb", query.toString(), new String[] {xh,xm}, colList,"", model);		
//	}
//
//	public boolean dao_xjchDel(String userType, String xjchdm, String pkVStr) throws SQLException {
//		DAO dao = DAO.getInstance();
//		String pk = "";
////		String shType = "";
//		String realTable= "xsxjchb";
//		pk = "xh||xn||xq||xjchdm";
//		String querry="";
//		if("xy".equalsIgnoreCase(userType)){
//			querry=" and xxsh='未审核'";
//		}else if("xx".equalsIgnoreCase(userType)||"admin".equalsIgnoreCase(userType)){
//			
//		}else{
//			querry="and xysh='未审核' and xxsh='未审核'";
//		}
//		String[] pkValueA = pkVStr.split("!!");		
//		String[] updPkSql  = new String[pkValueA.length];		
//		for (int i = 0; i < pkValueA.length; i++) {
//			updPkSql[i] = Base.isNull(pkValueA[i])?"":"delete "+realTable+" where "+pk+"= '"+pkValueA[i]+"'  "+querry;							
//		}              
//		boolean doFlag = false;
//		int[] array = dao.runBatch(updPkSql);
//		doFlag = dao.checkBatch(array);   
//		return doFlag;
//	}
	
	public boolean xjchPxxxfkSave(XjchModel myModel, HttpServletRequest request) throws Exception {
		String tableName = "zjlg_fdypxxmb";
		String pkComment = "zgh||pxxm||kssj";
		String pk = DealString.toGBK(myModel.getPk());
		boolean updata = true;
		if("".equalsIgnoreCase(pk)){
			String[] colList = new String[] {"bz","jssj","jtnr","kssj","pxxm","zgh"};
			String[] inputList = ModelToStrings.modelToStrings(colList, myModel);
			updata = StandardOperation.insert(tableName, colList, inputList,
					request);
		} else {
			String[] colList = new String[] {"bz","jssj","jtnr","kssj","pxxm","zgh","shzt"};
			String[] inputList = ModelToStrings.modelToStrings(colList, myModel);
			updata = StandardOperation.update(tableName, colList, inputList,
					pkComment, pk, request);
		}
		return updata;
	}
	
	public boolean xjchGzxxfkSave(XjchModel myModel, HttpServletRequest request) throws Exception {
		String tableName = "zjlg_fdygzxxb";
		String pkComment = "zgh||xxzt";
		String pk = DealString.toGBK(myModel.getPk());
		boolean updata = true;
		if("".equalsIgnoreCase(pk)){
			String[] colList = new String[] {"bz","jtnr","jyyj","xxzt","zgh"};
			String[] inputList = ModelToStrings.modelToStrings(colList, myModel);
			updata = StandardOperation.insert(tableName, colList, inputList,
					request);
		} else {
			String[] colList = new String[] {"bz","jtnr","jyyj","xxzt","zgh","shzt"};
			String[] inputList = ModelToStrings.modelToStrings(colList, myModel);
			updata = StandardOperation.update(tableName, colList, inputList,
					pkComment, pk, request);
		}
		return updata;
	}
	
	/**
	 * 信息反馈审核查询
	 * @param  
	 */
	public  ArrayList<String[]> getXxfkshList(XjchModel model, String xjchdm) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		MakeQuery mquery = new MakeQuery();
		mquery.makeQuery(new String []{"bmdm","shzt"},new String []{"xm","zgh"},model);
		String tableName = "";
		String[] colList=null;
		if(xjchdm.equalsIgnoreCase("pxxxfk")){
			tableName = "view_fdypxxm";
			colList = new String[]{"pk","zgh","xm","bmmc","zwmc","lxdh","pxxm","kssj","jssj","shzt"};
		}else if(xjchdm.equalsIgnoreCase("gzxxfk")){
			tableName = "view_fdygzxx";
			colList = new String[]{"pk","zgh","xm","bmmc","zwmc","lxdh","xxzt","shzt"};
		}
		return CommonQueryDAO.commonQuery(tableName, mquery.getQueryString(), mquery.getInputList(), colList,"", model);		
	}
	
	/**
	 * 信息反馈批量审核
	 */
	public boolean dao_XxfkCk(String check,String xmk,String pkVStr) throws SQLException{
		DAO dao = DAO.getInstance();
		String pk = "";
		String shType = "shzt";
		String realTable= "";
		if(xmk.equalsIgnoreCase("pxxxfk")){
			realTable="zjlg_fdypxxmb";
			pk="zgh||pxxm||kssj";
		}else{
			realTable="zjlg_fdygzxxb";
			pk="zgh||xxzt";
		}
		
		check = "yes".equalsIgnoreCase(check)?"通过":"不通过";
		String[] pkValueA = pkVStr.split("!!");		
		String[] updPkSql  = new String[pkValueA.length];		
		for (int i = 0; i < pkValueA.length; i++) {
			updPkSql[i] = Base.isNull(pkValueA[i])?"":"update "+realTable+" set "+shType+"='"+check+"'  where "+pk+"= '"+pkValueA[i]+"'";							
		}              
		boolean doFlag = false;
		int[] array = dao.runBatch(updPkSql);
		doFlag = dao.checkBatch(array);   
		return doFlag;
	}
	
	/**
	 * 先进称号列表
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getFdydwlbList() throws Exception {
		return dao.getList("select lbdm,lbmc from fdydwlbdmb", new String[]{}, new String[]{"lbdm", "lbmc"});
	}

}
