package xsgzgl.gygl.xjdfygl.qsxxgl;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.directwebremoting.annotations.Param;
import org.springframework.util.CollectionUtils;
import xgxt.action.Base;
import xgxt.form.User;
import xsgzgl.gygl.xjdfygl.cwxxgl.CwxxglDao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @描述：TODO
 * @作者：WANCHEN
 * @日期：
 */
public class QsxxglService extends SuperServiceImpl<QsxxglForm,QsxxglDao> {

    private CwxxglDao cwxxglDao = new CwxxglDao();
    @Override
    public QsxxglForm getModel(QsxxglForm qsxxglForm) throws Exception {
        return dao.getModel(qsxxglForm);
    }

    @Override
    public boolean runUpdate(QsxxglForm qsxxglForm) throws Exception {
        return dao.runUpdate(qsxxglForm);
    }

    public boolean del(String[] pk) throws Exception {
        boolean f = dao.deleteQscw(pk);
        if(f){
            f = dao.delete(pk);
        }
        return f;
    }

    public boolean batchInsert(List<String[]> params) throws SQLException {
        return dao.batchInsert(params);
    }

    public boolean batchUpdate(List<QsxxglForm> qsxxglForms,String lddm) throws Exception {
        return dao.batchUpdate(qsxxglForms,lddm);
    }

    public boolean plxgqs(QsxxglForm model) throws Exception {
        return dao.plxgqs(model);
    }

    public List<HashMap<String,String>> getQscwList(String lddm,String qsh){
        return dao.getQscwList(lddm,qsh);
    }

    public boolean delQsCw(String lddm,String qsh,String cwh) throws Exception {
        return dao.delQsCw(lddm, qsh, cwh);
    }

    public boolean addQsCw(String lddm,String qsh,String cwh) throws Exception {
        return dao.addQsCw(lddm, qsh, cwh);
    }

    public boolean isExistCwh(String lddm,String qsh,String cwh){
        return dao.isExistCwh(lddm, qsh, cwh);
    }

    //未入住寝室个数
    public int cxwrzqsgs() throws SQLException {
        List<String> qsIdList = dao.getAllWrzQsList();
        return CollectionUtils.isEmpty(qsIdList) ? 0 : qsIdList.size();
    }

    public boolean qssscshBc(QsxxglForm model) throws Exception {
        String[] pks;
        if(StringUtils.isNotEmpty(model.getPks())){
            pks = model.getPks().split(",");
        } else {
            List<String> ids = dao.getAllWrzQsList();
            pks = ids.toArray(new String[ids.size()]);
        }
        boolean flag = dao.qssscsh(pks);

        if(flag && "1".equals(model.getSfcshcw())){
            List<String> cwpks = dao.getCwids(pks);
            flag = cwxxglDao.cwsscsh(cwpks.toArray(new String[cwpks.size()]),"all");
        }
        return flag;
    }

    /**根据楼栋信息初始化楼层下拉框
     * @param ldcs 楼栋层数
     * @param qsch 起始层数
     * @param sfhlc 是否含0层
     * @return
     */
    public List<String> lccsh(String ldcs,String qsch,boolean sfhlc){
        List<String> list = new ArrayList<String>();
        int cs = Integer.parseInt(ldcs);//总层数
        int qsc = Integer.parseInt(qsch);//起始层
        boolean skipZero = false;
        if(qsc < 0 && !sfhlc){
            cs++;
            skipZero = true;
        }
        for(int i=0;i<cs;i++){
            int currentLc = qsc + i;
            if(skipZero && currentLc == 0){
                i++;
                currentLc++;
            }
            list.add(String.valueOf(currentLc));
        }
        return list;
    }
    public List<HashMap<String,String>> getQsxxListByLddm(String lddm){
        return dao.getQsxxListByLddm(lddm);
    }
    public List<HashMap<String,String>> getQsxxListByLddmAndCh(String lddm,String ch){
        return dao.getQsxxListByLddmAndCh(lddm,ch);
    }
    public List<HashMap<String, String>> getQsfpList(QsxxglForm t, User u) throws Exception {
        return dao.getQsfpList(t,u);
    }

    public List<HashMap<String,String>> getXysy(String fpfs){
        return dao.getXysy(fpfs);
    }

    public boolean fpBc(QsxxglForm model) throws Exception {
        return dao.fpBc(model);
    }
}
