/**
 * @部门:学工产品事业部
 * @日期：2013-6-13 下午04:52:06 
 */  
package com.zfsoft.xgxt.comm.shlc.test;

import static org.junit.Assert.*;


import org.junit.Test;

import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.shlc.model.ShxxModel;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: 审核流程测试 
 * @作者： Penghui.Qu [工号：445]
 * @时间： 2013-6-13 下午04:52:06 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ShlcTest{

	private ShlcInterface shlc = new CommShlcImpl();
	
	@Test
	public void testRunSubmit(){
		
		String guid = UniqID.getInstance().getUniqIDHash();
		boolean result;
		try {
			result = shlc.runSubmit(guid, "A89038340213053FE040007F01000702","10000","a.do","b.do");
			assertEquals(result, true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testRunAuditing(){
		
		ShxxModel model = new ShxxModel();
		model.setShlc("A89038340213053FE040007F01000702");
		model.setShr("qph");
		model.setShyj("退回");
		model.setShzt("3");
		model.setGwid("A438379C7DD4A1E8E040007F01001973");//
		//model.setThgw("A438379C7DD3A1E8E040007F01001972");
		model.setYwid("d5c65e8e94a0b644e14182b87c126aa7");
		
		try {
			String zhzt = shlc.runAuditing(model);
			System.out.println(zhzt);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testRunCancel(){
		try {
			boolean result = shlc.runCancel("qph", "d5c65e8e94a0b644e14182b87c126aa7", "A89038340213053FE040007F01000702", "A438379C7DD4A1E8E040007F01001973");
			assertEquals(result, true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
}
