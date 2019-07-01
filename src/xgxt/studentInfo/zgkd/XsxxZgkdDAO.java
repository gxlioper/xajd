package xgxt.studentInfo.zgkd;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import common.Globals;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.base.Excel2Oracle;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 中国矿业大学学生信息DAO
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 李容</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-09-03</p>
 */
public class XsxxZgkdDAO extends DAO{
	ArrayList<String> values = new ArrayList<String>();
	
	/**
	 * 获取学生信息的字段列表
	 * @return List
	 * */
	public List<HashMap<String, String>> getXsxxzdList(String xxdm){
		//中国矿大学生信息的所有字段
		String[] colCN = null;	
		String[] columns = null;
		try {
			columns = getArray("select zdmc from drb where xxdm=? and zdssb='xsxxb' order by to_number(xsxh)", new String[]{xxdm}, "zdmc");
			if(columns == null || columns.length<1){
				//通用
				columns = getArray("select zdmc from drb where xxdm=? and zdssb='xsxxb' order by to_number(xsxh)", new String[]{"public_xsxxb"}, "zdmc");
			}
			colCN = getColumnNameCN(columns, "xsxxb");
		} catch (SQLException e) {
			e.printStackTrace();
		}				
		return arrayToList(columns, colCN);
	}
		
	/**
	 * 获取用户可修改的字段
	 * @param yhjs
	 * @return String[]
	 * */
	public String[] getZdinfo(String yhjs){
		String[] col = null;
		try {
			col =  getArray("select distinct zdm from xsxx_qxfpb where yhjs=? and zdm<>'xh'", new String[]{yhjs}, "zdm");
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return col;
	}
	
	/**
	 * 根据表名称获取用户可修改的学生信息字段
	 * @param yhjs
	 * @return String[]
	 * */
	public String[] getZdInfoByTab(String yhjs,String tableName){
		String[] col = null;		
		try {
			//这里查询的字段是没有学号的
			col =  getArray("select distinct zdm from xsxx_qxfpb where yhjs=? and zdssb=? and zdm<>'xh'", new String[]{yhjs,tableName}, "zdm");
			if("student".equalsIgnoreCase(yhjs)){
				col =  getArray("select distinct zdm from xsxx_qxfpb where yhjs=? and zdssb=? and zdm<>'xydm' and zdm<>'zydm' and zdm<>'bjdm' and zdm<>'xh'", new String[]{yhjs,tableName}, "zdm");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return col;
	}
	
	/**
	 * 判断学生的信息是否存在
	 * @param xh
	 * @param tableName
	 * @return boolean
	 * */
	public boolean isExists(String xh, String tableName){
		String sql = "select count(*)count from " + tableName + " where xh=?";
		int count = Integer.parseInt(getOneRs(sql, new String[]{xh}, "count"));
		
		return count>0 ? true : false;
	}
	
	/**
	 * 获取学生家庭信息的字段列表
	 * @return List
	 * */
	public List<HashMap<String, String>> getJtxxzdList(String xxdm){
		String sql = "select distinct zdmc,xsxh from drb where xxdm=? and zdssb='xsfzxxb' order by to_number(xsxh)";
		String[] jtxx = null;
		String[] colCN = null;
		try {
			jtxx = getArray(sql, new String[]{xxdm}, "zdmc");
			if(jtxx == null || jtxx.length<1){
				jtxx = getArray(sql,new String[]{"public_xsfzxxb"}, "zdmc");
			}
			colCN = getColumnNameCN(jtxx, "xsfzxxb");
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return arrayToList(jtxx, colCN);
	}
	                            
	
	/**
	 * 获取用户列表
	 * @param String xxdm
	 * @return List
	 * */
	public List<HashMap<String, String>> getYhList(String xxdm){
		//用户角色
		String[] yhm = {"xx","xy","student"};
		String[] zwm = {"学校",Base.YXPZXY_KEY,"学生"};
		if(!(Globals.XXDM_ZGDZDX.equalsIgnoreCase(xxdm) 
				|| Globals.XXDM_ZGKYDX.equalsIgnoreCase(xxdm))){
			yhm = new String[]{"student"};
			zwm = new String[]{"学生"};
		}
		return arrayToList(yhm, zwm);
	}
	
	/**
	 * 根据用户角色获取用户可维护的学生信息字段列表
	 * @param yh
	 * @param zdssb
	 * @return List
	 * */
	public List<HashMap<String, String>> getWhzdByYh(String yh,String zdssb){
		//zdssb用于区分是家庭信息还是学生个人信息
		String sql = "select distinct zdm en,zdzwmc cn from xsxx_qxfpb where yhjs=? and zdssb=?";
		return getList(sql, new String[]{yh,zdssb}, new String[]{"en","cn"});
	}
	
	/**
	 * 根据用户角色获取用户必填的学生信息字段列表
	 * @param yh
	 * @param zdssb
	 * @return List
	 * */
	public List<HashMap<String, String>> getBtzdByYh(String yh,String zdssb){
		//zdssb用于区分是家庭信息还是学生个人信息
		String sql = "select distinct zdm en,zdzwmc cn from xsxx_btzdfpb where yhjs=? and zdssb=?";
		return getList(sql, new String[]{yh,zdssb}, new String[]{"en","cn"});
	}
	
	
	/**
	 * 保存学生信息修改权限信息
	 * @param model
	 * @return boolean
	 * */
	public boolean savePower(XsxxZgkdForm model,HttpServletRequest request){
		boolean flag = false;
		DAO dao = DAO.getInstance();
//		String tableName = "xsxx_qxfpb";
		String yhjs = model.getYhjs();
		String xsxxzd = model.getXsxxzd();
		String jtxxzd = model.getJtxxzd();
		
		try {
			if(xsxxzd != null && !"".equalsIgnoreCase(xsxxzd)){
				//保存学生个人信息的相关权限信息
				String[] xsxx = xsxxzd.split("!!");
				String[] colCN = getColumnNameCN(xsxx, "xsxxb");	
				String sql = "delete from xsxx_qxfpb where yhjs||zdssb = ?";
				flag = dao.runUpdate(sql, new String []{yhjs+"xsxxb"});
					String [] sqlMap = new String [xsxx.length]; 
					if(flag){
						for(int i=0; i<xsxx.length; i++){
							String sqlTmp = "insert into xsxx_qxfpb (yhjs,zdm,zdzwmc,zdssb) values ('"+yhjs+"','"+xsxx[i]+"','"+colCN[i]+"','xsxxb')";
							sqlMap[i]=sqlTmp;
						}
						dao.runBatch(sqlMap);
					}				
			}else{
				String sql = "delete from xsxx_qxfpb where yhjs||zdssb = ?";
				flag = dao.runUpdate(sql, new String []{yhjs+"xsxxb"});
			}
			if(jtxxzd != null && !"".equalsIgnoreCase(jtxxzd)){
				//保存学生家庭信息的相关权限信息
				String[] jtxx = jtxxzd.split("!!");
				String[] colCN = getColumnNameCN(jtxx, "xsfzxxb");
//				flag = StandardOperation.delete(tableName, "yhjs||zdssb", yhjs+"xsfzxxb", request);
				String sql = "delete from xsxx_qxfpb where yhjs||zdssb = ?";
				flag = dao.runUpdate(sql, new String []{yhjs+"xsfzxxb"});
				String [] sqlMap = new String [jtxx.length]; 
				if(flag){
					for(int i=0; i<jtxx.length; i++){
//						flag = StandardOperation.insert(tableName, new String[]{"yhjs","zdm","zdzwmc","zdssb"}, 
//								new String[]{yhjs,jtxx[i],colCN[i],"xsfzxxb"}, request);
						String sqlTmp = "insert into xsxx_qxfpb (yhjs,zdm,zdzwmc,zdssb) values ('"+yhjs+"','"+jtxx[i]+"','"+colCN[i]+"','xsfzxxb')";
						sqlMap[i]=sqlTmp;
					}
					dao.runBatch(sqlMap);
				}
			}else{
				String sql = "delete from xsxx_qxfpb where yhjs||zdssb = ?";
				flag = dao.runUpdate(sql, new String []{yhjs+"xsfzxxb"});
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	/**
	 * 查询除学号外学生可维护的字段
	 * @param yhjs
	 * @return String
	 * */
	public String getzdNoXh(String yhjs){
		String result = "";
		String sql = "select distinct zdm from xsxx_qxfpb where yhjs=? and zdm<>'xh'";
		String[] zdList = null;
		try {
			zdList = getArray(sql, new String[]{yhjs}, "zdm");
		    for(int i=0; i<zdList.length; i++){
		    	result += zdList[i] + "!!";
		    }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 查询除学号外学生可维护的字段
	 * @param yhjs
	 * @return String
	 * */
	public String getzdNoXh(String yhjs,String tableName){
		String result = "";
		String sql = "select distinct zdm from xsxx_qxfpb where yhjs=? and zdm<>'xh' and zdssb=?";
		String[] zdList = null;
		try {
			zdList = getArray(sql, new String[]{yhjs,tableName}, "zdm");
		    for(int i=0; i<zdList.length; i++){
		    	result += zdList[i] + "!!";
		    }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	/**
	 * 查询学生基本信息和家庭信息
	 * @param xh
	 * @return List
	 * */
	public HashMap<String, String> getStuAndFamily(String xh,String yhjs,String xxdm){
		HashMap<String, String> rs = null;
		int count = 0;
		String[] outputValue = null;
		String sql = "select count(*) count from xsxx_xsxgxxb where xh=?";
		count = Integer.parseInt(getOneRs(sql, new String[]{xh}, "count"));
		//查询学生信息和家庭信息
		try {
			outputValue = getArray("select distinct zdmc from drb where xxdm=? and zdssb='xsfzxxb'", new String[]{xxdm}, "zdmc");
			sql = "select * from xsfzxxb where xh=?";
			rs = getMap(sql, new String[]{xh}, outputValue);
			
			outputValue = getArray("select distinct zdmc from drb where xxdm=? and zdssb='xsxxb'", new String[]{xxdm}, "zdmc");			
			sql = "select * from view_xsxxb where xh=?";
			rs.putAll(getMap(sql, new String[]{xh}, outputValue));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(yhjs !=null && yhjs.equalsIgnoreCase("student")){
			if(count>0){
				//信息修改后还未审核通过	
				try {
					outputValue = getArray("select distinct zdm from xsxx_qxfpb where yhjs=?", new String[]{yhjs}, "zdm");
					sql = "select * from xsxx_xsxgxxb where xh=?";
					rs.putAll(getMap(sql, new String[]{xh}, outputValue));
				} catch (SQLException e) {
					e.printStackTrace();
				}			
			}
		}
		return rs;
	}
	
	/**
	 * 判断是否在修改学生信息的时间段内
	 * @return String
	 * */
	public String isSqqjFlag(){
		String sql = "select issz from xsxxxgsjb";
		String isSz = getOneRs(sql, new String[]{}, "issz");
		if("no".equals(isSz)){
			return "0";
		}
		
		sql = "select count(*)count from xsxxxgsjb where kssj<to_char(sysdate,'yyyy-mm-ddhh24miss') and jssj>to_char(sysdate,'yyyy-mm-ddhh24miss') and issz='yes'";
		int count = Integer.parseInt(getOneRs(sql, new String[]{}, "count"));
		if(count>0){
			return "0";
		}
		
		return "1";
	}
	
	/**
	 * 查询学生修改后的个人信息
	 * 
	 * @throws Exception
	 */
	public List<String[]> selectModiStuinfo(XsxxZgkdForm model,
			HttpServletRequest request) throws Exception {
		
		// ------------修改时间：2012.4.9 ---------------
		// ------------修改人： 裘力俊 ---------------
		// ------------修改内容：优化查询速度,BUG修改-----------------
		StringBuilder sql=new StringBuilder();
		sql.append(" select rownum r,a.* from(select  ");
		sql.append("  xh,xm,xb,a.nj,a.xydm,a.zydm,a.bjdm,");
		sql.append("  xz,shjg,fdysh,b.zymc,b.bjmc");
		sql.append("  from (select distinct a.xh,");
		sql.append("  nvl(a.xm,b.xm) xm,");
		sql.append("  nvl(a.xb,b.xb) xb,");
		sql.append("  nvl(a.nj,b.nj) nj,");
		sql.append("  nvl(a.xydm,b.xydm) xydm,");
		sql.append("  nvl(a.zydm,b.zydm) zydm,");
		sql.append("  nvl(a.bjdm,b.bjdm) bjdm,");
		sql.append("  a.xz,a.fdysh,a.shjg");
		sql.append("  from xsxx_xsxgxxb a left join view_xsjbxx b on a.xh = b.xh)a left join view_njxyzybj b");
		sql.append("  on a.bjdm=b.bjdm)a 	");
		
		String[] outputValue = {"xh","xm","xb","nj","zymc","bjmc","xz","shjg"};		
		if(Globals.XXDM_BJQNZZXY.equalsIgnoreCase(Base.xxdm)){
			outputValue = new String[]{"xh","xm","xb","nj","zymc","bjmc","fdysh","shjg"};
		}
		
		// 高级查询
		String superSearch = Base.getSuperSearch();
		String whereSql = "";
		String[] inputValue = null;
		
		if ("yes".equalsIgnoreCase(superSearch)) {
			
			SearchService searchService = new SearchService();
			String searchTjByUser = searchService.getSearchTjByUser(request,
					"a", "xydm", "bjdm");

			// 高级查询
			whereSql = " where 1 = 1 ";
			whereSql += SearchService.getSearchTj(model.getSearchModel());
			whereSql += searchTjByUser;
			inputValue = SearchService.getTjInput(model.getSearchModel());
		} else {
			whereSql = getWhereSql(model).toString();
			inputValue = values != null ? values.toArray(new String[0])
					: new String[] {};
		}
		
		//查询总记录
		String count = getOneRs("select count(*) num from ("+ sql + whereSql+")", inputValue, "num");
		model.getPages().setMaxRecord(Integer.parseInt(count));
		//分页
		String sqlStr="";
		sqlStr="select a.* from(" + sql + whereSql + " order by bjdm)a where r>" + model.getPages().getStart() + "and r<=" 
			  + (model.getPages().getStart()+model.getPages().getPageSize());
		System.out.println(sqlStr);
		return rsToVator(sqlStr, inputValue, outputValue);
	}
	
	/**
	 * 学生信息修改查询条件及值
	 * @param 
	 * @return
	 * @throws Exception
	 */
	public StringBuffer getWhereSql(XsxxZgkdForm model) throws Exception {
		StringBuffer whereSql = new StringBuffer(" where 1=1 ");
		String xh = model.getXh();
		String xm = DealString.toGBK(model.getXm());
		String xydm = model.getXydm();
		String zydm = model.getZydm();
		String bjdm = model.getBjdm();
		String nj= model.getNj();
		String fdysh = model.getFdysh();
		
		if (!StringUtils.isNull(xydm)) {
			whereSql.append(" and a.xydm = ?");
			values.add(xydm);
		}
		if (!StringUtils.isNull(zydm)) {
			whereSql.append(" and a.zydm = ?");
			values.add(zydm);
		}
		if (!StringUtils.isNull(bjdm)) {
			whereSql.append(" and a.bjdm = ?");
			values.add(bjdm);
		}
		if (!StringUtils.isNull(xh)) {
			whereSql.append(" and a.xh like '%'||?||'%'");
			values.add(xh);
		}
		if (!StringUtils.isNull(nj)) {
			whereSql.append(" and a.nj = ?");
			values.add(nj);
		}
		if (!StringUtils.isNull(xm)) {
			whereSql.append(" and a.xm like '%'||?||'%'");
			values.add(xm);
		}
		if (!StringUtils.isNull(fdysh)) {
			whereSql.append(" and a.fdysh = ?");
			values.add(fdysh);
		}
		return whereSql;
	}
	
	/**
	 * 获取查询结果表头 
	 * @return List
	 * */
	public List<HashMap<String, String>> getTopTr(){
		String tabName = "xsxx_xsxgxxb";
		String[] outputValue = {"xh","xm","xb","nj","专业名称","班级名称","xz","shjg"};
		if(Globals.XXDM_BJQNZZXY.equalsIgnoreCase(Base.xxdm)){
			outputValue = new String[]{"xh","xm","xb","nj","专业名称","班级名称","fdysh","shjg"};
		}
		String[] outputCN = getColumnNameCN(outputValue, tabName);
		return arrayToList(outputValue,outputCN);
	}
	
	/**
	 * 获取修改后的值
	 * @param xh
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getNewValue(String xh){
		HashMap<String, String> rs = new HashMap<String, String>();
		String yhjs = "student";
		String[] outputValue = getZdinfo(yhjs);		
		//将代码对应的名称查询出来
		String[] dmArr = {"mz", "zzmm", "jtcy1_mz", "jtcy1_zzmm", "jtcy2_mz", "jtcy2_zzmm", "jtcy3_mz", "jtcy3_zzmm", 
						  "pycc", "kslb", "rxfs", "pyfs", "jtcy1_gxmc", "jtcy2_gxmc", "jtcy3_gxmc"};
		String[] mcArr = {"yhmc"};
		for(String column : dmArr){
			if(StringUtils.contains(outputValue, new String[]{column})){
				mcArr = StringUtils.joinStrArr(mcArr, new String[]{column+"mc"});
			}
		}
		outputValue = StringUtils.joinStrArr(outputValue,mcArr);
		
		String sql = "select * from view_xsxx_xsxgxxb where xh=?";
		
		//获取学生信息的原始值	
		rs.putAll(getOldValue(xh));
		//修改后的新值
		rs = getMap(sql, new String[]{xh}, outputValue);
		
		return rs;
	}
	
	
	/**
	 * 获取修改后的值
	 * @param xh
	 * @return List
	 * */
	public HashMap<String, String> getOldValue(String xh){
		HashMap<String, String> resultMap = new HashMap<String, String>();
		HashMap<String, String> rs = new HashMap<String, String>();
		String[] outputValue = getColumnName("select * from view_xsxxb where 1=2");		
		String sql = "select a.*,(select distinct pyccmc from xg_xsxx_pyccdmb c where a.pycc=c.pyccdm)pyccmc," 
					+"(select distinct kslbmc from xg_xsxx_kslbdmb c where a.kslb=c.kslbdm)kslbmc," 
					+"(select distinct rxfsmc from xg_xsxx_rxfsdmb c where a.rxfs=c.rxfsdm)rxfsmc," 
					+"(select distinct pyfsmc from xg_xsxx_pyfsdmb c where a.pyfs=c.pyfsdm)pyfsmc from view_xsxxb a where xh=?";
		
		//学生原始个人信息		
		for(int i=0; i<outputValue.length; i++){
			outputValue[i] = outputValue[i].toLowerCase();
		}
		rs = getMapNotOut(sql, new String[]{xh});
		
		//学生原始家庭信息
		outputValue = getColumnName("select * from view_xsjtxx where 1=2");
		sql = "select * from view_xsjtxx where xh=?";		
		for(int i=0; i<outputValue.length; i++){
			outputValue[i] = outputValue[i].toLowerCase();
		}
		rs.putAll(getMap(sql, new String[]{xh}, outputValue));
		
		//未修改的字段取原始值
		String yhjs = "student";
		String[] fpzd = getZdinfo(yhjs);
		//所有字段
		String[] syzd = getColumnName(Excel2Oracle.getSqlColumn("","xsxxb"));//学生信息字段
		syzd = StringUtils.joinStrArr(syzd,getColumnName(Excel2Oracle.getSqlColumn("","xsfzxxb")));//家庭信息字段
		for(int i=0; i<syzd.length; i++){
			syzd[i] = syzd[i].toLowerCase();
		}
		
		//需要查询原始值的是除了分配的字段以为的其它字段
		List<String> syzdList = new ArrayList<String>(Arrays.asList(syzd));
		//syzdList.removeAll(Arrays.asList(fpzd));
		syzdList.addAll(Arrays.asList(fpzd));
	    syzd = syzdList.toArray(new String[]{});
		//syzd = fpzd;
	    
	    //学院、专业、班级即时分配了可修改，学生也不能修改
	    syzd = StringUtils.joinStrArr(syzd, new String[]{"xydm", "zydm", "bjdm"});
	    
	    syzd = StringUtils.joinStrArr(syzd, new String[]{"xymc", "zymc", "bjmc","yhmc"});
	    //如果含有代码的字段，将名称也查询出来
		String[] dmArr = {"mz", "zzmm", "jtcy1_mz", "jtcy1_zzmm", "jtcy2_mz", "jtcy2_zzmm", "jtcy3_mz", "jtcy3_zzmm", 
				"pycc", "kslb", "rxfs", "pyfs","jtcy1_gx", "jtcy2_gx", "jtcy3gx"};
		String[] mcArr = new String[dmArr.length];
		
		for(String column : dmArr){
			if(StringUtils.contains(syzd, new String[]{column})){
				mcArr = StringUtils.joinStrArr(mcArr, new String[]{column+"mc"});
			}
		}
		syzd = StringUtils.joinStrArr(syzd,mcArr);
		//值复制
	    for(String column : syzd){
	    	resultMap.put(column, rs.get(column));
	    }
		return resultMap;
	}
	
	
	/**
	 * 中国矿业大学获取学生的困难等级
	 * @param xh
	 * @return String
	 * */
	public String getPksdj(String xh){
		String nd = Base.currNd;
		String tempSql = "select XXSHJG from ZGKYDX_KNSXX where xh=? and nd=?";
		return getOneRs(tempSql, new String[]{xh,nd}, "xxshjg");
	}
	
	/**
	 * 判断学校是否审核通过
	 * @param xh
	 * @return String
	 * */
	public String getXxshjg(String xh){
		return getOneRs("select shjg from xsxx_xsxgxxb where xh=?", new String[]{xh}, "shjg");
	}
	
	/**
	 * 保存修改的字段
	 * */
	public boolean saveXgzdxx(String xh,String xgzd,User user){
		String[] sqlArr = new String[2];
		sqlArr[0] = "delete from xg_xsxx_xsxgzdb where xh='" +DealString.replaceImmitStr(xh)+"'";
		sqlArr[1] = "insert into xg_xsxx_xsxgzdb(xh,xgzd) values('" + DealString.replaceImmitStr(xh) 
					+ "','" + DealString.replaceImmitStr(xgzd) + "')";
		boolean flag = false;
		try{
			int[] result = runBatch(sqlArr);
			flag = checkBatch(result);
		}catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}
	
	/**
	 * 查询修改字段信息
	 * */
	public HashMap<String, String> selectXgzdxx(String xh){
		String sql = "select * from xg_xsxx_xsxgzdb where xh=?";
		return getMap(sql, new String[]{xh}, new String[]{"xh", "xgzd"});		
	}
}

