package xgxt.pjpy.szyqxy.zhszcp;

import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import xgxt.DAO.DAO;
import xgxt.DAO.ExcelMB;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.form.SaveForm;
import xgxt.utils.ExcelMethods;

public class PjpySzyqxyZhszcpService {

	private PjpySzyqxyZhszcpDAO dao = PjpySzyqxyZhszcpDAO.getInstance();
	
	public static PjpySzyqxyZhszcpService service = new PjpySzyqxyZhszcpService();
	
	public static PjpySzyqxyZhszcpService getInstance() {
		return service;
	}	
	/**
	 * 社会实践信息查询表头
	 */
	public List<HashMap<String, String>> serv_zznlChkTitle(String tableName) {
		String[] colListCN = new String[]{"学年","学期","学号","姓名","年级","院系","班级","审核情况"};
		String[] colListEN = new String[]{"xn","xq","xh","xm","nj","xymc","bjmc","sh"};
		if("szyq_dshdjzb".equalsIgnoreCase(tableName)){
			colListCN = new String[]{"学年","学期","学号","姓名","年级","院系","班级","班主任加减分", "读书活动总分","审核情况"};
			colListEN = new String[]{"xn","xq","xh","xm","nj","xymc","bjmc","zf","hdf","sh"};
		}else if ("szyq_yybdjzb".equalsIgnoreCase(tableName)) {
			colListCN = new String[] { "主键", "学年", "学期", "学号", "姓名", "年级", "学院名称",
					"专业名称", "班主任加减分", "语言表达总分","审核情况" };
			colListEN = new String[]{"pk", "xn","xq","xh","xm","nj","xymc","bjmc","zf","hdf","sh"};
		}else if ("szyq_ivtltb".equalsIgnoreCase(tableName)) {
			colListCN = new String[] { "主键", "学年", "学期", "学号", "姓名", "年级", "学院名称",
					"专业名称", "班主任加减分", "IVT论坛总分","审核情况" };
			colListEN = new String[]{"pk", "xn","xq","xh","xm","nj","xymc","bjmc","zf","hdf","sh"};
		}else if ("szyq_xthddjb".equalsIgnoreCase(tableName)) {
			colListCN = new String[] { "主键", "学年", "学期", "学号", "姓名", "年级", "学院名称",
					"专业名称", "班主任加减分", "文体活动总分","审核情况" };
			colListEN = new String[]{"pk", "xn","xq","xh","xm","nj","xymc","bjmc","zf","hdf","sh"};
		}else if ("szyc_zznlfzb".equalsIgnoreCase(tableName)) {
			colListCN = new String[] { "主键", "学年", "学期", "学号", "姓名", "年级", "学院名称",
					"专业名称", "班主任加减分", "组织能力总分","审核情况" };
			colListEN = new String[]{"pk", "xn","xq","xh","xm","nj","xymc","bjmc","zf","hdf","sh"};
		}else if ("szyc_shsjfzb".equalsIgnoreCase(tableName)) {
			colListCN = new String[] { "主键", "学年", "学期", "学号", "姓名", "年级", "学院名称",
					 "班主任加减分", "社会时间总分","审核情况" };
			colListEN = new String[]{"pk", "xn","xq","xh","xm","nj","xymc","zf","hdf","sh"};
		}
		return dao.getTitle(colListEN,colListCN);
	}
	/**
	 * 获取学生相关信息
	 */
	public HashMap<String,String> serv_getXsInfo(String xh){
		String querry = " where xh='"+xh+"' ";
		return dao.dao_getInfo("view_xsjbxx", querry);
	}
	/**
	 * 组织能力活动审核查询
	 */
	public ArrayList<String[]> serv_zznlChkSearch(ZhszcpModel model){
		return dao.dao_zznlChkSearch(model);
	}
	/**
	 *学团活动审核查询
	 */
	public ArrayList<String[]> serv_xthdChkSearch(ZhszcpModel model,String xxk){
		return dao.dao_xthdChkSearch(model,xxk);
	}
	/**
	 * 组织能力活动信息列表
	 */
	public ArrayList<String[]> serv_zznlViewAndChkSearch(String xh,String xn,String xq){
		return dao.dao_zznlViewAndChkSearch(xh, xn, xq);
	}
	/**
	 * 学团活动信息列表
	 */
	public ArrayList<String[]> serv_xthdlViewAndChkSearch(String xh,String xn,String xq,String xxk){
		return dao.dao_zznlViewAndChkSearch(xh, xn, xq,xxk);
	}
	/**
	 * 组织能力活动审核
	 */
	public boolean serv_zznlChk(String pkVStr,String shType) throws Exception{
		return dao.dao_zznlChk(pkVStr, shType);
	}
	/**
	 * 文团活动审核
	 */
	public boolean serv_wthdChk(String pkVStr,String shType,String xxk) throws Exception{
		return dao.dao_wthdChk(pkVStr, shType,xxk);
	}
	public ArrayList<String[]> service_QueryZznl(ZznlModel model,String userType,String userName) {
		return dao.dao_QueryZznl(model,userType,userName);
	}
	//学团活动查询
	public ArrayList<String[]> service_QueryXthd(XthdModel model,String xxk) {
		return dao.dao_QueryXthd(model,xxk);
	}
	
