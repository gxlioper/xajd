
package xgxt.wjcf.gdby;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.utils.String.StringUtils;

/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: �㶫����ѧԺΥ�ʹ���DAO</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: ����</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-07-15</p>
 */
public class WjcfGdbyDAO {

	DAO dao = DAO.getInstance();//���ݲ���DAO
	ArrayList<String> values = new ArrayList<String>();
	
	public StringBuffer getWhereSql(WjcfShModel wjcfModel) throws Exception {
		StringBuffer whereSql = new StringBuffer();
		String xn = wjcfModel.getXn();
		String nd = wjcfModel.getNd();
		String nj = wjcfModel.getNj();
		String xydm = wjcfModel.getXydm();
		String zydm = wjcfModel.getZydm();
		String bjdm = wjcfModel.getBjdm();
		String cflb = wjcfModel.getCflb();
		if (!StringUtils.isNull(xn)) {
			whereSql.append(" and xn = ?");
			values.add(xn);
		}
		if (!StringUtils.isNull(nd)) {
			whereSql.append(" and nd = ?");
			values.add(nd);
		}
		if (!StringUtils.isNull(nj)) {
			whereSql.append(" and nj = ?");
			values.add(nj);
		}
		if (!StringUtils.isNull(xydm)) {
			whereSql.append(" and xydm = ?");
			values.add(xydm);
		}
		if (!StringUtils.isNull(zydm)) {
			whereSql.append(" and zydm = ?");
			values.add(zydm);
		}
		if (!StringUtils.isNull(bjdm)) {
			whereSql.append(" and bjdm = ?");
			values.add(bjdm);
		}
		if (!StringUtils.isNull(cflb)) {
			whereSql.append(" and cflb = ?");
			values.add(cflb);
		}
		return whereSql;
	}
	
