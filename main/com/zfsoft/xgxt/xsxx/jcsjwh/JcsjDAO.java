package com.zfsoft.xgxt.xsxx.jcsjwh;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/**
 * @系统名称: 学生工作管理系统
 * @类功能描述:基础数据维护（年级学院专业班级）
 * @作者： qilm
 * @时间： 2013-9-29
 * @版本： V1.0
 */
public class JcsjDAO extends SuperDAOImpl<JcsjForm> {

	/*
	      描述:
	 */
	@Override
	protected void setTableInfo() {
		super.setKey("xh");
		super.setTableName("xsxxb");
		super.setClass(JcsjForm.class);
	}

	/*
	      描述:
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(JcsjForm model)
			throws Exception {
		return null;
	}
	
	/**
	 * @描述: 获取学院列表
	 * @作者：Qilm[工号：964]
	 * @日期：2013-12-9 上午09:08:01
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getXyList() {
		// 获取学院列表
		StringBuilder sql = new StringBuilder();
		sql.append(" select distinct bmdm xydm, bmmc xymc, substr(f_pinyin(bmmc), 0, 1) || bmmc pyszm ");
		sql.append("   from zxbz_xxbmdm ");
		sql.append("   where bmlb = '5' ");
		sql.append("  order by pyszm " );
		return dao.getListNotOut(sql.toString(), new String[]{});
	}
	
	/**
	 * 
	 * @描述: 获取专业列表
	 * @作者：Qilm[工号：964]
	 * @日期：2013-12-9 上午09:12:26
	 * @param xydm
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getZyList(String xydm) {
		// 获取专业列表
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.zydm,a.zymc,a.bmdm xydm,c.bmmc xymc,substr(f_pinyin(zymc),0,1)||a.zymc pyszm ");
		sql.append(" from bks_zydm a ");
		sql.append(" left join zxbz_xxbmdm c ");
		sql.append(" on a.bmdm = c.bmdm ");
		
		if (!((xydm == null) || xydm.equalsIgnoreCase("")
				|| xydm.equalsIgnoreCase("all") || xydm.equalsIgnoreCase("%"))) {
			sql.append("where a.bmdm='"+ xydm + "' ");
		}

		sql.append(" order by pyszm ");		
		return dao.getListNotOut(sql.toString(), new String[]{});
	}
	
	/**
	 * 取得需要修改的基础数据信息
	 */
	@Override
	public JcsjForm getModel(JcsjForm model) throws Exception{
		
		StringBuilder sql = new StringBuilder();
		String xzflg = model.getXzflg();
		// 学院
		if("".equals(xzflg) || "0".equals(xzflg)){
			sql.append(" select * from (" );
			sql.append(" select a.bmdm xydm,a.bmdm xydmold,a.bmmc xymc,nvl(b.xss,0) xss,nvl(c.zys,0) zys,nvl(d.bjs, 0) bjs, a.bmlb  ");		
			sql.append(" from zxbz_xxbmdm a ");		
			sql.append(" left join (select count(xh) xss,xydm ");		
			sql.append("            from view_xsbfxx ");		
			sql.append("            group by xydm ");		
			sql.append("            )b on b.xydm = a.bmdm ");
			sql.append(" left join ( select count(distinct zydm) zys,bmdm ");		
			sql.append("            from bks_zydm ");		
			sql.append("            group by bmdm ");		
			sql.append("            ) c on c.bmdm = a.bmdm ");			
			sql.append(" left join (select count(distinct a.bjdm) bjs, b.bmdm ");		
			sql.append("            from bks_bjdm a ");		
			sql.append("            left join bks_zydm b ");	
			sql.append("                on a.zydm = b.zydm ");		
			sql.append("            group by b.bmdm) d ");	
			sql.append("            on d.bmdm = a.bmdm ");
			sql.append(" )a "); 
			sql.append(" where 1=1 ");
			sql.append(" and xydm = '"+ model.getXydm() +"' ");
			
			//专业
		}else if("1".equals(xzflg)){

			sql.append(" select * from (" );
			sql.append(" select a.zydm,a.zydm zydmold,a.zymc,a.bmdm xydmzy,c.bmmc xymc,nvl(b.xss,0) xss,nvl(d.bjs,0) bjs ");
			sql.append("   from bks_zydm a ");
			sql.append("   left join (select count(xh) xss,xydm,zydm ");
			sql.append(" 	   from view_xsbfxx ");
			sql.append(" 	   group by xydm,zydm ");
			sql.append(" 	   )b on b.xydm = a.bmdm and a.zydm = b.zydm ");
			sql.append("   left join zxbz_xxbmdm c ");
			sql.append("   on a.bmdm = c.bmdm ");
			sql.append("   left join ( select count(distinct bjdm) bjs,zydm ");
			sql.append("      from bks_bjdm ");
			sql.append("      group by zydm ");
			sql.append(" ) d on d.zydm = a.zydm ");
			sql.append(" )a "); 
			sql.append(" where 1=1 ");	
			sql.append(" and zydm = '"+ model.getZydm() +"' ");
			
			//班级
		}else if("2".equals(xzflg)){

			sql.append(" select * from (" );
			sql.append(" select d.bmdm xydmbj, ");
			sql.append("        d.bmmc xymc, ");
			sql.append("        a.zydm zydmbj, ");
			sql.append("        c.zymc, ");
			sql.append("        a.bjdm, ");
			sql.append("        a.bjdm bjdmold, ");
			sql.append("        a.bjmc, ");
			sql.append("        a.nj, ");
			sql.append("        a.tgxydm, ");
			sql.append("        nvl(b.xss, 0) xss ");
			sql.append("   from bks_bjdm a ");
			sql.append("   left join (select count(xh) xss, xydm, zydm, bjdm,nj ");
			sql.append("                from view_xsbfxx ");
			sql.append("               group by xydm, zydm, bjdm,nj) b ");
			sql.append("     on a.zydm = b.zydm ");
			sql.append("    and a.bjdm = b.bjdm ");
			sql.append("    and a.nj = b.nj ");
			sql.append(" left join bks_zydm  c ");
			sql.append("   on a.zydm = c.zydm ");
			sql.append(" left join zxbz_xxbmdm d ");
			sql.append("   on c.bmdm = d.bmdm ");
			sql.append(" )a "); 
			sql.append(" where 1=1 ");
			sql.append(" and bjdm = '"+ model.getBjdm() +"' ");
		}
		logger.debug(sql);
		return getModel(sql.toString(), new String[]{});
	}
	
