package xsgzgl.pjpy.general.inter.pjsz;

import xgxt.form.User;
import xsgzgl.pjpy.general.pjsz.zcxm.PjszZcxmModel;

public interface PjszZcxmInterface {
	
	// 保存综测项目
	public Boolean saveZcxm(PjszZcxmModel model, String lx, User user);

	// 删除综测项目
	public Boolean deleteZcxm(PjszZcxmModel model, User user);

	// 修改综测项目
	public Boolean updateZcxm(PjszZcxmModel model, User user);

	// 保存综测比例
	public Boolean saveZcbl(PjszZcxmModel model, User user);
}
