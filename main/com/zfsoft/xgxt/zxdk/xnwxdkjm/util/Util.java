/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-2-19 ����09:56:32 
 */  
package com.zfsoft.xgxt.zxdk.xnwxdkjm.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.action.Base;
import xgxt.form.User;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� yxy[����:1206]
 * @ʱ�䣺 2016-2-19 ����09:56:32 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class Util extends SuperDAOImpl {

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List getPageList(Object t) throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List getPageList(Object t, User user) throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		// TODO �Զ����ɷ������
		
	}
	
	/**
	 * 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-2-19 ����09:58:00
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean isNotExists(UtilForm utilform){
		StringBuilder sql = new StringBuilder();
		String type = utilform.getType();
		ArrayList<String> parameter = new ArrayList<String>();
		boolean flag = true;
		//���������ͽ������ظ���֤,qb(ȫ��)
		if(type.equalsIgnoreCase("qb")){
			sql.append(" select sum(flag) flag from");
			sql.append(" (select count(1) flag from ");
			sql.append(" xg_zdgxh_wxjkjm_sqb");
			sql.append(" where xh = ?");
			sql.append(" union all");
			sql.append(" select count(1) flag from ");
			sql.append(" xg_zdgxh_wxjkjm_jgb");
			sql.append(" where xh = ?)");
			parameter.add(utilform.getXh());
			parameter.add(utilform.getXh());
		}else if(type.equalsIgnoreCase("sq")){
			sql.append(" select count(1) flag from ");
			sql.append(" xg_zdgxh_wxjkjm_sqb");
			sql.append(" where xh = ?");
			parameter.add(utilform.getXh());
		}else if(type.equalsIgnoreCase("jg")){
			sql.append(" select count(1) flag from ");
			sql.append(" xg_zdgxh_wxjkjm_jgb");
			sql.append(" where xh = ?");
			parameter.add(utilform.getXh());
		}
		String num = dao.getOneRs(sql.toString(), parameter.toArray(new String[]{}),"flag");
		if (!num.equals("0")){
			flag = false;
		}
		return flag;
	}
	
	//��ȡ��ͥ��Ա��Ϣ
	public List<HashMap<String, String>> getjtcyxx(String xh){
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.cyxm,");
		sql.append(" t1.mc cw,");
		sql.append(" t.cyxxdw,");
		sql.append(" t.cynsr cyysr");
		sql.append(" from xg_xszz_new_jtcyb t");
		sql.append(" left join xszz_jtcygxb t1");
		sql.append(" on t.cygx = t1.dm where t.xh = ?");
		return dao.getListNotOut(sql.toString(), new String[]{xh});
	}
	
	//��ȡѧ��������Ϣ
	public HashMap<String, String> getxsxx(String xh){
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.xm,");
		sql.append(" t.xb,");
		sql.append(" t.jgmc,");
		sql.append(" t.xh,");
		sql.append(" t.mzmc mz,");
		sql.append(" t.sfzh,");
		sql.append(" t.xymc, ");
		sql.append(" t.bjmc,");
		sql.append(" t.zymc,");
		sql.append(" t.sjhm,");
		sql.append(" t.jtdz,");
		sql.append(" t.yxmc ssyq,");
		sql.append(" t.nj,");
		sql.append(" t.dzyx,");
		sql.append(" t.jtyb");
		sql.append(" from view_xsxxb t");
		sql.append(" where t.xh  = ?");
		return dao.getMapNotOut(sql.toString(), new String[]{xh});
	}
	
	//��ȡѧ��ѧ�ƺ��꼶�����ж��Ƿ�Ϊ��ҵ��
	public boolean getSfbys(String xh,String usertype){
		StringBuilder sql = new StringBuilder();
		boolean flag = false;
		if(!usertype.equals("stu")){
			flag = true;
		}
		sql.append(" select xz,nj from view_xsjbxx where xh = ?");
		HashMap<String, String>  map = dao.getMapNotOut(sql.toString(), new String[]{xh});
		if(map != null && !map.equals("") && map.size() != 0){
			int nd = Integer.parseInt(Base.currXn.split("-")[1]);
		    int nj = Integer.parseInt(map.get("nj"));
		    int xz = Integer.parseInt(map.get("xz"));
		    if(nj+xz >  nd){
		    	flag = false;
		    }else{
		    	flag = true;
		    }
		}
	    
	    return flag;
	}
	
	//��ȡ������������
    public List<HashMap<String, String>> getjmyjList(String jml){
    	StringBuilder sql = new StringBuilder();
    	sql.append(" select * from ");
    	sql.append(" (select t.* ,t.jml jmbl,replace(t.jml,'%','') jmlsx from xg_zdgxh_wxjkjm_jmyj t ) t");
    	sql.append(" where jml = ? order by to_number(jmlsx) asc,to_number(xssx) asc");
    	return dao.getListNotOut(sql.toString(), new String[]{jml});
    }
    
    //��ȡ������������
    public List<HashMap<String, String>> getJml(){
    	StringBuilder sql = new StringBuilder();
    	sql.append(" select distinct t.jml jmbl  from xg_zdgxh_wxjkjm_jmyj t order by to_number(replace(t.jml,'%','')) asc");
    	return dao.getListNotOut(sql.toString(), new String[]{});
    }
    //��ȡ��ҵ����Ϣ����ܺ�
    public String getXsxxWxjkzh(String xh){
    	StringBuilder sql = new StringBuilder();
    	sql.append(" select sum(sqje) sqjezh from xg_zdgxh_wxjk_jgb where xh = ?");
    	return dao.getOneRs(sql.toString(), new String[]{xh}, "sqjezh");
    }
    //��ѯ�Ƿ�����Ϣ�����¼
    public String getWxdkjl(String xh){
    	StringBuilder sql = new StringBuilder();
    	sql.append(" select count(1) sfydk from xg_zdgxh_wxjk_jgb where xh = ?");
    	return dao.getOneRs(sql.toString(), new String[]{xh}, "sfydk");
    }
    
    //�鿴ʱ��ȡ��������list
    public List<HashMap<String, String>> getJmlCk(String[]jmyj,String jmbl){
    	StringBuilder sql = new StringBuilder();
    	ArrayList<String> paralist = new ArrayList<String>();
    	sql.append(" select distinct t.jml jmbl,t.jmyj,t.xssx  from xg_zdgxh_wxjkjm_jmyj t where t.jml = ?  ");
    	paralist.add(jmbl);
    	sql.append(" and t.xssx in (");
    	for(int i = 0; i < jmyj.length; i++) {
			sql.append("?");
			if(i != jmyj.length-1){
				sql.append(",");
			}
			paralist.add(jmyj[i]);
		}
    	sql.append(")");
        sql.append(" order by to_number(t.xssx) asc");
    	return dao.getListNotOut(sql.toString(), paralist.toArray(new String[]{}));
    }
    
    public List<HashMap<String, String>> getWxjmHz(String xydm,String nj){
    	StringBuilder sql = new StringBuilder();
    	sql.append(" select a.*,");
    	sql.append(" round(a.sqjmbl * a.zje / 100, 2) sqjmje,");
    	sql.append(" round(a.tyjmbl * a.zje / 100, 2) tyjmje");
    	sql.append(" from (select t.*,");
    	sql.append(" t1.xm,");
    	sql.append(" t1.bjmc,");
    	sql.append(" t1.xymc,");
    	sql.append(" t1.nj,");
    	sql.append(" t2.sqly,");
    	sql.append(" replace(nvl(t3.jmbl, t2.jmbl), '%', '') sqjmbl,");
    	sql.append(" replace(t2.jmbl, '%', '') tyjmbl");
    	sql.append(" from (select sum(sqje) zje, xh");
    	sql.append(" from xg_zdgxh_wxjk_jgb");
    	sql.append(" where xh in");
    	sql.append(" (select xh from view_xsjbxx t1 where t1.nj = ? and t1.xydm = ?)  and xh in (select xh from xg_zdgxh_wxjkjm_jgb)");
    	sql.append("  group by xh) t");
    	sql.append(" left join view_xsjbxx t1");
    	sql.append(" on t.xh = t1.xh");
    	sql.append(" left join xg_zdgxh_wxjkjm_jgb t2");
    	sql.append(" on t.xh = t2.xh");
    	sql.append("  left join xg_zdgxh_wxjkjm_sqb t3");
    	sql.append("  on t2.jgid = t3.sqid) a");
    	return dao.getListNotOut(sql.toString(), new String[]{nj,xydm});
    }

}
