package xgxt.pjpy.whlgdx;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import xgxt.utils.ExcelMethods;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 武汉理工大学评奖评优Service
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 李容</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-08-01</p>
 */
public class PjpyWhlgdxService {
	
	/**
	 * 获取学生的基本信息
	 * @param xh HashMap<String, String>
	 * @return 
	 * */
	public HashMap<String, String> getStuInfo(String xh){
		PjpyWhlgDAO dao = new PjpyWhlgDAO();		
		return dao.getStuInfo(xh);
	}
	
	/**
	 * 综合素质测评查询结果表头
	 * @return List
	 * */
	public List getZhszcpTitle(){
		PjpyWhlgDAO dao = new PjpyWhlgDAO();
		return dao.getZhszcpTitle();
	}
	
	/**
	 * 综合素质测评查询结果集
	 * @return List
	 * @throws Exception 
	 * */
	public List getZhszcpRs(WhlgdxZhszcpModel zhszcpModel) throws Exception{
		PjpyWhlgDAO dao = new PjpyWhlgDAO();
		return dao.getZhszcpResult(zhszcpModel);
	}
	
	/**
	 * 查询综合素质测评详细详细
	 * */
	public HashMap<String, String> getZhszcpInfoByPk(String pkValue){
		PjpyWhlgDAO dao = new PjpyWhlgDAO();
		return dao.getZhszcpInfoByPk(pkValue);
	}
	
	/**
	 * 综合素质测评信息保存
	 * @param model
	 * @param request
	 * @return boolean
	 * @throws Exception 
	 * */
	public boolean zhszcpSave(WhlgdxZhszcpModel model, HttpServletRequest request) throws Exception{
		PjpyWhlgDAO dao = new PjpyWhlgDAO();
		return dao.saveZhszcp(model,request);
	}
	
	/**
	 * @throws Exception 
	 * 
	 * */
	public String zhszcpDel(String[] key, HttpServletRequest request) throws Exception{
		PjpyWhlgDAO dao = new PjpyWhlgDAO();
		return dao.zhszcpDel(key,request);
	}
	
	/**
	 * 综合素质测评数据导出
	 * @param wwb
	 * @param model
	 * @throws WriteException 
	 * @retun void
	 * */
	public void printZhszcp(WritableWorkbook wwb,WhlgdxZhszcpModel model) throws WriteException{
		PjpyWhlgDAO dao = new PjpyWhlgDAO();
		List rsList = dao.getZhszcpToExp(model);
		String[] topList = dao.getZhszcpTop();
		WritableSheet ws = wwb.getSheet(0);
		WritableCellFormat tStyle = new WritableCellFormat();
 	    WritableCellFormat wcfStyle = new WritableCellFormat();
 	    Alignment alignMent = Alignment.CENTRE;
	    wcfStyle.setAlignment(alignMent);
	    tStyle.setAlignment(Alignment.CENTRE);
	    wcfStyle.setBorder(Border.ALL, BorderLineStyle.THIN);
	    for(int i=0; i<topList.length; i++){//写表头
	    	ws.addCell(new Label(i,0,topList[i],tStyle));
	    }
	    
	    for(int j=0; j<rsList.size(); j++){
	    	String[] tmpRs = (String[]) rsList.get(j);
		    for(int i=0; i<topList.length; i++){//写入查询出的数据	
		    		ws.addCell(new Label(i,j+1,tmpRs[i],tStyle));
		    	}
	    }
	    ExcelMethods.submitWritableWorkbookOperations(wwb);//输出到客户端
	}
	
	/**
	 * 获取先进班级表头
	 * @return List
	 * */
	public List getXjbjTitle(){
		PjpyWhlgDAO dao = new PjpyWhlgDAO();		
		return dao.getXjbjTitle();
	}
	
	/**
	 * 获取先进班级结果集
	 * @param model
	 * @return List
	 * */
	public List getXjbjRs(WhlgdxXjbjModel model) throws Exception{
		PjpyWhlgDAO dao = new PjpyWhlgDAO();		
		return dao.getXjbjResult(model);
	}
	
	/**
	 * 保存先进班级信息
	 * @param model
	 * @param request
	 * @return boolean
	 * */
	public boolean xjbjSave(WhlgdxXjbjModel model,HttpServletRequest request) throws Exception{
		PjpyWhlgDAO dao = new PjpyWhlgDAO();
		return dao.saveXjbj(model, request);
	}
	
	/**
	 * 批量删除先进班级信息
	 * @param key
	 * @param request
	 * @return String
	 * */
	public String xjbjDel(String[] key,HttpServletRequest request) throws Exception{
		PjpyWhlgDAO dao = new PjpyWhlgDAO();		
		return dao.xjbjDel(key, request);
	}
	
	/**
	 *根据主键查询先进班级详细信息
	 * @param pkValue
	 * @return HashMap<String, String> 
	 * */
	public HashMap<String, String> getXjbjInfoByPk(String pkValue){
		PjpyWhlgDAO dao = new PjpyWhlgDAO();
		return dao.getXjbjInfoByPk(pkValue);
	}
	
