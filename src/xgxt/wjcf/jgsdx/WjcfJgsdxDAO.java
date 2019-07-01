
package xgxt.wjcf.jgsdx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.utils.String.StringUtils;
import xgxt.wjcf.jgsdx.CxcfSqSaveModel;

/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: ����ɽ��ѧΥ�ʹ���DAO</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: ����</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-06-24</p>
 */
public class WjcfJgsdxDAO {

	DAO dao = DAO.getInstance(); 
	ArrayList<String> values = new ArrayList<String>();// ��ѯ����ֵ
	
	/**
	 * ͨ��ѧ�Ż�ȡѧ�������Ϣ
	 * getStuInfo ---- ��ȡѧ����Ϣ
	 * @param xh
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getStuInfo(String xh) throws Exception {
		HashMap<String, String> userMap = new HashMap<String, String>();
		String sql = "select xh,xm,xb,nj,xymc,bjmc,zymc from view_xsjbxx where xh=? and rownum=1";
		userMap = dao.getMapNotOut(sql, new String[]{xh});
		return userMap;
	}
	
	/**
	 * ����ǰ���ѧ������ʱ���Ƿ���һ��
	 * @param cfsj
	 * @return
	 * @throws Exception
	 */
	public boolean chkStuTj(String cfsj) throws Exception {
		boolean bFlag = false;
		String sql = "select round(months_between(to_date(to_char(sysdate,'yyyy-mm-dd'),'yyyy-mm-dd'),to_date('" + cfsj + "','yyyy-mm-dd'))) hg from dual";
		String[] flag = dao.getOneRs(sql, new String[]{}, new String[]{"hg"});
		if ((flag.length > 0 && (!StringUtils.isNull(flag[0])))) {
			int iTj = Integer.parseInt(flag[0]);//Υ�ʹ���ʱ�䳤��
			if (iTj >= 12) {
				bFlag = true;
			}
		}
		return bFlag;
	}
	
	/**
	 * ���泷��������Ϣ
	 * @param cxcfSaveModel
	 * @return
	 * @throws Exception
	 */
	public boolean saveCxcfSqlInfo(CxcfSqSaveModel cxcfSaveModel, HttpServletRequest request) throws Exception {
		boolean bFlag = false;
		String xh = cxcfSaveModel.getXh();
		String xn = cxcfSaveModel.getXn();
		String xq = cxcfSaveModel.getXq();
		String cfwh = DealString.toGBK(cxcfSaveModel.getCfwh());
		String cfsj = cxcfSaveModel.getCfsj();
		String cflbmc = DealString.toGBK(cxcfSaveModel.getCflbmc());
		String cfyymc = DealString.toGBK(cxcfSaveModel.getCfyymc());
		String bz = DealString.toGBK(cxcfSaveModel.getBz());
		String cxsj = cxcfSaveModel.getCxsj();
		bFlag = StandardOperation
				.insert("wjcf_cxcfb", new String[] { "xh", "xn", "xq", "cfwh",
						"cfsj", "cflbmc", "cfyymc", "bz", "cxsqsj" },
						new String[] { xh, xn, xq, cfwh, cfsj, cflbmc, cfyymc,
								bz, cxsj }, request);
		return bFlag;
	}
	
