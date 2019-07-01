package xgxt.utils;


import java.sql.Timestamp;

import common.Globals;

import xgxt.DAO.DAO;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.form.CommanForm;
import xgxt.qgzx.form.QgzxForm;
import xgxt.utils.String.StringUtils;

public class ToolClass {

	//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<练习抽取方法>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	public static void getZhszcp(DAO dao, String xxmc, String xh, String nd, String xn, String xq, String dcj, String xydykpf, String gydykpf, String zcj, String tcj, String bz, String jnjf, String gzxxcx, String cpzf,String znszcj,String zhszcpzf, String cxcj, String zpf) throws Exception {
		String sql;
		String sqlbl;
		String xxdm = StandardOperation.getXxdm();
		if(xxdm.equalsIgnoreCase(Globals.XXDM_ZJSYZYXY)){
			//计算综合素质测评总分
			cpzf = String.valueOf(dao.getOneRs("select to_number(" + dcj + ") * 0.2+to_number(" + zcj + ")*0.6 " +
					" + to_number("+ tcj +")*0.1 + to_number(" + jnjf + ")*0.1" + " cpzf from dual", new String[]{}, "cpzf"));
			
			sql = "insert into zhszcp(nd,xn,xq,xh,dcj,zcj,tcj,bz,jnjf,kpf) values(?,?,?,?,?,?,?,?,?,?)";
			dao.runUpdate(sql, new String[] { nd, xn, xq, xh, dcj, zcj,
					tcj, bz, jnjf, cpzf});
		} else if (xxdm.equalsIgnoreCase(Globals.XXDM_HZZY)){
			sql = "select xh from view_xszsxx where xh=?";
			String tag = dao.returntag(sql, new String []{xh});
			if ("notempty".equalsIgnoreCase(tag)){
				sqlbl = "select * from zhcpfblszb where sfzds='0'";
			} else{
				sqlbl = "select * from zhcpfblszb where sfzds='1'";
			}
			String [] bl = dao.getOneRs(sqlbl, new String [] {}, new String []{"xydykpf","gydykpf","zycj","tycj"});
			for (int i=0; i<bl.length; i++){
				bl[i] = String.valueOf((Float.valueOf(bl[i]))/100);
			}
			Float xydykpfBl = Float.valueOf(bl[0]);
			Float gydykpfBl = Float.valueOf(bl[1]);
			Float zycjBl = Float.valueOf(bl[2]);
			Float tycjBl = Float.valueOf(bl[3]);
			dcj = String.valueOf(Float.valueOf(xydykpf)*xydykpfBl + Float.valueOf(gydykpf)*gydykpfBl);
			dcj = dcj.substring(0, dcj.indexOf(".")+2);
			cpzf = String.valueOf(Float.valueOf(zcj)*zycjBl + Float.valueOf(dcj) + Float.valueOf(tcj)*tycjBl + Float.valueOf(gzxxcx));
			cpzf = cpzf.substring(0, cpzf.indexOf(".")+2);
			sql = "insert into zhszcp(nd,xn,xq,xh,xydykpf,gydykpf,dcj,zcj,tcj,bz,gzxxcx,kpf) values(?,?,?,?,?,?,?,?,?,?,?,?)";
			dao.runUpdate(sql, new String[] { nd, xn, xq, xh, xydykpf, gydykpf, dcj, zcj,
					tcj, bz, gzxxcx, cpzf });
		} else if (xxdm.equalsIgnoreCase(Globals.XXDM_GDBYXY)) {//广东白云学院
			sql = "insert into zhszcp(nd,xn,xq,xh,cxcj,bz) values(?,?,?,?,?,?)";
			dao.runUpdate(sql, new String[]{nd, xn, xq, xh, cxcj, bz});
		} else{
			sql = "insert into zhszcp(nd,xn,xq,xh,dcj,zcj,tcj,bz,znszcj,jnjf,zhszcpzf,zpf) values(?,?,?,?,?,?,?,?,?,?,?,?)";
			dao.runUpdate(sql, new String[] { nd, xn, xq, xh, dcj, zcj,
					tcj, bz, znszcj,jnjf,zhszcpzf,zpf});
		}
	}
	//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<获取查询条件>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	public static StringBuffer getQuery(String nj, String xn, String xq, String xh, String xy, String zy, String bj, String fzld,String gwxz, String gwdm,String wzlxdm, StringBuffer query) {
		String xxdm = StandardOperation.getXxdm();

		if ((xh == null) || xh.equalsIgnoreCase("")) {
			query.append(" and 1=1 ");
		} else {
			if(Check_Input_Value.check2(xh)){
				query.append("and xh like '%");
				query.append(xh);
				query.append("%' ");
			}
		}
		if ((nj == null) || nj.equalsIgnoreCase("")) {
			query.append("and 1=1 ");
		} else {
			if(Check_Input_Value.check2(nj)){
				query.append("and nj = '");
				query.append(nj);
				query.append("' ");
			}
		}
		if ((xn == null) || xn.equalsIgnoreCase("")) {
			query.append("and 1=1 ");
		} else {
			if(Check_Input_Value.check2(xn)){
				query.append("and xn = '");
				query.append(xn);
				query.append("' ");
			}
		}
		if ((xq == null) || xq.equalsIgnoreCase("")) {
			query.append("and 1=1 ");
		} else {
			if(Check_Input_Value.check2(xq)){
				query.append("and xq = '");
				query.append(xq);
				query.append("' ");
			}
		}
		if ((zy == null) || zy.equalsIgnoreCase("")) {
			query.append("and 1=1 ");
		} else {
			if(Check_Input_Value.check2(zy)){
				query.append("and zydm = '");
				query.append(zy);
				query.append("' ");
			}
		}
		if ((bj == null) || bj.equalsIgnoreCase("")) {
			query.append("and 1=1 ");
		} else {
			if(Check_Input_Value.check2(bj)){
				query.append("and bjdm = '");
				query.append(bj);
				query.append("' ");
			}
		}
		if ((xy == null || xy.equalsIgnoreCase(""))){
			query.append("and 1=1 ");
		} else {
			if(Check_Input_Value.check2(xy)){
				query.append("and xydm = '");
				query.append(xy);
				query.append("' ");
			}
		}
		if(xxdm.equalsIgnoreCase(Globals.XXDM_HZZY)){
			if("".equalsIgnoreCase(fzld) || fzld == null){
				query.append("and 1=1 ");
			} else {
				if(Check_Input_Value.check2(fzld)){
					query.append("and lddm = '");
					query.append(fzld);
					query.append("' ");
				}
			}
		}
		if("".equalsIgnoreCase(gwxz) || gwxz == null){
			query.append("and 1=1 ");
		}else{
			if(Check_Input_Value.check2(gwxz)){
				query.append("and gwxz = '");
				query.append(gwxz);
				query.append("' ");
			}
		}
		if("".equalsIgnoreCase(gwdm) || gwdm == null){
			query.append("and 1=1 ");
		}else{
			if(Check_Input_Value.check2(gwdm)){
				query.append("and gwdm = '");
				query.append(gwdm);
				query.append("' ");
			}
		}
		if("".equalsIgnoreCase(wzlxdm) || wzlxdm == null){
			query.append("and 1=1 ");
		}else{
			if(Check_Input_Value.check2(wzlxdm)){
				query.append("and wzlxdm = '");
				query.append(wzlxdm);
				query.append("' ");
			}
		}
		return query;
	}
	////////////////////用于视图“岗位调整表”,根据条件返回相应的字符串/////////////////////////
	public static StringBuffer getGWTZBQuery(QgzxForm model) {
		String xxdm = StandardOperation.getXxdm();
		StringBuffer query = new StringBuffer(" where 1=1 ");
		String xh = model.getXh();
		String nj = model.getNj();
		String xn = model.getXn();
		String xq = model.getXq();
		String zy = model.getZydm();
		String bj = model.getBjdm();
		String xy = model.getXydm();
		String fzld = model.getFzld();
		String gwxz = model.getGwxz();
		String gwdm = model.getGwdm();
		String wzlxdm = model.getWzlxdm();
		
		
		if (!StringUtils.isNull(xh)) {
			if(Check_Input_Value.check2(xh)){
				query.append("and xh = '");
				query.append(xh);
				query.append("' ");
			}
		}
		if (!StringUtils.isNull(nj)) {
			if(Check_Input_Value.check2(nj)){
				query.append("and nj = '");
				query.append(nj);
				query.append("' ");
			}
		}
		if (!StringUtils.isNull(xn)) {
			if(Check_Input_Value.check2(xn)){
				query.append("and xn = '");
				query.append(xn);
				query.append("' ");
			}
		} 		
		if (!StringUtils.isNull(xq)) {
			if(Check_Input_Value.check2(xq)){
				query.append("and xq = '");
				query.append(xq);
				query.append("' ");
			}
		} 
		if (!StringUtils.isNull(zy)) {
			if(Check_Input_Value.check2(zy)){
				query.append("and zydm = '");
				query.append(zy);
				query.append("' ");
			}
		}
		if (!StringUtils.isNull(bj)) {
			if(Check_Input_Value.check2(bj)){
				query.append("and bjdm = '");
				query.append(bj);
				query.append("' ");
			}
		}
		if (!StringUtils.isNull(xy)){
			if(Check_Input_Value.check2(xy)){
				query.append("and xydm = '");
				query.append(xy);
				query.append("' ");
			}
		}
		if(xxdm.equalsIgnoreCase(Globals.XXDM_HZZY)){
			//杭州职业
			if(!StringUtils.isNull(fzld)){
				if(Check_Input_Value.check2(fzld)){
					query.append("and lddm = '");
					query.append(fzld);
					query.append("' ");
				}
			}
		}
		if(!StringUtils.isNull(gwxz)){
			if(Check_Input_Value.check2(gwxz)){
				query.append("and gwxz = '");
				query.append(gwxz);
				query.append("' ");
			}
		}
		if(!StringUtils.isNull(gwdm)){
			if(Check_Input_Value.check2(gwdm)){
				query.append("and tzhgw = '");
				query.append(gwdm);
				query.append("' ");
			}
		}
		if(!StringUtils.isNull(wzlxdm)){
			if(Check_Input_Value.check2(wzlxdm)){
				query.append("and wzlxdm = '");
				query.append(wzlxdm);
				query.append("' ");
			}
		}
		return query;
	}
	////////////////////////////////获取学校名称////////////////////////////////////////////////////
	public static String getXxmc(){
		DAO dao = DAO.getInstance();
		return dao.getOneRs("select xxmc from xtszb where rownum=1", new String[] {}, "xxmc");
	}
	/////////////////////////////获取学院名称////////////////////////////////////////////////

	public static String getXymc(DAO dao, String xydm) {
		return dao.getOneRs("select distinct bmmc from zxbz_xxbmdm where bmdm=?", new String []{xydm}, "bmmc");
	}

	////////////////////////////////获取系统时间/////////////////////////////////////////////
	public static String getSysDate(){
		Timestamp date = new Timestamp(System.currentTimeMillis());
		return date.toString();
	}
	
	/**中国地质大学换岗审核查询用于中国地质大学学生换岗信息表zgdzdx_xshgxxb*/
	public static StringBuffer getHgxxQuery(CommanForm model,boolean isYrdw){
		StringBuffer sb = new StringBuffer(" where 1=1 ");
		//String xh,String gwdm,String gwsbsj,String sqsj,String xyyj,String xxyj,String lxdh,String xn,String nd,String xq,String sqly,String yhtc,String hgdm,String hgsj,String bz,String gwxz,String xydm,StringBuffer query
		model.setXh(DealString.toGBK(model.getXh()));
		model.setXm(DealString.toGBK(model.getXm()));
		model.setXh(DealString.toGBK(model.getXh()));
		model.setGwdm(DealString.toGBK(model.getGwdm()));
		
		String xh = model.getXh();
		if (!StringUtils.isNull(xh)) {
			sb.append("and xh like '%");
			sb.append(xh.replace("'", "‘"));
			sb.append("%' ");
		}
		String xm = model.getXm();
		if (!StringUtils.isNull(xm)) {
			sb.append("and xm like '%");
			sb.append(xm.replace("'", "‘"));
			sb.append("%' ");
		}
		
		String gwdm = model.getGwdm();
		if (!StringUtils.isNull(gwdm)) {
			sb.append("and gwdm = '");
			sb.append(gwdm.replace("'", "‘"));
			sb.append("' ");
		}
		
		String gwsbsj = model.getGwsbsj();		
		if (!StringUtils.isNull(gwsbsj)) {
			sb.append("and gwsbsj = '");
			sb.append(gwsbsj.replace("'", "‘"));
			sb.append("' ");
		}
		
		String sqsj = model.getSqsj();
		if (!StringUtils.isNull(sqsj)) {
			sb.append("and sqsj = '");
			sb.append(sqsj.replace("'", "‘"));
			sb.append("' ");
		}
		
		String xyyj = model.getXyyj();
		if (!StringUtils.isNull(xyyj)) {
			sb.append("and xyyj = '");
			sb.append(xyyj.replace("'", "‘"));
			sb.append("' ");
		}
		
		String xxyj = model.getXxyj();
		if (!StringUtils.isNull(xxyj)) {
			sb.append("and xxyj = '");
			sb.append(xxyj.replace("'", "‘"));
			sb.append("' ");
		} 
		
		String lxdh = model.getLxdh();
		if (!StringUtils.isNull(lxdh)) {
			sb.append("and lxdh = '");
			sb.append(lxdh.replace("'", "‘"));
			sb.append("' ");
		}
		String xn = model.getXn();
		if (!StringUtils.isNull(xn)) {
			sb.append("and xn = '");
			sb.append(xn.replace("'", "‘"));
			sb.append("' ");
		}
		String nd = model.getNd();
		if (!StringUtils.isNull(nd)) {
			sb.append("and nd = '");
			sb.append(nd.replace("'", "‘"));
			sb.append("' ");
		} 
		
		String xq = model.getXq();
		if (!StringUtils.isNull(xq)) {
			sb.append("and xq = '");
			sb.append(xq.replace("'", "‘"));
			sb.append("' ");
		} 
		
		String sqly = model.getSqly();
		if (!StringUtils.isNull(sqly)) {
			sb.append("and sqly = '");
			sb.append(sqly.replace("'", "‘"));
			sb.append("' ");
		}
		
		String bz = model.getBz();
		if (!StringUtils.isNull(bz)) {
			sb.append("and bz = '");
			sb.append(bz.replace("'", "‘"));
			sb.append("' ");
		} 
		
		String gwxz = model.getGwxz();
		if (!StringUtils.isNull(gwxz)) {
			sb.append("and gwxz = '");
			sb.append(gwxz.replace("'", "‘"));
			sb.append("' ");
		}
		
		String xydm = model.getXydm();
		if (!StringUtils.isNull(xydm)) {
			sb.append("and xydm = '");
			sb.append(xydm.replace("'", "‘"));
			sb.append("' ");
		} 
		
		if(isYrdw){
			sb.append(" and exists (select 1 from yrdwdmb b where a.sqdw=b.yrdwdm and b.dlm='");
			sb.append(model.getUserName() + "')");
		}
		
		String yhtc = model.getYhtc();
		if (!StringUtils.isNull(yhtc)) {
			sb.append("and yhtc = '");
			sb.append(yhtc.replace("'", "‘"));
			sb.append("' ");
		}
		String hgdm = model.getHgdm();
		if (!StringUtils.isNull(hgdm)) {
			sb.append("and hgdm = '");
			sb.append(hgdm.replace("'", "‘"));
			sb.append("' ");
		}
		
		String hgsj = model.getHgsj();
		if (!StringUtils.isNull(hgsj)) {
			sb.append("and hgsj = '");
			sb.append(hgsj.replace("'", "‘"));
			sb.append("' ");
		}
		
		return sb;
	}
	
	/**中国地质大学辞岗岗审核查询用于中国地质大学学生辞岗信息表zgdzdx_xscgxxb*/
	public static StringBuffer getCgxxQuery(CommanForm model,boolean isYrdw){
		StringBuffer sb = new StringBuffer(" where 1=1 ");
		
		model.setXh(DealString.toGBK(model.getXh()));
		model.setXm(DealString.toGBK(model.getXm()));
		model.setXh(DealString.toGBK(model.getXh()));
		model.setGwdm(DealString.toGBK(model.getGwdm()));
		
		String xh = model.getXh();
		if (!StringUtils.isNull(xh)) {
			sb.append("and xh like '%");
			sb.append(xh.replace("'", "‘"));
			sb.append("%' ");
		}
		String xm = model.getXm();
		if (!StringUtils.isNull(xm)) {
			sb.append("and xm like '%");
			sb.append(xm.replace("'", "‘"));
			sb.append("%' ");
		}
		
		String gwdm = model.getGwdm();
		if (!StringUtils.isNull(gwdm)) {
			sb.append("and gwdm = '");
			sb.append(gwdm.replace("'", "‘"));
			sb.append("' ");
		}
		
		String gwsbsj = model.getGwsbsj();		
		if (!StringUtils.isNull(gwsbsj)) {
			sb.append("and gwsbsj = '");
			sb.append(gwsbsj.replace("'", "‘"));
			sb.append("' ");
		}
		
		String sqsj = model.getSqsj();
		if (!StringUtils.isNull(sqsj)) {
			sb.append("and sqsj = '");
			sb.append(sqsj.replace("'", "‘"));
			sb.append("' ");
		}
		
		String xyyj = model.getXyyj();
		if (!StringUtils.isNull(xyyj)) {
			sb.append("and xyyj = '");
			sb.append(xyyj.replace("'", "‘"));
			sb.append("' ");
		}
		
		String xxyj = model.getXxyj();
		if (!StringUtils.isNull(xxyj)) {
			sb.append("and xxyj = '");
			sb.append(xxyj.replace("'", "‘"));
			sb.append("' ");
		} 
		
		String lxdh = model.getLxdh();
		if (!StringUtils.isNull(lxdh)) {
			sb.append("and lxdh = '");
			sb.append(lxdh.replace("'", "‘"));
			sb.append("' ");
		}
		String xn = model.getXn();
		if (!StringUtils.isNull(xn)) {
			sb.append("and xn = '");
			sb.append(xn.replace("'", "‘"));
			sb.append("' ");
		}
		String nd = model.getNd();
		if (!StringUtils.isNull(nd)) {
			sb.append("and nd = '");
			sb.append(nd.replace("'", "‘"));
			sb.append("' ");
		} 
		
		String xq = model.getXq();
		if (!StringUtils.isNull(xq)) {
			sb.append("and xq = '");
			sb.append(xq.replace("'", "‘"));
			sb.append("' ");
		} 
		
		String sqly = model.getSqly();
		if (!StringUtils.isNull(sqly)) {
			sb.append("and sqly = '");
			sb.append(sqly.replace("'", "‘"));
			sb.append("' ");
		}
		
		String bz = model.getBz();
		if (!StringUtils.isNull(bz)) {
			sb.append("and bz = '");
			sb.append(bz.replace("'", "‘"));
			sb.append("' ");
		} 
		
		String gwxz = model.getGwxz();
		if (!StringUtils.isNull(gwxz)) {
			sb.append("and gwxz = '");
			sb.append(gwxz.replace("'", "‘"));
			sb.append("' ");
		}
		
		String xydm = model.getXydm();
		if (!StringUtils.isNull(xydm)) {
			sb.append("and xydm = '");
			sb.append(xydm.replace("'", "‘"));
			sb.append("' ");
		} 
		
		if(isYrdw){
			sb.append(" and exists (select 1 from yrdwdmb b where a.sqdw=b.yrdwdm and b.dlm='");
			sb.append(model.getUserName() + "')");
		}
		return sb;
	}
	
	/**中国地质大学用人单位更换学生审核查询用于中国地质大学用人单位更换学生信息表zgdzdx_yrdwghxsb*/
	public static StringBuffer getYrdwGhXsxxQuery(String xh,String gwdm,String gwsbsj,String sqsj,String xxyj,String lxdh,String xn,String nd,String xq,String sqly,String bz,String gwxz,String xydm,String bj,StringBuffer query){
		
		if ((xh == null) || xh.equalsIgnoreCase("")) {
			query.append(" and 1=1 ");
		} else {
			if(Check_Input_Value.check2(xh)){
				query.append("and xh = '");
				query.append(xh);
				query.append("' ");
			}
		}
		if ((gwdm == null) || gwdm.equalsIgnoreCase("")) {
			query.append(" and 1=1 ");
		} else {
			if(Check_Input_Value.check2(gwdm)){
				query.append("and gwdm = '");
				query.append(gwdm);
				query.append("' ");
			}
		}
		if ((gwsbsj == null) || gwsbsj.equalsIgnoreCase("")) {
			query.append(" and 1=1 ");
		} else {
			if(Check_Input_Value.check2(gwsbsj)){
				query.append("and gwsbsj = '");
				query.append(gwsbsj);
				query.append("' ");
			}
		}
		if ((sqsj == null) || sqsj.equalsIgnoreCase("")) {
			query.append(" and 1=1 ");
		} else {
			if(Check_Input_Value.check2(sqsj)){
				query.append("and sqsj = '");
				query.append(sqsj);
				query.append("' ");
			}
		}
		if ((xxyj == null) || xxyj.equalsIgnoreCase("")) {
			query.append(" and 1=1 ");
		} else {
			if(Check_Input_Value.check2(xxyj)){
				query.append("and xxyj = '");
				query.append(xxyj);
				query.append("' ");
			}
		}
		if ((lxdh == null) || lxdh.equalsIgnoreCase("")) {
			query.append(" and 1=1 ");
		} else {
			if(Check_Input_Value.check2(lxdh)){
				query.append("and lxdh = '");
				query.append(lxdh);
				query.append("' ");
			}
		}
		if ((xn == null) || xn.equalsIgnoreCase("")) {
			query.append(" and 1=1 ");
		} else {
			if(Check_Input_Value.check2(xn)){
				query.append("and xn = '");
				query.append(xn);
				query.append("' ");
			}
		}
		if ((nd == null) || nd.equalsIgnoreCase("")) {
			query.append(" and 1=1 ");
		} else {
			if(Check_Input_Value.check2(nd)){
				query.append("and nd = '");
				query.append(nd);
				query.append("' ");
			}
		}
		if ((xq == null) || xq.equalsIgnoreCase("")) {
			query.append(" and 1=1 ");
		} else {
			if(Check_Input_Value.check2(xq)){
				query.append("and xq = '");
				query.append(xq);
				query.append("' ");
			}
		}
		if ((sqly == null) || sqly.equalsIgnoreCase("")) {
			query.append(" and 1=1 ");
		} else {
			if(Check_Input_Value.check2(sqly)){
				query.append("and sqly = '");
				query.append(sqly);
				query.append("' ");
			}
		}
		
		if ((bz == null) || bz.equalsIgnoreCase("")) {
			query.append(" and 1=1 ");
		} else {
			if(Check_Input_Value.check2(bz)){
				query.append("and bz = '");
				query.append(bz);
				query.append("' ");
			}
		}
		if ((gwxz == null) || gwxz.equalsIgnoreCase("")) {
			query.append(" and 1=1 ");
		} else {
			if(Check_Input_Value.check2(gwxz)){
				query.append("and gwxz = '");
				query.append(gwxz);
				query.append("' ");
			}
		}
		if ((xydm == null) || xydm.equalsIgnoreCase("")) {
			query.append(" and 1=1 ");
		} else {
			if(Check_Input_Value.check2(xydm)){
				query.append("and xydm = '");
				query.append(xydm);
				query.append("' ");
			}
		}
		if ((bj == null) || bj.equalsIgnoreCase("")) {
			query.append(" and 1=1 ");
		} else {
			if(Check_Input_Value.check2(bj)){
				query.append("and bj = '");
				query.append(bj);
				query.append("' ");
			}
		}
		return query;
	}
}
