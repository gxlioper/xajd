/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-6-13 ����04:52:06 
 */  
package com.zfsoft.xgxt.comm.shlc.test;

import static org.junit.Assert.*;


import org.junit.Test;

import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.shlc.model.ShxxModel;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: ������̲��� 
 * @���ߣ� Penghui.Qu [���ţ�445]
 * @ʱ�䣺 2013-6-13 ����04:52:06 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
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
		model.setShyj("�˻�");
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
