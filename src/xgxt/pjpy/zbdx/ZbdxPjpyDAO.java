package xgxt.pjpy.zbdx;

import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.xljk.lrh_Util.util.stu_info_util;
import xgxt.xljk.lrh_Util.model.*;

public class ZbdxPjpyDAO {
	
	public String[] getTableCol(String tableName){
		DAO dao = DAO.getInstance();
		String[] arr = null;
		String[] title;
		if(Pattern.matches("[a-z]+_tycjb", tableName)){//体育
			arr = new String[]{"xn","xq","xh","xm","bjmc","kcmc","cj","STSZCPCJ"};
		} else if(Pattern.matches("[a-z]+_rwszcjb", tableName)){//人文
			arr = new String[]{"xn","xq","xh","xm","bjmc","rwcj"};
		} else {//创新实践
			arr = new String[] {"xn","xq","xh","xm","bjmc","cxrjcj"};
		}
		title = dao.getColumnNameCN2(arr, tableName);
		return title;
	}
	
	public List<HashMap<String, String>> getTableData(String tableName,String[] condition,String[] val){
		DAO dao = DAO.getInstance();
		String[] arr = null;
		if(Pattern.matches("[a-z]+_tycjb", tableName)){//体育
			arr = new String[]{"xn","xq","xh","xm","bjmc","kcmc","cj","stszcpcj"};
		} else if(Pattern.matches("[a-z]+_rwszcjb", tableName)){//人文
			arr = new String[]{"xn","xq","xh","xm","bjmc","rwcj"};
		} else {//创新实践
			arr = new String[] {"xn","xq","xh","xm","bjmc","cxrjcj"};
		}
		StringBuffer sb = new StringBuffer("select ");
		for(String sub : arr){
			sb.append(sub.toLowerCase());
			sb.append(",");
		}
		sb.deleteCharAt(sb.lastIndexOf(","));
		sb.append(" from ");
		sb.append(tableName);
		StringBuffer tmpSb = new StringBuffer();
		for(int i=0;i<val.length;i++){
			if(val[i] != null && !val[i].trim().equals("")){
				tmpSb.append(" ");
				tmpSb.append(condition[i]);
				tmpSb.append("='");
				tmpSb.append(val[i]);
				tmpSb.append("'");
				tmpSb.append(" @ ");
			}
		}
		if(tmpSb.indexOf("@")>0){
			sb.append(" where ");
			sb.append(tmpSb.deleteCharAt(tmpSb.lastIndexOf("@")).toString().replace("@", "and"));
		}
//		System.out.println(sb.toString());
		List<HashMap<String, String>> rs = dao.getList(sb.toString(), new String[]{}, arr);
		return rs;
	}
	//查找学生综合素质测评详细资料的方法，需要提供学号，学年，年度
	public ZbdxPjpyForm find_stu_zhszcp_detail(ZbdxPjpyForm myform) throws Exception
	{
		String xh=myform.getXh_l().trim();
		String xn=myform.getXn().trim();
		String nd=myform.getNd().trim();
		myform.setXh_l(xh);
		myform.setXn(xn);
		myform.setNd(nd);
		stu_info_util myutil=new stu_info_util();
		stu_info_model stu_model=new stu_info_model();
		stu_model=myutil.stu_find_byxh(xh);
		String sql="select tcj,zcj,dcj,rwszf,cxysjszf,zhszcpzf from zhszcp where xh=? and xn=? and nd=?";
		HashMap<String, String>zhszcp_map= new HashMap<String, String>();
		zhszcp_map=myutil.mydao.getMapNotOut(sql, new String []{xh,xn,nd});
		myform.setXm_l(stu_model.getXM());
		myform.setXb(stu_model.getXB());
		myform.setNj(stu_model.getNJ());
		myform.setBjmc_l(stu_model.getBJMC());
		myform.setZymc_l(stu_model.getZYMC());
		myform.setXymc_l(stu_model.getXYMC());
		myform.setTcj(Base.chgNull(zhszcp_map.get("tcj"), "0", 0));
		myform.setZcj(Base.chgNull(zhszcp_map.get("zcj"), "0", 0));
		myform.setDcj(Base.chgNull(zhszcp_map.get("dcj"), "0", 0));
		myform.setRwszf(Base.chgNull(zhszcp_map.get("rwszf"), "0", 0));
		myform.setCxysjszf(Base.chgNull(zhszcp_map.get("cxysjszf"), "0", 0));
		myform.setZhszcpzf(Base.chgNull(zhszcp_map.get("zhszcpzf"), "0", 0));
		return myform;
	}
	
	public String Zbdx_save_zhszcpcj(ZbdxPjpyForm myform) throws Exception 
	{
		String message="";
		boolean flag=false;
		String xh=myform.getXh_l();
		String xn=myform.getXn();
		String nd=myform.getNd();
		String dcj=myform.getDcj();
		String zcj=myform.getZcj();
		String tcj=myform.getTcj();
		String rwszf=myform.getRwszf();
		String cxysjszf=myform.getCxysjszf();
		String zhszcpzf=myform.getZhszcpzf();
		String sql="update zhszcp set dcj=?,tcj=?,zcj=?,rwszf=?,cxysjszf=?,zhszcpzf=? where xh=? and xn=? and nd=?";
		stu_info_util myutil=new stu_info_util();
		flag=myutil.mydao.runUpdate(sql, new String [] {dcj,zcj,tcj,rwszf,cxysjszf,zhszcpzf,xh,xn,nd});
		if(true==flag)
		{
			message="update_success";
		}else
		{
			message="update_fail";
		}
		return message;
	}
	
	public ZbdxPjpyForm deal_gbk_notnull_form (ZbdxPjpyForm myform)
	{
		String xb=Base.chgNull(myform.getXb(), " ", 1);
    	String xm=Base.chgNull(myform.getXm_l(), " ", 1);
    	String xymc=Base.chgNull(myform.getXymc_l(), " ", 1);
    	String zymc=Base.chgNull(myform.getZymc_l(), " ", 1);
    	String bjmc=Base.chgNull(myform.getBjmc_l(), " ", 1);
		String dcj=Base.chgNull(myform.getDcj(), "0", 0);
		String zcj=Base.chgNull(myform.getZcj(),"0",0);
		String tcj=Base.chgNull(myform.getTcj(),"0",0);
		String rwszf=Base.chgNull(myform.getRwszf(),"0",0);
		String cxysjszf=Base.chgNull(myform.getCxysjszf(),"0",0);
		String zhszcpzf=Base.chgNull(myform.getZhszcpzf(),"0",0);
    	myform.setXb(xb);
    	myform.setXymc_l(xymc);
    	myform.setXm_l(xm);
    	myform.setZymc_l(zymc);
    	myform.setBjmc_l(bjmc);
		myform.setDcj(dcj);
		myform.setZcj(zcj);
		myform.setTcj(tcj);
		myform.setRwszf(rwszf);
		myform.setCxysjszf(cxysjszf);
		myform.setZhszcpzf(zhszcpzf);
		return myform;
	}
}