	/**
	 * 综合素质测评数据导出
	 * @param wwb
	 * @param model
	 * @throws WriteException 
	 * @retun void
	 * */
	public void printXjbj(WritableWorkbook wwb,WhlgdxXjbjModel model) throws WriteException{
		PjpyWhlgDAO dao = new PjpyWhlgDAO();
		List rsList = dao.getXjbjToExp(model);
		String[] topList = dao.getXjbjTop();
		WritableSheet ws = wwb.getSheet(0);
		WritableCellFormat tStyle = new WritableCellFormat();
 	    WritableCellFormat wcfStyle = new WritableCellFormat();
 	    Alignment alignMent = Alignment.CENTRE;
	    wcfStyle.setAlignment(alignMent);
	    tStyle.setAlignment(Alignment.CENTRE);
	    wcfStyle.setBorder(Border.ALL, BorderLineStyle.THIN);
	    for(int i=0; i<topList.length; i++){//写表头
	    	ws.addCell(new Label(i,0,topList[i],tStyle));
	    }
	    
	    for(int j=0; j<rsList.size(); j++){
	    	String[] tmpRs = (String[]) rsList.get(j);
		    for(int i=0; i<topList.length; i++){//写入查询出的数据	
		    		ws.addCell(new Label(i,j+1,tmpRs[i],tStyle));
		    	}
	    }
	    ExcelMethods.submitWritableWorkbookOperations(wwb);//输出到客户端
	}
	
	/**
	 * 获取选择学院的奖学金名额分配信息
	 * @param xydm
	 * @return List
	 * */
	public List getFpxxByXy(String xydm,String nd){
		PjpyWhlgDAO dao = new PjpyWhlgDAO();
		List list = null;
		list = dao.getFpxxByXydm(xydm,nd);
		return list;
	}
	
	/**
	 * 获取选择学院的奖学金金额
	 * @param xydm
	 * @return List
	 * */
	public String getJxjjeOfXy(String xydm,String nd){
		PjpyWhlgDAO dao = new PjpyWhlgDAO();
		
		return dao.getJxjjeOfXy(xydm,nd);		
	}
	
	/**
	 * 查询奖学金年度学年学院名称信息
	 * @param xydm
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getNdAndXyxx(String xydm){
		PjpyWhlgDAO dao = new PjpyWhlgDAO();
		return dao.getNdAndXyxx(xydm);
	}
	
	/**
	 * 奖学金设置条件表头字段
	 * @return List
	 * */
	public List getJxjsztjTitle() throws Exception{
		PjpyWhlgDAO dao = new PjpyWhlgDAO();		
		return dao.getJxjsztjTitle();		
	}
	
	/**
	 * 查询奖学金条件信息
	 * @param xn
	 * @param xydm
	 * */
	public List getJxjsztj(String xn,String jxjdm,String jxjfl,String tableName){
		PjpyWhlgDAO dao = new PjpyWhlgDAO();		
		return dao.getJxjsztj(xn,jxjdm,jxjfl,tableName);
	}
	
	/**
	 * 获取奖学金分类列表
	 * @return List
	 * */
	public List getJxjflList(String tableName){
		PjpyWhlgDAO dao = new PjpyWhlgDAO();		
		return dao.getJxjflList(tableName);
	}
	
	/**
	 * 获取奖学金代码列表
	 * @param jxjfl
	 * @return List
	 * */
	public List getJxjList(String jxjfl,String tableName){
		PjpyWhlgDAO dao = new PjpyWhlgDAO();		
		return dao.getJxjList(jxjfl,tableName);
	}
	
	/**
	 * 根据代码获取奖学金或荣誉称号名称
	 * @param tableName
	 * @param jxjdm
	 * @return String
	 * */
	public String getJxjmc(String tableName, String jxjdm){
		PjpyWhlgDAO dao = new PjpyWhlgDAO();
		return dao.getJxjmc(tableName, jxjdm);
	}
	
	/**
	 * 保存添加设置
	 * @param model
	 * @param tableName
	 * @return String
	 * */
	public String saveTjsz(PjpyWhlgdxForm model, String tableName){
		PjpyWhlgDAO dao = new PjpyWhlgDAO();
		
		return dao.saveTjsz(model, tableName);
	}
	
	/**
	 * 将表单的所有属性的值初始化为1
	 * @param model
	 * */
	public void initForm(PjpyWhlgdxForm model){
		model.setZhszcpcjpmbl("");
		model.setCztj(null);
		model.setDcj("");
		model.setXxpjcj("");
		model.setSztzzf("");
		model.setDkzdfs("");
		model.setZhszcpcjpmbl("");
		model.setXxpjcjpm("");
		model.setWygjqk("");
		model.setSfpks("");
	}
	
	/**
	 * 删除条件操作
	 * @param tableName
	 * @param pkValue
	 * @param request
	 * @return boolean
	 * */
	public boolean deleteTjsz(String tableName, String pkValue, HttpServletRequest request){
		PjpyWhlgDAO dao = new PjpyWhlgDAO();
		return dao.deleteTjsz(tableName,pkValue,request);
	}
	
