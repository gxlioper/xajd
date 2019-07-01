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
                url: "ztbhgl_bzrztbhjg.do?method=getXx&type=query",
                colList: [
                    {label: 'lxdh', name: 'lxdh', index: 'lxdh', hidden: true},
                    {label: '学号', name: 'xh', index: 'xh', hidden: true},
                    {label: '姓名', name: 'xm', index: 'mc', width: '10%'},
                    {label: '学院名称', name: 'xymc', index: 'xymc', width: '20%'},
                    {label: '专业', name: 'zymc', index: 'zymc', width: '10%'},
                    {label: '班级', name: 'bjmc', index: 'bjmc', width: '10%'},
                ],
                sortname: "xh",
                sortorder: "asc",
                radioselect: true

            }
            var map = getSuperSearch();
            map["xydm"] = jQuery("#xydm").val();
            map["js"] = jQuery("#js").val();
            gridSetting["params"] = map;
            jQuery("#dataTable").initGrid(gridSetting);
        })

        function choseXs() {
            var rows = jQuery("#dataTable").getSeletRow();
            if (rows.length == 0) {
                showAlertDivLayer("请选择一个学生！");
                return false;
            }
            var api = frameElement.api;
            var parentsW = api.get('parentDialog');
            parentsW.jQuery("#hdfzr").val(rows[0]['xh']);
            parentsW.jQuery("#hdfzrxm").val(rows[0]['xm']);
            parentsW.jQuery("#hdfzrlxdh").val(rows[0]['lxdh']);
            closeDialog();
        }

        function searchRs() {
            var map = getSuperSearch();
            jQuery("#dataTable").reloadGrid(map);
        }
    </script>
</head>

<body>
<html:form action="/ztbhgl_bzrztbhjg">
    <%@ include file="/comm/hiddenValue.jsp" %>
    <div class="toolbox">
        <!-- 按钮 -->
        <div class="buttonbox">
            <ul>


                <li>
                    <a href="javascript:void(0);" class="btn_sz" onclick="choseXs();return false;">选择人员</a>
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
