package xgxt.xsxx.comm.sjy.jcsjcsh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommDAO;
import xgxt.form.User;
import xgxt.xsxx.comm.sjy.XsxxSjyDAO;
import xgxt.xsxx.comm.sjy.jcsjsz.SjyJcsjszService;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 学生信息_数据源_基础数据初始化_DAO类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author 伟大的骆
 * @version 1.0
 */

public class SjyJcsjcshDAO extends XsxxSjyDAO {

	// =====================从接口同步数据======================================
	/**
	 * 同步部门信息
	 * 
	 * @throws Exception
	 * 
	 * @author 伟大的骆
	 */
	public boolean tbBmInfo() throws Exception {
		DAO dao = DAO.getInstance();
		return dao.runProcuder("{call pro_xg_jcsj_bmtb()}", new String[] {});
	}

	/**
	 * 同步专业信息
	 * 
	 * @throws Exception
	 * 
	 * @author 伟大的骆
	 */
	public boolean tbZyInfo() throws Exception {
		DAO dao = DAO.getInstance();
		return dao.runProcuder("{call pro_xg_jcsj_zytb()}", new String[] {});
	}

	/**
	 * 同步班级信息
	 * 
	 * @throws Exception
	 * 
	 * @author 伟大的骆
	 */
	public boolean tbBjInfo() throws Exception {
		DAO dao = DAO.getInstance();
		return dao.runProcuder("{call pro_xg_jcsj_bjtb()}", new String[] {});
	}
	
	/**
	 * 同步学生基本信息
	 * 
	 * @throws Exception
	 * 
	 * @author 伟大的骆
	 */
	public boolean tbStuInfo() throws Exception {
		DAO dao = DAO.getInstance();
		return dao.runProcuder("{call pro_xg_jcsj_stutb()}", new String[] {});
	}
	// =====================从接口同步数据 end======================================

	// =====================向正式表提交数据======================================
	/**
	 * 整体提交部门信息
	 * 
	 * @throws Exception
	 * 
	 * @author 伟大的骆
	 */
	public boolean allSubmitByXy() throws Exception {
		DAO dao = DAO.getInstance();
		return dao.runProcuder("{call pro_xg_jcsj_bmInfo_tj()}",
				new String[] {});
	}

	/**
	 * 勾选提交部门信息
	 * 
	 * @throws Exception
	 * 
	 * @author 伟大的骆
	 */
	public boolean submitXyInfo(SjyJcsjcshForm model) throws Exception {
		DAO dao = DAO.getInstance();
		
		String[] pk = model.getPrimarykey_checkVal();
		
		 //清空临时表
		StringBuilder del = new StringBuilder();
		del.append("delete from xg_jcsj_bmdmb ");
		del.append("where 1 = 1 ");
		del.append("and (");
		for(int i=0;i<pk.length;i++){
			if(i!=0){
				del.append(" or ");
			}
			del.append(" bmdm = ? ");
		}
		del.append(")");
		
		boolean flag = dao.runUpdate(del.toString(), pk);
		
		if(flag){
			//同步接口数据(具体数据可以根据实际情况修改)
			StringBuilder sql = new StringBuilder();
			sql.append("insert into xg_jcsj_bmdmb ");
			sql.append("select bmdm, bmmc, bmpyjc, bmfdm, bmjb, bmlb, bz ");
			sql.append("from xg_jcsj_bmdmb_temp ");
			sql.append("where (bmlb = '1' or bmlb = '5') ");
			sql.append("and (");
			for(int i=0;i<pk.length;i++){
				if(i!=0){
					sql.append(" or ");
				}
				sql.append(" bmdm = ? ");
			}
			sql.append(")");
			
			flag = dao.runUpdate(sql.toString(), pk);
		}
		  
		return flag;
	}
	
