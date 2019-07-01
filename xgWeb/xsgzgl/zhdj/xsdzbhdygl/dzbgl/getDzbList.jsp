<%@ page language="java" contentType="text/html; charset=GBK" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini" %>
    <script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
    <script type="text/javascript" src="js/calendar/calendar.js"></script>
    <script type="text/javascript" src="xsgzgl/zhdj/xsdzbhdygl/dzbgl/js/dzbgl.js"></script>
    <script type="text/javascript">
        jQuery(function () {
            var gridSetting = {
                caption: "",
                pager: "pager",
                url: "dzdy_dzbgl.do?method=getDzbList&type=query",
                colList: [
                    {label: 'key', name: 'dzbid', index: 'dzbid', hidden: true},
                    {label: 'key1', name: 'dzbhjid', index: 'dzbhjid', hidden: true},
                    {label: 'key2', name: 'jcdwdm', index: 'jcdwdm', hidden: true},
                    {label: 'key3', name: 'dzbsj', index: 'dzbsj', hidden: true},
                    {label: 'key4', name: 'zzwy', index: 'zzwy', hidden: true},
                    {label: 'key5', name: 'xcwy', index: 'xcwy', hidden: true},
                    {label: 'key6', name: 'jjwy', index: 'jjwy', hidden: true},
                    {label: '党支部名称', name: 'dzbmc', index: 'dzbmc', width: '18%', formatter: dzbLink},
                    {label: '党支部类型', name: 'dzblx', index: 'dzblx', width: '10%'},
                    {label: '基层党委', name: 'jcdwmc', index: 'jcdwmc',with:'18%'},
                    {label: '成立时间', name: 'clsj', index: 'clsj', width: '10%'},
                    {label: '换届时间', name: 'hjsj', index: 'hjsj', width: '10%'},


                ],
                sortname: "clsj",
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
<html:form action="/dzdy_dzbgl">
    <%@ include file="/comm/hiddenValue.jsp" %>
    <html:hidden property="js" styleId="js" value="${userType}"/>

    <div class="toolbox">
        <!-- 按钮 -->
        <div class="buttonbox">
            <ul>

                <li>
                    <a href="javascript:void(0);" class="btn_zj" onclick="addDzb();return false;">新增</a>
                </li>
                <li>
                    <a href="javascript:void(0);" onclick="updateDzb();return false;" class="btn_xg">修改</a>
                </li>
                <li>
                    <a href="javascript:void(0);" onclick="delDzb();return false;" class="btn_sc">删除</a>
                </li>
                <li>
                    <a href="javascript:void(0);" onclick="ljDzb();return false;" class="btn_ck">历届</a>
                </li>
                <li>
                    <a href="javascript:void(0);" onclick="hjDzb();return false;" class="btn_xg">换届</a>
                </li>
                <li>
                    <a href="javascript:void(0);" onclick="importConfig();return false;" class="btn_dr">导入</a>
                </li>
                <li><a href="javascript:void(0);" class="btn_dc" onclick="exportConfig();return false;">导出</a></li>


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
