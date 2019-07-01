
package xgxt.pjpy.jgsdx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.utils.String.StringUtils;

/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: ����ɽ��ѧ��������Service</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: ����</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-06-026</p>
 */
public class PjpyJgsdxService {

	PjpyJgsdxDAO dao = null;//���ݲ���DAO
	
	/**
	 * ��ȡרҵ��ѧ���б�
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getZyjxjList() throws Exception {
		dao = new PjpyJgsdxDAO();
		return dao.getZyjxjList();
	}
	
	/**
	 * ͨ����ѧ������ȡ��ѧ���
	 * getJxjJeByJxjdm ---- ͨ����ѧ������ȡ��ѧ��� 
	 * @param jxjdm
	 * @return
	 * @throws Exception
	 */
	public String getJxjJeByJxjdm(String jxjdm) throws Exception {
		dao = new PjpyJgsdxDAO();
		return dao.getJxjJeByJxjdm(jxjdm);
	}
	
	/**
	 * רҵ��ѧ���ѯ��ͷ
	 * @param isFdy
	 * @param userType
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getZyjxjSbTitle(String isFdy, String userType) throws Exception {
		dao = new PjpyJgsdxDAO();
		List<HashMap<String, String>> topList = new ArrayList<HashMap<String,String>>();		
			
		if (StringUtils.isEqual(isFdy, "true")) {//����Ա
			topList = dao.fdyZyjxjsbTitle();
		}
		if (StringUtils.isEqual(isFdy, "false") && StringUtils.isEqual(userType, "xy")) {//ѧԺ
			/*if (StringUtils.isEqual(isFdy, "true")) {
				topList = dao.fdyZyjxjsbTitle();
			}else {
				topList = dao.xyZyjxjsbTitle();
			}*/
			topList = dao.xyZyjxjsbTitle();
		}
		if (StringUtils.isEqual(userType, "xx") || StringUtils.isEqual(userType, "admin")) {//ѧУ
			topList = dao.xxZyjxjsbTitle();
		}
		return topList;
	}
	
	/**
	 * רҵ��ѧ���ѯ���
	 * @param isFdy
	 * @param userType
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getZyjxjSbResult(String isFdy, String userType, ZyjxjQryModel zyjxjModel) throws Exception {
		dao = new PjpyJgsdxDAO();
		List<String[]> resList = new ArrayList<String[]>();
		if (StringUtils.isEqual(isFdy, "true")) {//����Ա
			resList = dao.fdyZyjxjsbResult(zyjxjModel);
		}
		if (StringUtils.isEqual(isFdy, "false") && StringUtils.isEqual(userType, "xy")) {//ѧԺ
			resList = dao.xyZyjxjsbResult(zyjxjModel);
		}
		if (StringUtils.isEqual(userType, "xx") || StringUtils.isEqual(userType, "admin")) {//ѧУ
			resList = dao.xxZyjxjsbResult(zyjxjModel);
		}
		return resList;
	}
	
	/**
	 * ͨ��������ȡ�ۺ����ʺ�ѧϰ�ɼ�
	 * getZhszandXxcjByPk ---- ͨ��������ȡ�ۺ����ʺ�ѧϰ�ɼ�
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getZhszandXxcjByPk(String pkValue, String xh) throws Exception {
		dao = new PjpyJgsdxDAO();
		return dao.getZhszandXxcjByPk(pkValue, xh);
	}
	
	/**
	 * �ۺ����ʺ�ѧϰ�ɼ�����
	 * zhszandxxcjSave ---- �ۺ����ʺ�ѧϰ�ɼ�����
	 * @param zxsaveModel
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean zhszandxxcjSave(ZhszandXxcjSaveModel zxsaveModel, HttpServletRequest request) throws Exception {
		dao = new PjpyJgsdxDAO();
		return dao.zhszandxxcjSave(zxsaveModel, request);
	}
	
	/**
	 * רҵ��ѧ����˸���Ա��ѧԺ��ѧУ
	 * @param isFdy
	 * @param userType
	 * @return
	 * @throws Exception
	 */
	public boolean zyjxjSh(String isFdy, String userType, ZyjxjShModel zyjxjModel, HttpServletRequest request) throws Exception {
		dao = new PjpyJgsdxDAO();
		boolean bFlag = false;
		if (StringUtils.isEqual(isFdy, "true")) {//����Ա
			dao.zyjxjShByFdy(zyjxjModel, request);
		}
		if (StringUtils.isEqual(isFdy, "false") && StringUtils.isEqual(userType, "xy")) {//ѧԺ
			dao.zyjxjShByXy(zyjxjModel, request);
		}
		if (StringUtils.isEqual(userType, "xx") || StringUtils.isEqual(userType, "admin")) {//ѧУ
			dao.zyjxjShByXx(zyjxjModel, request);
		}
		return bFlag;
	}
	
	/**
	 * ��������б�
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public List getChkList(int type) throws Exception {
		dao = new PjpyJgsdxDAO();
		return dao.getChkList(type);
	}
	
	/**
	 * רҵ��ѧ������ɾ��
	 * @param keys
	 * @return
	 * @throws Exception
	 */
	public String zyjxjblDel(String[] keys) throws Exception {
		dao = new PjpyJgsdxDAO();
		return dao.zyjxjblDel(keys);
	}
	
	/**
	 * רҵ��ѧ���ӡ�б�
	 * @param zyjxjModel
	 * @return
	 * @throws Exception
	 */
	public List<String[]> zyjxjPrint(ZyjxjQryModel zyjxjModel) throws Exception {
		dao = new PjpyJgsdxDAO();
		List<String[]> resList = new ArrayList<String[]>();
		resList = dao.zyjxjPrint(zyjxjModel);
		return resList;
	}
	
	/**
	 * �����б�ѡ����Ϣ
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getRysqList(int type) throws Exception {
		List<HashMap<String, String>> list = new ArrayList<HashMap<String,String>>();
		String[] en = null;
		String[] cn = null;
		if (type == 1) {
			en = new String[] {"wmbj" , "grrych"};
			cn = new String[] {"�����༶" ,"���������ƺ�"};
		}else if (type == 2) {
			en = new String[] {"xjbj" };
			cn = new String[] {"�����༶"};
		}else if (type == 3) {
			en = new String[] {"wmbj" , "grrych", "grrych3"};
			cn = new String[] {"�����༶" ,"���������ƺŶ������", "���������ƺ��������"};
		}
		for (int i = 0; i < en.length; i++) {
			HashMap<String, String> tempMap = new HashMap<String, String>();
			tempMap.put("en", en[i]);
			tempMap.put("cn", cn[i]);
			list.add(tempMap);//����������ҳѡ��������Ϣ
		}//end for
		return list;
	}
	
	/**
	 * ��֤�����Ƿ��ظ�
	 * chkDataByXjbj ---- ��֤�Ƚ��༶�����Ƿ��ظ� 
	 * @param xjbjSqModel
	 * @return
	 * @throws Exception
	 */
	public boolean chkDataByWmbj(WmbjSqModel wmbjsqModel) throws Exception {
		dao = new PjpyJgsdxDAO();
		return dao.chkDataByWmbj(wmbjsqModel);
	}
	
	/**
	 * �����Ƚ��༶��Ϣ
	 * savexjbjinfo ---- �����Ƚ��༶��Ϣ 
	 * @param xjbjSqModel
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean saveWmBjInfo(WmbjSqModel xjbjSqModel, HttpServletRequest request) throws Exception {
		dao = new PjpyJgsdxDAO();
		return dao.saveWmBjInfo(xjbjSqModel, request);
	}
	
	/**
	 * �����༶�����ѯ���
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> wmbjcxTitle() throws Exception {
		dao = new PjpyJgsdxDAO();
		return dao.wmbjCxJg();
	}
	
	/**
	 * �����༶�����ѯ���
	 * @param xjbjqryModel
	 * @return
	 * @throws Exception
	 */
	public List<String[]> wmbjsqCxJg(WmbjSqModel xjbjqryModel) throws Exception {
		dao = new PjpyJgsdxDAO();
		return dao.wmbjsqCxJg(xjbjqryModel);
	}
	
	/**
	 * �����༶����ɾ��
	 * delxjbjxx ---- �Ƚ��༶��Ϣ����ɾ��
	 * @param keys
	 * @return
	 * @throws Exception
	 */
	public String delWmbjXx(String[] keys) throws Exception {
		dao = new PjpyJgsdxDAO();
		return dao.delWmbjXx(keys);
	}
	
	/**
	 * ͨ��������ȡ�����༶��Ϣ
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getWmbjByPk(String pkValue) throws Exception {
		dao = new PjpyJgsdxDAO();
		return dao.getWmbjByPk(pkValue);
	}
	
	/**
	 * רҵ��ѧ����˸���Ա��ѧԺ��ѧУ
	 * @param isFdy
	 * @param userType
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> wmbjShTitle(String isFdy, String userType, HttpServletRequest request) throws Exception {
		dao = new PjpyJgsdxDAO();
		List<HashMap<String, String>> topList = new ArrayList<HashMap<String,String>>();
		if (StringUtils.isEqual(isFdy, "true")) {//����Ա
			topList = dao.fdyQryWmbjTitle();
		}
		if (StringUtils.isEqual(isFdy, "false") && StringUtils.isEqual(userType, "xy")) {//ѧԺ
			topList = dao.xyQryWmbjTitle();
		}
		if (StringUtils.isEqual(userType, "xx") || StringUtils.isEqual(userType, "admin")) {//ѧУ
			topList = dao.xxQryWmbjTitle();
		}
		return topList;
	}
	
	/**
	 * רҵ��ѧ����˸���Ա��ѧԺ��ѧУ
	 * @param isFdy
	 * @param userType
	 * @return
	 * @throws Exception
	 */
	public List<String[]> wmbjShResult(String isFdy, String userType, WmbjSqModel wmbjModel, HttpServletRequest request) throws Exception {
		dao = new PjpyJgsdxDAO();
		List<String[]> resList = new ArrayList<String[]>();
		if (StringUtils.isEqual(isFdy, "true")) {//����Ա
			resList = dao.fdyQryWmbjResult(wmbjModel);
		}
		if (StringUtils.isEqual(isFdy, "false") && StringUtils.isEqual(userType, "xy")) {//ѧԺ
			resList = dao.xyQryWmbjResult(wmbjModel);
		}
		if (StringUtils.isEqual(userType, "xx") || StringUtils.isEqual(userType, "admin")) {//ѧУ
			resList = dao.xxQryWmbjResult(wmbjModel);
		}
		return resList;
	}
	
	/**
	 * ��ȡ�����༶���������ʾ��Ϣ
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getWmbjShView(String isFdy, String userType,String pkValue) throws Exception {
		dao = new PjpyJgsdxDAO();
		HashMap<String, String> resMap = new HashMap<String, String>();
		if (StringUtils.isEqual(isFdy, "true")) {//����Ա
			resMap = dao.getWmbjShViewByFdy(pkValue);
		}
		if (StringUtils.isEqual(isFdy, "false") && StringUtils.isEqual(userType, "xy")) {//ѧԺ
			resMap = dao.getWmbjShViewByXy(pkValue);
		}
		if (StringUtils.isEqual(userType, "xx") || StringUtils.isEqual(userType, "admin")) {//ѧУ
			resMap = dao.getWmbjShViewByXx(pkValue);
		}
		return resMap;
	}
	
	/**
	 * �����༶�������
	 * @param isFdy
	 * @param userType
	 * @param wmbjModel
	 * @return
	 * @throws Exception
	 */
	public boolean wmbjShByOne(String isFdy, String userType,WmbjShModel wmbjModel, HttpServletRequest request) throws Exception {
		dao = new PjpyJgsdxDAO();
		boolean bFlag = false;
		if (StringUtils.isEqual(isFdy, "true")) {//����Ա
			bFlag = dao.fdyShWmbjByone(wmbjModel, request);
		}
		if (StringUtils.isEqual(isFdy, "false") && StringUtils.isEqual(userType, "xy")) {//ѧԺ
			bFlag = dao.xyShWmbjByone(wmbjModel, request);
		}
		if (StringUtils.isEqual(userType, "xx") || StringUtils.isEqual(userType, "admin")) {//ѧУ
			bFlag = dao.xxShWmbjByone(wmbjModel, request);
		}
		return bFlag;
	}
	
	/**
	 * �����༶�������
	 * @param isFdy
	 * @param userType
	 * @param keys
	 * @param res
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean wmbjJtSh(String isFdy, String userType,String[] keys, String res, HttpServletRequest request) throws Exception {
		dao = new PjpyJgsdxDAO();
		boolean bFlag = false;
		if (StringUtils.isEqual(isFdy, "true")) {//����Ա
			dao.fdyWmbjjtSh(keys, res, request);
		}
		if (StringUtils.isEqual(isFdy, "false") && StringUtils.isEqual(userType, "xy")) {//ѧԺ
			dao.xyWmbjjtSh(keys, res, request);
		}
		if (StringUtils.isEqual(userType, "xx") || StringUtils.isEqual(userType, "admin")) {//ѧУ
			dao.xxWmbjjtSh(keys, res, request);
		}
		return bFlag;
	}
	
	/**
	 * ��ȡ�����ƺ��б�
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getRychList() throws Exception {
		dao = new PjpyJgsdxDAO();
		return dao.getRychList();
	}
	
	/**
	 * ��ȡ��ѧ������ѧ�����
	 * @return
	 * @throws Exception
	 */
	public String[] getJxjsqXnNd() throws Exception {
		dao = new PjpyJgsdxDAO();
		return dao.getJxjsqXnNd();
	}
	
	/**
	 * �����ƺŲ�ѯ��ͷ(����Ա��ѧԺ��ѧУ)
	 * @param isFdy
	 * @param userType
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getRychQryTitle(String isFdy, String userType, String rychdm) throws Exception {
		dao = new PjpyJgsdxDAO();
		String rychmc = dao.getRychmc(rychdm);
		List<HashMap<String, String>> topList = new ArrayList<HashMap<String,String>>();
		if (StringUtils.isEqual(isFdy, "true")
				&& !StringUtils.isNull(rychmc)
				&& (StringUtils.isEqual(rychmc, "������ѧ��")
						|| StringUtils.isEqual(rychmc, "�����ҵ��")
						|| StringUtils.isEqual(rychmc, "����ѧ�����") || StringUtils
						.isEqual(rychmc, "����ѧ��") || StringUtils.isEqual(rychmc, "����ѧ���ɲ�"))) {//����Ա
			topList = dao.getRychQryTitleByFdy();
		}
		else if (StringUtils.isEqual(isFdy, "false") && StringUtils.isEqual(userType, "xy")) {//ѧԺ
			topList = dao.getRychQryTitleByXy();
		}
		else if (StringUtils.isEqual(userType, "xx") || StringUtils.isEqual(userType, "admin")) {//ѧУ
			topList = dao.getRychQryTitleByXx();
		} else {//�����û�ȡѧԺ��ͷ
			topList = dao.getRychQryTitleByXy();
		}
		return topList;
	}
	
	/**
	 *  �����ƺŲ�ѯ���(����Ա��ѧԺ��ѧУ)
	 * @param isFdy
	 * @param userType
	 * @param rychModel
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getRychQryResult (String isFdy, String userType, RychQryModel rychModel, String rychdm) throws Exception {
		dao = new PjpyJgsdxDAO();
		String rychmc = dao.getRychmc(rychdm);
		List<String[]> resList = new ArrayList<String[]>();
		if (StringUtils.isEqual(isFdy, "true")
				&& !StringUtils.isNull(rychmc)
				&& (StringUtils.isEqual(rychmc, "������ѧ��")
						|| StringUtils.isEqual(rychmc, "�����ҵ��")
						|| StringUtils.isEqual(rychmc, "����ѧ�����") || StringUtils
						.isEqual(rychmc, "����ѧ��") || StringUtils.isEqual(rychmc, "����ѧ���ɲ�"))) {//����Ա
			resList = dao.getRychQryResultByFdy(rychModel);
		}
		else if (StringUtils.isEqual(isFdy, "false") && StringUtils.isEqual(userType, "xy")) {//ѧԺ
			if (StringUtils.isEqual(rychmc, "������ѧ��")
					|| StringUtils.isEqual(rychmc, "�����ҵ��")
					|| StringUtils.isEqual(rychmc, "����ѧ�����") || StringUtils
					.isEqual(rychmc, "����ѧ��") || StringUtils.isEqual(rychmc, "����ѧ���ɲ�")) {//�������
				resList = dao.getRychQryResultByXy3(rychModel);
			}else {//�������
				resList = dao.getRychQryResultByXy(rychModel);
			}
		}
		else if (StringUtils.isEqual(userType, "xx") || StringUtils.isEqual(userType, "admin")) {//ѧУ
			if (StringUtils.isEqual(rychmc, "������ѧ��")
					|| StringUtils.isEqual(rychmc, "�����ҵ��")
					|| StringUtils.isEqual(rychmc, "����ѧ�����") || StringUtils
					.isEqual(rychmc, "����ѧ��") || StringUtils.isEqual(rychmc, "����ѧ���ɲ�")) {//�������
				resList = dao.getRychQryResultByXx3(rychModel);
			}else {//�������
				resList = dao.getRychQryResultByXx(rychModel);
			}
		} else {//�����û�ȡѧԺ���
			resList = dao.getRychQryResultByXy(rychModel);
		}
		return resList;
	}
	
	/**
	 * ���������ƺ����(����Ա��ѧԺ��ѧУ)
	 * @param isFdy
	 * @param userType
	 * @param cbv
	 * @param rychdm
	 * @return
	 * @throws Exception
	 */
	public boolean grrychSh(String isFdy, String userType, GrrychShModel grrychModel, HttpServletRequest request) throws Exception {
		dao = new PjpyJgsdxDAO();
		boolean bFlag = false;
		String rychmc = dao.getRychmc(grrychModel.getRychdm());
		if (StringUtils.isEqual(isFdy, "true")
				&& !StringUtils.isNull(rychmc)
				&& (StringUtils.isEqual(rychmc, "������ѧ��")
						|| StringUtils.isEqual(rychmc, "�����ҵ��")
						|| StringUtils.isEqual(rychmc, "����ѧ�����") || StringUtils
						.isEqual(rychmc, "����ѧ��") || StringUtils.isEqual(rychmc, "����ѧ���ɲ�"))) {//����Ա
			dao.fdyshResult(grrychModel.getCbv(), grrychModel.getShjg(), request);
		}
		else if (StringUtils.isEqual(isFdy, "false") && StringUtils.isEqual(userType, "xy")) {//ѧԺ
			dao.xyshResult(grrychModel.getCbv(), grrychModel.getShjg(), request);
		}
		else if (StringUtils.isEqual(userType, "xx") || StringUtils.isEqual(userType, "admin")) {//ѧУ
			dao.xxshResult(grrychModel.getCbv(), grrychModel.getShjg(), request);
		} else {//�����û�ȡѧԺ
			dao.xyshResult(grrychModel.getCbv(), grrychModel.getShjg(), request);
		}
		return bFlag;
	}
	
	/**
	 * ��ȡ�����ƺ������Ϣ
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getRychInfo(String pkValue) throws Exception {
		dao = new PjpyJgsdxDAO();
		return dao.getRychInfo(pkValue);
	}
	
	/**
	 * ��ȡ�����ƺ�����
	 * @param rychdm
	 * @return
	 * @throws Exception
	 */
	public String getRychMc(String rychdm) throws Exception {
		dao = new PjpyJgsdxDAO();
		return dao.getRychmc(rychdm);
	}
	
	/**
	 * �����ƺ������ʾ��ϸ��Ϣ
	 * @param isFdy
	 * @param userType
	 * @param rychdm
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getRychshView(String isFdy, String userType, String rychdm, String pkValue) throws Exception {
		dao = new PjpyJgsdxDAO();
		String rychmc = dao.getRychmc(rychdm);
		HashMap<String, String> resMap = new HashMap<String, String>();
		if (StringUtils.isEqual(isFdy, "true")
				&& !StringUtils.isNull(rychmc)
				&& (StringUtils.isEqual(rychmc, "������ѧ��")
						|| StringUtils.isEqual(rychmc, "�����ҵ��")
						|| StringUtils.isEqual(rychmc, "����ѧ�����") || StringUtils
						.isEqual(rychmc, "����ѧ��") || StringUtils.isEqual(rychmc, "����ѧ���ɲ�"))) {//����Ա
			resMap = dao.getRychInfoByFdy(pkValue);
		}
		else if (StringUtils.isEqual(isFdy, "false") && StringUtils.isEqual(userType, "xy")) {//ѧԺ
			resMap = dao.getRychInfoByXy(pkValue);
		}
		else if (StringUtils.isEqual(userType, "xx") || StringUtils.isEqual(userType, "admin")) {//ѧУ
			resMap =  dao.getRychInfoByXx(pkValue);
		} else {//�����û�ȡѧԺ
			resMap = dao.getRychInfoByXy(pkValue);
		}
		return resMap;
	}
	
	/**
	 * ���������ƺŵ������
	 * @param isFdy
	 * @param userType
	 * @param grrychModel
	 * @return
	 * @throws Exception
	 */
	public boolean grrychModi(String isFdy, String userType, GrrychModiModel grrychModel, HttpServletRequest request) throws Exception {
		dao = new PjpyJgsdxDAO();
		String rychmc = dao.getRychmc(grrychModel.getRychdm());
		boolean bFlag = false;
		if (StringUtils.isEqual(isFdy, "true")
				&& !StringUtils.isNull(rychmc)
				&& (StringUtils.isEqual(rychmc, "������ѧ��")
						|| StringUtils.isEqual(rychmc, "�����ҵ��")
						|| StringUtils.isEqual(rychmc, "����ѧ�����") || StringUtils
						.isEqual(rychmc, "����ѧ��") || StringUtils.isEqual(rychmc, "����ѧ���ɲ�"))) {//����Ա
			bFlag = dao.rychShByFdy(grrychModel, request);
		}
		else if (StringUtils.isEqual(isFdy, "false") && StringUtils.isEqual(userType, "xy")) {//ѧԺ
			bFlag = dao.rychShByXy(grrychModel, request);
		}
		else if (StringUtils.isEqual(userType, "xx") || StringUtils.isEqual(userType, "admin")) {//ѧУ
			bFlag = dao.rychShByXx(grrychModel, request);
		} else {//�����û�ȡѧԺ
			bFlag = dao.rychShByXy(grrychModel, request);
		}
		return bFlag;
	}
	
	/**
	 * ��ѯ��ͷ
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> qryRychTitle() throws Exception {
		dao = new PjpyJgsdxDAO();
		return dao.qryRychTitle();
	}
	
	/**
	 * ��ѯ���
	 * @return
	 * @throws Exception
	 */
	public List<String[]> qryRychResult(RychSjQryModel rychModel) throws Exception {
		dao = new PjpyJgsdxDAO();
		return dao.qryRychResult(rychModel);
	}
	
	/**
	 * ��ȡ�����ƺ���Ϣ
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> rychInfoByPk(String pkValue) throws Exception {
		dao = new PjpyJgsdxDAO();
		return dao.rychInfoByPk(pkValue);
	}
	
	/**
	 * �����ƺ������޸�
	 * @param pkValue
	 * @param rychdm
	 * @param drzw
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean rychInfoModi(String pkValue, String rychdm, String drzw, String xh, HttpServletRequest request) throws Exception {
		dao = new PjpyJgsdxDAO();
		return dao.rychInfoModi(pkValue, rychdm, drzw, xh, request);
	}
	
	/**
	 * �����ƺ���Ϣ����ɾ��
	 * @param keys
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String rychInfoDel(String[] keys, HttpServletRequest request) throws Exception {
		dao = new PjpyJgsdxDAO();
		return dao.rychInfoDel(keys, request);
	}
	
	/**
	 * ��ȡ��ѧ���б�
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getJxjList() throws Exception {
		dao = new PjpyJgsdxDAO();
		return dao.getJxjList();
	}
	
	/**
	 * ��ȡ�ֶ��б�
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getZdList() throws Exception {
		dao = new PjpyJgsdxDAO();
		return dao.getZdList();
	}
	
	/**
	 * ��ѧ���Ӧ����
	 * @param xn
	 * @param jxjdm
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getJxjsztj(String xn, String jxjdm) throws Exception {
		dao = new PjpyJgsdxDAO();
		return dao.getJxjsztj(xn, jxjdm);
	}
	
	/**
	 * ��ѧ��������ѯ ��ͷ
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getJxjsztjTitle() throws Exception {
		dao = new PjpyJgsdxDAO();
		return dao.getJxjsztjTitle();
	}
	
	/**
	 * ��ѧ����������ɾ��
	 * @param pkValue
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean jxjsztjDel(String pkValue, HttpServletRequest request) throws Exception {
		dao = new PjpyJgsdxDAO();
		return dao.jxjsztjDel(pkValue, request);
	}
	
	/**
	 * ��ѧ���������ñ���
	 * @param tjszModel
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean jxjsztjSave(JxjtjszSaveModel tjszModel, HttpServletRequest request) throws Exception {
		dao = new PjpyJgsdxDAO();
		return dao.jxjsztjSave(tjszModel, request);
	}
	
	/**
	 * �������õ�����ϸ��Ϣ
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getTjInfo(String pkValue, String jxjdm) throws Exception {
		dao = new PjpyJgsdxDAO();
		return dao.getTjInfo(pkValue, jxjdm);
	}
	
	/**
	 * �������õ����޸�
	 * @param pkValue
	 * @param tjszModel
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean tjEditResult(String pkValue, JxjtjszSaveModel tjszModel,
			HttpServletRequest request) throws Exception {
		dao = new PjpyJgsdxDAO();
		return dao.tjEditResult(pkValue, tjszModel, request);
	}
	
	/**
	 * ��ȡ�ֶ���
	 * @param tjzdm
	 * @return
	 * @throws Exception
	 */
	public String getZdm(String tjzdm) throws Exception{
		String zdm = "";
		if (!StringUtils.isNull(tjzdm) && StringUtils.isEqual(tjzdm, "zhszcpzf")) {
			zdm = "�ۺ����ʲ����ܷ�";
		}
		if (!StringUtils.isNull(tjzdm) && StringUtils.isEqual(tjzdm, "rownum")) {
			zdm = "�ۺ����ʲ�������(��)";
		}
		if (!StringUtils.isNull(tjzdm) && StringUtils.isEqual(tjzdm, "cj")) {
			zdm = "�γ̵��Ƴɼ�";
		}
		if (!StringUtils.isNull(tjzdm) && StringUtils.isEqual(tjzdm, "pjf")) {
			zdm = "�γ�ƽ���ɼ�";
		}
		if (!StringUtils.isNull(tjzdm) && StringUtils.isEqual(tjzdm, "dcj")) {
			zdm = "�³ɼ�";
		}
		if (!StringUtils.isNull(tjzdm) && StringUtils.isEqual(tjzdm, "zcj")) {
			zdm = "�ǳɼ�";
		}
		if (!StringUtils.isNull(tjzdm) && StringUtils.isEqual(tjzdm, "tcj")) {
			zdm = "��ɼ�";
		}
		return zdm;
	}
	
	/**
	 * ��ȡ�ֶβ���
	 * @param zdcz
	 * @return
	 * @throws Exception
	 */
	public String getZdcz(String zdcz) throws Exception {
		String cz = "";
		if (!StringUtils.isNull(zdcz) && StringUtils.isEqual(zdcz, "sum")) {
			cz = "�ܺ�";
		}
		if (!StringUtils.isNull(zdcz) && StringUtils.isEqual(zdcz, "avg")) {
			cz = "ƽ��ֵ";
		}
		if (!StringUtils.isNull(zdcz) && StringUtils.isEqual(zdcz, "max")) {
			cz = "���ֵ";
		}
		if (!StringUtils.isNull(zdcz) && StringUtils.isEqual(zdcz, "min")) {
			cz = "��Сֵ";
		}
		return cz;
	}
	
	/**
	 * ��ȡ������ֵ
	 * @param tj
	 * @return
	 * @throws Exception
	 */
	public String[] getTjValue(String tj) throws Exception {
		String[] tjValue = new String[2];
		if (!StringUtils.isNull(tj)) {
			tjValue[0] = tj.substring(0, tj.indexOf("'"));
			tjValue[1] = tj.substring(tj.indexOf("'") + 1, tj.lastIndexOf("'"));
		}
		return tjValue;
	}
	
	/**
	 * ��ȡ����
	 * @param tj
	 * @return
	 * @throws Exception
	 */
	public String getTj(String tj) throws Exception {
		String tjen = "";
		if (!StringUtils.isNull(tj) && StringUtils.isEqual(tj, "=")) {
			tjen = "=";
		}
		if (!StringUtils.isNull(tj) && StringUtils.isEqual(tj, ">=")) {
			tjen = "&gt;=";
		}
		if (!StringUtils.isNull(tj) && StringUtils.isEqual(tj, ">")) {
			tjen = "&gt;";
		}
		if (!StringUtils.isNull(tj) && StringUtils.isEqual(tj, "<=")) {
			tjen = "&lt;=";
		}
		if (!StringUtils.isNull(tj) && StringUtils.isEqual(tj, "<")) {
			tjen = "&lt;";
		}
		return tjen;
	}
	
	public String getJxjTjsz(String xn, String jxjdm) throws Exception {
		dao = new PjpyJgsdxDAO();
		return dao.getJxjTjsz(xn, jxjdm);
	}
	
	/**
	 * ��ȡ��ѧ���б�
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getJxjList1() throws Exception {
		dao = new PjpyJgsdxDAO();
		return dao.getJxjList1(dao.getJxjList());
	}
	
	/**
	 * רҵ��ѧ���Զ����
	 * @param zyjxjModel
	 * @throws Exception
	 */
	public void zyjxjAutoSh(ZyjxjAutoshModel zyjxjModel, HttpServletRequest request) throws Exception {
		dao = new PjpyJgsdxDAO();
		String[] jxjsqxnxq = dao.getJxjSqxnxq();
		if (jxjsqxnxq != null && jxjsqxnxq.length == 2) {
			zyjxjModel.setXn(jxjsqxnxq[0]);
			zyjxjModel.setXq(jxjsqxnxq[1]);
		}
		dao.zyjxjAutoSh(zyjxjModel, request);
	}
	
	public HashMap<String, String> getStuInfo(String xh) throws Exception {
		dao = new PjpyJgsdxDAO();
		return dao.getStuInfo(xh);
	}

}
