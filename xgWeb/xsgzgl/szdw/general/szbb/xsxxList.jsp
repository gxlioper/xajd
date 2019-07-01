<%@ page language="java" contentType="text/html; charset=GBK" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini" %>
    <script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
    <script type="text/javascript" src="js/calendar/calendar.js"></script>
    <script type="text/javascript">
        jQuery(function () {
            var gridSetting = {
                caption: "",
                pager: "pager",
                url: "general_szdw.do?method=xsxxList&type=query&bbType="+jQuery("#bbType").val()+"&bjdm="+jQuery("#bjdm").val(),
                colList: [
                    {label: '学号', name: 'xh', index: 'xh', width: '10%'},
                    {label: '姓名', name: 'xm', index: 'xm', width: '10%'},
                    {label: '性别', name: 'xb', index: 'xb', width: '10%'},
                    {label: '书院', name: 'symc', index: 'symc', width: '10%'},
                    {label: '学院', name: 'xymc', index: 'xymc', width: '10%'},
                    {label: '专业', name: 'zymc', index: 'zymc', width: '10%'},
                    {label: '专业班级', name: 'zybjmc', index: 'zybjmc', width: '10%'},
                    {label: '行政班级', name: 'bjmc', index: 'bjmc', width: '10%'},
                    {label: '辅导员', name: 'fdyxm', index: 'fdy', width: '10%'},
                    {label: '班主任', name: 'bzrxm', index: 'bzr', width: '10%'},
                ],
                sortname: "xh",
                sortorder: "asc",
                multiselect:false,
                radioselect: true
            }
            var map = getSuperSearch();
            gridSetting["params"] = map;
            jQuery("#dataTable").initGrid(gridSetting);
        })

        function searchRs() {
            var map = getSuperSearch();
            jQuery("#dataTable").reloadGrid(map);
        }
    </script>
</head>

<body>
<html:form action="/ztbhgl_ztbhjg">
    <%@ include file="/comm/hiddenValue.jsp" %>
    <input type="hidden" value="${bbType}" id="bbType">
    <input type="hidden" value="${bjdm}" id="bjdm">
    <div class="toolbox">
        <!-- 按钮 -->
        <div class="buttonbox">

        </div>
        <!-- 过滤条件 -->
        <div style="display: none">
        <%@ include file="/comm/search/superSearchArea.jsp" %>
        </div>
        <!-- 过滤条件 end-->
    </div>
</html:form>

<div class="main_box">
    <h3 class=datetitle_01>
        <span>人员&nbsp;&nbsp;</span>
    </h3>
    <div class="con_overlfow">
        <table id="dataTable"></table>
        <div id="pager"></div>
    </div>
</div>
</body>
</html>
