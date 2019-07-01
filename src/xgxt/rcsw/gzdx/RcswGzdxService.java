package xgxt.rcsw.gzdx;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

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
import xgxt.form.SaveForm;
import xgxt.pjpy.zjcm.ZjcmPjpyModel;
import xgxt.rcsw.RcswService;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.ExcelMethods;
import xgxt.utils.MakeQuery;
import xgxt.utils.Pages;
import xgxt.utils.date.MoneyFormat;

public class RcswGzdxService extends RcswService {

	RcswGzdxDAO dao = new RcswGzdxDAO();

	// 根据维护的评价级别设置sql表别名，暂定26个级别
	private String[] arrTable = new String[] { "a", "b", "c", "d", "e", "f",
			"g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s",
			"t", "u", "v", "w", "x", "y", "z" };
	
	/**
	 * 获得个性化表头
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getTopTr(String lx) {
		DAO dao = DAO.getInstance();
		String[] colListCN = null;
		String[] colListEN = null;

		if ("hftj".equalsIgnoreCase(lx)) {
			
			String[] gdCNList = new String[] { "职工号", "姓名", "部门名称", "职务名称" };
			String[] gdENList = new String[] { "zgh", "xm", "bmmc", "zwmc" };
			
			// 获得评价等级列表
			List<HashMap<String, String>> list = dao.getWhList("rcsw_lypjb",
					"dm", "mc", "", "", "");
			
			colListCN = new String[list.size() + 4];
			colListEN = new String[list.size() + 4];
			
			for (int i = 0; i < gdCNList.length; i++) {
				colListCN[i] = gdCNList[i];
				colListEN[i] = gdENList[i];
			}
			
			for (int i = 1; i < list.size(); i++) {
				HashMap<String, String> map = list.get(i);
				String pjdm = map.get("dm");//评价代码
				String pjmc = map.get("mc");//评价名称
				//维护值
				colListEN[3 + i] = pjdm;
				colListCN[3 + i] = pjmc+"率";
			}
			colListEN[3 + list.size()] = "num";
			colListCN[3 + list.size()] = "回答数";
		}
		return dao.arrayToList(colListEN, colListCN);
	}
	
	/**
	 * 获得留言回复列表
	 * 
	 * @param form
	 * @param request
	 * @author luojw
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getLyhfList(RcswGzdxModel model) {
		return dao.getLyhfList(model);
	}

	/**
	 * 修改回复内容
	 * 
	 * @param form
	 * @param request
	 * @author luojw
	 * @throws Exception
	 * @throws Exception
	 */
	public Boolean editHfnr(RcswGzdxModel model, HttpServletRequest request)
			throws Exception {

		String bjlyxx = model.getBjlyxx();

		String realTable = "rcsw_lyhfb";
		String pk = "id";
		String pkValue = model.getId();
		String[] colList = new String[] { "id", "hfsj", "hfnr" };

		HashMap<String, String> map = dao.getRcswInfo(realTable, pk, pkValue,
				colList);
		String id = map.get("id");
		String hfsj = map.get("hfsj");
		String hfnr = map.get("hfnr");

		model = new RcswGzdxModel();
		model.setId(id);
		model.setHfsj(hfsj);
		model.setHfnr(hfnr);

		realTable = "rcsw_hflsb";
		pk = "id";
		pkValue = model.getId();
		// 保存字段
		String[] onezd = new String[] { "id", "hfsj", "hfnr" };

		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(realTable);
		saveForm.setOnezd(onezd);
		saveForm.setPk(pk);
		saveForm.setPkValue(new String[] { pkValue });

		boolean flag = saveRcsw(saveForm, model, request);

		if (flag) {

			model = new RcswGzdxModel();
			model.setId(id);
			model.setHfnr(bjlyxx);

			flag = dao.updateHfnr(model);
		}

		return flag;

	}

	/**
	 * 获得学生留言列表
	 * 
	 * @param form
	 * @param request
	 * @author luojw
	 * @throws Exception
	 */
	public ArrayList<String[]> getXslyInfoList(RcswGzdxModel model,
			String userType) {
		return dao.getXslyInfoList(model, userType);
	}

	/**
	 * 保存置顶信息
	 * 
	 * @param form
	 * @param request
	 * @author luojw
	 * @throws Exception
	 */
	public Boolean saveZdInfo(String pkValue, boolean flag) throws Exception {
		return dao.saveZdInfo(pkValue, flag);
	}

	/**
	 * 删除学生留言信息
	 * 
	 * @param form
	 * @param request
	 * @author luojw
	 * @throws Exception
	 */
	public Boolean delXslyInfo(String[] pkValue) throws Exception {
		return dao.delXslyInfo(pkValue);
	}

