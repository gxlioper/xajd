/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-7-2 ����06:55:35 
 */  
package com.zfsoft.xgxt.szdw.cssz;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ˼���������ģ��
 * @�๦������:�������� service
 * @���ߣ� zhangjw
 * @ʱ�䣺 2013-7-2 ����06:53:42 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class SzdwCsszService extends SuperServiceImpl<SzdwCsszForm,SzdwCsszDAO>{

	private SzdwCsszDAO dao = new SzdwCsszDAO();
	
	public SzdwCsszService(){
		super.setDao(dao);
	}
	/**
	 * @����:���ݼ���ȡ����������Ϣ
	 * @���ߣ�zhangjw
	 * @���ڣ�2013-7-3 ����04:33:44
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param key
	 * @return
	 * @throws Exception
	 * SzdwCsszForm ��������
	 */
	public SzdwCsszForm getShlcsqkzb(String key) throws Exception{
		return dao.getModel(key);
	}
	/**
	 * @����:��֤��������
	 * @���ߣ�zhangjw
	 * @���ڣ�2013-7-4 ����02:29:24
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param key
	 * @return
	 * @throws Exception
	 * String ��������
	 */
	public String yzCssz(String key) throws Exception{
		SzdwCsszForm cssz = getShlcsqkzb(key);
		String message = MessageUtil.getText("szdw_cssz_kg", cssz.getMs());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		if(!cssz.getKg().equals("1")){
		}else if(!(Integer.parseInt(sdf.format(new Date()))>=Integer.parseInt(cssz.getKssj()==null?"0":cssz.getKssj()) && Integer.parseInt(sdf.format(new Date()))<=Integer.parseInt(cssz.getJssj()==null?"99999999":cssz.getJssj()))){
		}else if(cssz.getSplc()==null || "".equals(cssz.getSplc()) ){
		}else{
			message = "true";
		}
		return message;
	}
}
