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
                caption : "${gnmkmc}�б�",
                pager : "pager",
                url : "jjgl_jjsc.do?method=jjscCx&doType=query",
                colList : [
                    {label:'�ҽ̱��',name:'jjbh', index: 'jjbh',formatter : jjxqLink},
                    {label:'�ҽ�ʱ��',name:'jjgs', index: 'jjgs'},
                    {label:'�ҽ�����',name:'jjny', index: 'jjny'}
                ],
                sortname : "jjbh",
                sortorder : "desc" };
            var map = getSuperSearch();
            gridSetting["params"] = map;
            jQuery("#dataTable").initGrid(gridSetting);
        })

        function jjxqLink(v,r) {
            v = v||"";
            return "<a class='name' onclick='showDialog(\"�ҽ���Ϣ\",700,500,\"jjgl_jjsc.do?method=jjscCk&jjbh="+r["jjbh"]+"\")'>"+v+"</a>";
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
        <em>���ĵ�ǰλ�ã�</em><a>${title }</a>
    </p>
</div>

<html:form action="/jjgl_jjsc">
    <input type="hidden" id="gnmkmc" value="${gnmkmc}"/>
    <%@ include file="/comm/hiddenValue.jsp"%>

    <div class="toolbox">
        <!-- �������� -->
        <%@ include file="/comm/search/superSearchArea.jsp"%>
        <!-- �������� end-->
    </div>
</html:form>

<div class="main_box">
    <h3 class=datetitle_01>
        <span>${gnmkmc}�б�&nbsp;&nbsp; </span>
    </h3>
    <div class="con_overlfow">
        <table id="dataTable" ></table>
        <div id="pager"></div>
    </div>
</div>
</body>
</html>
