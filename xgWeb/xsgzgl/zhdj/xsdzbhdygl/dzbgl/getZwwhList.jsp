<%@ page language="java" contentType="text/html; charset=GBK" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini" %>
    <script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
    <script type="text/javascript" src="js/calendar/calendar.js"></script>
    <script type="text/javascript" src="xsgzgl/zhdj/xsdzbhdygl/dzbgl/js/zwwh.js"></script>
    <script type="text/javascript">
        var gridSetting = {
            caption: "",
            pager: "pager",
            url: "dzdy_zwwh.do?method=getZwwhList&type=query",
            colList: [
                {label: 'ְ�����', name: 'dm', index: 'dm', width: '25%'},
                {label: 'ְ������', name: 'mc', index: 'mc', width: '25%'},
                {label: 'ְ������', name: 'zwss', index: 'zwss', width: '30%'},
                {label: 'ְ������', name: 'zwlx', index: 'zwlx', width: '30%'},
            ],
            sortname: "dm",
            sortorder: "asc",
            radioselect: false
        }


        jQuery(function () {
            var map = {};
            map["js"] = jQuery("#userType").val();
            gridSetting["params"] = map;
            jQuery("#dataTable").initGrid(gridSetting);
        });
    </script>
</head>

<body>
<html:form action="/dzdy_zwwh">
    <%@ include file="/comm/hiddenValue.jsp" %>
    <input type="hidden" id="userType" value="${userType}"/>
    <div class="toolbox">
        <!-- ��ť -->
        <div class="buttonbox">
            <ul>
                <li>
                    <a href="javascript:void(0);" class="btn_zj" onclick="addPage();return false;">����</a>
                </li>
                <li>
                    <a href="javascript:void(0);" onclick="update();return false;"
                       class="btn_xg">�޸�</a>
                </li>
                <li>
                    <a href="javascript:void(0);" onclick="delZw();return false;"
                       class="btn_sc">ɾ��</a>
                </li>

            </ul>
        </div>
    </div>
</html:form>

<div class="main_box">
    <h3 class=datetitle_01>
        <span>ְ��ά��&nbsp;&nbsp; </span>
    </h3>
    <div class="con_overlfow">
        <table id="dataTable"></table>
        <div id="pager"></div>
    </div>
</div>
</body>
</html>
