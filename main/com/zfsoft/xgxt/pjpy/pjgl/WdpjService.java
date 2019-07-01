/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-5-24 ����02:40:48 
 */  
package com.zfsoft.xgxt.pjpy.pjgl;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.DateTranCnDate;
import com.zfsoft.xgxt.base.util.FreeMarkerUtil;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �������Ź���ģ��
 * @�๦������: ��������֮�ҵ����� 
 * @���ߣ� zhangjw 
 * @ʱ�䣺 2013-5-24 ����02:24:52 
 * @�汾�� V5.8.16
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class WdpjService   extends SuperServiceImpl<WdpjForm, WdpjDAO>{

	private WdpjDAO dao = new WdpjDAO();
	/**
	 * @����:����֤���ӡ
	 * @���ߣ�zhangjw
	 * @���ڣ�2013-5-24 ����04:51:36
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param pk
	 * @return
	 * @throws Exception
	 * File �������� 
	 * @throws
	 */
	public File getRyzsWord(String xh)throws Exception {

		Map<String,Object> data = new HashMap<String,Object>();
		StringBuffer fileName = new StringBuffer();
		if (!StringUtils.isNull(xh)){
			if(xh == null || "".equals(xh)){
				return null;
			}
			HashMap<String,String> map = dao.getRyzsdyXX(xh);
			//��������ļ�����
			fileName.append(map.get("xm")).append("(").append(xh).append(")").append(map.get("xmmc"));
			if(map!=null){
				data.put("xm", map.get("xm"));
				data.put("xmmc", map.get("xmmc"));
				data.put("nd",map.get("xn"));
				String rq = DateTranCnDate.dateToCnDateSubMonth(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
				data.put("sj",rq);
			}else{
				return null;
			}
		}
		
		File wordFile = FreeMarkerUtil.getWordFile(data,"classpath://templates//pjpy","yrzsdy.xml",fileName.toString());
		return wordFile;
	}
}