	/**
	 * 取出毕业处理列表
	 */
	@Override
	public List<HashMap<String, String>> getPageList(JcsjForm model, User user)
			throws Exception {
		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");	
		StringBuilder sql = new StringBuilder();
		
		// 选择FLG
		String xzflg = model.getXzflg();

		//专业
		if("1".equals(xzflg)){

			sql.append(" select * from " );
			sql.append(" VIEW_NEW_DC_XSXX_JCSJWH_ZY ");
			sql.append(" a "); 
			sql.append(" where 1=1 ");		
			sql.append(searchTjByUser);
			sql.append(" ");
			sql.append(searchTj);
			//班级
		}else if("2".equals(xzflg)){

			sql.append(" select * from " );
			sql.append(" VIEW_NEW_DC_XSXX_JCSJWH_BJ ");
			sql.append(" a "); 
			sql.append(" where 1=1 ");		
			sql.append(searchTjByUser);
			sql.append(" ");
			sql.append(searchTj);
			// 学院
		}else{

			sql.append(" select * from " );
			sql.append(" VIEW_NEW_DC_XSXX_JCSJWH_XY ");
			sql.append(" a "); 
			sql.append(" where 1=1 ");
			sql.append(searchTjByUser);
			sql.append(" ");
			sql.append(searchTj);
			
		}
		return getPageList(model, sql.toString(), inputV);
	}

