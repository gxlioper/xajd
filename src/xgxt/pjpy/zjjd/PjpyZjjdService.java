
package xgxt.pjpy.zjjd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import xgxt.DAO.DAO;
import xgxt.pjpy.commonutils.PjpyCommonDAO;
import xgxt.utils.ExcelMethods;
import xgxt.utils.String.StringUtils;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 浙江机电评奖评优Service
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 李涛</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-08-05</p>
 */
public class PjpyZjjdService {
	
	PjpyZjjdDAO dao = null;//数据操作类
	
	/**
	 * 通过学期获取年月列表
	 * @return
	 */
	public List<HashMap<String, String>> getYfList(String xq) throws Exception {
		dao = new PjpyZjjdDAO();
		return dao.getYfList(xq);
	}
	
	/**
	 * 校园表现分查询结果
	 * @param xybxfModel
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getXybxfResult(XybxfModel xybxfModel) throws Exception {
		dao = new PjpyZjjdDAO();
		return dao.getXybxfResult(xybxfModel);
	}
	
	/**
	 * 校园表现分查询表头
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getXybxfTitle() throws Exception {
		dao = new PjpyZjjdDAO();
		return dao.getXybxfTitle();
	}
	
	/**
	 * 通过学号获取该生相关信息
	 * @param xh
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getStuInfo(String xh) throws Exception {
		PjpyCommonDAO commonDao = new PjpyCommonDAO();
		return commonDao.getStuInfo(xh);
	}
	
	/**
	 * 校园表现分的单个保存,先删除后插入
	 * @param xybxfModel
	 * @return 
	 * @throws Exception
	 */
	public boolean xybxfSave(XybxfModel xybxfModel, HttpServletRequest request) throws Exception {
		dao = new PjpyZjjdDAO();
		return dao.xybxfSave(xybxfModel, request);
	}
	
	/**
	 * 通过主键获取学生校园表现分信息
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getXybxfInfo(String pkValue) throws Exception {
		dao = new PjpyZjjdDAO();
		return dao.getXybxfInfo(pkValue);
	}
	
	/**
	 * 校园表现分的单个修改分为修改主键，未修改主键
	 * @param xybxfModel
	 * @param pkValue
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean xybxfModi(XybxfModel xybxfModel, String pkValue,
			HttpServletRequest request) throws Exception {
		dao = new PjpyZjjdDAO();
		return dao.xybxfModi(xybxfModel, pkValue, request);
	}
	
	/**
	 * 校园表现分的批量删除
	 * @param keys
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String xybxfDel(String[] keys, HttpServletRequest request) throws Exception {
		dao = new PjpyZjjdDAO();
		return dao.xybxfDel(keys, request);
	}
	
	/**
	 * 校园表现分审核结果学院，学校审核
	 * @param keys
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String xybxfSh(String[] keys, String sJg, String userType,
			HttpServletRequest request) throws Exception {
		dao = new PjpyZjjdDAO();
		sJg = StringUtils.isEqual(sJg, "tg") ? "通过" : (StringUtils.isEqual(sJg,
				"btg") ? "不通过" : "未审核");
		if (StringUtils.isEqual(userType, "xy")) {//学院审核
			return dao.xybxfShByxy(keys, sJg, request);
		}
		if (StringUtils.isEqual(userType, "xx") || StringUtils.isEqual(userType, "admin")) {
			return dao.xybxfShByxx(keys, sJg, request);
		}
		return "";
	}
	
	/**
	 * 校园表现分审核查询
	 * @param userType
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> xybxfshQryTitle(String userType) throws Exception {
		dao = new PjpyZjjdDAO();
		List<HashMap<String, String>> topList = new ArrayList<HashMap<String,String>>();
		if (StringUtils.isEqual(userType, "xy")) {
			topList = dao.xyXybxfshQryTitle();
		}
		if (StringUtils.isEqual(userType, "xx") || StringUtils.isEqual(userType, "admin")) {
			topList = dao.xxXybxfshQryTitle();
		}
		return topList;
	}
	
	/**
	 * 校园表现分审核查询结果
	 * @param xybxfModel
	 * @return
	 * @throws Exception
	 */
	public List<String[]> xybxfshQryResult(XybxfModel xybxfModel, String userType) throws Exception {
		dao = new PjpyZjjdDAO();
		List<String[]> resList = new ArrayList<String[]>();
		if (StringUtils.isEqual(userType, "xy")) {
			resList = dao.xyXybxfshQryResult(xybxfModel);
		}
		if (StringUtils.isEqual(userType, "xx") || StringUtils.isEqual(userType, "admin")) {
			resList = dao.xxXybxfshQryResult(xybxfModel);
		}
		return resList;
	}
	
