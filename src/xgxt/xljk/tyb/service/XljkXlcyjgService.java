/*
 * @className: XljkXlcyjgService.java
 * 
 * @time: 2010-4-30
 * 
 * @copyRight hz-zf
 * 
 */

package xgxt.xljk.tyb.service;

import java.util.HashMap;
import java.util.List;

import common.GlobalsVariable;

import xgxt.DAO.DAO;
import xgxt.pjpy.PjpyTyService;
import xgxt.utils.String.StringUtils;
import xgxt.xljk.tyb.dao.XljkXlcyjgDAO;
import xgxt.xljk.tyb.model.XljkXlcyjgModel;

/**
 * ������ - ���������ά�����õ�service,
 * �����Դ�ѧ���˸�����������Խ���Ϳ��ض��˸����ز��Խ����ά��
 * 
 * @author  lt
 * @version 1.0 2010-4-30
 * @see     xgxt.xljk.tyb.dao.XljkXlcyjgDAO
 */
public class XljkXlcyjgService {

	XljkXlcyjgDAO dao = new XljkXlcyjgDAO();
	
	/**
	 * ��ѧ���˸����ز�������ͷ
	 * @return
	 */
	public List<HashMap<String, String>> queryDxsrgysclJgTitle() {
		String[] enList = { "pk", "r", "xh", "xm", "xb", "bjmc",
				"cssj", "qtzz", "jsfl", "yyz", "sjz", "zf" };
		String[] cnList = { "pk", "�к�", "ѧ��", "����", "�Ա�", "�༶",
				"����ʱ��", "����֢״���Է�ֵ", "������Ѳ��Է�ֵ", "����֢���Է�ֵ", "��֢���Է�ֵ", "�ܷ�" };
		return getQueryTitle(enList, cnList);
	}
	
	/**
	 * ��ѯ��ѧ���˸����ز�����
	 * @param model
	 * @param userName
	 * @return
	 * @throws Exception
	 */
	public List<String[]> queryDxsrgysjgResult(XljkXlcyjgModel model, String userName) throws Exception{
		return dao.queryDxsrgysjgResult(model, userName);
	}
	
	/**
	 * ���Ӵ�ѧ���˸����ز�����Ϣ
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean addDxsrgyscyXx(XljkXlcyjgModel model) throws Exception{
		return dao.addDxsrgyscyXx(model);
	}
	
	/**
	 * �޸Ĵ�ѧ���˸����ز�����Ϣ
	 * @param model
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public boolean updateDxsrgyscyXx(XljkXlcyjgModel model, String pkValue)
			throws Exception {
		return dao.updateDxsrgyscyXx(model, pkValue);
	}
	
	/**
	 * ��ʾ��ѧ���˸����ز�����Ϣ
	 * @param pkValue
	 * @return
	 */
	public HashMap<String, String> viewDxsrgyscyXx(String pkValue) {
		return dao.viewDxsrgyscyXx(pkValue);
	}
	
	/**
	 * ɾ����ѧ���˸����ز�����Ϣ
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public boolean deletDxsrgyscyXx(String[] pkValue, String tableName) throws Exception{
		if (pkValue != null && pkValue.length > 0) {
			String[] sqlArr = new String[pkValue.length]; 
			for (int i = 0; i < pkValue.length; i++) {
				StringBuilder sb = new StringBuilder("delete from ");
				sb.append(tableName);
				sb.append(" where xh||cssj='");
				sb.append(pkValue[i]);
				sb.append("'");
				sqlArr[i] = sb.toString();
			}
			return dao.deleteDxsrgyscyXx(sqlArr);
		} 
		return false;
	}
	

	/**
	 * ��ѯ���ض��˸�������Ϣ���
	 * @param model
	 * @param userName
	 * @return
	 * @throws Exception
	 */
	public List<String[]> queryKtergysResult(XljkXlcyjgModel model,
			String userName) throws Exception {
		return dao.queryKtergysResult(model, userName);
	}
	
