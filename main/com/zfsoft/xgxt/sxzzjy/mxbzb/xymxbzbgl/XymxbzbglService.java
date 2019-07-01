package com.zfsoft.xgxt.sxzzjy.mxbzb.xymxbzbgl;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import xgxt.form.User;
import xgxt.utils.GetTime;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

/**
 * @类功能描述:
 * @作者： lgx [工号:1553]
 * @时间： 2018-06-27 11:41
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class XymxbzbglService extends SuperServiceImpl<XymxbzbglForm,XymxbzbglDao> {
    public List<HashMap<String,String>> getAlllb() {
        return dao.getAlllb();
    }

    public boolean xymxbzbglInsert(XymxbzbglForm model,User user) throws Exception {
        model.setFbsj(GetTime.getTimeByFormat("yyyy-mm-dd hh24:mi:ss"));//发布时间
        model.setFbr(user.getRealName());//发布人
        model.setFbbmdm(user.getUserDep());
        return dao.xymxbzbglInsert(model);
    }

    public boolean xymxbzbglUpdate(XymxbzbglForm model) throws Exception {
        model.setXgsj(GetTime.getTimeByFormat("yyyy-mm-dd hh24:mi:ss"));//修改时间
        return dao.xymxbzbglUpdate(model);
    }

    /**
     * @描述:获取单条信息
     * @作者：lgx [工号：1553]
     * @日期：2018/6/28 15:05
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param: [model]
     * @return: java.util.HashMap<java.lang.String,java.lang.String>
     */
    public HashMap<String,String> getxymxbzbglInfo(XymxbzbglForm model) throws SQLException {
        return dao.getxymxbzbglInfo(model);
    }

    /**
     * @描述:发布
     * @作者：lgx [工号：1553]
     * @日期：2018/6/28 15:06
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param: [ids]
     * @return: java.lang.String
     */
    public String xymxbzbglFb(String[] ids) throws Exception {
        String xgsj = GetTime.getTimeByFormat("yyyy-mm-dd hh24:mi:ss");//修改时间
        boolean rs = dao.xymxbzbglFb(ids,xgsj);
        return rs ? "发布成功":"发布失败";
    }
    /**
     * @描述:取消发布
     * @作者：lgx [工号：1553]
     * @日期：2018/6/28 15:06
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param: [ids]
     * @return: java.lang.String
     */
    public String xymxbzbglQxfb(String[] ids) throws Exception {
        String xgsj = GetTime.getTimeByFormat("yyyy-mm-dd hh24:mi:ss");//修改时间
        boolean rs = dao.xymxbzbglQxfb(ids,xgsj);
        return rs ? "取消发布成功":"取消发布失败";
    }


    /**
     * @描述:置顶
     * @作者：lgx [工号：1553]
     * @日期：2018/6/28 15:06
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param: [ids]
     * @return: java.lang.String
     */
    public String xymxbzbglZd(String[] ids) throws Exception {
        String xgsj = GetTime.getTimeByFormat("yyyy-mm-dd hh24:mi:ss");//修改时间
        boolean rs = dao.xymxbzbglZd(ids,xgsj);
        return rs ? "置顶成功":"置顶失败";
    }

    /**
     * @描述:取消置顶
     * @作者：lgx [工号：1553]
     * @日期：2018/6/28 15:06
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param: [ids]
     * @return: java.lang.String
     */
    public String xymxbzbglQxzd(String[] ids) throws Exception {
        String xgsj = GetTime.getTimeByFormat("yyyy-mm-dd hh24:mi:ss");//修改时间
        boolean rs = dao.xymxbzbglQxzd(ids,xgsj);
        return rs ? "取消置顶成功":"取消置顶失败";
    }

    public List<HashMap<String,String>> getAlbForView() {
        return dao.getAlbForView();
    }

    public List<HashMap<String,String>> getNewsList(String typedm,String size) {
        return dao.getNewsList(typedm,size);
    }

    public List<HashMap<String,String>> getNewsmore(XymxbzbglForm model) throws Exception{
        return dao.getNewsmore(model);
    }

    public boolean addYdr(XymxbzbglForm model,User user) throws Exception {

        return dao.addYdr(model,user);
    }

    public List<HashMap<String,String>> getYydmd(XymxbzbglForm model) throws Exception {
        return dao.getYydmd( model);
    }
}
