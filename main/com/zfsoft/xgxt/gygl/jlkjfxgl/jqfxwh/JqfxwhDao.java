package com.zfsoft.xgxt.gygl.jlkjfxgl.jqfxwh;


import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

public class JqfxwhDao extends  SuperDAOImpl<JqfxwhForm> {
	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(JqfxwhForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */	
	@Override
	public List<HashMap<String, String>> getPageList(JqfxwhForm t, User user)
			throws Exception {

		
		//���ɸ߼���ѯ�������������ֵ 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());		
		StringBuffer sql = new StringBuffer();	
		//��������ְҵ����ѧԺ ��λ�Ŵ������ģ����Ի��޸�
		String sb = "";
		if("12898".equals(Base.xxdm)){
			sb = " to_number(a.ch),a.qsh,a.cwh ";
		}else{
			sb = " to_number(a.ch),a.qsh,to_number(a.cwh) ";
		}
		sql.append(" select rownum r,a.*,(select xymc from zxbz_xxbmdm where bmdm=a.xydm) cwxymc,(select bjmc from "
				+ " bks_bjdm where bjdm=a.bjdm) cwbjmc from (select lddm||qsh||cwh pkValue,a.*,b.xm,"
				+ " b.zymc xszymc,b.zydm xszydm, b.bjmc xsbjmc,b.bjdm xsbjdm,b.xz,b.pycc,b.sjhm," 
				+ " c.id,c.xn,c.xq," + "  decode(c.fxzt,NULL , '-1' , '0' , '0' , '1' , '1')fxzt, "
				+ " decode(c.fxzt,'','δ����','0','δ��У','1','�ѷ�У',c.fxzt)fxztmc,c.fxsj,c.wfxyy,d.fxmc,");
		if ("13011".equals(Base.xxdm)){
			sql.append("c.tbsj,");
		}
		sql.append(" (case when a.xydm is not null or a.nj is not null or a.xh is not null then 'disabled' else '' end) dis," 
				+ " (case when length(a.bz)>5 then substr(a.bz,1,5)||'...' else a.bz end)subbz "
				+ " from view_xg_gygl_new_cwxx a left join view_xsbfxx b on a.xh=b.xh left join xg_gygl_new_fxgl_jgb c " 
				+ " on a.xh = c.xh left join xg_gygl_new_fxgl_dmwhb d on c.fxdm = d.fxdm order by "  
				+ " a.lddm,"+sb+") a where  a.sfrz='��' and 1=1 ");
		
		String searchTjQx = "";
		//�жϹ�Ԣ����ԱȨ��
		if( "yes".equalsIgnoreCase(user.getGyglyQx())){
			 searchTjQx =" and lddm in (select lddm from xg_gygl_new_gyfdyb where yhm = '"+user.getUserName()+"')";
		}	
		sql.append(searchTjByUser);
		sql.append(searchTj+searchTjQx);
		return getPageList(t, sql.toString(), inputV); 
		
		
	}
	
	
	/**
	 * 
	 * @����:TODO(��ȡ��У����)
	 * @���ߣ�������[���ţ�995]
	 * @���ڣ�2016-3-1 ����09:40:48
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getFxmc()throws Exception {
		String sql = " select fxmc "
			+"from xg_gygl_new_fxgl_csszb a left join  xg_gygl_new_fxgl_dmwhb b on a.fxdm = b.fxdm ";
		return dao.getOneRs(sql, new String[]{}, "fxmc");
	}
	
	/**
	 * 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�������[���ţ�995]
	 * @���ڣ�2016-3-2 ����11:01:51
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xwjgId
	 * @return
	 * Map<String,String> �������� 
	 * @throws
	 */
	public Map<String, String> getOneFxgljgList(String  xh) {		
			StringBuffer sql = new StringBuffer();
			//��������ְҵ����ѧԺ ��λ�Ŵ������ģ����Ի��޸�
			String sb = "";
			if("12898".equals(Base.xxdm)){
				sb = "a.cwh";
			}else{
				sb = "to_number(a.cwh)";
			}
			sql.append("select a.*,(select xymc from zxbz_xxbmdm where bmdm=a.xydm) cwxymc,(select bjmc from bks_bjdm where bjdm=a.bjdm) cwbjmc from" 
					+ " (select lddm||qsh||cwh pkValue,a.*,b.xm,b.zymc xszymc,b.zydm xszydm, b.bjmc xsbjmc,"
					+ "b.bjdm xsbjdm,b.xz,b.pycc,b.sjhm,b.sfzh, b.lxdh, c.id, c.xn,c.xq,c.fxdm,"
					+ "(case when a.xydm is not null or a.nj is not null or a.xh is not null then 'disabled' else '' end) dis," 
					+ "(case when length(a.bz)>5 then substr(a.bz,1,5)||'...' else a.bz end)subbz "
					+ "from view_xg_gygl_new_cwxx a left join view_xsbfxx b on a.xh=b.xh left join "
					+ "xg_gygl_new_fxgl_jgb c on a.xh = c.xh order by a.lddm,to_number(a.ch)," 
					+ "a.qsh,"+sb+") a where 1=1 and  a.xh = ? ");	
			return dao.getMapNotOut(sql.toString(),new String[]{xh});		
	}
	
	/**
	 * 
	 * @����:TODO(��ȡδ��У��Ϣ��¼)
	 * @���ߣ�������[���ţ�995]
	 * @���ڣ�2016-3-2 ����02:58:42
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * Map<String,String> �������� 
	 * @throws
	 */
	public Map<String, String> getOneWfxxwhjgList(String  xh) {
		StringBuffer sql = new StringBuffer();	
	    sql.append("select xh,xn,xq,decode(fxzt,'','δ����','0','δ��У','1','�ѷ�У',fxzt) fxztmc," 
	    		  +"fxsj,fxdm,wfxyy from xg_gygl_new_fxgl_jgb  where xh = ?");
		return dao.getMapNotOut(sql.toString(),new String[]{xh});
	}
			
	/**
	 * 
	 * @����:TODO(������ȡ���ݿ������δ��У��¼)
	 * @���ߣ�������[���ţ�995]
	 * @���ڣ�2016-3-7 ����10:26:43
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * @throws Exception
	 * String �������� 
	 * @throws
	 */
	public String getCountNum(JqfxwhForm t,User user)throws Exception {
		//wewew
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());	
		StringBuffer sql = new StringBuffer();			
		//��������ְҵ����ѧԺ ��λ�Ŵ������ģ����Ի��޸�
		String sb = "";
		if("12898".equals(Base.xxdm)){
			sb = "a.cwh";
		}else{
			sb = "to_number(a.cwh)";
		}
		sql.append( " select count(*) countNum from (select a.xh,a.sfrz,a.lddm," 
		+ " a.bjdm,a.nj,a.qsh,a.xydm,a.zydm,a.cwh,c.fxsj,b.xm,c.fxzt "         
		+  " from view_xg_gygl_new_cwxx a left join view_xsbfxx b on a.xh = b.xh "
		+  " left join xg_gygl_new_fxgl_jgb c on a.xh = c.xh "
		+  " order by a.lddm, to_number(a.ch), a.qsh, "+sb+") a "
		+  " where a.sfrz = '��'  and a.xh not in (select xh  from xg_gygl_new_fxgl_jgb where fxzt = '1') " );
		
		String searchTjQx = "";
		//�жϹ�Ԣ����ԱȨ��
		if( "yes".equalsIgnoreCase(user.getGyglyQx())){
			 searchTjQx =" and lddm in (select lddm from xg_gygl_new_gyfdyb where yhm = '"+user.getUserName()+"')";
		}	
		sql.append(searchTjByUser);
		sql.append(searchTj+searchTjQx);
		return dao.getOneRs(sql.toString(), inputV, "countNum");		
	}
	

