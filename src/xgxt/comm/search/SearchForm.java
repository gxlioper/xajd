package xgxt.comm.search;

import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.OptionUtil;
import common.Globals;
import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommForm;
import xsgzgl.wjcf.jcsz.WjcfJcszServices;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 高级查询_formBean
 * </p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 *
 * @author 伟大的骆
 * @version 1.0
 */
public class SearchForm extends CommForm {

    private static final long serialVersionUID = 1L;

    private String path;// 路径

    private String tj;// 条件

    private String mc;// 名称

    private String ssmk;// 所属模块

    private String stylePath;// 样式包地址

    // 特殊条件(民族，部门)
    private String[] spTj = new String[]{"mz", "bm"};

    // 联动条件(年级，学院，专业，班级，楼栋，层号，寝室号，校区，园区，勤工部门,团,营,连,排,纪律大类,纪律类别,评奖项目类型,评奖项目性质,评奖项目名称)
    private String[] ldTj = new String[]{"nj", "xy", "zy", "bj", "ld", "ch",
            "qsh", "xqdm", "yqdm", "qgbm", "tid", "yid", "lid", "pid", "bid", "ssid",
            "gyjllbdldm", "gyjllbdm", "sheng", "shi", "qu", "njNew", "xyNew",
            "zyNew", "bjNew", "xxmlx", "xxmxz", "xmmc", "zxsNj", "zxsXy", "zxsZy", "zxsBj", "fzxsNj", "fzxsXy", "fzxsZy"
            , "fzxsBj", "sy", "zybj"};

    public static SearchForm searchOptionModel;
    //TODO

    static {
        SearchForm.searchOptionModel = new SearchForm();
    }

    /**
     * *************************************************************************
     * ****************************************************
     */
    /**
     * ************************************************为解决65535问题，高级查询重构，优化*****
     * ********************************************
     */
    /**
     * *************************************************************************
     * ****************************************************
     */

    // ====================年级，学院，专业，班级=================================
    // 年级条件列表
    private List<HashMap<String, String>> njTjList;

    // 学院条件列表
    private List<HashMap<String, Object>> xyTjList;

    // 专业条件列表
    private List<HashMap<String, Object>> zyTjList;

    // 班级条件列表
    private List<HashMap<String, Object>> bjTjList;

    // ====================年级，学院，专业，班级 end=================================

    // ====================年级，学院，专业，班级START
    // 显示所有【学籍版追加】=================================
    // 年级条件列表
    private List<HashMap<String, String>> njNewTjList;

    // 部门/学院条件列表
    private List<HashMap<String, String>> bmNewTjList;

    // 学院条件列表
    private List<HashMap<String, Object>> xyNewTjList;

    // 专业条件列表
    private List<HashMap<String, Object>> zyNewTjList;

    // 班级条件列表
    private List<HashMap<String, Object>> bjNewTjList;

    // ====================年级，学院，专业，班级 END
    // 显示所有【学籍版追加】=================================

    // ====================在校生年级、学院、专业、班级、学制、学籍类别

    // 年级条件列表
    private List<HashMap<String, String>> zxsNjTjList;

    // 学院条件列表
    private List<HashMap<String, Object>> zxsXyTjList;

    // 专业条件列表
    private List<HashMap<String, Object>> zxsZyTjList;

    // 班级条件列表
    private List<HashMap<String, Object>> zxsBjTjList;

    // 学制条件列表
    private List<HashMap<String, String>> zxsXzTjList;

    // 学籍类别条件列表
    private List<HashMap<String, String>> zxsXjlbTjList;

    //====================在校生年级、学院、专业、班级、学制、学籍类别END

// ====================非在校生年级、学院、专业、班级、学制、学籍类别

    // 年级条件列表
    private List<HashMap<String, String>> fzxsNjList;

    // 学院条件列表
    private List<HashMap<String, Object>> fzxsXyTjList;

    // 书院条件列表
    private List<HashMap<String, String>> syTjList;
    // 专业条件列表
    private List<HashMap<String, Object>> fzxsZyTjList;

    // 班级条件列表
    private List<HashMap<String, Object>> fzxsBjTjList;

    // 学制条件列表
    private List<HashMap<String, String>> fzxsXzTjList;

    // 学籍类别条件列表
    private List<HashMap<String, String>> fzxsXjlbTjList;

    //====================非在校生年级、学院、专业、班级、学制、学籍类别END
    // 显示所有【学籍版追加】=================================

    // ====================通用条件 =================================
    // 学年条件列表
    private List<HashMap<String, String>> xnTjList;
    private List<HashMap<String, String>> pdxnTjList;
    // 入伍学年条件列表
    private List<HashMap<String, String>> rwxnTjList;

    // 学期条件列表
    private List<HashMap<String, String>> xqTjList;
    private List<HashMap<String, String>> pdxqTjList;
    // 学期名称条件列表
    private List<HashMap<String, String>> xqmcTjList;

    // 年度条件列表
    private List<HashMap<String, String>> ndTjList;

    // 月份条件列表
    private List<HashMap<String, String>> yfTjList;

    // 是否条件列表
    private List<HashMap<String, String>> sfTjList;

    private List<HashMap<String, String>> sfzsTjList;

    private List<HashMap<String, String>> sfsqrdTjList;

    private List<HashMap<String, String>> sfssmzTjList;

    private List<HashMap<String, String>> sfdgsxTjList;

    private List<HashMap<String, String>> sflspxTjList;

    private List<HashMap<String, String>> sfzjxyTjList;

    private List<HashMap<String, String>> sfjjknTjList;

    private List<HashMap<String, String>> stsfcjTjList;

    private List<HashMap<String, String>> sfxxknTjList;

    private List<HashMap<String, String>> sfxlkrTjList;

    private List<HashMap<String, String>> sfjtkrTjList;

    private List<HashMap<String, String>> sfyqtkrTjList;

    private List<HashMap<String,String>>  sfgkTjList;//是否挂科

    private List<HashMap<String,String>> sfxnTjList;

    // 是否合格条件列表
    private List<HashMap<String, String>> sfhgTjList;

    // 是否条件列表
    private List<HashMap<String, String>> sflxTjList;

    // 审核状态条件列表
    private List<HashMap<String, String>> shztTjList;

    // 审核状态（新）条件列表
    private List<HashMap<String, String>> shztxTjList;
    // 审核状态（班级评议）条件列表
    private List<HashMap<String, String>> shztxbjpyTjList;
    // 审核状态（班级评议结果查询）条件列表
    private List<HashMap<String, String>> shztbjpyjgTjList;
    // 审核状态代码条件列表
    private List<HashMap<String, String>> shztdmTjList;

    // 审核结果条件列表
    private List<HashMap<String, String>> shjgTjList;

    // 审核结果条件列表
    private List<HashMap<String, String>> shjg2TjList;

    // 审核结果条件列表
    private List<HashMap<String, String>> shjg3TjList;

    // 医疗保险状态条件列表
    private List<HashMap<String, String>> ylbxztTjList;

    // 应交年数条件列表
    private List<HashMap<String, String>> yjnumTjList;

    // 毕业年度条件列表
    private List<HashMap<String, String>> byndTjList;

    // 部门条件列表
    private List<HashMap<String, String>> bmTjList;

    // 审核狀態One
    private List<HashMap<String, String>> shztOneTjList;

    // 审核狀態Two
    private List<HashMap<String, String>> shztTwoTjList;
    // 省 市县三级联动
    private List<HashMap<String, Object>> shengTjList;
    // 市 市县三级联动
    private List<HashMap<String, Object>> shiTjList;
    // 县 市县三级联动
    private List<HashMap<String, Object>> quTjList;

    // ====================通用条件 end=================================

    // ====================学生信息 =================================
    // 性别条件列表
    private List<HashMap<String, String>> xbTjList;

    // 培养层次条件列表
    private List<HashMap<String, String>> pyccTjList;

    // 学制条件列表
    private List<HashMap<String, String>> xzTjList;

    // 学籍条件列表
    private List<HashMap<String, String>> xjTjList;

    // 是否在校条件列表
    private List<HashMap<String, String>> sfzxTjList;

    // 是否进修生条件列表
    private List<HashMap<String, String>> sfhqTjList;

    // 是否已毕业条件列表
    private List<HashMap<String, String>> sfybyTjList;

    // 政治面貌条件列表
    private List<HashMap<String, String>> zmTjList;

    // 民族条件列表
    private List<HashMap<String, String>> mzTjList;

    // 户口条件列表
    private List<HashMap<String, String>> hkTjList;

    // 是否缴费条件列表
    private List<HashMap<String, String>> sfjfTjList;

    // 转档类别条件列表
    private List<HashMap<String, String>> zdlbTjList;

    // 异动类别条件列表
    private List<HashMap<String, String>> ydlbTjList;

    // 省份条件列表
    private List<HashMap<String, String>> provTjList;

    // 是否已提交
    private List<HashMap<String, String>> sfytjTjList;

    // 单双号
    private List<HashMap<String, String>> sfdshTjList;

    // 社团星级
    private List<HashMap<String, String>> stxjTjList;

    //报名类型
    private List<HashMap<String, String>> bmlxTjList;

    // 档案在校情况
    private List<HashMap<String, String>> dazxqkTjList;

    // 启用状态
    private List<HashMap<String, String>> qyztTjList;

    // 档案清单维护状态
    private List<HashMap<String, String>> whztTjList;

    // 是否征兵离校
    private List<HashMap<String, String>> sfzblxTjList;

    // 认定档次
    private List<HashMap<String, String>> rddcTjList;

    // 上级推荐档次
    private List<HashMap<String, String>> sjdcTjList;

    // 资助项目
    private List<HashMap<String, String>> zzxmTjList;
    //同资助项目，防止相同PATH,TJ,LX违反唯一约束
    private List<HashMap<String, String>> zzxm1TjList;

    private List<HashMap<String, String>> tjztTjList; // 新评奖--综合测评--提交状态

    private List<HashMap<String, String>> cpzTjList;// 新评奖--参评小组

    private List<HashMap<String, String>> bjdlTjList;// 新评奖--班级大类
    private List<HashMap<String, String>> hjxzTjList;

    // 最后状态（审核状态）
    private List<HashMap<String, String>> zhztTjList;


    // 评分状态
    private List<HashMap<String, String>> pfztTjList;

    // ----------------学生其他信息 -----------------
    private List<HashMap<String, String>> zd1TjList;

    private List<HashMap<String, String>> zd3TjList;

    private List<HashMap<String, String>> zd5TjList;

    private List<HashMap<String, String>> zd24TjList;

    private List<HashMap<String, String>> zd26TjList;

    private List<HashMap<String, String>> zd8TjList;

    private List<HashMap<String, String>> zd9TjList;

    private List<HashMap<String, String>> zd10TjList;

    private List<HashMap<String, String>> zd11TjList;

    private List<HashMap<String, String>> zd13TjList;

    private List<HashMap<String, String>> zd15TjList;

    private List<HashMap<String, String>> sfbgbTjList;

    private List<HashMap<String, String>> sfsfsTjList;

    private List<HashMap<String, String>> xsfsfsTjList;

    private List<HashMap<String, String>> zd17TjList;

    private List<HashMap<String, String>> zd16TjList;

    private List<HashMap<String, String>> zd21TjList;

    private List<HashMap<String, String>> rxfsTjList;

    private List<HashMap<String, String>> sfpkxTjList;

    private List<HashMap<String, String>> cclxTjList;

    private List<HashMap<String, String>> qqlxTjList;

    private List<HashMap<String, String>> ffztTjList;

    private List<HashMap<String, String>> yxztTjList;

    private List<HashMap<String, String>> yyzcTjList;

    private List<HashMap<String, String>> sfgmbxTjList;

    private List<HashMap<String,String>> czTjList;


    /**
     * 学籍类别
     */
    private List<HashMap<String, String>> xjlbTjList;

    /**
     * 学籍类别名称
     */
    private List<HashMap<String, String>> xjlbmcTjList;

    /**
     * 部门类别
     */
    private List<HashMap<String, String>> bmlbTjList;

    // ====================学生信息 end=================================

    // ====================日常事务 =================================
    // 是否通知条件列表
    private List<HashMap<String, String>> sftzTjList;

    private List<HashMap<String, String>> sqjgTjList;

    private List<HashMap<String, String>> qjlxTjList;

    private List<HashMap<String, String>> qjlbTjList;

    private List<HashMap<String, String>> fyffXmTjList;

    private List<HashMap<String, String>> txhdlbTjList;

    private List<HashMap<String, String>> lstdLyTjList;

    // ====================日常事务 end=================================


    // ====================上海海洋-爱心超市=====================
    private List<HashMap<String, String>> wplbTjList;
    private List<HashMap<String, String>> wpmcTjList;


    // ====================上海海洋-爱心超市END=====================

    // ====================重庆三峡-假期留校名单维护记录=============
    private List<HashMap<String, String>> czlxTjList;

    private List<HashMap<String, String>> dwlbTjList;
    private List<HashMap<String, String>> gzxzTjList;

    private List<HashMap<String, String>> xsgbzwTjList;//学生干部职务

    private List<HashMap<String, String>> jldjTjList;
    private List<HashMap<String, String>> pmTjList;
    private List<HashMap<String, String>> sfkhtgTjList;
    private List<HashMap<String, String>> jbTjList;
    private List<HashMap<String, String>> thlxTjList;
    private List<HashMap<String, String>> sfzdgzTjList;
    private List<HashMap<String, String>> jfxzTjList;
    private List<HashMap<String, String>> bzTjList;
    private List<HashMap<String, String>> jszgztTjList;
    private List<HashMap<String, String>> sfzgTjList;
    private List<HashMap<String, String>> sfzbTjList;
    private List<HashMap<String, String>> sblxTjList;
    private List<HashMap<String, Object>> zybjTjList;
    private List<HashMap<String, String>> jjlxdmTjList;
    private List<HashMap<String, String>> sfzdgzxsTjList;

    public List<HashMap<String, String>> getSfzdgzxsTjList() {
        return sfzdgzxsTjList;
    }

    public void setSfzdgzxsTjList() {
        DAO dao = DAO.getInstance();
        String[] mc = {"是", "否"};
        String[] dm = {"1", "0"};
        this.sfzdgzxsTjList = dao.arrayToList(dm, mc);
    }

    public List<HashMap<String, String>> getJjlxdmTjList() {
        return jjlxdmTjList;
    }

    public void setJjlxdmTjList() {
        DAO dao = DAO.getInstance();
        StringBuilder sql = new StringBuilder();
        sql.append("select * from XG_GYGL_ZCLXDBB ");
        this.jjlxdmTjList = dao.getList(sql.toString(), new String[]{}, new String[]{"dm", "mc"});
    }

    public List<HashMap<String, Object>> getZybjTjList() {
        return zybjTjList;
    }

    public void setZybjTjList(List<HashMap<String, Object>> zybjTjList) {
        this.zybjTjList = zybjTjList;
    }

    public List<HashMap<String, String>> getSblxTjList() {
        return sblxTjList;
    }

