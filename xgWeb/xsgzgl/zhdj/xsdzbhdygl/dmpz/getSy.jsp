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
        jQuery(function () {
            var ccid = jQuery("#ccid").val();
            var gridSetting = {
                caption: "",
                pager: "pager",
                url: "dzdy_dmpz.do?method=getSy&type=query",
                colList: [{
                    label: '��Ժ����',
                    name: 'sydm',
                    index: 'sydm',
                    width: '10%'
                }, {
                    label: '��Ժ����',
                    name: 'symc',
                    index: 'symc',
                    width: '20%',
                }
                ],
                sortname: "sydm",
                sortorder: "desc",
                radioselect: false

            }
            var map = getSuperSearch();
            map["sydm"] = jQuery("#sydm").val();
            map["js"] = jQuery("#js").val();
            gridSetting["params"] = map;
            jQuery("#dataTable").initGrid(gridSetting);
        })
    </script>
</head>

<body>
<html:form action="/dzdy_dmpz">
    <%@ include file="/comm/hiddenValue.jsp" %>
    <div class="toolbox">
        <!-- ��ť -->
        <div class="buttonbox">
            <ul>


                <li>
                    <a href="javascript:void(0);" class="btn_sz" onclick="choseSy();return false;">ѡ����Ժ</a>
                </li>


            </ul>
        </div>
        <!-- �������� -->
        <%@ include file="/comm/search/superSearchArea.jsp" %>
        <!-- �������� end-->
    </div>
</html:form>

<div class="main_box">
    <h3 class=datetitle_01>
        <span>��Ժ�б�&nbsp;&nbsp;</span>
    </h3>
    <div class="con_overlfow">
        <table id="dataTable"></table>
        <div id="pager"></div>
    </div>
</div>
</body>
</html>
