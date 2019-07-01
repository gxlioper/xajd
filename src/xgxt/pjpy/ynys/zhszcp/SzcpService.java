
package xgxt.pjpy.ynys.zhszcp;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.pjpy.ynys.PjpyYnysService;
import xgxt.utils.String.StringUtils;

/**
 * ���ʲ���Service
 * @describe
 * @author ���� 2008-10-31
 */
public class SzcpService extends PjpyYnysService{
	
	SzcpDAO dao = null;
	
	/**
	 * �����������ͷ��ز�ͬ�ı���
	 * @param szlx
	 * @return
	 * @throws Exception
	 */
	public String getRealTable(String szlx) throws Exception {
		if (StringUtils.isNull(szlx)) {
			return "";
		} else {
			if (StringUtils.isEqual("0", szlx)) {
				return "sxzzddszb";//˼���������������
			} else if (StringUtils.isEqual("1", szlx)) {
				return "kxwhszfb";//��ѧ�Ļ�����
			} else if (StringUtils.isEqual("2", szlx)) {
				return "sxlxszb";//������������
			} else {
				return "sjlxcxszb";//ʵ�������봴������
			}
		}
	}
	
	/**
	 * ����˼�����ʷ�
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public String saveSxzzf(SzcpModel model) throws Exception {
		dao = new SzcpDAO();
		return dao.saveSxzzf(model);
	}
	
	/**
	 * 
	 * @param szlx
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> szcpQryTitle(String szlx, SzcpModel model)
			throws Exception {
		dao = new SzcpDAO();
		String[] enList = null;
		String[] cnList = null;
//		String cxfs = model.getCxfs();
		if (StringUtils.isEqual("0", szlx)) {//˼���������������
			enList = new String[] { "pk", "xh", "xm", "xn", "bjmc", "zf","shzt" };
			cnList = new String[] { "pk", "ѧ��", "����", "ѧ��", "�༶","˼���ܷ�(������60)","���״̬"};
		} else if (StringUtils.isEqual("1", szlx)) {
			enList = new String[] { "pk", "xh", "xm", "xn", "bjmc",
					"zykpjf","whkpjf","kcpjf","shzt"};
			cnList = new String[] { "pk", "ѧ��", "����", "ѧ��", "�༶", "רҵ��ƽ���ɼ�","�Ļ���ƽ���ɼ�","�γ�ƽ����","���״̬"};
//			if (!StringUtils.isNull(cxfs)) {
//				if (StringUtils.isEqual("0", cxfs)) {
//					enList = new String[] { "pk", "rownum", "xh", "xm", "xn", "bjmc",
//							"","zykpjf" };
//					cnList = new String[] { "pk", "�к�", "ѧ��", "����", "ѧ��", "�༶", "רҵ���ܷ�", "רҵ��ƽ����"};
//				} else if (StringUtils.isEqual("1", cxfs)) {
//					enList = new String[] { "pk", "rownum", "xh", "xm", "xn", "bjmc",
//					"","whkpjf" };
//					cnList = new String[] { "pk", "�к�", "ѧ��", "����", "ѧ��", "�༶", "�Ļ����ܷ�", "�Ļ���ƽ����"};
//				}else if (StringUtils.isEqual("2", cxfs)) {
//					enList = new String[] { "pk", "rownum", "xh", "xm", "xn", "bjmc",
//					"","kcpjf" };
//					cnList = new String[] { "pk", "�к�", "ѧ��", "����", "ѧ��", "�༶", "�γ��ܷ�","�γ�ƽ����"};
//				}
//			}
		} else if (StringUtils.isEqual("2", szlx)) {
			enList = new String[] { "pk",  "xh", "xm", "xn","bjmc","zf","shzt" };
			cnList = new String[] { "pk",  "ѧ��", "����", "ѧ��", "�༶", "�����ܷ�(������60)","���״̬"};
		}
		else {
			enList = new String[] { "pk",  "xh", "xm", "xn", "bjmc","zf","shzt" };
			cnList = new String[] { "pk", "ѧ��", "����", "ѧ��", "�༶", "ʵ���ܷ�(������60)","���״̬"};
		}
		return dao.getQryTitle(enList, cnList);
	}
	
	
	/**
	 * ���ʷֲ�ѯ���
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> szfQryRes(SzcpModel model, String szlx) throws Exception {
		dao = new SzcpDAO();
//		String cxfs = model.getCxfs();
		List<String[]> resList = new ArrayList<String[]>();
		if (StringUtils.isEqual("0", szlx)) {//˼���������������
			resList = dao.szxxddfQryRes(model);
		} else if (StringUtils.isEqual("1", szlx)) {//��ѧ�Ļ���
			resList = dao.getKxwhfList(model);
//			if (!StringUtils.isNull(cxfs)) {
//				if (StringUtils.isEqual("0", cxfs)) {
//					resList = dao.kxwhzypfj(model);
//				} else if (StringUtils.isEqual("1", cxfs)) {
//					resList = dao.kxwhwhpfj(model);
//				}else if (StringUtils.isEqual("2", cxfs)) {
//					resList = dao.kxwhkcpfj(model);
//				}
//			}
		} else if (StringUtils.isEqual("2", szlx)) {
			resList = dao.sxllfQry(model);
		} else {
			resList = dao.sjlxcxfQryRes(model);
		}
		return resList;
	}
	
	/**
	 * ˼�����ʷ�����ɾ��
	 * 
	 * @param keys
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String sxzzddszfDel(String[] keys, HttpServletRequest request)
			throws Exception {
		dao = new SzcpDAO();
		return dao.sxzzddszfDel(keys, request);
	}
	
	/**
	 * ˼�����ʷ��޸���ʾ
	 * 
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> viewSxzzf(String pkValue) throws Exception {
		dao = new SzcpDAO();
		return dao.viewSxzzf(pkValue);
	}
	
	/**
	 * ˼�����ʷ��޸ı�����
	 * @param pkValue
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean sxzzfModisave(String pkValue, SzcpModel model,
			HttpServletRequest request) throws Exception {
		dao = new SzcpDAO();
		return dao.sxzzfModisave(pkValue, model, request);
	}
	
	/**
	 * ����˼�����ʷ�
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public String saveSjlxf(SzcpModel model) throws Exception {
		dao = new SzcpDAO();
		return dao.saveSjlxf(model);
	}
	
	/**
	 * ˼�����ʷ��޸���ʾ
	 * 
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> viewSjlxf(String pkValue) throws Exception {
		dao = new SzcpDAO();
		return dao.viewSjlxf(pkValue);
	}
	
	/**
	 * ˼�����ʷ��޸ı�����
	 * @param pkValue
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean sjlxfModisave(String pkValue, SzcpModel model,
			HttpServletRequest request) throws Exception {
		dao = new SzcpDAO();
		return dao.sjlxfModisave(pkValue, model, request);
	}
	
	/**
	 * ˼�����ʷ�����ɾ��
	 * 
	 * @param keys
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String sjlxcxszbDel(String[] keys, HttpServletRequest request)
			throws Exception {
		dao = new SzcpDAO();
		return dao.sjlxcxszbDel(keys, request);
	}
	
	/**
	 * ��ѧ�Ļ����ʷֱ���
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public String saveKxwhf(SzcpModel model) throws Exception {
		dao = new SzcpDAO();
		return dao.saveKxwhf(model);
	}
	
	/**
	 * ��ѧ�Ļ����޸�ҳ��
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> kxwhfModi(String pkValue) throws Exception {
		dao = new  SzcpDAO();
		return dao.kxwhfModi(pkValue);
	}
	
	/**
	 * ��ѧ�Ļ����޸ı���
	 * @param pkValue
	 * @param request
	 * @param xn
	 * @param kcmc
	 * @param cj
	 * @param kclx
	 * @return
	 * @throws Exception
	 */
	public boolean kxwhfModiSave(String pkValue, HttpServletRequest request,
			String xn, String kcmc, String cj, String kclx, String df, String sfbxk) throws Exception {
		dao = new SzcpDAO();
		return dao.kxwhfModiSave(pkValue, request, xn, kcmc, cj, kclx,df, sfbxk);
	}
	
