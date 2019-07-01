package xgxt.qgzx.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.qgzx.form.QgzxForm;
import xgxt.utils.String.StringUtils;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 勤工助学模块岗位管理DAO</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 李容</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2009-05-11</p>
 */
public class QgzxGwglDAO extends DAO {
	ArrayList<String> value = new ArrayList<String>();
	
	/**
	 * 获取查询条件
	 * */
	public StringBuffer getWhereSql(QgzxForm model){
		StringBuffer sb = new StringBuffer(" where 1=1 ");
		String nd = model.getNd();
		String yf = model.getYf();
		String nj = model.getNj();
		String xydm = model.getXydm();
		String zydm = model.getZydm();
		String bjdm = model.getBjdm();
		String xh = model.getXh();
		String xm = model.getXm();
		String gwdm = model.getGwdm();
		String xn = model.getXn();
		String xq = model.getXq();
		String gwxz = model.getGwxz();
		
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
		if(!StringUtils.isNull(yf)){
			sb.append( " and yf=?");
			value.add(yf);
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
		if(!StringUtils.isNull(gwdm)){
			sb.append( " and tzhgw=?");
			value.add(gwdm);
		}
		if(!StringUtils.isNull(gwxz)){
			sb.append( " and gwxz=?");
			value.add(gwxz);
		}
		if(!StringUtils.isNull(xh)){
			sb.append( " and xh like '%'||?||'%'");
			value.add(xh);
		}
		if(!StringUtils.isNull(xm)){
			sb.append( " and xm like '%'||?||'%'");
			value.add(xm);
		}
//		if(checkExists("yrdwdmb", "dlm", model.getUserName())){
//			if(StringUtils.isNull(gwdm)){
//				sb.append( " and exists(select 1 from yrdwdmb b where b.yrdwdm=a.sqdw and b.dlm=?)");
//				value.add(model.getUserName());
//			}
//		}
		return sb;
	}
	
	/**
	 * 检测记录是否存在
	 * @param String tableName
	 * @param String pk
	 * @param String pkValue
	 * @return boolean
	 * */
	public boolean checkExists(String tableName, String pk, String pkValue){
		String sql = "select count(*) num from " + tableName + " where " + pk + " =?";
		String num = getOneRs(sql, new String[]{pkValue}, "num");
		num = StringUtils.isNull(num) ? "0" : num;
		return Integer.parseInt(num) >0 ? true : false;
	}
	/**
	 * 查询学生申请岗位信息
	 * @param model
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getGwsqxx(QgzxForm model){
		String pkValue = model.getPkValue();
		String pk = "xh||gwdm||sqsj";
		String[] outputValue = {"bdsj","bh","bhgkm","bjdm","bjmc","bz","fdyyj","fjwjmc","gh","gjzxdk","gwdm","gwsbsj","gwzydj","gzbx","gzjl","jsjsp","jtcy","jtnsr","jtysr","jtzyjjly","kcjqgzxsj","kh","ldyx","lxdh","lzsj","mqqgzxqk","nd","nj","pkdj","sfdjsj","sffcfp","sfpks","sfyx","sfzdtg","sqsj","ssbh","wjcf","xb","xg","xh","xm","xn","xq","xqmc","xscyj","xsgzqk","xssq","xxshyj","xxyj","xydm","xymc","xyyj","xzb","yhtc","yrdwdm","zydm","zymc","zzmm","zzmmm","zzqk"};
		
		String sql = "select * from view_xsgwxx where " + pk + "=?";
		return getMap(sql, new String[]{pkValue}, outputValue);
	}
	
	/**
	 * 查询学生申请的岗位信息
	 * @param model
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getGwxx(QgzxForm model){
		String pkValue = model.getPkValue();
		String sql = "select gzsj,gznr,ryyq from view_gwxx where gwdm||gwsbsj=(select gwdm||gwsbsj from view_xsgwxx where xh||gwdm||sqsj=?)";
		String[] outputValue = {"gzsj","gznr","ryyq"};
		
		return getMap(sql, new String[]{pkValue}, outputValue);
	}
	
	/**
	 * 查询岗位调整信息导出数据
	 * @param QgzxForm model
	 * @param String[] colList
	 * @return List<String[]>
	 * */
	public List<String[]> selectXsgwtzxxForExport(QgzxForm model, String[] colList){
		String sql = "select * from view_qgzx_gwtz a " + getWhereSql(model).toString();
		String[] input = value != null ? value.toArray(new String[0]) : new String[]{};
		return rsToVator(sql, input, colList);
	}
	
	/***
	 * 查询岗位汇总信息
	 * @param String nd
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> selectXsgwhzxx(String nd){
		String sql = "select yrdwmc,gwdm,gwxzmc,xyrs,gznr,gzksrq,gzjsrq,fzr,lxdh from view_gwxx where nd=?";
		String[] outputValue = {"yrdwmc","gwdm","gwxzmc","xyrs","gznr","gzksrq","gzjsrq","fzr","lxdh"};
		return getList(sql, new String[]{nd}, outputValue);
	}
	
	/**
	 * 根据岗位获取审核通过的学生数
	 * @param pkValue
	 * @return int
	 * */
	public int getGwrsCount(String pkValue){
		String sql = "select count(*)num from xsgwxxb where gwdm||gwsbsj=? and xxyj='通过'";
		String num = getOneRs(sql, new String[]{pkValue}, "num");
		num = StringUtils.isNull(num) ? "0" : num;
		return Integer.parseInt(num);
	}
	
	/**
	 * 根据岗位获取审核通过的困难学生数
	 * @param pkValue
	 * @return int
	 * */
	public int getGwknsrsCount(String pkValue){
		String sql = "select * from xsgwxxb where gwdm||gwsbsj=? and xxyj='通过'";
		List<HashMap<String, String>> list = getList(sql, new String[]{pkValue}, new String[]{"xh"});
		int knsrs = 0;
		for(HashMap<String, String> map : list){
			if(isKns(map.get("xh"))){
				knsrs += 1;
			}
		}
		return knsrs;
	}
	
	/**
	 * 获取用人单位信息
	 * @param dlm
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getYrdwInfo(String dlm){
		String sql = "select yrdwdm,yrdwmc,lxr,lxdh from yrdwdmb where dlm=?";
		return getMap(sql, new String[]{dlm}, new String[]{"yrdwdm","yrdwmc","lxr","lxdh"});
	}
	
	/**
	 * 获取用人单位岗位用人信息信息
	 * @param yrdwdm
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> getYrdwGwhzList(String yrdwdm){
		String sql = "select count(*)rs,gwdm,(select sum(cjje) from view_xscjff b where a.gwdm=b.gwdm and a.gwsbsj=b.sqsj)cjje from view_xsgwxx a where xxyj='通过' and yrdwdm=? group by gwdm,gwsbsj";
		
		return getList(sql, new String[]{yrdwdm}, new String[]{"gwdm","rs","cjje"});
	}
}
