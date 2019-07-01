package xsgzgl.gygl.wsjc.jcrcgl;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommDAO;
import xgxt.comm.search.SearchModel;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;
import xsgzgl.comm.BasicModel;
import xsgzgl.gygl.comm.GyglNewInit;

/**
 * <p>
 * Title: ѧ����������ϵͳ
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * <p>
 * Author: qlj
 * </p>
 * <p>
 * Version: 1.0
 * </p>
 * <p>
 * Time:2012-6-14 ����03:45:37
 * </p>
 */
public class GyglJcrcglDAO extends CommDAO {
	
	/**
	 * ����������ճ̹���
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getJcrcglList(GyglJcrcglForm model) throws Exception {
		// �߼���ѯMODEL
		SearchModel searchModel = model.getSearchModel();
		// �û�����
		User user = model.getUser();
		String[] colList = null;
		
		if (GyglNewInit.WSJC_XJQS){
			if("18180".equals(Base.xxdm)){
				colList = new String[] { "guid", "xn", "xqmc","jcyf", "mc", "lxmc","kssj", "jssj", "bzInfo", "bz", "sfwh","tjztInfo" };
			}else{
			colList = new String[] { "guid", "xn", "xqmc","mc", "lxmc","kssj", "jssj", "bzInfo", "bz", "sfwh","tjztInfo" };
			}
		} else {
			colList = new String[] { "guid", "xn", "xqmc", "mc", "kssj", "jssj", "bzInfo", "bz", "sfwh","tjztInfo" };
		}
		if("12688".equals(Base.xxdm)){
			colList = new String[] { "guid", "xn", "xqmc", "mc", "lxmc","kssj", "jssj", "bzInfo", "bz", "sfwh","tjztInfo","pfjbmc" };
		}
		// �û�����
		String userType = user.getUserType();
		// ====================��������===================================
		user.setUserStatus(userType);
		// �߼���ѯ����
		String searchTj = SearchService.getSearchTj(searchModel);
		// �߼���ѯ����ֵ
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		String query = " where 1 = 1 ";
		query += searchTj;
		// ====================�������� end================================
		// ====================SQLƴװ================================
		StringBuilder sql = new StringBuilder();

		sql.append("select a.*,rownum r from ( select a.guid,a.xn,a.xq,a.jcyf,a.mc,a.lxmc,");
		sql.append("a.jclx,a.kssj,a.jssj,a.bz,a.xqmc,a.tjzt,a.tjztInfo,a.bzinfo,a.sfwh " );
		if("12688".equals(Base.xxdm))
			sql.append(" ,a.pfjbmc");
		sql.append(" from(  ");
		sql.append(" select a.*,decode(a.jclx,'0','����','1','�ȼ�','2','�Ǽ�') lxmc,b.xqmc,case when length(bz)>15  ");
		sql.append(" then substr(bz,0,15)||'...' else bz end bzInfo, ");
		sql.append(" case when tjzt='1' then '���ύ' else 'δ�ύ' end tjztInfo, ");
		sql.append("(select a.guid from (select guid from XG_GYGL_NEW_WSJC_QSFSB group by guid) where a.guid=guid) sfwh ");
		if("12688".equals(Base.xxdm))
			sql.append(" , decode(a.pfjb, 'xj', 'У��', 'yj', 'Ժ��') pfjbmc ");
		sql.append(" from xg_gygl_new_wsjc_jcrcb a,xqdzb b where a.xq=b.xqdm)a ");
		sql.append(query);
		sql.append(" order by kssj desc ) a");
		// ====================SQLƴװ end================================
		ArrayList<String[]> list = (ArrayList<String[]>) CommonQueryDAO.commonQuery(sql.toString(), "", inputV, colList, model);
		return list;
	}

	/**
	 * ��ѯ������������ճ���Ϣ
	 * 
	 * @param model
	 * @return
	 */
	public HashMap<String, String> getJcrcglMap(GyglJcrcglForm model) {
		DAO dao = DAO.getInstance();
		String guid = model.getGuid();
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.guid,a.xn,a.xq,a.mc,a.jcyf,a.jclx,a.kssj,a.jssj,a.bz,b.xqmc from xg_gygl_new_wsjc_jcrcb a,xqdzb b where a.xq=b.xqdm and guid=? ");
		return dao.getMapNotOut(sql.toString(), new String[] { guid });
	}

