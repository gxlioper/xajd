package xsgzgl.gygl.xjdfygl.fsbj;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import org.apache.commons.lang.StringUtils;

import java.sql.SQLException;
import java.util.List;

/**
 * @描述：TODO
 * @作者：WANCHEN
 * @日期：
 */
public class FsbjServie extends SuperServiceImpl<FsbjForm,FsbjDao> {

    public List<String> getWfsQsgs() throws SQLException {
        return dao.getWfsQsgs();
    }

    public boolean save(FsbjForm t,String pks) throws Exception {
        String[] pkArray;
        if(StringUtils.isNotEmpty(pks)){
            pkArray = pks.split(",");
        } else {
            List<String> ids = getWfsQsgs();
            pkArray = ids.toArray(new String[ids.size()]);
        }

        return dao.save(t,pkArray);
    }
}
