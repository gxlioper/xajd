package xgxt.xszz.hndx;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import xgxt.DAO.DAO;
import xgxt.DAO.ExcelMB;
import xgxt.action.Base;
import xgxt.form.SaveForm;
import xgxt.szdw.xmlg.wmbj.WmbjService;
import xgxt.utils.ExcelMethods;
import xgxt.xszz.XszzService;
import xgxt.xszz.XszzTyForm;
import xgxt.xszz.comm.XszzCommTjbbService;

import common.XszzXmdm;

public class XszzHndxService extends XszzService implements XszzCommTjbbService{

	XszzHndxDAO dao = new XszzHndxDAO();

	/**
	 * 获得个性化表头
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getTopTr(String lx) {
		DAO dao = DAO.getInstance();
		String[] colListCN = null;
		String[] colListEN = null;

		// 参数设置
		if ("cssz".equalsIgnoreCase(lx)) {
			colListCN = new String[] { "学年", Base.YXPZXY_KEY+"名称", "总人数" };
			colListEN = new String[] { "xn", "xymc", "num" };
			
			//经济困难生级别列表
			List<HashMap<String, String>> djList = dao.getWhList(
					"hndx_xszz_jjknsjb", "dm", "mc", "", "", "");
			if (djList != null && djList.size() > 0) {
				
				colListCN = new String[2 + djList.size()];
				
				colListCN[0] = "学年";
				colListCN[1] = Base.YXPZXY_KEY+"名称";
				colListCN[2] = "总人数";
				
				colListEN = new String[2 + djList.size()];
				
				colListEN[0] = "xn";
				colListEN[1] = "xymc";
				colListEN[2] = "num";
				
				for (int i = 1; i < djList.size(); i++) {
					
					HashMap<String, String> map = djList.get(i);
					String jb = "jb" + map.get("dm");
					String mc = map.get("mc");
					
					colListEN[2 + i] = jb;
					colListCN[2 + i] = mc;
				}

			}
		}
		return dao.arrayToList(colListEN, colListCN);
	}
	
	/**
	 * 保存经济困难生申请
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public Boolean saveJjknssq(XszzTyForm model, String table,
			HttpServletRequest request) throws Exception {

		String filePath = uploadFile(model, request);

		if (!Base.isNull(filePath)) {
			model.setScdz(filePath);
		}
		
		String[] onezd = new String[] { "brjdqzmbqk", "dydj", "jtdd", "jtdh",
				"jtdxrs", "jtfzqk", "jtnzsr", "jtrjsr", "jtrs", "jtsrly",
				"jtyzsr", "qtqk", "scdz", "sfcj", "sfdb", "sfge", "sfjthb",
				"sflszn", "sfpkzm", "sfzrch", "xh", "xn", "xsjtjjqk" };
		
		String pk = "xh||xn";
		String pkValue = model.getXh() + model.getXn();

		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(table);
		saveForm.setOnezd(onezd);
		saveForm.setPk(pk);
		saveForm.setPkValue(new String[] { pkValue });

		boolean result = saveXszz(saveForm, model, request);

		return result;
	}

	/**
	 * 审核经济困难生
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public Boolean shJjknssq(XszzTyForm model, String table, String userType,
			Boolean isBjsh, String shzt) throws Exception {

		String[] onezd = null;
		String nowTime = getNowTime("YYYYMMDD");
		shzt = "tg".equalsIgnoreCase(shzt) ? "通过" : "不通过";

		if (isBjsh) {// 班主任或者班长
			onezd = new String[] { "knsjb", "bjsh", "bjshyj", "bjshsj" };
			model.setBjsh(shzt);
			model.setBjshsj(nowTime);
		} else if ("xy".equalsIgnoreCase(userType)) {// 学院
			onezd = new String[] { "knsjb", "xysh", "xyshyj", "xyshsj" };
			model.setXysh(shzt);
			model.setXyshsj(nowTime);
		} else {// 学校
			onezd = new String[] { "knsjb", "xxsh", "xxshyj", "xxshsj" };
			model.setXxsh(shzt);
			model.setXxshsj(nowTime);
		}

		String pk = "xh||xn";
		String pkValue = model.getXh() + model.getXn();

		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(table);
		saveForm.setOnezd(onezd);
		saveForm.setPk(pk);
		saveForm.setPkValue(new String[] { pkValue });

		boolean result = updateXszz(saveForm, model);

		return result;
	}
	
	/**
	 * 判断登陆用户是否有班级审核的权限
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public Boolean isBjsh(String userName, HttpServletRequest request)
			throws Exception {

		HttpSession session = request.getSession();
		WmbjService wmbjService = new WmbjService();

		// 是否班主任
		boolean isBzr  =  Boolean.parseBoolean(session.getAttribute("bzrQx").toString());
		// 是否班长
		boolean isBz = wmbjService.isBgb(userName);

		boolean flag = (isBzr || isBz) ? true : false;

		return flag;
	}

	/**
	 * 获得资助学院人数列表
	 * 
	 * @author luojw
	 */
	public ArrayList<String[]> getXzXyRsList(XszzTyForm model) {
		
		//学院人数List
		ArrayList<String[]> xyList = dao.getXyRsList(model);
		
		return  xyList;
	}

