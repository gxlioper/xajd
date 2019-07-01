/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016��10��14�� ����5:10:02 
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
 * @ϵͳ����: ѧ����������ϵͳ
 * @�๦������: �����������
 * @���ߣ� �����[����:445]
 * @ʱ�䣺 2016��10��14�� ����5:10:02 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
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
	      ����: @see java.util.concurrent.Callable#call()
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
				//��Ŀ����
				//���������ϸ
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
		//��������洢
		service.batchInsertCheckResult(resultList);
	}

	/*
	      ����: @see com.zfsoft.xgxt.comm.task.ProgressCallable#getProgress()
	 */

	public String getProgress() {
		float f = ((float)current / cpmdList.size())*100;
		return String.format("%.2f%%", f);
	}


}
