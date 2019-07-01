package com.zfsoft.xgxt.jskp.sbsh;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.jskp.cssz.CsszDao;

/**
 * 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��ʵ����
 * @�๦������: �걨��� 
 * @���ߣ� xiaxia[����:1104]
 * @ʱ�䣺 2017-7-7 ����02:07:55 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class JskpXmsbshDao extends SuperDAOImpl<JskpXmsbshForm>{


	
	@Override
	public List<HashMap<String, String>> getPageList(JskpXmsbshForm t) throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	
	@Override
	public List<HashMap<String, String>> getPageList(JskpXmsbshForm t, User user) throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		StringBuffer sql = new StringBuffer();
		String sfsh = new CsszDao().getSfsh();
		sql.append("select t.* from (");
		sql.append("select t7.xm fzrxm,t8.bmmc,t1.sqid,t1.xh,t1.xmid,t1.hjsj,t1.splcid splc,t1.sbsj, ");
		sql.append("t3.zdf,t3.zxf,t3.zdls,t3.xmlb,t3.zdbm,t3.xmmc ");
		/**��ͨ��������˵�һ��*/
		if("0".equals(sfsh)){
			sql.append(",'������' xmdlmc,'zlx' xmdl,");
			sql.append("t4.shzt,t2.xm,t2.xydm,t2.xymc,t2.zydm,t2.zymc,t2.bjdm,t2.bjmc,t2.nj, ");
			sql.append("t4.guid shid,t4.gwid,t4.shr,t4.shyj,t6.mc || '[' ||");
			sql.append("decode(t4.shzt, '0', 'δ���', '1', 'ͨ��', '2', '��ͨ��', '3',  '�˻�', '4', '������', '5', '�����') || ']' shztmc,t6.gwz, ");
			sql.append(" row_number() over(partition by t1.sqid order by t4.shsj desc) rn ");
			sql.append("from xg_jskp_xmsbb t1 left join view_xsjbxx t2 on t1.xh = t2.xh  ");
			sql.append(" left join xg_jskp_xmsqb t3 on t1.xmid = t3.sqid ");
			sql.append(" left join xg_xtwh_shztb t4 on t1.sqid = t4.ywid");
			sql.append(" left join xg_xtwh_spgwyh t5 on  t4.gwid = t5.spgw left join xg_xtwh_spgw t6 on t4.gwid = t6.id ");
			sql.append(" left join (select yhm yhm, xm from yhb union all select xh yhm, xm from xsxxb) t7 on t3.fzr = t7.yhm ");
			sql.append(" left join zxbz_xxbmdm t8 on t3.zdbm=t8.bmdm where t5.spyh ='" + user.getUserName() + "' ");
			String shlx = t.getShzt();
			if (!shlx.equals("dsh")) {
				sql.append(" and (t4.shzt<>0 and  t4.shzt<>4)");
			} else {
				sql.append(" and (t4.shzt=0  or t4.shzt = 4 )");
			}
			sql.append(" and t3.sjly = 'NO'");
			sql.append(" ) t where 1=1 ");
			sql.append(" and  rn = 1 ");
			sql.append(searchTjByUser);
			sql.append(searchTj);
		}else{
			sql.append(",decode(t3.xmdl, 'gdx', '�̶���', '������') xmdlmc,t3.xmdl,");
			sql.append("t4.shzt,t2.xm,t2.xydm,t2.xymc,t2.zydm,t2.zymc,t2.bjdm,t2.bjmc,t2.nj, ");
			sql.append("t4.guid shid,t4.gwid,t4.shr,t4.shyj,t6.mc || '[' ||");
			sql.append("decode(t4.shzt, '0', 'δ���', '1', 'ͨ��', '2', '��ͨ��', '3',  '�˻�', '4', '������', '5', '�����') || ']' shztmc,t6.gwz, ");
			sql.append(" row_number() over(partition by t1.sqid order by t4.shsj desc) rn ");
			sql.append("from xg_jskp_xmsbb t1 left join view_xsjbxx t2 on t1.xh = t2.xh  ");
			sql.append(" left join xg_jskp_xmjgb t3 on t1.xmid = t3.xmid ");
			sql.append(" left join xg_xtwh_shztb t4 on t1.sqid = t4.ywid");
			sql.append(" left join xg_xtwh_spgwyh t5 on  t4.gwid = t5.spgw left join xg_xtwh_spgw t6 on t4.gwid = t6.id ");
			sql.append(" left join yhb t7 on t3.fzr = t7.yhm ");
			sql.append(" left join zxbz_xxbmdm t8 on t3.zdbm=t8.bmdm where t5.spyh ='" + user.getUserName() + "' ");
			String shlx = t.getShzt();
			if (!shlx.equals("dsh")) {
				sql.append(" and (t4.shzt<>0 and  t4.shzt<>4)");
			} else {
				sql.append(" and (t4.shzt=0  or t4.shzt = 4 )");
			}
			sql.append(" and t3.sjly <> 'NO'");
			sql.append(" ) t where 1=1 ");
			sql.append(" and  rn = 1 ");
			sql.append(searchTj);
		}
		return getPageList(t, sql.toString(), inputV);
		
	}
	/**
	 * 
	 * @����:���������Ϣ
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2017-7-7 ����02:28:02
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> getSbshInfo(JskpXmsbshForm t) {
		StringBuilder sql = new StringBuilder();
		String sfsh = new CsszDao().getSfsh();
		/**������Ŀ����ˣ���������Ϊ��ʱ*/
		if("0".equals(sfsh)){
			sql.append("select t1.*,t2.xm,t2.xydm,t2.xymc,t2.zydm,t2.zymc,t2.bjdm,t2.bjmc,t2.nj, t3.xmmc,t4.xmlbmc");
			sql.append(",'������' xmdlmc,");
			sql.append(" t3.zdf,t3.zxf,t5.bmmc zddwmc,t6.xm fzrxm,t3.fzrlxfs,decode(t1.shzt,  '0', 'δ���', '1', 'ͨ��', '2', '��ͨ��', '3', ");
			sql.append(" '�˻�', '4', '������', '5', '�����', '', '�������', t1.shzt) shztmc,");
			sql.append(" case when length(t3.sjlrr) = '10' then 'ѧ����������' when length(t3.sjlrr) <> '10' then  '������Ϊ'||t9.bmmc||','||t9.xm||'('||'ְ���ţ�'||t9.yhm||')'||'¼��' else t3.sjlrr end sjlyfs");
			sql.append(" from xg_jskp_xmsbb t1 ");
			sql.append(" left join view_xsbfxx t2 on t1.xh = t2.xh ");
			sql.append(" left join xg_jskp_xmsqb t3 on t1.xmid = t3.sqid ");
			sql.append(" left join xg_jskp_xmlbb t4 on t3.xmlb=t4.xmlbdm left join zxbz_xxbmdm t5 on t3.zdbm=t5.bmdm");
			sql.append(" left join (select yhm yhm, xm from yhb union all select xh yhm, xm from xsxxb)t6 on t3.fzr = t6.yhm ");
			sql.append(" left join (select t7.yhm,t7.xm,t7.szbm,t8.bmmc from yhb t7 left join zxbz_xxbmdm t8 on t7.szbm = t8.bmdm) t9 on t3.sjlrr = t9.yhm ");
			sql.append(" where t1.sqid = ? ");
		}else{
			sql.append("select t1.*,t2.xm,t2.xydm,t2.xymc,t2.zydm,t2.zymc,t2.bjdm,t2.bjmc,t2.nj, t3.xmmc,t4.xmlbmc");
			sql.append(",decode(t3.xmdl,'gdx','�̶���','������') xmdlmc,");
			sql.append(" t3.zdf,t3.zxf,t5.bmmc zddwmc,t6.xm fzrxm,t3.fzrlxfs,decode(t1.shzt,  '0', 'δ���', '1', 'ͨ��', '2', '��ͨ��', '3', ");
			sql.append(" '�˻�', '4', '������', '5', '�����', '', '�������', ");
			sql.append(" t1.shzt) shztmc from xg_jskp_xmsbb t1 ");
			sql.append(" left join view_xsbfxx t2 on t1.xh = t2.xh ");
			sql.append(" left join xg_jskp_xmjgb t3 on t1.xmid = t3.xmid ");
			sql.append(" left join xg_jskp_xmlbb t4 on t3.xmlb=t4.xmlbdm left join zxbz_xxbmdm t5 on t3.zdbm=t5.bmdm");
			sql.append(" left join yhb t6 on t3.fzr = t6.yhm ");
			sql.append(" where t1.sqid = ? ");
		}
		return dao.getMapNotOut(sql.toString(), new String[] { t.getSqid() });
	}
	/**
	 * 
	 * @����:���������¼
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-1-20 ����06:57:10
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ywid
	 * @param tzhxmdm
	 * @param shzt
	 * @param dqxmdm
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean updateSqjl(String ywid, String shzt,String yxgs) throws Exception{
		String sql = "update xg_jskp_xmsbb set shzt=? ,yxgs=? where sqid = ?";
		
		return dao.runUpdate(sql, new String[]{shzt,yxgs,ywid});
		
	}
	
	@Override
	protected void setTableInfo() {
		super.setClass(JskpXmsbshForm.class);
		super.setKey("sqid");
		super.setTableName("xg_jskp_xmsbb");
		
	}
	
	/**
	 * 
	 * @����:����ID��ѯ�����ж���������λ
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2017-8-21 ����04:26:44
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param id
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getShxhForId(String id){
		
		StringBuffer sql = new StringBuffer();
		String[] ids = id.split(",");
		List<String> params = new ArrayList<String>();
		
		sql.append("select xh,count(1)count from ( ");
		sql.append("select decode(t3.xh,'1','first','','emp','second')xh from xg_jskp_xmsbb t1 ");
		sql.append("left join xg_xtwh_shztb t2 on t1.sqid = t2.ywid and shr is null ");
		sql.append("left join xg_xtwh_spbz t3 on t2.gwid = t3.spgw and t2.lcid = t3.splc where t1.sqid in ( ");
		for (int i = 0; i < ids.length; i++) {
			if(i!=0){
				sql.append(",");
			}
			sql.append(" ? ");
			params.add(ids[i]);
			
		}
		sql.append(")) group by xh");
		
		return dao.getListNotOut(sql.toString(), params.toArray(new String[]{}));
	}
	
	/**
	 * 
	 * @����:����id��ȡǰһ�η���
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2017-8-22 ����04:14:14
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param id
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getBeforeMark(String[] ids){
		
		StringBuffer sql = new StringBuffer();
		List<String> params = new ArrayList<String>();
		
		sql.append("select * from ( ");
		sql.append("select sqid,t3.xh,t2.shr,t2.shsj,lag(t2.zd3,1) over(partition by sqid order by sqid,shsj) sjfs ");
		sql.append("from xg_jskp_xmsbb t1 ");
		sql.append("left join xg_xtwh_shztb t2 on t1.sqid = t2.ywid ");
		sql.append("left join xg_xtwh_spbz t3 on t2.gwid = t3.spgw and t2.lcid = t3.splc ");
		sql.append("where sqid in (");
		
		for (int i = 0; i < ids.length; i++) {
			if(i!=0){
				sql.append(",");
			}
			sql.append(" ? ");
			params.add(ids[i]);
			
		}
		
		sql.append(") ) where shr is null and xh >1 ");
		return dao.getListNotOut(sql.toString(), params.toArray(new String[]{}));
		
	}
	
	/**
	 * @����: ��������idȡ���״̬���е�����һ�� �ķ�����zd3��
	 * @���ߣ�Meng.Wei[���ţ�1186]
	 * @���ڣ�2018-1-17 ����11:03:59
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param id
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public HashMap<String, String> getLevelXxBySqid(JskpXmsbshForm t)throws Exception{
		
		StringBuffer sql = new StringBuffer();
		sql.append("select * from (select * from xg_xtwh_shztb where shsj is not null order by shsj desc) ");
		sql.append("where ywid = ? and rownum = 1 ");
		
		return dao.getMapNotOut(sql.toString(), new String[]{t.getSqid()});
	}
}
