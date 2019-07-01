package xsgzgl.gygl.cwfpgl;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import org.springframework.util.CollectionUtils;
import xgxt.form.User;

import java.util.*;
import java.util.regex.Pattern;

/**
 * @描述：TODO
 * @作者：WANCHEN
 * @日期：
 */
public class CwfpglNewService extends SuperServiceImpl<CwfpglForm,CwfpglNewDao> {

    public List<HashMap<String,String>> searchList(CwfpglForm t, User user) throws Exception {
        if("xy".equals(t.getFpfs())){
            return dao.getPageList(t,user);
        } else {
            return dao.getSybjList(t,user);
        }
    }
    //年级下拉框
    public List<HashMap<String,String>> getNjList(){
        return dao.getNjList();
    }
    //年级下拉框（书院分配用）
    public List<HashMap<String,String>> getNjListForSy(){
        return dao.getNjListForSy();
    }
    //学院下拉框
    public List<HashMap<String,String>> getXyListByNj(String nj){
        return dao.getXyListByNj(nj);
    }
    //获取书院下拉框
    public List<HashMap<String,String>> getSyListByNj(String nj){
        return dao.getSyListByNj(nj);
    }
    //专业下拉框
    public List<HashMap<String,String>> getZyListByNjXy(String nj,String xy){
        return dao.getZyListByNjXy(nj,xy);
    }
    //班级下拉框
    public List<HashMap<String,String>> getBjListByNjXyZy(String nj,String xy,String zy){
        return dao.getBjListByNjXyZy(nj, xy, zy);
    }
    //获取行政班级下拉框（书院分配用）
    public List<HashMap<String,String>> getBjListByNjSy(String nj,String sy){
        return dao.getBjListByNjSy(nj, sy);
    }
    //获取当前班级已分配统计表格信息
    public List<HashMap<String,String>> getYfpTjXx(String nj,String xy,String zy,String bj){
        return dao.getYfpTjXx(nj, xy, zy, bj);
    }
    //获取当前班级已分配统计表格信息（书院分配用）
    public List<HashMap<String,String>> getYfpTjXxForSy(String nj,String sy,String bj){
        return dao.getYfpTjXxForSy(nj, sy, bj);
    }
    //获取当前班级已分配统计信息
    public HashMap<String,String> getBjTjXx(String nj,String xydm,String zydm,String zybj){
        return dao.getBjTjXx(nj,xydm,zydm,zybj);
    }
    //获取当前班级已分配统计信息（书院分配用）
    public HashMap<String,String> getBjTjXxForSy(String nj,String sydm,String bjdm){
        return dao.getBjTjXxForSy(nj,sydm,bjdm);
    }
    //获取楼层
    public List<HashMap<String,String>> getLdLc(String lddm,String qsxb,User user){
        return dao.getLcxx(lddm, qsxb, user);
    }
    //更具楼层获取寝室床位信息
    public Map<String,List<HashMap<String,String>>> getQscw(String key,User user){
        String[] lddmAndCh = key.split("@!!!");
        //床位
        List<HashMap<String,String>> cwList = dao.getQsCw(lddmAndCh[0], lddmAndCh[1], user);

        return this.groupBy(cwList);
    }
    //学院分配保存
    public boolean save(CwfpglForm t,User user) throws Exception {

        List<String> cwList = this.cwcl(t,user);

        return dao.save(t,cwList);
    }

    //书院分配保存
    public boolean saveForSy(CwfpglForm t,User user) throws Exception {

        List<String> cwList = this.cwcl(t,user);

        return dao.saveForSy(t,cwList);
    }
    //清空分配
    public boolean qkFp(CwfpglForm t) throws Exception {
        return dao.qkFp(t);
    }

    private List<String> cwcl(CwfpglForm t,User user) throws Exception {
        String lddmkey = t.getLddmkey();
        String[] lcKey = t.getLcKey();
        String[] cwKey = t.getCwIds();

        //因为床位不是画面打开就加载的，如果不点击暂开按钮，是没有床位的
        //在这种场合下有三种case:1.只有楼层key；2.只有床位key；3.同时都有
        List<String> cwList;
        if(lcKey != null && cwKey == null){
            cwList = dao.getCw(lddmkey,lcKey,user);

        } else if(lcKey == null && cwKey != null){
            cwList = Arrays.asList(cwKey);

        } else {
            List<String> lcs = new ArrayList<String>();
            //找出选中楼层中没有选中床位的楼层
            boolean mark;
            for(String lc : lcKey){
                mark = true;
                for(String cw : cwKey){//cw : lc@!!!qsh@!!!cwh
                    String[] s = cw.split("@!!!");
                    if(s[0].equals(lc)){
                        mark = false;
                        break;
                    }
                }
                if(mark){
                    lcs.add(lc);
                }
            }
            //根据楼层查询床位
            cwList = dao.getCw(lddmkey,lcs.toArray(new String[lcs.size()]),user);
            //查询的床位和页面提交的数据合并
            cwList.addAll(Arrays.asList(cwKey));
        }

        return cwList;
    }

    private Map<String,List<HashMap<String,String>>> groupBy(List<HashMap<String,String>> cwList){
        //按寝室分组
        Map<String,List<HashMap<String,String>>> qsMap = new LinkedHashMap<String, List<HashMap<String, String>>>();
        List<HashMap<String,String>> listCw;
        for(HashMap<String,String> cw : cwList){
            String qskey = cw.get("ch") + cw.get("qsh");
            if(qsMap.containsKey(qskey)){
                qsMap.get(qskey).add(cw);
            } else {
                listCw = new ArrayList<HashMap<String, String>>();
                listCw.add(cw);
                qsMap.put(qskey,listCw);
            }
        }
        return qsMap;
    }
}
