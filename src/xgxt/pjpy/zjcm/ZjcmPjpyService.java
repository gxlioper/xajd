package xgxt.pjpy.zjcm;

import java.util.ArrayList;
import java.util.List;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.math.RoundingMode;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.jaxen.function.RoundFunction;

import common.Globals;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCell;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import xgxt.DAO.DAO;
import xgxt.DAO.ExcelMB;
import xgxt.action.Base;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.form.SaveForm;
import xgxt.pjpy.PjpyTyForm;
import xgxt.pjpy.PjpyTyService;
import xgxt.pjpy.zjcm.xfjs.PjpyXfjsService;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.ExcelMethods;
import xgxt.utils.MakeQuery;
import xgxt.utils.String.StringUtils;
import xgxt.utils.date.MoneyFormat;
import xgxt.wjcf.zjcm.WjcfZjcmDAO;
import xgxt.wjcf.zjcm.WjcfZjcmModel;

public class ZjcmPjpyService extends PjpyTyService {

	ZjcmPjpyDAO dao = new ZjcmPjpyDAO();
	
	/**
	 * 获得个性化表头
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getTopTr(String lx) {
		DAO dao = DAO.getInstance();
		String[] colListCN = new String[] { "学号", "姓名", "性别", "年级", "班级",
				"评奖学年", "评奖学期", "德育分/折算分", "智育分/折算分", "体育分/折算分", "能力分/折算分",
				"综合分", "旷课节数" };
		String[] colListEN = new String[] { "xh", "xm", "xb", "nj", "bjmc",
				"xn", "xq", "dyf", "zyf", "tyf", "nlf", "zhf", "kkjs" };

		if ("jxjxn".equalsIgnoreCase(lx)) {
			colListCN = new String[] { "学号", "姓名", "性别", "德育折算分", "德育排名",
					"智育折算分", "智育排名", "体育折算分", "体育排名", "能力折算分", "能力排名", "综合分",
					"综合排名" };
			colListEN = new String[] { "xh", "xm", "xb", "dyzhf", "dypm",
					"zyzhf", "zypm", "tyzhf", "typm", "nlzhf", "nlpm", "zhf",
					"zhpm" };
		}
		return dao.arrayToList(colListEN, colListCN);
	}
	
	/**
	 * 获得旷课情况列表
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getKkList(ZjcmPjpyModel model)
			throws Exception {

		PjpyXfjsService xfService = new PjpyXfjsService();

		String xn = model.getXn();
		String xq = model.getXq();

		List<HashMap<String, String>> kkList = xfService.getXskqqk("", xn, xq,
				"xy");
		
		String tableName = "VIEW_ZJCM_PJPY_KKCSCX";
		
		String[] colList = new String[] { "xh", "kkcs" };

		StringBuilder query = new StringBuilder();
		query.append(" where xn = '" + xn + "'");
		query.append(" and xq = '" + xq + "'");
		
		List<HashMap<String, String>> whList = CommonQueryDAO
				.commonQueryforList(tableName, query.toString(),
						new String[] {}, colList, "");

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		if (whList != null && whList.size() > 0) {
			for (int i = 0; i < whList.size(); i++) {
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("xh", whList.get(i).get("xh"));
				map.put("kkjs", whList.get(i).get("kkcs"));
				list.add(map);
			}
		}
		
		if (kkList != null && kkList.size() > 0) {
			
			for (int i = 0; i < kkList.size(); i++) {
				
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("xh", kkList.get(i).get("xh"));
				map.put("kkjs", kkList.get(i).get("kkjs"));
				
				String kkxh = kkList.get(i).get("xh");
				
				boolean flag = true;
				
				for (int j = 0; j < list.size(); j++) {

					String xh = list.get(j).get("xh");
					
					if(xh.equalsIgnoreCase(kkxh)){
						flag = false;
					}
				}
				
				if(flag){
					list.add(map);
				}
			}
		}
		
		return list;
	}
	
	/**
	 * 获得评奖评优(综合分)列表
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public ArrayList<String[]> getZhfList(ZjcmPjpyModel model) throws Exception {

		String[] colList = new String[] { "xh", "xm", "xb", "nj", "bjmc", "xn",
				"xqmc", "dyf", "dyzhf", "zyf", "zyzhf", "tyf", "tyzhf", "nlf",
				"nlzhf", "zhf" };
		
		String table = getZhfTable(model);
		ArrayList<String[]> list = new ArrayList<String[]>();
		ArrayList<String[]> zhfList  = new ArrayList<String[]>();
		ArrayList<String[]> zhfNewList = dao.getZhfList(table, model, colList);
		
		if (zhfNewList != null && zhfNewList.size() > 0) {
			
			for (int i = 0; i < zhfNewList.size(); i++) {
				
				String[] zhfInfo = zhfNewList.get(i);
				
				String dyzhf = dao.setNumZero(zhfInfo[8]);
				String zyzhf = dao.setNumZero(zhfInfo[10]);
				String tyzhf = dao.setNumZero(zhfInfo[12]);
				String nlzhf = dao.setNumZero(zhfInfo[14]);
				String zhf = dao.setNumZero(zhfInfo[15]);
				
				zhfInfo[8] = dyzhf;
				zhfInfo[10] = zyzhf;
				zhfInfo[12] = tyzhf;
				zhfInfo[14] = nlzhf;
				zhfInfo[15] = zhf;
				
				zhfList.add(zhfInfo);
			}
		}
		
		//List<HashMap<String, String>> kkList = xfService.getXskqqk("", xn, xq, "xy");
		List<HashMap<String, String>> kkList = getKkList(model);

		if (zhfList != null && zhfList.size() > 0) {
			for (int i = 0; i < zhfList.size(); i++) {
				// 重构结果集，使综合分列表增加一列值（旷课节数）
				String[] rs = new String[zhfList.get(i).length + 1];
				for (int k = 0; k < zhfList.get(i).length; k++) {
					rs[k] = zhfList.get(i)[k];
				}
				rs[zhfList.get(i).length] = "无";
				String xh = rs[0];
				if (!Base.isNull(xh)) {
					if (kkList != null && kkList.size() != 0) {
						for (int j = 0; j < kkList.size(); j++) {
							HashMap<String, String> map = kkList.get(j);
							// 旷课者学号
							String kkxh = map.get("xh");
							// 旷课节数
							String kkjs = map.get("kkjs");
							// 判断分数是否在区间内
							if (xh.equalsIgnoreCase(kkxh)) {
								rs[zhfList.get(i).length] = kkjs;
							}
						}
					}
				}
				list.add(rs);
			}	
		}
		return list;
	}
	
	/**
	 * 获得综合素质分个项比例
	 * 
	 * @author luojw
	 */
	public HashMap<String, String> getZhfBl(ZjcmPjpyModel model,
			String[] colList) {
		return dao.getZhfBl(model, colList);
	}
	
	/**
	 * 获得综合素质分Table
	 * 
	 * @author luojw
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public String getZhfTable(ZjcmPjpyModel model)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		
		// 获得各个分值比例
		String[] bl = new String[] { "dyfbl", "zyfbl", "tyfbl", "nlfbl" };
		HashMap<String, String> map = getZhfBl(model, bl);

		String xn = model.getXn();
		String xq = model.getXq();
		String dyfbl = map.get("dyfbl");
		String zyfbl = map.get("zyfbl");
		String tyfbl = map.get("tyfbl");
		String nlfbl = map.get("nlfbl");
		
		String[] queryList = new String[] { "xydm", "zydm", "bjdm", "nj", "xn",
				"nd", "xq" };
		String[] queryLikeList = new String[] { "xh", "xm" };
		
		MakeQuery myQuery = new MakeQuery();
		myQuery.makeQuery(queryList, queryLikeList, model);
		String query = myQuery.getQueryString();
		
		StringBuffer sql = new StringBuffer();
		sql.append("(select xh, xm, xb, nj, xydm, xymc, zydm, zymc, bjdm, bjmc, xn, xq, xqmc, dyf, zyf, tyf, nlf,");
		sql.append("nvl(case when instr(to_char(round(dyzhf, 2)),'.',1,1)=1 then '0'||to_char(round(dyzhf, 2)) else to_char(round(dyzhf, 2)) end, '0') dyzhf, ");
		sql.append("nvl(case when instr(to_char(round(zyzhf, 2)),'.',1,1)=1 then '0'||to_char(round(zyzhf, 2)) else to_char(round(zyzhf, 2)) end, '0') zyzhf, ");
		sql.append("nvl(case when instr(to_char(round(tyzhf, 2)),'.',1,1)=1 then '0'||to_char(round(tyzhf, 2)) else to_char(round(tyzhf, 2)) end, '0') tyzhf, ");
		sql.append("nvl(case when instr(to_char(round(nlzhf, 2)),'.',1,1)=1 then '0'||to_char(round(nlzhf, 2)) else to_char(round(nlzhf, 2)) end, '0') nlzhf, ");
		sql.append("nvl(case when instr(to_char(round(zhf, 2)),'.',1,1)=1 then '0'||to_char(round(zhf, 2)) else to_char(round(zhf, 2)) end, '0') zhf from (");
		sql.append("select a.xh,a.xm,a.xb,a.nj,a.xydm, ");
		sql.append("a.xymc,a.zydm,a.zymc,a.bjdm,a.bjmc, '");
		sql.append(xn + "' xn,'" + xq + "' xq,(select xqmc from ");
		sql.append("xqdzb where xqdm = '" + xq + "' )xqmc, ");
		sql.append("nvl(b.dyf,0) dyf,nvl(b.zyf,0) zyf,nvl(b.tyf,0) tyf,nvl(b.nlf,0) nlf,");
		sql.append("nvl(case when instr(to_char(b.dyf * "+dyfbl+" / 100),'.',1,1)=1 then '0'||to_char(b.dyf * "+dyfbl+" / 100) else to_char(b.dyf * "+dyfbl+" / 100) end, '0') dyzhf, ");
		sql.append("nvl(case when instr(to_char(b.zyf * "+zyfbl+" / 100),'.',1,1)=1 then '0'||to_char(b.zyf * "+zyfbl+" / 100) else to_char(b.zyf * "+zyfbl+" / 100) end, '0') zyzhf, ");
		sql.append("nvl(case when instr(to_char(b.tyf * "+tyfbl+" / 100),'.',1,1)=1 then '0'||to_char(b.tyf * "+tyfbl+" / 100) else to_char(b.tyf * "+tyfbl+" / 100) end, '0') tyzhf, ");
		sql.append("nvl(case when instr(to_char(b.nlf * "+nlfbl+" / 100),'.',1,1)=1 then '0'||to_char(b.nlf * "+nlfbl+" / 100) else to_char(b.nlf * "+nlfbl+" / 100) end, '0') nlzhf, ");
		sql.append("nvl(case when instr(to_char(b.zhf),'.',1,1)=1 then '0'||to_char(b.zhf) else to_char(b.zhf) end, '0') zhf ");
		sql.append("from view_xsjbxx a left join (select * from (select a.xn,a.xq,a.pjxh, a.dyf, a.tyf, a.nlf, a.zhf, b.zyf from zjcm_zhf a ");
		sql.append("left join view_zjcm_zyf b on a.xn = b.xn and a.xq = b.xq and a.pjxh = b.xh) where xn = '" + xn + "'  and xq = '" + xq + "') b on a.xh = b.pjxh ");
		sql.append(" ) " + query + ")");
		
		return sql.toString();
	}
	
	/**
	 * 计算综合素质分
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public Boolean jsZhf(ZjcmPjpyModel model) throws Exception {
		
		boolean flag = false;
		
		String xn = model.getXn();
		String xq = model.getXq();
		
		List<HashMap<String,String>> list = dao.jsZhf(model);
		
		if (list != null && list.size() > 0) {
			
			String[] sql = new String[list.size()];
			
			for (int i = 0; i < list.size(); i++) {
				HashMap<String, String> map = list.get(i);
				String xh = map.get("xh");
				String zhf = map.get("zhf");
				sql[i] = "update zjcm_zhf set zhf = '" + zhf
						+ "' where xn||xq||pjxh = '" + xn + xq + xh + "'";
			}
			
			flag = dao.saveArrDate(sql);
		}
		
		return flag;
	}
	
	/**
	 * 计算学分绩点
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public String jsXfJd(ZjcmPjpyModel model) throws Exception {

		String message = "计算成功";

		String realTable = "zjcm_xfjdb";
		String pycc = model.getPycc();

		if ("专科".equalsIgnoreCase(pycc)) {

			List<HashMap<String, String>> sspjfList = dao.getSspjfList(model);
			if (sspjfList != null && sspjfList.size() > 0) {

				String xn = model.getXn();
				String xq = model.getXq();
				String[] xfjdxh = new String[sspjfList.size()];
				String[] xfjd = new String[sspjfList.size()];

				for (int i = 0; i < sspjfList.size(); i++) {
					xfjdxh[i] = sspjfList.get(i).get("xh");
					xfjd[i] = sspjfList.get(i).get("sspjf");
				}

				String[] arrzd = new String[] { "xfjdxh", "xfjd" };
				String[] onezd = new String[] { "xn", "xq" };
				String pk = "xn||xq||xfjdxh";
				String[] pkValue = new String[sspjfList.size()];

				for (int i = 0; i < sspjfList.size(); i++) {
					pkValue[i] = xn + xq + xfjdxh[i];
				}

				SaveForm saveForm = new SaveForm();
				saveForm.setTableName(realTable);
				saveForm.setArrzd(arrzd);
				saveForm.setOnezd(onezd);
				saveForm.setPk(pk);
				saveForm.setPkValue(pkValue);

				model = new ZjcmPjpyModel();
				model.setXn(xn);
				model.setXq(xq);
				model.setXfjdxh(xfjdxh);
				model.setXfjd(xfjd);

				savePjpy(saveForm, model);

			}else{
				message = "没有符合条件的数据，计算失败";
			}
		} else if ("本科".equalsIgnoreCase(pycc)) {
			List<HashMap<String, String>> pjxfjdList = dao.getPjxfjdList(model);
			if (pjxfjdList != null && pjxfjdList.size() > 0) {

				String xn = model.getXn();
				String xq = model.getXq();
				String[] xfjdxh = new String[pjxfjdList.size()];
				String[] xfjd = new String[pjxfjdList.size()];

				for (int i = 0; i < pjxfjdList.size(); i++) {
					xfjdxh[i] = pjxfjdList.get(i).get("xh");
					xfjd[i] = pjxfjdList.get(i).get("pjxfjd");
				}

				String[] arrzd = new String[] { "xfjdxh", "xfjd" };
				String[] onezd = new String[] { "xn", "xq" };
				String pk = "xn||xq||xfjdxh";
				String[] pkValue = new String[pjxfjdList.size()];

				for (int i = 0; i < pjxfjdList.size(); i++) {
					pkValue[i] = xn + xq + xfjdxh[i];
				}

				SaveForm saveForm = new SaveForm();
				saveForm.setTableName(realTable);
				saveForm.setArrzd(arrzd);
				saveForm.setOnezd(onezd);
				saveForm.setPk(pk);
				saveForm.setPkValue(pkValue);

				model = new ZjcmPjpyModel();
				model.setXn(xn);
				model.setXq(xq);
				model.setXfjdxh(xfjdxh);
				model.setXfjd(xfjd);

				savePjpy(saveForm, model);
			}else{
				message = "没有符合条件的数据，计算失败";
			}
		}
		return message;
	}
	
	/**
	 * 获得综合素质排名列表
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getZhfPmList(ZjcmPjpyModel model)
			throws Exception {
		String table = getZhfTable(model);
		List<HashMap<String, String>> list = dao.getZhfPmList(table, model);
		return list;
	}
	
	/**
	 * 打印综合素质测评表
	 * 
	 * @author luo
	 * @throws Exception
	 */
	public void printZhszcpb(ZjcmPjpyModel model, OutputStream os)
			throws Exception {

		String xn = model.getXn();
		String xq = model.getXq();
		String xydm = model.getXydm();
		String bjdm = model.getBjdm();
		String lx = model.getLx();
		
		// 判断报表是否需要英语，计算机成绩
		int num1 = ("zhbnoyj".equalsIgnoreCase(lx)) ? 0 : 2;
		
		//获得班级获得奖学金(一二三等)人数
		List<HashMap<String, String>> jxjRs = dao.getJxjRs(model);
		//获得班级名称以及人数
		HashMap<String, String> bjInfo = dao.getBjRs(bjdm);
		//获得学期名称
		String xqmc = getOneValue("xqdzb", "xqmc", "xqdm", xq);
		//获得学院名称
		String xymc = getOneValue("view_njxyzybj", "xymc", "xydm", xydm);
		//获得班级名称
		String bjmc = bjInfo.get("bjmc");
		//获得班级人数
		String bjrs = bjInfo.get("num");
		//一等奖学金获得人数
		String ydrs = "0";
		// 二等奖学金获得人数
		String edrs = "0";
		// 三等奖学金获得人数
		String sdrs = "0";
		
		// 对各个奖学金人数赋值(一二三等)
		if (jxjRs != null && jxjRs.size() > 0) {
			for (int i = 0; i < jxjRs.size(); i++) {
				HashMap<String, String> map = jxjRs.get(i);
				if (map.get("jxjmc").contains("一等")) {
					ydrs = map.get("num");
				} else if (map.get("jxjmc").contains("二等")) {
					edrs = map.get("num");
				} else if (map.get("jxjmc").contains("三等")) {
					sdrs = map.get("num");
				}
			}
		}
		
		// 设置标题
		StringBuffer title =new StringBuffer();
		title.append(xn);
		title.append("学年");
		title.append(xqmc);
		title.append("学期");
		title.append(xymc);
		title.append("综合素质测评表");

		WritableWorkbook wwb = Workbook.createWorkbook(os);
		
		WritableSheet ws = wwb.createSheet("综合素质测评表", 0);

		WritableCellFormat wcf2 = new WritableCellFormat(); // 构造单元格格式
		wcf2 = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL);
		
