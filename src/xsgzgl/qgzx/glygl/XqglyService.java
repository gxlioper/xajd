package xsgzgl.qgzx.glygl;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

public class XqglyService extends SuperServiceImpl<XqglyForm, XqglyDao> {
	/**
	 * 
	 * @描述: 验证校区是否重复
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-9-6 下午02:25:31
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zgh
	 * @param xq
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean checkIsRepeat(String zgh,String xq){
		return dao.checkIsRepeat(zgh, xq);
	}
	
	/**
	 * 
	 * @描述: 获取校区
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-9-6 下午02:28:02
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getXqList(){
		return dao.getXqList();
	}
	
	/**
	 *删除 
	 * @throws Exception 
	 */
	public boolean runDeletes(String[] ids) throws Exception{
		return dao.runDeletes(ids);
	}
}
