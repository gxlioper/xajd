package com.zfsoft.xgxt.zhdj.xsdzbhdygl;

import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.UniqID;
import xgxt.action.Base;
import xgxt.form.User;

import java.util.HashMap;
import java.util.List;

public class DzbglService extends SuperServiceImpl<DzbglForm, DzbglDao> {
    public List<HashMap<String, String>> getJcdw() {
        return dao.getJcdw();
    }


    public List<HashMap<String, String>> getZgList(DzbglForm myForm, User user) throws Exception {
        return dao.getZgList(myForm, user);
    }


    public boolean saveDzb(DzbglForm model) throws Exception {
        //���ж������Ƿ��ظ�
        boolean rs = dao.checkRepeatDZB(model);//�����Ƿ��ظ�
        if (!rs) {
            throw new SystemException(MessageKey.SYS_DMWH_MC_EXISTS);
        }
        boolean rsv = dao.saveZwb(model);
        if (rsv) {
            return dao.runInsert(model);
        }
        return false;

    }

    public boolean saveZwb(DzbglForm model) throws Exception {
        //�жϸõ�֧�����Ƿ��г�Ա
        return dao.saveZwb(model);

    }

    public boolean hjDzb(DzbglForm model) throws Exception {
        String zt = "hj";
        String id= UniqID.getInstance().getUniqIDHash();
        model.setDzbhjid(id);
        dao.runInsert(model);
        return dao.updateZwb(model, zt);
    }

    public List<HashMap<String, String>> getDzb(DzbglForm model) throws Exception {
        return dao.getDzb(model);
    }

    public boolean updateSava(DzbglForm model) throws Exception {
        String zt = "update";
        boolean rsv = dao.updateZwb(model, zt);
        if (rsv) {
            return dao.runUpdate(model);
        }
        return false;
    }



    public List<HashMap<String, String>> getljDzbList(DzbglForm model) {
        return dao.getljDzbList(model);
    }

    public boolean delCy(DzbglForm model) throws Exception {
        //�жϸõ�֧�����Ƿ��г�Ա
        //���ж������Ƿ��ظ�
        boolean rs = dao.checkCyInDzb(model);//�����Ƿ��ظ�
        if (!rs) {
            throw new SystemException(MessageKey.GZJL_LBGL_NOTDEL);
        }
        boolean rsv = dao.delZw(model);
        if (rsv) {
            return dao.delCy(model);

        }
        return false;
    }

    public List<HashMap<String, String>> getdzbInfo(DzbglForm model) {
        return dao.getdzbInfo(model);
    }

    public List<HashMap<String, String>> getInfoNoHjId(DzbglForm model) {
        return dao.getInfoNoHjId(model);
    }

    public List<HashMap<String, String>> getDzbCy(DzbglForm model, User user) {
        return dao.getDzbCy(model, user);
    }

    public List<HashMap<String, String>> getJgdzbCy(DzbglForm model, User user) {
        return dao.getJgdzbCy(model, user);
    }

    public List<HashMap<String, String>> getDzbZwList(DzbglForm model, User user) {
        return dao.getDzbZwList(model, user);
    }

    public HashMap<String, String> cjcyCk(List<HashMap<String, String>> cyInfoList) {
        HashMap<String, String> cyMap = new HashMap<String, String>();
        cyMap.put("cyHtml", creatHtml(cyInfoList));
        return cyMap;
    }

    private String creatHtml(List<HashMap<String, String>> cyInfoList) {
        StringBuilder html = new StringBuilder();
        if (cyInfoList != null && cyInfoList.size() > 0) {
            if (cyInfoList.get(0).get("dzblx").equals("ѧ����֧��")) {
                for (int i = 0; i < cyInfoList.size(); i++) {
                    HashMap<String, String> rs = cyInfoList.get(i);
                    html.append("<tr><td>" + (i + 1) + "</td>");
                    html.append("<td>" + rs.get("xh") + "</td>");
                    html.append("<td>" + rs.get("xm") + "</td>");
                    html.append("<td>" + rs.get("xb") + "</td>");
                    html.append("<td>" + rs.get("zymc") + "</td>");
                    html.append("<td>" + (Base.isNull(rs.get("lxdh")) ? "" : rs.get("lxdh")) + "</td>");
                    html.append("<td>" + (Base.isNull(rs.get("zzmmmc")) ? "" : rs.get("zzmmmc")) + "</td>");
                    html.append("<td>" + (Base.isNull(rs.get("djzt")) ? "" : rs.get("djzt")) + "</td>");
                    html.append("<td>" + rs.get("sl") + "</td>");
                    html.append("<td>" + rs.get("ld") + "</td><tr>");
                }
            } else if (cyInfoList.get(0).get("dzblx").equals("�̹���֧��")) {
                for (int i = 0; i < cyInfoList.size(); i++) {
                    HashMap<String, String> rs = cyInfoList.get(i);
                    html.append("<tr><td>" + (i + 1) + "</td>");
                    html.append("<td>" + rs.get("xh") + "</td>");
                    html.append("<td>" + rs.get("xm") + "</td>");
                    html.append("<td>" + rs.get("bmmc") + "</td>");
                    html.append("<td>" + (Base.isNull(rs.get("lxdh")) ? "" : rs.get("lxdh")) + "</td>");
                }
            }

        }
        return html.toString();
    }

    public List<HashMap<String, String>> getDCList(DzbglForm model, User user) throws Exception {
        return dao.getDCList(model, user);
    }

    /**
     * @return List<HashMap<String,String>> ��������
     * @throws
     * @����:TODO(������һ�仰�����������������)
     * @���ߣ��Ų�·[���ţ�982]
     * @���ڣ�2018-12-27 ����11:59:32
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     */
    public List<HashMap<String, String>> getJgdzbList() {
        // TODO �Զ����ɷ������
        return dao.getJgdzbList();
    }

    public List<HashMap<String, String>> getXsdzbList() {
        // TODO �Զ����ɷ������
        return dao.getXsdzbList();
    }


    /**
     * @param model
     * @param user
     * @return List<HashMap<String,String>> ��������
     * @throws Exception
     * @throws
     * @����:TODO(������һ�仰�����������������)
     * @���ߣ��Ų�·[���ţ�982]
     * @���ڣ�2018-12-27 ����08:28:30
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     */
    public List<HashMap<String, String>> getXxList(DzbglForm model, User user) throws Exception {
        // TODO �Զ����ɷ������
        return dao.getXxList(model, user);
    }


    /**
     * @return List<HashMap<String,String>> ��������
     * @throws
     * @����:TODO(������һ�仰�����������������)
     * @���ߣ��Ų�·[���ţ�982]
     * @���ڣ�2018-12-29 ����08:55:58
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     */
    public List<HashMap<String, String>> getJgdzbZwList(DzbglForm model) {
        // TODO �Զ����ɷ������
        return dao.getJgdzbZwList(model);
    }


    /**
     * @return List<HashMap<String,String>> ��������
     * @throws
     * @����:TODO(������һ�仰�����������������)
     * @���ߣ��Ų�·[���ţ�982]
     * @���ڣ�2018-12-29 ����08:58:30
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     */
    public List<HashMap<String, String>> getXsdzbZwList(DzbglForm model) {
        // TODO �Զ����ɷ������
        return dao.getXsdzbZwList(model);
    }

    public String getJgCount() {
        return dao.getJgCount();
    }

    public String getXsCount() {
        return dao.getXsCount();
    }
}