		WritableCellFormat wcf3 = new WritableCellFormat(); // 构造单元格格式
		wcf2 = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
				Alignment.LEFT, VerticalAlignment.CENTRE, true, Border.ALL);
		
		// 填充标题
		ExcelMB ex = new ExcelMB();
		ws.mergeCells(0, 0, 18 + num1, 1);
		ex.printToOneCell_multy(ws, title.toString(), 0, 0, 12, true, Alignment.CENTRE,
				VerticalAlignment.CENTRE, true, 650, Border.NONE);

		// 填充表头
		// --------------第一行----------------
		ws.addCell(new Label(0, 2, "班级：" + bjmc, wcf3));
		ws.mergeCells(0, 2, 10, 2);
		ws.addCell(new Label(11, 2, "班级人数：" + bjrs + "人", wcf3));
		ws.mergeCells(11, 2, 18 + num1, 2);
		// --------------第二行----------------
		ws.addCell(new Label(0, 3, "一等奖学金：" + ydrs + "人", wcf3));
		ws.mergeCells(0, 3, 5, 3);
		ws.addCell(new Label(6, 3, "二等奖学金：" + edrs + "人", wcf3));
		ws.mergeCells(6, 3, 10, 3);
		ws.addCell(new Label(11, 3, "三等奖学金：" + sdrs + "人", wcf3));
		ws.mergeCells(11, 3, 18 + num1, 3);
		// --------------第三行----------------
		ws.addCell(new Label(11, 4, "班主任签名：", wcf3));
		ws.mergeCells(11, 4, 18 + num1, 4);
		// --------------第四行----------------
		ws.addCell(new Label(0, 5, "学号", wcf2));
		ws.addCell(new Label(1, 5, "姓名", wcf2));
		ws.addCell(new Label(2, 5, "德育积分", wcf2));
		ws.addCell(new Label(3, 5, "德育名次", wcf2));
		ws.addCell(new Label(4, 5, "智育积分", wcf2));
		ws.addCell(new Label(5, 5, "智育名次", wcf2));
		ws.addCell(new Label(6, 5, "体育积分", wcf2));
		ws.addCell(new Label(7, 5, "能力积分", wcf2));
		ws.addCell(new Label(8, 5, "能力名次", wcf2));
		ws.addCell(new Label(9, 5, "总分", wcf2));
		ws.addCell(new Label(10, 5, "综合排名", wcf2));
		ws.addCell(new Label(11, 5, "奖学金及单项奖(因为其它原因不能评选的请注明)", wcf2));
		ws.addCell(new Label(10, 5, "综合排名", wcf2));
		ws.addCell(new Label(11, 5, "奖学金及单项奖(因为其它原因不能评选的请注明)", wcf2));
		ws.addCell(new Label(12, 5, "三好学生及优干", wcf2));
		ws.addCell(new Label(13, 5, "获校外奖学金情况（包括当学期获国家奖学金情况）", wcf2));
		ws.addCell(new Label(14, 5, "学习成绩不及格门数", wcf2));
		if ("zhbyj".equalsIgnoreCase(lx)) {
			ws.addCell(new Label(15, 5, "英语过级情况", wcf2));
			ws.addCell(new Label(16, 5, "计算机过级情况", wcf2));
		}
		ws.addCell(new Label(15 + num1, 5, "有无拖欠学费(已贷款请注明)", wcf2));
		ws.addCell(new Label(16 + num1, 5, "有无校级通报", wcf2));
		ws.addCell(new Label(17 + num1, 5, "处分情况(还在留校察看期请注明)", wcf2));
		ws.addCell(new Label(18 + num1, 5, "旷课节数", wcf2));
		
		//综合分排名相关信息
		List<HashMap<String, String>> zhflist = getZhfPmList(model);
		
		//获得奖学金，荣誉称号等相关信息
		List<HashMap<String, String>> jxjrychList = dao.getJxjhdList(model);
		
		//获得学风相关信息
		PjpyXfjsService xfService = new PjpyXfjsService();
		
		List<HashMap<String, String>> kkList = xfService.getXskqqk("", xn, xq,"xy");
		List<HashMap<String, String>> tbList = xfService.getXskqqk("", xn, xq,"xx");
		
		// 违纪名称列表
		List<HashMap<String, String>> cfList = dao.getCfmcList(model);
		
		// 英语成绩（计算机成绩）列表
		List<HashMap<String, String>> djksList = null;
		if ("zhbyj".equalsIgnoreCase(lx)) {
			djksList = dao.getDjksList(model);
		}
		
		if (zhflist != null && zhflist.size() > 0) {
			for (int i = 0; i < zhflist.size(); i++) {
				HashMap<String, String> map = zhflist.get(i);

				// 综合分信息相关
				ws.addCell(new Label(0, 6 + i, map.get("xh"), wcf2));
				ws.addCell(new Label(1, 6 + i, map.get("xm"), wcf2));
				ws.addCell(new Label(2, 6 + i, map.get("dyzhf"), wcf2));
				ws.addCell(new Label(3, 6 + i, map.get("dypm"), wcf2));
				ws.addCell(new Label(4, 6 + i, map.get("zyzhf"), wcf2));
				ws.addCell(new Label(5, 6 + i, map.get("zypm"), wcf2));
				ws.addCell(new Label(6, 6 + i, map.get("tyzhf"), wcf2));
				ws.addCell(new Label(7, 6 + i, map.get("nlzhf"), wcf2));
				ws.addCell(new Label(8, 6 + i, map.get("nlpm"), wcf2));
				ws.addCell(new Label(9, 6 + i, map.get("zhf"), wcf2));
				ws.addCell(new Label(10, 6 + i, map.get("zhpm"), wcf2));
				
				// 所获奖励相关信息
				if (jxjrychList != null && jxjrychList.size() > 0) {
					for (int j = 0; j < jxjrychList.size(); j++) {
						HashMap<String, String> jxjMap = jxjrychList.get(j);
						if (map.get("xh").equalsIgnoreCase(jxjMap.get("xh"))) {
							ws.addCell(new Label(11, 6 + i, jxjMap.get("xnjxj"), wcf2));
							ws.addCell(new Label(12, 6 + i, jxjMap.get("rychmc"), wcf2));
							ws.addCell(new Label(13, 6 + i, jxjMap.get("xwjxj"), wcf2));
							ws.addCell(new Label(14, 6 + i, jxjMap.get("bjgs"), wcf2));
						}
					}
				}
				
				// 等级考试情况
				if ("zhbyj".equalsIgnoreCase(lx)) {
					if (djksList != null && djksList.size() > 0) {
						for (int j = 0; j < djksList.size(); j++) {
							HashMap<String, String> cjMap = djksList.get(j);
							if (map.get("xh").equalsIgnoreCase(cjMap.get("xh"))) {

								// 英语等级
								if ("yy".equalsIgnoreCase(cjMap.get("lx"))) {
									ws.addCell(new Label(15, 6 + i, cjMap
											.get("ksqk"), wcf2));
								} else {
									ws.addCell(new Label(15, 6 + i, "", wcf2));
								}

								// 计算机等级
								if ("jxj".equalsIgnoreCase(cjMap.get("lx"))) {
									ws.addCell(new Label(16, 6 + i, cjMap
											.get("ksqk"), wcf2));
								} else {
									ws.addCell(new Label(16, 6 + i, "", wcf2));
								}
							} else {
								ws.addCell(new Label(15, 6 + i, "", wcf2));
								ws.addCell(new Label(16, 6 + i, "", wcf2));
							}
						}
					} else {
						ws.addCell(new Label(15, 6 + i, "", wcf2));
						ws.addCell(new Label(16, 6 + i, "", wcf2));
					}
				}
				
				// TODO
				// 拖欠学费
				ws.addCell(new Label(15+ num1, 6 + i, "", wcf2));
				
				// 学风相关信息(校级通报)
				if (tbList != null && tbList.size() > 0) {
					for (int j = 0; j < tbList.size(); j++) {
						HashMap<String, String> tbMap = tbList.get(j);
						if (map.get("xh").equalsIgnoreCase(tbMap.get("xh"))) {
							ws.addCell(new Label(16 + num1, 6 + i, "有", wcf2));
							break;
						} else {
							ws.addCell(new Label(16 + num1, 6 + i, "", wcf2));
						}
					}
				} else {
					ws.addCell(new Label(16 + num1, 6 + i, "", wcf2));
				}

				// 违纪名称
				if (cfList != null && cfList.size() > 0) {
					for (int j = 0; j < cfList.size(); j++) {
						HashMap<String, String> cfMap = cfList.get(j);
						if (map.get("xh").equalsIgnoreCase(cfMap.get("xh"))) {
							ws.addCell(new Label(17 + num1, 6 + i, cfMap
									.get("cflbmc"), wcf2));
							break;
						} else {
							ws.addCell(new Label(17 + num1, 6 + i, "", wcf2));
						}
					}
				} else {
					ws.addCell(new Label(17 + num1, 6 + i, "", wcf2));
				}

				// 学风相关信息(旷课节数)
				if (kkList != null && kkList.size() > 0) {
					for (int j = 0; j < kkList.size(); j++) {
						HashMap<String, String> kkMap = kkList.get(j);
						if (map.get("xh").equalsIgnoreCase(kkMap.get("xh"))) {
							ws.addCell(new Label(18 + num1, 6 + i, kkMap
									.get("kkjs"), wcf2));
							break;
						} else {
							ws.addCell(new Label(18 + num1, 6 + i, "", wcf2));
						}
					}
				} else {
					ws.addCell(new Label(18 + num1, 6 + i, "", wcf2));
				}
			}
		}

		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	/**
	 * 打印综合素质比例表
	 * 
	 * @author luo
	 * @throws Exception
	 */
	public void printZhszblb(ZjcmPjpyModel model, OutputStream os)
			throws Exception {
	
		String xydm = model.getXydm();
		String xn = model.getXn();
		String xq = model.getXq();
		
		//获得学期名称
		String xqmc = getOneValue("xqdzb", "xqmc", "xqdm", xq);
		
		//获得学院名称
		String xymc = getOneValue("view_njxyzybj", "xymc", "xydm", xydm);
		
		// 设置标题
		StringBuffer title =new StringBuffer();
		title.append("浙江传媒学院");
		title.append(xn);
		title.append("学年");
		title.append(xqmc);
		title.append("学期");
		title.append("各班奖学金综合测评比例");

		WritableWorkbook wwb = Workbook.createWorkbook(os);
		
		WritableSheet ws = wwb.createSheet(xymc + "奖学金综合测评比例", 0);

		WritableCellFormat wcf2 = new WritableCellFormat(); // 构造单元格格式
		wcf2 = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL);
		
		// 填充标题
		ExcelMB ex = new ExcelMB();
		ws.mergeCells(0, 0, 15, 1);
		ex.printToOneCell_multy(ws, title.toString(), 0, 0, 12, true, Alignment.CENTRE,
				VerticalAlignment.CENTRE, true, 650, Border.NONE);

		// 获得奖学金（荣誉称号）条件
		String firstCjBl = "0";// 一等奖成绩比例
		String firstZcBl = "0";// 一等奖综测比例
		String secondCjBl = "0";// 二等奖成绩比例
		String secondZcBl = "0";// 二等奖综测比例
		String thirdCjBl = "0";// 三等奖成绩比例
		String thirdZcBl = "0";// 三等奖综测比例
		String xxyxCjBl = "0";// 学习优秀奖成绩比例
		String xxjbCjBl = "0";// 学习进步奖成绩比例
		String yxgbDyBl = "0";// 优秀干部德育比例
		String yxgbZcBl = "0";// 优秀干部综测比例
		String shxsDyBl = "0";// 三好学生德育比例
		String shxsCjBl = "0";// 三好学生成绩比例
		
		List<HashMap<String, String>> tjList = dao.getJxjRychTjList(model);

		if (tjList != null && tjList.size() > 0) {
			for (int i = 0; i < tjList.size(); i++) {

				HashMap<String, String> map = tjList.get(i);

				// 条件字段
				String tjzd = map.get("tjzd");
				// 条件值
				String tjz = map.get("tjz");
				// 奖学金（荣誉称号）名称
				String jxjmc = map.get("jxjmc");

				// -------一等奖学金条件-------
				if (jxjmc.contains("一等")) {
					// 智育排名（成绩排名）条件
					if ("zypm".equalsIgnoreCase(tjzd)) {
						firstCjBl = tjz;
					}
					// 综合排名条件
					else if ("zhpm".equalsIgnoreCase(tjzd)) {
						firstZcBl = tjz;
					}
				}

				// -------二等奖学金条件-------
				if (jxjmc.contains("二等")) {
					// 智育排名（成绩排名）条件
					if ("zypm".equalsIgnoreCase(tjzd)) {
						secondCjBl = tjz;
					}
					// 综合排名条件
					else if ("zhpm".equalsIgnoreCase(tjzd)) {
						secondZcBl = tjz;
					}
				}

				// -------三等奖学金条件-------
				if (jxjmc.contains("三等")) {
					// 智育排名（成绩排名）条件
					if ("zypm".equalsIgnoreCase(tjzd)) {
						thirdCjBl = tjz;
					}
					// 综合排名条件
					else if ("zhpm".equalsIgnoreCase(tjzd)) {
						thirdZcBl = tjz;
					}
				}

				// -------学习优秀奖条件-------
				if (jxjmc.contains("学习") && jxjmc.contains("优秀")) {
					// 智育排名（成绩排名）条件
					if ("zypm".equalsIgnoreCase(tjzd)) {
						xxyxCjBl = tjz;
					}
				}

				// -------学习进步奖条件-------
				if (jxjmc.contains("学习") && jxjmc.contains("进步")) {
					// 学习进步条件
					if ("xxjb".equalsIgnoreCase(tjzd)) {
						xxjbCjBl = tjz;
					}
				}

				// -------优秀干部条件-------
				if (jxjmc.contains("优秀") && jxjmc.contains("干部")) {
					// 德育排名条件
					if ("dypm".equalsIgnoreCase(tjzd)) {
						yxgbDyBl = tjz;
					}
					// 综合排名条件
					else if ("zhpm".equalsIgnoreCase(tjzd)) {
						yxgbZcBl = tjz;
					}
				}

				// -------三好学生条件-------
				if (jxjmc.contains("三好学生")) {
					// 德育排名条件
					if ("dypm".equalsIgnoreCase(tjzd)) {
						shxsDyBl = tjz;
					}
					// 综合排名条件
					else if ("zypm".equalsIgnoreCase(tjzd)) {
						shxsCjBl = tjz;
					}
				}
			}
		}
		
		// 填充表头
		ws.addCell(new Label(0, 2, "学院", wcf2));
		ws.mergeCells(0, 2, 0, 3);
		ws.addCell(new Label(1, 2, "班级", wcf2));
		ws.mergeCells(1, 2, 1, 3);
		ws.addCell(new Label(2, 2, "人数", wcf2));
		ws.mergeCells(2, 2, 2, 3);
		ws.addCell(new Label(3, 2, "德育50%", wcf2));
		ws.mergeCells(3, 2, 3, 3);
		ws.addCell(new Label(4, 2, "一等奖", wcf2));
		ws.mergeCells(4, 2, 5, 2);
		ws.addCell(new Label(6, 2, "二等奖", wcf2));
		ws.mergeCells(6, 2, 7, 2);
		ws.addCell(new Label(8, 2, "三等奖", wcf2));
		ws.mergeCells(8, 2, 9, 2);
		ws.addCell(new Label(10, 2, "单项奖", wcf2));
		ws.mergeCells(10, 2, 11, 2);
		ws.addCell(new Label(12, 2, "优秀学生干部", wcf2));
		ws.mergeCells(12, 2, 13, 2);
		ws.addCell(new Label(14, 2, "三好学生", wcf2));
		ws.mergeCells(14, 2, 15, 2);

		ws.addCell(new Label(4, 3, "成绩排名" + firstCjBl + "%", wcf2));
		ws.addCell(new Label(5, 3, "综合测评" + firstZcBl + "%", wcf2));
		ws.addCell(new Label(6, 3, "成绩排名" + secondCjBl + "%", wcf2));
		ws.addCell(new Label(7, 3, "综合测评" + secondZcBl + "%", wcf2));
		ws.addCell(new Label(8, 3, "成绩排名" + thirdCjBl + "%", wcf2));
		ws.addCell(new Label(9, 3, "综合测评" + thirdZcBl + "%", wcf2));
		ws.addCell(new Label(10, 3, "学习优秀奖前" + xxyxCjBl + "%", wcf2));
		ws.addCell(new Label(11, 3, "学习进步奖提前" + xxjbCjBl + "%", wcf2));
		ws.addCell(new Label(12, 3, "德育前" + yxgbDyBl + "%", wcf2));
		ws.addCell(new Label(13, 3, "综合测评" + yxgbZcBl + "%", wcf2));
		ws.addCell(new Label(14, 3, "德育前" + shxsDyBl + "%", wcf2));
		ws.addCell(new Label(15, 3, "成绩排名" + shxsCjBl + "%", wcf2));

		// 获得学院班级人数列表
		List<HashMap<String, String>> xybjrslist = dao.getXyBjrsList(model);
		
		if (xybjrslist != null && xybjrslist.size() > 0) {
			
			// 班级人数
			int bjZrs = 0;
			// 德育人数
			int dyZrs = 0;
			// 一等成绩人数
			int firstCjZRs = 0;
			// 一等综测人数
			int firstZcZRs = 0;
			// 二等成绩人数
			int secondCjZRs = 0;
			// 二等综测人数
			int secondZcZRs = 0;
			// 三等成绩人数
			int thirdCjZRs = 0;
			// 三等综测人数
			int thirdZcZRs = 0;
			// 学习优秀成绩人数
			int xxyxCjZRs = 0;
			// 学习进步排名
			int xxjbZPm = 0;
			// 优秀干部德育人数
			int yxgbDyZRs = 0;
			// 优秀干部综测人数
			int yxgbZcZRs = 0;
			// 三好学生德育人数
			int shxsDyZRs = 0;
			// 三好学生成绩人数
			int shxsCjZRs = 0;
			
			for (int i = 0; i < xybjrslist.size(); i++) {

				HashMap<String, String> map = xybjrslist.get(i);

				// 班级名称
				String bjmc = map.get("bjmc");
				// 班级人数
				String bjrs = map.get("num");
				// 德育人数 页面写死50% 无法根据奖学金类别从条件设置中抽取
				String dyrs = String.valueOf(Math
						.round(Float.parseFloat(bjrs) * 50 / 100));
				// 一等成绩人数
				String firstCjRs = String.valueOf(Math.round(Float
						.parseFloat(bjrs)
						* Float.parseFloat(firstCjBl) / 100));
				// 一等综测人数
				String firstZcRs = String.valueOf(Math.round(Float
						.parseFloat(bjrs)
						* Float.parseFloat(firstZcBl) / 100));
				// 二等成绩人数
				String secondCjRs = String.valueOf(Math.round(Float
						.parseFloat(bjrs)
						* Float.parseFloat(secondCjBl) / 100));
				// 二等综测人数
				String secondZcRs = String.valueOf(Math.round(Float
						.parseFloat(bjrs)
						* Float.parseFloat(secondZcBl) / 100));
				// 三等成绩人数
				String thirdCjRs = String.valueOf(Math.round(Float
						.parseFloat(bjrs)
						* Float.parseFloat(thirdCjBl) / 100));
				// 三等综测人数
				String thirdZcRs = String.valueOf(Math.round(Float
						.parseFloat(bjrs)
						* Float.parseFloat(thirdZcBl) / 100));
				// 学习优秀成绩人数
				String xxyxCjRs = String.valueOf(Math.round(Float
						.parseFloat(bjrs)
						* Float.parseFloat(xxyxCjBl) / 100));
				// 学习进步排名
				String xxjbPm = String.valueOf(Math.round(Float
						.parseFloat(bjrs)
						* Float.parseFloat(xxjbCjBl) / 100));
				// 优秀干部德育人数
				String yxgbDyRs = String.valueOf(Math.round(Float
						.parseFloat(bjrs)
						* Float.parseFloat(yxgbDyBl) / 100));
				// 优秀干部综测人数
				String yxgbZcRs = String.valueOf(Math.round(Float
						.parseFloat(bjrs)
						* Float.parseFloat(yxgbZcBl) / 100));
				// 三好学生德育人数
				String shxsDyRs = String.valueOf(Math.round(Float
						.parseFloat(bjrs)
						* Float.parseFloat(shxsDyBl) / 100));
				// 三好学生成绩人数
				String shxsCjRs = String.valueOf(Math.round(Float
						.parseFloat(bjrs)
						* Float.parseFloat(shxsCjBl) / 100));

				ws.addCell(new Label(0, 4 + i, xymc, wcf2));
				ws.addCell(new Label(1, 4 + i, bjmc, wcf2));
				ws.addCell(new Label(2, 4 + i, bjrs, wcf2));
				ws.addCell(new Label(3, 4 + i, dyrs, wcf2));
				ws.addCell(new Label(4, 4 + i, firstCjRs, wcf2));
				ws.addCell(new Label(5, 4 + i, firstZcRs, wcf2));
				ws.addCell(new Label(6, 4 + i, secondCjRs, wcf2));
				ws.addCell(new Label(7, 4 + i, secondZcRs, wcf2));
				ws.addCell(new Label(8, 4 + i, thirdCjRs, wcf2));
				ws.addCell(new Label(9, 4 + i, thirdZcRs, wcf2));
				ws.addCell(new Label(10, 4 + i, xxyxCjRs, wcf2));
				ws.addCell(new Label(11, 4 + i, xxjbPm, wcf2));
				ws.addCell(new Label(12, 4 + i, yxgbDyRs, wcf2));
				ws.addCell(new Label(13, 4 + i, yxgbZcRs, wcf2));
				ws.addCell(new Label(14, 4 + i, shxsDyRs, wcf2));
				ws.addCell(new Label(15, 4 + i, shxsCjRs, wcf2));
				
				// 累加，计算总计
				bjZrs += Integer.valueOf(bjrs);
				dyZrs += Integer.valueOf(dyrs);
				firstCjZRs += Integer.valueOf(firstCjRs);
				firstZcZRs += Integer.valueOf(firstZcRs);
				secondCjZRs += Integer.valueOf(secondCjRs);
				secondZcZRs += Integer.valueOf(secondZcRs);
				thirdCjZRs += Integer.valueOf(thirdCjRs);
				thirdZcZRs += Integer.valueOf(thirdZcRs);
				xxyxCjZRs += Integer.valueOf(xxyxCjRs);
				xxjbZPm += Integer.valueOf(xxjbZPm);
				yxgbDyZRs += Integer.valueOf(yxgbDyRs);
				yxgbZcZRs += Integer.valueOf(yxgbZcRs);
				shxsDyZRs += Integer.valueOf(shxsDyRs);
				shxsCjZRs += Integer.valueOf(shxsCjRs);
			}
			
			// 总计
			ws.addCell(new Label(0, xybjrslist.size()+4, xymc, wcf2));
			ws.addCell(new Label(1, xybjrslist.size()+4, "总计", wcf2));
			ws.addCell(new Label(2, xybjrslist.size()+4, String.valueOf(bjZrs), wcf2));
			ws.addCell(new Label(3, xybjrslist.size()+4, String.valueOf(dyZrs), wcf2));
			ws.addCell(new Label(4, xybjrslist.size()+4, String.valueOf(firstCjZRs), wcf2));
			ws.addCell(new Label(5, xybjrslist.size()+4, String.valueOf(firstZcZRs), wcf2));
			ws.addCell(new Label(6, xybjrslist.size()+4, String.valueOf(secondCjZRs), wcf2));
			ws.addCell(new Label(7, xybjrslist.size()+4, String.valueOf(secondZcZRs), wcf2));
			ws.addCell(new Label(8, xybjrslist.size()+4, String.valueOf(thirdCjZRs), wcf2));
			ws.addCell(new Label(9, xybjrslist.size()+4, String.valueOf(thirdZcZRs), wcf2));
			ws.addCell(new Label(10, xybjrslist.size()+4, String.valueOf(xxyxCjZRs), wcf2));
			ws.addCell(new Label(11, xybjrslist.size()+4, String.valueOf(xxjbZPm), wcf2));
			ws.addCell(new Label(12, xybjrslist.size()+4, String.valueOf(yxgbDyZRs), wcf2));
			ws.addCell(new Label(13, xybjrslist.size()+4, String.valueOf(yxgbZcZRs), wcf2));
			ws.addCell(new Label(14, xybjrslist.size()+4, String.valueOf(shxsDyZRs), wcf2));
			ws.addCell(new Label(15, xybjrslist.size()+4, String.valueOf(shxsCjZRs), wcf2));
			
			ws.mergeCells(0, 4, 0, xybjrslist.size() + 4);
		}
		
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	/**
	 * 打印奖学金金额统计表
	 * 
	 * @author luo
	 * @throws Exception
	 */
	public void printJxjjetjb(ZjcmPjpyModel model, OutputStream os)
			throws Exception {
	
		DAO tyDao = DAO.getInstance();
		
		String xn = model.getXn();
		String xq = model.getXq();
		String jxjdm = model.getJxjdm();
		String yhdm = model.getYhdm();
		String nowTime = tyDao.getNowTime("1");//获得系统时间
		
		// 获得学期名称
		String xqmc = getOneValue("xqdzb", "xqmc", "xqdm", xq);

		// 获得学期名称
		String jxjmc = getOneValue("jxjdmb", "jxjmc", "jxjdm", jxjdm);

		// 获得银行名称
		String yhmc = getOneValue("dmk_yh", "yhmc", "yhdm", yhdm);
		
		// 设置标题
		StringBuffer title =new StringBuffer();
		title.append(xn);
		title.append("学年");
		title.append(xqmc);
		title.append("学期");
		title.append(jxjmc);
		title.append("获奖学生发放金额汇总细表");

		WritableWorkbook wwb = Workbook.createWorkbook(os);
		
		WritableSheet ws = wwb.createSheet(jxjmc+"金额汇总表", 0);

		WritableCellFormat wcf2 = new WritableCellFormat(); // 构造单元格格式
		wcf2 = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL);
		
		// 填充标题
		ExcelMB ex = new ExcelMB();
		ws.mergeCells(0, 0, 7, 1);
		ex.printToOneCell_multy(ws, title.toString(), 0, 0, 12, true, Alignment.CENTRE,
				VerticalAlignment.CENTRE, true, 650, Border.NONE);

		// 填充表头
		ws.addCell(new Label(0, 2, "序号", wcf2));
		ws.addCell(new Label(1, 2, "学生姓名", wcf2));
		ws.addCell(new Label(2, 2, "院系", wcf2));
		ws.addCell(new Label(3, 2, "专业", wcf2));
		ws.addCell(new Label(4, 2, "奖项", wcf2));
		ws.addCell(new Label(5, 2, "金额", wcf2));
		ws.addCell(new Label(6, 2, yhmc + "卡号", wcf2));
		ws.addCell(new Label(7, 2, "合计", wcf2));
		
		// 奖学金金额统计列表
		List<HashMap<String, String>> list = dao.getJxjjeList(model);
		
		int zjje = 0;//总计金额
		
		if (list != null && list.size() > 0) {
			
			for (int i = 0; i < list.size(); i++) {
				
				HashMap<String, String> map = list.get(i);
				
				ws.addCell(new Label(0, 3 + i, String.valueOf(i + 1), wcf2));//序号
				ws.addCell(new Label(1, 3 + i, map.get("xm"), wcf2));//姓名
				ws.addCell(new Label(2, 3 + i, map.get("xymc"), wcf2));//学院
				ws.addCell(new Label(3, 3 + i, map.get("zymc"), wcf2));//专业
				ws.addCell(new Label(4, 3 + i, map.get("jxjmc"), wcf2));//奖学金名称
				ws.addCell(new Label(5, 3 + i, map.get("je"), wcf2));//金额
				ws.addCell(new Label(6, 3 + i, map.get("yhkh"), wcf2));//银行卡号
				
				// 累加金额，计算总计金额
				if (!Base.isNull(map.get("je"))) {
					zjje += Integer.parseInt(map.get("je"));
				} 
			}
			
			// 表尾信息
			ws.addCell(new Label(0, list.size() + 3, "合计："
					+ MoneyFormat.format(String.valueOf(zjje)), wcf2));// 总计金额
			ws.mergeCells(0, list.size() + 3, 7, list.size() + 3);

			WritableCellFormat wcf3 = new WritableCellFormat(); // 构造单元格格式
			wcf3 = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
					Alignment.LEFT, VerticalAlignment.CENTRE, true, null);
			
			StringBuffer msg = new StringBuffer();
			msg.append("制表人：" );
			msg.append("                         " );
			msg.append("证明人：" );
			msg.append("                          " );
			msg.append("制表时间：" + nowTime);
			
			ws.addCell(new Label(0, list.size() + 5, msg.toString(), wcf3));// 总计金额,证明人,制表时间		
			ws.mergeCells(0, list.size() + 5, 7, list.size() + 5);
			
			ws.addCell(new Label(0, list.size() + 7, "部门领导签字：", wcf3));// 部门领导签字		
			ws.mergeCells(0, list.size() + 7, 7, list.size() + 7);
			
			ws.addCell(new Label(0, list.size() + 9, "校领导签字：", wcf3));// 校领导签字
			ws.mergeCells(0, list.size() + 9, 7, list.size() + 9);
			
			//合并单元格
			int xyje = 0;//总计金额
			int row = 1;//列数
			boolean c = false;
			
			for (int i = 0; i <= list.size(); i++) {
				
				String c3 = "";
				String c4 = "";
				
				WritableCell c1 = ws.getWritableCell(2, 3 + i);//学院
				WritableCell f1 = ws.getWritableCell(5, 3 + i);//金额
				
				c3 = c1.getContents();
				
				String je = f1.getContents();
				
				//累加金额，计算学院总计金额
				if (!Base.isNull(je)) {
					xyje += Integer.parseInt(je);
				} 
				
				if (i > 0) {
					WritableCell c2 = ws.getWritableCell(2, 3 + i - 1);
					c4 = c2.getContents();
				}
				
				if (c3.equals(c4)) {
					row++;
					c = true;
				}
				
				if ((!c3.equals(c4)) && c) {
					ws.addCell(new Label(7, 3 + i - row, String.valueOf(xyje), wcf2));//学院金额
					ws.mergeCells(7, 3 + i - row, 7, 3 + i - 1);
					// 单元格合并，重置参数
					row = 1;
					zjje = 0;
					c = false;
				}
			}
		}
		
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	/**
	 * 打印奖学金获奖名单
	 * @param ZjcmPjpyModel model
	 * @param OutputStream os
	 * @author lr
	 * @throws Exception
	 */
	public void printJxjhjmd(ZjcmPjpyModel model, OutputStream os)
			throws Exception {
		//奖学金类别 名称
		String jxjlbmc = dao.getOneValue("jxjlbdmb", "jxjlbmc", "jxjlbdm", model.getJxjlbdm());
		WritableWorkbook wwb = Workbook.createWorkbook(os);
		WritableSheet ws = wwb.createSheet(jxjlbmc + "获奖名单", 0);
		
		//学院列表
		List<String[]> xymcList = dao.queryXyList(model);
		//各奖学金获奖人数
		List<String[]> jxjdmList = dao.queryJxjmcjrs(model);
		//获奖姓名
		List<String[]> xmList = dao.queryJxjhjxmxx(model);
		
		//奖学金名称样式
		WritableCellFormat jxjFormat = new WritableCellFormat();
		WritableFont jxjFont = new WritableFont(WritableFont.ARIAL,13);
		jxjFont.setBoldStyle(WritableFont.BOLD);
		jxjFormat.setFont(jxjFont);
		jxjFormat.setAlignment(Alignment.LEFT);
		
		
		//输出第一行的表头
		ws.addCell(new Label(0, 0,  
						StringUtils.joinStr(StandardOperation.getXxmc(), 
										model.getXn(), 
										"学年第",
										model.getXq(), 
										"学期", 
										jxjlbmc, 
										"获得者名单" 
										),
						jxjFormat
					)
		);
		ws.mergeCells(0, 0, 8, 0);
		
		//输出第一级奖学金名称和获奖人数
		if (!xmList.isEmpty()) {
			JxjExportProperties properties = new JxjExportProperties();
			for (int i=0;i<jxjdmList.size();i++) {
				//将奖学金名称写入到EXCEL中
				String[] jxjdmArr = jxjdmList.get(i);
				String jxjmc = jxjdmArr[1];
				String jxjdm = jxjdmArr[0];
				Label resultCell = new Label(0,properties.row, jxjmc + " (共" +jxjdmArr[2] +"人)");
				resultCell.setCellFormat(jxjFormat);
				ws.addCell(resultCell);
				ws.mergeCells(0, properties.row, 4, properties.row);
				
				//输出第二级将学院名称写到EXCEL中
				for (int index = 0; index < xymcList.size(); index++) {
					String[] xymcArr = xymcList.get(index);
					if (jxjdm.equalsIgnoreCase(xymcArr[2])) {						
						String xy = xymcArr != null && xymcArr.length >= 4 ? xymcArr[1] : "";
						String xydm = xymcArr != null && xymcArr.length >= 4 ? xymcArr[0] : "";
						List<String> xmByXyList = new ArrayList<String>();//存放每个奖学金下面的每个学院的获奖名单
						for (String[] xmArr : xmList) {
							if (xmArr != null && xmArr.length >= 3
									&& xydm.equalsIgnoreCase(xmArr[1])
									&& jxjdm.equalsIgnoreCase(xmArr[0])) {
								xmByXyList.add(xmArr[2]);
							}
						}
						if (xmByXyList == null || xmByXyList.size() <= 0) {
							continue;
						}
						//输出学院名称
						++properties.row;
						Label xymcCell = new Label(1,properties.row,xy + " (" +xymcArr[3] + "人)");
						xymcCell.setCellFormat(jxjFormat);
						ws.addCell(xymcCell);
						ws.mergeCells(1,properties.row, 7, properties.row);
						//输出第三级将学院下面的获奖名单输出
						writeJxjXm2Excel(ws, properties, xmByXyList,0);//将获奖名单写入到EXCEL中
					}
				}
			}
		}
		ExcelMethods.submitWritableWorkbookOperations(wwb);//向客户端输出
	}
	
	private void writeJxjXm2Excel(WritableSheet ws,
			JxjExportProperties properties, List<String> xmList,int rowCellCount)
			throws Exception {
		properties.rowCellCount = 1;
		properties.row++;//开始写姓名行
		properties.x_axis = rowCellCount;//开始新奖学金的姓名
		if (xmList != null) {
			for(int index = 1;index < xmList.size() + 1;index++){
				String cellContent = xmList.get(index-1);
				if(properties.rowCellCount == 8){
					properties.row++;//满10个就换行
					properties.x_axis=0;
					properties.rowCellCount = 1;//重新每行计数
				}
				// 判断姓名长度,然后根据长度控制合并单元格，及超出长度的情况下进行换行
				if(cellContent.length() > 3){
					int xmLength = cellContent.length()/3 + (cellContent.length()%3==0 ? 0 : 1);
					int pre_x_axis = properties.x_axis;//合并前的x轴值
					if(pre_x_axis + xmLength > 10){
						properties.row++;//姓名过长，换行
						properties.x_axis=rowCellCount;
						properties.rowCellCount = 1;//重新每行计数
						pre_x_axis = properties.x_axis;
					}
					Label cell = new Label(++properties.x_axis,properties.row,cellContent);
					ws.addCell(cell);
					ws.setColumnView(properties.x_axis, 7);
					pre_x_axis++;
					ws.mergeCells(pre_x_axis, properties.row, pre_x_axis+xmLength-1, properties.row);
					properties.x_axis = pre_x_axis+xmLength-1;
				} else {
					Label cell = new Label(++properties.x_axis,properties.row,cellContent);
					ws.addCell(cell);
					ws.setColumnView(properties.x_axis, 7);
					properties.rowCellCount++;
				}
			}
		}
		properties.row+=1;//新奖学金换行
	}
	
	private class JxjExportProperties{
		 int x_axis = 0;//X座标
		 int row = 1;//行标
		 int rowCellCount = 1;//每行的cell个数计数
		 int[] maxLength = {7,7,7,7,7,7,7};//默认的列宽
		public int getX_axis() {
			return x_axis;
		}
		public void setX_axis(int x_axis) {
			this.x_axis = x_axis;
		}
		public int getRow() {
			return row;
		}
		public void setRow(int row) {
			this.row = row;
		}
		public int getRowCellCount() {
			return rowCellCount;
		}
		public void setRowCellCount(int rowCellCount) {
			this.rowCellCount = rowCellCount;
		}
		public int[] getMaxLength() {
			return maxLength;
		}
		public void setMaxLength(int[] maxLength) {
			this.maxLength = maxLength;
		}
	}
	
	/**
	 * 打印荣誉称号获奖名单
	 * @param ZjcmPjpyModel model
	 * @param OutputStream os
	 * @author lr
	 * @throws Exception
	 */
	public void printRychhjmd(ZjcmPjpyModel model, OutputStream os)
			throws Exception {
		//荣誉称号名称
		String rychmc = dao.getOneValue("rychdmb", "rychdm", "rychmc", model.getRychdm());
		WritableWorkbook wwb = Workbook.createWorkbook(os);
		WritableSheet ws = wwb.createSheet(rychmc + "获得者名单", 0);
		
		//学院列表
		List<String[]> xymcList = dao.queryRychXyList(model);
		//获奖姓名
		List<String[]> xmList = dao.queryRychhjxmxx(model);
		
		//荣誉称号名称样式
		WritableCellFormat jxjFormat = new WritableCellFormat();
		WritableFont jxjFont = new WritableFont(WritableFont.ARIAL,13);
		jxjFont.setBoldStyle(WritableFont.BOLD);
		jxjFormat.setFont(jxjFont);
		jxjFormat.setAlignment(Alignment.LEFT);
		
		
		//输出第一行的表头
		ws.addCell(new Label(0, 0,  
						StringUtils.joinStr(StandardOperation.getXxmc(), 
										model.getXn(), 
										"学年第",
										dao.getXqMc(model.getXq())										, 
										"学期", 
										rychmc, 
										"获得者名单" 
										),
						jxjFormat
					)
		);
		ws.mergeCells(0, 0, 8, 0);
		
		//输出第一级奖学金名称和获奖人数
		if (!xmList.isEmpty()) {
			JxjExportProperties properties = new JxjExportProperties();
			String rychdm = model.getRychdm();
			//输出第二级将学院名称写到EXCEL中
			for (int index = 0; index < xymcList.size(); index++) {
				String[] xymcArr = xymcList.get(index);
				if (rychdm.equalsIgnoreCase(xymcArr[2])) {						
					String xy = xymcArr != null && xymcArr.length >= 4 ? xymcArr[1] : "";
					String xydm = xymcArr != null && xymcArr.length >= 4 ? xymcArr[0] : "";
					List<String> xmByXyList = new ArrayList<String>();//存放每个奖学金下面的每个学院的获奖名单
					for (String[] xmArr : xmList) {
						if (xmArr != null && xmArr.length >= 3
								&& xydm.equalsIgnoreCase(xmArr[1])
								&& rychdm.equalsIgnoreCase(xmArr[0])) {
							xmByXyList.add(xmArr[2]);
						}
					}
					if (xmByXyList == null || xmByXyList.size() <= 0) {
						continue;
					}
					//输出学院名称
					
					Label xymcCell = new Label(0,properties.row,xy + " (" +xymcArr[3] + "人)");
					xymcCell.setCellFormat(jxjFormat);
					ws.addCell(xymcCell);
					ws.mergeCells(0,properties.row, 8, properties.row);
					
					//输出第三级将学院下面的获奖名单输出
					writeJxjXm2Excel(ws, properties, xmByXyList,-1);//将获奖名单写入到EXCEL中
					properties.row++;
				}
			}
		}
		ExcelMethods.submitWritableWorkbookOperations(wwb);//向客户端输出
	}	
	
	/**
	 * 保存文理科设置
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public Boolean saveWlk(ZjcmPjpyModel model) throws Exception {
		boolean flag = false;
		flag = dao.saveWlk(model);
		return flag;
	}
	
	/**
	 * 奖学金条件
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public String[] Jxjtj(ZjcmPjpyModel model) throws Exception {
		
		// 获得所申请奖学金所设置的条件

		String tableName = "view_zjcm_pjpy_tjsz";
		String query = " where xn = ? and xq = ? and lx = ? and jxjdm = ?";
		String xn = model.getXn();
		String xq = model.getXq();
		String lx = model.getLx();
		String jxjdm = "jxj".equalsIgnoreCase(lx) ? model.getJxjdm() : model.getRychdm();
		String[] inPutList = new String[] { xn, xq, lx, jxjdm };
		String[] colList = new String[] { "zdsm", "tjzd", "tjlx", "tjz", "bjlx" };

		List<HashMap<String, String>> tjList = CommonQueryDAO
				.commonQueryforList(tableName, query, inPutList, colList, "");

		String errXh[] = null;
		
		// 获得班级人数
		String bjdm = model.getBjdm();
		String bjrs = dao.getBjRs(bjdm).get("num");
		
		// 获得申报班级全部学生
		String[] pjxh = getZdXh("bj", bjdm);
		
		if (tjList != null && tjList.size() > 0) {

			for (int i = 0; i < tjList.size(); i++) {
				HashMap<String, String> tj = tjList.get(i);
				String tjzd = tj.get("tjzd"); // 条件字段
				String tjlx = tj.get("tjlx"); // 条件类型
				String tjz = tj.get("tjz"); // 条件值
				// TODO xsdjksb数据与jxjtjzdb数据要统一
				String zdsm = tj.get("zdsm");// 字段说明
				String bjlx = tj.get("bjlx"); // 班级类型

				// -------------判断德育排名是否满足条件--------------
				if ("dypm".equalsIgnoreCase(tjzd)) {
					errXh = isPmTj(model, pjxh, "dypm", tjlx, tjz, bjrs);
					pjxh = checkCfsj(pjxh, errXh);
				}
				// -------------判断智育排名是否满足条件--------------
				else if ("zypm".equalsIgnoreCase(tjzd)) {
					errXh = isPmTj(model, pjxh, "zypm", tjlx, tjz, bjrs);
					pjxh = checkCfsj(pjxh, errXh);
				}
				// -------------判断体育排名是否满足条件--------------
				else if ("typm".equalsIgnoreCase(tjzd)) {
					errXh = isPmTj(model, pjxh, "typm", tjlx, tjz, bjrs);
					pjxh = checkCfsj(pjxh, errXh);
				}
				// -------------判断能力排名是否满足条件--------------
				else if ("nlpm".equalsIgnoreCase(tjzd)) {
					errXh = isPmTj(model, pjxh, "nlpm", tjlx, tjz, bjrs);
					pjxh = checkCfsj(pjxh, errXh);
				}
				// -------------判断综合排名是否满足条件--------------
				else if ("zhpm".equalsIgnoreCase(tjzd)) {
					errXh = isPmTj(model, pjxh, "zhpm", tjlx, tjz, bjrs);
					pjxh = checkCfsj(pjxh, errXh);
				}
				// -------------判断不及格科目是否满足条件--------------
				else if ("bjgkm".equalsIgnoreCase(tjzd)) {
					errXh = isBjg(model);
					pjxh = checkCfsj(pjxh, errXh);
				}
				// -------------判断违纪处分是否满足条件--------------
				else if ("jg".equalsIgnoreCase(tjzd)
						|| "yzjg".equalsIgnoreCase(tjzd)
						|| "jig".equalsIgnoreCase(tjzd)
						|| "lxck".equalsIgnoreCase(tjzd)) {
					
					WjcfZjcmDAO wjcfDao = new WjcfZjcmDAO();
					
					// 处分名称
					String cflbmc = "";

					if ("jg".equalsIgnoreCase(tjzd)) {
						cflbmc = "警告";
					} else if ("yzjg".equalsIgnoreCase(tjzd)) {
						cflbmc = "严重警告";
					} else if ("jig".equalsIgnoreCase(tjzd)) {
						cflbmc = "记过";
					} else if ("lxck".equalsIgnoreCase(tjzd)) {
						cflbmc = "留校查看";
					}
					
					// pjxh = checkCfsj(pjxh, errXh);
					// errXh = isWjcf(model);
					errXh = wjcfDao.queryWjcfxxByArrXh(pjxh, xn, xq, cflbmc);
					pjxh = checkCfsj(pjxh, errXh);
				}
				// -------------判断等级考试成绩是否满足条件--------------
				else if ("yy4j".equalsIgnoreCase(tjzd)// 英语四级
						|| "yy3j".equalsIgnoreCase(tjzd)// 英语三级
						|| "zy4j".equalsIgnoreCase(tjzd)// 专业英语四级
						|| "jsj1j".equalsIgnoreCase(tjzd)// 计算机一级
						|| "jsj2j".equalsIgnoreCase(tjzd)) { // 计算机二级
					errXh = isDjks(model, pjxh, zdsm, tjlx, tjz, bjlx);
					pjxh = checkCfsj(pjxh, errXh);
				}
				// -------------判断学习进步是否满足条件--------------
				else if ("xxjb".equalsIgnoreCase(tjzd)) {
					errXh = isXxjb(model, pjxh, tjlx, tjz, bjrs);
					pjxh = checkCfsj(pjxh, errXh);
				}
				// -------------判断学风情况(校级通报，旷课)是否满足条件--------------
				else if ("xjtb".equalsIgnoreCase(tjzd)) {
					errXh = isXjtb(model, pjxh);
					pjxh = checkCfsj(pjxh, errXh);
				} 
				// -------------判断学风情况(旷课)是否满足条件--------------
				else if ("kkjs".equalsIgnoreCase(tjzd)) {
					errXh = isKkjs(model, pjxh);
					pjxh = checkCfsj(pjxh, errXh);				
				}
			}
		}
		
		return pjxh;
	}
	
	/**
	 * 判断排名条件
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public String[] isPmTj(ZjcmPjpyModel model, String[] pjxh, String pmlx,
			String tjlx, String tjz, String bjrs) throws Exception {
		
		int n = 0;
		String errXh[] = new String[pjxh.length];
//		String msg = "";
//
//		if ("dypm".equalsIgnoreCase(pmlx)) {
//			msg = "该学生德育分排名不满足本奖学金的申请条件,请确认！！！";
//		} else if ("zypm".equalsIgnoreCase(pmlx)) {
//			msg = "该学生智育分排名不满足本奖学金的申请条件,请确认！！！";
//		} else if ("typm".equalsIgnoreCase(pmlx)) {
//			msg = "该学生体育分排名不满足本奖学金的申请条件,请确认！！！";
//		} else if ("nlpm".equalsIgnoreCase(pmlx)) {
//			msg = "该学生能力分排名不满足本奖学金的申请条件,请确认！！！";
//		} else if ("zhpm".equalsIgnoreCase(pmlx)) {
//			msg = "该学生综合分排名不满足本奖学金的申请条件,请确认！！！";
//		}	
		
		//获得排名列表
		List<HashMap<String, String>> pmList = getPmList(model, pmlx);

		if (pmList != null && pmList.size() > 0) {
			if (pjxh != null && pjxh.length > 0) {
				for (int i = 0; i < pmList.size(); i++) {
					HashMap<String, String> map = pmList.get(i);
					for (int j = 0; j < pjxh.length; j++) {
						if (map.get("xh").equalsIgnoreCase(pjxh[j])) {
							// 学生班级排名
							float pm = Float.parseFloat(map.get("pm"));
							// 奖学金条件排名
							float tjpm = Float.parseFloat(bjrs)
									* Float.parseFloat(tjz) / 100;
							tjpm = Math.round(tjpm);
							// 判断两排名是否符合条件
							if (">".equalsIgnoreCase(tjlx)) {
								if (pm >= tjpm) {
									errXh[n] = pjxh[j];
									n++;
								}
							} else if (">=".equalsIgnoreCase(tjlx)) {
								if (pm > tjpm) {
									errXh[n] = pjxh[j];
									n++;
								}
							} else if ("=".equalsIgnoreCase(tjlx)) {
								if (pm != tjpm) {
									errXh[n] = pjxh[j];
									n++;
								}
							} else if ("<".equalsIgnoreCase(tjlx)) {
								if (pm <= tjpm) {
									errXh[n] = pjxh[j];
									n++;
								}
							} else if ("<".equalsIgnoreCase(tjlx)) {
								if (pm < tjpm) {
									errXh[n] = pjxh[j];
									n++;
								}
							}
						}
					}
				}
			}
		}
		
		return errXh;
	}
	
	/**
	 * 获得综合分相关排名
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getPmList(ZjcmPjpyModel model,
			String pmlx) throws Exception {

		String table = getZhfTable(model);
		
		List<HashMap<String, String>> dypmList = new ArrayList<HashMap<String,String>>();//德育分排名列表
		List<HashMap<String, String>> zypmList = new ArrayList<HashMap<String,String>>();//智育分排名列表
		List<HashMap<String, String>> typmList = new ArrayList<HashMap<String,String>>();//体育分排名列表
		List<HashMap<String, String>> nlpmList = new ArrayList<HashMap<String,String>>();//能力分排名列表
		List<HashMap<String, String>> zhpmList = new ArrayList<HashMap<String,String>>();//综合分排名列表
		
		List<HashMap<String, String>> list = dao.getZhfPmList(table, model);
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				HashMap<String, String> map = new HashMap<String, String>();

				String pjxh = list.get(i).get("xh");
				String dypm = list.get(i).get("dypm");
				String zypm = list.get(i).get("zypm");
				String typm = list.get(i).get("typm");
				String nlpm = list.get(i).get("nlpm");
				String zhpm = list.get(i).get("zhpm");

				if ("dypm".equalsIgnoreCase(pmlx)) {
					map.put("xh", pjxh);
					map.put("pm", dypm);
					dypmList.add(map);
				} else if ("zypm".equalsIgnoreCase(pmlx)) {
					map.put("xh", pjxh);
					map.put("pm", zypm);
					zypmList.add(map);
				} else if ("typm".equalsIgnoreCase(pmlx)) {
					map.put("xh", pjxh);
					map.put("pm", typm);
					typmList.add(map);
				} else if ("nlpm".equalsIgnoreCase(pmlx)) {
					map.put("xh", pjxh);
					map.put("pm", nlpm);
					nlpmList.add(map);
				} else if ("zhpm".equalsIgnoreCase(pmlx)) {
					map.put("xh", pjxh);
					map.put("pm", zhpm);
					zhpmList.add(map);
				}			
			}
		}
		
		if ("dypm".equalsIgnoreCase(pmlx)) {
			return dypmList;
		} else if ("zypm".equalsIgnoreCase(pmlx)) {
			return zypmList;
		} else if ("typm".equalsIgnoreCase(pmlx)) {
			return typmList;
		} else if ("nlpm".equalsIgnoreCase(pmlx)) {
			return nlpmList;
		} else if ("zhpm".equalsIgnoreCase(pmlx)) {
			return zhpmList;
		}

		return null;
	}
	
	/**
	 * 判断是否存在不及格科目的学生
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public String[] isBjg(ZjcmPjpyModel model) throws Exception {

		String[] errXh = null;

		List<HashMap<String, String>> list = dao.getBjgList(model);

		if (list != null && list.size() > 0) {
			errXh = new String[list.size()];
			for (int i = 0; i < list.size(); i++) {
				errXh[i] = list.get(i).get("xh");
			}
			// msg = "该学生存在不及格科目，不满足本奖学金的申请条件,请确认！！！";
		}

		return errXh;
	}
	
	/**
	 * 判断是否存在违纪处分
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public String[] isWjcf(ZjcmPjpyModel model) throws Exception {

		String[] errXh = null;

		List<HashMap<String, String>> list = dao.getWjcfList(model);

		if (list != null && list.size() > 0) {
			errXh = new String[list.size()];
			for (int i = 0; i < list.size(); i++) {
				errXh[i] = list.get(i).get("xh");
			}
			// msg = "该学生存在违纪处分，不满足本奖学金的申请条件,请确认！！！";
		}

		return errXh;
	}
	
	/**
	 * 判断等级考试是否通过
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public String[] isDjks(ZjcmPjpyModel model, String[] pjxh, String zdsm,
			String tjlx, String tjz, String bjlx) throws Exception {
		// TODO 文理科还未加
		int n = 0;
		String errXh[] = null;
		String bjdm = model.getBjdm();
		String wlk = getOneValue("zjcm_wlkb", "lx", "wlkbjdm", bjdm);

		if (wlk.equalsIgnoreCase(bjlx)) {
			List<HashMap<String, String>> list = dao.getDjksList(model, pjxh);

			if (list != null && list.size() > 0) {

				errXh = new String[list.size()];

				for (int i = 0; i < list.size(); i++) {

					HashMap<String, String> map = list.get(i);
					// msg = "该学生存在" + kcmc + "不及格，不满足本奖学金的申请条件,请确认！！！";

					if (zdsm.equalsIgnoreCase(map.get("djksmc"))) {
						int cj = Integer.parseInt(map.get("cj"));
						int tjcj = Integer.parseInt(tjz);
						// 判断两排名是否符合条件
						if (">".equalsIgnoreCase(tjlx)) {
							if (cj <= tjcj) {
								errXh[n] = map.get("xh");
								n++;
							}
						} else if (">=".equalsIgnoreCase(tjlx)) {
							if (cj < tjcj) {
								errXh[n] = map.get("xh");
								n++;
							}
						} else if ("=".equalsIgnoreCase(tjlx)) {
							if (cj != tjcj) {
								errXh[n] = map.get("xh");
								n++;
							}
						} else if ("<".equalsIgnoreCase(tjlx)) {
							if (cj >= tjcj) {
								errXh[n] = map.get("xh");
								n++;
							}
						} else if ("<=".equalsIgnoreCase(tjlx)) {
							if (cj > tjcj) {
								errXh[n] = map.get("xh");
								n++;
							}
						}
					}
				}
			}
		}
		return errXh;
	}
	
	/**
	 * 判断学习进步是否满足
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public String[] isXxjb(ZjcmPjpyModel model, String[] pjxh, String tjlx,
			String tjz, String bjrs) throws Exception {

		DAO dao = DAO.getInstance();
		
		// 评奖学年
		String xn = model.getXn();
		// 评奖学期
		String xq = model.getXq();
		
		int n = 0;
		String errXh[] = new String[pjxh.length];
		
		// 获得评奖学年的智育排名列表
		List<HashMap<String, String>> nowZyList = getPmList(model, "zypm");
		
		// 获得前一学期的智育排名列表
		HashMap<String, String> map = new HashMap<String, String>();

		map.put("xn", xn);
		map.put("xq", xq);
		map = dao.getBeforeXq(map);

		String befXn = map.get("xn");
		String befXq = map.get("xq");

		ZjcmPjpyModel befModel = new ZjcmPjpyModel();

		befModel.setXn(befXn);
		befModel.setXq(befXq);
		befModel.setNj(model.getNj());
		befModel.setXydm(model.getXydm());
		befModel.setZydm(model.getZydm());
		befModel.setBjdm(model.getBjdm());
		List<HashMap<String, String>> befZyList = getPmList(befModel, "zypm");
		
		// 判断智育分进步情况
		if (nowZyList != null && nowZyList.size() > 0) {
			if (befZyList != null && befZyList.size() > 0) {
				if (pjxh != null && pjxh.length > 0) {
					for (int i = 0; i < nowZyList.size(); i++) {
						HashMap<String, String> nowMap = nowZyList.get(i);
						for (int j = 0; j < befZyList.size(); j++) {
							HashMap<String, String> befMap = befZyList.get(j);
							for (int k = 0; k < pjxh.length; k++) {
								if (nowMap.get("xh").equalsIgnoreCase(befMap.get("xh"))
									&& befMap.get("xh").equalsIgnoreCase(pjxh[k])) {
									// 前次排名
									float befpm = Float.parseFloat(befMap.get("pm"));
									// 本次排名
									float nowpm = Float.parseFloat(nowMap.get("pm"));
									// 进步排名
									float pm = befpm - nowpm;
									// 奖学金条件排名
									float tjpm = Float.parseFloat(bjrs)
											* Float.parseFloat(tjz) / 100;
									tjpm = Math.round(tjpm);
									// 判断两排名是否符合条件
									if (">".equalsIgnoreCase(tjlx)) {
										if (pm <= tjpm) {
											errXh[n] = pjxh[j];
											n++;
										}
									} else if (">=".equalsIgnoreCase(tjlx)) {
										if (pm < tjpm) {
											errXh[n] = pjxh[j];
											n++;
										}
									} else if ("=".equalsIgnoreCase(tjlx)) {
										if (pm != tjpm) {
											errXh[n] = pjxh[j];
											n++;
										}
									} else if ("<".equalsIgnoreCase(tjlx)) {
										if (pm >= tjpm) {
											errXh[n] = pjxh[j];
											n++;
										}
									} else if ("<".equalsIgnoreCase(tjlx)) {
										if (pm > tjpm) {
											errXh[n] = pjxh[j];
											n++;
										}
									}
								}
							}
						}
					}
				}
			}
		}
		return errXh;
	}
	
	/**
	 * 判断校级通报是否满足
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public String[] isXjtb(ZjcmPjpyModel model, String[] pjxh) throws Exception {

		String xn = model.getXn();// 评奖学年
		String xq = model.getXq();// 评奖学期

		String errXh[] = new String[pjxh.length];

		PjpyXfjsService xfService = new PjpyXfjsService();

		List<HashMap<String, String>> tbList = xfService.getXskqqk("", xn, xq,
				"xx");

		// 获得有校级通报者学号
		if (pjxh != null && pjxh.length > 0) {
			
			if (tbList != null && tbList.size() > 0) {
				
				for (int i = 0; i < pjxh.length; i++) {
					
					for (int j = 0; j < tbList.size(); j++) {
						
						HashMap<String, String> map = tbList.get(j);
						
						String xh = map.get("xh");
						
						if (pjxh[i].equalsIgnoreCase(xh)) {
							
							errXh[i] = pjxh[i];
							
						}
					}
				}
			}
		}

		return errXh;
	}
	
	/**
	 * 判断旷课节数是否满足
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public String[] isKkjs(ZjcmPjpyModel model, String[] pjxh) throws Exception {
			
		String xn = model.getXn();// 评奖学年
		String xq = model.getXq();// 评奖学期

		String errXh[] = new String[pjxh.length];
		
		PjpyXfjsService xfService = new PjpyXfjsService();

		 List<HashMap<String, String>> kkList = xfService.getXskqqk("", xn, xq,
				"xy");

		// 获得有旷课者学号
		if (pjxh != null && pjxh.length > 0) {
			
			if (kkList != null && kkList.size() > 0) {
				
				for (int i = 0; i < pjxh.length; i++) {
					
					for (int j = 0; j < kkList.size(); j++) {
						
						HashMap<String, String> map = kkList.get(j);
						
						String xh = map.get("xh");
						String kkjs = map.get("kkjs");
						
						// 旷课节数>0者学号
						if (pjxh[i].equalsIgnoreCase(xh) && !Base.isNull(kkjs)
								&& (Integer.parseInt(kkjs) > 0)) {

							errXh[i] = pjxh[i];

						}
					}
				}
			}
		}
		
		return errXh;
	}
	
	/**
	 * 判断用户是否是参评组用户
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public Boolean isCpz(ZjcmPjpyModel model) {
		return dao.isCpz(model);
	}

	/**
	 * 获得校内奖学金申报列表
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public ArrayList<String[]> getJxjSqXnList(ZjcmPjpyModel model)
			throws Exception {

		ArrayList<String[]> list = new ArrayList<String[]>();
		List<HashMap<String, String>> pmList = getZhfPmList(model);

		// 有资格评奖者学号
		String[] pjxh = Jxjtj(model);
		// 已获得兼得奖学金（荣誉称号）者学号
		String[] jdXh = getJxjjdXh(model);
		pjxh = checkCfsj(pjxh, jdXh);
		
		if (pmList != null && pmList.size() > 0) {
			if (pjxh != null && pjxh.length > 0) {
				for (int i = 0; i < pmList.size(); i++) {

					HashMap<String, String> map = pmList.get(i);

					String[] arr = new String[14];

					String xh = map.get("xh");// 学号
					String xm = map.get("xm");// 姓名
					String xb = map.get("xb");// 性别
					String dyzhf = map.get("dyzhf");// 德育折后分
					String zyzhf = map.get("zyzhf");// 智育折后分
					String tyzhf = map.get("tyzhf");// 体育折后分
					String nlzhf = map.get("nlzhf");// 能力折后分
					String zhf = map.get("zhf");// 综合分
					String dypm = map.get("dypm");// 德育排名
					String zypm = map.get("zypm");// 智育排名
					String typm = map.get("typm");// 体育排名
					String nlpm = map.get("nlpm");// 能力排名
					String zhpm = map.get("zhpm");// 综合排名
					String pk = xh;// 主键

					boolean flag = false;
					for (int j = 0; j < pjxh.length; j++) {
						if (pjxh[j].equalsIgnoreCase(xh)) {
							flag = true;
						}
					}

					if (flag) {
						arr[0] = pk;
						arr[1] = xh;
						arr[2] = xm;
						arr[3] = xb;
						arr[4] = dyzhf;
						arr[5] = dypm;
						arr[6] = zyzhf;
						arr[7] = zypm;
						arr[8] = tyzhf;
						arr[9] = typm;
						arr[10] = nlzhf;
						arr[11] = nlpm;
						arr[12] = zhf;
						arr[13] = zhpm;

						list.add(arr);
					}
				}
			}}
		return list;
	}
	
	/**
	 * 获得奖学金申请者其他信息
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public HashMap<String, String> getJxjsqInfo(ZjcmPjpyModel model)
			throws Exception {

		String xh = model.getXh();// 申请者学号
		String xn = model.getXn();// 评奖学年
		String xq = model.getXq();// 评奖学期
		String jxjdm = model.getJxjdm();// 奖学金代码
		String rychdm = model.getRychdm();// 荣誉称号代码

		// ----------------学生基本信息-------------------
		HashMap<String, String> map = getStuInfo(xh); 

		map.put("xn", xn);// 评奖学年
		map.put("xq", xq);// 评奖学期
		map.put("jxjdm", jxjdm);// 奖学金代码
		map.put("rychdm", rychdm);// 荣誉称号代码

		// ----------------学风模块接口-------------------
		PjpyXfjsService xfService = new PjpyXfjsService();
		
		List<HashMap<String, String>> kkList = getKkList(model);
		// xfService.getXskqqk(xh, xn, xq,"xy");
		List<HashMap<String, String>> tbList = xfService.getXskqqk(xh, xn, xq,
				"xx");

		// 旷课节数
		if (kkList != null && kkList.size() > 0) {
			map.put("kkjs", kkList.get(0).get("kkjs"));
		} else {
			map.put("kkjs", "无");
		}

		// 校级通报
		if (tbList != null && tbList.size() > 0) {
			map.put("xjtb", "有");
		} else {
			map.put("xjtb", "无");
		}

		// ---------------违纪处分接口-------------------
		WjcfZjcmDAO wjcfDAO = new WjcfZjcmDAO();
		WjcfZjcmModel wjModel = new WjcfZjcmModel();
		
		wjModel.setXh(xh);
		wjModel.setXn(xn);
		wjModel.setXq(xq);
		
		String wjInfo = wjcfDAO.queryStuLxckxx(wjModel);
		wjInfo = Base.isNull(wjInfo) ? "无" : wjInfo;
		
		map.put("wjInfo", wjInfo);

		// ---------------财务接口，有无拖欠学费-------------------
		//TODO
		
		// ---------------英语四级（计算机）成绩相关-------------------
		String tableName = "view_zjcm_djksxx";
		String query = " where xh = ? ";
		String[] inPutList = new String[] { xh };
		String[] colList = new String[] { "kcm", "cj" };
		
		List<HashMap<String, String>> djksList = CommonQueryDAO
				.commonQueryforList(tableName, query, inPutList, colList, "");
		
		String yycj = "";
		String pccj = "";
		
		if (djksList != null && djksList.size() > 0) {
			
			for (int i = 0; i < djksList.size(); i++) {
				HashMap<String, String> djks = djksList.get(i);
				String kcm = djks.get("kcm");// 课程名
				String cj = djks.get("cj");// 成绩
				if ("yy4j".equalsIgnoreCase(kcm)) {
					yycj += "英语四级（" + cj + "） ";
				} else if ("yy3j".equalsIgnoreCase(kcm)) {
					yycj += "英语三级（" + cj + "） ";
				} else if ("jsj1j".equalsIgnoreCase(kcm)) {
					pccj += "计算机一级（" + cj + "） ";
				} else if ("jsj2j".equalsIgnoreCase(kcm)) {
					pccj += "计算机二级（" + cj + "） ";
				}
			}
		}
		
		map.put("yycj", Base.isNull(yycj) ? "无" : yycj);
		map.put("pccj", Base.isNull(pccj) ? "无" : pccj);

		// ---------------成绩表相关-------------------
		String bjgs = dao.getBjgs(model);
		map.put("bjgs", bjgs);
		
		// --------------已获得奖学金，荣誉称号---------------
		
		DAO tyDao = DAO.getInstance();
		
		// 获得上一学年学期
		HashMap<String, String> xnxqMap = new HashMap<String, String>();
		xnxqMap.put("xn", xn);
		xnxqMap.put("xq", xq);
		xnxqMap = tyDao.getBeforeXq(xnxqMap);

		String befXn = xnxqMap.get("xn");
		String befXq = xnxqMap.get("xq");

		xnxqMap = new HashMap<String, String>();
		xnxqMap.put("bxn", xn);
		xnxqMap.put("bxq", xq);
		xnxqMap.put("sxn", befXn);
		xnxqMap.put("sxq", befXq);
		xnxqMap.put("xh", xh);
		
		List<HashMap<String, String>> ryList = dao.getJxjRyList(xnxqMap);
		
		String bxqry = "无";// 本学期获得奖学金（荣誉称号）
		String sxqry = "无";// 上学期获得奖学金（荣誉称号）
		
		if (ryList != null && ryList.size() > 0) {

			for (int i = 0; i < ryList.size(); i++) {
				// 本学期
				if ("bxq".equalsIgnoreCase(ryList.get(i).get("xq"))) {
					
					bxqry = ("无".equalsIgnoreCase(bxqry)) ? "" : bxqry;
					
					if (Base.isNull(bxqry)) {
						bxqry += ryList.get(i).get("rymc");
					} else {
						bxqry += "," + ryList.get(i).get("rymc");
					}
				}

				if ("sxq".equalsIgnoreCase(ryList.get(i).get("xq"))) {
					
					sxqry = ("无".equalsIgnoreCase(sxqry)) ? "" : sxqry;
					
					if (Base.isNull(sxqry)) {
						sxqry += ryList.get(i).get("rymc");
					} else {
						sxqry += "," + ryList.get(i).get("rymc");
					}
				}
			}

			bxqry = ("无".equalsIgnoreCase(bxqry)) ? "无" : bxqry;
			sxqry = ("无".equalsIgnoreCase(sxqry)) ? "无" : sxqry;
	
		}
		map.put("sxqry", sxqry);
		map.put("bxqry", bxqry);
		
		// ---------------获得综合分以及相关排名信息-------------------
		model.setXh("");
		model.setNj(map.get("nj"));
		model.setXydm(map.get("xydm"));
		model.setZydm(map.get("zydm"));
		model.setBjdm(map.get("bjdm"));
		List<HashMap<String, String>> pmList = getZhfPmList(model);

		if (pmList != null && pmList.size() > 0) {

			for (int i = 0; i < pmList.size(); i++) {

				HashMap<String, String> zhfPm = pmList.get(i);

				if (zhfPm.get("xh").equalsIgnoreCase(xh)) {
					map.put("dyf", zhfPm.get("dyf"));// 德育分
					map.put("dyzhf", zhfPm.get("dyzhf"));// 德育折后分
					map.put("dypm", zhfPm.get("dypm"));// 德育排名

					map.put("zyf", zhfPm.get("zyf"));// 智育分
					map.put("zyzhf", zhfPm.get("zyzhf"));// 德育折后分
					map.put("zypm", zhfPm.get("zypm"));// 德育排名

					map.put("tyf", zhfPm.get("tyf"));// 体育分
					map.put("tyzhf", zhfPm.get("tyzhf"));// 体育折后分
					map.put("typm", zhfPm.get("typm"));// 体育排名

					map.put("nlf", zhfPm.get("nlf"));// 能力分
					map.put("nlzhf", zhfPm.get("nlzhf"));// 能力折后分
					map.put("nlpm", zhfPm.get("nlpm"));// 能力排名

					map.put("zhf", zhfPm.get("zhf"));// 综合分
					map.put("zhpm", zhfPm.get("zhpm"));// 综合排名
				}
			}
		}

		return map;
	}

	/**
	 * 获得国家奖学金所需要的信息
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public HashMap<String, String> getGjjxjNeedInfo(ZjcmPjpyModel model)
			throws Exception {
		
		// TODO
		DAO tyDao = DAO.getInstance();

		String xh = model.getXh();// 申请者学号
		String xn = model.getXn();// 评奖学年
		String xq = model.getXq();// 评奖学期

		HashMap<String, String> infoMap = new HashMap<String, String>();
		
		// 获得上一学年学期
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("xn", xn);
		map.put("xq", xq);
		map = tyDao.getBeforeXq(map);

		String befXn = map.get("xn");
		String befXq = map.get("xq");

		map = new HashMap<String, String>();
		map.put("bxn", xn);
		map.put("bxq", xq);
		map.put("sxn", befXn);
		map.put("sxq", befXq);
		map.put("xh", xh);
		
		// 获得班级人数
		String bjdm = model.getBjdm();
		String bjrs = dao.getBjRs(bjdm).get("num");
		
		// 成绩排名情况
		List<HashMap<String, String>> cjpmList = dao.getCjPmQk(map);
		
		if (cjpmList != null && cjpmList.size() > 0) {
			for (int i = 0; i < cjpmList.size(); i++) {
				// 本学期成绩排名
				if ("bxq".equalsIgnoreCase(cjpmList.get(i).get("xq"))) {
					infoMap.put("bxqcj", cjpmList.get(i).get("cjpm") + "/"
							+ bjrs);
				}
				// 上学期成绩排名
				if ("sxq".equalsIgnoreCase(cjpmList.get(i).get("xq"))) {
					infoMap.put("sxqcj", cjpmList.get(i).get("cjpm") + "/"
							+ bjrs);
				}
			}
		}
		
		// 综合成绩，排名情况
		List<HashMap<String, String>> zhfPmList = getZhfPmList(model);// 本学期
		
		if (zhfPmList != null && zhfPmList.size() > 0) {
			infoMap.put("bxqzhf", zhfPmList.get(0).get("zhf"));
			infoMap.put("bxqzhfpm", zhfPmList.get(0).get("zhpm") + "/" + bjrs);
		}

		model.setXn(befXn);
		model.setXq(befXq);
		zhfPmList = getZhfPmList(model);// 上学期
		
		if (zhfPmList != null && zhfPmList.size() > 0) {
			infoMap.put("sxqzhf", zhfPmList.get(0).get("zhf"));
			infoMap.put("sxqzhfpm", zhfPmList.get(0).get("zhpm") + "/" + bjrs);
		}
		
		// 已获得奖学金，荣誉称号
		List<HashMap<String, String>> ryList = dao.getJxjRyList(map);
		
		if (ryList != null && ryList.size() > 0) {

			String bxqry = "";// 本学期获得奖学金（荣誉称号）
			String sxqry = "";// 上学期获得奖学金（荣誉称号）

			for (int i = 0; i < ryList.size(); i++) {
				// 本学期
				if ("bxq".equalsIgnoreCase(ryList.get(i).get("xq"))) {
					if (Base.isNull(bxqry)) {
						bxqry += ryList.get(i).get("rymc");
					} else {
						bxqry += "," + ryList.get(i).get("rymc");
					}
				}

				if ("sxq".equalsIgnoreCase(ryList.get(i).get("xq"))) {
					if (Base.isNull(bxqry)) {
						sxqry += ryList.get(i).get("rymc");
					} else {
						sxqry += "," + ryList.get(i).get("rymc");
					}
				}
			}

			String rymc = ((Base.isNull(bxqry)) ? "" : bxqry + "(本学期)")
					+ ((Base.isNull(sxqry)) ? "" : sxqry + "(上学期)");
			infoMap.put("rymc", rymc);
		}
		return infoMap;
	}

	/**
	 * 获得申报者全部学号
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public String[] getJxjsbz(ArrayList<String[]> list) {
		String[] sbzxh = null;
		if (list != null && list.size() > 0) {
			sbzxh = new String[list.size()];
			for (int i = 0; i < list.size(); i++) {
				String xh = list.get(i)[1];
				sbzxh[i] = xh;
			}
		}
		return sbzxh;
	}
	
	/**
	 * 获得需维护下拉框值
	 * 
	 * @param tableName(表名)
	 * @param dm(代码)
	 * @param mc(名称)
	 * @param msg(显示信息)
	 * @param pk(主键)
	 * @param pkValue(主键值)
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getPjpyList(String tableName,
			String dm, String mc, String msg, String pk, String pkValue) {
		return dao.getPjpyList(tableName, dm, mc, msg, pk, pkValue);
	}
	
	/**
	 * 获得奖学金兼得情况列表
	 * 
	 * @author luojw
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public List<HashMap<String, String>> getJxjjdList(ZjcmPjpyModel model)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		String tableName = "zjcm_jdsz";
		String[] colList = new String[] { "xn", "xq", "lx", "jxjdm", "fjdlx",
				"fjddm", "fjddm" };

		String[] queryList = new String[] { "xn", "xq", "lx", "jxjdm" };
		String[] queryLikeList = new String[] {};

		MakeQuery myQuery = new MakeQuery();
		myQuery.makeQuery(queryList, queryLikeList, model);
		String query = myQuery.getQueryString();

		List<HashMap<String, String>> wsdjList = CommonQueryDAO
				.commonQueryforList(tableName, query, myQuery.getInputList(),
						colList, "");
		return wsdjList;
	}
	
	/**
	 * 获得奖学金兼得者学号
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public String[] getJxjjdXh(ZjcmPjpyModel model) throws Exception {

		String[] jdXh = null;
		// 奖学金兼得情况
		List<HashMap<String, String>> jxjjdList = dao.getJxjjdList(model);

		// 存在奖学金兼得
		boolean jxjjd = false;
		// 存在荣誉称号兼得
		boolean rychjd = false;
		
		if (jxjjdList != null && jxjjdList.size() > 0) {
			
			String[] fjdlx = new String[jxjjdList.size()];
			String[] fjddm = new String[jxjjdList.size()];
			
			for (int i = 0; i < jxjjdList.size(); i++) {
				HashMap<String, String> map = jxjjdList.get(i);
				fjdlx[i] = map.get("fjdlx");
				fjddm[i] = map.get("fjddm");
				if ("jxj".equalsIgnoreCase(fjdlx[i])) {
					jxjjd = true;
				} else if ("rych".equalsIgnoreCase(fjdlx[i])) {
					rychjd = true;
				}
			}
			
			// 已经获得不可兼得奖学金（荣誉称号）的学号
			List<String> xhList = dao.getJxjjdXhList(model, jxjjd, rychjd);

			if (xhList != null && xhList.size() > 0) {
				jdXh = new String[xhList.size()];
				for (int i = 0; i < xhList.size(); i++) {
					jdXh[i] = xhList.get(i);
				}
			}
		}
		return jdXh;
	}
	
	/**
	 * 保存奖学金申报信息
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public Boolean saveJxjsb(ZjcmPjpyModel model, HttpServletRequest request)
			throws Exception {
		return dao.saveJxjsb(model, request);
	}	

	/**
	 * 奖学金金额汇总数据导出
	 * @param model
	 * @param os
	 */
	public void exportJxjjeHzData(ZjcmPjpyModel model, OutputStream os) throws Exception{
		//查询出所有的记录数
		List<String[]> data = dao.queryJxjjehzxx(model);
		//查询所有获奖学生的学号
		List<String[]> xhList = dao.queryJxjjeXhxx(model);
		//查询所有输出要的奖学金代码信息
		List<HashMap<String, String>> dmList = dao.queryDmList();
		List<String[]> rs = new ArrayList<String[]>();
		
		WritableWorkbook wwb = Workbook.createWorkbook(os);
		WritableSheet ws = wwb.createSheet("奖学金金额汇总表", 0);
		//写入第一级表头
		writeYjTitle(model, ws);
		//写入二级表头
		writeEjTitle(ws);
		
		//通过学号，获奖数据，奖学金代码组装要输出的数据 并返回总金额
		float zje = appendData(data, xhList, dmList, rs);
		//输出获奖金额数据
		WritableCellFormat format = ExcelMB.getWritableCellFormat(10, false,
				Alignment.CENTRE, VerticalAlignment.CENTRE, Border.ALL);
		ExcelMB.writeDataToCellByIterator(ws, rs, 0, 2, format);
		//合并班级单元格
		ExcelMB.mergeCellsData(rs, ws, 0, 2, format);
		
		//输出总计金额
		int size = !rs.isEmpty() ? rs.size() : 0;
		ws.addCell(new Label(0, 2+size, "总计", format));
		ws.mergeCells(0, 2+size, 6, 2+size);
		ws.addCell(new Label(7, 2+size, String.valueOf(zje), format));
		
		//输出备注信息
		ws.addCell(new Label(0, 3+size, " 备注：请仔细核对学生姓名和奖项、金额、卡号是否正确，" 
				+ "有否遗漏和增添，有问题处请在原表上修改，最后以纸质版12月25日（周五）上午10：00之前上交" 
				+ "，谢谢！是否全部正确（请写清楚正确还是不正确）：核对人签名：             核对时间：" 
				+ "            学院：（盖章）", format));
		ws.mergeCells(0, 3+size, 9, 5+size);
		
		//设置第1,2,8,9单元格宽度
		ws.setColumnView(0, 17);
		ws.setColumnView(1, 9);
		ws.setColumnView(7, 12);
		ws.setColumnView(8, 20);
		
		ExcelMethods.submitWritableWorkbookOperations(wwb);
		
	}

	//通过学号，获奖数据，奖学金代码组装要输出的数据
	private float appendData(List<String[]> data, List<String[]> xhList,
			List<HashMap<String, String>> dmList, List<String[]> rs) {
		float zje = 0;
		if (!xhList.isEmpty() && !dmList.isEmpty() && !data.isEmpty()) {
			for (String[] arr : xhList) {
				if (arr != null && arr.length >= 4) {
					String[] oneArr = new String[]{"", "", "", "", "", "", "", "", "", ""};
					oneArr[0] = arr[2];
					oneArr[1] = arr[1];
					float je = 0;
					for (HashMap<String, String> map : dmList) {
						if (!map.isEmpty()) {
							for (String[] oneData : data) {
								//通过条件获取奖学金名称
								je = getJxjmcByCondition(arr, oneArr, je, map, oneData);
							}
						}
					}
					oneArr[7] = je != 0 ? String.valueOf(je) : "";
					oneArr[8] = arr[3];
					oneArr[9] = "";
					//如果为金额0 则输出为空
					oneArr[2] = StringUtils.isNotNull(oneArr[2]) ? oneArr[2]
							.substring(0, oneArr[2].length() - 1) : "";
					oneArr[3] = StringUtils.isNotNull(oneArr[3]) ? oneArr[3]
							.substring(0, oneArr[3].length() - 1) : "";
					oneArr[4] = StringUtils.isNotNull(oneArr[4]) ? oneArr[4]
							.substring(0, oneArr[4].length() - 1) : "";
					oneArr[5] = StringUtils.isNotNull(oneArr[5]) ? oneArr[5]
							.substring(0, oneArr[5].length() - 1) : "";
					oneArr[6] = StringUtils.isNotNull(oneArr[6]) ? oneArr[6]
							.substring(0, oneArr[6].length() - 1) : "";
					//姓名不为空且其中任何一个奖项不为空则加入数据
					if (StringUtils.isNotNull(oneArr[1])
							&& (StringUtils.isNotNull(oneArr[2])
									|| StringUtils.isNotNull(oneArr[3])
									|| StringUtils.isNotNull(oneArr[4])
									|| StringUtils.isNotNull(oneArr[5]) 
									|| StringUtils.isNotNull(oneArr[6]))) {
						
						rs.add(oneArr);
					}
					
					//计算总金额
					zje += je;
				}
			}
		}
		return zje;
	}

	//通过条件得到奖学金名称
	private float getJxjmcByCondition(String[] arr, String[] oneArr, float je,
			HashMap<String, String> map, String[] oneData) {
		if (oneData != null && oneData.length > 0
				&& arr[0].equalsIgnoreCase(oneData[1])
				&& map.get("dm").equalsIgnoreCase(oneData[3])) {
			if ("1".equalsIgnoreCase(map.get("lx"))
					&& map.get("dm").equalsIgnoreCase(oneData[3])) {
				oneArr[2] += map.get("mc");
				oneArr[2] += ",";
			} else if ("2".equalsIgnoreCase(map.get("lx"))
					&& map.get("dm").equalsIgnoreCase(oneData[3])) {
				oneArr[3] += map.get("mc");
				oneArr[3] += ",";
			} else if ("3".equalsIgnoreCase(map.get("lx"))
					&& map.get("dm").equalsIgnoreCase(oneData[3])) {
				oneArr[4] += map.get("mc");
				oneArr[4] += ",";
			} else if ("4".equalsIgnoreCase(map.get("lx"))
					&& map.get("dm").equalsIgnoreCase(oneData[3])) {
				oneArr[5] += map.get("mc");
				oneArr[5] += ",";
			} else if ("5".equalsIgnoreCase(map.get("lx"))
					&& map.get("dm").equalsIgnoreCase(oneData[3])) {
				oneArr[6] += map.get("mc");
				oneArr[6] += ",";
			}
			je += StringUtils.isNotNull(oneData[5]) ? Float
					.parseFloat(oneData[5]) : 0;
		}
		return je;
	}

	//写入二级表头信息
	private void writeEjTitle(WritableSheet ws) throws Exception {
		WritableCellFormat format = ExcelMB.getWritableCellFormat(12, true,
				Alignment.CENTRE, VerticalAlignment.CENTRE, Border.ALL);
		String[] title = { "班级", "姓名", "院内奖学金获得者（一、二、三等）", "三好学生获得者",
						   "优秀学生干部获得者", "单项奖获得者", "院外奖学金获得者", "发放金额", 
						   "学生银行卡号", "备注" };
		ExcelMB.writeEjTitleToCell(ws, title, 0, 1, format);
	}

	//写入第一级表头到EXCEL中
	private void writeYjTitle(ZjcmPjpyModel model, WritableSheet ws) throws Exception {
		//第一级表头样式
		WritableCellFormat format = ExcelMB.getWritableCellFormat(14, true,
				Alignment.CENTRE, VerticalAlignment.CENTRE, Border.ALL);
		
		StringBuilder title1 = new StringBuilder(Base.xxmc)
							.append(model.getXn())
							.append("学年第")
							.append(model.getXq())
							.append("学期");
		title1.append(model.getXymc());
		title1.append("(");
		if (StringUtils.isNotNull(model.getNj())) {
			title1.append(model.getNj());
			title1.append("年级");
		}
		title1.append("学生");
		if (StringUtils.isNotNull(model.getYhmc())) {
			title1.append(model.getYhmc());
		}
		title1.append(")");
		title1.append("评奖评优名单汇总表");
		
		ExcelMB.writeTitleToCell(ws, title1.toString(), 0, 0, 9, 0, format);
	}

	/**
	 * 保存奖学金（荣誉称号）兼得情况
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public Boolean saveJdqk(ZjcmPjpyModel model) throws Exception {

		boolean flag = false;
		
		String[] arrzd = new String[] { "fjdlx", "fjddm" };
		String[] onezd = new String[] { "xn", "xq", "lx", "jxjdm" };
		String pk = "xn||xq||lx||jxjdm";
		String[] jxjjd = model.getJxjjd();
		String[] rychjd = model.getRychjd();

		String tableName = model.getTableName();
		String xn = model.getXn();// 评奖学年
		String xq = model.getXq();// 评奖学期
		String lx = model.getLx();// 类型
		String jxjdm = model.getJxjdm();// 奖学金代码
		String rychdm = model.getRychdm();// 荣誉称号代码
		
		// 不可兼得的奖学金以及荣誉称号总数
		int num = ((jxjjd != null && jxjjd.length > 0) ? jxjjd.length : 0)
				+ ((rychjd != null && rychjd.length > 0) ? rychjd.length
						: 0);

		String[] pkValue = new String[num];// 主键
		String[] fjdlx = new String[num];// 非兼得类型
		String[] fjddm = new String[num];// 非兼得代码

		// 确认被比较项目为奖学金还是荣誉称号
		jxjdm = ("rych".equalsIgnoreCase(lx)) ? rychdm : jxjdm;

		int n = 0;

		// 不可兼得奖学金
		if (jxjjd != null && jxjjd.length > 0) {
			for (int i = 0; i < jxjjd.length; i++) {
				pkValue[n] = xn + xq + lx + jxjdm;
				fjdlx[n] = "jxj";
				fjddm[n] = jxjjd[i];
				n++;
			}
		}

		// 不可兼得荣誉称号
		if (rychjd != null && rychjd.length > 0) {
			for (int i = 0; i < rychjd.length; i++) {
				pkValue[n] = xn + xq + lx + jxjdm;
				fjdlx[n] = "rych";
				fjddm[n] = rychjd[i];
				n++;
			}
		}

		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(tableName);
		saveForm.setArrzd(arrzd);
		saveForm.setOnezd(onezd);
		saveForm.setPk(pk);
		saveForm.setPkValue(pkValue);

		// 构建数据
		model.setXn(xn);
		model.setXq(xq);
		model.setLx(lx);
		model.setJxjdm(jxjdm);
		model.setFjdlx(fjdlx);
		model.setFjddm(fjddm);
		
		flag = savePjpy(saveForm, model);

		if(flag){
			flag = saveJxjQtJd(xn, xq, fjdlx, fjddm, lx, jxjdm, tableName);
		}
		
		return flag;
	}
	
	/**
	 * 保存奖学金（荣誉称号）其他兼得情况
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public Boolean saveJxjQtJd(String xn, String xq, String[] fjdlx,
			String[] fjddm, String lx, String jxjdm, String tableName)
			throws Exception {
		return dao.saveJxjQtJd(xn, xq, fjdlx, fjddm, lx, jxjdm, tableName);
	}
	
	/**
	 * 撤销兼得情况
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public Boolean cxJdsz(ZjcmPjpyModel model) throws Exception {

		boolean flag = false;

		String tableName = model.getTableName();

		String xn = model.getXn();// 评奖学年
		String xq = model.getXq();// 评奖学期
		String lx = model.getLx();// 类型
		String jxjdm = model.getJxjdm();// 奖学金代码
		String rychdm = model.getRychdm();// 荣誉称号代码
		// 确认被比较项目为奖学金还是荣誉称号
		jxjdm = ("rych".equalsIgnoreCase(lx)) ? rychdm : jxjdm;

		String pk = "xn||xq||lx||jxjdm";
		String pkValue = xn + xq + lx + jxjdm;

		// 确认被比较项目为奖学金还是荣誉称号
		jxjdm = ("rych".equalsIgnoreCase(lx)) ? rychdm : jxjdm;

		SaveForm saveForm = new SaveForm();

		saveForm.setTableName(tableName);
		saveForm.setPk(pk);
		saveForm.setPkValue(new String[] { pkValue });

		flag = delPjpy(saveForm, model);

		if (flag) {
			flag = dao.cxCxjQtJd(xn, xq, lx, jxjdm, tableName);
		}

		return flag;
	}
	
	/**
	 * 保存参评组信息
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public Boolean saveCpzInfo(PjpyTyForm model) throws Exception {

		String[] arrzd = new String[] { "cpxy", "zwdm" };
		String[] onezd = new String[] { "xn", "xq", "zhfkg", "jxjkg" };
		String pk = "xn||xq||cpxy";

		// 操作表
		String tableName = model.getTableName();
		// 职务代码
		String[] zwdm = model.getZwdm();
		// 初始化学年学期
		String xn = Base.isNull(model.getXn()) ? Base.getJxjsqxn() : model
				.getXn();
		String xq = Base.isNull(model.getXq()) ? Base.getJxjsqxq() : model
				.getXq();
		// 学院代码
		String xydm = model.getXydm();
		String[] pkValue = new String[zwdm.length];
		String[] cpxy = new String[zwdm.length];
		String[] cpzw = new String[zwdm.length];
		// 构建主键,学院列表
		int n = 0;
		if (Base.isNull(xydm)) {// 没选学院，保存全部学院
			List<HashMap<String, String>> xyList = Base.getXyList();
			if (xyList != null && xyList.size() > 0) {
				pkValue = new String[zwdm.length * xyList.size()];
				cpxy = new String[zwdm.length * xyList.size()];
				cpzw = new String[zwdm.length * xyList.size()];
				for (int i = 0; i < xyList.size(); i++) {
					for (int j = 0; j < zwdm.length; j++) {
						pkValue[n] = xn + xq + xyList.get(i).get("xydm");
						cpxy[n] = xyList.get(i).get("xydm");
						cpzw[n] = zwdm[j];
						n++;
					}
				}
			}
		} else {// 选择学院，保存单一学院
			for (int i = 0; i < zwdm.length; i++) {
				pkValue[i] = xn + xq + xydm;
				cpxy[i] = xydm;
				cpzw[i] = zwdm[i];
			}
		}

		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(tableName);
		saveForm.setArrzd(arrzd);
		saveForm.setOnezd(onezd);
		saveForm.setPk(pk);
		saveForm.setPkValue(pkValue);

		model.setCpxy(cpxy);
		model.setZwdm(cpzw);

		return savePjpy(saveForm, model);

	}
	
	/**
	 * 撤销参评组信息
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public Boolean cxCpzInfo(PjpyTyForm model) throws Exception {

		String pk = "xn||xq||cpxy||zwdm";

		// 操作表
		String tableName = model.getTableName();
		// 职务列表
		List<HashMap<String, String>> zwList = dao.getPjpyList("sxjy_bjgbzlb",
				"bjgbdm", "bjgbmc", "", "", "");
		zwList.remove(0);
		int num = (zwList != null && zwList.size() > 0) ? zwList.size() : 0;
		// 初始化学年学期
		String xn = Base.isNull(model.getXn()) ? Base.getJxjsqxn() : model
				.getXn();
		String xq = Base.isNull(model.getXq()) ? Base.getJxjsqxq() : model
				.getXq();
		// 学院代码
		String xydm = model.getXydm();
		String[] pkValue = new String[num];
		// 构建主键,学院列表
		int n = 0;
		if (Base.isNull(xydm)) {// 没选学院，保存全部学院
			List<HashMap<String, String>> xyList = Base.getXyList();
			if (xyList != null && xyList.size() > 0) {
				pkValue = new String[num * xyList.size()];
				for (int i = 0; i < xyList.size(); i++) {
					for (int j = 0; j < num; j++) {
						pkValue[n] = xn + xq + xyList.get(i).get("xydm")
								+ zwList.get(j).get("dm");
						n++;
					}
				}
			}
		} else {// 选择学院，保存单一学院
			for (int i = 0; i < num; i++) {
				pkValue[i] = xn + xq + xydm + zwList.get(i).get("dm");
			}
		}

		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(tableName);
		saveForm.setPk(pk);
		saveForm.setPkValue(pkValue);

		return delPjpy(saveForm, model);

	}
	
	/**
	 * 评奖评优条件设置选项卡页
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> getPjpyTjszCard(){
		List<HashMap<String, String>> rs = new ArrayList<HashMap<String,String>>();
		if(Globals.XXDM_MJXY.equalsIgnoreCase(Base.xxdm)){
			//闽江学院
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("en", "jxj");
			map.put("cn", "奖学金");
			rs.add(map);
		}else if(Globals.XXDM_NTZYDX.equalsIgnoreCase(Base.xxdm)){
			//南通职业大学
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("en", "zhcpjxj");
			map.put("cn", "综合测评奖学金");
			rs.add(map);	
		}else{
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("en", "jxj");
			map.put("cn", "奖学金");
			rs.add(map);
			
			map = new HashMap<String, String>();
			map.put("en", "rych");
			map.put("cn", "荣誉称号");
			rs.add(map);
		}
		return rs;
	}
	
	/**
	 * 获取评奖评优条件设置默认项目
	 * @param lx
	 * @return String
	 * */
	public String getPjpyTjszLx(String lx){
		if(Globals.XXDM_NTZYDX.equalsIgnoreCase(Base.xxdm)){
			lx = StringUtils.isNull(lx) ? "zhcpjxj" : lx;
		}else{
			lx = StringUtils.isNull(lx) ? "jxj" : lx;
		}
		return lx;
	}
}