 /**
 * @throws NoSuchMethodException 
 * @throws InvocationTargetException 
 * @throws IllegalAccessException 
 * @throws SecurityException 
 * @throws IllegalArgumentException 
  * 
  * @����:TODO(����������ڷ�У)
  * @���ߣ�������[���ţ�995]
  * @���ڣ�2016-3-14 ����12:26:58
  * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
  * @param model
  * @return
  * boolean �������� 
  * @throws
  */
  public boolean plSaveJqwh(JqfxwhForm t,User user) throws Exception {
	  					
			String searchTj = SearchService.getSearchTj(t.getSearchModel());
			String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");		
			String[] inputV = SearchService.getTjInput(t.getSearchModel());		
			StringBuffer sql = new StringBuffer();	
			//��������ְҵ����ѧԺ ��λ�Ŵ������ģ����Ի��޸�
			String sb = "";
			if("12898".equals(Base.xxdm)){
				sb = "a.cwh";
			}else{
				sb = "to_number(a.cwh)";
			}
			sql.append(" select xh,(select xymc from zxbz_xxbmdm where bmdm=a.xydm) cwxymc,(select bjmc from "
					+ " bks_bjdm where bjdm=a.bjdm) cwbjmc from (select lddm||qsh||cwh pkValue,a.*,b.xm,"
					+ " b.zymc xszymc,b.zydm xszydm, b.bjmc xsbjmc,b.bjdm xsbjdm,b.xz,b.pycc,b.sjhm," 
					+ " c.id,c.xn,c.xq," + "  decode(c.fxzt,NULL , '-1' , '0' , '0' , '1' , '1')fxzt, " +
					" decode(c.fxzt,'','δ����','0','δ��У','1','�ѷ�У',c.fxzt)fxztmc,c.fxsj,c.wfxyy,d.fxmc,"
					+ " (case when a.xydm is not null or a.nj is not null or a.xh is not null then 'disabled' else '' end) dis," 
					+ " (case when length(a.bz)>5 then substr(a.bz,1,5)||'...' else a.bz end)subbz "
					+ " from view_xg_gygl_new_cwxx a left join view_xsbfxx b on a.xh=b.xh left join xg_gygl_new_fxgl_jgb c " 
					+ " on a.xh = c.xh left join xg_gygl_new_fxgl_dmwhb d on c.fxdm = d.fxdm order by "  
					+ " a.lddm,to_number(a.ch),a.qsh,"+sb+") a where  a.sfrz='��' and 1=1 ");
			
			String searchTjQx = "";
			//�жϹ�Ԣ����ԱȨ��
			if( "yes".equalsIgnoreCase(user.getGyglyQx())){
				 searchTjQx =" and lddm in (select lddm from xg_gygl_new_gyfdyb where yhm = '"+user.getUserName()+"')";
			}	
			sql.append(searchTjByUser);
			sql.append(searchTj+searchTjQx);
						
			//////////////////////////////////////////////////////////////////////////////////////////////
			
			List<HashMap<String, String>> xsjqfxxhmap =  dao.getListNotOut(sql.toString(),inputV);				
						
			StringBuffer insertJgbSql = new StringBuffer();
			insertJgbSql.append("insert into xg_gygl_new_fxgl_jgb ");
			if("13011".equals(Base.xxdm)){
				insertJgbSql.append("(xh,xn,xq,fxzt,fxsj,fxdm,wfxyy,tbsj)");
				insertJgbSql.append(" values(?,?,?,?,?,?,?,?)");
			}else{
				insertJgbSql.append("(xh,xn,xq,fxzt,fxsj,fxdm,wfxyy)");
				insertJgbSql.append(" values(?,?,?,?,?,?,?)");
			}
			
			
			List<String[]> params = new ArrayList<String[]>();
			String[] param = null;
			if("13011".equals(Base.xxdm)){
				for (Map<String, String> map : xsjqfxxhmap) {
					deleteFxgljgb(map.get("xh"));
					param = new String[8];
					param[0] = map.get("xh");
					param[1] = t.getXn();
					param[2] = t.getXq();
					param[3] = t.getFxzt();
					param[4] = t.getFxsj();
					param[5] = t.getFxdm();	
					param[6] = "";//δ��Уԭ�����ÿյ�ҵ����ȷ��	
					param[7] = t.getTbsj();
					params.add(param);
					
				}
			}else{
				for (Map<String, String> map : xsjqfxxhmap) {
					deleteFxgljgb(map.get("xh"));
					param = new String[7];
					param[0] = map.get("xh");
					param[1] = t.getXn();
					param[2] = t.getXq();
					param[3] = t.getFxzt();
					param[4] = t.getFxsj();
					param[5] = t.getFxdm();	
					param[6] = "";//δ��Уԭ�����ÿյ�ҵ����ȷ��	
					params.add(param);
					
				}
			}
			try {
				dao.runBatch(insertJgbSql.toString(), params);
				return true;		
			} catch (SQLException e){
				e.printStackTrace();
				return false;		
			}
	}
  
