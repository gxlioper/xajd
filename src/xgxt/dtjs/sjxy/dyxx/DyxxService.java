package xgxt.dtjs.sjxy.dyxx;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.dtjs.sjxy.SjxyDtjsForm;
import xgxt.form.SaveForm;
import xgxt.utils.CommonQueryDAO;

public class DyxxService {

	DyxxDAO dao = new DyxxDAO();

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
	 * ���ѧ����Ϣ
	 * 
	 * @author luojw
	 */
	public HashMap<String, String> getStuInfo(String xh) {
		return CommonQueryDAO.getDetStuInfo(xh);
	}
	
	/**
	 * ��õ�Ա����б�
	 * 
	 * @author luojw
	 */
	public ArrayList<String[]> getDyxxList(String tableName, DyxxModel model,
			String[] colList) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		return dao.getDyxxList(tableName, model, colList);
	}

	/**
	 * ��õ�Ա�����Ϣ
	 * 
	 * @param tableName(����)
	 * @param pk(����)
	 * @param pkValue(����ֵ)
	 * @param colList(���ֵ)
	 * 
	 * @author luojw
	 */
	public HashMap<String, String> getDyxxInfo(String tableName, String pk,
			String pkValue, String[] colList) {
		return dao.getDyxxInfo(tableName, pk, pkValue, colList);
	}

	/**
	 * ɾ����Ա�����Ϣ
	 * 
	 * @author luojw
	 * @param tableName(����)
	 * @param pk(����)
	 * @param pkValue(����ֵ)
	 * 
	 * @throws Exception
	 */
	public boolean delDyxx(SaveForm form, DyxxModel model) throws Exception {
		DAO dao = DAO.getInstance();
		return dao.delDate(form, model);
	}

	/**
	 * �������浳Ա��Ϣ�����Ϣ
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
	public boolean saveDyxx(SaveForm form, DyxxModel model) throws Exception {
		DAO dao = DAO.getInstance();
		return dao.saveData(form, model);
	}

	/**
	 * ���浳Ա��Ϣ�����Ϣ
	 * 
	 * @author luojw
	 * @param tableName(����)
	 * @param pk(����)
	 * @param pkValue(����ֵ)
	 * @param onezd(��һ�ֶ�)
	 * 
	 * @throws Exception
	 */
	public boolean saveDyxx(SaveForm form, DyxxModel model,
			HttpServletRequest request) throws Exception {
		DAO dao = DAO.getInstance();
		return dao.submitData(form, model, request);
	}
	
	/**
	 * ��������֧��
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public boolean saveSszb(DyxxModel model,
			HttpServletRequest request) throws Exception {
		
		String realTable = "sjxy_sszbb";
		String[] onezd = new String[] { "xn", "xq", "xh", "xydm", "zbmc", "lx" };
		String pk = "xn||xq||xh||xydm||lx";
		String pkValue = model.getXn() + model.getXq() + model.getXh() + model.getXydm() + model.getLx();

		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(realTable);
		saveForm.setOnezd(onezd);
		saveForm.setPk(pk);
		saveForm.setPkValue(new String[] { pkValue });
		
		return saveDyxx(saveForm, model, request);
	}
	
	/**
	 * ɾ������֧��
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public boolean delSszb(DyxxModel model, String[] checkVal) throws Exception {

		String realTable = "sjxy_sszbb";
		String pk = "xn||xq||xh||lx";
		String[] pkValue = new String[checkVal.length];

		for (int i = 0; i < checkVal.length; i++) {
			pkValue[i] = checkVal[i] + model.getLx();
		}
		
		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(realTable);
		saveForm.setPk(pk);
		saveForm.setPkValue(pkValue);

		return delDyxx(saveForm, model);
	}

	/**
	 * �޸ĵ�Ա����
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public boolean changeDylx(DyxxModel model,
			HttpServletRequest request)
			throws Exception {
		boolean flag = false;
		String lx = model.getLx();
		if ("��������".equalsIgnoreCase(lx)) {
			flag = dao.saveYbdy(model, request);
		} else if ("Ԥ����Ա".equalsIgnoreCase(lx)) {
			flag = dao.saveZsdy(model, request);
		}
		if (flag) {
			flag = dao.saveZbmc(model, request);
		}
		return flag;
	}
	
	/**
	 * �޸ĵ�Ա����
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public boolean changeDylx(String[] key, String lx,
			HttpServletRequest request) throws Exception {
		boolean flag = false;
		if ("��������".equalsIgnoreCase(lx)) {
			flag = dao.saveYbdy(key, request);
		} else if ("Ԥ����Ա".equalsIgnoreCase(lx)) {
			flag = dao.saveZsdy(key, request);
		}
		if (flag) {
			flag = dao.saveZbmc(key, lx, request);
		}
		return flag;
	}
}
