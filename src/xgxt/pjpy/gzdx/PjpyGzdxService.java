package xgxt.pjpy.gzdx;

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
import xgxt.daoActionLogic.StandardOperation;
import xgxt.utils.ExcelMethods;
import xgxt.utils.MakeQuery;
import xgxt.utils.Pages;

import common.Globals;

public class PjpyGzdxService {

	/**
	 * 查询审核列表
	 * @return
	 */
	public List<HashMap<String, String>> getShList() {
		PjpyGzdxDAO dao = new PjpyGzdxDAO();
		return dao.getShList();
	}
	
	/**
	 * 查询学生评奖学年的处分信息
	 * @param xh
	 * @param xn
	 * @return
	 */
	public List<String[]> queryStuwjcfxx(String xh, String xn) {
		PjpyGzdxDAO dao = new PjpyGzdxDAO();
		return dao.queryStucjxx(xh, xn);
	}
	
	/**
	 * 查询学生成绩信息
	 * @param xh
	 * @param xn
	 * @return
	 */
	public List<String[]> queryStucjxx(String xh, String xn) {
		PjpyGzdxDAO dao = new PjpyGzdxDAO();
		return dao.queryStucjxx(xh, xn);
	}
	
	/**
	 * 保存处分限制信息
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean saveCfszxx(PjpyGzdxModel model)throws Exception {
		PjpyGzdxDAO dao = new PjpyGzdxDAO();
		return dao.saveCfszxx(model);
	}
	
	/**
	 * 查询处分限制信息
	 * @return
	 */
	public PjpyGzdxModel queryCfszxx() {
		PjpyGzdxDAO dao = new PjpyGzdxDAO();
		return dao.queryCfszxx();
	}
	
	/**
	 * 奖学金人数调整申请后的审核列表
	 * @return
	 */
	public List<HashMap<String, String>> getRsshList() {
		PjpyGzdxDAO dao = new PjpyGzdxDAO();
		return dao.getRsshList();
	}
		
	/**
	 * 查询综合素质测评信息
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> queryZhszcpxx(PjpyGzdxModel model) throws Exception{
		PjpyGzdxDAO dao = new PjpyGzdxDAO();
		return dao.queryZhszcpxx(model);
	}
	
	/**
	 * 查询综合素质测评表头
	 * @return
	 */
	public List<HashMap<String, String>> queryZhszcpTitle() {
		PjpyGzdxDAO dao  = new PjpyGzdxDAO();
		String[] enList = new String[]{ "pk", "r", "xh", "xm", "bjmc", "xn",
				"xycpf", "zhbxf", "xwbxf", "tcbxf", "zf", "bjpm" };
		String[] cnList = new String[]{ "pk", "行号", "学号", "姓名", "班级", "学年",
				"学业分", "综合表现分", "行为表现分", "突出表现分", "综测总分", "综测班级排名" };
		return dao.getList(enList, cnList);
	}
	
	/**
	 * 综合素质测评自动计算
	 * @param xn
	 * @param xydm
	 * @return
	 * @throws Exception
	 */
	public boolean zhszcpAccount(PjpyGzdxModel model) throws Exception {
		PjpyGzdxDAO dao = new PjpyGzdxDAO();
		return dao.zhszcpAccount(model);
	}
	
	/**
	 * 保存综合素质测评数据
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean addZhszcpxx(PjpyGzdxModel model) throws Exception{
		PjpyGzdxDAO dao = new PjpyGzdxDAO();
		return dao.addZhszcpxx(model);
	}
	
	/**
	 * 保存综合素质测评数据
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean updateZhszcpxx(PjpyGzdxModel model) throws Exception{
		PjpyGzdxDAO dao = new PjpyGzdxDAO();
		return dao.updateZhszcpxx(model);
	}
	
	/**
	 * 查询单个综合素质测评信息
	 * @param pkValue
	 * @return
	 */
	public HashMap<String, String> queryZhszcpOnexx(String pkValue) {
		PjpyGzdxDAO dao = new PjpyGzdxDAO();
		return dao.queryZhszcpOnexx(pkValue);
	}
	
	/**
	 * 删除综合素质测评信息
	 * @param keys
	 * @return
	 * @throws Excepton
	 */
	public boolean deleteZhszcpxx(String[] keys) throws Exception {
		PjpyGzdxDAO dao = new PjpyGzdxDAO();
		return dao.deleteZhszcpxx(keys);
	}
	