	/**
	 * 获取审核列表
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getChkList(int type) throws Exception {
		dao = new PjpyZjjdDAO();
		return dao.getChkList(type);
	}
	
	/**
	 * 校园表现分审核查询
	 * @param userType
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> xybxfshView(String userType, String pkValue) throws Exception {
		dao = new PjpyZjjdDAO();
		HashMap<String, String> resMap = new HashMap<String, String>();
		if (StringUtils.isEqual(userType, "xy")) {
			resMap = dao.xyXybxfshRes(pkValue);
		}
		if (StringUtils.isEqual(userType, "xx") || StringUtils.isEqual(userType, "admin")) {
			resMap = dao.xxXybxfshRes(pkValue);
		}
		return resMap;
	}
	
	/**
	 * 单个审核校园表现分
	 * @param pkValue
	 * @param sh
	 * @param yj
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean xybxfShOne(String pkValue, String userType, String sh,
			String yj, HttpServletRequest request) throws Exception {
		dao = new PjpyZjjdDAO();
		boolean bFlag = false;
		if (StringUtils.isEqual(userType, "xy")) {
			bFlag = dao.xybxfShOneByXy(pkValue, sh, yj, request);
		}
		if (StringUtils.isEqual(userType, "xx") || StringUtils.isEqual(userType, "admin")) {
			bFlag = dao.xybxfShOneByXx(pkValue, sh, yj, request);
		}
		return bFlag;
	}
	
	/**
	 * 获取学生干部德育附加分查询表头
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getXsgbdyQryTitle() throws Exception {
		dao = new PjpyZjjdDAO();
		return dao.getXsgbdyQryTitle();
	}
	
	/**
	 * 获取学生干部德育附加分查询结果
	 * @param xsgbModel
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getXsgbdyQryResult(XsgbdyfjfModel xsgbModel) throws Exception {
		dao = new PjpyZjjdDAO();
		return dao.getXsgbdyQryResult(xsgbModel);
	}
	
	/**
	 * 获取等级列表
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getDjList(int type) throws Exception {
		dao = new PjpyZjjdDAO();
		return dao.getDjList(1);
	}
	
	/**
	 * 学生干部德育附加分单个保存
	 * @param xsgbModel
	 * @return
	 * @throws Exception
	 */
	public boolean saveXsgbdyfjf(XsgbdyfjfModel xsgbModel, HttpServletRequest request) throws Exception {
		dao = new PjpyZjjdDAO();
		return dao.saveXsgbdyfjf(xsgbModel, request);
	}
	
	/**
	 * 获取学生干部德育分信息
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getXsgbdyfxx(String pkValue) throws Exception {
		dao = new PjpyZjjdDAO();
		return dao.getXsgbdyfxx(pkValue);
	}
	
	/**
	 * 学生干部德育分修改保存
	 * @param pkValue
	 * @param xsgbModel
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean xsgbdyfModi(String pkValue, XsgbdyfjfModel xsgbModel,
			HttpServletRequest request) throws Exception {
		dao = new PjpyZjjdDAO();
		return dao.xsgbdyfModi(pkValue, xsgbModel, request);
	}
	
	/**
	 * 学生干部德育分批量删除
	 * @param keys
	 * @param request
	 * @return 记录删除不成功所返回的行数
	 * @throws Exception
	 */
	public String xsgbdyDel(String[] keys, HttpServletRequest request) throws Exception {
		dao = new PjpyZjjdDAO();
		return dao.xsgbdyDel(keys, request);
	}
	
