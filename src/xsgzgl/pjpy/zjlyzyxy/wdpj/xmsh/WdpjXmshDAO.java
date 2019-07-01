package xsgzgl.pjpy.zjlyzyxy.wdpj.xmsh;

import java.util.HashMap;

import xgxt.DAO.DAO;
import xgxt.form.User;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.general.wdpj.xmsh.WdpjXmshModel;



public class WdpjXmshDAO extends xsgzgl.pjpy.general.wdpj.xmsh.WdpjXmshDAO{
	/**
	 * 获取学生评奖申请详细
	 * 信息（包括学生基本信息）
	 * 
	 * @author qlj
	 */
	public HashMap<String,String> getXmsqInfo(WdpjXmshModel model,User user) {
		
		PjpyGeneralForm jbszModel=PjpyGeneralForm.getJbszModel();
		// 项目代码
		String xmdm = model.getXmdm();
		// 学号
		String xh=model.getXh()[0];
		
		String pjxn = jbszModel.getPjxn();
		
		String pjxq = jbszModel.getPjxq();
		
		String pjnd = jbszModel.getPjnd();
	
		StringBuilder sql = new StringBuilder();

		sql.append(" select a.pjxn,a.pjxq,a.pjnd,a.xh,a.kzzd1,a.kzzd2,a.kzzd3,a.kzzd4, ");   
		sql.append(" a.sqsj,a.sqly,b.xmmc,b.xmsm,c.nj,c.xm,c.xymc,c.zymc,c.bjmc ");   
		sql.append(" from xg_pjpy_pjxmsqb a left join xg_pjpy_pjxmwhb b ");   
		sql.append(" on a.xmdm=b.xmdm and a.pjxn=b.pjxn and a.pjxq=b.pjxq ");   
		sql.append(" and a.pjnd=b.pjnd left join xg_view_pjpy_pjryk c on a.xh=c.xh   ");   
		sql.append(" where a.xmdm = ? ");   
		sql.append(" and a.pjxn = ? ");   
		sql.append(" and a.pjxq = ? ");   
		sql.append(" and a.pjnd = ? ");   
		sql.append(" and a.xh = ? ");   
 
		DAO dao = DAO.getInstance();

		return dao.getMapNotOut(sql.toString(), new String[]{xmdm,pjxn,pjxq,pjnd,xh});
	}
	
	
}
