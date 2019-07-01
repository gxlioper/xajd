package xsgzgl.gyjc.jcsd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.transaction.TransactionControl;

public class PfbzService extends SuperServiceImpl<PfbzForm, PfbzDao> {
	/**
	 * @throws Exception 
	 * 
	 * @描述: 保护评分标准
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-4-11 下午06:04:19
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean savePfbz(PfbzForm t) throws Exception{
		//先判断评分标准是否重复
		boolean rs = dao.checkRepeat(t);
		if(!rs){
			throw new SystemException(MessageKey.XG_GYJC_PFBZ_REPEAT);
		}
		rs = dao.checkRepeatXh(t);
		if(!rs){
			throw new SystemException(MessageKey.XG_GYJC_PFBZ_XH_REPEAT);
		}
		if(StringUtils.isNotNull(t.getGuid())){
			return dao.runUpdate(t);
		}else{
			return dao.runInsert(t);
		}
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述:删除评分标准
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-4-11 下午06:53:24
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	@TransactionControl
	public boolean delRs(String[] guids, String[] fjids) throws Exception{
		//先对数据进行分组
		List<String> fjList = new ArrayList<String>();
		List<String> zjList = new ArrayList<String>();
		for (int i = 0; i < fjids.length; i++) {
			if("top".equals(fjids[i])){
				fjList.add(guids[i]);
			}else{
				zjList.add(guids[i]);
			}
		}
		
		boolean rs = true;
		if(!zjList.isEmpty() && zjList.size() > 0){
			//先判断子项目是否有存在已被使用
			if(!dao.checkIsUserd(zjList.toArray(new String[]{}))){
				throw new SystemException(MessageKey.XG_GYJC_PFBZ_USED);
			}
			rs = dao.delPfbz(zjList.toArray(new String[]{}));
		}
		if(!rs){
			throw new SystemException("");
		}
		
		if(!fjList.isEmpty() && fjList.size() > 0){
			//判断是否存在子项目
			if(!dao.checkIsCzZjxm((fjList.toArray(new String[]{})))){
				throw new SystemException(MessageKey.XG_GYJC_PFBZ_CZZXM);
			}
			rs = dao.delPfbz(fjList.toArray(new String[]{}));
		}
		if(!rs){
			throw new SystemException(MessageKey.SYS_DEL_FAIL);
		}
		return rs;
	}
	
	/**
	 * 
	 * @描述:获取评分标准下拉框中内容
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-4-12 上午09:05:14
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getXmSelectList(PfbzForm t){
		return dao.getXmSelectList(t);
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述:修改model
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-4-12 上午11:55:04
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * PfbzForm 返回类型 
	 * @throws
	 */
	public PfbzForm getPfbzModel(String guid) throws Exception{
		return dao.getPfbzModel(guid);
	}
	/**
	 * @throws Exception 
	 * 
	 * @描述:获取评分标准
	 * @作者：xiaxia[工号：1104]
	 * @日期：2017-4-18 下午05:32:18
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getPfbzListAjax(String fjid){
		return dao.getPfbzListAjax(fjid);
		
	}
}
