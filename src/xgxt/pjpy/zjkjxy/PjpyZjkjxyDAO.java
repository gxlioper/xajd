package xgxt.pjpy.zjkjxy;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import common.GlobalsVariable;

import xgxt.DAO.DAO;
import xgxt.pjpy.PjpyTyDAO;
import xgxt.pjpy.zjcm.cssz.PjpyZjcmCsszModel;
import xgxt.pjpy.zjjd.JxjpdxxModel;
import xgxt.utils.Pages;
import xgxt.utils.String.StringUtils;

public class PjpyZjkjxyDAO extends PjpyTyDAO{
	private final static String BZ_XY = "xy";//学院部门类别
	private final static String BZ_ZY = "zy";//专业部门类别
	private final static String BZ_BJ = "bj";//班级部门类别
	//修改奖学金比例设置SQL
	private final StringBuffer UPDATE_JXJRS_SQL = new StringBuffer("update xyjxjrs set ")
	                      .append("jxjbl=?,cpfw=? ")
	                      .append("where key=? and xn=? and xqdm=? and nd=? and jxjdm=?")
	                      .append(" and bmlb=?||'dm'");
	ArrayList<String> value = new ArrayList<String>();
	
	private void appendUpdateWhereSql(PjpyZjcmCsszModel model, StringBuffer sql) {
		if (BZ_XY.equalsIgnoreCase(model.getCpfw())) {
			if (StringUtils.isNotNull(model.getXydm())) {
				sql.append(" and bmdm = '");
				sql.append(model.getXydm());
				sql.append("'");
			}
			if (StringUtils.isNotNull(model.getNj())) {
				sql.append(" and nj = '");
				sql.append(model.getNj());
				sql.append("'");
			}
		} else if (BZ_ZY.equalsIgnoreCase(model.getCpfw())) {
			if (StringUtils.isNotNull(model.getNj())) {
				sql.append(" and nj = '");
				sql.append(model.getNj());
				sql.append("'");
			}
			if (StringUtils.isNotNull(model.getZydm())) {
				sql.append(" and bmdm ='");
				sql.append(model.getZydm());
				sql.append("'");
			}
			if (StringUtils.isNotNull(model.getXydm())) {
				sql.append(" and bmdm in (select distinct zydm from view_njxyzybj where xydm='");
				sql.append(model.getXydm());
				sql.append("')");
			}
			
		} else if (BZ_BJ.equalsIgnoreCase(model.getCpfw())) {
			if (StringUtils.isNotNull(model.getNj())) {
				sql.append(" and nj = '");
				sql.append(model.getNj());
				sql.append("'");
			}
			if (StringUtils.isNotNull(model.getBjdm())) {
				sql.append(" and bmdm='");
				sql.append(model.getBjdm());
				sql.append("'");
			}
			if (StringUtils.isNotNull(model.getXydm())) {
				sql.append(" and bmdm in (select distinct bjdm from view_njxyzybj where xydm='");
				sql.append(model.getXydm());
				sql.append("')");
			}
			if (StringUtils.isNotNull(model.getZydm())) {
				sql.append(" and bmdm in (select distinct bjdm from view_njxyzybj where zydm='");
				sql.append(model.getZydm());
				sql.append("')");
			}
			
		}
	}
	
