package xgxt.rcsw.zjxy;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.form.SaveForm;
import xgxt.mdqr.MdqrForm;
import xgxt.mdqr.MdqrModel;
import xgxt.rcsw.RcswForm;
import xgxt.rcsw.RcswService;
import xgxt.szdw.ghxy.njzrwh.GhxyNjzrwhService;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.ExcelMethods;
import xgxt.utils.MakeQuery;
import xgxt.utils.Pages;
import xgxt.xginterface.gxt.GxtWebService;
import xgxt.xginterface.gxt.SendMess;

import com.zfsoft.basic.BasicService;

public class RcswZjxyService extends RcswService {

	RcswZjxyDAO dao = new RcswZjxyDAO();

	/**
	 * 获得个性化表头
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getTopTr(String lx) {

		DAO dao = DAO.getInstance();
		String[] colListCN = null;
		String[] colListEN = null;

		if ("swff_jg".equalsIgnoreCase(lx)
				&& "13275".equalsIgnoreCase(Base.xxdm)) {
			colListCN = new String[] { "学号", "姓名", Base.YXPZXY_KEY,"项目类型","项目名称", "学年", "学期", "年度",
					 "办理开始时间", "办理结束时间", "是否办理" };
			colListEN = new String[] { "zgh", "xm", "xymc","xmlxmc","xmmc", "xn", "xqmc", "nd",
					 "ffsj","ffjssj", "isff" };
		} else if ("ffdx_xs".equalsIgnoreCase(lx)) {
			colListCN = new String[] { "学号", "姓名","年级", Base.YXPZXY_KEY+"名称", "专业名称", "班级名称" };
			colListEN = new String[] { "xh", "xm","nj", "xymc", "zymc", "bjmc" };
		} else if ("ffdx_ls".equalsIgnoreCase(lx)) {
			colListCN = new String[] { "学号", "姓名", "部门名称", "职务名称" };
			colListEN = new String[] { "xh", "xm", "bmmc", "zwmc" };
		} else if ("swff_jg".equalsIgnoreCase(lx)) {
			colListCN = new String[] { "职工号", "姓名", "所在部门", "身份", "学年", "学期",
					"年度", "发放项目", "发放时间", "是否发放" };
			colListEN = new String[] { "zgh", "xm", "xymc", "lx", "xn", "xqmc",
					"nd", "xmmc", "ffsj", "isff" };
		} else if ("swff_jgtj".equalsIgnoreCase(lx)) {
			colListCN = new String[] { "学年", "学期", "年度", "项目类型", "项目名称", "办理开始时间",
					"办理结束时间", "应办理人数", "已办理人数", "未办理人数","发送短信人数","收到短信人数","已经评价人数","未评价人数" };
			colListEN = new String[] { "xn", "xq", "nd", "xmlx", "xmmc", "ffsj",
					"ffjssj", "xffrs", "yffrs", "wffrs","ytz","yfs","ypj","wpj" };
		}

		return dao.arrayToList(colListEN, colListCN);
	}

	/**
	 * 保存实物发放项目维护
	 * 
	 * @author luojw
	 * 
	 * @throws Exception
	 */
	public Boolean saveSwffXmwh(RcswZjxyModel model, String tableName)
			throws Exception {

		String[] ffrq = model.getFfrq();

		boolean flag = false;

		if (ffrq != null && ffrq.length > 0) {

			String pk = "xn||xq||nd||xmlx||ffsj";
			String pkValue = model.getXn() + model.getXq() + model.getNd()
					+ model.getXmlx() + model.getFfsj();
			String[] arrzd = new String[] { "ffrq" };
			String[] onezd = new String[] { "xn", "xq", "nd", "xmlx", "ffsj",
					"bz" };

			SaveForm saveForm = new SaveForm();
			saveForm.setTableName(tableName);
			saveForm.setPk(pk);
			saveForm.setPkValue(new String[] { pkValue });
			saveForm.setArrzd(arrzd);
			saveForm.setOnezd(onezd);

			flag = saveRcsw(saveForm, model);
		}

		return flag;
	}

	/**
	 * 删除实物发放项目维护
	 * 
	 * @author luojw
	 * 
	 * @throws Exception
	 */
	public Boolean delSwffXmwh(RcswZjxyModel model, String[] pkValue,
			String realTable) throws Exception {

		String pk = "xn || xq || nd || xmlx || ffsj";

		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(realTable);
		saveForm.setPk(pk);
		saveForm.setPkValue(pkValue);

		// 删除发放项目
		boolean flag = delRcsw(saveForm, model);

		if (flag) {
			realTable = "rcsw_swffrqwhb";
			saveForm = new SaveForm();
			saveForm.setTableName(realTable);
			saveForm.setPk(pk);
			saveForm.setPkValue(pkValue);
			// 删除发放人群
			flag = delRcsw(saveForm, model);
		}
		return flag;
	}

