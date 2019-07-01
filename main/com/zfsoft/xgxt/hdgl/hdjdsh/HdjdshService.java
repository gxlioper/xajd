/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2018-2-8 ����03:46:25 
 */  
package com.zfsoft.xgxt.hdgl.hdjdsh;

import com.zfsoft.ms.mail.util.CollectionUtils;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.hdgl.hdxx.HdxxDao;
import com.zfsoft.xgxt.hdgl.hdxx.HdxxForm;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * ��׶����service.
 *
 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
 * @date 2018-02-08 03:43
 */
public class HdjdshService extends SuperServiceImpl<HdxxForm, HdjdshDao> {

    private HdxxDao hdxxDao = new HdxxDao();
    /**
     *  ��ȡ��׶���˳�Ա�б����˻����.
     *
     * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
     * @date 2018-02-10 16:31
     * @param model
     * @return java.util.List<java.util.HashMap<java.lang.String,java.lang.String>>
     * @throw Exception
     */
    public List<HashMap<String,String>> getHdjdshCyList(HdxxForm model,User user) throws Exception {
        String bmlx = model.getBmlx();
        List<HashMap<String,String>> resultList;
        if(StringUtils.isNotNull(bmlx)){
            if("1".equals(bmlx)){
                resultList = dao.getHdjdshGrList(model,user);
            }else {
                resultList = dao.getHdjdshDwList(model,user);
            }
        }else {
            resultList = null;
        }
        return resultList;
    }

    /**
     *  ��ѯ����ɽ׶���Ϣ�б�.
     *
     * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
     * @date 2018-03-07 17:01
     * @param model
     * @param jdsx
     * @return java.util.List<java.util.HashMap<java.lang.String,java.lang.String>>
     * @throw
     */
    public List<HashMap<String,String>> getYwcHdjdshCyList(HdxxForm model,String jdsx) throws Exception {

        //�ڻ��Ա���ж��Ǹ��˻�����ӣ����л�ȡsqid
        String hdsqid = dao.getHdsqid(model);
        List<HashMap<String,String>> list = dao.getYwcHdjdshCyList(model,hdsqid,jdsx);
        return list;
    }

    /**
     *  ���ݻid��ȡ��ǰ��׶���Ϣ.
     *
     * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
     * @date 2018-03-08 17:05
     * @param hdid
     * @return java.util.HashMap<java.lang.String,java.lang.String>
     * @throw
     */
    public HashMap<String,String> getCurrentHdjdInfo(String hdid) throws Exception {

        return dao.getCurrentHdjdInfo(hdid);
    }

    /**
     *  ���ݻid���׶�id����ȡָ����׶���Ϣ..
     *
     * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
     * @date 2018-03-08 17:05
     * @param hdid
     * @return java.util.HashMap<java.lang.String,java.lang.String>
     * @throw
     */
    public HashMap<String,String> getHdjdInfoWithJdid(String hdid,String jdid) throws Exception {

        return dao.getHdjdInfoWithJdid(hdid,jdid);
    }

    /**
     *  ��ѯ��׶���Ϣ���������׶δ���������.
     *
     * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
     * @date 2018-03-15 17:46
     * @param model
     * @return java.util.List<java.util.HashMap<java.lang.String,java.lang.String>>
     * @throw
     */
    public List<HashMap<String,String>> getHdjdList(HdxxForm model,User user) throws Exception {
        return dao.getHdjdList(model,user);
    }

