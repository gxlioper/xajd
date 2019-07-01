package com.zfsoft.xgxt.zhdj.xszbhd.zbhdfb;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.UniqID;
import xgxt.form.User;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @�๦������: ������ҵ���ǻ۵�У-ѧ��֧�������
 * @���ߣ� lgx [����:1553]
 * @ʱ�䣺 2018-5-30 ����09:11:30
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class ZbhdfbService extends SuperServiceImpl<ZbhdfbForm,ZbhdfbDAO>{

    public HashMap<String,String> gethdDetail(ZbhdfbForm model) {

        return  dao.gethdDetail( model);
    }

    public List<HashMap<String, String>> gethdjdDetail(ZbhdfbForm model) {

        return dao.gethdjdDetail( model);
    }
    /**
     * @��������: ��ȡδѡ��ĵ�֧��
     * @���ߣ� lgx [����:1553]
     * @ʱ�䣺 2018-6-5 ����07:51:36
     * @�汾�� V1.0
     * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
     */
    public List<HashMap<String,String>> getWxzDzbList(ZbhdfbForm model, User user) throws Exception {
        return dao.getWxzDzbList( model,  user);
    }
    /**
     * @��������: ��ȡ��ѡ��ĵ�֧��
     * @���ߣ� lgx [����:1553]
     * @ʱ�䣺 2018-6-5 ����07:51:36
     * @�汾�� V1.0
     * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
     */
    public List<HashMap<String,String>> getYxzDzbList(ZbhdfbForm model, User user) throws Exception {
        return dao.getYxzDzbList( model,  user);
    }
    /**
     * @����:
     * @���ߣ�lgx [���ţ�1553]
     * @���ڣ�2018/6/6 14:21
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @param: qtdzb�ж��Ƿ�ѡ��ȫ����֧��
     * @return:
     */
    public boolean save(ZbhdfbForm model, boolean qtdzb) throws Exception {
        if("add".equals(model.getType())){
            String guid = UniqID.getInstance().getUniqIDHash();
            model.setHdid(guid);
        }
        boolean rs = dao.save(model);
        if(rs && qtdzb){
            rs = dao.saveQtdzb(model.getHdid());
        }
        return  rs;
    }

    public int checkEdit(ZbhdfbForm model) {
        int rs = 0;
        SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //����ʱ��
        try {
            Date now = new Date();
            Date kssj = sf.parse(model.getKssj());
            Date jzsj = sf.parse(model.getJzsj());
            //�ж��Ƿ��ڻʱ����
            if(now.getTime() > kssj.getTime() &&
                    now.getTime() < jzsj.getTime()){
                return 2;
            }else{
                String count = dao.getYtj(model);
                if(Integer.parseInt(count) > 0){
                    return 1;
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int checkEditforDel(String[] ids) {
        SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //����ʱ��
        try {
            Date now = new Date();
            for (String hdid : ids) {
                ZbhdfbForm model = dao.getModel(hdid);
                Date kssj = sf.parse(model.getKssj());
                Date jzsj = sf.parse(model.getJzsj());
                //�ж��Ƿ��ڻʱ����
                if(now.getTime() > kssj.getTime() &&
                        now.getTime() < jzsj.getTime()){
                    return 2;
                }else{
                    String count = dao.getYtj(model);
                    if(Integer.parseInt(count) > 0){
                        return 1;
                    }
                }
            }

        }  catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    /**
     * @����:ɾ���μӻ�ĵ�֧��
     * @���ߣ�lgx [���ţ�1553]
     * @���ڣ�2018/6/15 8:55
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @param: [ids]
     * @return: boolean
     */
    public boolean delCjDzb(String[] ids) throws Exception {
        return dao.delCjDzb(ids);
    }

    public List<HashMap<String,String>> getAllDzbList(ZbhdfbForm model, User user) throws Exception {
       return dao.getAllDzbList( model,  user);
    }

    public boolean saveDzb(ZbhdfbForm model, String[] ids) throws SQLException {
        return dao.saveDzb(model,ids);
    }

    public int delDzb(ZbhdfbForm model, String[] ids) throws Exception {
        return dao.delDzb(model,ids);
    }
}
