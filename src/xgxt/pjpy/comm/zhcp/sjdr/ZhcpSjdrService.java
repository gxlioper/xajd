package xgxt.pjpy.comm.zhcp.sjdr;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import xgxt.action.Base;
import xgxt.comm.SearchRsModel;
import xgxt.comm.impexp.CommImpExpService;
import xgxt.form.User;
import xgxt.pjpy.comm.pjpy.PjpyCommService;
import xgxt.pjpy.comm.zhcp.jbsz.ZhcpJbszForm;
import xgxt.pjpy.comm.zhcp.jbsz.ZhcpJbszService;
import xgxt.utils.CommonQueryDAO;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 综合素质测评_数据导入_Service类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author 伟大的骆
 * @version 1.0
 */

public class ZhcpSjdrService extends PjpyCommService {
	

	ZhcpSjdrDAO dao = new ZhcpSjdrDAO();

	/**
	 * 获得初始化项目列表
	 * 
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getCshXmList(ZhcpSjdrForm model) {
		
		ZhcpJbszForm jbszModel = new ZhcpJbszForm();
		ZhcpJbszService service = new ZhcpJbszService();

		// 三级项目列表
		jbszModel.setXmjb("3");
		
		List<HashMap<String, String>> list = service.getZctreeList(jbszModel);

		return list;
	}

	/**
	 * 获得项目列表
	 * 
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getXmList(ZhcpSjdrForm model) {
		
		// 操作项目
		String czxm = model.getCzxm();
		czxm = Base.isNull(czxm) ? "pdjbf" : czxm;

		List<HashMap<String, String>> xmList = null;
		if ("pdjbf".equalsIgnoreCase(czxm)) {// 品德基本分
			xmList = getPdhpXmList(model);
		}

		return xmList;
	}

	/**
	 * 获得品德互评项目列表
	 * 
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getPdhpXmList(ZhcpSjdrForm model) {

		ZhcpJbszForm jbszModel = ZhcpJbszForm.zcjbszModel;

		String xn = jbszModel.getPjxn();
		String xq = jbszModel.getPjxq();
		String nd = jbszModel.getPjnd();

		// 品德互评项目表s
		String tableName = "xg_pjpy_pdbxhpxmb";
		// 过滤条件
		StringBuilder query = new StringBuilder(" where 1 = 1 ");
		query.append(" and xn = ? ");
		query.append(" and xq = ? ");
		query.append(" and nd = ? ");
		query.append(" order by xmdm ");
		// 输入值
		String[] inPutList = new String[] { xn, xq, nd };
		// 输出值
		String[] colList = new String[] { "xmdm", "xmmc" };

		return getRsList(tableName, query.toString(), inPutList, colList, "");
	}
	
	/**
	 * 获得品德互评列表
	 * 
	 * @author 伟大的骆
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public ArrayList<String[]> getPdhpInfoList(ZhcpSjdrForm model, User user,
			List<HashMap<String, String>> xmList)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		
		return dao.getPdhpInfoList(model, user, xmList);
	}
	
	/**
	 * 获得综合分维护列表
	 * 
	 * @author 伟大的骆
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public ArrayList<String[]> getZhfwhList(ZhcpSjdrForm model, User user)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		
		return dao.getZhfwhList(model, user);
	}
	
	/**
	 * 获得特殊Html
	 * 
	 * @author 伟大的骆
	 */
	public String getSpHtml(SearchRsModel rsModel, ZhcpSjdrForm model,
			ArrayList<String[]> rsArrList) {

		// 操作项目
		String czxm = model.getCzxm();
		// IE版本
		String ie = rsModel.getIe();
		// 来源表
		String lyb = model.getLyb();
		
		StringBuilder spHtml = new StringBuilder();

		if (rsArrList != null && rsArrList.size() > 0) {

			for (int i = 0; i < rsArrList.size(); i++) {

				String[] rs = rsArrList.get(i);
				spHtml.append("<tr onclick=\"rowOnClick(this);\" ondblclick=\"\">");

//				spHtml.append("<td align=\"center\" width=\"5px\">");
//				spHtml.append("<input type=\"checkbox\" name=\"primarykey_checkVal\" ");
//				spHtml.append("value=\"" + rs[0] + "\"/>");
//				spHtml.append("</td>");

				for (int j = 1; j < rs.length-2; j++) {
					spHtml.append("<td align=\"center\" nowrap=\"nowrap\"> ");
					
					spHtml.append(rs[j]);
					spHtml.append("</td>");
				}
				
				spHtml.append("<td align=\"center\" nowrap=\"nowrap\" ");
				// 处理IE8的情况
				if ("5.8".equalsIgnoreCase(ie)) {
					if("noLyb".equalsIgnoreCase(lyb)){
						spHtml.append("height=\"28.5px\"");
					}else{
						spHtml.append("height=\"29.5px\"");
					}
				} else {
					if("noLyb".equalsIgnoreCase(lyb)){
						//spHtml.append("height=\"28.5px\"");
					}else{
						spHtml.append("height=\"30px\"");
					}
				}
				spHtml.append(">");
				
				if("noLyb".equalsIgnoreCase(lyb)){
					spHtml.append("<input type=\"text\" onblur=\"setEditValue();chMax(this,100);checkInputNum(this);\"");
					spHtml.append("style=\"width:60px\" maxlength=\"5\" ");
					spHtml.append(" name=\"" + ("czxm_" + czxm) + "\"");
					spHtml.append(" id=\"" + ("czxm_" + czxm + "_" + rs[0]) + "\"");
					spHtml.append(" value=\"" + rs[rs.length-2] + "\"");
					spHtml.append("/>");
				}else{
					spHtml.append(rs[rs.length-2]);
				}
				spHtml.append("</td>");
				
				spHtml.append("</tr>");
			}
		}
		
		return spHtml.toString();
	}
	
