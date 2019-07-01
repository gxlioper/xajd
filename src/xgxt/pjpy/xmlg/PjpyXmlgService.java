package xgxt.pjpy.xmlg;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import xgxt.DAO.ExcelMB;
import xgxt.action.Base;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.ExcelMethods;
import xgxt.utils.String.StringUtils;

public class PjpyXmlgService {

	/**
	 * 查询综合素质测评表头
	 * @return
	 */
	public List<HashMap<String, String>> queryZhszcpTitle() {
		PjpyXmlgDAO dao = new PjpyXmlgDAO();
		String[] enList = new String[] { "pk", "r", "xn","xq", "xh",
				"xm", "bjmc","dcj", "zcj", "tcj", "xqzf", "xqpm","xnzf", "xnpm"};
		String[] cnList = new String[] { "pk", "行号", "学年","学期", "学号",
				"姓名", "班级","德育表现分", "智育表现分", "文体表现分","学期总分", "学期排名","学年总分", "学年排名"};
		return dao.getTitleList(enList, cnList);
	}
	
	/**
	 * 综合素质测评查询结果
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> queryZhszcpResult(PjpyXmlgModel model) throws Exception{
		PjpyXmlgDAO dao = new PjpyXmlgDAO();
		return dao.queryZhszcpResult(model);
	}
	
	/**
	 * 删除综测信息
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean deleteZhszcpxx(PjpyXmlgModel model) throws Exception{
		PjpyXmlgDAO dao = new PjpyXmlgDAO();
		return dao.deleteZhszcpxx(model);
	}
	
	/**
	 * 增加综合素质测评成绩
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean addZhszcpxx(PjpyXmlgModel model) throws Exception {
		PjpyXmlgDAO dao = new PjpyXmlgDAO();
		return dao.addZhszcpxx(model);
	}
	
	/**
	 * 修改综合素质测评成绩
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean updateZhszcpxx(PjpyXmlgModel model) throws Exception{
		PjpyXmlgDAO dao = new PjpyXmlgDAO();
		return dao.updateZhszcpxx(model);
	}
	
	/**
	 * 查询单条综合素质测评信息
	 * @param model
	 * @return
	 */
	public HashMap<String, String> queryZhszcpOnexx(PjpyXmlgModel model) {
		PjpyXmlgDAO dao = new PjpyXmlgDAO();
		return dao.queryZhszcpOnexx(model);
	}
	
	/**
	 * 学院用户保存综测排名方式信息
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean saveZhszcpPmfs(PjpyXmlgModel model) throws Exception{
		PjpyXmlgDAO dao = new PjpyXmlgDAO();
		return dao.saveZhszcpPmfs(model);
	}
	
	/**
	 * 学院用户查询综测排名方式
	 * @param model
	 * @return
	 */
	public HashMap<String, String> getXyZhszcppmfs(PjpyXmlgModel model) {
		PjpyXmlgDAO dao = new PjpyXmlgDAO();
		return dao.getXyZhszcppmfs(model);
	}
	
	/**
	 * 奖学金,荣誉称号比例设置分页菜单
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getPageCard(String userType) throws Exception{
		PjpyXmlgDAO dao = new PjpyXmlgDAO();
		return dao.getPageCard(userType);
	}
	
	/**
	 * 查询范围列表
	 * @return
	 */
	public List<HashMap<String, String>> getQueryType() {
		PjpyXmlgDAO dao = new PjpyXmlgDAO();
		String[] enList = new String[]{"xy", "zy", "bj"};
		String[] cnList = new String[]{"院系", "专业", "班级"};
		return dao.getTitleList(enList, cnList);
	}
	/**
	 * 查询范围列表
	 * @return
	 */
	public List<HashMap<String, String>> getQueryXyType() {
		PjpyXmlgDAO dao = new PjpyXmlgDAO();
		String[] enList = new String[]{"zy", "bj"};
		String[] cnList = new String[]{"专业", "班级"};
		return dao.getTitleList(enList, cnList);
	}
	/**
	 * 查询奖学金列表(是否启用为是)
	 * @param model
	 * @return
	 */
	public List<HashMap<String, String>> getJxjList(PjpyXmlgModel model) {
		PjpyXmlgDAO dao = new PjpyXmlgDAO();
		return dao.getJxjList(model);
	}
	
	/**
	 * 查询荣誉称号列表
	 * @return
	 */
	public List<HashMap<String, String>> getRychList() {
		PjpyXmlgDAO dao = new PjpyXmlgDAO();
		return dao.getRychList();
	}
	
