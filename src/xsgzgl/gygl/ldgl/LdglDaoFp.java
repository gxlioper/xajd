/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2017-1-23 ����04:10:32 
 */  
package xsgzgl.gygl.ldgl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� yxy[����:1206]
 * @ʱ�䣺 2017-1-23 ����04:10:32 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class LdglDaoFp extends SuperDAOImpl<LdglForm> {

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(LdglForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	
	@Override
	public List<HashMap<String, String>> getPageList(LdglForm t, User user)
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
		this.setClass(LdglForm.class);
		this.setKey("bmdm");
		this.setTableName("ZXBZ_XXBMDM");
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2017-1-23 ����03:31:20
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param user
	 * @param ldglform
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getFpryList(User user,LdglForm ldglform) throws  Exception{
		String searchTj = SearchService.getSearchTj(ldglform.getSearchModel());
		String[] inputV = SearchService.getTjInput(ldglform.getSearchModel());
		StringBuffer sql = new StringBuffer();
		String[] lddmArray = ldglform.getCheckVal();
		List<String> paraArray = new ArrayList<String>();
		sql.append(" select  distinct a.* ");
		sql.append(" from (select a.yhm,");
		sql.append(" a.xm,");
		sql.append(" b.bmmc,");
		sql.append(" a.szbm bmdm,");
		sql.append(" decode(c.xb, '1', '��', '2', 'Ů', c.xb) xb");
		sql.append(" from yhb a");
		sql.append(" left join zxbz_xxbmdm b");
		sql.append(" on a.szbm = b.bmdm");
		sql.append(" left join fdyxxb c");
		sql.append(" on a.yhm = c.zgh) a");
		sql.append("  where 1 = 1");
		
		//1���ѷ��䣬0��δ���� //��ѡû���ѷ���ҳǩ
		if("1".equals(ldglform.getSffp())){
			sql.append(" and ");
			sql.append("  a.yhm in(");
			sql.append(" select yhm from xg_gygl_new_gyfdyb");
			if(lddmArray != null && lddmArray.length > 0){
				if(lddmArray != null && lddmArray.length > 0){
					sql.append(" where lddm = ?");
					paraArray.add(lddmArray[0]);
				}
			}
			sql.append(" )");
		}else{
			sql.append(" and ");
			sql.append("  a.yhm not in(");
			sql.append(" select yhm from xg_gygl_new_gyfdyb");
			sql.append(" where lddm in(");
			for (int i = 0; i < lddmArray.length; i++) {
				sql.append("?");
				paraArray.add(lddmArray[i]);
				if(i != lddmArray.length-1){
					sql.append(",");
				}
			}
			sql.append(")");
			sql.append(" )");
		}
		for (int i = 0; i < inputV.length; i++) {
			paraArray.add(inputV[i]);
		}
		inputV = paraArray.toArray(new String[]{});
		sql.append(searchTj);
		return getPageList(ldglform, sql.toString(), inputV);
	}

}
