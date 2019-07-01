package xgxt.xsgygl.bjlh.sjwh;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.form.SaveForm;
import xgxt.xsgygl.bjlh.BjlhGyglDAO;
import xgxt.xsgygl.bjlh.BjlhGyglForm;
import xgxt.xsgygl.bjlh.fyk.FykService;

public class SjwhService {
	
	//	=======================���ķָ��ߣ�����luojw��===================================
	
	SjwhDAO dao = new SjwhDAO();

	public static final String TWDM = BjlhGyglDAO.TWDM;//��ί���Ŵ���
	public static final String TYDM = BjlhGyglDAO.TYDM;;//������ѧ�����Ŵ���
	public static final String KYDM = BjlhGyglDAO.KYDM;//���в��Ŵ���
	public static final String CJDM = BjlhGyglDAO.CJDM;//�ɽ̲��Ŵ���
	
	/**
	 * ִ�д洢���̣����´�λ��Ϣ
	 * 
	 * @author luojw
	 */
	public boolean createCwxx() {
		return dao.createCwxx();
	}
	
	/**
	 * �������湫Ԣ���������Ϣ
	 * 
	 * @author luojw
	 * 
	 * @param tableName(����)
	 * @param pk(����)
	 * @param pkValue(����ֵ)
	 * @param arrzd(�����ֶ�)
	 * @param onezd(��һ�ֶ�)
	 * @param notnull(�ǿ��ֶ�)
	 * 
	 * @throws Exception
	 */
	public boolean saveGygl(SaveForm form, SjwhModel model) throws Exception {
		DAO dao = DAO.getInstance();
		return dao.saveData(form, model);
	}

	/**
	 * ���湫Ԣ���������Ϣ
	 * 
	 * @author luojw
	 * @param tableName(����)
	 * @param pk(����)
	 * @param pkValue(����ֵ)
	 * @param onezd(��һ�ֶ�)
	 * 
	 * @throws Exception
	 */
	public boolean saveGygl(SaveForm form, SjwhModel model,
			HttpServletRequest request) throws Exception {
		DAO dao = DAO.getInstance();
		return dao.submitData(form, model, request);
	}

	/**
	 * ���¹�Ԣ���������Ϣ
	 * 
	 * @author luojw
	 * @param tableName(����)
	 * @param pk(����)
	 * @param pkValue(����ֵ)
	 * @param onezd(��һ�ֶ�)
	 * 
	 * @throws Exception
	 */
	public boolean updateGygl(SaveForm form, SjwhModel model) throws Exception {
		DAO dao = DAO.getInstance();
		return dao.updateData(form, model);
	}

	/**
	 * ɾ����Ԣ���������Ϣ
	 * 
	 * @author luojw
	 * @param tableName(����)
	 * @param pk(����)
	 * @param pkValue(����ֵ)
	 * 
	 * @throws Exception
	 */
	public boolean delGygl(SaveForm form, SjwhModel model) throws Exception {
		DAO dao = DAO.getInstance();
		return dao.delDate(form, model);
	}