	/**
	 * 查询比例设置表头
	 * @return 
	 */
	public List<HashMap<String, String>> queryCsszTitle() {
		PjpyXmlgDAO dao = new PjpyXmlgDAO();
		String[] en = new String[] { "pk", "r", "xn","mc", "bmmc", "nj","bmrs", "bl", "tzrs"};
		String[] cn = new String[] { "pk", "行号", "学年","奖项名称", "部门名称", "年级","部门人数", "比例(%)", "计算人数"};
		return dao.getTitleList(en, cn);
	}
	
	/**
	 * 初始化奖学金，荣誉称号比例设置数据
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean initData(PjpyXmlgModel model) throws Exception {
		PjpyXmlgDAO dao = new PjpyXmlgDAO();
		return dao.initData(model);
	}
	
	/**
	 * 查询比例设置表头结果
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> queryBlszxx(PjpyXmlgModel model) throws Exception{
		PjpyXmlgDAO dao = new PjpyXmlgDAO();
		return dao.queryBlszxx(model);
	}
	
	/**
	 * 批量设置奖学金比例
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean modiJxjblszxx(PjpyXmlgModel model) throws Exception {
		PjpyXmlgDAO dao = new PjpyXmlgDAO();
		return dao.modiJxjblszxx(model);
	}
	
	/**
	 * 查询比例设置单条信息
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> queryBlszxxOne(PjpyXmlgModel model)
			throws Exception {
		PjpyXmlgDAO dao = new PjpyXmlgDAO();
		return dao.queryBlszxxOne(model);
	}
	
	/**
	 * 修改比例设置单条信息
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean modiBlszxxOne(PjpyXmlgModel model) throws Exception {
		PjpyXmlgDAO dao = new PjpyXmlgDAO();
		return dao.modiBlszxxOne(model);
	}
	/**
	 * 查询比例设置表头
	 * @return 
	 */
	public List<HashMap<String, String>> queryXyJxjRsSzTitle() {
		PjpyXmlgDAO dao = new PjpyXmlgDAO();
		String[] en = new String[] { "pk", "r", "xn","mc","je","bmmc", "nj","bmrs", "bl", "jyrs","tzrs"};
		String[] cn = new String[] { "pk", "行号", "学年","奖项名称","奖项金额", "部门名称", "年级","部门人数", "比例(%)", "建议人数","调整人数"};
		return dao.getTitleList(en, cn);
	}
	/**
	 * 查询比例学院设置表头结果
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> queryXyJxjRsSz(PjpyXmlgModel model) throws Exception{
		PjpyXmlgDAO dao = new PjpyXmlgDAO();
		return dao.queryXyJxjRsSz(model);
	}
    public boolean XyJxjRsSzSave(PjpyXmlgModel model) throws SQLException{
    	PjpyXmlgDAO dao = new PjpyXmlgDAO();
    	return dao.XyJxjRsSzSave(model);
    }
    public boolean XyRychRsSzSave(PjpyXmlgModel model) throws SQLException{
    	PjpyXmlgDAO dao = new PjpyXmlgDAO();
    	return dao.XyJxjRsSzSave(model);
    }
    public boolean tzRsFsSzSave(PjpyXmlgModel model) throws SQLException{
    	PjpyXmlgDAO dao = new PjpyXmlgDAO();
    	return dao.XyJxjRsSzSave(model);
    }
	public String getXyZje(String xydm,String xn){
		PjpyXmlgDAO dao = new PjpyXmlgDAO();
		return dao.getXyZje(xydm, xn);
	}
	public boolean tzfsUpdateSave(PjpyXmlgModel model) throws Exception{
		PjpyXmlgDAO dao = new PjpyXmlgDAO();
		return dao.tzfsUpdateSave(model);
	}
	/**
	 * 查询奖学金类别列表
	 * @param model
	 * @return
	 */
	public List<HashMap<String, String>> getJxjlbList() {
		PjpyXmlgDAO dao = new PjpyXmlgDAO();
		return dao.getJxjlbList();
	}
	
	/**
	 * 查询限制方式列表
	 * @return
	 */
	public List<HashMap<String, String>> getXzfsList() {
		String[] en = new String[]{"1", "2"};
		String[] cn = new String[]{"四舍五入限制", "金额限制"};
		PjpyXmlgDAO dao = new PjpyXmlgDAO();
		return dao.getTitleList(en, cn);
	}
	
