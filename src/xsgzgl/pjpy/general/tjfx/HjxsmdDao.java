package xsgzgl.pjpy.general.tjfx;

import xgxt.DAO.DAO;
import xgxt.comm.CommDAO;

public class HjxsmdDao extends CommDAO {
	private DAO dao=DAO.getInstance();
	public String[] getxmList() throws Exception{
		String sql="select distinct xmmc from xg_view_pjpy_xyhjtj";
		
		return dao.getRs(sql, new String[]{}, "xmmc");
	}

	public String[] getXmlist(String xydm, String string, HjxsmdForm myform) throws Exception{
		// TODO 自动生成方法存根
		String  sql="select b.xm from xg_view_pjpy_xyhjtj a left join xsxxb b on a.xh=b.xh where a.xydm=? and a.pjsj=? and xmmc = ?";
		return dao.getRs(sql, new String[]{xydm,myform.getXn(),string}, "xm");
	}
}	