	/**
	 * �������ֲ�ѯ��ͷ
	 * getCxcfSearchTitle ---- �������ֲ�ѯ��ͷ 
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getCxcfSearchTitle() throws Exception {
		List<HashMap<String, String>> topList = new ArrayList<HashMap<String, String>>();
		String[] en = new String[]{"xh||cfwh||cfsj","xh","xm","xn","xq","cflbmc","cfyymc","cfwh","cfsj","cxwh","cxsj"};
		String[] cn = new String[]{"����","ѧ��","����","ѧ��","ѧ��","�������","����ԭ��","�����ĺ�","����ʱ��","����ĺ�","���ʱ��"};
		for (int i = 0; i < en.length; i++) {
			HashMap<String, String> tempMap = new HashMap<String, String>();
			tempMap.put("en", en[i]);
			tempMap.put("cn", cn[i]);
			topList.add(tempMap);
		}//end for
		return topList;
	}
	
	/**
	 * �������ֲ�ѯ���
	 * getCxcfSearchResult ---- �������ֲ�ѯ���
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getCxcfSearchResult(CxcfQryModel cxcfModel) throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		StringBuffer whereSql = getWhereSql(cxcfModel);
		String[] opList = new String[]{"xh||cfwh||cfsj","xh","xm","xn","xq","cflbmc","cfyymc","cfwh","cfsj","cxwh","cxsj"};
		String sql = "select xh||cfwh||cfsj,xh,xm,xn,xq,cflbmc,cfyymc,cfwh,cfsj,cxwh,cxsj from view_wjcf_cxcf where 1=1";
		resList = dao.rsToVator(sql + whereSql.toString(), values != null ? values
				.toArray(new String[0]) : new String[] {}, opList);
		return resList;
	}
	
	/**
	 * ���÷�������ȡ��ѯ����
	 * @param cxcfModel
	 * @return
	 * @throws Exception
	 */
	public StringBuffer getWhereSql(CxcfQryModel cxcfModel) throws Exception {
		StringBuffer whereSql = new StringBuffer();
		String xn = cxcfModel.getXn();
		String xh = DealString.toGBK(cxcfModel.getXh());
		String xm = DealString.toGBK(cxcfModel.getXm());
		String xydm = cxcfModel.getXydm();
		String zydm = cxcfModel.getZydm();
		String bjdm = cxcfModel.getBjdm();
		String nj = cxcfModel.getNj();
		String xq = cxcfModel.getXq();
		if (!StringUtils.isNull(xn)) {
			whereSql.append(" and xn = ?");
			values.add(xn);
		}//end if
		if (!StringUtils.isNull(xh)) {
			whereSql.append(" and xh = ?");
			values.add(xh);
		}//end if
		if (!StringUtils.isNull(xm)) {
			whereSql.append(" and xm like ?");
			values.add(xm);
		}//end if
		if (!StringUtils.isNull(xydm)) {
			whereSql.append(" and xydm = ?");
			values.add(xydm);
		}//end if
		if (!StringUtils.isNull(zydm)) {
			whereSql.append(" and zydm = ?");
			values.add(zydm);
		}//end if
		if (!StringUtils.isNull(bjdm)) {
			whereSql.append(" and bjdm = ?");
			values.add(bjdm);
		}//end if
		if (!StringUtils.isNull(nj)) {
			whereSql.append(" and nj = ?");
			values.add(nj);
		}//end if
		if (!StringUtils.isNull(xq)) {
			whereSql.append(" and xq = ?");
			values.add(xq);
		}//end if
		return whereSql;
	}
	