	/**
	 * 传入表名返回LIST查询表头
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getQryTitle(String tableName) throws Exception {
		dao = new PjpyZjjdDAO();
		String[] enList = null;
		String[] cnList = null;
		//综合素质测评表查询表头
		if (!StringUtils.isNull(tableName) && StringUtils.isEqual(tableName, "zhszcp")) {
			enList = new String[]{"pk", "rownum", "xh", "xn", "xq", "xm", "bjmc", "dyxj", "zyxj", "tyxj", "zhszcpzf", "zhszcpmc"};
			cnList = new String[]{"pk", "行号", "学号", "学年", "学期", "姓名", "班级", "德育总分", "智育总分", "体育总分", "综合素质总分", "综合素质班级排名"};
		}
		if (StringUtils.isEqual(tableName, "xsjxjb")) {
			enList = new String[]{"pk", "rownum", "nd", "xn", "xh", "xq", "xm", "bjmc","jxjmc", "xysh", "xxsh"};
			cnList = new String[]{"pk", "行号", "年度", "学年", "学号", "学期", "姓名", "班级名称", "奖学金名称", "学院审核", "学校审核"};
		}
		if (StringUtils.isEqual(tableName, "xsrychb")) {
			enList = new String[]{"pk", "rownum", "nd", "xn", "xh", "xq", "xm", "bjmc","rychmc", "xysh", "xxsh"};
			cnList = new String[]{"pk", "行号", "年度", "学年", "学号", "学期", "姓名", "班级名称", "荣誉称号名称", "学院审核", "学校审核"};
		}
		if (StringUtils.isEqual(tableName, "stusqxx")) {
			enList = new String[]{"xh||xn||xq||nd||jxjdm", "rownum","xn", "xq","nd","jxjmc", "xysh", "xxsh"};
			cnList = new String[]{"pk","行号", "学年","学期","年度", "奖学金", "学院审核", "学校审核"};
		}
		return dao.getQryTitle(enList, cnList);
	}
	
	/**
	 * 综合素质测评查询结果
	 * @param zhszcpModel
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getZhszcpQryResult(ZhszcpModel zhszcpModel) throws Exception {
		dao = new PjpyZjjdDAO();
		return dao.getZhszcpQryResult(zhszcpModel);
	}
	
	/**
	 * 获取学生学年学期相关表现分
	 * @param xh
	 * @param xn
	 * @param xq
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getZhszcpf(String xh, String xn , String xq) throws Exception {
		dao = new PjpyZjjdDAO();
		return dao.getZhszcpf(xh, xn, xq);
	}
	
	public List<String[]> getcjList(String xh, String xn, String xq) throws Exception {
		dao = new PjpyZjjdDAO();
		return dao.getcjList(xh, xn, xq);
	}
	
	/**
	 * 综合素质测评保存
	 * @param zhszcpModel
	 * @return
	 * @throws Exception
	 */
	public boolean zhszcpSave(ZhszcpModel zhszcpModel, HttpServletRequest request) throws Exception {
		dao = new PjpyZjjdDAO();
		return dao.zhszcpSave(zhszcpModel, request);
	}
	
	/**
	 * 综合素质批量删除
	 * @param keys
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String zhszcpDel(String[] keys, HttpServletRequest request) throws Exception {
		dao = new PjpyZjjdDAO();
		return dao.zhszcpDel(keys, request);
	}
	
	/**
	 * 综合素质测评修改显示详细信息
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getZhszcpInfo(String pkValue) throws Exception {
		dao = new PjpyZjjdDAO();
		return dao.getZhszcpInfo(pkValue);
	}
	
	/**
	 * 综合素质测评单个修改
	 * @param zhszcpModel
	 * @param requet
	 * @return
	 * @throws Exception
	 */
	public boolean zhszcpModi(String pkValue, ZhszcpModel zhszcpModel,
			HttpServletRequest request) throws Exception {
		dao = new PjpyZjjdDAO();
		return dao.zhszcpModi(pkValue, zhszcpModel, request);
	}
	
	/**
	 * 综合素质测评数据导出
	 * @param wwb
	 * @param zhszcpModel
	 * @throws Exception
	 */
	public void zhszcpPrint(WritableWorkbook wwb, ZhszcpModel zhszcpModel) throws Exception {
		dao = new PjpyZjjdDAO();
		//获取要导出的数据
		List<String[]> resList = dao.zhszcpDataExpQry(zhszcpModel);
		String[] xnxqbjList = new String[3];
		if (resList != null && resList.size() > 0) {
			String[] tmpList = resList.get(0);
			for (int i = 0; i < xnxqbjList.length; i++) {
				xnxqbjList[i] = tmpList[i];//获取学年，学期，班级信息作为单独输出
			}
			WritableSheet ws = wwb.getSheet(0);
		
			WritableCellFormat tStyle = new WritableCellFormat();
	 	    WritableCellFormat wcfStyle = new WritableCellFormat();
		    Alignment alignMent = Alignment.CENTRE;
		    wcfStyle.setAlignment(alignMent);
		    tStyle.setAlignment(Alignment.LEFT);
		    wcfStyle.setBorder(Border.ALL, BorderLineStyle.THIN);
		    ws.addCell(new Label(0, 0, String.format("%1$s 学年第 %2$s 学期德、智、体综合测评汇总表",
					xnxqbjList[0], xnxqbjList[1]), wcfStyle));//输出表头
		    ws
					.addCell(new Label(1, 1, String.format("%1$s", xnxqbjList[2]),
							tStyle));//输出班级
		    String[] tempList = null;
		    for (int i = 0; i < resList.size(); i++) {
		    	tempList = resList.get(i);//获取单行数据
		    	int k = 0;
		    	for (int j = 3; j < tempList.length; j++) { //输出每行数据
		    		ws.addCell(new Label(k,i+4,tempList[j],wcfStyle));
		    		k++;
		    	}
		    }
		}
		 ExcelMethods.submitWritableWorkbookOperations(wwb);//输出到客户端
	}
	
