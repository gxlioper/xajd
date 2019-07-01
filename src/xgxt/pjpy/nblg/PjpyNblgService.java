
package xgxt.pjpy.nblg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import common.Globals;

import xgxt.DAO.DAO;
import xgxt.utils.String.StringUtils;

/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: ������ѧԺ��������Service</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: ����</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-07-30</p>
 */
public class PjpyNblgService {

	PjpyNblgDAO dao = null;
	PjpyNblgRychDAO mydao = PjpyNblgRychDAO.getInstance();
	/**
	 * ��ȡ���������б�
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getDjksList() throws Exception {
		dao = new PjpyNblgDAO();
		return dao.getDjksList();
	}
	
	/**
	 * �ȼ����Գɼ���ѯ���
	 * @param djkscjModel
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getDjksCjResult(DjkscjModel djkscjModel, PjpyNblgActionForm dataSearchForm) throws Exception {
		dao = new PjpyNblgDAO();
		String cjlx = djkscjModel.getCjlx();
		if (!StringUtils.isNull(cjlx) && StringUtils.isEqual(cjlx, "cjb")) {
			return dao.getCjResult(djkscjModel, dataSearchForm);
		}
		if (!StringUtils.isNull(cjlx) && StringUtils.isEqual(cjlx, "djksb")) {
			return dao.getDjksCjResult(djkscjModel, dataSearchForm);
		}
		return null;
	}
	
	/**
	 * �ȼ����Գɼ���ѯ��ͷ
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getDjksCjTitle(String cjlx) throws Exception {
		dao = new PjpyNblgDAO();
		if (!StringUtils.isNull(cjlx) && StringUtils.isEqual(cjlx, "cjb")) {
			return dao.getCjbTitle();
		}
		if (!StringUtils.isNull(cjlx) && StringUtils.isEqual(cjlx, "djksb")) {
			return dao.getDjksCjTitle();
		}
		return null;
	}
	
	public int getCount(String cjlx, DjkscjModel djkscjModel) throws Exception {
		dao = new PjpyNblgDAO();
		if (!StringUtils.isNull(cjlx) && StringUtils.isEqual(cjlx, "cjb")) {
			return dao.getCjResultNum(djkscjModel);
		}
		if (!StringUtils.isNull(cjlx) && StringUtils.isEqual(cjlx, "djksb")) {
			return dao.getDjksCjResultNum(djkscjModel);
		}
		return 0;
	}
	
	/**
	 * �����ͬ���ɼ�
	 * @param xn
	 * @param xq
	 * @param djksmc
	 * @throws Exception
	 */
	public boolean djksCjTb(String xn, String xq, String cjlx, String xxdm) throws Exception {
		dao = new PjpyNblgDAO();
		if (!StringUtils.isNull(cjlx) && StringUtils.isEqual(cjlx, "cjb")) {
			if (Globals.XXDM_NBLGXY.equalsIgnoreCase(xxdm)) {
				return dao.cjbTb(xn, xq);
			} else {
				return dao.cjbTb1(xn, xq);
			}
		}
		if (!StringUtils.isNull(cjlx) && StringUtils.isEqual(cjlx, "djksb")) {
			if (Globals.XXDM_NBLGXY.equalsIgnoreCase(xxdm)) {
				return dao.djksCjTb(xn, xq);
			} else {
				return dao.djksCjTb1(xn, xq);
			}
		}
		return false;
	}
	
	public HashMap<String, String> getStuInfo(String xh) throws Exception {
		dao = new PjpyNblgDAO();
		return dao.getStuInfo(xh);
	}
	
	public List<HashMap<String, String>> tjTitle() throws Exception {
		dao = new PjpyNblgDAO();
		return dao.tjTitle();
	}
	
	public List<String[]> tjResult(String xn, String jxjdm) throws Exception {
		dao = new PjpyNblgDAO();
		return dao.tjResult(xn, jxjdm);
	}
	
