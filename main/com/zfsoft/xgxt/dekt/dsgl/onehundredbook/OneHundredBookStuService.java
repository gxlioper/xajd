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

        //大一，大二，大三，大四页面查询
        if(!"myStore".equals(t.getNj())){
            //获取对应年级的所有书籍
            List<HashMap<String, String>> list1 = dao.getPageList(t);
            //获取当前登录学生已收藏的书籍
            List<HashMap<String, String>> list2 = dao.getPageList(t, user);
            //获取每本书籍对应的订阅量
            List<HashMap<String, String>> list3 = dao.getSubscribeNum(t);
            //获取对应学生的书籍读书心得及状态
            List<HashMap<String, String>> list4 = dao.getXd(t,user);

            return createListView(list1,list2,list3,list4);
        } else {
            //我的收藏页面查询
            List<HashMap<String, String>> myStoreList = dao.getMyStoreBook(t,user);
            List<SmObject> list = new ArrayList<SmObject>();
            SmObject obj;
            for(HashMap<String, String> a : myStoreList){

                obj = new SmObject();
                obj.setSm(a.get("ssm"));//书名
                obj.setStp(a.get("stp"));//图片
                obj.setSftj(a.get("sftj"));//是否推荐
                obj.setAuthor(a.get("author"));//作者
                obj.setDyl(a.get("dyl"));//订阅量
                obj.setSqid(a.get("sqid"));

                list.add(obj);
            }
            return list;
        }
    }

    /**
     * 创建画面展示的list
     * @param alis 所有书籍
     * @param blis 收藏的书籍
     * @param clis 订阅量
     * @param dlis 读书心得
     * @return
     */
    private List<SmObject> createListView(List<HashMap<String, String>> alis,
                                          List<HashMap<String, String>> blis,
                                          List<HashMap<String, String>> clis,
                                          List<HashMap<String, String>> dlis){

        List<SmObject> list = new ArrayList<SmObject>();
        SmObject obj;
        for(HashMap<String, String> a : alis){
            String key = a.get("smid") == null ? "" : a.get("smid");//书目ID
            obj = new SmObject();
            obj.setSm(a.get("ssm"));//书名
            obj.setStp(a.get("stp"));//图片
            obj.setSftj(a.get("sftj"));//是否推荐
            obj.setAuthor(a.get("author"));//作者
            obj.setEbook(a.get("ebook"));//链接
            obj.setLx(a.get("lx"));
            //判断当前循环的书籍是否在被收藏的list当中
            for(HashMap<String, String> b : blis){
                if(key.equals(b.get("smid"))){
                    obj.setSfsc("on");
                    break;
                }
            }
            //设置每本书籍的订阅量
            for(HashMap<String, String> c : clis){
                if(key.equals(c.get("smid"))){
                    obj.setDyl(c.get("dyl"));
                    break;
                }
            }
            //当前循环的书籍是否有阅读心得
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
