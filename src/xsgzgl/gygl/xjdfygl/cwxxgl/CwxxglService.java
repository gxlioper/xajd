package xsgzgl.gygl.xjdfygl.cwxxgl;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.CollectionUtils;
import xgxt.DAO.DAO;
import xgxt.form.User;
import xsgzgl.gygl.xjdfygl.qsxxgl.QsxxglDao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

/**
 * @描述：TODO
 * @作者：WANCHEN
 * @日期：
 */
public class CwxxglService extends SuperServiceImpl<CwxxglForm,CwxxglDao>{

    private QsxxglDao qsxxglDao = new QsxxglDao();

    @Override
    public CwxxglForm getModel(CwxxglForm cwxxglForm) throws Exception {
        return dao.getModel(cwxxglForm);
    }

    public boolean batchInsert(List<String[]> params) throws SQLException {
        return dao.batchInsert(params);
    }

    public List<HashMap<String,String>> getBlyyList(){
        return dao.getBlyyList();
    }

    public List<HashMap<String,String>> getRzyyList(){
        return dao.getRzyyList();
    }

    public List<HashMap<String,String>> getTsyyList(){
        return dao.getTsyyList();
    }

    public boolean plblcw(CwxxglForm model) throws Exception {
        return dao.plblcw(model);
    }

    public int cxcwgs() throws SQLException {
        List<String> cwpks = dao.getAllWrzCw();
        return CollectionUtils.isEmpty(cwpks) ? 0 : cwpks.size();
    }

    public boolean cwsscshBc(CwxxglForm model) throws Exception {
        String[] pks;
        if(StringUtils.isNotEmpty(model.getPks())){
            pks = model.getPks().split(",");
        } else {
            List<String> ids = dao.getAllWrzCw();
            pks = ids.toArray(new String[ids.size()]);
        }

        boolean flag = dao.cwsscsh(pks,model.getCshlx());
        if(flag && "1".equals(model.getSfcshqs())){
            List<String> qsids = dao.getQsids(pks);
            flag = qsxxglDao.qssscsh(qsids.toArray(new String[qsids.size()]));
        }

        return flag;
    }

    public boolean rzSave(CwxxglForm model) throws Exception {
        boolean flag = dao.rzSave(model);
        if(flag){
            flag = dao.rzSsydBc(model);
        }
        return flag;
    }

    public HashMap<String,String> getXsxx(String pk){
        return dao.getXsxx(pk);
    }

    public boolean tsBc(CwxxglForm model) throws Exception {
        boolean flag = dao.tsxxBc(model);
        if(flag){
            flag = dao.tsSsydBc(model);
        }
        return flag;
    }

    public List<HashMap<String,String>> getQshXsxxList(String lddm,String qsh){
        return dao.getQshXsxxList(lddm,qsh);
    }

    public List<HashMap<String, String>> getzsqxcwList(CwxxglForm cwxxglForm, User user) throws Exception {
        return dao.getzsqxcwList(cwxxglForm,user);
    }

    public boolean saveDqsj(CwxxglForm model) throws Exception {
        return dao.saveDqsj(model);
    }

    public List<HashMap<String, String>> fyycList(CwxxglForm t, User user) throws Exception {
        if("cw".equals(t.getCxlx())){
            return dao.fyycList(t,user);
        } else {
            return dao.fyycqsList(t,user);
        }

    }
}
