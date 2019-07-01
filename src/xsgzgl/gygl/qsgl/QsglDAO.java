package xsgzgl.gygl.qsgl;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

import common.Globals;

import jxl.Sheet;
import jxl.Workbook;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.Excel2Oracle;
import xgxt.comm.CommDAO;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.ExcelMethods;
import xsgzgl.gygl.comm.GyglNewDAO;
import xsgzgl.gygl.comm.GyglNewInit;

public class QsglDAO extends GyglNewDAO{
	DAO dao = DAO.getInstance();
	
	/**
	 * 保存寝室
	 * @param model
	 * @return
	 */
	public boolean saveQswh(QsglModel model){
		boolean flag = false;
		
		String sql = "insert into xg_gygl_new_qsxxb(lddm,qsh,ch,qsxb,cws,sfbz,qsdh,xydm,nj,bz) values(?,?,?,?,?,?,?,?,?,?)";
		
		String[] input = new String[]{model.getLddm(), model.getQsh(), model.getCh(), 
									  model.getQsxb(), model.getCws(), model.getSfbz(),
									  model.getQsdh(), model.getXydm(), model.getNj(), model.getBz()};
		
		try {
			flag = dao.runUpdate(sql, input);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return flag;
	}
	
	/**
	 * 自动生成床位数
	 * @param params
	 * @return
	 */
	public boolean saveCws(List<String[]> params){
		boolean flag = false;
		
		String sql = "insert into xg_gygl_new_cwxxb(lddm,qsh,cwh) values(?,?,?)";
		
		try {
			int[] rs = dao.runBatch(sql, params);
			flag = dao.checkBatchResult(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return flag;
	}
	
	/**
	 * 修改寝室
	 * @param model
	 * @return
	 */
	public boolean updateQswh(String pk,QsglModel model){
		boolean flag = false;
		HashMap<String,String> map=getQsfprzInfo(pk);
		if(null==model.getYwkt()){
			model.setYwkt("");
		}
		StringBuffer sql = new StringBuffer("update xg_gygl_new_qsxxb set sfbz='"+model.getSfbz()+"',qsdh='"+
				model.getQsdh()+"',bz='"+model.getBz()+"',ywkt='"+model.getYwkt()+"'");
		ArrayList<String> sqls=new ArrayList<String>();
		/**
		 * 温州大学个性化1036
		 */
		if(StringUtils.equals(Base.xxdm, Globals.XXDM_WZDX)){
			sql.append(" ,qsh='" + model.getQsh()+"'");
		}
		
		if("true".equals(map.get("xbsfkxg"))){//修改寝室性别
			sql.append(" ,qsxb='"+model.getQsxb()+"' ");
		}
		if("0".equals(map.get("cwfprzgs"))&&!map.get("cws").equals(model.getCws())){//修改床位数
			int cws=Integer.parseInt(model.getCws());
			if(cws>0){
				sql.append(" ,cws='"+model.getCws()+"' ");
				sqls.add("delete from xg_gygl_new_cwxxb where lddm||qsh='"+pk+"'");
				if ("10698".equals(Base.xxdm)) {
					String[] ch2 = {"A","B","C","D","E","F","G","H","I","J","H","I","J","K","L","M"};
					for(int i=0;i<cws;i++){
						sqls.add("insert into xg_gygl_new_cwxxb(lddm,qsh,cwh) values('"+model.getLddm()
								+"','"+model.getQsh()+"','"+ch2[i]+"')");
					}
				}else {
					
					for(int i=0;i<cws;i++){
						sqls.add("insert into xg_gygl_new_cwxxb(lddm,qsh,cwh) values('"+model.getLddm()
								+"','"+model.getQsh()+"','"+(i+1)+"')");
					}
				}
			
			}
		}
		sql.append(" where lddm||qsh='"+pk+"' ");
		sqls.add(sql.toString());
		try {
//			flag = dao.runUpdate(sql.toString(), input.toArray(new String[]{}));
			CommDAO dao=new CommDAO();
			flag= dao.saveArrDate(sqls.toArray(new String[]{}));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return flag;
	}
	
	/**
	 * 查询寝室
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	public List<String[]> queryQs(QsglModel model) throws Exception{
		
		//高级查询条件
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		// 高级查询输入值
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		
		StringBuilder sql = new StringBuilder();
		List<String[]> list = new ArrayList<String[]>();				
		sql.append("select rownum r,a.* from (select rownum rr,a.*,a.lddm||a.qsh pkValue from view_xg_gygl_new_qsxx a ")
			.append(") a where 1=1 ");
							
		String[] output = new String[]{"pkValue","ldmc","qsh","qsxb", "nj", "xymc","qscws","blcws","wzcws","rzqk"};
				
		list = CommonQueryDAO.commonQuery(sql.toString(), searchTj, inputV, output, model);
		return list;
	}
	
	/**
	 * 批量删除寝室信息
	 * @param params
	 * @return
	 */
	public boolean delQs(List<String[]> params){
		boolean flag = false;
		
		StringBuilder sql = new StringBuilder();
		sql.append("delete xg_gygl_new_qsxxb where lddm||qsh=?");
		
		try {
			int[] rs = dao.runBatch(sql.toString(), params);
			flag = dao.checkBatchResult(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return flag; 
	}
	
	/**
	 * 删除床位数
	 * @param params
	 * @return
	 */
	public boolean delCws(List<String[]> params){
		boolean flag = false;
		
		StringBuilder sql = new StringBuilder();
		sql.append("delete xg_gygl_new_cwxxb where lddm||qsh=?");
		
		try {
			int[] rs = dao.runBatch(sql.toString(), params);
			flag = dao.checkBatchResult(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return flag; 
	}
	
	/**
	 * 通过主键获得单个寝室信息
	 * @param pk
	 * @return
	 */
	public Map<String, String> getQsForPk(String pk){
		StringBuilder sql = new StringBuilder();
		 
		sql.append("select b.lddm||b.qsh pkValue,a.ldmc,a.ldxb,a.ldcs,a.qsch,a.sfhlc,b.* ")
		.append("from xg_gygl_new_ldxxb a,(select a.*,b.bmmc xymc,(case when to_number(a.ch)>-1 then a.ch else 'B'||abs(a.ch) end) chmc from xg_gygl_new_qsxxb a ")
		.append("left join zxbz_xxbmdm b on a.xydm=b.bmdm)b where a.lddm=b.lddm and b.lddm||b.qsh=?");
		
		return dao.getMapNotOut(sql.toString(), new String[]{pk});
	}
	
	/**
	 * 获得指定楼栋指定层数下最大的寝室号和最大的床位数
	 * @param lddm
	 * @param ch
	 * @return
	 */
	public Map<String, String> getMaxQsInfo(String lddm, String ch){
		String sql = "select max(qsh) maxQsh,max(cws) maxCws from xg_gygl_new_qsxxb where lddm=? and ch=?";
		return dao.getMapNotOut(sql, new String[]{lddm, ch});
	}
	
	/**
	 * 获取指定的寝室号的床位信息
	 * @param lddm
	 * @param qsh
	 * @return
	 */
	public List<String[]> getCwForQs(String[] inputValue, String[] outputValue){
		StringBuilder sql = new StringBuilder();
		//辽宁机电职业技术学院 床位号存在中文，个性化修改
		String sb = "";
		if("12898".equals(Base.xxdm)){
			sb = "a.cwh";
		}else{
			sb = "to_number(a.cwh)";
		}
		sql.append("select a.*,b.xm,b.nj xsnj, b.xymc xsxymc, b.zymc xszymc, b.bjmc xsbjmc, ");
		sql.append("(select nvl2(t.xh,'寝室长','') from xg_gygl_new_gyglryb t ");
		sql.append("where rzzt = '在任' and a.lddm=t.lddm and a.qsh=t.qsh and a.xh=t.xh) qsz ");
		sql.append("from (select * from view_xg_gygl_new_cwxx a where lddm||qsh=?)a ")
			.append("left join view_xsbfxx b on a.xh=b.xh order by "+sb+" ");
		
		return dao.rsToVator(sql.toString(), inputValue, outputValue);
	}
	
	/**
	 * 导入数据
	 * @param filePath
	 * @param request
	 * @return
	 */
	public String importData(HttpServletRequest request,HttpServletResponse response){
		DAO dao = DAO.getInstance();
		int excelXsCount=0;//excel文件学生的记录数
		boolean b=false;
		//导入临时表
		try {
			//首先将临时表中的数据清除
			b=dao.runUpdate("delete from xg_gygl_new_impqsxxb", new String[]{});
			if(!b){
				return "临时表数据删除失败，请重新导入！";
			}
			
			String path=request.getAttribute("filepath").toString();
			Sheet sourceSheet = Workbook.getWorkbook(new File(path)).getSheet(0);			
			int sourceRowCount = sourceSheet.getRows();//获得源excel的行数
			String[] row;
			ArrayList<String> excelData_sql = new ArrayList<String>();//用于保存从excel获得的数据
			
			//模板验证
			row = ExcelMethods.getOneRow(sourceSheet,0,3);
			if(!(row[0].equals("楼栋代码")&&row[1].equals("寝室号")&&row[2].equals("导入更新字段"))){
				return "导入更新失败，请按照模板格式重新导入！";
			}
			
			for(int rownum=1;rownum<sourceRowCount;rownum++){//每条记录
				//添加要保存记录   start
				row = ExcelMethods.getOneRow(sourceSheet,rownum,5);
				excelData_sql.add("insert into xg_gygl_new_impqsxxb(lddm,qsh,drzd) " +
						"values( trim('"+row[0]+"'),trim('"+row[1]+"'),trim('"+row[2]+"') )");
			    //添加要保存记录   end
			}
			CommDAO commdao=new CommDAO();
			excelXsCount=excelData_sql.size();
			if(excelXsCount>0){
				b=commdao.saveArrDate(excelData_sql.toArray(new String[]{}));
				if(!b){
					return "数据字段过长，无法导入数据库！";
				}
			}else{
				return "文件中没有数据可导入！";
			}
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
			return "数据导入临时表时出现异常！可能是数据字段过长导致！";

		}
		
		try {
			
			//标记导入数据中，楼栋代码，寝室号，床位号，学号为空的数据
			String sql="update xg_gygl_new_impqsxxb a set mark='0' " +
			"where not exists (select 1 from xg_gygl_new_qsxxb b where a.lddm=b.lddm and a.qsh=b.qsh)";
			b=dao.runUpdate(sql, new String[]{});
			if(!b){
				return "导入数据系统无对应信息标记失败！";
			}
			

			//首先更新学号和入住时间
//			HttpSession session = request.getSession();
//			String rzczr=session.getAttribute("userName").toString();//设置操作人
			String drzd=request.getParameter("drzd");
			if(!("qsdh".equals(drzd)||"sfbz".equals(drzd)||"ywkt".equals(drzd))){
				return "导入更新字段无效！";
			}
			sql="update xg_gygl_new_qsxxb a set "+drzd+" = (select drzd from xg_gygl_new_impqsxxb b where a.lddm=b.lddm and a.qsh=b.qsh) " +
				" where exists (select 1 from xg_gygl_new_impqsxxb c where c.mark<>'0' and a.lddm=c.lddm and a.qsh=c.qsh)";
			b=dao.runUpdate(sql, new String[]{});
			if(!b){
				return "学号更新失败！";
			}
		} catch (Exception e1) {
			// TODO 自动生成 catch 块
			e1.printStackTrace();
			return "数据更新阶段发生异常！";
		}
		
		List<String[]> xsList=new ArrayList<String[]>();
		try {
			String sql="select lddm,qsh,drzd,'系统无对应记录' bz from xg_gygl_new_impqsxxb where mark='0'";
			String[] outputValue=new String[]{"lddm","qsh","drzd","bz"};
			String[] colListCN=new String[]{"楼栋代码","寝室号","更新字段","导入失败原因"};
			xsList=dao.rsToVator(sql, new String[]{}, outputValue);

			if(xsList!=null&&xsList.size()>0){
				response.reset();
				response.setContentType("application/vnd.ms-excel");
				Excel2Oracle.exportData(xsList,outputValue,colListCN, response.getOutputStream());
				request.setAttribute("sfdcExcel", "yes");
			}
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
			response.reset();
			response.setContentType("text/html");
			return "数据导入成功，但不符合规范的"+((xsList==null||xsList.size()==0)?"":(xsList.size()+"条"))+"数据在返回时发生了异常！";
		}
		
		return "导入成功！";
	}
	
	/**
	 * 获取寝室分配入住信息
	 * @param model
	 * @return
	 */
	public HashMap<String,String> getQsfprzInfo(String pkValue){
		String sql="select a.cws,a.xydm,a.nj,b.ldxb from xg_gygl_new_qsxxb a,xg_gygl_new_ldxxb b where a.lddm=b.lddm and b.lddm||a.qsh=?";
		HashMap<String,String> map=dao.getMapNotOut(sql, new String[]{pkValue});
		
		sql="select count(1) num from xg_gygl_new_cwxxb where lddm||qsh=? and (xydm is not null or nj is not null or xh is not null)";
		String num=dao.getOneRs(sql, new String[]{pkValue}, "num");
		map.put("cwfprzgs", num);//床位分配或入住个数
		
		if ("11799".equals(Base.xxdm)) {
			map.put("xbsfkxg", "true");
		}else {
			if("混住".equals(map.get("ldxb"))&&Base.isNull(map.get("xydm"))&&Base.isNull(map.get("nj"))&&"0".equals(num)){
				map.put("xbsfkxg", "true");//性别是否可修改
			}else{
				map.put("xbsfkxg", "false");
			}
		}
		return map;
	}
	/**
	 * 获取寝室号
	 * @param tj
	 * @return
	 * @throws SQLException 
	 */
	public String[] getQshs(String tj) throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append("select lddm||qsh pk from view_xg_gygl_new_qsxx a where sffp ='是' and rzqk ='全空'");
		return dao.getArray(sql+tj, new String[]{}, "pk");
	}
	
	/**
	 * 初始化寝室所属
	 * @param myForm
	 * @param user
	 * @return
	 * @throws Exception 
	 */
	public String initQsss(QsglForm myForm,User user) throws Exception{
		String[] pk_cw = myForm.getPrimarykey_checkVal();
		List<String[]> list = new ArrayList<String[]>();
		
		if(pk_cw==null||pk_cw.length==0){
			return "没有可初始化的寝室";
		}
		for(int i=0;i<pk_cw.length;i++){
			list.add(new String[]{pk_cw[i]});
		}
		String sql = "update xg_gygl_new_qsxxb a set xydm='',nj='' where lddm||qsh = ? and " +
		"exists (select 1 from xg_gygl_new_cwxxb x where x.lddm=a.lddm and x.qsh=a.qsh " +
		" and x.xh is null)";		
		boolean res = saveArrDate(sql,list, "xg_gygl_new_qsxxb", user);
		
		if(res&&"是".equals(myForm.getSfqccwss())){//清除床位所属
			sql ="update xg_gygl_new_cwxxb a set xydm='',nj='',bjdm='' where lddm||qsh = ? and a.xh is null ";		
			res = saveArrDate(sql,list, "xg_gygl_new_cwxxb", user);
			if(res){
				return "初始化成功";
			}else{
				return "寝室初始化成功，床位初始化失败";
			}
		}
		
		if(res){
			return "初始化成功";
		}else{
			return "初始化失败";
		}
	}
	
	/**
	 * 获取可初始化寝室数
	 * @param model
	 * @return
	 */
	public String getKcshqss(QsglModel model) throws Exception{
		String[] sfhkcw=model.getSearchModel().getSearch_tj_sf();//临时移除掉是否含空床位
		model.getSearchModel().setSearch_tj_sf(null);
		//高级查询条件
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		// 高级查询输入值
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		model.getSearchModel().setSearch_tj_sf(sfhkcw);
		StringBuilder sql = new StringBuilder();
		sql.append("select count(1) num from ")
		.append("view_xg_gygl_new_qsxx a where sffp ='是' and rzqk ='全空'  ");
							
		String num=dao.getOneRs(sql.toString()+searchTj, inputV, "num");
		return num;
	}
	
	/**
	 * 寝室信息管理 自定义 导出 
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	public List<HashMap<String,String>> qsxxglExportdata(QsglModel model) throws Exception{
		
		//高级查询条件
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		// 高级查询输入值
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		
		StringBuilder sql = new StringBuilder();
		List<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>();				
		sql.append("select rownum r,a.* from (select rownum rr,a.*,a.lddm||a.qsh pkValue from view_xg_gygl_new_qsxx a ")
			.append(") a where 1=1 ");
		
		String[] output = new String[]{"pkValue","ldmc","qsh","qsxb", "nj", "xymc","qscws","blcws","wzcws","rzqk"};
				
		list = CommonQueryDAO.commonQueryforExportList(sql.toString(), searchTj, inputV, output, model);
		return list;
	}

	/** 
	 * @描述:(获取楼层性别)
	 * @作者：cmj [工号：913]
	 * @日期：2013-8-20 下午03:34:21
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param lddm
	 * @param ch
	 * @return
	 * Map<String,String> 返回类型 
	 * @throws 
	 */
	public Map<String, String> getLcXb(String lddm, String ch) {
		// TODO 自动生成方法存根
		StringBuilder sql = new StringBuilder();
		sql.append("select distinct qsxb from xg_gygl_new_qsxxb where lddm=? and ch=?");
		return dao.getMapNotOut(sql.toString(), new String[]{lddm, ch});
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述: 保存寝室规格批量维护
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-6-27 上午10:06:33
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveplwhTypeOfQs(QsglForm para,String[] params) throws Exception{
		StringBuilder sql = new StringBuilder();
		List<String> paraList = new ArrayList<String>();
		sql.append(" update xg_gygl_new_qsxxb set cws = ?,sfbz=?,ywkt = ?" );
		if ("11799".equals(Base.xxdm)) {
			sql.append(",qsxb=?");
		}
		sql.append(" where lddm || qsh in(");
		paraList.add(para.getCws());
		paraList.add(para.getSfbz());
		paraList.add(para.getYwkt());
		if ("11799".equals(Base.xxdm)) {
			paraList.add(para.getQsxb());
		}
		for (int i = 0; i < params.length; i++) {
			sql.append("?");
			paraList.add(params[i]);
			if(i != params.length-1){
				sql.append(",");
			}
		}
		sql.append(")");
		return dao.runUpdate(sql.toString(), paraList.toArray(new String[]{}));
	}
	
	/**
	 * 
	 * @描述: 获取寝室信息List
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-6-27 下午05:35:34
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param params
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getQsxxList(String[] params){
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from xg_gygl_new_qsxxb where lddm || qsh in(");
		for (int i = 0; i < params.length; i++) {
			sql.append("?");
			if(i != params.length-1){
				sql.append(",");
			}
		} 
		sql.append(" )");
		sql.append(" ");
		sql.append(" ");
		sql.append(" ");
		return dao.getListNotOut(sql.toString(), params);
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述:保存寝室信息
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-6-27 下午05:49:20
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param params
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveCwxx(List<String[]> params) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" insert into xg_gygl_new_cwxxb(lddm,qsh,cwh");
		sql.append(")values(?,?,?");
		sql.append(")");
		return dao.runBatchBoolean(sql.toString(), params);
	}
	
	/**
	 * 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-6-27 下午05:53:27
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param params
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean delQsxx(String[] params) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" delete from xg_gygl_new_cwxxb where lddm || qsh in(");
		for (int i = 0; i < params.length; i++) {
			sql.append("?");
			if(i != params.length-1){
				sql.append(",");
			}
		} 
		sql.append(" )");
		sql.append(" ");
		sql.append(" ");
		sql.append(" ");
		return dao.runUpdate(sql.toString(), params);
	}
}