	/**
	 * 保存奖学金人数调整限制方式
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean saveJxjrstzrs(PjpyXmlgModel model) throws Exception {
		PjpyXmlgDAO dao = new PjpyXmlgDAO();
		return dao.saveJxjrstzrs(model);
	}
	/**
	 * 查询奖学金学院调整方式
	 * @return
	 */
	public String  getXyTzFs(String xn,String xydm) {
		PjpyXmlgDAO dao = new PjpyXmlgDAO();
		return dao.getXyTzFs(xn, xydm);
	}
	/**
	 * 查询奖学金调整限制方式
	 * @return
	 */
	public HashMap<String, String> getJxjrstzxzrs() {
		PjpyXmlgDAO dao = new PjpyXmlgDAO();
		return dao.getJxjrstzxzrs();
	}
	
	/**
	 * 查询学生综合素质测评信息
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getStuZhszcpList(PjpyXmlgModel model)
			throws Exception {
		PjpyXmlgDAO dao = new PjpyXmlgDAO();
		return dao.getStuZhszcpList(model);
	}
	
	/**
	 * 查询学生申请奖学金的辅助申请信息
	 * @param model
	 * @return
	 */
	public HashMap<String, String> getStuJxjsqfzxx(PjpyXmlgModel model) {
		PjpyXmlgDAO dao = new PjpyXmlgDAO();
		return dao.getStuJxjsqfzxx(model);
	}
	
	/**
	 * 增加奖学金申请信息和申请辅助信息
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean addJxjsqxx(PjpyXmlgModel model) throws Exception {
		PjpyXmlgDAO dao = new PjpyXmlgDAO();
		return dao.addJxjsqxx(model);
	}
		
	/**
	 * 奖学金查询表头
	 * @return
	 */
	public List<HashMap<String, String>> queryJxjsqTitle() {
		String[] en = new String[] { "pk",  "dis","r", "xn", "xh", "xm", "bjmc", "jxjmc","xnpm", "xysh", "xxsh"};
		String[] cn = new String[] { "pk", "dis", "行号", "学年", "学号", "姓名", "班级", "奖学金","学年综测排名", "学院审核", "学校审核"};
		PjpyXmlgDAO dao = new PjpyXmlgDAO();
		return dao.getTitleList(en, cn);
	}
	
	/**
	 * 奖学金查询结果(学院，学校)
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> queryJxjsqResult(PjpyXmlgModel model, boolean xysh) throws Exception{
		PjpyXmlgDAO dao = new PjpyXmlgDAO();
		if ("xy".equalsIgnoreCase(model.getUserType())) {
			return dao.queryJxjsqxxByxy(model);
		} else {
			return dao.queryJxjsqxxByxx(model, xysh);
		}
	}
	
	/**
	 * 删除奖学金申请数据
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean deleteJxjsqxx(PjpyXmlgModel model)throws Exception {
		PjpyXmlgDAO dao = new PjpyXmlgDAO();
		return dao.deleteJxjsqxx(model);
	}
	
	/**
	 * 查询奖学金单条申请信息
	 * @param model
	 * @return
	 */
	public HashMap<String, String> viewJxjsqxx(PjpyXmlgModel model) {
		PjpyXmlgDAO dao = new PjpyXmlgDAO();
		return dao.viewJxjsqxx(model);
	}
	
	/**
	 * 修改奖学金申请信息,申请辅助信息
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean updateJxjsqxx(PjpyXmlgModel model) throws Exception {
		PjpyXmlgDAO dao = new PjpyXmlgDAO();
		return dao.updateJxjsqxx(model);
	}
	
	/**
	 * 学生查询奖学金申请信息
	 * @param model
	 * @return
	 */
	public List<String[]> queryStujxjsqxx(PjpyXmlgModel model) {
		PjpyXmlgDAO dao = new PjpyXmlgDAO();
		return dao.queryStujxjsqxx(model);
	}
	
	/**
	 * 奖学金查询表头
	 * @return
	 */
	public List<HashMap<String, String>> queryJxjsqByStuTitle() {
		String[] en = new String[] { "pk",  "dis","r", "xn",  "jxjmc", "je","xysh", "xxsh"};
		String[] cn = new String[] { "pk", "dis", "行号", "学年","奖学金", "金额","学院审核", "学校审核"};
		PjpyXmlgDAO dao = new PjpyXmlgDAO();
		return dao.getTitleList(en, cn);
	}
	
	/**
	 * 查询比例设置表头
	 * @return 
	 */
	public List<HashMap<String, String>> queryRychRsszTitle() {
		PjpyXmlgDAO dao = new PjpyXmlgDAO();
		String[] en = new String[] { "pk", "r", "xn","mc", "bmmc", "nj","bmrs", "bl","jyrs","tzrs"};
		String[] cn = new String[] { "pk", "行号", "学年","奖项名称", "部门名称", "年级","部门人数", "比例(%)", "建议人数","调整人数"};
		return dao.getTitleList(en, cn);
	}
	