	public boolean tjSave(String xn, String jxjdm, String tj, String bl) throws Exception {
		dao = new PjpyNblgDAO();
		return dao.tjSave(xn, jxjdm, tj, bl);
	}
	
	public boolean tjDel(String[] keys, String tname) throws Exception {
		dao = new PjpyNblgDAO();
		return dao.tjDel(keys, tname);
	}
	
	public List<HashMap<String, String>> getPageTitle() throws Exception {
		String[] enList = new String[]{"nbzy_tjszb", "nblg_jxjyytjszb"};
		String[] cnList = new String[]{"������������", "Ӣ����������"};
		DAO dao = DAO.getInstance();
		return dao.arrayToList(enList, cnList);
	}
	
	public List<HashMap<String, String>> getTjList(String tname) throws Exception {
		DAO dao = DAO.getInstance();
		String[] enList = new String[]{};
		String[] cnList = new String[]{};
		if ("nbzy_tjszb".equalsIgnoreCase(tname)) {
			enList = new String[]{"jqf", "zhcp"};
			cnList = new String[]{"ѧϰ��Ȩƽ����רҵ����", "�ۺ����ʲ����ְ༶����"};
		} else {
			enList = new String[]{"cet4", "cet6", "pcet4"};
			cnList = new String[]{"��ѧӢ���ļ�", "��ѧӢ������", "Ӣ��רҵ�ļ�"};
		}
		return dao.arrayToList(enList, cnList);
	}
	
	public List<HashMap<String, String>> yytjTitle() throws Exception {
		dao = new PjpyNblgDAO();
		return dao.yytjTitle();
	}
	
	public List<String[]> yytjResult(String xn, String jxjdm) throws Exception {
		dao = new PjpyNblgDAO();
		return dao.yytjResult(xn, jxjdm);
	}
	
	public boolean saveYytj(String xn, String jxjdm, String tj, String fs)
	throws Exception {
		dao = new PjpyNblgDAO();
		return dao.saveYytj(xn, jxjdm, tj, fs);
	}
	
	/**
	 * ��ѧ����˱�ͷ
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getjxjshTitle(String userType) throws Exception {
		dao = new PjpyNblgDAO();
		return dao.getjxjshTitle(userType);
	}
	
	/**
	 * ѧԺ��ѧ�����ʱ�Ĳ�ѯ���
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getjxjshResult(PjpyNblgModel model, String userType)
			throws Exception {
		dao = new PjpyNblgDAO();
		if (StringUtils.isNull(model.getNj())) {
			String nj = mydao.getNjBybjdm(model.getBjdm());
			model.setNj(nj);
		}
		if ("xy".equalsIgnoreCase(userType)) {
			return dao.xyjxjshResult(model);
		} else {
			return dao.xxjxjshResult(model);
		}
	}
	
	public String jxjshResult(String[] keys, String res, PjpyNblgModel model, String userType) throws Exception {
		dao = new PjpyNblgDAO();
		if ("xy".equalsIgnoreCase(userType)) {
			return dao.xyjxjshResult(keys, res, model);
		} else {
			return dao.jxjshByxx(keys, res, model);
		}
	}
	
	/**
	 * ������ͷ
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getTjList() throws Exception {
		String[] enList = new String[]{"dycj", "tycj", "jxj"};
		String[] cnList = new String[]{"���������༶����", "�����ɼ�", "��������ѧ��"};
		DAO dao = DAO.getInstance();
		return dao.arrayToList(enList, cnList);
	}
	
	/**
	 * ������ͷ
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> tjszTitle() throws Exception {
		return mydao.tjTitle();
	}
	/**
	 * �������
	 * @return
	 * @throws Exception
	 */
	public List<String[]> tjszResult(String xn, String rychdm) throws Exception {
		return mydao.tjResult(xn, rychdm);
	}
	
