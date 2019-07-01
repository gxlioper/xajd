/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-7-27 ����04:11:27 
 */  
package xgxt.xtwh.mmzh;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����Դ[����:1206]
 * @ʱ�䣺 2015-7-27 ����04:11:27 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class MmZhDao extends SuperDAOImpl<MmZhForm> {

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(MmZhForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, com.zfsoft.xgxt.base.dao.impl.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(MmZhForm t, User user)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		// TODO �Զ����ɷ������
		super.setClass(MmZhForm.class);
		super.setKey("yhm");
		super.setTableName("XG_LOGIN_MB");
	}
	
	//������������ѡ��list,���û�������������������ѡ��
	public List<HashMap<String, String>> getMbList(){
		StringBuilder sql = new StringBuilder();
		sql.append("select wtid,mbwt from xg_login_wt ");
		return dao.getListNotOut(sql.toString(), new String[]{});
	}
	
	//��ȡ��½�û������õ���������
	public  HashMap<String, String> getmbjl(String yhm){
		StringBuilder sql = new StringBuilder();
		sql.append("select t1.wtid,t1.wtda from xg_login_mb t1,xg_login_wt t2   where t1.yhm = ? and t1.wtid=t2.wtid  ");
		return dao.getMapNotOut(sql.toString(), new String[]{yhm});
	}
	
	//��֤�û������Ƿ���ڸü�¼
	public String checkYhbExits(String yhm){
		StringBuilder sql = new StringBuilder();
		sql.append("select yhm from yhb  where yhm = ?  ");
		return dao.getOneRs(sql.toString(), new String[]{yhm}, "yhm");
	}
	
	//��֤ѧ����������Ƿ���ڸü�¼
	public String checkXsmmbExits(String xh){
		StringBuilder sql = new StringBuilder();
		sql.append("select xh from xsmmb  where xh = ?   ");
		return dao.getOneRs(sql.toString(), new String[]{xh}, "xh");
	}
	
	//��֤�����һر����Ƿ���ڸü�¼
	public String checkMbwtExits(String yhm){
		StringBuilder sql = new StringBuilder();
		sql.append("select t1.wtid from xg_login_mb t1  where t1.yhm = ?   ");
		return dao.getOneRs(sql.toString(), new String[]{yhm}, "wtid");
	}
	
	public HashMap<String, String> getMbmap(String yhm){
		StringBuilder sql = new StringBuilder();
		sql.append("select wtid,mbwt from xg_login_wt where wtid in (select wtid from xg_login_mb where yhm = ?)");
		return dao.getMapNotOut(sql.toString(), new String[]{yhm});
	}
}
