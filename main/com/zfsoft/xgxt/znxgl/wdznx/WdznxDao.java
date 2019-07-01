/**
 * @部门:学工产品事业部
 * @日期：2015-8-27 下午06:56:23 
 */
package com.zfsoft.xgxt.znxgl.wdznx;

import java.util.HashMap;
import java.util.List;

import org.junit.Test;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.ctc.wstx.util.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.xstgl.rtgl.rtjg.RtjgForm;
import com.zfsoft.xgxt.xstgl.stgl.stsq.StsqForm;
import com.zfsoft.xgxt.znxgl.fxbgl.FxbForm;
import com.zfsoft.xgxt.znxgl.znxgl.ZnxglForm;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： yxy[工号:1206]
 * @时间： 2015-8-27 下午06:56:23
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class WdznxDao extends SuperDAOImpl<WdznxForm> {

	/*
	 * 描述: @see
	 * com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(WdznxForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	 * 描述: @收件箱查询
	 * com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object,
	 * xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(WdznxForm t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		String username = user.getUserName();
		String usertype = user.getUserType();
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (");
		sql.append(" select t.xjbh,");
		sql.append(" t.jsrbh,");
		sql.append(" t.fprbh,");
		sql.append(" t.jsrydbj,");
		sql.append(" t.jsrscbj,");
		sql.append(" t1.fsrbh,");
		sql.append(" decode(t1.fsrbh,'系统管理员','系统管理员',t2.xm) fsrxm,");
		sql.append(" t1.fssj,");
		sql.append(" t1.ztlb,");
		sql.append(" t1.xjzt,");
		sql.append(" t1.fsrydbj,");
		sql.append(" t1.fsrscbj");
		sql.append(" from XG_ZNX_NEW_SXB t");
		sql.append(" left join XG_ZNX_NEW_FXB t1");
		sql.append(" on t.xjbh = t1.xjbh");
		if(usertype.equalsIgnoreCase("stu")){
			sql.append(" left join yhb t2");
			sql.append(" on t1.fsrbh = t2.yhm");
		}else{
			sql.append(" left join (select xh yhm,xm  from xsxxb union select yhm,xm from yhb) t2");
			sql.append(" on t1.fsrbh = t2.yhm");
		}
		sql.append(" where t.jsrbh = '" + username + "'");
		sql.append(" and t.jsrscbj = '0'");
		sql.append(" )t where 1= 1  ");
		sql.append(" ");
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}

	/*
	 * 描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		// TODO 自动生成方法存根
		super.setClass(WdznxForm.class);
		super.setKey("xjbh");
		super.setTableName("XG_ZNX_NEW_FXB");
	}

	//发信箱查询
	public List<HashMap<String, String>> getFjxList(WdznxForm t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		String username = user.getUserName();
		String usertype = user.getUserType();
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from (select xjbh,fsrbh,fssj,ztlb,xjzt,fsrscbj,fsrydbj,replace(wm_concat(jsrxm),';',',') jsrxm from (");
		sql.append(" select t.xjbh,");
		sql.append(" t1.jsrbh,");
		sql.append(" t1.fprbh,");
		sql.append(" t1.jsrydbj,");
		sql.append(" t1.jsrscbj,");
		sql.append(" decode(t1.jsrbh,'系统管理员','系统管理员',t2.xm) jsrxm,");
		sql.append(" t.fsrbh,");
		sql.append(" t.fssj,");
		sql.append(" t.ztlb,");
		sql.append(" t.xjzt,");
		sql.append(" t.fsrydbj,");
		sql.append(" t.fsrscbj");
		sql.append(" from XG_ZNX_NEW_FXB t");
		sql.append(" left join  XG_ZNX_NEW_SXB t1");
		sql.append(" on t.xjbh = t1.xjbh");
		if(usertype.equalsIgnoreCase("stu")){
			sql.append(" left join yhb t2");
			sql.append(" on t1.jsrbh = t2.yhm");
		}else{
			sql.append(" left join (select xh yhm,xm  from xsxxb union select yhm,xm from yhb) t2");
			sql.append(" on t1.jsrbh = t2.yhm");
		}
		sql.append(" where t.fsrbh = '" + username + "'");
		sql.append(" and t.fsrscbj = '0'");
		sql.append(" )  group by xjbh,fsrbh,fssj,ztlb,xjzt,fsrscbj,fsrydbj)t where 1=1  ");
		sql.append(" ");
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}
	
	//主题类别list
	public List<HashMap<String, String>> getZtlbList() throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from (select distinct gnmkmc dm, 	 gnmkmc mc");
		sql.append(" from view_yhzqx t");
		sql.append(" where  zdm = '6727' and length(gnmkdm) = '3' ");
		sql.append(" union select '其他' dm, '其他' mc from dual)");
		sql.append(" order by dm desc");
		return dao.getListNotOut(sql.toString(), new String[]{});
	}
	
	//获取发送表中插入数据的xjbh,用于插入接收表中
	public HashMap<String,String> getXjbh(WdznxForm t){
		StringBuilder sql = new StringBuilder();
		sql.append(" select xjbh");
		sql.append(" from XG_ZNX_NEW_FXB t");
		sql.append(" where t.fssj = ?");
		sql.append(" and t.ztlb = ?");
		sql.append(" and t.xjzt = ?");
		sql.append(" and t.fsrbh = ?");
		return dao.getMapNotOut(sql.toString(),new String[]{t.getFssj(),t.getZtlb(),t.getXjzt(),t.getFsrbh()});
	}
	
	//获取学生列表
	public List<HashMap<String, String>> getXsxxList(WdznxForm model, User user) throws Exception {
		// 生成高级查询相关条件、条件值
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		String[] xhs = model.getXharr();
		StringBuilder sql = new StringBuilder("select * from(select a.xh,a.sjhm lxdh,a.xm,a.nj,a.xz,a.xb,a.xymc,a.zymc,");
		sql.append("a.bjmc,a.xydm,a.zydm,a.bjdm from view_xsjbxx a ");
		sql.append(")a where 1=1 ");
		if(xhs != null && !xhs[0].equals("")){
			if(xhs.length>0){
				sql.append("  and a.xh not in(");
				for (int i = 0; i < xhs.length; i++) {
					if(i!=0){
						sql.append(", ");
					}
					sql.append("'"+xhs[i]+"' ");
				}
				sql.append(")");
			}
		}
		sql.append(" ");
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		return getPageList(model, sql.toString(), inputV);
	}
	
	//获取教师列表
	public List<HashMap<String, String>> getTeaList(WdznxForm model, User user) throws Exception {
		// 生成高级查询相关条件、条件值
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		//String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		StringBuilder sql = new StringBuilder();
		String[] zghs = model.getTeaarr();
		sql.append("select * from(select a.*,decode(a.fdydbs,'0','否','是') sffdy, decode(a.bzrdbs,'0','否','是') sfbzr from (");
		sql.append(" select t.zgh,t.lxdh,t.xm,t.xb,t.bmdm,t1.bmmc");
		sql.append(",nvl(c.num,0) fdydbs,nvl(d.num,0) bzrdbs");
		sql.append(" from fdyxxb t left join zxbz_xxbmdm t1 on t.bmdm = t1.bmdm");
		sql.append(" left join (select c.zgh, count(distinct bjdm) num ");
		sql.append("   from fdybjb c ");
		sql.append("  where bjdm in ");
		sql.append("       (select bjdm from view_njxyzybj) ");
		sql.append("  group by c.zgh) c ");
		sql.append("on t.zgh=c.zgh ");

		sql.append(" left join (select d.zgh, count(distinct bjdm) num ");
		sql.append("  from bzrbbb d ");
		sql.append("  where bjdm in ");
		sql.append("       (select bjdm from view_njxyzybj) ");
		sql.append("  group by d.zgh) d ");
		sql.append("on t.zgh=d.zgh ");
		sql.append(" )a ) a where 1=1 ");
		if(zghs != null && !zghs[0].equals("")){
			//获取数组长度
			int len = zghs.length;
			if(len > 0){
				sql.append(" and a.zgh not in(");
				for(int i=0;i<len;i++){
					sql.append("'"+zghs[i]+"'");
					if(i != (len-1)){
						sql.append(",");
					}
				}
				sql.append(")");
			}
		}
		if(!user.getUserType().equalsIgnoreCase("stu")){
			sql.append(" ");
		//	sql.append(searchTjByUser);
		}
		sql.append(" ");
		sql.append(searchTj);
		return getPageList(model, sql.toString(), inputV);
	}
	
	//信件查看
	public HashMap<String, String> XjckMap(WdznxForm t){
		StringBuilder sql = new StringBuilder();
		sql.append(" select  t1.fsrbh,t1.fssj,t1.ztlb,t1.xjzt,t1.fsrydbj,t1.fsrscbj from  XG_ZNX_NEW_FXB t1 where t1.xjbh = ?");
		return dao.getMapNotOut(sql.toString(), new String[]{t.getXjbh()});
	}
	
	/**
     * 
     * @描述:保存
     * @作者：喻鑫源[工号：1206]
     * @日期：2015-12-7 下午03:19:04
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param sx
     * @return
     * @throws Exception
     * boolean 返回类型 
     * @throws
     */
    public boolean save(WdznxForm t) throws Exception{
    	StringBuilder sql = new StringBuilder();
    	sql.append(" insert into xg_znx_new_fxb (xjbh,fsrbh,fssj,fsnr,ztlb,xjzt) values(?,?,?,?,?,?)");
    	return dao.runUpdate(sql.toString(), new String[]{t.getXjbh(),t.getFsrbh(),t.getFssj(),t.getFsnr(),t.getZtlb(),t.getXjzt()});
    } 
}
