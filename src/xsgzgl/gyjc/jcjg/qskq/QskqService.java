package xsgzgl.gyjc.jcjg.qskq;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import xgxt.form.User;

import java.util.HashMap;
import java.util.List;

/**
 * @�๦������:
 * @���ߣ� lgx [����:1553]
 * @ʱ�䣺 2018-08-09 17:07
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class QskqService extends SuperServiceImpl<QskqForm,QskqDao> {
    /**
     * @����:��ȡ�������ҿ��ڽ����Ϣ
     * @���ߣ�lgx [���ţ�1553]
     * @���ڣ�2018/8/11 11:39
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @param: [model]
     * @return: java.util.HashMap<java.lang.String,java.lang.String>
     */
    public HashMap<String,String> getQskqjgInfo(QskqForm model) {
        return  dao.getQskqjgInfo(model);
    }
    /**
     * @����:��ȡ�������ҿ������
     * @���ߣ�lgx [���ţ�1553]
     * @���ڣ�2018/8/11 11:39
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @param: []
     * @return: java.util.List<java.util.HashMap<java.lang.String,java.lang.String>>
     */
    public List<HashMap<String,String>> getAllKqlbList() {
        return  dao.getAllKqlbList();
    }
    /**
     * @����:�޸ı���
     * @���ߣ�lgx [���ţ�1553]
     * @���ڣ�2018/8/11 11:40
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @param: [model, user]
     * @return: boolean
     */
    public boolean update_save(QskqForm model) throws Exception {
        return  dao.update_save(model);
    }
}