	/**
	 * 
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public void delSwffXmwh(String[] pkValue) throws Exception {
		dao.delSwffXmwh(pkValue);
	}

	/**
	 * 保存实物发放发放对象
	 * 
	 * @author luojw
	 * 
	 * @throws Exception
	 */
	public Boolean saveSwffFfdx(RcswZjxyModel model, String tableName)
			throws Exception {

		boolean flag = false;

		// 学号职工号
		String[] xhzgh = model.getXhzgh();
		// 临时学号（职工号）
		String[] temp_xhzgh = checkCfsj(dao.getLsXhZgh(), xhzgh);
		int temp_Size = (temp_xhzgh != null && temp_xhzgh.length > 0) ? temp_xhzgh.length
				: 0;
		// 所选学号(职工号)
		String[] checkXhZgh = model.getCheckVal();

		// 最终增加学号(职工号)
		String[] addXhZgh = new String[temp_Size + checkXhZgh.length];
		int m = 0;
		for (int i = 0; i < checkXhZgh.length; i++) {
			addXhZgh[m] = checkXhZgh[i];
			m++;
		}
		for (int i = 0; i < temp_Size; i++) {
			addXhZgh[m] = temp_xhzgh[i];
			m++;
		}

		// 最终删除学号（职工号）
		String[] no_checkXhZgh = checkCfsj(xhzgh, checkXhZgh);
		int temp_no_Size = (no_checkXhZgh != null && no_checkXhZgh.length > 0) ? no_checkXhZgh.length
				: 0;
		String[] delXhZgh = new String[temp_no_Size + addXhZgh.length];

		int n = 0;
		for (int i = 0; i < temp_no_Size; i++) {
			delXhZgh[n] = no_checkXhZgh[i];
			n++;
		}
		for (int i = 0; i < addXhZgh.length; i++) {
			delXhZgh[n] = addXhZgh[i];
			n++;
		}

		// 构建主键
		String pk = "xn||xq||nd||xmlx||ffsj||lx||xhzgh";
		String[] pkValue = new String[delXhZgh.length];
		for (int i = 0; i < delXhZgh.length; i++) {
			pkValue[i] = model.getXn() + model.getXq() + model.getNd()
					+ model.getXmlx() + model.getFfsj() + model.getLx()
					+ delXhZgh[i];
		}

		String[] arrzd = new String[] { "xhzgh" };
		String[] onezd = new String[] { "xn", "xq", "nd", "xmlx", "ffsj", "lx",
				"ffr", "ffbz" };

		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(tableName);
		saveForm.setPk(pk);
		saveForm.setPkValue(pkValue);
		saveForm.setArrzd(arrzd);
		saveForm.setOnezd(onezd);

		model.setXhzgh(addXhZgh);

		flag = saveRcsw(saveForm, model);

		return flag;
	}

	/**
	 * 保存实物发放人员信息
	 * 
	 * @author qlj
	 * 
	 * @throws Exception
	 */
	public Boolean saveSwffRyxx(RcswZjxyModel model, String tableName)
			throws Exception {

		boolean flag = false;

		// 学号(hidden 隐藏域 所有)
		String[] xhzgh = model.getXhzgh();

		// 学号(checkbox 复选框 需保存)
		String[] checkVal = model.getCheckVal();

		// 最终删除学号（职工号）

		// 构建主键
		String pk = "xn||xq||nd||xmlx||ffsj||xhzgh";
		String[] pkValue = new String[xhzgh.length];

		// 需要删除的数据
		for (int i = 0; i < xhzgh.length; i++) {
			pkValue[i] = model.getXn() + model.getXq() + model.getNd()
					+ model.getXmlx() + model.getFfsj() + xhzgh[i];
		}

		// 插入的数据
		String[] arrzd = new String[] { "xhzgh" };
		String[] onezd = new String[] { "xn", "xq", "nd", "xmlx", "ffsj", };

		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(tableName);
		saveForm.setPk(pk);
		saveForm.setPkValue(pkValue);
		saveForm.setArrzd(arrzd);
		saveForm.setOnezd(onezd);

		model.setXhzgh(checkVal);

		flag = saveRcsw(saveForm, model);

		return flag;
	}

	/**
	 * 获得实物发放维护信息
	 * 
	 * @author luojw
	 * 
	 * @throws Exception
	 */
	public HashMap<String, String> getSwffXmwhInfo(String pk) {

		List<HashMap<String, String>> list = dao.getSwffXmwhInfo(pk);
		HashMap<String, String> map = new HashMap<String, String>();
		if (list != null && list.size() > 0) {
			map = list.get(0);
			String ffrq = "";
			for (int i = 0; i < list.size(); i++) {
				ffrq += list.get(i).get("ffrq") + "!!@@!!";
			}
			map.put("ffrq", ffrq);
		}

		return map;
	}

