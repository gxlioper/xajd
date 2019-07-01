/**
 * @部门:学工产品事业部
 * @日期：2015-6-25 下午04:32:03 
 */  
package com.zfsoft.xgxt.szdw.gzjl.gzjljg;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchModel;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 夏夏[工号:1104]
 * @时间： 2015-6-25 下午04:32:03 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class GzjljgDao extends SuperDAOImpl<GzjljgForm> {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(GzjljgForm t) throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(GzjljgForm t, User user) throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchGzjlTjByUser(user, "t", "xydm", "");
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (");
		sql.append("select t1.*,t2.lbmc gzlbmc,t3.xm,t3.xydm,t3.xymc,t4.lksmc, ");
		sql.append("(case when length(t1.bz)>10 then substr(t1.bz,0,10)||'...' else t1.bz end)bzstr ");
		sql.append("from xg_gzjlgl_gzjljgb t1 left join xg_gzjlgl_gzlbb t2 on t1.lbdm=t2.lbdm ");
		sql.append(" left join view_jsxx t3 on t1.zgh=t3.zgh ");
		sql.append(" left join XG_SZDWX_LKSDMB t4 on t1.lks = t4.lksdm");
		sql.append(") t where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}
	/**
	 * 
	 * @描述:工作记录统计
	 * @作者：夏夏[工号：1104]
	 * @日期：2015-7-2 下午03:23:00
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getGzjltjList(GzjljgForm model,List<HashMap<String, String>> lblist,
			User user) throws Exception {
		// ====================过滤条件===================================
		model.getPages().setPageSize(Integer.MAX_VALUE);
		// 高级查询条件
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		// 权限过滤
		String searchTjByUser = SearchService.getSearchGzjlTjByUser(user, "b",
				"xydm", "bjdm");
		// 高级查询输入值
		String[] inputV = SearchService.getTjInput(model.getSearchModel());

		// query += searchTjByUser;
		// ====================过滤条件 end================================
		// ====================SQL拼装================================
		StringBuilder sb = new StringBuilder();
		
		
		sb.append("select a.* from(select ");
		for (int i = 0 , j = lblist.size() ; i < j ; i++){
			sb.append("sum(t.cs").append(i).append(") gzlb").append(i).append(",");
		}
		sb.append("t.zgh,xm,xymc from  (select");
		for (int i = 0 , j = lblist.size() ; i < j ; i++){
			sb.append(" case when t.lbdm='").append(lblist.get(i).get("gzlbdm"))
			.append("' then 1 else 0 end cs")
			.append(i).append(",");
		}
		sb.append("t.zgh, b.xm,b.xymc  from xg_gzjlgl_gzjljgb t left join view_jsxx b on t.zgh=b.zgh where 1=1  ");
		sb.append(searchTjByUser);
		sb.append(" ");
		sb.append(searchTj);
		sb.append(" ) t where 1=1 ");
		sb.append("group by t.zgh ,t.xm,t.xymc)a ");
		return getPageList(model, sb.toString(), inputV);
	}
	
	public List<HashMap<String, String>> getJsxxList(GzjljgForm t, User user) throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchGzjlTjByUser(user, "t", "xydm", "bjdm");
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (select a.zgh,a.xm,a.lxdh,decode(a.xb, '1', '男', '2', '女', a.xb) xb, ");
		sql.append("b.bmdm xydm,b.bmmc xymc,c.zzmmmc from fdyxxb a  left join zxbz_xxbmdm b on a.bmdm = b.bmdm ");
		sql.append("left join zzmmdmb c on a.zzmm = c.zzmmdm");
		sql.append(") t where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}
	
	public HashMap<String, String> getJsjbxx(String zgh) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("select a.zgh,a.xm,a.lxdh,decode(a.xb, '1', '男', '2', '女', a.xb) xb, ");
		sql.append("b.bmmc xymc,c.zzmmmc from fdyxxb a  left join zxbz_xxbmdm b on a.bmdm = b.bmdm ");
		sql.append("left join zzmmdmb c on a.zzmm = c.zzmmdm where a.zgh=?");
		return dao.getMapNotOut(sql.toString(), new String[] { zgh });
	}
	
	/**
	 * 判断当是否有填写记录
	 */
	public boolean checkExistForSave(GzjljgForm model) {
		String id = model.getJgid() == null ? "-1" : model.getJgid();
		StringBuilder sql = new StringBuilder();
		sql.append("select count(1) num from xg_gzjlgl_gzjljgb where zgh=? and lbdm=? and gzsj=? and id<>? ");
		String num = dao.getOneRs(sql.toString(), new String[] {model.getZgh(),model.getLbdm(),model.getGzsj(),id}, "num");
		return Integer.valueOf(num) > 0;
	}
	/**
	 * 
	 * @描述:根据申请id删除结果数据
	 * @作者：夏夏[工号：1104]
	 * @日期：2015-7-2 上午08:58:34
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param id
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean delJgById(String id)throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append("delete from xg_gzjlgl_gzjljgb where sqid=? ");
		return dao.runUpdate(sql.toString(),new String[]{id});
	}
	/**
	 * 
	 * @描述:获取用户部门
	 * @作者：夏夏[工号：1104]
	 * @日期：2015-7-3 上午08:54:17
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xydm
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public String getXymc(String xydm)throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append("select bmmc from zxbz_xxbmdm where bmdm=? ");
		return dao.getOneRs(sql.toString(), new String[]{xydm}, "bmmc");
	}
	@Override
	public GzjljgForm getModel(GzjljgForm myForm) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("select t1.*,t2.lbmc gzlbmc,t3.xm,t3.xydm,t3.xymc,t4.lksmc from xg_gzjlgl_gzjljgb t1 left join xg_gzjlgl_gzlbb t2 on t1.lbdm=t2.lbdm ");
		sql.append(" left join view_jsxx t3 on t1.zgh=t3.zgh left join xg_szdwx_lksdmb t4 on t1.lks=t4.lksdm where t1.jgid=?");
		return getModel(sql.toString(), new String[]{myForm.getJgid()});
	}
	@Override
	protected void setTableInfo() {
		super.setClass(GzjljgForm.class);
		super.setTableName("xg_gzjlgl_gzjljgb");
		super.setKey("jgid");
	}
	
	/** 
	 * @描述:获得谈话对象信息(浙江树人)
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-1-18 上午10:45:53
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xhArr
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getThdxList(String[] xhArr) {
		StringBuilder sql = new StringBuilder("select a.* from(select a.xh,a.xm,a.xb,a.nj,a.xymc,a.zymc,");
		sql.append("a.bjmc,a.xydm,a.zydm,a.bjdm from view_xsjbxx a");
		if(xhArr.length>0) {
			sql.append(" where a.xh in(");
			for (int i = 0; i < xhArr.length; i++) {
				if(i!=0){
					sql.append(", ");
				}
				sql.append("'"+xhArr[i]+"' ");
			}
			sql.append("))a");
		}
		return dao.getListNotOut(sql.toString(), new String[]{});
	}
	public List<HashMap<String, String>> getZghList(GzjljgForm model,User user)throws Exception{
		// ====================过滤条件===================================
		model.getPages().setPageSize(Integer.MAX_VALUE);
		// 高级查询条件
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		// 权限过滤
		String searchTjByUser = SearchService.getSearchGzjlTjByUser(user, "t3",
				"xydm", "");
		// 高级查询输入值
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append("select a.*,b.xm,b.xymc from ( ");
		sql.append("select zgh from ( select t1.zgh,t.xm,t.xymc,t.gzsj,t.xydm,t.gzlbmc from  XG_GZJLGL_GZJLJGB t1 " + 
                   " left join  (select a.zgh,a.xm,a.xymc,a.xydm,b.gzsj,c.lbmc gzlbmc from view_jsxx a " + 
                   " left join XG_GZJLGL_GZJLJGB b on a.zgh=b.zgh " +
                   " left join xg_gzjlgl_gzlbb c on b.lbdm=c.lbdm ) t  on t.zgh = t1.zgh) t3");
		sql.append(" where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		sql.append("group by zgh ) a");
		sql.append(" left join  view_jsxx b on a.zgh = b.zgh  ");
		return dao.getListNotOut(sql.toString(), inputV);
	}

	/** 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：lgx[工号：1553]
	 * @日期：2018-4-19 下午02:15:41
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zgh
	 * @param searchModel
	 * @param user
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getGzjlListByZgh(String zgh,String lbmc,
			SearchModel searchModel, User user) throws Exception{
		// ====================过滤条件===================================
		// 高级查询条件
		String searchTj = SearchService.getSearchTj(searchModel);
		// 权限过滤
		String searchTjByUser = SearchService.getSearchGzjlTjByUser(user, "t",
				"xydm", "bjdm");
		// 高级查询输入值
		String[] inputV = null;
		if(StringUtil.isNull(lbmc)){
			inputV = new String[]{zgh};
		}else{
			inputV = new String[]{zgh,lbmc};
		}
		String[] s = SearchService.getTjInput(searchModel);
		int strLen1 = inputV.length;// 保存第一个数组长度
        int strLen2 = s.length;// 保存第二个数组长度
        inputV = Arrays.copyOf(inputV, strLen1 + strLen2);// 扩容
        System.arraycopy(s, 0, inputV, strLen1, strLen2);// 将第二个数组与第一个数组合并
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (select a.*,b.lbmc gzlbmc ,t1.xydm,t1.xm,t1.xymc  from XG_GZJLGL_GZJLJGB a left join xg_gzjlgl_gzlbb b on a.lbdm = b.lbdm " +
				" left join view_jsxx t1 on t1.zgh = a.zgh " +
				" where a.zgh=? ");
		if(StringUtil.isNull(lbmc)){
			sql.append(" and b.lbmc <> '学生面谈' ");
			sql.append(" and b.lbmc <> '联系家长' ");
			sql.append(" and b.lbmc <> '寝室走访' ");
			sql.append(" and b.lbmc <> '特殊情况' ");
		}else{
			sql.append("and b.lbmc=?  ");
		}

		

		
		
		sql.append(" ) t where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		return dao.getListNotOut(sql.toString(), inputV);
	}

}
