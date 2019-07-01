/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: LP</p>
 * <p>Version: 1.0</p>
 * <p>Time:2009-11-17 ����02:42:33</p>
 */
package xgxt.xsgygl.zjcmxy;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import xgxt.DAO.DAO;
import xgxt.action.Base;

//TODO: Auto-generated Javadoc
public class GyglZjcmService {


	/**
	 *ס�޼��ɴ����ѯ��ͷ
	 */
	public List<HashMap<String, String>> getZsjlTopTr() {
		DAO dao = DAO.getInstance();
		String[] colList = null;
		String[] colListCN = null;
		colList = new String[]{"pk","xn","xqmc","wjsj","ldmc","qsh","wjlbmc","lrrxm","sfcf","xycljgmc","xxsh","xxcljg"};
		colListCN = new String[]{"pk","ѧ��","ѧ��","Υ��ʱ��","¥��","���Һ�","Υ�����","Υ��¼����","�Ƿ���","������","ѧУ���","ѧУ���"}; 		
		List<HashMap<String, String>> topTr = dao.arrayToList(colList,colListCN);
		return topTr;
	}
	/**
	 *ס�޼��ɴ����ѯ
	 */
	public  ArrayList<String[]> serv_getZsjlClQue(GyglZsjlClModel model){
		GyglZjcmxyDAO dao = new GyglZjcmxyDAO();
		return dao.dao_getZsjlClQue(model);
	}
	public  List<HashMap<String, String>> ser_getCljgList(){
		GyglZjcmxyDAO dao = new GyglZjcmxyDAO();
		return dao.getCljgList();
	}
	public  List<HashMap<String, String>> ser_getZsjlXsList(String pkValue,String cfyf) {
		GyglZjcmxyDAO dao = new GyglZjcmxyDAO();
		return dao.getZsjlXsList(pkValue,cfyf);
	}
	public  String getZsjlCfXsList(String pkValue) throws SQLException {
		GyglZjcmxyDAO dao = new GyglZjcmxyDAO();
		return dao.getZsjlCfXsList(pkValue);
	}
	public boolean ser_disposeSave(String sfcf,String xycljg,String dcqk,String[] pks,String pkValue) throws Exception{
		GyglZjcmxyDAO dao = new GyglZjcmxyDAO();
		return dao.disposeSave(sfcf, xycljg, dcqk, pks,pkValue);
	}
	/**
	 *ס�޼��ɴ�����˲�ѯ��ͷ 
	 */
	public List<HashMap<String, String>> getZsjlChkTopTr() {
		DAO dao = DAO.getInstance();
		String[] colList = null;
		String[] colListCN = null;
		colList = new String[]{"pk","xn","xqmc","wjsj","ldmc","qsh","wjlbmc","lrrxm","sfcf","xycljgmc","xxsh"};
		colListCN = new String[]{"pk","ѧ��","ѧ��","Υ��ʱ��","¥��","���Һ�","Υ�����","Υ��¼����","�Ƿ���","������","ѧУ���"}; 		
		List<HashMap<String, String>> topTr = dao.arrayToList(colList,colListCN);
		return topTr;
	}
	/**
	 *ס�޼��ɴ����ѯ
	 */
	public  ArrayList<String[]> serv_getZsjlChkQue(GyglZsjlClModel model){
		GyglZjcmxyDAO dao = new GyglZjcmxyDAO();
		return dao.dao_getZsjlChkQue(model);
	}
	public boolean serv_doCheckSave(String xxcljg,String yesNo,String pkValue) throws Exception{		
		GyglZjcmxyDAO dao = new GyglZjcmxyDAO();
		return dao.doCheckSave(xxcljg, yesNo, pkValue);
	}
	public boolean serv_plCheckSave(String pkValue,String  check) throws SQLException{
		GyglZjcmxyDAO dao = new GyglZjcmxyDAO();
		return dao.plCheckSave(pkValue, check);
	}
	/**
	 * Serv_set ld wjlb.��ȡ�ļ����
	 * 
	 * @param request the request
	 */
	public List<HashMap<String, String>> serv_setLdWjlb(){
		GyglZjcmxyDAO dao = new GyglZjcmxyDAO();
		return dao.dao_setLdWjlb();
	}
	/**
	 * Serv_set ld wjlb.��ȡ¥������
	 * 
	 * @param request the request
	 */
	public List<HashMap<String, String>> ser_setLdlist(){
		GyglZjcmxyDAO dao = new GyglZjcmxyDAO();
		return dao.dao_setLdlist();
	}
	/**
	 * Serv_get xs info.����ѧ�Ż�ȡѧ����Ϣ
	 * 
	 * @param xh the xh
	 */
	public HashMap<String, String> serv_getXsInfo(String xh){
		GyglZjcmxyDAO dao = new GyglZjcmxyDAO();
		return dao.dao_getXsInfo(xh);
	}
	public boolean serv_saveXsInfo(zsjlModel model,HttpServletRequest request,String pk) throws Exception{
		GyglZjcmxyDAO dao = new GyglZjcmxyDAO();
		return dao.dao_saveXsInfo(model,request,pk);
	}
	/**
	 * .��ȡ��ͷ
	 */
	public List<HashMap<String,String>> getToptrTitle(String tableName,String[] colList) throws Exception {
		GyglZjcmxyDAO dao = new GyglZjcmxyDAO();
		return dao.getToptrTitle(tableName,colList);
	}
	/**
	 * .��ѯ
	 */
	public ArrayList<String[]> ser_xlzxsQuery(zsjlModel model,String tableName) throws Exception {
		GyglZjcmxyDAO dao = new GyglZjcmxyDAO();
		return dao.dao_getQuery(model,tableName);
	}
	/**
	 * .��ѯΥ�����
	 */
	public List<HashMap<String, String>> ser_wjlbList() throws Exception {
		GyglZjcmxyDAO dao = new GyglZjcmxyDAO();
		return dao.dao_wjlbList();
	}
	/**
	 * .ɾ��
	 */
	public boolean ser_xlzxsdel(String pk,HttpServletRequest request) throws Exception {
		GyglZjcmxyDAO dao = new GyglZjcmxyDAO();
		return dao.dao_getDel(pk,request);
	}
	/**
	 * Serv_get xs info.����������ȡѧ����Ϣ
	 * 
	 * @param xh the xh
	 */
	public HashMap<String, String> serv_getpkForXsInfo(String pk){
		GyglZjcmxyDAO dao = new GyglZjcmxyDAO();
		return dao.dao_getpkForXsInfo(pk);
	}
	/**
	 * .���ݵ���
	 */
	public void dao_expData(String tabName,HttpServletResponse response,zsjlModel model) throws Exception {
		GyglZjcmxyDAO dao = new GyglZjcmxyDAO();
		WritableWorkbook wwb = Workbook.createWorkbook(response.getOutputStream());
		WritableSheet ws = wwb.createSheet("���ݵ���", 0);
		String[] ColumnName = new String[]{"xh","xn","xqmc","xm","xb","nj","xymc","zymc","bjmc","ldmc","jg",
				"mz","cs","qsh","cwh","ssbh","wjsy","wjsj","xxsh","wjlbmc","lrr","lrrxm"};
		String[] ColumnNameCN  = {"ѧ��","ѧ��","ѧ��","����","�Ա�","�꼶",Base.YXPZXY_KEY+"����","רҵ����","�༶����",
				"¥������","����","����","¥��","���Һ�","��λ��","������","Υ��˵��","ʱ��","ѧУ���",
				"��������","¼�����û���","¼�����û���"};
		try {
			Vector<Object> vec = new Vector<Object>();
			for (int m = 0; m < ColumnNameCN.length; m++) {
				ws.addCell(new Label(m, 0, ColumnNameCN[m]));
			}
			int k = ColumnName.length;
			vec = dao.dao_expData(tabName, ColumnName, model);
			for (int i = 0; i < vec.size(); i++) {
				String[] tmp = (String[]) vec.toArray()[i];
				for (int j = 0; j < k; j++) {
					ws.addCell(new Label(j, i + 1, tmp[j]));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("���ݵ���ʧ��!");
		} finally {
			wwb.write();
			wwb.close();
		}
	}
	public List<HashMap<String,String>> serv_getWjlbList(){
		GyglZjcmxyDAO dao = new GyglZjcmxyDAO();
		return dao.getWjlbList();
	}
	public ArrayList<String[]> serv_zsjlStatQue(GyglZsjlClModel model) throws  Exception{
		GyglZjcmxyDAO dao = new GyglZjcmxyDAO();
		return dao.dao_zsjlStatQue(model);
	}
	/**
	 *ס�޼��ɴ����ѯ��ͷ
	 */
	public List<HashMap<String, String>> getZsjlStatTopTr() {
		DAO dao = DAO.getInstance();
		String[] colList = null;
		String[] colListCN = null;
		colList = new String[]{"r","xn","xqmc","xh","xm","xb","nj","bjmc","ldmc","qsh","cwh","wjsj","wjlbmc","lrrxm","sfcf","xycljg","xxsh"};
		colListCN = new String[]{"�к�","ѧ��","ѧ��","ѧ��","����","�Ա�","�꼶","�༶","¥��","���Һ�","��λ��","Υ��ʱ��","�������","Υ����Ϣ¼����","�Ƿ񴦷�","������","ѧУ���"}; 		
		List<HashMap<String, String>> topTr = dao.arrayToList(colList,colListCN);
		return topTr;
	}
	/**
	 * ͳ�����ݵ���
	 */
	public void ser_expStatData(WritableWorkbook wwb,GyglZsjlClModel model) throws Exception {
		GyglZjcmxyDAO dao = new GyglZjcmxyDAO();
		Vector<Object> vec = new Vector<Object>();

		WritableSheet ws = wwb.createSheet("¥�㳤��������", 0);
		String[] ColumnName = new String[]{"xh","xn","xqmc","xm","xb","nj","xymc","zymc","bjmc","ldmc","jg",
				"mz","cs","qsh","cwh","ssbh","wjsy","wjsj","wjlbmc","lrr","lrrxm","sfcf","xycljgmc","xxsh","xxcljg"};
		String[] ColumnNameCN  = {"ѧ��","ѧ��","ѧ��","����","�Ա�","�꼶",Base.YXPZXY_KEY+"����","רҵ����","�༶����",
				"¥������","����","����","¥��","���Һ�","��λ��","������","Υ��˵��","ʱ��",
				"��������","¼�����û���","¼�����û���","�Ƿ񴦷�","������","ѧУ���","ѧУ���"};
		for (int m = 0; m < ColumnNameCN.length; m++) {
			ws.addCell(new Label(m, 0, ColumnNameCN[m]));
		}
		vec = dao.dao_expStatData(model, ColumnName);
		int k = ColumnName.length;
		for (int i = 0; i < vec.size(); i++) {
			String[] tmp = (String[]) vec.toArray()[i];
			for (int j = 0; j < k; j++) {
				ws.addCell(new Label(j, i + 1, tmp[j]));
			}
		}
	}
	
}