	/**
	 * 获得发放对象列表信息（学生）
	 * 
	 * @author luojw
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * 
	 * @throws Exception
	 */
	public ArrayList<String[]> getXsFfdxList(RcswZjxyModel model, String rq)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		// 项目发放人群
		String[] ffrq = rq.split("!!@@!!");
		// 非班干部人群
		List<HashMap<String, String>> ffrqList = getSelectList("ffrq");
		// 干部列表
		List<String> gbList = new ArrayList<String>();

		// 删除非学生人群
		if (ffrq != null && ffrq.length > 0) {
			boolean flag = true;
			for (int i = 0; i < ffrq.length; i++) {
				for (int j = 0; j < ffrqList.size(); j++) {
					if (ffrq[i].equalsIgnoreCase(ffrqList.get(j).get("en"))) {
						flag = false;
					}
				}
				if (flag) {
					gbList.add(ffrq[i]);
				}
			}
		}

		// 班级学生列表
		ArrayList<String[]> bjxsList = null;

		// 学生干部列表
		List<HashMap<String, String>> xsgbList = getRcglList("sxjy_bjgbzlb",
				"bjgbmc", "bjgbmc", "", "", "");// 班干部
		// 干部列表
		List<String> allGbList = new ArrayList<String>();
		if (xsgbList != null && xsgbList.size() > 0) {
			for (int i = 0; i < xsgbList.size(); i++) {
				String gmmc = xsgbList.get(i).get("mc");
				allGbList.add(gmmc);
			}
		}

		// 判断发放人群是否有班级
		boolean isBj = false;

		// 干部学生列表
		ArrayList<String[]> gbxsList = null;

		if (ffrq != null && ffrq.length > 0) {
			for (int i = 0; i < ffrq.length; i++) {
				if ("班级".equalsIgnoreCase(ffrq[i])) {
					isBj = true;
					bjxsList = getBjxsList(model, allGbList);
					gbxsList = getGbxsList(model, allGbList);
				}
			}
		}

		// 干部学生列表(未设置班级)
		if (gbList != null && gbList.size() > 0 && !isBj) {
			gbxsList = getGbxsList(model, gbList);
		}

		// 学生列表
		ArrayList<String[]> xsList = new ArrayList<String[]>();
		if (bjxsList != null && bjxsList.size() > 0) {
			xsList.addAll(bjxsList);
		}
		if (gbxsList != null && gbxsList.size() > 0) {
			xsList.addAll(gbxsList);
		}

		// 分页
		Pages pages = model.getPages();
		int size = pages.getPageSize();
		int start = pages.getStart();
		int end = start + size;

		if (xsList != null && xsList.size() > 0) {
			pages.setMaxRecord(xsList.size());
		}