    public boolean sfwdf(String hdsqid,String jdid,String zgh){
        return dao.sfwdf(hdsqid, jdid, zgh);
    }
    /**
     * �жϻ�Ƿ�����Ҫ��ֵĻ
     * @param hdid
     * @return
     */
    public boolean isExist(String hdid){
        return dao.isExist(hdid);
    }
    public boolean saveDf(HdxxForm model,User user) throws Exception {
        String hdsqid = dao.getHdsqid(model);
        model.setHdsqid(hdsqid);
        model.setSqid(hdsqid);
        boolean flag = dao.saveDf(model,user);
        HashMap<String,String> map = getHdjdInfoWithJdid(model.getHdid(),model.getJdid());
        String prejdid = map.get("prejdid");
        String prejdlx = map.get("prejdlx");
        if(flag){
            int dfisFinish = dao.dfIsFinish(model);
            //3��ר��ȫ��������������ý�ʦ�׶����״̬ͨ��
            if(dfisFinish == 0){
                if("1".equals(model.getBmlx())){//����
                    //���³�Ա�׶�
                    flag = dao.updateCyjd(map.get("nextjdid"),hdsqid);
                    //��һ�׶���ѧ���׶�
                    if("1".equals(prejdlx)){
                        //����ѧ���׶���Ϣ����һ�׶Σ�
                        model.setShzt("1");
                        flag = dao.updateXsjdShzt(prejdid,model);
                        if(flag){
                            flag = dao.updateJd(model);
                        }
                    } else {
                        flag = dao.updateJd(model);
                    }
                } else {
                    //���³�Ա�׶�
                    flag = dao.updateZdCyjd(map.get("nextjdid"),hdsqid);
                    //��һ�׶���ѧ���׶�
                    if("1".equals(prejdlx)){
                        //����ѧ���׶���Ϣ����һ�׶Σ�
                        model.setShzt("1");
                        flag = dao.updateZdjdShzt(prejdid,model);
                        if(flag){
                            flag = dao.updateJd(model);
                        }
                    } else {
                        flag = dao.updateJd(model);
                    }
                }
            }
        }
        return flag;
    }
    /**
     *  �׶����ͨ��.
     *
     * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
     * @date 2018-03-16 17:25
     * @param model
     * @return boolean
     * @throw
     */
    public boolean jdshtg(HdxxForm model,User user) throws Exception {

        String hdid = model.getHdid();
        String jdid = model.getJdid();
        HashMap<String,String> hdjdInfo = getHdjdInfoWithJdid(hdid,jdid);
        String bmlx = model.getBmlx();
        String prejdid = hdjdInfo.get("prejdid");
        String prejdlx = hdjdInfo.get("prejdlx");
        String nextjdid = hdjdInfo.get("nextjdid");
        boolean result;
        model.setShzt("1");
        model.setZgh(user.getUserName());
        boolean sfdfhd = isExist(hdid);
        //��һ�׶���ѧ���׶�
        if("1".equals(prejdlx)){
            if("1".equals(bmlx)){//����
                //����ѧ���׶���Ϣ����һ�׶Σ�
                result = dao.updateXsjdShzt(prejdid,model);
            }else{//���
                String dwid = model.getDwid();
                //������ӽ׶���Ϣ����һ�׶Σ�
                result = dao.updateZdjdShzt(prejdid,model);
                //�������ӻ���һ���׶Σ�������ӽ����Ϣ��
                if(dao.isLastNode(hdid, jdid)){
                    result = dao.insertZdjgxx(model);
                }
            }
            //���ʦ�׶���Ϣ���������
            if(result){
                String hdsqid = dao.getHdsqid(model);
                model.setHdsqid(hdsqid);
                result = dao.insertJsjdInfo(jdid,model);
            }
        }else if("2".equals(prejdlx)) {//��һ �׶��ǽ�ʦ�׶�
            //���ʦ�׶���Ϣ���������
            String hdsqid = dao.getHdsqid(model);
            model.setHdsqid(hdsqid);
            result = dao.insertJsjdInfo(jdid,model);
            //�Ƿ��Ǵ�ֻ
            if(sfdfhd){
                String nextJdid = StringUtils.isNull(model.getNextjdid()) ? nextjdid : model.getNextjdid();
                if("1".equals(hdjdInfo.get("sftj"))){
                    List<String> jdids = dao.getZjjd(jdid,nextJdid,hdid);
                    if(!CollectionUtils.isEmpty(jdids)){
                        List<HdxxForm> hdxxForms = new ArrayList<HdxxForm>();
                        HdxxForm form;
                        for(String s : jdids){
                            form = new HdxxForm();
                            form.setHdid(hdid);
                            form.setJdid(s);
                            form.setShzt("1");
                            form.setHdsqid(model.getHdsqid());
                            form.setShyj(model.getShyj());
                            form.setZgh(model.getZgh());
                            hdxxForms.add(form);
                        }
                        for(HdxxForm form1 : hdxxForms){
                            dao.insertJsjdInfo(form1.getJdid(),form1);
                        }
                    } else {
                        //����һ�ִ��ר��
                        hdxxDao.zpzjbd(hdid,hdsqid,jdid);
                    }
                }
                if("1".equals(bmlx)){//����
                    result = dao.updateCyjd(nextJdid,hdsqid);
                } else {//���
                    result = dao.updateZdCyjd(nextJdid,hdsqid);
                }
            }
            //�������ӻ���һ���׶Σ�������ӽ����Ϣ��
            if(!"1".equals(bmlx) && dao.isLastNode(hdid, jdid)){
                result = dao.insertZdjgxx(model);
            }
        }else {
            throw new Exception("��˲������Ϸ�");
        }
        return result;
    }