	/**
	 * �ճ����Ʋ����ظ��ļ��
	 * 
	 * @param model
	 * @param Mc
	 * @return
	 * @throws IllegalArgumentException
	 *             , SecurityException, IllegalAccessException,
	 *             InvocationTargetException, NoSuchMethodException
	 */
	public boolean findInfo(BasicModel model, String Mc)
			throws IllegalArgumentException, SecurityException,IllegalAccessException, InvocationTargetException,NoSuchMethodException {
		DAO dao = DAO.getInstance();
		String rs = dao.getOneRs("select count(*) cnt from XG_GYGL_NEW_WSJC_JCRCB where mc =?",new String[] { Mc }, "cnt");
		if (!rs.equals("0")) {
			return true;
		}
		return false;
	}

	/**
	 * ��ֹʱ�䲻���ص��ļ��
	 * 
	 * @param model
	 * @param Mc
	 * @return
	 * @throws IllegalArgumentException , SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException
	 */
	public String findQzsj(BasicModel model, String Kssj, String Jssj,String Xn, String Xq, String jclx) 
			throws IllegalArgumentException,SecurityException, IllegalAccessException,InvocationTargetException, NoSuchMethodException {
		String pk=model.getPkValue()[0];
		long kssj1 = Long.parseLong(Kssj);
		long jssj1 = Long.parseLong(Jssj);
		long XnKs = Long.parseLong(Xn.substring(0, 4));
		long XnJs = Long.parseLong(Xn.substring(5, 9));
		long KssjXn = Long.parseLong(Kssj.substring(0, 4));
		long JssjXn = Long.parseLong(Jssj.substring(0, 4));
		if (kssj1 > jssj1) {
			return "��ʼʱ�䲻�ܴ��ڽ���ʱ��";
		}
		if (KssjXn < XnKs || JssjXn > XnJs) {
			return "��ֹʱ�䲻��ѧ�귶Χ��";
		}
		
		StringBuffer sql = new StringBuffer("select count(1) count from xg_gygl_new_wsjc_jcrcb where (? between kssj and jssj or ? between kssj and jssj) ");

		List<String> params = new ArrayList<String>();
		params.add(Kssj);
		params.add(Jssj);
		
		if(!StringUtils.isBlank(jclx)) {
			sql.append(" and jclx = ?");
			params.add(jclx);
		}
		
		if(!StringUtils.isBlank(pk)){
			sql.append(" and guid<> ?");
			params.add(pk);
		}
		
		DAO dao = DAO.getInstance();
		String count = dao.getOneRs(sql.toString(),  params.toArray(new String[]{}), "count");
		//�ж��Ƿ�������о���ó��ѧУ�����ǣ�ֱ�ӷſ������ظ���ѯ
		 if (!count.equals("0") && !Base.xxdm.equals("1103202") && !Base.xxdm.equals("13011")) {
			return "��ֹʱ�䲻���ص�";
		}
		
//		String sql = "select guid,Kssj,Jssj,rownum r from XG_GYGL_NEW_WSJC_JCRCB where xn=? and xq=?";
//		ArrayList<String[]> list = (ArrayList<String[]>) CommonQueryDAO.commonQuery(sql, "", new String[] { Xn, Xq }, new String[] {"guid","Kssj", "Jssj" }, model);
//		for (String[] val : list) {
//			//�Ų�· ���ж��������������ֱ�ӷ���
//			if(pk.equals(val[0])){
//				continue;
//			}
//			
//			long kssj2 = Long.parseLong(val[1]);
//			long jssj2 = Long.parseLong(val[2]);
//			if (kssj1 > kssj2 && kssj1 < jssj2) {
//				return "��ֹʱ�䲻���ص�";
//			} else if (jssj1 > kssj2 && jssj1 < jssj2) {
//				return "��ֹʱ�䲻���ص�";
//			} else if (kssj2 > kssj1 && kssj2 < jssj1) {
//				return "��ֹʱ�䲻���ص�";
//			} else if (jssj2 > kssj1 && jssj2 < jssj1) {
//				return "��ֹʱ�䲻���ص�";
//			}
//		}
		return "��ֹʱ����ã�";
	}
	
