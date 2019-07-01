package xgxt.pjpy.ycsfxy;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import common.Globals;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.VerticalAlignment;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import xgxt.DAO.DAO;
import xgxt.DAO.ExcelMB;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.pjpy.ahjg.PjpyAhjgService;
import xgxt.utils.String.StringUtils;

public class PjpyYcsfxyService {

	//数据操作DAO
	private PjpyYcsfxyDAO dao = PjpyYcsfxyDAO.getInstance();

	public static PjpyYcsfxyService getInstance() {
		
		return new PjpyYcsfxyService();
	}
	
	/**
	 * 平时，阶段考核成绩查询表头
	 * @return
	 */
	public List<HashMap<String, String>> getPsjdkhQueryTitle() {	
		return dao.getPsjdkhQueryTitle();
	}
	
	/**
	 * 平时，阶段考核成绩查询结果,加分页
	 * @param model
	 * @param userType
	 * @param userName
	 * @return
	 */
	public ArrayList<String[]> getPsjdkhQueryResult(PjpyYcsfxyModel model,
			String userType, String userName,
			PjpyYcsfxyActionForm dataSearchForm) {
		return dao.getPsjdkhQueryResult(model, userType, userName, dataSearchForm);
	}

	/**
	 * 平时，阶段考核成绩查询结果,加总记录数
	 * @param model
	 * @param userType
	 * @param userName
	 * @return
	 */
	public int getPsjdkhQueryResultCount(PjpyYcsfxyModel model,
			String userType, String userName) {
		return dao.getPsjdkhQueryResultCount(model, userType, userName);
	}
	
	/**
	 * 保存平时,阶段考核成绩信息
	 * @param pkList
	 * @param pjkhcj
	 * @param jdkhcj
	 * @return
	 * @throws Exception
	 */
	public boolean savePjjdkhcjxx(String[] pkList, String[] pjkhcj, String[] jdkhcj, String[] dis) throws Exception {
		return dao.savePjjdkhcjxx(pkList, pjkhcj, jdkhcj, dis);
	}
	
	/**
	 * 删除平时阶段,考核成绩信息,返回操作失败的数据
	 * @param pkList
	 * @return
	 * @throws Exception
	 */
	public String deletePjjdkhcjxx(String[] pkList) throws Exception{
		return dao.deletePjjdkhcjxx(pkList);
	}
	
	//综合素质测评比例设置
	public boolean setZhszcpBl_ser(PjpyYcsfxyModel model) throws Exception{
		return dao.setZhszcpBl_db(model);
	}
	
	//获得综合素质测评比例
	public PjpyYcsfxyModel getZhszcpBl_ser(){
		PjpyYcsfxyModel model = dao.getZhszcpBl_db(); 
		HashMap<String, String> rs = dao.queryZhszcpxmBlxx();
		model.setDyjcf(rs.get("dyjcf"));
		model.setDyjcfbl(rs.get("dyjcfbl"));
		model.setDyssgffbl(rs.get("dyssgffbl"));
		model.setDyfjfbl(rs.get("dyfjfbl"));
		model.setZykskmcjbl(rs.get("zykskmcjbl"));
		model.setZykckmcjbl(rs.get("zykckmcjbl"));
		model.setZyfjfbl(rs.get("zyfjfbl"));
		model.setTycjbl(rs.get("tycjbl"));
		model.setTykwtydlbl(rs.get("tykwtydlbl"));
		model.setTyfjfbl(rs.get("tyfjfbl"));
		return model;
	}
	
	//查询综合素质测评相关成绩
	public List<String[]> zhszcpQuery_ser(PjpyYcsfxyActionForm form,PjpyYcsfxyModel model,String userName,String isFdy){
		return dao.zhszcpQuery_db(form,model,userName,isFdy);
	}
	
	//查询综合素质测评总数
	public int zhszcpQueryCount_ser(PjpyYcsfxyModel model,String userName,String isFdy){
		return dao.zhszcpQueryCount_db(model, userName, isFdy);
	}
	
