package com.zfsoft.xgxt.zhdj;

import xgxt.DAO.DAO;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

/**
 * @�๦������:
 * @���ߣ� lgx [����:1553]
 * @ʱ�䣺 2018-06-14 16:58
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class ZhdjComm {

    /**
     * @����:�ж��Ƿ�Ϊ��֧�����
     * @���ߣ�lgx [���ţ�1553]
     * @���ڣ�2018/6/14 16:58
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @param: [user]
     * @return: boolean
     */
    public static   boolean isDzbsj(User user) {
        DAO dao = DAO.getInstance();
        String countSql = "select count(0) c from (select * from XG_ZHDJ_DZBGL_DZB where dzbid || hjsj in " + " (select z.dzbid || max(hjsj) from XG_ZHDJ_DZBGL_DZB z group by z.dzbid)) b " + " where b.dzbsj=? ";
        String count = dao.getOneRs(countSql, new String[]{user.getUserName()}, "c");
        return !StringUtils.isNull(user.getUserName()) && Integer.parseInt(count) != 0;
    }
}