		// 最终列表
		ArrayList<String[]> list = new ArrayList<String[]>();
		if (xsList != null && xsList.size() > 0) {
			for (int i = start; i < end; i++) {
				if (xsList.size() > i) {
					list.add(xsList.get(i));
				}
			}
		}
		return list;
	}

	/**
	 * 获得发放对象列表信息（老师）
	 * 
	 * @author luojw
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * 
	 * @throws Exception
	 */
	public ArrayList<String[]> getLsFfdxList(RcswZjxyModel model, String rq)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		// 项目发放人群
		String[] ffrq = rq.split("!!@@!!");

		// 班主任列表
		ArrayList<String[]> bzrList = null;
		// 辅导员列表
		ArrayList<String[]> fdyList = null;

		if (ffrq != null && ffrq.length > 0) {
			for (int i = 0; i < ffrq.length; i++) {
				String zw = ffrq[i];
				if ("辅导员".equalsIgnoreCase(zw)) {
					fdyList = getFdyxxList(model);
				} else if ("班主任".equalsIgnoreCase(zw)) {
					bzrList = getBzrxxList(model);
				}
			}
		}

		// 老师列表
		ArrayList<String[]> lsList = new ArrayList<String[]>();
		if (fdyList != null && fdyList.size() > 0) {
			lsList.addAll(fdyList);
		}
		if (bzrList != null && bzrList.size() > 0) {
			lsList.addAll(bzrList);
		}

		// 分页
		Pages pages = model.getPages();
		int size = pages.getPageSize();
		int start = pages.getStart();
		int end = start + size;

		if (lsList != null && lsList.size() > 0) {
			pages.setMaxRecord(lsList.size());
		}

		// 最终列表
		ArrayList<String[]> list = new ArrayList<String[]>();
		if (lsList != null && lsList.size() > 0) {
			for (int i = start; i < end; i++) {
				if (lsList.size() > i) {
					list.add(lsList.get(i));
				}
			}
		}
		return list;
	}

	/**
	 * 获得发放对象列表信息（学生）
	 * 
	 * @author luojw
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public ArrayList<String[]> getXsFfdxList(RcswZjxyModel model)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		ArrayList<String[]> xsList = dao.getXsFfdxList(model);

		// 分页
		Pages pages = model.getPages();
		int size = pages.getPageSize();
		int start = pages.getStart();
		int end = start + size;

		if (xsList != null && xsList.size() > 0) {
			pages.setMaxRecord(xsList.size());
		}

		// 最终列表
		ArrayList<String[]> list = new ArrayList<String[]>();
		if (xsList != null && xsList.size() > 0) {
			for (int i = start; i < end; i++) {
				if (xsList.size() > i) {
					list.add(xsList.get(i));
				}
			}
		}

		return list;
	}

	/**
	 * 获得发放对象列表信息（老师）
	 * 
	 * @author luojw
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public ArrayList<String[]> getLsFfdxList(RcswZjxyModel model)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		ArrayList<String[]> xsList = dao.getLsFfdxList(model);

		// 分页
		Pages pages = model.getPages();
		int size = pages.getPageSize();
		int start = pages.getStart();
		int end = start + size;

		if (xsList != null && xsList.size() > 0) {
			pages.setMaxRecord(xsList.size());
		}

		// 最终列表
		ArrayList<String[]> list = new ArrayList<String[]>();
		if (xsList != null && xsList.size() > 0) {
			for (int i = start; i < end; i++) {
				if (xsList.size() > i) {
					list.add(xsList.get(i));
				}
			}
		}

		return list;
	}

	/**
	 * 获得班级学生
	 * 
	 * @author luojw
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * 
	 * @throws Exception
	 */
	public ArrayList<String[]> getBjxsList(RcswZjxyModel model,
			List<String> gbList) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		return dao.getBjxsList(model, gbList);
	}

	public ArrayList<String[]> getFfxsList(RcswZjxyModel model)
			throws Exception {
		return dao.getFfxsList(model);
	}

	/**
	 * 获得班干部学生
	 * 
	 * @author luojw
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * 
	 * @throws Exception
	 */
	public ArrayList<String[]> getGbxsList(RcswZjxyModel model,
			List<String> gbList) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		return dao.getGbxsList(model, gbList);
	}

	/**
	 * 获得辅导员信息列表
	 * 
	 * @author luojw
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * 
	 * @throws Exception
	 */
	public ArrayList<String[]> getFdyxxList(RcswZjxyModel model)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		return dao.getFdyxxList(model);
	}

	/**
	 * 获得班主任信息列表
	 * 
	 * @author luojw
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * 
	 * @throws Exception
	 */
	public ArrayList<String[]> getBzrxxList(RcswZjxyModel model)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		return dao.getBzrxxList(model);
	}

	/**
	 * 保存被发放者信息(特殊操作，暂时无法适用通用方法)
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public void saveBffzxx(RcswZjxyModel model) throws Exception {

		// 清空表信息
		delffzxx();

		String tableName = "rcsw_swffbffzb";
		String pk = "xhzgh||lx";
		String[] pkValue = model.getXhzgh();

		String[] arrzd = new String[] { "xhzgh" };
		String[] onezd = new String[] { "lx" };

		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(tableName);
		saveForm.setPk(pk);
		saveForm.setPkValue(pkValue);
		saveForm.setArrzd(arrzd);
		saveForm.setOnezd(onezd);

		saveRcsw(saveForm, model);

	}

	/**
	 * 删除被发放者信息
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public void delffzxx() throws Exception {
		dao.delffzxx();
	}

	/**
	 * 获得发放项目结果列表
	 * 
	 * @author luojw
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * 
	 * @throws Exception
	 */
	public ArrayList<String[]> getFfjgList(RcswZjxyModel model)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		// 发放人群列表
		/*
		 * List<HashMap<String, String>> gbList = getRcglList("sxjy_bjgbzlb",
		 * "bjgbmc", "bjgbmc", "", "", "");// 班干部 List<HashMap<String,
		 * String>> xmList = dao.getXmList(model);
		 */
		ArrayList<String[]> ffjgList = dao.getFfjgList(model);

		return ffjgList;
	}

	/**
	 * 实物发放名单生成(新闻)
	 * 
	 * @author luojw
	 * 
	 * @throws Exception
	 */
	/**
	 * 实物发放名单生成(新闻)
	 * 
	 * @author luojw
	 * @throws SQLException
	 * 
	 * @throws Exception
	 */
	public Boolean setSwffmd(String pk, String userName) throws SQLException {
		return dao.setSwffmd(pk, userName);
	}

	/**
	 * 根据职工号和学号 获取发放信息
	 * 
	 * @throws Exception
	 */
	public void getFfxxByZgh(HttpServletRequest request, RcswForm myForm)
			throws Exception {

		BasicService basicService = new BasicService();
		MakeQuery mQuery = new MakeQuery();

		// 查询字段
		String[] outPutStr = { "pkValue", "zgh", "xm", "xymc", "xn", "xqmc",
				"nd","xmlxmc", "xmmc", "ffsj","ffjssj", "mycd" };
		// 查询条件
		String[] colList = { "xn", "xq", "nd", "zgh", "ffsj", "xmlx" };
		// 模糊查询条件
		String[] colLikeList = { "zgh" ,"xmmc"};
		// 生成查询条件
		mQuery.makeQuery(colList, colLikeList, myForm);

		StringBuilder sql = new StringBuilder();
		StringBuilder query = new StringBuilder();
		query.append(getFfpjTj(myForm));
		query.append(" and isff='发放' ");
		sql.append(" select rownum r,a.* from(select distinct (a.xhzgh)zgh, a.xn||a.xq||a.nd||b.xmlx||a.ffsj||b.lx||a.xhzgh pkValue,a.xm,");
		sql.append(" (case when b.mycd='fcmy' then '非常满意' when b.mycd='my' then '满意' ");
		sql.append("  when b.mycd='bjmy' then '比较满意' when b.mycd='bmy' then '不满意' end)mycd,a.xymc, a.xn, a.xq, a.nd,b.xmlx, ");
		sql.append(" (select xqmc from xqdzb where a.xq=xqdm)xqmc, a.xmmc,a.xmlxmc,a.ffjssj, a.ffsj,case when b.xhzgh is null then '未发放' else '发放' end isff from view_xg_swff_ffryb a ");
		sql.append(" left join rcsw_swffrqwhb b on a.xn = b.xn and a.xq = b.xq ");
		sql.append(" and a.nd = b.nd and a.xmlx = b.xmlx and a.ffsj = b.ffsj and a.xhzgh = b.xhzgh)a ");
		
		// 查询结果
		System.out.println(sql.toString() + mQuery.getQueryString() + query);
		request.setAttribute("rs", CommonQueryDAO.commonQuery(sql.toString(),
				mQuery.getQueryString() + query, mQuery.getInputList(),
				outPutStr, myForm));
		// 显示表头
		request.setAttribute("topTr", basicService.getTopTr("mdqr_xmszb",
				new String[] { "学号", "姓名", Base.YXPZXY_KEY+"名称", "学年", "学期", "年度","项目类型", "项目名称",
						"办理开始时间","办理结束时间", "满意程度" }));
	}

	/**
	 * 获取实物发放统计信息
	 * 
	 * @throws Exception
	 */
	public void getFfxx(HttpServletRequest request, RcswForm myForm)
			throws Exception {

		BasicService basicService = new BasicService();
		MakeQuery mQuery = new MakeQuery();

		// 查询字段
		String[] outPutStr = { "pkValue", "zgh", "xm", "xymc", "xn", "xqmc",
				"nd", "xmmc", "ffsj", "mycd" };
		// 查询条件
		String[] colList = { "xn", "xq", "nd", "ffsj", "xmlx" };
		// 模糊查询条件
		String[] colLikeList = { "zgh", "xm" };
		// 生成查询条件
		mQuery.makeQuery(colList, colLikeList, myForm);
		
		StringBuilder sql = new StringBuilder();
		StringBuilder query = new StringBuilder();
		query.append(getFfpjTj(myForm));
		query.append(" and  mycd is not null ");
		sql.append(" select a.*,rownum r from  view_rcsw_swffrqwh a ");
		// 查询结果
		request.setAttribute("rs", CommonQueryDAO.commonQuery(sql.toString(),
				mQuery.getQueryString() + query, mQuery.getInputList(),
				outPutStr, myForm));
		// 显示表头
		request.setAttribute("topTr", basicService.getTopTr("mdqr_xmszb",
				new String[] { "职工号", "姓名", Base.YXPZXY_KEY+"名称", "学年", "学期", "年度", "项目名称",
						"发放时间", "满意程度" }));
	}

	/**
	 * 获取实物发放评价查询条件
	 * 
	 * @return
	 */
	public String getFfpjTj(RcswForm myForm) {
		StringBuilder sb = new StringBuilder();

		if (!"".equalsIgnoreCase(myForm.getKssj())) {
			sb.append(" and ffsj>= " + myForm.getKssj());
		}

		if (!"".equalsIgnoreCase(myForm.getJssj())) {
			sb.append(" and ffsj<= " + myForm.getJssj());
		}
		if (!"".equalsIgnoreCase(myForm.getFfjskssj())) {
			sb.append(" and ffjssj>= " + myForm.getFfjskssj());
		}

		if (!"".equalsIgnoreCase(myForm.getFfjsjssj())) {
			sb.append(" and ffjssj<= " + myForm.getFfjsjssj());
		}

		return sb.toString();
	}

	/**
	 * 设置批量评价时的值
	 * 
	 */
	public HashMap<String, String> setPlpj(RcswForm myForm) {
		HashMap<String, String> hashMap = new HashMap<String, String>();
		hashMap.put("mycd", myForm.getMycd());
		hashMap.put("pjyj", myForm.getPjyj());
		return hashMap;
	}

	/**
	 * 获取单条发放记录
	 */
	public HashMap<String, String> getOneFfjl(String pkValue) {
		return dao.getOneFfjl(pkValue);
	}

	/**
	 * 学生成绩统计打印
	 * 
	 * @return
	 * @throws Exception
	 */
	public void pjxxTj(WritableWorkbook wwb, RcswForm myForm) throws Exception {

		// 获取评价统计信息
		List<String[]> list = dao.getPjtjXx(myForm);

		try {
			// 创建xls中SHEET对象
			WritableSheet ws = wwb.getSheet(0);
			WritableCellFormat wcfTytle = new WritableCellFormat();
			
			// 设置对齐方式
			Alignment alignMent = Alignment.CENTRE;
			VerticalAlignment vag = VerticalAlignment.CENTRE;

			wcfTytle.setVerticalAlignment(vag);
			wcfTytle.setAlignment(alignMent);

			WritableFont wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setBoldStyle(WritableFont.BOLD);

			wcfTytle = new WritableCellFormat();
			alignMent = Alignment.LEFT;
			vag = VerticalAlignment.CENTRE;

			wcfTytle.setVerticalAlignment(vag);
			wcfTytle.setAlignment(alignMent);
			wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);

			wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setBoldStyle(WritableFont.BOLD);
			wfTytle.setPointSize(12);
			wcfTytle.setFont(wfTytle);
			
			//标题单元格合并
			ws.mergeCells(0, 0, 11, 0);
			//标题
			ws.addCell(new Label(0,0,"                               			      			 实物发放评价统计               ",
						wcfTytle));
			wcfTytle = new WritableCellFormat();
			alignMent = Alignment.CENTRE;
			vag = VerticalAlignment.CENTRE;

			wcfTytle.setVerticalAlignment(vag);
			wcfTytle.setAlignment(alignMent);
			wcfTytle.setWrap(true);
			// 设置表格边框
			wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);

			wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setPointSize(10);
			wcfTytle.setFont(wfTytle);
			
			//表头
			ws.addCell(new Label(0, 1, "项目类型", wcfTytle));
			ws.addCell(new Label(1, 1, "项目名称", wcfTytle));
			ws.addCell(new Label(2, 1, "学年", wcfTytle));
			ws.addCell(new Label(3, 1, "学期", wcfTytle));
			ws.addCell(new Label(4, 1, "年度", wcfTytle));
			ws.addCell(new Label(5, 1, "办理开始时间", wcfTytle));
			ws.addCell(new Label(6, 1, "办理结束时间", wcfTytle));
			ws.addCell(new Label(7, 1, "非常满意", wcfTytle));
			ws.addCell(new Label(8, 1, "比较满意", wcfTytle));
			ws.addCell(new Label(9, 1, "满意", wcfTytle));
			ws.addCell(new Label(10, 1, "不满意", wcfTytle));
			ws.addCell(new Label(11, 1, "未评价", wcfTytle));
			
			//学生评价统计信息
			for (int j = 0; j < list.size(); j++) {
				String[] tjxxArr = list.get(j);
				for(int i=0;i<tjxxArr.length;i++){
					ws.addCell(new Label(i, j + 2, tjxxArr[i], wcfTytle));
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 向客户端输出
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}

	/**
	 * 实物发放领取通知
	 * 
	 * @param myForm
	 * @return String
	 * @throws Exception
	 */
	public String saveLqtz(RcswForm myForm) throws Exception {

		// 将需要发送通知的用户群 添加到 实物发放领取通知表
		dao.saveLqtzInfo(myForm);
		// 发送短信 获取短信发送结果
		return sendMessage(dao.getLqtzByqr(myForm), myForm.getUserName(),myForm);
	}

	public String getQuery(RcswForm myForm) {
		StringBuilder sb = new StringBuilder();
		if (!Base.isNull(myForm.getXn())) {
			sb.append(" and xn='" + myForm.getXn() + "' ");
		}

		if (!Base.isNull(myForm.getXq())) {
			sb.append(" and xq='" + myForm.getXq() + "' ");
		}

		if (!Base.isNull(myForm.getNd())) {
			sb.append(" and nd='" + myForm.getNd() + "' ");
		}

		if (!Base.isNull(myForm.getXmlx())) {
			sb.append(" and xmlx='" + myForm.getXmlx() + "' ");
		}

		if (!Base.isNull(myForm.getFfsj())) {
			sb.append(" and ffsj='" + myForm.getFfsj() + "' ");
		}
		return sb.toString();
	}

	public String getPassWord() {
		HashMap<String, String> passMap = dao.getPassWord();
		String passWord = passMap.get("gxtmy");
		return passWord;
	}

	/**
	 * 发送短信息 method sendMessage
	 * 
	 * @param dxtzList
	 *            author qlj
	 * @throws Exception
	 */
	public String sendMessage(List<HashMap<String, String>> dxtzList,
			String userName,RcswForm myForm) throws Exception {

		GxtWebService service = new GxtWebService();
		String[] sendResult = null;
		if (dxtzList != null) {
			sendResult = new String[dxtzList.size()];
		}
		for (int i = 0; i < dxtzList.size(); i++) {

			StringBuilder msg = new StringBuilder();

			HashMap<String, String> dxtzMap = dxtzList.get(i);
			SendMess sendMess = new SendMess();
			sendMess.setPassWord(getPassWord());
			// 学号
			sendMess.setMps(dxtzMap.get("xhzgh"));
			// 登陆名
			sendMess.setLoginName(userName);
			// 唯一标识
			sendMess.setUserDefId(Long.parseLong(dxtzMap.get("userdefid")));
			
			String ffsj=dxtzMap.get("ffsj").substring(0,4)+"年"+dxtzMap.get("ffsj").substring(4,6)+"月"+dxtzMap.get("ffsj").substring(6,8)+"日";
			String ffjssj=dxtzMap.get("ffjssj").substring(0,4)+"年"+dxtzMap.get("ffjssj").substring(4,6)+"月"+dxtzMap.get("ffjssj").substring(6,8)+"日";
			
			msg.append(dxtzMap.get("xm") + "同学您好！");
			msg.append("请于" + ffsj);
			if(!ffsj.equalsIgnoreCase(ffjssj)){
				msg.append("至"+ffjssj);
			}else{
				msg.append("(仅限当天)");
			}
			
			// 发送消息拼接
			
			msg.append("前往" + dxtzMap.get("ffdd")
					+ "领取“" + dxtzMap.get("xmmc")+"”。");
			sendMess.setSendTime(dxtzMap.get("dxtzsj"));
			sendMess.setSmsMsg(msg.toString());
			
			// 只支持单条学生信息的发送 返回一个回执状态
			String result=service.sendSMS(sendMess);
			if(result==null){
				dao.deleteLqtz(myForm);
				return "false";
			}else if(!"0".equalsIgnoreCase(result)){
				dao.deleteLqtz(myForm);
				return "false";
			}
		}
		
		// 修改是否通知的状态
		dao.updateSftz();
//		getMsg(sendResult)
		return "true";
	}

	/**
	 * 获取发送短信息的结果 method getMsg
	 * 
	 * @param sendResult
	 *  author qlj
	 */
	public String getMsg(String[] sendResult) {

		// 输出说明
		String hzlb = "scsm";
		// 获取输出说明列表
		List<HashMap<String, String>> hzList = dao.getDxtzHzxx(hzlb);
		List<HashMap<String, String>> msgList = new ArrayList<HashMap<String, String>>();
		StringBuilder msg = new StringBuilder();
		int tjsl = 0;
		for (int i = 0; i < hzList.size(); i++) {
			HashMap<String, String> hzMap = hzList.get(i);
			for (int j = 0; j < sendResult.length; j++) {
				if (sendResult[j].equalsIgnoreCase(hzMap.get("hzdm"))) {
					tjsl = Integer.parseInt(hzMap.get("tjsl")) + 1;
					hzMap.put("tjsl", "" + tjsl);
				}
			}
			tjsl = 0;
			msgList.add(hzMap);
		}

		for (int i = 0; i < msgList.size(); i++) {
			HashMap<String, String> hzMap = hzList.get(i);
			msg.append(hzMap.get("hznr") + ":" + hzMap.get("tjsl") + "条记录");
		}

		return msg.toString();
	}

	public void getDxhz(RcswForm myForm) throws SQLException {

		GxtWebService service = new GxtWebService();

		List<HashMap<String, String>> txtzList = dao.getTxtzFhzt(myForm);
		List<HashMap<String, String>> txList = new ArrayList<HashMap<String, String>>();
		// 高校通密钥
		String passWord = getPassWord();
		int dxhz = 0;
		for (int i = 0; i < txtzList.size(); i++) {
			HashMap<String, String> txtzMap = txtzList.get(i);
			HashMap<String, String> txMap = new HashMap<String, String>();
			SendMess sendMess = new SendMess();
			// 连接秘钥
			sendMess.setPassWord(passWord);
			// 登陆名
			sendMess.setLoginName(myForm.getUserName());
			// 学号
			sendMess.setMps(txtzMap.get("xhzgh"));
			// 唯一标识
			sendMess.setUserDefId(Long.parseLong(txtzMap.get("userdefid")));

			// 获取短信回执
			dxhz = service.receiveRPT(sendMess);

			txMap.put("userdefid", txtzMap.get("userdefid"));
			txMap.put("fhzt", "" + dxhz);
			txList.add(txMap);
		}

		dao.updateFhzt(txList);
	}

	/**
	 * 获取人员发放统计信息
	 * @param myForm
	 * @return List<String[]>
	 * @throws IllegalArgumentException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 * author qlj
	 */
	public List<String[]> getFfryTjxx(RcswForm myForm)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		return dao.getFfryTjxx(myForm);
	}

	/**
	 * 获取领取次数列表
	 * @return List<HashMap<String,String>> 
	 * author qlj
	 */
	public List<HashMap<String, String>> getLqcsList() {
		return dao.getLqcsList();
	}

	/**
	 * 获取发放次数列表 
	 * @return List<HashMap<String,String>> 
	 * author qlj
	 */
	public List<HashMap<String, String>> getFfcsList() {
		return dao.getFfcsList();
	}
	
	public List<HashMap<String,String>>getXmmcList(){
		return dao.getXmmcList();
	}
	
	public ArrayList<String[]> getYffry(RcswForm myForm,HttpServletRequest request) throws Exception{
		return dao.getYffry(myForm, request);
	}
	
	/**
	 * 以项目为主体的事物办理（发放）
	 * @param request
	 * @param myForm
	 * @throws Exception
	 */
	public void saveYffry(HttpServletRequest request, RcswForm myForm)
		throws Exception {
		GhxyNjzrwhService ghxyNjzrwhService = new GhxyNjzrwhService();
	
		// 进行数据操作 的表名
		String realTable = "rcsw_swffrqwhb";
	
		//主键值
		String[] pkValue = myForm.getCheckVal();
		//项目类型
		String xmlx = myForm.getXmlx();
		//学年
		String xn = myForm.getXn();
		//学期
		String xq= myForm.getXq();
		//年度
		String nd=myForm.getNd();
		//发放时间
		String ffsj=myForm.getFfsj();
		//发放备注信息
		String ffbz=myForm.getFfbz();
		//发放人
		String ffr=myForm.getFfr();
		//发放类型（学生）
		String lx="xs";
		
		
		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(realTable);
		
		//单个字段
		String []onezd={"xn","xq","nd","xmlx","ffsj","lx","ffbz","ffr"};
		//批量字段
		String []arrzd={"xhzgh"};
		//主键
		String pk="xn||xq||nd||xmlx||ffsj";
		saveForm.setOnezd(onezd);
		saveForm.setArrzd(arrzd);
	
		RcswZjxyModel model = new RcswZjxyModel();
		model.setFfsj(ffsj);
		model.setXmlx(xmlx);
		model.setXn(xn);
		model.setXq(xq);
		model.setNd(nd);
		model.setLx(lx);
		model.setFfbz(ffbz);
		model.setXhzgh(pkValue);
		model.setFfr(ffr);
		saveForm.setPk(pk);
		saveForm.setPkValue(pkValue);
		
		boolean reslut = ghxyNjzrwhService.saveTyxx(saveForm, model);
		request.setAttribute("result", reslut);
	}
	
	
	/**
	 * 以学生为主体的事物办理（发放）
	 * @param request
	 * @param myForm
	 * @throws Exception
	 */
	public boolean  saveFfxm(RcswForm myForm)
		throws Exception {
		return dao.saveFfxm(myForm);
	}
	
	
	public HashMap<String,String> getSwffxm(String pkValue){
		return dao.getSwffxm(pkValue);
	}
	
	public List<String[]>getXsFfxm(RcswForm myForm) throws Exception{
		return dao.getXsFfxm(myForm);
	}
	
	public List<String[]>getPjtjXx(RcswForm myForm)throws Exception{
		
		return dao.getPjtjXx(myForm);
	}
	
	public String saveDxtz(RcswForm myForm) throws Exception{

		String[]xhzgh=myForm.getXhzgh();
		String[]sftz=myForm.getSftz();
		
		int m=0;
		for(int i=0;i<sftz.length;i++){
			if("是".equalsIgnoreCase(sftz[i])){
				m++;
			}
		}
		String []xh=new String[m];
		m=0;
		for(int i=0;i<xhzgh.length;i++){
			if("是".equalsIgnoreCase(sftz[i])){
				xh[m]=xhzgh[i];
				m++;
			}
		}
		myForm.setTzdxxh(xh);
		dao.saveLqtzInfo(myForm);
		return sendMessage(dao.getLqtzry(myForm), myForm.getUserName(),myForm);
	}
	
	/**
	 * 获取学号(根据身份证号)
	 * @param sfzh
	 * @return
	 */
	public HashMap<String, String> getXhBySfzh(String sfzh) {
		
		return dao.getXhBySfzh(sfzh);
	}
	
	/**
	 * 事物办理,办理结果查询
	 * @return  List<String[]>
	 * @throws Exception 
	 */
	public ArrayList<String[]>getFfxxList(RcswForm model) throws Exception{
		
		return (ArrayList<String[]>)dao.getFfxxList(model);
	}
	
	public List<String[]>getTjxxOne(RcswForm model)throws Exception{
		
		return dao.getTjxxOne(model);
	}
}
