/**
 * @部门:学工产品事业部
 * @日期：2018-12-25 下午04:05:26 
 */  
package com.zfsoft.xgxt.zhdj.xsdzbhdygl;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import xgxt.form.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张昌路[工号:982]
 * @时间： 2018-12-25 下午04:05:26 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class DzbzwwhDao extends SuperDAOImpl<DzbzwwhForm>{

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(DzbzwwhForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(DzbzwwhForm t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		List<String> params = new ArrayList<String>();
        StringBuilder sql = new StringBuilder(" select a.dm, a.mc, a.zwss, a.zwlx from XG_ZHDJ_DZBGL_ZWDMB a ");
        return getPageList(t, sql.toString(), params.toArray(new String[]{}));
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		// TODO 自动生成方法存根
		this.setClass(DzbzwwhForm.class);
        this.setKey("dm");
        this.setTableName("XG_ZHDJ_DZBGL_ZWDMB");
		
	}
	
	public boolean delZw(DzbzwwhForm model,String zt) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<String> paraList = new ArrayList<String>();
        String[] dms = model.getDms();
        sql.append(" delete from XG_ZHDJ_DZBGL_ZWDMB where dm in(");
        for (int i = 0; i < dms.length; i++) {
            sql.append("?");
            if (i != dms.length - 1) {
                sql.append(",");
            }
            paraList.add(dms[i]);
        }
        sql.append(" )");
        StringBuilder sqls = new StringBuilder();
        List<String> parasList = new ArrayList<String>();
        if(zt.equals("1")){
            sqls.append(" delete from xg_zhdj_dzbgl_zwglb where zwid in(");
        }else {
            sqls.append(" delete from xg_zhdj_dzzgl_zwglb where zwdm in(");
        }
        for (int i = 0; i < dms.length; i++) {
            sqls.append("?");
            if (i != dms.length - 1) {
                sqls.append(",");
            }
            parasList.add(dms[i]);
        }
        sqls.append(" )");
        if(dao.runUpdate(sqls.toString(), parasList.toArray(new String[]{}))) {
            return dao.runUpdate(sql.toString(), paraList.toArray(new String[]{}));
        }else {
            return false;
        }
    }

	/** 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：张昌路[工号：982]
	 * @日期：2018-12-26 上午09:02:12
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean checkRepeatDm(DzbzwwhForm model) {
		// TODO 自动生成方法存根
	    StringBuilder sql = new StringBuilder();
        List<String> paraList = new ArrayList<String>();
        sql.append(" select count(1) cnt");
        sql.append(" from XG_ZHDJ_DZBGL_ZWDMB");
        sql.append(" where dm = ?");
        paraList.add(model.getDm());
        String cnt = dao.getOneRs(sql.toString(), paraList.toArray(new String[]{}), "cnt");
        return "0".equals(cnt) ? true : false;
	}
	
	
   public List<HashMap<String, String>> getZwList(DzbzwwhForm model) throws Exception {
        List<String> params = new ArrayList<String>();
        StringBuilder sql = new StringBuilder();
        sql.append("  select a.dm,a.mc,a.zwss,a.zwlx from XG_ZHDJ_DZBGL_ZWDMB a where a.dm = ?");
        params.add(model.getDm());
        return getPageList(model, sql.toString(), params.toArray(new String[]{}));
    }
   
   public List<HashMap<String, String>> getdzbid(DzbzwwhForm model) throws Exception {
       List<String> params = new ArrayList<String>();
       StringBuilder sql = new StringBuilder();
       sql.append("  select a.dzbid from XG_ZHDJ_DZBGL_dzb a where dzblx = ?");
       params.add(model.getZwss());
       return getPageList(model, sql.toString(), params.toArray(new String[]{}));
   }
   
   public boolean insertDzb(DzbzwwhForm model) throws Exception {
       StringBuilder dzb = new StringBuilder(" insert into xg_zhdj_dzbgl_zwglb(dzbid,zwid,zwmc) values (?,?,?) ");
       List<String> parasList = new ArrayList<String>();
       List<HashMap<String, String>> dzbidList = getdzbid(model);
       boolean sum =true;
       for(int i = 0;i<dzbidList.size();i++){
    	   String did =  dzbidList.get(i).get("dzbid");
    	   parasList.add( did);
           parasList.add(model.getDm());
           parasList.add(model.getMc());
    	   sum = dao.runUpdate(dzb.toString(),parasList.toArray(new String[]{}));
    	   if(!sum)
    		   break;
	       parasList = new ArrayList<String>();

       }
       return sum;
   }

    public boolean insertDzzZw(DzbzwwhForm model) throws Exception {
        StringBuilder dzz = new StringBuilder(" insert into XG_ZHDJ_DZZGL_ZWGLB(dzzid,zwdm,zwmc) values (?,?,?) ");
        List<String> parasList = new ArrayList<String>();
        List<HashMap<String, String>> dzzidList = getDzz(model);
        boolean sum =true;
        for(int i = 0;i<dzzidList.size();i++){
            String did =  dzzidList.get(i).get("dm");
            parasList.add(did);
            parasList.add(model.getDm());
            parasList.add(model.getMc());
            sum = dao.runUpdate(dzz.toString(),parasList.toArray(new String[]{}));
            if(!sum)
                break;
            parasList = new ArrayList<String>();

        }
        return sum;
    }

    private List<HashMap<String,String>> getDzz(DzbzwwhForm model) {
        StringBuilder sql = new StringBuilder(" select * from XG_ZHDJ_DZBGL_JCDWDMB_SYDMB a ");
        return dao.getListNotOut(sql.toString(), new String[]{});
    }

    public boolean checkDzbDm(DzbzwwhForm model) throws Exception {
	    StringBuilder sql = new StringBuilder("select count(*) from xg_zhdj_dzbgl_zwglb where zwid in ( ");
        List<String> paraList = new ArrayList<String>();
	    String[] dms = model.getDms();
        for (int i = 0; i < dms.length; i++) {
            sql.append("?");
            if (i != dms.length - 1) {
                sql.append(",");
            }
            paraList.add(dms[i]);
        }
        sql.append(" )");
        return dao.runUpdate(sql.toString(), paraList.toArray(new String[]{}));
    }

    public boolean checkDzzDm(DzbzwwhForm model) throws Exception {
        StringBuilder sql = new StringBuilder("select count(*) from xg_zhdj_dzzgl_zwglb where zwid in ( ");
        List<String> paraList = new ArrayList<String>();
        String[] dms = model.getDms();
        for (int i = 0; i < dms.length; i++) {
            sql.append("?");
            if (i != dms.length - 1) {
                sql.append(",");
            }
            paraList.add(dms[i]);
        }
        sql.append(" )");
        return dao.runUpdate(sql.toString(), paraList.toArray(new String[]{}));
    }
}