	/**
	 * ��ù�Ԣ��������б�
	 * 
	 * @author luojw
	 */
	public ArrayList<String[]> getGyglList(String tableName, SjwhModel model,
			String[] colList) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		
		String query = "";
		if ("view_bjlh_fpjg".equalsIgnoreCase(tableName)) {
			String lx = model.getLx();

			if ("��ί��".equalsIgnoreCase(lx)) {
				model.setXydm(FykService.TWDM);
			} else if ("������".equalsIgnoreCase(lx)) {
				model.setXydm(FykService.TYDM);
			} else if ("�о���".equalsIgnoreCase(lx)) {
				model.setXydm(FykService.KYDM);
			} else if ("�ɽ���".equalsIgnoreCase(lx)) {
				model.setXydm(FykService.CJDM);
			} else {
				query = " and xydm not in ('" + TWDM + "','" + TYDM + "','"
						+ KYDM + "','" + CJDM + "')";
			}
			model.setLx("");
		}
		return dao.getGyglList(tableName, model, colList,query);
	}

	/**
	 * �������ά��������ֵ
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getSelectList(String lx) {
		return dao.getSelectList(lx);
	}

	/**
	 * �����ά��������ֵ
	 * 
	 * @param tableName(����)
	 * @param dm(����)
	 * @param mc(����)
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getSelectList(String tableName,
			String dm, String mc) {
		return dao.getSelectList(tableName, dm, mc);
	}

	/**
	 * �õ����Ŵ���
	 * 
	 * @author luojw
	 */
	public String getBmdm(String bmmc) {
		return dao.getBmdm(bmmc);
	}

	/**
	 * ��÷�ȫ����ѧԺרҵ�༶�б�
	 * 
	 * @param bmlx(��������:xy,zy,bj)
	 * @param xslx(ѧ������:�о������ɽ���)
	 * @param nj(�꼶)
	 * @param xydm(ѧԺ����)
	 * @param zydm(רҵ����)
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getXyZyBjList(String bmlx,
			String xslx, String nj, String xydm, String zydm) {
		return dao.getXyZyBjList(bmlx, xslx, nj, xydm, zydm);
	}
	
	/**
	 * ��÷�ȫ�����꼶�б�
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getNjList(String lx) {
		return dao.getNjList(lx);
	}

	/**
	 * ��ù�Ԣ���������Ϣ
	 * 
	 * @param tableName(����)
	 * @param pk(����)
	 * @param pkValue(����ֵ)
	 * @param colList(���ֵ)
	 * 
	 * @author luojw
	 */
	public HashMap<String, String> getGyglInfo(String tableName, String pk,
			String pkValue, String[] colList) {
		return dao.getGyglInfo(tableName, pk, pkValue, colList);
	}
	
	/**
	 * ���¥�������б�
	 * @param lddm(¥������)
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getCsList(String lddm) {
		return dao.getCsList(lddm);
	}
	
	/**
	 * ���¥�������б�
	 * 
	 * @param lddm(¥������)
	 * @param cs(����)
	 * @author luojw
	 */
	public List<HashMap<String, String>> getQsList(String lddm, String cs,
			String fplx, String xydm) {
		return dao.getQsList(lddm, cs, fplx, xydm, "");
	}
	/**
	 * ������Ҵ�λ�б�
	 * @param lddm(¥������)
	 * @param cs(����)
	 * @param qsh(���Һ�)
	 * 
	 * @author luojw
	 * @throws Exception 
	 */
	public List<HashMap<String, String>> getCwList(String lddm, String cs,
			String qsh, String xh) throws Exception {
		return dao.getCwList(lddm, cs, qsh, xh);
	}
	
	/**
	 * ���ѧ��ס����ϢID
	 * 
	 * @author luojw
	 */
	public String getId(String xh, String lx){
		return dao.getId(xh, lx);
	}

	/**
	 * �޸��˷���Ϣ
	 * 
	 * @author luojw
	 * @throws Exception 
	 */
	public Boolean updateTfxx(SjwhModel model) throws Exception{
		return dao.updateTfxx(model);
	}
	
	/**
	 * ִ�������˷�
	 * 
	 * @author luojw
	 * @throws Exception 
	 */
	public Boolean excuteZttf(SjwhModel model) throws Exception{
		return dao.excuteZttf(model);
	}
	
	/**
	 * ���������б�
	 * 
	 * @param form
	 * @param request
	 * @author luojw
	 * @throws Exception 
	 */
	public  void setList(BjlhGyglForm myForm,HttpServletRequest request) throws Exception {
		dao.setList(myForm, request);
	}
	
	//	=======================���ķָ��ߣ�����luojw��===================================
	
	/**
	 * �������б�
	 * 
	 * @return
	 */
	public List<HashMap<String, String>> getFpbjList() {
		String[] en = { "qrz", TWDM, TYDM, KYDM, CJDM };
		String[] cn = { "ȫ����", "��ί", "������ѧ��", "���д�", "���˽�����" };
		return dao.getList(en, cn);
	}
	
	/**
	 * ͳ�������б�
	 * @return
	 */
	public List<HashMap<String, String>> getTjlxList() {
		String[] en = {"", "����", "�յ�", "�е�", "������"};
		String[] cn = {"----��ѡ��----", "����",  "�յ�", "�е�", "������"};
		return dao.getList(en, cn);
	}
}
