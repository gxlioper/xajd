package com.zfsoft.xgxt.qgzx.wycjqgzx;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.qgzx.xsgw.WdgwsqDAO;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @�๦������:
 * @���ߣ� lgx [����:1553]
 * @ʱ�䣺 2018-07-06 10:54
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class  WycjqgzxService extends SuperServiceImpl<WycjqgzxForm,WycjqgzxDao> {

    /**
     * @����:������ʱ���Ƿ�����޸ģ���ѧ��������л����ͨ���������������޸�
     * @���ߣ�lgx [���ţ�1553]
     * @���ڣ�2018/7/6 14:20
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @param: [xh]
     * @return: boolean
     */
    public boolean checkSfxg(String xh) throws SQLException {
        int sum = dao.getShCount(xh) ;
        return sum==0;
    }

    public boolean saveKysj(String xh, String[] mxrq, List<String[]> mxxmList) throws Exception {
        WdgwsqDAO wDao = new WdgwsqDAO();
        List<String[]> params = new ArrayList<String[]>();
        for (int i = 0 ; i < mxrq.length ; i++){
            String[] param = new String[]{xh,mxrq[i], StringUtils.joinArr(mxxmList.get(i)),i+""};
            params.add(param);
        }
        wDao.delQgmx(xh);
        return wDao.saveQgmx(params);
    }

    public boolean saveDgxs(String xh) throws Exception {
        WycjqgzxForm model = new WycjqgzxForm();
        model.setXh(xh);
        model.setBmsj(GetTime.getTimeByFormat("yyyy-mm-dd hh24:mi:ss"));
        model.setDgzt("1");
        dao.runDelete(new String[]{xh});
        return dao.runInsert(model);
    }
}
