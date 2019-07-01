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
	 * ��ѯ�ۺ����ʲ�����ͷ
	 * @return
	 */
	public List<HashMap<String, String>> queryZhszcpTitle() {
		PjpyXmlgDAO dao = new PjpyXmlgDAO();
		String[] enList = new String[] { "pk", "r", "xn","xq", "xh",
				"xm", "bjmc","dcj", "zcj", "tcj", "xqzf", "xqpm","xnzf", "xnpm"};
		String[] cnList = new String[] { "pk", "�к�", "ѧ��","ѧ��", "ѧ��",
				"����", "�༶","�������ַ�", "�������ַ�", "������ַ�","ѧ���ܷ�", "ѧ������","ѧ���ܷ�", "ѧ������"};
		return dao.getTitleList(enList, cnList);
	}
	
	/**
	 * �ۺ����ʲ�����ѯ���
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> queryZhszcpResult(PjpyXmlgModel model) throws Exception{
		PjpyXmlgDAO dao = new PjpyXmlgDAO();
		return dao.queryZhszcpResult(model);
	}
	
	/**
	 * ɾ���۲���Ϣ
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean deleteZhszcpxx(PjpyXmlgModel model) throws Exception{
		PjpyXmlgDAO dao = new PjpyXmlgDAO();
		return dao.deleteZhszcpxx(model);
	}
	
	/**
	 * �����ۺ����ʲ����ɼ�
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean addZhszcpxx(PjpyXmlgModel model) throws Exception {
		PjpyXmlgDAO dao = new PjpyXmlgDAO();
		return dao.addZhszcpxx(model);
	}
	
	/**
	 * �޸��ۺ����ʲ����ɼ�
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean updateZhszcpxx(PjpyXmlgModel model) throws Exception{
		PjpyXmlgDAO dao = new PjpyXmlgDAO();
		return dao.updateZhszcpxx(model);
	}
	
	/**
	 * ��ѯ�����ۺ����ʲ�����Ϣ
	 * @param model
	 * @return
	 */
	public HashMap<String, String> queryZhszcpOnexx(PjpyXmlgModel model) {
		PjpyXmlgDAO dao = new PjpyXmlgDAO();
		return dao.queryZhszcpOnexx(model);
	}
	
	/**
	 * ѧԺ�û������۲�������ʽ��Ϣ
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean saveZhszcpPmfs(PjpyXmlgModel model) throws Exception{
		PjpyXmlgDAO dao = new PjpyXmlgDAO();
		return dao.saveZhszcpPmfs(model);
	}
	
	/**
	 * ѧԺ�û���ѯ�۲�������ʽ
	 * @param model
	 * @return
	 */
	public HashMap<String, String> getXyZhszcppmfs(PjpyXmlgModel model) {
		PjpyXmlgDAO dao = new PjpyXmlgDAO();
		return dao.getXyZhszcppmfs(model);
	}
	
	/**
	 * ��ѧ��,�����ƺű������÷�ҳ�˵�
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getPageCard(String userType) throws Exception{
		PjpyXmlgDAO dao = new PjpyXmlgDAO();
		return dao.getPageCard(userType);
	}
	
	/**
	 * ��ѯ��Χ�б�
	 * @return
	 */
	public List<HashMap<String, String>> getQueryType() {
		PjpyXmlgDAO dao = new PjpyXmlgDAO();
		String[] enList = new String[]{"xy", "zy", "bj"};
		String[] cnList = new String[]{"Ժϵ", "רҵ", "�༶"};
		return dao.getTitleList(enList, cnList);
	}
	/**
	 * ��ѯ��Χ�б�
	 * @return
	 */
	public List<HashMap<String, String>> getQueryXyType() {
		PjpyXmlgDAO dao = new PjpyXmlgDAO();
		String[] enList = new String[]{"zy", "bj"};
		String[] cnList = new String[]{"רҵ", "�༶"};
		return dao.getTitleList(enList, cnList);
	}
	/**
	 * ��ѯ��ѧ���б�(�Ƿ�����Ϊ��)
	 * @param model
	 * @return
	 */
	public List<HashMap<String, String>> getJxjList(PjpyXmlgModel model) {
		PjpyXmlgDAO dao = new PjpyXmlgDAO();
		return dao.getJxjList(model);
	}
	
	/**
	 * ��ѯ�����ƺ��б�
	 * @return
	 */
	public List<HashMap<String, String>> getRychList() {
		PjpyXmlgDAO dao = new PjpyXmlgDAO();
		return dao.getRychList();
	}
	
	/**
	 * ��ѯ�������ñ�ͷ
	 * @return 
	 */
	public List<HashMap<String, String>> queryCsszTitle() {
		PjpyXmlgDAO dao = new PjpyXmlgDAO();
		String[] en = new String[] { "pk", "r", "xn","mc", "bmmc", "nj","bmrs", "bl", "tzrs"};
		String[] cn = new String[] { "pk", "�к�", "ѧ��","��������", "��������", "�꼶","��������", "����(%)", "��������"};
		return dao.getTitleList(en, cn);
	}
	
	/**
	 * ��ʼ����ѧ�������ƺű�����������
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean initData(PjpyXmlgModel model) throws Exception {
		PjpyXmlgDAO dao = new PjpyXmlgDAO();
		return dao.initData(model);
	}
	
	/**
	 * ��ѯ�������ñ�ͷ���
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> queryBlszxx(PjpyXmlgModel model) throws Exception{
		PjpyXmlgDAO dao = new PjpyXmlgDAO();
		return dao.queryBlszxx(model);
	}
	
	/**
	 * �������ý�ѧ�����
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean modiJxjblszxx(PjpyXmlgModel model) throws Exception {
		PjpyXmlgDAO dao = new PjpyXmlgDAO();
		return dao.modiJxjblszxx(model);
	}
	
	/**
	 * ��ѯ�������õ�����Ϣ
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
	 * �޸ı������õ�����Ϣ
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean modiBlszxxOne(PjpyXmlgModel model) throws Exception {
		PjpyXmlgDAO dao = new PjpyXmlgDAO();
		return dao.modiBlszxxOne(model);
	}
	/**
	 * ��ѯ�������ñ�ͷ
	 * @return 
	 */
	public List<HashMap<String, String>> queryXyJxjRsSzTitle() {
		PjpyXmlgDAO dao = new PjpyXmlgDAO();
		String[] en = new String[] { "pk", "r", "xn","mc","je","bmmc", "nj","bmrs", "bl", "jyrs","tzrs"};
		String[] cn = new String[] { "pk", "�к�", "ѧ��","��������","������", "��������", "�꼶","��������", "����(%)", "��������","��������"};
		return dao.getTitleList(en, cn);
	}
	/**
	 * ��ѯ����ѧԺ���ñ�ͷ���
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
	 * ��ѯ��ѧ������б�
	 * @param model
	 * @return
	 */
	public List<HashMap<String, String>> getJxjlbList() {
		PjpyXmlgDAO dao = new PjpyXmlgDAO();
		return dao.getJxjlbList();
	}
	
	/**
	 * ��ѯ���Ʒ�ʽ�б�
	 * @return
	 */
	public List<HashMap<String, String>> getXzfsList() {
		String[] en = new String[]{"1", "2"};
		String[] cn = new String[]{"������������", "�������"};
		PjpyXmlgDAO dao = new PjpyXmlgDAO();
		return dao.getTitleList(en, cn);
	}
	
	/**
	 * ���潱ѧ�������������Ʒ�ʽ
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean saveJxjrstzrs(PjpyXmlgModel model) throws Exception {
		PjpyXmlgDAO dao = new PjpyXmlgDAO();
		return dao.saveJxjrstzrs(model);
	}
	/**
	 * ��ѯ��ѧ��ѧԺ������ʽ
	 * @return
	 */
	public String  getXyTzFs(String xn,String xydm) {
		PjpyXmlgDAO dao = new PjpyXmlgDAO();
		return dao.getXyTzFs(xn, xydm);
	}
	/**
	 * ��ѯ��ѧ��������Ʒ�ʽ
	 * @return
	 */
	public HashMap<String, String> getJxjrstzxzrs() {
		PjpyXmlgDAO dao = new PjpyXmlgDAO();
		return dao.getJxjrstzxzrs();
	}
	
	/**
	 * ��ѯѧ���ۺ����ʲ�����Ϣ
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
	 * ��ѯѧ�����뽱ѧ��ĸ���������Ϣ
	 * @param model
	 * @return
	 */
	public HashMap<String, String> getStuJxjsqfzxx(PjpyXmlgModel model) {
		PjpyXmlgDAO dao = new PjpyXmlgDAO();
		return dao.getStuJxjsqfzxx(model);
	}
	
	/**
	 * ���ӽ�ѧ��������Ϣ�����븨����Ϣ
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean addJxjsqxx(PjpyXmlgModel model) throws Exception {
		PjpyXmlgDAO dao = new PjpyXmlgDAO();
		return dao.addJxjsqxx(model);
	}
		
	/**
	 * ��ѧ���ѯ��ͷ
	 * @return
	 */
	public List<HashMap<String, String>> queryJxjsqTitle() {
		String[] en = new String[] { "pk",  "dis","r", "xn", "xh", "xm", "bjmc", "jxjmc","xnpm", "xysh", "xxsh"};
		String[] cn = new String[] { "pk", "dis", "�к�", "ѧ��", "ѧ��", "����", "�༶", "��ѧ��","ѧ���۲�����", "ѧԺ���", "ѧУ���"};
		PjpyXmlgDAO dao = new PjpyXmlgDAO();
		return dao.getTitleList(en, cn);
	}
	
	/**
	 * ��ѧ���ѯ���(ѧԺ��ѧУ)
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
	 * ɾ����ѧ����������
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean deleteJxjsqxx(PjpyXmlgModel model)throws Exception {
		PjpyXmlgDAO dao = new PjpyXmlgDAO();
		return dao.deleteJxjsqxx(model);
	}
	
	/**
	 * ��ѯ��ѧ����������Ϣ
	 * @param model
	 * @return
	 */
	public HashMap<String, String> viewJxjsqxx(PjpyXmlgModel model) {
		PjpyXmlgDAO dao = new PjpyXmlgDAO();
		return dao.viewJxjsqxx(model);
	}
	
	/**
	 * �޸Ľ�ѧ��������Ϣ,���븨����Ϣ
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean updateJxjsqxx(PjpyXmlgModel model) throws Exception {
		PjpyXmlgDAO dao = new PjpyXmlgDAO();
		return dao.updateJxjsqxx(model);
	}
	
	/**
	 * ѧ����ѯ��ѧ��������Ϣ
	 * @param model
	 * @return
	 */
	public List<String[]> queryStujxjsqxx(PjpyXmlgModel model) {
		PjpyXmlgDAO dao = new PjpyXmlgDAO();
		return dao.queryStujxjsqxx(model);
	}
	
	/**
	 * ��ѧ���ѯ��ͷ
	 * @return
	 */
	public List<HashMap<String, String>> queryJxjsqByStuTitle() {
		String[] en = new String[] { "pk",  "dis","r", "xn",  "jxjmc", "je","xysh", "xxsh"};
		String[] cn = new String[] { "pk", "dis", "�к�", "ѧ��","��ѧ��", "���","ѧԺ���", "ѧУ���"};
		PjpyXmlgDAO dao = new PjpyXmlgDAO();
		return dao.getTitleList(en, cn);
	}
	
	/**
	 * ��ѯ�������ñ�ͷ
	 * @return 
	 */
	public List<HashMap<String, String>> queryRychRsszTitle() {
		PjpyXmlgDAO dao = new PjpyXmlgDAO();
		String[] en = new String[] { "pk", "r", "xn","mc", "bmmc", "nj","bmrs", "bl","jyrs","tzrs"};
		String[] cn = new String[] { "pk", "�к�", "ѧ��","��������", "��������", "�꼶","��������", "����(%)", "��������","��������"};
		return dao.getTitleList(en, cn);
	}
	
	/**
	 * ��ѯ�������ñ�ͷ���
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> queryRychRssz(PjpyXmlgModel model) throws Exception{
		PjpyXmlgDAO dao = new PjpyXmlgDAO();
		return dao.queryRychRssz(model);
	}
	
	/**
	 * ��ѯ����б�
	 * @return
	 */
	public List<HashMap<String, String>> getShList() {
		PjpyXmlgDAO dao = new PjpyXmlgDAO();
		return dao.getShList();
	}
	
	/**
	 * ��ѯ������ѧ�������Ϣ
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
	 * ���潱ѧ�������Ϣ
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean saveJxjshxx(PjpyXmlgModel model, String act, String key, boolean sh) throws Exception {
		PjpyXmlgDAO dao = new PjpyXmlgDAO();
		if (!StringUtils.isNull(act)) {
			if ("tg".equalsIgnoreCase(act)) {
				model.setSh("ͨ��");
			} else if ("btg".equalsIgnoreCase(act)) {
				model.setSh("��ͨ��");
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
	 * ���������ƺ�������Ϣ�����븨����Ϣ
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean serv_addRychsqxx(PjpyXmlgModel model) throws Exception {
		PjpyXmlgDAO dao = new PjpyXmlgDAO();
		return dao.addRychsqxx(model);
	}
	/**
	 * ��ѯ�����ƺ�������
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> serv_queryRychsqxx(PjpyXmlgModel model,String userType) throws Exception{
		PjpyXmlgDAO dao = new PjpyXmlgDAO();
		return dao.queryRychsqxx(model, userType);
		
	}
	/**
	 * �����ƺ�
	 * @return
	 */
	public List<HashMap<String, String>> queryRychsqTitle() {
		
		String[] en =  new String[] { "pk","dis","r","xn","xh","xm","xb","xymc","bjmc","rychmc","xysh","xxsh"};
		String[] cn = new String[] { "pk", "dis", "�к�", "ѧ��", "ѧ��", "����","�Ա�","Ժϵ", "�༶","�����ƺ�", "ѧԺ���", "ѧУ���"};
		PjpyXmlgDAO dao = new PjpyXmlgDAO();
		return dao.getTitleList(en, cn);
	}
	/**
	 * �����ƺ����
	 * @return
	 */
	public List<HashMap<String, String>> queryRychShTitle() {
		
		String[] en =  new String[] { "pk","dis","r","xn","xh","xm","bjmc","rychmc","xnpm","xysh","xxsh"};
		String[] cn = new String[] { "pk", "dis", "�к�", "ѧ��", "ѧ��", "����","�༶","�����ƺ�", "ѧԺ�۲�����","ѧԺ���", "ѧУ���"};
		PjpyXmlgDAO dao = new PjpyXmlgDAO();
		return dao.getTitleList(en, cn);
	}
	/**
	 * ��ѯ�����ƺŵ���������Ϣ
	 * @param model
	 * @return
	 */
	public HashMap<String, String> viewRychsqxx(PjpyXmlgModel model) {
		PjpyXmlgDAO dao = new PjpyXmlgDAO();
		return dao.viewRychsqxx(model);
	}
	/**
	 * �޸������ƺ�������Ϣ
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean serv_modiRychsqxx(PjpyXmlgModel model,String pkValue) throws Exception {
		PjpyXmlgDAO dao = new PjpyXmlgDAO();
		return dao.modiRychsqxx(model,pkValue);
	}
	/**
	 * ɾ�������ƺ���������
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean serv_deleteRychsqxx(PjpyXmlgModel model)throws Exception {
		PjpyXmlgDAO dao = new PjpyXmlgDAO();
		return dao.deleteRychsqxx(model);
	}
	
	/**
	 * ��ѯѧԺ����רҵ,�༶��ѧ��,�����ƺŵ�����
	 * 
	 * @param model
	 * @param key ��ѧ��:jxj �����ƺ�:rych  ����ǽ�ѧ���Ҫ��jxjdm��ֵ����֮rychdm��ֵ
	 * @return
	 */
	public String getXyJxtzrs(PjpyXmlgModel model, String key) {
		PjpyXmlgDAO dao = new PjpyXmlgDAO();
		return dao.getXyJxtzrs(model, key);
	}
	
	/**
	 * ��ѧ�������ƺ�������ӡ
	 * @param pkValue
	 * @param key ��ѧ��:jxj, �����ƺ�:rych
	 * @return
	 */
	public HashMap<String, String> jxjPrint(String pkValue, String key)throws Exception {
		PjpyXmlgDAO dao = new PjpyXmlgDAO();
		return dao.jxjPrint(pkValue, key);
	}
	
	/**
	 * ѧУ��ѯ�����ƺ�������
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> serv_queryRychShxx(PjpyXmlgModel model,String userType) throws Exception{
		PjpyXmlgDAO dao = new PjpyXmlgDAO();
		return dao.queryRychShxx(model, userType);
	}
	
	/**
	 * ����ѧ�������������
	 * @param wwb
	 */
	public void writeJxjjehz(WritableWorkbook wwb, PjpyXmlgModel model)
			throws Exception {
		PjpyXmlgDAO dao = new PjpyXmlgDAO();
		
		//�������е����������б�
		String[] yhmcList = dao.getJxjyhmcList(model.getXn());
		//��ҳ�����Ԫ��
		WritableSheet ws1 = wwb.createSheet("��ҳ",0);
		
		//��ʽ�������С
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
		   
	    double[] yhje = new double[yhmcList.length];//�����л񽱽��
	    double zje = 0;//��ҳ�ܽ��
	    int rs = 0;//��ҳ��������
	    
	    String tableTitle = model.getXn()
		+ "ѧ��"
		+ dao.getJxjscmc(model.getKeys()) + "���ʵ�";
	    
	    //����ڶ�,���ȵ�Ԫ��
		if (yhmcList != null) {
			for (int i=0;i<yhmcList.length;i++) {
				String yhmc = yhmcList[i];
				if (StringUtils.isNull(yhmc)) {
					continue;
				}
				WritableSheet ws = wwb.createSheet(yhmc, 1 + i);
				//��������Ӧ�Ļ�����
				List<String[]> yhmc_hjdata = dao.getJxjjeByYhmc(model, yhmc);
												
				ws.addCell(new Label(0, 0, tableTitle, wcfStyle));//�����ͷ
				ws.mergeCells(0, 0, 4, 1);
				String[] title = new String[]{"���", "����", yhmc + "����", "���", "�����ǩ��"};

				ws.addCell(new Label(0, 3, title[0], wcfStyle));//���������ͷ
				ws.addCell(new Label(1, 3, title[1], wcfStyle));//���������ͷ
				ws.addCell(new Label(2, 3, title[2], wcfStyle));//���������ͷ
				ws.addCell(new Label(3, 3, title[3], wcfStyle));//���������ͷ
				ws.addCell(new Label(4, 3, title[4], wcfStyle));//���������ͷ
	
				//��ʼ��ÿһ��д����
				for (int j = 0; j < yhmc_hjdata.size(); j++) {
					String[] oneData = yhmc_hjdata.get(j);
					int k = 0;
					for (int l = 1; l < oneData.length; l++) {
						ws.addCell(new Label(k, j + 4, oneData[l], tStyle));
						//�����п�
						ws.setColumnView(k, (StringUtils.isNull(oneData[l]) ? 1
								: oneData[l].length()) * 4);
						k++;
					}
				}
				
				double je = getZje(yhmc_hjdata);//�����ҳ���ܽ��
				//���ܽ�����
				ws.addCell(new Label(0, yhmc_hjdata.size() + 4, "ҳ���С��:" + je,
						wcfStyle));
				ws.mergeCells(0, yhmc_hjdata.size() + 4, 4,
						yhmc_hjdata.size() + 5);
				
				//ͳ����ҳ������
				yhje[i] = je;//ÿ�����е��ܽ��
				rs += yhmc_hjdata.size();//��ҳ��������
				zje += je;//��ҳ���ܽ��
				
			}
		}
		
		//�����ҳ��Ϣ
		ws1.addCell(new Label(2, 0, tableTitle + "��ҳ", wcfStyle));//�����ͷ
		ws1.mergeCells(2, 0, 1+3+ (yhmcList != null ? yhmcList.length : 2), 1);
		
		ws1.addCell(new Label(2, 2, "Ժϵ����", style));//�����ͷ
		ws1.mergeCells(2, 2, 1+3+ (yhmcList != null ? yhmcList.length : 2), 3);
		
		String[] ejTitle = getEjTitle(yhmcList);//������ͷ
		String[] ejResult = getEjResult(yhje, rs, zje);//������ͷ����
		List<String[]> dataList = new ArrayList<String[]>();
		dataList.add(ejTitle);
		dataList.add(ejResult);
		
		//д������ͷ
		if (dataList != null) {
			for (int i=0;i<dataList.size();i++) {
				String[] oneData = dataList.get(i);
				if (oneData != null) {
					//��ʼдÿ������
					int k=2;
					for (int j=0;j<oneData.length;j++) {
						ws1.addCell(new Label(k, 4+i, oneData[j], wcfStyle));
						ws1.setColumnView(k, 20);//����ÿ����Ԫ����
						k++;
					}
				}
			}
			ws1.addCell(new Label(2, 4+dataList.size() + 1, "��ˣ�   " +
					"                       " +
					"               �ֹ����ǩ�֣�", wcfStyle));
			ws1.mergeCells(2, dataList.size() + 5, 1+3+ (yhmcList != null ? yhmcList.length : 2),
					dataList.size() + 5);
		}
		
		ExcelMethods.submitWritableWorkbookOperations(wwb);//������ͻ���
	}
	/**
	 * ��ѧ�������ƺŹ�ʾ������
	 * @throws Exception 
	 * @throws Exception 
	 */
	public void serv_jxjRychGsddc(WritableWorkbook wwb, PjpyXmlgModel model,String type) throws Exception{
		PjpyXmlgDAO dao = new PjpyXmlgDAO();
		List<HashMap<String,String>> yxList = dao.getJxjRychYxList(type, model);
		List<HashMap<String,String>>xsList = dao.getJxjRychXsList(type, model);		
        String title = model.getXn()+"ѧ��ѧ�� "+dao.getJxjRychMc(type,model)+" ��ʾ����";
        WritableSheet ws = wwb.createSheet(title, 0);
		WritableCellFormat wcf1 = new WritableCellFormat(); // ���쵥Ԫ���ʽ
		wcf1 = ExcelMethods.getWcf(WritableFont.ARIAL, 12, false,
				Alignment.LEFT, VerticalAlignment.CENTRE, true, Border.NONE);
		// ������
		ExcelMB ex = new ExcelMB();
		ws.mergeCells(0, 0, 7 , 1);
		ex.printToOneCell_multy(ws, title, 0, 0, 10, true, Alignment.CENTRE,
				VerticalAlignment.CENTRE, true, 650, Border.NONE);
		int m=0;
//		���ձ����ʽ���Excel
		if(yxList.size()>0){
			for(int i=0;i<yxList.size();i++){
				String xymc = Base.isNull(yxList.get(i).get("xymc"))?"":yxList.get(i).get("xymc");
				String cout = yxList.get(i).get("cout");
				ws.mergeCells(0,2+i+m,7,2+i+m);
				ws.addCell(new Label(0,2+i+m,xymc+cout+"��",wcf1));
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
		ExcelMethods.submitWritableWorkbookOperations(wwb);//������ͻ���
	}
		
	/**
	 * ���ظý�����ܽ��
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
	 * ��ҳ������ͷ��Ϣ
	 * @param yhmc
	 * @return
	 */
	public String[] getEjTitle(String[] yhmc) {
		if (yhmc != null) {
			String[] title = new String[yhmc.length + 3];
			title[0] = "������";
			for (int i=0;i<yhmc.length;i++) {
				title[i+1] = yhmc[i] + "���";
			}
			title[title.length-1] = "��ҳ��";
			title[title.length-2] = "�ܽ��";
			return title;
		}
		return null;
	} 
	
	/**
	 * ��ҳ�������ͷ�����Ϣ
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
	 * �����Ƽ�����
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
			clin="��";
			mapV="bjmc";
			querry+=" and bjdm='"+bjdm+"'";
		}else if(!Base.isNull(zydm)){
			clin="רҵ";
			mapV="zymc";
			querry+=" and zydm='"+zydm+"'";
		}else{
			clin="Ժϵ";
			mapV="xymc";
			querry+=" and xydm='"+xydm+"'";
		}
		String cout = dao.getXsCout(querry);
		HashMap<String,String>xyZyBjMap = CommonQueryDAO.dao_getInfo("view_njxyzybj", null, querry);
		WritableSheet ws = wwb.createSheet(xyZyBjMap.get(mapV), 0);
        String title ="��"+clin+"����ѧ��ѧ�������� "+cout+" ��";
		WritableCellFormat wcf1 = new WritableCellFormat(); // ���쵥Ԫ���ʽ
		wcf1 = ExcelMethods.getWcf(WritableFont.ARIAL, 12, false,
				Alignment.LEFT, VerticalAlignment.CENTRE, true, Border.ALL);
		WritableCellFormat wcf2 = new WritableCellFormat(); // ���쵥Ԫ���ʽ
		wcf2 = ExcelMethods.getWcf(WritableFont.ARIAL, 12, false,
				Alignment.CENTRE, VerticalAlignment.TOP, false, Border.ALL);
		// ������
		ExcelMB ex = new ExcelMB();
		ws.mergeCells(0, 0, 13 , 1);
		ex.printToOneCell_multy(ws, title, 0, 0, 10, true, Alignment.LEFT,
				VerticalAlignment.BOTTOM, true, 650, Border.ALL);
		ws.addCell(new Label(0,2,"���",wcf1));
		ws.addCell(new Label(1,2,"�༶",wcf1));
		ws.addCell(new Label(2,2,"ѧ��",wcf1));
		ws.addCell(new Label(3,2,"����",wcf1));
		ws.addCell(new Label(4,2,"�ۺ�����",wcf1));
		ws.addCell(new Label(5,2,"�ۺ�(һ)",wcf1));
		ws.addCell(new Label(6,2,"�ۺ�(��)",wcf1));
		ws.addCell(new Label(7,2,"ѧҵ����",wcf1));
		ws.addCell(new Label(8,2,"���޿���",wcf1));
		ws.addCell(new Label(9,2,"��������",wcf1));
		ws.addCell(new Label(10,2,"�ڶ��ڼ�Υ�ͼ�¼",wcf1));
		ws.addCell(new Label(11,2,"�ڶ��ڼ��ѻ���",wcf1));
		ws.addCell(new Label(12,2,"��ѧ����ְ���",wcf1));
		ws.addCell(new Label(13,2,"Ժϵ�������",wcf1));		
//		int m=0;
//		���ձ����ʽ���Excel
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
		ExcelMethods.submitWritableWorkbookOperations(wwb);//������ͻ���
	}
		
	/**
	 * ��ѯѧԺ������Ľ������
	 * @param model
	 * @param fpfs
	 * @return
	 */
	public String getXytzje(PjpyXmlgModel model, String fpfs) {
		PjpyXmlgDAO dao = new PjpyXmlgDAO();
		return dao.getXytzje(model, fpfs);
	}
	
	/**
	 * �Բ�ѯ�����Ľ�ѧ�������ݽ������
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
	 * ��ѯ���������ݱ�ͷ
	 * @param rs
	 * @return
	 */
	public List<String[]> queryJxjjehzTitle(List<String[]> rs) {
		if (rs != null && rs.get(0) != null) {
			String jxjmc = rs.get(0)[1];
			String[] jxjmcList = jxjmc.split(",");
			String[] title = new String[(StringUtils.isNull(jxjmc) ? 3
					: ((jxjmcList != null ? jxjmcList.length : 0) + 3))];
			title[0] = "ѧԺ";
			for (int i = 0; i < jxjmcList.length; i++) {
				title[i + 1] = jxjmcList[i] + "\n(��������/��������)";
			}
			title[title.length - 2] = "������\n(Ԫ)";
			title[title.length - 1] = "�������\n(Ԫ)";
			List<String[]> titleList = new ArrayList<String[]>();
			titleList.add(title);
			return titleList;
		}
		return null;
	}

	/**
	 * ��ѯ���������ݽ��
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
