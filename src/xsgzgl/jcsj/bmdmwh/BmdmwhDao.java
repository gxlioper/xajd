package xsgzgl.jcsj.bmdmwh;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.utils.CommonQueryDAO;

public class BmdmwhDao extends DAO{
	
	/**
	 * 获取部门信息类别
	 * @param model
	 * @return
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws SecurityException 
	 * @throws IllegalArgumentException 
	 */
	public List<String[]> getBmdmList(BmdmwhForm model) throws Exception{
		// 高级查询条件
//		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		// 高级查询输入值
//		String[] inputV = SearchService.getTjInput(model.getSearchModel());

		//权限控制
//		SearchService searchService=new SearchService();
//		String searchTjQx = searchService.getSearchTjByUser(request, "","bmdm", null); 	//学院用户
		
		String searchTj="";
		List<String> inputV=new ArrayList<String>();
		if(!Base.isNull(model.getQuery_bmlb())){
			searchTj+=" and bmlb=? ";
			inputV.add(model.getQuery_bmlb());
		}
		if(!Base.isNull(model.getQuery_text())){
			searchTj+=" and (bmdm like '%'||?||'%' or bmmc like '%'||?||'%')";
			inputV.add(model.getQuery_text());
			inputV.add(model.getQuery_text());
		}
		if("是".equals(model.getQuery_sfycsj())){
			searchTj+=" and ycyy is not null ";
		}else if("否".equals(model.getQuery_sfycsj())){
			searchTj+=" and ycyy is null ";
		}
		
		String sql = "select rownum r, a.* from (" +
				"select a.*,(case when bmlb='1' then '部门' when bmlb='5' then '学院' else '' end) bmlbmc," +
				"b.ycyy from xg_jcsj_bmdmb a left join xg_excdatalsb b on a.bmdm=b.pk and b.ycb='xg_jcsj_bmdmb' order by ycyy,bmdm) a where 1 = 1";

		String[] output = new String[] { "bmdm","bmdm", "bmmc", "bmlb","bmlbmc","ycyy"};
		return CommonQueryDAO.commonQuery(sql, searchTj, inputV.toArray(new String[]{}),
				output, model);
		
		
	}
	
	/**
	 * 保存部门信息
	 * @param model
	 * @param type
	 * @return
	 * @throws Exception 
	 */
	public String saveBmdmInfo(BmdmwhForm model,String type) throws Exception{
		String msg="参数错误！";
		boolean b = false;
		String sql;
		if("add".equals(type)){
			sql="select count(1) num from xg_jcsj_bmdmb where bmdm=?";
			String num=this.getOneRs(sql, new String[]{model.getBmdm()}, "num");
			if("0".equals(num)){
				sql="insert into xg_jcsj_bmdmb(bmdm,bmmc,bmlb) values(?,?,?)";
				b=this.runUpdate(sql,new String[]{model.getBmdm(),model.getBmmc(),model.getBmlb()});
				msg=(b?"操作成功！":"操作失败！");
			}else{
				msg="该部门代码已存在！";
			}
		}else if("update".equals(type)){
			sql="update xg_jcsj_bmdmb set bmmc=?,bmlb=? where bmdm=?";
			b=this.runUpdate(sql,new String[]{model.getBmmc(),model.getBmlb(),model.getBmdm()});
			msg=(b?"操作成功！":"操作失败！");
		}
		return msg;
	}
	
	/**
	 * 删除部门信息
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	public boolean deleteBmdmInfo(BmdmwhForm model) throws Exception{
		String sql="delete from xg_jcsj_bmdmb where bmdm=?";
		boolean b=this.runUpdate(sql, new String[]{model.getBmdm()});
		return b;
	}
	
	/**
	 * 检查异常数据
	 * @return
	 * @throws Exception 
	 */
	public String checkExceptionData() throws Exception{
		String msg="检测完成！";
		String table="xg_jcsj_bmdmb";
		//清除旧的异常数据
		String sql="delete from xg_excdatalsb where ycb=?";
		boolean b=runUpdate(sql, new String[]{table});
		if(!b){
			msg="旧异常数据清除失败";
			return msg;
		}
		//1.部门名称重复
		String ycyy="部门名称重复";
		sql="insert into xg_excdatalsb(pk,ycyy,ycb) select bmdm,? ycyy,? ycb from xg_jcsj_bmdmb a " +
			"where exists (select 1 from (select bmmc from xg_jcsj_bmdmb group by bmmc having count(1)>1) x where x.bmmc=a.bmmc)";
		b=runUpdate(sql, new String[]{ycyy,table});
		if(!b){
			msg="部门名称重复插入失败！";
			return msg;
		}
		//2.部门类别不属于1或5（部门或学院）
		ycyy="部门类别不属于1或5（部门或学院）";
		sql="insert into xg_excdatalsb(pk,ycyy,ycb) select bmdm,? ycyy,? ycb from xg_jcsj_bmdmb a " +
		"where not exists (select 1 from xg_excdatalsb x where x.ycb=? and x.pk=a.bmdm) " +
		"and bmlb not in ('1','5')";
		b=runUpdate(sql, new String[]{ycyy,table,table});
		if(!b){
			msg="部门类别不属于1或5（部门或学院）插入失败！";
			return msg;
		}
		//2.部门属于学院，但该部门没有专业
		ycyy="部门属于学院，但该部门没有专业";
		sql="insert into xg_excdatalsb(pk,ycyy,ycb) select distinct bmdm,? ycyy,? ycb from xg_jcsj_bmdmb a ,xg_jcsj_zydmb b " +
		"where not exists (select 1 from xg_excdatalsb x where x.ycb=? and x.pk=a.bmdm) and a.bmdm=b.ssbmdm and a.bmlb='5' ";
		b=runUpdate(sql, new String[]{ycyy,table,table});
		if(!b){
			msg="部门属于学院，但该部门没有专业插入失败！";
			return msg;
		}
		return msg;
	}

}
