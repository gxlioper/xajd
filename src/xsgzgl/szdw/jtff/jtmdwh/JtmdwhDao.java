/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-3-8 ����02:28:04 
 */  
package xsgzgl.szdw.jtff.jtmdwh;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xsgzgl.gygl.xyzsgl.jg.XyzsglForm;
import xsgzgl.szdw.jtff.jtff.JtffForm;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����Դ[����:1206]
 * @ʱ�䣺 2016-3-8 ����02:28:04 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class JtmdwhDao extends SuperDAOImpl<JtmdwhForm> {

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(JtmdwhForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(JtmdwhForm t, User user)
			throws Exception {
		// TODO �Զ����ɷ������
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchGzjlTjByUser(user, "t", "xydm", "");
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* from (");
		sql.append(" select t.*,");
		sql.append(" (case (t1.xb)");
		sql.append("  when '1' then");
		sql.append(" '��'");
		sql.append(" when '2' then");
		sql.append(" 'Ů'");
		sql.append(" else");
		sql.append(" t1.xb");
		sql.append(" end) xb,");
		sql.append(" t1.xm,");
		sql.append(" t2.bmmc,");
		sql.append(" t2.bmdm,");
		sql.append(" t2.bmdm xydm,");
		sql.append(" t3.dbrs");
		sql.append(" from XG_SZDW_NEW_JTFFRYB t");
		sql.append(" left join fdyxxb t1");
		sql.append(" on t.zgh = t1.zgh");
		sql.append(" left join zxbz_xxbmdm t2");
		sql.append(" on t1.bmdm = t2.bmdm ");
		sql.append(" left join");
		sql.append(" ( select nvl(sum(n.dbrs),0)  dbrs,m.zgh  ");
		sql.append(" from fdyxxb m left join  ");
		sql.append(" ( select  count(xh) dbrs,zgh from view_xsjbxx a left join");
		sql.append(" (select bjdm, zgh");
		sql.append("  from fdybjb");
		sql.append(" union");
		sql.append(" select bjdm, zgh");
		sql.append(" from bzrbbb) b");
		sql.append(" on a.bjdm = b.bjdm");
		sql.append("  ");
		sql.append(" group by zgh) n on m.zgh = n.zgh group by m.zgh) t3");
		sql.append(" on t.zgh = t3.zgh");
		sql.append(" where t.jtlb='");
		sql.append( t.getJtlb()+"'");
		sql.append(" )t  where 1=1");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		// TODO �Զ����ɷ������
		super.setClass(JtmdwhForm.class);
		super.setKey("id");
		super.setTableName("XG_SZDW_NEW_JTFFRYB");
		
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2016-3-10 ����02:02:38
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public int runDeletegdjtmd(String[] para) throws Exception{
		StringBuilder sql = new StringBuilder();
		int num = para.length;
		sql.append(" update XG_SZDW_NEW_JTFFRYB set jtlb = 'zc',gdffje = ''");
		sql.append(" where id in(");
		for(int i=0;i<num;i++){
			sql.append("?");
			if(i != num-1){
				sql.append(",");
			}
		}
		sql.append(" )");
		boolean result = dao.runUpdate(sql.toString(), para);
		return result ? num : 0;
	}

}