	/**
	 * @描述: 判断是否已存在(代码是否冲突)
	 * @作者：Qilm[工号：964]
	 * @日期：2013-12-6 下午04:33:24
	 * @param myForm
	 * @return boolean 返回类型
	 * @throws
	 */
	public boolean ishasExist(JcsjForm myForm) throws Exception {

		StringBuilder sql = new StringBuilder();

		// 选择类别(0：学院；1：专业；2：班级)
		String xzflg = myForm.getXzflg();

		// 0：学院
		if ("".equals(xzflg) || "0".equals(xzflg)) {
			
			// 如果修改前后的代码一致 则无需判断
			if(myForm.getXydm().equals(myForm.getXydmold())){
				return false;
			}
			
			sql.append(" select count(1) counts ");
			sql.append(" from zxbz_xxbmdm ");
			sql.append(" where bmdm = ? ");

			List<HashMap<String, String>> lst = dao.getListNotOut(sql
					.toString(), new String[] { myForm.getXydm() });

			if (lst != null) {
				return Integer.parseInt(lst.get(0).get("counts")) > 0 ? true
						: false;
			} else {
				return false;
			}

		} else if ("1".equals(xzflg)) {
			
			// 如果修改前后的代码一致 则无需判断
			if(myForm.getZydm().equals(myForm.getZydmold())){
				return false;
			}

			sql.append(" select count(1) counts ");
			sql.append(" from bks_zydm ");
			sql.append(" where zydm = ? ");

			List<HashMap<String, String>> lst = dao.getListNotOut(sql
					.toString(), new String[] { myForm.getZydm() });

			if (lst != null) {
				return Integer.parseInt(lst.get(0).get("counts")) > 0 ? true
						: false;
			} else {
				return false;
			}
		} else if ("2".equals(xzflg)) {

			// 如果修改前后的代码一致 则无需判断
			if(myForm.getBjdm().equals(myForm.getBjdmold())){
				return false;
			}
			
			sql.append(" select count(1) counts ");
			sql.append(" from bks_bjdm ");
			sql.append(" where bjdm = ? ");

			List<HashMap<String, String>> lst = dao.getListNotOut(sql
					.toString(), new String[] { myForm.getBjdm() });

			if (lst != null) {
				return Integer.parseInt(lst.get(0).get("counts")) > 0 ? true
						: false;
			} else {
				return false;
			}
		}
		return false;
	}
	/** 
	 * @描述: 增加基础数据
	 * @作者：Qilm[工号：964]
	 * @日期：2013-12-6 下午04:33:24
	 * @param myForm
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean saveJcsj(JcsjForm myForm) throws Exception {
		StringBuilder sql = new StringBuilder();

		// 选择类别(0：学院；1：专业；2：班级)
		String xzflg = myForm.getXzflg();

		// 0：学院
		if ("0".equals(xzflg)) {
			
			sql.append(" insert into zxbz_xxbmdm ");
			sql.append(" values(?,?,'','','1',?,'') ");
			
			return dao.runUpdate(sql.toString(), new String[]{myForm.getXydm(),myForm.getXymc(),myForm.getBmlb()});
			
			// 1：专业
		} else if ("1".equals(xzflg)) {

			sql.append(" insert into bks_zydm (zydm,bmdm,xkmldm,zymc)");
			sql.append(" values(?,?,'00',?) ");
			
			return dao.runUpdate(sql.toString(), new String[]{myForm.getZydm(),myForm.getXydmzy(),myForm.getZymc()});
			
			
			//2：班级
		} else if ("2".equals(xzflg)) {

			sql.append(" insert into bks_bjdm (bjdm,zydm,bjmc,nj,tgxydm)");
			sql.append(" values(?,?,?,?,?) ");
			
			return dao.runUpdate(sql.toString(), new String[]{myForm.getBjdm(),myForm.getZydmbj(),myForm.getBjmc(),myForm.getNj(),myForm.getTgxydm()});
		}
		
		return false;
	}

	/** 
	 * @描述: 更新基础数据
	 * @作者：Qilm[工号：964]
	 * @日期：2013-12-9 上午11:38:44
	 * @param myForm
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean updJcsj(JcsjForm myForm) throws Exception {
		StringBuilder sql = new StringBuilder();
		
		// 选择类别(0：学院；1：专业；2：班级)
		String xzflg = myForm.getXzflg();

		boolean bolFlg = false;
		
		// 空/0：学院
		if ("".equals(xzflg) || "0".equals(xzflg)) {
			
			sql.append(" update ZXBZ_XXBMDM ");
			sql.append(" set bmdm='"+myForm.getXydm()+"', bmmc='"+myForm.getXymc()+"' ");
			sql.append(" where bmdm='" + myForm.getXydmold() +"' ");
			
			bolFlg = dao.runUpdate(sql.toString(), new String[]{});
			
			// 如果修改前后的代码一致 则无需判断
			if(bolFlg && !myForm.getXydm().equals(myForm.getXydmold())){

				sql = new StringBuilder();
				sql.append(" update BKS_ZYDM ");
				sql.append(" set bmdm='" + myForm.getXydm() +"' ");
				sql.append(" where bmdm='" + myForm.getXydmold() +"' ");				
				bolFlg = dao.runUpdate(sql.toString(), new String[]{});
				
			}
			
			// 1：专业
		} else if ("1".equals(xzflg)) {

			sql.append(" update BKS_ZYDM ");
			sql.append(" set zydm=?, zymc=?,bmdm=? ");
			sql.append(" where zydm=? ");

			bolFlg = dao.runUpdate(sql.toString(), new String[]{myForm.getZydm(),myForm.getZymc(),myForm.getXydmzy(),myForm.getZydmold()});
			
			// 如果修改前后的代码一致 则无需判断
			if(bolFlg && !myForm.getZydm().equals(myForm.getZydmold())){

				sql = new StringBuilder();
				sql.append(" update BKS_BJDM ");
				sql.append(" set zydm='" + myForm.getZydm() +"' ");
				sql.append(" where zydm='" + myForm.getZydmold() +"' ");			
				bolFlg = dao.runUpdate(sql.toString(), new String[]{});
			}
			
			//2：班级
		} else if ("2".equals(xzflg)) {

			sql.append(" update BKS_BJDM ");
			sql.append(" set bjdm=?, bjmc=? ,nj=?,zydm=?,tgxydm=? ");
			sql.append(" where bjdm=? ");
			
			bolFlg = dao.runUpdate(sql.toString(), new String[]{myForm.getBjdm(),myForm.getBjmc(),myForm.getNj(),myForm.getZydmbj(),myForm.getTgxydm(),myForm.getBjdmold()});
		}
		
		return bolFlg;
	}

	/**
	 * @描述: 删除基础数据
	 * @作者：Qilm[工号：964]
	 * @日期：2013-12-9 下午03:29:43
	 * @param xzflg
	 * @param split
	 * @return
	 * int 返回类型 
	 * @throws Exception  
	 */
	public int runDelete(String xzflg, String[] values) throws Exception {
		
		if (values == null || values.length == 0){
			logger.error("删除操作不能进行!");
			throw new NullPointerException();
		}
		
		StringBuilder sql = new StringBuilder();
		String tableName = "";
		String key = "";
		
		// 学院
		if("".equals(xzflg) || "0".equals(xzflg)){
			tableName = "ZXBZ_XXBMDM";
			key = "BMDM";
			// 1：专业
		}else if ("1".equals(xzflg)) {
			tableName = "BKS_ZYDM";
			key = "ZYDM";
			
			//2：班级
		}else if ("2".equals(xzflg)) {
			tableName = "BKS_BJDM";
			key = "BJDM";
		}
		
		sql.append("delete from ");
		sql.append(tableName);
		sql.append(" where ");
		sql.append("(");
		
		for (int i = 0 , n = values.length ; i < n ; i++){
			sql.append(key);
			sql.append("=?");
			
			if (i != n-1){
				sql.append(" or ");
			}
		}
		
		sql.append(")");
		logger.debug(sql);
		logger.debug(Arrays.toString(values));
		
		return dao.runDelete(sql.toString(), values);
		
	}

