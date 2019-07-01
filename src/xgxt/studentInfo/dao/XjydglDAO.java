package xgxt.studentInfo.dao;

import java.util.ArrayList;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.form.CommanForm;
import xgxt.studentInfo.model.StudentInfoForm;
import xgxt.utils.String.StringUtils;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 学生学籍异动管理DAO</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 李容</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2010-06-1</p>
 */
public class XjydglDAO extends DAO {
	ArrayList<String> value = new ArrayList<String>();
	/**
	 * 获取查询条件
	 * @return StringBuffer
	 * */
	public StringBuffer getWhereSql(StudentInfoForm model){		
		StringBuffer sb = new StringBuffer(" where 1=1 ");
		String xn = model.getXn();
		String xq = model.getXq();
		String xh = model.getXh();
		String xm = model.getXm();
		String xydm = model.getYdhxydm();
		String zydm = model.getYdhzydm();
		String bjdm = model.getYdhbjdm();
		String nj = model.getYdhnj();
		String ydlbdm = model.getYdlbdm();
		String ydrqks = model.getYdrqks();
		String ydrqjs = model.getYdrqjs();
		String ydjzrqks = model.getYdjzrqks();
		String ydjzrqjs = model.getYdjzrqjs();
		String sffx = model.getSffx();
		
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
			sb.append( " and ydqxydm=?");
			value.add(xydm);
		}
		if(!StringUtils.isNull(zydm)){
			sb.append( " and ydqzydm=?");
			value.add(zydm);
		}
		if(!StringUtils.isNull(bjdm)){
			sb.append( " and ydqbjdm=?");
			value.add(bjdm);
		}
		if(!StringUtils.isNull(nj)){
			sb.append( " and ydqnj=?");
			value.add(nj);
		}
		if(!StringUtils.isNull(ydlbdm)){
			sb.append( " and ydlbdm=?");
			value.add(ydlbdm);
		}
		if(StringUtils.isNotNull(ydrqks)){
			sb.append("and to_number(substr(ydrq,0,4)||substr(ydrq,6,2)||substr(ydrq,9,2)) >=?");
			value.add(ydrqks.replaceAll("-", ""));
		}
		if(StringUtils.isNotNull(ydrqjs)){
			sb.append("and to_number(substr(ydrq,0,4)||substr(ydrq,6,2)||substr(ydrq,9,2)) <=?");
			value.add(ydrqjs.replaceAll("-", ""));
		}
		if(StringUtils.isNotNull(ydjzrqks)){
			sb.append("and to_number(substr(ydjzrq,0,4)||substr(ydjzrq,6,2)||substr(ydjzrq,9,2)) >=?");
			value.add(ydjzrqks.replaceAll("-", ""));
		}
		if(StringUtils.isNotNull(ydjzrqjs)){
			sb.append("and to_number(substr(ydjzrq,0,4)||substr(ydjzrq,6,2)||substr(ydjzrq,9,2)) <=?");
			value.add(ydjzrqjs.replaceAll("-", ""));
		}
		if("是".equalsIgnoreCase(sffx)){
			sb.append(" and exists(select 1 from view_xjydjbxx b where to_number(a.ydxh)<to_number(b.ydxh) and b.ydlbmc='复学' and a.xh=b.xh) and ydlbmc='休学'");
		}
		if("否".equalsIgnoreCase(sffx)){
			sb.append(" and not exists(select 1 from view_xjydjbxx b where to_number(a.ydxh)<to_number(b.ydxh) and b.ydlbmc='复学' and a.xh=b.xh) and ydlbmc='休学'");
		}
		if("true".equalsIgnoreCase(model.getIsFdy())){
			//辅导员登录
			sb.append(" and exists(select 1 from view_fdybbj b where (a.ydhbjdm=b.bjdm or a.ydqbjdm=b.bjdm) and b.zgh=?)");
			value.add(model.getUserName());
		}
		
		return sb;		
	}
	
	/**
	 * 判断表中的记录是否存在
	 * @param tableName
	 * @param pk
	 * @param pkValue
	 * @return boolean
	 * */
	public boolean isExists(String tableName,String pk, String pkValue){
		boolean flag = false;
		String sql = "select count(*) num from " + tableName + " where " + pk + " =? ";
		String result = getOneRs(sql, new String[]{pkValue}, "num");
		flag = Integer.parseInt(result) > 0 ? true : false;
		return flag;
	}
	
	/**
	 * 查询学生学籍异动信息
	 * @param tableName
	 * @param model
	 * @param outputCol
	 * @return List<String[]>
	 * */
	public List<String[]> selectXjydxx(String tableName, 
									   StudentInfoForm model, 
			                           String[] outputCol){
		String sql = "select * from " + tableName + " a " + getWhereSql(model);
		return rsToVator(sql, value != null ? value.toArray(new String[0]) : new String[]{}, outputCol);
	}
}
