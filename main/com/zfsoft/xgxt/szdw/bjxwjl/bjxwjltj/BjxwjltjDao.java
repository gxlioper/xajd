/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-5-19 ����01:50:50 
 */  
package com.zfsoft.xgxt.szdw.bjxwjl.bjxwjltj;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �༶��Ϊ��¼����ģ��
 * @���ߣ� ��С��[����:1036]
 * @ʱ�䣺 2014-5-19 ����01:50:50 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class BjxwjltjDao extends SuperDAOImpl<BjxwjltjForm> {

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(BjxwjltjForm t)
			throws Exception {
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(BjxwjltjForm t, User user)
			throws Exception {
		//���ɸ߼���ѯ�������������ֵ 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
 		String searchTjByUser = SearchService.getSearchTjByUser(user, "t1", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuffer sql = new StringBuffer();
		sql.append("select t1.* from (select t.*, nvl2(a.fdyrs,a.fdyrs ,'0') fdyrs, nvl2(b.bzrrs , b.bzrrs , '0') bzrrs, c.fdylbxx, c.bzrlbxx")
		.append("  from VIEW_NJXYZYBJ_ALL t")
		.append("  left join (select t1.bjdm, count(1) as fdyrs")
		.append("               from (select a.*,")
		.append("                            (select xm from fdyxxb where zgh = a.jlr) jlrmc,")
		.append("                            b.xndsmc")
		.append("                       from xg_szdw_bjxwjlb a")
		.append("                       left join xg_szdw_xndsdmb b")
		.append("                         on a.xndsdm = b.xndsdm")
		.append("                       left join fdyxxb d")
		.append("                         on a.jlr = d.zgh")
		.append("                      where a.xndsdm = '1') t1")
		.append("              group by (t1.bjdm)) a")
		.append("    on a.bjdm = t.bjdm")
		.append("  left join (select t1.bjdm, count(1) as bzrrs")
		.append("               from (select a.*,")
		.append("                            (select xm from fdyxxb where zgh = a.jlr) jlrmc,")
		.append("                            b.xndsmc")
		.append("                       from xg_szdw_bjxwjlb a")
		.append("                       left join xg_szdw_xndsdmb b")
		.append("                         on a.xndsdm = b.xndsdm")
		.append("                       left join fdyxxb d")
		.append("                         on a.jlr = d.zgh")
		.append("                      where a.xndsdm = '2') t1")
		.append("              group by (t1.bjdm)) b")
		.append("    on b.bjdm = t.bjdm")
		.append("  left join (select a.bjdm, a.fdylbxx, b.bzrlbxx")
		/*.append("               from (select a.bjdm,")
		.append("                            WM_CONCAT(b.xm) over(partition by a.bjdm) fdylbxx")
		.append("                       from fdybjb a")
		.append("                       left join fdyxxb b")
		.append("                         on a.zgh = b.zgh) a")
		.append("               left join (select a.bjdm,")
		.append("                                WM_CONCAT(b.xm) over(partition by a.bjdm) bzrlbxx")
		.append("                           from bzrbbb a")
		.append("                           left join fdyxxb b")
		.append("                             on a.zgh = b.zgh) b")*/
		.append("  from (select t1.bjdm , t1.fdylbxx from (select a.bjdm,")
		.append("               WM_CONCAT(b.xm) over(partition by a.bjdm order by a.bjdm) fdylbxx,")
		.append("               row_number() over(partition by a.bjdm order by a.bjdm) as rn")
		.append("          from fdybjb a")
		.append("          left join fdyxxb b on a.zgh = b.zgh) t1 where t1.rn = 1) a")
		.append("  left join (select t2.bjdm , t2.bzrlbxx from (select a.bjdm,")
		.append("                    WM_CONCAT(b.xm) over(partition by a.bjdm order by a.bjdm) bzrlbxx,")
		.append("                    row_number() over(partition by a.bjdm order by a.bjdm) as rn")
		.append("               from bzrbbb a")
		.append("               left join fdyxxb b on a.zgh = b.zgh) t2  where t2.rn = 1) b")
		.append("                 on a.bjdm = b.bjdm) c")
		.append("    on c.bjdm = t.bjdm")
		.append(" order by t.nj desc, t.xymc, t.bjmc) t1 where 1 = 1 ")
		.append(searchTjByUser)
		.append(" ")
		.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}
	
	/** 
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-5-19 ����04:31:10
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param bjdm
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws 
	 */
	public HashMap<String, String> getbjxx(String bjdm) {
		StringBuffer sql = new StringBuffer();
		sql.append("select a.* , b.fdylbxx , b.bzrlbxx from VIEW_NJXYZYBJ_ALL a left join (select a.bjdm, a.fdylbxx, b.bzrlbxx")
		.append("                                              from (select a.bjdm,")
		.append("                                                           WM_CONCAT(b.xm) over(partition by a.bjdm) fdylbxx")
		.append("                                                      from fdybjb a")
		.append("                                                      left join fdyxxb b")
		.append("                                                       on a.zgh = b.zgh) a")
		.append("                                              left join (select a.bjdm,")
		.append("                                                               WM_CONCAT(b.xm) over(partition by a.bjdm) bzrlbxx")
		.append("                                                          from bzrbbb a")
		.append("                                                          left join fdyxxb b")
		.append("                                                            on a.zgh = b.zgh) b")
		.append("                                                on a.bjdm = b.bjdm) b on a.bjdm = b.bjdm where a.bjdm = ?");
		
		return dao.getMapNotOut(sql.toString(), new String[]{bjdm});
	}

	/**
	 * 
	 * @����:��ȡ�༶��Ϊ��Ϣ�б�
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-5-19 ����04:49:16
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param bjdm
	 * @param type
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getXwxx(String bjdm , String type) {
		StringBuffer sql = new StringBuffer("select  case when length(a.jlnr)>=15 then substr(a.jlnr,0,15)||'...' else a.jlnr end jlnr , a.jlsj , b.xm from xg_szdw_bjxwjlb a left join fdyxxb b on a.jlr = b.zgh where a.xndsdm = ? and a.bjdm = ? order by a.jlsj desc");
		return dao.getListNotOut(sql.toString(), new String[]{type , bjdm});
	}
	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		super.setClass(BjxwjltjForm.class);
		super.setKey("guid");
		super.setTableName("xg_szdw_bjxwjlb");
	}

}
