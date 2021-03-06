<%@ page language="java" contentType="text/html; charset=GBK" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini"%>
    <script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
    <script type="text/javascript" language="javascript" src="js/comm/commFunction.js"></script>
    <script type="text/javascript">
        jQuery(function(){
            gridSetting = {
                caption: "党支部列表",
                pager: "pager",
                url: "xszbhd_hdfb.do?method=showDzb&type=query&hdid="+jQuery("#hdid").val(),
                colList: [
                    {label: 'dzbid', name: 'dzbid', index: 'dzbid',hidden:true,key:true},
                    {label: '党支部名称', name: 'dzbmc', index: 'dzbmc', width: '10%'},
                    {label: '所属基层党委名称', name: 'jcdwmc', index: 'jcdwdm', width: '10%'},
                    {label: '成立时间', name: 'clsj', index: 'clsj', width: '4%'},
                    {label: '所属学院', name: 'xymc', index: 'xymc', width: '10%'}
                ],
                multiselect:false,
                sortname: "clsj",
                sortorder: "asc"
            };
            jQuery("#dataTable").initGrid(gridSetting);
        });

    </script>

</head>
<body>
<html:form action="/xszbhd_hdfb" method="post">
    <div style='width:100%; height:450px; overflow-y:auto;overflow-x:hidden'>
        <%@ include file="/comm/hiddenValue.jsp" %>
        <html:hidden property="hdid" styleId="hdid"  value="${hdid}"/>
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
                <span>党支部列表&nbsp;&nbsp; </span>
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
            <div class="bz">
            </div>
            <div class="btn">
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