	/**
	 * ��ȡΥ�ʹ��ֲ�ѯ��ͷ��ѧУ��
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getWjcfshTitleByXx() throws Exception {
		List<HashMap<String, String>> topList = new ArrayList<HashMap<String,String>>();
		String[] enList = new String[]{"pk", "bgcolor", "rownum", "xn", "nd", "xh", "xm", "bjmc", "cflbmc", "cfyymc", "sbsj", "sh"};
		String[] cnList = new String[]{"pk", "bgcolor", "�к�", "ѧ��", "���", "ѧ��", "����", "�༶����", "�������", "����ԭ��", "�걨ʱ��", "ѧУ���"};
		for (int i = 0; i < enList.length; i++) {
			HashMap<String, String> tmp = new HashMap<String, String>();
			tmp.put("en", enList[i]);
			tmp.put("cn", cnList[i]);
			topList.add(tmp);
		}
		return topList;
	}
	
	/**
	 * ��ȡΥ�ʹ��ֲ�ѯ��ͷ��ѧԺ��
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getWjcfshTitleByXy() throws Exception {
		List<HashMap<String, String>> topList = new ArrayList<HashMap<String,String>>();
		String[] enList = new String[]{"pk", "bgcolor", "rownum", "xn", "nd", "xh", "xm", "bjmc", "cflbmc", "cfyymc", "sbsj", "sh"};
		String[] cnList = new String[]{"pk", "bgcolor", "�к�", "ѧ��", "���", "ѧ��", "����", "�༶����", "�������", "����ԭ��", "�걨ʱ��", Base.YXPZXY_KEY+"���"};
		for (int i = 0; i < enList.length; i++) {
			HashMap<String, String> tmp = new HashMap<String, String>();
			tmp.put("en", enList[i]);
			tmp.put("cn", cnList[i]);
			topList.add(tmp);
		}
		return topList;
	}
	
	/**
	 * ��ȡΥ�ʹ��ֲ�ѯ��ͷ��ѧ������
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getWjcfshTitleByXsc() throws Exception {
		List<HashMap<String, String>> topList = new ArrayList<HashMap<String,String>>();
		String[] enList = new String[]{"pk", "bgcolor", "rownum", "xn", "nd", "xh", "xm", "bjmc", "cflbmc", "cfyymc", "sbsj", "sh"};
		String[] cnList = new String[]{"pk", "bgcolor", "�к�", "ѧ��", "���", "ѧ��", "����", "�༶����", "�������", "����ԭ��", "�걨ʱ��", "ѧ�������"};
		for (int i = 0; i < enList.length; i++) {
			HashMap<String, String> tmp = new HashMap<String, String>();
			tmp.put("en", enList[i]);
			tmp.put("cn", cnList[i]);
			topList.add(tmp);
		}
		return topList;
	}
	
	/**
	 * ��ȡΥ�ʹ��ֲ�ѯ���
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getWjcfshResultByXy(WjcfShModel wjcfModel) throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		String sql = "select xh||cfsj||cfwh pk,(case nvl(xysh,'δ���') when 'ͨ��' then '#FFFFFF' when 'δ���' then '#DDDDDD' else '#BBBBBB' end) bgcolor,rownum,xn,nd,xh,xm,bjmc,cflbmc,cfyymc,sbsj,xysh sh from view_gdby_wjcf where sbsj is not null ";
		String[] opList = new String[]{"pk", "bgcolor", "rownum", "xn", "nd", "xh", "xm", "bjmc", "cflbmc", "cfyymc", "sbsj", "sh"};
		StringBuffer whereSql = getWhereSql(wjcfModel);
		resList = dao.rsToVator(sql + whereSql, values != null ? values.toArray(new String[0]) : new String[]{}, opList);
		return resList;
	}
	
	/**
	 * ��ȡΥ�ʹ��ֲ�ѯ���
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getWjcfshResultByXsc(WjcfShModel wjcfModel) throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		String sql = "select xh||cfsj||cfwh pk,(case nvl(xxsh,'δ���') when 'ͨ��' then '#FFFFFF' when 'δ���' then '#DDDDDD' else '#BBBBBB' end) bgcolor,rownum,xn,nd,xh,xm,bjmc,cflbmc,cfyymc,sbsj,xxsh sh from view_gdby_wjcf where sbsj is not null and xysh='ͨ��' ";
		String[] opList = new String[]{"pk", "bgcolor", "rownum", "xn", "nd", "xh", "xm", "bjmc", "cflbmc", "cfyymc", "sbsj", "sh"};
		StringBuffer whereSql = getWhereSql(wjcfModel);
		resList = dao.rsToVator(sql + whereSql, values != null ? values.toArray(new String[0]) : new String[]{}, opList);
		return resList;
	}
	
	/**
	 * ��ȡΥ�ʹ��ֲ�ѯ���
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getWjcfshResultByXnd(WjcfShModel wjcfModel) throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		String sql = "select xh||cfsj||cfwh pk,(case nvl(xndsh,'δ���') when 'ͨ��' then '#FFFFFF' when 'δ���' then '#DDDDDD' else '#BBBBBB' end) bgcolor,rownum,xn,nd,xh,xm,bjmc,cflbmc,cfyymc,sbsj,xndsh sh from view_gdby_wjcf where sbsj is not null and xxsh='ͨ��' and xysh='ͨ��' ";
		String[] opList = new String[]{"pk", "bgcolor", "rownum", "xn", "nd", "xh", "xm", "bjmc", "cflbmc", "cfyymc", "sbsj", "sh"};
		StringBuffer whereSql = getWhereSql(wjcfModel);
		resList = dao.rsToVator(sql + whereSql, values != null ? values.toArray(new String[0]) : new String[]{}, opList);
		return resList;
	}
	
	/**
	 * ��ȡ��������б�
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getCflbList() throws Exception {
		return dao.getList("select cflbdm,cflbmc from cflbdmb", new String[]{}, new String[]{"cflbdm", "cflbmc"});
	}
	
	/**
	 * ��ȡ����ԭ���б�
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getCfyyList() throws Exception {
		return dao.getList("select cfyydm,cfyymc from cfyydmb", new String[]{}, new String[]{"cfyydm", "cfyymc"});
	}
	
	/**
	 * �ж��û��������Ƿ���У�쵼
	 * @param userName
	 * @return
	 * @throws Exception
	 */
	public boolean getUserZdm(String userName) throws Exception {
		boolean bFlag = false;
		String sql = "select zmc from yhzb where yhm = ? and rownum=1";
		String[] userZdm = dao.getOneRs(sql, new String[]{userName}, new String[]{"zmc"});
		if (userZdm != null && userZdm.length > 0) {
			if (StringUtils.isEqual(userZdm[0], "У�쵼")) {
				bFlag = true;
			}
		}
		return bFlag;
	}
	