	/**
	 * ��ѯ���ض��˸����ز�ѯ��ͷ
	 * @return
	 */
	public List<HashMap<String, String>> queryKtergysTitle() {
		String[] enList = { "pk", "r", "xh", "xm", "xb", "bjmc",
				"cssj", "ayz", "byz", "cyz", "dyz", "eyz" };
		String[] cnList = { "pk", "�к�", "ѧ��", "����", "�Ա�", "�༶",
				"����ʱ��", "A���ӷ�ֵ", "B���ӷ�ֵ", "C���ӷ�ֵ", "D���ӷ�ֵ", "E���ӷ�ֵ" };
		return getQueryTitle(enList, cnList);
	}
	
	/**
	 * ���ӿ��ض��˸�������Ϣ���
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean addKtergysxx(XljkXlcyjgModel model) throws Exception{
		return dao.addKtergysxx(model);
	}

	/**
	 * �޸Ŀ��ض��˸�������Ϣ���
	 * @param model
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public boolean updateKtergysxx(XljkXlcyjgModel model, String pkValue)
			throws Exception {
		return dao.updateKtergysxx(model, pkValue);
	}
	
	/**
	 * ��ʾ���ض��˸�������Ϣ���
	 * @param pkValue
	 * @return
	 */
	public HashMap<String, String> viewKtergysxx(String pkValue) {
		return dao.viewKtergysxx(pkValue);
	}
	
	/**
	 * ��ѯ��ѧ��������Ϣ
	 * @param rs
	 * @return
	 */
	public List<String[]> queryDxsrgpyResult(HashMap<String, String> rs) {
		return dao.queryDxsrgpyResult(rs);
	}
	
	/**
	 * ��ѯ���ڻ���������еĴ�ѧ���˸���������
	 * @return
	 * @throws Exception
	 */
	public String queryDxsyzNotExistsDB() throws Exception{
		String result = "";
		PjpyTyService service = new PjpyTyService();
		
		//���ݿ��е�������Ϣ
		String[] second = dao.queryDxsyzNotExistsDB();
		//Ĭ�ϵ�������Ϣ
		String[] first = {GlobalsVariable.XLJK_DXSRGYS_QTZZYZ,
				GlobalsVariable.XLJK_DXSRGYS_JSFLYZ, 
				GlobalsVariable.XLJK_DXSRGYS_YYZYZ,
				GlobalsVariable.XLJK_DXSRGYS_SJZYZ};
		
		//�����������в��ظ�������ȡ����
		String[] rs = service.checkCfsj(first, second);
		if (rs != null && rs.length > 0) {
			for (String s : rs) {
				result += s;
				result += ",";
			}
		}
		return StringUtils.isNotNull(result) ? result.substring(0, result.length() - 1) : result;
	}
	
	/**
	 * ��ѯ���ض��˸�����������Ϣ
	 * @param rs
	 * @return
	 */
	public List<String[]> queryKteyzpyResult(HashMap<String, String> rs) {
		return dao.queryKteyzpyResult(rs);
	}
	
	/**
	 * ��ѯ���ڻ���������еĿ��ض��˸���������
	 * @return
	 * @throws Exception
	 */
	public String queryKteyzNotExistsDB() throws Exception{
		String result = "";
		PjpyTyService service = new PjpyTyService();
		
		//Ĭ�ϵ�������Ϣ
		String[] yzArr = dao.getKteYzArr();
		//���ݿ��е�������Ϣ
		String[] secondArr = dao.queryKteyzNotExistsDB();
		yzArr = getYzArr(yzArr);
		
		//ȡ�����������в��ظ�����
		String[] rs = service.checkCfsj(yzArr, secondArr);
		if (rs != null && rs.length > 0) {
			for (String s : rs) {
				result += s;
				result += ",";
			}
		}
		return StringUtils.isNotNull(result) ? result.substring(0, result.length() - 1) : result;
	}
	
	public String[] getYzArr(String[] yzArr) {
		if (yzArr != null && yzArr.length > 0) {
			String[] rs = new String[yzArr.length];
			for (int i=0;i<yzArr.length;i++) {
				rs[i] = yzArr[i].replaceAll("yz", "").toUpperCase();
			}
			return rs;
		} else {
			return null;
		}
	}
	
	private List<HashMap<String, String>> getQueryTitle(String[] en, String[] cn) {
		DAO mydao = DAO.getInstance();
		return mydao.arrayToList(en, cn); 
	}
}
