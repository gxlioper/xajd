package xgxt.wjcf.bjqn;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xgxt.DAO.DAO;
import xgxt.dtjs.gdby.tygl.BasicExtendService;
import xgxt.utils.CommonQueryDAO;

public class BjqnWjcfService extends BasicExtendService{
	public Map<String, String> getCfInfo(String pkValue){
		String pk = "xh||cfwh||cfsj";
		String tableName = "view_wjcf";
		String[] colList = new String[]{"xh","xm","nj","xb","xymc","zymc","bjmc","cfwh","cfsj","cflbmc","cflb",
				"zzmmmc","cflb","cfyymc","cfyy","sbsj"};
		
		return CommonQueryDAO.commonQueryOne(tableName, colList, pk, pkValue);
	}
	
	public List<HashMap<String, String>> getJjcflbList(String cfdm){
		String query = " where cfjb < (select cfjb from cflbdmb where cflbdm = ?) order by cfjb";
		
		return CommonQueryDAO.commonQueryforList("cflbdmb", query, new String[]{cfdm}, new String[]{"cflbdm", "cflbmc"}, "");
	}
	
	public boolean Cfjj(String pkValue, WjcfModel model){
		boolean flag = false;
		String[] sqlArr = new String[2];
		StringBuilder sql = new StringBuilder();
		sql.append("insert into wjcf_bjqnzzxy_cfjjb (xn,nd,xh,sbsj,jjsj,cflb,cfyy,cfsj,cfwh,ssjg,")
		   .append("bz,xq,cxwh,cxsj,xyclyj,xxclyj,cfnx,kcsj,xsbx1,xsbx2,xsbx3,xsbx4,xyyj,xxsh,lxdh,wjsj,")
		   .append("jtwjsy,zacfqk,qtcfqk,cxjg,xysh,xndsh,xndclyj,ggcflbdm,cfyj,fjsclj,cxclsj,cxclwh,kf,")
		   .append("lxckqkssj,lxckqjssj,lxcksj,gryj,zgbmclyj,ncxsj,lswjjl,ycflb,sfjw,xftz,sfsb,sbsy,xsqr,")
		   .append("qrsj,xgcyj,xxyj,bzryj,fsjname)")
		   .append(" select xn,nd,xh,sbsj,'")
		   .append(model.getJjsj())
		   .append("' jssj,cflb,cfyy,cfsj,cfwh,ssjg,")
		   .append("bz,xq,cxwh,cxsj,xyclyj,xxclyj,cfnx,kcsj,xsbx1,xsbx2,xsbx3,xsbx4,xyyj,xxsh,lxdh,wjsj,")
		   .append("jtwjsy,zacfqk,qtcfqk,cxjg,xysh,xndsh,xndclyj,ggcflbdm,cfyj,fjsclj,cxclsj,cxclwh,kf,")
		   .append("lxckqkssj,lxckqjssj,lxcksj,gryj,zgbmclyj,ncxsj,lswjjl,ycflb,sfjw,xftz,sfsb,sbsy,xsqr,")
		   .append("qrsj,xgcyj,xxyj,bzryj,fsjname from wjcfb where xh||cfwh||cfsj='")
		   .append(pkValue)
		   .append("'");
		
		sqlArr[0] = sql.toString();
		
		sql = new StringBuilder();
		sql.append("update wjcfb set cfwh='");
		sql.append(model.getCfwh());
		sql.append("',cflb='");
		sql.append(model.getCflb());
		sql.append("' where xh||cfwh||cfsj='");
		sql.append(pkValue);
		sql.append("'");
		
		sqlArr[1] = sql.toString();
		
		DAO dao = DAO.getInstance();
		
		try {
			dao.runBatch(sqlArr);
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return flag;
	}
}
