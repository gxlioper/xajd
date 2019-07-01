/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-8-15 ����11:39:31 
 */
package com.zfsoft.xgxt.khgl.khxmgl;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ������Ŀ����ģ��
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� xiaxia [����:1104]
 * @ʱ�䣺 2015-8-15 ����11:39:31
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class KhxmglDao extends SuperDAOImpl<KhxmglForm> {
	
	@Override
	public List<HashMap<String, String>> getPageList(KhxmglForm t, User user)
			throws Exception {
		List<String> params = new ArrayList<String>();
		
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (");
		sql.append("select t1.*,t2.khdxmc,nvl2(t3.num,'1','0')sfypf,nvl2(t4.num,'1','0')pfdxsz from xg_khgl_khxm t1 left join xg_khgl_khdx t2 on t1.khdxid=t2.khdxid ");
		sql.append("left join(select count(1) num,xmid from ");
		sql.append(" xg_khgl_khpf where sftj='1' group by xmid)t3 on t1.xmid=t3.xmid left join (select count(1) num,xmid from xg_khgl_khxm_sz group by xmid)t4 on t1.xmid=t4.xmid");
		sql.append("  ) t where 1=1 ");
		if (!StringUtil.isNull(t.getXmmc())) {
			params.add(t.getXmmc());
			sql.append(" and t.xmmc like '%'||?||'%'");
		}
		sql.append(" order by kssj desc");
		return getPageList(t, sql.toString(), params.toArray(new String[] {}));
	}
	@Override
	public List<HashMap<String, String>> getPageList(KhxmglForm t) throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}
	
	@Override
	public KhxmglForm getModel(KhxmglForm model) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select t1.*,t2.khdxmc,nvl2(t3.num,'1','0') sfysy from xg_khgl_khxm t1 left join xg_khgl_khdx t2 on t1.khdxid=t2.khdxid");
		sql.append(" left join (select count(1) num,xmid from ");
		sql.append(" xg_khgl_khpf where sftj='1' group by xmid)t3 on t1.xmid=t3.xmid ");
		sql.append(" where t1.xmid=?");
		return getModel(sql.toString(), new String[]{model.getXmid()});
	}
	
	/**
	 * 
	 * @����:�жϿ��˱��Ƿ�ʹ��
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-8-18 ����02:13:10
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param khbid
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String,String> isUsed(String xmid) {
		StringBuffer sql = new StringBuffer();
		sql.append("select t1.xmmc,t2.num from xg_khgl_khxm t1 left join (select count(1) num,xmid from ");
		sql.append(" xg_khgl_khpf where sftj='1' group by xmid)t2 on t1.xmid=t2.xmid where t1.xmid=?");
 		return dao.getMapNotOut(sql.toString(), new String[]{xmid});
	}
	/**
	 * 
	 * @����:�жϿ��˱������Ƿ����
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-9-1 ����10:30:00
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xmmc
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public boolean isHave(KhxmglForm model) {
		StringBuffer sql = new StringBuffer();
		sql.append("select count(1) num from xg_khgl_khxm where xmmc=?");
		if(null!=model.getXmid()){
			sql.append(" and xmid<>'"+model.getXmid()+"' ");	
		}
 		String num= dao.getOneRs(sql.toString(), new String[]{model.getXmmc()}, "num");
 		return Integer.parseInt(num)>0;
	}
	
	
	
	/**
	 * @throws Exception
	 * 
	 * @����:��ȡ������Ŀ��Ϣ
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-8-15 ����04:38:03
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return HashMap<String,String> ��������
	 * @throws
	 */
	public HashMap<String,String> getKhxm(String xmid) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select t1.*,t2.khdxmc,t2.khlx from xg_khgl_khxm t1 left join xg_khgl_khdx t2 on t1.khdxid=t2.khdxid");
		sql.append(" where t1.xmid=?");
		return dao.getMapNotOut(sql.toString(), new String[] { xmid});
	}
	
	public HashMap<String,String> getKhxmByXmmc(String xmmc) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (select t1.*,t3.khbid,t3.xmszid,t4.khbmc from xg_khgl_khxm t1 ");
		sql.append(" left join xg_khgl_khxm_sz t3 on t1.xmid=t3.xmid  ");
		sql.append("left join xg_khgl_khb t4 on t3.khbid=t4.khbid where t1.xmmc like '%'||?||'%' order by t1.xmid desc)where rownum=1");
		return dao.getMapNotOut(sql.toString(), new String[] { xmmc});
	}
	

	/**
	 * 
	 * ɾ��������Ŀ���ֶ�����Ϣ
	 */
	public boolean delPfdxSz(String xmid) throws Exception {
		String sql = "delete from xg_khgl_khxm_sz where xmid=?";
		return dao.runUpdate(sql, new String[] { xmid });
	}
	
	public boolean PfdxSzPlbc(List<String[]> params) throws SQLException {
		String sql = "insert into xg_khgl_khxm_sz(xmid,pfzid,sjfwdm,khbid,szqz,jsfs,jsfsbfb) values(?,?,?,?,?,?,?)";
		int[] result = dao.runBatch(sql, params);
		return dao.checkBatchResult(result);
	}
	/**
	 * 
	 * @����:������Ŀ�б�
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-8-15 ����03:27:05
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * @throws Exception
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getKhxmList() throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select *from xg_khgl_khxm");
		return dao.getListNotOut(sql.toString(), new String[]{});
	}
	
	/**
	 * 
	 * @����:������Ŀ�б�
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-8-15 ����03:27:05
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * @throws Exception
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getpfdxXxList(String xmid) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select t1.*,t2.pfzmc,t2.sfnz,t3.khbmc,t4.sjfw,case when t1.jsfs='0' then 'ƽ����' when t1.jsfs='1' then 'ȥͷβ��ƽ��' else 'ȥͷβ��ƽ����������' end jsfsmc from xg_khgl_khxm_sz t1");
		sql.append(" left join xg_khgl_pfz t2 on t1.pfzid=t2.pfzid");
		sql.append(" left join xg_khgl_khb t3 on t1.khbid=t3.khbid left join");
		sql.append(" (select a.*,b.sjfwdm,b.sjfw from xg_khgl_pfz a left join xg_khgl_pfz_nz b on a.pflx=b.pflx left join xg_khgl_pfz c on a.pfzid= c.pfzid where a.sfnz='2')t4");
		sql.append(" on t1.sjfwdm=t4.sjfwdm and t1.pfzid=t4.pfzid");
		sql.append(" where xmid=?");
		return dao.getListNotOut(sql.toString(), new String[]{xmid});
	}
	/**
	 * 
	 * @����:���ַ�Χ�б�
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-8-15 ����01:51:02
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param pfzid
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getPffwList(String pfzid,String khlx) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select * from xg_khgl_pfz a left join xg_khgl_pfz_nz b on a.pflx=b.pflx where a.sfnz='2' and a.pfzid=? and  (b.khlx=? or khlx='3')");
		return dao.getListNotOut(sql.toString(), new String[]{pfzid,khlx});
	}
	
	public boolean delPfdxSz(String[] xmid) throws Exception {
		StringBuffer  sql = new StringBuffer();
		 sql.append("delete from xg_khgl_khxm_sz  where xmid in(");
		for (int i = 0; i < xmid.length; i++) {
			if(i!=0){
				sql.append(",");
			}
			sql.append("?");
		}
		sql.append(")");
		return dao.runUpdate(sql.toString(), xmid);
	}
	@Override
	protected void setTableInfo() {
		super.setClass(KhxmglForm.class);
		super.setKey("xmid");
		super.setTableName("xg_khgl_khxm");

	}
	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	
	
}