	/**
	 * ����û��Ƿ����
	 * @param userName
	 * @return
	 * @throws Exception
	 */
	public boolean chkUserExists(String userName) throws Exception {
		boolean bFlag = false;
		String sql = "select zdm from yhb where yhm = ? and rownum=1";
		//��ȡ�û���
		String[] user = dao.getOneRs(sql, new String[]{userName}, new String[]{"zdm"});
		if (user != null && user.length > 0) {
			sql = "select zmc from yhzb where zdm = ? and rownum=1";
			String[] tmp = dao.getOneRs(sql, new String[]{user[0]}, new String[]{"zmc"});
			if (tmp != null && tmp.length > 0 && StringUtils.isEqual(tmp[0], "У�쵼")) {
				bFlag = true;
			}
		}
		return bFlag;
	}
	
	/**
	 * ѧԺ��ȡ���������Ϣ
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> wjcfShViewByXy(String pkValue) throws Exception {
		HashMap<String, String> resMap = new HashMap<String, String>();
		String sql = "select xh,xm,xb,nj,xymc,bjmc,zymc,xn,nd,cflb,cfyy,cfsj,cfwh,xysh sh,xyclyj clyj from view_gdby_wjcf where xh||cfsj||cfwh= ?";
		resMap = dao.getMapNotOut(sql, new String[]{pkValue});
		return resMap;
	}
	
	/**
	 * ѧУ��ȡ���������Ϣ
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> wjcfShViewByXsc(String pkValue) throws Exception {
		HashMap<String, String> resMap = new HashMap<String, String>();
		String sql = "select xh,xm,xb,nj,xymc,bjmc,zymc,xn,nd,cflb,cfyy,cfsj,cfwh,xxsh sh,xxclyj clyj from view_gdby_wjcf where xh||cfsj||cfwh= ?";
		resMap = dao.getMapNotOut(sql, new String[]{pkValue});
		return resMap;
	}
	
	/**
	 * ѧ������ȡ���������Ϣ
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> wjcfShViewByXnd(String pkValue) throws Exception {
		HashMap<String, String> resMap = new HashMap<String, String>();
		String sql = "select xh,xm,xb,nj,xymc,bjmc,zymc,xn,nd,cflb,cfyy,cfsj,cfwh,xndsh sh,xndclyj clyj from view_gdby_wjcf where xh||cfsj||cfwh= ?";
		resMap = dao.getMapNotOut(sql, new String[]{pkValue});
		return resMap;
	}
	
	/**
	 * ��ȡ����б�
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getChkList(int type) throws Exception {
		return dao.getChkList(type);
	}
	
	/**
	 * ѧԺ��˽��
	 * @param shModel
	 * @param request
	 * @return
	 */
	public boolean shResultByXy(ShResultModel shModel, HttpServletRequest request) throws Exception{
		boolean bFlag = false;
	    bFlag = StandardOperation.update("wjcfb", new String[] { "cfsj",
						"cfwh", "xysh", "xyclyj" }, new String[] { shModel.getCfsj(),
						DealString.toGBK(shModel.getCfwh()),
						DealString.toGBK(shModel.getSh()),
						DealString.toGBK(shModel.getClyj()) }, "xh||cfsj||cfwh",
						DealString.toGBK(shModel.getPkValue()), request);
		return bFlag;
	}
	
	/**
	 * ѧУ��˽��
	 * @param shModel
	 * @param request
	 * @return
	 */
	public boolean shResultByXsc(ShResultModel shModel, HttpServletRequest request) throws Exception{
		boolean bFlag = false;
		bFlag = StandardOperation.update("wjcfb", new String[] { "cfsj",
					"cfwh", "xxsh", "xxclyj" }, new String[] { shModel.getCfsj(),
					DealString.toGBK(shModel.getCfwh()),
					DealString.toGBK(shModel.getSh()),
					DealString.toGBK(shModel.getClyj()) }, "xh||cfsj||cfwh",
					DealString.toGBK(shModel.getPkValue()), request);
		return bFlag;
	}
	
