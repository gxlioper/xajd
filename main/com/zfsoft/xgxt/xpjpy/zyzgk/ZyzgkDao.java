/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-11-18 ����08:41:29 
 */  
package com.zfsoft.xgxt.xpjpy.zyzgk;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����[����:1282]
 * @ʱ�䣺 2016-11-18 ����08:41:29 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ZyzgkDao extends SuperDAOImpl<ZyzgkModel>{

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(ZyzgkModel t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(ZyzgkModel t, User user)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		this.setKey("id");
		this.setTableName("xg_pjpy_new_zyzgkb");
		this.setClass(ZyzgkModel.class);		
	}
	
	/** 
	 * @����:�������ɿγ�(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-11-18 ����09:38:13
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws 
	 */
	public boolean insertZgk(ZyzgkModel t) throws Exception {
		String[] zgks = t.getZgks();
		String pjxn = t.getPjxn();
		StringBuilder sql = new StringBuilder();
		sql.append(" insert into xg_pjpy_new_zyzgkb(pjxn,zydm,kcmc) ");
		if(zgks.length==1){
			sql.append(" values (");
			String[] values = zgks[0].split("_");
			sql.append(" '"+pjxn+"'");
			for(int i = 0;i<values.length;i++){
				sql.append(" ,'"+values[i]+"'");
			}
			sql.append(" )");
		}else{
			for(int i = 0;i<zgks.length;i++){
				if(i>0){					
					sql.append(" union all ");
				}
				String[] values = zgks[i].split("_");
				sql.append(" select '"+pjxn+"'");
				for(int j = 0;j<values.length;j++){
					sql.append(" ,'"+values[j]+"'");
				}
				sql.append("from dual");
			}
		}
		return dao.runUpdate(sql.toString(), new String[]{});		
	}
	
	/** 
	 * @����:��ѧ���ȡרҵ���ɿ�����(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-11-18 ����09:50:25
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String,String>> getZyzgkszList(ZyzgkModel t){
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from xg_pjpy_new_zyzgkb where pjxn = ?");
		return dao.getListNotOut(sql.toString(), new String[]{t.getPjxn()});
	}
	
	/** 
	 * @����:����ѧ�갴רҵ��ȡ���ɿγ��б�(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-11-18 ����10:27:38
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String,String>> getzgkListByXnZy(ZyzgkModel t){
		StringBuilder sql = new StringBuilder();
		sql.append(" select distinct a.zydm,a.zymc,b.kcmc from (select a.zydm,a.zymc from bks_zydm a where exists (select 1 from view_xsxxb b where a.zydm = b.zydm))a ");
		sql.append(" ,(select distinct b.zydm,a.kcmc from cjb a left join view_xsxxb b on a.xh = b.xh where a.xn = ? and b.zydm is not null) b where a.zydm = b.zydm ");
		sql.append(" group by a.zymc,a.zydm,b.kcmc order by zymc,zydm,kcmc asc ");
		
		return dao.getListNotOut(sql.toString(), new String[]{t.getPjxn()});
 
	}
	
	/** 
	 * @����:����ѧ���ȡ���������ɿ�(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-11-18 ����05:27:46
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xn
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String,String>> getYszZgkModel(String xn){
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.zydm,b.zymc,a.kcmc from xg_pjpy_new_zyzgkb a left join bks_zydm b on a.zydm = b.zydm where a.pjxn = ? group by b.zymc,a.zydm,a.kcmc order by b.zymc,a.kcmc asc");
		return dao.getListNotOut(sql.toString(), new String[]{xn});
	}
	
	/**
	 * @throws Exception  
	 * @����:����ѧ��ɾ��רҵ���ɿ�����(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-11-18 ����05:47:26
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xn
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean deleteZyzgk(String xn,String xydm,String zydm) throws Exception{
		StringBuilder sql = new StringBuilder();
		List<String> strs=new ArrayList<String>();
		sql.append(" delete from xg_pjpy_new_zyzgkb where pjxn = ?");
		strs.add(xn);
		if(!StringUtils.isNull(xydm)){
			sql.append(" and zydm in (select zydm from view_njxyzybj_all where xydm= ?) ");
			strs.add(xydm);
		}
		if(!StringUtils.isNull(zydm)&&!"null".equals(zydm)){
			sql.append(" and zydm=? ");
			strs.add(zydm);
		}
		return dao.runUpdate(sql.toString(), strs.toArray(new String[]{}));
	}
	
	public List<HashMap<String,String>> getzgkList(String xn,String xydm,String zydm){
		StringBuilder sql = new StringBuilder();
		List<String> strs=new ArrayList<String>();
		sql.append(" select * from (select t1.* ,t2.xydm,t2.xymc from ( ");
		sql.append(" select distinct a.zydm,a.zymc,b.kcmc from (select a.zydm,a.zymc from bks_zydm a where exists (select 1 from view_xsxxb b where a.zydm = b.zydm))a ");
		sql.append(" ,(select distinct b.zydm,a.kcmc from cjb a left join view_xsxxb b on a.xh = b.xh where a.xn = ? and b.zydm is not null) b where a.zydm = b.zydm ");
		sql.append(" group by a.zymc,a.zydm,b.kcmc order by zymc,zydm,kcmc asc ");
		sql.append(" )t1 left join (select distinct zydm,xydm,xymc from view_njxyzybj_all)t2 on t1.zydm=t2.zydm ) where 1=1 ");
		strs.add(xn);
		if(!StringUtils.isNull(xydm)){
			sql.append(" and xydm=? ");
			strs.add(xydm);
		}
		if(!StringUtils.isNull(zydm)&&!"null".equals(zydm)){
			sql.append(" and zydm=? ");
			strs.add(zydm);
		}
		
		return dao.getListNotOut(sql.toString(), strs.toArray(new String[]{}));
 
	}
	
	public List<HashMap<String,String>> getXyList(){
		String sql="select distinct xydm,xymc from view_njxyzybj_all ";
		return dao.getListNotOut(sql.toString(), new String[]{});
	}
	
	public List<HashMap<String,String>> getZyList(String xydm){
		String sql="select distinct zydm,zymc from view_njxyzybj_all where xydm=? ";
		return dao.getListNotOut(sql.toString(), new String[]{xydm});
	}
	
}