	/**
	 * ��������
	 * @param model
	 * @param request
	 * @return �ɹ�Ϊtrue,ʧ��Ϊfalse
	 * @throws Exception
	 */
	public boolean saveTj(PjpyNblgModel model, HttpServletRequest request) throws Exception {
		return mydao.saveTj(model, request);
	}
	
	/**
	 * ����ɾ��
	 * @param keys
	 * @return
	 * @throws Exception
	 */
	public String delTj(String[] keys) throws Exception {
		return mydao.delTj(keys);
	}
	
	public List<HashMap<String, String>> rychqryTit(String userType)
			throws Exception {
		return mydao.rychqryTit(userType);
	}

	public List<String[]> rychqryRes(PjpyNblgModel model, String userType)
			throws Exception {
		if (StringUtils.isNull(model.getNj())) {
			String nj = mydao.getNjBybjdm(model.getBjdm());
			model.setNj(nj);
		}
		if ("xy".equalsIgnoreCase(userType)) {
			return mydao.xyrychqryRes(model);
		} else {
			return mydao.xxrychqryRes(model);
		}
	}
	
	/**
	 * �����ƺ���˽��
	 * 
	 * @param userType
	 * @param keys
	 * @param rychdm
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public String rychshRes(String userType, String[] keys, String rychdm,
			String type) throws Exception {
		if ("xy".equalsIgnoreCase(userType)) {
			return mydao.xyrychshRes(keys, rychdm, type);
		} else {
			return mydao.xxrychshRes(keys, rychdm, type);
		}
	}
	
	/**
	 * ���������ƺŴ��� ��ȡ�������ƺ������������
	 * 
	 * @param rychdm
	 * @return
	 * @throws Exception
	 */
	public int rychXzrs(String rychdm) throws Exception {
		return mydao.rychXzrs(rychdm);
	}
	
	/**
	 * ͨ���༶��ѧ����������
	 * 
	 * @param jxjdm
	 * @param xydm
	 * @param bjdm
	 * @param nj
	 * @return
	 * @throws Exception
	 */
	public String getJxjxzrs(String jxjdm, String bjdm) throws Exception {
		dao = new PjpyNblgDAO();
		return dao.getJxjxzrs(jxjdm, bjdm);
	}
	
	/**
	 * �ɼ����������б�
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getCjtjszList() throws Exception {
		return mydao.getCjtjszList();
	}
	
	/**
	 * ����ɼ�����
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean saveCjtj(PjpyNblgModel model, HttpServletRequest request)
			throws Exception {
		return mydao.saveCjtj(model, request);
	}
	
	/**
	 * ��ʾ�ɼ�������Ϣ
	 * 
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> viewCjtj(String pkValue) throws Exception {
		return mydao.viewCjtj(pkValue);
	}
	
	/**
	 * �޸ĳɼ�����
	 * @param model
	 * @param pkValue
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean updateCjtj(PjpyNblgModel model, String pkValue,
			HttpServletRequest request) throws Exception {
		return mydao.updateCjtj(model, pkValue, request);
	}
	
	/**
	 * ɾ���ɼ�������Ϣ
	 * 
	 * @param keys
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String deleteCjtj(String[] keys, HttpServletRequest request)
			throws Exception {
		return mydao.deleteCjtj(keys, request);
	}
	
	/**
	 * ��ȡ�ɼ������б�
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getCjtjList() throws Exception {
		String[] enList = new String[]{"kcxz", "kcmc"};
		String[] cnList = new String[]{"�γ�����", "�γ�����"};
		List<HashMap<String, String>> rs = new ArrayList<HashMap<String,String>>();
		for (int i=0;i<enList.length;i++) {
			HashMap<String, String> tmpMap = new HashMap<String, String>();
			tmpMap.put("tjxzdm", enList[i]);
			tmpMap.put("tjxzmc", cnList[i]);
			rs.add(tmpMap);
		}
		return rs;
	}
}