    public void setSblxTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"学风示范", "卫生示范", "党员示范", "文明示范"};
        String[] mc = {"学风示范", "卫生示范", "党员示范", "文明示范"};
        this.sblxTjList = dao.arrayToList(dm, mc);
    }

    public List<HashMap<String, String>> getSfzbTjList() {
        return sfzbTjList;
    }

    public void setSfzbTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"是", "否"};
        String[] mc = {"是", "否"};
        this.sfzbTjList = dao.arrayToList(dm, mc);
    }

    public List<HashMap<String, String>> getSfzgTjList() {
        return sfzgTjList;
    }

    public void setSfzgTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"是", "否"};
        String[] mc = {"是", "否"};
        this.sfzgTjList = dao.arrayToList(dm, mc);
    }

    public List<HashMap<String, String>> getJszgztTjList() {
        return jszgztTjList;
    }

    public void setJszgztTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"在岗", "离岗", "退休"};
        String[] mc = {"在岗", "离岗", "退休"};
        this.jszgztTjList = dao.arrayToList(dm, mc);
    }

    public List<HashMap<String, String>> getBzTjList() {
        return bzTjList;
    }

    public void setBzTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"辅导员(教师)", "专业教师", "职员", "专业技术人员"};
        String[] mc = {"辅导员(教师)", "专业教师", "职员", "专业技术人员"};
        this.bzTjList = dao.arrayToList(dm, mc);
    }

    public List<HashMap<String, String>> getJfxzTjList() {
        return jfxzTjList;
    }

    public void setJfxzTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"01", "02", "03"};
        String[] mc = {"实地", "电话", "微信"};
        this.jfxzTjList = dao.arrayToList(dm, mc);
    }

    public List<HashMap<String, String>> getSfzdgzTjList() {
        return sfzdgzTjList;
    }

    public void setSfzdgzTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"1", "0"};
        String[] mc = {"是", "否"};
        this.sfzdgzTjList = dao.arrayToList(dm, mc);
    }

    public List<HashMap<String, String>> getThlxTjList() {
        return thlxTjList;
    }

    public void setThlxTjList() {
        DAO dao = DAO.getInstance();
        String sql = "select lxdm dm,lxmc mc from XG_SZDW_THJL_THLX ";
        List<HashMap<String, String>> list = dao.getList(sql, new String[]{}, new String[]{"dm", "mc"});
        this.thlxTjList = list;
    }

    public List<HashMap<String, String>> getJbTjList() {
        return jbTjList;
    }

    public void setJbTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"01", "02"};
        String[] mc = {"校级", "省级"};
        this.jbTjList = dao.arrayToList(dm, mc);
    }

    public List<HashMap<String, String>> getSfkhtgTjList() {
        return sfkhtgTjList;
    }

    public void setSfkhtgTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"1", "0"};
        String[] mc = {"通过", "未通过"};
        this.sfkhtgTjList = dao.arrayToList(dm, mc);
    }

    public List<HashMap<String, String>> getPmTjList() {
        return pmTjList;
    }

    public void setPmTjList() {
        DAO dao = DAO.getInstance();
        StringBuilder sql = new StringBuilder();
        sql.append("select dm,mc from szdw_fdy_pmdmb");
        List<HashMap<String, String>> list = dao.getList(sql.toString(), new String[]{}, new String[]{"dm", "mc"});
        this.pmTjList = list;
    }

    public List<HashMap<String, String>> getJldjTjList() {
        return jldjTjList;
    }

    public void setJldjTjList() {
        DAO dao = DAO.getInstance();
        StringBuilder sql = new StringBuilder();
        sql.append("select dm,mc from szdw_fdy_jxdjdmb");
        List<HashMap<String, String>> list = dao.getList(sql.toString(), new String[]{}, new String[]{"dm", "mc"});
        this.jldjTjList = list;
    }

    public List<HashMap<String, String>> getSfgmbxTjList() {
        return sfgmbxTjList;
    }

    public void setSfgmbxTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"否", "是"};
        String[] mc = {"否", "是"};
        this.sfgmbxTjList = dao.arrayToList(dm, mc);
    }

    public List<HashMap<String, String>> getGzxzTjList() {
        return gzxzTjList;
    }

    public void setGzxzTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"0", "1"};
        String[] mc = {"临时", "正式"};
        this.gzxzTjList = dao.arrayToList(dm, mc);
    }

    public List<HashMap<String, String>> getGwlxTjList() {
        return gwlxTjList;
    }

    public void setGwlxTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"0", "1"};
        String[] mc = {"临时", "长期"};
        this.gwlxTjList = dao.arrayToList(dm, mc);
    }

    private List<HashMap<String, String>> gwlxTjList;

    public List<HashMap<String, String>> getDwlbTjList() {
        return dwlbTjList;
    }

    public void setDwlbTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"01", "02"};
        String[] mc = {"校内单位", "校外企业"};
        this.dwlbTjList = dao.arrayToList(dm, mc);
    }

    public void setCzlxTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"1", "2", "3"};
        String[] mc = {"增加", "修改", "删除"};
        this.czlxTjList = dao.arrayToList(dm, mc);
    }

    public List<HashMap<String, String>> getCzlxTjList() {
        return czlxTjList;
    }

    // ====================重庆三峡-假期留校名单维护记录END=====================

    // ====================新疆工业职业技术学院=====================
    //学校情况
    private List<HashMap<String, String>> xxqkTjList;

    public void setXxqkTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"顶岗实习", "在校学校", "其他"};
        String[] mc = {"顶岗实习", "在校学校", "其他"};
        this.xxqkTjList = dao.arrayToList(dm, mc);
    }

    public List<HashMap<String, String>> getXxqkTjList() {
        return xxqkTjList;
    }

    //请假
    private List<HashMap<String, String>> qjTjList;

    public void setQjTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"事假", "病假", "其他"};
        String[] mc = {"事假", "病假", "其他"};
        this.qjTjList = dao.arrayToList(dm, mc);
    }

    public List<HashMap<String, String>> getQjTjList() {
        return qjTjList;
    }

    //住宿情况
    private List<HashMap<String, String>> zsqkTjList;

    public void setZsqkTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"住宿", "走读"};
        String[] mc = {"住宿", "走读"};
        this.zsqkTjList = dao.arrayToList(dm, mc);
    }

    public List<HashMap<String, String>> getZsqkTjList() {
        return zsqkTjList;
    }

    //住宿请假
    private List<HashMap<String, String>> zsqjTjList;

    public void setZsqjTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"是", "否"};
        String[] mc = {"是", "否"};
        this.zsqjTjList = dao.arrayToList(dm, mc);
    }

    public List<HashMap<String, String>> getZsqjTjList() {
        return zsqjTjList;
    }

    //金华职业-文明寝室-寝室考勤类别
    private List<HashMap<String, String>> qskqlbTjList;

    public void setQskqlbTjList() {
        DAO dao = DAO.getInstance();
        StringBuilder sql = new StringBuilder();
        sql.append(" select distinct dm ,mc");
        sql.append(" from xg_gygl_qskq_qskqlbdmb ");
        List<HashMap<String, String>> qskqlbTjList = dao.getList(sql.toString(), new String[]{}, new String[]{"dm", "mc"});
        this.qskqlbTjList = qskqlbTjList;
    }

    public List<HashMap<String, String>> getQskqlbTjList() {
        return qskqlbTjList;
    }

    // ====================新疆工业职业技术学院=====================
    // ====================素质拓展（new）=====================
    private List<HashMap<String, String>> xmjbTjList;
    private List<HashMap<String, String>> sskmTjList;


    // ====================素质拓展（new)END=====================
    // 公寓员工
    private List<HashMap<String, String>> gyzgztTjList;
    // ====================评奖评优 =================================
    // 是否评分条件列表
    private List<HashMap<String, String>> sfpfTjList;

    // 是否确认条件列表
    private List<HashMap<String, String>> sfqrTjList;

    // 是否审核条件列表
    private List<HashMap<String, String>> sfshTjList;

    // 是否提交条件列表
    private List<HashMap<String, String>> sftjTjList;

    // 审核状态3
    private List<HashMap<String, String>> shzt3List;

    // 评奖项目
    private List<HashMap<String, String>> xmdmList;

    // 项目类型列表
    private List<HashMap<String, String>> xmlxTjList;

    // 项目性质列表
    private List<HashMap<String, String>> xmxzTjList;

    // 综测周期
    private List<HashMap<String, String>> zczqTjList;

    // 评奖周期
    private List<HashMap<String, String>> pjzqTjList;

    // 评奖历史项目
    private List<HashMap<String, String>> pjlsxmTjList;

    // 评奖申请项目
    private List<HashMap<String, String>> pjsqxmTjlist;

    // 评奖申请项目
    private List<HashMap<String, String>> jxsqzdTjlist;

    // 获得荣誉
    private List<HashMap<String, String>> hdryTjList;

    // 特殊学生类型
    private List<HashMap<String, String>> lxdmTjList;

    // 评奖项目名称
    private List<HashMap<String, Object>> xmmcTjList;

    // 新评奖项目类型
    private List<HashMap<String, Object>> xxmlxTjList;

    // 新项目性质
    private List<HashMap<String, Object>> xxmxzTjList;

    // 谈话记录-关注等级
    private List<HashMap<String, String>> thjlGzdjTjList;

    // =====================金华职业学生资助============================
    private List<HashMap<String, String>> jhshztTjList;
    private List<HashMap<String, String>> tjdcTjList;
    private List<HashMap<String, String>> zxjtjdcTjList;
    // ====================评奖评优 end=================================

    // ====================评奖评优 begin=================================
    // 违纪处分类别
    private List<HashMap<String, String>> cflbTjList;

    // 违纪处分原因
    private List<HashMap<String, String>> cfyyTjList;

    // 违纪处分类别
    private List<HashMap<String, String>> cflbmcTjList;

    // 违纪处分原因
    private List<HashMap<String, String>> cfyymcTjList;

    // 申述结果
    private List<HashMap<String, String>> ssjgTjList;

    // 奖项类别
    private List<HashMap<String, String>> jxlbTjList;

    // 竞赛方式
    private List<HashMap<String, String>> jsfsTjList;

    // 奖项等级
    private List<HashMap<String, String>> jxdjTjList;

    // 奖项名次
    private List<HashMap<String, String>> hjjxmcTjList;

    // ====================评奖评优 end=================================

    // ====================公寓管理 =================================
    // 校区条件列表
    private List<HashMap<String, Object>> xqdmTjList;

    // 学生类别条件列表
    private List<HashMap<String, String>> xslbTjList;

    // 园区条件列表
    private List<HashMap<String, Object>> yqdmTjList;

    // 楼栋条件列表
    private List<HashMap<String, Object>> ldTjList;

    // 层号条件列表
    private List<HashMap<String, Object>> chTjList;

    // 寝室号条件列表
    private List<HashMap<String, Object>> qshTjList;

    // 寝室性别条件列表
    private List<HashMap<String, String>> qsxbTjList;

    // 退宿原因条件列表
    private List<HashMap<String, String>> tsyyTjList;

    // 是否入住条件列表
    private List<HashMap<String, String>> sfrzTjList;

    // 是否分配条件列表
    private List<HashMap<String, String>> sffpTjList;

    // 楼栋性别条件列表
    private List<HashMap<String, String>> ldxbTjList;

    // 紧急程度条件列表
    private List<HashMap<String, String>> jjcdTjList;

    // 报修类别条件列表
    private List<HashMap<String, String>> gybxlbTjList;

    // 处理状态条件列表
    private List<HashMap<String, String>> clztTjList;

    // 处理状态代码条件列表
    private List<HashMap<String, String>> clztdmTjList;

    // 值班人员条件List
    private List<HashMap<String, String>> zbryTjList;

    // 寝室入住情况条件列表
    private List<HashMap<String, String>> rzqkTjList;

    // 公寓管理类型条件列表
    private List<HashMap<String, String>> gygllxTjList;

    // 公寓纪律类别大类代码条件列表

    private List<HashMap<String, Object>> gyjllbdldmTjList;

    // 公寓纪律类别代码
    private List<HashMap<String, Object>> gyjllbdmTjList;

    private List<HashMap<String, String>> gyjlcflbTjList;

    private List<HashMap<String, String>> gyyjxfkqkTjList;

    private List<HashMap<String, String>> gyyjflTjList;

    private List<HashMap<String, String>> pylbTjList;

    private List<HashMap<String, String>> xljkgxlxTjList;
    private List<HashMap<String, String>> xljkgzlxTjList;

    // 异动类型
    private List<HashMap<String, String>> ydlxTjList;
    // 异动类型结果
    private List<HashMap<String, String>> ydlxjgTjList;

    // 卫生检查类型
    private List<HashMap<String, String>> jclxTjList;

    // ====================公寓管理 end=================================

    // ====================党团建设 =================================
    // 党团建设阶段条件列表
    private List<HashMap<String, String>> jddmTjList;

    // ====================党团建设end =================================

    // ====================思政队伍 begin===============================
    private List<HashMap<String, String>> zwTjList;
    private List<HashMap<String, String>> zzmmTjList;
    private List<HashMap<String, String>> xlTjList;
    private List<HashMap<String, String>> xwTjList;
    private List<HashMap<String, String>> zcTjList;
    private List<HashMap<String, String>> yhsfTjList;// 用户身份
    private List<HashMap<String, String>> bbztTjList;// 编班状态
    // ====================思政队伍 end===============================

    // ====================学生资助 begin==============================
    private List<HashMap<String, String>> shzt4TjList;

    private List<HashMap<String, String>> shzt5TjList;

    private List<HashMap<String, String>> zzxmlbTjList;
    // ====================学生资助 end==============================

    // ====================勤工助学 start=================================
    private List<HashMap<String, String>> gwztTjList;
    private List<HashMap<String, String>> gwxzTjList;
    private List<HashMap<String, String>> zgztTjList;
    private List<HashMap<String, Object>> qgbmTjList;
    private List<HashMap<String, String>> sfknsTjList;
    private List<HashMap<String, String>> qgshztTjList;
    private List<HashMap<String, String>> qgxssqTjList;
    private List<HashMap<String, String>> qgsybmTjList;
    private List<HashMap<String, String>> qgsybmsqTjList;
    // ====================勤工助学 end===================================

    // ====================心理健康咨询start==============================

    private List<HashMap<String, String>> zgstatusTjList;

    private List<HashMap<String, String>> weekdayTjList;

    private List<HashMap<String, String>> yystatusTjList;

    private List<HashMap<String, String>> gzztTjList;

    private List<HashMap<String, String>> ybqkTjList;
    // ====================心理健康咨询end==============================

    // ====================军训管理 start=================================
    private List<HashMap<String, String>> jxnjTjList;
    private List<HashMap<String, String>> jxztTjList;
    private List<HashMap<String, String>> cxqkTjList;
    private List<HashMap<String, String>> jxxxTjList;
    private List<HashMap<String, String>> bxlbTjList;
    private List<HashMap<String, String>> jtbxTjList;
    private List<HashMap<String, String>> sfhdTjList;

    private List<HashMap<String, String>> sfybzTjList;// 是否已编制
    // 军训建制团
    private List<HashMap<String, Object>> tidTjList;
    // 军训建制营
    private List<HashMap<String, Object>> yidTjList;
    // 军训建制连
    private List<HashMap<String, Object>> lidTjList;
    // 军训建制排
    private List<HashMap<String, Object>> pidTjList;
    // 军训建制班
    private List<HashMap<String, Object>> bidTjList;
    // 军训建制宿舍
    private List<HashMap<String, Object>> ssidTjList;
    // 军训代码
    private List<HashMap<String, String>> jxdmList;
    // ====================军训管理 end===================================

    // =====================入伍管理===========================//
    private List<HashMap<String, String>> rwfsTjList;
    // =====================入伍管理===========================//
    //======================

    // ====================日常行为 start=================================
    private List<HashMap<String, String>> rcxwshztTjList;
    private List<HashMap<String, String>> rcxwdlTjList;
    private List<HashMap<String, String>> rcxwlbTjList;
    // ====================日常行为 end=================================

    private List<HashMap<String, String>> kycxxmlbTjList;

    private List<HashMap<String, String>> wjcddmTjList;

    private List<HashMap<String, String>> wjgabzTjList;

    private List<HashMap<String, String>> pjztTjList;

    private List<HashMap<String, String>> zxztnewTjList;

    private List<HashMap<String, String>> rcxwlbnewTjList;
    private List<HashMap<String, String>> rcxwdlnewTjList;
    private List<HashMap<String, String>> rcxwxlnewTjList;

    private List<HashMap<String, String>> zxbkTjList;

    private List<HashMap<String, String>> jydwxzTjList;
    private List<HashMap<String, String>> jyxsTjList;
    private List<HashMap<String, String>> sshyTjList;
    private List<HashMap<String, String>> pxlxTjList;
    private List<HashMap<String, String>> gslxTjList;

    private List<HashMap<String, String>> hfztTjList;

    // ====================日常行为 end===================================
    // =====================潍坊学院：==========================
    // 公寓物品维护
    private List<HashMap<String, String>> gywpdlTjList;// 物品大类
    private List<HashMap<String, String>> gywplbTjList;// 物品类别
    // 津贴发放
    private List<HashMap<String, String>> bzlxTjList;// 补助类型
    // =====================潍坊学院：==========================

    // ====================学校个性化 start=================================
    // --上海出版印刷--20111108--zhanghui--start--------------------------
    // 家庭困难条件列表
    private List<HashMap<String, String>> jtknlxTjList;
    // --上海出版印刷--20111108--zhanghui--end----------------------------

    // ====================学校个性化 start=================================
    // --浙江大学--20160528--start--------------------------
    // 困难生申请性质
    private List<HashMap<String, String>> knssqxzTjList;
    // --浙江大学--20160528--end----------------------------

    // -- 苏州工业园区 -- 20111121 -- qlj begin---------------------------
    private List<HashMap<String, String>> shztlxTjList;
    // -- 苏州工业园区 -- 20111121 -- qlj end---------------------------

    // -- 贵州大学【公寓管理-文明寝室】 -- 20120401 -- zh begin-----------
    // 文明寝室审核状态
    private List<HashMap<String, String>> shzt1TjList;

    //浙江警察学院【大队维护】大队条件
    private List<HashMap<String, String>> zjjcDdTjList;

    //浙江商业【考勤管理】周次条件
    private List<HashMap<String, String>> zjsyzcTjList;

    private List<HashMap<String, String>> shzt2TjList;
    // -- 贵州大学【公寓管理-文明寝室】 -- 20120401 -- zh end-----------

    // --华师大学期注册状态--begin//
    private List<HashMap<String, String>> zcztTjList;
    // --华师大学期注册状态--end//

    // --华师大学年小结状态--begin//
    private List<HashMap<String, String>> txztTjList;
    // --华师大学年小结状态--end//

    //浙江旅游医疗保险参续保状态
    private List<HashMap<String, String>> cxblbTjList;

    //浙江旅游医疗保险餐续保状态
    private List<HashMap<String, String>> shbzTjList;

    // ====================学校个性化 end=================================

    // 学生信息强制修改状态
    private List<HashMap<String, String>> qzxgztTjList;

    private List<HashMap<String, String>> cxdjdmTjList;

    private List<HashMap<String, String>> qjlxdmTjList;
    // 请假管理
    private List<HashMap<String, String>> xjztTjList;

    private List<HashMap<String, String>> qjztTjList;

    // ====================学生补办start=================================
    private List<HashMap<String, String>> xszbblxTjList;
    private List<HashMap<String, String>> sfbbhcyhkTjList;
    // ====================日常行为 end=================================

    // ====================学生补办start=================================
    private List<HashMap<String, String>> cbzkmcTjList;
    // ====================日常行为 end===================================
    // 分班管理
    private List<HashMap<String, String>> xhqkTjList;
    private List<HashMap<String, String>> fbqkTjList;
    private List<HashMap<String, String>> syztTjList;
    private List<HashMap<String, String>> sfnzTjList;
    private List<HashMap<String, String>> gzlxTjList;


    // ====================学生行为考核---》基本分管理==========================
    private List<HashMap<String, String>> bzrcpdjTjList;
    private List<HashMap<String, String>> xscpdjTjList;

    private List<HashMap<String, String>> ysjxjxmlxTjList;//浙江大学.院设奖学金项目类型

    //北京中医药-个人获奖附件上传-奖项级别高级查询条件
    private List<HashMap<String, String>> jxjbTjList;
    //北京中医药-个人获奖附件上传-奖项类别高级查询条件
    private List<HashMap<String, String>> jxlbnewTjList;
    //上海体育学院新增四个高级查询条件
    private List<HashMap<String, String>> lydqTjList;
    private List<HashMap<String, String>> xqahTjList;
    private List<HashMap<String, String>> drzwTjList;
    private List<HashMap<String, String>> sfqzTjList;
    private List<HashMap<String, String>> fkztTjList;

    //西藏民族大学-交通工具高级查询条件
    private List<HashMap<String, String>> jtgjTjList;
    private List<HashMap<String, String>> sfbmTjList;
    private List<HashMap<String, String>> zcfsTjList;
    private List<HashMap<String, String>> dazcxxTjList;

    //温州大学待岗学生 状态
    private List<HashMap<String, String>> ztTjList;

    public List<HashMap<String, String>> getZtTjList() {
        return ztTjList;
    }

    public void setZtTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"3", "5", "-5"};
        String[] mc = {"已退回", "审核中", "待岗中"};
        this.ztTjList = dao.arrayToList(dm, mc);
    }

    private List<HashMap<String, String>> zwlxTjList;

    public List<HashMap<String, String>> getZwlxTjList() {
        return zwlxTjList;
    }

    public void setZwlxTjList() {
        DAO dao = DAO.getInstance();
        StringBuilder sql = new StringBuilder();
        sql.append(" select distinct lxmc dm ,lxmc mc");
        sql.append(" from xg_szdw_xsgb_zwlxb ");
        List<HashMap<String, String>> zwlxTjList = dao.getList(sql.toString(), new String[]{}, new String[]{"dm", "mc"});
        this.zwlxTjList = zwlxTjList;

    }

    //苏州卫生保险类型
    private List<HashMap<String, String>> bxlxTjList;

    /**
     * @return the bxlxTjList
     */
    public List<HashMap<String, String>> getBxlxTjList() {
        return bxlxTjList;
    }

    /**
     * @param bxlxTjList要设置的 bxlxTjList
     */
    public void setBxlxTjList() {

        DAO dao = DAO.getInstance();
        StringBuilder sql = new StringBuilder();
        sql.append(" select distinct bxlxmc dm ,bxlxmc mc");
        sql.append(" from rcsw_ylbx_bxlxb ");
        List<HashMap<String, String>> bxlxTjList = dao.getList(sql.toString(), new String[]{}, new String[]{"dm", "mc"});
        this.bxlxTjList = bxlxTjList;

    }

    public List<HashMap<String, String>> getXsgbzwTjList() {
        return xsgbzwTjList;
    }

    public void setXsgbzwTjList() {
        DAO dao = DAO.getInstance();
        StringBuilder sql = new StringBuilder();
        sql.append("select zwid dm,zwmc mc from xg_szdw_xsgb_zwb ");
        List<HashMap<String, String>> xsgbzwList = dao.getList(sql.toString(), new String[]{}, new String[]{"dm", "mc"});
        this.xsgbzwTjList = xsgbzwList;
    }

    //江苏吴中中专
    private List<HashMap<String, String>> sfsxztTjList;
    //西安交通大学
    private List<HashMap<String, String>> hdxsTjList;
    private List<HashMap<String, String>> stztTjList;
    private List<HashMap<String, String>> hdzlTjList;
    private List<HashMap<String, String>> sfdbTjList;
    private List<HashMap<String, String>> kcxzTjList;
    private List<HashMap<String, String>> fsbjTjList;

    public List<HashMap<String, String>> getFsbjTjList() {
        return fsbjTjList;
    }

    public void setFsbjTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"已粉刷", "未粉刷"};
        String[] mc = {"已粉刷", "未粉刷"};
        this.fsbjTjList = dao.arrayToList(dm, mc);
    }

    public List<HashMap<String, String>> getKcxzTjList() {
        return kcxzTjList;
    }

    public void setKcxzTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"必修", "选修"};
        String[] mc = {"必修", "选修"};
        this.kcxzTjList = dao.arrayToList(dm, mc);
    }

    public List<HashMap<String, String>> getSfdbTjList() {
        return sfdbTjList;
    }

    public void setSfdbTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"是", "否"};
        String[] mc = {"是", "否"};
        this.sfdbTjList = dao.arrayToList(dm, mc);
    }

    private List<HashMap<String, String>> hdjxztTjList;

    public List<HashMap<String, String>> getSfbmTjList() {
        return sfbmTjList;
    }

    public void setSfbmTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"1", "0"};
        String[] mc = {"已报名", "未报名"};
        this.sfbmTjList = dao.arrayToList(dm, mc);
    }

    public List<HashMap<String, String>> getZcfsTjList() {
        return zcfsTjList;
    }

    public void setZcfsTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"1", "2"};
        String[] mc = {"邮寄", "自带"};
        this.zcfsTjList = dao.arrayToList(dm, mc);
    }

    public List<HashMap<String, String>> getDazcxxTjList() {
        return dazcxxTjList;
    }

    public void setDazcxxTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"已登记", "已转出", "未登记"};
        String[] mc = {"已登记", "已转出", "未登记"};
        this.dazcxxTjList = dao.arrayToList(dm, mc);
    }

    private List<HashMap<String, String>> smnjTjList;
    private List<HashMap<String, String>> smlxTjList;


    /**
     * @description    ： TODO
     * @author ： CP（1352）
     * @date        ： 2018-3-14 上午09:18:26
     * @return        : the smnjTjList
     */
    public List<HashMap<String, String>> getSmnjTjList() {
        return smnjTjList;
    }

    /**
     * @param ：smnjTjList the smnjTjList to set
     * @description    ： TODO
     * @author ： CP（1352）
     * @date        ： 2018-3-14 上午09:18:26
     */
    public void setSmnjTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"大一", "大二", "大三", "大四"};
        String[] mc = {"大一", "大二", "大三", "大四"};
        this.smnjTjList = dao.arrayToList(dm, mc);
    }

    /**
     * @description    ： TODO
     * @author ： CP（1352）
     * @date        ： 2018-3-14 上午09:18:26
     * @return        : the smlxTjList
     */
    public List<HashMap<String, String>> getSmlxTjList() {
        return smlxTjList;
    }

    /**
     * @param ：smlxTjList the smlxTjList to set
     * @description    ： TODO
     * @author ： CP（1352）
     * @date        ： 2018-3-14 上午09:18:26
     */
    public void setSmlxTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"选读", "必读"};
        String[] mc = {"选读", "必读"};
        this.smlxTjList = dao.arrayToList(dm, mc);
    }

    /*

	/*浙江大学.院设奖学金项目类型*/
    public List<HashMap<String, String>> getYsjxjxmlxTjList() {
        return ysjxjxmlxTjList;
    }

    public void setYsjxjxmlxTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"奖学金", "助学金"};
        String[] mc = {"奖学金", "助学金"};
        this.ysjxjxmlxTjList = dao.arrayToList(dm, mc);
    }

    /*   基本法管理*/
    public List<HashMap<String, String>> getBzrcpdjTjList() {
        return bzrcpdjTjList;
    }

    public void setBzrcpdjTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"优秀", "良好", "合格"};
        String[] mc = {"优秀", "良好", "合格"};
        this.bzrcpdjTjList = dao.arrayToList(dm, mc);
    }

    public List<HashMap<String, String>> getXscpdjTjList() {
        return xscpdjTjList;
    }

    public void setXscpdjTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"优秀", "良好", "合格"};
        String[] mc = {"优秀", "良好", "合格"};
        this.xscpdjTjList = dao.arrayToList(dm, mc);
    }

    // ====================温大心理健康 begin=================================
    private List<HashMap<String, String>> zxsstatusTjList = null;
    private List<HashMap<String, String>> zxszgTjList = null;
    private List<HashMap<String, String>> yyztTjList = null;
    private List<HashMap<String, String>> zxztTjList = null;

    //有无
    private List<HashMap<String, String>> ywTjList = null;
    //周报类型
    private List<HashMap<String, String>> zblxTjList = null;
    //关注等级
    private List<HashMap<String, String>> gzdjTjList = null;
    //心理问题类型
    private List<HashMap<String, String>> xlwtlxTjList = null;
    // ====================温大心理健康 end===================================


    private List<HashMap<String, String>> kqlxTjList;

    // 集体评奖
    private List<HashMap<String, String>> jxmcTjList;

    private List<HashMap<String, String>> ghztList;

    //军训
    private List<HashMap<String, String>> jxkqlxTjList;
    private List<HashMap<String, String>> jxkqlbTjList;


    // ====================社团管理（new）=====================
    private List<HashMap<String, String>> stlbTjList;
    private List<HashMap<String, String>> stxmlbTjList;
    private List<HashMap<String, String>> stxmTjList;

    private List<HashMap<String, String>> ystlbTjList;
    private List<HashMap<String, String>> ystxmlbTjList;
    private List<HashMap<String, String>> ystxmmcTjList;

    private List<HashMap<String, String>> tnztTjList;
    private List<HashMap<String, String>> zjxyTjList;//宗教信仰
    // ====================社团管理（new)END=====================
    // ====================学生户籍管理==========================
    private List<HashMap<String, String>> qyztmcTjList;

    // ====================上海电机学院学生信息新加查询条件=====================
    private List<HashMap<String, String>> yqmcTjList;
    private List<HashMap<String, String>> hkxzTjList;
    //辽宁机电新加查询条件  户口性质
    private List<HashMap<String, String>> lnjdhkTjList;
    /*浙江旅游请假类型*/
    private List<HashMap<String, String>> qjlxmcTjList = null;
    private List<HashMap<String, String>> qjtslxTjList = null;
    private List<HashMap<String, String>> qjksjcTjList = null;
    private List<HashMap<String, String>> qjjsjcTjList = null;
    private List<HashMap<String, String>> kcmcTjList = null;
    private String username = null;
    private List<HashMap<String, String>> bxxsTjList;//办学形式
    /*北京中医药大学岗位高级查询条件*/
    private List<HashMap<String, String>> gwTjList = null;
    /*浙江传媒提交类型*/
    private List<HashMap<String, String>> zjcmtjztTjList = null;
    //加扣分类别
    private List<HashMap<String, String>> lbTjList = null;
    //奖惩类别
    private List<HashMap<String, String>> jclbTjList = null;
    //活动频率
    private List<HashMap<String, String>> hdplTjList = null;
    //专职辅导员
    private List<HashMap<String, String>> zjzTjList = null;
    //审定状态
    private List<HashMap<String, String>> sdztTjList = null;
    //浙江旅游综合分导入审定状态
    private List<HashMap<String, String>> zjlysdztTjList = null;
    //浙江旅游综合分审核审定状态
    private List<HashMap<String, String>> zjlyshsdztTjList = null;
    //计分项目代码
    private List<HashMap<String, String>> jfxmdmTjList = null;
    //项目模块代码
    private List<HashMap<String, String>> xmmkdmTjList = null;
    //项目类型
    private List<HashMap<String, String>> csmsTjList = null;
    //认定状态
    private List<HashMap<String, String>> rdztTjList = null;
    //考生类别
    private List<HashMap<String, String>> KslbTjList = null;
    /**
     *
     * *************************************************************************
     * ****************************************************
     */
    /**
     * ************************************************通用条件*********************
     * ****************************
     */
    /**
     * *************************************************************************
     * ****************************************************
     */

    private List<HashMap<String, String>> czrTjList = null;
    private List<HashMap<String, String>> qgrqTjList = null;
    private List<HashMap<String, String>> qgmxTjList = null;
    //上海戏剧 评奖评优-综测维护-其他奖项设置 奖项代码 高级查询条件
    private List<HashMap<String, String>> jxdmxTjList = null;

    //日常事务-活动管理-活动类型
    private List<HashMap<String, String>> hdlxTjList = null;
    //季度
    private List<HashMap<String, String>> jdTjList = null;
    //浙大-勤工助学，岗位类别
    private List<HashMap<String, String>> gwlbTjList = null;
    //浙大-勤工助学，岗位性质
    private List<HashMap<String, String>> xiaoqTjList = null;
    //浙大-学习评价，短学期
    private List<HashMap<String, String>> dxqTjList = null;
    //政治面貌新[党组织关系转出-华科大武昌分校]
    private List<HashMap<String, String>> zzmmnewTjList = null;
    //是否省内[党组织关系转出-华科大武昌分校]
    private List<HashMap<String, String>> sfsnTjList = null;
    //是否省内[党组织关系转出-华科大武昌分校]
    private List<HashMap<String, String>> szdzbTjList = null;
    //重庆移通 素质拓展 板块归属
    private List<HashMap<String, String>> bkgsTjList = null;
    //开、关
    private List<HashMap<String, String>> kgTjList = null;
    //是否需要参加约谈
    private List<HashMap<String, String>> sfxyytTjList = null;
    //是否已约谈
    private List<HashMap<String, String>> sfyytTjList = null;
    /*心理健康咨询-谈话记录-特殊学生维护，（困难类型）【西安科技大学个性化，预警程度】*/
    private List<HashMap<String, String>> gfqkflTjList = null;//西安科技大个性化  国防情况分类

    /*南通科技kslb*/
    private List<HashMap<String, String>> kslbTjList = null;

    //心理健康咨询-咨询师管理-咨询师维护，（擅长领域）【湖南城市学院】
    private List<HashMap<String, String>> sclyTjList = null;

    private List<HashMap<String, String>> knlxTjList = null;
    //角色类型【金华职业-文明寝室】
    private List<HashMap<String, String>> jsTjList = null;
    //检查类型【金华职业-文明寝室】
    private List<HashMap<String, String>> jjlxTjList = null;
    //提交状态【金华职业-文明寝室】
    private List<HashMap<String, String>> tjztwpqsTjList = null;

    private List<HashMap<String, String>> yxzt1TjList = null;

    private List<HashMap<String, String>> xmlbTjList = null;
    private List<HashMap<String, String>> rcswbxlbTjList;
    private List<HashMap<String, String>> bxsfblTjList;
    private List<HashMap<String, String>> xmdlTjList = null;
    private List<HashMap<String, String>> fwjgTjList = null;
    private List<HashMap<String, String>> fwlxTjList = null;
    private List<HashMap<String, String>> pcjgTjList = null;
    private List<HashMap<String, String>> sfgzTjList = null;

    //江西航空职业技术学院 保险报销
    //类型
    private List<HashMap<String, String>> lxnewTjList = null;
    //保险性质
    private List<HashMap<String, String>> bxxzTjList = null;
    //治疗原因
    private List<HashMap<String, String>> clzbTjList = null;

    //湘潭大学 是否留校过春节
    private List<HashMap<String, String>> sfgcjTjList = null;

    //重庆信息档案管理学籍状态
    private List<HashMap<String, String>> cqxxxjztTjList = null;

    //西北工业大学 班级学风建设类型
    private List<HashMap<String, String>> bjsblxTjList;

    public List<HashMap<String, String>> getBjsblxTjList() {
        return bjsblxTjList;
    }

    public void setBjsblxTjList() {
        StringBuffer sql = new StringBuffer();
        sql.append("select SBLXDM dm,SBLXMC mc from xg_sxzzjy_bjxfjs_sblxdmb order by mc asc ");
        this.bjsblxTjList = DAO.getInstance().getListNotOut(sql.toString(),
                new String[]{});
    }

    //西北工业大学 个人学风建设类型
    private List<HashMap<String, String>> grsblxTjList;

    public List<HashMap<String, String>> getGrsblxTjList() {
        return grsblxTjList;
    }

    public void setGrsblxTjList() {
        StringBuffer sql = new StringBuffer();
        sql.append("select SBLXDM dm,SBLXMC mc from xg_sxzzjy_grxfjs_sblxdmb order by mc asc ");
        this.grsblxTjList = DAO.getInstance().getListNotOut(sql.toString(),
                new String[]{});
    }

    //西北工业大学 智慧党建活动类型
    private List<HashMap<String, String>> hdlx10699TjList;

    public List<HashMap<String, String>> getHdlx10699TjList() {
        return hdlx10699TjList;
    }

    public void setHdlx10699TjList() {
        StringBuffer sql = new StringBuffer();
        sql.append("select dm,mc from ZHDJ_HDLXB order by mc desc ");
        this.hdlx10699TjList = DAO.getInstance().getListNotOut(sql.toString(),
                new String[]{});
    }

    //西北工业大学 校园明星榜中榜类型
    private List<HashMap<String, String>> newstypeTjList;

    public List<HashMap<String, String>> getNewstypeTjList() {
        return newstypeTjList;
    }

    public void setNewstypeTjList() {
        StringBuffer sql = new StringBuffer();
        sql.append("select typedm dm,typemc mc from xg_sxzzjy_xymxbzb_type order by typexh ");
        this.newstypeTjList = DAO.getInstance().getListNotOut(sql.toString(),
                new String[]{});
    }


    //西北工业大学 班风
    private List<HashMap<String, String>> drhdTjList;

    public List<HashMap<String, String>> getDrhdTjList() {
        return drhdTjList;
    }

    public void setDrhdTjList() {
        StringBuffer sql = new StringBuffer();
        sql.append("select dm,mc from ZHDJ_DRHDLBB order by mc desc ");
        this.drhdTjList = DAO.getInstance().getListNotOut(sql.toString(),
                new String[]{});
    }

    /**
     * @return the cqxx_xjztTjList
     */
    public List<HashMap<String, String>> getCqxxxjztTjList() {
        return cqxxxjztTjList;
    }

    /**
     * @param cqxxXjztTjList要设置的 cqxx_xjztTjList
     */
    public void setCqxxxjztTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = new String[]{"已毕业", "未达到毕业条件", "退学", "休学", "当兵", "其他"};
        String[] mc = new String[]{"已毕业", "未达到毕业条件", "退学", "休学", "当兵", "其他"};
        this.cqxxxjztTjList = dao.arrayToList(dm, mc);
    }

    /**
     * @return the czrTjList
     */
    public List<HashMap<String, String>> getCzrTjList() {
        return czrTjList;
    }

    /**
     * @param czrTjList要设置的 czrTjList
     */
    public void setCzrTjList(List<HashMap<String, String>> czrTjList) {

        DAO dao = DAO.getInstance();
        StringBuilder sql = new StringBuilder();
        sql.append(" select distinct czr czrdm,czr ");
        sql.append(" from xg_xszz_new_knsqxjl order by czrmc ");
        czrTjList = dao.getList(sql.toString(), new String[]{}, new String[]{"czrdm", "czr"});
        this.czrTjList = czrTjList;

    }


    // ======================年度条件列表===============================
    public List<HashMap<String, String>> getNdTjList() {
        return ndTjList;
    }

    public void setNdTjList() {

        List<HashMap<String, String>> list = Base.getXnndList();

        List<HashMap<String, String>> ndList = new ArrayList<HashMap<String, String>>();

        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                HashMap<String, String> map = list.get(i);
                HashMap<String, String> ndMap = new HashMap<String, String>();
                if (!Base.isNull(map.get("nd"))) {
                    ndMap.put("dm", map.get("nd"));
                    ndMap.put("mc", map.get("nd"));

                    ndList.add(ndMap);
                }
            }
        }

        this.ndTjList = ndList;
    }

    // ======================年度条件列表 end===============================

    // ======================学年条件列表===============================
    public List<HashMap<String, String>> getXnTjList() {
        return xnTjList;
    }

    public List<HashMap<String, String>> getRwxnTjList() {
        return rwxnTjList;
    }

    public void setXnTjList() {

        List<HashMap<String, String>> list = Base.getXnndList();

        List<HashMap<String, String>> xnList = new ArrayList<HashMap<String, String>>();

        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                HashMap<String, String> map = list.get(i);
                HashMap<String, String> xnMap = new HashMap<String, String>();
                if (!Base.isNull(map.get("xn"))) {
                    xnMap.put("dm", map.get("xn"));
                    xnMap.put("mc", map.get("xn"));

                    xnList.add(xnMap);
                }
            }
        }

        this.xnTjList = xnList;
    }

    public void setRwxnTjList() {

        List<HashMap<String, String>> list = Base.getXnndList();

        List<HashMap<String, String>> xnList = new ArrayList<HashMap<String, String>>();

        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                HashMap<String, String> map = list.get(i);
                HashMap<String, String> xnMap = new HashMap<String, String>();
                if (!Base.isNull(map.get("xn"))) {
                    xnMap.put("dm", map.get("xn"));
                    xnMap.put("mc", map.get("xn"));

                    xnList.add(xnMap);
                }
            }
        }

        this.rwxnTjList = xnList;
    }

    // ======================学期条件列表===============================
    public List<HashMap<String, String>> getXqTjList() {
        return xqTjList;
    }

    public void setXqTjList() {

        List<HashMap<String, String>> list = Base.getXqList();

        List<HashMap<String, String>> xqList = new ArrayList<HashMap<String, String>>();

        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                HashMap<String, String> map = list.get(i);

                if (!Base.isNull(map.get("xqdm"))) {
                    map.put("dm", map.get("xqdm"));
                    map.put("mc", map.get("xqmc"));

                    xqList.add(map);
                }
            }
        }

        this.xqTjList = xqList;
    }

    // ======================学期条件列表 end===============================

    // ======================学期名称条件列表===============================
    public List<HashMap<String, String>> getXqmcTjList() {
        return xqmcTjList;
    }

    public void setXqmcTjList() {

        List<HashMap<String, String>> list = Base.getXqList();

        List<HashMap<String, String>> xqmcList = new ArrayList<HashMap<String, String>>();

        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                HashMap<String, String> map = list.get(i);
                HashMap<String, String> xqMap = new HashMap<String, String>();
                if (!Base.isNull(map.get("xqmc"))) {
                    xqMap.put("dm", map.get("xqmc"));
                    xqMap.put("mc", map.get("xqmc"));

                    xqmcList.add(xqMap);
                }
            }
        }

        this.xqmcTjList = xqmcList;
    }

    // ======================学期条件列表 end===============================

    // ======================部门条件列表===============================
    public List<HashMap<String, String>> getBmTjList() {
        return bmTjList;
    }

    public void setBmTjList() {
        // SearchTj tjService = new SearchTj();
        // String[] PY_BIG = SearchService.PY_BIG;
        // List<HashMap<String, Object>> bmList =
        // tjService.setNewBmListBy(PY_BIG);

        DAO dao = DAO.getInstance();

        // 部门信息
        String sql = "select distinct bmdm dm,bmmc mc from zxbz_xxbmdm where bmmc is not null order by bmmc";

        List<HashMap<String, String>> bmList = dao.getList(sql,
                new String[]{}, new String[]{"dm", "mc"});

        this.bmTjList = bmList;
    }

    // ======================部门条件列表 end===============================

    // ======================是否条件列表===============================
    public List<HashMap<String, String>> getSfTjList() {
        return sfTjList;
    }

    public void setSfTjList() {
        // 是否列表
        List<HashMap<String, String>> sfList = getCommSelectList("sf");
        this.sfTjList = sfList;
    }

    public List<HashMap<String, String>> getSflxTjList() {
        return sflxTjList;
    }

    public void setSflxTjList() {
        // 是否列表
        List<HashMap<String, String>> sflxList = getCommSelectList("sflx");
        this.sflxTjList = sflxList;
    }

    public List<HashMap<String, String>> getSfhgTjList() {
        return sfhgTjList;
    }

    public void setSfhgTjList() {
        // 是否列表
        List<HashMap<String, String>> sfhgList = getCommSelectList("sfhg");
        this.sfhgTjList = sfhgList;
    }
    // ======================是否条件列表 end===============================

    // ======================审核结果条件列表===============================
    public List<HashMap<String, String>> getShjgTjList() {
        return shjgTjList;
    }

    public void setShjgTjList() {
        // 审核结果
        List<HashMap<String, String>> shjgList = getCommSelectList("shjg");
        this.shjgTjList = shjgList;
    }

    // ======================审核结果条件列表 end===============================

    // ======================审核结果2条件列表===============================
    public List<HashMap<String, String>> getShjg2TjList() {
        return shjg2TjList;
    }

    public void setShjg2TjList() {
        // 审核结果
        List<HashMap<String, String>> shjg2List = getCommSelectList("shjg2");
        this.shjg2TjList = shjg2List;
    }

    public List<HashMap<String, String>> getShjg3TjList() {
        return shjg3TjList;
    }

    public void setShjg3TjList() {
        // 审核结果
        List<HashMap<String, String>> shjg3List = getCommSelectList("shjg3");
        this.shjg3TjList = shjg3List;
    }

    // ======================审核结果2条件列表 end===============================

    // ======================审核状态条件列表===============================
    public List<HashMap<String, String>> getShztTjList() {
        return shztTjList;
    }

    public void setShztTjList() {
        // 审核状态
        List<HashMap<String, String>> shztList = getCommSelectList("shzt");
        this.shztTjList = shztList;
    }

    // ======================审核状态条件列表 end===============================

    // ======================审核状态代码条件列表===============================
    public List<HashMap<String, String>> getShztdmTjList() {
        return shztdmTjList;
    }

    public void setShztdmTjList() {
        // 审核状态
        List<HashMap<String, String>> shztdmList = getCommSelectList("shztdm");
        this.shztdmTjList = shztdmList;
    }

    // ======================审核状态条件列表 end===============================

    // ======================审核状态 begin===============================
    public List<HashMap<String, String>> getShztOneTjList() {
        return shztOneTjList;
    }

    public void setShztOneTjList() {
        List<HashMap<String, String>> list = getShztSelectList("one");
        this.shztOneTjList = list;
    }

    public List<HashMap<String, String>> getShztTwoTjList() {
        return shztTwoTjList;
    }

    public void setShztTwoTjList() {
        List<HashMap<String, String>> list = getShztSelectList("two");
        this.shztTwoTjList = list;
    }

    // ======================审核状态 end===============================

    // =====================医疗保险状态条件列表begin===============================
    public List<HashMap<String, String>> getYlbxztTjList() {
        return ylbxztTjList;
    }

    public void setYlbxztTjList() {
        List<HashMap<String, String>> ylbxztList = getCommSelectList("ylbxzt");
        this.ylbxztTjList = ylbxztList;
    }

    // =====================医疗保险状态条件列表 end===============================

    // ======================应交年数条件列表begin===============================
    public List<HashMap<String, String>> getYjnumTjList() {
        return yjnumTjList;
    }

    public void setYjnumTjList() {
        DAO dao = DAO.getInstance();
        StringBuilder sql = new StringBuilder();
        sql.append(" select distinct yjnum dm,yjnum mc from ( ");
        sql.append(" select ");
        sql.append(" case when a.anum > a.bnum then a.bnum else a.anum end yjnum ");
        sql.append(" from ( ");
        sql.append(" select ");
        sql.append(" (a.bynd - to_char(sysdate,'yyyy')) anum, ");
        sql.append(" (a.bynd - b.jgnd - b.tbnx) bnum ");
        sql.append(" from ( ");
        sql.append(" select a.xh, ");
        sql.append(" xzxm bynd from view_xsxxb a where (a.sfzx='在校' or a.sfzx is null) ");
        sql.append(" ) a left join ( ");
        sql.append(" select ");
        sql.append(" a.xh bxh, ");
        sql.append(" count(1) over (partition by a.xh order by a.xn desc) rs, ");
        sql.append(" substr(nvl(a.xn,0),0,4) jgnd, ");
        sql.append(" nvl(a.zd2,0) tbnx ");
        sql.append(" from xg_rcsw_ylbx_jgb a ");
        sql.append(" ) b on a.xh=b.bxh ");
        sql.append(" where (b.rs= 1 or b.rs is null) ");
        sql.append(" ) a ");
        sql.append(" ) a where to_number(a.yjnum)>0 order by a.yjnum ");
        this.yjnumTjList = dao.getList(sql.toString(), new String[]{}, new String[]{"dm", "mc"});
    }

    // ======================应交年数条件列表 end===============================

    // ======================毕业年度条件列表begin===============================
    public List<HashMap<String, String>> getByndTjList() {
        return byndTjList;
    }

    public void setByndTjList() {
        DAO dao = DAO.getInstance();
        StringBuilder sql = new StringBuilder();
        sql.append(" select distinct bynd dm,bynd mc from ( ");
        sql.append(" select xzxm bynd from view_xsxxb a where (a.sfzx='在校' or a.sfzx is null) and a.xzxm is not null ");
        sql.append(" ) a order by to_number(a.bynd) ");
        this.byndTjList = dao.getList(sql.toString(), new String[]{}, new String[]{"dm", "mc"});
    }

    // ======================毕业年度条件列表 end===============================

    /**
     * *************************************************************************
     * ****************************************************
     */
    /**
     * ************************************************学生信息*********************
     * ****************************
     */
    /**
     * *************************************************************************
     * ****************************************************
     */

    // ======================性别条件列表===============================
    public List<HashMap<String, String>> getXbTjList() {
        return xbTjList;
    }

    public void setXbTjList() {
        // 性别
        List<HashMap<String, String>> xbList = getCommSelectList("xblx");

        this.xbTjList = xbList;
    }

    // ======================性别条件列表 end===============================

    // ======================学制条件列表===============================
    public List<HashMap<String, String>> getXzTjList() {
        return xzTjList;
    }

    public void setXzTjList() {

        DAO dao = DAO.getInstance();

        // 学制条件
        String sql = "select distinct t.xz dm,t.xz mc from view_xsbfxx t where t.xz is not null order by t.xz";
        List<HashMap<String, String>> xzList = dao.getList(sql,
                new String[]{}, new String[]{"dm", "mc"});

        this.xzTjList = xzList;
    }

    private String[] search_tj_sfytj = null;// 是否已提交

    private String[] search_tj_sfdsh = null;//单双号

    private String[] search_tj_stxj = null;//社团星级

    private String[] search_tj_bmlx = null;//报名类型

    private String[] search_tj_dazxqk = null; // 档案在校情况

    // ======================学制条件列表 end===============================

    // ======================团学活动类别条件列表===============================

    public List<HashMap<String, String>> getTxhdlbTjList() {
        return txhdlbTjList;
    }

    public void setTxhdlbTjList() {

        DAO dao = DAO.getInstance();

        // 团学活动类别
        String sql = "select distinct lbdm dm,lbmc mc from xg_rcsw_txhd_lbdm order by lbdm";
        List<HashMap<String, String>> txhdlbList = dao.getList(sql,
                new String[]{}, new String[]{"dm", "mc"});

        this.txhdlbTjList = txhdlbList;
    }

    // ======================团学活动类别条件列表 end===============================


    // ======================培养层次条件列表===============================

    public List<HashMap<String, String>> getPyccTjList() {
        return pyccTjList;
    }

    public void setPyccTjList() {

        DAO dao = DAO.getInstance();

        // 培养层次
        String sql = "select distinct pyccdm dm,pyccmc mc from xg_xsxx_pyccdmb order by pyccdm";
        List<HashMap<String, String>> pyccList = dao.getList(sql,
                new String[]{}, new String[]{"dm", "mc"});

        this.pyccTjList = pyccList;
    }

    // ======================培养层次件列表 end===============================

    // ======================学籍条件列表===============================

    public List<HashMap<String, String>> getXjTjList() {
        return xjTjList;
    }

    public void setXjTjList() {

        DAO dao = DAO.getInstance();
        // 学籍状态
        // String sql =
        // "select distinct zxdmmc dm,zxdmmc mc from dm_zju_xjzt order by zxdmmc";
        String sql = "select distinct xjztm dm,xjztm mc from view_xsbfxx where xjztm is not null  order by xjztm";
        List<HashMap<String, String>> xjList = dao.getList(sql,
                new String[]{}, new String[]{"dm", "mc"});

        this.xjTjList = xjList;
    }

    // ======================学籍条件列表===============================

    // ======================民族条件列表===============================
    public List<HashMap<String, String>> getMzTjList() {
        return mzTjList;
    }

    public void setMzTjList() {

        DAO dao = DAO.getInstance();
        // 民族
        String sql = "select distinct mzdm dm,mzmc mc from mzdmb where mzdm <> '-8' order by mzdm";
        List<HashMap<String, String>> mzList = dao.getList(sql,
                new String[]{}, new String[]{"dm", "mc"});

        this.mzTjList = mzList;
    }

    // ======================民族条件列表 end===============================

    // ======================是否已毕业条件列表===============================
    public List<HashMap<String, String>> getSfybyTjList() {
        return sfybyTjList;
    }

    public void setSfybyTjList() {

        // 是否列表
        List<HashMap<String, String>> sfList = getCommSelectList("sflx");

        this.sfybyTjList = sfList;
    }

    // ======================是否已毕业条件列表end===============================
    //=======================早晚自习考勤====================================
    public List<HashMap<String, String>> getCclxTjList() {
        return cclxTjList;
    }

    public void setCclxTjList() {
        DAO dao = DAO.getInstance();
        // 抽查类型
        String sql = "select distinct lxdm dm,lxmc mc from XG_RCSW_ZWZXKQ_CCLXB  order by lxdm";
        List<HashMap<String, String>> cclxList = dao.getList(sql,
                new String[]{}, new String[]{"dm", "mc"});

        this.cclxTjList = cclxList;
    }

    public List<HashMap<String, String>> getQqlxTjList() {
        return qqlxTjList;
    }

    public void setQqlxTjList() {
        DAO dao = DAO.getInstance();
        // 缺勤类型
        String sql = "select distinct qqlxdm dm,qqlxmc mc from XG_RCSW_ZWZXKQ_QQLXB  order by qqlxdm";
        List<HashMap<String, String>> qqlxList = dao.getList(sql,
                new String[]{}, new String[]{"dm", "mc"});

        this.qqlxTjList = qqlxList;
    }
    // ======================是否在校条件列表===============================

    public List<HashMap<String, String>> getSfzxTjList() {
        return sfzxTjList;
    }

    public void setSfzxTjList() {

        // 是否在校
        List<HashMap<String, String>> sfzxList = getXsxxSelectList("sfzx");

        this.sfzxTjList = sfzxList;
    }

    // ======================是否在校条件列表 end===============================

    // ======================政治面貌条件列表===============================
    public List<HashMap<String, String>> getZmTjList() {
        return zmTjList;
    }

    public void setZmTjList() {

        DAO dao = DAO.getInstance();

        // 政治面貌
        String sql = "select distinct zzmmdm dm,zzmmmc mc from zzmmdmb where zzmmmc is not null order by zzmmdm";
        List<HashMap<String, String>> zzmmList = dao.getList(sql,
                new String[]{}, new String[]{"dm", "mc"});

        this.zmTjList = zzmmList;
    }

    // ======================政治面貌条件列表 end===============================

    // ======================户口条件列表===============================
    public List<HashMap<String, String>> getHkTjList() {
        return hkTjList;
    }

    public void setHkTjList() {

        DAO dao = DAO.getInstance();
        // 户口状态
        String sql = "select distinct hkztdm dm,hkztmc mc from hkztdmb order by hkztdm";
        List<HashMap<String, String>> hkztList = dao.getList(sql,
                new String[]{}, new String[]{"dm", "mc"});
        this.hkTjList = hkztList;
    }

    // ======================户口条件列表 end===============================

    // ======================省份条件列表===============================
    public List<HashMap<String, String>> getProvTjList() {
        return provTjList;
    }

    public void setProvTjList() {

        DAO dao = DAO.getInstance();
        // 来源省
        String sql = "select distinct sydqdm dm,sydq mc from dmk_sydq order by sydqdm";
        List<HashMap<String, String>> provList = dao.getList(sql,
                new String[]{}, new String[]{"dm", "mc"});
        this.provTjList = provList;
    }

    // ======================省份条件列表 end===============================

    // ======================是否缴费条件列表===============================
    public List<HashMap<String, String>> getSfjfTjList() {
        return sfjfTjList;
    }

    public void setSfjfTjList() {
        // 是否缴费
        List<HashMap<String, String>> sfjfList = getXsxxSelectList("sfjf");
        this.sfjfTjList = sfjfList;
    }

    // ======================是否缴费条件列表 end===============================

    // ======================异动类别条件列表===============================
    public List<HashMap<String, String>> getYdlbTjList() {
        return ydlbTjList;
    }

    public void setYdlbTjList() {

        DAO dao = DAO.getInstance();
        // 异动类别
        String sql = "select distinct ydlbm dm,ydlbmc mc from dm_ydlb order by ydlbm";
        List<HashMap<String, String>> ydlbList = dao.getList(sql,
                new String[]{}, new String[]{"dm", "mc"});
        this.ydlbTjList = ydlbList;
    }

    // ======================异动类别条件列表 end===============================

    // ======================转档类别条件列表===============================
    public List<HashMap<String, String>> getZdlbTjList() {
        return zdlbTjList;
    }

    public void setZdlbTjList() {
        // 转档类别
        List<HashMap<String, String>> zdlbList = getXsxxSelectList("zdlb");
        this.zdlbTjList = zdlbList;
    }

    // ======================转档类别条件列表 end===============================

    // ======================是否已提交 begin 2012.4.9
    // qlj===============================
    public List<HashMap<String, String>> getSfytjTjList() {
        return sfytjTjList;
    }

    public void setSfytjTjList() {

        DAO dao = DAO.getInstance();

        String[] en = {"未提交", "已提交"};

        this.sfytjTjList = dao.arrayToList(en, en);
    }

    public List<HashMap<String, String>> getSfdshTjList() {
        return sfdshTjList;
    }

    public void setSfdshTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"1", "0"};
        String[] mc = {"单号", "双号"};

        this.sfdshTjList = dao.arrayToList(dm, mc);
    }

    public List<HashMap<String, String>> getStxjTjList() {
        return stxjTjList;
    }

    public void setStxjTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"1", "2", "3", "4", "5"};
        String[] mc = {"1", "2", "3", "4", "5"};

        this.stxjTjList = dao.arrayToList(dm, mc);
    }

    public List<HashMap<String, String>> getBmlxTjList() {
        return bmlxTjList;
    }

    public void setBmlxTjList() {
        DAO dao = DAO.getInstance();
        String[] bmlx = {"0", "1"};
        String[] bmlxmc = {"组队", "个人"};
        this.bmlxTjList = dao.arrayToList(bmlx, bmlxmc);
    }
