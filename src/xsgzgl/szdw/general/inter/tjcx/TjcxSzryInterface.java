package xsgzgl.szdw.general.inter.tjcx;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import xgxt.form.User;
import xsgzgl.szdw.general.SzdwGeneralForm;
import xsgzgl.szdw.general.tjcx.szry.SzryModel;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ˼������_ͳ�Ʋ�ѯ_˼����Ա_Interface��
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

public interface TjcxSzryInterface {

	// ��������ά��DIV
	public void createSzryStatDiv(SzdwGeneralForm myForm, SzryModel model,
			User user, HttpServletResponse response) throws Exception;
	//��������
	public void exportData(ServletOutputStream outputStream,
			SzdwGeneralForm myForm, User user) throws Exception;
}
