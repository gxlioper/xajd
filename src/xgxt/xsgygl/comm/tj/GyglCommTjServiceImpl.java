package xgxt.xsgygl.comm.tj;

import java.util.HashMap;
import java.util.List;
/**
 * author lyl
 * Title ����ͳ�Ʒ�����Serviceʵ��
 */
public class GyglCommTjServiceImpl implements GyglCommTjService{
	GyglCommTjDaoImpl gcDAO = null;
	/**
	 * author lyl
	 * Title:ѧ����ס�����ͳ�Ʒ���
	 */
	@SuppressWarnings("unchecked")
	public List<HashMap<String,String>> service_FpDeTailView(String userDep,String nj,String xydm,String zydm,String bjdm){
		gcDAO = new GyglCommTjDaoImpl();
		return gcDAO.dao_FpDeTailView(userDep,nj,xydm,zydm,bjdm);
	}
}
