package xgxt.pjpy.zjcm;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.utils.String.StringUtils;

public class ZhszcpService {

	ZhszcpDAO dao = ZhszcpDAO.getInstance();
	
	public static ZhszcpService service = new ZhszcpService();
	
	public static ZhszcpService getInstance() {
		return service;
	}
	
	/**
	 * ͨ���������ͷ��ر���
	 * @param szlx
	 * @return
	 */
	public String getTableName(String szlx) {
		if ("0".equalsIgnoreCase(szlx)) {
			return "zjcm_dycjb";
		} else if ("1".equalsIgnoreCase(szlx)) {
			return "zjcm_zycjb";
		} else if ("2".equalsIgnoreCase(szlx)) {
			return "zjcm_tycjb";
		} else if ("3".equalsIgnoreCase(szlx)) {
			return "zjcm_sjcxcjb";
		} else if ("4".equalsIgnoreCase(szlx)) {
			return "zjcm_zhszcpb";
		} else {
			return "zjcm_dycjb";
		}
	}
	
	/**
	 * ͨ���������ͷ��ر���
	 * @param szlx
	 * @return
	 */
	public String getTitName(String szlx) {
		if ("0".equalsIgnoreCase(szlx)) {
			return "�����ɼ�";
		} else if ("1".equalsIgnoreCase(szlx)) {
			return "�����ɼ�";
		} else if ("2".equalsIgnoreCase(szlx)) {
			return "�����ɼ�";
		} else if ("3".equalsIgnoreCase(szlx)) {
			return "ʵ�����³ɼ�";
		} else if ("4".equalsIgnoreCase(szlx)) {
			return "�ۺϲ����ɼ�";
		} else {
			return "�����ɼ�";
		}
	}
	
	/**
	 * ͨ���������ͷ��ر���
	 * @param szlx
	 * @return
	 */
	public String getTitNameBytable(String tableName) {
		if ("zjcm_dycjb".equalsIgnoreCase(tableName)) {
			return "�����ɼ�";
		} else if ("zjcm_zycjb".equalsIgnoreCase(tableName)) {
			return "�����ɼ�";
		} else if ("zjcm_tycjb".equalsIgnoreCase(tableName)) {
			return "�����ɼ�";
		} else if ("zjcm_sjcxcjb".equalsIgnoreCase(tableName)) {
			return "ʵ�����³ɼ�";
		} else if ("zjcm_zhszcpb".equalsIgnoreCase(tableName)) {
			return "�ۺϲ����ɼ�";
		} else {
			return "�����ɼ�";
		}
	}
	
	/**
	 * ��ѯ��ͷ
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getTitleName(String szlx)
			throws Exception {
		return dao.getTitleName(szlx);
	}
	
	/**
	 * ��ѯ���
	 * 
	 * @param model
	 * @param tableName
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getResult(ZhszcpModel model, String tableName,
			PjpyZjcmActionForm dataSearchForm, String isFdy, String userName) throws Exception {
		return dao.getResult(model, tableName, dataSearchForm, isFdy, userName);
	}

	/**
	 * ��ѯ�����¼��
	 * 
	 * @param model
	 * @param tableName
	 * @return
	 * @throws Exception
	 */
	public int getResultNum(ZhszcpModel model, String tableName, String isFdy, String userName)
			throws Exception {
		return dao.getResultNum(model, tableName, isFdy, userName);
	}
	
	/**
	 * ���ʷֱ���
	 * @param model
	 * @param tableName
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean save(ZhszcpModel model, String tableName,
			HttpServletRequest request) throws Exception {
		return dao.save(model, tableName, request);
	}
	
	/**
	 * ���ʷ��޸�
	 * @param model
	 * @param tableName
	 * @param request
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public boolean update(ZhszcpModel model, String tableName,
			HttpServletRequest request, String pkValue) throws Exception {
		return dao.update(model, tableName, request, pkValue);
	}
	
	/**
	 * ɾ��
	 * @param keys
	 * @param tableName
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String delete(String[] keys, String tableName,
			HttpServletRequest request) throws Exception {
		return dao.delete(keys, tableName, request);
	}
	
	/**
	 * �޸���ʾ
	 * 
	 * @param pkValue
	 * @param tableName
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> view(String pkValue, String tableName)
			throws Exception {
		return dao.view(pkValue, tableName);
	}
	
	/**
	 * �ۺ����ʷֵ��Զ�����
	 * @param xn
	 * @param xq
	 * @param xydm
	 * @return
	 * @throws Exception
	 */
	public boolean zhszAutoCount(String xn , String xq, String xydm) throws Exception {
		return dao.zhszAutoCount(xn, xq, xydm);
	}
	
	/**
	 * �ۺ����ʲ�������
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getZhszcpBlxx() throws Exception {
		return dao.getZhszcpBlxx();
	}
	
	/**
	 * �����ۺϲ�������
	 * 
	 * @param pkValue
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean saveZhcpbl(String pkValue, ZhszcpModel model,
			HttpServletRequest request) throws Exception {
		return dao.saveZhcpbl(pkValue, model, request);
	}
	
	/**
	 * �����������Ƿ�������
	 * @return
	 * @throws Exception
	 */
	public boolean chgZhszcpBlExists() throws Exception {
		return dao.chgZhszcpBlExists();
	}
	
	/**
	 * ��ȡ�����������༶�б�
	 * @param userName
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getFdybjList(String userName)
			throws Exception {
		return dao.getFdybjList(userName);
	}
	
	public HashMap<String, String> getJxjsqxnxq() throws Exception {
		return dao.getJxjsqxnxq();
	}
	
	public HashMap<String, String> getXy(String xh, String pkValue) throws Exception {
		return dao.getXy(xh, pkValue);
	}
	
	/**
	 * ��ʽ����������������,��
	 * ��ʽΪ2009-10-20 ����2009��10��
	 * ��ʽΪ20091020 ����2009��10��
	 * @param rxrq
	 * @return
	 * @throws Exception
	 */
	public String returnNy(String ny) throws Exception {
		String rxn = "";
		String rxy = "";
		if (!StringUtils.isNull(ny)) {
			if (ny.indexOf("-") != -1) {//��ʽ��:2009-01-20
				if (ny.length()>=7) {
					rxn = ny.substring(0, 4);
					rxy = ny.substring(5, 7);
				}
			} else {//��ʽ��:20091020
				if (ny.length()>=6) {
					rxn = ny.substring(0, 4);
					rxy = ny.substring(4, 6);
				}
			}
			return rxn + " �� " + rxy + " ��";
		}
		return "";
	}
}
