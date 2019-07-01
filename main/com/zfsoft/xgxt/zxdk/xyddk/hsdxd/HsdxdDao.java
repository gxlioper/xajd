/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-11-15 ����03:10:18 
 */  
package com.zfsoft.xgxt.zxdk.xyddk.hsdxd;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.base.util.Constants;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� yxy[����:1206]
 * @ʱ�䣺 2016-11-15 ����03:10:18 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class HsdxdDao extends SuperDAOImpl<HsdxdForm> {

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(HsdxdForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(HsdxdForm t, User user)
			throws Exception {
		// TODO �Զ����ɷ������
		StringBuffer sql = new StringBuffer();
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		String shgwzByUser = SearchService.getShgwzByUser(user, "t", "xydm", "bjdm");
		sql.append("select t.* from (");
		sql.append(" select t.id,");
		sql.append(" t.htbh,");
		sql.append(" t.xdxn,");
		sql.append(" t.xdje,");
		sql.append(" t.sqsj,");
		sql.append(" t.splc,");
		sql.append(" t1.xh,");
		sql.append(" t2.dkje,");
		sql.append(" t3.BJDM,");
		sql.append(" t3.BJMC,");
		sql.append(" t3.XYDM,");
		sql.append(" t3.XYMC,");
		sql.append(" t3.NJ,");
		sql.append(" t3.XM,");
		sql.append(" t3.ZYDM,");
		sql.append(" t3.ZYMC,");
		sql.append(" t3.XB,");
		sql.append(" nvl(t4.fkze, 0) fkze,");
		sql.append(" t5.shzt,");
		sql.append(" t5.guid shid,");
		sql.append(" t5.gwid,");
		sql.append(" t5.shr,");
		sql.append(" t5.shyj,");
		sql.append(" t7.mc || '[' ||");
		sql.append(" decode(t5.shzt,");
		sql.append(" '0',");
		sql.append(" 'δ���',");
		sql.append(" '1',");
		sql.append(" 'ͨ��',");
		sql.append(" '2',");
		sql.append(" '��ͨ��',");
		sql.append(" '3',");
		sql.append(" '�˻�',");
		sql.append(" '4',");
		sql.append(" '������',");
		sql.append(" '5',");
		sql.append(" '�����') || ']' shztmc,");
		sql.append(" t7.gwz,");
		sql.append(" row_number() over(partition by t.id order by t5.shsj desc) rn");
		sql.append(" from XG_ZXDK_XDSQB t");
		sql.append(" left join XG_HSD_ZXDK_NDKB t1");
		sql.append(" on t.id = t1.jgid");
		sql.append(" left join xg_zxdk_xydkjgb t2");
		sql.append(" on t1.id = t2.id");
		sql.append(" left join view_xsxxb t3");
		sql.append(" on t1.xh = t3.XH");
		sql.append(" left join (select sum(fkje) fkze, htbh");
		sql.append(" from xg_zxdk_hsd_xydfdb");
		sql.append(" where (fkzt = '1' or fkje is not null)");
		sql.append(" group by htbh");
		sql.append(" ) t4");
		sql.append(" on t.htbh = t4.htbh");
		sql.append(" left join xg_xtwh_shztb t5");
		sql.append(" on t.id = t5.ywid");
		sql.append(" left join xg_xtwh_spgwyh t6");
		sql.append(" on t5.gwid = t6.spgw");
		sql.append(" left join xg_xtwh_spgw t7");
		sql.append(" on t5.gwid = t7.id");
		sql.append("  where t6.spyh = '"+user.getUserName()+"'");
		String shlx = t.getShzt();
		if (!shlx.equals("dsh")) {
			sql.append(" and (t5.shzt<>0 and  t5.shzt<>4)");
		} else {
			sql.append(" and (t5.shzt=0  or t5.shzt = 4 )");
		}
		sql.append(" ) t where 1=1 ");
	    sql.append(" and  rn = 1  and xh is not null");
	    sql.append(searchTjByUser);
		sql.append(searchTj);
		//sql.append(qxfw);
		sql.append(shgwzByUser);
		sql.append(" order by sqsj desc,xymc asc,bjmc asc,xh asc");
		return getPageList(t, sql.toString(), inputV);
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		// TODO �Զ����ɷ������
		this.setClass(HsdxdForm.class);
		this.setKey("id");
		this.setTableName("XG_ZXDK_XDSQB");
	}
	
	/**
	 * 
	 * @����:��ȡ������Ϣ��ϸ�б�
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-11-16 ����04:00:51
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getXdxxMx(String xh){
		StringBuffer sql = new StringBuffer();
		String flagxn = Base.currXn.substring(0, 4);
		sql.append("  select t.*,");
		sql.append("  case");
		sql.append("  when t.shztmc not in('δ�ύ','δ����') then t.shztmc ");
		sql.append("  when t.dkzt = 'xind' then");
		sql.append("  '��ͨ��'");
		sql.append("  when t.dkzt = 'xud' and t.flagn = 'dn' and t.shztmc = 'δ����' then");
		sql.append("  'czan'");
		sql.append("  when t.dkzt = 'xud' and t.flagn = 'ln' then");
		sql.append("  'δ��ʼ'");
		sql.append("  when t.dkzt = 'qxxud' then");
	    sql.append("  '��������'");
		sql.append("  else");
		sql.append("  t.shztmc");
		sql.append("  end cznr,t.nzsfdk + t.nxfdk + t.nshfdk dkze");
		sql.append("  from (select t.*,");
		sql.append("  decode(nvl(t1.fkje,0), '0','0','1') fkzt,");
		sql.append("  decode(nvl(t1.fkje, '0'),");
		sql.append("  '0',");
		sql.append("  'δ�ſ�',");
		sql.append("  '�ѷſ�'");
		sql.append("  ) fkztmc,");
		sql.append("  t2.shzt,");
		sql.append("  decode(nvl(t2.shzt, 'wsq'),");
		sql.append("  '1',");
		sql.append("  '��ͨ��',");
		sql.append("  '2',");
		sql.append("  '��ͨ��',");
		sql.append("  '3',");
		sql.append("  '���˻�',");
		sql.append("  '5',");
		sql.append("  '�����',");
		sql.append("  'wsq',");
		sql.append("  'δ����',");
		sql.append("  '0',");
		sql.append("  'δ�ύ') shztmc,");
		sql.append("  case");
		sql.append("  when substr(t.xn, 0, 4) = ? then");
		sql.append("  'dn'");
		sql.append("  when substr(t.xn, 0, 4) > ? then");
		sql.append("  'ln'");
		sql.append("  when substr(t.xn, 0, 4) < ? then");
		sql.append("  'wn'");
		sql.append("  else");
		sql.append("  'wz'");
		sql.append("  end flagn,");
		sql.append("  t3.htbh");
		
		sql.append("  from XG_HSD_ZXDK_NDKB t");
		sql.append("  left join xg_zxdk_hsd_xydfdb t1");
		sql.append("  on t.jgid = t1.id");
		sql.append("  left join XG_ZXDK_XDSQB t2");
		sql.append("  on t.jgid = t2.id");
		sql.append("  left join xg_zxdk_xydkjgb t3");
		sql.append("  on t.id = t3.id");
		sql.append("  where t.xh = ?");
		sql.append("  and exists");
		sql.append("  (select 1 from xg_zxdk_xydkjgb x where x.id = t.id)) t");
		sql.append("  order by t.xn asc");
		
		return dao.getListNotOut(sql.toString(), new String[]{flagxn,flagxn,flagxn,xh});
	}
	
	/**
	 * 
	 * @����: ������Ϣ
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-11-16 ����04:46:37
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> getDkxxMap(String xh){
		StringBuffer sql = new StringBuffer();
		sql.append("  select sum(t.dkqx) dkqs,sum(t.dkje) dkje,sum(t.jhr1) dkqx,sum(t.zsfdks) zsfdks,sum(t.xfdks) xfdks,sum(t.shfdks) shfdks from  xg_zxdk_xydkjgb t where xh = ?");
		return dao.getMapNotOut(sql.toString(), new String[]{xh});
	}
	
	/**
	 * 
	 * @����: ������������֤
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-11-16 ����05:29:41
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getDkjgNum(String xh){
		StringBuffer sql = new StringBuffer();
		sql.append("  select count(1) num from xg_zxdk_xydkjgb where xh = ?");
		return dao.getOneRs(sql.toString(), new String[]{xh}, "num");
	}
	
	/**
	 *
	 * @����: �Ŵ�����ܺ�
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-11-16 ����05:47:40
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getFdjeZh(String xh){
		StringBuffer sql = new StringBuffer();
		sql.append(" select nvl(sum(fkje),0) fkzh from xg_zxdk_hsd_xydfdb  where xh = ?");
		return dao.getOneRs(sql.toString(), new String[]{xh}, "fkzh");
	}
	
	/**
	 * 
	 * @����: ��ȡ������������
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-11-17 ����11:42:14
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
    public List<HashMap<String, String>> getXdsqList(String xh){
    	StringBuffer sql = new StringBuffer();
    	sql.append(" select t.*,");
    	sql.append(" decode(t.shzt,");
    	sql.append(" '0',");
    	sql.append(" 'δ�ύ',");
    	sql.append(" '5',");
    	sql.append(" '�����',");
    	sql.append(" '2',");
    	sql.append(" '��ͨ��',");
    	sql.append(" '3',");
    	sql.append(" '���˻�',");
    	sql.append(" '5',");
    	sql.append(" '�����','1','��ͨ��') shztmc");
    	sql.append(" from XG_ZXDK_XDSQB t");
    	sql.append(" where t.id in (select jgid from XG_HSD_ZXDK_NDKB where xh = ?)");
    	return dao.getListNotOut(sql.toString(), new String[]{xh});
    }
    
    /**
     * @throws Exception 
     * 
     * @����:�������ݵ������
     * @���ߣ�yxy[���ţ�1206]
     * @���ڣ�2016-11-17 ����02:42:53
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @param xh
     * @return
     * boolean �������� 
     * @throws
     */
    public boolean insertInToXdb(HsdxdForm t) throws Exception{
    	StringBuffer sql = new StringBuffer();
    	sql.append("  insert into XG_ZXDK_XDSQB(id,htbh,xdxn,xdje,xdly,sqsj,splc,shzt)");
    	sql.append("  values(?,?,?,?,?,?,?,?,?)");
    	return dao.runUpdate(sql.toString(), new String[]{t.getId(),t.getHtbh(),t.getXdxn(),t.getXdje(),t.getXdly(),t.getSqsj(),t.getSplc(),t.getShzt()});
    }
    
    /**
     * @throws Exception 
     * 
     * @����: ��������
     * @���ߣ�yxy[���ţ�1206]
     * @���ڣ�2016-11-18 ����09:15:54
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @return
     * boolean �������� 
     * @throws
     */
    public boolean qxsq(String jgid) throws Exception{
    	StringBuffer sql = new StringBuffer();
    	sql.append(" update XG_HSD_ZXDK_NDKB set dkzt = ? where jgid = ?");
    	return dao.runUpdate(sql.toString(), new String[]{"qxxud",jgid});
    }
    
    /**
     * 
     * @����:��ȡ������Ϣ
     * @���ߣ�yxy[���ţ�1206]
     * @���ڣ�2016-11-18 ����04:36:14
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @param id
     * @return
     * HashMap<String,String> �������� 
     * @throws
     */
    public HashMap<String, String> getViewCk(String id){
  	  StringBuilder sql = new StringBuilder();
  	  sql.append(" select t.id,");
  	  sql.append(" t.htbh,");
  	  sql.append(" t.xdxn,");
  	  sql.append(" t.xdje,");
  	  sql.append(" t.xdly,");
  	  sql.append(" t.sqsj,");
  	  sql.append(" t.splc,");
  	  sql.append(" t.shzt,");
  	  sql.append(" nvl(t4.fkze,0) fkze,");
  	  sql.append(" nvl(t4.dkje,0) dkje,");
  	  sql.append(" t4.dkqx");
  	  sql.append(" from XG_ZXDK_XDSQB t");
  	  sql.append(" left join (select t1.*, nvl(t3.fkze, 0) fkze");
  	  sql.append(" from xg_zxdk_xydkjgb t1");
  	  sql.append(" left join (select htbh, sum(fkje) fkze");
  	  sql.append(" from xg_zxdk_hsd_xydfdb");
  	  sql.append(" group by htbh) t3");
  	  sql.append(" on t1.htbh = t3.htbh) t4");
  	  sql.append(" on t.htbh = t4.htbh");
  	  sql.append("  where t.id = ?");
  	  return dao.getMapNotOut(sql.toString(), new String[]{id});
  		
  	}
    
    /**
     * @throws Exception 
     * 
     * @����: �������ͨ������Ŵ���
     * @���ߣ�yxy[���ţ�1206]
     * @���ڣ�2016-11-18 ����05:32:49
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @param id
     * @return
     * boolean �������� 
     * @throws
     */
    public boolean insertIntoFdb(String id) throws Exception{
    	StringBuffer sql = new StringBuffer();
    	String htbh = new HsdxdDao().getModel(id).getHtbh();
    	sql.append(" insert into xg_zxdk_hsd_xydfdb(id,xh,xn,xq,htbh)");
		sql.append(" select jgid id,xh,xn,?,'"+htbh+"' from XG_HSD_ZXDK_NDKB t where jgid = ?");
    	return dao.runUpdate(sql.toString(), new String[]{Base.currXq,id});
    }
    
    /**
     * @throws Exception 
     * 
     * @����:������˳���֮��ɾ���Ŵ�������
     * @���ߣ�yxy[���ţ�1206]
     * @���ڣ�2016-11-18 ����05:40:18
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @return
     * boolean �������� 
     * @throws
     */
    public boolean delFdb(String id) throws Exception{
    	StringBuffer sql = new StringBuffer();
    	sql.append(" delete from xg_zxdk_hsd_xydfdb where id = ?");
    	return dao.runUpdate(sql.toString(), new String[]{id});
    }
    
    /**
     * 
     * @����: �Ŵ������ѯ
     * @���ߣ�yxy[���ţ�1206]
     * @���ڣ�2016-11-21 ����09:02:19
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @param user
     * @param t
     * @return
     * @throws Exception
     * List<HashMap<String,String>> �������� 
     * @throws
     */
    public List<HashMap<String, String>> getFdcxList(User user,HsdxdForm t) throws Exception{
    	StringBuffer sql = new StringBuffer();
    	String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		sql.append(" select t.* from (");
    	sql.append(" select t.id,");
    	sql.append(" t.xh,");
    	sql.append(" t.xn,");
    	sql.append(" t.xq,");
    	sql.append(" t.fkje,");
    	sql.append(" t.fksj,");
    	sql.append(" t.fkpzh,");
    	sql.append(" t.dkzh,");
    	sql.append(" t.khh,");
    	sql.append(" t.htbh,");
    	sql.append(" decode(nvl(t.fkje,0),'0','0','1') fkzt,");
    	sql.append(" t1.xm,");
    	sql.append(" t1.bjdm,");
    	sql.append(" t1.bjmc,");
    	sql.append(" t1.zydm,");
    	sql.append(" t1.zymc,");
    	sql.append(" t1.xydm,");
    	sql.append(" t1.xymc,");
    	sql.append(" t1.nj,");
    	sql.append(" t1.xb,");
    	sql.append(" t2.xqmc");
    	sql.append(" from XG_ZXDK_HSD_XYDFDB t");
    	sql.append(" left join view_xsjbxx t1");
    	sql.append(" on t.xh = t1.xh");
    	sql.append(" left join xqdzb t2");
    	sql.append(" on t.xq = t2.xqdm");
    	sql.append(" ) t where 1=1 ");
    	sql.append(searchTjByUser);
   		sql.append(searchTj);
   		//sql.append(qxfw);
   		sql.append(" order by fksj desc,xymc asc,bjmc asc,xh asc");
   		return getPageList(t, sql.toString(), inputV);
    } 
    
    /**
     * @throws NoSuchMethodException 
     * @throws InvocationTargetException 
     * @throws IllegalAccessException 
     * @throws SecurityException 
     * @throws IllegalArgumentException 
     * 
     * @����: ���·Ŵ���
     * @���ߣ�yxy[���ţ�1206]
     * @���ڣ�2016-11-21 ����09:30:49
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @param t
     * @return
     * boolean �������� 
     * @throws
     */
    @SuppressWarnings("unchecked")
    public boolean updateFdb(HsdxdForm form) throws Exception{
    	StringBuffer sql = new StringBuffer();
    	ArrayList<String> paralist = new ArrayList<String>();
    	sql.append(" update XG_ZXDK_HSD_XYDFDB set ");
    	if(xgxt.utils.String.StringUtils.isNull(form.getId())){
    		return false;
    	}
    	Class t = form.getClass();
        List<HashMap<String, String>> colList = this.getFdbCol();
    	for (int i = 0;i < colList.size();i++) {
    		String fieldName = colList.get(i).get("col");
			if ("serialVersionUID".equals(fieldName)){
				continue;
			}
			String getMethodName = new StringBuffer("get").append(fieldName.substring(0, 1).toUpperCase()).append(fieldName.substring(1)).toString();
			Object value = t.getMethod(getMethodName).invoke(form);
			if (value instanceof String){
				String newValue = (String)value;
				if(xgxt.utils.String.StringUtils.isNotNull(newValue)){
					sql.append(fieldName + " = ? ");
					paralist.add(newValue);
					if(i != colList.size()-1){
						sql.append(",");
					}
				}
			}
		}
    	sql.append(" where id = ?");
    	paralist.add(form.getId());
    	return dao.runUpdate(sql.toString(), paralist.toArray(new String[]{}));
    	
    }
    
    /**
     * 
     * @����: ��ȡ�Ŵ����ֶ�
     * @���ߣ�yxy[���ţ�1206]
     * @���ڣ�2016-11-21 ����10:01:26
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @return
     * List<HashMap<String,String>> �������� 
     * @throws
     */
    public List<HashMap<String, String>> getFdbCol(){
    	StringBuffer sql = new StringBuffer();
    	sql.append(" select lower(column_name) col from user_tab_columns t where t.TABLE_NAME = ?");
    	return dao.getListNotOut(sql.toString(), new String[]{"XG_ZXDK_HSD_XYDFDB"});
    }
    
    /**
     * @throws NoSuchMethodException 
     * @throws InvocationTargetException 
     * @throws IllegalAccessException 
     * @throws SecurityException 
     * @throws IllegalArgumentException 
     * 
     * @����: ��ȡ�Ŵ���ʵ��model
     * @���ߣ�yxy[���ţ�1206]
     * @���ڣ�2016-11-21 ����10:32:15
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @return
     * HsdxdForm �������� 
     * @throws
     */
    @SuppressWarnings("unchecked")
    public HsdxdForm getFdbModel(String id) throws Exception{
        StringBuffer sql = new StringBuffer();
        sql.append("  select * from XG_ZXDK_HSD_XYDFDB where id = ?");
        HashMap<String, String> dataMap = dao.getMapNotOut(sql.toString(), new String[]{id});
        List<HashMap<String, String>> colList = this.getFdbCol();
        HsdxdForm hsdxdForm = new HsdxdForm();
        Class t = hsdxdForm.getClass();
        for (int i = 0; i < colList.size(); i++) {
        	String fieldName = colList.get(i).get("col");
        	String value = dataMap.get(fieldName);
			String setMethodName = new StringBuffer("set").append(fieldName.substring(0, 1).toUpperCase()).append(fieldName.substring(1)).toString();
			t.getMethod(setMethodName,String.class).invoke(hsdxdForm, value);
		}
        return hsdxdForm;
        	
        
    }
    
    /**
     * 
     * @����: ���ݺ�ͬ��Ż�ȡ�����˺�
     * @���ߣ�yxy[���ţ�1206]
     * @���ڣ�2016-11-22 ����05:15:34
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @return
     * String �������� 
     * @throws
     */
    public String getDkzh(String htbh){
    	StringBuffer sql = new StringBuffer();
    	sql.append(" select distinct dkzh");
    	sql.append(" from xg_zxdk_hsd_xydfdb");
    	sql.append(" where htbh in (select htbh");
    	sql.append(" from (select count(1) num, htbh");
    	sql.append(" from xg_zxdk_hsd_xydfdb");
    	sql.append(" group by htbh)");
    	sql.append(" where num > 1)");
    	sql.append(" and dkzh is not null and htbh = ?");
    	return dao.getOneRs(sql.toString(), new String[]{htbh}, "dkzh");
    }
    
    /**
     * 
     * @����:��ʦ����Ի��жϣ�������зŴ���¼����ѧ�������벻����ɾ������˲�������
     * @���ߣ�yxy[���ţ�1206]
     * @���ڣ�2016-11-23 ����09:26:09
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @param htbh
     * @return
     * String �������� 
     * @throws
     */
    public String getfdNum(String htbh){
    	StringBuffer sql = new StringBuffer();
    	sql.append(" select count(1) num from xg_zxdk_hsd_xydfdb where htbh = ? and fkje is not null");
    	return dao.getOneRs(sql.toString(), new String[]{htbh}, "num");
    }
    
    /**
     * 
     * @����:��ʦ����Ի��жϣ�����ʱ��������ɾ��ʱ�жϣ��Ƿ��зŴ���¼
     * @���ߣ�yxy[���ţ�1206]
     * @���ڣ�2016-11-23 ����09:26:09
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @param htbh
     * @return
     * String �������� 
     * @throws
     */
    public String getfdNumXd(String[] ids){
    	StringBuffer sql = new StringBuffer();
    	sql.append(" select count(1) num from xg_zxdk_hsd_xydfdb where id in ( ");
    	for (int i = 0; i < ids.length; i++) {
			sql.append("?");
			if(i != ids.length-1){
				sql.append(",");
			}
		}
    	sql.append(") and fkje is not null");
    	return dao.getOneRs(sql.toString(), ids, "num");
    }
    
    /**
     * 
     * @����: ��ȡ�Ŵ����
     * @���ߣ�yxy[���ţ�1206]
     * @���ڣ�2016-11-23 ����10:56:35
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @param jgid
     * @return
     * String �������� 
     * @throws
     */
    public String getFdjes(String jgid){
    	StringBuffer sql = new StringBuffer();
    	sql.append(" select nzsfdk+nxfdk+nshfdk  fdje from XG_HSD_ZXDK_NDKB where jgid = ?");
    	return dao.getOneRs(sql.toString(), new String[]{jgid}, "fdje");
    }
    
}