	/**
	 * У�쵼��˽��
	 * @param shModel
	 * @param request
	 * @return
	 */
	public boolean shResultByXnd(ShResultModel shModel, HttpServletRequest request) throws Exception{
		boolean bFlag = false;
		bFlag = StandardOperation.update("wjcfb", new String[] { "cfsj",
					"cfwh", "xndsh", "xndclyj" }, new String[] { shModel.getCfsj(),
					DealString.toGBK(shModel.getCfwh()),
					DealString.toGBK(shModel.getSh()),
					DealString.toGBK(shModel.getClyj()) }, "xh||cfsj||cfwh",
					DealString.toGBK(shModel.getPkValue()), request);
		return bFlag;
	}
	
	/**
	 * ��������Ƿ��ظ�
	 * @param shModel
	 * @return
	 * @throws Exception
	 */
	public boolean chkDataExists(ShResultModel shModel, String pkValue) throws Exception {
		boolean bFlag = false;
		String pk = shModel.getXh() + shModel.getCfsj() + DealString.toGBK(shModel.getCfwh());
		pkValue = !StringUtils.isNull(pkValue) ? DealString.toGBK(pkValue) : "";
		pk = !StringUtils.isNull(pk) ? pk : "";
		if (!StringUtils.isEqual(pk, pkValue)) {
			boolean[] tmp= dao.checkExists("wjcfb", "xh||cfsj||cfwh", new String[]{pk});
			if (tmp != null && tmp.length > 0) {
				bFlag = tmp[0];
			}
		}
		return bFlag;
	}
	
	/**
	 * ����Ա��ѯ�����û������Ϣ��ͷ
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getAdminQryTitle() throws Exception {
		List<HashMap<String, String>> topList = new ArrayList<HashMap<String,String>>();
		String[] enList = new String[]{"pk", "bgcolor", "rownum", "xn", "nd", "xh", "xm", "bjmc", "cflbmc", "cfyymc", "xysh", "xxsh", "xndsh"};
		String[] cnList = new String[]{"pk", "bgcolor", "�к�", "ѧ��", "���", "ѧ��", "����", "�༶����", "�������", "����ԭ��", Base.YXPZXY_KEY+"���", "ѧ�������", "ѧУ���"};
		for (int i = 0; i < enList.length; i++) {
			HashMap<String, String> tmp = new HashMap<String, String>();
			tmp.put("en", enList[i]);
			tmp.put("cn", cnList[i]);
			topList.add(tmp);
		}
		return topList;
	}
	
	/**
	 * ����Ա��ѯ�����û������Ϣ���
	 * @param wjcfModel
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getAdminQryResult(WjcfShModel wjcfModel) throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		String sql = "select xh||cfsj||cfwh pk,(case nvl(xysh||xxsh||xndsh,'δ���') when 'ͨ��ͨ��ͨ��' then '#FFFFFF' else '#DDDDDD' end) bgcolor,rownum,xn,nd,xh,xm,bjmc,cflbmc,cfyymc,xysh,xxsh,xndsh from view_gdby_wjcf where sbsj is not null ";
		String[] opList = new String[]{"pk", "bgcolor", "rownum", "xn", "nd", "xh", "xm", "bjmc", "cflbmc", "cfyymc", "xysh", "xxsh", "xndsh"};
		StringBuffer whereSql = getWhereSql(wjcfModel);
		resList = dao.rsToVator(sql + whereSql, values != null ? values.toArray(new String[0]) : new String[]{}, opList);
		return resList;
	}
	
	/**
	 * ��ȡ�����������༶�б�
	 * @param userName
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getFdybjList(String userName)
			throws Exception {
		String mySql = "select a.bjdm, b.bjmc from view_fdybbj b,view_njxyzybj a where a.bjdm=b.bjdm  and  b.zgh=? order by bjdm";
		return dao.getList(mySql, new String[] { userName }, new String[] {
				"bjdm", "bjmc" });
	}
	
	public List<HashMap<String, String>> getJxjxdmList() throws Exception {
		return dao.getList("select jxdm dm,jxmc mc from jxjxdmb",
				new String[] {}, new String[] { "dm", "mc" });
	}
	public List<HashMap<String, String>> getZyList(String xydm) {
		return dao.getZyList(xydm);
	}
	public List<HashMap<String, String>> getBjList(String xydm, String zydm, String nj) {
		return dao.getBjList(xydm, zydm, nj);
	}
}
