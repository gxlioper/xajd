/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-6-27 ����04:41:49 
 */  
package com.zfsoft.xgxt.zjly.zhf.xmwh;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �ۺϷֹ���ģ��
 * @�๦������: �Ʒ���Ŀ(������һ�仰��������������) 
 * @���ߣ� ����[����:1282]
 * @ʱ�䣺 2016-6-27 ����04:41:49 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ZhfJfxmwhDao extends SuperDAOImpl<ZhfJfxmwhForm>{

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(ZhfJfxmwhForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(ZhfJfxmwhForm t, User user)
			throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
//		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");	
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* from ( ");
		sql.append(" select t1.*,t2.xmmkmc,");
		sql.append(" decode(t1.sfxf,'0','���޷�','1','�޷�') sfxfmc,");
		sql.append(" decode(t1.sfbt,'0','�Ǳ���','1','����') sfbtmc,");
		sql.append(" t3.num bms ");
		sql.append(" from xg_zjly_zhszf_jfxmb t1 left join xg_zjly_zhszf_mkxmb t2 on t1.xmmkdm = t2.xmmkdm");
		sql.append(" left join (select count(1) num,jfxmdm from xg_zjly_zhszf_xmsqb group by jfxmdm) t3 on t1.jfxmdm = t3.jfxmdm ");
		sql.append(" ) t where 1 = 1 ");
//		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		this.setClass(ZhfJfxmwhForm.class);
		this.setKey("jfxmdm");
		this.setTableName("xg_zjly_zhszf_jfxmb");
	}
	
	/** 
	 * @����:ͳ�ƼƷ���Ŀ����(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-6-27 ����05:18:50
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return
	 * int �������� 
	 * @throws 
	 */
	public int count(ZhfJfxmwhForm t) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) num from xg_zjly_zhszf_jfxmb where jfxmmc = ? ");
		if(null != t.getJfxmmc() && !"".equals(t.getJfxmmc()) && "" != t.getJfxmmc()){
			sql.append(" and jfxmdm <> ? ");
			return Integer.valueOf(dao.getOneRs(sql.toString(), new String[]{t.getJfxmmc(),t.getJfxmdm()}, "num"));
		}else{
			return Integer.valueOf(dao.getOneRs(sql.toString(), new String[]{t.getJfxmmc()}, "num"));
		}
	}
	
	/** 
	 * @����:��Ŀ��Ȩ(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-6-28 ����03:27:31
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param bmdms
	 * @param jfxms
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws 
	 */
	public boolean jfxmSq(String[]bmdms,String[]jfxms) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" insert into xg_zjly_zhszf_xmsqb ");
		for(int i = 0;i<jfxms.length;i++){
			for(int j=0;j<bmdms.length;j++){
				if(i==0){					
					if(j>0){
						sql.append(" union all ");
					}
					sql.append(" select '"+jfxms[i]+"','"+bmdms[j]+"' from dual");
				}else{
					sql.append(" union all select '"+jfxms[i]+"','"+bmdms[j]+"' from dual");
				}
			}			
		}
		return dao.runUpdate(sql.toString(), new String[]{});
		
	}
	
	/** 
	 * @����:ɾ����Ŀ��Ȩ(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-6-28 ����03:41:13
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param bmdms
	 * @param jfxms
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws 
	 */
	public boolean deljfxmSq(String[]bmdms,String[]jfxms) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" delete from xg_zjly_zhszf_xmsqb where jfxmdm in (");
		if(jfxms.length==1){
			sql.append("'"+jfxms[0]+"')");
		}else{			
			for(int i = 0;i<jfxms.length;i++){
				if(i==jfxms.length-1){
					sql.append("'"+jfxms[i]+"'");
				}else{
					sql.append("'"+jfxms[i]+"',");
				}
			}
			sql.append(")");
		}
		sql.append(" and bmdm in (");
		if(bmdms.length==1){
			sql.append("'"+bmdms[0]+"')");
		}else{			
			for(int i = 0;i<bmdms.length;i++){
				if(i==bmdms.length-1){
					sql.append("'"+bmdms[i]+"'");
				}else{
					sql.append("'"+bmdms[i]+"',");
				}
			}
			sql.append(")");
		}
		return dao.runUpdate(sql.toString(), new String[]{});
	}
	
	/** 
	 * @����:��ȡ�����б�(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-6-28 ����04:04:55
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param cxzd
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getPageListForSq(ZhfJfxmwhForm t) throws Exception{
//		String searchTj = SearchService.getSearchTj(t.getSearchModel());	
//		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (select a.bmdm,a.bmmc from ZXBZ_XXBMDM a ");
		if(("wsq").equals(t.getCxzd())){
			sql.append(" where not exists (select 1 from xg_zjly_zhszf_xmsqb b where a.bmdm = b.bmdm and b.jfxmdm='"+t.getJfxmdm()+"')) ");
		}else if(("ysq").equals(t.getCxzd())){
			sql.append(" where exists (select 1 from xg_zjly_zhszf_xmsqb b where a.bmdm = b.bmdm and b.jfxmdm='"+t.getJfxmdm()+"')) ");
		}
		sql.append(" where 1= 1 ");
		if(null != t.getCxmc() && !"".equals(t.getCxmc())){
			sql.append(" and bmmc like '%'||?||'%'" );
			return getPageList(t, sql.toString(), new String[]{t.getCxmc()});
		}else{
			return getPageList(t, sql.toString(), new String[]{});
		}
		
		
	}
	
	/** 
	 * @����:��ȡ�����б�(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-6-28 ����04:04:55
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param cxzd
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getBmList(String cxzd){
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (select a.bmdm,a.bmmc from ZXBZ_XXBMDM a ");
		if(cxzd.equals("wsq")){
			sql.append(" where not exists (select 1 from xg_zjly_zhszf_xmsqb b where a.bmdm = b.bmdm)) ");
		}else if(cxzd.equals("ysq")){
			sql.append(" where exists (select 1 from xg_zjly_zhszf_xmsqb b where a.bmdm = b.bmdm)) ");
		}
		return dao.getListNotOut(sql.toString(), new String[]{});
	}
	
	/** 
	 * @����:����������(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-6-29 ����09:15:57
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param list
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws 
	 */
	public boolean jdsz(List<HashMap<String, String>> list) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" insert into xg_zjly_zhszf_jdszb (jfxmdm,jfxmmc)");
		for(int i = 0;i<list.size();i++){
			if(i>0){
				sql.append(" union all ");
			}
			sql.append(" select '"+ list.get(i).get("jfxmdm")+"','"+ list.get(i).get("jfxmmc") +"' from dual");
		}
		return dao.runUpdate(sql.toString(), new String[]{});
	}
	
	/** 
	 * @����:���ݼƷ���Ŀ���Ƶõ��Ʒ���Ŀ�б�(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-6-29 ����09:22:54
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param jfxmmcs
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getJfxmList(String[] jfxmmcs){
		StringBuilder sql = new StringBuilder();
		sql.append(" select jfxmdm,jfxmmc from xg_zjly_zhszf_jfxmb where jfxmmc in (");
		for(int i = 0;i<jfxmmcs.length;i++){
			if(i==jfxmmcs.length-1){
				sql.append("'"+jfxmmcs[i]+"'");
			}else{
				sql.append("'"+jfxmmcs[i]+"',");
			}
		}
		sql.append(")");
		return dao.getListNotOut(sql.toString(), new String[]{});
	}

	/** 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2017-1-16 ����03:06:57
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * String �������� 
	 * @throws 
	 */
	public String getnewjfxmdm() {
		StringBuffer sb = new StringBuffer();
		sb.append(" select  max(to_number(jfxmdm))+1 jfxmdm from xg_zjly_zhszf_jfxmb  ");
		return dao.getOneRs(sb.toString(), new String[] {}, "jfxmdm");
	}
	
}
