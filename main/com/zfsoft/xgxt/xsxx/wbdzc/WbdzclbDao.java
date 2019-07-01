/**
 * @部门:学工产品事业部
 * @日期：2016-3-8 上午10:57:51 
 */  
package com.zfsoft.xgxt.xsxx.wbdzc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import xgxt.form.User;
import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张昌路[工号:982]
 * @时间： 2016-3-8 上午10:57:51 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

	public class WbdzclbDao extends SuperDAOImpl<WbdzclbForm> {
		/*
    		描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
		 */
		@Override
		/**
		 * 获得未报到注册类型
		 */
		public List<HashMap<String, String>> getPageList (WbdzclbForm t)
			throws Exception {
			List<String> params = new ArrayList<String>();
			StringBuilder sql = new StringBuilder();
			sql.append(" select wbdlbdm,wbdlbmc from xg_xsxx_bdzc_wbdlb a where 1 = 1 ");
			if (!StringUtil.isNull(t.getWbdlbmc())) {
				params.add(t.getWbdlbmc());
				sql.append("  and wbdlbmc like '%'||?||'%'");
			}
			return getPageList(t, sql.toString(),params.toArray(new String[] {}));
		}
		
		
		/*
		      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
		 */
		
		
		@Override
		public List<HashMap<String, String>> getPageList(WbdzclbForm t,
				User user) throws Exception {
			// TODO 自动生成方法存根
			return null;
		}
		
		
		/*
	      	描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
		 */

			@Override
			protected void setTableInfo() {	
				super.setTableName("xg_xsxx_bdzc_wbdlb");
				super.setKey("wbdlbdm");
			}
			
			
		/**
		 * @描述: 	增加类型
		 * @作者：	孟威[工号：1186]
		 * @日期：2016-3-10 上午09:14:55
		 * @修改记录: 修改者名字-修改日期-修改内容
		 * @param model
		 * @return
		 * @throws Exception
		 * boolean 返回类型 
		 * @throws
		 */
			public boolean addLxInfo(WbdzclbForm model) throws Exception {
				boolean wbdzc = false;
				String sql;
				sql = " select count(1) num from xg_xsxx_bdzc_wbdlb where wbdlbmc=?  ";
				String num = dao.getOneRs(sql,new String[] {model.getWbdlbmc()}, "num");
				if ("0".equals(num)) {
					sql = " insert into xg_xsxx_bdzc_wbdlb(wbdlbdm,wbdlbmc) values(?,?)";
					wbdzc = dao.runUpdate(sql, new String[] {model.getWbdlbdm(),model.getWbdlbmc()});
				} else {
					// msg="该未报到注册类型名称已存在！";
					throw new SystemException(MessageKey.XSXX_WBDZC_WBDLBMC);
				}			
				return wbdzc;
			}
			
			
		/**
		 * @描述:	未报到注册修改
		 * @作者：	孟威[工号：1186]
		 * @日期：2016-3-10 上午09:24:14
		 * @修改记录: 修改者名字-修改日期-修改内容
		 * @param model
		 * @return
		 * @throws Exception
		 * boolean 返回类型 
		 * @throws
		 */
			public boolean updateLxInfo(WbdzclbForm model)
			throws Exception {
				boolean wbdzc = false;
				String sql1;
				String sql;
				sql1 = "select count(1) num from xg_xsxx_bdzc_wbdlb where wbdlbmc=? ";
				String num = dao.getOneRs(sql1,new String[] { model.getWbdlbmc() }, "num");
				//用相同的类型名称和类型代码  查找类型是否已存在
				sql = "select wbdlbdm  from xg_xsxx_bdzc_wbdlb where wbdlbdm = ? and wbdlbmc=? ";
				String wbdlbdm = dao.getOneRs(sql, new String[] { model.getWbdlbdm(),model.getWbdlbmc()},"wbdlbdm");
				if ((wbdlbdm.equals(model.getWbdlbdm())) || "0".equals(num)) {
					sql = "update xg_xsxx_bdzc_wbdlb set wbdlbmc=? where wbdlbdm=?";
					wbdzc = dao.runUpdate(sql, new String[] { model.getWbdlbmc(),model.getWbdlbdm()});
				} else {
					// msg="该未报到注册类型名称已存在！";
					throw new SystemException(MessageKey.XSXX_WBDZC_WBDLBMC);
				}
				return wbdzc;
			}
			
			
		/**
		 * @描述:	获取最大类型代码
		 * @作者：	孟威[工号：1186]
		 * @日期：2016-3-10 上午09:25:07
		 * @修改记录: 修改者名字-修改日期-修改内容
		 * @return
		 * String 返回类型 
		 * @throws
		 */
			public String getWbdlbdm() {
				String sql = "select max(to_number(wbdlbdm)) max from xg_xsxx_bdzc_wbdlb ";
				return dao.getOneRs(sql, new String[]{}, "max");
			}
			
			
		/**
		 * @描述:TODO(这里用一句话描述这个方法的作用)
		 * @作者：孟威[工号：982]
		 * @日期：2016-3-10 上午09:48:31
		 * @修改记录: 修改者名字-修改日期-修改内容
		 * @param wbdlbdm
		 * @return
		 * boolean 返回类型 
		 * @throws
		 */
			public boolean isCanDel(String wbdlbdm){
				StringBuffer sql = new StringBuffer();
				sql.append(" select distinct a.wbdlbmc from xg_xsxx_bdzc_wbdlb a left join ");
				sql.append(" xg_xsxx_bdzcb b on a.wbdlbdm=b.wbdlbdm ");
				sql.append(" where a.wbdlbdm  = ?   and  ( b.wbdlbdm is not null ) " );
				Map<String,String> map= dao.getMapNotOut(sql.toString(),new String[]{wbdlbdm});
				String wbdlbmc = map.get("wbdlbmc");
				//如果未提交才可以提交
				return wbdlbmc==null?true:false;
			}
			
			
			/**
			 * 判断是否存在结果应用
			 * 孟威
			 */
			public String[] pdsfsy( String value) throws Exception{
				
				StringBuilder sql = new StringBuilder(" select distinct b.wbdlbmc from xg_xsxx_bdzcb a,xg_xsxx_bdzc_wbdlb b where a.wbdlbdm = b.wbdlbdm and a.wbdlbdm in (" +value +") ");
				String[] xmmc=dao.getRs(sql.toString(), new String[]{}, "wbdlbmc");
					
				return xmmc;
			}
			
			
			/**
			 * @描述:	判断未报到类别名称是否存在
			 * @作者：	孟威[工号：1186]
			 * @日期：	2016-3-16 上午10:02:01
			 * @修改记录: 修改者名字-修改日期-修改内容
			 * @param model
			 * @return
			 * String 返回类型 
			 * @throws
			 */
			public String checkExistForSave(WbdzclbForm model) {
				StringBuilder sql = new StringBuilder(" select count(1) num from xg_xsxx_bdzc_wbdlb where wbdlbmc = ? ");
				String num=dao.getOneRs(sql.toString(), new String[]{model.getWbdlbmc()}, "num");
				return num;	
			}
			
			
			/**
			 *修改保存时候判断是否存在记录
			 *孟威
			 */
			public String checkExistForUpdate(WbdzclbForm model) {
				String sql=" select count(1) num from xg_xsxx_bdzc_wbdlb where wbdlbmc = ? and wbdlbdm <> ?";
				String num=dao.getOneRs(sql.toString(), new String[]{model.getWbdlbmc(),model.getWbdlbdm()}, "num");
				return num;	
			}
			
			
}
