package xsgzgl.qgzx.glygl;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;
import xsgzgl.gygl.xyzsgl.jg.XyzsglForm;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

public class XqglyDao extends SuperDAOImpl<XqglyForm> {

	@Override
	public List<HashMap<String, String>> getPageList(XqglyForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	@Override
	public List<HashMap<String, String>> getPageList(XqglyForm t, User user)
			throws Exception {
		// TODO �Զ����ɷ������
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.zgh,t.xq,t1.xm,t1.bmmc,t2.xqmc,t1.xb from xg_zjzyy_xqdzb t");
        sql.append(" left join view_fdyxx t1");
        sql.append(" on t.zgh = t1.zgh");
        sql.append(" left join dm_zju_xq t2");
        sql.append(" on t.xq = t2.dm  where 1=1 order by t.zgh,t.xq");
		return getPageList(t, sql.toString(), new String[]{});
	}

	@Override
	protected void setTableInfo() {
		// TODO �Զ����ɷ������
		this.setTableName("xg_zjzyy_xqdzb");
		this.setKey("zgh");
		this.setClass(XqglyForm.class);
	}
	
	/**
	 * 
	 * @����: ��֤�Ƿ��ظ�
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-9-6 ����02:27:40
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param zgh
	 * @param xq
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean checkIsRepeat(String zgh,String xq){
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) cnt from xg_zjzyy_xqdzb where xq = ? and zgh = ?");
		String rs = dao.getOneRs(sql.toString(), new String[]{xq,zgh},"cnt");
		return Integer.valueOf(rs) > 0 ? false :true;
	}

	/**
	 * 
	 * @����: ��ȡУ��
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-9-6 ����02:28:02
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getXqList(){
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from dm_zju_xq order by dm");
		return dao.getListNotOut(sql.toString(), new String[]{});
	}
	
	/**
	 *ɾ�� 
	 * @throws Exception 
	 */
	public boolean runDeletes(String[] ids) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" delete from xg_zjzyy_xqdzb where zgh||xq in(");
		for (int i = 0; i < ids.length; i++) {
			sql.append("?");
			if(i != ids.length-1){
				sql.append(",");
			}
		}
		sql.append(" ");
		sql.append(" )");
		return dao.runUpdate(sql.toString(), ids);
	}

}