	public StringBuilder appendWhereSql(PjpyZjkjxyActionForm model){
		StringBuilder sb = new StringBuilder(" where 1=1 ");
		String nd = model.getQueryequals_nd();
		String xn = model.getQueryequals_xn();
		String xq = model.getQueryequals_xq();
		String nj = model.getQueryequals_nj();
		String xydm = model.getQueryequals_xydm();
		String zydm = model.getQueryequals_zydm();
		String bjdm = model.getQueryequals_bjdm();
		String xh = model.getQuerylike_xh();
		String xm = model.getQuerylike_xm();
		String jxjdm = model.getQueryequals_jxjdm();
		String rychdm = model.getQueryequals_rychdm();
		String sfksq = model.getQueryequals_sfksq();
		String xxsh = model.getXxsh();
		String key = model.getKey();
		
		value = new ArrayList<String>();
		if(!StringUtils.isNull(nd)){
			sb.append( " and nd=?");
			value.add(nd);
		}
		if(!StringUtils.isNull(xn)){
			sb.append( " and xn=?");
			value.add(xn);
		}
		if(!StringUtils.isNull(xq)){
			sb.append( " and xq=?");
			value.add(xq);
		}
		if(!StringUtils.isNull(nj)){
			sb.append( " and nj=?");
			value.add(nj);
		}
		if(!StringUtils.isNull(xydm)){
			sb.append( " and xydm=?");
			value.add(xydm);
		}
		if(!StringUtils.isNull(zydm)){
			sb.append( " and zydm=?");
			value.add(zydm);
		}
		if(!StringUtils.isNull(bjdm)){
			sb.append( " and bjdm=?");
			value.add(bjdm);
		}
		if(!StringUtils.isNull(jxjdm)){
			sb.append( " and xmdm=?");
			value.add(jxjdm);
		}
		if(!StringUtils.isNull(rychdm)){
			sb.append( " and xmdm=?");
			value.add(rychdm);
		}
		if(!StringUtils.isNull(sfksq)){
			sb.append( " and sfksq=?");
			value.add(sfksq);
		}
		if(!StringUtils.isNull(key)){
			sb.append( " and key=?");
			value.add(key);
		}
		if(!StringUtils.isNull(xxsh)){
			sb.append( " and xxsh=?");
			value.add(xxsh);
		}
		if(!StringUtils.isNull(xh)){
			sb.append( " and xh like '%'||?||'%'");
			value.add(xh);
		}
		if(!StringUtils.isNull(xm)){
			sb.append( " and xm like '%'||?||'%'");
			value.add(xm);
		}
		return sb;
	}
	
	/**
	 * 奖学金人数比例批量设置
	 * @param model
	 * @return boolean
	 * @throws Exception
	 */
	public boolean updateJxjrs(PjpyZjcmCsszModel model) throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append(UPDATE_JXJRS_SQL);
		
