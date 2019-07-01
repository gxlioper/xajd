package xsgzgl.qgzx.yftssz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import xgxt.form.User;
import xsgzgl.comm.BasicService;

/**
 * 勤工助学-勤工岗位管理-岗位信息管理
 */
public class QgzxYftsszService extends BasicService {
	
	
	public String[] getYftsszArr(QgzxYftsszForm t, User user) throws Exception{
		return qgzxYftsszDAO.getYftsszArr(t, user);
	}
	/**
	 * 
	 * @描述: 修改保存
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-7-19 上午11:03:58
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ywid
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * String 返回类型 
	 * @throws
	 */
	QgzxYftsszDAO qgzxYftsszDAO = new QgzxYftsszDAO();
	public boolean bcYftssz(List<String[]> params,QgzxYftsszForm t) throws Exception {
		List<QgzxYftsszForm> gwsqrList = new ArrayList<QgzxYftsszForm>();
		boolean result = true;
		result = qgzxYftsszDAO.xgYftssz(params,t);
		return result;
	}
	
	
}
