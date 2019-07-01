/**
 * @部门:学工产品事业部
 * @日期：2018-3-21 上午09:51:43 
 */  
package xgxt.action.yktyj;

import java.util.TimerTask;

import xgxt.DAO.DAO;
import xgxt.xtwh.XtwhService;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称:系统设置—系统初始化—一卡通预警（杭州师范个性化）
 * 自动执行的方法体
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： lgx[工号:1553]
 * @时间： 2018-3-21 上午09:51:43 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class AutoTask extends TimerTask {
	
	XtwhService service = new XtwhService();
	DAO dao = DAO.getInstance();
	@Override
	public void run() {
		String yjzt = service.getYjzt();
		if("1".equals(yjzt)){
			try {
				boolean rs = dao.runProcuder("{call PRO_XG_XSXX_YKTYJ()}",
					 new String[] {});
				System.out.println(rs);
			} catch (Exception e) {
				cancel();
				e.printStackTrace();
			}
		}else{
			System.out.println("---一卡通预警已关闭---");
		}
	}

}
