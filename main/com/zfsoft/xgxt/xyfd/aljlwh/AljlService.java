package com.zfsoft.xgxt.xyfd.aljlwh;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.*;
import com.zfsoft.xgxt.comm.shlc.util.ShlcUtil;
import common.Globals;
import org.springframework.util.ResourceUtils;
import xgxt.action.Base;
import xgxt.form.User;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by llf on 2019/8/30.
 */
public class AljlService extends SuperServiceImpl<AljlForm,AljlDao> {

    public List<HashMap<String,String>> getXn() throws Exception{
        return dao.getXn();
    }

    public List<HashMap<String,String>> getXq() throws Exception{
        return dao.getXq();
    }

    public HashMap<String,String> getXsxx(String xh) throws Exception{
        return dao.getXsxx(xh);
    }

    public List<HashMap<String,String>> getFdyxx(String bjdm) throws Exception{
        return dao.getFdyxx(bjdm);
    }

    public List<HashMap<String,String>> getBzrxx(String zybj) throws Exception{
        return dao.getBzrxx(zybj);
    }

    public List<HashMap<String,String>> getBjgkcList(AljlForm t) throws Exception{
        return dao.getBjgkcList(t);
    }

    public boolean saveGzal(AljlForm t, User user) throws Exception{
        String sqid = UniqID.getInstance().getUniqIDHash();
        t.setJdh(sqid);
        t.setAlzt("1"); //在档
        t.setJdr(user.getUserName());
        t.setJdsj(GetTime.getTimeByFormat("yyyy-MM-dd hh24:mm:ss"));
        return dao.runInsert(t);
    }

    public HashMap<String,String> getAljlxx(AljlForm t) throws Exception{
        return dao.getAljlxx(t);
    }

    public List<HashMap<String,String>> getJyList() throws Exception{
        return dao.getJyList();
    }

    public boolean saveGzjl(AljlForm t,User user) throws Exception{
        return dao.saveGzjl(t,user);
    }

    public String delAl(String[] jdhs) throws Exception{
        int succ = 0;
        int fail = 0;
        StringBuffer mes = new StringBuffer();
        for (int i=0;i<jdhs.length;i++){
            if(!dao.delAl(jdhs[i])){
                fail++;
            }else {
                succ++;
            }
        }
        mes.append("成功删除" + succ +"条");
        if(fail>0){
            mes.append("，" + fail + "条删除失败");
        }
        return mes.toString();
    }

    public List<HashMap<String,String>>  getGzjlList(AljlForm t) throws Exception{
        return dao.getGzjlList(t);
    }

    public File printForWord(HashMap<String, String> xsxx,AljlForm model,HttpServletRequest request) throws Exception {

        List<HashMap<String,String>> fdyxx = getFdyxx(xsxx.get("bjdm"));//辅导员
        List<HashMap<String,String>> bzrxx = getBzrxx(xsxx.get("zybj"));//班主任

        List<HashMap<String,String>> bjgkclist = getBjgkcList(model);//不及格课程

        List<HashMap<String,String>> gzjlList = getGzjlList(model); //工作记录

        Map<String, Object> data = new HashMap<String, Object>();

        data.putAll(xsxx);
        data.put("xxmc", Base.xxmc);
        data.put("alxx",model);
        data.put("fdyxx",fdyxx);
        data.put("bzrxx",bzrxx);
        data.put("bjgkclist",bjgkclist);
        data.put("gzjlList",gzjlList);

        return getWord(data);
    }

    private File getWord(Map<String, Object> data) throws FileNotFoundException {
//		File wordFile = FreeMarkerUtil.getWordFile(data,
//				"classpath://templates//xszz", "knsrdsq.xml");
        String templatePath = Constants.TEP_DIR+"xyfd/gzal_"+Base.xxdm+".xml";

        File wordFile = null;

        try{
            ResourceUtils.getFile(templatePath);
            wordFile = FreeMarkerUtil.getWordFile(data, Constants.TEP_DIR + "xyfd", "gzal.xml", FreeMarkerUtil.getFileName(data.get("xh") +"["+data.get("xm")+"]"));
        }catch (Exception e) {
            wordFile = FreeMarkerUtil.getWordFile(data, Constants.TEP_DIR + "xyfd", "gzal_"+Base.xxdm+".xml", FreeMarkerUtil.getFileName(data.get("xh") +"["+data.get("xm")+"]"));
        }

        return wordFile;

    }
}
