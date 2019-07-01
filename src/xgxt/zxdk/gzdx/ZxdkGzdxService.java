package xgxt.zxdk.gzdx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.wjcf.zjlg.WjcfZjlgActionForm;
import xgxt.wjcf.zjlg.WjcfZjlgDAO;
import xgxt.wjcf.zjlg.WjcfZjlgModel;

public class ZxdkGzdxService {

	
	/**
	 * ��ѯ��ѧ��������ά����ͷ
	 * @return
	 */
	public List<HashMap<String, String>> queryZxdkxxTitle() {
		ZxdkGzdxDao dao = new ZxdkGzdxDao();
		return dao.queryZxdkxxTitle();
	}
	
	/**
	 * ��ѯ��ѧ�������ݽ��
	 * @param userType
	 * @param model
	 * @return
	 */
	public ArrayList<String[]> queryZxdkxxResult(ZxdkGzdxActionForm zxdkForm, ZxdkGzdxModel model, String userType, String userName) {
		ZxdkGzdxDao dao = new ZxdkGzdxDao();
		if ("xy".equalsIgnoreCase(userType) || "fdy".equalsIgnoreCase(userType)) {
			return dao.queryZxdkxxResultByxy(model, zxdkForm, userType,userName);
		} else {
			return dao.queryZxdkxxResult(model, zxdkForm);
		}
	}
	/**
	 * ��ѯ��ѧ�������ݼ�¼��
	 * @param userType
	 * @param model
	 * @return
	 */
	public int queryZxdkxxResultNum(String userType, ZxdkGzdxModel model, String userName) {
		ZxdkGzdxDao dao = new ZxdkGzdxDao();
		if ("xy".equalsIgnoreCase(userType) || "fdy".equalsIgnoreCase(userType)) {
			return dao.queryZxdkxxResultNumByxy(model, userType, userName);
		} else {
			return dao.queryZxdkxxResultNum(model);
		}
	}
	
	/**
	 * ������ʾ��ϸ��Ϣ
	 * @param pkValue
	 * @return
	 */
	public HashMap<String, String> queryZxdkxxOne(String pkValue) {
		ZxdkGzdxDao dao = new ZxdkGzdxDao();
		return dao.queryZxdkxxOne(pkValue);
	}
	/**
	 * ����ǰ��ѯ��Ϣ�Ƿ��Ѿ�����
	 * @param pkValue
	 * @return
	 */
	public HashMap<String, String> queryAddZxdk(String pkValue) {
		ZxdkGzdxDao dao = new ZxdkGzdxDao();
		return dao.queryAddZxdk(pkValue);
	}
	/**
	 * ��ѧ������Ϣ��������
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean saveZxdkxx(ZxdkGzdxModel model) throws Exception {
		ZxdkGzdxDao dao = new ZxdkGzdxDao();
		return dao.saveZxdkxx(model);
	}
	/**
	 * ��ѯѧ���������ݱ�ͷ
	 * @return
	 */
	public List<HashMap<String, String>> queryZxdkdkxxTitle() {
		ZxdkGzdxDao dao = new ZxdkGzdxDao();
		return dao.queryZxdkdkxxTitle();
	}
	/**
	 * ��ѯѧ����Ϣ���
	 * @param zxdkForm
	 * @param userType
	 * @param userName
	 * @return
	 */
	public ArrayList<String[]> queryZxdkxxResultnotExis(ZxdkGzdxActionForm zxdkForm, String userType, String userName) {
		ZxdkGzdxDao dao = new ZxdkGzdxDao();
		return dao.queryZxdkxxResultnotExis(zxdkForm, userType, userName);
	}
	/**
	 * �޸���ѧ��������ά����Ϣ
	 * @param pkValue
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean updateZxdkSjwh(String pkValue, ZxdkGzdxModel model) throws Exception{
		ZxdkGzdxDao dao = new ZxdkGzdxDao();
		return dao.updateZxdkSjwh(pkValue, model);
	}
	/**
	 * �鿴��ѧ������Ϣ
	 * @param pkValue
	 * @return
	 */
	public HashMap<String, String> viewZxdkxx(String pkValue) {
		ZxdkGzdxDao dao = new ZxdkGzdxDao();
		return dao.viewZxdkxx(pkValue);
	}
	/**
	 * ɾ����ѧ������Ϣ
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public boolean delZxdkSjwh(String[] pkValue) throws Exception {
		ZxdkGzdxDao dao = new ZxdkGzdxDao();
		return dao.delZxdkSjwh(pkValue);
	}
}