	/**
	 * 整体提交专业信息
	 * 
	 * @throws Exception
	 * 
	 * @author 伟大的骆
	 */
	public boolean allSubmitByZy() throws Exception {
		DAO dao = DAO.getInstance();
		return dao.runProcuder("{call pro_xg_jcsj_zyInfo_tj()}",
				new String[] {});
	}
	
	/**
	 * 勾选提交专业信息
	 * 
	 * @throws Exception
	 * 
	 * @author 伟大的骆
	 */
	public boolean submitZyInfo(SjyJcsjcshForm model) throws Exception {
		DAO dao = DAO.getInstance();
		
		String[] pk = model.getPrimarykey_checkVal();
		
		StringBuilder addSql = new StringBuilder();
		addSql.append("insert into xg_jcsj_zydmb ");
		addSql.append("select zydm,bmdm,xkmldm,zymc,zyjc,");
		addSql.append("zyywmc,jlny,gjzydm,ksdm,ywxtbh,zhgxsj ");
		addSql.append("from xg_jcsj_zydmb_temp a ");
		addSql.append("where exists (select 1 from xg_jcsj_bmdmb b where a.bmdm = b.bmdm) ");
		addSql.append("and not exists(select 1 from xg_jcsj_zydmb c where a.zydm = c.zydm)  ");
		addSql.append("and (");
		for(int i=0;i<pk.length;i++){
			if(i!=0){
				addSql.append(" or ");
			}
			addSql.append(" a.zydm = ? ");
		}
		addSql.append(")");
		
		boolean add = dao.runUpdate(addSql.toString(), pk);

		StringBuilder editSql = new StringBuilder();
		editSql.append("update xg_jcsj_zydmb a ");
		editSql.append("set a.zymc = (select b.zymc from xg_jcsj_zydmb_temp b where a.zydm = b.zydm ),");
		editSql.append("	a.bmdm = (select b.bmdm from xg_jcsj_zydmb_temp b where a.zydm = b.zydm )");
		editSql.append("where exists(select 1 from xg_jcsj_zydmb_temp c where a.zydm = c.zydm)  ");
		editSql.append("and (");
		for(int i=0;i<pk.length;i++){
			if(i!=0){
				editSql.append(" or ");
			}
			editSql.append(" a.zydm = ? ");
		}
		editSql.append(")");
		
		boolean edit = dao.runUpdate(editSql.toString(), pk);
		
		boolean flag = add && edit ? true : false;

		return flag;
	}
	
	/**
	 * 整体提交班级信息
	 * 
	 * @throws Exception
	 * 
	 * @author 伟大的骆
	 */
	public boolean allSubmitByBj() throws Exception {
		DAO dao = DAO.getInstance();
		return dao.runProcuder("{call pro_xg_jcsj_bjInfo_tj()}",
				new String[] {});
	}
	
	/**
	 * 整体提交学生信息
	 * 
	 * @throws Exception
	 * 
	 * @author 伟大的骆
	 */
	public boolean allSubmitByStu() throws Exception {
		DAO dao = DAO.getInstance();
		return dao.runProcuder("{call pro_xg_jcsj_stuInfo_tj()}",
				new String[] {});
	}
	
