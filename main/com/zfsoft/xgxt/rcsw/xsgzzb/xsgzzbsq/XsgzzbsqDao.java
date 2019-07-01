package com.zfsoft.xgxt.rcsw.xsgzzb.xsgzzbsq;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import sun.org.mozilla.javascript.internal.EcmaError;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

public class XsgzzbsqDao extends SuperDAOImpl<XsgzzbsqForm> {

	/*
	 * ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		super.setKey("sqid");
//		super.setTableName("xg_rcsw_xsgzzb_xsgzzbsqb"); // ��Ϊ���Ƕ�̬�ģ��˴�������TableName
	}
	/**
	 * ��̬ȡ��
	 */
	protected void setTableInfo(XsgzzbsqForm t) {
		String table = "xg_rcsw_xsgzzb_xsgzzbsqb";
		if("bj".equals(t.getGzzblx())){
			table = "xg_rcsw_bjgzzb_bjgzzbsqb";
		}
		super.setTableName(table);
	}

	/*
	 * ����: @see
	 * com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(XsgzzbsqForm t)
			throws Exception {
		return null;
	}

		/*
	 * ����: @see
	 * com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object,
	 * xgxt.form.User)
	 */
	@Override
	public List<HashMap<String, String>> getPageList(XsgzzbsqForm t, User user)
			throws Exception {

		// ���ɸ߼���ѯ�������������ֵ
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.* from (select t1.sqid,t1.xn,t1.xq,t1.bmdm,t1.bmdm xydm,t1.zc,t1.yzzygz,t1.xzzygz,t1.yj,t1.lrsj,t1.lrr,t1.shzt,t1.splc,t1.zcksjsrq,t1.qtgz, ");
		sql.append(" '��'||t1.zc||'��' zcmc,t5.xqmc,t2.bmmc bmdmmc,t2.bmmc xymc,t3.xm lrrxm, ");
		sql.append(" decode(t1.shzt, '0','δ�ύ', '1', 'ͨ��', '2', '��ͨ��', '3', '�˻�', '4', '������', '5', '�����', '','�������', t1.shzt) shztmc ");
		sql.append(" from xg_rcsw_xsgzzb_xsgzzbsqb t1 ");
		sql.append(" left join zxbz_xxbmdm t2 on t1.bmdm = t2.bmdm ");
		sql.append(" left join fdyxxb t3 on t1.lrr = t3.zgh ");
		sql.append(" left join xqdzb t5 on t1.xq = t5.xqdm ");
		sql.append(" ) a where 1 = 1 ");
		sql.append(searchTj);
		String userStatus = user.getUserStatus();
		String userDep = user.getUserDep();
		String userName = user.getUserName();
		if ("xy".equalsIgnoreCase(userStatus)) {
			sql.append(" and a.bmdm='" + userDep + "' ");
		}else if ("bzr".equalsIgnoreCase(userStatus) ||
				  "fdy".equalsIgnoreCase(userStatus) || 
				  "jd".equalsIgnoreCase(userStatus)){
			sql.append(" and a.lrr='" + userName + "' ");
		}
		return getPageList(t, sql.toString(), inputV);
	}
	/**
	 * �༶�ܱ�
	 */
	public List<HashMap<String, String>> getPageListBj(XsgzzbsqForm t, User user)
		throws Exception {
		
		// ���ɸ߼���ѯ�������������ֵ
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.* from (select t1.sqid,t1.xn,t1.xq,t1.zc,t1.ydrs,t1.sdrs,t1.qjrs,t1.wdrs,t1.zynr,t1.zywt,t1.jjdc,t1.filepath,t1.lrsj,t1.lrr,t1.shzt,t1.splc,t1.zcksjsrq, ");
		sql.append(" '��'||t1.zc||'��' zcmc,t5.xqmc,t3.xm lrrxm,t1.ydrs||'/'||t1.sdrs||'/'||t1.qjrs||'/'||t1.wdrs rstj, ");
		sql.append(" t2.*,t2.xydm bmdm, c.dbfdy,");
		sql.append(" decode(t1.shzt, '0','δ�ύ', '1', 'ͨ��', '2', '��ͨ��', '3', '�˻�', '4', '������', '5', '�����', '','�������', t1.shzt) shztmc ");
		sql.append(" from xg_rcsw_bjgzzb_bjgzzbsqb t1 ");
		sql.append(" left join view_njxyzybj_all t2 on t1.bjdm = t2.bjdm ");
		sql.append(" left join fdyxxb t3 on t1.lrr = t3.zgh ");
		sql.append(" left join xqdzb t5 on t1.xq = t5.xqdm ");
		sql.append(" LEFT JOIN (SELECT bjdm,WM_CONCAT(t2.XM) dbfdy FROM FDYBJB t1 LEFT JOIN FDYXXB t2 ON t1.ZGH = t2.ZGH  GROUP BY BJDM) c ON t1.BJDM = c.BJDM ");
		sql.append(" ) a where 1 = 1 ");
		sql.append(searchTj);
		String userStatus = user.getUserStatus();
		String userDep = user.getUserDep();
		String userName = user.getUserName();
		if ("xy".equalsIgnoreCase(userStatus)) {
			sql.append(" and a.bmdm='" + userDep + "' ");
		}else if ("bzr".equalsIgnoreCase(userStatus) ||
				"fdy".equalsIgnoreCase(userStatus) || 
				"jd".equalsIgnoreCase(userStatus)){
			sql.append(" and a.lrr='" + userName + "' ");
		}
		return getPageList(t, sql.toString(), inputV);
	}
	
	@Override
	public XsgzzbsqForm getModel(XsgzzbsqForm t) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" select t1.sqid,t1.xn,t1.xq,t1.bmdm,t1.bmdm xydm,t1.zc,t1.yzzygz,t1.xzzygz,t1.yj,t1.lrsj,t1.lrr,t1.shzt,t1.splc,t1.zcksjsrq,t1.qtgz, ");
		sql.append(" '��'||t1.zc||'��' zcmc,t5.xqmc,t2.bmmc bmdmmc,t2.bmmc xymc,t3.xm lrrxm, ");
		sql.append(" decode(t1.shzt, '0','δ�ύ', '1', 'ͨ��', '2', '��ͨ��', '3', '�˻�', '4', '������', '5', '�����', '','�������', t1.shzt) shztmc ");
		sql.append(" from xg_rcsw_xsgzzb_xsgzzbsqb t1 ");
		sql.append(" left join zxbz_xxbmdm t2 on t1.bmdm = t2.bmdm ");
		sql.append(" left join fdyxxb t3 on t1.lrr = t3.zgh ");
		sql.append(" left join xqdzb t5 on t1.xq = t5.xqdm ");
		sql.append(" where t1.sqid = ? ");
		HashMap<String,String> map = dao.getMapNotOut(sql.toString(), new String[] { t.getSqid() });
		XsgzzbsqForm model=new XsgzzbsqForm();
		BeanUtils.copyProperties(model, map);
		return model;
	}
	/**
	 * �༶�ܱ�
	 */
	public XsgzzbsqForm getModelBj(XsgzzbsqForm t) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" select t1.sqid,t1.xn,t1.xq,t1.zc,t1.ydrs,t1.sdrs,t1.qjrs,t1.wdrs,t1.zynr,t1.zywt,t1.jjdc,t1.filepath,t1.lrsj,t1.lrr,t1.shzt,t1.splc,t1.zcksjsrq, ");
		sql.append(" '��'||t1.zc||'��' zcmc,t5.xqmc,t3.xm lrrxm,t2.nj||'�� '||t2.xymc||' '||t2.zymc||' '||t2.bjmc bjmcall, ");
		sql.append(" t2.*,t2.xydm bmdm, c.dbfdy,");
		sql.append(" decode(t1.shzt, '0','δ�ύ', '1', 'ͨ��', '2', '��ͨ��', '3', '�˻�', '4', '������', '5', '�����', '','�������', t1.shzt) shztmc ");
		sql.append(" from xg_rcsw_bjgzzb_bjgzzbsqb t1 ");
		sql.append(" left join view_njxyzybj_all t2 on t1.bjdm = t2.bjdm ");
		sql.append(" left join fdyxxb t3 on t1.lrr = t3.zgh ");
		sql.append(" left join xqdzb t5 on t1.xq = t5.xqdm ");
		sql.append(" LEFT JOIN (SELECT bjdm,WM_CONCAT(t2.XM) dbfdy FROM FDYBJB t1 LEFT JOIN FDYXXB t2 ON t1.ZGH = t2.ZGH  GROUP BY BJDM) c ON t1.BJDM = c.BJDM ");
		sql.append(" where t1.sqid = ? ");
		HashMap<String,String> map = dao.getMapNotOut(sql.toString(), new String[] { t.getSqid() });
		XsgzzbsqForm model=new XsgzzbsqForm();
		BeanUtils.copyProperties(model, map);
		return model;
	}
	
	public String getShlcID(String gzzblx) {
		String table = "xg_rcsw_xsgzzb_jcszb";
		if("bj".equals(gzzblx)){
			table = "xg_rcsw_bjgzzb_jcszb";
		}
		StringBuffer sql = new StringBuffer();
		sql.append(" select splc from " + table);
		return dao.getOneRs(sql.toString(), new String[] {}, "splc");
	}
	
	/**
	 * @����:�õ���ǰѧ������
	 * @param model
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getCurrentXqmc(XsgzzbsqForm model) {
		StringBuilder sql = new StringBuilder(" select xqmc from xqdzb where xqdm=? ");
		String xqmc = dao.getOneRs(sql.toString(), new String[] { model.getXq()}, "xqmc");
		return xqmc;
	}
	
	/**
	 * @����:�õ�ѧ������,������ʼ����
	 */
	public HashMap<String, String> getZc() {
		StringBuilder sql = new StringBuilder(" select xqzs,qsrq from xtszb ");
		return dao.getMapNotOut(sql.toString(), new String[] { });
	}
	
	/**
	 * @����: ��ȡѧԺ�б�
	 */
	public List<HashMap<String, String>> getXyList() {
		StringBuilder sql = new StringBuilder();
		sql.append(" select distinct bmdm xydm, bmmc xymc, substr(f_pinyin(bmmc), 0, 1) || bmmc pyszm ");
		sql.append(" from zxbz_xxbmdm ");
		sql.append(" order by pyszm " );
		return dao.getListNotOut(sql.toString(), new String[]{});
	}
	/**
	 * @����: ��ȡ�༶�б�
	 */
	public List<HashMap<String, String>> getBjList(XsgzzbsqForm t, User user)
		throws Exception {
		
		//���ɸ߼���ѯ�������������ֵ 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputValue = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t1", "xydm", "bjdm");		
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select * from ( ");
		sql.append(" select a.bjrs,b.*,c.dbfdy from ( ");
		sql.append(" select bjdm,count(1) bjrs  from view_xsbfxx where (sfzx='��У' or sfzx is null) group by bjdm ");
		sql.append(" ) a ");
		sql.append(" left join view_njxyzybj_all b on a.bjdm=b.bjdm ");
		sql.append(" LEFT JOIN (SELECT bjdm,WM_CONCAT(t2.XM) dbfdy FROM FDYBJB t1 LEFT JOIN FDYXXB t2 ON t1.ZGH = t2.ZGH  GROUP BY BJDM) c ON a.BJDM = c.BJDM ");
		sql.append(" ) t1 where 1=1 ");
		sql.append(searchTj);
		sql.append(searchTjByUser);
		
		return getPageList(t, sql.toString(), inputValue);
	}
	
	/**
	 * @����:�����ܱ�����
	 * @param sqid
	 * @param shzt
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean updateXsgzzbsq(XsgzzbsqForm model) throws Exception{
		String table = "xg_rcsw_xsgzzb_xsgzzbsqb";
		if("bj".equals(model.getGzzblx())){
			table = "xg_rcsw_bjgzzb_bjgzzbsqb";
		}
		String[] inputV = new String[3];
		StringBuilder sql = new StringBuilder();
		sql.append(" UPDATE " + table);
		sql.append(" set ");
		sql.append(" shzt = ?,");
		sql.append(" splc = ? ");
		sql.append(" where sqid = ?");
		inputV[0] = model.getShzt();
		inputV[1] = model.getSplc();
		inputV[2] = model.getSqid();
		return dao.update(sql.toString(),inputV)>0 ? true:false;
	}
	
	/**
	 * @����:�Ƿ��Ѿ�����
	 * @param model
	 * @return
	 */
	public String checkExistForSave(XsgzzbsqForm model, User user) {
		String sqid = "-1";
		StringBuilder sql = new StringBuilder(
				" select count(1) num from xg_rcsw_xsgzzb_xsgzzbsqb where xn = ? and xq = ? and bmdm = ? and zc = ? and lrr = ? and sqid <> ? ");
		if(model.getSqid() != null){
			sqid = model.getSqid();
		}
		String num = dao.getOneRs(sql.toString(), new String[] { model.getXn(),model.getXq(),model.getBmdm(),model.getZc(),user.getUserName(),sqid}, "num");
		return num;
	}
	/**
	 * @����:�Ƿ��Ѿ����ڣ��༶�ܱ���
	 * @param model
	 * @return
	 */
	public String checkExistForSaveBj(XsgzzbsqForm model, User user) {
		String sqid = "-1";
		StringBuilder sql = new StringBuilder(
		" select count(1) num from xg_rcsw_bjgzzb_bjgzzbsqb where xn = ? and xq = ? and bjdm = ? and zc = ? and lrr = ? and sqid <> ? ");
		if(model.getSqid() != null){
			sqid = model.getSqid();
		}
		String num = dao.getOneRs(sql.toString(), new String[] { model.getXn(),model.getXq(),model.getBjdm(),model.getZc(),user.getUserName(),sqid}, "num");
		return num;
	}
	
	/**
	 * 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2015-11-19 ����04:56:54
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean savefjb(XsgzzbsqForm model) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" insert into XG_RCSW_XSGZZB_XSGZZB_FJB(wjlxdm,fjlj,fjmc,filegid) values(?,?,?,?)");
		return dao.runUpdate(sql.toString(), new String[]{model.getWjlxdm(),model.getFjlj(),model.getFjmc(),model.getFilegid()});
	}
	
	/**
	 * �Ĵ���Ϣְҵ����ѧԺ�����ɾ��ʱ��ȥ��ȡ��ɾ����¼��filegid
	 */
	public String getFileGID(String sqid) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.filegid");
		sql.append(" from xg_rcsw_xsgzzb_xsgzzbsqb t where t.sqid = ?");
		return dao.getOneRs(sql.toString(), new String[]{sqid}, "filegid");
	}
	
	/**
	 * �Ĵ���Ϣְҵ����ѧԺ�����ɾ��ʱ��ȥ��ȡ��ɾ����¼��fjlj
	 */
	public List<HashMap<String, String>>  getfjlj(String[]filegids) throws Exception{
		StringBuilder sql = new StringBuilder();
		int filegidlen = filegids.length;
		sql.append(" select * from XG_RCSW_XSGZZB_XSGZZB_FJB");
		sql.append(" where filegid in (");
		for(int i = 0;i < filegidlen ;i++){
			sql.append(" ?");
			if(i != filegidlen-1){
				sql.append(", ");
			}
		}
		sql.append(" )");
		return dao.getListNotOut(sql.toString(), filegids);
	}

	/**
	 * �Ĵ���Ϣְҵ����ѧԺ�����ɾ��ʱ��ɾ���������е�����
	 * @throws Exception 
	 */
	public boolean Delfile_13815(String[] filegids) throws Exception{
		StringBuilder sql = new StringBuilder();
		int filegidlen = filegids.length;
		sql.append(" delete from XG_RCSW_XSGZZB_XSGZZB_FJB where filegid in(");
		for(int i = 0;i < filegidlen ;i++){
			sql.append(" ?");
			if(i != filegidlen-1){
				sql.append(", ");
			}
		}
		sql.append(")");
		return dao.runUpdate(sql.toString(), filegids);
	}
	
	/**
	 * �޸ĵ�ʱ���ѯ�Ƿ���filegid���ж��Ƿ���ԭ�ļ�
	 */
	public String getUpdateFilegid(String sqid){
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.filegid");
		sql.append(" from xg_rcsw_xsgzzb_xsgzzbsqb t");
		sql.append(" where t.sqid = ?");
		return dao.getOneRs(sql.toString(), new String[]{sqid}, "filegid");
	}
	
	/**
	 * �޸Ľ��浥��ɾ����������ɾ�������¼���������ȡ�����ļ����ļ�fjlj
	 */
	public HashMap<String,String> onefjlj(String fileid){
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* from XG_RCSW_XSGZZB_XSGZZB_FJB t where t.id = ?");
		return dao.getMapNotOut(sql.toString(), new String[]{fileid});
	}
	
	/**
	 * ɾ����������
	 */
	public boolean delonefjlj(String fileid) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append("  delete from XG_RCSW_XSGZZB_XSGZZB_FJB where id = ?");
		return dao.runUpdate(sql.toString(), new String[]{fileid});
	}
}
