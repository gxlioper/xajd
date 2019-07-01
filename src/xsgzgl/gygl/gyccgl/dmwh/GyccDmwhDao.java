package xsgzgl.gygl.gyccgl.dmwh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

public class GyccDmwhDao extends SuperDAOImpl<GyccDmwhForm> {
	
	/**
	 * 
	 * @����: ��Ʒ�Ʋ���ѯ
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-3-6 ����11:46:18
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param key
	 * @return
	 * @throws Exception
	 * GyccDmwhForm �������� 
	 * @throws
	 */
	@Override
	public List<HashMap<String, String>> getPageList(GyccDmwhForm t)
			throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from xg_gygl_new_ssccgl_wpwhb t where 1=1");
		sql.append(" ");
		// TODO �Զ����ɷ������
		return getPageList(t, sql.toString(), new String[]{});
	}
	
	/**
	 * 
	 * @����: ˳���̶���Ʒ��ѯ
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-3-6 ����11:46:18
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param key
	 * @return
	 * @throws Exception
	 * GyccDmwhForm �������� 
	 * @throws
	 */
	@Override
	public List<HashMap<String, String>> getPageList(GyccDmwhForm t, User user)
			throws Exception {
		// TODO �Զ����ɷ������
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from xg_gygl_new_ssccgl_shcdwhb t where 1=1");
		sql.append(" ");
		// TODO �Զ����ɷ������
		return getPageList(t, sql.toString(), new String[]{});
	}

	@Override
	protected void setTableInfo() {
		// TODO �Զ����ɷ������
		this.setClass(GyccDmwhForm.class);
		this.setKey("dm");
		this.setTableName("xg_gygl_new_ssccgl_wpwhb");
	}
	
	/**
	 * 
	 * @����: �𻵳̶Ȼ�ȡmodel
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-3-6 ����11:46:18
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param key
	 * @return
	 * @throws Exception
	 * GyccDmwhForm �������� 
	 * @throws
	 */
	public GyccDmwhForm getBscdForm(String key) throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from xg_gygl_new_ssccgl_shcdwhb where shcddm = ?");
		return getModel(sql.toString(),new String[]{key});
	}
	
	/**
	 * 
	 * @����: ��֤��Ʒ���롢��Ʒ�����Ƿ����
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-3-6 ����04:34:20
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean checkIsNotExistWpdm(String dm,String mc,String type){
		StringBuffer sql = new StringBuffer();
		ArrayList<String> paraList = new ArrayList<String>();
		sql.append(" select count(1) cnt from xg_gygl_new_ssccgl_wpwhb where 1=1");
		if(StringUtils.isNotNull(dm)){
			if("update".equals(type)){
				sql.append(" and dm != ?");
			}else{
				sql.append(" and dm = ?");
			}
			paraList.add(dm);
		}
		if(StringUtils.isNotNull(mc)){
			sql.append(" and mc = ?");
			paraList.add(mc);
		}
		
		return "0".equals(dao.getOneRs(sql.toString(), paraList.toArray(new String[]{}), "cnt"));
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����:�����𺦳̶�
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-3-6 ����05:16:41
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param form
	 * @param type
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveShcd(GyccDmwhForm form,String type) throws Exception{
		StringBuffer sql = new StringBuffer();
		List<String> paraList = new ArrayList<String>();
		if("add".equals(type)){
			sql.append("insert into xg_gygl_new_ssccgl_shcdwhb(shcddm,shcdmc,je) values(?,?,?)");
			paraList.add(form.getShcddm());
			paraList.add(form.getShcdmc());
			paraList.add(form.getJe());
		}else{
			sql.append("update xg_gygl_new_ssccgl_shcdwhb set shcdmc = ?,je = ? where shcddm = ?");
			paraList.add(form.getShcdmc());
			paraList.add(form.getJe());
			paraList.add(form.getShcddm());
		}
	    return dao.runUpdate(sql.toString(), paraList.toArray(new String[]{}));
	}
	
	/**
	 * 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-3-6 ����05:34:53
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param dm
	 * @param mc
	 * @param type
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean checkIsNotExistShcd(String dm,String mc,String type){
		StringBuffer sql = new StringBuffer();
		ArrayList<String> paraList = new ArrayList<String>();
		sql.append(" select count(1) cnt from xg_gygl_new_ssccgl_shcdwhb where 1=1");
		if(StringUtils.isNotNull(dm)){
			if("update".equals(type)){
				sql.append(" and shcddm != ?");
			}else{
				sql.append(" and shcddm = ?");
			}
			paraList.add(dm);
		}
		if(StringUtils.isNotNull(mc)){
			sql.append(" and shcdmc = ?");
			paraList.add(mc);
		}
		
		return "0".equals(dao.getOneRs(sql.toString(), paraList.toArray(new String[]{}), "cnt"));
	}
	
	/**
	 * 
	 * @����:ɾ���𺦳̶�
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-3-6 ����05:50:27
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param shcddm
	 * @return
	 * @throws Exception
	 * int �������� 
	 * @throws
	 */
	public int DelShcd(String[] shcddm) throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append(" delete from xg_gygl_new_ssccgl_shcdwhb where shcddm in(");
		for (int i = 0; i < shcddm.length; i++) {
			sql.append("?");
			if(i != shcddm.length-1){
				sql.append(",");
			}
		}
		sql.append(")");
		return dao.runDelete(sql.toString(), shcddm);
	}
	
	/**
	 * 
	 * @����: ��Ʒ�����Ƿ��ѱ�ʹ��
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-3-7 ����09:59:17
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * String[] �������� 
	 * @throws
	 */
	public boolean isWpdmNotUserd(String[] dms){
		StringBuilder sql = new StringBuilder();
		List<String> paraList = new ArrayList<String>();
		sql.append(" select count(1) cnt from xg_gygl_new_ssccgl_qswpshb");
		sql.append(" where wpdm in(");
		for (int i = 0; i < dms.length; i++) {
			sql.append("?");
			paraList.add(dms[i]);
			if(i != dms.length-1){
				sql.append(",");
			}
		}
		sql.append(" )");
		return "0".equals(dao.getOneRs(sql.toString(), paraList.toArray(new String[]{}), "cnt"));
	}
	
	/**
	 * 
	 * @����: �𺦳̶��Ƿ��ѱ�ʹ��
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-3-7 ����09:59:17
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * String[] �������� 
	 * @throws
	 */
	public boolean isShcdNotUserd(String[] dms){
		StringBuilder sql = new StringBuilder();
		List<String> paraList = new ArrayList<String>();
		sql.append(" select count(1) cnt from xg_gygl_new_ssccgl_qswpshb");
		sql.append(" where shcd in(");
		for (int i = 0; i < dms.length; i++) {
			sql.append("?");
			paraList.add(dms[i]);
			if(i != dms.length-1){
				sql.append(",");
			}
		}
		sql.append(" )");
		return "0".equals(dao.getOneRs(sql.toString(), paraList.toArray(new String[]{}), "cnt"));
	}
}
