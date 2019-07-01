/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-7-22 ����10:46:55 
 */  
package com.zfsoft.xgxt.xsztz.tttzxm.sh;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.xsztz.tttzxm.sq.TttzxmForm;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� yxy[����:1206]
 * @ʱ�䣺 2016-7-22 ����10:46:55 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class TttzxmShDao extends SuperDAOImpl<TttzxmShForm> {

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(TttzxmShForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(TttzxmShForm t, User user)
			throws Exception {
		// TODO �Զ����ɷ������
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		String shgwzByUser = SearchService.getSearchSztzShTjByUser(user, "t", "sbbmdm", "sbr");
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (");
		sql.append(" select ");
		sql.append(" t.*,");
		sql.append(" nvl(t1.tdnum,0) tdnum,");
		sql.append(" t2.xm dzxm,");
		sql.append(" t2.xb,");
		sql.append(" t2.xymc,");
		sql.append(" t2.xydm,");
		sql.append(" t2.nj,");
		sql.append(" t2.zydm,");
		sql.append(" t2.zymc,");
		sql.append(" t2.bjdm,");
		sql.append(" t2.bjmc,");
		sql.append(" t3.xmmc,");
		sql.append(" t3.xmkssj,");
		sql.append(" t4.xmjbmc,");
		sql.append(" t5.guid shid,");
		sql.append(" t5.gwid,");
		sql.append(" t5.shr,");
		sql.append(" t5.shyj,");
		sql.append(" t3.xfrdsqzt,");
		sql.append(" t3.xfrdjgzt,");
		sql.append(" t3.xn,");
		sql.append(" t3.xq,");
		sql.append(" t3.sbbmdm,");
		sql.append(" t3.sskmdm,");
		sql.append(" t3.xmjbdm,");
		sql.append(" t8.sskmmc,");
		sql.append(" t.dzxh xh,");
		sql.append(" t7.mc || '[' ||");
		sql.append(" decode(t5.shzt,");
		sql.append("  '0',");
		sql.append(" 'δ���',");
		sql.append(" '1',");
		sql.append(" 'ͨ��',");
		sql.append(" '2',");
		sql.append(" '��ͨ��',");
		sql.append(" '3',");
		sql.append(" '�˻�',");
		sql.append(" '4',");
		sql.append(" '������',");
		sql.append(" '5',");
		sql.append(" '�����') || ']' shztmc,");
		sql.append(" t7.gwz,");		
		sql.append(" row_number() over(partition by t.ttsqid order by t5.shsj desc) rn");
		sql.append(" from xg_sztz_ttxmsq t ");
		sql.append(" left join (select count(1) tdnum,xmdm,ttsqid from xg_sztz_ttcy group by xmdm,ttsqid) t1");
		sql.append(" on t.ttsqid = t1.ttsqid");
		sql.append(" left join  view_xsjbxx t2");
		sql.append(" on t.dzxh = t2.xh");
		sql.append(" left join xg_sztz_xmjg t3");
		sql.append(" on t.xmdm = t3.xmdm");
		sql.append(" left join xg_sztz_xmjb t4");
		sql.append(" on t3.xmjbdm = t4.xmjbdm");
		sql.append(" left join xg_xtwh_shztb t5");
		sql.append(" on t.ttsqid = t5.ywid");
		sql.append(" left join xg_xtwh_spgwyh t6");
		sql.append(" on t5.gwid = t6.spgw");
		sql.append(" left join xg_xtwh_spgw t7");
		sql.append(" on t5.gwid = t7.id");
		sql.append(" left join xg_sztz_sskm t8");
		sql.append(" on t3.sskmdm = t8.sskmdm");
		sql.append(" where t6.spyh ='" + user.getUserName() + "' ");
		String shlx = t.getShzt();
		if (!shlx.equals("dsh")) {
			sql.append(" and (t5.shzt<>0 and  t5.shzt<>4)");
		} else {
			sql.append(" and (t5.shzt=0  or t5.shzt = 4 )");
		}
		sql.append(" ");
		sql.append(" ");
		sql.append(" ");
		sql.append(") t where 1= 1  ");
		sql.append(" and  rn = 1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		sql.append(shgwzByUser);
		return getPageList(t, sql.toString(), inputV);
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		// TODO �Զ����ɷ������
		this.setClass(TttzxmShForm.class);
		this.setTableName("xg_sztz_ttxmsq");
		this.setKey("ttsqid");
	}
	
   /**
    * 
    * @����:��ȡ��Ŀ�����õ��������Ƽ���
    * @���ߣ�yxy[���ţ�1206]
    * @���ڣ�2016-7-28 ����11:24:49
    * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
    * @param xmdm
    * @return
    * String �������� 
    * @throws
    */
   public String getRskzXh(String xmdm){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select xh from xg_sztz_xmjg a left join XG_XTWH_SPBZ b on a.splc=b.splc and b.spgw=(replace(a.rskzjb,',','')) where xmdm = ? ");
		return dao.getOneRs(sql.toString(), new String[]{xmdm}, "xh");
	}
   	
   	/**
   	 * @����:��ȡ��Ŀ��Ϣ
   	 * @���ߣ�yxy[���ţ�1206]
   	 * @���ڣ�2016-7-28 ����11:28:04
   	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
   	 * @param xmdm
   	 * @return
   	 * @throws Exception
   	 * HashMap<String,String> �������� 
   	 * @throws
   	 */
	public HashMap<String, String> getDataById(String xmdm) throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append(" select * ");
		sb.append(" from  xg_sztz_xmjg ");
		sb.append(" where xmdm=?");
		String[] inputValue = { xmdm };
		return dao.getMapNotOut(sb.toString(), inputValue);
	}
	
	/**
	 * 
	 * @����:��ȡ����������Ƶ�ͨ������
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-7-28 ����11:28:55
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param gwid
	 * @param xmdm
	 * @param xn
	 * @param xq
	 * @return
	 * String �������� 
	 * @throws
	 */
    public String getTgrs( String gwid, String xmdm){
		
		StringBuilder sql = new StringBuilder();
		sql.append("  select a.sqs + b.jgs count");
		sql.append("  from (select count(1) sqs");
		sql.append("  from (select t1.ttsqid,");
		sql.append("  t2.shzt,");
		sql.append("  t2.gwid,");
		sql.append("  t1.shzt flag,");
		sql.append("  t1.xmdm,");
		sql.append("  row_number() over(partition by t1.ttsqid, t2.gwid order by t2.shsj desc) lvl");
		sql.append("  from xg_sztz_ttxmsq t1");
		sql.append("  left join xg_xtwh_shztb t2");
		sql.append("  on t1.ttsqid = t2.ywid)");
		sql.append("  where lvl = 1");
		sql.append("  and shzt = '1'");
		sql.append("  and gwid = ?");
		sql.append("  and xmdm = ?");
		sql.append("  and flag != '2' and flag != '3') a,");//���˵���ͨ�������˻ص�����
		sql.append("  (select count(1) jgs");
		sql.append("   from xg_sztz_ttxmjg");
		sql.append("  where ttjgid not in");
		sql.append("  (select ttsqid from xg_sztz_ttxmsq where xmdm = ?)");
		sql.append("  and xmdm = ?) b");
		sql.append("   where 1 = 1");
		return dao.getOneRs(sql.toString(), new String[]{gwid,xmdm,xmdm,xmdm}, "count");
	}
}
