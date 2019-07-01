package xgxt.studentInfo.xbemy;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import xgxt.daoActionLogic.StandardOperation;
import xgxt.utils.String.NullStringException;
import xgxt.utils.String.StringUtils;


/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 西北二民院学生信息Service</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 李容</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-06-30</p>
 */
public class XbemyStudentInfoService {
	public HashMap<String, String> getAppOfTranferSch(TransferSchModel model){
		HashMap<String, String> map = new HashMap<String, String>();
		xbemyStudentInfoDAO dao = new xbemyStudentInfoDAO();
		String zxlx = dao.getZxlx(model);
		try {
			if(StringUtils.isEqual(zxlx, "转入")){
				map = dao.getZrInfo(model);
			}
			if(StringUtils.isEqual(zxlx, "转出")){
				map = dao.getZcInfo(model);
			}
		} catch (NullStringException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return map;
	}
	
	public boolean insertNewStuInfo(XbemyStudentInfoForm model,HttpServletRequest request) throws Exception{
		boolean flag = true;
		xbemyStudentInfoDAO dao = new xbemyStudentInfoDAO();
		String zdzrbj = model.getZdzrbj();
		String xh = model.getXh();		
		String sqrq = model.getSqrq();
		String sql = ""; 
		try {
			//如果用户选择将学生信息自动转入到学生基本信息库
			if(zdzrbj!=null && StringUtils.isEqual(zdzrbj, "1")){
				//将信息插入学生信息库
				if(dao.isStuExists(xh)){
					flag = StandardOperation.delete("xsxxb", "xh", xh, request);					
				}
				sql = "insert into xsxxb( xh,xm,xb,xy,xydm,zymc,zydm,bjmc,bjdm,nj,xjztm,ksh,xxszd,cym,zyfx,"+
			          "pyfx,xz,zylb,fxzy,fxzyfx,bxxs,bxlx,xxxs,zsjj,pycc,syd," + 
			          "rxrq,rxfs,sfzh,mz,jg,csrq,csd,zzmm,byny,zsbh,zsxlh,xw," + 
			          "xwzsbh,xwzsxlh,xzxm,shbj,dqszj,dybj,thbs,bz,lxdh,bjyjl)(select xh,xm," +
			          "xb,(select distinct xymc from view_njxyzybj b where a.zrxydm=b.xydm)zrxymc," +
			          "zrxydm,(select distinct zymc from view_njxyzybj b where a.zrzydm=b.zydm)zrzymc,zrzydm," +
			          "(select distinct bjmc from view_njxyzybj b where a.zrbjdm=b.bjdm)zrbjmc,zrbjdm,zrnj,xjztm,ksh,xxszd,cym,zyfx,"+
			          "pyfx,zrxz,zylb,fxzy,fxzyfx,bxxs,bxlx,xxxs,zsjj,zrxlcc,syd," + 
			          "rxsj,rxfs,sfzh,mzdm,jg,csrq,csd,zzmm,byrq,zsbh,zsxlh,xw," + 
			          "xwzsbh,xwzsxlh,xzxm,shbj,zrnj,dybj,thbs,bz,lxdh,bjyjl from xbemy_xszxsqb a where xh||sqrq='" + xh + sqrq + "')";
				flag = StandardOperation.update("xsxxb", sql, request);					
				
			}
		} catch (NullStringException e) {
			e.printStackTrace();
		}
		return flag;
	}
}