	/**
	 * 查询比例设置表头结果
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> queryRychRssz(PjpyXmlgModel model) throws Exception{
		PjpyXmlgDAO dao = new PjpyXmlgDAO();
		return dao.queryRychRssz(model);
	}
	
	/**
	 * 查询审核列表
	 * @return
	 */
	public List<HashMap<String, String>> getShList() {
		PjpyXmlgDAO dao = new PjpyXmlgDAO();
		return dao.getShList();
	}
	
	/**
	 * 查询单个奖学金审核信息
	 * @param pkValue
	 * @return
	 */
	public HashMap<String, String> queryJxjshDgxx(PjpyXmlgModel model) {
		PjpyXmlgDAO dao = new PjpyXmlgDAO();
		if ("xy".equalsIgnoreCase(model.getUserType())) {
			return dao.queryJxjshDgxxByxy(model.getPkValue());
		} else {
			return dao.queryJxjshDgxxByxx(model.getPkValue());
		}
	}
	
	/**
	 * 保存奖学金审核信息
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean saveJxjshxx(PjpyXmlgModel model, String act, String key, boolean sh) throws Exception {
		PjpyXmlgDAO dao = new PjpyXmlgDAO();
		if (!StringUtils.isNull(act)) {
			if ("tg".equalsIgnoreCase(act)) {
				model.setSh("通过");
			} else if ("btg".equalsIgnoreCase(act)) {
				model.setSh("不通过");
			}
		}
		if ("xy".equalsIgnoreCase(model.getUserType())) {
			if ("btg".equalsIgnoreCase(act)) {
				return dao.saveJxjshxxByxy(model, key);
			} else {
				return dao.saveJxjshxxByxy(model, key, sh);
			}
		} else {
			if ("btg".equalsIgnoreCase(act)) {
				return dao.saveJxjshxxByxx(model, key);
			} else {
				return dao.saveJxjshxxByxx(model, key, sh);	
			}
		}
	}
	
	/**
	 * 增加荣誉称号申请信息和申请辅助信息
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean serv_addRychsqxx(PjpyXmlgModel model) throws Exception {
		PjpyXmlgDAO dao = new PjpyXmlgDAO();
		return dao.addRychsqxx(model);
	}
	/**
	 * 查询荣誉称号申请结果
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> serv_queryRychsqxx(PjpyXmlgModel model,String userType) throws Exception{
		PjpyXmlgDAO dao = new PjpyXmlgDAO();
		return dao.queryRychsqxx(model, userType);
		
	}
	/**
	 * 荣誉称号
	 * @return
	 */
	public List<HashMap<String, String>> queryRychsqTitle() {
		
		String[] en =  new String[] { "pk","dis","r","xn","xh","xm","xb","xymc","bjmc","rychmc","xysh","xxsh"};
		String[] cn = new String[] { "pk", "dis", "行号", "学年", "学号", "姓名","性别","院系", "班级","荣誉称号", "学院审核", "学校审核"};
		PjpyXmlgDAO dao = new PjpyXmlgDAO();
		return dao.getTitleList(en, cn);
	}
	/**
	 * 荣誉称号审核
	 * @return
	 */
	public List<HashMap<String, String>> queryRychShTitle() {
		
		String[] en =  new String[] { "pk","dis","r","xn","xh","xm","bjmc","rychmc","xnpm","xysh","xxsh"};
		String[] cn = new String[] { "pk", "dis", "行号", "学年", "学号", "姓名","班级","荣誉称号", "学院综测排名","学院审核", "学校审核"};
		PjpyXmlgDAO dao = new PjpyXmlgDAO();
		return dao.getTitleList(en, cn);
	}
	/**
	 * 查询荣誉称号单条申请信息
	 * @param model
	 * @return
	 */
	public HashMap<String, String> viewRychsqxx(PjpyXmlgModel model) {
		PjpyXmlgDAO dao = new PjpyXmlgDAO();
		return dao.viewRychsqxx(model);
	}
	/**
	 * 修改荣誉称号申请信息
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean serv_modiRychsqxx(PjpyXmlgModel model,String pkValue) throws Exception {
		PjpyXmlgDAO dao = new PjpyXmlgDAO();
		return dao.modiRychsqxx(model,pkValue);
	}
	/**
	 * 删除荣誉称号申请数据
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean serv_deleteRychsqxx(PjpyXmlgModel model)throws Exception {
		PjpyXmlgDAO dao = new PjpyXmlgDAO();
		return dao.deleteRychsqxx(model);
	}
	
	/**
	 * 查询学院调整专业,班级奖学金,荣誉称号的人数
	 * 
	 * @param model
	 * @param key 奖学金:jxj 荣誉称号:rych  如果是奖学金就要把jxjdm赋值，反之rychdm赋值
	 * @return
	 */
	public String getXyJxtzrs(PjpyXmlgModel model, String key) {
		PjpyXmlgDAO dao = new PjpyXmlgDAO();
		return dao.getXyJxtzrs(model, key);
	}
	
