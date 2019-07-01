/**
 * @部门:学工产品事业部
 * @日期：2016-11-15 下午03:10:18 
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
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： yxy[工号:1206]
 * @时间： 2016-11-15 下午03:10:18 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class HsdxdDao extends SuperDAOImpl<HsdxdForm> {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(HsdxdForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(HsdxdForm t, User user)
			throws Exception {
		// TODO 自动生成方法存根
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
		sql.append(" '未审核',");
		sql.append(" '1',");
		sql.append(" '通过',");
		sql.append(" '2',");
		sql.append(" '不通过',");
		sql.append(" '3',");
		sql.append(" '退回',");
		sql.append(" '4',");
		sql.append(" '需重审',");
		sql.append(" '5',");
		sql.append(" '审核中') || ']' shztmc,");
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
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		// TODO 自动生成方法存根
		this.setClass(HsdxdForm.class);
		this.setKey("id");
		this.setTableName("XG_ZXDK_XDSQB");
	}
	
	/**
	 * 
	 * @描述:获取续贷信息明细列表
	 * @作者：yxy[工号：1206]
	 * @日期：2016-11-16 下午04:00:51
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getXdxxMx(String xh){
		StringBuffer sql = new StringBuffer();
		String flagxn = Base.currXn.substring(0, 4);
		sql.append("  select t.*,");
		sql.append("  case");
		sql.append("  when t.shztmc not in('未提交','未申请') then t.shztmc ");
		sql.append("  when t.dkzt = 'xind' then");
		sql.append("  '已通过'");
		sql.append("  when t.dkzt = 'xud' and t.flagn = 'dn' and t.shztmc = '未申请' then");
		sql.append("  'czan'");
		sql.append("  when t.dkzt = 'xud' and t.flagn = 'ln' then");
		sql.append("  '未开始'");
		sql.append("  when t.dkzt = 'qxxud' then");
	    sql.append("  '放弃申请'");
		sql.append("  else");
		sql.append("  t.shztmc");
		sql.append("  end cznr,t.nzsfdk + t.nxfdk + t.nshfdk dkze");
		sql.append("  from (select t.*,");
		sql.append("  decode(nvl(t1.fkje,0), '0','0','1') fkzt,");
		sql.append("  decode(nvl(t1.fkje, '0'),");
		sql.append("  '0',");
		sql.append("  '未放款',");
		sql.append("  '已放款'");
		sql.append("  ) fkztmc,");
		sql.append("  t2.shzt,");
		sql.append("  decode(nvl(t2.shzt, 'wsq'),");
		sql.append("  '1',");
		sql.append("  '已通过',");
		sql.append("  '2',");
		sql.append("  '不通过',");
		sql.append("  '3',");
		sql.append("  '已退回',");
		sql.append("  '5',");
		sql.append("  '审核中',");
		sql.append("  'wsq',");
		sql.append("  '未申请',");
		sql.append("  '0',");
		sql.append("  '未提交') shztmc,");
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
	 * @描述: 贷款信息
	 * @作者：yxy[工号：1206]
	 * @日期：2016-11-16 下午04:46:37
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getDkxxMap(String xh){
		StringBuffer sql = new StringBuffer();
		sql.append("  select sum(t.dkqx) dkqs,sum(t.dkje) dkje,sum(t.jhr1) dkqx,sum(t.zsfdks) zsfdks,sum(t.xfdks) xfdks,sum(t.shfdks) shfdks from  xg_zxdk_xydkjgb t where xh = ?");
		return dao.getMapNotOut(sql.toString(), new String[]{xh});
	}
	
	/**
	 * 
	 * @描述: 贷款结果数量验证
	 * @作者：yxy[工号：1206]
	 * @日期：2016-11-16 下午05:29:41
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getDkjgNum(String xh){
		StringBuffer sql = new StringBuffer();
		sql.append("  select count(1) num from xg_zxdk_xydkjgb where xh = ?");
		return dao.getOneRs(sql.toString(), new String[]{xh}, "num");
	}
	
	/**
	 *
	 * @描述: 放贷金额总和
	 * @作者：yxy[工号：1206]
	 * @日期：2016-11-16 下午05:47:40
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getFdjeZh(String xh){
		StringBuffer sql = new StringBuffer();
		sql.append(" select nvl(sum(fkje),0) fkzh from xg_zxdk_hsd_xydfdb  where xh = ?");
		return dao.getOneRs(sql.toString(), new String[]{xh}, "fkzh");
	}
	
	/**
	 * 
	 * @描述: 获取续贷申请数据
	 * @作者：yxy[工号：1206]
	 * @日期：2016-11-17 上午11:42:14
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
    public List<HashMap<String, String>> getXdsqList(String xh){
    	StringBuffer sql = new StringBuffer();
    	sql.append(" select t.*,");
    	sql.append(" decode(t.shzt,");
    	sql.append(" '0',");
    	sql.append(" '未提交',");
    	sql.append(" '5',");
    	sql.append(" '审核中',");
    	sql.append(" '2',");
    	sql.append(" '不通过',");
    	sql.append(" '3',");
    	sql.append(" '已退回',");
    	sql.append(" '5',");
    	sql.append(" '审核中','1','已通过') shztmc");
    	sql.append(" from XG_ZXDK_XDSQB t");
    	sql.append(" where t.id in (select jgid from XG_HSD_ZXDK_NDKB where xh = ?)");
    	return dao.getListNotOut(sql.toString(), new String[]{xh});
    }
    
    /**
     * @throws Exception 
     * 
     * @描述:插入数据到申请表
     * @作者：yxy[工号：1206]
     * @日期：2016-11-17 下午02:42:53
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param xh
     * @return
     * boolean 返回类型 
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
     * @描述: 放弃续贷
     * @作者：yxy[工号：1206]
     * @日期：2016-11-18 上午09:15:54
     * @修改记录: 修改者名字-修改日期-修改内容
     * @return
     * boolean 返回类型 
     * @throws
     */
    public boolean qxsq(String jgid) throws Exception{
    	StringBuffer sql = new StringBuffer();
    	sql.append(" update XG_HSD_ZXDK_NDKB set dkzt = ? where jgid = ?");
    	return dao.runUpdate(sql.toString(), new String[]{"qxxud",jgid});
    }
    
    /**
     * 
     * @描述:获取续贷信息
     * @作者：yxy[工号：1206]
     * @日期：2016-11-18 下午04:36:14
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param id
     * @return
     * HashMap<String,String> 返回类型 
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
     * @描述: 续贷审核通过插入放贷表
     * @作者：yxy[工号：1206]
     * @日期：2016-11-18 下午05:32:49
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param id
     * @return
     * boolean 返回类型 
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
     * @描述:续贷审核撤销之后删除放贷表数据
     * @作者：yxy[工号：1206]
     * @日期：2016-11-18 下午05:40:18
     * @修改记录: 修改者名字-修改日期-修改内容
     * @return
     * boolean 返回类型 
     * @throws
     */
    public boolean delFdb(String id) throws Exception{
    	StringBuffer sql = new StringBuffer();
    	sql.append(" delete from xg_zxdk_hsd_xydfdb where id = ?");
    	return dao.runUpdate(sql.toString(), new String[]{id});
    }
    
    /**
     * 
     * @描述: 放贷情况查询
     * @作者：yxy[工号：1206]
     * @日期：2016-11-21 上午09:02:19
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param user
     * @param t
     * @return
     * @throws Exception
     * List<HashMap<String,String>> 返回类型 
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
     * @描述: 更新放贷表
     * @作者：yxy[工号：1206]
     * @日期：2016-11-21 上午09:30:49
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param t
     * @return
     * boolean 返回类型 
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
     * @描述: 获取放贷表字段
     * @作者：yxy[工号：1206]
     * @日期：2016-11-21 上午10:01:26
     * @修改记录: 修改者名字-修改日期-修改内容
     * @return
     * List<HashMap<String,String>> 返回类型 
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
     * @描述: 获取放贷表实体model
     * @作者：yxy[工号：1206]
     * @日期：2016-11-21 上午10:32:15
     * @修改记录: 修改者名字-修改日期-修改内容
     * @return
     * HsdxdForm 返回类型 
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
     * @描述: 根据合同编号获取贷款账号
     * @作者：yxy[工号：1206]
     * @日期：2016-11-22 下午05:15:34
     * @修改记录: 修改者名字-修改日期-修改内容
     * @return
     * String 返回类型 
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
     * @描述:华师大个性化判断，如果已有放贷记录，助学贷款申请不允许删除，审核不允许撤销
     * @作者：yxy[工号：1206]
     * @日期：2016-11-23 上午09:26:09
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param htbh
     * @return
     * String 返回类型 
     * @throws
     */
    public String getfdNum(String htbh){
    	StringBuffer sql = new StringBuffer();
    	sql.append(" select count(1) num from xg_zxdk_hsd_xydfdb where htbh = ? and fkje is not null");
    	return dao.getOneRs(sql.toString(), new String[]{htbh}, "num");
    }
    
    /**
     * 
     * @描述:华师大个性化判断，续贷时撤销或者删除时判断，是否有放贷记录
     * @作者：yxy[工号：1206]
     * @日期：2016-11-23 上午09:26:09
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param htbh
     * @return
     * String 返回类型 
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
     * @描述: 获取放贷金额
     * @作者：yxy[工号：1206]
     * @日期：2016-11-23 上午10:56:35
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param jgid
     * @return
     * String 返回类型 
     * @throws
     */
    public String getFdjes(String jgid){
    	StringBuffer sql = new StringBuffer();
    	sql.append(" select nzsfdk+nxfdk+nshfdk  fdje from XG_HSD_ZXDK_NDKB where jgid = ?");
    	return dao.getOneRs(sql.toString(), new String[]{jgid}, "fdje");
    }
    
}
