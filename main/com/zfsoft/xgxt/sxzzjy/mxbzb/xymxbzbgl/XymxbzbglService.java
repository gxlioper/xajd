package com.zfsoft.xgxt.sxzzjy.mxbzb.xymxbzbgl;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import xgxt.form.User;
import xgxt.utils.GetTime;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

/**
 * @�๦������:
 * @���ߣ� lgx [����:1553]
 * @ʱ�䣺 2018-06-27 11:41
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class XymxbzbglService extends SuperServiceImpl<XymxbzbglForm,XymxbzbglDao> {
    public List<HashMap<String,String>> getAlllb() {
        return dao.getAlllb();
    }

    public boolean xymxbzbglInsert(XymxbzbglForm model,User user) throws Exception {
        model.setFbsj(GetTime.getTimeByFormat("yyyy-mm-dd hh24:mi:ss"));//����ʱ��
        model.setFbr(user.getRealName());//������
        model.setFbbmdm(user.getUserDep());
        return dao.xymxbzbglInsert(model);
    }

    public boolean xymxbzbglUpdate(XymxbzbglForm model) throws Exception {
        model.setXgsj(GetTime.getTimeByFormat("yyyy-mm-dd hh24:mi:ss"));//�޸�ʱ��
        return dao.xymxbzbglUpdate(model);
    }

    /**
     * @����:��ȡ������Ϣ
     * @���ߣ�lgx [���ţ�1553]
     * @���ڣ�2018/6/28 15:05
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @param: [model]
     * @return: java.util.HashMap<java.lang.String,java.lang.String>
     */
    public HashMap<String,String> getxymxbzbglInfo(XymxbzbglForm model) throws SQLException {
        return dao.getxymxbzbglInfo(model);
    }

    /**
     * @����:����
     * @���ߣ�lgx [���ţ�1553]
     * @���ڣ�2018/6/28 15:06
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @param: [ids]
     * @return: java.lang.String
     */
    public String xymxbzbglFb(String[] ids) throws Exception {
        String xgsj = GetTime.getTimeByFormat("yyyy-mm-dd hh24:mi:ss");//�޸�ʱ��
        boolean rs = dao.xymxbzbglFb(ids,xgsj);
        return rs ? "�����ɹ�":"����ʧ��";
    }
    /**
     * @����:ȡ������
     * @���ߣ�lgx [���ţ�1553]
     * @���ڣ�2018/6/28 15:06
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @param: [ids]
     * @return: java.lang.String
     */
    public String xymxbzbglQxfb(String[] ids) throws Exception {
        String xgsj = GetTime.getTimeByFormat("yyyy-mm-dd hh24:mi:ss");//�޸�ʱ��
        boolean rs = dao.xymxbzbglQxfb(ids,xgsj);
        return rs ? "ȡ�������ɹ�":"ȡ������ʧ��";
    }


    /**
     * @����:�ö�
     * @���ߣ�lgx [���ţ�1553]
     * @���ڣ�2018/6/28 15:06
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @param: [ids]
     * @return: java.lang.String
     */
    public String xymxbzbglZd(String[] ids) throws Exception {
        String xgsj = GetTime.getTimeByFormat("yyyy-mm-dd hh24:mi:ss");//�޸�ʱ��
        boolean rs = dao.xymxbzbglZd(ids,xgsj);
        return rs ? "�ö��ɹ�":"�ö�ʧ��";
    }

    /**
     * @����:ȡ���ö�
     * @���ߣ�lgx [���ţ�1553]
     * @���ڣ�2018/6/28 15:06
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @param: [ids]
     * @return: java.lang.String
     */
    public String xymxbzbglQxzd(String[] ids) throws Exception {
        String xgsj = GetTime.getTimeByFormat("yyyy-mm-dd hh24:mi:ss");//�޸�ʱ��
        boolean rs = dao.xymxbzbglQxzd(ids,xgsj);
        return rs ? "ȡ���ö��ɹ�":"ȡ���ö�ʧ��";
    }

    public List<HashMap<String,String>> getAlbForView() {
        return dao.getAlbForView();
    }

    public List<HashMap<String,String>> getNewsList(String typedm,String size) {
        return dao.getNewsList(typedm,size);
    }

    public List<HashMap<String,String>> getNewsmore(XymxbzbglForm model) throws Exception{
        return dao.getNewsmore(model);
    }

    public boolean addYdr(XymxbzbglForm model,User user) throws Exception {

        return dao.addYdr(model,user);
    }

    public List<HashMap<String,String>> getYydmd(XymxbzbglForm model) throws Exception {
        return dao.getYydmd( model);
    }
}