	/**
	 * 获得老师回复留言列表
	 * 
	 * @param form
	 * @param request
	 * @author luojw
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public ArrayList<String[]> getHflyInfoList(RcswGzdxModel model)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		DAO dao = DAO.getInstance();
		
		Pages pages = model.getPages();
		
		String[] queryList = new String[] { "bmdm", "zw" };

		String[] queryLikeList = new String[] { "zgh", "xm" };

		MakeQuery myQuery = new MakeQuery();

		myQuery.makeQuery(queryList, queryLikeList, model);

		String query = myQuery.getQueryString();
		
		// 获得评价等级列表
		List<HashMap<String, String>> list = dao.getWhList("rcsw_lypjb", "dm",
				"mc", "", "", "");
		
		StringBuffer sql = new StringBuffer();
		StringBuffer sb = new StringBuffer();
		
		String[] colList = null;
		String[] valueList = myQuery.getInputList();
		// 统计固定值
		String[] gdList = new String[] { "zgh", "xm", "bmmc", "zwmc" };
		if (list != null && list.size() > 0) {

			colList = new String[list.size() + 4];
			for (int i = 0; i < gdList.length; i++) {
				colList[i] = gdList[i];
			}

			sb.append("select * from (select t.* ,rownum r from ( ");
			sql.append("select t.*");
			for (int i = 1; i < list.size(); i++) {
				sql.append(",case when t.num <> 0 and t." + arrTable[i]);
				sql.append(" <> 0 then round(t." + arrTable[i]);
				sql.append("/t.num*100,2)||'%' else '0' end " + arrTable[i]);
				sql.append("bfb ");
			}
			sql.append(" from (");
			sql.append("select a.zgh,a.xm,a.bmdm,a.bmmc,a.zw,a.zwmc,");

			for (int i = 1; i < list.size(); i++) {
				sql.append("sum(" + arrTable[i] + ") " + arrTable[i] + ",");
				// 维护值
				colList[3 + i] = arrTable[i]+"bfb";
			}
			// 回答数
			colList[3 + list.size()] = "num";
			
			sql.append("count(a.pk) num from (select a.zgh, a.xm, a.bmdm, a.bmmc, a.zw, a.zwmc,");
			
			for (int i = 1; i < list.size(); i++) {
				HashMap<String, String> map = list.get(i);
				String pjdm = map.get("dm");//评价代码
				String pjmc = map.get("mc");//评价名称
				sql.append("case when hfpj = '"+pjdm+"' then 1 else 0 end "+arrTable[i]+",");
			}
			sql.append("a.pk from (select a.zgh,a.xm,a.bmdm,a.bmmc,a.zw,a.zwmc,b.pk,b.hfpj ");
			sql.append("from view_fdyxx a left join (select hfr, pk, hfpj from  ");
			sql.append("view_rcsw_lyhf b) b on a.zgh = b.hfr order by zgh) a) a ");
			sql.append("group by a.zgh, a.xm, a.bmdm, a.bmmc, a.zw, a.zwmc");
			sql.append(") t ");
		}
		sb.append(sql);
		sb.append(") t ");
		sb.append(query);
		sb.append(") where r > "); 
		sb.append(pages.getStart());
		sb.append(" and r <= ");
		sb.append((pages.getStart() + pages.getPageSize()));
		
		System.out.println(model.getZw()+sb.toString());
		ArrayList<String[]> rsList = dao.rsToVator(sb.toString(), valueList,
				colList);
		
		sb = new StringBuffer();
		sb.append("select count(*) count from (");
		sb.append(sql);	
		sb.append(query);
		sb.append(")");
		
		String count = dao.getOneRs(sb.toString(), valueList, "count");
		if(!Base.isNull(count)){
			pages.setMaxRecord(Integer.parseInt(count));
		}
		
		return rsList;
		
	}
	
	/**
	 * 打印统计报表
	 * 
	 * @author luo
	 * @throws Exception
	 */
	public void printTjbb(RcswGzdxModel model, OutputStream os)
			throws Exception {

		DAO dao = DAO.getInstance();

		// 设置标题
		WritableWorkbook wwb = Workbook.createWorkbook(os);

		WritableSheet ws = wwb.createSheet("统计表", 0);

		WritableCellFormat wcf2 = new WritableCellFormat(); // 构造单元格格式
		wcf2 = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL);	

		// 填充表头
		String[] colListCN = null;
		String[] colListEN = null;

		String[] gdCNList = new String[] { "职工号", "姓名", "部门名称", "职务名称" };
		String[] gdENList = new String[] { "zgh", "xm", "bmmc", "zwmc" };

		// 获得评价等级列表
		List<HashMap<String, String>> list = dao.getWhList("rcsw_lypjb", "dm",
				"mc", "", "", "");

		colListCN = new String[list.size() + 4];
		colListEN = new String[list.size() + 4];

		for (int i = 0; i < gdCNList.length; i++) {
			colListCN[i] = gdCNList[i];
			colListEN[i] = gdENList[i];
		}

