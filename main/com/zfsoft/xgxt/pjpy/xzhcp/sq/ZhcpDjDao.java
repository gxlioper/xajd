package com.zfsoft.xgxt.pjpy.xzhcp.sq;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

public class ZhcpDjDao extends SuperDAOImpl<ZhcpDjForm> {

	@Override
	public List<HashMap<String, String>> getPageList(ZhcpDjForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	@Override
	public List<HashMap<String, String>> getPageList(ZhcpDjForm t, User user)
			throws Exception {
		// TODO �Զ����ɷ������
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from (");
		sql.append(" select t.*,t1.XM,t1.XB,t1.NJ,t1.XYDM,t1.XYMC, t1.ZYDM,t1.ZYMC,t1.BJDM,t1.BJMC,t2.xqmc,decode(t.shzt,'0','δ�ύ','1','ͨ��','2','��ͨ��','3','���˻�','5','�����',t.shzt) shztmc ");
		sql.append(" from xg_pjpy_new_zhcpdjb t left join view_xsbfxx t1 on t.xh = t1.XH ");
		sql.append(" left join xqdzb t2 on t.xq = t2.xqdm ");
		sql.append(") t where 1= 1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}

	@Override
	protected void setTableInfo() {
		// TODO �Զ����ɷ������
		this.setClass(ZhcpDjForm.class);
		this.setKey("sqid");
		this.setTableName("xg_pjpy_new_zhcpdjb");
	}
	
	/**
	 * 
	 * @����: ��֤�ظ���
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2018-2-7 ����01:50:10
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean checkNotRepeat(ZhcpDjForm t){
		StringBuilder sql = new StringBuilder();
		List<String> paraList = new ArrayList<String>();
		sql.append(" select count(1) cnt from xg_pjpy_new_zhcpdjb where xh = ? and xn = ? ");
		paraList.add(t.getXh());
		paraList.add(t.getXn());
		if(StringUtils.isNotNull(t.getXq())){
			sql.append(" and xq = ? ");
			paraList.add(t.getXq());
		}
		String rs = dao.getOneRs(sql.toString(),paraList.toArray(new String[]{}), "cnt");
		return "0".equals(rs) ? true : false;
	}
	
	/**
	 * 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2018-2-28 ����10:29:48
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param xn
	 * @param xq
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getZcfsForDjb(String xh,String xn,String xq){
		StringBuilder sql = new StringBuilder();
		List<String> paraList = new ArrayList<String>();
		paraList.add(xh);
		paraList.add(xn);
		sql.append(" select t.fs,t1.xmmc,t.bjpm  from xg_zhcp_zcfsb  t left join xg_zhcp_zcxmb t1");
		sql.append(" on t.xmdm = t1.xmdm where t.xh = ? and t.xn = ? ");
		if(StringUtils.isNotNull(xq)){
		  sql.append(" and t.xq = ?");
		  paraList.add(xq);
		}
		return dao.getListNotOut(sql.toString(), paraList.toArray(new String[]{}));
		
	}
	
	/**
	 * 
	 * @����: ��ȡ������ͷ�
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2018-2-28 ����02:23:32
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getDkZdf(String xh,String xn,String xq){
		StringBuilder sql = new StringBuilder();
		sql.append(" select nvl(min(cj),0) cj from view_cjb t where t.xh = ? and t.xn = ? and t.xq = ?");
		return dao.getOneRs(sql.toString(), new String[]{xh,xn,xq}, "cj");
	}
	
	/**
	 * 
	 * @����: ��ȡѧ��������Ϣ
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2018-2-28 ����02:42:47
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param xn
	 * @param xq
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String,String> getXsjbxx(String xh){
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.XH,t.XM,t.BJMC,t.CSRQ,t.bjdm,t.zymc,mzmc,xymc,zzmmmc,nj,to_char(to_date(t.csrq,'yyyy-mm-dd hh24:mi:ss'),'yyyy-mm') csny,xb from view_xsbfxx t where  xh = ?");
		return dao.getMapNotOut(sql.toString(), new String[]{xh});
	}
	
	/**
	 * 
	 * @����: ��ȡ�༶����
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2018-2-28 ����03:17:53
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getBjrs(String bjdm){
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) cnt  from  view_xsbfxx where bjdm = ? ");
		return dao.getOneRs(sql.toString(), new String[]{bjdm}, "cnt");
	}
	
	/**
	 * 
	 * @����: ��ȡ���������List
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2018-3-5 ����10:51:40
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param xn
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getListByHjjg(String xh,String xn){
		StringBuilder sql = new StringBuilder();
		sql.append(" select CASE WHEN SQSJ IS NOT NULL THEN substr(SQSJ,0,10) ELSE SQSJ END sqsj,");
		sql.append(" xmmc from xg_pjpy_new_pjjgb where  xh = ? and xn = ? order by sqsj desc");
		return dao.getListNotOut(sql.toString(),new String[]{xh,xn});
	}
	
	
}
