/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-1-27 ����10:10:37 
 */
package com.zfsoft.xgxt.xsxx.fbgl.gzsd.gzpz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �ְ����
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2014-1-27 ����10:10:37
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class FbglGzpzTjXxDao extends SuperDAOImpl<FbglGzpzTjXxForm> {

	
	@Override
	public List<HashMap<String, String>> getPageList(FbglGzpzTjXxForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}
	
	@Override
	public List<HashMap<String, String>> getPageList(FbglGzpzTjXxForm t,
			User user) throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}
	/**
	 * 
	 * @����: ������ϸ������Ϣ
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-4-4 ����11:17:54
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param pzgzid
	 * @return
	 * List<HashMap<String,String>> ��������
	 */
	public List<HashMap<String, String>> getTJpzxxId(String pzgzid){
		StringBuffer sb=new StringBuffer();
		sb.append("select * from xg_xsxx_fbbxhgl_tjgzpzsxb where pzgzid=? order by sx");
		return dao.getListNotOut(sb.toString(), new String[]{pzgzid});
	}
	/**
	 * 
	 * @����: ������������id��ȡ������ϸ��Ϣ
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-4-4 ����11:18:21
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param pzgzid
	 * @param tjgzid
	 * @return
	 * List<HashMap<String,String>> ��������
	 */
	public List<HashMap<String, String>> getTJpzxxId(String pzgzid,String tjgzid){
		StringBuffer sb=new StringBuffer();
		sb.append("select * from xg_xsxx_fbbxhgl_tjgzpzsxb where pzgzid=? and tjgzid=? order by sx");
		return dao.getListNotOut(sb.toString(), new String[]{pzgzid,tjgzid});
	}
	/**
	 * 
	 * @����: �������ù���id��ȡ���õ�������������
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-2-27 ����10:59:12
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param pzgzid
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getTjgzxx(String pzgzid){
		StringBuffer sb=new StringBuffer();
		sb.append("select tjgzid from xg_xsxx_fbbxhgl_tjgzpzsxb where pzgzid=? group by tjgzid");
		return dao.getListNotOut(sb.toString(), new String[]{pzgzid});
	}

	/**
	 * 
	 * @����: �������ö�Ӧ����������
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-2-18 ����02:41:19
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param gzpzid
	 * @return
	 * List<HashMap<String,String>> �������� 
	 */
	public List<HashMap<String, String>> getGzpzTjxxLx(String gzpzid){
		StringBuffer sql = new StringBuffer();
		sql.append(" select distinct a.tjgzid,b.tjgzmc from XG_XSXX_FBBXHGL_TJGZPZSXB a ");
		sql.append("left join XG_XSXX_FBBXHGL_TJGZSZB b on a.tjgzid=b.tjgzid where pzgzid=?");
		return dao.getListNotOut(sql.toString(), new String[]{gzpzid});
	}
	/**
	 * 
	 * @����: ���ݹ������ͻ�ȡ����������ϸ��Ϣ
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-2-18 ����02:49:36
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param tjgzid
	 * @param gzpzid
	 * @return
	 * List<HashMap<String,String>> �������� 
	 */
	public List<HashMap<String, String>> getGzpzTjxxForLx(String tjgzid,String gzpzid){
		StringBuffer sql = new StringBuffer();
		sql.append("select a.*,decode(wsbl,'0','������','1','����','') wsblT");
		sql.append(",decode(sfkxg,'0','�����޸�','1','���޸�','') sfkxgT");
		sql.append(" from(select a.*, b.tjszmc,b.xxz xxzsz,b.xxlx,b.ylz ylzqz");
		sql.append(" from xg_xsxx_fbbxhgl_tjgzpzsxb a");
		sql.append(" left join xg_xsxx_fbbxhgl_tjgzszb b");
		sql.append(" on a.tjgzid = b.tjgzid");
		sql.append(" and a.tjszzd = b.tjszzd");
		sql.append(" where b.tjgzid =?");
		sql.append(" and a.pzgzid =?");
		sql.append(")a ");
		sql.append(" order by a.sx asc");
		return dao.getListNotOut(sql.toString(), new String[]{tjgzid,gzpzid});
	}
	/**
	 * 
	 * @����: ��ȡ�Ƿ���޸���Ϣ
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-2-21 ����02:36:29
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param pzgzid
	 * @param sfkxg 1���޸�/0�����޸�
	 * @return
	 * List<HashMap<String,String>> �������� 
	 */
	public List<HashMap<String,String>> getXgXx(String pzgzid,String sfkxg){
		StringBuffer sb=new StringBuffer();
		sb.append("select b.tjszmc,b.ylz from xg_xsxx_fbbxhgl_tjgzpzsxb a left join XG_XSXX_FBBXHGL_TJGZSZB b ");
		sb.append(" on a.tjgzid=b.tjgzid and a.tjszzd=b.tjszzd");
		sb.append(" where a.pzgzid=? and a.sfkxg=?");
		return dao.getListNotOut(sb.toString(),new String[]{pzgzid,sfkxg});
	}
	/**
	 * 
	 * @����: ��ȡ��Ӧ��ŵ���Ϣ
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-2-19 ����03:06:03
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param gzpzid
	 * @param sx
	 * @return
	 * List<HashMap<String,String>> �������� 
	 */
	public HashMap<String, String> getGzpzTjgz(String pzgzid,String tjgzid,String tjszzd,String sx){
		List<String> param=new ArrayList<String>();
		param.add(pzgzid);
		param.add(tjgzid);
		param.add(sx);
		StringBuffer sql = new StringBuffer();
		sql.append("select * from XG_XSXX_FBBXHGL_TJGZPZSXB where pzgzid=? and tjgzid=? and sx=?");
		if(StringUtils.isNotNull(tjszzd)){
			sql.append(" and tjszzd=?");
			param.add(tjszzd);
		}
		return dao.getMapNotOut(sql.toString(), param.toArray(new String[]{}));
	}
	@Override
	protected void setTableInfo() {
		this.setKey("pk");
		this.setTableName("xg_xsxx_fbbxhgl_tjgzpzsxb");
		this.setClass(FbglGzpzTjXxForm.class);
	}

}
