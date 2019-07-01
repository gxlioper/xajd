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
	 * ��ѯ����б�
	 * @return
	 */
	public List<HashMap<String, String>> getShList() {
		PjpyGzdxDAO dao = new PjpyGzdxDAO();
		return dao.getShList();
	}
	
	/**
	 * ��ѯѧ������ѧ��Ĵ�����Ϣ
	 * @param xh
	 * @param xn
	 * @return
	 */
	public List<String[]> queryStuwjcfxx(String xh, String xn) {
		PjpyGzdxDAO dao = new PjpyGzdxDAO();
		return dao.queryStucjxx(xh, xn);
	}
	
	/**
	 * ��ѯѧ���ɼ���Ϣ
	 * @param xh
	 * @param xn
	 * @return
	 */
	public List<String[]> queryStucjxx(String xh, String xn) {
		PjpyGzdxDAO dao = new PjpyGzdxDAO();
		return dao.queryStucjxx(xh, xn);
	}
	
	/**
	 * ���洦��������Ϣ
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean saveCfszxx(PjpyGzdxModel model)throws Exception {
		PjpyGzdxDAO dao = new PjpyGzdxDAO();
		return dao.saveCfszxx(model);
	}
	
	/**
	 * ��ѯ����������Ϣ
	 * @return
	 */
	public PjpyGzdxModel queryCfszxx() {
		PjpyGzdxDAO dao = new PjpyGzdxDAO();
		return dao.queryCfszxx();
	}
	
	/**
	 * ��ѧ��������������������б�
	 * @return
	 */
	public List<HashMap<String, String>> getRsshList() {
		PjpyGzdxDAO dao = new PjpyGzdxDAO();
		return dao.getRsshList();
	}
		
	/**
	 * ��ѯ�ۺ����ʲ�����Ϣ
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> queryZhszcpxx(PjpyGzdxModel model) throws Exception{
		PjpyGzdxDAO dao = new PjpyGzdxDAO();
		return dao.queryZhszcpxx(model);
	}
	
	/**
	 * ��ѯ�ۺ����ʲ�����ͷ
	 * @return
	 */
	public List<HashMap<String, String>> queryZhszcpTitle() {
		PjpyGzdxDAO dao  = new PjpyGzdxDAO();
		String[] enList = new String[]{ "pk", "r", "xh", "xm", "bjmc", "xn",
				"xycpf", "zhbxf", "xwbxf", "tcbxf", "zf", "bjpm" };
		String[] cnList = new String[]{ "pk", "�к�", "ѧ��", "����", "�༶", "ѧ��",
				"ѧҵ��", "�ۺϱ��ַ�", "��Ϊ���ַ�", "ͻ�����ַ�", "�۲��ܷ�", "�۲�༶����" };
		return dao.getList(enList, cnList);
	}
	
	/**
	 * �ۺ����ʲ����Զ�����
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
	 * �����ۺ����ʲ�������
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean addZhszcpxx(PjpyGzdxModel model) throws Exception{
		PjpyGzdxDAO dao = new PjpyGzdxDAO();
		return dao.addZhszcpxx(model);
	}
	
	/**
	 * �����ۺ����ʲ�������
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean updateZhszcpxx(PjpyGzdxModel model) throws Exception{
		PjpyGzdxDAO dao = new PjpyGzdxDAO();
		return dao.updateZhszcpxx(model);
	}
	
	/**
	 * ��ѯ�����ۺ����ʲ�����Ϣ
	 * @param pkValue
	 * @return
	 */
	public HashMap<String, String> queryZhszcpOnexx(String pkValue) {
		PjpyGzdxDAO dao = new PjpyGzdxDAO();
		return dao.queryZhszcpOnexx(pkValue);
	}
	
	/**
	 * ɾ���ۺ����ʲ�����Ϣ
	 * @param keys
	 * @return
	 * @throws Excepton
	 */
	public boolean deleteZhszcpxx(String[] keys) throws Exception {
		PjpyGzdxDAO dao = new PjpyGzdxDAO();
		return dao.deleteZhszcpxx(keys);
	}
	
	/**
	 * �ۺϱ��ַ��걨����
	 * @throws SQLException 
	 */
	public boolean serv_xsZhbxfsb(ZhbxxfModel model,String act,String strV) throws SQLException{
		PjpyGzdxDAO dao = new PjpyGzdxDAO();
		return dao.dao_xsZhbxfsb(model,act,strV);
	}
	/**
	 * �ۺϱ��ֲַ�ѯ
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> serv_xsZhbxfQuery(ZhbxxfModel model) throws Exception{
		PjpyGzdxDAO dao = new PjpyGzdxDAO();
		return dao.xsZhbxfQuery(model);
	}
	/**
	 * �ۺϱ��ֲַ�ѯ��ͷ
	 * @return
	 */
	public List<HashMap<String, String>> queryxsZhbxfTitle() {
		PjpyGzdxDAO dao  = new PjpyGzdxDAO();
		String[] enList =new String[] { "pk", "r", "xn","xh", "xm", "xymc", "zymc","bjmc","sfpf"};
		String[] cnList = new String[]{ "pk", "�к�","ѧ��","ѧ��", "����","Ժϵ","רҵ","�༶","�������"};
		return dao.getList(enList, cnList);
	}
	/**
	 * ����ɾ���ۺϱ���
	 * @param pkV
	 * @throws SQLException
	 */
	public void serv_delZhbx(String pkV) throws SQLException{
		PjpyGzdxDAO dao  = new PjpyGzdxDAO();
		dao.delZhbx(pkV);
	}
		
	/**
	 * ͨ������ѯ��ѧ��,�����ƺ��б�
	 * @param lb
	 * @return
	 */
	public List<HashMap<String, String>> getDmList(String lb) {
		PjpyGzdxDAO dao = new PjpyGzdxDAO();
		return dao.getDmList(lb);
	}
	
	/**
	 * �����ۺϲ�������
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

		//�����ʽ����ʽ
		Alignment alignMent = Alignment.CENTRE;
		VerticalAlignment va = VerticalAlignment.CENTRE;
		wcfStyle.setAlignment(alignMent);
		style.setAlignment(alignMent);
		wcfStyle.setBorder(Border.ALL, BorderLineStyle.THIN);
		style.setBorder(Border.ALL, BorderLineStyle.THIN);
		style.setFont(font);
		style.setVerticalAlignment(va);

		if (rs != null) {
			String title = dao.queryXyzynjbj(model) + model.getXn() + "ѧ��";
			//���������ͷ����
			ws.addCell(new Label(0, 1, title, style));
			//ѭ�����ÿ������
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
		ExcelMethods.submitWritableWorkbookOperations(wwb);//������ͻ���
	}
	
		
	/**
	 * �걨���ݲ�ѯ��ͷ
	 * @return
	 */
	public List<HashMap<String, String>> queryHjsbTitle() {
		PjpyGzdxDAO dao = new PjpyGzdxDAO();
		// ---------2010/5/25 edit by luojw---------------
		String[] enList = new String[] { "pk", "dis", "bgcolor", "xh", "xm",
				"bjmc", "xn", "xycpf", "zhbxf", "zf", "bjpm", "gk", "cf",
				"wjxz" };
		String[] cnList = new String[] { "pk", "dis", "bgcolor", "ѧ��", "����",
				"�༶", "ѧ��", "ѧҵ��", "�ۺϱ��ַ�", "�ܷ�", "����", "�ҿ�", "����", "�����ʸ�" };
		// ---------end ------------
		return dao.getList(enList, cnList);
	}
	
	/**
	 * ��ѯ���ϱ�����
	 * @param model
	 * @return
	 */
	public List<String[]> queryHjsbdata(PjpyGzdxModel model, String userType) throws Exception{
		PjpyGzdxDAO dao = new PjpyGzdxDAO();
		return dao.queryHjsbdata(model, userType);
	}
	
