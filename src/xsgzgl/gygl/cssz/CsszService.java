package xsgzgl.gygl.cssz;

import java.util.HashMap;
import java.util.List;

import xsgzgl.gygl.comm.GyglNewService;

public class CsszService extends GyglNewService{
	private CsszDAO csszDao = new CsszDAO();
	
	public List<String[]> querySjsz(CsszModel model) throws Exception{
		return csszDao.querySjsz(model);
	}
	
	public boolean saveKqzt(CsszModel model){
		return csszDao.saveKqzt(model);
	}

	/** 
	 * @描述:TODO(获取辅导员操作床位时间范围)
	 * @作者：cmj [工号：913]
	 * @日期：2013-6-20 下午02:18:08
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws 
	 */
	public HashMap<String, String> getSjfw() {
		// TODO 自动生成方法存根
		return csszDao.getSjfw();
	}

	/**
	 * @throws Exception  
	 * @描述:TODO(保存辅导员操作床位时间范围)
	 * @作者：cmj [工号：913]
	 * @日期：2013-6-20 下午02:42:52
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param myForm
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean saveSjfw(CsszForm myForm) throws Exception {
		// TODO 自动生成方法存根
		
		return csszDao.saveSjfw(myForm);
	}
}