// ======================是否已提交 end 2012.4.9
    // qlj===============================

    // ======================入学方式 begin 2012.11.20
    // gbb===============================
    public List<HashMap<String, String>> getRxfsTjList() {
        return rxfsTjList;
    }

    public void setRxfsTjList() {

        DAO dao = DAO.getInstance();

        StringBuilder sql = new StringBuilder();

        sql
                .append(" select distinct rxfsdm dm,rxfsmc mc from xg_xsxx_rxfsdmb  ");

        List<HashMap<String, String>> rxfsTjList = dao.getList(sql.toString(),
                new String[]{}, new String[]{"dm", "mc"});

        this.rxfsTjList = rxfsTjList;
    }

    // ======================入学方式 end 2012.11.20

    // ======================困难生申请性质 begin
    // gbb===============================
    public List<HashMap<String, String>> getKnssqxzTjList() {
        return knssqxzTjList;
    }

    public void setKnssqxzTjList() {

        DAO dao = DAO.getInstance();

        String[] en = {"新申请", "重新认定", "档次调整"};

        this.knssqxzTjList = dao.arrayToList(en, en);
    }

    // ======================困难生申请性质 end


    // gbb===============================

    // ======================档案在校情况 begin 2012.4.9
    // qlj===============================
    public List<HashMap<String, String>> getDazxqkTjList() {
        return dazxqkTjList;
    }

    public void setDazxqkTjList() {

        DAO dao = DAO.getInstance();

        String[] en = {"未设置", "在校", "不在校"};

        this.dazxqkTjList = dao.arrayToList(en, en);
    }

    public List<HashMap<String, String>> getZd1TjList() {
        return zd1TjList;
    }

    public void setZd1TjList() {

        DAO dao = DAO.getInstance();

        StringBuilder sql = new StringBuilder();

        sql
                .append(" select distinct zd1 dm,zd1 mc from xg_xsxx_qtxxb where zd1 is not null   ");

        List<HashMap<String, String>> zd1TjList = dao.getList(sql.toString(),
                new String[]{}, new String[]{"dm", "mc"});
        this.zd1TjList = zd1TjList;
    }

    public List<HashMap<String, String>> getZd3TjList() {
        return zd3TjList;
    }

    public void setZd3TjList() {

        DAO dao = DAO.getInstance();

        StringBuilder sql = new StringBuilder();

        sql
                .append(" select distinct zd3 dm,zd3 mc from xg_xsxx_qtxxb where zd3 is not null   ");

        List<HashMap<String, String>> zd3TjList = dao.getList(sql.toString(),
                new String[]{}, new String[]{"dm", "mc"});
        this.zd3TjList = zd3TjList;
    }

    public List<HashMap<String, String>> getZd5TjList() {
        return zd5TjList;
    }

    public void setZd5TjList() {

        DAO dao = DAO.getInstance();

        StringBuilder sql = new StringBuilder();

        sql
                .append(" select distinct zd5 dm,zd5 mc from xg_xsxx_qtxxb where zd5 is not null   ");

        List<HashMap<String, String>> zd5TjList = dao.getList(sql.toString(),
                new String[]{}, new String[]{"dm", "mc"});
        this.zd5TjList = zd5TjList;
    }

    public List<HashMap<String, String>> getZd24TjList() {
        return zd24TjList;
    }

    public void setZd24TjList() {

        DAO dao = DAO.getInstance();

        StringBuilder sql = new StringBuilder();

        sql
                .append(" select distinct zd24 dm,zd24 mc from xg_xsxx_qtxxb where zd24 is not null   ");

        List<HashMap<String, String>> zd24TjList = dao.getList(sql.toString(),
                new String[]{}, new String[]{"dm", "mc"});
        this.zd24TjList = zd24TjList;
    }

    public List<HashMap<String, String>> getZd26TjList() {
        return zd26TjList;
    }

    public void setZd26TjList() {

        DAO dao = DAO.getInstance();

        StringBuilder sql = new StringBuilder();

        sql
                .append(" select distinct zd26 dm,zd26 mc from xg_xsxx_qtxxb where zd26 is not null   ");

        List<HashMap<String, String>> zd26TjList = dao.getList(sql.toString(),
                new String[]{}, new String[]{"dm", "mc"});
        this.zd26TjList = zd26TjList;
    }

    public List<HashMap<String, String>> getZd8TjList() {
        return zd8TjList;
    }

    public void setZd8TjList() {

        DAO dao = DAO.getInstance();

        StringBuilder sql = new StringBuilder();

        sql
                .append(" select distinct zd8 dm,zd8 mc from xg_xsxx_qtxxb where zd8 is not null   ");

        List<HashMap<String, String>> zd8TjList = dao.getList(sql.toString(),
                new String[]{}, new String[]{"dm", "mc"});
        this.zd8TjList = zd8TjList;
    }

    public List<HashMap<String, String>> getZd9TjList() {
        return zd9TjList;
    }

    public void setZd9TjList() {

        DAO dao = DAO.getInstance();

        StringBuilder sql = new StringBuilder();

        sql
                .append(" select distinct zd9 dm,zd9 mc from xg_xsxx_qtxxb where zd9 is not null   ");

        List<HashMap<String, String>> zd9TjList = dao.getList(sql.toString(),
                new String[]{}, new String[]{"dm", "mc"});
        this.zd9TjList = zd9TjList;
    }

    public List<HashMap<String, String>> getZd10TjList() {
        return zd10TjList;
    }

    public void setZd10TjList() {

        DAO dao = DAO.getInstance();

        StringBuilder sql = new StringBuilder();

        sql
                .append(" select distinct zd10 dm,zd10 mc from xg_xsxx_qtxxb where zd10 is not null   ");

        List<HashMap<String, String>> zd10TjList = dao.getList(sql.toString(),
                new String[]{}, new String[]{"dm", "mc"});
        this.zd10TjList = zd10TjList;
    }

    public List<HashMap<String, String>> getZd11TjList() {
        return zd11TjList;
    }

    public void setZd11TjList() {

        DAO dao = DAO.getInstance();

        StringBuilder sql = new StringBuilder();

        sql
                .append(" select distinct zd11 dm,zd11 mc from xg_xsxx_qtxxb  where zd11 is not null  ");

        List<HashMap<String, String>> zd11TjList = dao.getList(sql.toString(),
                new String[]{}, new String[]{"dm", "mc"});
        this.zd11TjList = zd11TjList;
    }

    public List<HashMap<String, String>> getZd13TjList() {
        return zd13TjList;
    }

    public void setZd13TjList() {

        DAO dao = DAO.getInstance();

        StringBuilder sql = new StringBuilder();

        sql
                .append(" select distinct zd13 dm,zd13 mc from xg_xsxx_qtxxb where zd13 is not null   ");

        List<HashMap<String, String>> zd13TjList = dao.getList(sql.toString(),
                new String[]{}, new String[]{"dm", "mc"});
        this.zd13TjList = zd13TjList;
    }

    public List<HashMap<String, String>> getZd15TjList() {
        return zd15TjList;
    }

    public void setZd15TjList() {

        DAO dao = DAO.getInstance();

        StringBuilder sql = new StringBuilder();

        sql
                .append(" select distinct zd15 dm,zd15 mc from xg_xsxx_qtxxb where zd15 is not null   ");

        List<HashMap<String, String>> zd15TjList = dao.getList(sql.toString(),
                new String[]{}, new String[]{"dm", "mc"});
        this.zd15TjList = zd15TjList;
    }

    public List<HashMap<String, String>> getZd17TjList() {
        return zd17TjList;
    }

    public void setZd17TjList() {

        DAO dao = DAO.getInstance();

        StringBuilder sql = new StringBuilder();

        sql
                .append(" select distinct zd17 dm,zd17 mc from xg_xsxx_qtxxb where zd17 is not null   ");

        List<HashMap<String, String>> zd17TjList = dao.getList(sql.toString(),
                new String[]{}, new String[]{"dm", "mc"});
        this.zd17TjList = zd17TjList;
    }

    public List<HashMap<String, String>> getZd16TjList() {
        return zd16TjList;
    }

    public void setZd16TjList() {

        DAO dao = DAO.getInstance();

        StringBuilder sql = new StringBuilder();

        sql
                .append(" select distinct zd16 dm,zd16 mc from xg_xsxx_qtxxb where zd16 is not null  ");

        List<HashMap<String, String>> zd16TjList = dao.getList(sql.toString(),
                new String[]{}, new String[]{"dm", "mc"});
        this.zd16TjList = zd16TjList;
    }

    public List<HashMap<String, String>> getZd21TjList() {
        return zd21TjList;
    }

    public void setZd21TjList() {

        DAO dao = DAO.getInstance();

        StringBuilder sql = new StringBuilder();

        sql
                .append(" select distinct zd21 dm,zd21 mc from xg_xsxx_qtxxb where zd21 is not null ");

        List<HashMap<String, String>> zd21TjList = dao.getList(sql.toString(),
                new String[]{}, new String[]{"dm", "mc"});
        this.zd21TjList = zd21TjList;
    }

    public List<HashMap<String, String>> getXjlbTjList() {
        return xjlbTjList;
    }

    /**
     * @throws
     * @描述:学籍类别
     * @作者：Qilm[工号：964]
     * @日期：2013-12-16 下午01:45:14 void 返回类型
     */
    public void setXjlbTjList() {
        DAO dao = DAO.getInstance();
        StringBuilder sql = new StringBuilder();
        sql.append(" select distinct xjlbdm dm,xjlbmc mc from dm_xjlb order by xjlbdm");

        List<HashMap<String, String>> xjlbTjList = dao.getList(sql.toString(),
                new String[]{}, new String[]{"dm", "mc"});

        this.xjlbTjList = xjlbTjList;
    }

    public List<HashMap<String, String>> getXjlbmcTjList() {
        return xjlbmcTjList;
    }

    public void setXjlbmcTjList() {

        DAO dao = DAO.getInstance();
        StringBuilder sql = new StringBuilder();
        sql
                .append(" select distinct xjlb dm,xjlb mc from view_xsxxb where xjlb is not null order by xjlb");

        List<HashMap<String, String>> xjlbmcTjList = dao.getList(
                sql.toString(), new String[]{}, new String[]{"dm", "mc"});

        this.xjlbmcTjList = xjlbmcTjList;
    }

    // ======================档案在校情况 end 2012.4.9
    // qlj===============================
    /**
     * *************************************************************************
     * ****************************************************
     */
    /**
     * ************************************************日常事务*********************
     * ****************************
     */
    /**
     * *************************************************************************
     * ****************************************************
     */
    // ======================是否通知条件列表===============================
    public List<HashMap<String, String>> getSftzTjList() {
        return sftzTjList;
    }

    public void setSftzTjList() {
        // 是否已通知
        List<HashMap<String, String>> sftzList = getRcswSelectList("sftz");
        this.sftzTjList = sftzList;
    }

    // ======================申请结果===============================

    public List<HashMap<String, String>> getSqjgTjList() {
        return sqjgTjList;
    }

    public void setSqjgTjList() {
        List<HashMap<String, String>> sqjgTjList = getRcswSelectList("sqjg");
        this.sqjgTjList = sqjgTjList;
    }

    // ======================日常事务——费用发放项目===============================

    public List<HashMap<String, String>> getFyffXmTjList() {
        return fyffXmTjList;
    }

    public void setFyffXmTjList() {

        DAO dao = DAO.getInstance();

        // 费用发放项目
        String sql = " select distinct ffxmdm dm, ffxmmc mc from xg_rcsw_fyff_ffxmdmb ";
        List<HashMap<String, String>> fyffXmTjList = dao.getList(sql,
                new String[]{}, new String[]{"dm", "mc"});

        this.fyffXmTjList = fyffXmTjList;
    }

    // ======================日常事务——绿色通道===============================
    public List<HashMap<String, String>> getLstdLyTjList() {
        return lstdLyTjList;
    }

    public void setLstdLyTjList() {

        DAO dao = DAO.getInstance();

        // 团学活动类别
        String sql = "select distinct lxdm dm,lxmc mc from xg_rcsw_lstd_lxwhb";
        List<HashMap<String, String>> lstdLyList = dao.getList(sql,
                new String[]{}, new String[]{"dm", "mc"});

        this.lstdLyTjList = lstdLyList;
    }

    // ======================上海海洋——爱心超市===============================
    public List<HashMap<String, String>> getWplbTjList() {
        return wplbTjList;
    }

    public void setWplbTjList() {
        DAO dao = DAO.getInstance();
        // 爱心超市物品类别
        String sql = "select distinct dm,mc from xg_xszz_axcslbb";
        List<HashMap<String, String>> wplbTjList = dao.getList(sql,
                new String[]{}, new String[]{"dm", "mc"});
        this.wplbTjList = wplbTjList;
    }

    public List<HashMap<String, String>> getWpmcTjList() {
        return wpmcTjList;
    }

    public void setWpmcTjList() {
        DAO dao = DAO.getInstance();
        // 爱心超市物品名称
        String sql = "select distinct xmmc dm,xmmc mc from xg_xszz_axcsxmb";
        List<HashMap<String, String>> wpmcTjList = dao.getList(sql,
                new String[]{}, new String[]{"dm", "mc"});
        this.wpmcTjList = wpmcTjList;
    }

    public List<HashMap<String, String>> getXmjbTjList() {
        return xmjbTjList;
    }

    public void setXmjbTjList() {
        DAO dao = DAO.getInstance();
        // 素质拓展项目级别
        String sql = "select distinct xmjbdm dm,xmjbmc mc from xg_sztz_xmjb";
        List<HashMap<String, String>> xmjbTjList = dao.getList(sql,
                new String[]{}, new String[]{"dm", "mc"});
        this.xmjbTjList = xmjbTjList;
    }

    public List<HashMap<String, String>> getSskmTjList() {
        return sskmTjList;
    }

    public void setSskmTjList() {
        DAO dao = DAO.getInstance();
        // 素质拓展所属科目
        String sql = "select distinct sskmdm dm,sskmmc mc from xg_sztz_sskm";
        List<HashMap<String, String>> sskmTjList = dao.getList(sql,
                new String[]{}, new String[]{"dm", "mc"});
        this.sskmTjList = sskmTjList;
    }

    /**
     * *************************************************************************
     * ****************************************************
     */
    /**
     * ************************************************评奖评优
     * begin*************************************************
     */
    /**
     * *************************************************************************
     * ****************************************************
     */

    // ======================项目类型列表 begin===============================
    public List<HashMap<String, String>> getXmlxTjList() {
        return xmlxTjList;
    }

    public void setXmlxTjList() {
        List<HashMap<String, String>> xmlxList = getPjpySelectList("xmlx");
        this.xmlxTjList = xmlxList;
    }

    // ======================项目类型列表 end===============================

    // ======================项目性质 begin===============================
    public List<HashMap<String, String>> getXmxzTjList() {
        return xmxzTjList;
    }

    public void setXmxzTjList() {

        DAO dao = DAO.getInstance();

        StringBuilder sql = new StringBuilder();

        sql.append(" select xzdm dm,xzmc mc from xg_pjpy_xmxzb ");

        List<HashMap<String, String>> xmxzTjList = dao.getList(sql.toString(),
                new String[]{}, new String[]{"dm", "mc"});
        this.xmxzTjList = xmxzTjList;
    }

    // ======================项目性质 end===============================

    // ======================新项目性质 begin===============================
    public List<HashMap<String, Object>> getXxmxzTjList() {
        return xxmxzTjList;
    }

    public void setXxmxzTjList(List<HashMap<String, Object>> xxmxzTjList) {
        this.xxmxzTjList = xxmxzTjList;
    }

    // ======================新项目性质 end===============================
    // ======================谈话记录-关注等级 begin===============================

    public List<HashMap<String, String>> getThjlGzdjTjList() {
        return thjlGzdjTjList;
    }

    public void setThjlGzdjTjList() {
        List<HashMap<String, String>> thjlGzdjTjList = new OptionUtil().getOptions(OptionUtil.THJL_GZDJ);
        this.thjlGzdjTjList = thjlGzdjTjList;
    }

    // ======================谈话记录-关注等级 end===============================

    // ======================新项目类型 begin===============================
    public List<HashMap<String, Object>> getXxmlxTjList() {
        return xxmlxTjList;
    }

    public void setXxmlxTjList(List<HashMap<String, Object>> xxmlxTjList) {
        this.xxmlxTjList = xxmlxTjList;
    }

    // ======================新项目类型 end===============================

    // ======================综测周期信息 begin===============================
    public List<HashMap<String, String>> getZczqTjList() {
        return zczqTjList;
    }

    public void setZczqTjList() {

        DAO dao = DAO.getInstance();

        StringBuilder sql = new StringBuilder();

        sql
                .append("  select xn||'luojw'||xq||'luojw'||nd dm,xninfo||xqinfo||ndinfo mc from( ");
        sql.append("  select xn, xq, nd,  ");
        sql.append("   case when xn='no' then '' else xn||' 学年 ' end xninfo, ");
        sql
                .append("   case when xq='no' then '' else (select b.xqmc from xqdzb b where a.xq = b.xqdm)||' 学期 ' end xqinfo, ");
        sql.append("   case when nd='no' then '' else nd||'年度' end ndinfo ");
        sql.append("   from xg_pjpy_zcxmb a group by xn, xq,nd) ");

        List<HashMap<String, String>> zczqTjList = dao.getList(sql.toString(),
                new String[]{}, new String[]{"dm", "mc"});

        this.zczqTjList = zczqTjList;
    }

    // ======================综测周期信息 end===============================

    // ======================评奖周期信息 begin===============================
    public List<HashMap<String, String>> getPjzqTjList() {
        return pjzqTjList;
    }

    public void setPjzqTjList() {

        DAO dao = DAO.getInstance();

        StringBuilder sql = new StringBuilder();

        sql.append(" select distinct pjsj dm,pjsj mc ");
        sql.append(" from xg_view_pjpy_pjlsxx ");
        sql.append(" order by pjsj");

        List<HashMap<String, String>> pjzqTjList = dao.getList(sql.toString(),
                new String[]{}, new String[]{"dm", "mc"});

        this.pjzqTjList = pjzqTjList;
    }

    // ======================评奖周期信息 begin===============================

    // ======================评奖申请项目信息 begin===============================

    public List<HashMap<String, String>> getPjsqxmTjList() {
        return pjsqxmTjlist;
    }

    public void setPjsqxmTjList() {
        DAO dao = DAO.getInstance();

        StringBuilder sql = new StringBuilder();

        sql.append(" select distinct xmmc dm, xmmc mc from xg_pjpy_new_xmsq a ");
        sql.append(" left join xg_pjpy_new_pjxmb b on a.xmdm=b.xmdm and b.xzdm = '1' order by b.xmmc ");

        List<HashMap<String, String>> pjsqxmTjList = dao.getList(
                sql.toString(), new String[]{}, new String[]{"dm", "mc"});

        this.pjsqxmTjlist = pjsqxmTjList;
    }

    public List<HashMap<String, String>> getJxsqzdTjList() {
        return jxsqzdTjlist;
    }

    public void setJxsqzdTjList() {
        DAO dao = DAO.getInstance();
        StringBuilder sql = new StringBuilder();
        sql.append(" select distinct xmmc dm, xmmc mc from xg_zjdx_pjpy_pjxmb order by xmmc ");
        List<HashMap<String, String>> jxsqzdTjList = dao.getList(sql.toString(), new String[]{}, new String[]{"dm", "mc"});
        this.jxsqzdTjlist = jxsqzdTjList;
    }
    // ======================评奖申请项目信息 begin===============================

    // ======================评奖历史项目信息 begin===============================
    public List<HashMap<String, String>> getPjlsxmTjList() {
        return pjlsxmTjList;
    }

    public void setPjlsxmTjList() {
        DAO dao = DAO.getInstance();

        StringBuilder sql = new StringBuilder();

        sql.append(" select distinct xmmc dm,xmmc mc ");
        sql.append(" from xg_view_pjpy_pjlsxx order by xmmc ");

        List<HashMap<String, String>> pjlsxmTjList = dao.getList(
                sql.toString(), new String[]{}, new String[]{"dm", "mc"});

        this.pjlsxmTjList = pjlsxmTjList;
    }

    // ======================评奖历史项目信息 begin===============================

    public List<HashMap<String, String>> getHdryTjList() {
        return hdryTjList;
    }

    public void setHdryTjList() {
        DAO dao = DAO.getInstance();

        StringBuilder sql = new StringBuilder();

        sql.append(" select dm,mc ");
        sql.append(" from xg_pjpy_bjrydmb ");

        List<HashMap<String, String>> hdryTjList = dao.getList(sql.toString(),
                new String[]{}, new String[]{"dm", "mc"});

        this.hdryTjList = hdryTjList;
    }

    /**
     * *************************************************************************
     * ****************************************************
     */
    /**
     * ************************************************评奖评优end******************
     * *******************************
     */
    /**
     * *************************************************************************
     * ****************************************************
     */

    // =========================处分类别 begin======================
    public List<HashMap<String, String>> getCflbTjList() {
        return cflbTjList;
    }

    // 处分类别
    public void setCflbTjList() throws Exception {
        WjcfJcszServices service = new WjcfJcszServices();
        List<HashMap<String, String>> cflbTjList = service.cflbdmCx();

        this.cflbTjList = cflbTjList;
    }

    public List<HashMap<String, String>> getCfyyTjList() {
        return cfyyTjList;
    }

    // 处分原因
    public void setCfyyTjList() throws Exception {
        WjcfJcszServices service = new WjcfJcszServices();
        List<HashMap<String, String>> cfyyTjList = service.cfyydmCx();

        this.cfyyTjList = cfyyTjList;
    }

    public List<HashMap<String, String>> getCflbmcTjList() {
        return cflbmcTjList;
    }

    // 处分类别名称
    public void setCflbmcTjList() throws Exception {
        WjcfJcszServices service = new WjcfJcszServices();
        List<HashMap<String, String>> cflbmcTjList = service.cflbmcCx();

        this.cflbmcTjList = cflbmcTjList;
    }

    public List<HashMap<String, String>> getCfyymcTjList() {
        return cfyymcTjList;
    }

    // 处分原因名称
    public void setCfyymcTjList() throws Exception {
        WjcfJcszServices service = new WjcfJcszServices();
        List<HashMap<String, String>> cfyymcTjList = service.cfyymcCx();

        this.cfyymcTjList = cfyymcTjList;
    }

    // =========================处分类别 end======================

    // ======================申述结果 begin================
    public List<HashMap<String, String>> getSsjgTjList() {
        return ssjgTjList;
    }

    // 处分原因
    public void setSsjgTjList() throws Exception {

        List<HashMap<String, String>> ssjgTjList = getWjcfSelectList("ssjg");

        this.ssjgTjList = ssjgTjList;
    }

    // ======================申述结果 end================

    // ======================奖项类别 begin================
    public void setJxlbTjList() {
        DAO dao = DAO.getInstance();
        String sql = "select jxlbdm dm,jxlbmc mc from xg_hjxxgl_jxlb";
        this.jxlbTjList = dao.getList(sql, new String[]{}, new String[]{
                "dm", "mc"});
    }

    public List<HashMap<String, String>> getJxlbTjList() {
        return jxlbTjList;
    }
    // ======================奖项类别 end================

    // ======================奖项等级 begin================
    public void setJxdjTjList() {
        DAO dao = DAO.getInstance();
        String sql = "select distinct jxdjmc dm,jxdjmc mc from xg_hjxxgl_jxdj";
        this.jxdjTjList = dao.getList(sql, new String[]{}, new String[]{
                "dm", "mc"});
    }

    public List<HashMap<String, String>> getJxdjTjList() {
        return jxdjTjList;
    }
    // ======================奖项等级 end================

    // ======================奖项名次 begin================
    public void setHjjxmcTjList() {
        DAO dao = DAO.getInstance();
        String sql = "select distinct jxmcmc dm,jxmcmc mc from xg_hjxxgl_jxmc";
        this.hjjxmcTjList = dao.getList(sql, new String[]{}, new String[]{
                "dm", "mc"});
    }

    public List<HashMap<String, String>> getHjjxmcTjList() {
        return hjjxmcTjList;
    }
    // ======================奖项名次 end================

    // ======================竞赛方式 begin================
    public List<HashMap<String, String>> getJsfsTjList() {
        return jsfsTjList;
    }

    public void setJsfsTjList() {
        List<HashMap<String, String>> jsfsList = getPjpySelectList("jsfs");
        this.jsfsTjList = jsfsList;
    }
    // ======================竞赛方式 end================

    // ======================是否评分条件列表===============================
    public List<HashMap<String, String>> getSfpfTjList() {
        return sfpfTjList;
    }

    public void setSfpfTjList() {
        List<HashMap<String, String>> sfpfList = getPjpySelectList("sfpf");
        this.sfpfTjList = sfpfList;
    }

    // ======================是否评分条件列表 end===============================

    // ======================是否确认条件列表===============================
    public List<HashMap<String, String>> getSfqrTjList() {
        return sfqrTjList;
    }

    public void setSfqrTjList() {
        List<HashMap<String, String>> sfqrList = getPjpySelectList("sfqr");
        this.sfqrTjList = sfqrList;
    }

    // ======================是否确认条件列表 end===============================

    // ======================是否审核条件列表===============================
    public List<HashMap<String, String>> getSfshTjList() {
        return sfshTjList;
    }

    public void setSfshTjList() {
        List<HashMap<String, String>> sfshList = getCommSelectList("sf");
        this.sfshTjList = sfshList;
    }

    // ======================是否审核条件列表 end===============================

    // ======================审核状态===============================
    public List<HashMap<String, String>> getShzt3TjList() {
        return shzt3List;
    }

    public void setShzt3TjList() {
        List<HashMap<String, String>> shzt3List = getPjpySelectList("shzt3");
        this.shzt3List = shzt3List;
    }

    // ======================是否审核条件列表 end===============================

    // ======================审核状态===============================
    public List<HashMap<String, String>> getXmdmTjList() {
        return xmdmList;
    }

    public void setXmdmTjList() {

        DAO dao = DAO.getInstance();

        StringBuilder sql = new StringBuilder();

        sql.append(" select xmdm en,xmmc cn from xg_pjpy_pjxmwhb where ");
        sql.append(" pjxn =(select pjxn from xg_pjpy_xtszb where rownum =1 )");
        sql
                .append(" and pjxq =(select pjxq from xg_pjpy_xtszb where rownum =1 )");
        sql
                .append(" and pjnd =(select pjnd from xg_pjpy_xtszb where rownum =1 )");

        this.xmdmList = dao.getList(sql.toString(), new String[]{},
                new String[]{"en", "cn"});
    }

    // ======================是否审核条件列表 end===============================

    // ======================是否提交条件列表===============================
    public List<HashMap<String, String>> getSftjTjList() {
        return sftjTjList;
    }

    public void setSftjTjList() {
        List<HashMap<String, String>> sftjList = getCommSelectList("sf");
        this.sftjTjList = sftjList;
    }

    // ======================是否提交条件列表 end===============================

    /**
     * *************************************************************************
     * ****************************************************
     */
    /**
     * ************************************************公寓管理*********************
     * ****************************
     */
    /**
     * *************************************************************************
     * ****************************************************
     */

    // ======================寝室性别条件列表===============================
    public List<HashMap<String, String>> getQsxbTjList() {
        return qsxbTjList;
    }

    public void setQsxbTjList(List<HashMap<String, String>> qsxbTjList) {
        this.qsxbTjList = qsxbTjList;
    }

    // ======================寝室性别条件列表 end===============================

    // ======================退宿原因条件列表===============================
    public List<HashMap<String, String>> getTsyyTjList() {

        return tsyyTjList;
    }

    public void setTsyyTjList() {
        DAO dao = DAO.getInstance();
        String sql = "select distinct tsyydm,tsyymc dm,tsyymc mc from xg_gygl_new_tsyydmb order by to_number(tsyydm)";
        List<HashMap<String, String>> tsyyTjList = dao.getList(sql,
                new String[]{}, new String[]{"dm", "mc"});
        this.tsyyTjList = tsyyTjList;
    }

    // ======================退宿原因条件列表 end===============================

    // ======================是否入住条件列表===============================
    public List<HashMap<String, String>> getSfrzTjList() {
        return sfrzTjList;
    }

    public void setSfrzTjList() {
        List<HashMap<String, String>> sfrzList = getGyglSelectList("sfrz");
        this.sfrzTjList = sfrzList;
    }

    // ======================是否入住条件列表 end===============================

    // ======================是否分配条件列表===============================
    public List<HashMap<String, String>> getSffpTjList() {
        return sffpTjList;
    }

    public void setSffpTjList() {
        List<HashMap<String, String>> sffpList = getGyglSelectList("sffp");
        this.sffpTjList = sffpList;
    }

    // ======================是否入住条件列表 end===============================

    // ======================楼栋性别条件列表===============================
    public List<HashMap<String, String>> getLdxbTjList() {
        return ldxbTjList;
    }

    public void setLdxbTjList() {
        List<HashMap<String, String>> ldxbTjList = getGyglSelectList("ldxb");
        this.ldxbTjList = ldxbTjList;
    }

    // ======================楼栋性别条件列表 end===============================

    // ======================紧急程度条件列表===============================
    public List<HashMap<String, String>> getJjcdTjList() {
        return jjcdTjList;
    }

    public void setJjcdTjList() {
        List<HashMap<String, String>> jjcdTjList = getGyglSelectList("jjcd");
        this.jjcdTjList = jjcdTjList;
    }

    // ======================紧急程度条件列表 end===============================

    /**
     * @return the gybxlbTjList
     */
    public List<HashMap<String, String>> getGybxlbTjList() {
        return gybxlbTjList;
    }

    /**
     * @param bxlbmcTjList要设置的 bxlbmcTjList
     */
    public void setGybxlbTjList() {
        DAO dao = DAO.getInstance();

        StringBuilder sql = new StringBuilder();
        sql.append(" select dm,mc from gygl_bxlbdmb ");
        List<HashMap<String, String>> gybxlbTjList = dao.getList(
                sql.toString(), new String[]{}, new String[]{"dm", "mc"});
        this.gybxlbTjList = gybxlbTjList;
    }

    // ======================处理状态条件列表===============================
    public List<HashMap<String, String>> getClztTjList() {
        return clztTjList;
    }

    public void setClztTjList() {
        List<HashMap<String, String>> clztTjList = getGyglSelectList("clzt");
        this.clztTjList = clztTjList;
    }

    // ======================处理状态条件列表 end===============================

    // ======================处理状态代码条件列表===============================
    public List<HashMap<String, String>> getClztdmTjList() {
        return clztdmTjList;
    }

    public void setClztdmTjList() {
        List<HashMap<String, String>> clztdmTjList = getGyglSelectList("clztdm");
        this.clztdmTjList = clztdmTjList;
    }

    // ======================处理状态代码条件列表 end===============================

    // ======================入住情况条件列表===============================
    public List<HashMap<String, String>> getRzqkTjList() {
        return rzqkTjList;
    }

    public void setRzqkTjList() {
        List<HashMap<String, String>> rzqkTjList = getGyglSelectList("rzqk");
        this.rzqkTjList = rzqkTjList;
    }

    // ======================入住情况条件列表 end===============================

    // ======================公寓管理类型条件列表===============================
    public List<HashMap<String, String>> getGygllxTjList() {
        return gygllxTjList;
    }

    public void setGygllxTjList() {
        List<HashMap<String, String>> gygllxTjList = getGyglSelectList("gygllx");
        this.gygllxTjList = gygllxTjList;
    }

    // ======================公寓管理类型条件列表 end===============================

    // ====================公寓纪律类别大类代码条件列表=============================
    public List<HashMap<String, Object>> getGyjllbdldmTjList() {
        return gyjllbdldmTjList;
    }

    public void setGyjllbdldmTjList(
            List<HashMap<String, Object>> gyjllbdldmTjList) {
        this.gyjllbdldmTjList = gyjllbdldmTjList;
    }

    // ====================公寓纪律类别代码条件列表=============================
    public List<HashMap<String, Object>> getGyjllbdmTjList() {
        return gyjllbdmTjList;
    }

    public void setGyjllbdmTjList(List<HashMap<String, Object>> gyjllbdmTjList) {
        this.gyjllbdmTjList = gyjllbdmTjList;
    }

    // ====================公寓纪律类别大类代码条件列表end==========================

    // ====================公寓纪律处分类别条件列表=============================
    public List<HashMap<String, String>> getGyjlcflbTjList() {
        return gyjlcflbTjList;
    }

    public void setGyjlcflbTjList() {
        DAO dao = DAO.getInstance();
        String sql = " select gyjlcfdm dm,gyjlcfmc mc from xg_gygl_new_gyjlcflbb UNION ALL select 'wcl' dm,'未处理' mc from dual UNION select 'th' dm,'退回' mc from dual ";
        List<HashMap<String, String>> gyjlcflbTjList = dao.getList(sql,
                new String[]{}, new String[]{"dm", "mc"});
        this.gyjlcflbTjList = gyjlcflbTjList;
    }

    // ====================公寓纪律处分类别条件列表end==========================

    // ======================卫生检查类型条件列表===============================
    public List<HashMap<String, String>> getJclxTjList() {
        return jclxTjList;
    }

    public void setJclxTjList() {
        List<HashMap<String, String>> jclxTjList = getGyglSelectList("jclx");
        this.jclxTjList = jclxTjList;
    }

    // ======================卫生检查类型条件列表 end===============================

    public List<HashMap<String, String>> getPylbTjList() {
        return pylbTjList;
    }

    public void setPylbTjList() {
        DAO dao = DAO.getInstance();
        String sql = "select pylbdm dm,pylbmc mc from xg_gygl_pylbdmb order by pylbdm";
        List<HashMap<String, String>> pylbTjList = dao.getList(sql,
                new String[]{}, new String[]{"dm", "mc"});
        this.pylbTjList = pylbTjList;
    }

    // ======================党团建设阶段代码条件列表===============================
    public List<HashMap<String, String>> getJddmTjList() {
        return jddmTjList;
    }

    public void setJddmTjList() {
        DAO dao = DAO.getInstance();
        String sql = "select jddm dm,jdmc mc from xg_dtjs_jbszb order by to_number(jdsx)";
        List<HashMap<String, String>> jddmTjList = dao.getList(sql,
                new String[]{}, new String[]{"dm", "mc"});
        this.jddmTjList = jddmTjList;
    }

    // ======================党团建设阶段代码条件列表===============================

    /**
     * *************************************************************************
     * ****************************************************
     */
    /**
     * ************************************************学校个性化********************
     * *****************************
     */
    /**
     * *************************************************************************
     * ****************************************************
     */

    // ======================家庭困难条件列表===============================
    public List<HashMap<String, String>> getJtknlxTjList() {

        return jtknlxTjList;
    }

    public void setJtknlxTjList() {
        DAO dao = DAO.getInstance();
        String sql = "select dm,mc from xg_xsxx_jtknlxb order by to_number(dm)";
        List<HashMap<String, String>> jtknlxTjList = dao.getList(sql,
                new String[]{}, new String[]{"dm", "mc"});
        this.jtknlxTjList = jtknlxTjList;
    }

    // ======================家庭困难条件列表 end===========================

    // ======================家庭困难条件列表===============================
    public List<HashMap<String, String>> getShztlxTjList() {

        return shztlxTjList;
    }

    public void setShztlxTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = new String[]{"无需审核", "存在未审核", "存在需重审", "全部审核通过"};
        String[] mc = new String[]{"无需审核", "存在未审核", "存在需重审", "全部审核通过"};
        this.shztlxTjList = dao.arrayToList(dm, mc);
    }

    // ======================家庭困难条件列表 end===========================

    // ======================【贵州大学】文明寝室审核条件列表===============================
    public List<HashMap<String, String>> getShzt1TjList() {
        return shzt1TjList;
    }

    public void setShzt1TjList() {
        List<HashMap<String, String>> shzt1TjList = getGyglSelectList("shzt1");
        this.shzt1TjList = shzt1TjList;
    }

    public List<HashMap<String, String>> getShzt2TjList() {
        return shzt2TjList;
    }

    public void setShzt2TjList() {
        List<HashMap<String, String>> shzt2TjList = getGyglSelectList("shzt1");
        this.shzt2TjList = shzt2TjList;
    }

    // ======================【贵州大学】文明寝室审核条件列表 end===============================

    // =========================学生资助 begin==============================
    public List<HashMap<String, String>> getShzt4TjList() {
        return shzt4TjList;
    }

    public void setShzt4TjList() {
        List<HashMap<String, String>> shzt4TjList = getXszzSelectList("shzt4");
        this.shzt4TjList = shzt4TjList;
    }

    public List<HashMap<String, String>> getShzt5TjList() {
        return shzt5TjList;
    }

    public void setShzt5TjList() {
        List<HashMap<String, String>> shzt5TjList = getXszzSelectList("shzt5");
        this.shzt5TjList = shzt5TjList;
    }

    public List<HashMap<String, String>> getZzxmlbTjList() {
        return zzxmlbTjList;
    }

    public void setZzxmlbTjList() {
        DAO dao = DAO.getInstance();
        String sql = "select lbdm dm,lbmc mc from xg_xszz_new_zzxmlbb order by to_number(lbdm)";
        List<HashMap<String, String>> zzxmlbTjList = dao.getList(sql,
                new String[]{}, new String[]{"dm", "mc"});
        this.zzxmlbTjList = zzxmlbTjList;
    }

    // ====================勤工助学 start=================================

    public List<HashMap<String, String>> getGwztTjList() {
        return gwztTjList;
    }

    public void setGwztTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"已提交", "未提交"};
        String[] mc = {"已提交", "未提交"};
        this.gwztTjList = dao.arrayToList(dm, mc);
    }

    public List<HashMap<String, String>> getGwxzTjList() {
        return gwxzTjList;
    }

    public void setGwxzTjList() {
        DAO dao = DAO.getInstance();
        String sql = "select gwxzdm dm,gwxzmc mc from xg_qgzx_gwxzdmb";
        this.gwxzTjList = dao.getList(sql, new String[]{}, new String[]{
                "dm", "mc"});
    }

    public List<HashMap<String, String>> getZgztTjList() {
        return zgztTjList;
    }

    public void setZgztTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"在岗", "已离职"};
        String[] mc = {"在岗", "已离职"};
        this.zgztTjList = dao.arrayToList(dm, mc);
    }

    public List<HashMap<String, Object>> getQgbmTjList() {
        return qgbmTjList;
    }

    public void setQgbmTjList(List<HashMap<String, Object>> qgbmTjList) {
        this.qgbmTjList = qgbmTjList;
    }

    public List<HashMap<String, String>> getSfknsTjList() {
        return sfknsTjList;
    }

    public void setSfknsTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"是", "否"};
        String[] mc = {"是", "否"};
        this.sfknsTjList = dao.arrayToList(dm, mc);
    }

    public List<HashMap<String, String>> getQgshztTjList() {
        return qgshztTjList;
    }

    public void setQgshztTjList() {
        this.qgshztTjList = getCommSelectList("qgshzt");
    }

    public List<HashMap<String, String>> getQgxssqTjList() {
        return qgxssqTjList;
    }

    public void setQgxssqTjList() {
        this.qgxssqTjList = getCommSelectList("qgxssq");
    }


    /**
     * @description    ： TODO
     * @author ： CP（1352）
     * @date        ： 2018-1-8 上午11:28:11
     * @return        : the gfqkflTjList
     */
    public List<HashMap<String, String>> getGfqkflTjList() {
        return gfqkflTjList;
    }

    /**
     * @param ：gfqkflTjList the gfqkflTjList to set
     * @description    ： TODO
     * @author ： CP（1352）
     * @date        ： 2018-1-8 上午11:28:11
     */
    public void setGfqkflTjList() {
        DAO dao = DAO.getInstance();

        String[] dm = {"1", "2", "3", "4", "5", "6"};
        String[] mc = {"兵役登记情况", "参军入伍情况", "退伍复学情况", "评奖评优", "奖惩情况", "参加活动情况"};

        // 勤工助学所用部门信息
//		String sql = "select distinct (t1.gfqkfl) dm ,decode(t1.gfqkfl,1,'兵役登记情况',2,'参军入伍情况',3,'退伍复学情况',4,'评奖评优',5,'奖惩情况',6,'参加活动情况') mc  from xg_gfjy_gfjyqkjgb t1";
//		List<HashMap<String, String>> gfqkflTjList = dao.getList(sql,
//				new String[] {}, new String[] { "dm", "mc" });
        this.gfqkflTjList = dao.arrayToList(dm, mc);
    }

    public List<HashMap<String, String>> getQgsybmTjList() {
        return qgsybmTjList;
    }

    public void setQgsybmTjList() {

        DAO dao = DAO.getInstance();

        // 勤工助学所用部门信息
        String sql = "select distinct(yrdwdm) dm,yrdwmc mc from view_xg_qgzx_gwxxb where yrdwmc is not null order by yrdwdm";

        List<HashMap<String, String>> qgsybmList = dao.getList(sql,
                new String[]{}, new String[]{"dm", "mc"});

        this.qgsybmTjList = qgsybmList;
    }

    public List<HashMap<String, String>> getQgsybmsqTjList() {
        return qgsybmsqTjList;
    }

    public void setQgsybmsqTjList() {

        DAO dao = DAO.getInstance();

        // 勤工助学所用部门信息
        String sql = "select distinct(yrdwdm) dm,yrdwmc mc from view_xg_qgzx_gwxxsqb where yrdwmc is not null order by yrdwdm";

        List<HashMap<String, String>> qgsybmsqTjList = dao.getList(sql,
                new String[]{}, new String[]{"dm", "mc"});

        this.qgsybmsqTjList = qgsybmsqTjList;
    }

    // ====================勤工助学 end===================================

    // ====================心理健康咨询start===================================
    public List<HashMap<String, String>> getZgstatusTjList() {
        return zgstatusTjList;
    }

    public void setZgstatusTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"1", "2"};
        String[] mc = {"在岗", "不在岗"};
        this.zgstatusTjList = dao.arrayToList(dm, mc);
    }

    public List<HashMap<String, String>> getYystatusTjList() {
        return yystatusTjList;
    }

    public void setYystatusTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"1", "2", "3", "4", "5", "6"};
        String[] mc = {"预约中", "预约成功", "预约中-学生取消", "预约成功-学生取消", "预约失败", "已过期"};
        this.yystatusTjList = dao.arrayToList(dm, mc);
    }

    public List<HashMap<String, String>> getGzztTjList() {
        return gzztTjList;
    }

    public void setGzztTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"1", "2"};
        String[] mc = {"关注", "取消关注"};
        this.gzztTjList = dao.arrayToList(dm, mc);
    }

    /**
     * @description    ： TODO
     * @author ： CP（1352）
     * @date        ： 2017-12-28 上午10:15:02
     * @return        : the ybqkTjList
     */
    public List<HashMap<String, String>> getYbqkTjList() {
        return ybqkTjList;
    }

    /**
     * @param ：ybqkTjList the ybqkTjList to set
     * @description    ： TODO
     * @author ： CP（1352）
     * @date        ： 2017-12-28 上午10:15:02
     */
    public void setYbqkTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"未上报", "无问题", "已上报"};
        String[] mc = {"未上报", "无问题", "已上报"};
        this.ybqkTjList = dao.arrayToList(dm, mc);
    }

    public List<HashMap<String, String>> getWeekdayTjList() {
        return weekdayTjList;
    }

    public void setWeekdayTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"1", "2", "3", "4", "5", "6", "7"};
        String[] mc = {"星期一", "星期二", "星期三", "星期四", "星期五", "星期六", "星期日"};
        this.weekdayTjList = dao.arrayToList(dm, mc);
    }

    // ====================心理健康咨询end===================================

    // ====================军训管理 start=================================

    public List<HashMap<String, String>> getJxnjTjList() {
        return jxnjTjList;
    }

    public void setJxnjTjList() {
        DAO dao = DAO.getInstance();
        String sql = "select distinct nj dm,nj mc from view_xsjbxx where nj is not null order by nj";
        this.jxnjTjList = dao.getList(sql, new String[]{}, new String[]{
                "dm", "mc"});
    }

    public List<HashMap<String, String>> getJxztTjList() {
        return jxztTjList;
    }

    public void setJxztTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"运行", "停止"};
        String[] mc = {"运行", "停止"};
        this.jxztTjList = dao.arrayToList(dm, mc);
    }

    public List<HashMap<String, String>> getCxqkTjList() {
        return cxqkTjList;
    }

    public void setCxqkTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"缓训", "免训", "参训"};
        String[] mc = {"缓训", "免训", "参训"};
        this.cxqkTjList = dao.arrayToList(dm, mc);
    }

    /**
     * @description    ： TODO
     * @author ： CP（1352）
     * @date        ： 2018-2-9 上午11:07:36
     * @return        : the stztTjList
     */
    public List<HashMap<String, String>> getStztTjList() {
        return stztTjList;
    }

    /**
     * @param ：stztTjList the stztTjList to set
     * @description    ： TODO
     * @author ： CP（1352）
     * @date        ： 2018-2-9 上午11:07:36
     */
    public void setStztTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"正式", "预备期", "已注销"};
        this.stztTjList = dao.arrayToList(dm, dm);
    }

    public List<HashMap<String, String>> getJxxxTjList() {
        return jxxxTjList;
    }

    public void setJxxxTjList() {
        DAO dao = DAO.getInstance();
        String sql = "select Jxmc dm,jxmc mc from xg_jxgl_jxxxwhb order by cjsj";
        this.jxxxTjList = dao.getList(sql, new String[]{}, new String[]{
                "dm", "mc"});
    }

    public List<HashMap<String, String>> getBxlbTjList() {
        return bxlbTjList;
    }

    public void setBxlbTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"个人荣誉", "日常表现"};
        String[] mc = {"个人荣誉", "日常表现"};
        this.bxlbTjList = dao.arrayToList(dm, mc);
    }

    public List<HashMap<String, String>> getJtbxTjList() {
        return jtbxTjList;
    }

    public void setJtbxTjList() {
        DAO dao = DAO.getInstance();
        String sql = "select mc dm,mc from xg_jxgl_grrydmb union all select mc dm,mc from xg_jxgl_rcbxdmb";
        this.jtbxTjList = dao.getList(sql, new String[]{}, new String[]{
                "dm", "mc"});
    }

    public List<HashMap<String, String>> getSfhdTjList() {
        return sfhdTjList;
    }

    public void setSfhdTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"是", "否"};
        String[] mc = {"是", "否"};
        this.sfhdTjList = dao.arrayToList(dm, mc);
    }

    public List<HashMap<String, String>> getSfybzTjList() {
        return sfybzTjList;
    }

    public void setSfybzTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"是", "否"};
        String[] mc = {"是", "否"};
        this.sfybzTjList = dao.arrayToList(dm, mc);
    }

    // ====================军训管理 end===================================

    // ========================思政队伍 begin ============================

    public List<HashMap<String, String>> getYhsfTjList() {

        return yhsfTjList;
    }

    public void setYhsfTjList() {

        DAO dao = DAO.getInstance();

        String[] dm = new String[]{"校级用户", "院级用户", "班级用户", "兼任学校用户", "兼任院系用户"};
        String[] mc = new String[]{"校级用户", "院级用户", "班级用户", "兼任学校用户", "兼任院系用户"};

        this.yhsfTjList = dao.arrayToList(dm, mc);
    }

    public List<HashMap<String, String>> getBbztTjList() {
        return bbztTjList;
    }

    public void setBbztTjList() {
        DAO dao = DAO.getInstance();

        String[] dm = new String[]{"1", "2"};
        String[] mc = new String[]{"已编班", "未编班"};
        this.bbztTjList = dao.arrayToList(dm, mc);
    }

    public List<HashMap<String, String>> getZwTjList() {

        return zwTjList;
    }

    public void setZwTjList() {
        DAO dao = DAO.getInstance();

        String sql = "select zwdm dm,zwmc mc from zwb";

        this.zwTjList = dao.getList(sql, new String[]{}, new String[]{"dm",
                "mc"});
    }

    // ========================思政队伍 end ================================
    /**
     * *************************************************************************
     * ****************************************************
     */
    /**
     * ************************************************为解决65535问题，高级查询重构，相关方法***
     * **********************************************
     */
    /**
     * *************************************************************************
     * ****************************************************
     */

    /**
     * 获得无需维护下拉框值（公用）
     *
     * @author luojw
     */
    private List<HashMap<String, String>> getCommSelectList(String lx) {

        DAO dao = DAO.getInstance();

        String[] dm = null;
        String[] mc = null;

        if ("shzt".equalsIgnoreCase(lx)) {// 审核状态
            // dm = new String[] { "未审核", "通过", "不通过", "退回", "需重审" };
            // mc = new String[] { "未审核", "通过", "不通过", "退回", "需重审" };
            dm = new String[]{"未提交", "通过", "不通过", "退回", "审核中"};
            mc = new String[]{"未提交", "通过", "不通过", "退回", "审核中"};
        } else if ("shjg".equalsIgnoreCase(lx)) {// 审核结果
            dm = new String[]{"未审核", "审核中", "通过", "不通过", "退回"};
            mc = new String[]{"未审核", "审核中", "通过", "不通过", "退回"};
        } else if ("xblx".equalsIgnoreCase(lx)) {
            dm = new String[]{"男", "女"};
            mc = new String[]{"男", "女"};
        } else if ("sflx".equalsIgnoreCase(lx)) {
            dm = new String[]{"是", "否"};
            mc = new String[]{"是", "否"};
        } else if ("sf".equalsIgnoreCase(lx)) {
            dm = new String[]{"是", "否"};
            mc = new String[]{"是", "否"};
        } else if ("sfhg".equalsIgnoreCase(lx)) {
            dm = new String[]{"合格", "不合格"};
            mc = new String[]{"合格", "不合格"};
        } else if ("shjg2".equalsIgnoreCase(lx)) {// 审核结果
            dm = new String[]{"未审核", "审核中", "通过", "不通过", "无需审核"};
            mc = new String[]{"未审核", "审核中", "通过", "不通过", "无需审核"};
        } else if ("qgshzt".equalsIgnoreCase(lx)) {
            dm = new String[]{"未审核", "通过", "不通过", "退回"};
            mc = new String[]{"未审核", "通过", "不通过", "退回"};
        } else if ("shztdm".equalsIgnoreCase(lx)) {
            dm = new String[]{"wsh", "tg", "btg", "th"};
            mc = new String[]{"未审核", "通过", "不通过", "退回"};
        } else if ("rcxwshzt".equalsIgnoreCase(lx)) {
            dm = new String[]{"未审核", "通过", "不通过", "审核中", "无需审核", "退回"};
            mc = new String[]{"未审核", "通过", "不通过", "审核中", "无需审核", "退回"};
        } else if ("qgxssq".equalsIgnoreCase(lx)) {
            dm = new String[]{"待审核", "通过", "不通过", "退回", "需重审", "审核中", "其它"};
            mc = new String[]{"待审核", "通过", "不通过", "退回", "需重审", "审核中", "其它"};
        } else if ("ydlx".equalsIgnoreCase(lx)) {
            dm = new String[]{"00", "01", "02"};
            mc = new String[]{"退宿", "宿舍调整", "实习留宿"};
        } else if ("ylbxzt".equalsIgnoreCase(lx)) {
            dm = new String[]{"wcb", "wjm", "yjm", "ygb"};
            mc = new String[]{"未参保", "未交满", "已交满", "已过保"};
        } else if ("sfsfs".equalsIgnoreCase(lx)) {
            dm = new String[]{"非师范生", "师范生"};
            mc = new String[]{"非师范生", "师范生"};
        } else if ("ffzt".equalsIgnoreCase(lx)) {
            dm = new String[]{"1", "0", "-1"};
            mc = new String[]{"正常发放", "终止发放", "暂停发放"};
        } else if ("tnzt".equalsIgnoreCase(lx)) {
            dm = new String[]{"正常", "退团"};
            mc = new String[]{"正常", "退团"};
        } else if ("shjg3".equalsIgnoreCase(lx)) {// 审核结果
            dm = new String[]{"0", "5", "1", "2", "3"};
            mc = new String[]{"未审核", "审核中", "通过", "不通过", "退回"};
        }

        return dao.arrayToList(dm, mc);
    }

    /**
     * 获得无需维护下拉框值（学生信息）
     *
     * @author luojw
     */
    public List<HashMap<String, String>> getXsxxSelectList(String lx) {

        DAO dao = DAO.getInstance();

        String[] dm = null;
        String[] mc = null;

        if ("sfzx".equalsIgnoreCase(lx)) {// 是否在校
            dm = new String[]{"在校", "不在校"};
            mc = new String[]{"在校", "不在校"};
        } else if ("sfjf".equalsIgnoreCase(lx)) {// 是否缴费
            dm = new String[]{"已缴", "未缴"};
            mc = new String[]{"已缴", "未缴"};
        } else if ("zdlb".equalsIgnoreCase(lx)) {// 转档类别
            dm = new String[]{"毕业", "升学", "转学", "退学"};
            mc = new String[]{"毕业", "升学", "转学", "退学"};
        } else if ("qyzt".equalsIgnoreCase(lx)) {
            dm = new String[]{"1", "0"};
            mc = new String[]{"启用", "停用"};
        } else if ("whzt".equalsIgnoreCase(lx)) {
            dm = new String[]{"已维护", "未维护"};
            mc = new String[]{"已维护", "未维护"};
        }

        return dao.arrayToList(dm, mc);
    }

    /**
     * 获得无需维护下拉框值（日常事务）
     *
     * @author luojw
     */
    public List<HashMap<String, String>> getRcswSelectList(String lx) {

        DAO dao = DAO.getInstance();

        String[] dm = null;
        String[] mc = null;

        if ("sftz".equalsIgnoreCase(lx)) {// 是否通知
            dm = new String[]{"已通知", "未通知"};
            mc = new String[]{"已通知", "未通知"};
        }

        if ("sqjg".equalsIgnoreCase(lx)) {// 申请结果1
            dm = new String[]{"未审核", "审核中", "审核通过", "审核不通过"};
            mc = new String[]{"未审核", "审核中", "审核通过", "审核不通过"};
        }

        if ("qjlb".equalsIgnoreCase(lx)) {// 申请结果1
            dm = new String[]{"事假", "病假"};
            mc = new String[]{"事假", "病假"};
        }
        return dao.arrayToList(dm, mc);
    }

    private List<HashMap<String, String>> getQgzxSelectList(String lx) {
        DAO dao = DAO.getInstance();

        String[] dm = null;
        String[] mc = null;

        if ("yf".equalsIgnoreCase(lx)) {// 申请结果1
            dm = new String[]{"01", "02", "03", "04", "05", "06", "07", "08",
                    "09", "10", "11", "12"};
            mc = new String[]{"1月", "2月", "3月", "4月", "5月", "6月", "7月", "8月",
                    "9月", "10月", "11月", "12月"};
        }
        return dao.arrayToList(dm, mc);
    }

    /**
     * 获得无需维护下拉框值（评奖评优）
     *
     * @author qlj
     */
    public List<HashMap<String, String>> getPjpySelectList(String lx) {

        DAO dao = DAO.getInstance();

        String[] dm = null;
        String[] mc = null;

        if ("sfpf".equalsIgnoreCase(lx)) {// 是否评分
            dm = new String[]{"是", "否", "重新评分"};
            mc = new String[]{"是", "否", "重新评分"};
        } else if ("sfqr".equalsIgnoreCase(lx)) {// 是否确认
            dm = new String[]{"是", "否", "退回"};
            mc = new String[]{"是", "否", "退回"};
        } else if ("shzt3".equalsIgnoreCase(lx)) {// 评奖评优
            dm = new String[]{"未审核", "审核通过", "审核不通过", "审核中", "无需审核"};
            mc = new String[]{"未审核", "审核通过", "审核不通过", "审核中", "无需审核"};
        } else if ("xmlx".equalsIgnoreCase(lx)) {// 项目类型
            dm = new String[]{"01", "02"};
            mc = new String[]{"奖学金", "荣誉称号"};
        } else if ("jsfs".equalsIgnoreCase(lx)) {
            dm = new String[]{"1", "2"};
            mc = new String[]{"个人", "团体"};
        }

        return dao.arrayToList(dm, mc);
    }

    /**
     * 获得无需维护下拉框值（公寓管理）
     *
     * @author luojw
     */
    public List<HashMap<String, String>> getGyglSelectList(String lx) {

        DAO dao = DAO.getInstance();

        String[] dm = null;
        String[] mc = null;

        if ("ldxb".equalsIgnoreCase(lx)) {// 楼栋性别
            dm = new String[]{"男", "女", "混住"};
            mc = new String[]{"男", "女", "混住"};
        } else if ("sfrz".equalsIgnoreCase(lx)) {// 是否入住
            dm = new String[]{"是", "否"};
            mc = new String[]{"已入住", "未入住"};
        } else if ("sffp".equalsIgnoreCase(lx)) {// 是否分配
            dm = new String[]{"是", "否"};
            mc = new String[]{"已分配", "未分配"};
        } else if ("jjcd".equalsIgnoreCase(lx)) {// 紧急程度
            dm = new String[]{"紧急", "一般"};
            mc = new String[]{"紧急", "一般"};
        } else if ("clzt".equalsIgnoreCase(lx)) {// 处理状态
            dm = new String[]{"未处理", "已处理", "暂不处理", "不处理"};
            mc = new String[]{"未处理", "已处理", "暂不处理", "不处理"};
        } else if ("rzqk".equalsIgnoreCase(lx)) {// 入住情况
            dm = new String[]{"满员", "缺额", "全空"};
            mc = new String[]{"满员", "缺额", "全空"};
        } else if ("gygllx".equalsIgnoreCase(lx)) {// 入住情况
            dm = new String[]{"楼长", "层长", "寝室长"};
            mc = new String[]{"楼长", "层长", "寝室长"};
        } else if ("shzt1".equalsIgnoreCase(lx)) {// 文明寝室辅导员/学校审核状态
            dm = new String[]{"未审核", "通过", "不通过"};
            mc = new String[]{"未审核", "通过", "不通过"};
        } else if ("clztdm".equalsIgnoreCase(lx)) {// 处理状态
            dm = new String[]{"wcl", "ycl", "bcl"};
            mc = new String[]{"未处理", "已处理", "不处理"};
        } else if ("jclx".equalsIgnoreCase(lx)) {
            dm = new String[]{"0", "1", "2"};
            mc = new String[]{"分数", "等级", "星级"};
        }

        return dao.arrayToList(dm, mc);
    }

    /**
     * 获得无需维护下拉框值（学生资助）
     *
     * @author luojw
     */
    public List<HashMap<String, String>> getXszzSelectList(String lx) {

        DAO dao = DAO.getInstance();

        String[] dm = null;
        String[] mc = null;

        if ("shzt4".equalsIgnoreCase(lx)) {// 审核状态4
            dm = new String[]{"未审核", "需重审", "通过", "不通过", "退回"};
            mc = new String[]{"未审核", "需重审", "通过", "不通过", "退回"};
        } else if ("shzt5".equalsIgnoreCase(lx)) {// 审核状态5
            dm = new String[]{"未审核", "需重审", "通过", "不通过", "退回"};
            mc = new String[]{"未审核", "需重审", "通过", "不通过", "退回"};
        }
        return dao.arrayToList(dm, mc);
    }

    /**
     * 获得无需维护下拉框值（违纪处分）
     *
     * @author qlj
     */
    public List<HashMap<String, String>> getWjcfSelectList(String lx) {

        DAO dao = DAO.getInstance();

        String[] dm = null;
        String[] mc = null;

        if ("ssjg".equalsIgnoreCase(lx)) {// 申述结果
            dm = new String[]{"wsh", "shz", "wcycf", "cxcf", "ggcf"};
            mc = new String[]{"未审核", "审核中", "维持原处分", "撤销处分", "更改处分"};
        }
        return dao.arrayToList(dm, mc);
    }

    public List<HashMap<String, String>> getZbryTjList() {
        DAO dao = DAO.getInstance();
        String sql = "select zbrydm dm,zbrymc mc from zbrydmb";

        zbryTjList = dao.getList(sql, new String[]{}, new String[]{"dm",
                "mc"});

        return zbryTjList;
    }

    /**
     * 获得審核狀態相關列表
     *
     * @date 2013-01-28
     * @author luojw
     */
    private List<HashMap<String, String>> getShztSelectList(String lx) {

        DAO dao = DAO.getInstance();

        String[] dm = null;
        String[] mc = null;

        if ("one".equalsIgnoreCase(lx)) {// 审核结果
            dm = new String[]{"退回", "未审核", "审核中", "通过"};
            mc = new String[]{"退回", "未审核", "审核中", "通过"};
        } else if ("two".equalsIgnoreCase(lx)) {// 审核状态
            dm = new String[]{"未审核", "需重审"};
            mc = new String[]{"未审核", "需重审"};
        }

        return dao.arrayToList(dm, mc);
    }

    /**
     * *************************************************************************
     * ****************************************************
     */
    /**
     * *********************************************************
     * end*************************************************************** /**
     * ***
     * ***********************************************************************
     * ***************************************************
     */

    public String getMc() {
        return mc;
    }

    public void setMc(String mc) {
        this.mc = mc;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getSsmk() {
        return ssmk;
    }

    public void setSsmk(String ssmk) {
        this.ssmk = ssmk;
    }

    public String getTj() {
        return tj;
    }

    public void setTj(String tj) {
        this.tj = tj;
    }

    public List<HashMap<String, String>> getNjTjList() {
        return njTjList;
    }

    public void setNjTjList(List<HashMap<String, String>> njTjList) {
        this.njTjList = njTjList;
    }

    public List<HashMap<String, Object>> getXyTjList() {
        return xyTjList;
    }

    public void setXyTjList(List<HashMap<String, Object>> xyTjList) {
        this.xyTjList = xyTjList;
    }

    public List<HashMap<String, Object>> getZyTjList() {
        return zyTjList;
    }

    public void setZyTjList(List<HashMap<String, Object>> zyTjList) {
        this.zyTjList = zyTjList;
    }

    public List<HashMap<String, Object>> getBjTjList() {
        return bjTjList;
    }

    public void setBjTjList(List<HashMap<String, Object>> bjTjList) {
        this.bjTjList = bjTjList;
    }

    public static SearchForm getSearchOptionModel() {
        return searchOptionModel;
    }

    public static void setSearchOptionModel(SearchForm searchOptionModel) {
        SearchForm.searchOptionModel = searchOptionModel;
    }

    public String[] getSpTj() {
        return spTj;
    }

    public void setSpTj(String[] spTj) {
        this.spTj = spTj;
    }

    public String[] getLdTj() {
        return ldTj;
    }

    public void setLdTj(String[] ldTj) {
        this.ldTj = ldTj;
    }

    public String getStylePath() {
        return stylePath;
    }

    public void setStylePath(String stylePath) {
        this.stylePath = stylePath;
    }

    public List<HashMap<String, Object>> getChTjList() {
        return chTjList;
    }

    public void setChTjList(List<HashMap<String, Object>> chTjList) {
        this.chTjList = chTjList;
    }

    public List<HashMap<String, Object>> getYqdmTjList() {
        return yqdmTjList;
    }

    public void setYqdmTjList(List<HashMap<String, Object>> yqdmTjList) {
        this.yqdmTjList = yqdmTjList;
    }

    public List<HashMap<String, Object>> getXqdmTjList() {
        return xqdmTjList;
    }

    public void setXqdmTjList(List<HashMap<String, Object>> xqdmTjList) {
        this.xqdmTjList = xqdmTjList;
    }

    public List<HashMap<String, Object>> getLdTjList() {
        return ldTjList;
    }

    public void setLdTjList(List<HashMap<String, Object>> ldTjList) {
        this.ldTjList = ldTjList;
    }

    public List<HashMap<String, Object>> getQshTjList() {
        return qshTjList;
    }

    public void setQshTjList(List<HashMap<String, Object>> qshTjList) {
        this.qshTjList = qshTjList;
    }

    public void setQjlxTjList() {

        DAO dao = DAO.getInstance();
        String sql = "select id dm,lxmc mc from xg_rcsw_qjgl_qjlxb";
        List<HashMap<String, String>> qjlxtjList = dao.getList(sql,
                new String[]{}, new String[]{"dm", "mc"});
        this.qjlxTjList = qjlxtjList;
    }

    public List<HashMap<String, String>> getQjlxTjList() {
        return qjlxTjList;
    }

    public void setQjlbTjList() {

        this.qjlbTjList = getRcswSelectList("qjlb");
    }

    public List<HashMap<String, String>> getQjlbTjList() {
        return qjlbTjList;
    }

    public List<HashMap<String, String>> getYfTjList() {
        return yfTjList;
    }

    public void setYfTjList() {
        this.yfTjList = getQgzxSelectList("yf");
    }

    public List<HashMap<String, String>> getQyztTjList() {
        return qyztTjList;
    }

    public void setQyztTjList() {
        List<HashMap<String, String>> qyztTjList = getXsxxSelectList("qyzt");
        this.qyztTjList = qyztTjList;
    }

    public List<HashMap<String, String>> getWhztTjList() {
        return whztTjList;
    }

    public void setWhztTjList() {
        List<HashMap<String, String>> whztTjList = getXsxxSelectList("whzt");
        this.whztTjList = whztTjList;
    }

    public List<HashMap<String, String>> getSfzblxTjList() {
        return sfzblxTjList;
    }

    public void setSfzblxTjList() {
        DAO dao = DAO.getInstance();

        String[] en = {"征兵离校", "征兵未离校"};

        this.sfzblxTjList = dao.arrayToList(en, en);
    }

    public List<HashMap<String, Object>> getTidTjList() {
        return tidTjList;
    }

    public void setTidTjList(List<HashMap<String, Object>> tidTjList) {
        this.tidTjList = tidTjList;
    }

    public List<HashMap<String, Object>> getYidTjList() {
        return yidTjList;
    }

    public void setYidTjList(List<HashMap<String, Object>> yidTjList) {
        this.yidTjList = yidTjList;
    }

    public List<HashMap<String, Object>> getLidTjList() {
        return lidTjList;
    }

    public void setLidTjList(List<HashMap<String, Object>> lidTjList) {
        this.lidTjList = lidTjList;
    }

    public List<HashMap<String, Object>> getPidTjList() {
        return pidTjList;
    }

    public void setPidTjList(List<HashMap<String, Object>> pidTjList) {
        this.pidTjList = pidTjList;
    }

    public List<HashMap<String, Object>> getSsidTjList() {
        return ssidTjList;
    }

    public void setSsidTjList(List<HashMap<String, Object>> ssidTjList) {
        this.ssidTjList = ssidTjList;
    }

    public List<HashMap<String, Object>> getBidTjList() {
        return bidTjList;
    }

    public void setBidTjList(List<HashMap<String, Object>> bidTjList) {
        this.bidTjList = bidTjList;
    }

    public List<HashMap<String, String>> getJxdmList() {
        return jxdmList;
    }

    public void setJxdmList(List<HashMap<String, String>> jxdmList) {
        this.jxdmList = jxdmList;
    }


    public void setZzmmTjList() {
        DAO dao = DAO.getInstance();

        String sql = "select zzmmdm dm,zzmmmc mc from zzmmdmb";

        this.zzmmTjList = dao.getList(sql, new String[]{}, new String[]{
                "dm", "mc"});

    }

    public List<HashMap<String, String>> getZzmmTjList() {
        return zzmmTjList;
    }

    public List<HashMap<String, String>> getRddcTjList() {
        return rddcTjList;
    }

    public void setRddcTjList() {
        DAO dao = DAO.getInstance();
        String sql = "select dcdm dm,dcmc mc from xg_xszz_new_kndcdmb";

        this.rddcTjList = dao.getListNotOut(sql, new String[]{});
    }

    /**
     * @return the sjdcTjList
     */
    public List<HashMap<String, String>> getSjdcTjList() {
        return sjdcTjList;
    }

    /**
     * @param sjdcTjList要设置的 sjdcTjList
     */
    public void setSjdcTjList() {
        DAO dao = DAO.getInstance();
        String sql = "select dcdm dm,dcmc mc from xg_xszz_new_kndcdmb";

        this.sjdcTjList = dao.getListNotOut(sql, new String[]{});
    }

    public void setXlTjList() {
        DAO dao = DAO.getInstance();

        String[] dm = new String[]{"大专", "本科", "硕士", "博士", "博士后"};
        String[] mc = new String[]{"大专", "本科", "硕士", "博士", "博士后"};

        this.xlTjList = dao.arrayToList(dm, mc);
    }

    public List<HashMap<String, String>> getXlTjList() {
        return xlTjList;
    }

    public void setXwTjList() {
        DAO dao = DAO.getInstance();

        String sql = "select distinct xw dm,xw mc from fdyxxb where xw is not null";

        this.xwTjList = dao.getList(sql, new String[]{}, new String[]{"dm",
                "mc"});
    }

    public List<HashMap<String, String>> getXwTjList() {
        return xwTjList;
    }

    public void setZcTjList() {
        DAO dao = DAO.getInstance();
        String sql = null;
        if ("11318".equals(Base.xxdm)) {
            sql = "select distinct zcdm dm,zcmc mc from zcb ";
        } else {
            sql = "select distinct zc dm,zc mc from fdyxxb  where zc is not null ";
        }

        this.zcTjList = dao.getList(sql, new String[]{}, new String[]{"dm",
                "mc"});

    }

    public List<HashMap<String, String>> getZcTjList() {
        return zcTjList;
    }

    public void setJhshztTjList() {
        DAO dao = DAO.getInstance();
        String sql = "select distinct shzt dm,decode(shzt,'wsh','未审核','tg','通过','btg','不通过','审核中') mc from xg_xszz_jhzy_knssqb";
        this.jhshztTjList = dao.getList(sql, new String[]{}, new String[]{
                "dm", "mc"});
    }

    public List<HashMap<String, String>> getJhshztTjList() {
        return jhshztTjList;
    }

    public void setTjdcTjList() {
        DAO dao = DAO.getInstance();
        String sql = "select distinct xxtjdc dm,xxtjdc mc from xg_xszz_jhzy_knssqb where xxtjdc is not null";
        this.tjdcTjList = dao.getList(sql, new String[]{}, new String[]{
                "dm", "mc"});
    }

    public List<HashMap<String, String>> getTjdcTjList() {
        return tjdcTjList;
    }

    public void setZxjtjdcTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = new String[]{"一等", "二等"};
        String[] mc = new String[]{"一等", "二等"};
        this.zxjtjdcTjList = dao.arrayToList(dm, mc);
    }

    public List<HashMap<String, String>> getZxjtjdcTjList() {
        return zxjtjdcTjList;
    }

    public List<HashMap<String, String>> getZhztTjList() {
        return zhztTjList;
    }

    public void setZhztTjList() {

        OptionUtil optionUtil = new OptionUtil();

        this.zhztTjList = optionUtil.getOptions(OptionUtil.SHZT);
    }

    public List<HashMap<String, String>> getQzxgztTjList() {
        return qzxgztTjList;
    }

    public void setQzxgztTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = new String[]{"wfp", "wxg", "yxg"};
        String[] mc = new String[]{"未分配", "未修改", "已修改"};
        this.qzxgztTjList = dao.arrayToList(dm, mc);
    }

    public List<HashMap<String, String>> getZzxmTjList() {
        return zzxmTjList;
    }

    public void setZzxmTjList() {
        DAO dao = DAO.getInstance();
        StringBuffer sql = new StringBuffer();
        sql.append(" select distinct xmmc dm, xmmc mc from xg_xszz_new_zzxmdmb order by xmmc ");

        this.zzxmTjList = dao.getListNotOut(sql.toString(), new String[]{});
    }

    public List<HashMap<String, String>> getZzxm1TjList() {
        return zzxm1TjList;
    }

    public void setZzxm1TjList() {
        DAO dao = DAO.getInstance();
        StringBuffer sql = new StringBuffer();
        sql.append(" select distinct xmmc dm, xmmc mc from xg_xszz_new_zzxmdmb order by xmmc ");

        this.zzxm1TjList = dao.getListNotOut(sql.toString(), new String[]{});
    }

    public List<HashMap<String, String>> getPfztTjList() {
        return pfztTjList;
    }

    public void setPfztTjList() {

        DAO dao = DAO.getInstance();
        String[] dm = new String[]{"是", "否"};
        String[] mc = new String[]{"已评完", "未评完"};
        this.pfztTjList = dao.arrayToList(dm, mc);
    }


    public List<HashMap<String, Object>> getShengTjList() {
        return shengTjList;
    }

    public void setShengTjList(List<HashMap<String, Object>> shengTjList) {
        this.shengTjList = shengTjList;
    }

    public List<HashMap<String, Object>> getShiTjList() {
        return shiTjList;
    }

    public void setShiTjList(List<HashMap<String, Object>> shiTjList) {
        this.shiTjList = shiTjList;
    }

    public List<HashMap<String, Object>> getQuTjList() {
        return quTjList;
    }

    public void setQuTjList(List<HashMap<String, Object>> quTjList) {
        this.quTjList = quTjList;
    }

    public void setCxdjdmTjList() {
        DAO dao = DAO.getInstance();
        String sql = "select cxdjdm dm,cxdjmc mc from xsxx_cxdjdmb";
        this.cxdjdmTjList = dao.getListNotOut(sql, new String[]{});
    }

    public List<HashMap<String, String>> getCxdjdmTjList() {
        return cxdjdmTjList;
    }

    public List<HashMap<String, String>> getTjztTjList() {
        return tjztTjList;
    }

    public void setSfzsTjList() {
        List<HashMap<String, String>> sfzsTjList = getCommSelectList("sf");
        this.sfzsTjList = sfzsTjList;
    }

    public List<HashMap<String, String>> getSfzsTjList() {
        return sfzsTjList;
    }

    public void setSfsqrdTjList() {
        List<HashMap<String, String>> sfsqrdTjList = getCommSelectList("sf");
        this.sfsqrdTjList = sfsqrdTjList;
    }

    public List<HashMap<String, String>> getSfsqrdTjList() {
        return sfsqrdTjList;
    }

    public void setSfssmzTjList() {
        List<HashMap<String, String>> sfssmzTjList = getCommSelectList("sf");
        this.sfssmzTjList = sfssmzTjList;
    }

    public List<HashMap<String, String>> getSfssmzTjList() {
        return sfssmzTjList;
    }

    public void setSfdgsxTjList() {
        List<HashMap<String, String>> sfdgsxTjList = getCommSelectList("sf");
        this.sfdgsxTjList = sfdgsxTjList;
    }

    public List<HashMap<String, String>> getSfdgsxTjList() {
        return sfdgsxTjList;
    }

    public void setSflspxTjList() {
        List<HashMap<String, String>> sflspxTjList = getCommSelectList("sf");
        this.sflspxTjList = sflspxTjList;
    }

    public List<HashMap<String, String>> getSflspxTjList() {
        return sflspxTjList;
    }

    public void setSfzjxyTjList() {
        List<HashMap<String, String>> sfzjxyTjList = getCommSelectList("sf");
        this.sfzjxyTjList = sfzjxyTjList;
    }

    public List<HashMap<String, String>> getSfzjxyTjList() {
        return sfzjxyTjList;
    }

    public void setSfjjknTjList() {
        List<HashMap<String, String>> sfjjknTjList = getCommSelectList("sf");
        this.sfjjknTjList = sfjjknTjList;
    }

    public List<HashMap<String, String>> getSfjjknTjList() {
        return sfjjknTjList;
    }

    public void setStsfcjTjList() {
        List<HashMap<String, String>> stsfcjTjList = getCommSelectList("sf");
        this.stsfcjTjList = stsfcjTjList;
    }

    public List<HashMap<String, String>> getStsfcjTjList() {
        return stsfcjTjList;
    }

    public void setSfxxknTjList() {
        List<HashMap<String, String>> sfxxknTjList = getCommSelectList("sf");
        this.sfxxknTjList = sfxxknTjList;
    }

    public List<HashMap<String, String>> getSfxxknTjList() {
        return sfxxknTjList;
    }

    public void setSfxlkrTjList() {
        List<HashMap<String, String>> sfxlkrTjList = getCommSelectList("sf");
        this.sfxlkrTjList = sfxlkrTjList;
    }

    public List<HashMap<String, String>> getSfxlkrTjList() {
        return sfxlkrTjList;
    }

    public void setSfjtkrTjList() {
        List<HashMap<String, String>> sfjtkrTjList = getCommSelectList("sf");
        this.sfjtkrTjList = sfjtkrTjList;
    }

    public List<HashMap<String, String>> getSfjtkrTjList() {
        return sfjtkrTjList;
    }

    public void setSfyqtkrTjList() {
        List<HashMap<String, String>> sfyqtkrTjList = getCommSelectList("sf");
        this.sfyqtkrTjList = sfyqtkrTjList;
    }

    public List<HashMap<String, String>> getSfyqtkrTjList() {
        return sfyqtkrTjList;
    }

    public void setTjztTjList() {

        this.tjztTjList = new OptionUtil().getOptions(OptionUtil.ZHCP_TJZT);

    }

    public void setLxdmTjList() {

        DAO dao = DAO.getInstance();
        String sql = "select lxdm dm,lxmc mc from xg_pjpy_new_tslxdmb";
        List<HashMap<String, String>> lxdmTjList = dao.getList(sql,
                new String[]{}, new String[]{"dm", "mc"});
        this.lxdmTjList = lxdmTjList;
    }

    public List<HashMap<String, String>> getLxdmTjList() {
        return lxdmTjList;
    }

    public List<HashMap<String, Object>> getXmmcTjList() {
        return xmmcTjList;
    }

    public void setXmmcTjList(List<HashMap<String, Object>> xmmcTjList) {
        this.xmmcTjList = xmmcTjList;
    }

    public void setCpzTjList() {

        StringBuilder sql = new StringBuilder();
        sql
                .append(" select distinct t1.cpzdm dm,t2.cpzmc mc from xg_zhcp_fstjjlb t1");
        sql.append(" left join xg_zhcp_cpzb t2 on t1.cpzdm=t2.cpzdm");
        sql
                .append(" where t1.xn||t1.xq=(select xn||xq from xg_pjpy_new_csszb where rownum=1)");

        DAO dao = DAO.getInstance();
        this.cpzTjList = dao.getListNotOut(sql.toString(), new String[]{});
    }

    public List<HashMap<String, String>> getCpzTjList() {
        return cpzTjList;
    }

    public List<HashMap<String, String>> getRcxwshztTjList() {
        return rcxwshztTjList;
    }

    public void setRcxwshztTjList() {
        this.rcxwshztTjList = getCommSelectList("rcxwshzt");
    }

    public List<HashMap<String, String>> getRcxwdlTjList() {

        return rcxwdlTjList;
    }

    public void setRcxwdlTjList() {

        DAO dao = DAO.getInstance();

        String sql = "select a.rcxwlbdldm dm ,a.rcxwlbdlmc mc from xg_rcsw_rcxwdbdlb  a where a.rcxwlbdldm is not null order by a.rcxwlbdldm ";
        List<HashMap<String, String>> rcxwlbdldmList = dao.getList(sql,
                new String[]{}, new String[]{"dm", "mc"});

        this.rcxwdlTjList = rcxwlbdldmList;
    }

    public List<HashMap<String, String>> getRcxwdlnewTjList() {

        return rcxwdlnewTjList;
    }

    public void setRcxwdlnewTjList() {

        DAO dao = DAO.getInstance();

        String sql = "select a.rcxwlbdldm dm ,a.rcxwlbdlmc mc from XG_RCSW_NEW_RCXWDLDMB a order by a.rcxwlbdldm ";
        List<HashMap<String, String>> rcxwdlnewTjList = dao.getList(sql, new String[]{}, new String[]{"dm", "mc"});

        this.rcxwdlnewTjList = rcxwdlnewTjList;
    }

    public List<HashMap<String, String>> getRcxwlbnewTjList() {

        return rcxwlbnewTjList;
    }

    public void setRcxwlbnewTjList() {

        DAO dao = DAO.getInstance();

        String sql = "select a.rcxwlbdm dm ,a.rcxwlbmc mc from XG_RCSW_NEW_RCXWLBDMB a order by a.rcxwlbdm ";
        List<HashMap<String, String>> rcxwlbnewTjList = dao.getList(sql, new String[]{}, new String[]{"dm", "mc"});

        this.rcxwlbnewTjList = rcxwlbnewTjList;
    }

    public List<HashMap<String, String>> getRcxwxlnewTjList() {

        return rcxwxlnewTjList;
    }

    public void setRcxwxlnewTjList() {

        DAO dao = DAO.getInstance();

        String sql = "select a.rcxwlbxldm dm ,a.rcxwlbxlmc mc from XG_RCSW_NEW_RCXWXLDMB a order by a.rcxwlbxldm ";
        List<HashMap<String, String>> rcxwxlnewTjList = dao.getList(sql, new String[]{}, new String[]{"dm", "mc"});

        this.rcxwxlnewTjList = rcxwxlnewTjList;
    }

    public List<HashMap<String, String>> getZxbkTjList() {

        return zxbkTjList;
    }

    public void setZxbkTjList() {

        DAO dao = DAO.getInstance();

        String sql = "select a.bkid dm ,a.bkmc mc from XG_RCSW_ZXZX_ZXBK a order by a.xssx ";
        List<HashMap<String, String>> zxbkTjList = dao.getList(sql, new String[]{}, new String[]{"dm", "mc"});

        this.zxbkTjList = zxbkTjList;
    }

    public List<HashMap<String, String>> getJydwxzTjList() {

        return jydwxzTjList;
    }

    public void setJydwxzTjList() {

        DAO dao = DAO.getInstance();

        String sql = "select a.lxdm dm ,a.lxmc mc from xg_jygl_jygl_jydwxzb a order by a.lxdm ";
        List<HashMap<String, String>> jydwxzTjList = dao.getList(sql, new String[]{}, new String[]{"dm", "mc"});

        this.jydwxzTjList = jydwxzTjList;
    }

    public List<HashMap<String, String>> getJyxsTjList() {

        return jyxsTjList;
    }

    public void setJyxsTjList() {

        DAO dao = DAO.getInstance();

        String sql = "select a.lxdm dm ,a.lxmc mc from xg_jygl_jygl_jyxsb a order by a.lxdm ";
        List<HashMap<String, String>> jyxsTjList = dao.getList(sql, new String[]{}, new String[]{"dm", "mc"});

        this.jyxsTjList = jyxsTjList;
    }

    public List<HashMap<String, String>> getSshyTjList() {

        return sshyTjList;
    }

    public void setSshyTjList() {

        DAO dao = DAO.getInstance();

        String sql = "select a.lxdm dm ,a.lxmc mc from xg_jygl_jygl_sshyb a order by a.lxdm ";
        List<HashMap<String, String>> sshyTjList = dao.getList(sql, new String[]{}, new String[]{"dm", "mc"});

        this.sshyTjList = sshyTjList;
    }

    public List<HashMap<String, String>> getPxlxTjList() {

        return pxlxTjList;
    }

    public void setPxlxTjList() {

        DAO dao = DAO.getInstance();

        String sql = "select a.lxdm dm ,a.lxmc mc from xg_jygl_jygl_pxlxb a order by a.lxdm ";
        List<HashMap<String, String>> pxlxTjList = dao.getList(sql, new String[]{}, new String[]{"dm", "mc"});

        this.pxlxTjList = pxlxTjList;
    }

    public List<HashMap<String, String>> getGslxTjList() {

        return gslxTjList;
    }

    public void setGslxTjList() {

        String[] dm = new String[]{"st", "wd"};
        String[] mc = new String[]{"实体", "网店"};
        this.gslxTjList = DAO.getInstance().arrayToList(dm, mc);
    }

    public List<HashMap<String, String>> getHfztTjList() {

        return hfztTjList;
    }

    public void setHfztTjList() {

        String[] dm = new String[]{"1", "0"};
        String[] mc = new String[]{"已回复", "未回复"};
        this.hfztTjList = DAO.getInstance().arrayToList(dm, mc);
    }

    public List<HashMap<String, String>> getKycxxmlbTjList() {

        return kycxxmlbTjList;
    }

    public void setKycxxmlbTjList() {

        DAO dao = DAO.getInstance();

        String sql = "select a.lbdm dm ,a.lbmc mc from xg_kycxgl_kycxxmlb a order by a.lbdm ";
        List<HashMap<String, String>> kycxxmlbList = dao.getList(sql,
                new String[]{}, new String[]{"dm", "mc"});

        this.kycxxmlbTjList = kycxxmlbList;
    }

    public List<HashMap<String, String>> getWjcddmTjList() {

        return wjcddmTjList;
    }

    public void setWjcddmTjList() {

        DAO dao = DAO.getInstance();

        String sql = "select a.lxdm dm ,a.lxmc mc from TSXS_WJCDB a order by a.lxdm ";
        List<HashMap<String, String>> wjcddmList = dao.getList(sql,
                new String[]{}, new String[]{"dm", "mc"});

        this.wjcddmTjList = wjcddmList;
    }

    public List<HashMap<String, String>> getWjgabzTjList() {

        return wjgabzTjList;
    }

    public void setWjgabzTjList() {
        String[] dm = new String[]{"0", "1"};
        String[] mc = new String[]{"已解除", "危机个案"};
        this.wjgabzTjList = DAO.getInstance().arrayToList(dm, mc);
    }

    public List<HashMap<String, String>> getPjztTjList() {

        return pjztTjList;
    }

    public void setPjztTjList() {
        String[] dm = new String[]{"1", "2"};
        String[] mc = new String[]{"待评价", "已评价"};
        this.pjztTjList = DAO.getInstance().arrayToList(dm, mc);
    }

    public List<HashMap<String, String>> getZxztnewTjList() {

        return zxztnewTjList;
    }

    public void setZxztnewTjList() {
        String[] dm = new String[]{"1", "2", "3"};
        String[] mc = new String[]{"待咨询", "已咨询", "未咨询"};
        this.zxztnewTjList = DAO.getInstance().arrayToList(dm, mc);
    }

    public List<HashMap<String, String>> getRcxwlbTjList() {

        return rcxwlbTjList;
    }

    public void setRcxwlbTjList() {
        DAO dao = DAO.getInstance();

        String sql = "select a.rcxwlbdm dm ,a.rcxwlbmc mc from xg_rcsw_rcxwlbdmb  a where a.rcxwlbdm is not null order by a.rcxwlbdm ";
        List<HashMap<String, String>> rcxwlbList = dao.getList(sql,
                new String[]{}, new String[]{"dm", "mc"});

        this.rcxwlbTjList = rcxwlbList;
    }

    public void setGywpdlTjList() {
        DAO dao = DAO.getInstance();
        String sql = "select wpdldm dm,wpdlmc mc from xg_gygl_wfxy_qswpdlb";
        this.gywpdlTjList = dao.getList(sql, new String[]{}, new String[]{
                "dm", "mc"});
    }

    public List<HashMap<String, String>> getGywpdlTjList() {
        return gywpdlTjList;
    }

    public void setGywplbTjList() {
        DAO dao = DAO.getInstance();
        String sql = "select wplbdm dm,wplbmc mc from xg_gygl_wfxy_qswplbb";
        this.gywplbTjList = dao.getList(sql, new String[]{}, new String[]{
                "dm", "mc"});
    }

    public List<HashMap<String, String>> getGywplbTjList() {
        return gywplbTjList;
    }

    public void setBzlxTjList() {
        List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("dm", "0");
        map.put("mc", "岗位补助");
        list.add(map);
        map = new HashMap<String, String>();
        map.put("dm", "1");
        map.put("mc", "绩效考核补助");
        list.add(map);
        this.bzlxTjList = list;
    }

    public List<HashMap<String, String>> getBzlxTjList() {
        return bzlxTjList;
    }

    public List<HashMap<String, String>> getBjdlTjList() {
        return bjdlTjList;
    }

    public void setBjdlTjList() {

        StringBuilder sql = new StringBuilder();

        sql.append(" select lbdm dm,lbmc mc from xg_xtwh_bjdlb t1 ");
        sql
                .append(" where exists (select 1 from xg_xtwh_bjlbb t2 where t1.lbdm=t2.lbdm)");

        List<HashMap<String, String>> list = DAO.getInstance().getListNotOut(
                sql.toString(), null);
        ;

        HashMap<String, String> map = new HashMap<String, String>();
        map.put("dm", "0");
        map.put("mc", "未设置");

        list.add(map);

        this.bjdlTjList = list;
    }

    /**
     * @return the qjlxdmTjList
     */
    public List<HashMap<String, String>> getQjlxdmTjList() {
        return qjlxdmTjList;
    }

    /**
     * @param qjlxdmTjList要设置的 qjlxdmTjList
     */
    public void setQjlxdmTjList() {
        StringBuffer sql = new StringBuffer();
        sql
                .append(" select qjlxid dm,qjlxmc mc from xg_rcsw_qjgl_qjlx order by qjlxid asc");
        this.qjlxdmTjList = DAO.getInstance().getListNotOut(sql.toString(),
                new String[]{});
    }

    /**
     * @return the xjztTjList
     */
    public List<HashMap<String, String>> getXjztTjList() {
        return xjztTjList;
    }

    /**
     * @param xjztTjList要设置的 xjztTjList
     */
    public void setXjztTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = new String[]{"0", "1"};
        String[] mc = new String[]{"未销假", "已销假"};
        this.xjztTjList = dao.arrayToList(dm, mc);
    }

    /**
     * @return the gyzgztTjList
     */
    public List<HashMap<String, String>> getGyzgztTjList() {
        return gyzgztTjList;
    }

    /**
     * @param gyzgztTjList要设置的 gyzgztTjList
     */
    public void setGyzgztTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"0", "1"};
        String[] mc = {"否", "是"};
        this.gyzgztTjList = dao.arrayToList(dm, mc);
    }

    /**
     * @return the qjztTjList
     */
    public List<HashMap<String, String>> getQjztTjList() {
        return qjztTjList;
    }

    /**
     * @param qjztTjList要设置的 qjztTjList
     */
    public void setQjztTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = new String[]{"0", "1"};
        String[] mc = new String[]{"草稿", "已提交"};
        this.qjztTjList = dao.arrayToList(dm, mc);
    }

    /**
     * @return the shztxTjList
     */
    public List<HashMap<String, String>> getShztxTjList() {
        return shztxTjList;
    }

    /**
     * @param shztxTjList要设置的 shztxTjList
     */
    public void setShztxTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = new String[]{Constants.YW_WTJ, Constants.YW_TG,
                Constants.YW_BTG, Constants.YW_SHZ}; //Constants.YW_YTH,
        String[] mc = new String[]{"未提交", "通过", "不通过", "审核中"}; //"已退回",
        this.shztxTjList = dao.arrayToList(dm, mc);
    }

    //西北工业大学-学风建设-汇报的审核状态
    private List<HashMap<String, String>> shztHbTjList;

    public List<HashMap<String, String>> getShztHbTjList() {
        return shztHbTjList;
    }

    public void setShztHbTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = new String[]{"未汇报", "未提交", "通过", "不通过", "已退回", "审核中"};
        String[] mc = new String[]{"未汇报", "未提交", "通过", "不通过", "已退回", "审核中"};
        this.shztHbTjList = dao.arrayToList(dm, mc);
    }

    //西北工业大学-学风建设-总结的审核状态
    private List<HashMap<String, String>> shztZjTjList;

    public List<HashMap<String, String>> getShztZjTjList() {
        return shztZjTjList;
    }

    public void setShztZjTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = new String[]{"未汇报", "未提交", "通过", "不通过", "已退回", "审核中"};
        String[] mc = new String[]{"未汇报", "未提交", "通过", "不通过", "已退回", "审核中"};
        this.shztZjTjList = dao.arrayToList(dm, mc);
    }

    public List<HashMap<String, String>> getShztxbjpyTjList() {
        return shztxbjpyTjList;
    }

    public void setShztxbjpyTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = new String[]{Constants.YW_WTJ, Constants.YW_BJPYZ, Constants.YW_BJPYBTG, Constants.YW_TG,
                Constants.YW_BTG, Constants.YW_YTH, Constants.YW_SHZ};
        String[] mc = new String[]{"未提交", "班级评议中", "班级评议不通过", "通过", "不通过", "已退回", "审核中"};
        this.shztxbjpyTjList = dao.arrayToList(dm, mc);
    }

    public List<HashMap<String, String>> getShztbjpyjgTjList() {
        return shztbjpyjgTjList;
    }

    public void setShztbjpyjgTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = new String[]{Constants.YW_BJPYZ, "bjpytg", Constants.YW_BJPYBTG};
        String[] mc = new String[]{"未提交", "班级评议通过", "班级评议不通过"};
        this.shztbjpyjgTjList = dao.arrayToList(dm, mc);
    }

    /**
     * @return the xszbblxTjList
     */
    public List<HashMap<String, String>> getXszbblxTjList() {
        return xszbblxTjList;
    }

    /**
     * @param xszbblxTjList要设置的 xszbblxTjList
     */
    public void setXszbblxTjList() {
        DAO dao = DAO.getInstance();
        String sql = "  select t.xszbblxdm dm,t.xszbblxmc mc from xg_rcsw_xszbb_bblxwhb  t ";
        List<HashMap<String, String>> xszbblxList = dao.getList(sql,
                new String[]{}, new String[]{"dm", "mc"});
        this.xszbblxTjList = xszbblxList;
    }

    /**
     * @return the sfbbhcyhkTjList
     */
    public List<HashMap<String, String>> getSfbbhcyhkTjList() {
        return sfbbhcyhkTjList;
    }

    /**
     * @param sfbbhcyhkTjList要设置的 sfbbhcyhkTjList
     */
    public void setSfbbhcyhkTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = new String[]{"是", "否"};
        String[] mc = new String[]{"是", "否"};
        this.sfbbhcyhkTjList = dao.arrayToList(dm, mc);
    }

    /**
     * @return the zcztTjList
     */
    public List<HashMap<String, String>> getZcztTjList() {
        return zcztTjList;
    }

    /**
     * @param zcztTjList要设置的 zcztTjList
     */
    public void setZcztTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = new String[]{"-1", "1", "0"};
        String[] mc;
        if (Globals.XXDM_ZJCMXY.equalsIgnoreCase(Base.xxdm)) {
            mc = new String[]{"未处理", "已报到", "未报到"};
        } else {
            mc = new String[]{"未处理", "已注册", "未注册"};
        }
        this.zcztTjList = dao.arrayToList(dm, mc);
    }

    /**
     * @return the txztTjList
     */
    public List<HashMap<String, String>> getTxztTjList() {
        return txztTjList;
    }

    /**
     * @param txztTjList要设置的 txztTjList
     */
    public void setTxztTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = new String[]{"1", "0"};
        String[] mc = new String[]{"已填", "未填"};
        this.txztTjList = dao.arrayToList(dm, mc);
    }

    public List<HashMap<String, String>> getBmlbTjList() {
        return bmlbTjList;
    }

    public void setBmlbTjList() {
        StringBuffer sql = new StringBuffer();
        sql.append(" select bmlbdm dm, bmlbmc mc");
        sql.append("   from dm_bmlb ");
        sql.append("  order by bmlbdm desc ");
        this.bmlbTjList = DAO.getInstance().getListNotOut(sql.toString(),
                new String[]{});
    }

    public void setRwfsTjList() {
        StringBuffer sql = new StringBuffer();
        sql.append("select dm , mc from dmk_rwfsb order by mc desc ");
        this.rwfsTjList = DAO.getInstance().getListNotOut(sql.toString(),
                new String[]{});
    }

    public List<HashMap<String, String>> getRwfsTjList() {
        return this.rwfsTjList;
    }

    public List<HashMap<String, String>> getNjNewTjList() {
        return njNewTjList;
    }

    public List<HashMap<String, String>> getBmNewTjList() {
        return bmNewTjList;
    }

    public void setBmNewTjList() {
        StringBuffer sql = new StringBuffer();
        sql
                .append(" select distinct(bmdm) dm ,bmmc mc from ZXBZ_XXBMDM order by bmdm ");
        this.bmNewTjList = DAO.getInstance().getListNotOut(sql.toString(),
                new String[]{});
    }

    public void setNjNewTjList(List<HashMap<String, String>> njNewTjList) {
        this.njNewTjList = njNewTjList;
    }

    public List<HashMap<String, Object>> getXyNewTjList() {
        return xyNewTjList;
    }

    public void setXyNewTjList(List<HashMap<String, Object>> xyNewTjList) {
        this.xyNewTjList = xyNewTjList;
    }

    public List<HashMap<String, Object>> getZyNewTjList() {
        return zyNewTjList;
    }

    /**
     * @return the gyyjxfkqkList
     */
    public List<HashMap<String, String>> getGyyjxfkqkTjList() {
        return gyyjxfkqkTjList;
    }

    /**
     * @param gyyjxfkqkList要设置的 gyyjxfkqkList
     */
    public void setGyyjxfkqkTjList() {
        String[] dm = new String[]{"0", "1"};
        String[] mc = new String[]{"未反馈", "已反馈"};
        this.gyyjxfkqkTjList = DAO.getInstance().arrayToList(dm, mc);
    }

    /**
     * @return the gyyjFlList
     */
    public List<HashMap<String, String>> getGyyjflTjList() {
        return this.gyyjflTjList;
    }

    /**
     * @param gyyjFlList要设置的 gyyjFlList
     */
    public void setGyyjflTjList() {
        StringBuffer sql = new StringBuffer();
        sql
                .append("select YJFLDM dm, YJFLMC mc from XG_GYGL_GYYJX_YJFL order by YJFLMC desc ");
        this.gyyjflTjList = DAO.getInstance().getListNotOut(sql.toString(),
                new String[]{});
    }

    public void setZyNewTjList(List<HashMap<String, Object>> zyNewTjList) {
        this.zyNewTjList = zyNewTjList;
    }

    public List<HashMap<String, Object>> getBjNewTjList() {
        return bjNewTjList;
    }

    public void setBjNewTjList(List<HashMap<String, Object>> bjNewTjList) {
        this.bjNewTjList = bjNewTjList;
    }

    /**
     * @return the cbzkmcTjList
     */
    public List<HashMap<String, String>> getCbzkmcTjList() {
        return cbzkmcTjList;
    }

    /**
     * @param cbzkmcTjList要设置的 cbzkmcTjList
     */
    public void setCbzkmcTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = new String[]{"已参保", "未参保"};
        String[] mc = new String[]{"已参保", "未参保"};
        this.cbzkmcTjList = dao.arrayToList(dm, mc);

    }

    /**
     * @return the xhqkTjList
     */
    public List<HashMap<String, String>> getXhqkTjList() {
        return xhqkTjList;
    }

    /**
     * @param xhqkTjList要设置的 xhqkTjList
     */
    public void setXhqkTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = new String[]{"0", "1"};
        String[] mc = new String[]{"已编学号", "未编学号"};
        this.xhqkTjList = dao.arrayToList(dm, mc);
    }

    /**
     * @return the fbqkTjList
     */
    public List<HashMap<String, String>> getFbqkTjList() {
        return fbqkTjList;
    }

    /**
     * @param fbqkTjList要设置的 fbqkTjList
     */
    public void setFbqkTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = new String[]{"0", "1"};
        String[] mc = new String[]{"已分班", "未分班"};
        this.fbqkTjList = dao.arrayToList(dm, mc);
    }

    /**
     * @return the syztTjList
     */
    public List<HashMap<String, String>> getSyztTjList() {
        return syztTjList;
    }

    /**
     * @param syztTjList要设置的 syztTjList
     */
    public void setSyztTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = new String[]{"1", "0"};
        String[] mc = new String[]{"已使用", "未使用"};
        this.syztTjList = dao.arrayToList(dm, mc);
    }

    /**
     * @return the sfnzTjList
     */
    public List<HashMap<String, String>> getSfnzTjList() {
        return sfnzTjList;
    }

    /**
     * @param sfnzTjList要设置的 sfnzTjList
     */
    public void setSfnzTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = new String[]{"1", "0"};
        String[] mc = new String[]{"是", "否"};
        this.sfnzTjList = dao.arrayToList(dm, mc);
    }


    /**
     * @return the sfpkxTjList
     */
    public List<HashMap<String, String>> getSfpkxTjList() {
        return sfpkxTjList;
    }

    /**
     * @param sfpkxTjList要设置的 sfpkxTjList
     */
    public void setSfpkxTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = new String[]{"1", "0"};
        String[] mc = new String[]{"是", "否"};
        this.sfpkxTjList = dao.arrayToList(dm, mc);
    }

    public List<HashMap<String, String>> getSfsfsTjList() {
        return sfsfsTjList;
    }


    public void setSfsfsTjList() {
        List<HashMap<String, String>> sfsfsList = getCommSelectList("sf");
        this.sfsfsTjList = sfsfsList;
    }

    public List<HashMap<String, String>> getXsfsfsTjList() {
        return xsfsfsTjList;
    }

    public void setFfztTjList() {
        List<HashMap<String, String>> ffztList = getCommSelectList("ffzt");
        this.ffztTjList = ffztList;
    }

    public List<HashMap<String, String>> getFfztTjList() {
        return ffztTjList;
    }


    public void setXsfsfsTjList() {
        List<HashMap<String, String>> xsfsfsList = getCommSelectList("sfsfs");
        this.xsfsfsTjList = xsfsfsList;
    }

    /**
     * @return the sfbgbTjList
     */
    public List<HashMap<String, String>> getSfbgbTjList() {
        return sfbgbTjList;
    }

    /**
     * @param sfbgbTjList要设置的 sfbgbTjList
     */
    public void setSfbgbTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = new String[]{"1", "0"};
        String[] mc = new String[]{"是", "否"};
        this.sfbgbTjList = dao.arrayToList(dm, mc);
    }

    /**
     * @return the gzlxTjList
     */
    public List<HashMap<String, String>> getGzlxTjList() {
        return gzlxTjList;
    }

    /**
     * @param gzlxTjList要设置的 gzlxTjList
     */
    public void setGzlxTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = new String[]{"BBGZ", "FBGZ", "BXHGZ"};
        String[] mc = new String[]{"编班规则", "分班规则", "编学号规则"};
        this.gzlxTjList = dao.arrayToList(dm, mc);
    }

    /**
     * @return the zxsstatusTjList
     */
    public List<HashMap<String, String>> getZxsstatusTjList() {
        return zxsstatusTjList;
    }

    /**
     * @param zxsstatusTjList要设置的 zxsstatusTjList
     */
    public void setZxsstatusTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"1", "0"};
        String[] mc = {"在岗", "不在岗"};
        this.zxsstatusTjList = dao.arrayToList(dm, mc);
    }

    /**
     * @return the zxszgTjList
     */
    public List<HashMap<String, String>> getZxszgTjList() {
        return zxszgTjList;
    }

    /**
     * @param zxszgTjList要设置的 zxszgTjList
     */
    public void setZxszgTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"无", "心理咨询师资格证书二级", "心理咨询师资格证书三级"};
        String[] mc = {"无", "心理咨询师资格证书二级", "心理咨询师资格证书三级"};
        this.zxszgTjList = dao.arrayToList(dm, mc);
    }

    /**
     * @return the yyztTjList
     */
    public List<HashMap<String, String>> getYyztTjList() {
        return yyztTjList;
    }

    /**
     * @param yyztTjList要设置的 yyztTjList
     */
    public void setYyztTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"1", "2", "3", "4", "5"};
        String[] mc = {"预约中", "预约成功", "预约中(学生取消)", "预约成功(学生取消)", "预约失败"};
        this.yyztTjList = dao.arrayToList(dm, mc);
    }

    /**
     * @return the zxztTjList
     */
    public List<HashMap<String, String>> getZxztTjList() {
        return zxztTjList;
    }

    /**
     * @return the xljkgxlxTjList
     */
    public List<HashMap<String, String>> getXljkgxlxTjList() {
        return xljkgxlxTjList;
    }

    /**
     * @param xljkgxlxTjList要设置的 xljkgxlxTjList
     */
    public void setXljkgxlxTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"学习困难", "经济困难", "心理困难", "其它"};
        String[] mc = {"学习困难", "经济困难", "心理困难", "其它"};
        this.xljkgxlxTjList = dao.arrayToList(dm, mc);
    }


    /**
     * @return the xslbTjList
     */
    public List<HashMap<String, String>> getXslbTjList() {
        return xslbTjList;
    }

    /**
     * @param xslbTjList要设置的 xslbTjList
     */
    public void setXslbTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"本部", "滨江"};
        String[] mc = {"本部", "滨江"};
        this.xslbTjList = dao.arrayToList(dm, mc);
    }

    /**
     * @return the xljkgzlxTjList
     */
    public List<HashMap<String, String>> getXljkgzlxTjList() {
        return xljkgzlxTjList;
    }

    /**
     * @param xljkgzlxTjList要设置的 xljkgzlxTjList
     */
    public void setXljkgzlxTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"一般关注", "重点关注", "危机干预"};
        String[] mc = {"一般关注", "重点关注", "危机干预"};
        this.xljkgzlxTjList = dao.arrayToList(dm, mc);
    }

    /**
     * @param zxztTjList要设置的 zxztTjList
     */
    public void setZxztTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"0", "1", "2"};
        String[] mc = {"待咨询", "已咨询", "未咨询"};
        this.zxztTjList = dao.arrayToList(dm, mc);
    }

    /**
     * @return the jxmcTjList
     */
    public List<HashMap<String, String>> getJxmcTjList() {
        return jxmcTjList;
    }

    /**
     * @param jxmcTjList要设置的 jxmcTjList
     */
    public void setJxmcTjList() {
        DAO dao = DAO.getInstance();
        /*
		 * String[] dm = new String[] { "BBGZ", "FBGZ", "BXHGZ" }; String[] mc =
		 * new String[] { "编班规则", "分班规则", "编学号规则" }; this.jxmcTjList =
		 * dao.arrayToList(dm, mc);
		 */
        StringBuffer sb = new StringBuffer();
        sb.append("select jxid dm,jxmc mc from xg_pjpy_jtpj_jtjxsz");
        this.jxmcTjList = DAO.getInstance().getListNotOut(sb.toString(),
                new String[]{});
    }


    /**
     * @return the yxztTjList
     */
    public List<HashMap<String, String>> getYxztTjList() {
        return yxztTjList;
    }

    /**
     * @param yxztTjList要设置的 yxztTjList
     */
    public void setYxztTjList() {
        StringBuffer sb = new StringBuffer();
        sb.append("select * from XG_QGZX_XMYXZT");
        this.yxztTjList = DAO.getInstance().getListNotOut(sb.toString(),
                new String[]{});
    }

    /**
     * @return the pdxqTjList
     */
    public List<HashMap<String, String>> getPdxqTjList() {
        return pdxqTjList;
    }

    /**
     * @param pdxqTjList要设置的 pdxqTjList
     */
    public void setPdxqTjList() {
        List<HashMap<String, String>> list = Base.getXqList();

        List<HashMap<String, String>> xqList = new ArrayList<HashMap<String, String>>();

        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                HashMap<String, String> map = list.get(i);

                if (!Base.isNull(map.get("xqdm"))) {
                    map.put("dm", map.get("xqdm"));
                    map.put("mc", map.get("xqmc"));

                    xqList.add(map);
                }
            }
        }
        this.pdxqTjList = xqList;
    }

    /**
     * @return the pdxnTjList
     */
    public List<HashMap<String, String>> getPdxnTjList() {
        return pdxnTjList;
    }

    /**
     * @param pdxnTjList要设置的 pdxnTjList
     */
    public void setPdxnTjList() {
        List<HashMap<String, String>> list = Base.getXnndList();

        List<HashMap<String, String>> xnList = new ArrayList<HashMap<String, String>>();

        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                HashMap<String, String> map = list.get(i);
                HashMap<String, String> xnMap = new HashMap<String, String>();
                if (!Base.isNull(map.get("xn"))) {
                    xnMap.put("dm", map.get("xn"));
                    xnMap.put("mc", map.get("xn"));

                    xnList.add(xnMap);
                }
            }
        }
        this.pdxnTjList = xnList;
    }

    /**
     * @return the zblxTjList
     */
    public List<HashMap<String, String>> getZblxTjList() {
        return zblxTjList;
    }

    public void setZblxTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"0", "1", "2"};
        String[] mc = {"班级心理周报", "公寓心理周报", "平时情况上报"};
        this.zblxTjList = dao.arrayToList(dm, mc);
    }

    /**
     * @return the gzdjTjList
     */
    public List<HashMap<String, String>> getGzdjTjList() {
        return gzdjTjList;
    }

    public void setGzdjTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"A", "B", "C"};
        String[] mc = {"A", "B", "C"};
        this.gzdjTjList = dao.arrayToList(dm, mc);
    }

    /**
     * @return the xlwtlxTjList
     */
    public List<HashMap<String, String>> getXlwtlxTjList() {
        return xlwtlxTjList;
    }

    public void setXlwtlxTjList() {
        StringBuffer sql = new StringBuffer();
        sql
                .append("select lxdm dm , lxmc mc from XG_XLJK_XLWJYJ_XLWTLX order by lxmc ");
        this.xlwtlxTjList = DAO.getInstance().getListNotOut(sql.toString(),
                new String[]{});
    }

    /**
     * @return the ywTjList
     */
    public List<HashMap<String, String>> getYwTjList() {
        return ywTjList;
    }

    /**
     * @param ywTjList要设置的 ywTjList
     */
    public void setYwTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"无", "有"};
        String[] mc = {"无", "有"};
        this.ywTjList = dao.arrayToList(dm, mc);
        ;
    }

    public List<HashMap<String, String>> getKqlxTjList() {
        return kqlxTjList;
    }

    public void setKqlxTjList() {

        DAO dao = DAO.getInstance();

        String sql = "select kqlxdm dm,kqlxmc mc from xg_kqgl_kqlx order by to_number(kqlxdm)";
        List<HashMap<String, String>> kqlxList = dao.getList(sql,
                new String[]{}, new String[]{"dm", "mc"});

        this.kqlxTjList = kqlxList;
    }


    // ======================异动类型条件列表===============================
    public List<HashMap<String, String>> getYdlxTjList() {
        return ydlxTjList;
    }

    public void setYdlxTjList() {
        DAO dao = DAO.getInstance();

        String sql = "select distinct SSYDLX dm,decode(SSYDLX,'00','退宿','01','宿舍调整','03','入住','实习留宿') mc from XG_GYGL_NEW_SSYD_SSYDSQ order by SSYDLX ";
        List<HashMap<String, String>> ydlxTjList = dao.getList(sql,
                new String[]{}, new String[]{"dm", "mc"});

        this.ydlxTjList = ydlxTjList;
    }

    public List<HashMap<String, String>> getYdlxjgTjList() {
        return ydlxjgTjList;
    }

    public void setYdlxjgTjList() {
        DAO dao = DAO.getInstance();

        String sql = "select distinct SSYDLX dm,decode(SSYDLX,'00','退宿','01','宿舍调整','03','入住','实习留宿') mc from XG_GYGL_NEW_SSYD_SSYDJG order by SSYDLX ";
        List<HashMap<String, String>> ydlxjgTjList = dao.getList(sql,
                new String[]{}, new String[]{"dm", "mc"});

        this.ydlxjgTjList = ydlxjgTjList;
    }

    /**
     * @return the ghztList
     */
    public List<HashMap<String, String>> getGhztTjList() {

        return this.ghztList;
    }

    /**
     * @param ghztList要设置的 ghztList
     */
    public void setGhztTjList() {

        DAO dao = DAO.getInstance();
        String[] dm = {"已归还", "未归还"};
        String[] mc = {"已归还", "未归还"};

        this.ghztList = dao.arrayToList(dm, mc);
    }

    // ======================异动类型条件列表 end===============================

    //------------------------------------------------
    public List<HashMap<String, String>> getHjxzTjList() {

        return this.ghztList;
    }

    /**
     * @param ghztList要设置的 ghztList
     */
    public void setHjxzTjList() {

        DAO dao = DAO.getInstance();
        String[] dm = {"农村", "城市", "县镇非农"};
        String[] mc = {"农村", "城市", "县镇非农"};

        this.ghztList = dao.arrayToList(dm, mc);
    }

    //浙江警察学院【大队维护】大队条件
    public List<HashMap<String, String>> getZjjcDdTjList() {
        return zjjcDdTjList;
    }

    public void setZjjcDdTjList() {
        DAO dao = DAO.getInstance();
        String sql = "select dddm dm,ddmc mc from xg_xsxx_zjjcxy_dddmb order by to_number(dddm)";
        List<HashMap<String, String>> dddmList = dao.getList(sql,
                new String[]{}, new String[]{"dm", "mc"});
        this.zjjcDdTjList = dddmList;
    }
    //浙江商业【考勤管理】周次条件

    public List<HashMap<String, String>> getZjsyzcTjList() {
        return zjsyzcTjList;
    }

    public void setZjsyzcTjList() {
        DAO dao = DAO.getInstance();
        String sql = "select  distinct zc dm, '第' || translate(to_number(zc), '0123456789', '零一二三四五六七八九') || '周' mc from xg_rcsw_kqgl_zjsykqjgb";
        List<HashMap<String, String>> dddmList = dao.getList(sql,
                new String[]{}, new String[]{"dm", "mc"});
        this.zjsyzcTjList = dddmList;
    }

    public List<HashMap<String, String>> getJxkqlxTjList() {
        return jxkqlxTjList;
    }

    public void setJxkqlxTjList() {
        DAO dao = DAO.getInstance();
        String sql = "select distinct lbdm dm,lbmc mc from xg_jxgl_kqlxb";
        List<HashMap<String, String>> dddmList = dao.getList(sql,
                new String[]{}, new String[]{"dm", "mc"});
        this.jxkqlxTjList = dddmList;
    }

    public List<HashMap<String, String>> getJxkqlbTjList() {
        return jxkqlbTjList;
    }

    public void setJxkqlbTjList() {
        DAO dao = DAO.getInstance();
        String sql = "select distinct lbdm dm,lbmc mc from xg_jxgl_kqlbb";
        List<HashMap<String, String>> dddmList = dao.getList(sql,
                new String[]{}, new String[]{"dm", "mc"});
        this.jxkqlbTjList = dddmList;
    }

    //社团管理
    public List<HashMap<String, String>> getStlbTjList() {
        return stlbTjList;
    }

    public void setStlbTjList() {
        DAO dao = DAO.getInstance();
        String sql = "select distinct stlbdm dm,stlbmc mc from xg_stgl_stlb";
        List<HashMap<String, String>> dddmList = dao.getList(sql,
                new String[]{}, new String[]{"dm", "mc"});
        this.stlbTjList = dddmList;
    }

    public List<HashMap<String, String>> getStxmlbTjList() {
        return stxmlbTjList;
    }

    public void setStxmlbTjList() {
        DAO dao = DAO.getInstance();
        String sql = "select distinct xmlbdm dm,xmlbmc mc from xg_stgl_xmlb";
        List<HashMap<String, String>> dddmList = dao.getList(sql,
                new String[]{}, new String[]{"dm", "mc"});
        this.stxmlbTjList = dddmList;
    }

    public List<HashMap<String, String>> getStxmTjList() {
        return stxmTjList;
    }

    public void setStxmTjList() {
        DAO dao = DAO.getInstance();
        String sql = "select stid  dm,stxmmc mc from xg_stgl_jtjg";
        List<HashMap<String, String>> dddmList = dao.getList(sql,
                new String[]{}, new String[]{"dm", "mc"});
        this.stxmTjList = dddmList;
    }

    //艺术团管理
    public List<HashMap<String, String>> getYstlbTjList() {
        return ystlbTjList;
    }

    public void setYstlbTjList() {
        DAO dao = DAO.getInstance();
        String sql = "select distinct ystlbdm dm,ystlbmc mc from XG_YSTGL_YSTLB";
        List<HashMap<String, String>> dddmList = dao.getList(sql,
                new String[]{}, new String[]{"dm", "mc"});
        this.ystlbTjList = dddmList;
    }

    public List<HashMap<String, String>> getYstxmlbTjList() {
        return ystxmlbTjList;
    }

    public void setYstxmlbTjList() {
        DAO dao = DAO.getInstance();
        String sql = "select distinct xmlbdm dm,xmlbmc mc from XG_YSTGL_Xmlb";
        List<HashMap<String, String>> dddmList = dao.getList(sql,
                new String[]{}, new String[]{"dm", "mc"});
        this.ystxmlbTjList = dddmList;
    }

    public List<HashMap<String, String>> getYstxmmcTjList() {
        return ystxmmcTjList;
    }

    public void setYstxmmcTjList() {
        DAO dao = DAO.getInstance();
        String sql = "select ystid  dm,ystxmmc mc from XG_YSTGL_YSTJGB";
        List<HashMap<String, String>> dddmList = dao.getList(sql,
                new String[]{}, new String[]{"dm", "mc"});
        this.ystxmmcTjList = dddmList;
    }

    public List<HashMap<String, String>> getTnztTjList() {
        return tnztTjList;
    }

    public void setTnztTjList() {
        this.tnztTjList = getCommSelectList("tnzt");
    }

    public List<HashMap<String, String>> getQyztmcTjList() {
        return qyztmcTjList;
    }

    public void setQyztmcTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"0", "1"};
        String[] mc = {"迁入", "迁出"};
        this.qyztmcTjList = dao.arrayToList(dm, mc);
    }

    /**
     * @return List<HashMap<String,Object>> 返回类型
     * @throws
     * @描述:自定义高级查询内容
     * @作者：cq [工号：785]
     * @日期：2015-9-30 下午04:06:19
     * @修改记录: 修改者名字-修改日期-修改内容
     */
    public List<HashMap<String, String>> getZdycxTjList(String tj, String path) {
        DAO dao = DAO.getInstance();

        String sql = "select dm,mc from xg_gjcx_zdypz where tj = ? and path = ? order by to_number(xssx)";

        return dao.getList(sql, new String[]{tj, path}, new String[]{"dm", "mc"});

    }


    // 华中师范大学用途类别高级查询
    private List<HashMap<String, String>> ytlbTjList;

    /**
     * @return the ytlbTjList
     */
    public List<HashMap<String, String>> getYtlbTjList() {
        return ytlbTjList;
    }

    /**
     * @param ytlbTjList要设置的 ytlbTjList
     */
    public void setYtlbTjList() {
        DAO dao = DAO.getInstance();
        String sql = "select lbdm dm,lbmc mc from xg_zxdk_ytzjlbdmb";
        List<HashMap<String, String>> ytlbTjList = dao.getList(sql,
                new String[]{}, new String[]{"dm", "mc"});
        this.ytlbTjList = ytlbTjList;
    }


    /**
     * 返校假期管理高级查询配置返校状态名称查询
     */
    private List<HashMap<String, String>> fxztTjList;

    /**
     * @return the fxztTjList
     */
    public List<HashMap<String, String>> getFxztTjList() {
        return fxztTjList;
    }

    /**
     * @param fxztTjList要设置的 fxztTjList
     */
    public void setFxztTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = new String[]{"-1", "0", "1"};
        String[] mc = new String[]{"未处理", "未返校", "已返校"};
        this.fxztTjList = dao.arrayToList(dm, mc);
    }

    // ====================学情分类（北京中医药）==========================
    private List<HashMap<String, String>> xqflTjList;

    public List<HashMap<String, String>> getXqflTjList() {
        return xqflTjList;
    }

    public void setXqflTjList() {
        DAO dao = DAO.getInstance();
        String sql = "select xqdm dm,xqmc mc from xg_bjzyy_xqyb_xqfl";
        List<HashMap<String, String>> xqflTjList = dao.getList(sql,
                new String[]{}, new String[]{"dm", "mc"});
        this.xqflTjList = xqflTjList;
    }

    // ====================咨询时间段（北京中医药）==========================
    private List<HashMap<String, String>> sjdTjList;

    public List<HashMap<String, String>> getSjdTjList() {
        return sjdTjList;
    }

    public void setSjdTjList() {
        DAO dao = DAO.getInstance();
        String sql = "select sjdmc dm,sjdmc mc from XG_XLZX_SJDB";
        List<HashMap<String, String>> sjdTjList = dao.getList(sql,
                new String[]{}, new String[]{"dm", "mc"});
        this.sjdTjList = sjdTjList;
    }

    // ====================教师身份（北京中医药）==========================
    private List<HashMap<String, String>> jssfTjList;

    public List<HashMap<String, String>> getJssfTjList() {
        return jssfTjList;
    }

    public void setJssfTjList() {

        DAO dao = DAO.getInstance();
        String[] dm = {"专职辅导员", "兼职辅导员", "借调辅导员", "优于保研", "班主任"};
        String[] mc = {"专职辅导员", "兼职辅导员", "借调辅导员", "优于保研", "班主任"};
        this.jssfTjList = dao.arrayToList(dm, mc);
    }

    // ====================问题紧急程度（北京中医药）==========================
    private List<HashMap<String, String>> wtjjcdTjList;

    public List<HashMap<String, String>> getWtjjcdTjList() {
        return wtjjcdTjList;
    }

    public void setWtjjcdTjList() {

        DAO dao = DAO.getInstance();
        String[] dm = {"一般紧急", "比较紧急", "非常紧急"};
        String[] mc = {"一般紧急", "比较紧急", "非常紧急"};
        this.wtjjcdTjList = dao.arrayToList(dm, mc);
    }

    /**
     * @description    ： TODO
     * @author ： CP（1352）
     * @date        ： 2018-1-29 下午05:55:45
     * @return        : the syTjList
     */
    public List<HashMap<String, String>> getSyTjList() {
        return syTjList;
    }

    /**
     * @param ：syTjList the syTjList to set
     * @description    ： TODO
     * @author ： CP（1352）
     * @date        ： 2018-1-29 下午05:55:45
     */
    public void setSyTjList(List<HashMap<String, String>> syTjList) {
        this.syTjList = syTjList;
    }

    private List<HashMap<String, String>> xqfl1TjList;

    public List<HashMap<String, String>> getXqfl1TjList() {
        return xqfl1TjList;
    }

    public void setXqfl1TjList() {
        DAO dao = DAO.getInstance();
        String sql = "select xqdm dm,xqmc mc from xg_bjzyy_xqyb_xqfl";
        List<HashMap<String, String>> xqfl1TjList = dao.getList(sql,
                new String[]{}, new String[]{"dm", "mc"});
        this.xqfl1TjList = xqfl1TjList;
    }


    public List<HashMap<String, String>> getYqmcTjList() {
        return yqmcTjList;
    }

    //上海电机学院新增园区条件
    public void setYqmcTjList() {

        List<HashMap<String, String>> list = this.getYxmcList();

        List<HashMap<String, String>> yqmcList = new ArrayList<HashMap<String, String>>();

        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                HashMap<String, String> map = list.get(i);
                HashMap<String, String> yqmcMap = new HashMap<String, String>();
                if (!Base.isNull(map.get("dm"))) {
                    yqmcMap.put("dm", map.get("dm"));
                    yqmcMap.put("mc", map.get("xqmc"));

                    yqmcList.add(yqmcMap);
                }
            }
        }

        this.yqmcTjList = yqmcList;
    }

    public List<HashMap<String, String>> getYxmcList() {
        StringBuilder sql = new StringBuilder();
        sql.append(" select * from  dm_zju_xq");
        return DAO.getInstance().getListNotOut(sql.toString(), new String[]{});
    }

    //户口性质条件
    public List<HashMap<String, String>> getHkxzTjList() {
        return hkxzTjList;
    }

    public void setHkxzTjList() {

        DAO dao = DAO.getInstance();
        String[] dm = {"农业户口", "非农业户口"};
        String[] mc = {"农业户口", "非农业户口"};
        this.hkxzTjList = dao.arrayToList(dm, mc);
    }


    /**
     * @description    ： TODO
     * @author ： CP（1352）
     * @date        ： 2017-12-7 上午11:19:43
     * @return        : the lnjdhkTjList
     */
    public List<HashMap<String, String>> getLnjdhkTjList() {
        return lnjdhkTjList;
    }

    public void setLnjdhkTjList() {
        DAO dao = DAO.getInstance();
        // 户口性质
        String sql = "select distinct zd5 dm,zd5 mc from view_xsbfxx where zd5 is not null";
        List<HashMap<String, String>> lnjdhkList = dao.getList(sql,
                new String[]{}, new String[]{"dm", "mc"});

        this.lnjdhkTjList = lnjdhkList;

    }

    /**
     * @description    ： TODO
     * @author ： CP（1352）
     * @date        ： 2017-12-12 上午09:43:22
     * @return        : the bxxsTjList
     */
    public List<HashMap<String, String>> getBxxsTjList() {
        return bxxsTjList;
    }

    public void setBxxsTjList() {
        DAO dao = DAO.getInstance();
        // 办学形式
        String sql = "select distinct bxxs dm,bxxs mc from view_xsxxcxjg where bxxs is not null";
        List<HashMap<String, String>> bxxsTjList = dao.getList(sql,
                new String[]{}, new String[]{"dm", "mc"});

        this.bxxsTjList = bxxsTjList;

    }

    /**
     * @description    ： TODO
     * @author ： CP（1352）
     * @date        ： 2017-12-12 下午01:49:59
     * @return        : the zjxyTjList
     */
    public List<HashMap<String, String>> getZjxyTjList() {
        return zjxyTjList;
    }

    public void setZjxyTjList() {
        DAO dao = DAO.getInstance();
        // 宗教信仰
        String sql = "select distinct a.zjdm dm,b.zjmc mc  from view_xsxxcxjg a left join zjxxb b on a.zjdm=b.zjdm where a.zjdm is not null";
        List<HashMap<String, String>> zjxyTjList = dao.getList(sql,
                new String[]{}, new String[]{"dm", "mc"});
        this.zjxyTjList = zjxyTjList;
    }

    private List<HashMap<String, String>> zyjrddjTjList;

    /**
     * @return the zyjrddjTjList
     */
    public List<HashMap<String, String>> getZyjrddjTjList() {
        return zyjrddjTjList;
    }

    /**
     * @param zyjrddjTjList要设置的 zyjrddjTjList
     */
    public void setZyjrddjTjList() {
        DAO dao = DAO.getInstance();
        String sql = "select distinct rddjdm dm,rddjmc mc from xg_pjpy_zyjrddjdmb ";
        List<HashMap<String, String>> zyjrddjTjList = dao.getList(sql,
                new String[]{}, new String[]{"dm", "mc"});
        this.zyjrddjTjList = zyjrddjTjList;
    }

    //六困生
    private List<HashMap<String, String>> lksTjList;

    public List<HashMap<String, String>> getLksTjList() {
        return lksTjList;
    }

    public void setLksTjList() {
        DAO dao = DAO.getInstance();
        String sql = "select distinct lksdm dm,lksmc mc from xg_szdwx_lksdmb ";
        List<HashMap<String, String>> lksTjList = dao.getList(sql,
                new String[]{}, new String[]{"dm", "mc"});
        this.lksTjList = lksTjList;
    }

    //寝室长（浙江旅游职业学院）
    private List<HashMap<String, String>> qszTjList;

    /**
     * @return the qszTjList
     */
    public List<HashMap<String, String>> getQszTjList() {
        return qszTjList;
    }

    /**
     * @param qszTjList要设置的 qszTjList
     */
    public void setQszTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"是", "否"};
        String[] mc = {"是", "否"};
        this.qszTjList = dao.arrayToList(dm, mc);
    }

    /**
     * @return the zxsNjList
     */
    public List<HashMap<String, String>> getZxsNjTjList() {
        return zxsNjTjList;
    }

    /**
     * @param zxsNjList要设置的 zxsNjList
     */
    public void setZxsNjTjList() {
        DAO dao = DAO.getInstance();
        String sql = " select distinct nj  from view_xsxxcxjg where nj is not null and (sfzx='在校' or sfzx is null) order by nj ";
        List<HashMap<String, String>> zxsNjTjList = dao.getList(sql,
                new String[]{}, new String[]{"nj", "nj"});
        this.zxsNjTjList = zxsNjTjList;
    }

    /**
     * @return the zxsXyTjList
     */
    public List<HashMap<String, Object>> getZxsXyTjList() {
        return zxsXyTjList;
    }

    /**
     * @param zxsXyTjList要设置的 zxsXyTjList
     */
    public void setZxsXyTjList(List<HashMap<String, Object>> zxsXyTjList) {
        this.zxsXyTjList = zxsXyTjList;
    }

    /**
     * @return the zxsZyTjList
     */
    public List<HashMap<String, Object>> getZxsZyTjList() {
        return zxsZyTjList;
    }

    /**
     * @param zxsZyTjList要设置的 zxsZyTjList
     */
    public void setZxsZyTjList(List<HashMap<String, Object>> zxsZyTjList) {
        this.zxsZyTjList = zxsZyTjList;
    }

    /**
     * @return the zxsBjTjList
     */
    public List<HashMap<String, Object>> getZxsBjTjList() {
        return zxsBjTjList;
    }

    /**
     * @param zxsBjTjList要设置的 zxsBjTjList
     */
    public void setZxsBjTjList(List<HashMap<String, Object>> zxsBjTjList) {
        this.zxsBjTjList = zxsBjTjList;
    }

    /**
     * @return the zxsXzTjList
     */
    public List<HashMap<String, String>> getZxsXzTjList() {
        return zxsXzTjList;
    }

    /**
     * @param zxsXzTjList要设置的 zxsXzTjList
     */
    public void setZxsXzTjList() {
        DAO dao = DAO.getInstance();
        String sql = " select distinct xz dm,xz mc from view_xsxxcxjg where xz is not null and (sfzx='在校' or sfzx is null) order by xz ";
        List<HashMap<String, String>> zxsXzTjList = dao.getList(sql,
                new String[]{}, new String[]{"dm", "mc"});
        this.zxsXzTjList = zxsXzTjList;
    }

    /**
     * @return the zxsXjlbTjList
     */
    public List<HashMap<String, String>> getZxsXjlbTjList() {
        return zxsXjlbTjList;
    }

    /**
     * @param zxsXjlbTjList要设置的 zxsXjlbTjList
     */
    public void setZxsXjlbTjList() {
        DAO dao = DAO.getInstance();
        String sql = " select distinct a.xjlbdm dm,b.xjlbmc mc from view_xsxxcxjg a left join dm_xjlb b on a.xjlbdm=b.xjlbdm where b.xjlbmc is not null and (a.sfzx='在校' or a.sfzx is null) order by a.xjlbdm ";
        List<HashMap<String, String>> zxsXjlbTjList = dao.getList(sql,
                new String[]{}, new String[]{"dm", "mc"});
        this.zxsXjlbTjList = zxsXjlbTjList;
    }

    /**
     * @return the FzxsNjList
     */
    public List<HashMap<String, String>> getFzxsNjList() {
        return fzxsNjList;
    }

    /**
     * @param FzxsNjList要设置的 FzxsNjList
     */
    public void setFzxsNjList() {
        DAO dao = DAO.getInstance();
        String sql = " select distinct nj  from view_xsxxcxjg where nj is not null and (sfzx='不在校') order by nj ";
        List<HashMap<String, String>> FzxsNjList = dao.getList(sql,
                new String[]{}, new String[]{"nj", "nj"});
        this.fzxsNjList = FzxsNjList;
    }

    /**
     * @return the FzxsXyTjList
     */
    public List<HashMap<String, Object>> getFzxsXyTjList() {
        return fzxsXyTjList;
    }

    /**
     * @param FzxsXyTjList要设置的 FzxsXyTjList
     */
    public void setFzxsXyTjList(List<HashMap<String, Object>> FzxsXyTjList) {
        this.fzxsXyTjList = FzxsXyTjList;
    }

    /**
     * @return the FzxsZyTjList
     */
    public List<HashMap<String, Object>> getFzxsZyTjList() {
        return fzxsZyTjList;
    }

    /**
     * @param FzxsZyTjList要设置的 FzxsZyTjList
     */
    public void setFzxsZyTjList(List<HashMap<String, Object>> FzxsZyTjList) {
        this.fzxsZyTjList = FzxsZyTjList;
    }

    /**
     * @return the FzxsBjTjList
     */
    public List<HashMap<String, Object>> getFzxsBjTjList() {
        return fzxsBjTjList;
    }

    /**
     * @param FzxsBjTjList要设置的 FzxsBjTjList
     */
    public void setFzxsBjTjList(List<HashMap<String, Object>> FzxsBjTjList) {
        this.fzxsBjTjList = FzxsBjTjList;
    }

    /**
     * @return the FzxsXzTjList
     */
    public List<HashMap<String, String>> getFzxsXzTjList() {
        return fzxsXzTjList;
    }

    /**
     * @param FzxsXzTjList要设置的 FzxsXzTjList
     */
    public void setFzxsXzTjList() {
        DAO dao = DAO.getInstance();
        String sql = " select distinct xz dm,xz mc from view_xsxxcxjg where xz is not null and (sfzx='不在校') order by xz ";
        List<HashMap<String, String>> FzxsXzTjList = dao.getList(sql,
                new String[]{}, new String[]{"dm", "mc"});
        this.fzxsXzTjList = FzxsXzTjList;
    }

    /**
     * @return the FzxsXjlbTjList
     */
    public List<HashMap<String, String>> getFzxsXjlbTjList() {
        return fzxsXjlbTjList;
    }

    /**
     * @param FzxsXjlbTjList要设置的 FzxsXjlbTjList
     */
    public void setFzxsXjlbTjList() {
        DAO dao = DAO.getInstance();
        String sql = " select distinct a.xjlbdm dm,b.xjlbmc mc from view_xsxxcxjg a left join dm_xjlb b on a.xjlbdm=b.xjlbdm where a.xjlbdm is not null and (a.sfzx='不在校') order by a.xjlbdm ";
        List<HashMap<String, String>> FzxsXjlbTjList = dao.getList(sql,
                new String[]{}, new String[]{"dm", "mc"});
        this.fzxsXjlbTjList = FzxsXjlbTjList;
    }


    public List<HashMap<String, String>> getQjlxmcTjList() {
        return qjlxmcTjList;
    }

    /**
     * @param qszTjList要设置的 qszTjList
     */
    public void setQjlxmcTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"2", "1", "0"};
        String[] mc = {"公假", "病假", "事假"};
        this.qjlxmcTjList = dao.arrayToList(dm, mc);
    }

    //gwTjList
    public List<HashMap<String, String>> getGwTjList() {
        return gwTjList;
    }

    /**
     * @param qszTjList要设置的 qszTjList
     */
    public void setGwTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"部门兼职辅导员", "学院专职辅导员", "借调辅导员", "学院副书记", "班主任", "国际学院、台港澳班主任", "一临二临辅导员"};
        String[] mc = {"部门兼职辅导员", "学院专职辅导员", "借调辅导员", "学院副书记", "班主任", "国际学院、台港澳班主任", "一临二临辅导员"};
        this.gwTjList = dao.arrayToList(dm, mc);
    }

    public List<HashMap<String, String>> getZjcmtjztTjList() {
        return zjcmtjztTjList;
    }

    public void setZjcmtjztTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"0", "1"};
        String[] mc = {"未提交", "已提交"};
        this.zjcmtjztTjList = dao.arrayToList(dm, mc);
    }

    //gwTjList
    public List<HashMap<String, String>> getQjtslxTjList() {
        return qjtslxTjList;
    }

    /**
     * @param qszTjList要设置的 qszTjList
     */
    public void setQjtslxTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"1天", "跨天"};
        String[] mc = {"1天", "跨天"};
        this.qjtslxTjList = dao.arrayToList(dm, mc);
    }

    //qjksjc
    public List<HashMap<String, String>> getQjksjcTjList() {
        return qjksjcTjList;
    }

    /**
     * @param qszTjList要设置的 qszTjList
     */
    public void setQjksjcTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"早自习", "第1节", "第2节", "第3节", "第4节", "第5节", "第6节", "第7节", "第8节", "第9节", "第10节", "第11节", "第12节", "晚自习"};
        String[] mc = {"早自习", "第1节", "第2节", "第3节", "第4节", "第5节", "第6节", "第7节", "第8节", "第9节", "第10节", "第11节", "第12节", "晚自习"};
        this.qjksjcTjList = dao.arrayToList(dm, mc);
    }

    //qjjsjc
    public List<HashMap<String, String>> getQjjsjcTjList() {
        return qjjsjcTjList;
    }

    /**
     * @param qszTjList要设置的 qszTjList
     */
    public void setQjjsjcTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"早自习", "第1节", "第2节", "第3节", "第4节", "第5节", "第6节", "第7节", "第8节", "第9节", "第10节", "第11节", "第12节", "晚自习"};
        String[] mc = {"早自习", "第1节", "第2节", "第3节", "第4节", "第5节", "第6节", "第7节", "第8节", "第9节", "第10节", "第11节", "第12节", "晚自习"};
        this.qjjsjcTjList = dao.arrayToList(dm, mc);
    }

    /**
     * @param FzxsXzTjList要设置的 FzxsXzTjList
     */
    public void setKcmcTjList() {
        DAO dao = DAO.getInstance();
        String sql = " select distinct kcmc dm,kcmc mc from SYS_XSXKXXB where jszgh = ? and '0' || xq = '" + Base.currXq + "' and xn = '" + Base.currXn + "'";
        List<HashMap<String, String>> kcmcTjList = dao.getList(sql,
                new String[]{this.getUsername()}, new String[]{"dm", "mc"});
        this.kcmcTjList = kcmcTjList;
    }

    /**
     * @return the FzxsXjlbTjList
     */
    public List<HashMap<String, String>> getKcmcTjList() {
        return kcmcTjList;
    }

    public void SetUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    //qjjsjc
    public List<HashMap<String, String>> getLbTjList() {
        return lbTjList;
    }

    /**
     * @param qszTjList要设置的 qszTjList
     */
    public void setLbTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"jf", "kf"};
        String[] mc = {"加分项", "减分项"};
        this.lbTjList = dao.arrayToList(dm, mc);
    }

    /**
     * @param FzxsXzTjList要设置的 FzxsXzTjList
     */
    public void setJclbTjList() {
        DAO dao = DAO.getInstance();
        String sql = " select distinct gyjllbdldm dm,gyjllbdlmc mc from XG_GYGL_NEW_GYJLLBDLB ";
        List<HashMap<String, String>> jclbTjList = dao.getList(sql,
                new String[]{}, new String[]{"dm", "mc"});
        this.jclbTjList = jclbTjList;
    }

    /**
     * @return the FzxsXjlbTjList
     */
    public List<HashMap<String, String>> getJclbTjList() {
        return jclbTjList;
    }

    public List<HashMap<String, String>> getHdplTjList() {
        return hdplTjList;
    }

    public void setHdplTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"1", "2"};
        String[] mc = {"天", "周"};
        this.hdplTjList = dao.arrayToList(dm, mc);
    }

    //专兼职
    public List<HashMap<String, String>> getZjzTjList() {
        return zjzTjList;
    }

    public void setZjzTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"专职", "兼职"};
        String[] mc = {"专职", "兼职"};
        this.zjzTjList = dao.arrayToList(dm, mc);
    }

    //审定状态sdztTjList
    public List<HashMap<String, String>> getSdztTjList() {
        return sdztTjList;
    }

    public void setSdztTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"0", "1"};
        String[] mc = {"未审定", "已审定"};
        this.sdztTjList = dao.arrayToList(dm, mc);
    }

    //浙江旅游综合分导入 审定状态
    public List<HashMap<String, String>> getZjlysdztTjList() {
        return zjlysdztTjList;
    }

    public void setZjlysdztTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"0", "1", "2"};
        String[] mc = {"未审定", "已审定", "退回"};
        this.zjlysdztTjList = dao.arrayToList(dm, mc);
    }

    //浙江旅游综合分审核 审定状态
    public List<HashMap<String, String>> getZjlyshsdztTjList() {
        return zjlyshsdztTjList;
    }

    public void setZjlyshsdztTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"0", "1"};
        String[] mc = {"已审定", "未审定"};
        this.zjlyshsdztTjList = dao.arrayToList(dm, mc);
    }

    /**
     * @throws
     * @描述:jfxmdmTjList
     * @作者：yxy[工号：1206]
     * @日期：2016-6-23 下午05:49:01
     * @修改记录: 修改者名字-修改日期-修改内容
     * void 返回类型
     */
    public void setJfxmdmTjList() {
        DAO dao = DAO.getInstance();
        String sql = "select jfxmdm dm,jfxmmc mc from xg_zjly_zhszf_jfxmb order by xmmkdm ,jfxmdm";
        List<HashMap<String, String>> jfxmdmTjList = dao.getList(sql,
                new String[]{}, new String[]{"dm", "mc"});
        this.jfxmdmTjList = jfxmdmTjList;
    }

    /**
     * @return the FzxsXjlbTjList
     */
    public List<HashMap<String, String>> getJfxmdmTjList() {
        return jfxmdmTjList;
    }

    /**
     * @throws
     * @描述:xmmkdmTjList
     * @作者：yxy[工号：1206]
     * @日期：2016-6-23 下午05:49:01
     * @修改记录: 修改者名字-修改日期-修改内容
     * void 返回类型
     */
    public void setXmmkdmTjList() {
        DAO dao = DAO.getInstance();
        String sql = "select xmmkdm dm,xmmkmc mc from xg_zjly_zhszf_mkxmb  order by xmmkdm";
        List<HashMap<String, String>> xmmkdmTjList = dao.getList(sql,
                new String[]{}, new String[]{"dm", "mc"});
        this.xmmkdmTjList = xmmkdmTjList;
    }

    /**
     * @return the FzxsXjlbTjList
     */
    public List<HashMap<String, String>> getXmmkdmTjList() {
        return xmmkdmTjList;
    }

    //浙江旅游yyzcTjList
    public List<HashMap<String, String>> getYyzcTjList() {
        return yyzcTjList;
    }

    public void setYyzcTjList() {
        DAO dao = DAO.getInstance();
        //2017-03-06 浙江旅游服务器已去掉这个高级查询条件。
        String[] dm = {
                "2016-10-10至2016-10-16"
                , "2016-17-10至2016-10-23"
                , "2016-10-24至2016-10-30"
                , "2016-10-31至2016-11-06"
                , "2016-11-07至2016-11-13"
                , "2016-11-14至2016-11-20"
                , "2016-11-21至2016-11-27"
                , "2016-11-28至2016-12-04"
                , "2016-12-05至2016-12-11"
                , "2016-12-12至2016-12-18"
                , "2016-12-19至2016-12-25"
                , "2016-12-26至2016-12-31"

        };
        String[] mc = {
                "2016-10-10  至  2016-10-16"
                , "2016-17-10  至  2016-10-23"
                , "2016-10-24  至  2016-10-30"
                , "2016-10-31  至  2016-11-06"
                , "2016-11-07  至  2016-11-13"
                , "2016-11-14  至  2016-11-20"
                , "2016-11-21  至  2016-11-27"
                , "2016-11-28  至  2016-12-04"
                , "2016-12-05  至  2016-12-11"
                , "2016-12-12  至  2016-12-18"
                , "2016-12-19  至  2016-12-25"
                , "2016-12-26  至  2016-12-31"

        };
        this.yyzcTjList = dao.arrayToList(dm, mc);
    }

    //素质拓展项目申报—项目维护，增加参赛模式高级查询条件
    public List<HashMap<String, String>> getCsmsTjList() {
        return csmsTjList;
    }

    public void setCsmsTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {
                "1"
                , "2"
        };
        String[] mc = {
                "个人"
                , "团体"

        };
        this.csmsTjList = dao.arrayToList(dm, mc);
    }

    //素质拓展项目申报—项目维护，增加认定状态高级查询条件
    public List<HashMap<String, String>> getRdztTjList() {
        return rdztTjList;
    }

    public void setRdztTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {
                "已认定"
                , "未认定"
        };
        String[] mc = {
                "已认定"
                , "未认定"

        };
        this.rdztTjList = dao.arrayToList(dm, mc);
    }

    //勤工日期
    public List<HashMap<String, String>> getQgrqTjList() {
        return qgrqTjList;
    }

    public void setQgrqTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {
                "星期一"
                , "星期二"
                , "星期三"
                , "星期四"
                , "星期五"
                , "星期六"
                , "星期日"
        };
        String[] mc = {
                "星期一"
                , "星期二"
                , "星期三"
                , "星期四"
                , "星期五"
                , "星期六"
                , "星期日"

        };
        this.qgrqTjList = dao.arrayToList(dm, mc);
    }

    //勤工日期
    public List<HashMap<String, String>> getQgmxTjList() {
        return qgmxTjList;
    }

    public void setQgmxTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {
                "1"
                , "2"
                , "3"
                , "4"
                , "5"
                , "6"
                , "7"
                , "8"
                , "9"
        };
        String[] mc = {
                "早自修"
                , "1-2节"
                , "3-4节"
                , "5-6节"
                , "7-8节"
                , "9-10节"
                , "11-12节"
                , "晚自习"
                , "住宿"

        };
        this.qgmxTjList = dao.arrayToList(dm, mc);
    }

    //北京中医药大学奖项级别高级查询条件
    public List<HashMap<String, String>> getJxjbTjList() {
        return jxjbTjList;
    }

    //北京中医药大学奖项级别高级查询条件
    public void setJxjbTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {
                "国家级"
                , "省（部）级"
                , "市（地）级"
                , "区（县）级"
                , "校（院）级"
        };
        String[] mc = {
                "国家级"
                , "省（部）级"
                , "市（地）级"
                , "区（县）级"
                , "校（院）级"
        };
        this.jxjbTjList = dao.arrayToList(dm, mc);
    }

    //北京中医药大学奖项级别高级查询条件
    public List<HashMap<String, String>> getJxlbnewTjList() {
        return jxlbnewTjList;
    }

    //北京中医药大学奖项级别高级查询条件
    public void setJxlbnewTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {
                "个人"
                , "团体"
        };
        String[] mc = {
                "个人"
                , "团体"
        };
        this.jxlbnewTjList = dao.arrayToList(dm, mc);
    }

    //浙江旅游高级查询条件
    public List<HashMap<String, String>> getCxblbTjList() {
        return cxblbTjList;
    }

    public void setCxblbTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"续保", "新参保", "未参保"};
        String[] mc = {"续保", "新参保", "未参保"};
        this.cxblbTjList = dao.arrayToList(dm, mc);
    }

    //浙江旅游高级查询条件
    public List<HashMap<String, String>> getShbzTjList() {
        return shbzTjList;
    }

    public void setShbzTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"审核通过", "审核不通过"};
        String[] mc = {"审核通过", "审核不通过"};
        this.shbzTjList = dao.arrayToList(dm, mc);
    }

    //上海体育来源地区
    public List<HashMap<String, String>> getLydqTjList() {
        return lydqTjList;
    }

    public void setLydqTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"东部地区", "西部地区", "中部地区"};
        String[] mc = {"东部地区", "西部地区", "中部地区"};
        this.lydqTjList = dao.arrayToList(dm, mc);
    }

    //上海体育兴趣爱好
    public List<HashMap<String, String>> getXqahTjList() {
        return xqahTjList;
    }

    public void setXqahTjList() {
        DAO dao = DAO.getInstance();
        String sql = "select xqahdm dm,xqahmc mc from xg_xsxx_xqahb  order by xqahdm";
        List<HashMap<String, String>> xmmkdmTjList = dao.getList(sql,
                new String[]{}, new String[]{"dm", "mc"});
        this.xqahTjList = xmmkdmTjList;
    }

    //上海体育担任职务
    public List<HashMap<String, String>> getDrzwTjList() {
        return drzwTjList;
    }

    public void setDrzwTjList() {
        DAO dao = DAO.getInstance();
        String sql = "select zwid dm,zwmc mc from xg_szdw_xsgb_zwb  order by zwid";
        List<HashMap<String, String>> xmmkdmTjList = dao.getList(sql,
                new String[]{}, new String[]{"dm", "mc"});
        this.drzwTjList = xmmkdmTjList;
    }

    //上海体育是否清镇
    public List<HashMap<String, String>> getSfqzTjList() {
        return sfqzTjList;
    }

    public void setSfqzTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"是", "否"};
        String[] mc = {"是", "否"};
        this.sfqzTjList = dao.arrayToList(dm, mc);
    }

    //上海戏剧 评奖评优-综测维护-其他奖项设置 奖项代码 高级查询条件
    public List<HashMap<String, String>> getJxdmxTjList() {
        return jxdmxTjList;
    }

    public void setJxdmxTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"1", "2"};
        String[] mc = {"专业奖学金", "新生奖学金"};
        this.jxdmxTjList = dao.arrayToList(dm, mc);
    }

    //华师大放款操作，是否放款高级查询条件
    public List<HashMap<String, String>> getFkztTjList() {
        return fkztTjList;
    }

    public void setFkztTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"1", "0"};
        String[] mc = {"是", "否"};
        this.fkztTjList = dao.arrayToList(dm, mc);
    }

    public List<HashMap<String, String>> getHdlxTjList() {
        return hdlxTjList;
    }

    public void setHdlxTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"1", "0"};
        String[] mc = {"校外", "校内"};
        this.hdlxTjList = dao.arrayToList(dm, mc);
    }

    //季度
    public List<HashMap<String, String>> getJdTjList() {
        return jdTjList;
    }

    //季度
    public void setJdTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"第一季度", "第二季度", "第三季度", "第四季度"};
        String[] mc = {"第一季度", "第二季度", "第三季度", "第四季度"};
        this.jdTjList = dao.arrayToList(dm, mc);
    }

    //浙大-勤工助学-岗位类别
    public List<HashMap<String, String>> getGwlbTjList() {
        return this.gwlbTjList;
    }

    public void setGwlbTjList() {
        DAO dao = DAO.getInstance();
        String sql = "select GWXZDM dm,GWXZMC mc from XG_QGZX_GWXZDMB order by dm asc";
        List<HashMap<String, String>> gwlbList = dao.getList(sql,
                new String[]{}, new String[]{"dm", "mc"});
        this.gwlbTjList = gwlbList;
    }

    //浙大-勤工助学-校区
    public List<HashMap<String, String>> getXiaoqTjList() {
        return this.xiaoqTjList;
    }

    public void setXiaoqTjList() {
        DAO dao = DAO.getInstance();
        String sql = "select  dm,xqmc mc  from dm_zju_xq  order by dm asc";
        List<HashMap<String, String>> xiaoqList = dao.getList(sql,
                new String[]{}, new String[]{"dm", "mc"});
        this.xiaoqTjList = xiaoqList;
    }

    //浙大-学习评价-短学期
    public List<HashMap<String, String>> getDxqTjList() {
        return this.dxqTjList;
    }

    public void setDxqTjList() {
        DAO dao = DAO.getInstance();
        String sql = "select dxqdm dm,dxqmc mc from xg_jskp_dxq order by dxqdm asc";
        List<HashMap<String, String>> dxqList = dao.getList(sql, new String[]{}, new String[]{"dm", "mc"});
        this.dxqTjList = dxqList;
    }

    //政治面貌新[党组织关系转出-华科大武昌分校]
    public List<HashMap<String, String>> getZzmmnewTjList() {
        return zzmmnewTjList;
    }

    //政治面貌新[党组织关系转出-华科大武昌分校]
    public void setZzmmnewTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"正式党员", "预备党员"};
        String[] mc = {"正式党员", "预备党员"};
        this.zzmmnewTjList = dao.arrayToList(dm, mc);
    }

    //是否省内[党组织关系转出-华科大武昌分校]
    public List<HashMap<String, String>> getSfsnTjList() {
        return sfsnTjList;
    }

    //是否省内[党组织关系转出-华科大武昌分校]
    public void setSfsnTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"省内", "省外"};
        String[] mc = {"省内", "省外"};
        this.sfsnTjList = dao.arrayToList(dm, mc);
    }

    //所在党支部[党组织关系转出-华科大武昌分校]
    public List<HashMap<String, String>> getSzdzbTjList() {
        return szdzbTjList;
    }

    //所在党支部[党组织关系转出-华科大武昌分校]
    public void setSzdzbTjList() {
        DAO dao = DAO.getInstance();
        String sql = "select  DZBDM dm, DZBMC mc  from xg_dtjs_zzgxzc_dzbdmb  order by dm asc";
        List<HashMap<String, String>> szdzbList = dao.getList(sql,
                new String[]{}, new String[]{"dm", "mc"});
        this.szdzbTjList = szdzbList;
    }

    //心理健康咨询-谈话记录-特殊学生维护，（困难类型）【西安科技大学个性化,预警程度】
    public List<HashMap<String, String>> getKnlxTjList() {
        return knlxTjList;
    }

    //心理健康咨询-谈话记录-特殊学生维护，（困难类型）【西安科技大学个性化，预警程度】
    public void setKnlxTjList() {
        DAO dao = DAO.getInstance();
        String sql = "select knlxdm dm,knlxmc mc from TSXS_KNLXB";
        List<HashMap<String, String>> knlxList = dao.getList(sql,
                new String[]{}, new String[]{"dm", "mc"});
        this.knlxTjList = knlxList;
    }

    //心理健康咨询-咨询师管理-咨询师维护，（擅长领域）【湖南城市学院】
    public List<HashMap<String, String>> getSclyTjList() {
        return sclyTjList;
    }

    //心理健康咨询-咨询师管理-咨询师维护，（擅长领域）【湖南城市学院】
    public void setSclyTjList() {
        DAO dao = DAO.getInstance();
        String sql = "select sclydm dm,sclymc mc from TSXS_SCLYB";
        List<HashMap<String, String>> sclyList = dao.getList(sql,
                new String[]{}, new String[]{"dm", "mc"});
        this.sclyTjList = sclyList;
    }

    //重庆移通 素质拓展 板块归属
    public List<HashMap<String, String>> getBkgsTjList() {
        return this.bkgsTjList;
    }

    public void setBkgsTjList() {
        DAO dao = DAO.getInstance();
        String sql = "select  dm,mc  from XG_SZTZ_BKGS  order by dm asc";
        List<HashMap<String, String>> bkgsTjList = dao.getList(sql,
                new String[]{}, new String[]{"dm", "mc"});
        this.bkgsTjList = bkgsTjList;
    }

    //开、关
    public List<HashMap<String, String>> getKgTjList() {
        return kgTjList;
    }

    //开、关
    public void setKgTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"开", "关"};
        String[] mc = {"开", "关"};
        this.kgTjList = dao.arrayToList(dm, mc);
    }

    //是否需要约谈
    public List<HashMap<String, String>> getSfxyytTjList() {
        return sfxyytTjList;
    }

    //是否需要约谈
    public void setSfxyytTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"1", "0"};
        String[] mc = {"是", "否"};
        this.sfxyytTjList = dao.arrayToList(dm, mc);
    }

    //是否已约谈
    public List<HashMap<String, String>> getSfyytTjList() {
        return sfyytTjList;
    }

    //是否已约谈
    public void setSfyytTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"1", "0"};
        String[] mc = {"是", "否"};
        this.sfyytTjList = dao.arrayToList(dm, mc);
    }

    //角色类型
    public List<HashMap<String, String>> getJsTjList() {
        return jsTjList;
    }

    //角色类型
    public void setJsTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"xx", "xy"};
        String[] mc = {"校级", "院级"};
        this.jsTjList = dao.arrayToList(dm, mc);
    }

    public List<HashMap<String, String>> getJjlxTjList() {
        return jjlxTjList;
    }

    public void setJjlxTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"1", "2", "3"};
        String[] mc = {"卫生检查", "安全检查", "纪律检查"};
        this.jjlxTjList = dao.arrayToList(dm, mc);
    }

    public List<HashMap<String, String>> getTjztwpqsTjList() {
        return tjztwpqsTjList;
    }

    public void setTjztwpqsTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"1", "0"};
        String[] mc = {"已提交", "未提交"};
        this.tjztwpqsTjList = dao.arrayToList(dm, mc);
    }

    public List<HashMap<String, String>> getYxzt1TjList() {
        return yxzt1TjList;
    }

    public void setYxzt1TjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"1", "0"};
        String[] mc = {"有效", "无效"};
        this.yxzt1TjList = dao.arrayToList(dm, mc);
    }

    public List<HashMap<String, String>> getXmlbTjList() {
        return xmlbTjList;
    }

    public void setXmlbTjList() {
        DAO dao = DAO.getInstance();
        StringBuilder sql = new StringBuilder();
        sql.append(" select xmlbdm dm,xmlbmc mc from xg_jskp_xmlbb order by xmlbmc asc");
        List<HashMap<String, String>> tempList = dao.getList(sql.toString(),
                new String[]{}, new String[]{"dm", "mc"});
        this.xmlbTjList = tempList;
    }


    public List<HashMap<String, String>> getRcswbxlbTjList() {
        return rcswbxlbTjList;
    }

    public void setRcswbxlbTjList() {
        DAO dao = DAO.getInstance();
        String sql = "select distinct dm,mc from XG_RCSW_YLBX_BXLBB ";
        List<HashMap<String, String>> rcswbxlbTjList = dao.getList(sql, new String[]{}, new String[]{"dm", "mc"});
        this.rcswbxlbTjList = rcswbxlbTjList;
    }

    public List<HashMap<String, String>> getBxsfblTjList() {
        return bxsfblTjList;
    }

    public void setBxsfblTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"自愿办理", "不办理"};
        String[] mc = {"自愿办理", "不办理"};
        this.bxsfblTjList = dao.arrayToList(dm, mc);
    }


    /**
     * @return the jtgjTjList
     */
    public List<HashMap<String, String>> getJtgjTjList() {
        return jtgjTjList;
    }

    /**
     * @param jtgjTjList要设置的 jtgjTjList
     */
    public void setJtgjTjList() {
        DAO dao = DAO.getInstance();
        String sql = "  select dm,mc from xg_rcsw_lxqxdj_dmwhb ";
        List<HashMap<String, String>> jtgjList = dao.getList(sql,
                new String[]{}, new String[]{"dm", "mc"});
        this.jtgjTjList = jtgjList;
    }

    public List<HashMap<String, String>> getXmdlTjList() {
        return xmdlTjList;
    }

    public void setXmdlTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"gdx", "zlx"};
        String[] mc = {"固定项", "自立项"};
        this.xmdlTjList = dao.arrayToList(dm, mc);
    }


    /**
     * 青岛酒店管理职业技术学院_发文结果(可通用)
     */
    public List<HashMap<String, String>> getFwjgTjList() {
        return fwjgTjList;
    }

    public void setFwjgTjList() {
        DAO dao = DAO.getInstance();
        String sql = "select fwjg dm,fwjg mc from (select distinct case when a.jcwh is not null then '解除处分' " +
                "when a.zzwh is not null then '终止处分' " +
                "when a.ssjg is not null then a.ssjg else '处分成立' end fwjg " +
                "from xg_wjcf_wjcfb a) ";
        List<HashMap<String, String>> fwjgList = dao.getList(sql,
                new String[]{}, new String[]{"dm", "mc"});
        this.fwjgTjList = fwjgList;
    }

    /**
     * @param fwlxTjList要设置的 fwlxTjList
     */
    public void setFwlxTjList() {
        DAO dao = DAO.getInstance();
        String sql = "select dm,mc from xg_dekt_fwlxb";
        List<HashMap<String, String>> fwtjList = dao.getList(sql,
                new String[]{}, new String[]{"dm", "mc"});
        this.fwlxTjList = fwtjList;
    }

    public List<HashMap<String, String>> getFwlxTjList() {
        return fwlxTjList;
    }

    public List<HashMap<String, String>> getPcjgTjList() {
        return pcjgTjList;
    }

    public void setPcjgTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"没有问题", "一般心理问题", "严重心理问题"};
        String[] mc = {"没有问题", "一般心理问题", "严重心理问题"};
        this.pcjgTjList = dao.arrayToList(dm, mc);
    }

    public List<HashMap<String, String>> getSfgzTjList() {
        return sfgzTjList;
    }

    public void setSfgzTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"0", "1"};
        String[] mc = {"否", "是"};
        this.sfgzTjList = dao.arrayToList(dm, mc);
    }

    public List<HashMap<String, String>> getLxnewTjList() {
        return lxnewTjList;
    }

    public void setLxnewTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"因病住院", "意外住院", "因病门诊", "意外门诊"};
        String[] mc = {"因病住院", "意外住院", "因病门诊", "意外门诊"};
        this.lxnewTjList = dao.arrayToList(dm, mc);
    }

    public List<HashMap<String, String>> getClzbTjList() {
        return clzbTjList;
    }

    public void setClzbTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"因病", "意外"};
        String[] mc = {"因病", "意外"};
        this.clzbTjList = dao.arrayToList(dm, mc);
    }

    public List<HashMap<String, String>> getBxxzTjList() {
        return bxxzTjList;
    }

    public void setBxxzTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"门诊报销", "城镇医保报销", "意外保险报销", "实习险报销", "校方险报销"};
        String[] mc = {"门诊报销", "城镇医保报销", "意外保险报销", "实习险报销", "校方险报销"};
        this.bxxzTjList = dao.arrayToList(dm, mc);
    }

    public List<HashMap<String, String>> getSfsxztTjList() {
        return sfsxztTjList;
    }

    public void setSfsxztTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"否", "是"};
        String[] mc = {"否", "是"};
        this.sfsxztTjList = dao.arrayToList(dm, mc);
    }

    /**
     * @description    ： TODO
     * @author ： CP（1352）
     * @date        ： 2018-1-11 上午11:48:56
     * @return        : the sfgcjTjList
     */
    public List<HashMap<String, String>> getSfgcjTjList() {
        return sfgcjTjList;
    }

    /**
     * @param ：sfgcjTjList the sfgcjTjList to set
     * @description    ： TODO
     * @author ： CP（1352）
     * @date        ： 2018-1-11 上午11:48:56
     */
    public void setSfgcjTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"否", "是"};
        String[] mc = {"否", "是"};
        this.sfgcjTjList = dao.arrayToList(dm, mc);
    }
