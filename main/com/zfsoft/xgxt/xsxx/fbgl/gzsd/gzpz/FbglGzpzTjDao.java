/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-1-27 ����10:09:52 
 */
package com.zfsoft.xgxt.xsxx.fbgl.gzsd.gzpz;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.base.extend.SuperDAOImplExtend;
import com.zfsoft.xgxt.xsxx.fbgl.xsxx.FbglXsxxForm;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �ְ����
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2014-1-27 ����10:09:52
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class FbglGzpzTjDao extends SuperDAOImplExtend<FbglGzpzTjForm> {

	@Override
	public List<HashMap<String, String>> getPageList(FbglGzpzTjForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	@Override
	public List<HashMap<String, String>> getPageList(FbglGzpzTjForm t, User user)
			throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());

		StringBuffer sql = new StringBuffer();
		sql
				.append(" select * from (select t.*,t2.gzmc gzmc, decode(t.qyzt,'0','ͣ��','1','����',t.qyzt) qyztmc, decode(t.sfnz, '0', '��', '1', '��', t.qyzt) sfnzmc,");
		sql.append(" (case when pzgzid in (select pzgzid from XG_XSXX_FBBXHGL_BJDM_LSB) then '��ʹ��'");
		sql.append("  when pzgzid in(select fbgz pzgzid from XG_XSXX_FBGL_XSXX_LSB) then '��ʹ��'");
		sql.append("  when pzgzid in(select bxhgz pzgzid from XG_XSXX_FBGL_XSXX_LSB) then '��ʹ��'");
		sql.append("  else 'δʹ��' end) sfysy");
		sql.append(" ,(case when pzgzid in (select pzgzid from XG_XSXX_FBBXHGL_BJDM_LSB) then '1'");
		sql.append("  when pzgzid in(select fbgz pzgzid from XG_XSXX_FBGL_XSXX_LSB) then '1'");
		sql.append("  when pzgzid in(select bxhgz pzgzid from XG_XSXX_FBGL_XSXX_LSB) then '1'");
		sql.append("  else '0' end) syzt");
		sql.append(",t.gzdm gzlx");
		sql.append(" from xg_xsxx_fbbxhgl_tjgzpzb t ");
		sql.append(" left join XG_XSXX_FBBXHGL_GZDMB t2 on t.gzdm=t2.gzdm ) a where 1=1 ");
		sql.append(" ");
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}
	/**
	 * 
	 * @����: ɾ���������ö�Ӧ��Ϣ
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-2-14 ����09:55:40
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param gzpzid
	 * @return
	 * int �������� 
	 */
	public int delGzpzXxtj(String gzpzid){
		StringBuffer sb=new StringBuffer();
		sb.append("delete xg_xsxx_fbbxhgl_tjgzpzsxb where pzgzid=?");
		try {
			return dao.runDelete(sb.toString(), new String[]{gzpzid});
		} catch (Exception e) {
			throw new RuntimeException("ɾ��ʧ��!");
		}
	}
	/**
	 * 
	 * @����: ������������
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-2-17 ����11:55:50
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param pzgzid
	 * @param tjgzid
	 * @return
	 * List<HashMap<String,String>> �������� 
	 */
	public List<HashMap<String, String>> getGzpznr(String pzgzid,String tjgzid){
		StringBuffer sb = new StringBuffer();
		sb.append("select a.*, b.tjgzmc tjgzmc");
		sb
				.append(" (select * from xg_xsxx_fbbxhgl_tjgzpzsxb");
		sb.append(" where a.pzgzid = ?");
		sb.append(" and a.tjgzid = ?");
		sb.append(") a left join xg_xsxx_fbbxhgl_tjgzszb b");
		sb.append(" on a.tjgzid = b.tjgzid");
		return dao
				.getListNotOut(sb.toString(), new String[] { pzgzid, tjgzid });
	}
	/**
	 * 
	 * @����: �Ƿ�����
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-2-17 ����11:56:02
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param gzdm
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean sfQy(String gzdm){
		StringBuffer sb = new StringBuffer();
		sb.append("select * from xg_xsxx_fbbxhgl_tjgzpzb where gzdm=? and qyzt=?");
		 List<HashMap<String, String>> list=dao.getListNotOut(sb.toString(),new String[]{gzdm,"1"});
		return (null==list||list.size()<=0)?false:true;
	}
	/**
	 * 
	 * @����: ��ȡ���������б�
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-2-17 ����11:57:52
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 */
	public List<HashMap<String, String>> getTjnrList(){
		StringBuffer sb = new StringBuffer();
		sb.append("select pzgzid,pzgzmc from xg_xsxx_fbbxhgl_tjgzpzb");
		return dao.getListNotOut(sb.toString(),new String[]{});
	}
	/**
	 * 
	 * @����: ��ȡ���������б�
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-4-2 ����09:18:21
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param key Ψһ���� gzdm
	 * @return
	 * List<HashMap<String,String>> �������� 
	 */
	public List<HashMap<String, String>> getTjnrList(String key){
		StringBuffer sb = new StringBuffer();
		sb.append("select pzgzid,pzgzmc from xg_xsxx_fbbxhgl_tjgzpzb");
		sb.append(" where gzdm=?");
		return dao.getListNotOut(sb.toString(),new String[]{key});
	}
	/**
	 * 
	 * @����: ��ȡ���������б�(�Ѿ����õ�)
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-4-2 ����09:18:21
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param key Ψһ���� gzdm
	 * @param sfqy �Ƿ��Ѿ�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 */
	public List<HashMap<String, String>> getTjnrList(String key,String sfqy){
		StringBuffer sb = new StringBuffer();
		sb.append("select pzgzid,pzgzmc from xg_xsxx_fbbxhgl_tjgzpzb");
		sb.append(" where gzdm=?");
		sb.append(" and qyzt=?");
		return dao.getListNotOut(sb.toString(),new String[]{key,sfqy});
	}
	@Override
	protected void setTableInfo() {
		this.setKey("pzgzid");
		this.setTableName("xg_xsxx_fbbxhgl_tjgzpzb");
		this.setClass(FbglGzpzTjForm.class);
	}

}
