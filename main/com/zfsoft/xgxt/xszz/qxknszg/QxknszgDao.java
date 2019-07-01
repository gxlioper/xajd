/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-4-20 ����06:25:44 
 */  
package com.zfsoft.xgxt.xszz.qxknszg;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import com.zfsoft.xgxt.base.extend.SuperDAOImplExtend;
import com.zfsoft.xgxt.base.message.MessageUtil;


/**
 * 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ������[����:995]
 * @ʱ�䣺 2016-4-21 ����08:35:23 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class QxknszgDao extends SuperDAOImplExtend<QxknszgForm> {

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(QxknszgForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(QxknszgForm t, User user)
			throws Exception {

		//���ɸ߼���ѯ�������������ֵ 
		if("xn".equals(MessageUtil.getText("xszz.knsrd.sqzq"))){
			t.getSearchModel().setSearch_tj_xq(null);
		}
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		
		StringBuffer sql = new StringBuffer();	
		String view = "(select * from (select a.*,row_number()over(partition by xh order by xn desc,xq desc) rn from VIEW_NEW_DC_XSZZ_KNSRDCX a) where rn = 1 and nvl(sfqxrd,'0')<>'1')";
		sql.append("select a.*, rownum xl from "+view+" a where 1=1 ");
		
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		
		return getPageList(t, sql.toString(), inputV);
	}
	
	/**
	 * 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�������[���ţ�995]
	 * @���ڣ�2016-4-26 ����09:05:08
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String checkExistForKnsqxjlSave(QxknszgForm model) {
		
		StringBuilder sql = new StringBuilder(
				" select count(1) num from xg_xszz_new_knsqxjl where jgGuid=? ");
		String num = dao.getOneRs(sql.toString(), new String[] {model.getGuid()}, "num");
		return num;
		
	}

	/**
	 * 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�������[���ţ�995]
	 * @���ڣ�2016-4-26 ����09:05:16
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String checkExistForQxknszgUpdate(QxknszgForm model) {
		
		StringBuilder sql = new StringBuilder(
				" select count(1) num from xg_xszz_new_knsjgb where guid=? ");
		String num = dao.getOneRs(sql.toString(), new String[] { model.getGuid()}, "num");
		return num;
		
	}
	
	/**
	 * 
	 * @����:TODO(��ȡ��Ҫȡ�����������ʸ�����)
	 * @���ߣ�������[���ţ�995]
	 * @���ڣ�2016-4-27 ����09:04:28
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * String �������� 
	 * @throws
	 */
	public String getCountNum(QxknszgForm t,User user)throws Exception {
	
		
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());	
		StringBuffer sql = new StringBuffer();	
				
		sql.append( " select count(*) countNum from ( select * " );
		sql.append( " from (select * from (select a.*,row_number()over(partition by xh order by xn desc,xq desc) rn from VIEW_NEW_DC_XSZZ_KNSRDCX a) ");
		sql.append( "  where rn = 1 and nvl(sfqxrd,'0')<>'1' ) ) a ");
		sql.append( " where 1=1 " );
			
		sql.append(searchTj);
		sql.append(searchTjByUser);
		return dao.getOneRs(sql.toString(), inputV, "countNum");
		
	}
	
	/**
	 * 
	 * @����:TODO(��ȡ�����������϶��ʸ�����ȡ����������¼���в����������)
	 * @���ߣ�������[���ţ�995]
	 * @���ڣ�2016-4-26 ����01:55:44
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean cancelOneKnsrdzg(QxknszgForm model) throws Exception{ 
		//��ȡ�����������϶��ʸ�����ȡ����������¼���в����������
		updateOneKnsrdzg(model);						
		deleteKnsqxjl(model.getGuid());			
		StringBuffer insertKnsqxjl = new StringBuffer();		
		insertKnsqxjl.append(" insert into xg_xszz_new_knsqxjl ");
		insertKnsqxjl.append(" (jgguid,czr,czsj,qxyy) ");
		insertKnsqxjl.append(" values(?,?,?,?) ");
		
		try {
			dao.runUpdate(insertKnsqxjl.toString(), new String[]{model.getGuid(),model.getCzr(),model.getCzsj(),model.getQxyy()});
			return true;		
		} catch (SQLException e){
			e.printStackTrace();
			return false;		
		}
	}
	
	/**
	 * 
	 * @����:TODO(�Ƿ�ȡ���������϶��������϶�ʱ���ֶ�Ϊ0���������ֶΣ�ȡ����ʱ����ֶ�Ϊ1)
	 * @���ߣ�������[���ţ�995]
	 * @���ڣ�2016-4-26 ����01:51:28
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean updateOneKnsrdzg(QxknszgForm model) throws Exception{ 	
		
		//�����������϶�0��������ΪĬ�Ͻ������Ǹ��ֶε�ֵ1Ϊȡ���������϶�
		String[] inputValue = new String[]{"1",model.getGuid()}; 
		String sql = " update xg_xszz_new_knsjgb set sfqxrd=? where guid = ? ";			
		try {
			dao.runUpdate(sql,inputValue);
			return true;		
		} catch (SQLException e){
			e.printStackTrace();
			return false;		
		}
		
	}
	
	/**
	 * 
	 * @����:TODO(ȡ����δȡ����ȫ�����������϶��ʸ�)
	 * @���ߣ�������[���ţ�995]
	 * @���ڣ�2016-4-27 ����10:23:32
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean cancelAllKnsrdzg(QxknszgForm t,User user) throws Exception{
		
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());		
		
		StringBuffer sql = new StringBuffer();				
		String view = "(select * from (select a.*,row_number()over(partition by xh order by xn desc,xq desc) rn from VIEW_NEW_DC_XSZZ_KNSRDCX a) where rn = 1 and nvl(sfqxrd,'0')<>'1')";							
		sql.append("select a.*, rownum xl from "+view+" a where 1=1 ");						
		sql.append(searchTjByUser);
		sql.append(searchTj);
		
		List<HashMap<String, String>> knsrdzgmap =  dao.getListNotOut(sql.toString(),inputV);										
		StringBuffer insertKnsqxjl = new StringBuffer();		
		insertKnsqxjl.append(" insert into xg_xszz_new_knsqxjl ");
		insertKnsqxjl.append(" (jgguid,czr,czsj,qxyy) ");
		insertKnsqxjl.append(" values(?,?,?,?) ");
		
		List<String[]> params = new ArrayList<String[]>();		
		String[] param = null;
		
		for (Map<String, String> map : knsrdzgmap){
			
			String guid = map.get("guid");
			deleteKnsqxjl(guid);						
			t.setGuid(guid);
			updateOneKnsrdzg(t);
			
			param = new String[4];			
			param[0] = map.get("guid");			
			param[1] = t.getCzr();
			param[2] = t.getCzsj();
			param[3] = t.getQxyy();
			
			params.add(param);
			
		}

		try {			
			dao.runBatch(insertKnsqxjl.toString(), params);
			return true;			
		} catch (SQLException e){			
			e.printStackTrace();
			return false;			
		}
	}
	
	/**
	 * 
	 * @����:TODO(ȡ������������϶��ʸ�)
	 * @���ߣ�������[���ţ�995]
	 * @���ڣ�2016-4-27 ����10:25:06
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean cancelMoreKnsrdzg(QxknszgForm model) throws Exception{ 
		
		String[] jgguids = model.getGuids();		
		for (int i = 0, n = jgguids.length; i < n; i++) {				
			
			String guid = jgguids[i];
			model.setGuid(guid);
			deleteKnsqxjl(guid);
			updateOneKnsrdzg(model);	
		}		
		StringBuffer insertKnsqxjl = new StringBuffer();
		
		insertKnsqxjl.append(" insert into xg_xszz_new_knsqxjl ");
		insertKnsqxjl.append(" (jgguid,czr,czsj,qxyy) ");
		insertKnsqxjl.append(" values(?,?,?,?) ");
		List<String[]> params = new ArrayList<String[]>();
		
		String[] param = null;
		for (int i = 0, n = jgguids.length; i < n; i++) {
			
			param = new String[4];
			param[0] = jgguids[i];
			param[1] = model.getCzr();
			param[2] = model.getCzsj();
			param[3] = model.getQxyy();				
			params.add(param);
			
		}
		
		try {			
			dao.runBatch(insertKnsqxjl.toString(), params);
			return true;			
		} catch (SQLException e){			
			e.printStackTrace();
			return false;				
		}
		
	}
	
	public int deleteKnsqxjl(String jgGuid) throws Exception {
		String sql = " delete from xg_xszz_new_knsqxjl where jgguid = ? ";
		return dao.runDelete(sql, new String[] {jgGuid});

	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	@Override
	protected void setTableInfo() {
		// TODO �Զ����ɷ������
		super.setTableName("XG_XSZZ_NEW_KNSJGB");
		super.setKey("guid");
	}

}