  /**
   * 
   * @����:TODO(����������ڷ�У)
   * @���ߣ�������[���ţ�995]
   * @���ڣ�2016-4-19 ����11:21:47
   * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
   * @param model
   * @param user
   * @return
   * @throws Exception
   * boolean �������� 
   * @throws
   */
  public boolean pldgSaveJqwh(JqfxwhForm model,User user) throws Exception{
	  
	  	String[] xhs = model.getXhs();
		for (int i = 0, n = xhs.length; i < n; i++) {				
			deleteFxgljgb(xhs[i]);
		}
	  
		StringBuffer insertJgbSql = new StringBuffer();
		insertJgbSql.append(" insert into xg_gygl_new_fxgl_jgb ");
		insertJgbSql.append(" (xh,xn,xq,fxzt,fxsj,fxdm,wfxyy) ");
		insertJgbSql.append(" values(?,?,?,?,?,?,?) ");
		List<String[]> params = new ArrayList<String[]>();
		//String[] xhs = model.getXhs();
		String[] param = null;
		for (int i = 0, n = xhs.length; i < n; i++) {
			
			param = new String[7];
			param[0] = xhs[i];
			param[1] = model.getXn();
			param[2] = model.getXq();
			param[3] = model.getFxzt();
			param[4] = model.getFxsj();
			param[5] = model.getFxdm();	
			param[6] = "";//δ��Уԭ�����ÿյ�ҵ����ȷ��	
			params.add(param);
			
		}
		try {
			dao.runBatch(insertJgbSql.toString(), params);
			return true;		
		} catch (SQLException e){
			e.printStackTrace();
			return false;		
		}
}