	/**
	 * 勾选提交班级信息
	 * 
	 * @throws Exception
	 * 
	 * @author 伟大的骆
	 */
	public boolean submitBjInfo(SjyJcsjcshForm model) throws Exception {
		
		DAO dao = DAO.getInstance();
		
		String[] pk = model.getPrimarykey_checkVal();
		
		StringBuilder addSql = new StringBuilder();
		addSql.append("insert into xg_jcsj_bjdmb ");
		addSql.append("select BJDM, JZGH, ZYDM, BMDM, BJMC,");
		addSql.append("NJ, YWXTBH, ZHGXSJ, BJJC, ZCRS ");
		addSql.append("from xg_jcsj_bjdmb_temp a ");
		addSql.append("where exists (select 1 from xg_jcsj_zydmb b where a.zydm = b.zydm) ");
		addSql.append("and not exists(select 1 from xg_jcsj_bjdmb c where a.bjdm = c.bjdm)  ");
		addSql.append("and (");
		for(int i=0;i<pk.length;i++){
			if(i!=0){
				addSql.append(" or ");
			}
			addSql.append(" a.bjdm = ? ");
		}
		addSql.append(")");
		
		boolean add = dao.runUpdate(addSql.toString(), pk);

		StringBuilder editSql = new StringBuilder();
		editSql.append("update xg_jcsj_bjdmb a ");
		editSql.append("set a.bjmc = (select b.bjmc from xg_jcsj_bjdmb_temp b where a.bjdm = b.bjdm ),");
		editSql.append("	a.zydm = (select b.zydm from xg_jcsj_bjdmb_temp b where a.bjdm = b.bjdm )");
		editSql.append("where exists(select 1 from xg_jcsj_bjdmb_temp c where a.bjdm = c.bjdm)  ");
		editSql.append("and (");
		for(int i=0;i<pk.length;i++){
			if(i!=0){
				editSql.append(" or ");
			}
			editSql.append(" a.bjdm = ? ");
		}
		editSql.append(")");
		
		boolean edit = dao.runUpdate(editSql.toString(), pk);
		
		boolean flag = add && edit ? true : false;

		return flag;
	
	}
	
	/**
	 * 勾选提交学生基本信息
	 * 
	 * @throws Exception
	 * 
	 * @author 伟大的骆
	 */
	public boolean submitStuInfo(SjyJcsjcshForm model) throws Exception {
		
		DAO dao = DAO.getInstance();
		
		String[] pk = model.getPrimarykey_checkVal();
		
		StringBuilder addSql = new StringBuilder();
		
		// 将学工系统中不存在的学生导入到系统中
		addSql.append("insert into xsxxb ");
		addSql.append("(bjdm,nj,xh,byny,bz,csrq,cym,dzyx,xb, ");
		addSql.append("fdyxm,xm,mz,zzmm,sfzh,pycc,syd,xz,sfzc, ");
		addSql.append("sfzd,sfzx,sfbys,sfyby,rxrq,xjztm,lxdh, ");
		addSql.append("sjhm,ssbh,qsdh,qqhm,yhdm,yhkh,xmpy, ");
		addSql.append("sg,tz,tc,kslb,rxfs,pyfs,hkszd,jg,kh, ");
		addSql.append("ksh,nfby) ");

		addSql.append("select bjdm,nj,xh,byny,bz,csrq,cym,dzyx, ");
		addSql.append("xb,fdyxm,xm,mz,zzmm,sfzh,pycc,syd,xz, ");
		addSql.append("sfzc,sfzd,sfzx,sfbys,sfyby,rxrq,xjztm, ");
		addSql.append("lxdh,sjhm,ssbh,qsdh,qqhm,yhdm,yhkh,xmpy, ");
		addSql.append("sg,tz,tc,kslb,rxfs,pyfs,hkszd,jg,kh, ");
		addSql.append("ksh,nfby from xg_jcsj_xsxxb_temp a ");
		addSql.append("where not exists(select 1 from xsxxb b where a.xh=b.xh)");
		
		addSql.append("and (");
		for(int i=0;i<pk.length;i++){
			if(i!=0){
				addSql.append(" or ");
			}
			addSql.append(" a.xh = ? ");
		}
		addSql.append(")");
		
		boolean add = dao.runUpdate(addSql.toString(), pk);

		String tableName = "xg_xsxx_zdszb";
		String query = " where xgwz = '否' ";
		
		SjyJcsjszService service = new SjyJcsjszService();
		
		// 字段设置
		List<HashMap<String, String>> zdszList = getRsList(tableName, query,
				new String[] {}, new String[] { "zd" }, "");
		
		ArrayList<String> sqlList = new ArrayList<String>();
		
		if (zdszList != null && zdszList.size() > 0) {
			
			// 不执行更新的字段
			String[] notUpdate = new String[] { "nj", "xydm", "zydm" };
			// 不执行更新的字段
			String[] addRecode = new String[] { "zzmm" };
			
			for (int i = 0; i < zdszList.size(); i++) {
				
				HashMap<String, String> map = zdszList.get(i);	
				String zd = map.get("zd");
				
				//非不更新字段，执行更新操作
				if(!service.isExistInArr(notUpdate, zd)){
					StringBuilder editSql = new StringBuilder();
					editSql.append(" update xsxxb a set ");
					editSql.append(zd);
					editSql.append(" = (");
					editSql.append(" select ");
					editSql.append(zd);
					editSql.append(" from xg_jcsj_xsxxb_temp b ");
					editSql.append(" where a.xh = b.xh) ");
					editSql.append(" where exists ( ");
					editSql.append(" select 1 from xg_jcsj_xsxxb_temp b ");
					editSql.append(" where a.xh = b.xh ");
					editSql.append(service.getQuery(zd));
					editSql.append(" ) ");
					editSql.append("and (");
					for(int j=0;j<pk.length;j++){
						if(j!=0){
							editSql.append(" or ");
						}
						editSql.append(" a.xh = '" + pk[j] + "' ");
					}
					editSql.append(")");
					
					sqlList.add(editSql.toString());
				}
				
				// 关联内容字段，在学工中构建信息记录
				if (service.isExistInArr(addRecode, zd)) {
					String[] gnmkSql = service.getAddGnmkSql(zd,pk);
					if (gnmkSql != null && gnmkSql.length > 0) {
						for (int j = 0; j < gnmkSql.length; j++) {
							sqlList.add(gnmkSql[j]);
						}
					}	
				}
			}	
		}
		
		boolean edit = saveArrDate(sqlList.toArray(new String[]{}));

		boolean flag = add && edit ? true : false;

		return flag;
	}
	
