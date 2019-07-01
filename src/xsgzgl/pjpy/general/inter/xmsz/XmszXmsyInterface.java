package xsgzgl.pjpy.general.inter.xmsz;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import xgxt.form.User;
import xsgzgl.pjpy.general.xmsz.xmsy.XmszXmsyModel;


/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ��������_��Ŀ����_��Ŀ˳��_Interface��
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

public interface XmszXmsyInterface {
	
	// �����Ŀ˳���б�
	public List<HashMap<String, String>> getXmsyList(XmszXmsyModel model) ;

	public void showXmsyDiv(XmszXmsyModel model, HttpServletResponse response)throws IOException;
	
	public boolean saveXmsy(XmszXmsyModel model,User user)throws Exception;
}