	/**
	 * 奖学金，荣誉称号申请表打印
	 * @param pkValue
	 * @param key 奖学金:jxj, 荣誉称号:rych
	 * @return
	 */
	public HashMap<String, String> jxjPrint(String pkValue, String key)throws Exception {
		PjpyXmlgDAO dao = new PjpyXmlgDAO();
		return dao.jxjPrint(pkValue, key);
	}
	
	/**
	 * 学校查询荣誉称号申请结果
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> serv_queryRychShxx(PjpyXmlgModel model,String userType) throws Exception{
		PjpyXmlgDAO dao = new PjpyXmlgDAO();
		return dao.queryRychShxx(model, userType);
	}
	
	/**
	 * 将奖学金金额按银行类别输出
	 * @param wwb
	 */
	public void writeJxjjehz(WritableWorkbook wwb, PjpyXmlgModel model)
			throws Exception {
		PjpyXmlgDAO dao = new PjpyXmlgDAO();
		
		//获奖数据中的银行名称列表
		String[] yhmcList = dao.getJxjyhmcList(model.getXn());
		//首页输出单元格
		WritableSheet ws1 = wwb.createSheet("首页",0);
		
		//样式及字体大小
		WritableCellFormat tStyle = new WritableCellFormat();
 	    WritableCellFormat wcfStyle = new WritableCellFormat();
 	    WritableCellFormat style = new WritableCellFormat();
 	    WritableFont font = new WritableFont(WritableFont.ARIAL,13);
		wcfStyle.setFont(font);
		style.setFont(font); 
	    wcfStyle.setAlignment(Alignment.CENTRE);
	    tStyle.setAlignment(Alignment.CENTRE);
	    style.setAlignment(Alignment.LEFT);
	    wcfStyle.setBorder(Border.ALL, BorderLineStyle.THIN);
		   
	    double[] yhje = new double[yhmcList.length];//各银行获奖金额
	    double zje = 0;//首页总金额
	    int rs = 0;//首页获奖总人数
	    
	    String tableTitle = model.getXn()
		+ "学年"
		+ dao.getJxjscmc(model.getKeys()) + "工资单";
	    
	    //输出第二,三等单元格
		if (yhmcList != null) {
			for (int i=0;i<yhmcList.length;i++) {
				String yhmc = yhmcList[i];
				if (StringUtils.isNull(yhmc)) {
					continue;
				}
				WritableSheet ws = wwb.createSheet(yhmc, 1 + i);
				//银行所对应的获奖数据
				List<String[]> yhmc_hjdata = dao.getJxjjeByYhmc(model, yhmc);
												
				ws.addCell(new Label(0, 0, tableTitle, wcfStyle));//输出表头
				ws.mergeCells(0, 0, 4, 1);
				String[] title = new String[]{"序号", "姓名", yhmc + "卡号", "金额", "领款人签名"};

				ws.addCell(new Label(0, 3, title[0], wcfStyle));//输出二级表头
				ws.addCell(new Label(1, 3, title[1], wcfStyle));//输出二级表头
				ws.addCell(new Label(2, 3, title[2], wcfStyle));//输出二级表头
				ws.addCell(new Label(3, 3, title[3], wcfStyle));//输出二级表头
				ws.addCell(new Label(4, 3, title[4], wcfStyle));//输出二级表头
	
				//开始对每一行写数据
				for (int j = 0; j < yhmc_hjdata.size(); j++) {
					String[] oneData = yhmc_hjdata.get(j);
					int k = 0;
					for (int l = 1; l < oneData.length; l++) {
						ws.addCell(new Label(k, j + 4, oneData[l], tStyle));
						//设置列宽
						ws.setColumnView(k, (StringUtils.isNull(oneData[l]) ? 1
								: oneData[l].length()) * 4);
						k++;
					}
				}
				
				double je = getZje(yhmc_hjdata);//计算该页的总金额
				//将总金额输出
				ws.addCell(new Label(0, yhmc_hjdata.size() + 4, "页金额小计:" + je,
						wcfStyle));
				ws.mergeCells(0, yhmc_hjdata.size() + 4, 4,
						yhmc_hjdata.size() + 5);
				
				//统计首页的内容
				yhje[i] = je;//每个银行的总金额
				rs += yhmc_hjdata.size();//首页的总人数
				zje += je;//首页的总金额
				
			}
		}
		
		//输出首页信息
		ws1.addCell(new Label(2, 0, tableTitle + "首页", wcfStyle));//输出表头
		ws1.mergeCells(2, 0, 1+3+ (yhmcList != null ? yhmcList.length : 2), 1);
		
		ws1.addCell(new Label(2, 2, "院系盖章", style));//输出表头
		ws1.mergeCells(2, 2, 1+3+ (yhmcList != null ? yhmcList.length : 2), 3);
		
		String[] ejTitle = getEjTitle(yhmcList);//二级表头
		String[] ejResult = getEjResult(yhje, rs, zje);//二级表头内容
		List<String[]> dataList = new ArrayList<String[]>();
		dataList.add(ejTitle);
		dataList.add(ejResult);
		
		//写二级表头
		if (dataList != null) {
			for (int i=0;i<dataList.size();i++) {
				String[] oneData = dataList.get(i);
				if (oneData != null) {
					//开始写每行数据
					int k=2;
					for (int j=0;j<oneData.length;j++) {
						ws1.addCell(new Label(k, 4+i, oneData[j], wcfStyle));
						ws1.setColumnView(k, 20);//设置每个单元格宽度
						k++;
					}
				}
			}
			ws1.addCell(new Label(2, 4+dataList.size() + 1, "填报人：   " +
					"                       " +
					"               分管书记签字：", wcfStyle));
			ws1.mergeCells(2, dataList.size() + 5, 1+3+ (yhmcList != null ? yhmcList.length : 2),
					dataList.size() + 5);
		}
		
		ExcelMethods.submitWritableWorkbookOperations(wwb);//输出到客户端
	}
	/**
	 * 奖学金、荣誉称号公示单导出
	 * @throws Exception 
	 * @throws Exception 
	 */
	public void serv_jxjRychGsddc(WritableWorkbook wwb, PjpyXmlgModel model,String type) throws Exception{
		PjpyXmlgDAO dao = new PjpyXmlgDAO();
		List<HashMap<String,String>> yxList = dao.getJxjRychYxList(type, model);
		List<HashMap<String,String>>xsList = dao.getJxjRychXsList(type, model);		
        String title = model.getXn()+"学年学生 "+dao.getJxjRychMc(type,model)+" 公示名单";
        WritableSheet ws = wwb.createSheet(title, 0);
		WritableCellFormat wcf1 = new WritableCellFormat(); // 构造单元格格式
		wcf1 = ExcelMethods.getWcf(WritableFont.ARIAL, 12, false,
				Alignment.LEFT, VerticalAlignment.CENTRE, true, Border.NONE);
		// 填充标题
		ExcelMB ex = new ExcelMB();
		ws.mergeCells(0, 0, 7 , 1);
		ex.printToOneCell_multy(ws, title, 0, 0, 10, true, Alignment.CENTRE,
				VerticalAlignment.CENTRE, true, 650, Border.NONE);
		int m=0;
//		按照报表格式填充Excel
		if(yxList.size()>0){
			for(int i=0;i<yxList.size();i++){
				String xymc = Base.isNull(yxList.get(i).get("xymc"))?"":yxList.get(i).get("xymc");
				String cout = yxList.get(i).get("cout");
				ws.mergeCells(0,2+i+m,7,2+i+m);
				ws.addCell(new Label(0,2+i+m,xymc+cout+"人",wcf1));
				int[] p= new int[]{0,3,6};
				int[] q= new int[]{1,4,7};
				int n=0;
				int num=0;		
				for(int j=0;j<xsList.size();j++){
					if(n>2){
						n=0;
					}
					String xymcV =xsList.get(j).get("xymc");
					xymcV = Base.isNull(xymcV)?" ":xymcV;
					if(xymcV.equalsIgnoreCase(xymc)){
						ws.addCell(new Label(p[n],3+i+m,xsList.get(j).get("bjmc")));
						ws.addCell(new Label(q[n],3+i+m,xsList.get(j).get("xm")));												
						n++;
						num++;
						if(Integer.parseInt(cout)<2
								||num==Integer.parseInt(cout)
								||n>2){
							m++;
						}
//						if(num==Integer.parseInt(cout)){
//							m++;
//						}
					}else{						
						n=0;
					}					
				}
				m++;
			}
		}
		ExcelMethods.submitWritableWorkbookOperations(wwb);//输出到客户端
	}
		
