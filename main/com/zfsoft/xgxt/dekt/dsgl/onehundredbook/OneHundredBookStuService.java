package com.zfsoft.xgxt.dekt.dsgl.onehundredbook;

import com.zfsoft.xgxt.dekt.dsgl.dsglsq.DsglsqForm;
import org.springframework.util.CollectionUtils;
import xgxt.form.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OneHundredBookStuService{

    OneHundredBookStuDao dao = new OneHundredBookStuDao();

    public List<SmObject> getPageList(DsglsqForm t, User user) throws Exception {

        //��һ�����������������ҳ���ѯ
        if(!"myStore".equals(t.getNj())){
            //��ȡ��Ӧ�꼶�������鼮
            List<HashMap<String, String>> list1 = dao.getPageList(t);
            //��ȡ��ǰ��¼ѧ�����ղص��鼮
            List<HashMap<String, String>> list2 = dao.getPageList(t, user);
            //��ȡÿ���鼮��Ӧ�Ķ�����
            List<HashMap<String, String>> list3 = dao.getSubscribeNum(t);
            //��ȡ��Ӧѧ�����鼮�����ĵü�״̬
            List<HashMap<String, String>> list4 = dao.getXd(t,user);

            return createListView(list1,list2,list3,list4);
        } else {
            //�ҵ��ղ�ҳ���ѯ
            List<HashMap<String, String>> myStoreList = dao.getMyStoreBook(t,user);
            List<SmObject> list = new ArrayList<SmObject>();
            SmObject obj;
            for(HashMap<String, String> a : myStoreList){

                obj = new SmObject();
                obj.setSm(a.get("ssm"));//����
                obj.setStp(a.get("stp"));//ͼƬ
                obj.setSftj(a.get("sftj"));//�Ƿ��Ƽ�
                obj.setAuthor(a.get("author"));//����
                obj.setDyl(a.get("dyl"));//������
                obj.setSqid(a.get("sqid"));

                list.add(obj);
            }
            return list;
        }
    }

    /**
     * ��������չʾ��list
     * @param alis �����鼮
     * @param blis �ղص��鼮
     * @param clis ������
     * @param dlis �����ĵ�
     * @return
     */
    private List<SmObject> createListView(List<HashMap<String, String>> alis,
                                          List<HashMap<String, String>> blis,
                                          List<HashMap<String, String>> clis,
                                          List<HashMap<String, String>> dlis){

        List<SmObject> list = new ArrayList<SmObject>();
        SmObject obj;
        for(HashMap<String, String> a : alis){
            String key = a.get("smid") == null ? "" : a.get("smid");//��ĿID
            obj = new SmObject();
            obj.setSm(a.get("ssm"));//����
            obj.setStp(a.get("stp"));//ͼƬ
            obj.setSftj(a.get("sftj"));//�Ƿ��Ƽ�
            obj.setAuthor(a.get("author"));//����
            obj.setEbook(a.get("ebook"));//����
            obj.setLx(a.get("lx"));
            //�жϵ�ǰѭ�����鼮�Ƿ��ڱ��ղص�list����
            for(HashMap<String, String> b : blis){
                if(key.equals(b.get("smid"))){
                    obj.setSfsc("on");
                    break;
                }
            }
            //����ÿ���鼮�Ķ�����
            for(HashMap<String, String> c : clis){
                if(key.equals(c.get("smid"))){
                    obj.setDyl(c.get("dyl"));
                    break;
                }
            }
            //��ǰѭ�����鼮�Ƿ����Ķ��ĵ�
            for(HashMap<String, String> d : dlis){
                if(key.equals(d.get("smid"))){
//                    obj.setXd("true");
//                    obj.setShzt(d.get("shzt"));
                    obj.setSqid(d.get("sqid"));
                }
            }
            list.add(obj);
        }
        return CollectionUtils.isEmpty(list) ? null : list;
    }

    public boolean sc(String ssm,String operation,String xh) throws SQLException {
        return dao.sc(ssm,operation,xh);
    }
}
