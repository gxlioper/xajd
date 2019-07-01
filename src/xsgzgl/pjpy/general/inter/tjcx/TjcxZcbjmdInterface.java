package xsgzgl.pjpy.general.inter.tjcx;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import xgxt.form.User;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.general.tjcx.zcbjmd.TjcxZcbjmdModel;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ��������_ͳ�Ʋ�ѯ_�۲�༶����_Interface��
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

public interface TjcxZcbjmdInterface {

	// �����۲�༶����HTMl���еȼ����ԡ�
	public void createZcbjmdDjksHTML(PjpyGeneralForm myForm,
			TjcxZcbjmdModel model, User user, HttpServletResponse response)
			throws IOException;

	// �����۲�༶����HTMl���޵ȼ����ԡ�
	public void createZcbjmdNoDjksHTML(PjpyGeneralForm myForm,
			TjcxZcbjmdModel model, User user, HttpServletResponse response)
			throws IOException;
	
	// �����۲�༶����
	public void expZcbjmd(TjcxZcbjmdModel model, OutputStream os)
			throws Exception;
}
