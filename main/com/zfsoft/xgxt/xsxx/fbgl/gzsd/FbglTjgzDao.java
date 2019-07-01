/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-1-27 ����09:43:40 
 */
package com.zfsoft.xgxt.xsxx.fbgl.gzsd;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.xsxx.fbgl.gzsd.gzpz.FbglGzpzTjForm;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �ְ����ģ��
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2014-1-27 ����09:43:40
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class FbglTjgzDao extends SuperDAOImpl<FbglTjgzForm> {

	@Override
	public List<HashMap<String, String>> getPageList(FbglTjgzForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	@Override
	public List<HashMap<String, String>> getPageList(FbglTjgzForm t, User user)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	@Override
	protected void setTableInfo() {
		this.setKey("tjgzid");
		this.setTableName("xg_xsxx_fbbxhgl_tjgzszb");
		this.setClass(FbglTjgzForm.class);
	}

	/**
	 * @����: ��ȡ��������
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-1-27 ����05:05:30
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param tjgzid
	 * @return List<HashMap<String,String>> ��������
	 */
	public List<HashMap<String, String>> getTjlx(String[] tjgzid) {
		StringBuffer sql = new StringBuffer();
		sql
				.append(" select distinct a.tjgzid,a.tjgzmc from XG_XSXX_FBBXHGL_TJGZSZB a");
		sql.append(" where a.tjgzid in(");
		int i = 0;
		for (String id : tjgzid) {
			sql.append("?");
			if (i + 1 < tjgzid.length) {
				sql.append(",");
			}
			i++;
		}
		sql.append(")");
		return dao.getListNotOut(sql.toString(), tjgzid);
	}
	/**
	 * 
	 * @����: ����������ϸ��Ϣ
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-1-27 ����05:06:52
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param tjgzid
	 * @return
	 * List<HashMap<String,String>> �������� 
	 */
	public List<HashMap<String, String>> getTjpzXx(String tjgzid) {
		StringBuffer sb = new StringBuffer();
		sb.append("select * from XG_XSXX_FBBXHGL_TJGZSZB where tjgzid=?");
		sb.append(" order by sx");
		return dao.getListNotOut(sb.toString(), new String[] { tjgzid });
	}
	/**
	 * 
	 * @����:��ȡ��������������Ϣ
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-2-10 ����02:14:16
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param tjgzid
	 * @param tjszzd
	 * @return
	 * List<HashMap<String,String>> �������� 
	 */
	public HashMap<String, String> getTjNrpzXx(String tjgzid,String tjszzd) {
		StringBuffer sb = new StringBuffer();
		sb.append("select * from XG_XSXX_FBBXHGL_TJGZSZB where tjgzid=? and tjszzd=?");
		return dao.getMapNotOut(sb.toString(), new String[] { tjgzid,tjszzd });
	}
	
	public String getMrylz(String[] tableMsg) {
		StringBuffer sql = new StringBuffer();
		sql.append("select "+tableMsg[1]+" from "+tableMsg[0]+" where rownum=1 and "+tableMsg[1]+" is not null");
		return dao.getOneRs(sql.toString(), new String[] {},tableMsg[2]);
	}
	/**
	 * 
	 * @����:��֤���������Ƿ��Ѵ���
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-9-15 ����02:08:34
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param pzgzid
	 * @param gzmc
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean isHave(String pzgzid,String gzmc){
		StringBuffer sql = new StringBuffer();
		String id=null==pzgzid?"-1":pzgzid;
		sql.append("select count(1) num from xg_xsxx_fbbxhgl_tjgzpzb where pzgzmc=? and pzgzid <> ?");
		String num =  dao.getOneRs(sql.toString(), new String[] {gzmc,id},"num");
		return Integer.parseInt(num)>0;
	}
}
