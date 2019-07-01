<%@ page language="java" contentType="text/html; charset=GBK" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini" %>
    <script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
    <script type="text/javascript" src="xsgzgl/qgzx/xswh/js/xsWh.js"></script>
    <script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
    <script type="text/javascript">

        var gridSetting = {
            caption: "学生信息列表",
            pager: "pager",
            url: "qgzx_xsgl.do?method=xswh&type=query",
            colList: [
                {label: '学号', name: 'xh', index: 'xh', width: '10%', key: true, formatter: xhLink},
                {label: '姓名', name: 'xm', index: 'xm', width: '10%'},
                {label: '性别', name: 'xb', index: 'xb', width: '8%'},
                {label: '年级', name: 'nj', index: 'nj', width: '8%'},
                {label: '<bean:message key="lable.xb" />', name: 'xymc', index: 'xydm', width: '12%'},
                {label: '专业', name: 'zymc', index: 'zydm', width: '12%'},
                {label: '专业班级', name: 'zybjmc', index: 'zybjdm', width: '12%'},
                {label: '行政班级', name: 'bjmc', index: 'bjdm', width: '12%'},
                {label: '书院', name: 'symc', index: 'sydm', width: '12%'},
                {label: '是否购买保险', name: 'sfgmbx', index: 'sfgmbx', width: '4%'}

            ],
            sortname: "nj,xymc,zymc,bjmc,zybjmc,symc",
            sortorder: "desc"
        }

        jQuery(function () {

            gridSetting["params"] = getSuperSearch();
            jQuery("#dataTable").initGrid(gridSetting);
        });

        //查询
        function searchRs() {
            var map = getSuperSearch();
            jQuery("#dataTable").reloadGrid(map);
        }
    </script>
</head>

<body>
<div class="tab_cur">
    <p class="location">
        <em>您的当前位置：</em><a>${title }</a>
    </p>
</div>
<html:form action="/qgzx_xsgl">
    <%@ include file="/comm/hiddenValue.jsp" %>

    <div class="toolbox">
        <!-- 按钮 -->
        <logic:equal name="writeAble" value="yes">
            <div class="buttonbox">
                <ul>
                    <li><a href="javascript:void(0);" onclick="add();" class="btn_zj">增加</a></li>
                    <li><a href="javascript:void(0);" onclick="del();" class="btn_sc">删除</a></li>
                    <li><a href="javascript:void(0);" onclick="importConfig();" class="btn_dr">导入</a></li>
                    <li><a href="javascript:void(0);" onclick="dc();" class="btn_dc">导出</a></li>
                    <li><a href="javascript:void(0);" onclick="xg();" class="btn_xg">保险信息维护</a></li>
                </ul>
            </div>
        </logic:equal>
        <!-- 过滤条件 -->
        <%@ include file="/comm/search/superSearchArea.jsp" %>
        <!-- 过滤条件 end-->
    </div>
</html:form>

<div class="main_box">
    <h3 class=datetitle_01>
        <span>勤工学生列表</span>
    </h3>
    <div class="con_overlfow">
        <table id="dataTable"></table>
        <div id="pager"></div>
    </div>
</div>
</body>
</html>