	/**
	 * 获取个人奖学金评定信息
	 * @param jxjpdModel
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getJxjpdxx(JxjpdxxModel jxjpdModel) throws Exception {
		dao = new PjpyZjjdDAO();
		return dao.getJxjpdxx(jxjpdModel);
	}
	
	/**
	 * 获取学期代码
	 * @param jxjpdModel
	 * @return
	 * @throws Exception
	 */
	public String getXqmc(String xqdm) throws Exception {
		dao = new PjpyZjjdDAO();
		return dao.getXqmc(xqdm);
	}
	/**
	 * 奖学金申请保存
	 * @param jxjpdModel
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean jxjsqSave(JxjpdxxModel jxjpdModel, HttpServletRequest request) throws Exception {
		dao = new PjpyZjjdDAO();
		return dao.jxjsqSave(jxjpdModel, request);
	}
	
	/**
	 * 获取奖学金申请学年，年度，学期
	 * @return
	 * @throws Exception
	 */
	public String[] getJxjsqxnxqnd() throws Exception {
		dao = new PjpyZjjdDAO();
		return dao.getJxjsqxnxqnd();
	}
	
	/**
	 * 奖学金查询结果
	 * @param jxjpdModel
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getJxjsqQryResult(JxjpdxxModel jxjpdModel) throws Exception {
		dao = new PjpyZjjdDAO();
		return dao.getJxjsqQryResult(jxjpdModel);
	}
	
	/**
	 * 奖学金批量删除
	 * @param keys
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String jxjDel(String[] keys, HttpServletRequest request) throws Exception {
		dao = new PjpyZjjdDAO();
		return dao.jxjDel(keys, request);
	}
	
	/**
	 * 奖学金修改显示详细信息
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getJxjModixx(String pkValue) throws Exception {
		dao = new PjpyZjjdDAO();
		return dao.getJxjModixx(pkValue);
	}
	
	/**
	 * 奖学金审核是否通过
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public String[] jxjshResult(String pkValue) throws Exception {
		dao = new PjpyZjjdDAO();
		return dao.jxjshResult(pkValue);
	}
	
	/**
	 * 奖学金修改保存
	 * @param pkValue
	 * @param jxjpdModel
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean jxjModi(String pkValue, JxjpdxxModel jxjpdModel,
			HttpServletRequest request) throws Exception {
		dao = new PjpyZjjdDAO();
		return dao.jxjModi(pkValue, jxjpdModel, request);
	}
	
	/**
	 * 奖学金数据导出
	 * @param wwb
	 * @param jxjpdModel
	 * @throws Exception
	 */
	public void jxjPrint(WritableWorkbook wwb, JxjpdxxModel jxjpdModel) throws Exception {
		dao = new PjpyZjjdDAO();
		//获取要导出的数据
		List<String[]> resList = dao.jxjExpData(jxjpdModel);
		String bjrs = "";//班级人数
		String tabTitle = "";//表头
		if (resList != null && resList.size() > 0) {
			String[] tmpList = resList.get(0);
			bjrs = dao.getBjrs(tmpList != null ? tmpList[0] : "") + " 人";
			tabTitle = String.format("%1$s 班级 %2$s 学年第 %3$s 学期学生奖学金评定表",
					tmpList != null ? tmpList[0] : "",
					tmpList != null ? tmpList[1] : "",
					tmpList != null ? tmpList[2] : "");
			
			WritableSheet ws = wwb.getSheet(0);//写入到第一个sheet
			
			WritableCellFormat tStyle = new WritableCellFormat();
	 	    WritableCellFormat wcfStyle = new WritableCellFormat();
		    Alignment alignMent = Alignment.CENTRE;
		    wcfStyle.setAlignment(alignMent);//设置输出数据居中
		    tStyle.setAlignment(Alignment.LEFT);
		    wcfStyle.setBorder(Border.ALL, BorderLineStyle.THIN);//设置边框及边框线条
		    tStyle.setBorder(Border.ALL, BorderLineStyle.THIN);
		    ws.addCell(new Label(0, 0, tabTitle, wcfStyle));//输出表头
		    ws.addCell(new Label(1, 1, bjrs, tStyle));//输出班级人数
		    for (int i = 0; i < resList.size(); i++) {
		    	String[] tempList = resList.get(i);//获取单行数据
		    	int k = 0;
		    	for (int j = 3; j < tempList.length; j++) { //输出每行数据
		    		ws.addCell(new Label(k,i+3,tempList[j],wcfStyle));
		    		k++;
		    	}
		    }
		}
		ExcelMethods.submitWritableWorkbookOperations(wwb);//输出到客户端
	}
	