	/**
	 * 保存经济困难生设置
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public Boolean saveJjknsSz(XszzTyForm model, String table) throws Exception {
		
		
		ArrayList<String[]> list = getXzXyRsList(model);
		// 学院
		String[] arr_xy = new String[list.size()];
		// 学院人数
		String[] arr_rs = new String[list.size()];
		
		for (int i = 0; i < list.size(); i++) {
			String[] xyInfo = list.get(i);
			arr_xy[i] = xyInfo[0];
			arr_rs[i] = xyInfo[3];
		}
		
		// 级别
		List<HashMap<String, String>> jbList = dao.getWhList(
				"hndx_xszz_jjknsjb", "dm", "mc", "", "", "");
		jbList.remove(0);
		String[] arr_xmjb = new String[jbList.size()];
		
		for (int i = 0; i < jbList.size(); i++) {
			String jb = jbList.get(i).get("dm");
			arr_xmjb[i] = jb;
		}
		
		// 项目级别
		String[] arr_xmbl = model.getXmbl();

		String[] szxy = null;
		String[] xmjb = null;
		String[] xmbl = null;
		String[] xmrs = null;
		String[] pkValue = null;
		String xn = model.getXn();
		//TODO 目前写死
		String xmmc = "经济困难生认定";
		
		// 构建参数
		if (arr_xy != null && arr_xy.length > 0 && arr_xmjb != null && arr_xmjb.length > 0) {
			
			szxy = new String[arr_xy.length * arr_xmjb.length];
			xmjb = new String[arr_xy.length * arr_xmjb.length];
			xmbl = new String[arr_xy.length * arr_xmjb.length];
			xmrs = new String[arr_xy.length * arr_xmjb.length];
			pkValue = new String[arr_xy.length * arr_xmjb.length];
			
			int n = 0;
			
			for (int i = 0; i < arr_xy.length; i++) {
				for (int j = 0; j < arr_xmjb.length; j++) {
					szxy[n] = arr_xy[i];
					xmjb[n] = arr_xmjb[j];
					xmbl[n] = arr_xmbl[j];
					//计算人数
					int rs = Math.round((Float.parseFloat(xmbl[j])
							* Float.parseFloat(arr_rs[i]) / 100));
					xmrs[n] = String.valueOf(rs);
					pkValue[n] = xn + arr_xy[i] + xmmc + arr_xmjb[j];
					n++;
				}
			}
		}
		
		model.setSzxy(szxy);
		model.setXmbl(xmbl);
		model.setXmjb(xmjb);
		//model.setXmmc(xmmc);
		model.setXmrs(xmrs);
		
		String[] arrzd = new String[] { "szxy", "xmjb", "xmbl","xmrs" };
		
		String[] onezd = new String[] { "xn", "xmmc" };
		
		String pk = "xn||szxy||xmmc||xmjb";

		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(table);
		saveForm.setArrzd(arrzd);
		saveForm.setOnezd(onezd);
		saveForm.setPk(pk);
		saveForm.setPkValue(pkValue);

		boolean result = saveXszz(saveForm, model);

		return result;
	}
	
	/**
	 * 保存学生资助开关
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public Boolean saveKg(XszzTyForm model, String table,
			HttpServletRequest request) throws Exception {

		String[] onezd = new String[] { "xn", "xmmc", "kg" };
		String pk = "xn||xmmc";
		String pkValue = model.getXn() + model.getXmmc();

		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(table);
		saveForm.setOnezd(onezd);
		saveForm.setPk(pk);
		saveForm.setPkValue(new String[] { pkValue });

		boolean result = saveXszz(saveForm, model, request);

		return result;
	}
	
	/**
	 * 获得学院人数比例(批量)
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getXyRsBlList(XszzTyForm model) {
		return dao.getXyRsBlList(model);
	}
	
	/**
	 * 获得学院人数(单个操作)
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getXySzRsList(XszzTyForm model) {
		return dao.getXySzRsList(model);
	}
	
	/**
	 * 修改项目人数
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public Boolean editXszzXmRs(XszzTyForm model, String table)
			throws Exception {

		String[] pkValue = null;

		String[] xmrs = model.getXmrs();
		String[] xmjb = model.getXmjb();
		String[] szxy = null; 
		
		if (xmrs != null && xmrs.length > 0) {
			pkValue = new String[xmrs.length];
			szxy = new String[xmrs.length];
			for (int i = 0; i < xmrs.length; i++) {
				pkValue[i] = model.getXn() + model.getXydm() + model.getXmmc()
						+ xmjb[i];
				szxy[i] = model.getXydm();
			}
		}

		model.setSzxy(szxy);
		
		String[] arrzd = new String[] { "szxy", "xmjb", "xmbl", "xmrs" };

		String[] onezd = new String[] { "xn", "xmmc" };
		
		String pk = "xn||szxy||xmmc||xmjb";
		
		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(table);
		saveForm.setArrzd(arrzd);
		saveForm.setOnezd(onezd);
		saveForm.setPk(pk);
		saveForm.setPkValue(pkValue);

		boolean result = saveXszz(saveForm, model);

		return result;
	}

	/**
	 * 判断批量保存时人数是否超过
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public String isCgrs(XszzTyForm model)
			throws Exception {

		List<HashMap<String, String>> xyrsList = getPlshXyZrsList(model);
		
		String message = "";
		
		if (xyrsList != null && xyrsList.size() > 0) {
			for (int i = 0; i < xyrsList.size(); i++) {
				HashMap<String, String> map = xyrsList.get(i);
				//学院名称
				String xymc = map.get("xymc");
				//申请人数
				String sqrs = map.get("sqrs");
				//其他通过人
				String qttgr = map.get("qttgr");
				//项目人数
				String xmrs = map.get("xmrs");
				
				// 超过人数
				if (Integer.parseInt(sqrs) + Integer.parseInt(qttgr) > Integer
						.parseInt(xmrs)) {
					message = xymc +"该级别困难生超过所设置组大人数，请确定！";
					break;
				}
				
			}
		}
		return message;
	}
	
	/**
	 * 获得批量审核学生所在学院当前学年的项目设置人数
	 * @author luojw
	 */
	public List<HashMap<String, String>> getPlshXyZrsList(XszzTyForm model) {
		return dao.getPlshXyZrsList(model);
	}
	
