<%@ page language="java" contentType="text/html; charset=GBK" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini" %>
    <script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
    <script type="text/javascript" src="js/calendar/calendar.js"></script>
    <script type="text/javascript" src="xsgzgl/zhdj/xsdzbhdygl/cygl/js/jgdcygl.js"></script>
    <script type="text/javascript">
        jQuery(function () {
            var gridSetting = {
                caption: "",
                pager: "pager",
                url: "dzdy_jgdcygl.do?method=getJg&type=query",
                colList: [
                    {label: '学号', name: 'xh', index: 'xh', hidden: true},
                    {label: 'key1', name: 'symc', index: 'symc', hidden: true},
                    {label: 'key2', name: 'jtzz', index: 'jtzz', hidden: true},
                    {label: 'key3', name: 'lxdh', index: 'lxdh', hidden: true},
                    {label: 'key4', name: 'dzyx', index: 'dzyx', hidden: true},
                    {label: 'key5', name: 'jg', index: 'jg', hidden: true},
                    {label: '姓名', name: 'xm', index: 'mc', width: '10%'},
                    {label: '性别', name: 'xb', index: 'xb', width: '10%'},
                    {label: '部门名称', name: 'bmmc', index: 'bmmc', width: '20%'},
                ],
                sortname: "xh",
                sortorder: "asc",
                radioselect: true

            }
            var map = getSuperSearch();
            map["zgh"] = jQuery("#zgh").val();
            map["js"] = jQuery("#js").val();
            gridSetting["params"] = map;
            jQuery("#dataTable").initGrid(gridSetting);
        })
    </script>
</head>

<body>
<html:form action="/dzdy_dzbgl">
    <%@ include file="/comm/hiddenValue.jsp" %>
    <div class="toolbox">
        <!-- 按钮 -->
        <div class="buttonbox">
            <ul>


                <li>
                    <a href="javascript:void(0);" class="btn_sz" onclick="choseJg();return false;">选择人员</a>
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
        <span>人员&nbsp;&nbsp;</span>
    </h3>
    <div class="con_overlfow">
        <table id="dataTable"></table>
        <div id="pager"></div>
    </div>
</div>
</body>
</html>
