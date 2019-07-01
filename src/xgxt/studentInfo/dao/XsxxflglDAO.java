package xgxt.studentInfo.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import common.Globals;

import xgxt.DAO.DAO;
import xgxt.form.CommanForm;
import xgxt.pjpy.zgkd.PjpyZgkdZhszcpDAO;
import xgxt.studentInfo.model.StudentInfoForm;
import xgxt.studentInfo.model.XsxxflForm;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.String.StringUtils;

public class XsxxflglDAO extends DAO{
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
	 * 保存分流信息
	 * @param model
	 * @return boolean
	 * */
	public boolean saveXsxxfl(XsxxflForm model){
		String zrbjdm = model.getZrbjdm();
		String xh = model.getXh();
		String sql = "";
		boolean result = false;
		
		if(checkExists("xsxxb", "xh", xh)){//学生信息在本地表存在
			try{
				sql = "update xsxxb set bjdm=? where xh=?";
				result = runUpdate(sql, new String[]{zrbjdm,xh});
			} catch (Exception e) {
				result = false;
				e.printStackTrace();
			}
		}else{
			sql = "insert into xsxxb(xh,xy,zymc,bjmc,bjdm,zydm,xydm,bz,xm,xmpy,nj,syd,csrq,sfzh,xb,mz,zzmm,lxdh,dzyx,cym,sg,tz,tc,kslb,rxfs,pyfs,pycc,xjlb,xszp,xjztm,xz,whcd,rxqdw,jtdh,jrgqtsj,jrgcdsj,jtcygc,jlcfjl,jkzk,jtdz,jtyb,jg,xx,ah,sfdk,shgxxm1,shgxgx1,shgxgzdw1,shgxzw1,shgxdwdh1,shgxsjhm1,shgxxm2,shgxgx2,shgxgzdw2,shgxzw2,shgxdwdh2,shgxsjhm2,jtqkjj,jtjjqk,sjhm,byxx,kh,rxrq,fdyxm,gkcj,qqhm,hkxz,zyjb,hkszd,ssyq,ssld,jtdzs,jtdzx,sfzsb,sfzfx,zjdm,sfyby,byny,sfzx,zw,thbs,dybj,shbj,xwzsxlh,xwzsbh,xw,xzxm,zsxlh,zsbh,bjyjl,csd,zsjj,xxxs,bxlx,bxxs,fxzyfx,fxzy,zylb,dqszj,pyfx,zyfx,xxszd,ksh,xxfx,zslb,gj,sfjh,ccqj,byzffztdm,xwzsxxdz,jgs,jgshi,jgx,ssbh,rxnj,nfby,sfzc,dasfyl,daylyy,yxdm,sfzz,sfsf,sfdl,dxhwp,bysj,zxwyyzdm,wydj,jsjdj,lxdz,yzbm,shzw,jypx,xmsj,zgzs,jljn,sybz1,sybz2,sybz3,xldm,zkzh,grjl,sfcj,ssch,zsjzrq,qsdh,ykth,yhkh,xslb,xslx,sfbys,yhdm,hkshen,hkshi,hkxian,zcsxhm,rxqwhcd)(select xh,xymc,zymc,bjmc,'" + zrbjdm + "',zydm,xydm,bz,xm,xmpy,nj,syd,csrq,sfzh,xb,mz,zzmm,lxdh,dzyx,cym,sg,tz,tc,kslb,rxfs,pyfs,pycc,xjlb,xszp,xjztm,xz,whcd,rxqdw,jtdh,jrgqtsj,jrgcdsj,jtcygc,jlcfjl,jkzk,jtdz,jtyb,jg,xx,ah,sfdk,shgxxm1,shgxgx1,shgxgzdw1,shgxzw1,shgxdwdh1,shgxsjhm1,shgxxm2,shgxgx2,shgxgzdw2,shgxzw2,shgxdwdh2,shgxsjhm2,jtqkjj,jtjjqk,sjhm,byxx,kh,rxrq,fdyxm,gkcj,qqhm,hkxz,zyjb,hkszd,ssyq,ssld,jtdzs,jtdzx,sfzsb,sfzfx,zjdm,sfyby,byny,sfzx,zw,thbs,dybj,shbj,xwzsxlh,xwzsbh,xw,xzxm,zsxlh,zsbh,bjyjl,csd,zsjj,xxxs,bxlx,bxxs,fxzyfx,fxzy,zylb,dqszj,pyfx,zyfx,xxszd,ksh,xxfx,zslb,gj,sfjh,ccqj,byzffztdm,xwzsxxdz,jgs,jgshi,jgx,ssbh,rxnj,nfby,sfzc,dasfyl,daylyy,yxdm,sfzz,sfsf,sfdl,dxhwp,bysj,zxwyyzdm,wydj,jsjdj,lxdz,yzbm,shzw,jypx,xmsj,zgzs,jljn,sybz1,sybz2,sybz3,xldm,zkzh,grjl,sfcj,ssch,zsjzrq,qsdh,ykth,yhkh,xslb,xslx,sfbys,yhdm,hkshen,hkshi,hkxian,zcsxhm,rxqwhcd from view_xsxxb where xh='" + xh + "')";
			try{
				result = runUpdate(sql, new String[]{});
			} catch (Exception e) {
				result = false;
				e.printStackTrace();
			}
		}
		return result;
	}
	
