package xsgzgl.pjpy.general.inter.pjsz;

import xgxt.form.User;
import xsgzgl.pjpy.general.pjsz.zcxm.PjszZcxmModel;

public interface PjszZcxmInterface {
	
	// �����۲���Ŀ
	public Boolean saveZcxm(PjszZcxmModel model, String lx, User user);

	// ɾ���۲���Ŀ
	public Boolean deleteZcxm(PjszZcxmModel model, User user);

	// �޸��۲���Ŀ
	public Boolean updateZcxm(PjszZcxmModel model, User user);

	// �����۲����
	public Boolean saveZcbl(PjszZcxmModel model, User user);
}