	//计算学业考核成绩和综合素质测评成绩
	public String computeZhszcp_ser(String userType,String xydm,String userName){
		try {
			return dao.computeZhszcp_db(userType,xydm,userName);
		} catch (Exception e) {
			e.printStackTrace();
			return "no";
		}
	}

	//组合表头
	public List<HashMap<String, String>> getTabName_ser(String[] en,String[] cn){
		DAO comdao = DAO.getInstance();
		return comdao.arrayToList(en, cn);
	}
	
	//查询综合素质测评全部通过
	public String zhszcpShAll_ser(){
		try {
			return dao.zhszcpShAll_db();
		} catch (Exception e) {
			e.printStackTrace();
			return "no";
		}
	}
	
	//综合素质测评批量通过
	public String zhszcpShPart_ser(String[] pks,String act){
		String pkVals = "!@!";
		if(pks != null){
			for(int i=0;i<pks.length;i++){
				pkVals += pks[i]+"!@!";
			}
		}
		try {
			return dao.zhszcpShPart_db(pkVals,act);
		} catch (Exception e) {
			e.printStackTrace();
			return "no";
		}
	}
	
	//查看学生成绩
	public List<String[]> getXscj_ser(String pk){
		PjpyAhjgService service = new PjpyAhjgService();
		String jwflag = service.getJwFlag();
		jwflag = StringUtils.isNull(jwflag) ? "" : jwflag.trim();//1学分0过渡
		try{
			if ("1".equalsIgnoreCase(jwflag)) {
				return dao.getCjListByxf_db(pk);
			} else {
				return dao.getCjList_db(pk);
			}
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
	}
	
	//获得学生基本信息及成绩汇总信息
	public HashMap<String,String> getXsxxAndCjhz_ser(String pk){
		try{
			return dao.getXsxxAndCjhz_db(pk);
		}catch(Exception ex){
			ex.printStackTrace();
			return new HashMap<String,String>();
		}
	}
	
	//获得学生基本信息及平时，阶段成绩信息
	public HashMap<String,String> getXsxxAndPsjdcj_ser(String pk){
		try{
			return dao.getXsxxAndPsjdcj_db(pk);
		}catch(Exception ex){
			ex.printStackTrace();
			return new HashMap<String,String>();
		}
	}
	
	/**
	 * 学生查询综合素质测评信息
	 * @param xh
	 * @return
	 */
	public List<String[]> stuQueryZhszcpxx(String xh) {
		return dao.stuQueryZhszcpxx(xh);
	}
	
	/**
	 * 学生综合素质测评显示信息
	 * @param pk
	 * @return
	 */
	public HashMap<String, String> stuZhszcpView(String pk) {
		return dao.stuZhszcpView(pk);
	}
	
	/**
	 * 学生课程成绩信息
	 * @param pk
	 * @return
	 */
	public List<String[]> stuCjInfo(String pk) {
		return dao.stuCjInfo(pk);
	}
	
	//奖学金荣誉称号审核
	public List<String[]> getJxjOrRychQueryList_ser(PjpyYcsfxyActionForm form,PjpyYcsfxyModel model){
		return dao.getJxjOrRychQueryList_db(form,model);
	}
	//奖学金荣誉称号审核
	public List<String[]> getJxjOrRychList_ser(PjpyYcsfxyActionForm form,PjpyYcsfxyModel model,String userType){
		return dao.getJxjOrRychList_db(form,model,userType);
	}
	
	//奖学金荣誉称号审核记录数
	public int getJxjOrRychCount_db(PjpyYcsfxyModel model){
		return dao.getJxjOrRychCount_db(model);
	}
	
	//组合奖学金荣誉称号审核表头
	public List<HashMap<String,String>> getJxjOrRychTabName(String lb){
		String[] colEn = null;
		String[] colCn = null;
		if(Globals.XXDM_AHZYJSXY.equals(Base.xxdm)){
			if("jxj".equals(lb)){
				colEn = new String[] { "pk", "xh", "xm", "xn", "xqmc",
						"nj", "bjmc", "pjf","pm","jxjmc","jlje","xysh","xxsh" };
				colCn = new String[] { "pk", "学号", "姓名", "学年", "学期",
						"年级", "班级名称", "总科平均分","班级排名","奖学金名称","奖励金额", "学院审核", "学校审核" };
			}else{
				colEn = new String[] { "pk", "xh", "xm", "xn", "xqmc",
						"nj", "bjmc", "zhszcpzf","pm","jxjmc","xxsh" };
				colCn = new String[] { "pk", "学号", "姓名", "学年", "学期",
						"年级", "班级名称", "综测成绩","综测班级排名","奖学金名称", "学校审核" };
			}
		}else{
			if("jxj".equals(lb)){
				colEn = new String[] { "pk", "xh", "xm", "xn", "xqmc",
						"nj", "bjmc","xykhcj","xyzfpm", "zhszcpzf","pm","jxjmc","jlje","xxsh" };
				colCn = new String[] { "pk", "学号", "姓名", "学年", "学期",
						"年级", "班级名称","学业成绩","学业班级排名", "综测成绩","综测班级排名","奖学金名称","奖励金额", "学校审核" };
			}else{
				colEn = new String[] { "pk", "xh", "xm", "xn", "xqmc",
						"nj", "bjmc","xykhcj","xyzfpm", "zhszcpzf","pm","jxjmc","xxsh" };
				colCn = new String[] { "pk", "学号", "姓名", "学年", "学期",
						"年级", "班级名称","学业成绩","学业班级排名", "综测成绩","综测班级排名","奖学金名称", "学校审核" };
			}
		}	
		return getTabName_ser(colEn, colCn);
	}
	
	//审核全部通过
	public String jxjOrRychShAll_ser(String userType,String userDep){
		try {
			return dao.jxjOrRychShAll_db(userType,userDep);
		} catch (Exception e) {
			e.printStackTrace();
			return "no";
		}
	}
	
	//批量审核
	public String jxjOrRychShPart_ser(String[] pks,String act,String lb,String userType,String userDep){
		String pkVals = "!@!";
		if(pks != null){
			for(int i=0;i<pks.length;i++){
				pkVals += pks[i]+"!@!";
			}
		}
		try {
			return dao.jxjOrRychShPart_ser(pkVals, act, lb,userType,userDep);
		} catch (Exception e) {
			e.printStackTrace();
			return "no";
		}
	}
	/**
	 * 查询学生信息包括综合素质测评信息
	 * @param xh
	 * @return
	 */
	public HashMap<String, String> getStuInfo(String xh,String xxdm) {
		return dao.getStuInfo(xh,xxdm);
	}
	
	/**
	 * 学生课程成绩信息
	 * @param pk
	 * @return
	 */
	public List<String[]> stuCjInfo(String xh, String xn, String xq) {
		return dao.stuCjInfo(xh, xn,xq);
	}
	
	/**
	 * 保存单个录入的获奖名单信息
	 * @param model
	 * @param userType
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public boolean saveHjmdxx(PjpyYcsfxyModel model, String userType, String type) throws Exception {	
		return dao.saveAddHjmdxx(model, userType,type);
	}
	
	/**
	 * 删除获奖名单信息
	 * @param pkList
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public boolean deleteHjmdxx(String[] pkList, String type) throws Exception{
		return dao.deleteHjmdxx(pkList, type);
	}
	
	/**
	 * 修改获奖名单信息 分学院,学校用户
	 * @param xm
	 * @param userType
	 * @param pkValue
	 * @return
	 */
	public HashMap<String, String> viewHjmdxx(String xm, String userType, String pkValue) {	
		return dao.viewModifyHjmdxx(xm, pkValue,userType);
	}
	
	/**
	 * 修改获奖名单信息 分学院,学校用户
	 * @param userType
	 * @param xm
	 * @param pkValue
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean modiHjmdxx(String userType, String xm, String pkValue,
			PjpyYcsfxyModel model) throws Exception {
			return dao.saveModiHjmdxx(userType,xm, pkValue, model);
	}
	
	/**
	 * 查询获奖名单信息
	 * @param model
	 * @param userType
	 * @param userName
	 * @param dataSearchForm
	 * @return
	 */
	public ArrayList<String[]> queryHjmdxxResult(PjpyYcsfxyModel model,
			String userType, String userName, PjpyYcsfxyActionForm dataSearchForm) {
		return dao.queryHjmdxxResult(model, userType, userName, dataSearchForm);
	}
	
	/**
	 * 查询获奖信息总记录数
	 * @param model
	 * @param userType
	 * @param userName
	 * @param dataSearchForm
	 * @return
	 */
	public int queryHjmdxxResultCount(PjpyYcsfxyModel model,
			String userType, String userName, PjpyYcsfxyActionForm dataSearchForm) {
		return dao.queryHjmdxxResultCount(model, userType, userName, dataSearchForm);
	}
	
	/**
	 * 获奖名单上报查询表头
	 * 
	 * @return
	 */
	public List<HashMap<String, String>> queryHjmdxxTitle() {
		return dao.queryHjmdxxTitle();
	}
	
	/**
	 * 用户批量上报获奖名单
	 * @param xhList
	 * @param model
	 * @return
	 */
	public boolean hjmdPlsb(String userType, PjpyYcsfxyModel model, String[] xhList) throws Exception {
		return dao.hjmdPlsb_db(userType,xhList, model);
	}
	
	/**
	 * 查询获奖名单信息
	 * @param model
	 * @return
	 */
	public HashMap<String, String> viewHjmdxx(PjpyYcsfxyModel model, String pkValue, String userType, String xm) {
		HashMap<String, String> rs = new HashMap<String, String>();
		rs = dao.viewModifyHjmdxx(xm, pkValue,userType);
		if (rs == null || StringUtils.isNull(rs.get("dm"))) {
			return dao.viewHjmdxx(model); 
		}
		return rs;
	}
	
	//获得审核情况
	public HashMap<String, String> getJxjOrRychShxx_ser(String lb,String pk){
		return dao.getJxjOrRychShxx_db(lb, pk);
	}
	
	//单个审核
	public String jxjOrRychDgsh_ser(String pk,PjpyYcsfxyModel model,String userType){
		try {
			return dao.jxjOrRychDgsh_db(pk, model,userType);
		} catch (Exception e) {
			e.printStackTrace();
			return "no";
		}
	}
	
	//查询审核结果(分页)
	public List<String[]> queryShResult_ser(PjpyYcsfxyActionForm form,PjpyYcsfxyModel model,String userName,String isFdy){
		return dao.queryShResult_db(form, model, userName, isFdy);
	}
	
	//查询审核结果总数
	public int queryShResultCount_ser(PjpyYcsfxyModel model,String userName,String isFdy){
		return dao.queryShResultCount_db(model, userName, isFdy);
	}
	
	//学生奖学金获奖情况
	public List<String[]> queryXsJxj_ser(String userName){
		return dao.queryXsJxj_db(userName);
	}
	
	//学生荣誉称号获奖情况
	public List<String[]> queryXsRych_ser(String userName){
		return dao.queryXsRych_db(userName);
	}
	
	//班级成绩导出
	public void expBjcj_ser(PjpyYcsfxyModel model,OutputStream os) throws Exception{
		WritableWorkbook wwb = Workbook.createWorkbook(os);
		WritableSheet ws = wwb.createSheet("数据导出", 0);
		ExcelMB mb = new ExcelMB();
		List<String[]> kclist = dao.queryBjxsKcmc_db(model);
		ws.mergeCells(0, 0, kclist.size()+7, 1);
		String xq = dao.getXqmc_db(model.getXq());
		mb.printToOneCell_multy(ws, model.getXn()+xq+"学期"+DealString.toGBK(model.getBjmc())+"班级成绩一览表" , 0, 0, 18, true, Alignment.CENTRE,
				VerticalAlignment.CENTRE, false, 1000, Border.NONE);
		mb.printToOneCell_multy(ws, "学号", 0, 2, 10, false, Alignment.CENTRE,
				VerticalAlignment.CENTRE, false, 300, Border.ALL);
		mb.printToOneCell_multy(ws, "姓名", 1, 2, 10, false, Alignment.CENTRE,
				VerticalAlignment.CENTRE, false, 300, Border.ALL);
		for(int i=0;i<kclist.size();i++){
			mb.printToOneCell_multy(ws, kclist.get(i)[0], 2+i, 2, 10, false, Alignment.CENTRE,
					VerticalAlignment.CENTRE, false, 300, Border.ALL);
		}
		mb.printToOneCell_multy(ws, "平时考核成绩", kclist.size()+2, 2, 10, false, Alignment.CENTRE,
				VerticalAlignment.CENTRE, false, 300, Border.ALL);
		mb.printToOneCell_multy(ws, "阶段考核成绩", kclist.size()+3, 2, 10, false, Alignment.CENTRE,
				VerticalAlignment.CENTRE, false, 300, Border.ALL);
		mb.printToOneCell_multy(ws, "学业考核成绩", kclist.size()+4, 2, 10, false, Alignment.CENTRE,
				VerticalAlignment.CENTRE, false, 300, Border.ALL);
		mb.printToOneCell_multy(ws, "学业成绩排名", kclist.size()+5, 2, 10, false, Alignment.CENTRE,
				VerticalAlignment.CENTRE, false, 300, Border.ALL);
		mb.printToOneCell_multy(ws, "综合测评成绩", kclist.size()+6, 2, 10, false, Alignment.CENTRE,
				VerticalAlignment.CENTRE, false, 300, Border.ALL);
		mb.printToOneCell_multy(ws, "综测成绩排名", kclist.size()+7, 2, 10, false, Alignment.CENTRE,
				VerticalAlignment.CENTRE, false, 300, Border.ALL);
		List<String[]> xslist = dao.getBjxs_db(model.getBjdm());
		for(int i=0;i<xslist.size();i++){
			model.setXh(xslist.get(i)[0]);
			List<HashMap<String,String>> xscj = dao.getOneXscj_db(model);
			for(int j=0;j<xscj.size();j++){
				HashMap<String,String> tempcj = xscj.get(j);
				mb.printToOneCell_multy(ws, tempcj.get("cj"), Integer.valueOf(tempcj.get("r"))+1, 3+i, 10, false, Alignment.CENTRE,
						VerticalAlignment.CENTRE, false, 300, Border.ALL);
			}
			HashMap<String,String> tempcj = dao.getOneXshzcj_db(model);
			mb.printToOneCell_multy(ws, tempcj.get("xh"), 0, 3+i, 10, false, Alignment.CENTRE,
					VerticalAlignment.CENTRE, false, 300, Border.ALL);
			mb.printToOneCell_multy(ws, tempcj.get("xm"), 1, 3+i, 10, false, Alignment.CENTRE,
					VerticalAlignment.CENTRE, false, 300, Border.ALL);
			mb.printToOneCell_multy(ws, tempcj.get("pjkhcj"), kclist.size()+2, 3+i, 10, false, Alignment.CENTRE,
						VerticalAlignment.CENTRE, false, 300, Border.ALL);
			mb.printToOneCell_multy(ws, tempcj.get("jdkhcj"), kclist.size()+3, 3+i, 10, false, Alignment.CENTRE,
						VerticalAlignment.CENTRE, false, 300, Border.ALL);
			mb.printToOneCell_multy(ws, tempcj.get("xykhcj"), kclist.size()+4, 3+i, 10, false, Alignment.CENTRE,
						VerticalAlignment.CENTRE, false, 300, Border.ALL);
			mb.printToOneCell_multy(ws, tempcj.get("xyzfpm"), kclist.size()+5, 3+i, 10, false, Alignment.CENTRE,
						VerticalAlignment.CENTRE, false, 300, Border.ALL);
			mb.printToOneCell_multy(ws, tempcj.get("zhszcpzf"), kclist.size()+6, 3+i, 10, false, Alignment.CENTRE,
						VerticalAlignment.CENTRE, false, 300, Border.ALL);
			mb.printToOneCell_multy(ws, tempcj.get("pm"), kclist.size()+7, 3+i, 10, false, Alignment.CENTRE,
						VerticalAlignment.CENTRE, false, 300, Border.ALL);
		}
		wwb.write();
		wwb.close();
	}
}