	/**
	 * 保存综合素质相关分数
	 * 
	 * @author 伟大的骆
	 * @throws Exception 
	 */
	public Boolean saveZhcpfXgInfo(ZhcpSjdrForm model, User user)
			throws Exception {
		return dao.saveZhcpfXgInfo(model, user);
	}
	
	/**
	 * 同步综合素质相关分数
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public Boolean tbZhcpfXgInfo(ZhcpSjdrForm model, User user) {
		return dao.tbZhcpfXgInfo(model, user);
	}

	/**
	 * 获得需导出部门信息
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getExpBmInfo(ZhcpSjdrForm model) {
		return dao.getExpBmInfo(model);
	}
	
	/**
	 * 导出综测分模板
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public boolean expZcfmbToExcel(ZhcpSjdrForm model, User user) throws Exception {
		
		CommImpExpService ioService = new CommImpExpService();
		
		ZhcpJbszForm jbszModel = ZhcpJbszForm.zcjbszModel;
		
		String sheetName = "";
		
		// 部门列表
		List<HashMap<String, String>> bmList = model.getBmList();
		// 综测周期
		String zczq = model.getZczq();
		// 导出形式
		String dcxs = model.getDcxs();
		// 操作项目
		String czxm = model.getCzxm();
		// 学年
		String xn = jbszModel.getPjxn();
		// 学期
		String xq = jbszModel.getPjxq();
		// 年度
		String nd = jbszModel.getPjnd();
		
		String xmmc = getOneValue("xg_pjpy_zcxmb", "xmmc", "xn||xq||nd||xmdm", xn+xq+nd+czxm);
		// =======================表头============================
		List<String> topName = new ArrayList<String>();
		if("xn".equalsIgnoreCase(zczq)){//学年
			topName.add("学年");
		}else if("xq".equalsIgnoreCase(zczq)){//学期
			topName.add("学年");
			topName.add("学期");
		}else{//年度
			topName.add("年度");
		}
		
		// 固定列
		String[] gdName = new String[] { "学号", "姓名", "性别", "年级",Base.YXPZXY_KEY, "专业",
				"班级", xmmc + "_(请导入数字，否则可能导致系统异常)" };

		for (int i = 0; i < gdName.length; i++) {
			topName.add(gdName[i]);
		}
		// =======================表头 end============================
		
		// 导出报表表头
		List<HashMap<String, String>> topTr = getTopList(topName
				.toArray(new String[] {}));
		List<String[]> fileList = new ArrayList<String[]>();
		
		for (int i = 0; i < bmList.size(); i++) {
			
			HashMap<String, String> map = bmList.get(i);
			
			String bmdm = map.get("bmdm");
			String fileName = map.get("bmmc") + "_" + map.get("bmdm");
			
			if ("xy".equalsIgnoreCase(dcxs)){
				model.setXydm(bmdm);
			}else{
				model.setBjdm(bmdm);
			}
			// 最终导出数据
			List<String[]> list = dao.getZhfxsList(model, user);
		
			ioService.createXLS(sheetName, model.getFilePath(), fileName, topTr, list);
			fileList.add(new String[]{fileName,bmdm,dcxs,user.getUserName()});
		}
		
		return dao.saveStencilLog(fileList);
	}
	
	
	/**
	 * 导入数据
	 * 
	 * @author luo
	 * @throws Exception
	 */
	public String impZcfInfo(ZhcpSjdrForm model, String filePath,User user) throws Exception {

		File file = new File(filePath);
		
		String message = "";
		
		boolean flag = false;
		
		// 导入数据
		if (!Base.isNull(filePath)) {

			Workbook book = Workbook.getWorkbook(file);
			// 获得第一个工作表对象
			Sheet sheet = book.getSheet(0);
			// 导入list
			List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
			// 行
			int rows = sheet.getRows();
			// 列
			int column = sheet.getColumns() + 1;
			// 表头
			String[] bt = new String[column];

			//项目名称
			String xmmc = "";
			
			// 循环行
			for (int i = 0; i < rows; i++) {
				// 第一行标题，不算
				if (i != 0) {
					HashMap<String, String> map = new HashMap<String, String>();
					// 循环列
					for (int j = 0; j < column; j++) {

						if (j == column - 1) {

							Cell cell = sheet.getCell(j - 1, i);
							String nr = cell.getContents();

							map.put("分数", nr);
						} else {
							Cell cell = sheet.getCell(j, i);
							String nr = cell.getContents();

							map.put(bt[j], nr);
						}
					}
					list.add(map);
				} else {
					for (int j = 0; j < column; j++) {

						if (j == column - 1) {
							Cell cell = sheet.getCell(j - 1, i);
							String nr = cell.getContents();
							bt[j] = nr;
							xmmc = nr.split("_")[0];
						} else {
							Cell cell = sheet.getCell(j, i);
							String nr = cell.getContents();
							bt[j] = nr;
						}
					}
				}
			}
			
			ZhcpJbszForm jbszModel = ZhcpJbszForm.zcjbszModel;
			
			String sheetName = "";
			// 综测周期
			String zczq = model.getZczq();
			
			// 学年
			String xn = jbszModel.getPjxn();
			// 学期
			String xq = jbszModel.getPjxq();
			// 年度
			String nd = jbszModel.getPjnd();
			// 主键
			String pkValue = xn + xq + nd + xmmc;
			// 操作项目
			String czxm = getOneValue("xg_pjpy_zcxmb", "xmdm",
					"xn||xq||nd||xmmc", pkValue);
			
			ArrayList<String> xnList = new ArrayList<String>();
			ArrayList<String> xqList = new ArrayList<String>();
			ArrayList<String> ndList = new ArrayList<String>();
			ArrayList<String> xhList = new ArrayList<String>();
			ArrayList<String> fsList = new ArrayList<String>();
			
			// 循环内容
			for (int i = 0; i < list.size(); i++) {
				HashMap<String, String> map = list.get(i);
				xnList.add(Base.isNull(map.get("学年")) ? "无" : map.get("学年"));
				xqList.add(Base.isNull(map.get("学期")) ? "无" : map.get("学期"));
				ndList.add(Base.isNull(map.get("年度")) ? "无" : map.get("年度"));
				xhList.add(map.get("学号"));
				fsList.add(map.get("分数"));
			}
			
			StringBuilder sql = new StringBuilder();
			sql.append("update xg_pjpy_zhcpb ");
			sql.append("set " + czxm + "=? ");
			sql.append("where 1 = 1 ");
			sql.append("and xn=? ");
			sql.append("and xq=? ");
			sql.append("and nd=? ");
			sql.append("and xh=? ");
			
			List<String[]> params = new ArrayList<String[]>();
			
			if (xnList != null && xnList.size() > 0) {
				for (int i = 0; i < xnList.size(); i++) {
					String[] value = new String[] { fsList.get(i),
							xnList.get(i), xqList.get(i), ndList.get(i),
							xhList.get(i) };
					
					params.add(value);
				}
			}
			
			flag = saveArrDate(sql.toString(), params, "xg_pjpy_zhcpb", user);
			
			file.delete();
		}

		return message;
	}
	
	
	
	/**
	 * 生成模版的记录
	 * @param user
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getStencilLog(User user,ZhcpSjdrForm model) throws Exception{
		
		StringBuilder sql = new StringBuilder();
		String[] queryArr = new String[]{"bmlx"};
		String[] queryLikeArr = new String[]{"wjmc"};
		String[] colList = new String[]{"wjmc","cjsj"};
		Map<String,Object>  map = CommonQueryDAO.getQuerySQL(model, queryArr, queryLikeArr);
		
		sql.append("select a.wjmc,a.cjsj,rownum r from pjpy_zhcp_mmccb a where 1=1 ");
		
		if (Boolean.parseBoolean(user.getIsFdy())){
			sql.append(" and exists (select 1 from fdybjb b where a.bmdm=b.bjdm)");
		} else if ("xy".equalsIgnoreCase(user.getUserType())){
			sql.append(" and a.bmdm='")
			   .append(user.getUserDep())
			   .append("'");
		}
		sql.append(map.get("sql"));
		return CommonQueryDAO.commonPageQuery(model.getPages(), sql.toString(), (String[])map.get("input"), colList);
	}
	
	
}