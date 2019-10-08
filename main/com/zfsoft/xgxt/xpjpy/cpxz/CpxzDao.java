/**
 * @部门:学工产品事业部
 * @日期：2013-7-20 下午01:59:00 
 */  
package com.zfsoft.xgxt.xpjpy.cpxz;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 评奖评优管理模块
 * @类功能描述: 评奖评优2013-参评小组  
 * @作者： Penghui.Qu [工号：445]
 * @时间： 2013-7-20 下午01:59:00 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class CpxzDao extends SuperDAOImpl<CpxzModel> {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		// TODO 自动生成方法存根

	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(CpxzModel t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	   	参评小组查询
	 */

	public List<HashMap<String, String>> getPageList(CpxzModel model, User user)
			throws Exception {
	
	//生成高级查询相关条件、条件值 
	String searchTj = SearchService.getSearchTj(model.getSearchModel());
	String searchTjByUser = SearchService.getSearchTjByUser(user, "t1", "xydm", "bjdm");		
	String[] inputV = SearchService.getTjInput(model.getSearchModel());
	
	StringBuilder sql = new StringBuilder();
	sql.append(" select * from (select distinct c.nj,c.xydm,c.xymc,c.zydm,c.zymc,c.bjdm,c.bjmc,t7.sydm,t7.symc, ");
	sql.append(" b.cpzdm,b.cpzmc,a.tjzt from XG_ZHCP_FSTJJLB a ");
	sql.append(" left join XG_ZHCP_CPZB b on a.cpzdm=b.cpzdm ");
	sql.append(" left join view_njxyzybj_all c on a.bjdm=c.bjdm ");
	sql.append(" left join XG_XTWH_SYBJGLB t6 on a.bjdm = t6.bjdm ");
	sql.append(" left join XG_XTWH_SYDMB t7 on t6.sydm = t7.sydm ");
	sql.append(" left join VIEW_XSJBXX t8 on a.BJDM=t8.BJDM  ");
	sql.append(" where t8.PYCC=3 and exists (select 1 from xg_pjpy_new_csszb d where a.xn=d.xn and a.xq=d.xq and rownum =1) ");
	sql.append(" ) t1 where 1=1 ");
	sql.append(searchTjByUser);
	sql.append(" ");
	sql.append(searchTj);
	
	return getPageList(model, sql.toString(), inputV);
	}

	
	/**
	 * 
	 * @描述: 按班级初始化参评组
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-7-23 下午04:12:24
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws Exception
	 * boolean 返回类型
	 */
	public boolean createCpxzByBj() throws Exception{
		
		String sql = "insert into xg_zhcp_cpzb(cpzdm,cpzmc) select * from (select distinct bjdm,bjmc from view_njxyzybj_fdy t1 where not exists (select 1 from xg_zhcp_cpzb t2 where t1.bjdm=t2.cpzdm))";
		return dao.runUpdate(sql, new String[]{});
	}

	/**
	 * 按书院初始化参评组
	 * @return
	 * @throws Exception
	 */
	public boolean createCpxzBySy()throws Exception{
		String sql = "insert into xg_zhcp_cpzb(cpzdm,cpzmc) select * from (select sydm,symc from xg_xtwh_sydmb t1 where not exists (select 1 from xg_zhcp_cpzb t2 where t1.sydm=t2.cpzdm))";
		return dao.runUpdate(sql, new String[]{});
	}
	/**
	 * 
	 * @描述: 按年级专业初始化参评组
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-7-23 下午04:12:43
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws Exception
	 * boolean 返回类型
	 */
	public boolean createCpxzByNjZy() throws Exception{
		
		String sql = "insert into xg_zhcp_cpzb(cpzdm,cpzmc) select * from (select distinct nj||zydm,nj||zymc from view_njxyzybj_fdy t1 where not exists (select 1 from xg_zhcp_cpzb t2 where t1.nj||t1.zydm=t2.cpzdm))";
		return dao.runUpdate(sql, new String[]{});
	}
	
	
	/**
	 * 
	 * @描述: 按班级建立 班与参评组之间的关系
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-7-23 下午04:23:55
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xn
	 * @param xq
	 * @return
	 * @throws Exception
	 * boolean 返回类型
	 */
	public boolean initCpxzByBj(String xn , String xq , User user) throws Exception{
		StringBuilder sql = new StringBuilder("insert into xg_zhcp_fstjjlb(xn,xq,bjdm,cpzdm) ");
		sql.append("select distinct ?,?,t1.bjdm,t1.bjdm from view_njxyzybj_fdy t1 where 1=1 ");
		
		if ("xy".equals(user.getUserType())){
			sql.append(" and xydm='")
			   .append(user.getUserDep())
			   .append("'");
		}
		
		return dao.runUpdate(sql.toString(), new String[]{xn,xq});
	}

	public boolean initCpxzBySy(String xn , String xq , User user) throws Exception {
		StringBuilder sql = new StringBuilder("insert into xg_zhcp_fstjjlb(xn,xq,bjdm,cpzdm) ");

		sql.append(" select ?,?,t1.bjdm,'' from ");
		sql.append(" (select distinct a.bjdm from xsxxb a where a.sfzx = '在校' and a.bjdm is not null)");
		sql.append("  t1 where 1=1");
		return dao.runUpdate(sql.toString(), new String[]{xn,xq});
	}

	public boolean updateCpxzBySy(String xn,String xq,User user) throws Exception{
		StringBuilder sql = new StringBuilder();

		sql.append(" update xg_zhcp_fstjjlb t1 set (bjdm,cpzdm)=");
		sql.append(" (select x.bjdm,x1.sydm from (select distinct bjdm from xsxxb a where a.sfzx = '在校') x ");
		sql.append(" left join XG_XTWH_SYBJGLB x1 on x.bjdm = x1.bjdm ");
		sql.append(" where t1.bjdm=x.bjdm)");
		sql.append(" where exists (select 1 from xsxxb t3 where t1.bjdm=t3.bjdm ");

		sql.append(") and xn=? and xq=?");

		return dao.runUpdate(sql.toString(), new String[]{xn,xq});
	}

	/**
	 * 
	 * @描述: 批量更新班级所属参评小组
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-7-24 上午09:10:29
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xn
	 * @param xq
	 * @param user
	 * @return
	 * @throws Exception
	 * boolean 返回类型
	 */
	public boolean updateCpxzByBj(String xn,String xq,User user) throws Exception{
		StringBuilder sql = new StringBuilder();
		
		sql.append(" update xg_zhcp_fstjjlb t1 set (bjdm,cpzdm)=");
		sql.append(" (select distinct bjdm,bjdm from view_njxyzybj_fdy t2 where t1.bjdm=t2.bjdm)");
		sql.append(" where exists (select 1 from view_njxyzybj_fdy t3 where t1.bjdm=t3.bjdm ");
		
		if ("xy".equals(user.getUserType())){
			sql.append(" and t3.xydm='")
			   .append(user.getUserDep())
			   .append("'");
		}
		
		sql.append(") and xn=? and xq=?");
		
		return dao.runUpdate(sql.toString(), new String[]{xn,xq});
	}
	
	
	
	/**
	 * 
	 * @描述: 批量更新班级所属参评小组
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-7-24 上午09:11:41
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xn
	 * @param xq
	 * @param user
	 * @return
	 * @throws Exception
	 * boolean 返回类型
	 */
	public boolean updateCpxzByNjzy(String xn,String xq,User user) throws Exception{
		StringBuilder sql = new StringBuilder();
		
		sql.append("update xg_zhcp_fstjjlb t1 set (bjdm,cpzdm)=");
		sql.append("(select distinct bjdm,nj||zydm from view_njxyzybj_fdy t2 where t1.bjdm=t2.bjdm)");
		sql.append("where exists (select 1 from view_njxyzybj_fdy t3 where t1.bjdm=t3.bjdm ");
		
		if ("xy".equals(user.getUserType())){
			sql.append(" and t3.xydm=='")
			   .append(user.getUserDep())
			   .append("'");
		}
		
		sql.append(") and xn=? and xq=?");
		
		return dao.runUpdate(sql.toString(), new String[]{xn,xq});
	}
	
	
	/**
	 * 
	 * @描述: 按年级、专业建立 班与参评组之间的关系
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-7-23 下午04:24:52
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xn
	 * @param xq
	 * @return
	 * @throws Exception
	 * boolean 返回类型
	 */
	public boolean initCpxzByNjzy(String xn , String xq, User user) throws Exception{
		StringBuilder sql = new StringBuilder("insert into xg_zhcp_fstjjlb(xn,xq,bjdm,cpzdm) ");
		
		sql.append("select distinct ?,?,t1.bjdm,t1.nj||zydm from view_njxyzybj_fdy t1 where 1=1 ");
		
		if ("xy".equals(user.getUserType())){
			sql.append(" and xydm='")
			   .append(user.getUserDep())
			   .append("'");
		}
		return dao.runUpdate(sql.toString(), new String[]{xn,xq});
	}
	
	
	
	/**
	 * 
	 * @描述: 查询当前周期是否有参评 小组
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-7-23 下午05:10:09
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xn
	 * @param xq
	 * @return
	 * boolean 返回类型
	 */
	public boolean isHaveCpxz(String xn ,String xq){
		
		String sql = "select count(1) num from xg_zhcp_fstjjlb where xn = ? and xq=?";
		
		return Integer.valueOf(dao.getOneRs(sql, new String[]{xn,xq}, "num")) > 0;
	}

	/** 
	 * @描述:查询班级信息
	 * @作者：cq [工号：785]
	 * @日期：2013-8-7 下午04:39:41
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ids
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getBjInfo(String[] id) {
		
		StringBuilder sql = new StringBuilder();
		List<String> params = new ArrayList<String>();
		
		sql.append(" select * from view_njxyzybj_all where bjdm in ( ");
			for(int i = 0; i<id.length; i++){
				
				if(i!=0){
					sql.append(",");
				}
				sql.append("?");
				params.add(id[i]);
			}
			sql.append(" ) ");
				
		return dao.getListNotOut(sql.toString(), id);
	}

	/**
	 * @throws Exception  
	 * @描述:新增参评组
	 * @作者：cq [工号：785]
	 * @日期：2013-8-13 上午10:33:41
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param bjdm
	 * @param pmz
	 * @param cpzdm
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean insertCpz(String cpzdm, String pmz) throws Exception {
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("insert into xg_zhcp_cpzb values(?,?)");
		
		return dao.runUpdate(sql.toString(), new String[]{cpzdm,pmz});
	}

	/**
	 * @throws Exception  
	 * @描述:更新班级所对应的排名组代码
	 * @作者：cq [工号：785]
	 * @日期：2013-8-13 上午10:40:56
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xn
	 * @param xq
	 * @param bjdm
	 * @param cpzdm
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean updateBj(String xn, String xq, String[] bjdm, String cpzdm) throws Exception {
		
		StringBuilder sql = new StringBuilder();
		String[] params = new String[1+bjdm.length+2];
		System.out.println(bjdm);
		System.out.println(params);
		sql.append(" update  xg_zhcp_fstjjlb set cpzdm = ? where bjdm in ( ");
		params[0] = cpzdm;
		for(int i=0; i<bjdm.length; i ++ ){
			if(i!=0){
				sql.append(",");
			}
			sql.append("?");
			params[i+1] = bjdm[i];
		}
		sql.append(") and xn = ? and xq = ? ");
		params[1+bjdm.length] = xn;
		params[1+bjdm.length+1] = xq;
				
		return dao.runUpdate(sql.toString(), params);
	}
	
	/**
	 * 
	 * @描述:根据参评组名称取代码
	 * @作者：ligl
	 * @日期：2013-8-27 上午10:28:27
	 * @修改记录: 
	 * @param xmdm
	 * @return
	 * @throws Exception
	 * String 返回类型 
	 * @throws
	 */
	public String getIdByName(String mc) throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append(" select cpzdm ");
		sb.append(" from  xg_zhcp_cpzb ");
		sb.append(" where cpzmc=?");
		String[] inputValue = { mc };
		String[] outputValue = { "cpzdm" };
		String[] oneRs = dao.getOneRs(sb.toString(), inputValue, outputValue);
		String dm = null;
		if (oneRs != null && oneRs.length > 0) {
			dm = oneRs[0];
		}
		return dm;
	}

	/**
	 * @throws Exception  
	 * @描述:添加学年班级与参评组关联
	 * @作者：cq [工号：785]
	 * @日期：2014-8-5 下午02:13:52
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xn
	 * void 返回类型 
	 * @throws 
	 */
	public boolean initXnfsjt(String xn) throws Exception {
		return dao.runProcuder("{call pro_xg_zhcp_xnfstjjlb(?)}", new String[]{xn});
		
	}

	public List<HashMap<String, String>> getCpzglyList(CpxzModel model, User user) throws Exception {
		//生成高级查询相关条件、条件值
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t1", "xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(model.getSearchModel());

		StringBuilder sql = new StringBuilder();

		sql.append("select * from (");
		sql.append("select t1.cpzdm,t2.yhm,t1.cpzmc");
		sql.append(" from xg_zhcp_cpzb t1 ");
		sql.append(" left join (");
		sql.append(" select t.cpzdm, wm_concat(t1.xm) yhm");
		sql.append(" from FDYCPZGLB t ");
		sql.append(" left join fdyxxb t1 on t.zgh = t1.zgh group by cpzdm ");
		sql.append(" ) t2 on t1.cpzdm = t2.cpzdm");
		sql.append(" ) where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		return getPageList(model,sql.toString(),inputV);
	}

	public List<HashMap<String, String>> getGlyList(CpxzModel model, User user) throws Exception {

		//生成高级查询相关条件、条件值
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
//		String searchTjByUser = SearchService.getSearchTjByUser(user, "t1", "xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		String[] cpzdms = model.getCpzdms().split(",");

		StringBuilder sql = new StringBuilder();
		sql.append("select * from (");
		sql.append("select t1.zgh,t1.xm,t1.sydm,t2.symc");
		sql.append(",decode(t1.xb, '1', '男', '2', '女', t1.xb) xb");
		sql.append(" from fdyxxb t1 ");
		sql.append(" left join XG_XTWH_SYDMB t2 on t1.sydm = t2.sydm ");
		sql.append(" ) a where 1=1 ");

		StringBuilder s = new StringBuilder();
		if("0".equals(model.getSffp())){//未分配
			sql.append(" and a.zgh not in (");
			sql.append(" select zgh from FDYCPZGLB where cpzdm in (");
			for(int i=0;i<cpzdms.length;i++){
				s.append(" '").append(cpzdms[i]).append("',");
			}
			String s1 =s.substring(0,s.toString().length() - 1);//去掉最后一个,
			sql.append(s1).append(")) ");
		} else {
			sql.append(" and a.zgh in (");
			sql.append(" select zgh from FDYCPZGLB where cpzdm in (");
			for(int i=0;i<cpzdms.length;i++){
				s.append(" '").append(cpzdms[i]).append("',");
			}
			String s1 =s.substring(0,s.toString().length() - 1);//去掉最后一个,
			sql.append(s1).append(")) ");
		}
		sql.append(searchTj);
		return getPageList(model,sql.toString(),inputV);
	}

	public int[] savefp(CpxzModel model)throws Exception{
		List<String> sqlArray = new ArrayList<String>();
		StringBuffer sql = new StringBuffer();
		String[] cpzdms = model.getCpzdms().split(",");
		String[] zghs = model.getZghs();
		for(int i=0;i<cpzdms.length;i++){
			for(int j=0;j<zghs.length;j++){
				sql.append("insert into FDYCPZGLB(ZGH,CPZDM) values('"+zghs[j]+"','"+cpzdms[i]+"')");
				sqlArray.add(sql.toString());
				sql.setLength(0);
			}
		}
		return dao.runBatch(sqlArray.toArray(new String[]{}));
	}

	public boolean cancelFp(CpxzModel model)throws Exception{
		String[] cpzdms = model.getCpzdms().split(",");
		String[] zghs = model.getZghs();
		StringBuffer sql = new StringBuffer();
		List<String> paraList = new ArrayList<String>();
		sql.append("delete from FDYCPZGLB where cpzdm = '"+cpzdms[0]+"' and zgh in (");
		for(int i=0;i<zghs.length;i++){
			sql.append("?");
			if(i != zghs.length-1){
				sql.append(",");
			}
			paraList.add(zghs[i]);
		}
		sql.append(" )");
		return dao.runUpdate(sql.toString(),paraList.toArray(new String[]{}));
	}
}