	/**
	 * 困难生统计报表
	 * @param form
	 * @param os
	 * */
	public void printKnstjbb(XszzTyForm model, OutputStream os){
		String xmdm = XszzXmdm.XSZZ_KNS;//困难生
		String xymc = dao.getXymc(model.getXydm());//学院名称
		List<String[]> data = dao.getKnsData(xmdm, model);//查询导出的数据

		StringBuilder title = new StringBuilder();
		title.append("海南大学");
		title.append(xymc);
		title.append("贫困生库统计表");

		ExcelMB excel = new ExcelMB();
		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(os);
		WritableSheet ws = wwb.createSheet("贫困生库统计表", 0);

		try {
			excel.printTitle(ws, title.toString(), 19, 800);// 标题			
			WritableCellFormat wcfTytle = ExcelMB.getWritableCellFormat(10,
					true, Alignment.CENTRE, VerticalAlignment.CENTRE,
					Border.ALL);// 单元格样式
			//数据输出
			ws.addCell(new Label(0, 2, "序号", wcfTytle));
			ws.addCell(new Label(1, 2, "姓名", wcfTytle));
			ws.addCell(new Label(2, 2, "学号", wcfTytle));
			ws.addCell(new Label(3, 2, Base.YXPZXY_KEY, wcfTytle));
			ws.addCell(new Label(4, 2, "专业班级", wcfTytle));
			ws.addCell(new Label(5, 2, "籍贯", wcfTytle));
			ws.addCell(new Label(6, 2, "出生日期", wcfTytle));
			ws.addCell(new Label(7, 2, "身份证号", wcfTytle));
			ws.addCell(new Label(8, 2, "性别", wcfTytle));
			ws.addCell(new Label(9, 2, "民族", wcfTytle));
			ws.addCell(new Label(10, 2, "贫困程度", wcfTytle));
			ws.addCell(new Label(11, 2, "奖、贷、勤等情况", wcfTytle));
			ws.addCell(new Label(12, 2, "宿舍", wcfTytle));
			ws.addCell(new Label(13, 2, "联系电话", wcfTytle));
			ws.addCell(new Label(14, 2, "家庭详细地址", wcfTytle));
			ws.addCell(new Label(15, 2, "家庭邮编", wcfTytle));
			ws.addCell(new Label(16, 2, "家庭联系电话", wcfTytle));
			ws.addCell(new Label(17, 2, "校区", wcfTytle));
			ws.addCell(new Label(18, 2, "备注", wcfTytle));

			for (int i = 0; i < data.size(); i++) {
				for (int j = 0; j < data.get(i).length; j++) {
					ws.addCell(new Label(j, 3 + i, data.get(i)[j], wcfTytle));
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 向客户端输出
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	/**
	 * 国家奖学金学生名单备案表
	 * @param form
	 * @param os
	 * */
	public void printGjjxjtjbb(XszzTyForm model, OutputStream os){
		String xmdm = XszzXmdm.XSZZ_GJJXJ;//国家奖学金
		List<String[]> data = dao.getGjjxjData(xmdm, model);//查询要导出的数据
		
		StringBuilder title = new StringBuilder();
		title.append("海南省");
		title.append(model.getXn());
		title.append("年度获国家奖学金学生名单备案表");

		ExcelMB excel = new ExcelMB();
		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(os);
		WritableSheet ws = wwb.createSheet("国家奖学金名单", 0);

		try {
			excel.printTitle(ws, title.toString(), 19, 800);// 标题
			WritableCellFormat btomTytle = ExcelMB.getWritableCellFormat(12,
					false, Alignment.LEFT, VerticalAlignment.CENTRE,
					Border.NONE);// 单元格样式			
			WritableCellFormat wcfTytle = ExcelMB.getWritableCellFormat(12,
					false, Alignment.CENTRE, VerticalAlignment.CENTRE,
					Border.ALL);// 单元格样式
			
			ws.mergeCells(0, 1, 9, 1);
			ws.addCell(new Label(0, 1, "编制单位：               （公章）                                           报送时间：       年       月       日", btomTytle));
			
			//数据输出
			ws.addCell(new Label(0, 2, "序号", wcfTytle));
			ws.addCell(new Label(1, 2, "学校名称", wcfTytle));
			ws.addCell(new Label(2, 2, Base.YXPZXY_KEY+"名称", wcfTytle));
			ws.addCell(new Label(3, 2, "学号", wcfTytle));
			ws.addCell(new Label(4, 2, "姓名", wcfTytle));
			ws.addCell(new Label(5, 2, "性别", wcfTytle));
			ws.addCell(new Label(6, 2, "出生日期", wcfTytle));
			ws.addCell(new Label(7, 2, "户籍所在地", wcfTytle));
			ws.addCell(new Label(8, 2, "民族", wcfTytle));
			ws.addCell(new Label(9, 2, "专业", wcfTytle));
			ws.addCell(new Label(10, 2, "年级", wcfTytle));
			ws.addCell(new Label(11, 2, "入学时间", wcfTytle));
			ws.addCell(new Label(12, 2, "校区", wcfTytle));
			ws.addCell(new Label(13, 2, "银行账号", wcfTytle));
			ws.addCell(new Label(14, 2, "备注", wcfTytle));

			for (int i = 0; i < data.size(); i++) {
				for (int j = 0; j < data.get(i).length; j++) {
					ws.addCell(new Label(j, 3 + i, data.get(i)[j], wcfTytle));
				}
			}			
			//页底			
			ws.mergeCells(0, data.size()+3, 7, data.size()+3);
			ws.addCell(new Label(0, data.size()+3, "负责人：                        联系人：                         联系电话：", btomTytle));
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 向客户端输出
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	
	/**
	 * 国家励志奖学金学生名单备案表
	 * @param form
	 * @param os
	 * */
	public void printGjlsjxjtjbb(XszzTyForm model, OutputStream os){
		String xmdm = XszzXmdm.XSZZ_GJLZJXJ;//国家励志奖学金
		List<String[]> data = dao.getGjlzjxjData(xmdm, model);//查询要导出的数据
		
		StringBuilder title = new StringBuilder();
		title.append("海南省");
		title.append(model.getXn());
		title.append("年度获国家励志奖学金学生名单备案表");

		ExcelMB excel = new ExcelMB();
		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(os);
		WritableSheet ws = wwb.createSheet("国家励志奖学金名单", 0);

		try {
			excel.printTitle(ws, title.toString(), 19, 800);// 标题
			WritableCellFormat btomTytle = ExcelMB.getWritableCellFormat(12,
					false, Alignment.LEFT, VerticalAlignment.CENTRE,
					Border.NONE);// 单元格样式			
			WritableCellFormat wcfTytle = ExcelMB.getWritableCellFormat(12,
					false, Alignment.CENTRE, VerticalAlignment.CENTRE,
					Border.ALL);// 单元格样式
			
			//数据输出
			ws.mergeCells(0, 1, 9, 1);
			ws.addCell(new Label(0, 1, "编制单位：               （公章）                                           报送时间：       年       月       日", btomTytle));
			
			ws.addCell(new Label(0, 2, "序号", wcfTytle));
			ws.addCell(new Label(1, 2, "学校名称", wcfTytle));
			ws.addCell(new Label(2, 2, Base.YXPZXY_KEY+"名称", wcfTytle));
			ws.addCell(new Label(3, 2, "学号", wcfTytle));
			ws.addCell(new Label(4, 2, "姓名", wcfTytle));
			ws.addCell(new Label(5, 2, "性别", wcfTytle));
			ws.addCell(new Label(6, 2, "出生日期", wcfTytle));
			ws.addCell(new Label(7, 2, "户籍所在地", wcfTytle));
			ws.addCell(new Label(8, 2, "民族", wcfTytle));
			ws.addCell(new Label(9, 2, "专业", wcfTytle));
			ws.addCell(new Label(10, 2, "年级", wcfTytle));
			ws.addCell(new Label(11, 2, "入学时间", wcfTytle));
			ws.addCell(new Label(12, 2, "银行账号", wcfTytle));
			ws.addCell(new Label(13, 2, "家庭困难情形", wcfTytle));
			ws.addCell(new Label(14, 2, "校区", wcfTytle));
			ws.addCell(new Label(15, 2, "备注", wcfTytle));

			for (int i = 0; i < data.size(); i++) {
				for (int j = 0; j < data.get(i).length; j++) {
					ws.addCell(new Label(j, 3 + i, data.get(i)[j], wcfTytle));
				}
			}			
			//页底			
			ws.mergeCells(0, data.size()+3, 7, data.size()+3);
			ws.addCell(new Label(0, data.size()+3, "负责人：                        联系人：                         联系电话：", btomTytle));
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 向客户端输出
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	/**
	 * 国家助学金学生名单备案表
	 * @param form
	 * @param os
	 * */
	public void printGjzxjtjbb(XszzTyForm model, OutputStream os){
		String xmdm = XszzXmdm.XSZZ_GJZXJ;//国家助学金
		List<String[]> data = dao.getGjzxjData(xmdm, model);//查询要导出的数据
		
		StringBuilder title = new StringBuilder();
		title.append("海南省");
		title.append(model.getXn());
		title.append("年度获国家助学金学生名单备案表");

		ExcelMB excel = new ExcelMB();
		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(os);
		WritableSheet ws = wwb.createSheet("国家助学金名单", 0);

		try {
			excel.printTitle(ws, title.toString(), 19, 800);// 标题
			WritableCellFormat btomTytle = ExcelMB.getWritableCellFormat(12,
					false, Alignment.LEFT, VerticalAlignment.CENTRE,
					Border.NONE);// 单元格样式			
			WritableCellFormat wcfTytle = ExcelMB.getWritableCellFormat(12,
					false, Alignment.CENTRE, VerticalAlignment.CENTRE,
					Border.ALL);// 单元格样式
			
			//数据输出
			ws.mergeCells(0, 1, 9, 1);
			ws.addCell(new Label(0, 1, "编制单位：               （公章）                                           报送时间：       年       月       日", btomTytle));
			
			ws.addCell(new Label(0, 2, "序号", wcfTytle));
			ws.addCell(new Label(1, 2, "学校名称", wcfTytle));
			ws.addCell(new Label(2, 2, Base.YXPZXY_KEY+"名称", wcfTytle));
			ws.addCell(new Label(3, 2, "学号", wcfTytle));
			ws.addCell(new Label(4, 2, "姓名", wcfTytle));
			ws.addCell(new Label(5, 2, "性别", wcfTytle));
			ws.addCell(new Label(6, 2, "出生日期", wcfTytle));
			ws.addCell(new Label(7, 2, "户籍所在地", wcfTytle));
			ws.addCell(new Label(8, 2, "民族", wcfTytle));
			ws.addCell(new Label(9, 2, "专业", wcfTytle));
			ws.addCell(new Label(10, 2, "年级", wcfTytle));
			ws.addCell(new Label(11, 2, "入学时间", wcfTytle));
			ws.addCell(new Label(12, 2, "资助档次", wcfTytle));
			ws.addCell(new Label(13, 2, "资助金额", wcfTytle));
			ws.addCell(new Label(14, 2, "银行账号", wcfTytle));
			ws.addCell(new Label(15, 2, "校区", wcfTytle));
			ws.addCell(new Label(16, 2, "备注", wcfTytle));

			for (int i = 0; i < data.size(); i++) {
				for (int j = 0; j < data.get(i).length; j++) {
					ws.addCell(new Label(j, 3 + i, data.get(i)[j], wcfTytle));
				}
			}			
			//页底			
			ws.mergeCells(0, data.size()+3, 7, data.size()+3);
			ws.addCell(new Label(0, data.size()+3, "负责人：                        联系人：                         联系电话：", btomTytle));
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 向客户端输出
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	/**
	 * 优秀困难生奖学金学生名单备案表
	 * @param form
	 * @param os
	 * */
	public void printYxpksjxjtjbb(XszzTyForm model, OutputStream os){
		String xmdm = XszzXmdm.XSZZ_HNSGXYXPKSJXJ;//优秀困难生奖学金
		List<String[]> data = dao.getYxpksjxjData(xmdm, model);//查询要导出的数据
		
		StringBuilder title = new StringBuilder();
		title.append(model.getNd());
		title.append("年获得省优秀贫困生奖学金学生名单备案表");

		ExcelMB excel = new ExcelMB();
		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(os);
		WritableSheet ws = wwb.createSheet("优秀困难生奖学金名单", 0);

		try {
			excel.printTitle(ws, title.toString(), 13, 800);// 标题			
			WritableCellFormat wcfTytle = ExcelMB.getWritableCellFormat(12,
					true, Alignment.CENTRE, VerticalAlignment.CENTRE,
					Border.ALL);// 单元格样式
			wcfTytle.setBorder(Border.ALL, BorderLineStyle.MEDIUM);
			WritableCellFormat btomTytle = ExcelMB.getWritableCellFormat(12,
					false, Alignment.LEFT, VerticalAlignment.CENTRE,
					Border.NONE);// 单元格样式
			//数据输出			
			ws.mergeCells(0, 1, 9, 1);
			ws.addCell(new Label(0, 1, "填报单位：               （公章）                                           报送日期：       年       月       日", btomTytle));
			
			ws.addCell(new Label(0, 2, "序号", wcfTytle));
			ws.addCell(new Label(1, 2, "院校名称", wcfTytle));
			ws.addCell(new Label(2, 2, Base.YXPZXY_KEY, wcfTytle));
			ws.addCell(new Label(3, 2, "专业", wcfTytle));
			ws.addCell(new Label(4, 2, "姓名", wcfTytle));
			ws.addCell(new Label(5, 2, "性别", wcfTytle));
			ws.addCell(new Label(6, 2, "出生年月", wcfTytle));
			ws.addCell(new Label(7, 2, "民族", wcfTytle));
			ws.addCell(new Label(8, 2, "学号", wcfTytle));
			ws.addCell(new Label(9, 2, "入学时间", wcfTytle));
			ws.addCell(new Label(10, 2, "家庭所在地(详细）", wcfTytle));
			ws.addCell(new Label(11, 2, "银行卡号", wcfTytle));
			ws.addCell(new Label(12, 2, "备注", wcfTytle));

			for (int i = 0; i < data.size(); i++) {
				for (int j = 0; j < data.get(i).length; j++) {
					ws.addCell(new Label(j, 3 + i, data.get(i)[j], wcfTytle));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 向客户端输出
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	
	/**
	 * 中海油大学生助学基金汇总表
	 * @param form
	 * @param os
	 * */
	public void printZhydxszxjtjbb(XszzTyForm model, OutputStream os){
		String xmdm = XszzXmdm.XSZZ_TJBB_ZHYDXSZXJ;//中海油大学生助学基金
		List<String[]> data = dao.getZhydxszxjData(xmdm, model);//查询要导出的数据
		HashMap<String, String> map = dao.getStuCollect(model);//学生总体情况
		
		StringBuilder title = new StringBuilder();
		title.append("中国宋庆龄基金会“中海油大学生助学基金”受助学生汇总表");

		ExcelMB excel = new ExcelMB();
		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(os);
		WritableSheet ws = wwb.createSheet("中海油大学生助学基金汇总表", 0);		

		try {
			excel.printTitle(ws, title.toString(), 11, 800);// 标题			
			WritableCellFormat wcfTytle = ExcelMB.getWritableCellFormat(12,
					false, Alignment.CENTRE, VerticalAlignment.CENTRE,
					Border.ALL);// 普通单元格样式
			wcfTytle.setBorder(Border.ALL, BorderLineStyle.MEDIUM);
			WritableCellFormat titleTytle = ExcelMB.getWritableCellFormat(12,
					true, Alignment.CENTRE, VerticalAlignment.CENTRE,
					Border.ALL);// 表表头单元格样式
			titleTytle.setBackground(Colour.GRAY_25);
			
			WritableCellFormat liTytle = ExcelMB.getWritableCellFormat(10,
					false, Alignment.CENTRE, VerticalAlignment.CENTRE,
					Border.NONE);// 小字体单元格样式
			WritableCellFormat qzTytle = ExcelMB.getWritableCellFormat(12,
					true, Alignment.LEFT, VerticalAlignment.CENTRE,
					Border.NONE);// 小字体单元格样式
			
			
			WritableCellFormat zhTytle = ExcelMB.getWritableCellFormat(12,
					true, Alignment.CENTRE, VerticalAlignment.CENTRE,
					Border.NONE);// 小字体单元格样式
			
			//数据输出					
			ws.mergeCells(0, 2, 11, 2);
			ws.addCell(new Label(0, 2, "学校名称（盖章）_________", qzTytle));
			ws.mergeCells(1, 4, 10, 4);
			ws.addCell(new Label(1, 4, "学生总体情况", titleTytle));
			
			ws.addCell(new Label(1, 5, "项目年度", wcfTytle));
			ws.addCell(new Label(2, 5, "在校总人数", wcfTytle));
			ws.addCell(new Label(3, 5, "特困生人数", wcfTytle));
			ws.addCell(new Label(4, 5, "贫困生人数", wcfTytle));
			ws.addCell(new Label(5, 5, "农村籍学生数", wcfTytle));
			ws.mergeCells(6, 5, 7, 5);
			ws.addCell(new Label(6, 5, "特困新生人数", wcfTytle));
			ws.mergeCells(8, 5, 9, 5);
			ws.addCell(new Label(8, 5, "贫困新生人数", wcfTytle));
			ws.addCell(new Label(10, 5, "填表人签章", wcfTytle));
			
			ws.addCell(new Label(1, 6, model.getXn(), wcfTytle));
			ws.addCell(new Label(2, 6, map.get("zxrs"), wcfTytle));
			ws.addCell(new Label(3, 6, map.get("tksrs"), wcfTytle));
			ws.addCell(new Label(4, 6, map.get("pksrs"), wcfTytle));
			ws.addCell(new Label(5, 6, map.get("ncxss"), wcfTytle));
			ws.mergeCells(6, 6, 7, 6);
			ws.addCell(new Label(6, 6, map.get("xstkrs"), wcfTytle));
			ws.mergeCells(8, 6, 9, 6);
			ws.addCell(new Label(8, 6, map.get("xspkrs"), wcfTytle));
			ws.addCell(new Label(10, 6, "", wcfTytle));
			
			ws.mergeCells(0, 10, 10, 10);
			ws.addCell(new Label(0, 10, "                        开户行_________     开户名_________       帐号_________", zhTytle));
			ws.mergeCells(1, 12, 6, 12);
			ws.addCell(new Label(1, 12, "填表说明：①“贫困程度”栏填写“特困”、“贫困”、“困难”；②学校对此表的真实性负责。", liTytle));
			ws.mergeCells(1, 13, 10, 13);
			ws.addCell(new Label(1, 13, "受助学生情况汇总", titleTytle));
			
			ws.addCell(new Label(1, 14, "姓名", wcfTytle));
			ws.addCell(new Label(2, 14, "性别", wcfTytle));
			ws.addCell(new Label(3, 14, "贫困程度", wcfTytle));
			ws.addCell(new Label(4, 14, "民族", wcfTytle));
			ws.addCell(new Label(5, 14, "家中是否欠债", wcfTytle));
			ws.addCell(new Label(6, 14, "每月生活费", wcfTytle));
			ws.mergeCells(7, 14, 8, 14);
			ws.addCell(new Label(7, 14, "所在院系及专业", wcfTytle));
			
			ws.addCell(new Label(9, 14, "联系电话", wcfTytle));
			ws.addCell(new Label(10, 14, "身份证号", wcfTytle));

			
			for (int i = 0; i < data.size(); i++) {	
				int rowNum = 0;
				for (int j = 0; j < data.get(i).length; j++) {
					if(j==7){
						ws.mergeCells(7, 15 + i, 8, 15 + i);
						rowNum++;
					}
					ws.addCell(new Label(rowNum+1, 15 + i, data.get(i)[j], wcfTytle));
					rowNum++;
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 向客户端输出
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	

	/***
	 * 打印资助所有的统计报表
	 * @param form
	 * @param os
	 * */
	
	public void printZztjbb(XszzTyForm form, OutputStream os) {
		if(XszzXmdm.XSZZ_TJBB_KNS.equalsIgnoreCase(form.getTjbbdm())){//困难生统计
			printKnstjbb(form,os);
		}else if(XszzXmdm.XSZZ_TJBB_GJJXJ.equalsIgnoreCase(form.getTjbbdm())){//国家奖学金
			printGjjxjtjbb(form,os);
		}else if(XszzXmdm.XSZZ_TJBB_GJLZJXJ.equalsIgnoreCase(form.getTjbbdm())){//国家励志奖学金
			printGjlsjxjtjbb(form,os);
		}else if(XszzXmdm.XSZZ_TJBB_FJZXJ.equalsIgnoreCase(form.getTjbbdm())){//国家助学金
			printGjzxjtjbb(form,os);
		}else if(XszzXmdm.XSZZ_TJBB_YXPKSJXJ.equalsIgnoreCase(form.getTjbbdm())){//优秀贫困生奖学金
			printYxpksjxjtjbb(form,os);
		}else if(XszzXmdm.XSZZ_TJBB_ZHYDXSZXJ.equalsIgnoreCase(form.getTjbbdm())){//中海油大学生助学基金
			printZhydxszxjtjbb(form,os);
		}
	}
	
}
