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
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: �人����ѧ��������Service
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: ����</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-08-01</p>
 */
public class PjpyWhlgdxService {
	
	/**
	 * ��ȡѧ���Ļ�����Ϣ
	 * @param xh HashMap<String, String>
	 * @return 
	 * */
	public HashMap<String, String> getStuInfo(String xh){
		PjpyWhlgDAO dao = new PjpyWhlgDAO();		
		return dao.getStuInfo(xh);
	}
	
	/**
	 * �ۺ����ʲ�����ѯ�����ͷ
	 * @return List
	 * */
	public List getZhszcpTitle(){
		PjpyWhlgDAO dao = new PjpyWhlgDAO();
		return dao.getZhszcpTitle();
	}
	
	/**
	 * �ۺ����ʲ�����ѯ�����
	 * @return List
	 * @throws Exception 
	 * */
	public List getZhszcpRs(WhlgdxZhszcpModel zhszcpModel) throws Exception{
		PjpyWhlgDAO dao = new PjpyWhlgDAO();
		return dao.getZhszcpResult(zhszcpModel);
	}
	
	/**
	 * ��ѯ�ۺ����ʲ�����ϸ��ϸ
	 * */
	public HashMap<String, String> getZhszcpInfoByPk(String pkValue){
		PjpyWhlgDAO dao = new PjpyWhlgDAO();
		return dao.getZhszcpInfoByPk(pkValue);
	}
	
	/**
	 * �ۺ����ʲ�����Ϣ����
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
	 * �ۺ����ʲ������ݵ���
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
	    for(int i=0; i<topList.length; i++){//д��ͷ
	    	ws.addCell(new Label(i,0,topList[i],tStyle));
	    }
	    
	    for(int j=0; j<rsList.size(); j++){
	    	String[] tmpRs = (String[]) rsList.get(j);
		    for(int i=0; i<topList.length; i++){//д���ѯ��������	
		    		ws.addCell(new Label(i,j+1,tmpRs[i],tStyle));
		    	}
	    }
	    ExcelMethods.submitWritableWorkbookOperations(wwb);//������ͻ���
	}
	
	/**
	 * ��ȡ�Ƚ��༶��ͷ
	 * @return List
	 * */
	public List getXjbjTitle(){
		PjpyWhlgDAO dao = new PjpyWhlgDAO();		
		return dao.getXjbjTitle();
	}
	
	/**
	 * ��ȡ�Ƚ��༶�����
	 * @param model
	 * @return List
	 * */
	public List getXjbjRs(WhlgdxXjbjModel model) throws Exception{
		PjpyWhlgDAO dao = new PjpyWhlgDAO();		
		return dao.getXjbjResult(model);
	}
	
	/**
	 * �����Ƚ��༶��Ϣ
	 * @param model
	 * @param request
	 * @return boolean
	 * */
	public boolean xjbjSave(WhlgdxXjbjModel model,HttpServletRequest request) throws Exception{
		PjpyWhlgDAO dao = new PjpyWhlgDAO();
		return dao.saveXjbj(model, request);
	}
	
	/**
	 * ����ɾ���Ƚ��༶��Ϣ
	 * @param key
	 * @param request
	 * @return String
	 * */
	public String xjbjDel(String[] key,HttpServletRequest request) throws Exception{
		PjpyWhlgDAO dao = new PjpyWhlgDAO();		
		return dao.xjbjDel(key, request);
	}
	
	/**
	 *����������ѯ�Ƚ��༶��ϸ��Ϣ
	 * @param pkValue
	 * @return HashMap<String, String> 
	 * */
	public HashMap<String, String> getXjbjInfoByPk(String pkValue){
		PjpyWhlgDAO dao = new PjpyWhlgDAO();
		return dao.getXjbjInfoByPk(pkValue);
	}
	
