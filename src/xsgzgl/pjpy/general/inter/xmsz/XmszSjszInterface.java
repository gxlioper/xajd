package xsgzgl.pjpy.general.inter.xmsz;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import xgxt.form.User;
import xsgzgl.pjpy.general.xmsz.sjsz.XmszSjszModel;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ��������_��Ŀ����_ʱ������_Interface��
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

public interface XmszSjszInterface {
	
	// ��ʼ����Ŀʱ������
	public void defaultSjszSetting(XmszSjszModel model, User user,
			HttpServletResponse response) throws IOException;

	// ������Ŀʱ������
	public Boolean saveSjsz(XmszSjszModel model, User user);
	
	// ɾ����Ŀʱ������
	public Boolean deleteSjsz(XmszSjszModel model, User user);
}
