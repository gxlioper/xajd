package xsgzgl.pjpy.general.inter.xmsz;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;

import xgxt.form.User;
import xsgzgl.pjpy.general.xmsz.pjtj.XmszPjtjModel;
import xsgzgl.pjpy.general.xmsz.xmjd.XmszXmjdModel;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ��������_��Ŀ����_��������_Interface��
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author ΰ�����
 * @version 1.0
 */

public interface XmszPjtjInterface {

	// ��ʼ��������������
	public void defaultPjtjSetting(XmszPjtjModel model, User user,
			HttpServletResponse response) throws IOException;
	
	// ��ÿɷ�������Ϣ
	public String getPjtjMessage(XmszPjtjModel model, User user);

	// ��ʼ������������Ϣ
	public HashMap<String, String> getPjtjInfo(XmszPjtjModel model,
			User user);
	
	// ������������
	public Boolean savePjtj(XmszPjtjModel model, User user);
	
	// ɾ����������
	public Boolean deletePjtj(XmszPjtjModel model, User user);
}
