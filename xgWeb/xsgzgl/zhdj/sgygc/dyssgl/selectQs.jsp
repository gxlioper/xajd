<%@ page language="java" contentType="text/html; charset=GBK" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini"%>
    <script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
    <script type="text/javascript" language="javascript" src="js/comm/commFunction.js"></script>
    <script type="text/javascript" src="xsgzgl/zhdj/sgygc/dyssgl/js/dyssglEdit.js"></script>
    <script type="text/javascript">

        jQuery(function () {
            var gridSetting = {
                caption: "寝室列表",
                pager: "pager",
                url: "zhdj_dyssgl.do?method=selectQs&type=query",
                colList: [
                    {label: 'lddm', name: 'lddm', index: 'lddm',hidden:true},
                    {label: '楼栋', name: 'ldmc', index: 'ldmc', width: '10%'},
                    {label: '寝室号', name: 'qsh', index: 'qsh', width: '10%'},
                    {label: '学院', name: 'xymc', index: 'xymc', width: '10%'},
                    {label: '年级', name: 'nj', index: 'nj', width: '10%'}
                ],
                sortname: "ldmc,qsh",
                sortorder: "asc"
            };
            jQuery("#dataTable").initGrid(gridSetting);
        });
        function searchRs() {
            var map = getSuperSearch();
            jQuery("#dataTable").reloadGrid(map);
        }
    </script>

</head>
<body>
<html:form action="/zhdj_dyssgl" method="post">
    <div style='width:100%; height:500px; overflow-y:auto;overflow-x:hidden'>
        <%@ include file="/comm/hiddenValue.jsp" %>
        <div class="tab_cur">
            <p class="location">
                <em>您的当前位置：</em><a>${title }</a>
            </p>
        </div>
        <div class="toolbox">
            <!-- 按钮 -->
            <!-- 过滤条件 -->
            <%@ include file="/comm/search/superSearchArea.jsp" %>
            <!-- 过滤条件 end-->
        </div>

        <div class="main_box">
            <h3 class=datetitle_01>
                <span>寝室列表&nbsp;&nbsp; </span>
            </h3>
            <div class="con_overlfow">
                <table id="dataTable"></table>
                <div id="pager"></div>
            </div>
        </div>


    </div>

</html:form>
<table width="100%" border="0" class="formlist">
    <tfoot>
    <tr>
        <td colspan="4">
            <div class="btn">
                <button id="buttonSave" style="margin-right: 50px;" onclick="saveQs();return false;">
                    选 择
                </button>
                <button onclick="Close();return false;">
                    关 闭
                </button>

            </div>
        </td>
    </tr>
    </tfoot>
</table>
</body>
</html>