/*	//南通科技增加考生类别ntkjlbTjList
	public List<HashMap<String, String>> getNtkjlbTjList(){

	}

	//南通科技增加考生类别
	public void setNtkjlbTjList(){

		this.ntkjlbTjList= kslbList;
	}*/

    /**
     * @return the ntkjlbTjList
     */
    public List<HashMap<String, String>> getKslbTjList() {
        return kslbTjList;
    }

    /**
     * @param ntkjlbTjList要设置的 ntkjlbTjList
     */
    public void setKslbTjList() {
        DAO dao = DAO.getInstance();
        String sql = "select kslbdm dm , kslbmc mc from XG_XSXX_KSLBDMB";
        List<HashMap<String, String>> kslbList = dao.getList(sql,
                new String[]{}, new String[]{"dm", "mc"});
        this.kslbTjList = kslbList;
    }

    public List<HashMap<String, String>> getHdxsTjList() {
        return hdxsTjList;
    }

    public void setHdxsTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"活动", "讲座"};
        String[] mc = {"活动", "讲座"};
        this.hdxsTjList = dao.arrayToList(dm, mc);
    }

    /**
     * @description    ： TODO
     * @author ： 柳俊（1282）
     * @date        ： 2018-1-24 上午11:57:00
     * @return        : the hdzlTjList
     */
    public List<HashMap<String, String>> getHdzlTjList() {
        return hdzlTjList;
    }

    /**
     * @param ：hdzlTjList the hdzlTjList to set
     * @description    ： TODO
     * @author ： 柳俊（1282）
     * @date        ： 2018-1-24 上午11:57:00
     */
    public void setHdzlTjList() {
        DAO dao = DAO.getInstance();
        String sql = "select hdlxdm dm , hdlxmc mc from xg_hdgl_hdlxdmb";
        List<HashMap<String, String>> hdzlTjList = dao.getList(sql,
                new String[]{}, new String[]{"dm", "mc"});
        this.hdzlTjList = hdzlTjList;
    }

    public List<HashMap<String, String>> getHdjxztTjList() {
        return hdjxztTjList;
    }

    public void setHdjxztTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"未开始", "进行中", "已结束"};
        String[] mc = {"未开始", "进行中", "已结束"};
        this.hdjxztTjList = dao.arrayToList(dm, mc);
    }

    public List<HashMap<String, String>> getSfgkTjList() {
        return sfgkTjList;
    }

    public void setSfgkTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"0", "1"};
        String[] mc = {"否", "是"};
        this.sfgkTjList = dao.arrayToList(dm, mc);
    }

    public List<HashMap<String, String>> getSfxnTjList() {
        return sfxnTjList;
    }

    public void setSfxnTjList() {
        DAO dao = DAO.getInstance();
        String[] dm = {"0", "1"};
        String[] mc = {"学期", "学年"};
        this.sfxnTjList = dao.arrayToList(dm,mc);
    }
    private List<HashMap<String,String>> kclbdmTjList;

    public List<HashMap<String, String>> getKclbdmTjList() {
        return kclbdmTjList;
    }

    public void setKclbdmTjList(){
        DAO dao = DAO.getInstance();
        String sql = "select dm , mc from XG_SZDW_KCLBB";
        List<HashMap<String, String>> kclbdmTjList = dao.getList(sql,
            new String[]{}, new String[]{"dm", "mc"});
        this.kclbdmTjList = kclbdmTjList;
    }

    public List<HashMap<String, String>> getCzTjList() {
        return czTjList;
    }

    public void setCzTjList() {
        DAO dao=DAO.getInstance();
        String[] dm = {"0", "1"};
        String[] mc = {"否", "是"};
        this.czTjList =dao.arrayToList(dm,mc);
    }
}

 