	/**
	 * 奖学金查询表头查询表头
	 */
	public ArrayList<HashMap<String, String>> getjxjTitle(String tableName) {
		String[] colListCN = null;
		if ("szyc_5sb".equalsIgnoreCase(tableName)) {
			colListCN = new String[] { "主键", "学号", "学年", "学期", "姓名", "学院名称",
					"专业名称", "班主任加减分", "5S分" };
		}else if ("szyq_dshdjzb".equalsIgnoreCase(tableName)) {
			colListCN = new String[] { "主键", "学号", "学年", "学期", "姓名", "学院名称",
					"专业名称", "班主任加减分", "读书活动总分" };
		}else if ("szyq_yybdjzb".equalsIgnoreCase(tableName)) {
			colListCN = new String[] { "主键", "学号", "学年", "学期", "姓名", "学院名称",
					"专业名称", "班主任加减分", "语言表达总分" };
		}else if ("szyq_ivtltb".equalsIgnoreCase(tableName)) {
			colListCN = new String[] { "主键", "学号", "学年", "学期", "姓名", "学院名称",
					"专业名称", "班主任加减分", "IVT论坛总分" };
		}else if ("szyq_xthddjb".equalsIgnoreCase(tableName)) {
			colListCN = new String[] { "主键", "学号", "学年", "学期", "姓名", "学院名称",
					"专业名称", "班主任加减分", "文体活动总分" };
		}else if ("szyc_zznlfzb".equalsIgnoreCase(tableName)) {
			colListCN = new String[] { "主键", "学号", "学年", "学期", "姓名", "学院名称",
					"专业名称", "班主任加减分", "组织能力总分" };
		}else if ("szyc_shsjfzb".equalsIgnoreCase(tableName)) {
			colListCN = new String[] { "主键", "学年", "学期", "学号", "姓名", "年级",
					"班级名称", "班主任加减分", "社会实践总分" };
		}
		return dao.dao_getSearchTitle(colListCN);
	}

	public String service_addZznl(ZznlModel model, HttpServletRequest request) {
		return dao.dao_addZznl(model, request);
	}

	public HashMap<String, String> getXsxxInfo(String xh) {
		return dao.dao_getXsxxInfo(xh);
	}

	public ArrayList<HashMap<String, String>> getXszznl(String pk) {
		return dao.dao_getXszznl(pk);
	}
	
	public String getZznlDel(String[] key) {
		return dao.dao_getZznlDel(key);
	}
	//学团活动删除
	public String getXthdDel(String[] key,String xxk) {
		return dao.dao_getXthdDel(key,xxk);
	}
	
	public ArrayList<HashMap<String, String>> getXsXthd(String pk,String xxk) {
		return dao.dao_getXsXthd(pk,xxk);
	}
	/**
	 * 学团活动增加.
	 * 
	 * @param model the model
	 * @param request the request
	 * 
	 * @return the string
	 */
	public String service_addXthd(XthdModel model, HttpServletRequest request,String xxk) {
		String xthd = "";
		try {
			xthd = dao.dao_addXthd(model, request,xxk);
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		return xthd;
	}
	
	//转化GBK
	public static Class formToGBK(Object model)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		// 把form或者model里所有String类型的值转码一次返回页面
		Class myClass = model.getClass();
		Method[] methods = myClass.getMethods();
		for (int i = 0; i < methods.length; i++) {
			String methodOne = methods[i].getName();
			String methodType = methods[i].getReturnType().getName();
			if (methodOne.length() > 3
					&& methodOne.substring(0, 3).equalsIgnoreCase("get")
					&& methodType.equalsIgnoreCase("java.lang.String")) {
				String setMethod = "set" + methodOne.substring(3);
				String newValue = DealString.toGBK((String) myClass.getMethod(
						methodOne, (Class[]) null).invoke(model,
						(Object[]) null));
				myClass.getMethod(setMethod, new Class[] { String.class })
						.invoke(model, newValue);
			}
		}
		return null;
	}
	
		
	/**
	 * 获取班主任下属班级
	 * 
	 * @param userName
	 * @return
	 * @throws SQLException
	 */
	public List<String> getBzrBjList(String userName) throws SQLException {
		return dao.getBzrBjList(userName);
	}

	/**
	 * 获取学期名称
	 * 
	 * @param xqdm
	 * @return
	 * @throws SQLException
	 */
	public String getXqmc(String xqdm) throws SQLException {
		return dao.getXqmc(xqdm);
	}
	/**
	 * 获取学期代码
	 * 
	 * @param xqdm
	 * @return
	 * @throws SQLException
	 */
	public String getXqdm(String xqmc) throws SQLException {
		return dao.getXqdm(xqmc);
	}