	/**
	 * 获取学生的综合设置测评成绩
	 * @param xh
	 * @return List
	 * */
	public HashMap<String, String> getStuZhszcpxx(String xh,String xn,String xq){
		PjpyWhlgDAO dao = new PjpyWhlgDAO();
		return dao.getStuZhszcpxx(xh,xn,xq);
	}
	
	/**
	 * 判断是否是贫困生
	 * @param xh
	 * @return boolean
	 * */
	public boolean isKns(String xh){
		PjpyWhlgDAO dao = new PjpyWhlgDAO();
		return dao.isKns(xh);
	}
	
	/**
	 * 保存奖学金申请
	 * @param model
	 * @return boolean
	 * */
	public boolean saveJxjsq(WhlgJxjModel model,HttpServletRequest request){
		PjpyWhlgDAO dao = new PjpyWhlgDAO();		
		return dao.saveJxjsq(model,request);
	}
	
	/**
	 * 根据主键查询学生的奖学金申请信息
	 * @param pk
	 * @param pkValue
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getXsjxjInfo(String pk,String pkValue){
		PjpyWhlgDAO dao = new PjpyWhlgDAO();		
		return dao.getXsjxjInfo(pk,pkValue);
	}
	
	/**
	 * 获取审核列表
	 * @param num
	 * @return List
	 * */
	public List getChkList(int num){
		PjpyWhlgDAO dao = new PjpyWhlgDAO();
		return dao.getChkList(num);
	}
	
	/**
	 * 获取学生申请奖学金的信息
	 * @param pkValue
	 * @param userType
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getXsjxjCheckInfo(String pkValue, String userType){
		HashMap<String, String> map = new HashMap<String, String>();
		PjpyWhlgDAO dao = new PjpyWhlgDAO();		
		map = dao.getXsjxjCheckInfo(pkValue,userType);//学生奖学金信息
		return map;
	}
	
	/**
	 * 保存奖学金审核结果
	 * @param model
	 * @param request
	 * @return boolean 
	 * */
	public boolean saveCheckPrise(WhlgJxjModel model,HttpServletRequest request){
		PjpyWhlgDAO dao = new PjpyWhlgDAO();
		return dao.saveCheckPrise(model,request);
	}
	
	/**
	 * 学生综合素质测评条件检测
	 * @param xh
	 * @param jxjdm
	 * @return String
	 * */
	public String getCheckResult(String xh,String jxjdm,String tableName){
		PjpyWhlgDAO dao = new PjpyWhlgDAO();
		return dao.checkDemand(xh, jxjdm, tableName);
	}
	
	/**
	 * 检测超限人数
	 * @param xh
	 * @param jxjdm
	 * @return boolean 
	 * */
	public int checkPersonNumber(WhlgJxjModel model){
		PjpyWhlgDAO dao = new PjpyWhlgDAO();
		return dao.checkPersonNumber(model);
	}
	
	/**
	 * 学生荣誉称号申请保存
	 * @param modle
	 * @param request
	 * @return boolean
	 * */	
	public boolean saveRychsq(WhlgJxjModel model,HttpServletRequest request){
		PjpyWhlgDAO dao = new PjpyWhlgDAO();
		return dao.saveRychsq(model,request);
	}
	
	/**
	 * 获取学生荣誉称号信息
	 * @param pk
	 * @param pkValue
	 * @param userType
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getXsrychInfo(String pk, String pkValue,String userType){
		PjpyWhlgDAO dao = new PjpyWhlgDAO();
		return dao.getXsrychInfo(pk,pkValue,userType);
	}
	
	/**
	 * 学生荣誉称号审核保存
	 * @param model
	 * @param request
	 * @return boolean
	 * */
	public boolean saveCheckRych(WhlgJxjModel model, HttpServletRequest request){
		PjpyWhlgDAO dao = new PjpyWhlgDAO();		
		return dao.saveCheckRych(model,request);
	}
	
	/**
	 * 学生荣誉称号批量审核
	 * @param pkValue
	 * @param userType
	 * @param yesNo
	 * @param request
	 * @return boolean 
	 * */
	public boolean checkRychAll(String[] pkValue, String userType,String yesNo, HttpServletRequest request) throws Exception{
		PjpyWhlgDAO dao = new PjpyWhlgDAO();		
		return dao.checkRychAll(pkValue,userType,yesNo,request);
	}
	
	/**
	 *获取奖学金按奖学金分类导出的查询语句
	 *@param model
	 *@return String
	 * */
	public String getPriseExpSql(WhlgJxjModel model){
		PjpyWhlgDAO dao = new PjpyWhlgDAO();		
		return dao.getPriseExpSql(model);
	}
	
	/**
	 *获取荣誉称号按荣誉称号分类导出的查询语句
	 *@param model
	 *@return String
	 * */
	public String getRychExpSql(WhlgJxjModel model){
		PjpyWhlgDAO dao = new PjpyWhlgDAO();		
		return dao.getRychExpSql(model);
	}
}
