package xsgzgl.pjpy.general.inter;

import xgxt.form.User;
import xsgzgl.pjpy.general.xmsz.PjpyXmszModel;
import xsgzgl.pjpy.general.xmsz.pjtj.XmszPjtjModel;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ��������_��Ŀ����_Interface��
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

public interface PjpyXmszInterface {

	// �����Ŀ����ʸ���Ϣ
	public String getXmshzg(PjpyXmszModel model, User user) throws Exception;
	
}
