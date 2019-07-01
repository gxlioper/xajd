package xgxt.xszz;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.util.ArrayList;
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

import org.apache.struts.upload.FormFile;

import common.Globals;
import common.XszzXmdm;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.xml.XMLReader;
import xgxt.form.SaveForm;
import xgxt.studentInfo.dao.StuInfoDAO;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.ExcelMethods;
import xgxt.utils.FormModleCommon;
import xgxt.utils.Pages;
import xgxt.xszz.comm.XszzCommDAO;
import xgxt.xszz.comm.XszzCommService;

public class XszzService extends CommService{

	XszzDAO dao = new XszzDAO();

	/**
	 * 设置所需页面初始化列表
	 * 
	 * @param form
	 * @param request
	 * @author luojw
	 * @throws Exception
	 */
	public void setList(XszzTyForm myForm, HttpServletRequest request,
			String manu) throws Exception {

		// =====================相关默认项目的项目代码=============================
		// 困难生代码
		String knsdm = XszzXmdm.XSZZ_KNS;
		String mklx = myForm.getMklx();
		
		// =====================通用=============================
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);// 年级学院专业班级
		FormModleCommon.setNdXnXqList(request);// 年度学年学期
		FormModleCommon.requestSetList(new String[] { "bm" }, request);// 自定义(目前：部门代码)

		List<HashMap<String, String>> xbList = dao.getSelectList("xblx");// 性别
		request.setAttribute("xbList", xbList);

		List<HashMap<String, String>> sfList = dao.getSelectList("sflx");// 是否列表
		request.setAttribute("sfList", sfList);

		// 审核列表
		List<HashMap<String, String>> shList = dao.getSelectList("shlx");
		request.setAttribute("shList", shList);
		
		// 家庭成员标题属性
		List<HashMap<String, String>> jtcyTitleList = dao
				.getSelectList("jtcyzd");
		request.setAttribute("jtcyTitleList", jtcyTitleList);
		
