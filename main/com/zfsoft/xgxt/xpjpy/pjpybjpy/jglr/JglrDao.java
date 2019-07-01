package com.zfsoft.xgxt.xpjpy.pjpybjpy.jglr;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

public class JglrDao extends SuperDAOImpl<JglrForm> {

	/*
	 * ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		super.setKey("sqid");
		super.setTableName("xg_pjpy_new_pjpy_bjpyxzpy");
	}

	/*
	 * ����: @see
	 * com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(JglrForm t)
			throws Exception {
		return null;
	}

		/*
	 * ����: @see
	 * com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object,
	 * xgxt.form.User)
	 */
	@Override
	public List<HashMap<String, String>> getPageList(JglrForm t, User user)
			throws Exception {

		//���ɸ߼���ѯ�������������ֵ 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputValue = SearchService.getTjInput(t.getSearchModel());
//		String searchTjByUser = SearchService.getSearchTjByUser(user, "t1", "xydm", "bjdm");		
		String xh = user.getUserName();
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select * from ( ");
		sql.append(" select t1.sqid,t1.xh,t1.splc,t2.xm,t1.xn,t1.xmdm, ");
		sql.append(" (select xmmc from xg_pjpy_new_pjxmb t where t1.tzhxmdm=t.xmdm) tzhxmmc, ");
		sql.append(" (select xmmc from xg_pjpy_new_pjxmb t where t1.xmdm=t.xmdm) xmmc, ");
		sql.append(" t2.bjdm,(case t2.xb when '1' then '��' when '2' then 'Ů' else t2.xb end) xb, ");
		sql.append("case when t6.tjzt <>'1'and t6.num= t7.pyxzrs then 'true' else 'false' end canSubmit,");
		sql.append(" t3.xqmc,t1.sqsj,t1.sqly, ");
		sql.append(" t1.xq,t1.shzt, ");
		sql.append(" decode(t5.ylzd1,'1', 'ͬ������', '0', '��ͬ������','') ylzd1mc, ");
		sql.append(" t5.bjpyr,t5.ylzd1,t5.ylzd2,t5.ylzd3,t5.ylzd4,t5.ylzd5,t5.ylzd6,t5.ylzd7,t5.ylzd8,t5.ylzd9,t5.ylzd10,t5.tjzt,t5.tjsj ");
		sql.append(" from xg_pjpy_new_xmsq t1 ");
		sql.append(" left join (select a.xn,a.xq,a.xh,a.xm,a.bjdm,b.xb from xg_pjpy_new_cpmdb a left join xsxxb b on a.xh=b.xh) t2 on t1.xh=t2.xh and t1.xn=t2.xn and t1.xq=t2.xq ");
		sql.append(" left join xqdzb t3 on t1.xq=t3.xqdm ");
		sql.append(" left join (select * from xg_pjpy_new_pjpy_bjpyxzpy where bjpyr=?) t5 on (t1.xn=t5.xn and t1.xq=t5.xq and t1.xmdm=t5.xmdm and t1.xh=t5.sqr) ");
		sql.append(" left join (select count(1) num,sqr,tjzt,xn,xq,xmdm from xg_pjpy_new_pjpy_bjpyxzpy group by sqr,tjzt,xn,xq,xmdm )t6 on (t1.xh=t6.sqr and t1.xn = t5.xn and t1.xq = t5.xq and t1.xmdm=t6.xmdm)");
	    sql.append(" left join (select count(1)pyxzrs,bjdm from xg_pjpy_new_pjpy_bjpyxz group by bjdm)t7 on t2.bjdm=t7.bjdm");
		sql.append(" where exists (select 1 from xg_pjpy_new_cpmdb t6 where t6.xh=? and t2.bjdm=t6.bjdm and t2.xn=t6.xn and t2.xq=t6.xq) ");
		sql.append(" and t1.shzt not in ('0','3') ");
		sql.append(" ) t1 where 1=1 ");
		sql.append(searchTj);
		sql.append(" order by sqsj desc");
		
		return getPageList(t, sql.toString(),StringUtils.joinStrArr(new String[]{xh,xh}, inputValue));
	}
	
	/**
	 * ��ȡ�����༶����С���Ա��Ϣ
	 */
	public HashMap<String,String> queryBjpyxzcy(String xh) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from xg_pjpy_new_pjpy_bjpyxz where xh=? ");
		return dao.getMapNotOut(sql.toString(), new String[]{ xh });
	}
	/**
	 * ��ȡѧ����Ϣ
	 */
	public HashMap<String,String> queryXsxx(String xh) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.xh,a.xm,c.xymc,c.zymc,c.bjmc from (select xh,xm,bjdm from xg_pjpy_new_cpmdb where xn||xq=(select xn||(case when xq is null then 'on' else xq end) from xg_pjpy_new_csszb)) a left join view_njxyzybj_all c on a.bjdm = c.bjdm where a.xh=? ");
		return dao.getMapNotOut(sql.toString(), new String[]{ xh });
	}
	/**
	 * ɾ�������༶����
	 */
	public boolean delBjpyxzpy(String xn, String xq, String xmdm, String sqr, String bjpyr) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" delete from xg_pjpy_new_pjpy_bjpyxzpy where xn=? and xq=? and xmdm=? and sqr=? and bjpyr=? ");
		return dao.runUpdate(sql.toString(), new String[]{ xn, xq, xmdm, sqr, bjpyr });
	}
	public HashMap<String,String> getPyxx(JglrForm model){
		StringBuilder sql = new StringBuilder();
		sql.append(" select t1.sqid,t1.xn,t1.xq,t1.xh sqr,t1.xh,t2.xm,t1.xmdm,decode(t5.ylzd1,'1', 'ͬ������', '0', '��ͬ������','') ylzd1mc, (select xmmc from xg_pjpy_new_pjxmb t where t1.xmdm = t.xmdm) xmmc, ");
		sql.append(" t5.bjpyr,t5.ylzd1,t5.ylzd2,t5.ylzd3,t5.ylzd4,t5.ylzd5,t5.ylzd6,t5.ylzd7,t5.ylzd8,t5.ylzd9,t5.ylzd10,t5.tjzt,t5.tjsj ");
		sql.append(" from xg_pjpy_new_xmsq t1 left join xsxxb t2 on t1.xh=t2.xh ");
		sql.append(" left join (select * from xg_pjpy_new_pjpy_bjpyxzpy where bjpyr=?) t5 on (t1.xn=t5.xn and t1.xq=t5.xq and t1.xmdm=t5.xmdm and t1.xh=t5.sqr) ");
		sql.append("where t1.sqid=?");
		return dao.getMapNotOut(sql.toString(), new String[]{model.getBjpyr(),model.getSqid()});
	}
	
}
