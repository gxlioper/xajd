package xsgzgl.dtjs.dtxxgl;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.util.MessageResources;

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

public class DtxxglDAO {
	MessageResources message = MessageResources
	.getMessageResources("config.ApplicationResources");
	DAO dao = DAO.getInstance();
	
	/**
	 * 查询党团信息
	 * @param form
	 * @return
	 */
	public List<String[]> dtxxQuery(User user,DtxxglForm form,String type,HttpServletRequest request) throws Exception{
		
		// 高级查询条件
		String searchTj = SearchService.getSearchTj(form.getSearchModel());
		
		String qxSql = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");
		
		// 高级查询输入值
		String[] inputV = SearchService.getTjInput(form.getSearchModel());
				
		StringBuilder sql = new StringBuilder();
		List<String[]> list = new ArrayList<String[]>();
		String[] output=new String[]{"pk","dis","xh", "xm", "xb", "zzmmmc", "xymc","zymc", "bjmc", "jdmc", "kssj"};
		
		sql.append("select rownum r,a.* from (select a.xh||a.jddm pk,a.*,b.xm,b.xb,b.zzmm,b.zzmmmc,b.xydm,b.xymc,b.zydm,b.zymc,b.bjdm,b.bjmc,c.jdmc,"+
				"case when a.dqjdbj ='0' then '' else 'disabled' end dis,case when a.dqjdbj ='0' then '否' else '是' end sfdqjd "+
				"from xg_dtjs_xsdtxxjlb a left join view_xsbfxx b on a.xh=b.xh left join xg_dtjs_jbszb c on a.jddm=c.jddm order by b.nj,b.xydm,b.zydm,b.zydm,b.xh) a where 1=1");
		sql.append(qxSql);
		
		if("manage".equalsIgnoreCase(type)){
			sql.append(" and a.dqjdbj = '1' ");
		}else{
			output=new String[]{"pk","dis","xh", "xm", "xb", "zzmmmc", "xymc","zymc", "bjmc", "jdmc", "kssj", "sfdqjd"};			
		}				
		
				
		try {
			list = CommonQueryDAO.commonQuery(sql.toString(), searchTj , inputV, output, form);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public List<String[]> dtxxQuery2(User user,DtxxglForm form,String type,HttpServletRequest request) throws Exception{
		
		// 高级查询条件
		String searchTj = SearchService.getSearchTj(form.getSearchModel());
		
		String qxSql = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");
		
		// 高级查询输入值
		String[] inputV = SearchService.getTjInput(form.getSearchModel());
				
		StringBuilder sql = new StringBuilder();
		List<String[]> list = new ArrayList<String[]>();
		String[] output=new String[]{"pk","dis","xh", "xm", "xb", "zzmmmc", "xymc","zymc", "bjmc", "jdmc", "kssj", "sfdqjd"};
		
		sql.append("select rownum r,a.* from (select a.xh||a.jddm pk,a.*,b.xm,b.xb,b.zzmm,b.zzmmmc,b.xydm,b.xymc,b.zydm,b.zymc,b.bjdm,b.bjmc,c.jdmc,"+
				"case when a.dqjdbj ='0' then '' else 'disabled' end dis,case when a.dqjdbj ='0' then '否' else '是' end sfdqjd "+
				"from (select xh,max(jddm) jddm from xg_dtjs_xsdtxxjlb group by xh) d left join xg_dtjs_xsdtxxjlb a on a.xh=d.xh and a.jddm=d.jddm left join view_xsbfxx b on d.xh=b.xh left join xg_dtjs_jbszb c on d.jddm=c.jddm) a where 1=1");
		sql.append(qxSql);
		
		
				
		try {
			list = CommonQueryDAO.commonQuery(sql.toString(), searchTj , inputV, output, form);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	/**
	 * @param user
	 * @param myForm
	 * @param type
	 * @param request
	 * @return
	 */
	public List<String[]> xytjQuery(User user, DtxxglForm myForm, String type,
			HttpServletRequest request)throws Exception {
		// TODO 自动生成方法存根
		String searchTj = SearchService.getSearchTj(myForm.getSearchModel());
		String[] inputV = SearchService.getTjInput(myForm.getSearchModel());
		List<String[]> list=new ArrayList<String[]>();
		String[] output = new String[]{"xymc","zrs","ty","per_ty","tys","per_tys","yb","per_yb","dy","per_dy"};
		String sql="select b.xymc,b.zrs,a.ty,CONCAT(TO_CHAR(a.ty/b.zrs*100,'990.99'),'%') per_ty,a.tys,CONCAT(TO_CHAR(a.tys/b.zrs*100,'990.99'),'%') per_tys,a.yb,CONCAT(TO_CHAR(a.yb/b.zrs*100,'990.99'),'%') per_yb,a.dy,CONCAT(TO_CHAR(a.dy/b.zrs*100,'990.99'),'%') per_dy from"+
		"(select a.xymc, (case when sum(a.jdmc02) is null then 0 else sum(a.jdmc02) end) ty,(case when sum(a.jdmc04) is null then 0 else sum(a.jdmc04) end) tys,(case when sum(a.jdmc07) is null then 0 else sum(a.jdmc07) end) yb,(case when sum(a.jdmc08) is null then 0 else sum(a.jdmc08) end) dy "+
		"from(select b.xymc,a.xh,a.jdmc02,a.jdmc04,a.jdmc07,a.jdmc08 from(select a.xh,b.xymc,a.jdmc02,a.jdmc04,a.jdmc07,a.jdmc08 "+
		"from(select a.xh, case when jddm='02' then 1 else 0 end jdmc02,case when jddm='04' then 1 else 0 end jdmc04,case when jddm='07' then 1 else 0 end jdmc07,case when jddm='08' then 1 else 0 end jdmc08 "+
		"from(select xh,max(jddm) jddm from xg_dtjs_xsdtxxjlb group by xh)a)a left join view_xsbfxx b on a.xh=b.xh)a right join (select distinct xymc from view_njxyzybj)b on a.xymc=b.xymc)a group by a.xymc)a left join (select xymc, count(xh) zrs from view_xsbfxx group by xymc)b on a.xymc=b.xymc";
		String tmpsql=sql;
		sql=sql+" union all select '总计' xymc,sum(zrs),sum(ty),CONCAT(TO_CHAR(sum(ty)/sum(zrs)*100,'990.99'),'%') per_ty,sum(tys),CONCAT(TO_CHAR(sum(tys)/sum(zrs)*100,'990.99'),'%') per_tys,sum(yb),CONCAT(TO_CHAR(sum(yb)/sum(zrs)*100,'990.99'),'%') per_yb,sum(dy),CONCAT(TO_CHAR(sum(dy)/sum(zrs)*100,'990.99'),'%') per_dy from ("+tmpsql+")";
		list = CommonQueryDAO.xytjQuery(sql.toString(), searchTj , inputV, output, myForm);
		return list;
	}
	
	/**
	 * 批量删除学生党团信息(单个学生)
	 * */
	public boolean dtxxDel(List<String[]> params){
		boolean flag = false;
		
		StringBuilder sql = new StringBuilder();
		sql.append("delete xg_dtjs_xsdtxxjlb where xh=?");
		
		try {
			int[] rs = dao.runBatch(sql.toString(), params);
			flag = dao.checkBatchResult(rs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return flag; 
	}
	
	/**
	 * 批量删除学生党团信息(单条信息)
	 * */
	public boolean delDtxx(List<String[]> params){
		boolean flag = false;
		
		StringBuilder sql = new StringBuilder();
		sql.append("delete xg_dtjs_xsdtxxjlb where xh||jddm=?");
		
		try {
			int[] rs = dao.runBatch(sql.toString(), params);
			flag = dao.checkBatchResult(rs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return flag; 
	}
	
	/**
	 * 获取基本设置中的党团建设阶段list
	 * */
	public List<HashMap<String,String>> getJdList(){
		return dao.getArrayList("select * from xg_dtjs_jbszb order by to_number(jdsx)", 
				new String[]{}, new String[]{"jddm","jdmc","jdsx","sfszjssj"});
	}
	/**
	 * 获取阶段代码名称
	 */
	public List<HashMap<String, String>> getJdList1(){
		
		return null;
	}
	
	/**
	 * 获取学生的党团建设阶段list
	 * */
	public List<HashMap<String,String>> getXsjdList(String xh){
		return dao.getArrayList("select a.*,b.kssj,b.jssj,b.dqjdbj,b.zd1,b.zd2,b.zd3,b.zd4,b.zd5 from xg_dtjs_jbszb a " +
				"left join xg_dtjs_xsdtxxjlb b on a.jddm = b.jddm and b.xh =? order by to_number(a.jdsx)", 
				new String[]{xh}, new String[]{"jddm","jdmc","jdsx","sfszjssj","dqjdbj","kssj","jssj","zd1","zd2","zd3","zd4","zd5"});
	}
	/**
	 * 获取学生的党团建设阶段其它信息
	 * */
	public HashMap<String,String> getXsjdOther(String xh){
		return dao.getMap(" select distinct b.xh,b.zd1,b.zd2,b.zd3,b.zd4,b.zd5,b.zd6,b.zd7 from xg_dtjs_xsdtxxjlb  b where b.xh =?  " ,
				new String[]{xh}, new String[]{"zd1","zd2","zd3","zd4","zd5","zd6","zd7"});
	}
	
	/**
	 * 获取学生当前阶段的阶段信息
	 * */
	public HashMap<String,String> getDqjd(String xh){
		return dao.getMap("select a.*,b.kssj,b.jssj,b.dqjdbj from xg_dtjs_jbszb a " +
				"left join xg_dtjs_xsdtxxjlb b on a.jddm = b.jddm and b.xh =? where dqjdbj = '1'", 
				new String[]{xh}, new String[]{"jddm","jdmc","jdsx"});
	}
	
	/**
	 * 保存党团信息
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	public boolean saveDtxx(DtxxglForm model) throws Exception{
		boolean b=false;
		String xh=model.getXh();
		String curr_jddm=model.getCurr_jddm();
		String[] jddm=model.getJddm();
		String[] kssj=model.getKssj();
		String[] jssj=model.getJssj();
		model.setZd7(model.getZd7()==null?"":model.getZd7());
		model.setZd6(model.getZd6()==null?"":model.getZd6());
		if (Base.isNull(curr_jddm) || jddm == null || jddm.length == 0
				|| kssj == null || kssj.length == 0 || jssj == null
				|| jssj.length == 0 || jddm.length != kssj.length
				|| jddm.length != jssj.length) {
			return b;
		}
		List<String> sql=new ArrayList<String>();
		for(int i=0;i<jddm.length;i++){
			if(curr_jddm.equals(jddm[i])){//最后一个
				sql.add("insert into xg_dtjs_xsdtxxjlb (xh,jddm,kssj,jssj,dqjdbj,zd1,zd2,zd3,zd4,zd5,zd6,zd7) values('"
						+xh+"','"+jddm[i]+"','"+kssj[i]+"','"+jssj[i]+"','1','"+model.getZd1()+"','"+model.getZd2()+"','"+model.getZd3()+"','"+model.getZd4()+"','"+model.getZd5()+"','"+model.getZd6()+"','"+model.getZd7()+"')");
				break;
			}else{
				if(!(Base.isNull(kssj[i])&&Base.isNull(jssj[i]))){
					sql.add("insert into xg_dtjs_xsdtxxjlb (xh,jddm,kssj,jssj,dqjdbj,zd1,zd2,zd3,zd4,zd5,zd6,zd7) values('"
							+xh+"','"+jddm[i]+"','"+kssj[i]+"','"+jssj[i]+"','0','"+model.getZd1()+"','"+model.getZd2()+"','"+model.getZd3()+"','"+model.getZd4()+"','"+model.getZd5()+"','"+model.getZd6()+"','"+model.getZd7()+"')");
				}
			}
		}
//		CommDAO dao=new CommDAO();
		b=dao.saveArrDate(sql.toArray(new String[]{}));
		return b;
	}
	
	/**
	 * 修改党团信息
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	public boolean updateDtxx(DtxxglForm model) throws Exception{
		boolean b=false;
		String xh=model.getXh();
		String curr_jddm=model.getCurr_jddm();
		String[] jddm=model.getJddm();
		String[] kssj=model.getKssj();
		String[] jssj=model.getJssj();
		model.setZd7(model.getZd7()==null?"":model.getZd7());
		model.setZd6(model.getZd6()==null?"":model.getZd6());
		if (Base.isNull(curr_jddm) || jddm == null || jddm.length == 0
				|| kssj == null || kssj.length == 0 || jssj == null
				|| jssj.length == 0 || jddm.length != kssj.length
				|| jddm.length != jssj.length) {
			return b;
		}
		List<String> sql=new ArrayList<String>();
		for(int i=0;i<jddm.length;i++){
			sql.add("delete from xg_dtjs_xsdtxxjlb where xh='"+xh+"' and jddm='"+jddm[i]+"'");
			if(curr_jddm.equals(jddm[i])){//最后一个
				sql.add("insert into xg_dtjs_xsdtxxjlb (xh,jddm,kssj,jssj,dqjdbj,zd1,zd2,zd3,zd4,zd5,zd6,zd7) values('"
						+xh+"','"+jddm[i]+"','"+kssj[i]+"','"+jssj[i]+"','1','"+model.getZd1()+"','"+model.getZd2()+"','"+model.getZd3()+"','"+model.getZd4()+"','"+model.getZd5()+"','"+model.getZd6()+"','"+model.getZd7()+"')");
				break;
			}else{
				if(!(Base.isNull(kssj[i])&&Base.isNull(jssj[i]))){
					sql.add("insert into xg_dtjs_xsdtxxjlb (xh,jddm,kssj,jssj,dqjdbj,zd1,zd2,zd3,zd4,zd5,zd6,zd7) values('"
							+xh+"','"+jddm[i]+"','"+kssj[i]+"','"+jssj[i]+"','0','"+model.getZd1()+"','"+model.getZd2()+"','"+model.getZd3()+"','"+model.getZd4()+"','"+model.getZd5()+"','"+model.getZd6()+"','"+model.getZd7()+"')");
				}
			}
		}
//		CommDAO dao=new CommDAO();
		b=dao.saveArrDate(sql.toArray(new String[]{}));
		return b;
	}
	
	/**
	 * 导入数据
	 * @param filePath
	 * @param request
	 * @return
	 */
	public String importData(HttpServletRequest request,HttpServletResponse response){
		//首先处理阶段代码 start
		List<HashMap<String,String>> jddmList=getJdList();//获取阶段列表
		String[] jddms=new String[jddmList.size()];//阶段代码数组
		String[] sfszjssj=new String[jddmList.size()];//是否设置结束时间
		HashMap<String,Integer> jddmMap=new HashMap<String, Integer>();//阶段代码Map,用于获取指定阶段代码的索引
		for(int i=0;i<jddmList.size();i++){
			jddms[i]=jddmList.get(i).get("jddm");
			jddmMap.put(jddms[i], i);
			sfszjssj[i]=jddmList.get(i).get("sfszjssj");
		}
		//阶段代码end
		DAO dao = DAO.getInstance();
		int excelXsCount=0;//excel文件学生的记录数
		
		boolean b=false;
		//导入临时表
		try {
			//首先将临时表中的数据清除
			b=dao.runUpdate("delete from xg_dtjs_impxsdtxxjlb", new String[]{});
			if(!b){
				return "临时表数据删除失败，请重新导入！";
			}
			
			String path=request.getAttribute("filepath").toString();
			Sheet sourceSheet = Workbook.getWorkbook(new File(path)).getSheet(0);
			int sourceRowCount = sourceSheet.getRows();//获得源excel的行数
			int dqColIndex=0;
			String[] row;
			String dqjddm=null;
			ArrayList<String> excelData_sql = new ArrayList<String>();//用于保存从excel获得的数据
			for(int rownum=1;rownum<sourceRowCount;rownum++){//每条记录
				//添加要保存记录   start
				row = ExcelMethods.getOneRow(sourceSheet,rownum,2+jddms.length*2);
				dqjddm=Base.chgNull(row[1], "", 0);
				if("".equals(dqjddm)){//当前阶段代码为空
					excelData_sql.add("insert into xg_dtjs_impxsdtxxjlb(xh,mark,bz) " +
							"values(trim('"+row[0]+"'),'0','当前阶段代码为空')");
				}else if(jddmMap.get(dqjddm)==null){//阶段代码不正确
					excelData_sql.add("insert into xg_dtjs_impxsdtxxjlb(xh,jddm,mark,bz) " +
							"values(trim('"+row[0]+"'),'"+dqjddm+"','0','当前阶段代码不正确')");
				}else{
					dqColIndex=jddmMap.get(dqjddm)*2+2;
					if(Base.isNull(row[dqColIndex])){//判断当前阶段开始时间是否为空
						excelData_sql.add("insert into xg_dtjs_impxsdtxxjlb(xh,jddm,mark,bz) " +
								"values(trim('"+row[0]+"'),'"+dqjddm+"','0','当前阶段开始时间为空')");
					}else{
						for(int i=0;i<jddms.length;i++){
							if(jddms[i].equals(dqjddm)){//判断阶段是否尾当前阶段代码
								excelData_sql.add("insert into xg_dtjs_impxsdtxxjlb(xh,jddm,kssj,jssj,dqjdbj,mark) " +
										"values(trim('"+row[0]+"'),'"+jddms[i]+"',trim('"+row[i*2+2]+"'),trim('"+row[i*2+3]+"'),'1','1')");
								break;
							}else if(!Base.isNull(row[i*2+2])||("是".equals(sfszjssj[i])&&!Base.isNull(row[i*2+3]))){
								excelData_sql.add("insert into xg_dtjs_impxsdtxxjlb(xh,jddm,kssj,jssj,dqjdbj,mark) " +
										"values(trim('"+row[0]+"'),'"+jddms[i]+"',trim('"+row[i*2+2]+"'),trim('"+row[i*2+3]+"'),'0','1')");
							}
						}
					}
				}
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
			//第一步：标记导入数据中，学号为空的数据
			String sql="update xg_dtjs_impxsdtxxjlb a set mark='0',bz='导入学号字段为空' " +
			"where a.mark<>'0' and a.xh is null";
			b=dao.runUpdate(sql, new String[]{});
			if(!b){
				return "导入学号字段为空标记失败！";
			}
			//第二步：标记导入数据中，学号不存在于系统中的数据
			sql="update xg_dtjs_impxsdtxxjlb a set mark='0',bz='该学号学生不存在' "+
				"where mark<>'0' and not exists (select 1 from view_xsjbxx b where a.xh=b.xh)";
			b=dao.runUpdate(sql, new String[]{});
			if(!b){
				return "学号学生不存在标记失败！";
			}
			//第三步：学生的数据以存在于党团信息记录中
			sql="update xg_dtjs_impxsdtxxjlb a set mark='0',bz='该学号学生信息已存在' "+
			"where mark<>'0' and exists (select 1 from xg_dtjs_xsdtxxjlb b where a.xh=b.xh)";
			b=dao.runUpdate(sql, new String[]{});
			if(!b){
				return "学号学生信息存在标记失败！";
			}
			//第四步：学号阶段重复
			sql="update xg_dtjs_impxsdtxxjlb a set mark='0',bz='学号阶段重复' "+
			"where mark<>'0' and "+
			"exists (select 1 from " +
			"(select xh,jddm from xg_dtjs_impxsdtxxjlb where mark<>'0' group by xh,jddm having count(1)>1) b " +
			"where a.xh=b.xh)";
			b=dao.runUpdate(sql, new String[]{});
			if(!b){
				return "学号阶段重复标记失败！";
			}
			//第五步：验证时间格式
			sql="update xg_dtjs_impxsdtxxjlb a set mark='0',bz='时间格式不正确' " +
			"where a.mark<>'0' and exists (select 1 from (" +
			"select distinct xh from xg_dtjs_impxsdtxxjlb where mark<>'0' and (" +
			"(kssj is not null and (length(kssj)<>8 and length(kssj)<>10)) or " +
			"(jssj is not null and (length(jssj)<>8 and length(jssj)<>10))" +
			")) b where a.xh=b.xh)";
			b=dao.runUpdate(sql, new String[]{});
			if(!b){
				return "时间格式不正确标记失败！";
			}
			//将数据插入到正式表中
			sql="insert into xg_dtjs_xsdtxxjlb(xh,jddm,kssj,jssj,dqjdbj) " +
				"select xh,jddm,kssj,jssj,dqjdbj from xg_dtjs_impxsdtxxjlb where mark='1'";
			b=dao.runUpdate(sql, new String[]{});
			if(!b){
				return "数据转入正式库失败！";
			}
		} catch (Exception e1) {
			// TODO 自动生成 catch 块
			e1.printStackTrace();
			return "数据更新阶段发生异常！";
		}
		
		List<String[]> xsList=new ArrayList<String[]>();
		try {
			String sql="select distinct xh,bz from xg_dtjs_impxsdtxxjlb where mark='0' order by bz";
			String[] outputValue=new String[]{"xh","bz"};
			String[] colListCN=new String[]{"学号","导入失败原因"};
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
	 * 导出数据
	 * @param form
	 * @param type
	 * @param user 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> exportData(DtxxglForm form,String type,HttpServletResponse response, User user) throws Exception{
		
        Base.YXPZXY_KEY = message.getMessage("lable.xb");
		//首先处理行转列 start
		List<String> output=new ArrayList<String>();//输出列
		output.add("xh");
		output.add("xm");
		output.add("xb");
		output.add("zzmmmc");
		output.add("xymc");
		output.add("zymc");
		output.add("bjmc");
		output.add("jdmc");
		List<String> colListCN=new ArrayList<String>();//输出列表头
		colListCN.add("学号");
		colListCN.add("姓名");
		colListCN.add("性别");
		colListCN.add("政治面貌");
		colListCN.add(Base.YXPZXY_KEY);
		colListCN.add("专业");
		colListCN.add("班级");
		colListCN.add("当前阶段");
		
		StringBuffer hzl_sql=new StringBuffer();//行转列sql
		StringBuffer hzl_sql_case=new StringBuffer();//行转列行列转换sql
		StringBuffer hzl_sql_max=new StringBuffer();//行转列合并sql
		List<HashMap<String,String>> jddmList=getJdList();//阶段代码列表
		String jddm,jdmc;
		for(int i=0;i<jddmList.size();i++){
			jddm=jddmList.get(i).get("jddm");
			jdmc=jddmList.get(i).get("jdmc");
			hzl_sql_case.append(" , (case when jddm='"+jddm+"' then kssj else '' end) kssj"+jddm);
			hzl_sql_case.append(" , (case when jddm='"+jddm+"' then jssj else '' end) jssj"+jddm);
			hzl_sql_max.append(",max(kssj"+jddm+") kssj"+jddm);
			hzl_sql_max.append(",max(jssj"+jddm+") jssj"+jddm);
			output.add("kssj"+jddm);
			output.add("jssj"+jddm);
			colListCN.add(jdmc+"开始时间");
			colListCN.add(jdmc+"结束时间");
			
		}
		hzl_sql.append("select xh ");
		hzl_sql.append(hzl_sql_max);
		hzl_sql.append(" from ( ");//1#
		hzl_sql.append("select xh ");
		hzl_sql.append(hzl_sql_case);
		hzl_sql.append(" from xg_dtjs_xsdtxxjlb ");
		hzl_sql.append(" ) ");//1#
		hzl_sql.append(" group by xh ");
		
		//首先处理行转列 end
		//替换后表 start
		String xg_dtjs_xsdtxxjlb="(select a.*,b.jddm,b.dqjdbj from ("+hzl_sql+") a left join xg_dtjs_xsdtxxjlb b on a.xh=b.xh and b.dqjdbj='1')";
		//替换后表 end
		
		// 高级查询条件
		String searchTj = SearchService.getSearchTj(form.getSearchModel());
		// 高级查询输入值
		String[] inputV = SearchService.getTjInput(form.getSearchModel());
		String qxSql = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");
				
		StringBuilder sql = new StringBuilder();
		List<String[]> list = new ArrayList<String[]>();
		
		sql.append("select a.* from (select a.*,b.xm,b.xb,b.zzmm,b.zzmmmc,b.xydm,b.xymc,b.zydm,b.zymc,b.bjdm,b.bjmc,c.jdmc " +
				" from "+xg_dtjs_xsdtxxjlb+" a " +
				"left join view_xsbfxx b on a.xh = b.xh left join xg_dtjs_jbszb c on a.jddm = c.jddm " +
				"order by xydm,zydm,bjdm,a.xh,jdsx desc) a where 1=1 "+searchTj+qxSql);
		
		try {
			list = CommonQueryDAO.commonQueryNotFy(sql.toString(), "" , inputV, output.toArray(new String[]{}), form);
			Excel2Oracle.exportData(list,output.toArray(new String[]{}),colListCN.toArray(new String[]{}), response.getOutputStream());
		} catch (Exception e) {
		}
		
		return null;
	}
	
	public List<String[]> exportData2(DtxxglForm form,String type,HttpServletResponse response) throws Exception{
		Base.YXPZXY_KEY = message.getMessage("lable.xb");
		//首先处理行转列 start
		List<String> output=new ArrayList<String>();//输出列
		output.add("xh");
		output.add("xm");
		output.add("xb");
		output.add("zzmmmc");
		output.add("xymc");
		output.add("zymc");
		output.add("bjmc");
		output.add("jdmc");
		List<String> colListCN=new ArrayList<String>();//输出列表头
		colListCN.add("学号");
		colListCN.add("姓名");
		colListCN.add("性别");
		colListCN.add("政治面貌");
		colListCN.add(Base.YXPZXY_KEY);
		colListCN.add("专业");
		colListCN.add("班级");
		colListCN.add("当前阶段");
		
//		StringBuffer hzl_sql=new StringBuffer();//行转列sql
//		StringBuffer hzl_sql_case=new StringBuffer();//行转列行列转换sql
//		StringBuffer hzl_sql_max=new StringBuffer();//行转列合并sql
//		List<HashMap<String,String>> jddmList=getJdList();//阶段代码列表
//		String jddm,jdmc;
//		for(int i=0;i<jddmList.size();i++){
//			jddm=jddmList.get(i).get("jddm");
//			jdmc=jddmList.get(i).get("jdmc");
//			hzl_sql_case.append(" , (case when jddm='"+jddm+"' then kssj else '' end) kssj"+jddm);
//			hzl_sql_case.append(" , (case when jddm='"+jddm+"' then jssj else '' end) jssj"+jddm);
//			hzl_sql_max.append(",max(kssj"+jddm+") kssj"+jddm);
//			hzl_sql_max.append(",max(jssj"+jddm+") jssj"+jddm);
//			output.add("kssj"+jddm);
//			output.add("jssj"+jddm);
//			colListCN.add(jdmc+"开始时间");
//			colListCN.add(jdmc+"结束时间");
//			
//		}
//		hzl_sql.append("select xh ");
//		hzl_sql.append(hzl_sql_max);
//		hzl_sql.append(" from ( ");//1#
//		hzl_sql.append("select xh ");
//		hzl_sql.append(hzl_sql_case);
//		hzl_sql.append(" from xg_dtjs_xsdtxxjlb ");
//		hzl_sql.append(" ) ");//1#
//		hzl_sql.append(" group by xh ");
//		
//		//首先处理行转列 end
//		//替换后表 start
//		String xg_dtjs_xsdtxxjlb="(select a.*,b.jddm,b.dqjdbj from ("+hzl_sql+") a left join xg_dtjs_xsdtxxjlb b on a.xh=b.xh and b.dqjdbj='1')";
//		//替换后表 end
		
		// 高级查询条件
		String searchTj = SearchService.getSearchTj(form.getSearchModel());
		// 高级查询输入值
		String[] inputV = SearchService.getTjInput(form.getSearchModel());
				
		StringBuilder sql = new StringBuilder();
		List<String[]> list = new ArrayList<String[]>();
		
		sql.append("select b.xh,b.xm,b.XB,b.XYMC,b.ZZMMMC,b.ZYMC,b.BJMC,c.jdmc from (select xh,max(jddm) jddm from xg_dtjs_xsdtxxjlb group by xh) a left join view_xsbfxx b on a.xh=b.xh left join xg_dtjs_jbszb c on a.jddm=c.jddm where 1=1 "+searchTj);
		
		try {
			list = CommonQueryDAO.commonQueryNotFy(sql.toString(), "" , inputV, output.toArray(new String[]{}), form);
			Excel2Oracle.exportData(list,output.toArray(new String[]{}),colListCN.toArray(new String[]{}), response.getOutputStream());
		} catch (Exception e) {
		}
		
		return null;
	}
	
	/**
	 * 同步更新政治面貌
	 * @return
	 * @throws Exception 
	 */
	public boolean tbgxzzmm() throws Exception{
		//判断bks_xsjbxx是否是表,只有是表的情况是才进行该表的更新
		String bks_xsjbxx_isTable_sql="select count(1) num from user_tables where table_name='BKS_XSJBXX'";
		String num=new DAO().getOneRs(bks_xsjbxx_isTable_sql, new String[]{}, "num");
		
		String zdm = new DAO().getOneRs("select dyzd from xg_dtjs_jbszb where dyzd is not null and rownum=1", new String[]{}, "dyzd");
		
		boolean bks_xsjbxx_isTable="0".equals(num)?false:true;
		
		boolean b=false;
		List<String> sqls=new ArrayList<String>();
		sqls.add("update xsxxb x set "+zdm+" = (select dyz from (select xh,max(jdsx) jdsx from xg_dtjs_xsdtxxjlb a " +
				"left join xg_dtjs_jbszb b on a.jddm = b.jddm where b.dyz is not null group by xh) a " +
				"left join xg_dtjs_jbszb b on a.jdsx = b.jdsx where b.dyz is not null and a.xh= x.xh )where exists(" +
				"select xh from (select xh,max(jdsx) jdsx from xg_dtjs_xsdtxxjlb a " +
				"left join xg_dtjs_jbszb b on a.jddm = b.jddm where b.dyz is not null group by xh) a " +
				"left join xg_dtjs_jbszb b on a.jdsx = b.jdsx where b.dyz is not null and a.xh= x.xh )");
		if(bks_xsjbxx_isTable){
			sqls.add("update BKS_XSJBXX x set "+zdm+" = (select dyz from (select xh,max(jdsx) jdsx from xg_dtjs_xsdtxxjlb a " +
				"left join xg_dtjs_jbszb b on a.jddm = b.jddm where b.dyz is not null group by xh) a " +
				"left join xg_dtjs_jbszb b on a.jdsx = b.jdsx where b.dyz is not null and a.xh= x.xh )where exists(" +
				"select xh from (select xh,max(jdsx) jdsx from xg_dtjs_xsdtxxjlb a " +
				"left join xg_dtjs_jbszb b on a.jddm = b.jddm where b.dyz is not null group by xh) a " +
				"left join xg_dtjs_jbszb b on a.jdsx = b.jdsx where b.dyz is not null and a.xh= x.xh )");
		}
		CommDAO commdao=new CommDAO();
		b=commdao.saveArrDate(sqls.toArray(new String[]{}));
		return b;
	}
	
	/**
	 * 学生党团信息管理字定义导出
	 * @param form
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>>  xsxxglExportData(DtxxglForm form) throws Exception{
		//首先处理行转列 start
		Base.YXPZXY_KEY = message.getMessage("lable.xb"); 
		List<String> output=new ArrayList<String>();//输出列
		output.add("xh");
		output.add("xm");
		output.add("xb");
		output.add("zzmmmc");
		output.add("xymc");
		output.add("zymc");
		output.add("bjmc");
		output.add("jdmc");
		List<String> colListCN=new ArrayList<String>();//输出列表头
		colListCN.add("学号");
		colListCN.add("姓名");
		colListCN.add("性别");
		colListCN.add("政治面貌");
		colListCN.add(Base.YXPZXY_KEY);
		colListCN.add("专业");
		colListCN.add("班级");
		colListCN.add("当前阶段");
		
		StringBuffer hzl_sql=new StringBuffer();//行转列sql
		StringBuffer hzl_sql_case=new StringBuffer();//行转列行列转换sql
		StringBuffer hzl_sql_max=new StringBuffer();//行转列合并sql
		List<HashMap<String,String>> jddmList=getJdList();//阶段代码列表
		String jddm,jdmc;
		for(int i=0;i<jddmList.size();i++){
			jddm=jddmList.get(i).get("jddm");
			jdmc=jddmList.get(i).get("jdmc");
			hzl_sql_case.append(" , (case when jddm='"+jddm+"' then kssj else '' end) kssj"+jddm);
			hzl_sql_case.append(" , (case when jddm='"+jddm+"' then jssj else '' end) jssj"+jddm);
			hzl_sql_max.append(",max(kssj"+jddm+") kssj"+jddm);
			hzl_sql_max.append(",max(jssj"+jddm+") jssj"+jddm);
			output.add("kssj"+jddm);
			output.add("jssj"+jddm);
			colListCN.add(jdmc+"开始时间");
			colListCN.add(jdmc+"结束时间");
			
		}
		hzl_sql.append("select xh ");
		hzl_sql.append(hzl_sql_max);
		hzl_sql.append(" from ( ");//1#
		hzl_sql.append("select xh ");
		hzl_sql.append(hzl_sql_case);
		hzl_sql.append(" from xg_dtjs_xsdtxxjlb ");
		hzl_sql.append(" ) ");//1#
		hzl_sql.append(" group by xh ");
		
		//首先处理行转列 end
		//替换后表 start
		String xg_dtjs_xsdtxxjlb="(select a.*,b.jddm,b.dqjdbj from ("+hzl_sql+") a left join xg_dtjs_xsdtxxjlb b on a.xh=b.xh and b.dqjdbj='1')";
		//替换后表 end
		
		// 高级查询条件
		String searchTj = SearchService.getSearchTj(form.getSearchModel());
		// 高级查询输入值
		String[] inputV = SearchService.getTjInput(form.getSearchModel());
				
		StringBuilder sql = new StringBuilder();
		
		sql.append("select  rownum r, a.* from (select a.*,b.xm,b.xb,b.zzmm,b.zzmmmc,b.xydm,b.xymc,b.zydm,b.zymc,b.bjdm,b.bjmc,c.jdmc " +
				" from "+xg_dtjs_xsdtxxjlb+" a " +
				"left join view_xsbfxx b on a.xh = b.xh left join xg_dtjs_jbszb c on a.jddm = c.jddm " +
				"order by xydm,zydm,bjdm,a.xh,jdsx desc) a where 1=1 "+searchTj);

		return  CommonQueryDAO.commonQueryforExportList(sql.toString(), "" , inputV, output.toArray(new String[]{}), form);
	}
	
	/**
	 * 学生党团信息查询自定义导出
	 * @param form
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> xsdtxxcxExportData(DtxxglForm form,User user) throws Exception{
	
		// 高级查询条件
		String searchTj = SearchService.getSearchTj(form.getSearchModel());
		
		String qxSql = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");
		
		// 高级查询输入值
		String[] inputV = SearchService.getTjInput(form.getSearchModel());
				
		StringBuilder sql = new StringBuilder();

		String[] output=new String[]{"pk","dis","xh", "xm", "xb", "zzmmmc", "xymc","zymc", "bjmc", "jdmc", "kssj", "sfdqjd"};
		
		sql.append("select rownum r,a.* from (select a.xh||a.jddm pk,a.*,b.xm,b.xb,b.zzmm,b.zzmmmc,b.xydm,b.xymc,b.zydm,b.zymc,b.bjdm,b.bjmc,c.jdmc,"+
				"case when a.dqjdbj ='0' then '' else 'disabled' end dis,case when a.dqjdbj ='0' then '否' else '是' end sfdqjd "+
				"from xg_dtjs_xsdtxxjlb a left join view_xsbfxx b on a.xh=b.xh left join xg_dtjs_jbszb c on a.jddm=c.jddm order by b.nj,b.xydm,b.zydm,b.zydm,b.xh) a where 1=1");
		sql.append(qxSql);
		
		
				
		
		return  CommonQueryDAO.commonQueryforExportList(sql.toString(), searchTj , inputV, output, form);
		
	}

}
