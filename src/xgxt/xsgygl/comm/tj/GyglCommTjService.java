package xgxt.xsgygl.comm.tj;

import java.util.HashMap;
import java.util.List;
/**
 * author lyl
 * Title:数据统计的Service 接口
 */
public interface GyglCommTjService {
	public List<HashMap<String,String>> service_FpDeTailView(String userDep,String nj,String xydm,String zydm,String bjdm);
}
