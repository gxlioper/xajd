package xgxt.pjpy.ghxy.bjryf;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import xgxt.DAO.DAO;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.MakeQuery;

public class GhxyBjryfDao {
	
	/**
	 * 获取班级列表
	 * @return
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws SecurityException 
	 * @throws IllegalArgumentException 
	 */
	public List<String[]>getBjList(GhxyBjryfForm form,String userName,String nj) throws Exception{
		CommonQueryDAO dao=new CommonQueryDAO();
		GhxyBjryfModel ghxyBjryfModel=new GhxyBjryfModel();
		BeanUtils.copyProperties(ghxyBjryfModel,form);
		MakeQuery makeQuery=new MakeQuery();
		StringBuilder sb=new StringBuilder();
		
		String []colList={"bjdm","zydm","xydm"};
		makeQuery.makeQuery(colList, new String[]{}, ghxyBjryfModel);
		sb.append("select rownum r,zydm,xydm, bjdm,bjmc from(select b.bjdm,b.bjmc,b.zydm,b.xydm from fdybjb a,view_njxyzybj b  ");
		sb.append(" where zgh='"+userName+"' and a.bjdm=b.bjdm ");
		if("".equalsIgnoreCase(nj)){
			sb.append(")");
		}else{
			sb.append("and nj='"+nj+"')");
		}
		return dao.commonQuery(sb.toString(),makeQuery.getQueryString() ,makeQuery.getInputList(),new String[]{"bjdm","bjmc"},ghxyBjryfModel);
	}
	
	/**
	 * 获取指标代码表
	 */
	public List<HashMap<String,String>>getBjzb(){
		DAO dao=DAO.getInstance();
		String sql="select zbdm,zbnr from bjzbdmb";
		return dao.getList(sql, new String[]{}, new String[]{"zbdm","zbnr"});
	}
	
	/**
	 * 根据班级获取班级荣誉分表信息
	 */
	public List<HashMap<String,String>>getZblb(String sql){
		DAO dao=DAO.getInstance();
		return dao.getList(sql, new String[]{}, new String[]{"plbjdm","xmdm","xmfz","pc","xn","xq","nj"});
	}
	
	/**
	 * 获取班级荣誉分批次表信息
	 */
	public List<HashMap<String,String>>getRyfpc(){
		DAO dao=DAO.getInstance();
		String sql="select pcdm,pcmc from bjryfpcb";
		return dao.getList(sql,new String[]{},new String[]{"pcdm","pcmc"});
	}
	
	/**
	 * 获取班级加分信息
	 */
	public List<String[]>getBjjfxx(String bjdm){
		CommonQueryDAO dao=new CommonQueryDAO();
		String query="";
		String sql="(select * from view_ghxy_bjryf where bjdm=?)";
		return dao.commonQueryNotFy(sql,query ,new String[]{bjdm},new String[]{"xn","xq","pcmc","bjmc","xmmc","xmfz"},null);
	}
	
	/**
	 * 获取班级加分信息
	 */
	public List<String[]>getBlszxx(){
		CommonQueryDAO dao=new CommonQueryDAO();
		String query="";
		String sql="(select * from view_ghxy_bjryfbl )";
		return dao.commonQueryNotFy(sql,query ,new String[]{},new String[]{"djdm","djmc","jf","bjbl"},null);
	}
	
	/**
	 * 班级荣誉分设置表
	 * @return
	 */
	public List<HashMap<String,String>>ryfdjList(){
		DAO dao=DAO.getInstance();
		String sql="select djdm,djmc from bjryfszb";
		return dao.getList(sql,new String[]{},new String[]{"djdm","djmc"});
	}
	
	/**
	 * 获取年级总班级数
	 */
	public List<HashMap<String,String>>getZbjByNj(String nj){
		DAO dao=DAO.getInstance();
		String sql="select count(*)zbjs from view_njxyzybj where nj=?";
		return dao.getList(sql,new String[]{nj},new String[]{"zbjs"});
	}
	
	/**
	 * 根据荣誉等级获取班级荣誉等级数量
	 */
	public List<HashMap<String,String>>getBjRyfSl(String nj,String djdm,String pc){
		DAO dao=DAO.getInstance();
		String sql="select count(*)rydj from view_ghxy_bjryfsh where nj=? and djdm=? and pc=?";
		return dao.getList(sql,new String[]{nj,djdm,pc},new String[]{"rydj"});
	}
	
	/**
	 * 判断是否超出各等级的数量
	 */
	public boolean checkDjSl(float djbl){
		DAO dao=DAO.getInstance();
		String djblStr=""+djbl;
		String sql="select count(*)djsl from ghxy_bjryfblsz where djdm='001' and to_number(bjbl)> ? ";
		List<HashMap<String,String>>list=dao.getList(sql,new String[]{djblStr},new String[]{"djsl"});
		HashMap<String,String>map=list.get(0);
		if("0".equals(map.get("djsl"))){
			return false;
		}else{
			return true;
		}
	}
	
	/**
	 *判断班级的荣誉分是否被审核
	 */
	public boolean checkBjryf(String bjdm){
		DAO dao=DAO.getInstance();
		String sql="select count(*)num from view_ghxy_bjryfsh where plbjdm=?";
		List<HashMap<String,String>>list= dao.getList(sql, new String[]{bjdm}, new String[]{"num"});
		HashMap<String,String>map=list.get(0);
		if("0".equals(map.get("num"))){
			return false;
		}else{
			return true;
		}
	}
	
	
	/**
	 *判断寝室的荣誉分是否被审核
	 */
	public HashMap<String,String> checkBjryf(String bjdm,String pc,String xn,String xq){
		DAO dao=DAO.getInstance();
		String sql="select njzrsh,xxsh from view_ghxy_bjryfsh where plbjdm=? and pc=? and xn=? and xq=?";
		List<HashMap<String,String>>list= dao.getList(sql, new String[]{bjdm,pc,xn,xq}, new String[]{"njzrsh","xxsh"});
		if(list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}
}