  /**
   * 
   * @����:TODO(��ȡ�������)
   * @���ߣ�������[���ţ�995]
   * @���ڣ�2016-3-14 ����06:30:24
   * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
   * @param user
   * @return
   * List<HashMap<String,String>> �������� 
   * @throws
   */
   public List<HashMap<String, String>> getCyyyList(User user) {		
		String sql = " select * from xg_gygl_new_fxgl_wfxcyyyb where zgh=? ";		
		return dao.getListNotOut(sql, new String[]{user.getUserName()});
	}
   	
	
	/**
	 * 
	 * @����:TODO(�����û�ɾ��δ��У����ԭ��)
	 * @���ߣ�������[���ţ�995]
	 * @���ڣ�2016-3-15 ����11:29:59
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param user
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean delCyyy(User user) throws Exception{
		
		String sql = " delete from xg_gygl_new_fxgl_wfxcyyyb where zgh=? ";
		return dao.runUpdate(sql, new String[]{user.getUserName()});
	}
	
	
	/**
	 * 
	 * @����:TODO(�����û�����δ��У����ԭ��)
	 * @���ߣ�������[���ţ�995]
	 * @���ڣ�2016-3-15 ����11:30:59
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param user
	 * @param cyyy
	 * @return
	 * @throws SQLException
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveCyyy(User user,String[]cyyy) throws SQLException{		
		List<String[]> params = new ArrayList<String[]>();
		String[] param = null;		
		for (String str : cyyy){			
			if (StringUtil.isNull(str))
				continue;			
			param = new String[]{user.getUserName(),str};
			params.add(param);
		}		
		String sql = "insert into xg_gygl_new_fxgl_wfxcyyyb(id,zgh,cyyy) values (sys_guid(),?,?)";		
		int[] result = dao.runBatch(sql, params);
		return dao.checkBatchResult(result);
	}
	
	/**
	 * 
	 * @����:TODO(ɾ�����ڷ�У������е�����)
	 * @���ߣ�������[���ţ�995]
	 * @���ڣ�2016-4-18 ����06:17:42
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xhs
	 * @return
	 * @throws Exception
	 * int �������� 
	 * @throws
	 */
	public int deleteFxgljgb(String xhs) throws Exception {
		String sql = " delete from xg_gygl_new_fxgl_jgb where xh = ? ";
		return dao.runDelete(sql, new String[] {xhs});

	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����:TODO(����������ѧ������δ��Уά��)
	 * @���ߣ�������[���ţ�995]
	 * @���ڣ�2016-4-18 ����06:18:37
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean pldgSaveJqwfx(JqfxwhForm model) throws Exception{ 			
			String[] xhs = model.getXhs();
			for (int i = 0, n = xhs.length; i < n; i++) {				
				deleteFxgljgb(xhs[i]);
			}		
			StringBuffer insertJgbSql = new StringBuffer();
			insertJgbSql.append(" insert into xg_gygl_new_fxgl_jgb ");
			insertJgbSql.append(" (xh,xn,xq,fxzt,fxsj,fxdm,wfxyy) ");
			insertJgbSql.append(" values(?,?,?,?,?,?,?) ");
			List<String[]> params = new ArrayList<String[]>();
			//String[] xhs = model.getXhs();
			String[] param = null;
			for (int i = 0, n = xhs.length; i < n; i++) {					
				param = new String[7];
				param[0] = xhs[i];
				param[1] = model.getXn();
				param[2] = model.getXq();
				param[3] = model.getFxzt();
				param[4] = model.getFxsj();
				param[5] = model.getFxdm();	
				param[6] = model.getWfxyy();	
				params.add(param);				
			}
			try {
				dao.runBatch(insertJgbSql.toString(), params);
				return true;		
			} catch (SQLException e){
				e.printStackTrace();
				return false;		
			}
	}
	
	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	@Override
	protected void setTableInfo() {		
		// TODO �Զ����ɷ������
		super.setClass(JqfxwhForm.class);
		super.setTableName("xg_gygl_new_fxgl_jgb");
		super.setKey("id");
		
		
	}

}
