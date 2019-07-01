/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-9-9 ����12:07:04 
 */
package com.zfsoft.xgxt.rcsw.qjgl.qjjg;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import common.Globals;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2013-9-9 ����12:07:04
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class QjjgDao extends SuperDAOImpl<QjjgForm> {

	/*
	 * ����: @see
	 * com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object,
	 * xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(QjjgForm t, User user)
			throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(t.getSearchModel());

		StringBuffer sql = new StringBuffer();
		/*sql
				.append(" select * from (select qjjg.*,(select xqmc from xqdzb where xqdm=qjjg.xq) xqmc,xsxx.xm,xsxx.xb,xsxx.nj,xsxx.xymc,xsxx.xydm,xsxx.zymc,xsxx.zydm,xsxx.bjmc,xsxx.bjdm,b.qjlxmc,");
		sql
				.append(" decode(qjjg.shzt,'0','δ���','1','ͨ��','2','��ͨ��','3','�˻�','4','������','5','�����','','�������', qjjg.shzt) shztmc,");
		sql
				.append(" decode(qjjg.xjzt, '', 'δ����', '0', 'δ����', '1', '������') xjztmc");
		// ���������Ϣ
		sql
				.append(" from xg_rcsw_qjgl_qjjg qjjg left join xg_rcsw_qjgl_qjlx b on 1=1 ");
		sql.append(" and  qjjg.qjlxid=b.qjlxid");
		// ѧ����Ϣ
		sql
				.append(" left join view_xsxxb xsxx on qjjg.xh=xsxx.xh )a where 1=1 ");*/
		
		
			sql.append(" select a.* from ( ");
			sql.append("  select a.*,a.kssj||'��'||a.jssj qjsjd  ");
			sql.append("   ,b.lddm,d.ch,c.ldmc,b.qsh,(case nvl(c.ldmc,'0') when '0' then '' else (c.ldmc||','||b.qsh||'��'||','||b.cwh||'�Ŵ�') end) qsxx,xjsq.qjjgid xjqjjgid,nvl(xjsq.shzt,'x') xjshzt,decode(xjsq.shzt,'0','δ�ύ','1','ͨ��','2','��ͨ��','3','���˻�','5','�����','δ����') xjshztmc ");
			if(Base.xxdm.equals("10695")){//���������ѧ���Ի�
				sql.append(" from view_new_dc_rcsw_qjjg_xzmz a ");
			}else if(Base.xxdm.equals("70002")){
				sql.append(" from view_new_dc_rcsw_qjjg_xzxy a ");
			}else{				
				sql.append("  from VIEW_NEW_DC_RCSW_QJJG a ");
			}
			sql.append("  left join xg_gygl_new_cwxxb b on a.xh = b.xh ");
			sql.append("  left join xg_qjgl_xjsqb xjsq on  a.qjjgid = xjsq.qjjgid ");
			sql.append("  left join xg_gygl_new_ldxxb c on b.lddm = c.lddm left join xg_gygl_new_qsxxb d on b.lddm=d.lddm and b.qsh=d.qsh ");
			sql.append(" ) a where 1=1 ");
		
		String searchTjQx="";
		String searchTjByGyfdy = " and lddm in (select lddm from xg_gygl_new_gyfdyb where yhm = '"+user.getUserName()+"')";	
		/* ���û�Ϊ��Ԣ����Աʱ��xg_gygl_new_gyfdyb �� ���ڸ��û�����
		 * ����������ΪѧԺ���߸���Ա��Ȩ�޿��ƣ�ֻ����¥�����ݷ�Χ
		 */
		if("yes".equalsIgnoreCase(user.getGyglyQx())&&Base.xxdm.equals(Globals.XXDM_ZJJJZYJSXY)){//�û�Ϊ��Ԣ����Ա
			searchTjQx = searchTjByGyfdy;
		}else{//�û��ǹ�Ԣ����Ա��������ѧУ����Ա��������ѧԺ����Ա
			searchTjQx = searchTjByUser;
		}
		sql.append(searchTjQx);
		sql.append(" ");
		sql.append(searchTj);
		return this.getPageList(t, sql.toString(), inputV);
	}

	/**
	 * 
	 * @����:����ѧ�Ż���б�
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-9-16 ����06:25:52
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getListForXh(String xh) {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from xg_rcsw_qjgl_qjjg where xh=?");
		return dao.getListNotOut(sql.toString(), new String[] { xh });
	}
	/**
	 * 
	 * @����:��ȡ�����Ϣ���ڴ�ӡ
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-4-22 ����03:18:53
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public HashMap<String, String> getQjjgForPrint(String qjjgid) {
		StringBuffer sql = new StringBuffer();
		sql.append("select a.*,b.shr1,b.shsj1,b.shyj1,b.shr2,b.shsj2,b.shyj2,b.shr3,b.shsj3, ");
		sql.append("b.shyj3,b.shr4,b.shsj4,b.shyj4 from xg_rcsw_qjgl_qjjg a left join( ");
		sql.append("select a.qjjgid,a.qjsqid, wm_concat(decode(rn,'1',shrxm,''))shr1,");
		sql.append("wm_concat(decode(rn,'1',shsj,''))shsj1,");
		sql.append("wm_concat(decode(rn,'1',shyj,''))shyj1,");
		sql.append("wm_concat(decode(rn,'2',shrxm,''))shr2,");
		sql.append("wm_concat(decode(rn,'2',shsj,''))shsj2,");
		sql.append("wm_concat(decode(rn,'2',shyj,''))shyj2,");
		sql.append("wm_concat(decode(rn,'3',shrxm,''))shr3,");
		sql.append("wm_concat(decode(rn,'3',shsj,''))shsj3,");
		sql.append("wm_concat(decode(rn,'3',shyj,''))shyj3,");
		sql.append("wm_concat(decode(rn,'4',shrxm,''))shr4,");
		sql.append("wm_concat(decode(rn,'4',shsj,''))shsj4,");
		sql.append("wm_concat(decode(rn,'4',shyj,''))shyj4");
		sql.append(" from(select row_number()over(partition by ");
		sql.append("a.qjjgid order by b.xh) rn,a.*,b.shyj,b.shrxm,b.shsj from xg_rcsw_qjgl_qjjg a");
		sql.append(" left join ( select * from (select a.*,b.xm shrxm,c.xh,");
		sql.append("  row_number() over(partition by a.ywid, a.gwid order by a.shsj desc) rs ");
		sql.append("  from xg_xtwh_shztb a left join yhb b on a.shr=b.yhm left join xg_xtwh_spbz c on ");
		sql.append("a.gwid=c.spgw and a.lcid=c.splc ) where rs = 1 ) b  on a.qjsqid = b.ywid )a ");
		sql.append(" group by a.qjjgid ,a.qjsqid)b on a.qjjgid=b.qjjgid where a.qjjgid=?");
		return dao.getMapNotOut(sql.toString(), new String[] { qjjgid });
	}
	/**
	 * 
	 * @����:��������Ϣ
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-9-14 ����04:36:50
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param qjjgid
	 * @return HashMap<String,String> ��������
	 * @throws
	 */
	public HashMap<String, String> getQjjg(String qjjgid) {
		StringBuffer sb = new StringBuffer();
		sb.append("select a.*,(select xqmc from xqdzb where xqdm=a.xq) xqmc,b.xm, b.bjmc, b.zymc, b.sjhm from xg_rcsw_qjgl_qjjg a");
		sb.append(",view_xsxxb b where a.xh=b.xh and a.qjjgid=?");
		return dao.getMapNotOut(sb.toString(), new String[] { qjjgid });
	}
	
	public boolean  haveNewSqjl(QjjgForm model) {
		StringBuffer sql = new StringBuffer();
		sql.append("select count(1) num from xg_rcsw_qjgl_qjsq where qjsqid<>? and kssj>? and xh=? and shzt in('1','5')");
		String num =  dao.getOneRs(sql.toString(), new String[] { model.getQjsqid(),model.getKssj(),model.getXh()},"num");
		return Integer.parseInt(num)>0;
	}
	@Override
	public QjjgForm getModel(QjjgForm t) throws Exception {
		String sql = "select t1.*,t2.qjlxmc,decode(t1.sflx,'1','��','0','��','') sflxmc from xg_rcsw_qjgl_qjjg t1 left join xg_rcsw_qjgl_qjlx t2 on t1.qjlxid=t2.qjlxid where t1.qjjgid=?";
		return super.getModel(sql, new String[]{t.getQjjgid()});
	}

	@Override
	public List<HashMap<String, String>> getPageList(QjjgForm t)
			throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());

		StringBuffer sql = new StringBuffer();
		sql
				.append(" select * from (select qjjg.*,(select xqmc from xqdzb where xqdm=qjjg.xq) xqmc,xsxx.xm,xsxx.xb,xsxx.nj,xsxx.xymc,xsxx.xydm,xsxx.zymc,xsxx.zydm,xsxx.bjmc,xsxx.bjdm,b.qjlxmc,");
		sql
				.append(" decode(qjjg.shzt,'0','δ���','1','ͨ��','2','��ͨ��','3','�˻�','4','������','5','�����','','�������', qjjg.shzt) shztmc,");
		sql
				.append(" decode(qjjg.xjzt, '', 'δ����', '0', 'δ����', '1', '������') xjztmc");
		// ���������Ϣ
		sql
				.append(" from xg_rcsw_qjgl_qjjg qjjg left join xg_rcsw_qjgl_qjlx b on 1=1 ");
		sql.append(" and  qjjg.qjlxid=b.qjlxid");
		// ѧ����Ϣ
		sql
				.append(" left join view_xsbfxx xsxx on qjjg.xh=xsxx.xh ) where 1=1 ");
		sql.append(" ");
		sql.append(searchTj);
		return this.getPageList(t, sql.toString(), inputV);
	}

	/**
	 * 
	 * @����:����������
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-9-12 ����11:00:14
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getQjlx() {
		StringBuffer sql = new StringBuffer();
		sql
				.append(" select qjlxid,qjlxmc from xg_rcsw_qjgl_qjlx order by qjlxid asc");
		return dao.getListNotOut(sql.toString(), new String[] {});
	}

	/**
	 * 
	 * @����:���������Ϣ
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-9-12 ����11:01:39
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param qjsqid
	 * @return HashMap<String,String> ��������
	 * @throws
	 */
	public HashMap<String, String> getXjxx(String qjsqid) {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from xg_rcsw_qjgl_qjjg where qjsqid=?");
		return dao.getMapNotOut(sql.toString(), new String[] { qjsqid });
	}

	/**
	 * 
	 * @����:�Ƿ����ɾ��
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-9-14 ����02:12:37
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param qjsqid
	 * @return boolean ��������
	 * @throws
	 */
	public boolean isCanDel(String qjjgid) {
		StringBuffer sb = new StringBuffer();
		sb.append("select * from xg_rcsw_qjgl_qjjg where qjjgid=?");
		Map<String, String> map = getQjjg(qjjgid);
		// Ϊ��ʦ����ʱ����ɾ��
		String qjzt = map.get("qjzt");
		return qjzt.equals("1") ? true : false;
	}

	/*
	 * ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	@Override
	protected void setTableInfo() {
		this.setKey("qjjgid");
		this.setTableName("xg_rcsw_qjgl_qjjg");
		this.setClass(QjjgForm.class);
	}

	
	/**��ѯ�����Ŀ�б�*/
	public List<HashMap<String,String>> getQjxmList(){
		
		String sql = "select * from XG_RCSW_QJMXDMB";
		return dao.getListNotOut(sql, new String[]{});
	}
	/**������˼�¼�Ƿ��ظ��ύ */
	public boolean isYtj(String sqid){
		String sql = "select count(1) num from xg_rcsw_qjgl_qjjg where qjsqid=? ";
		String num=dao.getOneRs(sql, new String[]{sqid}, "num");
		return Integer.parseInt(num)>0;
	}
	/**
	 * 
	 * @����:�ڹ���ѧ��������
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-4-22 ����05:05:25
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String,String> getCssz(){
		String sql ="select * from xg_rcsw_qjgl_cspzb where rownum=1";
		return dao.getMapNotOut(sql, new String[]{});
	}
	
	/**
	 * 
	 * @����:ȡ�����ڶ����������Ϣ
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-4-8 ����09:44:56
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param sqid
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> xjrpdTenD(String sqid) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from (select a.* from XG_XTWH_SHZTB a where ywid = ? order by shsj desc) where rownum <= 2 ");
        sql.append(" minus ");
        sql.append(" select * from (select a.* from XG_XTWH_SHZTB a where ywid = ? order by shsj desc) where rownum = 1 ");
        return dao.getMapNotOut(sql.toString(), new String[] { sqid, sqid });
	}
	
	/**
	 * 
	 * @����:ȡ���һ�����������Ϣ
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-4-8 ����11:18:42
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param sqid
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> xjrpdOneToN(String sqid) {
		StringBuffer sql = new StringBuffer();
        sql.append(" select * from (select a.* from XG_XTWH_SHZTB a where ywid = ? order by shsj desc) where rownum = 1 ");
        return dao.getMapNotOut(sql.toString(), new String[] { sqid });
	}
	
	/**
	 * �ƶ�������
	 */
	public List<HashMap<String, String>> getPageListXj(QjjgForm t, User user)
	throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		
		StringBuffer sql = new StringBuffer();
		/*sql
				.append(" select * from (select qjjg.*,(select xqmc from xqdzb where xqdm=qjjg.xq) xqmc,xsxx.xm,xsxx.xb,xsxx.nj,xsxx.xymc,xsxx.xydm,xsxx.zymc,xsxx.zydm,xsxx.bjmc,xsxx.bjdm,b.qjlxmc,");
		sql
				.append(" decode(qjjg.shzt,'0','δ���','1','ͨ��','2','��ͨ��','3','�˻�','4','������','5','�����','','�������', qjjg.shzt) shztmc,");
		sql
				.append(" decode(qjjg.xjzt, '', 'δ����', '0', 'δ����', '1', '������') xjztmc");
		// ���������Ϣ
		sql
				.append(" from xg_rcsw_qjgl_qjjg qjjg left join xg_rcsw_qjgl_qjlx b on 1=1 ");
		sql.append(" and  qjjg.qjlxid=b.qjlxid");
		// ѧ����Ϣ
		sql
				.append(" left join view_xsxxb xsxx on qjjg.xh=xsxx.xh )a where 1=1 ");*/


	sql.append(" select a.* from ( ");
	sql.append("  select a.*,a.kssj||'��'||a.jssj qjsjd  ");
	sql.append("   ,b.lddm,d.ch,c.ldmc,b.qsh,(case nvl(c.ldmc,'0') when '0' then '' else (c.ldmc||','||b.qsh||'��'||','||b.cwh||'�Ŵ�') end) qsxx,nvl(xj.shzt,'none') xjshzt ");
	sql.append("  ,decode(xj.shzt, '5', '�����', '1', '��ͨ��','0','δ�ύ','3','���˻�','δ����') xjshztmc ");
	if(Base.xxdm.equals("10695")){//���������ѧ���Ի�
		sql.append(" from view_new_dc_rcsw_qjjg_xzmz a ");
	}else if(Base.xxdm.equals("70002")){
		sql.append(" from view_new_dc_rcsw_qjjg_xzxy a ");
	}else{				
		sql.append("  from VIEW_NEW_DC_RCSW_QJJG a ");
	}
	sql.append("  left join xg_gygl_new_cwxxb b on a.xh = b.xh ");
	sql.append("  left join xg_gygl_new_ldxxb c on b.lddm = c.lddm left join xg_gygl_new_qsxxb d on b.lddm=d.lddm and b.qsh=d.qsh ");
	sql.append(" left join xg_qjgl_xjsqb xj on a.qjjgid = xj.qjjgid");
	sql.append(" ) a where 1=1 ");

	String searchTjQx="";
	String searchTjByGyfdy = " and lddm in (select lddm from xg_gygl_new_gyfdyb where yhm = '"+user.getUserName()+"')";	
	/* ���û�Ϊ��Ԣ����Աʱ��xg_gygl_new_gyfdyb �� ���ڸ��û�����
	 * ����������ΪѧԺ���߸���Ա��Ȩ�޿��ƣ�ֻ����¥�����ݷ�Χ
	 */
	if("yes".equalsIgnoreCase(user.getGyglyQx())&&Base.xxdm.equals(Globals.XXDM_ZJJJZYJSXY)){//�û�Ϊ��Ԣ����Ա
		searchTjQx = searchTjByGyfdy;
	}else{//�û��ǹ�Ԣ����Ա��������ѧУ����Ա��������ѧԺ����Ա
		searchTjQx = searchTjByUser;
	}
	sql.append(searchTjQx);
	sql.append(" ");
	sql.append(searchTj);
	return this.getPageList(t, sql.toString(), inputV);
}

	
}
