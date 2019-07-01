package xsgzgl.pjpy.general.inter;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import xgxt.form.User;
import xsgzgl.pjpy.general.index.PjpyIndexModel;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ��������_������Ŀ_Interface��
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

public interface PjpyIndexInterface {

	// ��ʼ���Ѷ�����������
	public void defaultCustomPjlc(PjpyIndexModel model, User user,
			HttpServletResponse response) throws IOException;

	// ��ʼ����������
	public void defaultFreePjlc(PjpyIndexModel model, User user,
			HttpServletResponse response) throws IOException;

	// ������������
	public Boolean savePjlc(PjpyIndexModel model, User user);
	
	// ���濪ʼ������
	public Boolean saveStart(PjpyIndexModel model, User user);
	
	// �ύ��������
	public Boolean submitPjlc(String lcdj, User user) ;
	
	// ===========================������������=====================================
	// ��ȡ��������ͳ����Ϣ
	public List<HashMap<String, String>> getBcpjtjInfo(User user)throws Exception;
	
	// ���ݽ�����ʷ��
	public void  theEnd(User user);

}
