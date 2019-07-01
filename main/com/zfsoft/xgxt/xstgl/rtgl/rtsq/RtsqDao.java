/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-12-5 ����02:21:41 
 */
package com.zfsoft.xgxt.xstgl.rtgl.rtsq;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.axcs.wpsz.WpszForm;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.xstgl.rtgl.rtjg.RtjgForm;
import com.zfsoft.xgxt.xsztz.tzxmsq.XsXmSqForm;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ����ģ��
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� xiaxia [����:1104]
 * @ʱ�䣺2015-8-6 ����02:21:41
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class RtsqDao extends SuperDAOImpl<RtsqForm> {

	@Override
	public List<HashMap<String, String>> getPageList(RtsqForm model) throws Exception {
		return null;
	}

	@Override
	public List<HashMap<String, String>> getPageList(RtsqForm model, User user) throws Exception {

		// TODO �Զ����ɷ������
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (");
		sql.append("select t1.xh,t2.xm,");
		sql.append("t1.rtid,");
		sql.append("t1.stid,");
		sql.append("t1.sqly,");
		sql.append("t1.tc,");
		sql.append("t1.rtxn,");
		sql.append("t1.rtxq,");
		sql.append("t1.shzt,");
		sql.append("t5.stxmmc,");
		sql.append("t3.xmlbdm,");
		sql.append("t1.sqsj,");
		sql.append("t1.splc,");
		sql.append("t2.zydm,");
		sql.append("t2.zymc,");
		sql.append("t2.bjdm,");
		sql.append("t2.bjmc,");
		sql.append("t2.xydm,");
		sql.append("t2.xymc,");
		sql.append("t2.nj,");
		sql.append("t2.xb,");
		sql.append("t3.xmlbmc,");
		sql.append("t4.stlbdm,");
		sql.append("t4.stlbmc,");
		sql.append("t5.zdls,");
		sql.append("t5.xn, ");
		sql.append("t5.kssj, ");
		sql.append("t5.stxj,");
		sql.append("t5.jssj, ");
		sql.append("t5.stfzr, ");
		sql.append("t5.jtr, ");
		sql.append(" case when t5.sqkg = 1 and sysdate between to_date(nvl(t5.sqkssj,'1990-01-01 00:00'),'yyyy-mm-dd hh24:mi')");
		sql.append(" and to_date(nvl(t5.sqjssj,'9999-01-01 00:00'),'yyyy-mm-dd hh24:mi') + 1 then '1' else '0' end sqkg, ");
		sql.append(" decode(t1.shzt,'0','δ�ύ','1','ͨ��','2','��ͨ��','3','�˻�','5','�����',t1.shzt) shztmc,");
		sql.append(" t6.xm zdlsxm");
		sql.append(" from xg_rtgl_rtsq t1");
		sql.append(" left join view_xsjbxx t2");
		sql.append(" on t1.xh = t2.xh");
		sql.append(" left join xg_stgl_jtjg t5");
		sql.append(" on t1.stid = t5.stid");
		sql.append(" left join xg_stgl_xmlb t3");
		sql.append(" on t5.xmlbdm = t3.xmlbdm");
		sql.append(" left join xg_stgl_stlb t4");
		sql.append(" on t3.stlbdm = t4.stlbdm");
		sql.append(" left join yhb t6");
		sql.append(" on t5.zdls = t6.yhm");
		sql.append(" )t where 1= 1");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(model, sql.toString(), inputV);
	
	}


	@Override
	protected void setTableInfo() {
		super.setClass(RtsqForm.class);
		super.setKey("rtid");
		super.setTableName("xg_rtgl_rtsq");
	}
	
	//��ȡ������Ϣ�������ų�Ա��Ϣά���Լ��鿴
	public HashMap<String, String> getStxxMap(RtsqForm rtsq){
		StringBuilder sql = new StringBuilder();
		sql.append("select t1.stxmmc,");
		sql.append(" t1.stid,");
		sql.append("t2.xmlbmc,");
		sql.append("t2.xmlbdm,");
		sql.append("t3.stlbmc,");
		sql.append("t1.gkdw,");
		sql.append("t1.xn,");
		sql.append("t1.kssj,");
		sql.append("t1.fzrlb,");
		sql.append("t1.lxdh,");
		sql.append("t1.zdlslxfs,");
		sql.append("t1.stclsj,");
		sql.append("t1.stsm,");
		sql.append("t1.sthjqk, ");
		sql.append("t2.jssj,");
		sql.append("t5.xm stfzr,");
		sql.append("t6.xm jtr,");
		sql.append("t4.xm zdlsxm,");
		sql.append("t7.zcmc zdlszc,");
		sql.append("t8.bmmc ssbm,");
		sql.append("t1.sqsj");
		sql.append(" from xg_stgl_jtjg t1");
		sql.append(" left join xg_stgl_xmlb t2");
		sql.append(" on t1.xmlbdm = t2.xmlbdm");
		sql.append(" left join xg_stgl_stlb t3");
		sql.append(" on t2.stlbdm = t3.stlbdm");
		sql.append(" left join yhb t4");
		sql.append(" on t1.zdls = t4.yhm");
		sql.append(" left join (select xh yhm,xm from xsxxb union select yhm,xm from yhb) t5");
		sql.append(" on t1.stfzr = t5.yhm");
		sql.append(" left join (select xh yhm,xm from xsxxb union select yhm,xm from yhb) t6");
		sql.append(" on t1.jtr = t6.yhm");
		sql.append(" left join zcb t7 on t1.zdlszc = t7.zcdm");
		sql.append(" left join zxbz_xxbmdm t8 on t1.ssbm = t8.bmdm");
		sql.append(" where t1.stid = ?");
		return dao.getMapNotOut(sql.toString(), new String[]{rtsq.getStid()});
	}
	
	//��ȡ���ų�Ա��Ϣ
	public HashMap<String, String> getStxxCyMap(RtsqForm rtsq,User user){
		StringBuilder sql = new StringBuilder();
		String[]inputValue = new String[]{};
		sql.append("select t1.xh,");
		sql.append("t1.rtid,");
		sql.append("t2.xm,");
		sql.append("t2.xb,");
		sql.append("t2.bjmc,");
		sql.append("t2.xymc,");
		sql.append("t1.sqly,");
		sql.append("t1.tc,");
		sql.append("t3.rylbmc");
		sql.append(" from xg_rtgl_rtsq t1");
		sql.append(" left join view_xsjbxx t2");
		sql.append(" on t1.xh = t2.xh");
		sql.append(" left join xg_stgl_rylb t3");
		sql.append(" on t1.rylbdm = t3.rylbdm");
		sql.append(" where  t1.rtid = ? ");
		inputValue = new String[]{rtsq.getRtid()};
		return dao.getMapNotOut(sql.toString(), inputValue);
	}
	
	//��Ŀѡ��(�汾������kssj��jssj�ж���xn����Чѧ����桿)
	public List<HashMap<String, String>> getStxmList(RtsqForm model, User user) throws Exception {
		// ���ɸ߼���ѯ�������������ֵ
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		String xh = model.getXh();
		StringBuilder sql = new StringBuilder("select * from(");
		sql.append(" select t.stid,");
		sql.append(" t.stlbdm,");
		sql.append(" t.xmlbdm,");
		sql.append(" t.xn,");
		sql.append(" t.sqsj,");
		sql.append(" lxdh,");
		sql.append(" t.stxmmc,");
		sql.append(" t.kssj,");
		sql.append(" t.jssj,");
		sql.append(" t.zdls,");
		sql.append(" t9.xm jtr,");
		sql.append(" t.stfzr,");
		sql.append(" t.splc,");
		sql.append(" t.fzrlb,");
		sql.append(" t.zdlslxfs,");
		sql.append(" t.stclsj,");
		sql.append(" t.stsm,");
		sql.append(" t.sthjqk,");
		sql.append(" t2.xmlbmc,");
		sql.append(" t3.stlbmc,");
		sql.append(" t5.xm stfzrxm,");
		sql.append(" t6.xm zdlsxm,");
		sql.append(" t7.zcmc zdlszc,");
		sql.append(" t8.bmmc ssbm,");
		sql.append(" case when t.sqkg = 1 and sysdate between to_date(nvl(t.sqkssj,'1990-01-01 00:00'),'yyyy-mm-dd hh24:mi') ");
		sql.append(" and to_date(nvl(t.sqjssj,'9999-01-01 00:00'),'yyyy-mm-dd hh24:mi') + 1 then '1' else '0' end sqkg, ");
//		sql.append(" case when sysdate between to_date(nvl(t.kssj,'1990-01-01 00:00'),'yyyy-mm-dd hh24:mi')");
//		sql.append(" and to_date(nvl(t.jssj,'9999-01-01 00:00'),'yyyy-mm-dd hh24:mi') + 1");
//		sql.append(" then '1' else '0' end bz,");
		sql.append(" t.gkdw ");
		sql.append("  from xg_stgl_jtjg t");
		sql.append(" left join xg_stgl_xmlb t2");
		sql.append(" on t.xmlbdm = t2.xmlbdm");
		sql.append(" left join xg_stgl_stlb t3");
		sql.append(" on t.stlbdm = t3.stlbdm");
		sql.append(" left join (select yhm,xm from yhb union " +
				" select xh yhm,xm from xsxxb) t5 on t.stfzr = t5.yhm");
		sql.append(" left join yhb t6 on t.zdls = t6.yhm ");
		sql.append(" left join zcb t7 on t.zdlszc = t7.zcdm ");
		sql.append(" left join zxbz_xxbmdm t8 on  t.ssbm = t8.bmdm  ");
		sql.append(" left join (select yhm,xm from yhb union " +
		" select xh yhm,xm from xsxxb) t9 on t.jtr = t9.yhm ");
		sql.append(" where t.stid not in (select stid from (select stid,xh from xg_rtgl_rtjg union select stid,xh from xg_rtgl_rtsq where shzt <>'2') t1 where t1.xh = ");
		sql.append("'"+xh+"'");
		sql.append(")");
		sql.append(")a where a.sqkg = '1'  and 1=1 ");// and a.bz = '1'
		sql.append(" and a.xn>='"+Base.currXn+"'");
		sql.append(" ");
		sql.append(searchTj);
		return getPageList(model, sql.toString(), inputV);
	}
	
	//��ȡsqid
	public String getSqid(RtsqForm model) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select rtid from XG_RTGL_RTSQ where xh = ?  and stid = ? ");
		return dao.getOneRs(sql.toString(), new String[] {model.getXh(),model.getStid()}, "rtid");
	}
	
	//�ظ��ж�
	public boolean checkExistForSave(RtsqForm model) {
		boolean flag = false;
		StringBuilder sql = new StringBuilder();
		sql.append("select max(rtid) rtid from");
		sql.append("(select count(rtid) rtid from XG_RTGL_RTSQ t where t.xh = ? and t.stid = ? and t.shzt <> '2'");
		sql.append(" union select count(rtid) rtid from XG_RTGL_RTJG t where t.xh = ? and t.stid = ? )");
		String num = dao.getOneRs(sql.toString(), new String[] {model.getXh(),model.getStid(),
			model.getXh(),model.getStid()}, "rtid");
		if (!num.equals("0")){
			flag = true;
		}
		return flag;
	}
	
	public String getsplc(RtsqForm model){
		boolean flag = false;
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.splc from xg_stgl_jtjg t where stid = ? ");
		return dao.getOneRs(sql.toString(), new String[]{model.getStid()}, "splc");
	}
	
	//�汾����������ѧ��ҳ��--��ѯ
	public List<HashMap<String, String>> getStuRtsqList(RtsqForm model, User user)  throws Exception{
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (");
		sql.append("select t1.xh,t2.xm,");
		sql.append("t1.rtid,");
		sql.append("t1.stid,");
		sql.append("t1.sqly,");
		sql.append("t1.tc,");
		sql.append("t1.rtxn,");
		sql.append("t1.rtxq,");
		sql.append("t1.shzt,");
		sql.append("t5.stxmmc,");
		sql.append("t3.xmlbdm,");
		sql.append("t1.sqsj,");
		sql.append("t1.splc,");
		sql.append("t2.zydm,");
		sql.append("t2.zymc,");
		sql.append("t2.bjdm,");
		sql.append("t2.bjmc,");
		sql.append("t2.xydm,");
		sql.append("t2.xymc,");
		sql.append("t2.nj,");
		sql.append("t2.xb,");
		sql.append("t3.xmlbmc,");
		sql.append("t4.stlbdm,");
		sql.append("t4.stlbmc,");
		sql.append("t5.zdls,");
		sql.append("t5.xn, ");
		sql.append("t5.kssj, ");
		sql.append("t5.jssj, ");
		sql.append("t5.stfzr, ");
		sql.append("t5.jtr, ");
		sql.append(" case when t5.sqkg = 1 and sysdate between to_date(nvl(t5.sqkssj,'1990-01-01 00:00'),'yyyy-mm-dd hh24:mi')");
		sql.append(" and to_date(nvl(t5.sqjssj,'9999-01-01 00:00'),'yyyy-mm-dd hh24:mi') + 1 then '1' else '0' end sqkg, ");
		sql.append(" decode(t1.shzt,'0','δ�ύ','1','ͨ��','2','��ͨ��','3','�˻�','5','�����',t1.shzt) shztmc,");
		sql.append(" t6.xm zdlsxm");
		sql.append(" from xg_rtgl_rtsq t1");
		sql.append(" left join view_xsjbxx t2");
		sql.append(" on t1.xh = t2.xh");
		sql.append(" left join xg_stgl_jtjg t5");
		sql.append(" on t1.stid = t5.stid");
		sql.append(" left join xg_stgl_xmlb t3");
		sql.append(" on t5.xmlbdm = t3.xmlbdm");
		sql.append(" left join xg_stgl_stlb t4");
		sql.append(" on t3.stlbdm = t4.stlbdm");
		sql.append(" left join yhb t6");
		sql.append(" on t5.zdls = t6.yhm");
		sql.append(" )t where 1= 1");
		if(model.getFlag().equalsIgnoreCase("wsq")){
			sql.append(" and t.shzt <> 1");
		}else{
			sql.append(" and t.shzt = 1");
		}
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(model, sql.toString(), inputV);
	}

    public List<HashMap<String,String>> getZdlsInfo(RtsqForm model) {
		String sql ="select a.*,y.xm, y.lxdh ,y.bmdm,t1.bmmc, y.zc ,t2.zcmc from xg_stgl_zdlscy a left join fdyxxb y on  a.zgh = y.zgh " +
				" left join zxbz_xxbmdm t1 on y.bmdm = t1.bmdm left join zcb t2 on y.zc = t2.zcdm where a.stid = ?";
		return dao.getListNotOut(sql, new String[]{model.getStid()});
    }
}
