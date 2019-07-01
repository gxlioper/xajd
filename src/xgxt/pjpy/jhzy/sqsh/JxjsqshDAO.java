package xgxt.pjpy.jhzy.sqsh;

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
import xgxt.pjpy.jhzy.JhzyPjpyDAO;
import xgxt.szdw.utils.ModelToStrings;

public class JxjsqshDAO extends JhzyPjpyDAO {
	
	DAO dao = DAO.getInstance();

	/**
	 * 奖学金列表
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getJxjList() throws Exception {
		return dao.getList("select jxjdm,jxjmc from jxjdmb", new String[]{}, new String[]{"jxjdm", "jxjmc"});
	}

	public String[] getJxjXx(String jxjdm) {
		return dao.getOneRs("select jxjmc,jxjlb from jxjdmb where jxjdm = ?", new String[]{jxjdm}, new String[]{"jxjmc","jxjlb"});
	}

	public boolean JxjCommonSave(JxjsqshModel myModel, HttpServletRequest request) throws Exception {
		String tableName = "xsjxjb";
		String pkComment = "xh||xn||xq||jxjdm";
		String pk = DealString.toGBK(myModel.getPk());
		boolean updata = true;
		if("".equalsIgnoreCase(pk)){
			String[] colList = new String[] { "xh","xn","xq","sqly","jtjjqk","jfqk","bz","jxjdm","sjhm"};
			String[] inputList = ModelToStrings.modelToStrings(colList, myModel);
			updata = StandardOperation.insert(tableName, colList, inputList,
					request);
		} else {
			myModel.setFdysh("未审核");
			myModel.setXysh("未审核");
			myModel.setXxsh("未审核");
			String[] colList = new String[] { "sqly","jtjjqk","jfqk","bz","sjhm","fdysh","xysh","xxsh"};
			String[] inputList = ModelToStrings.modelToStrings(colList, myModel);
			updata = StandardOperation.update(tableName, colList, inputList,
					pkComment, pk, request);
		}
		return updata;
	}
	
	public boolean JxjKnbSave(JxjsqshModel myModel, HttpServletRequest request) throws Exception {
		String tableName = "xsjxjb";
		String pkComment = "xh||xn||xq||jxjdm";
		String pk = DealString.toGBK(myModel.getPk());
		boolean updata = true;
		if("".equalsIgnoreCase(pk)){
			String[] colList = new String[] { "xh","xn","xq","sqly","bz","jxjdm","sjhm","xxjl","zysj","dnshjxj","cjqk"};
			String[] inputList = ModelToStrings.modelToStrings(colList, myModel);
			updata = StandardOperation.insert(tableName, colList, inputList,
					request);
		} else {
			myModel.setFdysh("未审核");
			myModel.setXysh("未审核");
			myModel.setXxsh("未审核");
			String[] colList = new String[] { "sqly","bz","sjhm","xxjl","zysj","dnshjxj","cjqk","fdysh","xysh","xxsh"};
			String[] inputList = ModelToStrings.modelToStrings(colList, myModel);
			updata = StandardOperation.update(tableName, colList, inputList,
					pkComment, pk, request);
		}
		return updata;
	}

	public boolean JxjGjjxjSave(JxjsqshModel myModel, HttpServletRequest request) throws Exception {
		String tableName = "xsjxjb";
		String pkComment = "xh||xn||xq||jxjdm";
		String pk = DealString.toGBK(myModel.getPk());
		boolean updata = true;
		if("".equalsIgnoreCase(pk)){
			String[] colList = new String[] { "xh","xn","xq","sqly","jxjdm","lxdh"};
			String[] inputList = ModelToStrings.modelToStrings(colList, myModel);
			colList[5] = "sjhm";
			updata = StandardOperation.insert(tableName, colList, inputList,
					request);
		} else {
			myModel.setFdysh("未审核");
			myModel.setXysh("未审核");
			myModel.setXxsh("未审核");
			String[] colList = new String[] { "sqly","lxdh","xyshyj","szxyj","fdysh","xysh","xxsh"};
			String[] inputList = ModelToStrings.modelToStrings(colList, myModel);
			colList[1] = "sjhm";
			updata = StandardOperation.update(tableName, colList, inputList,
					pkComment, pk, request);
		}
		return updata;
	}
	
	/**
	 * 奖学金审核查询
	 * @param jxjdm 
	 */
	public  ArrayList<String[]> getJxjshList(String userType,String userName, JxjsqshModel model, String jxjdm) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		// 学院代码
		String xydm = DealString.toGBK(model.getXydm());
		// 专业代码
		String zydm = DealString.toGBK(model.getZydm());
		// 班级代码
		String bjdm = DealString.toGBK(model.getBjdm());
		// 年级
		String nj = DealString.toGBK(model.getNj());
		// 学年
		String xn = DealString.toGBK(model.getXn());
		// 学期
		String xq = DealString.toGBK(model.getXq());
		// 学号
		String xh = model.getXh();
		xh = Base.isNull(xh) ? "%" : xh;
		// 姓名
		String xm = model.getXm();
		xm = Base.isNull(xm) ? "%" : "%" + xm + "%";		
		StringBuffer query = new StringBuffer();
		query.append(" where 1=1");
		query.append(" and jxjdm = ? ");
		query.append(Base.isNull(xq) ? " and 1=1" : " and xq='"+xq+ "'");
		query.append(Base.isNull(xydm) ? " and 1=1" : " and xydm='"+xydm+ "'");
		query.append(Base.isNull(zydm) ? " and 1=1" : " and zydm='"+zydm+ "'");
		query.append(Base.isNull(bjdm) ? " and 1=1" : " and bjdm='"+bjdm+ "'");
		query.append(Base.isNull(nj) ? " and 1=1" : " and nj='"+nj+ "'");
		query.append(Base.isNull(xn) ? " and 1=1" : " and xn='"+xn+ "'");	
		String fdyQuerry = "isFdy".equalsIgnoreCase(userType) ? " and bjdm in (select bjdm from fdybjb b where zgh like '"+ userName + "')":"";
		query.append(fdyQuerry);
		query.append(" and xh like ?");
		query.append(" and xm like ?");			
		String[] colList = new String[]{"xh||xn||xq||jxjdm","xn","xh","xm","xymc","bjmc","fdysh","xysh","xxsh"}; 		
		return commonQuery("view_xsjxjb", query.toString(), new String[] {jxjdm,xh,xm}, colList,"", model);		
	}

	public boolean dao_JxjsqshChk(String userType, String pkValue, String jxjdm, String fdyshyj, String xyshyj, String xxshyj, String yesNo) throws Exception {
		DAO dao = DAO.getInstance();
		String sql = "";
		boolean doFlag=false;
		String realTable="";
		String pk="";
		realTable="xsjxjb";
		pk="xh||xn||xq||jxjdm";
		if("xy".equalsIgnoreCase(userType)){
			sql=" update "+realTable+" set xysh=? , xyshyj=? where "+pk+"=? ";
			doFlag = dao.runUpdate(sql, new String[]{yesNo,xyshyj,pkValue});
		}else if("xx".equalsIgnoreCase(userType)||"admin".equalsIgnoreCase(userType)){
			sql=" update "+realTable+" set xxsh=? , xxshyj=? where "+pk+"=?";
			doFlag = dao.runUpdate(sql, new String[]{yesNo,xxshyj,pkValue});
		}else{
			sql=" update "+realTable+" set fdysh=? ,fdyyj=? where "+pk+"=?";
			doFlag = dao.runUpdate(sql, new String[]{yesNo,fdyshyj,pkValue});
		}
		return doFlag;
	}
	
	/**
	 * 所获等级列表
	 */
	public List<HashMap<String,String>>getShDjList(String xh){
		DAO dao = DAO.getInstance();
		String sql="select xn,(select xqmc from xqdzb b where a.xq=b.xqdm )xqmc,djksmc,cj from xsdjksb a where xh=? order by xn,xq";
		return dao.getList(sql,new String[]{xh},new String[]{"xn","xqmc","djksmc","cj"});
	}
	
	/**
	 * 所获奖学金列表
	 */
	public List<HashMap<String,String>>getShJxjList(String xh){
		DAO dao = DAO.getInstance();
		String sql="select xn ,jxjmc from view_xsjxjb where xh=? order by xn";
		return dao.getList(sql,new String[]{xh},new String[]{"xn","jxjmc"});
	}
	
	/**
	 * 获取学生成绩相关数据集
	 */
	public List<HashMap<String,String>> dao_getCjList(String xh){
		return getFzPmList(xh,"","");
	}

	/**
	 * 获取审核下拉框信息
	 */
	public List<HashMap<String, String>>  dao_getChkList(){
		DAO dao = DAO.getInstance();		
		return dao.getChkList(3);
	}
	
	/**
	 * 评奖评优批量审核
	 */
	public boolean dao_JxjsqshCk(String userType,String check,String xmk,String pkVStr) throws SQLException{
		DAO dao = DAO.getInstance();
		String pk = "";
		String shType = "";
		String realTable= "";
		realTable="xsjxjb";
		pk="xh||xn||xq||jxjdm";
		check = "yes".equalsIgnoreCase(check)?"通过":"不通过";
		if("xy".equalsIgnoreCase(userType)){//院系用户审核时，进行审核荣誉称号数限制控制
			shType = "xysh";			
		}else if("xx".equalsIgnoreCase(userType)||"admin".equalsIgnoreCase(userType)){	
			shType = "xxsh";
		}else{
			shType = "fdysh";
		}			
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

	public ArrayList<String[]> getJxjCxList(String userType, String userName, JxjsqshModel model, String jxjdm) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		// TODO 自动生成方法存根
//		 学院代码
		String xydm = DealString.toGBK(model.getXydm());
		// 专业代码
		String zydm = DealString.toGBK(model.getZydm());
		// 班级代码
		String bjdm = DealString.toGBK(model.getBjdm());
		// 年级
		String nj = DealString.toGBK(model.getNj());
		// 学年
		String xn = DealString.toGBK(model.getXn());
		// 学期
		String xq =model.getXq();
		// 学号
		String xh =model.getXh();
		xh = Base.isNull(xh) ? "%" : xh;
		// 姓名
		String xm = model.getXm();
		xm = Base.isNull(xm) ? "%" : "%" + xm + "%";		
		StringBuffer query = new StringBuffer();
		query.append(" where 1=1");
		query.append(Base.isNull(jxjdm) ? " and 1=1" : " and jxjdm='"+jxjdm+ "'");
		query.append(Base.isNull(xq) ? " and 1=1" : " and xq='"+xq+ "'");
		query.append(Base.isNull(xydm) ? " and 1=1" : " and xydm='"+xydm+ "'");
		query.append(Base.isNull(zydm) ? " and 1=1" : " and zydm='"+zydm+ "'");
		query.append(Base.isNull(bjdm) ? " and 1=1" : " and bjdm='"+bjdm+ "'");
		query.append(Base.isNull(nj) ? " and 1=1" : " and nj='"+nj+ "'");
		query.append(Base.isNull(xn) ? " and 1=1" : " and xn='"+xn+ "'");	
		String fdyQuerry = "isFdy".equalsIgnoreCase(userType) ? " and bjdm in (select bjdm from fdybjb b where zgh like '"+ userName + "' )":"";
		query.append(fdyQuerry);
		query.append(" and xh like ?");
		query.append(" and xm like ?");			
		String[] colList = new String[]{"xh||xn||xq||jxjdm","xn","xh","xm","xymc","bjmc","fdysh","xysh","xxsh"}; 		
		return commonQuery("view_xsjxjb", query.toString(), new String[] {xh,xm}, colList,"", model);		
	}

	public boolean dao_jxjDel(String userType, String jxjdm, String pkVStr) throws SQLException {
		DAO dao = DAO.getInstance();
		String pk = "";
//		String shType = "";
		String realTable= "xsjxjb";
		pk = "xh||xn||xq||jxjdm";
		String querry="";
		if("xy".equalsIgnoreCase(userType)){
			querry=" and xxsh='未审核'";
		}else if("xx".equalsIgnoreCase(userType)||"admin".equalsIgnoreCase(userType)){
			
		}else{
			querry="and xysh='未审核' and xxsh='未审核'";
		}
		String[] pkValueA = pkVStr.split("!!");		
		String[] updPkSql  = new String[pkValueA.length];		
		for (int i = 0; i < pkValueA.length; i++) {
			updPkSql[i] = Base.isNull(pkValueA[i])?"":"delete "+realTable+" where "+pk+"= '"+pkValueA[i]+"'  "+querry;							
		}              
		boolean doFlag = false;
		int[] array = dao.runBatch(updPkSql);
		doFlag = dao.checkBatch(array);   
		return doFlag;
	}
}
