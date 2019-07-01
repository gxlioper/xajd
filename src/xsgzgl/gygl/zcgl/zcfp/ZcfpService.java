package xsgzgl.gygl.zcgl.zcfp;

import com.zfsoft.ms.mail.util.CollectionUtils;
import com.zfsoft.ms.mail.util.StringUtils;
import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import xgxt.form.User;
import xsgzgl.gygl.xjdfygl.qsxxgl.QsxxglService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @描述：TODO
 * @作者：WANCHEN
 * @日期：
 */
public class ZcfpService extends SuperServiceImpl<ZcfpForm,ZcfpDao> {
    private QsxxglService qsxxglService = new QsxxglService();

    public List<HashMap<String, String>> zcfpList(ZcfpForm t, User user) throws Exception {
        if("lc".equalsIgnoreCase(t.getCxlx())){
            return dao.getLcZcslList(t,user);
        } else if("qs".equalsIgnoreCase(t.getCxlx())){
            return dao.getQsZcslList(t,user);
        } else {
            return dao.getPageList(t,user);
        }
    }

    public String yz(ZcfpForm t){
        Map<String,String> doubleCheck = new HashMap<String, String>();
        List<FpFrom> list = t.getFpFromList();
        if(CollectionUtils.isEmpty(list)){
            return "资产信息为空！";
        }
        for(int i=0;i<list.size();i++){

            if(StringUtils.isNull(list.get(i).getLxdm())){
                return "资产类型为空！";
            }
            if(StringUtils.isNull(list.get(i).getZcid())){
                return "资产名称为空！";
            }
            if(StringUtils.isNull(list.get(i).getSl())){
                return "数量为空！";
            }
            if(doubleCheck.containsKey(list.get(i).getZcid())){
                return "资产重复！";
            }
            doubleCheck.put(list.get(i).getZcid(),list.get(i).getZcid());
        }
        return "true";
    }
    public boolean save(ZcfpForm t) throws Exception {

        List<FpFrom> inserttData = new ArrayList<FpFrom>();
        List<HashMap<String,String>> qsList = null;
        //查询楼栋寝室信息
        if(StringUtil.isNull(t.getCh())){
            qsList = qsxxglService.getQsxxListByLddm(t.getLddm());
            //查询楼层寝室信息
        } else if(StringUtil.isNull(t.getQsh())){
            qsList = qsxxglService.getQsxxListByLddmAndCh(t.getLddm(),t.getCh());
        } else {
            //单个寝室资产信息
        }

        List<FpFrom> zcxxList = t.getFpFromList();
        if(qsList == null){
            for(int i=0;i<zcxxList.size();i++){
                FpFrom s = zcxxList.get(i);
                FpFrom data = new FpFrom();
                data.setLddm(t.getLddm());
                data.setQsh(t.getQsh());
                data.setCh(t.getCh());
                data.setLxdm(s.getLxdm());
                data.setZcid(s.getZcid());
                data.setSl(s.getSl());
                data.setBz(s.getBz());
                inserttData.add(data);
            }
        } else {
            for(int i=0;i<zcxxList.size();i++){
                FpFrom s = zcxxList.get(i);
                for(HashMap<String,String> item : qsList){
                    FpFrom data = new FpFrom();
                    data.setLddm(item.get("lddm"));
                    data.setQsh(item.get("qsh"));
                    data.setCh(item.get("ch"));
                    data.setLxdm(s.getLxdm());
                    data.setZcid(s.getZcid());
                    data.setBz(s.getBz());
                    data.setSl(s.getSl());
                    inserttData.add(data);
                }
            }
        }

        return dao.save(inserttData);
    }
    public boolean del(String[] pks) throws Exception {
        return dao.del(pks);
    }

    public List<HashMap<String,String>> getQsZclist(String lddm,String qsh){
        return dao.getQsZclist(lddm,qsh);
    }
}
