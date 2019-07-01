/**
 * @部门:学工产品事业部
 * @日期：2015-6-19 下午02:08:59 
 */  
package com.zfsoft.xgxt.xsxx.dyxj.zpjg;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @类功能描述: 自评结果
 * @作者： 屈朋辉 [工号:445]
 * @时间： 2015-6-19 下午02:08:59 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZpjgService extends SuperServiceImpl<ZpjgModel, ZpjgDao> {

	/**查询是否已申请**/
	public String getCount(ZpjgModel model){
		return dao.getCount(model);
	}
	
	 //德育自评报表导出信息获取
    public HashMap<String, String> getDyzpbbxx(String id,String flag){
    	return dao.getDyzpbbxx(id,flag);
    }
    
    //德育自评等级查询
    public String getXsZpdj(String djdm){
    	return dao.getXsZpdj(djdm);
    }
    
    //德育自评班级汇总表
    public List<HashMap<String, String>> getDyzpHzlist(String bjdm,String xn,String xq){
    	return dao.getDyzpHzlist(bjdm,xn,xq);
    }
    
    //德育自评班级汇总表
    public List<HashMap<String, String>> getDyzpHztjlist(String bjdm,String xn,String xq){
    	return dao.getDyzpHztjlist(bjdm,xn,xq);
    }
    
  //德育自评学院汇总表
    public List<HashMap<String, String>> getDyzpXyHzlist(String xydm,String xn,String xq){
    	return dao.getDyzpXyHzlist(xydm, xn, xq);
    }
    
    //德育自评学院汇总表最后求和
    public HashMap<String, String> getDyzpXyHzSumlist(String xydm,String xn,String xq){
    	return dao.getDyzpXyHzSumlist(xydm, xn, xq);
    }
    
  //德育自评学校汇总表
    public List<HashMap<String, String>> getDyzpXxHzlist(String xn,String xq){
    	return dao.getDyzpXxHzlist(xn, xq);
    }
    
    //德育自评学校汇总表最后求和
    public HashMap<String, String> getDyzpXxHzSumlist(String xn,String xq){
    	return dao.getDyzpXxHzSumlist(xn, xq);
    }
    
    /**
     * 
     * @描述: 德育学生明细汇总取学号List
     * @作者：yxy[工号：1206]
     * @日期：2016-10-20 下午06:43:10
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param ids
     * @return
     * List<HashMap<String,String>> 返回类型 
     * @throws
     */
    public List<HashMap<String, String>> getDistinctXh(String[] ids){
    	return dao.getDistinctXh(ids);
    }
    
    /**
     *
     * @描述:德育自评明细汇总学生信息
     * @作者：yxy[工号：1206]
     * @日期：2016-10-20 下午06:52:29
     * @修改记录: 修改者名字-修改日期-修改内容
     * @return
     * HashMap<String,String> 返回类型 
     * @throws
     */
    public HashMap<String, String> getDyzpMxhzXsxx(String xh){
    	return dao.getDyzpMxhzXsxx(xh);
    }
    
    /**
     * 
     * @描述: 
     * @作者：yxy[工号：1206]
     * @日期：2016-10-20 下午07:04:56
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param xh
     * @return
     * List<HashMap<String,String>> 返回类型 
     * @throws
     */
    public List<HashMap<String, String>> getEveryXsDyMx(String xh){
    	return dao.getEveryXsDyMx(xh);
    }
}
