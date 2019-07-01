package xsgzgl.gygl.cwdh;

import com.zfsoft.ms.mail.util.StringUtils;
import com.zfsoft.xgxt.base.export.util.DateUtils;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import org.apache.commons.lang.ObjectUtils;
import xgxt.action.Base;

import java.util.*;

/**
 * @描述：TODO
 * @作者：WANCHEN
 * @日期：
 */
public class CwdhService extends SuperServiceImpl<CwdhForm,CwdhDao> {

    //验证
    public String yz(CwdhForm t){
        String[] outKey = t.getOutKey();
        String[] inKey = t.getInKey();
        if(outKey == null || inKey == null){
            return "请选择床位！";
        }
        if(outKey.length != inKey.length){
            return "转出和转入的床位数量不一致，请确认！";
        }
        for(int i=0;i<outKey.length;i++){
            if(outKey[i].equals(inKey[i])){
                return "转出与转入床位重复！";
            }
        }
        return "true";
    }
    //保存
    //按顺序调换床位
    public boolean save(CwdhForm t) throws Exception {

        String[] outKey = t.getOutKey();
        String[] inKey = t.getInKey();
        //根据key查询到床位信息
        List<HashMap<String,String>> outList = dao.getCwListByKey(outKey);
        List<HashMap<String,String>> inList = dao.getCwListByKey(inKey);

        //查询出来的数据顺序可能会被打乱，所以按照提交的顺序排序
        this.setOrder(Arrays.asList(outKey),outList);
        this.setOrder(Arrays.asList(inKey),inList);

        //宿舍异动信息list
        List<String[]> ydList = new ArrayList<String[]>();
        //调换信息
        List<String[]> dhxx = new ArrayList<String[]>();
        //转出宿舍的异动信息
        String rq = DateUtils.getCustomFomratCurrentDate("yyyy-MM-dd");
        for(int i=0;i<outList.size();i++){
            HashMap<String,String> out = outList.get(i);
            HashMap<String,String> in = inList.get(i);
            //转出宿舍异动信息
            String[] sout = {out.get("xh"), Base.currXn,Base.currXq,rq,"03","03",rq,out.get("rzsj"),out.get("lddm"),out.get("ldmc")
            ,out.get("qsh"),out.get("cwh"),in.get("lddm"),in.get("ldmc"),in.get("qsh"),in.get("cwh"),"0"};
            ydList.add(sout);
            //筛选出转入宿舍中有学号的床位，生成异动信息
            if(StringUtils.isNotEmpty(in.get("xh"))){
                String[] sin = {in.get("xh"), Base.currXn,Base.currXq,rq,"03","03",rq,in.get("rzsj"),in.get("lddm"),in.get("ldmc")
                        ,in.get("qsh"),in.get("cwh"),out.get("lddm"),out.get("ldmc"),out.get("qsh"),out.get("cwh"),"0"};
                ydList.add(sin);
            }
            //生成调换信息
            String[] s1 = {out.get("nj"),out.get("xydm"),out.get("zydm"),out.get("bjdm"),out.get("xh"),rq
                    ,in.get("lddm"),in.get("qsh"),in.get("cwh")};
            String[] s2 = {in.get("nj"),in.get("xydm"),in.get("zydm"),in.get("bjdm"),in.get("xh"),StringUtils.isNotEmpty(in.get("xh"))?rq:""
                    ,out.get("lddm"),out.get("qsh"),out.get("cwh")};
            dhxx.add(s1);
            dhxx.add(s2);
        }

        boolean rs = dao.insertYdxx(ydList);
        if(rs){
            rs = dao.updateCwxx(dhxx);
        }
        return rs;
    }

    /**
     * list2 按照list1的顺序排序
     * @param list1
     * @param list2
     */
    public void setOrder(final List<String> list1, List<HashMap<String,String>> list2){
        Collections.sort(list2, new Comparator<HashMap<String, String>>() {
            @Override
            public int compare(HashMap<String, String> o1, HashMap<String, String> o2) {
                int i = list1.indexOf(o1.get("pk"));
                int j = list1.indexOf(o2.get("pk"));
                return i - j;
            }
        });
    }
}
