package xsgzgl.pjpy.general.inter.wdpj;

import xgxt.form.User;
import xsgzgl.pjpy.general.wdpj.PjpyWdpjModel;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ��������_�ҵ�����_��������_Interface��
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

public interface WdpjPjtjInterface {

	// �ж������ʸ�
	public String checkSqzg(PjpyWdpjModel model, User user) throws Exception;
	
	// �ж�����ʸ�
	public String checkShzg(PjpyWdpjModel model, User user) throws Exception;
	
	// �ж�ָ����Ŀ����˿���
	public boolean  checkShkz(String xmdm) throws Exception;
}