	/**
	 * 批量保存学生分流信息
	 * @param xhArr
	 * @param  model
	 * @return boolean
	 * @throws SQLException 
	 * */
	public boolean saveXsxxflBatch(String[] xhArr, XsxxflForm model) throws SQLException{
		String[] sqlArr = new String[xhArr.length];
		String bjdm = model.getZrbjdm();
		for(int i=0; i<xhArr.length; i++){			
			if(checkExists("xsxxb", "xh", xhArr[i])){
				sqlArr[i] = "update xsxxb set bjdm='" + bjdm + "' where xh='" + xhArr[i] + "'";
			}else{
				sqlArr[i] = "insert into xsxxb(xh,xy,zymc,bjmc,bjdm,zydm,xydm,bz,xm,xmpy,nj,syd,csrq,sfzh,xb,mz,zzmm,lxdh,dzyx,cym,sg,tz,tc,kslb,rxfs,pyfs,pycc,xjlb,xszp,xjztm,xz,whcd,rxqdw,jtdh,jrgqtsj,jrgcdsj,jtcygc,jlcfjl,jkzk,jtdz,jtyb,jg,xx,ah,sfdk,shgxxm1,shgxgx1,shgxgzdw1,shgxzw1,shgxdwdh1,shgxsjhm1,shgxxm2,shgxgx2,shgxgzdw2,shgxzw2,shgxdwdh2,shgxsjhm2,jtqkjj,jtjjqk,sjhm,byxx,kh,rxrq,fdyxm,gkcj,qqhm,hkxz,zyjb,hkszd,ssyq,ssld,jtdzs,jtdzx,sfzsb,sfzfx,zjdm,sfyby,byny,sfzx,zw,thbs,dybj,shbj,xwzsxlh,xwzsbh,xw,xzxm,zsxlh,zsbh,bjyjl,csd,zsjj,xxxs,bxlx,bxxs,fxzyfx,fxzy,zylb,dqszj,pyfx,zyfx,xxszd,ksh,xxfx,zslb,gj,sfjh,ccqj,byzffztdm,xwzsxxdz,jgs,jgshi,jgx,ssbh,rxnj,nfby,sfzc,dasfyl,daylyy,yxdm,sfzz,sfsf,sfdl,dxhwp,bysj,zxwyyzdm,wydj,jsjdj,lxdz,yzbm,shzw,jypx,xmsj,zgzs,jljn,sybz1,sybz2,sybz3,xldm,zkzh,grjl,sfcj,ssch,zsjzrq,qsdh,ykth,yhkh,xslb,xslx,sfbys,yhdm,hkshen,hkshi,hkxian,zcsxhm,rxqwhcd)(select xh,xymc,zymc,bjmc,'" + bjdm +"',zydm,xydm,bz,xm,xmpy,nj,syd,csrq,sfzh,xb,mz,zzmm,lxdh,dzyx,cym,sg,tz,tc,kslb,rxfs,pyfs,pycc,xjlb,xszp,xjztm,xz,whcd,rxqdw,jtdh,jrgqtsj,jrgcdsj,jtcygc,jlcfjl,jkzk,jtdz,jtyb,jg,xx,ah,sfdk,shgxxm1,shgxgx1,shgxgzdw1,shgxzw1,shgxdwdh1,shgxsjhm1,shgxxm2,shgxgx2,shgxgzdw2,shgxzw2,shgxdwdh2,shgxsjhm2,jtqkjj,jtjjqk,sjhm,byxx,kh,rxrq,fdyxm,gkcj,qqhm,hkxz,zyjb,hkszd,ssyq,ssld,jtdzs,jtdzx,sfzsb,sfzfx,zjdm,sfyby,byny,sfzx,zw,thbs,dybj,shbj,xwzsxlh,xwzsbh,xw,xzxm,zsxlh,zsbh,bjyjl,csd,zsjj,xxxs,bxlx,bxxs,fxzyfx,fxzy,zylb,dqszj,pyfx,zyfx,xxszd,ksh,xxfx,zslb,gj,sfjh,ccqj,byzffztdm,xwzsxxdz,jgs,jgshi,jgx,ssbh,rxnj,nfby,sfzc,dasfyl,daylyy,yxdm,sfzz,sfsf,sfdl,dxhwp,bysj,zxwyyzdm,wydj,jsjdj,lxdz,yzbm,shzw,jypx,xmsj,zgzs,jljn,sybz1,sybz2,sybz3,xldm,zkzh,grjl,sfcj,ssch,zsjzrq,qsdh,ykth,yhkh,xslb,xslx,sfbys,yhdm,hkshen,hkshi,hkxian,zcsxhm,rxqwhcd from view_xsxxb where xh='" + xhArr[i] + "')";
			}
		}
		int[] chkReslt = runBatch(sqlArr);
		return checkBatch(chkReslt);
	}
}
