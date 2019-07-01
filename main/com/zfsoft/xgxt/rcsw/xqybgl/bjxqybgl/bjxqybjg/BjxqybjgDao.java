/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-3-31 ����04:40:31 
 */  
package com.zfsoft.xgxt.rcsw.xqybgl.bjxqybgl.bjxqybjg;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;


/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ������[����:995]
 * @ʱ�䣺 2016-3-31 ����04:40:31 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class BjxqybjgDao extends SuperDAOImpl<BjxqybjgForm> {

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(BjxqybjgForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	@Override
	public List<HashMap<String, String>> getPageList(BjxqybjgForm t, User user)
			throws Exception {
		//���ɸ߼���ѯ�������������ֵ 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuffer sql = new StringBuffer();
		
		sql.append(" select * from (");
		sql.append(" select t1.jgid,t1.xn,t1.xq,t1.yf ny,substr(t1.yf,1, 4)nd,substr(t1.yf,6,2)yf,t1.bygzkzqk,t1.xsgzrd, ");
		sql.append(" t1.xssxdt,t1.xstsjgzjy,t1.txsj,t1.txr,t1.bjdm,t1.sjly, t2.nj,t2.xymc,t2.xydm,t2.bjmc,t2.zymc,t2.zydm,t7.xqmc,t3.xm lrrxm from xg_bjzyy_xqyb_bjyb_jg t1 ");
		sql.append(" left join view_njxyzybj_all t2 on t1.bjdm = t2.bjdm  left join fdyxxb t3 on t1.txr = t3.zgh  ");
		sql.append(" left join xqdzb t7 on t1.xq = t7.xqdm  ");
		sql.append(" ) a where 1 = 1 ");

		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}

	/**
	 * 
	 * @����:TODO(�༶ѧ���±�����-�༶ѧ����-���ͨ������ɾ��������)
	 * @���ߣ�������[���ţ�995]
	 * @���ڣ�2016-4-12 ����01:57:58
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean deleteExist(BjxqybjgForm model) throws Exception {
		
		StringBuffer sql = new StringBuffer(
		" delete from xg_bjzyy_xqyb_bjyb_jg where xn=? and xq = ? and yf = ? and bjdm = ? ");
		return dao.runUpdate(sql.toString(), new String[] { model.getXn(),model.getXq(),model.getYf(),model.getBjdm()});
	}
	
	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	@Override
	protected void setTableInfo() {
		// TODO �Զ����ɷ������
		super.setKey("jgid");
		super.setTableName("xg_bjzyy_xqyb_bjyb_jg");

	}
	
	
	/**
	 * 
	 * @����:TODO(�༶ѧ���±�����-�༶ѧ����-���հ༶����, ѧ��,�·�,��д���жϰ༶ѧ���±���������Ƿ��Ѿ����ڸð�)
	 * @���ߣ�������[���ţ�995]
	 * @���ڣ�2016-4-7 ����04:34:19
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String checkExistForBjxqybjgSave(BjxqybjgForm model) {
		StringBuffer sql = new StringBuffer(
				" select count(1) num from xg_bjzyy_xqyb_bjyb_jg where xn = ?  and xq = ? and yf = ? and bjdm = ? ");
		String num = dao.getOneRs(sql.toString(), new String[] {model.getXn(),model.getXq(),model.getYf(),model.getBjdm()}, "num");
		return num;
	}
	
	/**
	 * 
	 * @����:TODO(���հ༶����, ѧ��,�·�,��д��,����id�жϰ༶ѧ���±���������Ƿ��Ѿ����ڸð�)
	 * @���ߣ�������[���ţ�995]
	 * @���ڣ�2016-4-7 ����04:50:22
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String checkExistForBjxqybjgUpdate(BjxqybjgForm model) {
		StringBuilder sql = new StringBuilder(
				" select count(1) num from xg_bjzyy_xqyb_bjyb_jg where txr = ? and xn = ?  and xq = ? and yf = ?  and jgid <> ? ");
		String num = dao.getOneRs(sql.toString(), new String[] { model.getTxr(),model.getXn(),
			model.getXq(),model.getYf(),model.getJgid()}, "num");
		return num;
	}
	
	/**
	 * 
	 * @����:TODO(�༶ѧ���±�����-�༶ѧ����-��ȡ�����༶ѧ���±��������)
	 * @���ߣ�������[���ţ�995]
	 * @���ڣ�2016-4-11 ����09:21:51
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String,String> getBjxqybjgInfo(BjxqybjgForm model){
		
		StringBuffer sql = new StringBuffer();				
		
		sql.append(" select t1.jgid,t1.xn,t1.xq,t1.yf,t1.bygzkzqk,t1.xsgzrd,t1.xssxdt,t1.xstsjgzjy, ");		
		sql.append("  t1.txsj,t1.txr,t1.sjly,t1.lylcywid,t1.bjdm,t2.xymc,t2.bjmc,t3.xqmc,t4.xm ");		
		sql.append(" from xg_bjzyy_xqyb_bjyb_jg t1 left join view_njxyzybj_all t2 ");		
		sql.append(" on t1.bjdm = t2.bjdm left join xqdzb t3 on t1.xq = t3.xqdm ");
		sql.append(" left join fdyxxb t4 on t1.txr = t4.zgh ");
						
		sql.append("  where t1.jgid  = ? ");
		
		return dao.getMapNotOut(sql.toString(), new String[]{model.getJgid()});
	}
	
	/**
	 * 
	 * @����:TODO(�༶ѧ���±�����-�༶ѧ����-����������Դ�ж��Ƿ��������������)
	 * @���ߣ�������[���ţ�995]
	 * @���ڣ�2016-4-11 ����10:05:14
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param jgid
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean isCanDel(String jgid){
		StringBuffer sb=new StringBuffer();
		sb.append(" select sjly from xg_bjzyy_xqyb_bjyb_jg where jgid=? ");
		Map<String,String> map= dao.getMapNotOut(sb.toString(),new String[]{jgid});
		String sjly=map.get("sjly");
		//���δ�ύ�ſ����ύ
		return sjly.equals("0")?true:false;
	}
	
	/**
	 * 
	 * @����:TODO(�༶ѧ���±�����-�༶ѧ����-��ȡҪɾ���İ༶ѧ���±�������еİ༶����)
	 * @���ߣ�������[���ţ�995]
	 * @���ڣ�2016-4-11 ����10:11:00
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param jgid
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> getBjxqybjg(String jgid){
		StringBuffer sb=new StringBuffer();
		sb.append(" select a.bjdm bjdm,b.bjmc bjmc from xg_bjzyy_xqyb_bjyb_jg a,view_njxyzybj_all b where a.bjdm = b.bjdm ");
		sb.append(" and a.jgid = ? ");
		return dao.getMapNotOut(sb.toString(),new String[]{jgid});
	}
	
	/** 
	 * @����:��ȡ�ϲ�����list(������ҽҩ���Ի�)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-12-30 ����09:08:38
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getHbdcList(BjxqybjgForm t, User user) throws Exception{
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuffer sql = new StringBuffer();
		
		sql.append(" select * from (select * from (");
		sql.append(" select t1.jgid,t1.xn,t1.xq,t1.yf ny,substr(t1.yf,1, 4)nd,substr(t1.yf,6,2)yf,t1.bygzkzqk,t1.xsgzrd, ");
		sql.append(" t1.xssxdt,t1.xstsjgzjy,t1.txsj,t1.txr,t1.bjdm,t1.sjly, t2.nj,t2.xymc,t2.xydm,t2.bjmc,t2.zymc,t2.zydm,t7.xqmc,t3.xm lrrxm from xg_bjzyy_xqyb_bjyb_jg t1 ");
		sql.append(" left join view_njxyzybj_all t2 on t1.bjdm = t2.bjdm  left join fdyxxb t3 on t1.txr = t3.zgh  ");
		sql.append(" left join xqdzb t7 on t1.xq = t7.xqdm  ");
		sql.append(" ) b group by nd,yf,xymc,bjmc,xn,xq,ny,jgid,bygzkzqk,xsgzrd,xssxdt,xstsjgzjy,txsj,txr,bjdm,sjly,nj,xydm,zydm,zymc,xqmc,lrrxm order by nd,yf,xymc,bjmc asc) a where 1 = 1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return dao.getListNotOut(sql.toString(), inputV);
	}
}