	/**
	 * ͨ��������ȡѧ�������Ϣ
	 * getStuInfo1 ---- ͨ��������ȡѧ�������Ϣ 
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getStuInfo1(String pkValue) throws Exception {
		HashMap<String, String> stuMap = new HashMap<String, String>();
		String sql = "select xh,xm,xymc,bjmc,cfyymc,cfsj from view_wjcf where xh||cfwh||cfsj=?";
		stuMap = dao.getMapNotOut(sql, new String[]{pkValue});
		return stuMap;
	}
	
	/**
	 * ������������ɾ��
	 * wjcfCxcfPlDel ---- Υ�ʹ��ֳ�����������ɾ�� 
	 * @param keys
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String wjcfCxcfPlDel(String[] keys, HttpServletRequest request) throws Exception {
		StringBuffer pksql1 = new StringBuffer();
		String[] pksql = new String[] {};
		String sql = "";
		for (int i = 0; i < keys.length; i++) {
			String whichxh = DealString.toGBK(keys[i]);// �õ�����
			sql = "delete from wjcf_cxcfb where xh||cfwh||cfsj = '" + whichxh
					+ "'";
			// ��������ϳ�sql���
			pksql1.append(sql);
			pksql1.append("!!#!!");
		}// end for
		// sql����ֳ�����
		pksql = pksql1.toString().split("!!#!!");
		int[] judge2 = dao.runBatch(pksql);
		String whichpk = "";
		// �����һ��ɾ��ʧ��
		for (int i = 0; i < judge2.length; i++) {
			if (judge2[i] > 0) {
				whichpk = whichpk + " ��" + String.valueOf(i) + "������ɾ��ʧ��;\n";
			}// end if
		}// end for
		return whichpk;
	}
	
	/**
	 * ��������������ѯ��ͷ
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getCxcfSpTitle() throws Exception {
		List<HashMap<String, String>> topList = new ArrayList<HashMap<String,String>>();
		String[] en = new String[]{"xh||cfwh||cfsj","xh","xm","xn","xq","bjmc","cflbmc","cfyymc","cfwh","cfsj","cxsqsj"};
		String[] cn = new String[]{"����","ѧ��","����","ѧ��","ѧ��","�༶����","�������","����ԭ��","�����ĺ�","����ʱ��","��������ʱ��"};
		for (int i = 0; i < en.length; i++) {
			HashMap<String, String> tempMap = new HashMap<String, String>();
			tempMap.put("en", en[i]);
			tempMap.put("cn", cn[i]);
			topList.add(tempMap);
		}//end for
		return topList;
	}
	
	/**
	 * ��������������ѯ���
	 * @param cxcfModel
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getCxcfSpResult(CxcfQryModel cxcfModel) throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		StringBuffer whereSql = getWhereSql(cxcfModel);
		String[] opList = new String[]{"xh||cfwh||cfsj","xh","xm","xn","xq","bjmc","cflbmc","cfyymc","cfwh","cfsj","cxsqsj"};
		String sql = "select xh||cfwh||cfsj,xh,xm,xn,xq,bjmc,cflbmc,cfyymc,cfwh,cfsj,cxsqsj from view_wjcf_cxcf where 1=1";
		resList = dao.rsToVator(sql + whereSql.toString(), values != null ? values
				.toArray(new String[0]) : new String[] {}, opList);
		return resList;
	}
	
	/**
	 * ͨ��������ȡ�������������Ϣ
	 * getCxcfInfoByPk ---- ͨ��������ȡ�������������Ϣ 
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getCxcfInfoByPk(String pkValue) throws Exception {
		HashMap<String, String> resMap = new HashMap<String, String>();
		String sql = "select a.xh||a.cfwh||a.cfsj,a.xh,a.nj,a.xymc,a.zymc,a.xm,a.xn,a.xq,a.bjmc,a.cflbmc,a.cfyymc,a.cxsqsj,a.cfwh,a.cfsj,a.cxsj,a.bz,a.cxwh,a.cxsj,a.spzt,b.nd,b.xn,b.xb from view_wjcf_cxcf a left join view_wjcf b on a.xh=b.xh and a.cfwh=b.cfwh and a.cfsj=b.cfsj where a.xh||a.cfwh||a.cfsj=?";
		resMap = dao.getMapNotOut(sql, new String[]{pkValue});
		return resMap;
	}
	
	/**
	 * ��������б�
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getChList(int type) throws Exception {
		return dao.getChkList(type);
	}
	
	/**
	 * ������������
	 * cxcfSp ---- ������������ 
	 * @param cxcfsqModel
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean cxcfSp(CxcfSpSaveModel cxcfsqModel, HttpServletRequest request) throws Exception {
		boolean bFlag =false;
		String pkValue = cxcfsqModel.getPkValue();
		String spzt = DealString.toGBK(cxcfsqModel.getSpzt());
		spzt = !StringUtils.isNull(spzt) ? spzt.trim() : "";
		String cxwh = DealString.toGBK(cxcfsqModel.getCxwh());
		String cxsj = cxcfsqModel.getCxsj();
		bFlag = StandardOperation.update("wjcf_cxcfb", new String[]{"spzt","cxwh","cxsj"}, new String[]{spzt,cxwh,cxsj}, "xh||cfwh||cfsj", pkValue, request);
		if (bFlag) {//���³ɹ������WJCF������Ľ���ĺź�ʱ��
			StandardOperation.update("wjcfb", new String[]{"cxwh", "cxsj"}, new String[]{cxwh,cxsj}, "xh||cfwh||cfsj", pkValue, request);
		}
		return bFlag;
	}
	
	/**
	 * ѧ��Υ�ʹ��������Ϣ
	 * getStuWjcfinfo ---- ѧ��Υ�ʹ��������Ϣ 
	 * @param xh
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getStuWjcfinfo(String xh) throws Exception {
		String sql = "select xh||cfwh||cfsj,xh,xm,xb,bjmc,cflbmc,cfyymc,cfsj,cfwh,spzt,cxwh,cxsj from view_wjcf_cxcf where xh=?";
		List<String[]> stuList = new ArrayList<String[]>();
		stuList = dao.rsToVator(sql, new String[]{xh}, new String[]{"xh||cfwh||cfsj","xh","xm","xb","bjmc","cflbmc","cfyymc","cfsj","cfwh","spzt","cxwh","cxsj"});
		return stuList;
	}
	
	/**
	 * ѧ��Υ�ʹ��������Ϣ��ͷ
	 * @param xh
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getStuWjcfTit(String xh) throws Exception {
		List<HashMap<String, String>> topList = new ArrayList<HashMap<String,String>>();
		String[] en = new String[]{"xh||cfwh||cfsj","xh","xm","xb","bjmc","cflbmc","cfyymc","cfsj","cfwh","spzt","cxwh","cxsj"};
		String[] cn = new String[]{"����","ѧ��","����","�Ա�","�༶����","�������","����ԭ��","����ʱ��","�����ĺ�","ѧУ����","����ĺ�","���ʱ��"};
		for (int i = 0; i < en.length; i++) {
			HashMap<String, String> tempMap = new HashMap<String, String>();
			tempMap.put("en", en[i]);
			tempMap.put("cn", cn[i]);
			topList.add(tempMap);
		}//end for
		return topList;
	}
}