	/**
	 * 返回该奖项的总金额
	 * @param yhmc_hjdata
	 * @return
	 */
	public double getZje(List<String[]> yhmc_hjdata) {
		double je = 0;
		if (yhmc_hjdata != null) {
			for (String[] s : yhmc_hjdata) {
				if (s != null && s.length==5) {
					je += StringUtils.isNull(s[4]) ? 0 : Double
							.parseDouble(s[4]);
				}
			}
		}
		return je;
	}

	/**
	 * 首页二级表头信息
	 * @param yhmc
	 * @return
	 */
	public String[] getEjTitle(String[] yhmc) {
		if (yhmc != null) {
			String[] title = new String[yhmc.length + 3];
			title[0] = "总人数";
			for (int i=0;i<yhmc.length;i++) {
				title[i+1] = yhmc[i] + "金额";
			}
			title[title.length-1] = "总页数";
			title[title.length-2] = "总金额";
			return title;
		}
		return null;
	} 
	
	/**
	 * 首页面二级表头结果信息
	 * @param yhje
	 * @param rs
	 * @param zje
	 * @return
	 */
	public String[] getEjResult(double[] yhje, int rs, double zje) {
		if (yhje != null) {
			String[] je = new String[yhje.length + 3];
			for (int i=0;i<yhje.length;i++) {
				je[i+1] = String.valueOf(yhje[i]);
			}
			je[0] = String.valueOf(rs);
			je[je.length-2] = String.valueOf(zje);
			return je;
		}
		return null;
	}
	/**
	 * 评优推荐表导出
	 * @throws Exception 
	 * @throws Exception 
	 */
	public void serv_pytjb(WritableWorkbook wwb, PjpyXmlgModel model) throws Exception{
		PjpyXmlgDAO dao = new PjpyXmlgDAO();
		
		List<HashMap<String,String>>list = dao.pytjb(model);
		
		String bjdm = model.getBjdm();
		String zydm = model.getZydm();
		String xydm = model.getXydm();
		String querry = " where 1=1 ";
		String clin="";
		String mapV = "";
		if(!Base.isNull(bjdm)){
			clin="班";
			mapV="bjmc";
			querry+=" and bjdm='"+bjdm+"'";
		}else if(!Base.isNull(zydm)){
			clin="专业";
			mapV="zymc";
			querry+=" and zydm='"+zydm+"'";
		}else{
			clin="院系";
			mapV="xymc";
			querry+=" and xydm='"+xydm+"'";
		}
		String cout = dao.getXsCout(querry);
		HashMap<String,String>xyZyBjMap = CommonQueryDAO.dao_getInfo("view_njxyzybj", null, querry);
		WritableSheet ws = wwb.createSheet(xyZyBjMap.get(mapV), 0);
        String title ="本"+clin+"评定学年学生总数： "+cout+" 人";
		WritableCellFormat wcf1 = new WritableCellFormat(); // 构造单元格格式
		wcf1 = ExcelMethods.getWcf(WritableFont.ARIAL, 12, false,
				Alignment.LEFT, VerticalAlignment.CENTRE, true, Border.ALL);
		WritableCellFormat wcf2 = new WritableCellFormat(); // 构造单元格格式
		wcf2 = ExcelMethods.getWcf(WritableFont.ARIAL, 12, false,
				Alignment.CENTRE, VerticalAlignment.TOP, false, Border.ALL);
		// 填充标题
		ExcelMB ex = new ExcelMB();
		ws.mergeCells(0, 0, 13 , 1);
		ex.printToOneCell_multy(ws, title, 0, 0, 10, true, Alignment.LEFT,
				VerticalAlignment.BOTTOM, true, 650, Border.ALL);
		ws.addCell(new Label(0,2,"序号",wcf1));
		ws.addCell(new Label(1,2,"班级",wcf1));
		ws.addCell(new Label(2,2,"学号",wcf1));
		ws.addCell(new Label(3,2,"姓名",wcf1));
		ws.addCell(new Label(4,2,"综合排名",wcf1));
		ws.addCell(new Label(5,2,"综合(一)",wcf1));
		ws.addCell(new Label(6,2,"综合(二)",wcf1));
		ws.addCell(new Label(7,2,"学业排名",wcf1));
		ws.addCell(new Label(8,2,"重修科数",wcf1));
		ws.addCell(new Label(9,2,"补考次数",wcf1));
		ws.addCell(new Label(10,2,"在读期间违纪记录",wcf1));
		ws.addCell(new Label(11,2,"在读期间已获奖励",wcf1));
		ws.addCell(new Label(12,2,"本学年任职情况",wcf1));
		ws.addCell(new Label(13,2,"院系初评意见",wcf1));		
//		int m=0;
//		按照报表格式填充Excel
		if(list.size()>0){
			for(int i=0;i<list.size();i++){
				ws.addCell(new Label(0,3+i,list.get(i).get("r"),wcf2));
				ws.addCell(new Label(1,3+i,list.get(i).get("bjmc"),wcf2));
				ws.addCell(new Label(2,3+i,list.get(i).get("xh"),wcf2));
				ws.addCell(new Label(3,3+i,list.get(i).get("xm"),wcf2));
				ws.addCell(new Label(4,3+i,list.get(i).get("xnpm"),wcf2));
				ws.addCell(new Label(5,3+i,list.get(i).get("zh1"),wcf2));
				ws.addCell(new Label(6,3+i,list.get(i).get("zh2"),wcf2));
				ws.addCell(new Label(7,3+i,list.get(i).get("xypm"),wcf2));
				ws.addCell(new Label(8,3+i,list.get(i).get("cxcs"),wcf2));
				ws.addCell(new Label(9,3+i,list.get(i).get("bkcs"),wcf2));
				ws.addCell(new Label(10,3+i,list.get(i).get("cflbmc"),wcf2));
				ws.addCell(new Label(11,3+i,list.get(i).get("jlqk"),wcf2));
				ws.addCell(new Label(12,3+i,list.get(i).get("drzw"),wcf2));
				ws.addCell(new Label(13,3+i,list.get(i).get("xyyj"),wcf2));
			}
		}
		ExcelMethods.submitWritableWorkbookOperations(wwb);//输出到客户端
	}
		
