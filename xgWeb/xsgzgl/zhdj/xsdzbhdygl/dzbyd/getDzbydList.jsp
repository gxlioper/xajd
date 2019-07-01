<%@ page language="java" contentType="text/html; charset=GBK" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini" %>
    <script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
    <script type="text/javascript" src="js/calendar/calendar.js"></script>
    <script type="text/javascript" src="xsgzgl/zhdj/xsdzbhdygl/dzbyd/js/dzbyd.js"></script>
    <script type="text/javascript">
        jQuery(function () {
            var gridSetting = {
                caption: "",
                pager: "pager",
                url: "dzdy_dzbyd.do?method=getDzbydList&type=query",
                colList: [
                    {label: '学号', name: 'xh', index: 'xh', width:'18%',formatter: dzbydLink},
                    {label: '姓名', name: 'xm', index: 'xm', width: '18%'},
                    {label: '原党支部', name: 'dzbmc', index: 'dzbmc',width: '10%'},
                    {label: '异动党支部', name: 'yddzbmc', index: 'yddzbmc', width: '10%'},
                ],
                sortname: "xh",
                sortorder: "desc",
                radioselect: true
            }
            var map = getSuperSearch();
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
<html:form action="/dzdy_dzbyd">
    <%@ include file="/comm/hiddenValue.jsp" %>
    <div class="toolbox">
        <!-- 按钮 -->
        <div class="buttonbox">
            <ul>
                <li>
                    <a href="javascript:void(0);" onclick="updateDzbyd();return false;" class="btn_xg">修改</a>
                </li>
                <li>
                    <a href="javascript:void(0);" onclick="delDzbyd();return false;" class="btn_sc">删除</a>
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
