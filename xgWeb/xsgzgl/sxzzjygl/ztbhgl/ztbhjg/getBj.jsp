<%@ page language="java" contentType="text/html; charset=GBK" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini" %>
    <script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
    <script type="text/javascript" src="js/calendar/calendar.js"></script>
    <script type="text/javascript">
        jQuery(function () {
            var gridSetting = {
                caption: "",
                pager: "pager",
                url: "ztbhgl_ztbhjg.do?method=getBj&type=query",
                colList: [
                    {label: 'bjdm', name: 'bjdm', index: 'bjdm', hidden: true},
                    {label: '年级', name: 'nj', index: 'nj', width: '10%'},
                    {label: '班级', name: 'bjmc', index: 'bjmc', width: '10%'},
                    {label: '所属书院', name: 'symc', index: 'symc', width: '10%'},
                    {label: '人数', name: 'bjrs', index: 'bjrs', width: '10%'},
                    {label: '辅导员', name: 'fdyxm', index: 'fdyxm', width: '10%'},
                    {label: '联系电话', name: 'lxdh', index: 'lxdh', width: '10%'}
                ],
                sortname: "symc",
                sortorder: "asc",
                radioselect: true

            }
            var map = getSuperSearch();
//            map["js"] = jQuery("#js").val();
            gridSetting["params"] = map;
            jQuery("#dataTable").initGrid(gridSetting);
        })

        function choseBj() {
            var rows = jQuery("#dataTable").getSeletRow();
            if (rows.length == 0) {
                showAlertDivLayer("请选择一个班级！");
                return false;
            }
            var api = frameElement.api;
            var parentsW = api.get('parentDialog');
            parentsW.setBjxxCallBack(rows);

            closeDialog();
        }
        function searchRs() {
            var map = getSuperSearch();
            jQuery("#dataTable").reloadGrid(map);
        }
    </script>
</head>

<body>
<html:form action="/ztbhgl_ztbhjg">
    <%@ include file="/comm/hiddenValue.jsp" %>
    <div class="toolbox">
        <!-- 按钮 -->
        <div class="buttonbox">
            <ul>


                <li>
                    <a href="javascript:void(0);" class="btn_sz" onclick="choseBj();return false;">选择班级</a>
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
