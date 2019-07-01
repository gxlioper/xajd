/**
 * @部门:学工产品事业部
 * @日期：2013-12-19 上午10:46:32 
 */
package com.zfsoft.xgxt.base.extend;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 批量操作
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： 张昌路[工号:982]
 * @时间： 2013-12-19 上午10:46:32
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class AllExecute {
	public static final String _EXECUTE_TRUE = "true";
	/**
	 * 统一验证
	 */
	public static final String _EXECUTE_ALLCHECK_ERROR="allerror";
	/**
	 * 单条验证
	 */
	public static final String _EXECUTE_ROWCHECK_ERROR="rowerror";
	/**
	 * 验证错误类型key
	 */
	public static final String _EXECUTE_CHECK_TYPE="checktype";
	private Integer cgts=0;
	boolean isHaveError = false;

	/**
	 * 
	 * @描述: 批量处理数据
	 * @作者：张昌路[工号：982]
	 * @日期：2013-12-19 下午02:49:18
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ie
	 *            批量处理接口
	 * @param data
	 *            批量处理参数
	 * @param isSuccessMes
	 *            是否保存成功信息
	 * @return List<HashMap<String,String>> 返回类型
	 */
	public List<HashMap<String, String>> execute(IExecute ie,
			List<Object> data, boolean isSuccessMes) {
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		String ywMessage = null;
		ywMessage=ie.allCheck(data);
		//所有验证成功才进行逐条验证
		if(_EXECUTE_TRUE.equals(ywMessage)){
			for (Object o : data) {
				HashMap<String, String> hm = new HashMap<String, String>();
				ywMessage = ie.execute(o);
				// 执行业务不成功
				if (!_EXECUTE_TRUE.equals(ywMessage)) {
					hm.put("message", ie.message(o, ywMessage));
					//标注为行验证错误
					hm.put(_EXECUTE_CHECK_TYPE, _EXECUTE_ROWCHECK_ERROR);
					isHaveError = true;
				} else if (isSuccessMes) {// 如果需要记录成功信息
					hm.put("success", ie.message(o, ywMessage));
				}else{
					cgts++;
				}
				if (hm.size() > 0) {
					list.add(hm);
				}
			}
		}else{
			HashMap<String, String> hm = new HashMap<String, String>();
			hm.put("message", ywMessage);
			//标注为统一验证错误
			hm.put(_EXECUTE_CHECK_TYPE, _EXECUTE_ALLCHECK_ERROR);
			isHaveError=true;
			list.add(hm);
		}
		return list;
	}

	public boolean isHaveError() {
		return isHaveError;
	}
	public Integer getSuccessNumber(){
		return cgts;
	}
}
