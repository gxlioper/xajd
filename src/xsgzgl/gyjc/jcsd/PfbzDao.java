package xsgzgl.gyjc.jcsd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

public class PfbzDao extends SuperDAOImpl<PfbzForm> {

	@Override
	public List<HashMap<String, String>> getPageList(PfbzForm t) throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	@Override
	public List<HashMap<String, String>> getPageList(PfbzForm t, User user)
			throws Exception {
		// TODO �Զ����ɷ������
		StringBuilder sql = new StringBuilder();
		List<String> paraList = new ArrayList<String>();
		sql.append(" SELECT RPAD(' ', 2 * (LEVEL - 1)) || wsqkyq wsqkyq,");
		sql.append(" t.guid,");
		sql.append(" t.fjid,");
		sql.append(" t.xh");
		sql.append(" FROM XG_JHZY_GYGL_PFBZ t");
		sql.append(" where t.jjlx = ? and t.xydm = ? and js = ?");
		paraList.add(t.getJjlx());
		paraList.add(t.getXydm());
		paraList.add(t.getJs());
		if(StringUtils.isNotNull(t.getWsqkyq())){
			sql.append(" and t.wsqkyq like ? ");
			paraList.add("%"+t.getWsqkyq()+"%");
		}
		sql.append(" START WITH FJID = 'top'");
		sql.append(" CONNECT BY FJID = PRIOR GUID");
		sql.append(" ORDER SIBLINGS BY t.xh");
		sql.append(" ");
		t.getPages().setPageSize(Integer.MAX_VALUE);
		return getPageList(t, sql.toString(), paraList.toArray(new String[]{}));
	}

	@Override
	protected void setTableInfo() {
		// TODO �Զ����ɷ������
		this.setKey("guid");
		this.setClass(PfbzForm.class);
		this.setTableName("xg_jhzy_gygl_pfbz");
	}
	
