package xgxt.rcgl.nbzy;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.szdw.utils.ModelToStrings;
import xgxt.utils.SearchUtils;
import xgxt.szdw.utils.MakeQuery;

public class MpglService {
	MpglDAO dao = new MpglDAO();
	public ArrayList<String[]> getXyxx(MpglModel myModel) {
		// TODO 自动生成方法存根
		return null;
	}

	public ArrayList<String[]> getBysmpxxwh(MpglModel myModel) {
		// 得到毕业生维护信息
		String tableName = "view_xyqkyl";
		String xydm = DealString.toGBK(myModel.getXydm());
		String xm = DealString.toGBK(myModel.getXm());
		String nj = DealString.toGBK(myModel.getNj());
		String xb = DealString.toGBK(myModel.getXb());
		StringBuffer query = new StringBuffer(SearchUtils.makeQueryCondition(
				xydm, "", "", xb, "", xm, nj, "", "", ""));
		String[] colList = new String[] { "pk","xm","xb","grdh","xymc", "dw","gw","nsrsp"};
		ArrayList<String[]> rs = dao.commonQuery(tableName, query.toString(),
				new String[] {}, colList, "");
		return rs;
	}
	
	public List getBysmpxxwhTopTr() {
		// 得到毕业生维护信息表头
		DAO dao = DAO.getInstance();
		String tableName = "view_xyqkyl";
		String[] colList = new String[] { "pk","xm","xb","grdh","xymc", "dw","gw","nsrsp"};
		String[] colListCN = dao.getColumnNameCN(colList, tableName);
		List topTr = dao.arrayToList(colList, colListCN);// 表头
		return topTr;
	}
	
	public HashMap<String, String> getBysmpOne(String pk) {
		// 得到单个毕业生维护详细信息
		String tableName = "view_xyqkyl";
		String[] colList = new String[] { "bz","djcjy","xb","dw","dwdh","grdh","gw","jtdz","nj","nsrsp","pk","sfdk","xm","xydm","xymc","zw"};
		HashMap<String, String> rs = dao.commonQueryOne(tableName, colList, "pk",
				pk);
		return rs;
	}

	public boolean updataBysmp(MpglModel myModel, String pk,
			HttpServletRequest request) throws Exception {
		// 保存单个毕业生维护
		String tableName = "xyqkylb";
		String pkComment = "xm||grdh";
		String[] colList = new String[] {"bz","djcjy","xb","dw","dwdh","grdh","gw","jtdz","nj","nsrsp","sfdk","xm","xydm","zw"};
		String[] inputList = ModelToStrings.modelToStrings(colList, myModel);
		boolean inserted = StandardOperation.delete(tableName, pkComment, pk,request);
		if (inserted) {
			inserted = StandardOperation.insert(tableName, colList, inputList,
					request);
		}
		return inserted;
	}
	
	public boolean deleteBysmpOne(String pk, HttpServletRequest request)
	throws Exception {
		// 删除毕业生名片信息
		String tableName = "xyqkylb";
		String pkComment = "xm||grdh";
		boolean del = StandardOperation.delete(tableName, pkComment, pk,
				request);
		return del;
	}

	public boolean updataXyftjm(MpglModel myModel, String pk, HttpServletRequest request) throws Exception {
		// 宁职院有约信息录入保存
		String tableName = "xyftjmdjb";
		String pkComment = "fttm||ftsj";
		boolean inserted = true;
		if(pk.equalsIgnoreCase("")){
			String[] colList = new String[] {"bz","ftdd","ftjbnr","ftsj","fttm","jmfzr","sfjbf","xydm","sfjbfdh","sfjbfzw","sfjbs","sfjbsdh","sfjbszw","sfjbt","sfjbtdh","sfjbtzw","xgbyj","zbbmyj","zcr"};
			String[] inputList = ModelToStrings.modelToStrings(colList, myModel);
			inserted = StandardOperation.insert(tableName, colList, inputList,request);
		}else{
			String[] colList = new String[] {"bz","ftdd","ftjbnr","ftsj","fttm","jmfzr","sfjbf","sfjbfdh","sfjbfzw","sfjbs","sfjbsdh","sfjbszw","sfjbt","sfjbtdh","sfjbtzw","xgbyj","xxsh","xysh","zbbmyj","zcr"};
			String[] inputList = ModelToStrings.modelToStrings(colList, myModel);
			inserted = StandardOperation.update(tableName, colList, inputList, pkComment, pk, request);
		}
		return inserted;
	}
	
