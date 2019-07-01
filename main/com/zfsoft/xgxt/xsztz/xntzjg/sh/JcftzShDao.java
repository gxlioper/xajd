/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-3-30 ����04:45:33 
 */  
package com.zfsoft.xgxt.xsztz.xntzjg.sh;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.xsztz.xntzjg.jg.JcftzJgForm;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����[����:1282]
 * @ʱ�䣺 2016-3-30 ����04:45:33 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class JcftzShDao extends SuperDAOImpl<JcftzShForm>{

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(JcftzShForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(JcftzShForm t, User user)
			throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		//String shgwzByUser = SearchService.getSearchSztzShTjByUser(user, "t", "sbbmdm", "sbr");
		//String userName = user.getUserName();
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* from ( ");
		sql.append(" select t1.*,decode(t1.csms,1,'����',2,'����',t1.csms) csmsmc,t2.xmjbmc,t3.sskmmc,t4.bmmc sbbmmc,t5.xqmc, nvl(t10.xm,t11.xm) sbrxm,nvl(t8.num,0)ybjrs,");
		sql.append(" t6.guid shid,t6.shzt shztx,t6.gwid,t6.shr,t6.shyj,t9.mc || '[' ||");
		sql.append(" decode(t6.shzt, '0', 'δ���', '1', 'ͨ��', '2', '��ͨ��', '3',  '�˻�', '4', '������', '5', '�����') || ']' shztmc,t9.gwz, ");
		sql.append(" row_number() over(partition by t1.rdsqid order by t6.shsj desc) rn ");
		sql.append(" from xg_sztz_xmjg t1 left join xg_sztz_xmjb t2 on t1.xmjbdm = t2.xmjbdm left join xg_sztz_sskm t3 on t1.sskmdm=t3.sskmdm ");
		sql.append(" left join xg_xtwh_shztb t6 on t1.rdsqid = t6.ywid ");
		sql.append(" left join zxbz_xxbmdm t4 on t1.sbbmdm=t4.bmdm left join xqdzb t5 on t1.xq=t5.xqdm left join yhb t10 on t1.sbr= t10.yhm left join xsxxb t11 on t1.sbr=t11.xh ");
		sql.append(" left join(select count(xh)num,xmdm from XG_SZTZ_XS_SQJG group by xmdm" +
				" union all" +
				" select count(ttjgid) num,xmdm" +
				" from xg_sztz_ttxmjg  group by xmdm" +
				")t8 on t1.xmdm=t8.xmdm ");
		sql.append(" left join xg_xtwh_spgwyh t7 on  t6.gwid = t7.spgw left join xg_xtwh_spgw t9 on t6.gwid = t9.id where t7.spyh ='" + user.getUserName() + "' ");
		String shlx = t.getShzt();
		if (!shlx.equals("dsh")) {
			sql.append(" and (t6.shzt<>0 and  t6.shzt<>4)");
		} else {
			sql.append(" and (t6.shzt=0  or t6.shzt = 4)");
		}
		sql.append(" order by t1.xfrdsqzt desc,t1.xmkssj desc) t ");
		sql.append(" where 1=1 and rn = 1 ");
		sql.append(searchTj);
		//sql.append(shgwzByUser);
		return getPageList(t, sql.toString(), inputV);
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		super.setClass(JcftzShForm.class);
		super.setKey("rdsqid");
		super.setTableName("xg_sztz_xmjg");		
	}
	
	public boolean updateSqjl(String ywid, String shzt) throws Exception{
		String sql = "update xg_sztz_xmjg set xfrdsqzt = ?,xfrdjgzt = '0'  where rdsqid = ?";		
		return dao.runUpdate(sql, new String[]{shzt,ywid});		
	}
	
	/**
	 * 
	 * @����: �����Ա����
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-8-5 ����08:43:28
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param jcftzJgForm
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean updateTtxx(JcftzJgForm jcftzJgForm,ArrayList<String[]> paralist){
		StringBuilder sql = new StringBuilder();
		sql.append(" insert into xg_sztz_jcftz_jg (xmdm,xh,tzhjcf,jxdm,sfqq,lylcywid,sjly,bz1,bz2,bz3,bz4,bz5) ");
	    sql.append(" values(?,?,?,?,?,?,?,?,?,?,?,?)");
		try {
			int[] len =  dao.runBatch(sql.toString(),paralist);
			if(len != null && len.length > 0){
				return true;
			}else{
				return false;
			}
		} catch (SQLException e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
			return false;
		}
	}
}