	/**
	 * �Ļ���ɾ��
	 * @param keys
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String kxwhfDel(String[] keys, HttpServletRequest request) throws Exception {
		dao = new SzcpDAO();
		return dao.kxwhfDel(keys, request);
	}
	
	/**
	 * ��������������
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public String saveSxllf(SzcpModel model) throws Exception {
		dao = new SzcpDAO();
		return dao.saveSxllf(model);
	}
	
	public HashMap<String, String> sxllfView(String pkValue) throws Exception {
		dao = new SzcpDAO();
		return dao.sxllfView(pkValue);
	}
	
	public boolean sxllfModisave(String pkValue, String xn, String fs, String lr, String zt, String dfxm) throws Exception {
		dao = new SzcpDAO();
		return dao.sxllfModisave(pkValue, xn, fs, lr, zt, dfxm);
	}
	
	public String sxllfDel(String[] keys, HttpServletRequest request) throws Exception {
		dao = new SzcpDAO();
		return dao.sxllfDel(keys, request);
	}

	public HashMap<String, String> getSqxsxx(String pkValue) {
		dao = new SzcpDAO();
		return dao.getSqxsxx(pkValue);
	}

	public ArrayList<HashMap<String, String>> getSqxsxxs(String pkValue) throws Exception {
		dao = new SzcpDAO();
		return dao.getSqxsxxs(pkValue);
	}

	public HashMap<String, String> getKxwhszf(String pkValue) {
		dao = new SzcpDAO();
		return dao.getKxwhszf(pkValue);
	}

	public ArrayList<HashMap<String, String>> getKxwhszfs(String pkValue) {
		dao = new SzcpDAO();
		return dao.getKxwhszfs(pkValue);
	}

	public HashMap<String, String> getSjlxcxf(String pkValue) {
		dao = new SzcpDAO();
		return dao.getSjlxcxf(pkValue);
	}

	public ArrayList<HashMap<String, String>> getSjlxcxfs(String pkValue) {
		dao = new SzcpDAO();
		return dao.getSjlxcxfs(pkValue);
	}

	public HashMap<String, String> getSxllf(String pkValue) {
		dao = new SzcpDAO();
		return dao.getSxllf(pkValue);
	}

	public ArrayList<HashMap<String, String>> getSxllfs(String pkValue) {
		dao = new SzcpDAO();
		return dao.getSxllfs(pkValue);
	}
	
	public boolean updataSxzzf(SzcpModel model) throws Exception{
		dao = new SzcpDAO();
		return dao.updataSxzzf(model);
	}

	public boolean updataSjlx(SzcpModel model) throws SQLException {
		dao = new SzcpDAO();
		return dao.updataSjlx(model);
		
	}

	public boolean updataSxllfsz(SzcpModel model) throws SQLException {
		dao = new SzcpDAO();
		return dao.updataSxllfsz(model);
	}

	public boolean updataKxwhszf(SzcpModel model) throws SQLException {
		dao = new SzcpDAO();
		return dao.updataKxwhszf(model);
	}
}