    /**
     *  �׶�����˻�.��ͨ��
     *
     * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
     * @date 2018-03-16 17:25
     * @param model
     * @return boolean
     * @throw Exception
     */
    public boolean jdshth(HdxxForm model,User user) throws Exception {

        String hdid = model.getHdid();
        String jdid = model.getJdid();
        HashMap<String,String> hdjdInfo = getHdjdInfoWithJdid(hdid,jdid);
        String bmlx = model.getBmlx();
        String prejdid = hdjdInfo.get("prejdid");
        String prejdlx = hdjdInfo.get("prejdlx");

        boolean result;
        model.setZgh(user.getUserName());

        //��һ�׶���ѧ���׶�
//        if("1".equals(prejdlx)){
            if("1".equals(bmlx)){//����
                //����ѧ���׶���Ϣ����һ�׶Σ�
                result = dao.updateXsjdShzt(prejdid,model);
            }else{//���
                String dwid = model.getDwid();
                //������ӽ׶���Ϣ����һ�׶Σ�
                result = dao.updateZdjdShzt(prejdid,model);
            }
            //���ʦ�׶���Ϣ��������� ���˻ص��������ȣ�
            if(result){
                String hdsqid = dao.getHdsqid(model);
                model.setHdsqid(hdsqid);
                result = dao.insertJsjdInfo(jdid,model);
            }
        String hdsqid = dao.getHdsqid(model);
        if("1".equals(bmlx)){//����
            result = dao.updateCyjd(null,hdsqid);
        } else {//���
            result = dao.updateZdCyjd(null,hdsqid);
        }
        //�������ӻ���һ���׶Σ�������ӽ����Ϣ��
        if(!"1".equals(bmlx) && dao.isLastNode(hdid, jdid)){
            result = dao.insertZdjgxx(model);
        }
        /*}else {
            throw new Exception("��˲������Ϸ�");
        }*/
        return result;
    }

    /**
     *  ���ݻid��ȡ�����б�.
     *
     * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
     * @date 2018-03-22 10:05
     * @param hdid
     * @return java.util.List<java.util.HashMap<java.lang.String,java.lang.String>>
     * @throw
     */
    public List<HashMap<String,String>> getJxList(String hdid) {

        return dao.getJxList(hdid);
    }
    //��ȡ��ǰ�׶��Ժ�Ľ׶�
    public List<HashMap<String,String>> getJdList(String hdid,String currentJdid){
        return dao.getJdList(hdid,currentJdid);
    }
    /**
     *  ��ѯָ����׶������Ϣ.
     *
     * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
     * @date 2018-03-28 10:57
     * @param model
     * @return java.util.HashMap<java.lang.String,java.lang.String>
     * @throw
     */
    public HashMap<String,String> getHdjdShInfo(HdxxForm model) {

        String hdsqid = dao.getHdsqid(model);
        model.setHdsqid(hdsqid);
        return dao.getHdjdShInfo(model);
    }

    /**
     *  �ж��Ƿ�ר���ų�Ա.
     *
     * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
     * @date 2018-03-29 16:21
     * @param hdid
     * @param userName
     * @return boolean
     * @throw
     */
    public boolean isZjtcy(String hdid, String userName) {

        return dao.isZjtcy(hdid,userName);
    }
}