	/**
	 * 查询学院调整后的金额数据
	 * @param model
	 * @param fpfs
	 * @return
	 */
	public String getXytzje(PjpyXmlgModel model, String fpfs) {
		PjpyXmlgDAO dao = new PjpyXmlgDAO();
		return dao.getXytzje(model, fpfs);
	}
	
	/**
	 * 对查询出来的奖学金金额数据解析输出
	 * @param rs
	 * @return
	 */
	public List<String[]> parseQueryJxjjeResult(List<String[]> rs) {
		List<String[]> dataList = new ArrayList<String[]>();
		if (rs != null) {
			for (int i=0;i<rs.size();i++) {
				String[] oneData = rs.get(i);
				if (oneData != null && oneData.length == 5) {
					String[] jxjmcList = StringUtils.isNull(oneData[2]) ? null : oneData[2].split(",");
					String[] newData = new String[jxjmcList != null ? (jxjmcList.length + 3) : 3];
					newData[0] = oneData[0];
					for (int j=0;j<jxjmcList.length;j++) {
						String[] rsList = jxjmcList[j] != null ? jxjmcList[j].split("/") : null;
						String tzrs = "";
						if (rsList != null && rsList.length==2) {
							if (rsList[0] != null && rsList[0].startsWith(".")) {
								rsList[0] = "0" + rsList[0];
							}
							if (rsList[1] != null && rsList[1].startsWith(".")) {
								rsList[1] = "0" + rsList[1];
							}
							tzrs = rsList[0] + "/" + rsList[1];
						}
						newData[j+1] = tzrs;
					}
					newData[newData.length-2] = oneData[3];
					newData[newData.length-1] = oneData[4];
					dataList.add(newData);
				}
			}
		}
		return dataList;
	}
	
