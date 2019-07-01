package xgxt.studentInfo.ynys;

import java.util.ArrayList;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.base.DealString;
import xgxt.utils.String.StringUtils;

public class XsxxYnysDAO extends DAO{
	
	ArrayList<String> values = new ArrayList<String>();
	
	/**
	 * 查询学生信息
	 * @param CommanForm model
	 * @return List<String[]> 
	 * */
	public List<String[]> selectXsxx(XsxxYnysForm model){
		int  start= model.getPages().getStart();
		int  end= model.getPages().getStart() + model.getPages().getPageSize();
		String[] outputValue = getCols();
		String tj = getCondition(model).toString();
		
		String sql = "select count(1) num from view_bks_xsxx a " + tj; 
		String count = getOneRs(sql, values != null ? values.toArray(new String[0]) : new String[]{} , "num");
		count = StringUtils.isNull(count) ? "0" : count;
		model.getPages().setMaxRecord(Integer.parseInt(count));//总记录数
		
		//数据查询
		sql = "select * from (select a.xh,a.xm,a.xb,a.xz,a.nj,a.bjmc,a.xjztm,a.zt,a.ssbh,a.byny,rownum r,rownum 行号  from view_bks_xsxx a " + tj + ") where r> " + start + " and r<=" + end;
		System.out.println(sql);
		System.out.println(values);
		return rsToVator(sql,  values != null ? values.toArray(new String[0]) : new String[]{}, outputValue);
	}
	
