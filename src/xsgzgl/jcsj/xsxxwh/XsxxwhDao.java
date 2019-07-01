package xsgzgl.jcsj.xsxxwh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.utils.CommonQueryDAO;

public class XsxxwhDao extends DAO{
	
	
	/**
	 * 获取学生信息列表
	 * @param model
	 * @return
	 */
	public List<String[]> getXsxxList(XsxxwhForm model) throws Exception{
		// 高级查询条件
//		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		// 高级查询输入值
//		String[] inputV = SearchService.getTjInput(model.getSearchModel());

		//权限控制
//		SearchService searchService=new SearchService();
//		String searchTjQx = searchService.getSearchTjByUser(request, "","bmdm", null); 	//学院用户
		
		String searchTj="";
		List<String> inputV=new ArrayList<String>();
		if(!Base.isNull(model.getNj())){
			searchTj+=" and nj=? ";
			inputV.add(model.getNj());
		}
		if(!Base.isNull(model.getBmdm())){
			searchTj+=" and bmdm=? ";
			inputV.add(model.getBmdm());
		}
		if(!Base.isNull(model.getZydm())){
			searchTj+=" and zydm=? ";
			inputV.add(model.getZydm());
		}
		if(!Base.isNull(model.getBjdm())){
			searchTj+=" and bjdm=? ";
			inputV.add(model.getBjdm());
		}
		if(!Base.isNull(model.getQuery_text())){
			searchTj+=" and (xh like '%'||?||'%' or xm like '%'||?||'%' or sfzh like '%'||?||'%')";
			inputV.add(model.getQuery_text());
			inputV.add(model.getQuery_text());
			inputV.add(model.getQuery_text());
		}
		if("是".equals(model.getQuery_sfycsj())){
			searchTj+=" and ycyy is not null ";
		}else if("否".equals(model.getQuery_sfycsj())){
			searchTj+=" and ycyy is null ";
		}
		
		String sql = "select rownum r, a.* from (" +
				"select a.xh,a.xm,a.xb,a.nj,d.bmmc,c.zymc,b.bjmc,e.ycyy," +
				"a.sfzh,b.bjdm,c.zydm,d.bmdm from xsxxb a left join xg_jcsj_bjdmb b " +
				"on a.bjdm=b.bjdm left join xg_jcsj_zydmb c on b.sszydm=c.zydm left join xg_jcsj_bmdmb d on c.ssbmdm=d.bmdm "+ 
				" left join xg_excdatalsb e on a.xh=e.pk and e.ycb='xsxxb' order by ycyy) a where 1 = 1";

		String[] output = new String[] { "xh","xh", "xm", "xb","nj","bmmc","zymc","bjmc","ycyy"};
		return CommonQueryDAO.commonQuery(sql, searchTj, inputV.toArray(new String[]{}),
				output, model);
	}
	
	/**
	 * 删除学生信息
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean deleteXsxxInfo(XsxxwhForm model) throws Exception{
		String sql="delete from xsxxb where xh=?";
		boolean b=this.runUpdate(sql, new String[]{model.getXh()});
		return b;
	}
	
	/**
	 * 检查异常数据
	 * @return
	 * @throws Exception 
	 */
	public String checkExceptionData() throws Exception{
		String msg="检测完成！";
		String table="xsxxb";
		//清除旧的异常数据
		String sql="delete from xg_excdatalsb where ycb=?";
		boolean b=runUpdate(sql, new String[]{table});
		if(!b){
			msg="旧异常数据清除失败";
			return msg;
		}
		//1.检验班级代码是否存在班级代码表中
		String ycyy="班级代码不存在班级代码表中";
		sql="insert into xg_excdatalsb(pk,ycyy,ycb) select xh,? ycyy,? ycb from xsxxb a " +
			"where not exists (select 1 from xg_jcsj_bjdmb x where x.bjdm=a.bjdm)";
		b=runUpdate(sql, new String[]{ycyy,table});
		if(!b){
			msg="班级代码不存在班级代码表中插入失败！";
			return msg;
		}
		//2.姓名不为空
		ycyy="姓名不可以为空";
		sql="insert into xg_excdatalsb(pk,ycyy,ycb) select xh,? ycyy,? ycb from xsxxb a " +
			"where not exists (select 1 from xg_excdatalsb x where x.ycb=? and x.pk=a.xh) " +
			"and a.xm is null";
		b=runUpdate(sql, new String[]{ycyy,table,table});
		if(!b){
			msg="姓名不可以为空插入失败！";
			return msg;
		}
		//3.检验学生性别
		ycyy="性别不是(男、女)或(1、2)";
		sql="insert into xg_excdatalsb(pk,ycyy,ycb) select xh,? ycyy,? ycb from xsxxb a " +
			"where not exists (select 1 from xg_excdatalsb x where x.ycb=? and x.pk=a.xh) " +
			"and nvl(a.xb,' ') not in('1','2','男','女') ";
		b=runUpdate(sql, new String[]{ycyy,table,table});
		if(!b){
			msg="性别不是(男、女)或(1、2)!";
			return msg;
		}
		return msg;
	}
	
	/**
	 * 获取部门列表
	 * @return
	 */
	public List<HashMap<String,String>> getBmdmList(){
		String sql="select bmdm,bmmc from xg_jcsj_bmdmb where bmlb='5' order by bmmc";
		return this.getList(sql, new String[]{}, new String[]{"bmdm","bmmc"});
	}
	
	/**
	 * 获取专业列表
	 * @return
	 */
	public List<HashMap<String,String>> getZydmList(String bmdm){
		String sql="select zydm dm,zymc mc from xg_jcsj_zydmb where ssbmdm=? order by zymc";
		return this.getList(sql, new String[]{bmdm}, new String[]{"dm","mc"});
	}
	
	/**
	 * 获取班级列表
	 * @return
	 */
	public List<HashMap<String,String>> getBjdmList(String zydm){
		String sql="select bjdm dm,bjmc mc from xg_jcsj_bjdmb where sszydm=? order by bjmc";
		return this.getList(sql, new String[]{zydm}, new String[]{"dm","mc"});
	}

}
