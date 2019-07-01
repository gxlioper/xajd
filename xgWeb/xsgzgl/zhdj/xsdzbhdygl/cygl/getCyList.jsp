<%@ page language="java" contentType="text/html; charset=GBK" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini" %>
    <script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
    <script type="text/javascript" src="js/calendar/calendar.js"></script>
    <script type="text/javascript" src="xsgzgl/zhdj/xsdzbhdygl/cygl/js/cygl.js"></script>
    <script type="text/javascript">
        jQuery(function () {
            var gridSetting = {
                caption: "",
                pager: "pager",
                url: "dzdy_cygl.do?method=getCyList&type=query",
                colList: [
                    {label: '学号', name: 'xh', index: 'xh', width: '10%'},
                    {label: '姓名', name: 'xm', index: 'xm', width: '10%'},
                    {label: '性别', name: 'xb', index: 'xb', width: '10%'},
                    {label: '专业', name: 'zymc', index: 'zymc', width: '18%'},
                    {label: '班级', name: 'bjmc', index: 'bjmc', width: '10%'},
                    {label: '联系电话', name: 'lxdh', index: 'lxdh', width: '10%'},
                    {label: '政治面貌', name: 'zzmmmc', index: 'zzmmmc', width: '10%'},
                    {label: '党支部名称', name: 'dzbmc', index: 'dzbmc', width: '10%'},
                    {label: '状态', name: 'djzt', index: 'djzt', width: '10%'},
                    {label: '是否失联', name: 'sl', index: 'sl', width: '10%'},
                    {label: '是否流动', name: 'ld', index: 'ld', width: '10%'}
                ],
                sortname: "xh",
                sortorder: "desc",
                radioselect: true
            }
            var map = getSuperSearch();
            map["xydm"] = jQuery("#xydm").val();
            map["js"] = jQuery("#js").val();
            gridSetting["params"] = map;
            jQuery("#dataTable").initGrid(gridSetting);
        })
    </script>
</head>

<body>
<div class="tab_cur">
    <p class="location">
        <em>您的当前位置：</em><a>${title }</a>
    </p>
</div>
<html:form action="/dzdy_cygl">
    <%@ include file="/comm/hiddenValue.jsp" %>
    <html:hidden property="js" styleId="js" value="${userType}"/>

    <div class="toolbox">
        <!-- 按钮 -->
        <div class="buttonbox">
            <ul>

                <li>
                    <a href="javascript:void(0);" class="btn_zj" onclick="addCy();return false;">新增成员</a>
                </li>
                <li>
                    <a href="javascript:void(0);" onclick="updateCy();return false;" class="btn_xg">修改</a>
                </li>
                <li>
                    <a href="javascript:void(0);" onclick="delCy();return false;" class="btn_sc">删除</a>
                </li>
                <li><a href="javascript:void(0);" class="btn_xg" onclick="tb();return false;">同步异动信息</a></li>


            </ul>
        </div>
        <!-- 过滤条件 -->
        <%@ include file="/comm/search/superSearchArea.jsp" %>
        <!-- 过滤条件 end-->
    </div>
</html:form>

<div class="main_box">

    <div class="con_overlfow">
        <table id="dataTable"></table>
        <div id="pager"></div>
    </div>
</div>
</body>
</html>