	/**
	 * ����ճ̹��� �Զ�������
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getJcrcglExportList(GyglJcrcglForm model,User user) throws Exception {
		// �߼���ѯMODEL
		SearchModel searchModel = model.getSearchModel();
		
		String[] colList = new String[] { "guid", "xn", "xqmc", "mc", "kssj", "jssj", "bzInfo", "bz", "sfwh" };
		// �û�����
		String userType = user.getUserType();
		// ====================��������===================================
		user.setUserStatus(userType);
		// �߼���ѯ����
		String searchTj = SearchService.getSearchTj(searchModel);
		// �߼���ѯ����ֵ
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		String query = " where 1 = 1 ";
		query += searchTj;
		// ====================�������� end================================
		// ====================SQLƴװ================================
		StringBuilder sql = new StringBuilder();

		sql.append(" select a.guid,a.lxmc,a.xn,a.xq,a.mc,a.kssj,a.jssj,a.bz,a.xqmc,a.bzinfo,a.sfwh, ");
		sql.append(" case nvl(tjzt,0) when '1' then '���ύ' else 'δ�ύ' end tjzt,rownum r from( ");
		sql.append(" select a.*,decode(a.jclx,'0','����','1','�ȼ�','2','�Ǽ�') lxmc,b.xqmc,case when length(bz)>15  ");
		sql.append(" then substr(bz,0,15)||'...' else bz end bzInfo, ");
		sql.append("(select a.guid from (select guid from XG_GYGL_NEW_WSJC_QSFSB group by guid) where a.guid=guid) sfwh ");
		sql.append(" from xg_gygl_new_wsjc_jcrcb a,xqdzb b where a.xq=b.xqdm)a ");
		sql.append(query);
		sql.append(" order by kssj desc");
		// ====================SQLƴװ end================================
		
//		sql.append("select rownum r , k.* from VIEW_NEW_DC_GYGL_JCRCGL k ").append(query);
		
		List<HashMap<String, String>> list =   CommonQueryDAO.commonQueryforExportList(sql.toString(), "", inputV, colList, model);
		return list;
	}

	/**
	 * @throws SQLException  
	 * @����:�޸ļ���ճ̹���Ϊ�ύ״̬
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2013-8-29 ����10:13:26
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean tjJcrcgl(String pkStr) {

		DAO dao = DAO.getInstance();

		String[] pkValue = pkStr.split("!!array!!");

		StringBuffer sql = new StringBuffer();
		sql.append(" update xg_gygl_new_wsjc_jcrcb ");
		sql.append(" set tjzt='1' ");
		sql.append(" where guid=? ");

		List<String[]> params = new ArrayList<String[]>();
		for (int i = 0; i < pkValue.length; i++) {
			String[] val = new String[1];
			val[0] = pkValue[i];
			params.add(val);
		}
		try {
			dao.runBatch(sql.toString(), params);
			return true;		
		} catch (SQLException e) {
			e.printStackTrace();
			return false;		
		}

	}
	
	/**
	 * 
	 * @����: �ύͬ�����µ�XG_QSFMX���Ϻ�Ϸ����Ի���
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-1-5 ����04:12:47
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param pkStr
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveSubmit(String pkStr) {

		DAO dao = DAO.getInstance();

		String[] pkValue = pkStr.split("!!array!!");

		StringBuffer sql = new StringBuffer();
		sql.append(" insert into XG_QSFMX(xh,xn,xq,rcid,lddm,qsh,cwh,qsfs,cwfs,zfs) ");
		sql.append(" select a.xh, a.xn, a.xq, a.guid, lddm, qsh, cwh, sum(a.qsfs) qsfs, sum(cwfs) as cwfs, sum(zfs) as zfs ");		
		sql.append(" from view_new_gygl_qswsffs a ");
		sql.append(" where a.guid = ? ");
		sql.append(" group by a.xh, a.xn, a.xq, a.guid, lddm, qsh, cwh ");

		List<String[]> params = new ArrayList<String[]>();
		for (int i = 0; i < pkValue.length; i++) {
			String[] val = new String[1];
			val[0] = pkValue[i];
			params.add(val);
		}
		try {
			dao.runBatch(sql.toString(), params);
			return true;		
		} catch (SQLException e) {
			e.printStackTrace();
			return false;		
		}

	}
	
	/**
	 * 
	 * @����: ȡ���ύͬ��ɾ����Ӧ����XG_QSFMX���Ϻ�Ϸ����Ի���
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-1-5 ����04:12:47
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param pkStr
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean delCancel(String pkStr) {

		DAO dao = DAO.getInstance();

		String[] pkValue = pkStr.split("!!array!!");

		StringBuffer sql = new StringBuffer();
		sql.append(" delete from XG_QSFMX ");
		sql.append(" where rcid = ? ");
		
		List<String[]> params = new ArrayList<String[]>();
		for (int i = 0; i < pkValue.length; i++) {
			String[] val = new String[1];
			val[0] = pkValue[i];
			params.add(val);
		}
		try {
			dao.runBatch(sql.toString(), params);
			return true;		
		} catch (SQLException e) {
			e.printStackTrace();
			return false;		
		}

	}
	
	
	/**
	 * 
	 * @����:����ѧ�������ּ�¼
	 * @���ߣ�xiaxia [���ţ�1104]
	 * @���ڣ�2014-11-28 ����11:30:07
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param pkStr
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean bcXsWsf(List<HashMap<String,String>> xswsf) {
		DAO dao = DAO.getInstance();
		StringBuffer sql = new StringBuffer();
		sql.append(" insert into XG_GYGL_NEW_WSJC_XSFSB (jcrcid,xn,xq,xh,fs,djbz) ");
		sql.append(" values(?,?,?,?,?,?) ");
		List<String[]> params = new ArrayList<String[]>();
		if("10344".equals(Base.xxdm)){
			for (int i = 0; i < xswsf.size(); i++) {
				String[] val = new String[6];
				val[0] = xswsf.get(i).get("guid");
				val[1] = xswsf.get(i).get("xn");
				val[2] = xswsf.get(i).get("xq");
				val[3] = xswsf.get(i).get("xh");
				val[4] = xswsf.get(i).get("djs");
				val[5] = xswsf.get(i).get("pfbz");
				params.add(val);
			}
		}else{
			for (int i = 0; i < xswsf.size(); i++) {
				String[] val = new String[6];
				val[0] = xswsf.get(i).get("guid");
				val[1] = xswsf.get(i).get("xn");
				val[2] = xswsf.get(i).get("xq");
				val[3] = xswsf.get(i).get("xh");
				val[4] = xswsf.get(i).get("xsfs");
				val[5] = xswsf.get(i).get("pfbz");
				params.add(val);
			}
		}
		try {
			dao.runBatch(sql.toString(), params);
			return true;		
		} catch (SQLException e) {
			e.printStackTrace();
			return false;		
		}

	}
	/**
	 * 
	 * @����:ȡ���������ʱɾ��ѧ�������ּ�¼
	 * @���ߣ�xiaxia [���ţ�1104]
	 * @���ڣ�2014-11-28 ����02:05:39
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xswsf
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean scXsWsf(String pkStr) {
        DAO dao = DAO.getInstance();
		String[] pkValue = pkStr.split("!!array!!");
		StringBuffer sql = new StringBuffer();
		sql.append(" delete from XG_GYGL_NEW_WSJC_XSFSB where jcrcid=?");	
		List<String[]> params = new ArrayList<String[]>();
		for (int i = 0; i < pkValue.length; i++) {
			String[] val = new String[1];
			val[0] = pkValue[i];
			params.add(val);
		}
		try {
			dao.runBatch(sql.toString(), params);
			return true;		
		} catch (SQLException e) {
			e.printStackTrace();
			return false;		
		}

	}
	
	/**
	 * 
	 * @����:��ȡ������סѧ��������
	 * @���ߣ�xiaxia [����:1104]
	 * @���ڣ�2014-11-28 ����11:15:33
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param guids
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getXsfs(String pkStr){
		String[] guids = pkStr.split("!!array!!");
		DAO dao = DAO.getInstance();
		StringBuffer sql = new StringBuffer();
		
		//�㽭��ҽҩ ׼��ҵ����λ�����
		if("10344".equals(Base.xxdm)){
			sql.append(" select m.*,case when (to_number(nvl(n.nj,0))+to_number(nvl(n.xz,0))) = to_number(Substr(m.jcrq, 0, 4)) then 'N' else m.dj end djs from ( ");
			sql.append(" select a.*,d.cwh,d.xh,b.xn,b.xq,(select b.fssx from XG_GYGL_NEW_WSJC_DJFSB b where b.dj=a.dj ) xsfs");
			sql.append(" from xg_gygl_new_wsjc_qsfsb a");
			sql.append(" left join xg_gygl_new_wsjc_jcrcb b on a.guid = b.guid left join xg_gygl_new_cwxxb d");
			sql.append(" on a.lddm = d.lddm and a.qsh = d.qsh where a.guid in (");
			for (int i = 0; i < guids.length; i++) {
				sql.append("?");
				if(i!=guids.length-1){
					sql.append(",");
				}
			}
			sql.append(") and d.sfbl='��' and d.xh is not null");
			sql.append(" ) m left join xsxxb n on m.xh = n.xh ");
		} else {
			sql.append("select a.*,d.cwh,d.xh,b.xn,b.xq,(select b.fssx from XG_GYGL_NEW_WSJC_DJFSB b where b.dj=a.dj ) xsfs");
			sql.append(" from xg_gygl_new_wsjc_qsfsb a");
			sql.append(" left join xg_gygl_new_wsjc_jcrcb b on a.guid = b.guid left join xg_gygl_new_cwxxb d");
			sql.append(" on a.lddm = d.lddm and a.qsh = d.qsh where a.guid in (");
			for (int i = 0; i < guids.length; i++) {
				sql.append("?");
				if(i!=guids.length-1){
					sql.append(",");
				}
			}
			sql.append(") and d.sfbl='��' and d.xh is not null");
		}
		
		return dao.getListNotOut(sql.toString(), guids);
	}
	
	/**
	 * 
	 * @����:ȡ���ύ״̬
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2013-8-29 ����11:45:48
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param pkStr
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean qxtjJcrcgl(String pkStr){
		
		DAO dao = DAO.getInstance();
		
		String[] pkValue = pkStr.split("!!array!!");

		StringBuffer sql = new StringBuffer();
		sql.append(" update xg_gygl_new_wsjc_jcrcb ");
		sql.append(" set tjzt='' ");
		sql.append(" where guid=? ");

		List<String[]> params = new ArrayList<String[]>();
		for (int i = 0; i < pkValue.length; i++) {
			String[] val = new String[1];
			val[0] = pkValue[i];
			params.add(val);
		}
		try {
			dao.runBatch(sql.toString(), params);
			return true;		
		} catch (SQLException e) {
			e.printStackTrace();
			return false;		
		}
		
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-7-10 ����10:17:22
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean updateByqsForZjcm(String[] pkValue) throws Exception{
		StringBuilder sql = new StringBuilder();
		List<String> paraList = new ArrayList<String>();
		sql.append(" update XG_GYGL_NEW_WSJC_QSFSB t");
		sql.append(" set t.BYQS =(select t1.byqs from");
		sql.append(" view_zjcm_xg_gygl_byqs t1 ");
		sql.append(" where t1.lddm || t1.qsh = t.lddm || t.qsh");
		sql.append(" and t.guid in(");
		for (int i = 0; i < pkValue.length; i++) {
			sql.append("?");
			if(i != pkValue.length-1){
				sql.append(",");
			}
			paraList.add(pkValue[i]);
		}
		sql.append(" ))");
		sql.append("  where exists(select 1 from  ");
		sql.append("  view_zjcm_xg_gygl_byqs t1  where t1.lddm || t1.qsh = t.lddm || t.qsh");
		sql.append("  and t.guid in (");
		for (int i = 0; i < pkValue.length; i++) {
			sql.append("?");
			if(i != pkValue.length-1){
				sql.append(",");
			}
			paraList.add(pkValue[i]);
		}
		sql.append(" ))");
		return DAO.getInstance().runUpdate(sql.toString(), paraList.toArray(new String[]{}));
	}
}