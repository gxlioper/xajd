package xsgzgl.szdw.general.inter.tjcx;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import xgxt.form.User;
import xsgzgl.szdw.general.SzdwGeneralForm;
import xsgzgl.szdw.general.tjcx.bmqk.BmqkModel;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ˼������_ͳ�Ʋ�ѯ_�������_Interface��
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author ΰ�����
 * @version 1.0
 */

public interface TjcxBmqkInterface {

	// �����������ͳ��DIV
	public void createBmqkStatDiv(SzdwGeneralForm myForm, BmqkModel model,
			User user, HttpServletResponse response) throws Exception;

	public void exportData(ServletOutputStream outputStream,
			SzdwGeneralForm myForm, User user) throws Exception;
}