	/**
	 * 5S分值查询
	 */
	public ArrayList<String[]> dao_Query5S(FiveSModel model,String userType,String userName) {
		return dao.dao_Query5S(model,userType,userName);
	}
	/**
	 * 5S分值审核查询
	 */
	public ArrayList<String[]> dao_View5S(FiveSModel model){
		return dao.dao_View5S(model);
	}
	/**
	 * 5s分值增加
	 * 
	 * @throws SQLException
	 */
	public boolean dao_add5s(FiveSModel model, HttpServletRequest request)
			throws SQLException {
		return dao.dao_add5s(model, request);
	}
/**
	 * 5s分值审核
	 * 
	 * @throws SQLException
	 */
	public boolean dao_sh5s(String[] key, String shzt) throws SQLException {
		return dao.dao_sh5s(key, shzt);
	}
	/**
	 * 5s分值删除
	 * 
	 * @throws SQLException
	 */
	public boolean dao_del5s(String pks) throws SQLException {
		return dao.dao_del5s(pks);
	}
	
	/**
	 * 5s审核信息列表
	 */
	public ArrayList<String[]> dao_5sList(String pk){
		return dao.dao_5sList(pk);
	}
	
	/**
	 * 获得审核通过5s分
	 * 
	 * @throws SQLException
	 * 
	 */
	public boolean dao_get5sfzList(String[] key, String pk, String kjf)
			throws SQLException {
		return dao.dao_get5sfzList(key, pk, kjf);
	}			
	/**
	 * 获得学生基本信息
	 * 
	 */
	public HashMap<String, String> dao_getStuInfo(String xh, String pk,String tableName) {
		return dao.dao_getStuInfo(xh, pk,tableName);
	}
    /**
	 * 5s审核基本信息
	 */
	public HashMap<String, String> dao_5sXsInfo(String pk) {
		return dao.dao_5sXsInfo(pk);
	}	
	
	/**
	 * 获得该学期月份
	 * 
	 * @throws SQLException
	 */
	public List<HashMap<String, String>> dao_getYf(String xq) throws SQLException {
		return dao.dao_getYf(xq);
	}
	
	/**
	 * 获得周数
	 * 
	 * @throws SQLException
	 */
	public List<HashMap<String, String>> dao_getZc() throws SQLException {
		return dao.dao_getZc();
	}
	
	/**
	 * 5s报表汇总
	 */
	public void print5sList(PjpySzyqxyZhszcpActionForm myForm, String bblx,
			OutputStream os) throws Exception {

		WritableWorkbook wwb = Workbook.createWorkbook(os);
		// 填充内容
		List<HashMap<String, String>> list = null;
		String title = "";
		String[] arrBj = myForm.getCheckVal();
		String bjInfo = "班级：";
		String xq = myForm.getXq();
		String xn = myForm.getXn();
		String yf = myForm.getYf();
		String zc = myForm.getZc();

		int xqs = 0;
		List<HashMap<String, String>> xqList = dao_getYf(xq);
		if (xqList != null && xqList.size() > 0) {
			xqs = xqList.size();
		}
		if ("xqhz".equalsIgnoreCase(bblx)) {
			title = "学期5S汇总表";
			if (arrBj != null && arrBj.length > 0) {
				bjInfo += dao.dao_getBjmc(arrBj);
				list = dao.dao_getXqhzList(xn, xq, arrBj);
			}
		} else if ("ydhz".equalsIgnoreCase(bblx)) {
			title = "月度5S汇总表";
			if (arrBj != null && arrBj.length > 0) {
				bjInfo += dao.dao_getBjmc(arrBj) + "     汇总月份：" + yf+"月";
				list = dao.dao_getYdhzList(xn, xq, yf, arrBj);
			}
		} else if ("hz".equalsIgnoreCase(bblx)) {
			title = "学生日常行为考核表";
			bjInfo = "周次：第" + zc + "周";

			String qsrq = dao.dao_getQsrq();
			int after = 7 * Integer.parseInt(zc);
			int before = 7 * (Integer.parseInt(zc)-1);
			
			Date dateresult = new Date();
			
			DateFormat df = new SimpleDateFormat("yyyyMMdd");    
			Date dt = df.parse(qsrq); 
			
			GregorianCalendar cal = new GregorianCalendar();
			cal.setTime(dt);
			cal.add(Calendar.DAY_OF_YEAR, after);
			dateresult = cal.getTime();
            
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			sdf.format(dateresult);
			String jssj = sdf.format(dateresult);
			
			cal.setTime(dt);
			cal.add(Calendar.DAY_OF_YEAR, before);
			dateresult = cal.getTime();
			
			sdf.format(dateresult);
			String kssj = sdf.format(dateresult);
			
			list=dao.dao_get5shzList(xn, xq, kssj, jssj, arrBj);
		}

		WritableSheet ws = wwb.createSheet(title, 0);

		WritableCellFormat wcf2 = new WritableCellFormat(); // 构造单元格格式
		wcf2 = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL);
		// 填充标题
		ExcelMB ex = new ExcelMB();
		ex.printToOneCell_multy(ws, title, 0, 0, 20, true, Alignment.CENTRE,
				VerticalAlignment.CENTRE, true, 650, Border.NONE);
		// 填充表头);
		ex.printToOneCell_multy(ws, bjInfo, 0, 1, 10, true, Alignment.LEFT,
				VerticalAlignment.CENTRE, true, 650, Border.NONE);

