/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-8-5 ����08:47:43 
 */  
package com.zfsoft.xgxt.rcsw.rcxwwh.rcxwsh;

import java.util.HashMap;
import java.util.List;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �ճ���Ϊ����ģ��
 * @�๦������: �ճ���Ϊ���  
 * @���ߣ�Dlq [���ţ�995]
 * @ʱ�䣺 2013-8-5 ����08:47:43 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class RcxwshDao extends SuperDAOImpl<RcxwshForm> {

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		// TODO �Զ����ɷ������
		super.setKey("id");
		super.setTableName("xg_rcsw_rcxwxxwh");

	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(RcxwshForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(RcxwshForm t, User user)
			throws Exception {
		// TODO �Զ����ɷ������
		//���ɸ߼���ѯ�������������ֵ 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t5", "xydm", "bjdm");		
		String shgwzByUser = SearchService.getShgwzByUser(user, "t5", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append("select  t5.id,t5.guid shid,t5.xh,t5.xm,t5.bz,t5.gfly,t5.xb,t5.bjdm,t5.nj,t5.bjmc,t5.zybj,t5.zybjmc,t5.xydm,t5.xn,(select xqmc from xqdzb b where t5.xq=b.xqdm) xq,t5.rcxwlbdlmc,t5.rcxwlbmc,t5.rcxwlbdldm,t5.rcxwlbdm,t5.rcxwjlsj,t5.shztmc,t5.shzt,t5.gwid,t5.gwz,t5.shr,t5.shyj,t5.splc,t5.rn,t5.rcxwlbbz,t5.fssj,t5.jlrxm,t5.fz from (");
		sql.append(" select t1.id,t1.xh,t1.bz,t1.gfly,t2.xm,t2.xb,t2.bjmc,");
		sql.append("t2.lxdh,t1.xn,t1.xq,t2.xydm,t2.zydm,t2.bjdm,t2.nj,t2.zybj,t2.zybjmc,");
		sql.append("t1.rcxwjlsj,t3.rcxwlbmc,t3.rcxwlbdlmc,t1.shzt,t3.rcxwlbdldm,t3.rcxwlbdm, d.gwid,d.shr,d.shyj,d.guid,t1.splc,"); 
		sql.append("f.mc ||'['|| decode(d.shzt,'0','�����','1','ͨ��','2','��ͨ��',");
		sql.append("'3','�˻�','4','������','5','�����' ) ||']' shztmc,f.gwlx,f.gwz ");
		sql.append(" ,row_number()over(partition by t1.id order by d.shsj desc) rn ");
		sql.append(",t3.rcxwlbbz,t1.fssj,(select xm from yhb y where y.yhm=t1.jlr) jlrxm,(case when rcxwfzlx = '01' then '+' || t1.fz else '-' || t1.fz end) fz ");
		sql.append(" from xg_rcsw_rcxwxxwh t1 ");
		sql.append(" left join view_xsxxb t2 on t1.xh=t2.xh ");
		sql.append(" left join (select *  from (select a.*,b.rcxwlbdlmc, ");
		sql.append(" case when b.sqkg=1 and sysdate between to_date(nvl(b.sqkssj,'1990-01-01'),'yyyy-mm-dd') and to_date(nvl(b.sqjssj,'2200-01-01'),'yyyy-mm-dd')+1 then 'true' else 'false' end isopen ");
		sql.append(" from xg_rcsw_rcxwlbdmb a left join ");
		sql.append(" xg_rcsw_rcxwdbdlb b on a.rcxwlbdldm = b.rcxwlbdldm) where sfqy='1' and isopen='true') t3 ");
		sql.append(" on t1.rcxwlbdm =t3.rcxwlbdm ");
		sql.append(" left join xg_xtwh_shztb d on t1.id = d.ywid ");
		String shlx = t.getShzt();
		if(!shlx.equals("dsh")){
			sql.append(" and (d.shzt<>0 and  d.shzt<>4) ");
		}else{
			sql.append(" and (d.shzt=0  or d.shzt = 4 )" );
		}
	
		sql.append(" left join xg_xtwh_spgwyh e on d.gwid = e.spgw  left join  xg_xtwh_spgw f on d.gwid = f.id where e.spyh = '"+user.getUserName()+"'   ");
		sql.append(" and t3.rcxwlbdm is not null ");
		sql.append(" )t5 where 1=1 ");
		sql.append(" and  rn = 1 "); //ȡ������һ��
		
		sql.append(searchTjByUser);
		sql.append(searchTj);
		sql.append(shgwzByUser);
		return getPageList(t, sql.toString(), inputV);
	}
	
	/**
	 * ������������Ϣ
	 * @���ߣ�Dlq [���ţ�995]
	 * @���ڣ�2013-8-13 ����11:45:06
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String,String>  getSplcInfo(RcxwshForm t){
		
		StringBuilder sql = new StringBuilder();
		sql.append("select  t5.fjlj,t5.fjmc,t5.id,t5.xn,t5.xq,t5.fz,t5.xqmc,t5.xh,t5.fzqj,t5.xm,t5.xb,t5.bjmc,t5.rcxwlbdlmc,rcxwlbmc,t5.rcxwjlsj,t5.shztmc,t5.gfly,t5.bz,t5.splc,t5.rcxwlbbz,t5.fssj,t5.jlrxm,t5.sqfz,t5.rcxwfzlx from (");
		sql.append(" select t1.fjlj,t1.fjmc,t1.id,t1.xh,t2.xm,t2.xb,t2.sfzh,t2.nj,t2.xymc,t2.zymc,t2.bjmc,t1.gfly,t1.bz,t2.zzmmmc,t1.splc,t3.rcxwfzlx,");
		sql.append("t2.lxdh,t1.xn,t1.xq,t2.xydm,t2.zydm,t2.bjdm,");
		sql.append("t1.rcxwjlsj,t3.rcxwlbmc,t3.rcxwlbdlmc,t1.shzt,(case when rcxwfzlx = '01' then '+' || t1.fz else '-' || t1.fz end) fz,t1.fz sqfz,t3.rcxwlbdldm,t3.rcxwlbdm,t4.xqmc,t3.rcxwlbzdfz||'-'||t3.rcxwlbzgfz fzqj,"); 
		sql.append("decode(t1.shzt,'0','�����','1','ͨ��','2','��ͨ��',");
		sql.append("'3','�˻�','4','������','5','�����',t1.shzt) shztmc ");
		sql.append(",t3.rcxwlbbz,t1.fssj,(select xm from yhb y where y.yhm=t1.jlr) jlrxm ");
		sql.append(" from xg_rcsw_rcxwxxwh t1 ");
		sql.append(" left join view_xsxxb t2 on t1.xh=t2.xh ");
		sql.append(" left join (select *  from (select a.*,b.rcxwlbdlmc  from xg_rcsw_rcxwlbdmb a left join ");
		sql.append(" xg_rcsw_rcxwdbdlb b on a.rcxwlbdldm = b.rcxwlbdldm )) t3 ");
		sql.append(" on t1.rcxwlbdm =t3.rcxwlbdm left join  xqdzb t4 on t1.xq = t4.xqdm ) t5  where t5.id=? ");
		
		return dao.getMapNotOut(sql.toString(), new String[]{t.getId()});
	}
	
	public boolean updateRcxwxx(String id,String shzt) throws Exception{
		String[] inputV = new String[2];
		StringBuilder sql = new StringBuilder();
		sql.append(" UPDATE xg_rcsw_rcxwxxwh ");
		sql.append(" set ");
		sql.append(" shzt = ?");
		sql.append(" where id = ?");
		inputV[0] = shzt;
		inputV[1] = id;
		return dao.update(sql.toString(),inputV)>0 ? true:false;
	}

	public boolean deleteRcxwjg(String id) throws Exception{
		String[] inputV = new String[1];
		StringBuilder sql = new StringBuilder();
		sql.append(" delete from  xg_rcsw_rcxwjg ");
		sql.append(" where rcxwxxid = ?");
		inputV[0] = id;
		return dao.runDelete(sql.toString(),inputV)>0 ? true:false;
	}
}
