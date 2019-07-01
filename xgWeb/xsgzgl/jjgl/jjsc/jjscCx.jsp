<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini"%>
    <script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
    <script type="text/javascript" src="js/calendar/calendar.js"></script>
    <script type="text/javascript">
        jQuery(function(){
            var gridSetting = {
                caption : "${gnmkmc}列表",
                pager : "pager",
                url : "jjgl_jjsc.do?method=jjscCx&doType=query",
                colList : [
                    {label:'家教编号',name:'jjbh', index: 'jjbh',formatter : jjxqLink},
                    {label:'家教时长',name:'jjgs', index: 'jjgs'},
                    {label:'家教年月',name:'jjny', index: 'jjny'}
                ],
                sortname : "jjbh",
                sortorder : "desc" };
            var map = getSuperSearch();
            gridSetting["params"] = map;
            jQuery("#dataTable").initGrid(gridSetting);
        })

        function jjxqLink(v,r) {
            v = v||"";
            return "<a class='name' onclick='showDialog(\"家教信息\",700,500,\"jjgl_jjsc.do?method=jjscCk&jjbh="+r["jjbh"]+"\")'>"+v+"</a>";
        }

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

<html:form action="/jjgl_jjsc">
    <input type="hidden" id="gnmkmc" value="${gnmkmc}"/>
    <%@ include file="/comm/hiddenValue.jsp"%>

    <div class="toolbox">
        <!-- 过滤条件 -->
        <%@ include file="/comm/search/superSearchArea.jsp"%>
        <!-- 过滤条件 end-->
    </div>
</html:form>

<div class="main_box">
    <h3 class=datetitle_01>
        <span>${gnmkmc}列表&nbsp;&nbsp; </span>
    </h3>
    <div class="con_overlfow">
        <table id="dataTable" ></table>
        <div id="pager"></div>
    </div>
</div>
</body>
</html>
