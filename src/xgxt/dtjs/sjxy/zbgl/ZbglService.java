package xgxt.dtjs.sjxy.zbgl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.dtjs.sjxy.SjxyDtjsForm;
import xgxt.form.SaveForm;

public class ZbglService {

	ZbglDAO dao = new ZbglDAO();

	/**
	 * ���������б�
	 * 
	 * @param form
	 * @param request
	 * @author luojw
	 * @throws Exception
	 */
	public void setList(SjxyDtjsForm myForm, HttpServletRequest request)
			throws Exception {
		dao.setList(myForm, request);
	}
	
	/**
	 * ���֧����������б�
	 * 
	 * @author luojw
	 */
	public ArrayList<String[]> getZbglList(String tableName, ZbglModel model,
			String[] colList) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		
		return dao.getZbglList(tableName, model, colList);
	}
	
	/**
	 * ���֧�����������Ϣ
	 * 
	 * @param tableName(����)
	 * @param pk(����)
	 * @param pkValue(����ֵ)
	 * @param colList(���ֵ)
	 * 
	 * @author luojw
	 */
	public HashMap<String, String> getZbglInfo(String tableName, String pk,
			String pkValue, String[] colList) {
		return dao.getZbglInfo(tableName, pk, pkValue, colList);
	}
	
	/**
	 * ɾ��֧�����������Ϣ
	 * 
	 * @author luojw
	 * @param tableName(����)
	 * @param pk(����)
	 * @param pkValue(����ֵ)
	 * 
	 * @throws Exception
	 */
	public boolean delZbgl(SaveForm form, ZbglModel model) throws Exception {
		DAO dao = DAO.getInstance();
		return dao.delDate(form, model);
	}
	
	/**
	 * ��������֧�����������Ϣ
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
	public boolean saveZbgl(SaveForm form, ZbglModel model) throws Exception {
		DAO dao = DAO.getInstance();
		return dao.saveData(form, model);
	}
	
	/**
	 * ����֧�����������Ϣ
	 * 
	 * @author luojw
	 * @param tableName(����)
	 * @param pk(����)
	 * @param pkValue(����ֵ)
	 * @param onezd(��һ�ֶ�)
	 * 
	 * @throws Exception
	 */
	public boolean saveZbgl(SaveForm form, ZbglModel model,
			HttpServletRequest request) throws Exception {
		DAO dao = DAO.getInstance();
		return dao.submitData(form, model, request);
	}
}