		if ("xqhz".equalsIgnoreCase(bblx)) {
			ws.mergeCells(0, 0, 4 + xqs, 0);
			ws.mergeCells(0, 1, 4 + xqs, 2);
			ws.addCell(new Label(0, 3, "学号", wcf2)); // 添加有指定格式的单元格值
			ws.addCell(new Label(1, 3, "姓名", wcf2)); // 添加有指定格式的单元格值

			if (xqs > 0) {
				for (int i = 0; i < xqs; i++) {
					HashMap<String, String> map = xqList.get(i);
					ws.addCell(new Label(2 + i, 3, map.get("yfmc"), wcf2)); // 添加有指定格式的单元格值
				}
				ws.addCell(new Label(2 + xqs, 3, "个人素质分", wcf2)); // 添加有指定格式的单元格值
				ws.addCell(new Label(3 + xqs, 3, "合计", wcf2)); // 添加有指定格式的单元格值
				ws.addCell(new Label(4 + xqs, 3, "20%", wcf2)); // 添加有指定格式的单元格值
			}
		} else if ("ydhz".equalsIgnoreCase(bblx)) {
			ws.mergeCells(0, 0, 7, 0);
			ws.mergeCells(0, 1, 7, 2);
			ws.addCell(new Label(0, 3, "学号", wcf2)); // 添加有指定格式的单元格值
			ws.addCell(new Label(1, 3, "姓名", wcf2)); // 添加有指定格式的单元格值
			ws.addCell(new Label(2, 3, "日期", wcf2)); // 添加有指定格式的单元格值
			ws.addCell(new Label(3, 3, "加减分原因", wcf2)); // 添加有指定格式的单元格值
			ws.addCell(new Label(4, 3, "类别", wcf2)); // 添加有指定格式的单元格值
			ws.addCell(new Label(5, 3, "扣分", wcf2)); // 添加有指定格式的单元格值
			ws.addCell(new Label(6, 3, "加分", wcf2)); // 添加有指定格式的单元格值		
			ws.addCell(new Label(7, 3, "合计", wcf2)); // 添加有指定格式的单元格值		
		}else if ("hz".equalsIgnoreCase(bblx)) {
			ws.mergeCells(0, 0, 8, 0);
			ws.mergeCells(0, 1, 8, 2);
			ws.addCell(new Label(0, 3, "系部", wcf2)); // 添加有指定格式的单元格值
			ws.addCell(new Label(1, 3, "班级", wcf2)); // 添加有指定格式的单元格值
			ws.addCell(new Label(2, 3, "学号", wcf2)); // 添加有指定格式的单元格值
			ws.addCell(new Label(3, 3, "姓名", wcf2)); // 添加有指定格式的单元格值
			ws.addCell(new Label(4, 3, "日期", wcf2)); // 添加有指定格式的单元格值
			ws.addCell(new Label(5, 3, "加减分原因", wcf2)); // 添加有指定格式的单元格值
			ws.addCell(new Label(6, 3, "类别", wcf2)); // 添加有指定格式的单元格值		
			ws.addCell(new Label(7, 3, "扣分", wcf2)); // 添加有指定格式的单元格值	
			ws.addCell(new Label(8, 3, "加分", wcf2)); // 添加有指定格式的单元格值		
		}
		
		

		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				if ("xqhz".equalsIgnoreCase(bblx)) {
					ws.addCell(new Label(0, 4 + i, list.get(i).get("xh"), wcf2)); // 添加有指定格式的单元格值
					ws.addCell(new Label(1, 4 + i, list.get(i).get("xm"), wcf2)); // 添加有指定格式的单元格值
					ws.addCell(new Label(2, 4 + i, list.get(i).get("one"), wcf2)); // 添加有指定格式的单元格值
					ws.addCell(new Label(3, 4 + i, list.get(i).get("two"), wcf2)); // 添加有指定格式的单元格值
					ws.addCell(new Label(4, 4 + i, list.get(i).get("thr"), wcf2)); // 添加有指定格式的单元格值
					ws.addCell(new Label(5, 4 + i, list.get(i).get("four"), wcf2)); // 添加有指定格式的单元格值
					ws.addCell(new Label(6, 4 + i, list.get(i).get("fiv"), wcf2)); // 添加有指定格式的单元格值
					ws.addCell(new Label(7, 4 + i, list.get(i).get("grf"), wcf2)); // 添加有指定格式的单元格值
					ws.addCell(new Label(8, 4 + i, list.get(i).get("zf"), wcf2)); // 添加有指定格式的单元格值
					ws.addCell(new Label(9, 4 + i, "", wcf2)); // 添加有指定格式的单元格值
				}else if ("ydhz".equalsIgnoreCase(bblx)){
					ws.addCell(new Label(0, 4 + i, list.get(i).get("xh"), wcf2)); // 添加有指定格式的单元格值
					ws.addCell(new Label(1, 4 + i, list.get(i).get("xm"), wcf2)); // 添加有指定格式的单元格值
					ws.addCell(new Label(2, 4 + i, list.get(i).get("lrrq"), wcf2)); // 添加有指定格式的单元格值
					ws.addCell(new Label(3, 4 + i, list.get(i).get("yy"), wcf2)); // 添加有指定格式的单元格值
					ws.addCell(new Label(4, 4 + i, list.get(i).get("fzxm"), wcf2)); // 添加有指定格式的单元格值
					ws.addCell(new Label(5, 4 + i, list.get(i).get("kf"), wcf2)); // 添加有指定格式的单元格值
					ws.addCell(new Label(6, 4 + i, list.get(i).get("jf"), wcf2)); // 添加有指定格式的单元格值
					ws.addCell(new Label(7, 4 + i, list.get(i).get("zf"), wcf2)); // 添加有指定格式的单元格值
				}else if ("hz".equalsIgnoreCase(bblx)) {
					ws.addCell(new Label(0, 4 + i, list.get(i).get("xymc"), wcf2)); // 添加有指定格式的单元格值
					ws.addCell(new Label(1, 4 + i, list.get(i).get("bjmc"), wcf2)); // 添加有指定格式的单元格值
					ws.addCell(new Label(2, 4 + i, list.get(i).get("xh"), wcf2)); // 添加有指定格式的单元格值
					ws.addCell(new Label(3, 4 + i, list.get(i).get("xm"), wcf2)); // 添加有指定格式的单元格值
					ws.addCell(new Label(4, 4 + i, list.get(i).get("lrrq"), wcf2)); // 添加有指定格式的单元格值
					ws.addCell(new Label(5, 4 + i, list.get(i).get("yy"), wcf2)); // 添加有指定格式的单元格值
					ws.addCell(new Label(6, 4 + i, list.get(i).get("fzxm"), wcf2)); // 添加有指定格式的单元格值
					ws.addCell(new Label(7, 4 + i, list.get(i).get("kf"), wcf2)); // 添加有指定格式的单元格值
					ws.addCell(new Label(8, 4 + i, list.get(i).get("jf"), wcf2)); // 添加有指定格式的单元格值
				}
			}
		}
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	public String[] getFzsxForMkdm(String mkdm) {
		return dao.getFzsxForMkdm(mkdm);
	}
	
	/**
	 * 社会实践分值增加
	 * 
	 * @throws SQLException
	 */
	public boolean serv_addShsj(ShsjModel model, HttpServletRequest request)
			throws SQLException {
		return dao.dao_addShsj(model, request);
	}
	/**
	 * 社会实践查询表头
	 */
	public ArrayList<HashMap<String, String>> getshsjTitle() {
		String[] colListCN = new String[] { "主键","学年", "学期", "学号", "姓名","年级",
				"学院名称", "专业名称","班级名称","审核情况" };
		return dao.dao_getSearchTitle(colListCN);
	}
	/**
	 * 社会实践活动查询页面
	 */
	public ArrayList<String[]> serv_shsjSearch(ShsjModel model,String userType,String userName){
		return dao.dao_shsjSearch(model,userType,userName );
	}
	
	public ArrayList<String[]> getPointSetting() {
		return dao.getPointSetting();
	}

	public boolean updatePointSetting(FsszModel model) throws SQLException {
		return dao.updatePointSetting(model);
	}
		/**
	 * 社会实践活动审核查询
	 */
	public ArrayList<String[]> serv_shsjChkSearch(ShsjModel model){	
		return dao.dao_shsjChkSearch(model);
	}
	/**
	 * 社会实践活动审核查询
	 * @throws Exception 
	 */
	public boolean serv_shsjChk(String pkVStr, String shType) throws Exception{	
		return dao.dao_shsjChk(pkVStr, shType);
	}
	/**
	 * 社会实践审核查询表头
	 */
	public List<HashMap<String, String>> serv_shsjChkTitle() {
		String[] colListCN = new String[] { "主键","学年", "学期", "学号", "姓名","年级",
				"学院名称", "专业名称","班级名称","审核情况" };
		String[] colListEN = new String[]{"pk","xn","xq","xh","xm","nj","xymc","zymc","bjmc","sh"};
		return dao.getTitle(colListEN,colListCN);
	}
	/**
	 * 社会实践活动信息列表
	 */
	public ArrayList<String[]> serv_shsjViewAndChkSearch(String xh,String xn,String xq){
		return dao.dao_shsjViewAndChkSearch(xh, xn, xq);
	}
	/**
	 * 社会实践活动信息删除
	 */
	public String serv_shsjDel(String[] key){
		return dao.dao_shsjDel(key);
	}
	/**
	 * 社会实践、组织能力汇总表
	 */
	public void printXxFfList(ShsjModel model,OutputStream os,String printType,String userType,String userName) throws Exception {
         		
		String content = model.getXn()+" "+model.getXqmc()+" "+model.getNj()+" "+model.getXymc()+" "+model.getZymc()+" "+model.getBjmc()+" ";		
		WritableWorkbook wwb = Workbook.createWorkbook(os);
		// 填充内容
        String tableName="szyc_zznlfzb";
        String thead="";
		if("zznl".equalsIgnoreCase(printType)){
			tableName="szyc_zznlfzb";
			content=content+"组织能力分汇总表";
			thead="平时分";
		}else if("shsj".equalsIgnoreCase(printType)){
			tableName="szyc_shsjfzb";
			thead="平时记载（小时）";
			content=content+"社会实践分汇总表";
		}
		List<HashMap<String,String>>   list = dao.dao_getShsjHz(model,tableName,userType,userName);//结果集
        String maxtem = dao.maxJl(model,tableName,userType,userName);//按学生最大记录数
		
        int maxcout = Integer.parseInt(Base.isNull(maxtem)?"0":maxtem);
        
		WritableSheet ws = wwb.createSheet("统计表", 0);
		WritableCellFormat wcf2 = new WritableCellFormat(); // 构造单元格格式
		wcf2 = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL);
		// 填充标题
		ExcelMB ex = new ExcelMB();
		ws.mergeCells(0, 0, 4+maxcout, 0);
		ex.printToOneCell_multy(ws, content, 0, 0, 12, true, Alignment.CENTRE,
				VerticalAlignment.CENTRE, true, 650, Border.NONE);
		// 填充表头);
		ws.mergeCells(0, 1, 0, 2);
		ws.addCell(new Label(0, 1, "学号", wcf2)); // 添加有指定格式的单元格值
		ws.mergeCells(1, 1, 1, 2);
		ws.addCell(new Label(1, 1, "姓名", wcf2)); // 添加有指定格式的单元格值		
