package xsgzgl.gyjc.jcsd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.transaction.TransactionControl;

import common.newp.ArrayUtil;

public class JcsdService extends SuperServiceImpl<JcsdForm, JcsdDao> {
	/**
	 * @throws Exception 
	 * 
	 * @描述: 查询人员分配List
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-4-10 上午10:28:01
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getRyfpList(JcsdForm t, User user) throws  Exception{
		return dao.getRyfpList(t, user);
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述: 分配各类型人员保存(批量保存方式)
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-4-10 上午11:39:41
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	@TransactionControl
	public boolean saveRyFp(JcsdForm t,String js) throws Exception{
		/**
		 * 参数组合
		 */
		List<String[]> paraList = new ArrayList<String[]>();
		String[] zghs = t.getZghs();
		String xydm = t.getXydm();
		String jjlx = t.getJjlx();
		/**
		 * 参数组合
		 */
		for (int i = 0; i < zghs.length; i++) {
			paraList.add(new String[]{xydm,zghs[i],jjlx,js});
		}
		boolean rs = dao.saveFpry(paraList);
		if(!rs){
			throw new SystemException(MessageKey.SYS_SAVE_FAIL);
		}
		return rs;
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述: 取消分配
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-4-10 下午01:55:54
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param js
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean cancelRyfp(JcsdForm t,String js) throws Exception{
		return dao.cancelRyfp(t, js);
	}
}
