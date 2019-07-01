/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-6-22 ����10:42:00 
 */  
package com.zfsoft.xgxt.zjly.zhf.sh;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.zjly.zhf.sq.ZhfForm;
import com.zfsoft.xgxt.zjly.zhf.zhfdr.ZhfDrDao;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: �ۺϷ����(������һ�仰��������������) 
 * @���ߣ� ����[����:1282]
 * @ʱ�䣺 2016-6-22 ����10:42:00 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ZhfshDao extends SuperDAOImpl<ZhfForm>{

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(ZhfForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(ZhfForm t, User user)
			throws Exception {
			String sqll = "select distinct a.xmmkdm,b.xmmkmc from xg_zjly_zhszf_drjlb a left join xg_zjly_zhszf_mkxmb b on a.xmmkdm = b.xmmkdm order by a.xmmkdm asc";
			List<HashMap<String,String>> list = dao.getListNotOut(sqll, new String[]{} );//�õ��Ѵ�����Ŀģ�����
			
			String searchTj = SearchService.getSearchTj(t.getSearchModel());
			String[] inputV = SearchService.getTjInput(t.getSearchModel());
			String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
			StringBuilder sql = new StringBuilder();
			sql.append(" select t.*,to_number(nvl(t.fs0,0))+to_number(nvl(t.fs1,0))+to_number(nvl(t.fs2,0))+to_number(nvl(t.fs3,0))+to_number(nvl(t.fs4,0)) zf from (select a.*,case when to_number(a.shzt1)>0 then 'δ��' else '����' end shztmc,case when to_number(a.shzt1) > 0 then '1' else '0' end shzt from (select a.xh,a.xm,a.xb,a.nj,a.xymc,a.xydm,a.zydm,a.zymc,a.bjdm,a.bjmc");
			if(list.size()>0){
				for(int i = 0;i<list.size();i++){
//					sql.append(",(select sum(c.fs) from xg_zjly_zhszf_drjlb b left join xg_zjly_zhszf_jfxmb c on b.jfxmdm = c.jfxmdm where a.xh=b.xh and b.xmmkdm='"+ list.get(i).get("xmmkdm")+ "' group by b.xmmkdm) fs"+i);
					sql.append(",( select  case when fs>k.xf then k.xf else fs end fs  from( select xh,to_number(xf) xf,sum(fs1) fs from  ( select xh,xf,case when  (to_number(z.fs)*z.cou) > to_number(z.xdfs) then to_number(z.xdfs) else (to_number(z.fs) * z.cou) end fs1 " +
							" from (select xh,b.jfxmdm,fs,xdfs,xf,count(*) cou from xg_zjly_zhszf_drjlb b left join xg_zjly_zhszf_jfxmb c " +
							" on b.jfxmdm = c.jfxmdm  left join XG_ZJLY_ZHSZF_MKXMB d on b.xmmkdm =d.xmmkdm where b.xmmkdm='"+ list.get(i).get("xmmkdm")+ "' and b.shzt='1' group by xh,b.jfxmdm,fs,xdfs,xf )z) group by xh,xf) k  where  a.xh=k.xh) fs"+i);
				}
			}
//			sql.append(",(select sum(c.fs) from xg_zjly_zhszf_drjlb b left join xg_zjly_zhszf_jfxmb c on b.jfxmdm = c.jfxmdm where a.xh=b.xh) zf, ");
			sql.append(", (select count(1) from xg_zjly_zhszf_drjlb b where b.xh = a.xh and b.shzt = '0') shzt1 ");
			sql.append(" from view_xsxxb a ");
			sql.append(" where exists (select 1 from xg_zjly_zhszf_drjlb d where a.xh = d.xh) and a.sfzx = '��У' ");
			sql.append(" )a) t ");
			sql.append(" where 1=1 ");
			if ("xg".equals(t.getLb())) {
				sql.append(" and shzt='0' ");	
			}
			sql.append(searchTj);
			sql.append(searchTjByUser);
			return getPageList(t, sql.toString(), inputV);
		}
	
	

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		super.setClass(ZhfForm.class);
		super.setKey("id");
		super.setTableName("xg_zjly_zhszf_drjlb");
		
	}
	
	/** 
	 * @����:��ȡ��ѧ���������Ŀģ��list(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-6-22 ����05:03:12
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String,String>> getXmmkList(){
		String sqll = "select distinct a.xmmkdm,b.xmmkmc from xg_zjly_zhszf_drjlb a left join xg_zjly_zhszf_mkxmb b on a.xmmkdm = b.xmmkdm order by a.xmmkdm asc";
		List<HashMap<String,String>> list = dao.getListNotOut(sqll, new String[]{} );
		return list;
	}
	
	/** 
	 * @����:�õ�������Ŀ(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-6-23 ����01:41:40
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String,String>> getJfxmYsdByXh(String xh){
		String sql = "select a.*,case when to_number(length(a.lrr))>10 then 'ѧ������' else (select bmmc||'����' from view_fdyxx where zgh =a.lrr) end  lb,b.jfxmmc,b.fs,c.xmmkmc from xg_zjly_zhszf_drjlb a left join xg_zjly_zhszf_jfxmb b on a.jfxmdm = b.jfxmdm left join xg_zjly_zhszf_mkxmb c on a.xmmkdm = c.xmmkdm where xh = ? and shzt = '1' order by a.xmmkdm asc";
		return dao.getListNotOut(sql, new String[]{xh});
	}
	
	/** 
	 * @����:�õ�δ����Ŀ(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-6-23 ����01:41:57
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String,String>> getJfxmWsdByXh(String xh){
		String sql = "select a.*,case when to_number(length(a.lrr))>10 then 'ѧ������' else (select bmmc||'����' from view_fdyxx where zgh =a.lrr) end  lb,b.jfxmmc,b.fs,c.xmmkmc from xg_zjly_zhszf_drjlb a left join xg_zjly_zhszf_jfxmb b on a.jfxmdm = b.jfxmdm left join xg_zjly_zhszf_mkxmb c on a.xmmkdm = c.xmmkdm where xh = ? and shzt = '0' order by a.xmmkdm asc";
		return dao.getListNotOut(sql, new String[]{xh});
	}
	
	/** 
	 * @����:������(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-6-23 ����01:55:11
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param list
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws 
	 */
	public boolean saveSd(List<ZhfForm> list) throws Exception{
			StringBuilder sql = new StringBuilder();
			sql.append("insert into xg_zjly_zhszf_drjlb (xh,jfxmdm,xmmkdm,sxsm,cysj,shsj,shr,fj,fjmc,shzt)");
			if(null != list && list.size()>0 ){
				if(list.size() == 1){
					sql.append("values ('" + list.get(0).getXh() + "','" + list.get(0).getJfxmdm() + "','" + list.get(0).getXmmkdm() + "','" + list.get(0).getSxsm() + "','" + list.get(0).getCysj() + "','" + list.get(0).getShsj() + "','" + list.get(0).getShr() + "','" + list.get(0).getFj() + "','" +list.get(0).getFjmc() +"','"+ list.get(0).getShzt() + "')"); 
				}
				else{			
					for(int i = 0;i<list.size();i++){
						if(i>0){
							sql.append(" union all ");
						}
						sql.append(" select '" + list.get(i).getXh() + "','" + list.get(i).getJfxmdm() + "','" + list.get(i).getXmmkdm() + "','" + list.get(i).getSxsm() + "','" + list.get(i).getCysj() + "','" + list.get(0).getShsj() + "','" + list.get(0).getShr() + "','" + list.get(i).getFj() + "','" + list.get(i).getFjmc() + "','" + list.get(i).getShzt()+ "' from dual");
					}
				}
			}
			return dao.runUpdate(sql.toString(), new String[]{});
	}
	
	/**
	 * @throws Exception  
	 * @����:����ѡ����ѧ��������(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-6-23 ����01:55:28
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ids
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean saveSdByXh(String[] xhs,String shr,String shsj) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append("update xg_zjly_zhszf_drjlb set shzt = '1',shr = ?,shsj = ? where xh in ( ");
		for(int i = 0;i<xhs.length;i++){
			if(i!= xhs.length-1){
				sql.append("'"+xhs[i]+"',");
			}else{
				sql.append("'"+xhs[i]+"'");
			}			
		}
		sql.append(")");
		return dao.runUpdate(sql.toString(), new String[]{shr,shsj});
	}
	
	/** 
	 * @����:����id���(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-6-23 ����03:30:42
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ids
	 * @param shr
	 * @param shsj
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws 
	 */
	public boolean saveSdById(String[] ids,String shr,String shsj) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append("update xg_zjly_zhszf_drjlb set shzt = '1',shr = ?,shsj = ? where id in ( ");
		for(int i = 0;i<ids.length;i++){
			if(i!= ids.length-1){
				sql.append("'"+ids[i]+"',");
			}else{
				sql.append("'"+ids[i]+"'");
			}			
		}
		sql.append(")");
		return dao.runUpdate(sql.toString(), new String[]{shr,shsj});
	}

	/**
	 * @param thyj 
	 * @throws Exception  
	 * @����:�����˻صļƷ���
	 * @���ߣ�CP[���ţ�1352]
	 * @���ڣ�2017-1-10 ����01:49:27
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param idsss
	 * @param shr
	 * @param shsj
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean saveBack(String[] ids, String shr, String shsj, String thyj) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("update xg_zjly_zhszf_drjlb set shzt = '2',shr = ?,shsj = ?,thyj=? where id in ( ");
		for(int i = 0;i<ids.length;i++){
			if(i!= ids.length-1){
				sql.append("'"+ids[i]+"',");
			}else{
				sql.append("'"+ids[i]+"'");
			}			
		}
		sql.append(")");
		return dao.runUpdate(sql.toString(), new String[]{shr,shsj,thyj});
	}

	/** 
	 * @����:ȡ�˻صļ�¼
	 * @���ߣ�CP[���ţ�1352]
	 * @���ڣ�2017-1-10 ����05:00:16
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getJfxmThByXh(String xh) {
		String sql = "select a.*,case when to_number(length(a.lrr))>10 then 'ѧ������' else (select bmmc||'����' from view_fdyxx where zgh =a.lrr) end  lb,b.jfxmmc,b.fs,c.xmmkmc from xg_zjly_zhszf_drjlb a left join xg_zjly_zhszf_jfxmb b on a.jfxmdm = b.jfxmdm left join xg_zjly_zhszf_mkxmb c on a.xmmkdm = c.xmmkdm where xh = ? and shzt = '2' order by a.xmmkdm asc";
		return dao.getListNotOut(sql, new String[]{xh});
	}

	/**
	 * @throws Exception  
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�CP[���ţ�1352]
	 * @���ڣ�2017-3-13 ����07:52:17
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param time
	 * @param user
	 * @param xh
	 * void �������� 
	 * @throws 
	 */
	public void addlog(String time, User user, String values) throws Exception {
		ZhfDrDao zhfDrDao = new ZhfDrDao();
		HashMap<String, String> map = zhfDrDao.getJfxmxx(values);
		zhfDrDao.saveSd(time,user,map);
	}

	/**
	 * @throws Exception  
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2017-3-13 ����08:07:31
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param values
	 * void �������� 
	 * @throws 
	 */
	public void dellog(String values) throws Exception {
		String sql=" delete from xg_zjly_zhfsclog where logid =?";
		dao.runUpdate(sql.toString(), new String[]{values});
	}
}