	/**
	 * 综合表现分申报保存
	 * @throws SQLException 
	 */
	public boolean serv_xsZhbxfsb(ZhbxxfModel model,String act,String strV) throws SQLException{
		PjpyGzdxDAO dao = new PjpyGzdxDAO();
		return dao.dao_xsZhbxfsb(model,act,strV);
	}
	/**
	 * 综合表现分查询
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> serv_xsZhbxfQuery(ZhbxxfModel model) throws Exception{
		PjpyGzdxDAO dao = new PjpyGzdxDAO();
		return dao.xsZhbxfQuery(model);
	}
	/**
	 * 综合表现分查询表头
	 * @return
	 */
	public List<HashMap<String, String>> queryxsZhbxfTitle() {
		PjpyGzdxDAO dao  = new PjpyGzdxDAO();
		String[] enList =new String[] { "pk", "r", "xn","xh", "xm", "xymc", "zymc","bjmc","sfpf"};
		String[] cnList = new String[]{ "pk", "行号","学年","学号", "姓名","院系","专业","班级","评分与否"};
		return dao.getList(enList, cnList);
	}
	/**
	 * 批量删除综合表现
	 * @param pkV
	 * @throws SQLException
	 */
	public void serv_delZhbx(String pkV) throws SQLException{
		PjpyGzdxDAO dao  = new PjpyGzdxDAO();
		dao.delZhbx(pkV);
	}
		
	/**
	 * 通过类别查询奖学金,荣誉称号列表
	 * @param lb
	 * @return
	 */
	public List<HashMap<String, String>> getDmList(String lb) {
		PjpyGzdxDAO dao = new PjpyGzdxDAO();
		return dao.getDmList(lb);
	}
	
	/**
	 * 导出综合测评数据
	 * 
	 * @param wwb
	 * @param model
	 * @throws Exception
	 */
	public void exportZhszcpData(WritableWorkbook wwb, PjpyGzdxModel model)
			throws Exception {
		PjpyGzdxDAO dao = new PjpyGzdxDAO();
		List<String[]> rs = dao.exportZhszcpxx(model);

		WritableSheet ws = wwb.getSheet(0);

		WritableCellFormat wcfStyle = new WritableCellFormat();
		WritableCellFormat style = new WritableCellFormat();
		WritableFont font = new WritableFont(WritableFont.ARIAL, 15);

		//输出样式及格式
		Alignment alignMent = Alignment.CENTRE;
		VerticalAlignment va = VerticalAlignment.CENTRE;
		wcfStyle.setAlignment(alignMent);
		style.setAlignment(alignMent);
		wcfStyle.setBorder(Border.ALL, BorderLineStyle.THIN);
		style.setBorder(Border.ALL, BorderLineStyle.THIN);
		style.setFont(font);
		style.setVerticalAlignment(va);

		if (rs != null) {
			String title = dao.queryXyzynjbj(model) + model.getXn() + "学年";
			//输出二级表头数据
			ws.addCell(new Label(0, 1, title, style));
			//循环输出每行数据
			for (int i = 0; i < rs.size(); i++) {
				String[] oneData = rs.get(i);
				if (oneData != null && oneData.length == 6) {
					int k = 0;
					for (int j = 0; j < 6; j++) {
						ws.addCell(new Label(k, i + 3, oneData[j], wcfStyle));
						k++;
					}
				}
			}
		}
		ExcelMethods.submitWritableWorkbookOperations(wwb);//输出到客户端
	}
	
		
	/**
	 * 申报数据查询表头
	 * @return
	 */
	public List<HashMap<String, String>> queryHjsbTitle() {
		PjpyGzdxDAO dao = new PjpyGzdxDAO();
		// ---------2010/5/25 edit by luojw---------------
		String[] enList = new String[] { "pk", "dis", "bgcolor", "xh", "xm",
				"bjmc", "xn", "xycpf", "zhbxf", "zf", "bjpm", "gk", "cf",
				"wjxz" };
		String[] cnList = new String[] { "pk", "dis", "bgcolor", "学号", "姓名",
				"班级", "学年", "学业分", "综合表现分", "总分", "排名", "挂科", "处分", "参评资格" };
		// ---------end ------------
		return dao.getList(enList, cnList);
	}
	
	/**
	 * 查询获奖上报数据
	 * @param model
	 * @return
	 */
	public List<String[]> queryHjsbdata(PjpyGzdxModel model, String userType) throws Exception{
		PjpyGzdxDAO dao = new PjpyGzdxDAO();
		return dao.queryHjsbdata(model, userType);
	}
	
//	/**
//	 * 查询学院单个奖学金的调整人数
//	 * @param model
//	 * @return
//	 */
//	public String getXyxzrs(PjpyGzdxModel model) {
//		PjpyGzdxDAO dao = new PjpyGzdxDAO();
//		return dao.getXyxzrs(model.getXn(), model.getXydm(), model.getLb(), model.getDm());
//	}
	