		// 项目类别列表
		if("pj".equalsIgnoreCase(mklx)){
			List<HashMap<String, String>> xmlbList = dao.getSelectList("xmlb_pj");
			request.setAttribute("xmlbList", xmlbList);
		}else if("zz".equalsIgnoreCase(mklx)){
			List<HashMap<String, String>> xmlbList = dao.getSelectList("xmlb_zz");
			request.setAttribute("xmlbList", xmlbList);
		}else{
			
			String xxdm = Base.xxdm;
			
			//武汉铁路职业技术学院
			if(Globals.XXDM_WHTLZYJSXY.equalsIgnoreCase(xxdm)){	
				List<HashMap<String, String>> xmlbList =  dao.getWhList(
						"xg_xszz_xmlbb", "xmlb", "xmlb", "", "", "");
				
				ArrayList<HashMap<String, String>> lbList = new ArrayList<HashMap<String, String>>();
				for(int i = 1;i<xmlbList.size();i++){
					HashMap<String,String> map = xmlbList.get(i);
					map.put("en", map.get("dm"));
					map.put("cn", map.get("mc"));
					lbList.add(map);
				}
				
				request.setAttribute("xmlbList", lbList);
			
			}else{
				List<HashMap<String, String>> xmlbList = dao.getSelectList("xmlb");
				request.setAttribute("xmlbList", xmlbList);
			}
		}
		// =====================副食补助=============================
		if ("fsbz_fp".equalsIgnoreCase(manu)) {// 分配
			DAO tyDao = DAO.getInstance();
			// 学年列表
			List<HashMap<String, String>> ndList = tyDao.getXnndList();
			ndList.remove(0);
			request.setAttribute("ndList", ndList);

		} else if ("fsbz_ff".equalsIgnoreCase(manu)) {// 发放
			DAO tyDao = DAO.getInstance();
			// 学年列表
			List<HashMap<String, String>> ndList = tyDao.getXnndList();
			ndList.remove(0);
			request.setAttribute("ndList", ndList);

			// 月份列表
			List<HashMap<String, String>> yfList = tyDao.getYfList();
			request.setAttribute("yfList", yfList);

			// 补助类型列表
			List<HashMap<String, String>> bzlxList = dao.getWhList(
					"xszz_fsbzlxb", "dm", "mc", "", "", "");
			bzlxList.remove(0);
			request.setAttribute("bzlxList", bzlxList);
		}
		// =====================经济困难生=============================
		else if ("jjkns".equalsIgnoreCase(manu)) {

			// 户口所在信息
			StuInfoDAO stuDao = new StuInfoDAO();
			HashMap<String, String> map = new HashMap<String, String>();
			String xh = myForm.getXh();
			String[] colList = new String[] { "hkshen", "hkshi", "hkxian",
					"syd" };
			map = CommonQueryDAO
					.commonQueryOne("view_xsxxb", colList, "xh", xh);

			// 省列表
			List<HashMap<String, String>> ssList = stuDao.getSsList();
			request.setAttribute("ssList", ssList);

			// 户口所在市列表
			List<HashMap<String, String>> hkshiList = stuDao.getShiList(
					map.get("hkshen")).get("shiList");
			request.setAttribute("hkshiList", hkshiList);

			// 户口所在县列表
			List<HashMap<String, String>> hkxianList = stuDao.getShiList(
					map.get("hkshi")).get("xianList");
			request.setAttribute("hkxianList", hkxianList);

			// 德育等级列表
			List<HashMap<String, String>> dydjList = dao.getWhList(
					"hndx_xszz_dydjb", "dm", "mc", "", "", "");
			request.setAttribute("dydjList", dydjList);

			// 审核列表
			List<HashMap<String, String>> knsjbList = dao.getWhList(
					"hndx_xszz_jjknsjb", "dm", "mc", "", "", "");
			knsjbList.remove(0);
			request.setAttribute("knsjbList", knsjbList);
		}
		// =====================项目维护=============================
		else if ("xmwh".equalsIgnoreCase(manu)) {

			XszzCommService commService = new XszzCommService();
			// 所有项目列表
			List<HashMap<String, String>> xmList = commService
					.getShXmList(myForm);
			request.setAttribute("xmList", xmList);
			
			// 审核级别列表
			List<HashMap<String, String>> shjbList = dao.getSelectList("shjb");
			request.setAttribute("shjbList", shjbList);

			// 人数控制列表
			List<HashMap<String, String>> rsjbList = dao.getSelectList("rsjb");
			request.setAttribute("rsjbList", rsjbList);

			// 控制级别列表
			List<HashMap<String, String>> kzjbList = dao.getSelectList("kzjb");
			request.setAttribute("kzjbList", kzjbList);

			// 项目设置是否列表
			List<HashMap<String, String>> szsfList = dao
					.getSelectList("szsflx");
			request.setAttribute("szsfList", szsfList);

			// 学制列表
			List<HashMap<String, String>> xzList = dao.getWhList("view_xsjbxx",
					"xz", "xz", "", "", "");
			xzList.remove(0);
			request.setAttribute("xzList", xzList);

			// 申请周期列表
			List<HashMap<String, String>> sqzqList = dao.getSelectList("sqzq");
			request.setAttribute("sqzqList", sqzqList);

			// 是否需要金额列表
			List<HashMap<String, String>> sfjeList = dao.getSelectList("sfje");
			request.setAttribute("sfjeList", sfjeList);

			// 是否分级列表
			List<HashMap<String, String>> sffjList = dao.getSelectList("sffj");
			request.setAttribute("sffjList", sffjList);

			// 金额类型列表
			List<HashMap<String, String>> jelxList = dao.getSelectList("jelx");
			request.setAttribute("jelxList", jelxList);

			// 开关类型列表
			List<HashMap<String, String>> kgztList = dao.getSelectList("kgzt");
			request.setAttribute("kgztList", kgztList);
			
			// 评定时间列表
			List<HashMap<String, String>> pdsjList = dao.getSelectList("pdsj");
			request.setAttribute("pdsjList", pdsjList);
			
			// 困难生列表
			List<HashMap<String, String>> knsList = dao.getWhList(
					"xszz_xmfjqkb", "fjmc", "fjmc", "", "xmdm", knsdm);
			request.setAttribute("knsList", knsList);
			
			// 条件类型
			List<HashMap<String, String>> tjlxList = dao.getSelectList("zhftj");
			tjlxList.remove(0);
			request.setAttribute("tjlxList", tjlxList);
		}
		// =====================人数设置=============================
		else if ("rssz".equalsIgnoreCase(manu)) {

			XszzCommDAO commDao = new XszzCommDAO();

			// 资助项目列表
			String query = getFlowControlSql(myForm);
			List<HashMap<String, String>> zzxmList = commDao.getZzxmList(mklx,myForm.getQueryequals_xmlb(),query);
			request.setAttribute("zzxmList", zzxmList);

			// 控制级别列表
			List<HashMap<String, String>> kzjbList = dao.getSelectList("kzjb");
			request.setAttribute("kzjbList", kzjbList);
 
		}
		// =====================结果查询=============================
		else if ("jgcx".equalsIgnoreCase(manu)) {
			XszzCommService commService = new XszzCommService();

			// 所有项目列表
			List<HashMap<String, String>> xmList = commService
					.getShXmList(myForm);
			request.setAttribute("xmList", xmList);
		}

