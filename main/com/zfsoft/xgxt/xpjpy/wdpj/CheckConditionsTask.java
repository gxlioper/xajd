/**
 * @部门:学工产品事业部
 * @日期：2016年10月14日 下午5:10:02 
 */  
package com.zfsoft.xgxt.xpjpy.wdpj;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zfsoft.xgxt.comm.task.ProgressCallable;
import com.zfsoft.xgxt.xpjpy.wdpj.sqsh.SqshService;

import xgxt.utils.date.DateUtils;

/** 
 * @系统名称: 学生工作管理系统
 * @类功能描述: 检测条件任务
 * @作者： 屈朋辉[工号:445]
 * @时间： 2016年10月14日 下午5:10:02 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class CheckConditionsTask  implements ProgressCallable<String> {

	
	private List<HashMap<String,String>> cpmdList;
	private int current = 0;
	
	public CheckConditionsTask(List<HashMap<String,String>> cpmdList){
		this.cpmdList = cpmdList;
	}
	
	public CheckConditionsTask(){
	}
	
	/*
	      描述: @see java.util.concurrent.Callable#call()
	 */

	
	public String call() throws Exception {
		runTask();
		return null;
	}

	@SuppressWarnings("unchecked")
	private void runTask() throws Exception, SQLException {
		SqshService service = new SqshService();
		
		if (cpmdList == null || cpmdList.isEmpty()){
			throw new NullPointerException("studentList can`t null ! ");
		}
		List<String[]> resultList = new ArrayList<String[]>();
		
		for (HashMap<String,String> map : cpmdList){
			Map<String,Object> infoMap = service.getXmsqInfoList(map.get("xh"));
			
			for (Map.Entry<String, Object> entry : infoMap.entrySet()){
				String xmdm = entry.getKey();
				if ("wsqList".equals(xmdm) || "ysqList".equals(xmdm)){
					 continue;
				}
				String[] resultArr = new String[5];
				resultArr[0] = map.get("xh");
				resultArr[1] = xmdm;
				//项目代码
				//条件检测明细
				List<HashMap<String, String>> detailList = (List<HashMap<String, String>>) entry.getValue();
				
				boolean checkResult = true;
				StringBuffer checkInfo = new StringBuffer();
				
				if (detailList != null && !detailList.isEmpty()){
					for (Map<String,String> info : detailList){
						if (!Boolean.valueOf(info.get("result"))){
							checkResult = false;
						}
						checkInfo.append(String.format("%s=%s;", info.get("result"),info.get("sqts")));
					}
				} 
				
				
				resultArr[2] = checkInfo.toString();
				resultArr[3] = String.valueOf(checkResult);
				resultArr[4] = DateUtils.getCurrTime();
				
				resultList.add(resultArr);
			}
			
			current ++;
		}
		//将检测结果存储
		service.batchInsertCheckResult(resultList);
	}

	/*
	      描述: @see com.zfsoft.xgxt.comm.task.ProgressCallable#getProgress()
	 */

	public String getProgress() {
		float f = ((float)current / cpmdList.size())*100;
		return String.format("%.2f%%", f);
	}


}
