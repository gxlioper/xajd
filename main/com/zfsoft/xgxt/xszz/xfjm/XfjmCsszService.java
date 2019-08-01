package com.zfsoft.xgxt.xszz.xfjm;

import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.DateTranCnDate;
import com.zfsoft.xgxt.szdw.xgsz.CsszModel;
import common.newp.StringUtil;
import xgxt.utils.GetTime;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称:
 * @类功能描述:
 * @作者: 何爽 [工号:1730]
 * @时间: 2019/7/1 21:40
 */
public class XfjmCsszService extends SuperServiceImpl<CsszModel, XfjmCsszDao> implements Constants {


    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    private static final String startTime = " 00:00:00";
    private static final String endTime = " 23:59:59";

    private XfjmCsszDao dao = new XfjmCsszDao();

    public CsszModel getModel() {
        CsszModel model = null;
        try {
            model = dao.getModel();
            if (model == null) {
                model = new CsszModel();
                model.setSqkg(CLOSE);
            } else {
                long nowTime = System.currentTimeMillis();
                if (CLOSE.equals(model.getSqkg())) {
                    model.setSqkg(CLOSE);
                } else if (!StringUtil.isNull(model.getSqkssj()) && StringUtil.isNull(model.getSqjssj())) {
                    long start = DateTranCnDate.dateToTimeStamp(model.getSqkssj() +startTime);
                    model.setSqkg(start < nowTime ? OPEN : CLOSE);
                } else if (StringUtil.isNull(model.getSqkssj()) && !StringUtil.isNull(model.getSqjssj())) {
                    long end = DateTranCnDate.dateToTimeStamp(model.getSqjssj() +endTime);
                    model.setSqkg(end > nowTime ? OPEN : CLOSE);
                } else if (!StringUtil.isNull(model.getSqkssj()) && !StringUtil.isNull(model.getSqjssj())) {
                    long start = DateTranCnDate.dateToTimeStamp(model.getSqkssj() +startTime);
                    long end = DateTranCnDate.dateToTimeStamp(model.getSqjssj() +endTime);
                    if(nowTime>start && nowTime<end){
                        model.setSqkg(OPEN);
                    }else{
                        model.setSqkg(CLOSE);
                    }
                } else {
                    model.setSqkg(OPEN);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return model;
    }

    public boolean save(CsszModel model, boolean isAdd) {
        if (!isAdd) {
            return dao.update(model);
        } else {
            return dao.insert(model);
        }

    }
}