	/** 
	 * @描述: 部门类别列表
	 * @作者：Qilm[工号：964]
	 * @日期：2013-12-19 下午05:22:06
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getBmlbList() {
		// 获取部门类别列表
		StringBuilder sql = new StringBuilder();
		sql.append(" select bmlbdm , bmlbmc ");
		sql.append("   from dm_bmlb ");
		sql.append("  order by bmlbdm desc " );
		return dao.getListNotOut(sql.toString(), new String[]{});
	}

	/** 
	 * @描述: 获取学院/专业/班级列表
	 * @作者：Qilm[工号：964]
	 * @日期：2013-12-25 下午02:31:03
	 * @param model
	 * @param user
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getAllList(JcsjForm model, User user) {
		
		// 是否控制数据范围 sfkzSjfw:是否控制数据范围[0:控制;1:不控制]	
		String sfkzSjfw = model.getSfkzSjfw();
		
		// 是否在校生的数据集sfzx:是否在校范围[0:在校view_njxyzybj;1:不在校view_njxyzybj_all]
		String sfzx = model.getSfzx();
		
		//选择FLG(0：学院列表；1：专业列表；2：班级列表  3：年级列表) 
		String xzFlg = model.getXzflg();
		
		String tableName = "view_njxyzybj";
		if("1".equals(sfzx)){
			tableName = "view_njxyzybj_all";
		}	    
			
		// 获取部门类别列表
		StringBuilder sql = new StringBuilder();
		sql.append(" select ");
		// 学院列表
		if("0".equals(xzFlg)){
			
			sql.append(" distinct xydm, xymc ");
			
			//专业列表
		}else if("1".equals(xzFlg)){

			sql.append(" distinct zydm, zymc ");
			
			// 班级列表 
		}else if("2".equals(xzFlg)){

			sql.append(" distinct bjdm, bjmc ");
			
			// 年级列表
		}else if("3".equals(xzFlg)){
			
			sql.append(" distinct nj ");			
		}
		
		sql.append("   from " + tableName +" a ");
		sql.append(" where 1=1 ");
		// 年级条件
		if(model.getNj()!=null && !"".equals(model.getNj())){
			sql.append(" and nj = '" + model.getNj() +"'");
		}
		// 学院代码条件
		if(model.getXydm()!=null && !"".equals(model.getXydm())){
			sql.append(" and xydm = '" + model.getXydm() +"'");
		}
		// 专业代码条件
		if(model.getZydm()!=null && !"".equals(model.getZydm())){
			sql.append(" and zydm = '" + model.getZydm() +"'");
		}
		// 班级代码条件
		if(model.getBjdm()!=null && !"".equals(model.getBjdm())){
			sql.append(" and bjdm = '" + model.getBjdm() +"'");
		}
		
		// 控制用户的数据范围
		if("0".equals(sfkzSjfw)){
			
			String userStatus = user.getUserStatus();
			String userName = user.getUserName();
			String userDep = user.getUserDep();
			if ("jd".equalsIgnoreCase(userStatus)) {
				// 兼得
				sql.append(" and (exists(select 1 from fdybjb b ");
				sql.append(" where a.bjdm = b.bjdm and b.zgh = '"+userName+"' ) ");
				sql.append(" or exists(select 1 from bzrbbb b ");
				sql.append(" where a.bjdm = b.bjdm and b.zgh = '"+userName+"' )) ");
				
			} else if ("fdy".equalsIgnoreCase(userStatus)) {
				// 辅导员
				sql.append(" and exists(select 1 from fdybjb b ");
				sql.append(" where a.bjdm = b.bjdm and b.zgh = '"+userName+"' ) ");
			} else if ("bzr".equalsIgnoreCase(userStatus)) {
				// 班主任
				sql.append(" and exists(select 1 from bzrbbb b ");
				sql.append(" where a.bjdm = b.bjdm and b.zgh = '"+userName+"' ) ");
			} else if ("xy".equalsIgnoreCase(userStatus)) {
				// 学院
				sql.append(" and a.xydm = '"+userDep+"' ");
			} else if ("stu".equalsIgnoreCase(userStatus)) {
				// 学生
				sql.append(" and exists(select 1 from view_xsjbxx b ");
				sql.append(" where a.bjdm = b.bjdm and b.xh = '"+userName+"')");
			}
		}
		// 排序
		sql.append("  order by " );

		// 学院列表
		if("".equals(xzFlg) || "0".equals(xzFlg)){
			
			sql.append(" xydm, xymc ");
			
			//专业列表
		}else if("1".equals(xzFlg)){

			sql.append(" zydm, zymc ");
			
			// 班级列表 
		}else if("2".equals(xzFlg)){

			sql.append(" bjdm, bjmc ");
			
			// 年级列表
		}else if("3".equals(xzFlg)){
			
			sql.append(" nj ");			
		}
		

		return dao.getListNotOut(sql.toString(), new String[]{});
	}
}