//	/**
//	 * ��ѯѧԺ������ѧ��ĵ�������
//	 * @param model
//	 * @return
//	 */
//	public String getXyxzrs(PjpyGzdxModel model) {
//		PjpyGzdxDAO dao = new PjpyGzdxDAO();
//		return dao.getXyxzrs(model.getXn(), model.getXydm(), model.getLb(), model.getDm());
//	}
	
	/**
	 * �걨������
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
	 * �ۺϱ������ֱ���
	 * @throws SQLException 
	 */
	public boolean ser_zhbxpf(ZhbxxfModel model) throws SQLException{
		PjpyGzdxDAO dao = new PjpyGzdxDAO();
		return dao.dao_zhbxpf(model);
	}
	
	/**
	 * ɾ��������
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
	 * ��ѯѧ���ۺ����ʲ�����Ϣ
	 * @param model
	 * @return
	 */
	public HashMap<String, String> queryStuZhszcpxx(PjpyGzdxModel model) {
		PjpyGzdxDAO dao = new PjpyGzdxDAO();
		return dao.queryStuZhszcpxx(model);
	}
	
	/**
	 * ����������걨����
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
	 * ��ѯѧ����ѧ�������ƺŻ���Ϣ
	 * @param pkValue  xh||xn||jxjdm , xh||xn||rychdm
	 * @param lb
	 * @return
	 */
	public HashMap<String, String> viewJxjRychresult(String pkValue, String lb) {
		PjpyGzdxDAO dao = new PjpyGzdxDAO();
		return dao.viewJxjRychresult(pkValue, lb);
	}
	
	/**
	 * ��ѧ�������ƺŽ����ѯ��ע�������ѧԺ�û���ѯ����ô���ѧУ���ͨ������ô�Ͳ����޸ģ�ѧУ�û���ѯ���ÿ��ơ�
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
	 * ������ӿƼ�ְҵѧԺ
	 * ���칤��ְҵѧԺ
	 * ��ѧ�������ƺŽ����ѯ
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
	 * ��ѧ�������ƺŽ����ѯ��ͷ��
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
			 cn = new String[]{ "pk", "dis", "�к�", "ѧ��", "����", "�༶", "ѧ��",
					"��ѧ��", "�������", "����Ա���","ѧԺ���","ѧУ���" };
			if ("rych".equalsIgnoreCase(lb)) {
				en = new String[]{ "pk", "dis", "r", "xh", "xm", "bjmc", "xn",
						"rychmc", "fdysh","xysh","xxsh"};
				cn = new String[]{ "pk", "dis", "�к�", "ѧ��", "����", "�༶", "ѧ��",
						"�����ƺ�",  "����Ա���","ѧԺ���","ѧУ���" };
			}
		}else if(Globals.XXDM_GZDX.equals(xxdm)){ 
			 en = new String[]{ "pk", "dis", "r", "xh", "xm", "bjmc", "xn",
						"jxjmc", "jlje", "fdysh","xysh","xxsh"};
			 cn = new String[]{ "pk", "dis", "�к�", "ѧ��", "����", "�༶", "ѧ��",
						"��ѧ��", "�������","ѧУ���" };
			 if ("rych".equalsIgnoreCase(lb)) {
				en = new String[]{ "pk", "dis", "r", "xh", "xm", "bjmc", "xn",
							"rychmc", "fdysh","xysh","xxsh" };
				cn = new String[]{ "pk", "dis", "�к�", "ѧ��", "����", "�༶", "ѧ��",
							"�����ƺ�","ѧУ���" };
			}
		}else{
			 en = new String[]{ "pk", "dis", "r", "xh", "xm", "bjmc", "xn",
					"jxjmc", "jlje", "fdysh","xysh","xxsh"};
			 cn = new String[]{ "pk", "dis", "�к�", "ѧ��", "����", "�༶", "ѧ��",
					"��ѧ��", "�������",  "����Ա���","ѧԺ���","ѧУ���" };
			if ("rych".equalsIgnoreCase(lb)) {
				en = new String[]{ "pk", "dis", "r", "xh", "xm", "bjmc", "xn",
						"rychmc", "fdysh","xysh","xxsh" };
				cn = new String[]{ "pk", "dis", "�к�", "ѧ��", "����", "�༶", "ѧ��",
						"�����ƺ�",  "����Ա���","ѧԺ���","ѧУ���" };
			}
		}
		
		
		return dao.getList(en, cn);
	}
	/**
	 * ɾ�����׳ƺŻ�ѧ��������
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean serv_deleteData(PjpyGzdxModel model) throws Exception{
		PjpyGzdxDAO dao = new PjpyGzdxDAO();
		return dao.deleteData(model);
	}
	/**
	 * �޸������ƺŻ�ѧ��������
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean modiData(PjpyGzdxModel model,String pkValue) throws Exception {
		PjpyGzdxDAO dao = new PjpyGzdxDAO();
		return dao.modiData(model, pkValue);
	}
	
	/**
	 * ���潱ѧ�������ƺŵ��������Ϣ
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
	 * ������ͳ������
	 * @param model
	 * @param wwb
	 */
	public void expData(PjpyGzdxModel model, WritableWorkbook wwb) throws Exception{
		PjpyGzdxDAO dao = new PjpyGzdxDAO();
		String title = Base.xxmc + model.getXn() + "ѧ��" + "��������ѧ�����ͳ�Ʊ�";
		WritableSheet ws = wwb.createSheet(title, 0);
		List<String[]> rs = new ArrayList<String[]>();
		rs = dao.expHjData(model);
		WritableCellFormat wcfStyle = new WritableCellFormat();
		WritableCellFormat style = new WritableCellFormat();
		WritableFont font = new WritableFont(WritableFont.ARIAL, 16);

		//�����ʽ����ʽ
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
		String[] erTitle = {"ѧԺ", "רҵ", "����", "ѧ��", "�ܷ�", "����", "�������", "��ѧ��ȼ�", "���"};
		for (int i=0;i<erTitle.length;i++) {
			ws.addCell(new Label(i,2,erTitle[i], wcfStyle));
		}
		if (rs != null) {
			for (int j = 0; j < rs.size(); j++) {
				String[] oneData = rs.get(j);
				if (oneData != null && oneData.length == 9) {
					int k=0;
					//ѭ�����ÿһ��
					for (int l=0;l<oneData.length;l++) {
						ws.addCell(new Label(k, j+3, oneData[l], wcfStyle));
						//�����п�
						ws.setColumnView(k, 15);
						k++;
					}
				}
			}
		}
		ExcelMethods.submitWritableWorkbookOperations(wwb);//������ͻ���
	}
	
	/**
	 * ������������
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
	 * ��ʼ������
	 * @param key
	 * @return
	 * @throws Exception 
	 */
	public boolean cshrs(String key) throws Exception {
		PjpyGzdxDAO dao = new PjpyGzdxDAO();
		return dao.cshrs(key);
	}
	
	/**
	 * �������ò�ѯ
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
	 * �������ñ�ͷ
	 * @return
	 */
	public List<HashMap<String, String>> getRsszTopTr() {
		PjpyGzdxDAO dao = new PjpyGzdxDAO();
		return dao.getRsszTopTr();
	}
	/**
	 * ��õ�������������Ϣ
	 * @param pk
	 * @return
	 */
	public HashMap<String, String> getDgrsxx(String pk) {
		PjpyGzdxDAO dao = new PjpyGzdxDAO();
		return dao.getDgrsxx(pk);
	}
	
	/**
	 * ����������������
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
//	 * ��ȡ����б�
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
	 * ��ý�ѧ���걨��
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
		sql.append("(case when xxsh='ͨ��' then '"+dis+"' ");
		sql.append(" else '' end) dis,(case when cj > 0 or wj >0 then '#CCCCCC' ");
		sql.append("when cfxz > 0 then '#DDDDDD' else '' end) bgcolor,(case when cj >0 then '��' else '��' end) ");
		sql.append("gk,(case when wj > 0 then '��' else '��' end) cf,(case when cfxz > 0 then '��' else '��' end) wjxz from (select ");
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
		sql.append(" and (xxsh is null or xxsh <> 'ͨ��')");
		sql.append(" order by bjmc,bjpm ");
		System.out.println(sql.toString());
		return sql.toString();
	}
	
	/**
	 * ��������ƺ��걨��
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
		sql.append("(case when xxsh='ͨ��' then '"+dis+"'");
		sql.append(" else '' end) dis,(case when cj > 0 or wj >0 then '#CCCCCC' ");
		sql.append("when cfxz > 0 then '#DDDDDD' else '' end) bgcolor,(case when cj >0 then '��' else '��' end) ");
		sql.append("gk,(case when wj > 0 then '��' else '��' end) cf,(case when cfxz > 0 then '��' else '��' end) wjxz from (select ");
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
		sql.append(" and (xxsh is null or xxsh <> 'ͨ��')");
		sql.append(" order by bjmc,bjpm ");
		
		return sql.toString();
	}
	
	/**
	 * ��ѯ���ϱ�����
	 * 
	 * @param model
	 * @return
	 */
	public ArrayList<String[]> queryHjsbStuList(PjpyGzdxModel model,
			String userType) throws Exception {

		DAO dao = DAO.getInstance();
		PjpyGzdxDAO pjDao = new PjpyGzdxDAO();
		
		// ��ѧ��������ƺ�
		String lb = model.getLb();
		// ����ѧ��
		String xn = model.getXn();
		// ��ѧ��������ƺŴ���
		String dm = model.getDm();
		
		// ���ʸ��걨���б�
		List<HashMap<String, String>> sbList = new ArrayList<HashMap<String, String>>();
		
		String[] queryList = new String[] { "nj", "xydm", "zydm", "bjdm", "xn" };
		String[] likeList = new String[] { "xh", "xm" };

		String sql = "";
		
		if ("jxj".equalsIgnoreCase(lb)) {// ��ѧ��
			sql = getJxjSbTable(model, userType, queryList, likeList);
		//	getJxjStuList(model, userType, dao, pjDao, sbList, xsList);
		} else if ("rych".equalsIgnoreCase(lb)) {// �����ƺ�
			sql = getRychSbTable(model, userType, queryList, likeList);
		//	getJxjStuList(model, userType, dao, pjDao, sbList, xsList);
		}
		
		//	����������ѯ����ȫ��ѧ��
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
		
		//��ҳ
		
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
				// �༶����
				String bjrs = map.get("bjrs");
				// �༶����
				String bjpm = Base.isNull(map.get("bjpm")) ? "1" : map
						.get("bjpm");
				if (tjList != null && tjList.size() > 0) {
					for (int j = 0; j < tjList.size(); j++) {
						HashMap<String, String> tj = tjList.get(j);
						// �����ֶ�
						String tjzd = tj.get("tjzd");
						// ��������
						String tjlx = tj.get("tjlx");
						// ����ֵ
						String tjz = tj.get("tjz");
						if ("zhpm".equalsIgnoreCase(tjzd)) {
							// ��ѧ����������
							float pm = Float.parseFloat(bjpm);
							float tjpm = Float.parseFloat(bjrs)
									* Float.parseFloat(tjz) / 100;
							tjpm = Math.round(tjpm);
							// �ж��������Ƿ��������
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
	 * �Ƿ����ZCPM����
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