		DAO dao = DAO.getInstance();
		appendUpdateWhereSql(model, sql);
		return  dao.runUpdate(sql.toString(), new String[] { model.getJxjbl(),
				model.getCpfw(),
				model.getKey(), model.getXn(), model.getXq(),
				model.getNd(), model.getJxjdm(), model.getCpfw() });
	}

	/**
	 * 获取当前调整人数的部门类别
	 * @param form
	 * @return String
	 * */
	public String getTzrsbm(PjpyZjkjxyActionForm form){
		String bmlb = "";//部门类别
		DAO dao = DAO.getInstance();
		String[] pkValues = form.getCheckVal();
		String pk = form.getPk();
		
		if(pkValues != null && pkValues.length>0){
			bmlb = dao.getOneValue("xyjxjrs", "cpfw", pk, pkValues[0]);
		}
		
		return bmlb;
	}
	
	/**
	 * 获取班级所属专业人数
	 * @param form
	 * @return int
	 * */
	public int getBjsszyrs(PjpyZjkjxyActionForm form){
		DAO dao = DAO.getInstance();
		int zyrs = 0;
		String pk = form.getPk();
		String[] pkValues = form.getCheckVal();
		
		if(pkValues != null && pkValues.length>0){
			String bjdm = dao.getOneValue("xyjxjrs", "bmdm", pk, pkValues[0]);
			String sql = StringUtils.joinStr("select jxjrs from xyjxjrs where ",
					     "bmdm=(select distinct zydm from view_njxyzybj b where b.bjdm=?)" +
					     " and bmlb='zydm' ",//and bmlb='zy' 
					     "and nj=? and jxjdm=? and xn=? and nd=? and xqdm=? and key=?");  
			
			String zyNum = dao.getOneRs(sql, 
					new String[]{bjdm,form.getNj(), form.getJxjdm(), form.getXn(), form.getNd(), form.getXq(), form.getKey()}, 
					"jxjrs");
			zyrs = StringUtils.isNull(zyNum) ? -1 : Integer.parseInt(zyNum);
		}
		return zyrs;
	}
	
	/**
	 * 没有调整人数的班级的总人数
	 * @param form
	 * @return int
	 * */
	public int getWtjbjrs(PjpyZjkjxyActionForm form){
		DAO dao = DAO.getInstance();
		String pk = form.getPk();
		String[] pkValues = form.getCheckVal();
		int wtzrs = 0;//未调整人数
		//班级信息
		HashMap<String, String> bjxxMap = new HashMap<String, String>();
		
		if(pkValues != null && pkValues.length>0){
			String query = StringUtils.joinStr("select distinct zydm,nj,bjdm from ",
					"view_njxyzybj a where bjdm=(select bmdm from xyjxjrs where ",
					pk,
					"=?",
					")");
			bjxxMap = dao.getMap(query,new String[]{pkValues[0]}, new String[]{"zydm", "bjdm", "nj"});
		}
		
		//组合查询未调整人数的班级总人数
		StringBuilder sql = new StringBuilder("select sum(jxjrs)num from xyjxjrs a where ");
		sql.append(pk);
		sql.append(" not in(");
		
		for(int i=0; i<pkValues.length; i++){
			if(i==0){
				sql.append("'");				
			}else{
				sql.append(",'");
			}
			sql.append(pkValues[i]);
			sql.append("'");
		}
		sql.append(") and exists(select 1 from view_njxyzybj b where a.bmdm=b.bjdm and b.zydm=? and b.nj=?) and bmlb='bjdm' and jxjdm=? and xn=? and nd=? and xqdm=? and key=?");
		
		String wtzbjzrs = dao.getOneRs(sql.toString(), new String[]{bjxxMap.get("zydm"),bjxxMap.get("nj"),form.getJxjdm(),form.getXn(), form.getNd(), form.getXq(), form.getKey()},"num");
		wtzrs = StringUtils.isNull(wtzbjzrs) ? 0 : Integer.parseInt(wtzbjzrs);
		return wtzrs;
	}
	
	/**
	 * 获取专业所属学院人数
	 * @param form
	 * @return int
	 * */
	public int getZyssxyrs(PjpyZjkjxyActionForm form){
		DAO dao = DAO.getInstance();
		int xyrs = 0;
		String pk = form.getPk();
		String[] pkValues = form.getCheckVal();
		
		if(pkValues != null && pkValues.length>0){
			String zydm = dao.getOneValue("xyjxjrs", "bmdm", pk, pkValues[0]);
			String sql = StringUtils.joinStr("select jxjrs from xyjxjrs where ",
					     "bmdm=(select distinct xydm from view_njxyzybj b where b.zydm=?)",
					     " and nj=?",
					     " and bmlb='xydm' and jxjdm=? and xn=? and nd=? and xqdm=? and key=?");  //and cpfw='xy' 
			
			String xyNum = dao.getOneRs(sql, 
					new String[]{zydm,form.getNj(),form.getJxjdm(),form.getXn(),form.getNd(),form.getXq(),form.getKey()}, "jxjrs");
			xyrs = StringUtils.isNull(xyNum) ? -1 : Integer.parseInt(xyNum);
		}
		return xyrs;
	}
	
	/**
	 * 没有调整人数的专业的人数
	 * @param form
	 * @return int
	 * */
	public int getWtjzyrs(PjpyZjkjxyActionForm form){
		DAO dao = DAO.getInstance();
		String pk = form.getPk();
		String[] pkValues = form.getCheckVal();
		int wtzrs = 0;//未调整人数
		//专业信息
		HashMap<String, String> zyxxMap = new HashMap<String, String>();
		
		if(pkValues != null && pkValues.length>0){
			String query = StringUtils.joinStr("select distinct zydm,nj,xydm from ",
					"view_njxyzybj a where xydm=(select bmdm from xyjxjrs where ",
					pk,
					"=?",
					")");
			zyxxMap = dao.getMap(query,new String[]{pkValues[0]}, new String[]{"xydm", "zydm", "nj"});
		}
		
		//组合查询未调整人数的班级总人数
		StringBuilder sql = new StringBuilder("select sum(jxjrs)num from xyjxjrs a where ");
		sql.append(pk);
		sql.append(" not in(");
		
		for(int i=0; i<pkValues.length; i++){
			if(i==0){
				sql.append("'");				
			}else{
				sql.append(",'");
			}
			sql.append(pkValues[i]);
			sql.append("'");
		}
		sql.append(") and exists(select 1 from view_njxyzybj b where a.bmdm=b.zydm and b.xydm=?) and cpfw='zy' and jxjdm=? and xn=? and nd=? and xqdm=? and key=?");
		
		String wtzzyzrs = dao.getOneRs(sql.toString(), new String[]{zyxxMap.get("xydm"),form.getJxjdm(),form.getXn(), form.getNd(), form.getXq(), form.getKey()},"num");
		wtzrs = StringUtils.isNull(wtzzyzrs) ? 0 : Integer.parseInt(wtzzyzrs);
		return wtzrs;
	}
	
	/**
	 * 获取专业下级部门人数
	 * @param form
	 * @return int
	 * */
	public int getZyxjbmrs(PjpyZjkjxyActionForm form){
		DAO dao = DAO.getInstance();
		String pk = form.getPk();
		String[] pkValues = form.getCheckVal();
		int xjbmrs = 0;//未调整人数
		//专业信息
		HashMap<String, String> zyxxMap = new HashMap<String, String>();
		
		if(pkValues != null && pkValues.length>0){
			String query = StringUtils.joinStr("select distinct zydm,nj,xydm from ",
					"view_njxyzybj a where xydm=(select bmdm from xyjxjrs where ",
					pk,
					"=?",
					")");
			zyxxMap = dao.getMap(query,new String[]{pkValues[0]}, new String[]{"xydm", "zydm", "nj"});
		}
		
		String sql = "select sum(jxjrs) num from xyjxjrs a where jxjdm=? and nj=? and xn=? and nd=? and xqdm=? and cpfw='bj' and exists(select 1 from view_njxyzybj b where a.bmdm=b.bjdm and b.zydm=?)";
		String num = dao.getOneRs(sql, new String[]{form.getJxjdm(),form.getNj(),form.getXn(), form.getNd(), form.getXq(),zyxxMap.get("zydm")}, "num");
		xjbmrs = StringUtils.isNull(num) ? 0 : Integer.parseInt(num);
		return xjbmrs;
	}
	
	/**
	 * 获取学院下级部门人数
	 * @param form
	 * @return int
	 * */
	public int getXyxjbmrs(PjpyZjkjxyActionForm form){
		DAO dao = DAO.getInstance();
		String pk = form.getPk();
		String[] pkValues = form.getCheckVal();
		int xjbmrs = 0;//未调整人数
		//学院信息
		HashMap<String, String> xyxxMap = new HashMap<String, String>();
		
		if(pkValues != null && pkValues.length>0){
			String query = StringUtils.joinStr("select distinct xydm,nj from ",
					"view_njxyzybj a where xydm=(select bmdm from xyjxjrs where ",
					pk,
					"=?",
					")");
			xyxxMap = dao.getMap(query,new String[]{pkValues[0]}, new String[]{"xydm", "nj"});
		}
		
		String sql = "select sum(jxjrs) num from xyjxjrs a where jxjdm=? and nj=? and xn=? and nd=? and xqdm=? and bmlb='zydm' and exists(select 1 from view_njxyzybj b where a.bmdm=b.zydm and b.xydm=?)";
		String num = dao.getOneRs(sql, new String[]{form.getJxjdm(),form.getNj(),form.getXn(), form.getNd(), form.getXq(),xyxxMap.get("xydm")}, "num");
		xjbmrs = StringUtils.isNull(num) ? 0 : Integer.parseInt(num);
		return xjbmrs;
	}
	
	/**
	 * 更新调整后的人数
	 * @param form
	 * @return boolean
	 * @throws SQLException 
	 * */
	public String updateJxjrstz(PjpyZjkjxyActionForm form, String tzbm){
		DAO dao = DAO.getInstance();
		String pk = form.getPk();
		String[] pkValues = form.getCheckVal();
		String[] jxjrsArr = form.getJxjtzrs();
		String[] sqlArr = new String[pkValues.length];
		String message = "";
		for(int i=0; i<pkValues.length; i++){
			if("xy".equalsIgnoreCase(tzbm)){
				if(getXyxjbmrs(form)>0){
					message += "第" + (i+1)+ "行下级部门已经调整了人数，操作失败！\n";
					continue;
				}
			}else if("zy".equalsIgnoreCase(tzbm)){
				if(getZyxjbmrs(form)>0){
					message += "第" + (i+1)+ "行下级部门已经调整了人数，操作失败！\n";
					continue;
				}
			}
			sqlArr[i] = StringUtils.joinStr("update xyjxjrs set jxjrs='",
					jxjrsArr[i],"' where ", pk,"='",pkValues[i],"'");
		}		
		try{
			dao.checkBatch(dao.runBatch(sqlArr));
			if(StringUtils.isNull(message)){
				message = "操作成功！";
			}
		}catch (Exception e) {
			message = "操作失败！";
		}
		
		return message;
	}
	
	/**
	 * 奖学金申请人数设置查询
	 * @param model
	 * @param output
	 * @return List<String[]>
	 * */
	public List<String[]> queryJxjsqrsz(PjpyZjkjxyActionForm model,String[] output){
		DAO dao = DAO.getInstance();
		Pages pages = model.getPages();
		
		StringBuilder sql = new StringBuilder();
		//查询条件语句
		String whereSql = appendWhereSql(model).toString();
		//查询条件值
		String[] inputValue = value != null ? value.toArray(new String[0]) : new String[]{};
		String mainSql = getMainSql(model);//主查询语句
		
		//分页查询语句
		sql.append("select a.*,a.xh||a.xn||a.nd||a.xq||xmdm||key pkValue from(select xh,xm,xb,nj,xydm,xymc,zydm,zymc,bjdm,bjmc,xn,nd,xq,xqmc,rownum r,");
		sql.append("xmdm,xmmc,key,sfksq from(");
		sql.append(mainSql);
		sql.append(")");
		sql.append(whereSql);
		sql.append(")a where r>");
		sql.append(pages.getStart());
		sql.append(" and r<=");
		sql.append(pages.getStart()+pages.getPageSize());
		
		//查询总记录数
		int maxRecord = Integer.parseInt(dao.getOneRs("select count(*)num from(" + mainSql+")" +whereSql , inputValue, "num"));
		pages.setMaxRecord(maxRecord);
		
		return dao.rsToVator(sql.toString(), inputValue, output);
	}
	
	/**
	 * 奖学金申请人数设置查询,无分页
	 * @param model
	 * @param output
	 * @return List<String[]>
	 * */
	public List<String[]> queryJxjsqrsszNotP(PjpyZjkjxyActionForm model,String[] output){
		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();
		//查询条件语句
		String whereSql = appendWhereSql(model).toString();
		//查询条件值
		String[] inputValue = value != null ? value.toArray(new String[0]) : new String[]{};
		String mainSql = getMainSql(model);//主查询语句
		
		//分页查询语句
		sql.append("select a.*,a.xh||a.xn||a.nd||a.xq||xmdm||key pkValue from(select xh,xm,xb,nj,xydm,xymc,zydm,zymc,bjdm,bjmc,xn,nd,xq,xqmc,rownum r,");
		sql.append("xmdm,xmmc,key,sfksq from(");
		sql.append(mainSql);
		sql.append(")");
		sql.append(whereSql);
		sql.append(")a ");		
		return dao.rsToVator(sql.toString(), inputValue, output);
	}
	
	/**
	 * 获取主查询语句
	 * @param model
	 * @return String
	 * */
	public String getMainSql (PjpyZjkjxyActionForm model){
		String key = model.getKey();
		String xmdm = GlobalsVariable.PJPY_JXJ.equalsIgnoreCase(key) 
							? model.getQueryequals_jxjdm() 
							: model.getQueryequals_rychdm();
		String xmmc = GlobalsVariable.PJPY_JXJ.equalsIgnoreCase(key) 
							? "(select jxjmc from jxjdmb b where a.xmdm=b.jxjdm)xmmc,"
							: "(select rychmc from rychdmb b where a.xmdm=b.rychdm)xmmc,";
		String mainSql = StringUtils.joinStr("select xh,xm,xb,nj,xydm,xymc,zydm,zymc,",
				"bjdm,bjmc,xn,nd,xq,",
				"(select xqmc from xqdzb b where a.xq=b.xqdm)xqmc,xmdm,",
				xmmc,
				"key,sfksq from(",
				"select xh,xm,xb,nj,xydm,xymc,zydm,zymc",
                  ",bjdm,bjmc,xn,nd,xq,xqmc,xmdm,xmmc,key,",
                  "sfksq from view_pjpy_jxjsqxs ",
                  "union select xh,xm,xb,nj,xydm,xymc,zydm,zymc,bjdm,bjmc,'" ,
                  model.getQueryequals_xn(),"' xn,'",
                  model.getQueryequals_nd(),
                  "'nd,'",
                  model.getQueryequals_xq(),
                  "'xq,''xqmc,'",
                  xmdm,
                  "' xmdm,'' xmmc,'",
                  key,
                  "' key,'否' sfksq from view_xsjbxx a where not exists (select 1 from pjpy_jxjsqxsb b where a.xh=b.xh and b.key='",
                  key,
                  "' and b.xn='",
                  model.getQueryequals_xn(),
                  "' and b.xq='",
                  model.getQueryequals_xq(),
                  "' and b.nd='",
                  model.getQueryequals_nd(),
                  "' and b.xmdm='",
                  xmdm,
                  "'))a");
		
		return mainSql;
	}
	
	
	
	/**
	 * 保存奖学金申请学生信息
	 * @param model
	 * @return String
	 * */
	public String saveJxjsqxs(PjpyZjkjxyActionForm model){
		DAO dao = DAO.getInstance();
		String[] pkValue = model.getCheckVal();//主键值
		String[] xhV = model.getXhV();
		String[] xnV = model.getXnV();
		String[] ndV = model.getNdV();
		String[] xqV = model.getXqV();
		String[] xmdmV = model.getXmdmV();
		String key = model.getKey();//奖学金或荣誉称号
		
		String[] sqlArr  = new String[pkValue.length*2];//执行的sql语句集合
		String[] sqzt = model.getSqzt();//可申请状态
		String pk = "xh||xn||nd||xq||xmdm||key";//主键字段
		String message = "";//执行后的消息结果
		
		int flagNum = 0;
		for(int i=0; i<pkValue.length; i++){
			//将数据按主键删除
			sqlArr[flagNum++] = StringUtils.joinStr("delete from pjpy_jxjsqxsb where ",
												pk,
												"='",
												pkValue[i],
												"'");
			//如果可申请，将数据插入到可申请学生信息表中
			if(sqzt != null && sqzt[i] != null && "是".equalsIgnoreCase(sqzt[i])){				
				sqlArr[flagNum++] = StringUtils.joinStr("insert into pjpy_jxjsqxsb (xh,xn,nd,xq,xmdm,key) values('",
						xhV[i],
						"','",
						xnV[i],
						"','",
						ndV[i],
						"','",
						xqV[i],
						"','",
						xmdmV[i],
						"','",
						key,
						"')");
			}			
		}
		try{
			int[] result = dao.runBatch(sqlArr);
			if(!dao.checkBatch(result)){
				message = "操作失败！";
			}
		}catch(Exception e){
			message = "操作失败！";
		}
		
		message = StringUtils.isNull(message) ? "操作成功！" : message;
		
		return message;
	}
	
	/**
	 * 获取人数分配信息
	 * @param pk
	 * @param pkV
	 * @param shzd
	 * @param key
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getRsfpMap(String pk,String pkV, String shzd,String key){
		DAO dao = DAO.getInstance();
		
		//表名
		String tableName = GlobalsVariable.PJPY_JXJ.equalsIgnoreCase(key) 
		                 ? "view_typj_jxjsqb"
		                 : "view_typj_rychsq";
		//主键值字段
		String pkStr = GlobalsVariable.PJPY_JXJ.equalsIgnoreCase(key) 
        				? "xn||nd||xq||jxjdm||nj"
        				: "xn||nd||xq||rychdm||nj";
		//代码字段
		String dmStr = GlobalsVariable.PJPY_JXJ.equalsIgnoreCase(key) 
						? "jxjdm"
						: "rychdm";
		
		String bm = "xydm";//部门
		if("fdysh".equalsIgnoreCase(shzd) 
				|| "bzrsh".equalsIgnoreCase(shzd)){
			bm = "bjdm";
			pkStr += "||bjdm";
		}else{
			pkStr += "||xydm";
		}
		String sql = StringUtils.joinStr("select ",
										 pkStr,
										 " pkV,",
										 "(select count(*) from ",
										 tableName,
										 " b where a.xn=b.xn and a.nd=b.nd and a.xq=b.xq and a.",
										 dmStr,
										 "=b.",
										 dmStr,
										 " and b.", 
										 shzd,
										 "='通过' and a." + bm + "=b." + bm + " and a.xh<>b.xh)tgrs from ",
										 tableName,
										 " a where ",
										 pk,
										 "=?");
		return dao.getMap(sql, new String[]{pkV}, new String[]{"pkV", "tgrs"});
	}
	
	/**
	 * 获取奖学金人数
	 * @param pk
	 * @param pkV
	 * @param shzd
	 * @param key
	 * @return String
	 * */
	public String getJxjrs(String pkV, String shzd,String key){
		DAO dao = DAO.getInstance();
		String pk = "xn||nd||xqdm||jxjdm||nj||bmdm";
		String sql = StringUtils.joinStr("select jxjrs from xyjxjrs where jxjrs is not null and key='",
				                          key,
				                          "' and ",
				                          pk,
				                          "=?");
		if("fdysh".equalsIgnoreCase(shzd)){
			sql += " and bmlb='bjdm'";
		}else{
			sql += " and bmlb='xydm'";
		}
		
		return dao.getOneRs(sql, new String[]{pkV}, "jxjrs");
	}
	
	/**
	 * 获取综合测评信息
	 * @param jxjpdModel
	 * @param resMap
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getJxjpdxx(JxjpdxxModel jxjpdModel, HashMap<String, String> resMap){
		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();
		if (StringUtils.isNotNull(jxjpdModel.getXh())) {
			sql.append("select nvl(a.fs,0) zf,a.pm,(select mc from pjpy_zctjszb b where a.dm=b.dm) mc from pjpy_zhszcpb a where jb='1' and xh='");
			sql.append(jxjpdModel.getXh());
			sql.append("'");
			if (StringUtils.isNotNull(jxjpdModel.getXn())) {
				sql.append(" and xn='"); 
				sql.append(jxjpdModel.getXn());
				sql.append("'");
			}
		} else {
			System.out.println("传入参数中,学号不能为空!!!!!!");
		}	
		
		resMap.putAll(dao.getMap(sql.toString(),new String[]{},new String[]{"zf","pm"}));
		return resMap;
	}
}
