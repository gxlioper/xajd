package xsgzgl.pjpy.general.inter.xmsz;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import xgxt.form.User;
import xsgzgl.pjpy.general.xmsz.xmjd.XmszXmjdModel;


/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ��������_��Ŀ����_��Ŀ���_Interface��
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

public interface XmszXmjdInterface {
	
	// ��ʼ����Ŀ�������
	public void defaultXmjdSetting(XmszXmjdModel model, User user,
			HttpServletResponse response) throws Exception;

	// ������Ŀ���
	public Boolean saveXmjd(XmszXmjdModel model, User user);

	// ɾ����Ŀ���
	public Boolean deleteXmjd(XmszXmjdModel model, User user);
}
