/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-5-26 ����03:22:39 
 */  
package com.zfsoft.xgxt.xljkwzdx.xlwygl.xxsbgl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ��С��[����:1036]
 * @ʱ�䣺 2014-5-26 ����03:22:39 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XxsbDao extends SuperDAOImpl<XxsbForm>{
	
	public static final String YSH = "Y";
	
	public static final String DSH = "D";
	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(XxsbForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/**
	 * 
	 * @����:��ȡ�ϱ���Ϣ
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-5-30 ����01:32:08
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param sbsqid
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String ,  String> getModelMap(String sbsqid){
		String sql = "select a.* , b.* , c.* , c.zbksrq||' ~ '||c.zbjsrq as zbqzrq , z.xqmc from XG_XLJK_XLWYGL_XSSBSQB a left join view_xsjbxx b on a.xh = b.xh left join XG_XLJK_XLWYGL_ZBRCXXB c on a.sbzbid = c.zbid left join xqdzb z on a.xq = z.xqdm where a.sbsqid = ? ";
		return dao.getMapNotOut(sql, new String[]{sbsqid});	
	}
	
	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(XxsbForm t, User user)
			throws Exception {
		List<String> params = new ArrayList<String>();
		StringBuffer sql = new StringBuffer();
		if("0".equals(t.getSblx())){
			sql.append("select '0' sblxx, t1.* , a.xqmc, t2.sbsqid,t2.xh,t2.sblx,t2.sbsj,t2.sbzbid,t2.ztqk,t2.xlxsxxqk,t2.bz,t2.splcid,t2.shzt,decode(t2.shzt,")
			.append("              '0',")
			.append("              'δ�ύ',")
			.append("              '1',")
			.append("              'ͨ��',")
			.append("              '2',")
			.append("              '��ͨ��',")
			.append("              '3',")
			.append("              '���˻�',")
			.append("              '5',")	
			.append("              '�����',")
			.append("              'δ�ϱ�') shztmc from (select b.* from XG_XLJK_XLWYGL_ZBRCXXB b where b.zblx = '0' ");
			if (!StringUtil.isNull(t.getXn())) {
				params.add(t.getXn());
				sql.append(" and b.xn=? ");
			}
			if (!StringUtil.isNull(t.getXq())) {
				params.add(t.getXq());
				sql.append(" and b.xq=? ");
			}
			sql.append(") t1   ")
			.append("  left join (select a.*")
			.append("               from XG_XLJK_XLWYGL_XSSBSQB a")
			.append("              where a.sblx = '0'")
			.append("                and a.xh = '" + user.getUserName() + "') t2")
			.append("    on t1.zbid = t2.sbzbid left join xqdzb a on t1.xq = a.xqdm");
		}else if("1".equals(t.getSblx())){
			sql.append("select '1' sblxx, t1.* , a.xqmc, t2.sbsqid,t2.xh,t2.sblx,t2.sbsj,t2.sbzbid,t2.ztqk,t2.xlxsxxqk,t2.bz,t2.splcid,t2.shzt,decode(t2.shzt,")
			.append("              '0',")
			.append("              'δ�ύ',")
			.append("              '1',")
			.append("              'ͨ��',")
			.append("              '2',")
			.append("              '��ͨ��',")
			.append("              '3',")
			.append("              '���˻�',")
			.append("              '5',")	
			.append("              '�����',")
			.append("              'δ�ϱ�') shztmc from (select b.* from XG_XLJK_XLWYGL_ZBRCXXB b where b.zblx = '1' ");
			if (!StringUtil.isNull(t.getXn())) {
				params.add(t.getXn());
				sql.append(" and b.xn=? ");
			}
			if (!StringUtil.isNull(t.getXq())) {
				params.add(t.getXq());
				sql.append(" and b.xq=? ");
			}
			sql.append(") t1   ")
			.append("  left join (select a.*")
			.append("               from XG_XLJK_XLWYGL_XSSBSQB a")
			.append("              where a.sblx = '1'")
			.append("                and a.xh = '" + user.getUserName() + "') t2")
			.append("    on t1.zbid = t2.sbzbid left join xqdzb a on t1.xq = a.xqdm");
		}else if("2".equals(t.getSblx())){
			sql.append("select '2' sblxx, a.*, z.xqmc " +
					",decode(a.shzt," +
					"              '0'," +
					"              'δ�ύ'," +
					"              '1'," +
					"              'ͨ��'," +
					"              '2'," +
					"              '��ͨ��'," +
					"              '3'," +
					"              '���˻�'," +
					"              '5'," +
					"			   '�����'," +
					"              'δ�ϱ�') shztmc, " + " b.xm " +
					"from XG_XLJK_XLWYGL_XSSBSQB a left join view_xsjbxx b on a.xh = b.xh " +
					"left join xqdzb z on a.xq = z.xqdm " +
					"where a.xh = '" + user.getUserName() + "' and a.sblx = '2'");
			if (!StringUtil.isNull(t.getXn())) {
				params.add(t.getXn());
				sql.append(" and a.xn=? ");
			}
			if (!StringUtil.isNull(t.getXq())) {
				params.add(t.getXq());
				sql.append(" and a.xq=? ");
			}
		}
		
		return getPageList(t, sql.toString(), params
				.toArray(new String[] {}));
	}

	
	//****************************************** ���*************************************************/
	
	public List<HashMap<String, String>> getSHPageList(XxsbForm t, User user)
		throws Exception {

		//���ɸ߼���ѯ�������������ֵ 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
			String searchTjByUser = SearchService.getSearchTjByUser(user, "t2", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuffer sql = new StringBuffer();
		sql.append("select t2.* from (select t1.*,row_number() over(partition by sbsqid order by shsj desc) rn  from ")
		.append("(select a.* , '[' || b.mc || ':' || decode(a.shzt,'0','�����','1','ͨ��','2','��ͨ��','3','�˻�','4','������','5','�����',a.shzt) || ']' shztmc from (select a.sbsqid,")
		.append("       a.xh,")
		.append("       a.sblx,")
		.append("       decode(a.sblx , '0' , '�༶�����ܱ�' , '1' , '��Ԣ�����ܱ�' , '2' , 'ƽʱ����ϱ�' , '') sblxmc,")
		.append("       a.sbsj,")
		.append("       a.sbzbid,")
		.append("       a.splcid,")
		.append("       a.xn,")
		.append("       a.xq,")
		.append("       z.xqmc,")
		.append("       b.xm,")
		.append("       b.nj,")
		.append("       b.xydm,")
		.append("       b.xymc,")
		.append("       b.zydm,")
		.append("       b.zymc,")
		.append("       b.bjdm,")
		.append("       b.bjmc,")
		.append("       c.zblx,")
		.append("       c.zbzc,")
		.append("       c.zbksrq,")
		.append("       c.zbjsrq,")
		.append("		d.gwid as xtgwid,")
		.append("       d.shzt,")
		.append("       d.shsj,")
		.append("       d.guid as shid ")
		.append("  from XG_XLJK_XLWYGL_XSSBSQB a")
		.append("  left join view_xsjbxx b")
		.append("    on a.xh = b.xh")
		.append("  left join xqdzb z on a.xq = z.xqdm ")
		.append("  left join XG_XLJK_XLWYGL_ZBRCXXB c")
		.append("    on a.sbzbid = c.zbid ")
		.append("  left join xg_xtwh_shztb d on a.sbsqid = d.ywid")
		.append(" ) a  left join xg_xtwh_spgw b on a.xtgwid = b.id ")
		.append(" where a.xtgwid in ")
		.append(" (select spgw from xg_xtwh_spgwyh where spyh = '")
		.append(user.getUserName());
		
		if(DSH.equals(t.getType()))
			sql.append("') and a.shzt in ('0', '4')) t1) t2 where rn = 1  ");
		else if(YSH.equals(t.getType()))
			sql.append("') and a.shzt not in ('0', '4')) t1) t2 where rn = 1  ");
		
		sql.append(searchTjByUser)
		.append(" ")
		.append(searchTj);
		
		return  getPageList(t, sql.toString(), inputV);
		}
	
	
	
	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		setClass(XxsbForm.class);
		setKey("sbsqid");
		setTableName("XG_XLJK_XLWYGL_XSSBSQB");
	}
	
	
	public HashMap<String,String> getXnXqmc(String zbid){
		StringBuffer sql = new StringBuffer();
		sql.append(" select a.*,b.xqmc from XG_XLJK_XLWYGL_ZBRCXXB a left join xqdzb b on a.xq = b.xqdm where zbid = ? ");
		
		return dao.getMapNotOut(sql.toString(), new String[]{zbid});
	}

}