		// =====================项目申请审核=============================
		else if ("sqsh".equalsIgnoreCase(manu)) {

			// 所有项目列表
			List<HashMap<String, String>> gxList = dao.getWhList(
					"xszz_jtcygxb", "dm", "mc", "", "", "");
			gxList.remove(0);
			request.setAttribute("gxList", gxList);
		}
		// =====================字段设置=============================
		else if ("zdsz".equalsIgnoreCase(manu)) {

			// 所有项目列表
			List<HashMap<String, String>> xmList = dao.getWhList("xszz_zzxmb",
					"xmdm", "xmmc", "", "mrxm", "是");
			
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("dm", "ws");
			map.put("mc", "其他");
			
			xmList.add(map);
			
			request.setAttribute("xmList", xmList);
		}
		// =====================条件设置=============================
		else if ("tjsz".equalsIgnoreCase(manu)) {

			// 项目列表
			List<HashMap<String, String>> xmList = dao.getWhList(
					"xszz_com_wszxjdmb", "xmdm", "xmmc", "", "", "");

			HashMap<String, String> map = new HashMap<String, String>();
			map.put("dm", "kns");
			map.put("mc", "经济困难生认定");
			xmList.add(map);

			map = new HashMap<String, String>();
			map.put("dm", "xfhj");
			map.put("mc", "学费缓交");
			xmList.add(map);
			request.setAttribute("xmList", xmList);

			// 条件列表
			List<HashMap<String, String>> tjList = dao.getWhList("xszz_tjb",
					"tjdm", "tjmc", "", "", "");
			request.setAttribute("tjList", tjList);

			// 条件类型列表
			List<HashMap<String, String>> tjlxList = dao.getSelectList("tjlx");
			request.setAttribute("tjlxList", tjlxList);

		}
		
