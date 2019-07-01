/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-1-26 ����02:37:39 
 */  
package com.zfsoft.xgxt.rcsw.zwzxkqgl.zwzxkqsq;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import java.util.HashMap;
import java.util.List;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� xiaxia [����:1104]
 * @ʱ�䣺 2015-1-26 ����02:37:39 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ZwzxKqsqDao extends SuperDAOImpl<ZwzxKqsqForm>{

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(ZwzxKqsqForm t) throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(ZwzxKqsqForm t, User user) throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.*");
		sql.append(" from (select t1.*,");
		sql.append(" (select xqmc");
		sql.append("  from xqdzb b");
		sql.append("  where t1.xq = b.xqdm) xqmc,");
		sql.append(" t2.nj,");
		sql.append(" t2.xydm,");
		sql.append(" t2.xymc,");
		sql.append(" t2.zydm,");
		sql.append(" t2.zymc,");
		sql.append(" t2.bjmc,");
		sql.append(" t3.lxmc cclxmc,");
		sql.append(" nvl(t4.qqxss, 0) qqxss,");
		sql.append(" decode(t1.shzt,");
		sql.append(" '0',");
		sql.append(" 'δ�ύ',");
		sql.append(" '1',");
		sql.append(" 'ͨ��',");
		sql.append(" '2',");
		sql.append(" '��ͨ��',");
		sql.append(" '3',");
		sql.append(" '�˻�',");
		sql.append(" '5',");
		sql.append(" '�����',");
		sql.append(" t1.shzt) shztmc,");
		sql.append(" t5.xm jlrxm,");
		sql.append(" t6.bmmc jlrbmmc");
		/**
		 * �������θ��Ի�����
		 */
		if("2297".equals(Base.xxdm)){
			sql.append(" ,nvl(t7.rs,0) ydrsszly");
		}

		/**
		 * �����Ƽ�
		 */
		if("10704".equals(Base.xxdm)){
			sql.append(" ,c.dbfdy ");
		}
		sql.append(" from XG_RCSW_ZXKQ_SQB t1");
		sql.append(" left join VIEW_NJXYZYBJ_all t2");
		sql.append(" on t1.bjdm = t2.bjdm");
		sql.append(" left join XG_RCSW_ZWZXKQ_CCLXB t3");
		sql.append(" on t1.cclxdm = t3.lxdm");
		sql.append(" left join (select count(1) qqxss, id");
		sql.append(" from XG_RCSW_ZWZXKQ_XSKQXXB");
		sql.append(" group by id) t4");
		sql.append(" on t1.sqid = t4.id");
		sql.append(" left join fdyxxb t5");
		sql.append(" on t1.jlr = t5.zgh");
		sql.append(" left join zxbz_xxbmdm t6");
		sql.append(" on t6.bmdm = t5.bmdm");
		/**
		 * �������θ��Ի�����
		 */
		if("2297".equals(Base.xxdm)){
			sql.append(" left join (select count(1) rs, bjdm");
			sql.append(" from view_xsxxb");
			sql.append(" group by bjdm) t7");
			sql.append(" on t1.bjdm = t7.bjdm");
		}

		//�����Ƽ�
		if("10704".equals(Base.xxdm)){
			sql.append(" LEFT JOIN (SELECT bjdm,WM_CONCAT(t2.XM) dbfdy FROM FDYBJB t1 LEFT JOIN FDYXXB t2 ON t1.ZGH = t2.ZGH  GROUP BY BJDM) c ON t1.BJDM = c.BJDM ");
		}

		sql.append("  ) t");
		sql.append("  where 1 = 1");
		sql.append(" ");
		sql.append(" ");
		sql.append(" ");
		sql.append(" ");
		sql.append(" ");
		sql.append(" ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}
	/**
	 * 
	 * @����:�ж��Ƿ������д��¼
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-1-26 ����04:29:39
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param czlx
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean isHaveSqJl(ZwzxKqsqForm model,String czlx) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("select count(1) num from XG_RCSW_ZXKQ_SQB where xn=? and xq=? and bjdm=? and ccrq=?");
		if("update".equals(czlx)){
			sql.append(" and sqid<>'"+model.getSqid()+"' ");	
		}
 		String num = dao.getOneRs(sql.toString(), new String[] {model.getXh(),model.getXn(),model.getBjdm(),model.getCcrq()}, "num");
		return Integer.parseInt(num)>0;
	}

	public ZwzxKqsqForm getKqsq(ZwzxKqsqForm t) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" select t1.*,(select xqmc from xqdzb b where t1.xq=b.xqdm) xqmc,t2.nj,t2.xydm,");
		sql.append(" t2.xymc,t2.zydm,t2.zymc,t2.bjmc,t3.lxmc cclxmc,t4.xm jlrxm,c.dbfdy ");
		sql.append(" from XG_RCSW_ZXKQ_SQB t1 left join VIEW_NJXYZYBJ_all t2 on t1.bjdm = t2.bjdm left join XG_RCSW_ZWZXKQ_CCLXB");
		sql.append(" t3 on t1.cclxdm=t3.lxdm");
		sql.append(" left join fdyxxb t4 on t1.jlr=t4.zgh ");
		sql.append(" LEFT JOIN (SELECT bjdm,WM_CONCAT(t2.XM) dbfdy FROM FDYBJB t1 LEFT JOIN FDYXXB t2 ON t1.ZGH = t2.ZGH  GROUP BY BJDM) c ON t1.BJDM = c.BJDM ");
		sql.append(" where t1.sqid=? ");
		return super.getModel(sql.toString(), new String[]{t.getSqid()});
		
	}
	
	@Override
	protected void setTableInfo() {
		super.setClass(ZwzxKqsqForm.class);
		super.setKey("sqid");
		super.setTableName("XG_RCSW_ZXKQ_SQB");
		
	}
	
	/**
	 * 
	 * @����: ��������ʵʱ����Ӧ������
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2017-1-10 ����10:40:30
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param bjdm
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public String getYdrsSzly(String bjdm){
		StringBuffer sql = new StringBuffer();
		sql.append(" select rs from (");
		sql.append(" select count(1) rs, bjdm from view_xsxxb  where bjdm = ? group by bjdm");
		sql.append(" )");
		String num = dao.getOneRs(sql.toString(), new String[]{bjdm}, "rs");
		if(StringUtils.isNull(num)){
			num = "0";
		}
		return num;
	}
}
