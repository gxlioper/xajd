package xsgzgl.pjpy.bjlhdx.zhcp;

import xgxt.DAO.DAO;
import xsgzgl.pjpy.general.PjpyGeneralForm;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 评奖评优_综合测评_通用_DAO类
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author qlj
 *
 * @version 1.0
 */

public class PjpyZhcpDAO extends xsgzgl.pjpy.general.zhcp.PjpyZhcpDAO {

	/**
	 * 将百分制的综测分转换成五分制
	 * @return
	 * @throws Exception
	 */
	public boolean updateZcf() throws Exception{
		
		DAO dao=DAO.getInstance();
		// -------------------获取评奖周期 begin ------------------------
		PjpyGeneralForm jbszForm=PjpyGeneralForm.getJbszModel();
		
		String pjxn=jbszForm.getPjxn();
		
		String pjxq=jbszForm.getPjxq();
		// -------------------获取评奖周期 emd ------------------------
		
		StringBuilder sql= new StringBuilder();
		
		sql.append(" update xg_pjpy_zhcpb set zd1=round(to_number(nvl(zd1,0))/20,2) where xn=? and xq=? ");
		
		return dao.runUpdate(sql.toString(), new String[]{pjxn,pjxq});
		
	}
}