	/**
	 * 荣誉称号评选条件
	 * @param xh
	 * @param xn
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getXxtj(String xh) throws Exception {
		dao = new PjpyZjjdDAO();
		return dao.getXxtj(xh);
	}
	
	/**
	 * 荣誉称号保存
	 */
	public boolean rychSave(RychModel rychModel, HttpServletRequest request) throws Exception {
		dao = new PjpyZjjdDAO();
		return dao.rychSave(rychModel, request);
	}
	
	/**
	 * 荣誉称号查询结果
	 * @param rychModel
	 * @return
	 * @throws Exception
	 */
	public List<String[]> rychQryResult(RychModel rychModel) throws Exception {
		dao = new PjpyZjjdDAO();
		return dao.rychQryResult(rychModel);
	}
	
	/**
	 * 荣誉称号修改显示详细信息
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getRychXx(String pkValue) throws Exception {
		dao = new PjpyZjjdDAO();
		return dao.getRychXx(pkValue);
	}
	
	/**
	 * 荣誉称号修改
	 * @param rychModel
	 * @param pkVlue
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean rychModi(RychModel rychModel, String pkValue ,HttpServletRequest request) throws Exception {
		dao = new PjpyZjjdDAO();
		return dao.rychModi(rychModel, pkValue, request);
	}
	
	/**
	 * 荣誉称号批量删除
	 * @param keys
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String rychDel(String[] keys, HttpServletRequest request) throws Exception {
		dao = new PjpyZjjdDAO();
		return dao.rychDel(keys, request);
	}
	
	/**
	 * 学生奖学金查询信息
	 * @param xh
	 * @return
	 * @throws Exception
	 */
	public List<String[]> stuJxjSqxx(String xh) throws Exception {
		dao = new PjpyZjjdDAO();
		return dao.stuJxjSqxx(xh);
	}
	
//	是否存学生成绩
	public boolean stuCjFlag(String xh, String xn, String xq) throws Exception {
		dao = new PjpyZjjdDAO();
		return dao.stuCjFlag(xh, xn, xq);
	}
	
	// 保存公寓表现分
	public boolean saveGybxf(PjpyZjjdActionForm myForm,
			HttpServletRequest request) throws Exception {
		dao = new PjpyZjjdDAO();
		return dao.saveGybxf(myForm, request);
	}
	
	/**
	 * @describe 获得表头
	 * @author luo
	 */
	public List<HashMap<String, String>> getTopTr(String tableName,
			String[] colList) {
		DAO dao = DAO.getInstance();
		String[] colListCN = dao.getColumnNameCN(colList, tableName);
		List<HashMap<String, String>> topTr = dao.arrayToList(colList,
				colListCN);
		return topTr;
	}

	// 公寓表现分信息List
	public Vector<Object> getGybxfList(PjpyZjjdActionForm myForm) {
		dao = new PjpyZjjdDAO();
		return dao.getGybxfList(myForm);
	}
	
	//	保存公寓表现分
	public HashMap<String, String> getGybxfOne(String pk) throws Exception {
		dao = new PjpyZjjdDAO();
		return dao.getGybxfOne(pk);
	}
	
	// 删除公寓表现分
	public boolean delGybxf(String pk, HttpServletRequest request)
			throws Exception {
		dao = new PjpyZjjdDAO();
		return dao.delGybxf(pk, request);
	}
}
