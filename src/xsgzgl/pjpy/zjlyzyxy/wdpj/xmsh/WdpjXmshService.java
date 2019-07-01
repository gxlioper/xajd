package xsgzgl.pjpy.zjlyzyxy.wdpj.xmsh;


import java.util.HashMap;

import xgxt.form.User;
import xsgzgl.pjpy.general.wdpj.lssb.WdpjLssbService;
import xsgzgl.pjpy.general.wdpj.xmsh.WdpjXmshModel;
import xsgzgl.pjpy.general.zhcp.PjpyZhcpService;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ��������_�ҵ�����_��Ŀ���_ͨ��_Service��
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author ΰ�����
 * @version 1.0
 */

public class WdpjXmshService extends xsgzgl.pjpy.general.wdpj.xmsh.WdpjXmshService {
	
	WdpjXmshDAO dao = new WdpjXmshDAO();
	// ===========================������Ŀ�����ϸ begin===============================
	public HashMap<String, Object> defaultWdpjXmsh(WdpjXmshModel model, User user) throws Exception {

		HashMap<String,Object>rs=new HashMap<String,Object>();
		
		WdpjLssbService xmsbService=new WdpjLssbService();
		
		PjpyZhcpService zhcpService=new PjpyZhcpService();
		
		String xmdm=model.getXmdm();
		
		String xh=model.getXh()[0];
		
		// ��������ѧ��������Ϣ��ѧ��������Ϣ��
		rs.putAll(dao.getXmsqInfo(model, user));
		// ������Ŀ�����Ϣ
		rs.put("xmshInfo",dao.getXmshInfo(model, user));
		// ѧ���γ̳ɼ�
		rs.put("kccjInfo",xmsbService.getXscjList(xh));
		// ѧ���۲�ɼ�
		rs.put("zccjInfo",zhcpService.getBczcInfo(xh));
		return rs;
	}
	// ===========================������Ŀ�����ϸ end===============================
	
}