	/**
	 * 申报获奖名单
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean saveHjmdData(PjpyGzdxModel model) throws Exception {
		PjpyGzdxDAO dao = new PjpyGzdxDAO();
		return dao.saveHjmdData(model);
	}
	
		/**
	 * 综合表现评分保存
	 * @throws SQLException 
	 */
	public boolean ser_zhbxpf(ZhbxxfModel model) throws SQLException{
		PjpyGzdxDAO dao = new PjpyGzdxDAO();
		return dao.dao_zhbxpf(model);
	}
	
	/**
	 * 删除获奖名单
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean deleteHjmdData(PjpyGzdxModel model) throws Exception {
		PjpyGzdxDAO dao = new PjpyGzdxDAO();
		return dao.deleteHjmdData(model);
	}
	
	/**
	 * 查询学生综合素质测评信息
	 * @param model
	 * @return
	 */
	public HashMap<String, String> queryStuZhszcpxx(PjpyGzdxModel model) {
		PjpyGzdxDAO dao = new PjpyGzdxDAO();
		return dao.queryStuZhszcpxx(model);
	}
	
	/**
	 * 单个保存获奖申报数据
	 * @param model
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public boolean saveHjsbData(PjpyGzdxModel model, String pkValue)
			throws Exception {
		PjpyGzdxDAO dao = new PjpyGzdxDAO();
		return dao.saveHjsbData(model, pkValue);
	}
	
	/**
	 * 查询学生奖学金，荣誉称号获奖信息
	 * @param pkValue  xh||xn||jxjdm , xh||xn||rychdm
	 * @param lb
	 * @return
	 */
	public HashMap<String, String> viewJxjRychresult(String pkValue, String lb) {
		PjpyGzdxDAO dao = new PjpyGzdxDAO();
		return dao.viewJxjRychresult(pkValue, lb);
	}
	
