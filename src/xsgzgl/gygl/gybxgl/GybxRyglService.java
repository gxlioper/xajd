/**
 * @部门:学工产品事业部
 * @日期：2015-7-13 上午09:12:57 
 */  
package xsgzgl.gygl.gybxgl;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： ChenQ[工号:856]
 * @时间： 2015-7-13 上午09:12:57 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class GybxRyglService extends SuperServiceImpl<GybxRyglForm, GybxRyglDao> {
	private GybxRyglDao dao = new GybxRyglDao();

	@SuppressWarnings("deprecation")
	public GybxRyglService() {
		super.setDao(dao);
	}
	
	public List<HashMap<String, String>> getBxlbYhList(GybxRyglForm model) throws Exception{
		return dao.getBxlbYhList(model);
	}
	public String saveBxry(GybxRyglForm model) throws Exception{
		String[] yhlist = model.getYhAll();
		String messageKey =  MessageKey.SYS_SAVE_SUCCESS;
		for(int i=0;i<yhlist.length;i++){
				boolean flag = dao.saveBxry(model.getBxlb(), yhlist[i]);
				if(!flag){
					messageKey =  MessageKey.SYS_SAVE_FAIL;
					break;
				}
		}
		return messageKey;
	}
	
	public String delBxry(GybxRyglForm model) throws Exception{
		String[] yhlist = model.getYhAll();
		String messageKey =  MessageKey.SYS_DEL_SUCCESS;
		for(int i=0;i<yhlist.length;i++){
			boolean flag = dao.delBxry(model.getBxlb(), yhlist[i]);
			if(!flag){
				messageKey =  MessageKey.SYS_DEL_FAIL;
				break;
			}
		}
		return messageKey;
	}
}
