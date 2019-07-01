
<%@ page language="java" contentType="text/html; charset=GBK" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini"%>
    <script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
    <script type="text/javascript" language="javascript" src="js/comm/commFunction.js"></script>
    <script type="text/javascript" src="xsgzgl/sxzzjygl/bjxfjs/bjxfjshb/js/bjxfjshbList.js"></script>
    <script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
    <script type="text/javascript">
        var gridSetting = {
            caption: "活动列表",
            pager: "pager",
            url: "sxzzjy_bjxfjshb.do?method=getList&type=query",
            colList: [
                {label: 'nzhbid', name: 'nzhbid', index: 'nzhbid',hidden:true},
                {label: 'nzzjid', name: 'nzzjid', index: 'nzzjid',hidden:true},
                {label: 'jgid', name: 'jgid', index: 'jgid',hidden:true,key:true},
                {label: '班级代码', name: 'bjdm', index: 'bjdm',hidden:true},
                {label: '录入时间', name: 'lrsj', index: 'lrsj',hidden:true},
                {label: '班级', name: 'bjmc', index: 'bjmc', width: '10%',formatter:bjLink},
                {label: '学院', name: 'xymc', index: 'xymc', width: '10%'},
                {label: '名称', name: 'xfjsmc', index: 'xfjsmc', width: '10%'},
                {label: '申报类型', name: 'sblxmc', index: 'sblxmc', width: '10%'},
                {label: '学年学期', name: 'xnxq', index: 'xnxq', width: '10%'},
                {label: '中期汇报状态', name: 'nzhbshztmc', index: 'nzhbshztmc', width: '10%'},
                {label: '年终总结状态', name: 'nzzjshztmc', index: 'nzzjshztmc', width: '10%'},
                {label: 'nzhbshzt', name: 'nzhbshzt', index: 'nzhbshzt', hidden:true},
                {label: 'nzzjshzt', name: 'nzzjshzt', index: 'nzzjshzt', hidden:true},
                {label: 'nzhbsplc', name: 'nzhbsplc', index: 'nzhbsplc', hidden:true},
                {label: 'nzzjsplc', name: 'nzzjsplc', index: 'nzzjsplc', hidden:true}

            ],
            sortname: "lrsj",
            sortorder: "desc"
        };
        jQuery(function () {
            jQuery("#dataTable").initGrid(gridSetting);

        });

        function searchRs() {
            var map = getSuperSearch();
            jQuery("#dataTable").reloadGrid(map);
        }
        //导入
       /* function importConfig(){
            toImportDataNew("IMPORT_SXZZJYGL_BJXFJSJG");
            return false;
        }*/
    </script>

</head>
<body>
<html:form action="/sxzzjy_bjxfjshb" method="post">
    <input type="hidden" name="isopen_nzzj" id="isopen_nzzj" value="${jcszModel.isopen_nzzj }"/>
    <input type="hidden" name="isopen_nzhb" id="isopen_nzhb" value="${jcszModel.isopen_nzhb }"/>
    <%@ include file="/comm/hiddenValue.jsp" %>
    <div class="tab_cur">
        <p class="location">
            <em>您的当前位置：</em><a>${title}</a>
        </p>
    </div>

    <div class="toolbox">
        <!-- 按钮 -->
        <div class="buttonbox">
            <ul>
                <logic:equal name="writeAble" value="yes">
                    <li>
                        <a href="javascript:void(0);" class="btn_zj" onclick="add('nzhb');return false;">中期汇报申请</a>
                    </li>
                    <li>
                        <a href="javascript:void(0);" class="btn_zj" onclick="add('nzzj');return false;">年度总结申请</a>
                    </li>
                    <li>
                        <a href="javascript:void(0);" class="btn_xg" onclick="update('nzhb');return false;">中期汇报修改</a>
                    </li>
                    <li>
                        <a href="javascript:void(0);" class="btn_xg" onclick="update('nzzj');return false;">年度总结修改</a>
                    </li>
                    <li>
                        <a href="javascript:void(0);" class="btn_sc" onclick="selectHblx('del');return false;">删除</a>
                    </li>
                    <li>
                        <a href="javascript:void(0);" class="btn_shuc" onclick="selectHblx('submit');return false;" >提交</a>
                    </li>
                    <li>
                        <a href="javascript:void(0);" onclick="selectHblx('cancel');return false;" class="btn_sr">撤销</a>
                    </li>

                </logic:equal>
                <li><a href="javascript:void(0);" onclick="bjxfjshbLcinfo();return false;"
                       title="选中一条记录，点击该按钮可以查看审核流程。"
                       class="btn_cs">流程跟踪</a>
                </li>
            </ul>
            <ul>
                <li>
                    <a href="#" class="btn_down" onclick="printWord('nzhb');return false;">中期汇报下载</a>
                </li>
                <li>
                    <a href="#" class="btn_down" onclick="printWord('nzzj');return false;">年终总结下载</a>
                </li>
            </ul>
        </div>
        <!-- 过滤条件 -->
        <%@ include file="/comm/search/superSearchArea.jsp" %>
        <!-- 过滤条件 end-->
    </div>
</html:form>
<div class="main_box">
    <h3 class=datetitle_01>
        <span>班级学风建设汇报列表&nbsp;&nbsp; </span>
    </h3>
    <div class="con_overlfow">
        <table id="dataTable"></table>
        <div id="pager"></div>
    </div>
</div>
</body>
</html>