	/**
	 * 奖学金，荣誉称号结果查询，注意如果是学院用户查询，那么如果学校审核通过，那么就不能修改，学校用户查询不用控制。
	 * @param lb jxj,rycy
	 * @param userType xy,xx
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> queryJxjRychResult(String lb,String userType,PjpyGzdxModel model) throws Exception {
		PjpyGzdxDAO dao = new PjpyGzdxDAO();
		return dao.queryJxjRychResult(lb, userType, model);
	}
	
	
	
	/**
	 * 重庆电子科技职业学院
	 * 重庆工程职业学院
	 * 奖学金、荣誉称号结果查询
	 * @param lb
	 * @param userType
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> queryJxjRychResultForCq(String lb,String userType,PjpyGzdxModel model) throws Exception {
		PjpyGzdxDAO dao = new PjpyGzdxDAO();
		return dao.queryJxjRychResultForCq(lb, userType, model);
	}
	
	/**
	 * 奖学金，荣誉称号结果查询表头，
	 * @param lb jxj,rych
	 * @return
	 */
	public List<HashMap<String, String>> queryJxjRychTitle(String lb) {
		PjpyGzdxDAO dao = new PjpyGzdxDAO();
		String xxdm = StandardOperation.getXxdm();
		String[] en = null;
		String[] cn = null;
		
		if (Globals.XXDM_CQDZKJ.equals(xxdm)
				 ||Globals.XXDM_CQGCZY.equals(xxdm)) {
			 en = new String[]{ "pk", "dis", "r", "xh", "xm", "bjmc", "xn",
					"jxjmc", "jlje", "fdysh","xysh","xxsh" };
			 cn = new String[]{ "pk", "dis", "行号", "学号", "姓名", "班级", "学年",
					"奖学金", "奖励金额", "辅导员审核","学院审核","学校审核" };
			if ("rych".equalsIgnoreCase(lb)) {
				en = new String[]{ "pk", "dis", "r", "xh", "xm", "bjmc", "xn",
						"rychmc", "fdysh","xysh","xxsh"};
				cn = new String[]{ "pk", "dis", "行号", "学号", "姓名", "班级", "学年",
						"荣誉称号",  "辅导员审核","学院审核","学校审核" };
			}
		}else if(Globals.XXDM_GZDX.equals(xxdm)){ 
			 en = new String[]{ "pk", "dis", "r", "xh", "xm", "bjmc", "xn",
						"jxjmc", "jlje", "fdysh","xysh","xxsh"};
			 cn = new String[]{ "pk", "dis", "行号", "学号", "姓名", "班级", "学年",
						"奖学金", "奖励金额","学校审核" };
			 if ("rych".equalsIgnoreCase(lb)) {
				en = new String[]{ "pk", "dis", "r", "xh", "xm", "bjmc", "xn",
							"rychmc", "fdysh","xysh","xxsh" };
				cn = new String[]{ "pk", "dis", "行号", "学号", "姓名", "班级", "学年",
							"荣誉称号","学校审核" };
			}
		}else{
			 en = new String[]{ "pk", "dis", "r", "xh", "xm", "bjmc", "xn",
					"jxjmc", "jlje", "fdysh","xysh","xxsh"};
			 cn = new String[]{ "pk", "dis", "行号", "学号", "姓名", "班级", "学年",
					"奖学金", "奖励金额",  "辅导员审核","学院审核","学校审核" };
			if ("rych".equalsIgnoreCase(lb)) {
				en = new String[]{ "pk", "dis", "r", "xh", "xm", "bjmc", "xn",
						"rychmc", "fdysh","xysh","xxsh" };
				cn = new String[]{ "pk", "dis", "行号", "学号", "姓名", "班级", "学年",
						"荣誉称号",  "辅导员审核","学院审核","学校审核" };
			}
		}
		
		
		return dao.getList(en, cn);
	}
	/**
	 * 删除容易称号或奖学金结果数据
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean serv_deleteData(PjpyGzdxModel model) throws Exception{
		PjpyGzdxDAO dao = new PjpyGzdxDAO();
		return dao.deleteData(model);
	}
	/**
	 * 修改荣誉称号或奖学金结果数据
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean modiData(PjpyGzdxModel model,String pkValue) throws Exception {
		PjpyGzdxDAO dao = new PjpyGzdxDAO();
		return dao.modiData(model, pkValue);
	}
	
	/**
	 * 保存奖学金，荣誉称号单个审核信息
	 * @param model
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public boolean saveJxjRychshxx(PjpyGzdxModel model, String pkValue)
			throws Exception {
		PjpyGzdxDAO dao = new PjpyGzdxDAO();
		return dao.saveJxjRychshxx(model, pkValue);
	}
	
	/**
	 * 导出获奖统计数据
	 * @param model
	 * @param wwb
	 */
	public void expData(PjpyGzdxModel model, WritableWorkbook wwb) throws Exception{
		PjpyGzdxDAO dao = new PjpyGzdxDAO();
		String title = Base.xxmc + model.getXn() + "学年" + "评奖评优学生金额统计表";
		WritableSheet ws = wwb.createSheet(title, 0);
		List<String[]> rs = new ArrayList<String[]>();
		rs = dao.expHjData(model);
		WritableCellFormat wcfStyle = new WritableCellFormat();
		WritableCellFormat style = new WritableCellFormat();
		WritableFont font = new WritableFont(WritableFont.ARIAL, 16);

		//输出样式及格式
		Alignment alignMent = Alignment.CENTRE;
		VerticalAlignment va = VerticalAlignment.CENTRE;
		wcfStyle.setAlignment(alignMent);
		style.setAlignment(alignMent);
		wcfStyle.setBorder(Border.ALL, BorderLineStyle.THIN);
		style.setBorder(Border.ALL, BorderLineStyle.THIN);
		style.setFont(font);
		style.setVerticalAlignment(va);
		wcfStyle.setVerticalAlignment(va);
		ws.addCell(new Label(0, 0, title, style));
		ws.mergeCells(0, 0, 8, 1);
		String[] erTitle = {"学院", "专业", "姓名", "学号", "总分", "排名", "获得荣誉", "奖学金等级", "金额"};
		for (int i=0;i<erTitle.length;i++) {
			ws.addCell(new Label(i,2,erTitle[i], wcfStyle));
		}
		if (rs != null) {
			for (int j = 0; j < rs.size(); j++) {
				String[] oneData = rs.get(j);
				if (oneData != null && oneData.length == 9) {
					int k=0;
					//循环输出每一行
					for (int l=0;l<oneData.length;l++) {
						ws.addCell(new Label(k, j+3, oneData[l], wcfStyle));
						//设置列宽
						ws.setColumnView(k, 15);
						k++;
					}
				}
			}
		}
		ExcelMethods.submitWritableWorkbookOperations(wwb);//输出到客户端
	}
	
	/**
	 * 人数批量设置
	 * @param xydm
	 * @param dm
	 * @param key
	 * @param bl 
	 * @return
	 * @throws Exception 
	 */
	public boolean rsblsz(String xydm, String dm, String key, String bl) throws Exception {
		String xn = Base.getJxjsqxn();
		PjpyGzdxDAO dao = new PjpyGzdxDAO();
		return dao.rsblsz(xydm,dm,key,xn,bl);
	}
	
