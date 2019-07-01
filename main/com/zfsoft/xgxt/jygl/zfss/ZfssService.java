/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-5-27 ����02:05:22 
 */
package com.zfsoft.xgxt.jygl.zfss;

import java.util.HashMap;
import java.util.List;

import xgxt.utils.Pages;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/**
 * @ϵͳ����: ��������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� huj
 * @ʱ�䣺 2013-5-27 ����02:05:22
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class ZfssService extends SuperServiceImpl<ZfssForm, ZfssDao> {

	private ZfssDao dao = new ZfssDao();

	public ZfssService() {
		super.setDao(dao);
	}
	
	public List<HashMap<String, String>> getZfssCountList(ZfssForm t)
		throws Exception {
		return dao.getZfssCountList(t);
	}
	public List<HashMap<String, String>> getAllZfssCountList(ZfssForm t)
		throws Exception {
		Pages pages = (Pages) t.getClass().getMethod("getPages").invoke(t);
		pages.setPageSize(Integer.MAX_VALUE);
		
		t.getClass().getMethod("setPages",Pages.class).invoke(t, pages);
		
		return dao.getZfssCountList(t);
	}
	
	public ZfssForm getModel(ZfssForm t) throws Exception {
		return dao.getModel(t);
	}

}
