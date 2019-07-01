<%@ page language="java" contentType="text/html; charset=GBK" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini" %>
    <script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
    <script type="text/javascript" src="js/calendar/calendar.js"></script>
    <script type="text/javascript" src="xsgzgl/zhdj/xsdzbhdygl/dmpz/js/dmpz.js"></script>
    <script type="text/javascript">
        var gridSetting = {
            caption: "",
            pager: "pager",
            url: "dzdy_dmpz.do?method=getDmpzList&type=query",
            colList: [
                {label: '基层党委代码', name: 'dm', index: 'dm', width: '15%'},
                {label: '名称', name: 'mc', index: 'mc', width: '15%'},
                {label: '教工党支部数', name: 'jgdzbs', index: 'jgdzbs', width: '15%'},
                {label: '学生党支部数', name: 'xsdzbs', index: 'jgdzbs', width: '15%'},
                {label: '教工党员数', name: 'jgdrs', index: 'jgdrs', width: '15%'},
                {label: '学生党员数', name: 'xsdrs', index: 'xsdrs', width: '15%'}
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
<html:form action="/dzdy_dmpz">
    <%@ include file="/comm/hiddenValue.jsp" %>
    <input type="hidden" id="userType" value="${userType}"/>
    <div class="toolbox">
        <!-- 按钮 -->
        <div class="buttonbox">
            <ul>
                <li>
                    <a href="javascript:void(0);" class="btn_zj" onclick="addPage();return false;">增加</a>
                </li>
                <li>
                    <a href="javascript:void(0);" onclick="update();return false;"
                       class="btn_xg">修改</a>
                </li>
                <li>
                    <a href="javascript:void(0);" onclick="del();return false;"
                       class="btn_sc">删除</a>
                </li>
                <li><a href="javascript:void(0);" class="btn_dc" onclick="exportConfig();return false;">导出</a></li>
                <li>
                    <a href="javascript:void(0);" onclick="ljDzb();return false;" class="btn_ck">历届</a>
                </li>
                <li>
                    <a href="javascript:void(0);" onclick="hjDzz();return false;" class="btn_xg">换届</a>
                </li>


            </ul>
        </div>
    </div>
</html:form>

<div class="main_box">
    <h3 class=datetitle_01>
        <span>代码配置&nbsp;&nbsp; </span>
    </h3>
    <div class="con_overlfow">
        <table id="dataTable"></table>
        <div id="pager"></div>
    </div>
</div>
</body>
</html>