	/**
	 * 初始化人数
	 * @param key
	 * @return
	 * @throws Exception 
	 */
	public boolean cshrs(String key) throws Exception {
		PjpyGzdxDAO dao = new PjpyGzdxDAO();
		return dao.cshrs(key);
	}
	
	/**
	 * 参数设置查询
	 * @param model
	 * @return
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws SecurityException 
	 * @throws IllegalArgumentException 
	 */
	public ArrayList<String[]> getRsszList(PjpyGzdxModel model) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		PjpyGzdxDAO dao = new PjpyGzdxDAO();
		return dao.getRsszList(model);
	}
	
	/**
	 * 参数设置表头
	 * @return
	 */
	public List<HashMap<String, String>> getRsszTopTr() {
		PjpyGzdxDAO dao = new PjpyGzdxDAO();
		return dao.getRsszTopTr();
	}
	/**
	 * 获得单个调整人数信息
	 * @param pk
	 * @return
	 */
	public HashMap<String, String> getDgrsxx(String pk) {
		PjpyGzdxDAO dao = new PjpyGzdxDAO();
		return dao.getDgrsxx(pk);
	}
	
	/**
	 * 单个人数调整保存
	 * @param pk
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	public boolean dgrsUpdate(String pk, PjpyGzdxModel model,HttpServletRequest request) throws Exception {
		PjpyGzdxDAO dao = new PjpyGzdxDAO();
		return dao.dgrsUpdate(pk,model,request);
	}
	
//	/**
//	 * 获取审核列表
//	 * @return
//	 */
//	public List<HashMap<String, String>> getShztList() {
//		DAO dao =DAO.getInstance();
//		return dao.getChkList(3);
//	}
	
	public List<HashMap<String, String>> queryShList() {
		PjpyGzdxDAO dao = new PjpyGzdxDAO();
		return dao.queryShList();
	}
	
	/**
	 * 获得奖学金申报表
	 * 
	 * @param pk
	 * @param model
	 * @return
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws Exception
	 */
	public String getJxjSbTable(PjpyGzdxModel model, String userType,
			String[] queryList, String[] likeList)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		
		DAO dao = DAO.getInstance();
		
		MakeQuery queryObject = new MakeQuery();
		queryObject.makeQuery(queryList, likeList, model);

		String[] colList = new String[] { "pk", "dis", "bgcolor", "xh", "xm",
				"bjmc", "xn", "xycpf", "zhbxf", "zf", "bjpm", "gk", "cf",
				"wjxz", "mc", "xxsh" };
		
		String dis = "";
		if ("xy".equalsIgnoreCase(userType)) {
			dis = "disabled";
		}
		String cfjb = dao.getOneRs("select (select b.cfjb from cflbdmb b where " +
				"a.cflb=b.cflbdm) jb from hjcfxzb a", new String[]{}, "jb");
		
		StringBuffer sql = new StringBuffer();
		sql.append("select distinct(a.xh)xh,a.xh || '!@' || a.xn || '!@' pk,a.xn,a.xm,a.xb,");
		sql.append("a.nj,a.xymc,a.zymc,a.bjmc,a.xycpf,a.zhbxf,a.zf,a.bjrs,a.cj,a.wj,a.cfxz,a.bjpm,");
		sql.append("(case when xxsh='通过' then '"+dis+"' ");
		sql.append(" else '' end) dis,(case when cj > 0 or wj >0 then '#CCCCCC' ");
		sql.append("when cfxz > 0 then '#DDDDDD' else '' end) bgcolor,(case when cj >0 then '有' else '无' end) ");
		sql.append("gk,(case when wj > 0 then '有' else '无' end) cf,(case when cfxz > 0 then '无' else '有' end) wjxz from (select ");
		sql.append("a.*,a.xh||'!@'||a.xn||'!@'||b.jxjdm pk,b.jxjdm dm,b.xxsh,(select jxjmc from jxjdmb ");
		sql.append("c where c.jxjdm=b.jxjdm) mc from (select a.*,(select count(xh) ");
		sql.append("from cjb b where a.xh=b.xh and a.xn=b.xn and (b.cj < 60 or ");
		sql.append("b.bkcj is not null or b.cxcj is not null)) cj,(select count(xh)");
		sql.append("from wjcfb b where a.xh=b.xh and a.xn=b.xn and b.cfwh is ");
		sql.append("not null) wj,(select count(c.xh) from (select c.xh,c.xn,c.cflb,");
		sql.append("(select d.cfjb from cflbdmb d where c.cflb=d.cflbdm) cfjb ");
		sql.append("from wjcfb c where cfwh is not null) c where a.xh=c.xh and c.xn=a.xn and c.cfjb>'"+cfjb+"') cfxz,(rank() over (partition by xn,bjdm order by ");
		sql.append("to_number(zf) desc nulls last)) bjpm from (select '"+ model.getXn());
		sql.append("' xn,a.xh,a.xm,a.nj,a.xydm,a.xymc,a.zydm,a.zymc,a.xb,a.bjdm,");
		sql.append("a.bjmc,(select (nvl(case when instr(to_char(b.xycpf),'.',1,1)=1 ");
		sql.append("then '0'||to_char(b.xycpf) when instr(to_char(b.xycpf),'-',1,1)=1 ");
		sql.append("and instr(to_char(b.xycpf),'.',2,1)=2 then replace(b.xycpf,'-','-0') ");
		sql.append("else to_char(b.xycpf) end, '0')) xycpf from gzdx_zhszcpb b where a.xh=b.xh and b.xn='"+ model.getXn());
		sql.append("') xycpf,(select (nvl(case when instr(to_char(b.zhbxf),'.',1,1)=1 then '0'||to_char(b.zhbxf) ");
		sql.append("when instr(to_char(b.zhbxf),'-',1,1)=1 and instr(to_char(b.zhbxf),'.',2,1)=2 ");
		sql.append("then replace(b.zhbxf,'-','-0') else to_char(b.zhbxf) end, '0')) zhbxf from gzdx_zhszcpb b where a.xh=b.xh and b.xn='"+ model.getXn());
		sql.append( "') zhbxf,(select (nvl(case when instr(to_char(b.zf),'.',1,1)=1 then "); 
		sql.append("'0'||to_char(b.zf) when instr(to_char(b.zf),'-',1,1)=1 and instr(to_char(b.zf),'.',2,1)=2 ");
		sql.append("then replace(b.zf,'-','-0') else to_char(b.zf) end, '0')) zf from gzdx_zhszcpb b where a.xh=b.xh and b.xn='"+model.getXn());
		sql.append("') zf,(select count(b.xh) bjrs from view_xsjbxx b where b.bjdm = a.bjdm group by b.bjdm) bjrs ");
		sql.append("from view_xsjbxx a) a) a left join xsjxjb b on a.xh=b.xh and b.xn='"
						+ model.getXn() + "' order by cj,wj,bjpm) a ");
		sql.append(queryObject.getQueryString());
		sql.append(" and (xxsh is null or xxsh <> '通过')");
		sql.append(" order by bjmc,bjpm ");
		System.out.println(sql.toString());
		return sql.toString();
	}
	
	/**
	 * 获得荣誉称号申报表
	 * 
	 * @param pk
	 * @param model
	 * @return
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws Exception
	 */
	public String getRychSbTable(PjpyGzdxModel model, String userType,
			String[] queryList, String[] likeList)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		
		DAO dao = DAO.getInstance();
		
		MakeQuery queryObject = new MakeQuery();
		queryObject.makeQuery(queryList, likeList, model);

		String[] colList = new String[] { "pk", "dis", "bgcolor", "xh", "xm",
				"bjmc", "xn", "xycpf", "zhbxf", "zf", "bjpm", "gk", "cf",
				"wjxz", "mc", "xxsh" };
		
		String dis = "";
		if ("xy".equalsIgnoreCase(userType)) {
			dis = "disabled";
		}
		String cfjb = dao.getOneRs("select (select b.cfjb from cflbdmb b where " +
				"a.cflb=b.cflbdm) jb from hjcfxzb a", new String[]{}, "jb");
		
		StringBuffer sql = new StringBuffer();
			
		sql.append("select distinct(a.xh)xh,a.xh || '!@' || a.xn || '!@' pk,a.xn,a.xm,a.xb,");
		sql.append("a.nj,a.xymc,a.zymc,a.bjmc,a.xycpf,a.zhbxf,a.zf,a.bjrs,a.cj,a.wj,a.cfxz,a.bjpm,");
		sql.append("(case when xxsh='通过' then '"+dis+"'");
		sql.append(" else '' end) dis,(case when cj > 0 or wj >0 then '#CCCCCC' ");
		sql.append("when cfxz > 0 then '#DDDDDD' else '' end) bgcolor,(case when cj >0 then '有' else '无' end) ");
		sql.append("gk,(case when wj > 0 then '有' else '无' end) cf,(case when cfxz > 0 then '无' else '有' end) wjxz from (select ");
		sql.append("a.*,a.xh||'!@'||a.xn||'!@'||b.rychdm pk,b.rychdm dm,b.xxsh,(select rychmc from rychdmb ");
		sql.append("c where c.rychdm=b.rychdm) mc from (select a.*,(select count(xh) ");
		sql.append("from cjb b where a.xh=b.xh and a.xn=b.xn and (b.cj < 60 or ");
		sql.append("b.bkcj is not null or b.cxcj is not null)) cj,(select count(xh)");
		sql.append(" from wjcfb b where a.xh=b.xh and a.xn=b.xn and b.cfwh is ");
		sql.append("not null) wj,(select count(c.xh) from (select c.xh,c.xn,c.cflb,");
		sql.append("(select d.cfjb from cflbdmb d where c.cflb=d.cflbdm) cfjb ");
		sql.append("from wjcfb c where cfwh is not null) c where a.xh=c.xh and c.xn=a.xn and c.cfjb>'"+cfjb+"') cfxz,(rank() over (partition by xn,bjdm order by ");
		sql.append("to_number(zf) desc nulls last)) bjpm from (select '"+ model.getXn());
		sql.append("' xn,a.xh,a.xm,a.nj,a.xydm,a.xymc,a.zydm,a.zymc,a.xb,a.bjdm,a.bjmc,(select (nvl(case when " );
		sql.append("instr(to_char(b.xycpf),'.',1,1)=1 then '0'||to_char(b.xycpf) when instr(to_char(b.xycpf),'-',1,1)=1 and instr(to_char(b.xycpf),");
		sql.append("'.',2,1)=2 then replace(b.xycpf,'-','-0') else to_char(b.xycpf) end, '0')) xycpf from gzdx_zhszcpb b where a.xh=b.xh and b.xn='"+ model.getXn());
		sql.append( "') xycpf,(select (nvl(case when instr(to_char(b.zhbxf),'.',1,1)=1 then '0'||to_char(b.zhbxf) ");
		sql.append("when instr(to_char(b.zhbxf),'-',1,1)=1 and instr(to_char(b.zhbxf),'.',2,1)=2 then ");
		sql.append("replace(b.zhbxf,'-','-0') else to_char(b.zhbxf) end, '0')) zhbxf from gzdx_zhszcpb b where a.xh=b.xh and b.xn='"+ model.getXn());
		sql.append( "') zhbxf,(select (nvl(case when instr(to_char(b.zf),'.',1,1)=1 then '0'||to_char(b.zf) ");
		sql.append("when instr(to_char(b.zf),'-',1,1)=1 and instr(to_char(b.zf),'.',2,1)=2 then replace(b.zf,'-','-0') ");
		sql.append("else to_char(b.zf) end, '0')) zf from gzdx_zhszcpb b where a.xh=b.xh and b.xn='"+ model.getXn());
		sql.append("') zf,(select count(b.xh) bjrs from view_xsjbxx b where b.bjdm = a.bjdm group by b.bjdm)bjrs");
		sql.append(" from view_xsjbxx a) a) a left join xsrychb b on a.xh=b.xh and b.xn='"+ model.getXn() + "' order by cj,wj,bjpm) a");
		sql.append(queryObject.getQueryString());
		sql.append(" and (xxsh is null or xxsh <> '通过')");
		sql.append(" order by bjmc,bjpm ");
		
		return sql.toString();
	}
	
	/**
	 * 查询获奖上报数据
	 * 
	 * @param model
	 * @return
	 */
	public ArrayList<String[]> queryHjsbStuList(PjpyGzdxModel model,
			String userType) throws Exception {

		DAO dao = DAO.getInstance();
		PjpyGzdxDAO pjDao = new PjpyGzdxDAO();
		
		// 奖学金或荣誉称号
		String lb = model.getLb();
		// 评奖学年
		String xn = model.getXn();
		// 奖学金或荣誉称号代码
		String dm = model.getDm();
		
		// 有资格申报者列表
		List<HashMap<String, String>> sbList = new ArrayList<HashMap<String, String>>();
		
		String[] queryList = new String[] { "nj", "xydm", "zydm", "bjdm", "xn" };
		String[] likeList = new String[] { "xh", "xm" };

		String sql = "";
		
		if ("jxj".equalsIgnoreCase(lb)) {// 奖学金
			sql = getJxjSbTable(model, userType, queryList, likeList);
		//	getJxjStuList(model, userType, dao, pjDao, sbList, xsList);
		} else if ("rych".equalsIgnoreCase(lb)) {// 荣誉称号
			sql = getRychSbTable(model, userType, queryList, likeList);
		//	getJxjStuList(model, userType, dao, pjDao, sbList, xsList);
		}
		
		//	根据条件查询到的全部学生
		MakeQuery queryObject = new MakeQuery();
		queryObject.makeQuery(queryList, likeList, model);
		
		String[] output = new String[] { "pk", "dis", "bgcolor", "xh", "xm",
				"bjmc", "xn", "xycpf", "zhbxf", "zf", "bjpm", "gk", "cf",
				"wjxz", "bjrs" };
		
		List<HashMap<String, String>> xsList = dao.getList(sql, queryObject
				.getInputList(), output);
		System.out.println(sql);
		sbList=getJxjStuList(model, userType, dao, pjDao, sbList, xsList);
		
		ArrayList<String[]> list = new ArrayList<String[]>();
		
		String[] colList = new String[] { "pk", "dis", "bgcolor", "xh",
				"xm", "bjmc", "xn", "xycpf", "zhbxf", "zf", "bjpm", "gk",
				"cf", "wjxz" };
		if (this.checkZcpm(model)) {
			if (sbList != null && sbList.size() > 0) {

				for (int i = 0; i < sbList.size(); i++) {

					String[] arrInfo = new String[colList.length];

					HashMap<String, String> map = sbList.get(i);

					for (int j = 0; j < colList.length; j++) {
						arrInfo[j] = arrInfo[j] = Base.isNull(map
								.get(colList[j])) ? "" : map.get(colList[j]);
					}

					list.add(arrInfo);

				}
			}
		} else {
			if (xsList != null && xsList.size() > 0) {

				for (int i = 0; i < xsList.size(); i++) {

					String[] arrInfo = new String[colList.length];

					HashMap<String, String> map = xsList.get(i);

					for (int j = 0; j < colList.length; j++) {
						arrInfo[j] = Base.isNull(map.get(colList[j])) ? ""
								: map.get(colList[j]);
					}

					list.add(arrInfo);
				}
			}
		}
		
		//分页
		
		ArrayList<String[]> rsList = new ArrayList<String[]>();
		
		if(list != null && list.size()>0){
			
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

	private List<HashMap<String,String>> getJxjStuList(PjpyGzdxModel model, String userType, DAO dao,
			PjpyGzdxDAO pjDao, List<HashMap<String, String>> sbList,
			List<HashMap<String, String>> list) throws IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		List<HashMap<String, String>> tjList = pjDao.getTjList(model);

		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				HashMap<String, String> map = list.get(i);
				// 班级人数
				String bjrs = map.get("bjrs");
				// 班级排名
				String bjpm = Base.isNull(map.get("bjpm")) ? "1" : map
						.get("bjpm");
				if (tjList != null && tjList.size() > 0) {
					for (int j = 0; j < tjList.size(); j++) {
						HashMap<String, String> tj = tjList.get(j);
						// 条件字段
						String tjzd = tj.get("tjzd");
						// 条件类型
						String tjlx = tj.get("tjlx");
						// 条件值
						String tjz = tj.get("tjz");
						if ("zhpm".equalsIgnoreCase(tjzd)) {
							// 奖学金条件排名
							float pm = Float.parseFloat(bjpm);
							float tjpm = Float.parseFloat(bjrs)
									* Float.parseFloat(tjz) / 100;
							tjpm = Math.round(tjpm);
							// 判断两排名是否符合条件
							if (">".equalsIgnoreCase(tjlx)) {
								if (pm < tjpm) {
									sbList.add(map);
								}
							} else if (">=".equalsIgnoreCase(tjlx)) {
								if (pm <= tjpm) {
									sbList.add(map);
								}
							} else if ("=".equalsIgnoreCase(tjlx)) {
								if (pm != tjpm) {
									sbList.add(map);
								}
							} else if ("<".equalsIgnoreCase(tjlx)) {
								if (pm > tjpm) {
									sbList.add(map);
								}
							} else if ("<=".equalsIgnoreCase(tjlx)) {
								if (pm >= tjpm) {
									sbList.add(map);
								}
							}
						}
					}
				}
			}
		}
		return sbList;
	}
	
	/**
	 * 是否存在ZCPM条件
	 */
	public boolean checkZcpm(PjpyGzdxModel model){
		PjpyGzdxDAO pjpyGzdxDao=new PjpyGzdxDAO();
		HashMap<String,String>hashMap=pjpyGzdxDao.checkZcpm(model);
		int tj=Integer.parseInt(hashMap.get("tj"));
		if(tj==0){
			return false;
		}else{
			return true;
		}
	}
}
