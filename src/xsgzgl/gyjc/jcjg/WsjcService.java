/**
 * @部门:学工产品事业部
 * @日期：2018年5月28日 下午4:26:26 
 */  
package xsgzgl.gyjc.jcjg;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import xgxt.form.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张昌路[工号:982]
 * @时间： 2018年5月28日 下午4:26:26 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class WsjcService extends SuperServiceImpl<WsjcForm, WsjcDao>{

    public List<HashMap<String, String>> getList(WsjcForm t,User user) throws Exception {
        // TODO 自动生成方法存根
        return dao.getList(t,user);
    }


    public HashMap<String,String> getQsjbxx(String lddm, String qsh) {

        return dao.getQsjbxx(lddm,qsh);
    }

    public HashMap<String,String> getJcxx(String jcsj, String lddm, String qsh) {

        return dao.getJcxx(jcsj,lddm,qsh);
    }

    public List<HashMap<String,String>> getZtwspj(String jcrq , String lddm, String qsh) {
        return dao.getZtwspj(jcrq,lddm,qsh);
    }

    public List<HashMap<String,String>> getGrwspj(String jcrq, String lddm, String qsh) {
        return dao.getGrwspj(jcrq,lddm,qsh);
    }

    public List<GrpjModel> getGrpjModelList(List<HashMap<String, String>> grwspjList) {

//        List<GrpjModel> grpjModelList = new ArrayList<GrpjModel>();
        Map<String,GrpjModel> grpjModelMap = new HashMap<String,GrpjModel>();

        for(HashMap<String,String> grwspj:grwspjList){
            String xh = grwspj.get("xh");
            GrpjModel grpjModel =  grpjModelMap.get(xh);

            if(grpjModel==null){
                grpjModel = new GrpjModel();
                grpjModel.setXh(xh);
                grpjModel.setCwh(grwspj.get("cwh"));
                grpjModel.setXm(grwspj.get("xm"));
                List<Map<String,String>> pjList = new ArrayList<Map<String, String>>();
                grpjModel.setPjList(pjList);
                grpjModelMap.put(xh,grpjModel);
            }

            List<Map<String,String>> list =grpjModel.getPjList();
            if(grwspj.get("pjdm")!= null){
                Map<String,String> map = new HashMap<String,String>();
                map.put("pjdm",grwspj.get("pjdm"));
                map.put("mc",grwspj.get("mc"));
                map.put("xsxh",grwspj.get("xsxh"));
                list.add(map);
            }
        }

        return new ArrayList<GrpjModel>(grpjModelMap.values());
    }

    public List<HashMap<String,String>> getOptions() {
       return dao.getOptions();
    }

    public List<HashMap<String,String>> getGrOptions() {
        return dao.getGrOptions();

    }

    public boolean delZtpj(WsjcForm model) throws Exception {
       return dao.delZtpj(model);
    }

    public boolean insertZtpj(WsjcForm model) throws SQLException {
        return dao.insertZtpj(model);
    }

    public boolean delGrpj(WsjcForm model) throws SQLException {
        return dao.delGrpj(model);
    }

    public boolean insertGrpj(WsjcForm model) throws SQLException {
        return  dao.insertGrpj(model);
    }

    public List<HashMap<String,String>> getJbz(String[] ztpjs) {
        return dao.getJbz(ztpjs);
    }

    public boolean updatePj(WsjcForm model) throws Exception {
        return dao.runUpdate(model);
    }

    public boolean updatePjdj(WsjcForm model) throws Exception {
        //计算等级
        String pf = "A";
        int num =0;
        if(model.getZtpjs()!=null) {
            if (model.getZtpjs().length == 0) {
                pf = "A";
            } else {
                List<HashMap<String, String>> JbzList = getJbz(model.getZtpjs());
                for (int i = 0; i < JbzList.size(); i++) {
                    HashMap<String, String> map = JbzList.get(i);
                    String jbz = map.get("jbz");
                    num = num + Integer.parseInt(jbz);

                }
                if (num == 0) {
                    pf = "A";
                } else if (num > 0 && num <= 2) {
                    pf = "B";
                } else if (num > 2) {
                    pf = "C";
                }
            }
        }
        model.setPjdj(pf);
        return dao.updatePjdj(model);
    }

    public List<HashMap<String,String>> getDCList(WsjcForm model, User user) throws Exception {
        return dao.getDCList(model,user);
    }
}