		for (int i = 1; i < list.size(); i++) {
			HashMap<String, String> map = list.get(i);
			String pjdm = map.get("dm");// 评价代码
			String pjmc = map.get("mc");// 评价名称
			// 维护值
			colListEN[3 + i] = pjdm;
			colListCN[3 + i] = pjmc + "率";
		}
		colListEN[3 + list.size()] = "num";
		colListCN[3 + list.size()] = "回答数";

		for (int i = 0; i < colListCN.length; i++) {
			ws.addCell(new Label(i, 0, colListCN[i], wcf2));
		}

		// 获得统计表名
		
		String[] queryList = new String[] { "bmdm", "zw" };

		String[] queryLikeList = new String[] { "zgh", "xm" };

		MakeQuery myQuery = new MakeQuery();

		myQuery.makeQuery(queryList, queryLikeList, model);
		
		String sql = getTjSql(list);
		
		String query = myQuery.getQueryString();

		String[] inPutList = myQuery.getInputList();
		
		String[] colList = null;
		
		// 统计固定值
		String[] gdList = new String[] { "zgh", "xm", "bmmc", "zwmc" };
		
		if (list != null && list.size() > 0) {

			colList = new String[list.size() + 4];
			for (int i = 0; i < gdList.length; i++) {
				colList[i] = gdList[i];
			}
			
			for (int i = 1; i < list.size(); i++) {
				// 维护值
				colList[3 + i] = arrTable[i]+"bfb";
			}
			// 回答数
			colList[3 + list.size()] = "num";
		}
		// 奖学金金额统计列表
		ArrayList<String[]> tjList = CommonQueryDAO.commonQueryNotFy(sql, query, inPutList, colList, model);

		if (tjList != null && tjList.size() > 0) {
			for (int i = 0; i < tjList.size(); i++) {
				
				String[] tjInfo = tjList.get(i);
				
				ws.addCell(new Label(0, 1 + i, tjInfo[0], wcf2));// 职工号
				ws.addCell(new Label(1, 1 + i, tjInfo[1], wcf2));// 姓名
				ws.addCell(new Label(2, 1 + i, tjInfo[2], wcf2));// 部门
				ws.addCell(new Label(3, 1 + i, tjInfo[3], wcf2));// 职务
				
				// 自定义评价等级
				int size = list.size() - 1;
				for (int j = 0; j < size; j++) {
					ws.addCell(new Label(4 + j, 1 + i, tjInfo[4 + j], wcf2));// 评价率
				}
				
				ws.addCell(new Label(4 + size, 1 + i, tjInfo[4 + size], wcf2));// 回答数
				
			}
		}

		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	/**
	 * 获得回复留言统计table
	 * 
	 * @author luo
	 */
	public String getTjSql(List<HashMap<String, String>> list){
		
		StringBuffer sql = new StringBuffer();
		
		sql.append("select t.*");
		for (int i = 1; i < list.size(); i++) {
			sql.append(",case when t.num <> 0 and t." + arrTable[i]);
			sql.append(" <> 0 then round(t." + arrTable[i]);
			sql.append("/t.num*100,2)||'%' else '0' end " + arrTable[i]);
			sql.append("bfb ");
		}
		sql.append(" from (");
		sql.append("select a.zgh,a.xm,a.bmdm,a.bmmc,a.zw,a.zwmc,");

		for (int i = 1; i < list.size(); i++) {
			sql.append("sum(" + arrTable[i] + ") " + arrTable[i] + ",");

		}
		
		sql.append("count(a.pk) num from (select a.zgh, a.xm, a.bmdm, a.bmmc, a.zw, a.zwmc,");
		
		for (int i = 1; i < list.size(); i++) {
			HashMap<String, String> map = list.get(i);
			String pjdm = map.get("dm");//评价代码
			String pjmc = map.get("mc");//评价名称
			sql.append("case when hfpj = '"+pjdm+"' then 1 else 0 end "+arrTable[i]+",");
		}
		sql.append("a.pk from (select a.zgh,a.xm,a.bmdm,a.bmmc,a.zw,a.zwmc,b.pk,b.hfpj ");
		sql.append("from view_fdyxx a left join (select hfr, pk, hfpj from  ");
		sql.append("view_rcsw_lyhf b) b on a.zgh = b.hfr order by zgh) a) a ");
		sql.append("group by a.zgh, a.xm, a.bmdm, a.bmmc, a.zw, a.zwmc");
		sql.append(") t ");
	
		return sql.toString();
	}
	

	/**
	 * 获得回复楼数
	 * 
	 * @param form
	 * @param request
	 * @author luojw
	 */
	public int getHfls(RcswGzdxModel model){
		return dao.getHfls(model);
	}
	
	/**
	 * 判断是否选择咨询师
	 */
	public String  getXzZxs(String dm){
		HashMap<String,String>hashMap=dao.getXzZxs(dm);
		String xzzxs=hashMap.get("xzzxs");
		if("".equalsIgnoreCase(xzzxs) || xzzxs==null){
			return "no";
		}
		return xzzxs;
	}
}