//		ws.mergeCells(2, 1, maxcout+1,1 );
		
		ws.addCell(new Label(2, 1, thead, wcf2)); // 添加有指定格式的单元格值	
		
		
		for(int i=0;i<maxcout;i++){
			ws.addCell(new Label(2+i, 2, (i+1)+"", wcf2)); // 添加有指定格式的单元格值	
		}
		ws.mergeCells(2, 1, maxcout+1, 1);
		ws.mergeCells(maxcout+2, 1, maxcout+2, 2);
		ws.addCell(new Label(maxcout+2, 1, "汇总", wcf2)); // 添加有指定格式的单元格值
		ws.mergeCells(maxcout+3, 1, maxcout+3, 2);
		ws.addCell(new Label(maxcout+3, 1, "加分", wcf2)); // 添加有指定格式的单元格值
		ws.mergeCells(maxcout+4, 1, maxcout+4, 2);
		ws.addCell(new Label(maxcout+4, 1, "减分", wcf2)); // 添加有指定格式的单元格值
		List<HashMap<String,String>> rs = new ArrayList<HashMap<String,String>>();		
		if (list != null && list.size() > 0) {	
			String xh="";
			String xhV = "";
//		    int j=0;	
			for (int i = 0; i < list.size(); i++) {		
				if(xh==""){
					xh=list.get(i).get("xh");
					xhV+=xh+"!!";					
				}else if(!xh.equalsIgnoreCase(list.get(i).get("xh"))){
					xh=list.get(i).get("xh");
					xhV+=xh+"!!";					
				}
			}
			String[] xhtem = xhV.split("!!");
			for(int i=0;i<xhtem.length;i++){
				HashMap<String,String> map = new HashMap<String, String>();
				int m=1;
				xh = xhtem[i];
				map.put("xh",xh);
				for (int n= 0; n < list.size(); n++) {		
					if(xh.equalsIgnoreCase(list.get(n).get("xh"))){						
						map.put("fz"+m,list.get(n).get("shfz"));						
						if(m==1){
							map.put("xm",list.get(n).get("xm"));
						}
						m++;
					}
				}
				rs.add(map);
			}
		}	
		for(int i=0;i<rs.size();i++){		
			ws.addCell(new Label(0, 3+i,rs.get(i).get("xh"), wcf2)); // 添加有指定格式的单元格值		
			ws.addCell(new Label(1, 3+i,rs.get(i).get("xm"), wcf2)); // 添加有指定格式的单元格值										
			float hzf=0;//汇总
			float jzf=0;//加总分
			float kzf=0;//减总分
			for(int j=0;j<maxcout;j++){				
				String fval = rs.get(i).get("fz"+(j+1));
				ws.addCell(new Label(2+j, 3+i,fval, wcf2)); // 添加有指定格式的单元格值	
				fval=(Base.isNull(fval)?"0":fval);
				if(fval.indexOf("加分:")!=-1){
					fval=fval.replace("加分:", "");
					jzf = jzf+Float.parseFloat(fval);
				}else if(fval.indexOf("减分:")!=-1){
					fval=fval.replace("减分:", "");
					kzf = kzf+Float.parseFloat(fval);
				}				
			}
			hzf =jzf-kzf;
			ws.addCell(new Label(maxcout+2, 3+i, hzf+"", wcf2)); // 添加有指定格式的单元格值			
			ws.addCell(new Label(maxcout+3, 3+i,jzf+"", wcf2)); // 添加有指定格式的单元格值			
			ws.addCell(new Label(maxcout+4, 3+i,kzf+"", wcf2)); // 添加有指定格式的单元格值					
		}
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	public ArrayList<String[]> getZhszfCjList(ZhszcpModel model,String userType,String userName) {
		return dao.getZhszfCjList(model,userType,userName);
	}
	
	public boolean autoAccount(String xn,String xq,String xydm,String zydm, String bjdm) throws Exception{
		return dao.autoAccount(xn, xq, xydm, zydm, bjdm);
	}

	public List<HashMap<String, String>> getZhszfCjTitle() {
		return dao.getZhszfCjTitle();
	}
	/**
	 *学团活动.汇总
	 * @throws Exception 
	 * 
	 */
	public void serv_exportDataXthd(XthdModel model,String xxk,String userType,String userName, OutputStream os) throws Exception{
		
		String title = "";
		if("ivlt".equals(xxk)){
			title = "IVT论坛记载分汇总表";
		}else if("wthd".equals(xxk)){
			title = "IVT文团活动分汇总表";
		}else if("yybd".equals(xxk)){
			title = "语言表达分汇总表";
		}else{
			title = "IVT读书活动分汇总表";
		}
		String content = model.getXn()+" "+model.getXqmc()+" "+model.getNj()+" "+model.getXymc()+" "+model.getZymc()+" "+model.getBjmc()+title;
		WritableWorkbook wwb = Workbook.createWorkbook(os);
		// 填充内容
		List<HashMap<String,String>>   list = dao.dao_exportDataXthd(model,xxk,userType,userName,os);//结果集
        String maxtem = dao.maxXthd(model,xxk,userType,userName);//按学生最大记录数
        int maxcout = Integer.parseInt(Base.isNull(maxtem)?"0":maxtem);
        
		WritableSheet ws = wwb.createSheet("统计表", 0);
		WritableCellFormat wcf2 = new WritableCellFormat(); // 构造单元格格式
		wcf2 = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL);
		// 填充标题
		ExcelMB ex = new ExcelMB();
		ws.mergeCells(0, 0, 4+maxcout, 0);
		ex.printToOneCell_multy(ws, content, 0, 0, 12, true, Alignment.CENTRE,
				VerticalAlignment.CENTRE, true, 650, Border.NONE);
		// 填充表头);
		ws.mergeCells(0, 1, 0, 2);
		ws.addCell(new Label(0, 1, "学号", wcf2)); // 添加有指定格式的单元格值
		ws.mergeCells(1, 1, 1, 2);
		ws.addCell(new Label(1, 1, "姓名", wcf2)); // 添加有指定格式的单元格值	
		ws.addCell(new Label(2, 1, "平时分", wcf2)); // 添加有指定格式的单元格值
