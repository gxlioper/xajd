package xsgzgl.pjpy.general.inter;

import java.util.HashMap;
import java.util.List;

import common.Globals;

import xgxt.action.Base;
import xgxt.pjpy.comm.pjpy.pjlc.jgcx.PjpyJgcxForm;
import xsgzgl.pjpy.general.djbg.PjpyDjbgModel;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ��������_�ǼǱ��_Interface��
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

public interface PjpyDjbgInterface {

	// ��õǼǱ������
	public String getDjbg(PjpyDjbgModel model);

	// ��õǼǱ������
	public HashMap<String, Object> getDjbgInfo(PjpyDjbgModel model);

	// ��õǼǱ������
	public List<HashMap<String, String>> getDjbgInfoList(PjpyDjbgModel model);

	// ��ȡ��ӡ��·��
	public String getPrintJspForward(PjpyDjbgModel model) throws Exception;
}
