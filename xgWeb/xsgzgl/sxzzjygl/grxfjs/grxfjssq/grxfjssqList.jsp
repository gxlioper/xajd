<%@ page language="java" contentType="text/html; charset=GBK" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini"%>
    <script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
    <script type="text/javascript" language="javascript" src="js/comm/commFunction.js"></script>
    <script type="text/javascript" src="xsgzgl/sxzzjygl/grxfjs/grxfjssq/js/grxfjssqList.js"></script>
    <script type="text/javascript">
        var gridSetting = {
            caption: "活动列表",
            pager: "pager",
            url: "sxzzjy_grxfjssq.do?method=getList&type=query",
            colList: [
                {label: 'sqid', name: 'sqid', index: 'sqid',hidden:true,key:true},
                {label: '审批流程',name:'splc', index: 'splc',hidden:true},
                {label: '学号', name: 'xh', index: 'xh', width: '6%',formatter:xhLink},
                {label: '姓名', name: 'xm', index: 'xm', width: '6%'},
                {label: '班级', name: 'bjmc', index: 'bjmc', width: '10%'},
                {label: '学院', name: 'xymc', index: 'xymc', width: '10%'},
                {label: '名称', name: 'xfjsmc', index: 'xfjsmc', width: '10%'},
                {label: '申报类型', name: 'sblxmc', index: 'sblxmc', width: '10%'},
                {label: '学年学期', name: 'xnxq', index: 'xnxq', width: '10%'},
                {label: '申请时间', name: 'sqsj', index: 'sqsj',hidden:true},
                {label: 'shzt', name: 'shzt', index: 'shzt',hidden:true},
                {label: '审核状态', name: 'shztmc', index: 'shztmc', width: '5%'}

            ],
            sortname: "sqsj",
            sortorder: "desc"
        };
        jQuery(function () {
            var map = getSuperSearch();
            gridSetting["params"] = map;
            jQuery("#dataTable").initGrid(gridSetting);
        });

        function searchRs() {
            var map = getSuperSearch();
            jQuery("#dataTable").reloadGrid(map);
        }
    </script>

</head>
<body>
<html:form action="/sxzzjy_grxfjssq" method="post">
    <%@ include file="/comm/hiddenValue.jsp" %>
    <input type="hidden" name="isopen" id="isopen" value="${jcszModel.isopen }"/>
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
                        <a href="javascript:void(0);" class="btn_zj" onclick="add();return false;">申请</a>
                    </li>
                    <li>
                        <a href="javascript:void(0);" class="btn_xg" onclick="update();return false;">修改</a>
                    </li>

                    <li>
                        <a href="javascript:void(0);" class="btn_sc" onclick="del();return false;">删除</a>
                    </li>
                    <li>
                        <a href="javascript:void(0);" class="btn_shuc" onclick="submit();return false;" >提交</a>
                    </li>
                    <li>
                        <a href="javascript:void(0);" onclick="cancel();return false;" class="btn_sr">撤销</a>
                    </li>
                </logic:equal>
                <li><a href="javascript:void(0);" onclick="grxfjsshLcinfo();return false;"
                       title="选中一条记录，点击该按钮可以查看审核流程。"
                       class="btn_cs">流程跟踪</a>
                </li>
                <li>
                    <a href="#" class="btn_down" onclick="printWord();return false;">申报表下载</a>
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
        <span>申请列表&nbsp;&nbsp; </span>
    </h3>
    <div class="con_overlfow">
        <table id="dataTable"></table>
        <div id="pager"></div>
    </div>
</div>
</body>
</html>