	/**
	 *    
	 * @����: ��ѯ�����û���ظ�
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-4-11 ����05:17:04
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean checkRepeatXh(PfbzForm t){
		StringBuilder sql = new StringBuilder();
		List<String> paraList = new ArrayList<String>();
		sql.append(" select count(1) cnt");
		sql.append(" from xg_jhzy_gygl_pfbz");
		sql.append(" where xydm = ?");
		if("top".equals(t.getFjid())){
			sql.append(" and fjid = 'top'");
		}else{
			sql.append(" and fjid != 'top'");
		}
		sql.append(" and xh = ?");
		sql.append(" and js = ?");
		sql.append(" and jjlx = ?");
		paraList.add(t.getXydm());
		paraList.add(t.getXh());
		paraList.add(t.getJs());
		paraList.add(t.getJjlx());
		if(StringUtils.isNotNull(t.getGuid())){
			sql.append(" and guid !=?");
			paraList.add(t.getGuid());
		}
		String cnt = dao.getOneRs(sql.toString(), paraList.toArray(new String[]{}), "cnt");
		return "0".equals(cnt)?true:false;
	}
	
	/**
	 * 
	 * @����: ���ֱ�׼�Ƿ��ظ�
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-4-11 ����05:52:53
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean checkRepeat(PfbzForm t){
		StringBuilder sql = new StringBuilder();
		List<String> paraList = new ArrayList<String>();
		sql.append(" select count(1) cnt");
		sql.append(" from xg_jhzy_gygl_pfbz");
		sql.append(" where xydm = ?");
		sql.append(" and fjid = ?");
		sql.append(" and wsqkyq = ?");
		sql.append(" and js = ?");
		sql.append(" and jjlx = ?");
		paraList.add(t.getXydm());
		paraList.add(t.getFjid());
		paraList.add(t.getWsqkyq());
		paraList.add(t.getJs());
		paraList.add(t.getJjlx());
		if(StringUtils.isNotNull(t.getGuid())){
			sql.append(" and guid !=?");
			paraList.add(t.getGuid());
		}
		String cnt = dao.getOneRs(sql.toString(), paraList.toArray(new String[]{}), "cnt");
		return "0".equals(cnt)?true:false;
	}
	
	/**
	 * 
	 * @����:�����Ŀ�Ƿ�ʹ��
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-4-11 ����07:11:49
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean checkIsUserd(String[] guids){
		StringBuilder sql = new StringBuilder();
		List<String> paraList = new ArrayList<String>();
		sql.append(" select count(1) cnt from (select pfid from xg_jhzy_gygl_jcmxbz union select pfid from xg_jhzy_gygl_ccmxbz) where pfid in(");
		for (int i = 0; i < guids.length; i++) {
			sql.append("?");
			if(i != guids.length-1){
				sql.append(",");
			}
			paraList.add(guids[i]);
		}
		sql.append(")");
		String cnt = dao.getOneRs(sql.toString(),paraList.toArray(new String[]{}),"cnt");
		return "0".equals(cnt) ? true :false;
	}
	
	/**
	 * 
	 * @����:����Ƿ�����Ӽ���Ŀ
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-4-11 ����07:19:45
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param guids
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean checkIsCzZjxm(String[] guids){
		StringBuilder sql = new StringBuilder();
		List<String> paraList = new ArrayList<String>();
		sql.append(" select count(1) cnt from xg_jhzy_gygl_pfbz where fjid in(");
		for (int i = 0; i < guids.length; i++) {
			sql.append("?");
			if(i != guids.length-1){
				sql.append(",");
			}
			paraList.add(guids[i]);
		}
		sql.append(")");
		String cnt = dao.getOneRs(sql.toString(),paraList.toArray(new String[]{}),"cnt");
		return "0".equals(cnt) ? true :false;
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����: ɾ�����ֱ�׼
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-4-11 ����07:21:45
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param guids
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean delPfbz(String[] guids) throws Exception{
		StringBuilder sql = new StringBuilder();
		List<String> paraList = new ArrayList<String>();
		sql.append(" delete from xg_jhzy_gygl_pfbz where guid in(");
		for (int i = 0; i < guids.length; i++) {
			sql.append("?");
			if(i != guids.length-1){
				sql.append(",");
			}
			paraList.add(guids[i]);
		}
		sql.append(")");
		return dao.runUpdateNotCommit(sql.toString(), paraList.toArray(new String[]{}));
	}
	
	/**
	 * 
	 * @����:��ȡ���ֱ�׼������������
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-4-12 ����09:05:14
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getXmSelectList(PfbzForm t){
		StringBuilder sql = new StringBuilder();
		sql.append(" select guid dm, wsqkyq mc");
		sql.append(" from xg_jhzy_gygl_pfbz t");
		sql.append(" where t.jjlx = ?");
		sql.append(" and t.js = ?");
		sql.append(" and t.xydm = ?");
		sql.append(" and t.fjid = 'top'");
		sql.append(" order by t.xh");
		return dao.getListNotOut(sql.toString(), new String[]{t.getJjlx(),t.getJs(),t.getXydm()});
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����:�޸�model
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-4-12 ����11:55:04
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * PfbzForm �������� 
	 * @throws
	 */
	public PfbzForm getPfbzModel(String guid) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.*, t1.wsqkyq fjmc");
		sql.append(" from XG_JHZY_GYGL_PFBZ t");
		sql.append(" left join XG_JHZY_GYGL_PFBZ t1");
		sql.append(" on t.fjid = t1.guid");
		sql.append("  where t.guid = ?");
		return getModel(sql.toString(), new String[]{guid});
	}
	
	public List<HashMap<String, String>>  getPfbzListAjax(String fjid){
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.guid dm, t.wsqkyq mc");
		sql.append(" from XG_JHZY_GYGL_PFBZ t where t.fjid=? order by t.xh asc");
		return dao.getListNotOut(sql.toString(), new String[]{fjid});
	}
	
}