	public ArrayList<String[]> getXyftjm(MpglModel myModel) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
	// 宁职院有约信息
		String tableName = "view_xyftjmdj";
		StringBuffer query = new StringBuffer(MakeQuery.makeQuery(new String[]{"xydm","xysh","xxsh"},myModel));
		String[] colList = new String[] { "pk","xymc","fttm","ftsj","ftdd", "zcr","xysh","xxsh"};
		ArrayList<String[]> rs = dao.commonQuery(tableName, query.toString(),
				new String[] {}, colList, "");
		return rs;
	}
	
	public List getXyftjmTopTr() {
		// 得到毕业生维护信息表头
		DAO dao = DAO.getInstance();
		String tableName = "view_xyftjmdj";
		String[] colList = new String[] { "pk","xymc","fttm","ftsj","ftdd", "zcr","xysh","xxsh"};
		String[] colListCN = dao.getColumnNameCN(colList, tableName);
		List topTr = dao.arrayToList(colList, colListCN);// 表头
		return topTr;
	}
	
	public HashMap<String, String> getXyftjmsh(String pk) {
		//		 宁职院有约信息单个审核
		String tableName = "view_xyftjmdj";
		String[] colList = new String[] { "bz","ftdd","ftjbnr","ftsj","fttm","jmfzr","pk","sfjbf","sfjbfdh","sfjbfzw","sfjbs","sfjbsdh","sfjbszw","sfjbt","sfjbtdh","sfjbtzw","xgbyj","xxsh","xydm","xymc","xysh","zbbmyj","zcr"};
		HashMap<String, String> rs = dao.commonQueryOne(tableName, colList, "pk",pk);
		return rs;
	}
	
	public boolean deleteXyftjmshOne(String pk, HttpServletRequest request)
	throws Exception {
		// 删除 单个宁职院有约信息
		String tableName = "xyftjmdjb";
		String pkComment = "fttm||ftsj";
		boolean del = StandardOperation.delete(tableName, pkComment, pk,
				request);
		return del;
	}
	/*
	 * 三江学院－户口管理
	 * 
	 */
	
	//户口信息查询
	public List<HashMap<String, String>> queryHkxx_ser(MpglForm form) {
		MpglDAO dao = new MpglDAO();
		try {
			return dao.queryHkxx(form);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	//户口信息查询总记录数
	public String queryHkxxCount_ser(MpglForm form) {
		MpglDAO dao = new MpglDAO();
		try {
			return dao.queryHkxxCount(form);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	//获得表头
	public List<HashMap<String,String>> getTableTop(String lb){
		String[] ens = null;
		String[] cns = null;
		DAO dao = DAO.getInstance();
		if("xshkgl".equals(lb)){
			ens = new String[]{"pk","xh","xm","xb","nj","xymc","zymc","bjmc","hkzt"};
			cns = new String[]{"pk","学号","姓名","性别","年级",Base.YXPZXY_KEY+"名称","专业名称","班级名称","户口状态"};
		}
		if("sfzbb".equals(lb)){
			ens = new String[]{"pk","xn","xq","xh","xm","xb","nj","xymc","zymc",
					           "bjmc","bbzt","fdysh"};
			cns = new String[]{"pk","学年","学期","学号","姓名","性别","年级",
					Base.YXPZXY_KEY+"名称","专业名称","班级名称","补办状态","辅导员审核"};
		}
		return dao.arrayToList(ens, cns);
	}
	
	//获得表头
	public List<HashMap<String,String>> getTableQueryTop(String lb){
		String[] ens = null;
		String[] cns = null;
		DAO dao = DAO.getInstance();
		if("xshkgl".equals(lb)){
			ens = new String[]{"pk","xh","xm","xb","nj","xymc","zymc","bjmc","hkzt"};
			cns = new String[]{"pk","学号","姓名","性别","年级",Base.YXPZXY_KEY+"名称","专业名称","班级名称","户口状态"};
		}
		if("sfzbb".equals(lb)){
			ens = new String[]{"pk","xn","xq","xh","xm","xb","nj","xymc","zymc",
					           "bjmc","fdysh","bbzt","sflq","lqr","lqsj"};			
			cns = new String[]{"pk","学年","学期","学号","姓名","性别","年级",
					Base.YXPZXY_KEY+"名称","专业名称","班级名称","辅导员审核",
			           "补办状态","是否领取", "领取人","领取时间"};
		}
		return dao.arrayToList(ens, cns);
	}
	//获得学生的的基本信息
	public HashMap<String,String> getXsJbxx_ser(String xh) {
		MpglDAO dao = new MpglDAO();
		try {
			return dao.getXsJbxx_db(xh);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	//获得学生的的户口信息
	public HashMap<String,String> getXsHkxx_ser(String xh) {
		MpglDAO dao = new MpglDAO();
		try {
			return dao.getXsHkxx_db(xh);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	//增加学生的的户口信息
	public boolean saveXsHkxx_ser(MpglForm form) {
		MpglDAO dao = new MpglDAO();
		return dao.saveXsHkxx_db(form);
	}
	
	//修改学生的的户口信息
	public boolean updateXsHkxx_ser(MpglForm form) {
		MpglDAO dao = new MpglDAO();
		return dao.updateXsHkxx_db(form);
	}
	
	//删除学生的的户口信息
	public boolean deleteXsHkxx_ser(String pkvals) {
		MpglDAO dao = new MpglDAO();
		return dao.deleteXsHkxx_db(pkvals);
	}
	
	//获得学生的的基本信息
	public HashMap<String,String> getXsSfzJbxx_ser(String xh) {
		MpglDAO dao = new MpglDAO();
		try {
			return dao.getXsSfzJbxx_db(xh);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	//学生是否为常住户口
	public boolean isCzhk_ser(String xh) {
		MpglDAO dao = new MpglDAO();
		return dao.isCzhk_db(xh);
	}
	
	//学生补办申请增加(学生)
	public boolean saveXsBbsq_ser(MpglForm form) {
		MpglDAO dao = new MpglDAO();
		return dao.saveXsBbsq_db(form);
	}
	
	//获得学生的的基本信息(教师)
	public HashMap<String,String> getXsxx_ser(String xh) {
		MpglDAO dao = new MpglDAO();
		return dao.getXsxx_db(xh);
	}
	
	//学生补办申请增加(教师)
	public boolean saveXsBbsqByTeacher_ser(MpglForm form) {
		MpglDAO dao = new MpglDAO();
		return dao.saveXsBbsqByTeacher_db(form);
	}
	
	//获得学生的的身份证补办信息
	public HashMap<String,String> getXsSfzbbxx_ser(String pk) {
		MpglDAO dao = new MpglDAO();
		return dao.getXsSfzbbxx_db(pk);
	}
	
	//修改学生的的身份证申请信息
	public boolean updateXsSfzsqxx_ser(MpglForm form) {
		MpglDAO dao = new MpglDAO();
		return dao.updateXsSfzsqxx_db(form);
	}
	
	//删除学生身份证申请信息
	public boolean deleteXsSfzsqxx_ser(String pkvals) {
		MpglDAO dao = new MpglDAO();
		return dao.deleteXsSfzsqxx_db(pkvals);
	}
	
	//身份证补办审核查询
	public List<HashMap<String, String>> querySfzbbSh_ser(MpglForm form,String userName,String userType) {
		MpglDAO dao = new MpglDAO();
		try {
			return dao.querySfzbbSh_db(form,userName,userType);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 身份证补办查询
	 * @param form
	 * @param userName
	 * @param userType
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> querySfzbb_ser(MpglForm form,String userName,String userType) {
		MpglDAO dao = new MpglDAO();
		try {
			return dao.querySfzbb_db(form,userName,userType);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	//身份证补办审核
	public boolean sfzbbSh_ser(String pkvals,String fdysh) {
		MpglDAO dao = new MpglDAO();
		return dao.sfzbbSh_db(pkvals, fdysh);
	}
	
	//身份证补办审核结果查询（学生）
	public List<HashMap<String, String>> sfzbbShxscx_ser(String xh) {
		MpglDAO dao = new MpglDAO();
		return dao.sfzbbShxscx_db(xh);
	}
	
	/**
	 * 保存户口须知
	 * */
	public boolean saveHkxz(String title, String content, HttpServletRequest request,String infoType) {
		boolean flag = false;
		String tableName = "xzb";
		try {
			flag = StandardOperation.delete(tableName, "title", title, request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(flag){
			flag = StandardOperation.insert(tableName, new String[]{"dm","title", "nr"}, new String[]{infoType,title,content},request);
		}		
		return flag;
	}
	
	/**
	 * 获得户口须知
	 * */
	public String getHkxz(String lb) {
		MpglDAO dao = new MpglDAO();
		return dao.getHkxz(lb);
	}
}