//		ws.mergeCells(2, 1, maxcout+1,1);	
			
		for (int i = 0; i < maxcout; i++) {
			ws.addCell(new Label(2 + i, 2, (i + 1) + "", wcf2)); // 添加有指定格式的单元格值
			// ws.mergeCells(i + 2, 1, i + 2, 2);
		}
		ws.mergeCells(2, 1, maxcout + 1, 1);
		ws.mergeCells(maxcout+2, 1, maxcout+2, 2);
		ws.addCell(new Label(maxcout+2, 1, "汇总", wcf2)); // 添加有指定格式的单元格值
		ws.mergeCells(maxcout+3, 1, maxcout+3, 2);
		ws.addCell(new Label(maxcout+3, 1, "加分", wcf2)); // 添加有指定格式的单元格值
		ws.mergeCells(maxcout+4, 1, maxcout+4, 2);
		ws.addCell(new Label(maxcout+4, 1, "减分", wcf2)); // 添加有指定格式的单元格值
		List<HashMap<String,String>> rs = new ArrayList<HashMap<String,String>>();		
		if (list != null && list.size() > 0) {	
			String xh="";
			String xhV = "";
//		    int j=0;	
			for (int i = 0; i < list.size(); i++) {		
				if(xh==""){
					xh=list.get(i).get("xh");
					xhV+=xh+"!!";					
				}else if(!xh.equalsIgnoreCase(list.get(i).get("xh"))){
					xh=list.get(i).get("xh");
					xhV+=xh+"!!";					
				}
			}
			String[] xhtem = xhV.split("!!");
			for(int i=0;i<xhtem.length;i++){
				HashMap<String,String> map = new HashMap<String, String>();
				int m=1;
				xh = xhtem[i];
				map.put("xh",xh);
				for (int n= 0; n < list.size(); n++) {		
					if(xh.equalsIgnoreCase(list.get(n).get("xh"))){						
						map.put("fz"+m,list.get(n).get("pf"));						
						if(m==1){
							map.put("xm",list.get(n).get("xm"));
						}
						m++;
					}
				}
				rs.add(map);
			}
		}	
		for(int i=0;i<rs.size();i++){		
			ws.addCell(new Label(0, 3+i,rs.get(i).get("xh"), wcf2)); // 添加有指定格式的单元格值		
			ws.addCell(new Label(1, 3+i,rs.get(i).get("xm"), wcf2)); // 添加有指定格式的单元格值										
			float hzf=0;//汇总
			float jzf=0;//加总分
			float kzf=0;//减总分
			for(int j=0;j<maxcout;j++){				
				String fval = rs.get(i).get("fz"+(j+1));
				ws.addCell(new Label(2+j, 3+i,fval, wcf2)); // 添加有指定格式的单元格值	
				fval=(Base.isNull(fval)?"0":fval);
				if(fval.indexOf("加分:")!=-1){
					fval=fval.replace("加分:", "");
					jzf = jzf+Float.parseFloat(fval);
				}else if(fval.indexOf("减分:")!=-1){
					fval=fval.replace("减分:", "");
					kzf = kzf+Float.parseFloat(fval);
				}				
			}
			hzf =jzf-kzf;
			ws.addCell(new Label(maxcout+2, 3+i, hzf+"", wcf2)); // 添加有指定格式的单元格值			
			ws.addCell(new Label(maxcout+3, 3+i,jzf+"", wcf2)); // 添加有指定格式的单元格值			
			ws.addCell(new Label(maxcout+4, 3+i,kzf+"", wcf2)); // 添加有指定格式的单元格值					
		}
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	public void zhszcphzPrint(WritableWorkbook wwb, ZhszcpModel model,String userType,String userName) throws WriteException {
			List<String[]> rs = dao.getZhszfCjList(model, userType, userName);
			if(rs!=null&&rs.size()> 0){
			String bjmc = rs.get(0)[5];
			String xn = model.getXn();
			String xq = model.getXq();
			String title = xn+"学年"+xq+"学期"+bjmc+ "班学生综合素质养成课成绩汇总";
			WritableSheet ws = wwb.getSheet(0);
			WritableCellFormat tStyle = new WritableCellFormat();
	 	    WritableCellFormat wcfStyle = new WritableCellFormat();
		    Alignment alignMent = Alignment.CENTRE;
		    tStyle.setAlignment(alignMent);
		    wcfStyle.setAlignment(alignMent);
		    WritableFont wfTytle = new WritableFont(WritableFont.ARIAL);//设置字体
			wfTytle.setBoldStyle(WritableFont.BOLD);//设置字体加粗
			wfTytle.setPointSize(15);//设置字体大小
			tStyle.setFont(wfTytle);
		    ws.addCell(new Label(0, 0, title, tStyle));//输出表头
		    	for (int i=0;i<rs.size();i++) {
		    		String[] tempList = rs.get(i);//获取单行数据
			    	ws.addCell(new Label(0,i+3,tempList[0],wcfStyle));
			    	ws.addCell(new Label(1,i+3,tempList[3],wcfStyle));
			    	ws.addCell(new Label(2,i+3,tempList[6],wcfStyle));
			    	ws.addCell(new Label(3,i+3,tempList[12],wcfStyle));
			    	ws.addCell(new Label(4,i+3,tempList[9],wcfStyle));
			    	ws.addCell(new Label(5,i+3,tempList[10],wcfStyle));
			    	ws.addCell(new Label(6,i+3,tempList[11],wcfStyle));
			    	ws.addCell(new Label(7,i+3,tempList[8],wcfStyle));
			    	ws.addCell(new Label(8,i+3,tempList[7],wcfStyle));
			    	ws.addCell(new Label(9,i+3,tempList[14],wcfStyle));	
			    	ws.addCell(new Label(10,i+3,tempList[15],wcfStyle));	
		    	}
			}
		ExcelMethods.submitWritableWorkbookOperations(wwb);//输出到客户端
		
	}

	/**
	 * 返回班主任所属班级学生列表
	 * 
	 * @param zgh 职工号 xn 学年 xq 学期
	 * @return list
	 * @author luojw
	 */
	public List<HashMap<String, String>> getBzrXsList(String tableName,
			String zgh, String xn, String xq, String nj, String xh, String xm,
			String xydm, String zydm, String bjdm) {
		return dao.getBzrXsList(tableName, zgh, xn, xq, nj, xh, xm, xydm, zydm,
				bjdm);
	}
	
	/**
	 * 保存班主任所属班级学生5S分
	 * 
	 * @param zgh
	 *            职工号
	 * @return list
	 * @author luojw
	 * @throws Exception
	 */
	public boolean saveBzrXs(SaveForm form, Object model) throws Exception {
		DAO dao = DAO.getInstance();
		return dao.saveData(form, model);
	}
	
	/**
	 * 获得加减分原因列表
	 * @return list
	 * @author luojw
	 */
	public List<HashMap<String, String>> getYyList(){
		return dao.getYyList();
	}
	
	/**
	 * 保存审核状态
	 * @return list
	 * @author luojw
	 * @throws Exception 
	 */
	public boolean saveSh(String tableName,String[] key,String shzt) throws Exception{
		return dao.saveSh(tableName,key, shzt);
	}
	
	/**
	 * 获得班主任班级列表
	 * @return list
	 * @author luojw
	 */
	public List<HashMap<String, String>> getBzrBj(String zgh){
		return dao.getBzrBj(zgh);
	}
	
	/**
	 * 获得班主任下属班级列表
	 * 
	 * @return list
	 * @author luojw
	 */
	public List<HashMap<String, String>> getBjList(String userName) {
		return dao.getBjList(userName);
	}
	
	/**
	 * 获得指定表的指定字段
	 * 
	 * @author luojw
	 */
	public String getOneValue(String tableName, String dm, String pk,
			String pkValue) {
		return dao.getOneValue(tableName, dm, pk, pkValue);
	}
}

