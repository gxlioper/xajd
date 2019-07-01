/**
 * @部门:学工产品事业部
 * @日期：2015-10-8 上午09:27:50 
 */  
package com.zfsoft.xgxt.xtwh.ksdh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.action.Base;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： yxy[工号:1206]
 * @时间： 2015-10-8 上午09:27:50 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */
//修改表结构之后，关联相应的改变，XG_XTWH_KSDHB与 xg_xtwh_dhtp之间依赖cdid关联
//修改表结构之后，关联相应的改变，xg_xtwh_dhtp与 gnmkdmb之间依赖dyym关联
public class KsdhDao extends SuperDAOImpl<KsdhForm> {

	//组代码6727为学生组，固定不变
	private static final String XS_ZDM = "6727";
	
	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(KsdhForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(KsdhForm t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		// TODO 自动生成方法存根
		super.setClass(KsdhForm.class);
		super.setKey("id");
		super.setTableName("XG_XTWH_KSDHB");
	}
	
	//已设置我的应用入口菜单查询 yxy
	public List<HashMap<String, String>> getGncdSzList(User user){
		StringBuilder sql = new StringBuilder();
		ArrayList<String> paralist = new ArrayList<String>();
		sql.append("select distinct * from (");
		sql.append(" select  t.id,");
		sql.append(" t.yhm,");
		sql.append(" t.cdid gnmkdm,");
		sql.append(" t.czsj,");
		sql.append(" t.yhlx,");
		sql.append(" t.xssx,");
		sql.append(" t1.gnmktblj gnmklj,");
		sql.append(" t1.mkfldm,");
		sql.append(" t1.cshdhmk,");
		sql.append(" t2.dyym,");
		sql.append(" case when length(t2.gnmkmc) <= 4 then t2.gnmkmc else substr(t2.gnmkmc,0,4) || '...' end gnmkmc,");
		sql.append(" t2.gnmkmc title");
		sql.append(" from XG_XTWH_KSDHB t");
		sql.append(" join xg_xtwh_dhtp t1");
		sql.append(" on t.cdid = t1.cdid");
		sql.append(" join gnmkdmb t2");
		sql.append(" on t1.dyym = t2.dyym");
		sql.append(" where t.yhm = ?");
		paralist.add(user.getUserName());
		if("stu".equalsIgnoreCase(user.getUserType())){
			sql.append(" and t2.gnmkdm  in(select distinct gnmkdm from");
			sql.append(" yhzqxb where zdm = ? ");
			paralist.add(XS_ZDM);
		}else{
			sql.append(" and t2.gnmkdm  in(select distinct gnmkdm from ");
			sql.append(" yhqxb where  yhm = ? ");
			paralist.add(user.getUserName());
		}
		sql.append(")");
		sql.append(" and (t1.xxqx = 'public' or t1.xxqx = ?) ");
		paralist.add(Base.xxdm);
		sql.append(") order by to_number(xssx) asc");
		return dao.getListNotOut(sql.toString(), paralist.toArray(new String[]{}));
	}
	
	//未设置我的应用入口菜单默认值 yxy
	public List<HashMap<String, String>> getGncdSzList_mr(User user){
		StringBuilder sql = new StringBuilder();
		ArrayList<String> paralist = new ArrayList<String> ();
		sql.append("select distinct * from (");
		sql.append(" select distinct t.cdid gnmkdm, " +
				" case when length(t1.gnmkmc) <= 4 then t1.gnmkmc else substr(t1.gnmkmc,0,4) || '...' end gnmkmc, " +
				" t1.gnmkmc title,t.dyym, t.gnmktblj gnmklj, t.mkfldm");
		sql.append(" from xg_xtwh_dhtp t");
		sql.append(" join gnmkdmb t1");
		sql.append(" on t.dyym = t1.dyym");
		//如要加入新的默认应用可在此处加入，但是需与该类中insertInto_mrz()方法相一致，且必须存在getFlcx_head()该分类中
		sql.append(" where t.cshdhmk = '1' ");
		if("stu".equalsIgnoreCase(user.getUserType())){
			sql.append("  and t1.mklx <> 'tea'");
		}else{
			sql.append(" and t1.mklx <> 'stu'");
		}
		if("stu".equalsIgnoreCase(user.getUserType())){
			sql.append(" and t1.gnmkdm  in(select distinct gnmkdm from");
			sql.append(" yhzqxb where  zdm  = ? ");
			paralist.add(XS_ZDM);//组代码6727为学生组，固定不变
		}else{
			sql.append(" and t1.gnmkdm  in(select distinct gnmkdm from ");
			sql.append(" yhqxb where  yhm = ? ");
			paralist.add(user.getUserName());
		}
		sql.append(")");
		sql.append(" and  (t.xxqx = 'public' or t.xxqx = ?) ");
		paralist.add(Base.xxdm);
		sql.append(")");
		sql.append(" order by gnmkdm asc");
		return dao.getListNotOut(sql.toString(), paralist.toArray(new String[]{}));
	}
	
	//在用户打开编辑应用菜单时，先去验证是否有设置值
	public String checkExistSz(User user){
		StringBuilder sql = new StringBuilder();
		ArrayList<String> paralist = new ArrayList<String>();
		sql.append(" select count(1) flag");
		sql.append(" from XG_XTWH_KSDHB t");
		sql.append(" join XG_XTWH_DHTP t1");
		sql.append(" on t.cdid = t1.cdid");
		sql.append(" join gnmkdmb t2");
		sql.append(" on t1.dyym = t2.dyym");
		sql.append(" where t.yhm = ?");
		paralist.add(user.getUserName());
		if("stu".equalsIgnoreCase(user.getUserType())){
			sql.append(" and t2.gnmkdm  in(select distinct gnmkdm from");
			sql.append(" yhzqxb where  zdm  = ? ");
			paralist.add(XS_ZDM);//组代码6727为学生组，固定不变
		}else{
			sql.append(" and t2.gnmkdm  in(select distinct gnmkdm from ");
			sql.append(" yhqxb where  yhm = ? ");
			paralist.add(user.getUserName());
		}
		sql.append(")");
		return dao.getOneRs(sql.toString(), paralist.toArray(new String[]{}), "flag");
	}
	
	//首次编辑我的应用时插入数据
	public boolean insertInto_mrz(User user) throws Exception{
		StringBuilder sql = new StringBuilder();
		ArrayList<String> paralist = new ArrayList<String>();
		sql.append(" insert into XG_XTWH_KSDHB");
		sql.append(" select distinct * from (");
		sql.append(" select  sys_guid() id,  ? yhm,  t.cdid cdid, sysdate czsj, ? yhlx,replace(t.cdid,'0','')  xssx");
		paralist.add(user.getUserName());
		paralist.add(user.getUserType());
		sql.append(" from xg_xtwh_dhtp t");
		sql.append(" join  gnmkdmb t1");
		sql.append(" on t.dyym = t1.dyym");
		//如要加入新的默认应用可在此处加入，但是需与该类中getGncdSzList_mr()方法相一致,且必须存在getFlcx_head()该分类中
		sql.append(" where t.cshdhmk = '1'");
		//sql.append(" and nvl(t.dxq, 0) <> 0");
		if("stu".equalsIgnoreCase(user.getUserType())){ 
			sql.append("  and t1.mklx <> 'tea'");
		}else{
			sql.append(" and t1.mklx <> 'stu'");
		}
		if("stu".equalsIgnoreCase(user.getUserType())){
			sql.append(" and t1.gnmkdm  in(select distinct gnmkdm from");
			sql.append(" yhzqxb where  zdm  = ? ");
			paralist.add(XS_ZDM);//组代码6727为学生组，固定不变
		}else{
			sql.append(" and t1.gnmkdm  in(select distinct gnmkdm from ");
			sql.append(" yhqxb where  yhm = ? ");
			paralist.add(user.getUserName());
		}
		sql.append(")");
		sql.append(" and  (t.xxqx = 'public' or t.xxqx = ?) ");
		sql.append(")");
		paralist.add(Base.xxdm);
		return dao.runUpdate(sql.toString(), paralist.toArray(new String[]{}));
	}
	
	//分类查询头
	public List<HashMap<String, String>> getFlcx_head(User user){
		StringBuilder sql = new StringBuilder();
		ArrayList<String> paralist = new ArrayList<String>();
		sql.append("  select t.mkfldm,t.mkflmc from XG_DHFLDMB t where (t.xxqx = 'public' or t.xxqx = ?)");
		paralist.add(Base.xxdm);
	    return dao.getListNotOut(sql.toString(), paralist.toArray(new String[]{}) );
	}
	
	//应用查询 @gnmkmc功能模块名称 @gnmkdm 功能分类代码
	public List<HashMap<String, String>> getFlcx(String usertype,String username){
		StringBuilder sql = new StringBuilder();
		ArrayList<String> paralist = new ArrayList<String>();
		sql.append("select distinct * from (");
		sql.append(" select  t.cdid gnmkdm," +
				" case when length(t1.gnmkmc) <= 4 then t1.gnmkmc else substr(t1.gnmkmc,0,4) || '...' end gnmkmc," +
				" t1.gnmkmc title,t1.dyym, t.gnmktblj gnmklj, t.mkfldm");
		sql.append(" from xg_xtwh_dhtp  t");
		sql.append(" join gnmkdmb t1");
		sql.append(" on t.dyym = t1.dyym");
		if("stu".equalsIgnoreCase(usertype)){
			sql.append("  and t1.mklx <> 'tea'");
			sql.append(" and t1.gnmkdm  in(select distinct gnmkdm from");
			sql.append(" yhzqxb where  zdm  = ?");
			paralist.add(XS_ZDM);//组代码6727为学生组，固定不变
			sql.append(")");
		}else{
			sql.append(" and t1.mklx <> 'stu'");
			sql.append(" and t1.gnmkdm in(select distinct gnmkdm from ");
			sql.append(" yhqxb where  yhm = ?");
			paralist.add(username);
			sql.append(")");
		}
		sql.append(" and t.cdid not in(select cdid from XG_XTWH_KSDHB where yhm = ? )");
		paralist.add(username);
		sql.append(" and  (t.xxqx = 'public' or t.xxqx = ?) ");
		paralist.add(Base.xxdm);
		sql.append(" order by t1.xssx asc");
		sql.append(")");
		sql.append(" order by gnmkdm asc");
		return dao.getListNotOut(sql.toString(),paralist.toArray(new String[]{}));
	}
	
	
    /**
     * 
     * @描述:查询
     * @作者：yxy路[工号：1206]
     * @日期：2015-10-19 上午11:22:17
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param usertype
     * @param gnmkmc 
     * @param gnmkdm 保留该参数，因为暂时不分类，该参数现在都传值null
     * @param username
     * @param htmlgnmkdm
     * @return
     * @throws Exception
     * List<HashMap<String,String>> 返回类型 
     * @throws
     */
    public List<HashMap<String, String>> QueryData(String usertype,String gnmkmc,String mkfldm,String username,String[] htmlgnmkdm) throws Exception{
    	StringBuilder sql = new StringBuilder();
    	ArrayList<String> paralist = new ArrayList<String>();
    	sql.append("select distinct * from (");
    	sql.append(" select distinct t.cdid gnmkdm, " +
    			" case when length(t1.gnmkmc) <= 4 then t1.gnmkmc else substr(t1.gnmkmc,0,4) || '...'   end gnmkmc, " +
    			"  t1.gnmkmc title,t1.dyym,  t.gnmktblj gnmklj, t.mkfldm");
		sql.append(" from xg_xtwh_dhtp t");
		sql.append(" join gnmkdmb t1");
		sql.append(" on t.dyym = t1.dyym");
		if("stu".equalsIgnoreCase(usertype)){
			sql.append("  and t1.mklx <> 'tea'");
			sql.append(" and t1.gnmkdm  in(select distinct gnmkdm from");
			sql.append(" yhzqxb where  zdm  = ?");
			paralist.add(XS_ZDM);
			sql.append(")");
		}else{
			sql.append(" and t1.mklx <> 'stu'");
			sql.append(" and t1.gnmkdm in(select distinct gnmkdm from ");
			sql.append(" yhqxb where  yhm = ?");
			paralist.add(username);
			sql.append(")");
		}
		if(htmlgnmkdm != null && htmlgnmkdm.length != 0){
			sql.append(" and t.cdid not in(");
			int htmllen =  htmlgnmkdm.length;
			for (int i = 0; i < htmllen; i++) {
				sql.append("?");
				paralist.add(htmlgnmkdm[i]);
				if(i !=  htmllen-1){
					sql.append(",");
				}
			}
			sql.append(")");
		}
		
		if(mkfldm != null && !mkfldm.trim().equals("")){
			sql.append(" and t.mkfldm = ?");
			paralist.add(mkfldm);
		}
		if(gnmkmc != null && !gnmkmc.trim().equals("")){
			sql.append(" and t1.gnmkmc like '%'|| ? || '%'");
			paralist.add(gnmkmc);
		}
		sql.append(" and  (t.xxqx = 'public' or t.xxqx = ?) ");
		paralist.add(Base.xxdm);
		sql.append(")");
		sql.append(" order by gnmkdm asc");
    	return dao.getListNotOut(sql.toString(), paralist.toArray(new String[]{}));
    }
    
    public boolean del_originalData(User user) throws Exception{
    	StringBuilder sql = new StringBuilder();
    	sql.append(" delete from XG_XTWH_KSDHB where yhm = ?");
    	return dao.runUpdate(sql.toString(), new String[]{user.getUserName()});
    }
    
    /**
     * 
     * @描述:单个用户功能授权保存之后删除快速导航表中的内容
     * @作者：yxy[工号：1206]
     * @日期：2015-10-23 下午02:59:54
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param yhm
     * @return
     * @throws Exception
     * boolean 返回类型 
     * @throws
     */
    public boolean del_Rubbish_data(String yhm){
    	StringBuilder sql = new StringBuilder();
    	sql.append(" delete from XG_XTWH_KSDHB where yhm = ?");
		sql.append(" and cdid not in(");
		sql.append(" select cdid from xg_xtwh_dhtp where dyym in(");
		sql.append(" select distinct dyym from gnmkdmb where gnmkdm in(");
		sql.append(" select distinct gnmkdm from yhqxb where yhm = ?");
		sql.append(")");
		sql.append(" )");
		sql.append(")");
		boolean result = true;
		try {
		    result =  dao.runUpdate(sql.toString(), new String[]{yhm,yhm});
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}finally{
			System.out.print(sql.toString());
			System.out.println("[yhm:"+yhm+"]");
		}
    	return result;
    }
    
    /**
     * 
     * @描述:用户组授权保存之后删除快速导航表中的内容
     * @作者：yxy[工号：1206]
     * @日期：2015-10-23 下午04:13:12
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param yhm
     * @return
     * boolean 返回类型 
     * @throws
     */
    public boolean del_Rubbish_data_yhz(String zdm){
    	StringBuilder sql = new StringBuilder();
    	ArrayList<String> paralist = new ArrayList<String>();
		if(!XS_ZDM.equals(zdm)){
			sql.append(" delete from XG_XTWH_KSDHB where yhm in (");
			sql.append(" select yhm from view_yhz_yhxxb where zdm =  ?)");
			sql.append("  and  cdid not in (select distinct cdid from xg_xtwh_dhtp where dyym in  (select distinct dyym from gnmkdmb where gnmkdm  in(select gnmkdm from yhzqxb where zdm =?");
			sql.append(")))");
			paralist.add(zdm);
			paralist.add(zdm);
		}else{
			sql.append(" delete from XG_XTWH_KSDHB where yhlx = 'stu' ");
			sql.append("  and  cdid not in (select distinct cdid from xg_xtwh_dhtp where dyym in  (select distinct dyym from gnmkdmb where gnmkdm  in(select gnmkdm from yhzqxb where zdm = ?");
			sql.append(")))");
			paralist.add(zdm);
		}
		boolean result = true;
		try {
		    result =  dao.runUpdate(sql.toString(), paralist.toArray(new String[]{}));
		} catch (Exception e) {
			logger.error(e.getMessage());
		}finally{
			logger.info(sql.toString());
			logger.info("[yhm:"+zdm+"]");
		}
    	return result;
    }
    
    
}