		//困难生是否独立模块
		if ("yes".equals(myForm.getIskns())){
			request.setAttribute("knsdl", XMLReader.getFlowControl("xszz", "knsdl"));
			//困难生认定是否包含了"家庭情况调查"
			request.setAttribute("jtqkdc", XMLReader.getFlowControl("xszz", "jtqkdc"));
		}
		
	}

	/**
	 * 获得学生信息
	 * 
	 * @author luojw
	 */
	public HashMap<String, String> getStuInfo(String xh) {
		return CommonQueryDAO.getStuInfo(xh);
	}

	/**
	 * 获得学生信息
	 * 
	 * @author luojw
	 */
	public HashMap<String, String> getDetStuInfo(String xh) {
		return CommonQueryDAO.getDetStuInfo(xh);
	}

	/**
	 * 获得学生资助信息（列表）
	 * 
	 * @author luojw
	 */
	public ArrayList<String[]> getXszzList(String tableName, Object model,
			String[] colList, String other_query)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		return dao.getXszzListInfo(tableName, model, colList, other_query);
	}

	/**
	 * 获得学生资助相关信息（详细）
	 * 
	 * @author luojw
	 * 
	 * @param tableName(表名)
	 * @param pk(主键)
	 * @param pkValue(主键值)
	 * @param colList(输出值)
	 * 
	 */
	public HashMap<String, String> getXszzInfo(String tableName, String pk,
			String pkValue, String[] colList) {
		return dao.getXszzInfo(tableName, pk, pkValue, colList);
	}

	/**
	 * 删除学生资助信息
	 * 
	 * @author luojw
	 * 
	 * @param tableName(表名)
	 * @param pk(主键)
	 * @param pkValue(主键值)
	 * 
	 * @throws Exception
	 */
	public boolean delXszz(SaveForm form, Object model) throws Exception {
		DAO dao = DAO.getInstance();

		return dao.delDate(form, model);
	}

	/**
	 * 保存学生资助相关信息（批量）
	 * 
	 * @author luojw
	 * 
	 * @param tableName(表名)
	 * @param pk(主键)
	 * @param pkValue(主键值)
	 * @param arrzd(批量字段)
	 * @param onezd(单一字段)
	 * @param notnull(非空字段)
	 * 
	 * @throws Exception
	 */
	public boolean saveXszz(SaveForm form, Object model) throws Exception {
		DAO dao = DAO.getInstance();
		return dao.saveData(form, model);
	}

	/**
	 * 保存生资助相关信息（单条）
	 * 
	 * @author luojw
	 * 
	 * @param tableName(表名)
	 * @param pk(主键)
	 * @param pkValue(主键值)
	 * @param onezd(单一字段)
	 * 
	 * @throws Exception
	 */
	public boolean saveXszz(SaveForm form, Object model,
			HttpServletRequest request) throws Exception {
		DAO dao = DAO.getInstance();
		return dao.submitData(form, model, request);
	}

	/**
	 * 更新学生资助相关信息
	 * 
	 * @author luojw
	 * 
	 * @param tableName(表名)
	 * @param pk(主键)
	 * @param pkValue(主键值)
	 * @param onezd(单一字段)
	 * 
	 * @throws Exception
	 */
	public boolean updateXszz(SaveForm form, Object model) throws Exception {
		DAO dao = DAO.getInstance();
		return dao.updateData(form, model);
	}

	/**
	 * @describe 删除所上传文件
	 * @author luojw
	 * 
	 * @param tableName(表名)
	 * @param pk(主键)
	 * @param pkValue(主键值)
	 * @param dzzd(地址字段)
	 * 
	 * @throws Exception
	 */
	public boolean fileDel(String tableName, String dzzd, String pk,
			String pkValue) throws Exception {
		return dao.fileDel(tableName, dzzd, pk, pkValue);
	}

	/**
	 * 获得系统当前时间
	 * 
	 * @author luojw
	 */
	public String getNowTime(String lx) {
		return dao.getNowTime(lx);
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

	/**
	 * 处理文件上传
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public String uploadFile(XszzTyForm model, HttpServletRequest request)
			throws FileNotFoundException, IOException {
		// 处理文件上传
		FormFile file = model.getUploadFile();
		String filePath = "";
		String fName = "";

		if (file != null && !Base.isNull(file.getFileName())) {
			String dir = "/upload/xszz";
			File f = new File(dir);
			if (!f.exists()) {
				f.mkdirs();
			}
			Timestamp date = new Timestamp(System.currentTimeMillis());
			String dateStr = date.toString().substring(0, 19);
			dateStr = dateStr.replaceAll("-", "").replaceAll(" ", "")
					.replaceAll(":", "");
			int size = file.getFileSize();
			if (size < 10485760 && size != 0) {
				fName = dateStr + file.getFileName();
				InputStream in = file.getInputStream();
				filePath = dir + "/" + fName;
				OutputStream out = new FileOutputStream(filePath);
				int bytesRead = 0;
				byte[] buffer = new byte[size];
				while ((bytesRead = in.read(buffer, 0, size)) != -1) {
					out.write(buffer, 0, bytesRead);
				}
				out.close();
				in.close();
			} else {
				request.setAttribute("fileUplod", "false");
			}
		}
		return filePath;
	}
	
	/**
	 * 获得学生资助分页
	 * 
	 * @author luojw
	 */
	public ArrayList<String[]> getResultList(ArrayList<String[]> list,
			XszzTyForm model) {

		// 分页
		ArrayList<String[]> rsList = new ArrayList<String[]>();

		if (list != null && list.size() > 0) {

			Pages pages = model.getPages();
			pages.setMaxRecord(list.size());
			int start = pages.getStart();
			int size = pages.getPageSize();

			for (int i = start; i < start + size; i++) {
				if (i < list.size()) {
					rsList.add(list.get(i));
				}
			}
		}

		return rsList;
	}
		
	/**
	 * 获得学生资助分页
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getResultList(
			List<HashMap<String, String>> list, XszzTyForm model) {

		// 分页
		List<HashMap<String, String>> rsList = new ArrayList<HashMap<String, String>>();
		Pages pages = model.getPages();
		if (list != null && list.size() > 0) {
			pages.setMaxRecord(list.size());
			int start = pages.getStart();
			int size = pages.getPageSize();

			for (int i = start; i < start + size; i++) {
				if (i < list.size()) {
					rsList.add(list.get(i));
				}
			}
		}else{
			pages.setMaxRecord(0);
		}

		return rsList;
	}
	
	/**
	 * 学生资助导出
	 * 
	 * @author luo
	 * @throws Exception
	 */
	public void expXszzData(String title, List<HashMap<String, String>> topTr,
			ArrayList<String[]> list, OutputStream os) throws Exception {

		WritableWorkbook wwb = Workbook.createWorkbook(os);

		WritableSheet ws = wwb.createSheet(title, 0);

		WritableCellFormat wcf2 = new WritableCellFormat(); // 构造单元格格式
		wcf2 = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL);

		// 填充表头
		if (topTr != null && topTr.size() > 0) {
			for (int i = 0; i < topTr.size(); i++) {
				HashMap<String, String> map = topTr.get(i);
				ws.addCell(new Label(i, 0, map.get("cn"), wcf2));
			}
		}

		//填充内容
		if (list != null && list.size() > 0) {

			for (int i = 0; i < list.size(); i++) {

				String[] info = list.get(i);

				if (info != null && info.length > 0) {

					for (int j = 0; j < info.length; j++) {
						ws.addCell(new Label(j, i + 1, info[j], wcf2));
					}
				}
			}
		}

		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	/**
	 * 获得指定表的字段
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public String[] getTableZd(String tableName) throws Exception {
		return dao.getTableZd(tableName);
	}
	
	/**
	 * 获得班级人数
	 * 
	 * @author luojw
	 */
	public String getBjrs(HashMap<String, String> map) {
		return dao.getBjrs(map);
	}
	
	/**
	 * 获得资助项目相关信息
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public HashMap<String, String> getXmxgInfo(XszzTyForm model) {

		String tableName = "view_xszz_comm_xmwh";
		// 主键
		String pk = "pk";
		// 主键值得
		String pkValue = model.getPkValue();
		// 输出字段
		String[] colList = new String[] { "bzrkz", "bzrsh", "fdykz", "fdysh",
				"kgzt", "kzjb", "mrxm", "rskz", "rssx", "sffj", "jelx", "sfje",
				"shjb", "sqzq", "xmb", "xmdm", "xmmc", "xmsm", "xxdm", "xxkz",
				"xxsh", "xykz", "xysh", "xmlb", "pdsj", "xssx" };

		// 项目相关信息
		HashMap<String, String> map = (Base.isNull(pkValue)) ? new HashMap<String, String>()
				: getXszzInfo(tableName, pk, pkValue, colList);

		return map;
	}
	
	/**
	 * 判断是否困难生（N32）
	 * 
	 * @author luojw
	 */
	public Boolean isKns(HashMap<String, String> map) {

		// 学年
		String xn = Base.currXn;
		// 学期
		String xq = Base.currXq;
		// 年度
		String nd = Base.currNd;
		// 评奖学年
		String pjxn = Base.getJxjsqxn();
		// 评奖学期
		String pjxq = Base.getJxjsqxq();
		// 评奖年度
		String pjnd = Base.getJxjsqnd();
		// 困难生项目代码
		String xmdm = XszzXmdm.XSZZ_KNS;
		// 项目相关信息
		XszzTyForm model = new XszzTyForm();
		model.setPkValue(xmdm);
		HashMap<String, String> xmInfo = getXmxgInfo(model);
		// 困难生评奖周期
		String sqzq = xmInfo.get("sqzq");
		// 困难生审核级别
		String shjb = xmInfo.get("shjb");

		map.put("xn", xn);
		map.put("xq", xq);
		map.put("nd", nd);
		map.put("pjxn", pjxn);
		map.put("pjxq", pjxq);
		map.put("pjnd", pjnd);
		map.put("sqzq", sqzq);
		map.put("shjb", shjb);

		return dao.isKns(map);
	}
	
	/**
	 * 贷款年限全部初始化
	 * @return
	 * @throws Exception 
	 */
	public boolean dknxCsh() throws Exception {
		return dao.dknxCsh();
	}
	
	/**
	 * 贷款年限部分初始化
	 * @param bjdm
	 * @return
	 * @throws Exception
	 */
	public boolean dknxCsh(String[] bjdm) throws Exception {
		return dao.dknxCsh(bjdm);
	}
	
	/**
	 * 获取资助统计报表列表
	 * 
	 * @return List<HashMap<String, String>>
	 */
	public List<HashMap<String, String>> getTjbbList() {
		// ------------2010.9.25 edit by luojw-----------------------
		List<HashMap<String, String>> list = dao.getWhList("xszz_zztjbb",
				"tjbbdm", "tjbbmc", "", "", "");
		String xxdm = Base.xxdm;
		// 中国地大
		if (Globals.XXDM_ZGDZDX.equalsIgnoreCase(xxdm)) {
			list = dao.getWhList("xszz_zzxmb", "xmdm", "xmmc", "", "", "");
		}
		return list;
	}
	
	
	/**
	 * 初始化项目
	 * @param model
	 * @param manu
	 * @return
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws SecurityException 
	 * @throws IllegalArgumentException 
	 */
	public XszzTyForm initXmdm(XszzTyForm model, String manu)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		XszzCommService commonService = new XszzCommService();
		String xxdm=Base.xxdm;
		
		//人数设置初始化
		if ("rssz".equalsIgnoreCase(manu)) {

			// 项目代码列表
			List<HashMap<String, String>> xmdmList = dao.getXmxxByRssz("%",getFlowControlSql(model));

			// 第一个项目代码
			if (xmdmList.size() > 0) {
				model.setXmdm(xmdmList.get(0).get("dm"));
			}
			// 人数控制时 控制级别（学院、班级等）
			HashMap<String, String> kzjbMap = dao.getXszzInfo("xszz_zzxmb",
					"xmdm", model.getXmdm(), new String[] { "kzjb" });

			model.setKzjb(kzjbMap.get("kzjb"));
			
		}else if("jgcx".equalsIgnoreCase(manu) 
			//地大资助 结果查询初始化
				&& Globals.XXDM_ZGDZDX.equalsIgnoreCase(xxdm)){ 
			List<HashMap<String, String>> xmdmList =  commonService.getShXmList(model);

			// 第一个项目代码
			if (xmdmList.size() > 0) {
				model.setXmdm(xmdmList.get(0).get("xmdm"));
			}

			// 项目信息
			HashMap<String, String> xmMap = dao.getXszzInfoByXmdm(model
					.getXmdm());
			model.setXmb(xmMap.get("xmb"));
		
		}else if ("jgcx".equalsIgnoreCase(manu)) {
			//结果查询初始化
			List<HashMap<String, String>> xmdmList = dao.getXszzxmList(
					"xszz_zzxmb", "xmdm", "xmmc", "", "kgzt", "开放申请");

			// 第一个项目代码,如果是困难生独立模块则为困难生认定
			if ("yes".equals(model.getIskns())){
				model.setXmdm(XszzXmdm.XSZZ_KNS);
			} else if (xmdmList.size() > 0) {
				model.setXmdm(xmdmList.get(0).get("dm"));
			}

			// 项目信息
			HashMap<String, String> xmMap = dao.getXszzInfoByXmdm(model
					.getXmdm());
			model.setXmb(xmMap.get("xmb"));
		
		//项目审核初始化
		} else if ("xmsh_xs".equalsIgnoreCase(manu)) {

			List<HashMap<String, String>> xmdmList = commonService
					.getXmshList(model);

			// 第一个项目代码
			if (xmdmList.size() > 0) {
				model.setXmdm(xmdmList.get(0).get("xmdm"));
			}
		}
		return model;
	}
	
	/**
	 * 初始化项目
	 * @param model
	 * @param manu
	 * @return
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws SecurityException 
	 * @throws IllegalArgumentException 
	 */
	public XszzTyForm initXmdm(XszzTyForm model, HttpServletRequest request)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		XszzCommService commonService = new XszzCommService();
	
		List<HashMap<String, String>> xmdmList = commonService
				.getXmshList(model);

		// 第一个项目代码
		if (xmdmList.size() > 0) {
			model.setXmdm(xmdmList.get(0).get("xmdm"));
		}
		
		request.setAttribute("xmshList", xmdmList);
		
		return model;
	}
	
	
	
	/**
	 * 控制查询范围
	 * @param request
	 * @param myForm
	 */
	public String getFlowControlSql(XszzTyForm myForm) {
		//困难生是否作为独立模块
		boolean knsdl = Boolean.valueOf(XMLReader.getFlowControl("xszz", "knsdl"));
		//独立的"困难生认定"项目是否包含了"家庭情况调查"
		boolean jtqkdc = Boolean.valueOf(XMLReader.getFlowControl("xszz", "jtqkdc"));
		
		if (knsdl){
			String sql = "";
			
			if ("yes".equals(myForm.getIskns())){
				if (jtqkdc){
					sql = " and xmdm='"+XszzXmdm.XSZZ_KNS+"' ";
				} else {
					sql = " and (xmdm='"+XszzXmdm.XSZZ_KNS+"' or xmdm='"+XszzXmdm.XSZZ_JTQKDC+"') ";
				}
				
			} else {
				sql = " and xmdm<>'"+XszzXmdm.XSZZ_KNS+"' and xmdm<>'"+XszzXmdm.XSZZ_JTQKDC+"' ";
			}
			return sql;
		}
		return "";
	}
}
