package xsgzgl.gygl.zcgl.zcxx;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

import java.util.HashMap;
import java.util.List;

/**
 * @������TODO
 * @���ߣ�WANCHEN
 * @���ڣ�
 */
public class ZcxxService extends SuperServiceImpl<ZcxxFrom,ZcxxDao> {

    public List<HashMap<String,String>> getLxdmList(){
        return dao.getLxdmList();
    }
    public List<HashMap<String,String>> getZcxxByLxdm(String lxdm){
        return dao.getZcxxByLxdm(lxdm);
    }
}
