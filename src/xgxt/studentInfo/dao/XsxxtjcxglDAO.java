package xgxt.studentInfo.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.base.DealString;
import xgxt.form.CommanForm;
import xgxt.studentInfo.model.XsxxtjcxglForm;
import xgxt.utils.String.StringUtils;

public class XsxxtjcxglDAO extends DAO{
	ArrayList<String> value = new ArrayList<String>();
	/**
	 * 获取查询条件
	 * @return StringBuffer
	 * */
	public StringBuffer getWhereSql(CommanForm model){		
		StringBuffer sb = new StringBuffer(" where 1=1 ");
		String xn = model.getXn();
		String xq = model.getXq();
		String xh = model.getXh();
		String xm = model.getXm();
		String xydm = model.getXydm();
		String zydm = model.getZydm();
		String bjdm = model.getBjdm();
		String nj = model.getNj();
		
		if(!StringUtils.isNull(xn)){
			sb.append( " and xn=?");
			value.add(xn);
		}
		if(!StringUtils.isNull(xq)){
			sb.append( " and xq=?");
			value.add(xq);
		}
		if(!StringUtils.isNull(xh)){
			sb.append( " and xh like '%'||?||'%'");
			value.add(xh);
		}
		if(!StringUtils.isNull(xm)){
			sb.append( " and xm like '%'||?||'%'");
			value.add(xm);
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
		if(!StringUtils.isNull(nj)){
			sb.append( " and nj=?");
			value.add(nj);
		}
		return sb;		
	}
	
	/**
	 * 获取专业信息
	 * @param xydm
	 * @param fdyQx
	 * @param userName
	 * @return List<ArrayList<HashMap<String, String>>>
	 * */
	public List<ArrayList<HashMap<String, String>>> getZyGoupList(String xydm, String fdyQx, String userName){
		List<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>();
		List<HashMap<String, String>> szList = getXxtjszxx();
		String[] outputValue = {"专业代码", "专业名称"};
		StringBuilder sql = new StringBuilder("select ");
		//查询要提交的人数信息
		for(HashMap<String, String> map : szList){
			sql.append("(select count(*) from view_xsbfxx b where a.zydm = b.zydm and b.");
			sql.append(map.get("tjzd"));
			sql.append(" = '");
			sql.append(map.get("tjzdz"));
			sql.append("')");
			sql.append(map.get("xsmc"));
			sql.append(",");
			
			outputValue = StringUtils.joinStrArr(outputValue, new String[]{map.get("xsmc")});
		}
		
		sql.append(" zydm 专业代码,zymc 专业名称  from view_njxyzybj a where xydm=?");		
		//辅导员条件
		if("true".equalsIgnoreCase(fdyQx)){
			sql.append(" and exists(select 1 from fdybjb b where a.bjdm=b.bjdm and b.zgh='");
			sql.append(DealString.replaceImmitStr(userName));
			sql.append("')");
		}
		//按专业分组
		sql.append(" group by zydm,zymc order by zydm ");
		list = getList(sql.toString(), new String[]{xydm}, outputValue);		
		
		return listToList(list);
	}
	
	/**
	 * 获取班级信息
	 * @param xydm
	 * @param fdyQx
	 * @param userName
	 * @return List<ArrayList<HashMap<String, String>>>
	 * */
	public List<ArrayList<HashMap<String, String>>> getBjGoupList(String zydm, String fdyQx, String userName){
		List<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>();
		List<HashMap<String, String>> szList = getXxtjszxx();
		String[] outputValue = {"班级代码", "班级名称"};
		StringBuilder sql = new StringBuilder("select ");
		//查询要提交的人数信息
		for(HashMap<String, String> map : szList){
			sql.append("(select count(*) from view_xsbfxx b where a.bjdm = b.bjdm and b.");
			sql.append(map.get("tjzd"));
			sql.append(" = '");
			sql.append(map.get("tjzdz"));
			sql.append("')");
			sql.append(map.get("xsmc"));
			sql.append(",");
			
			outputValue = StringUtils.joinStrArr(outputValue, new String[]{map.get("xsmc")});
		}
		
		sql.append(" bjdm 班级代码,bjmc 班级名称  from view_njxyzybj a where zydm=?");		
		//辅导员条件
		if("true".equalsIgnoreCase(fdyQx)){
			sql.append(" and exists(select 1 from fdybjb b where a.bjdm=b.bjdm and b.zgh='");
			sql.append(DealString.replaceImmitStr(userName));
			sql.append("')");
		}
		//按班级分组
		sql.append(" group by bjdm,bjmc order by bjdm");
		list = getList(sql.toString(), new String[]{zydm}, outputValue);		
		
		return listToList(list);
	}
	
	/**
	 * 数据转换
	 * @param list
	 * @return List<ArrayList<HashMap<String, String>>>
	 * */
	public List<ArrayList<HashMap<String, String>>> listToList(List<HashMap<String, String>> list){
		List<ArrayList<HashMap<String, String>>> result = new ArrayList<ArrayList<HashMap<String,String>>>();
		ArrayList<HashMap<String, String>> tmpList = new ArrayList<HashMap<String,String>>();
		for(HashMap<String, String> oneMap : list){
			tmpList = new ArrayList<HashMap<String,String>>();
			HashMap<String, String> valMap = new HashMap<String, String>();
			for(String key : oneMap.keySet()){
				valMap = new HashMap<String, String>();
				valMap.put("en", key);
				valMap.put("cn", oneMap.get(key));
				tmpList.add(valMap);
			}
			result.add(tmpList);
		}
		return result;
	}
	
	/**
	 * 查询学生干部信息
	 * @param model
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> selectXsgbList(XsxxtjcxglForm model){
		String sql = "select a.*,b.xm,b.xb,b.nj,b.xz,b.xymc,b.zymc,b.bjmc,c.bjgbmc from" +
				     " sxjy_bjgbxxb a left join view_xsbfxx b on a.xh=b.xh " +
				     "left join sxjy_bjgbzlb c on a.bjgbdm=c.bjgbdm where b.bjdm=?";
		String[] outputValue = {"xh", "xm", "xb", "nj", "xymc", "zymc", "bjmc", "xz"};
		return getList(sql, new String[]{model.getBjdm()}, outputValue);
	}
	
	
	/**
	 * return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> getXxtjszxx(){
		String sql = "select * from xsxx_tjsz_szb order by tjzd";
		return getList(sql, new String[]{}, new String[]{"tjzd","tjzdz","xsmc"});
	}
	
	/**
	 * 判断记录是否存在
	 * @param tableName
	 * @param pk
	 * @param pkValue
	 * @return boolean
	 * */
	public boolean checkExists(String tableName, String pk, String pkValue){
		String sql = "select count(*) num from " + tableName + " where " + pk + "=?";
		return Integer.parseInt(getOneRs(sql, new String[]{pkValue}, "num")) >0 ? true : false;
	}
}
