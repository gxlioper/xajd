package xsgzgl.jcsj.zydmwh;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.utils.CommonQueryDAO;
import xsgzgl.jcsj.bmdmwh.BmdmwhDao;

public class ZydmwhDao extends DAO{
	
	/**
	 * 获取专业信息类别
	 * @param model
	 * @return
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws SecurityException 
	 * @throws IllegalArgumentException 
	 */
	public List<String[]> getZydmList(ZydmwhForm model) throws Exception{
		// 高级查询条件
//		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		// 高级查询输入值
//		String[] inputV = SearchService.getTjInput(model.getSearchModel());

		//权限控制
//		SearchService searchService=new SearchService();
//		String searchTjQx = searchService.getSearchTjByUser(request, "","zydm", null); 	//学院用户
		
		String searchTj="";
		List<String> inputV=new ArrayList<String>();
		if(!Base.isNull(model.getQuery_ssbmdm())){
			searchTj+=" and ssbmdm=? ";
			inputV.add(model.getQuery_ssbmdm());
		}
		if(!Base.isNull(model.getQuery_text())){
			searchTj+=" and (zydm like '%'||?||'%' or zymc like '%'||?||'%')";
			inputV.add(model.getQuery_text());
			inputV.add(model.getQuery_text());
		}
		if("是".equals(model.getQuery_sfycsj())){
			searchTj+=" and ycyy is not null ";
		}else if("否".equals(model.getQuery_sfycsj())){
			searchTj+=" and ycyy is null ";
		}
		
		String sql = "select rownum r, a.* from (" +
				"select a.*,(select bmmc from xg_jcsj_bmdmb x where x.bmdm=a.ssbmdm) bmmc,b.ycyy from xg_jcsj_zydmb a " +
				"left join xg_excdatalsb b on a.zydm=b.pk and b.ycb='xg_jcsj_zydmb' "+
				" order by ycyy,zydm ) a where 1 = 1";

		String[] output = new String[] { "zydm","ssbmdm","zydm", "zymc", "bmmc","xz","ycyy"};
		return CommonQueryDAO.commonQuery(sql, searchTj, inputV.toArray(new String[]{}),
				output, model);
		
		
	}
	
	/**
	 * 保存专业信息
	 * @param model
	 * @param type
	 * @return
	 * @throws Exception 
	 */
	public String saveZydmInfo(ZydmwhForm model,String type) throws Exception{
		String msg="参数错误！";
		boolean b = false;
		String sql;
		if("add".equals(type)){
			sql="select count(1) num from xg_jcsj_zydmb where zydm=?";
			String num=this.getOneRs(sql, new String[]{model.getZydm()}, "num");
			if("0".equals(num)){
				sql="insert into xg_jcsj_zydmb(zydm,zymc,ssbmdm,xz) values(?,?,?,?)";
				b=this.runUpdate(sql,new String[]{model.getZydm(),model.getZymc(),model.getSsbmdm(),model.getXz()});
				msg=(b?"操作成功！":"操作失败！");
			}else{
				msg="该专业代码已存在！";
			}
		}else if("update".equals(type)){
			sql="update xg_jcsj_zydmb set zymc=?,ssbmdm=?,xz=? where zydm=?";
			b=this.runUpdate(sql,new String[]{model.getZymc(),model.getSsbmdm(),model.getXz(),model.getZydm()});
			msg=(b?"操作成功！":"操作失败！");
		}
		return msg;
	}
	
	/**
	 * 删除专业信息
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	public boolean deleteZydmInfo(ZydmwhForm model) throws Exception{
		String sql="delete from xg_jcsj_zydmb where zydm=?";
		boolean b=this.runUpdate(sql, new String[]{model.getZydm()});
		return b;
	}
	
	/**
	 * 获取部门列表
	 * @return
	 */
	public List<HashMap<String,String>> getBmdmList(){
		String sql="select bmdm,bmmc from xg_jcsj_bmdmb where bmlb='5'";
		return this.getList(sql, new String[]{}, new String[]{"bmdm","bmmc"});
	}
	

	/**
	 * 检查异常数据
	 * @return
	 * @throws Exception 
	 */
	public String checkExceptionData() throws Exception{
		String msg="检测完成！";
		String table="xg_jcsj_zydmb";
		//清除旧的异常数据
		String sql="delete from xg_excdatalsb where ycb=?";
		boolean b=runUpdate(sql, new String[]{table});
		if(!b){
			msg="旧异常数据清除失败";
			return msg;
		}
		//1.部门代码未全部通过的情况下不得进行专业代码检测
		String msg_temp=new BmdmwhDao().checkExceptionData();
		if(!msg.equals(msg_temp)){//如果部门代码检测失败
			return "部门代码未全部通过的情况下不得进行专业代码检测！部门代码检测失败！"+msg_temp;
		}else{
			sql="select count(1) num from xg_excdatalsb where ycb=?";
			String num=getOneRs(sql, new String[]{"xg_jcsj_bmdmb"}, "num");
			if(!"0".equals(num)){
				return "部门代码未全部通过的情况下不得进行专业代码检测！部门代码有"+num+"条异常数据还未处理！";
			}
		}
		//2.专业的所属部门必须在部门代码表中已存在且该部门代码的部门类别为5（学院）
		String ycyy="专业的所属部门不在部门代码表中或该部门代码的部门类别为不是5（学院）";
		sql="insert into xg_excdatalsb(pk,ycyy,ycb) select zydm,? ycyy,? ycb from xg_jcsj_zydmb a " +
				"where not exists (select 1 from xg_jcsj_bmdmb x where x.bmdm=a.ssbmdm and x.bmlb='5')";
		b=runUpdate(sql, new String[]{ycyy,table});
		if(!b){
			msg="专业的所属部门不在部门代码表中或该部门代码的部门类别为不是5（学院）插入失败！";
			return msg;
		}
		//3.同部门下的专业名称不能重复
		ycyy="同部门下的专业名称不能重复";
		sql="insert into xg_excdatalsb(pk,ycyy,ycb) select zydm,? ycyy,? ycb from xg_jcsj_zydmb a " +
		"where not exists (select 1 from xg_excdatalsb x where x.ycb=? and x.pk=a.zydm) " +
		"and exists (select 1 from (select ssbmdm,zymc from xg_jcsj_zydmb group by ssbmdm,zymc having count(1)>1) x where x.zymc=a.zymc)";
		b=runUpdate(sql, new String[]{ycyy,table,table});
		if(!b){
			msg="同部门下专业名称重复插入失败！";
			return msg;
		}
		//4.学制非空且数字
		ycyy="学制为空或非数字或不合理";
		sql="insert into xg_excdatalsb(pk,ycyy,ycb) select zydm,? ycyy,? ycb from xg_jcsj_zydmb a " +
		"where not exists (select 1 from xg_excdatalsb x where x.ycb=? and x.pk=a.zydm) " +
		"and xz is null or length(xz)<>1 or (xz<'0' or xz>'9')";
		b=runUpdate(sql, new String[]{ycyy,table,table});
		if(!b){
			msg="学制为空或非数字或不合理插入失败！";
			return msg;
		}
		return msg;
	}

}