	// =====================向正式表提交数据end======================================

	// =====================规则相关======================================
	/**
	 * 执行规则
	 * 
	 * @throws Exception
	 * 
	 * @author 伟大的骆
	 */
	public boolean doRule() throws Exception {
		DAO dao = DAO.getInstance();
		return dao.runProcuder("{call pro_xg_jcsj_updateByRule()}",
				new String[] {});
	}

	// =====================规则相关 end======================================
	
	/**
	 * 获得部门类别
	 * 
	 * @throws Exception
	 * 
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getBmlb() {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		sql.append(" select distinct bmlb dm, ruledm from (select a.bmlb, b.zdh, ");
		sql.append(" decode(b.zdh, '', a.bmlb, b.zdh) ruledm ");
		sql.append(" from (select distinct bmlb from XG_JCSJ_BMDMB_TEMP) a ");
		sql.append(" left join (select zdq, zdh from xg_jcsj_gzzdb ");
		sql.append(" where lyb = 'xg_jcsj_bmdmb' and zd = 'bmdmb_bmlb') b ");
		sql.append(" on a.bmlb = b.zdq) order by dm");

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] {}, new String[] { "dm", "ruledm" });

		return list;

	}

	/**
	 * 获得院系信息列表
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getXyList() throws Exception {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		sql.append(" select bmdm,bmmc from xg_jcsj_bmdmb where bmlb = '5'");

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] {}, new String[] { "bmdm", "bmmc" });

		return list;

	}
	
	/**
	 * 获得专业信息列表
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getZyList() throws Exception {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		sql.append(" select zydm,zymc from xg_jcsj_zydmb");

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] {}, new String[] { "zydm", "zymc" });

		return list;

	}
	
	/**
	 * 获得班级信息列表
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getBjList() throws Exception {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		sql.append(" select bjdm,bjmc from xg_jcsj_bjdmb");

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] {}, new String[] { "bjdm", "bjmc" });

		return list;

	}
	
	/**
	 * 获得学籍状态列表
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getXjztList() throws Exception {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		sql.append(" select zxdm,zxdmmc from dm_zju_xjzt");

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] {}, new String[] { "zxdm", "zxdmmc" });

		return list;

	}
	
	/**
	 * 获得行政区块列表
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getXzqkList() throws Exception {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		sql.append(" select qxdm,qxmc from dmk_qx");

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] {}, new String[] { "qxdm", "qxmc" });

		return list;

	}
	
	/**
	 * 获得上级院系
	 * 
	 * @throws Exception
	 * 
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getBmdm() {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		sql.append(" select distinct bmdm dm, ruledm from (select a.bmdm, b.zdh, ");
		sql.append(" decode(b.zdh, '', a.bmdm, b.zdh) ruledm ");
		sql.append(" from (select distinct bmdm from xg_jcsj_zydmb_temp) a ");
		sql.append(" left join (select zdq, zdh from xg_jcsj_gzzdb ");
		sql.append(" where lyb = 'xg_jcsj_zydmb' and zd = 'zydmb_bmdm') b ");
		sql.append(" on a.bmdm = b.zdq) order by dm");
		
		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] {}, new String[] { "dm", "ruledm" });

		return list;

	}
	
	/**
	 * 获得所属专业
	 * 
	 * @throws Exception
	 * 
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getZydm() {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		sql.append(" select distinct zydm dm, ruledm from (select a.zydm, b.zdh, ");
		sql.append(" decode(b.zdh, '', a.zydm, b.zdh) ruledm ");
		sql.append(" from (select distinct zydm from xg_jcsj_bjdmb_temp) a ");
		sql.append(" left join (select zdq, zdh from xg_jcsj_gzzdb ");
		sql.append(" where lyb = 'xg_jcsj_bjdmb' and zd = 'bjdmb_zydm') b ");
		sql.append(" on a.zydm = b.zdq) order by dm");
		
		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] {}, new String[] { "dm", "ruledm" });

		return list;

	}
	
	/**
	 * 获得部门类别
	 * 
	 * @throws Exception
	 * 
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getStuXb() {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		sql.append(" select distinct nvl(xb,'空') dm, ruledm from (select a.xb, b.zdh, ");
		sql.append(" decode(b.zdh, '', a.xb, b.zdh) ruledm ");
		sql.append(" from (select distinct nvl(xb, '空') xb from xg_jcsj_xsxxb_temp) a ");
		sql.append(" left join (select zdq, zdh from xg_jcsj_gzzdb ");
		sql.append(" where lyb = 'xsxxb' and zd = 'xsxxb_xb') b ");
		sql.append(" on a.xb = b.zdq) order by dm");

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] {}, new String[] { "dm", "ruledm" });

		return list;

	}
	
	/**
	 * 获得所属班级
	 * 
	 * @throws Exception
	 * 
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getBjdm() {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		sql.append(" select distinct bjdm dm, ruledm from (select a.bjdm, b.zdh, ");
		sql.append(" decode(b.zdh, '', a.bjdm, b.zdh) ruledm ");
		sql.append(" from (select distinct bjdm from xg_jcsj_xsxxb_temp) a ");
		sql.append(" left join (select zdq, zdh from xg_jcsj_gzzdb ");
		sql.append(" where lyb = 'xsxxb' and zd = 'xsxxb_bjdm') b ");
		sql.append(" on a.bjdm = b.zdq) order by dm");
		
		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] {}, new String[] { "dm", "ruledm" });

		return list;

	}
	
	/**
	 * 获得学籍状态
	 * 
	 * @throws Exception
	 * 
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getXjzt() {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		sql.append(" select distinct nvl(xjztm,'空') dm, ruledm from (select a.xjztm, b.zdh, ");
		sql.append(" decode(b.zdh, '', a.xjztm, b.zdh) ruledm ");
		sql.append(" from (select distinct nvl(xjztm, '空') xjztm from xg_jcsj_xsxxb_temp) a ");
		sql.append(" left join (select zdq, zdh from xg_jcsj_gzzdb ");
		sql.append(" where lyb = 'xsxxb' and zd = 'xsxxb_xjztm') b ");
		sql.append(" on a.xjztm = b.zdq) order by dm");
		
		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] {}, new String[] { "dm", "ruledm" });

		return list;

	}
	
	/**
	 * 获得行政区块
	 * 
	 * @throws Exception
	 * 
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getXzqk() {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();

		sql.append("select a.ssx,a.sheng,a.shengmc,a.shi,a.shimc,a.xian, ");
		sql.append("(select c.qxmc from dmk_qx c where b.zdsheng = c.qxdm) zdsheng, ");
		sql.append("(select c.qxmc from dmk_qx c where b.zdshi = c.qxdm) zdshi, ");
		sql.append("(select c.qxmc from dmk_qx c where b.zdxian = c.qxdm) zdxian, ");
		sql.append("a.xianmc,b.zdq,b.zdh from (select a.ssx,a.sheng,a.shi,a.xian, ");
		sql.append("(select b.qxmc from dmk_qx b where a.sheng = b.qxdm) shengmc, ");
	    sql.append("(select b.qxmc from dmk_qx b where a.shi = b.qxdm) shimc, ");          
	    sql.append("(select b.qxmc from dmk_qx b where a.xian = b.qxdm) xianmc ");
	    sql.append("from xg_view_jcsj_shx a) a ");
	    sql.append("left join ");
	    
	    sql.append("(select zdq,zdh,substr(newSsx, 1, 6) zdsheng,substr(newSsx, 7, 6) zdshi, ");
	    sql.append("substr(newSsx, 13, 6) zdxian from (select distinct zdq,  ");
	    sql.append("zdh, replace(zdh, '/', '') newSsx from (select b.zdq, b.zdh ");
	    sql.append("from xg_jcsj_gzzdb b where b.zd = 'xsxxb_xzqkm' ");
	    sql.append("and lyb = 'xsxxb'))) b on a.ssx = b.zdq");
		
		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] {}, new String[] { "ssx", "sheng", "shengmc",
						"shi", "shimc", "xian", "xianmc", "zdq", "zdh",
						"zdsheng", "zdshi", "zdxian" });

		return list;

	}
	//=====================以下用于页面列表加载========================================
	/**
	 * 根据录入获取部门列表
	 * 
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getBmOption(String bmmc) {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		sql.append(" select distinct bmdm dm,bmmc mc from xg_jcsj_bmdmb ");
		sql.append(" where 1 = 1 ");
		sql.append(" and bmlb = '5' ");
		sql.append(Base.isNull(bmmc) ? "" : " and  bmmc like '" + bmmc + "%' ");
		sql.append(" and rownum <=30 ");

		return dao.getList(sql.toString(), new String[] {}, new String[] {
				"dm", "mc" });
	}
	
	/**
	 * 根据录入获取年级列表
	 * 
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getNjOption(String nj) {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		sql.append(" select distinct nj dm,nj mc from xg_jcsj_bjdmb_temp ");
		sql.append(" where 1 = 1 ");
		sql.append(Base.isNull(nj) ? "" : " and  nj like '" + nj + "%' ");
		sql.append(" and rownum <=20 ");
		sql.append(" order by dm ");
		
		return dao.getList(sql.toString(), new String[] {}, new String[] {
				"dm", "mc" });
	}
	
	/**
	 * 根据录入获取专业列表
	 * 
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getZyOption(String zymc,
			String[] searchTj) {

		DAO dao = DAO.getInstance();
		
		// 年级
		String nj = searchTj[0];
		// 学院
		String xy = searchTj[1];
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select distinct zydm dm,zymc mc from xg_jcsj_zydmb ");
		sql.append(" where 1 = 1 ");
		sql.append(Base.isNull(xy) ? "" : " and bmdm = '" + xy + "' ");
		sql.append(Base.isNull(zymc) ? "" : " and  zymc like '" + zymc + "%' ");
		sql.append(" and rownum <=20 ");

		return dao.getList(sql.toString(), new String[] {}, new String[] {
				"dm", "mc" });
	}
	
	/**
	 * 根据录入获取班级列表
	 * 
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getBjOption(String bjmc,String[] searchTj) {

		DAO dao = DAO.getInstance();

		// 年级
		String nj = searchTj[0];
		// 学院
		String xy = searchTj[1];
		// 学院
		String zy = searchTj[2];
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select distinct bjdm dm,bjmc mc from xg_jcsj_bjdmb a");
		sql.append(" where 1 = 1 ");
		sql.append(Base.isNull(nj) ? "" : " and nj = '" + nj + "' ");
		sql.append(Base.isNull(zy) ? "" : " and zydm = '" + zy + "' ");
		sql.append(Base.isNull(xy) ? "" : " and exists (select 1 from xg_jcsj_zydmb b where a.zydm = b.zydm and b.bmdm='"+xy+"')");
		
		sql.append(Base.isNull(bjmc) ? "" : " and  bjmc like '" + bjmc + "%' ");
		sql.append(" and rownum <=20 ");
		sql.append(" order by a.bjdm ");

		return dao.getList(sql.toString(), new String[] {}, new String[] {
				"dm", "mc" });
	}
	
	/**
	 * 根据录入获取学籍列表
	 * 
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getXjOption(String xjzt) {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		sql.append(" select distinct zxdmmc dm,zxdmmc mc from dm_zju_xjzt ");
		sql.append(" where 1 = 1 ");
		sql.append(Base.isNull(xjzt) ? "" : " and  zxdmmc like '" + xjzt + "%' ");
		sql.append(" and rownum <=20 ");

		return dao.getList(sql.toString(), new String[] {}, new String[] {
				"dm", "mc" });
	}
	
	/**
	 * 根据录入获取班级列表
	 * 
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getXzqkOption(String xzqmc, String lx,
			String[] searchTj) {

		DAO dao = DAO.getInstance();

		// 省
		String sheng = searchTj[0];
		// 市
		String shi = searchTj[1];
		// 县
		String xian = searchTj[2];

		StringBuilder sql = new StringBuilder();

		//省
		if("sheng".equalsIgnoreCase(lx)){
			sql.append("select qxdm dm,qxmc mc from dmk_qx ");
			sql.append("where 1 = 1 ");
			sql.append("and qxdm like '%0000' ");
			sql.append(Base.isNull(xzqmc) ? "" : " and  qxmc like '" + xzqmc + "%' ");
			sql.append("order by qxdm ");
		}
		// 市
		else if ("shi".equalsIgnoreCase(lx)) {
			
			String dm = sheng.substring(0, 2);
			sql.append("select qxdm dm,qxmc mc from dmk_qx ");
			sql.append("where 1 = 1 ");
			sql.append("and qxdm like '" + dm + "%' ");
			sql.append("and qxdm <> '" + sheng + "' ");
			sql.append("and qxdm like '__%00' ");
			sql.append(Base.isNull(xzqmc) ? "" : " and  qxmc like '" + xzqmc + "%' ");
			sql.append("order by qxdm ");
		}
		//县
		else if ("xian".equalsIgnoreCase(lx)) {
			
			String dm = shi.substring(0, 4);
			sql.append("select qxdm dm,qxmc mc from dmk_qx ");
			sql.append("where 1 = 1 ");
			sql.append("and qxdm like '" + dm + "%' ");
			sql.append("and qxdm <> '" + shi + "' ");
			sql.append("and qxdm like '______' ");
			sql.append(Base.isNull(xzqmc) ? "" : " and  qxmc like '" + xzqmc + "%' ");
			sql.append("order by qxdm ");
		}
		
		return dao.getList(sql.toString(), new String[] {}, new String[] {
				"dm", "mc" });
	}
}