	/**
	 * 获取学生信息查询条件
	 * @param XsxxZgdzdxForm model
	 * @return StringBuffer
	 * */
	public StringBuffer getCondition(XsxxYnysForm model){
		StringBuffer sb = new StringBuffer(" where 1=1 ");
		//将页面的值转换为中文
		model.setXh(DealString.toGBK(model.getXh()));
		model.setXm(DealString.toGBK(model.getXm()));
		model.setXb(DealString.toGBK(model.getXb()));
		model.setSfzh(DealString.toGBK(model.getSfzh()));
		model.setXz(DealString.toGBK(model.getXz()));
		model.setJg(DealString.toGBK(model.getJg()));
		model.setByny(DealString.toGBK(model.getByny()));
		model.setSsbh(DealString.toGBK(model.getSsbh()));
		model.setJtcyxm(DealString.toGBK(model.getJtcyxm()));
		model.setXjztm(DealString.toGBK(model.getXjztm()));
		model.setZw(DealString.toGBK(model.getZw()));
		model.setSyd(DealString.toGBK(model.getSyd()));
		model.setRxrq(DealString.toGBK(model.getRxrq()));
		model.setCsrq(DealString.toGBK(model.getCsrq()));
		model.setSjhm(DealString.toGBK(model.getSjhm()));
		model.setPycc(DealString.toGBK(model.getPycc()));
		model.setJtnsrd(DealString.toGBK(model.getJtnsrd()));
		model.setJtnsrg(DealString.toGBK(model.getJtnsrg()));
		model.setSfdk(DealString.toGBK(model.getSfdk()));
		
		//添加查询条件
		if (!StringUtils.isNull(model.getXh())) {
			sb.append(" and a.xh like '%'||?||'%'");
			values.add(model.getXh());
		}
		if (!StringUtils.isNull(model.getXm())) {
			sb.append(" and a.xm like '%'||?||'%'");
			values.add(model.getXm());
		}
		if (!StringUtils.isNull(model.getSfzh())) {
			sb.append(" and a.sfzh like '%'||?||'%'");
			values.add(model.getSfzh());
		}
		if (!StringUtils.isNull(model.getXz())) {
			sb.append(" and a.xz like '%'||?||'%'");
			values.add(model.getXz());
		}
		if (!StringUtils.isNull(model.getJg())) {
			sb.append(" and a.jg like '%'||?||'%'");
			values.add(model.getJg());
		}
		if (!StringUtils.isNull(model.getByny())) {
			sb.append(" and a.byny like '%'||?||'%'");
			values.add(model.getByny());
		}
		if (!StringUtils.isNull(model.getTc())) {
			sb.append(" and a.tc like '%'||?||'%'");
			values.add(model.getTc());
		}
		if (!StringUtils.isNull(model.getSsbh())) {
			sb.append(" and a.ssbh like '%'||?||'%'");
			values.add(model.getSsbh());
		}
		if (!StringUtils.isNull(model.getJtcyxm())) {
			sb.append(" and exists(select 1 from view_xsjtxx b where a.xh=b.xh and (b.jtcy1_xm like '%'||?||'%' or b.jtcy2_xm like '%'||?||'%' or b.jtcy3_xm like '%'||?||'%'))");
			values.add(model.getJtcyxm());
			values.add(model.getJtcyxm());
			values.add(model.getJtcyxm());
		}
		if (!StringUtils.isNull(model.getLxdh1())) {
//			sb.append(" and exists(select 1 from view_xsjtxx b where a.xh=b.xh and b.lxdh1 like '%'||?||'%')");
			sb.append(" and lxdh like '%'||?||'%'");
			values.add(model.getLxdh1());
		}
		if (!StringUtils.isNull(model.getNj())) {
			sb.append(" and a.nj = ?");
			values.add(model.getNj());
		}
		if (!StringUtils.isNull(model.getXydm())) {
			sb.append(" and a.xydm = ?");
			values.add(model.getXydm());
		}
		if (!StringUtils.isNull(model.getZydm())) {
			sb.append(" and a.zydm = ?");
			values.add(model.getZydm());
		}
		if (!StringUtils.isNull(model.getBjdm())) {
			sb.append(" and a.bjdm = ?");
			values.add(model.getBjdm());
		}
		if (!StringUtils.isNull(model.getMzdm())) {
			sb.append(" and a.mz = ?");
			values.add(model.getMzdm());
		}
		if (!StringUtils.isNull(model.getZzmmdm())) {
			sb.append(" and a.zzmm = ?");
			values.add(model.getZzmmdm());
		}
		if (!StringUtils.isNull(model.getXb())) {
			sb.append(" and a.xb = ?");
			values.add(model.getXb());
		}
		if (!StringUtils.isNull(model.getXjztm())) {
			sb.append(" and a.xjztm = ?");
			values.add(model.getXjztm());
		}
		if (!StringUtils.isNull(model.getZw())) {
			sb.append(" and a.zw like '%'||?||'%'");
			values.add(model.getZw());
		}
		if (!StringUtils.isNull(model.getSyd())) {
			sb.append(" and a.syd like '%'||?||'%'");
			values.add(model.getSyd());
		}
		if (!StringUtils.isNull(model.getCsrq())) {
			sb.append(" and a.csrq like '%'||?||'%'");
			values.add(model.getCsrq());
		}
		if (!StringUtils.isNull(model.getRxrq())) {
			sb.append(" and a.rxrq like '%'||?||'%'");
			values.add(model.getRxrq());
		}
		if (!StringUtils.isNull(model.getPycc())) {
			sb.append(" and a.pycc like '%'||?||'%'");
			values.add(model.getPycc());
		}
		if (!StringUtils.isNull(model.getSjhm())) {
			sb.append(" and a.sjhm like '%'||?||'%'");
			values.add(model.getSjhm());
		}
		if (!StringUtils.isNull(model.getSfdk())) {
			sb.append(" and a.sfdk = ?");
			values.add(model.getSfdk());
		}
		//家庭年收入
		if(!StringUtils.isNull(model.getJtnsrd()) || !StringUtils.isNull(model.getJtnsrg())){
			sb.append(" and exists(select 1 from view_xsjtxx b where a.xh=b.xh and exists(select jtzsr from (select xh,to_number(jtzsr)jtzsr from view_xsjtxx where trim(jtzsr) is not null) c where b.xh=c.xh ");
			if(!StringUtils.isNull(model.getJtnsrd())){
				sb.append(" and to_number(jtzsr)>= ?");
				values.add(model.getJtnsrd());
			}
			if(!StringUtils.isNull(model.getJtnsrg())){
				sb.append(" and to_number(jtzsr)<=?");
				values.add(model.getJtnsrg());
			}
			sb.append("))");
		}
		//是否是辅导员
		if(model.isFdy()){
			sb.append(" and exists(select 1 from view_fdybbj f where a.bjdm=f.bjdm and f.zgh=?)");
			values.add(model.getUserName());
		}
		return sb;
	}
	
	/**
	 * 判断是否可以修改学生信息
	 * */
	public boolean checkModifyXsxx(){
		String flag = getOneRs("select modistuinfo from xtszb", new String[] {}, "modistuinfo");
		flag = StringUtils.isNull(flag) ? "1" : flag;
		return Integer.parseInt(flag) == 1 ? true : false;
	}
	
	/**
	 * 获取学生信息查询显示的字段
	 * @return String[]
	 * */
	public String[] getCols(){
		String[] colList = null;
		boolean modiFlag = checkModifyXsxx();
		if(modiFlag){
			colList =new String[] { "xh", "r", "xm", "xb", "nj", "xz", "bjmc", "xjztm","zt", "ssbh","byny"};
		}else{
			colList = new String[] { "行号", "r",  "xh", "xm", "xb", "nj", "xz", "bjmc","xjztm","zt","ssbh" ,"byny"};
		}
		
		return colList;
	}
}