	/**
	 * �ۺ����ʲ������ݵ���
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
	    for(int i=0; i<topList.length; i++){//д��ͷ
	    	ws.addCell(new Label(i,0,topList[i],tStyle));
	    }
	    
	    for(int j=0; j<rsList.size(); j++){
	    	String[] tmpRs = (String[]) rsList.get(j);
		    for(int i=0; i<topList.length; i++){//д���ѯ��������	
		    		ws.addCell(new Label(i,j+1,tmpRs[i],tStyle));
		    	}
	    }
	    ExcelMethods.submitWritableWorkbookOperations(wwb);//������ͻ���
	}
	
	/**
	 * ��ȡѡ��ѧԺ�Ľ�ѧ�����������Ϣ
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
	 * ��ȡѡ��ѧԺ�Ľ�ѧ����
	 * @param xydm
	 * @return List
	 * */
	public String getJxjjeOfXy(String xydm,String nd){
		PjpyWhlgDAO dao = new PjpyWhlgDAO();
		
		return dao.getJxjjeOfXy(xydm,nd);		
	}
	
	/**
	 * ��ѯ��ѧ�����ѧ��ѧԺ������Ϣ
	 * @param xydm
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getNdAndXyxx(String xydm){
		PjpyWhlgDAO dao = new PjpyWhlgDAO();
		return dao.getNdAndXyxx(xydm);
	}
	
	/**
	 * ��ѧ������������ͷ�ֶ�
	 * @return List
	 * */
	public List getJxjsztjTitle() throws Exception{
		PjpyWhlgDAO dao = new PjpyWhlgDAO();		
		return dao.getJxjsztjTitle();		
	}
	
	/**
	 * ��ѯ��ѧ��������Ϣ
	 * @param xn
	 * @param xydm
	 * */
	public List getJxjsztj(String xn,String jxjdm,String jxjfl,String tableName){
		PjpyWhlgDAO dao = new PjpyWhlgDAO();		
		return dao.getJxjsztj(xn,jxjdm,jxjfl,tableName);
	}
	
	/**
	 * ��ȡ��ѧ������б�
	 * @return List
	 * */
	public List getJxjflList(String tableName){
		PjpyWhlgDAO dao = new PjpyWhlgDAO();		
		return dao.getJxjflList(tableName);
	}
	
	/**
	 * ��ȡ��ѧ������б�
	 * @param jxjfl
	 * @return List
	 * */
	public List getJxjList(String jxjfl,String tableName){
		PjpyWhlgDAO dao = new PjpyWhlgDAO();		
		return dao.getJxjList(jxjfl,tableName);
	}
	
	/**
	 * ���ݴ����ȡ��ѧ��������ƺ�����
	 * @param tableName
	 * @param jxjdm
	 * @return String
	 * */
	public String getJxjmc(String tableName, String jxjdm){
		PjpyWhlgDAO dao = new PjpyWhlgDAO();
		return dao.getJxjmc(tableName, jxjdm);
	}
	
	/**
	 * �����������
	 * @param model
	 * @param tableName
	 * @return String
	 * */
	public String saveTjsz(PjpyWhlgdxForm model, String tableName){
		PjpyWhlgDAO dao = new PjpyWhlgDAO();
		
		return dao.saveTjsz(model, tableName);
	}
	
	/**
	 * �������������Ե�ֵ��ʼ��Ϊ1
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
	 * ɾ����������
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
	 * ��ȡѧ�����ۺ����ò����ɼ�
	 * @param xh
	 * @return List
	 * */
	public HashMap<String, String> getStuZhszcpxx(String xh,String xn,String xq){
		PjpyWhlgDAO dao = new PjpyWhlgDAO();
		return dao.getStuZhszcpxx(xh,xn,xq);
	}
	
	/**
	 * �ж��Ƿ���ƶ����
	 * @param xh
	 * @return boolean
	 * */
	public boolean isKns(String xh){
		PjpyWhlgDAO dao = new PjpyWhlgDAO();
		return dao.isKns(xh);
	}
	
