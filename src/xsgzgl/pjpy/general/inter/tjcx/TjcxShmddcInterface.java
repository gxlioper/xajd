package xsgzgl.pjpy.general.inter.tjcx;

import java.io.OutputStream;
import xsgzgl.pjpy.general.tjcx.shmddc.TjcxShmddcModel;

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

public interface TjcxShmddcInterface {
	
	// �����������
	public void expShmddc(TjcxShmddcModel model, OutputStream os)
			throws Exception;
}