	/**
	 * 查询金额汇总数据表头
	 * @param rs
	 * @return
	 */
	public List<String[]> queryJxjjehzTitle(List<String[]> rs) {
		if (rs != null && rs.get(0) != null) {
			String jxjmc = rs.get(0)[1];
			String[] jxjmcList = jxjmc.split(",");
			String[] title = new String[(StringUtils.isNull(jxjmc) ? 3
					: ((jxjmcList != null ? jxjmcList.length : 0) + 3))];
			title[0] = "学院";
			for (int i = 0; i < jxjmcList.length; i++) {
				title[i + 1] = jxjmcList[i] + "\n(计算人数/调整人数)";
			}
			title[title.length - 2] = "计算金额\n(元)";
			title[title.length - 1] = "调整金额\n(元)";
			List<String[]> titleList = new ArrayList<String[]>();
			titleList.add(title);
			return titleList;
		}
		return null;
	}

	/**
	 * 查询金额汇总数据结果
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> queryJxjjehzData(PjpyXmlgModel model) throws Exception{
		PjpyXmlgDAO dao = new PjpyXmlgDAO();
		return dao.queryJxjjehzData(model);
	}

//	public static void main(String...strings) {
//		String s= ".5";
//		System.out.println(s.startsWith("."));
//	}		
}