	/**
	 * ���潱ѧ������
	 * @param model
	 * @return boolean
	 * */
	public boolean saveJxjsq(WhlgJxjModel model,HttpServletRequest request){
		PjpyWhlgDAO dao = new PjpyWhlgDAO();		
		return dao.saveJxjsq(model,request);
	}
	
	/**
	 * ����������ѯѧ���Ľ�ѧ��������Ϣ
	 * @param pk
	 * @param pkValue
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getXsjxjInfo(String pk,String pkValue){
		PjpyWhlgDAO dao = new PjpyWhlgDAO();		
		return dao.getXsjxjInfo(pk,pkValue);
	}
	
	/**
	 * ��ȡ����б�
	 * @param num
	 * @return List
	 * */
	public List getChkList(int num){
		PjpyWhlgDAO dao = new PjpyWhlgDAO();
		return dao.getChkList(num);
	}
	
	/**
	 * ��ȡѧ�����뽱ѧ�����Ϣ
	 * @param pkValue
	 * @param userType
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getXsjxjCheckInfo(String pkValue, String userType){
		HashMap<String, String> map = new HashMap<String, String>();
		PjpyWhlgDAO dao = new PjpyWhlgDAO();		
		map = dao.getXsjxjCheckInfo(pkValue,userType);//ѧ����ѧ����Ϣ
		return map;
	}
	
	/**
	 * ���潱ѧ����˽��
	 * @param model
	 * @param request
	 * @return boolean 
	 * */
	public boolean saveCheckPrise(WhlgJxjModel model,HttpServletRequest request){
		PjpyWhlgDAO dao = new PjpyWhlgDAO();
		return dao.saveCheckPrise(model,request);
	}
	
	/**
	 * ѧ���ۺ����ʲ����������
	 * @param xh
	 * @param jxjdm
	 * @return String
	 * */
	public String getCheckResult(String xh,String jxjdm,String tableName){
		PjpyWhlgDAO dao = new PjpyWhlgDAO();
		return dao.checkDemand(xh, jxjdm, tableName);
	}
	
	/**
	 * ��ⳬ������
	 * @param xh
	 * @param jxjdm
	 * @return boolean 
	 * */
	public int checkPersonNumber(WhlgJxjModel model){
		PjpyWhlgDAO dao = new PjpyWhlgDAO();
		return dao.checkPersonNumber(model);
	}
	
	/**
	 * ѧ�������ƺ����뱣��
	 * @param modle
	 * @param request
	 * @return boolean
	 * */	
	public boolean saveRychsq(WhlgJxjModel model,HttpServletRequest request){
		PjpyWhlgDAO dao = new PjpyWhlgDAO();
		return dao.saveRychsq(model,request);
	}
	
	/**
	 * ��ȡѧ�������ƺ���Ϣ
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
	 * ѧ�������ƺ���˱���
	 * @param model
	 * @param request
	 * @return boolean
	 * */
	public boolean saveCheckRych(WhlgJxjModel model, HttpServletRequest request){
		PjpyWhlgDAO dao = new PjpyWhlgDAO();		
		return dao.saveCheckRych(model,request);
	}
	
	/**
	 * ѧ�������ƺ��������
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
	 *��ȡ��ѧ�𰴽�ѧ����ർ���Ĳ�ѯ���
	 *@param model
	 *@return String
	 * */
	public String getPriseExpSql(WhlgJxjModel model){
		PjpyWhlgDAO dao = new PjpyWhlgDAO();		
		return dao.getPriseExpSql(model);
	}
	
	/**
	 *��ȡ�����ƺŰ������ƺŷ��ർ���Ĳ�ѯ���
	 *@param model
	 *@return String
	 * */
	public String getRychExpSql(WhlgJxjModel model){
		PjpyWhlgDAO dao = new PjpyWhlgDAO();		
		return dao.getRychExpSql(model);
	}
}
