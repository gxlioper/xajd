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
                url: "szdw_xsgb_dwwh.do?method=getXsxxList&type=query&bjdm="+jQuery("#bjdm").val(),
                colList: [
                    {label: 'ѧ��', name: 'xh', index: 'xh', width: '10%'},
                    {label: '����', name: 'xm', index: 'xm', width: '10%'},
                    {label: '�Ա�', name: 'xb', index: 'xb', width: '10%'},
                    {label: '��Ժ', name: 'symc', index: 'symc', width: '10%'},
                    {label: '�����༶', name: 'bjmc', index: 'bjmc', width: '10%'},
                    {label: '��ϵ�绰', name: 'lxdh', index: 'lxdh', width: '10%'},
                    {label: '����', name: '', index: '', width: '10%',formatter:function(cell,row){
                        var a = JSON.stringify(row);
                        return '<button type=\'button\' onclick=\'select('+a+');\'>ѡ��</button>';
                    }}
                ],
                sortname: "xh",
                sortorder: "asc",
                multiselect:false,
                radioselect: true
            }
            var map = getSuperSearch();
            gridSetting["params"] = map;
            jQuery("#dataTable").initGrid(gridSetting);
        })

        function searchRs() {
            var map = getSuperSearch();
            jQuery("#dataTable").reloadGrid(map);
        }
        function select(row){
            var api = frameElement.api;
            var parentsW = api.get('parentDialog');
            parentsW.setXsxxCallBack(row);
            closeDialog();
        }
    </script>
</head>

<body>
<html:form action="/ztbhgl_ztbhjg">
    <%@ include file="/comm/hiddenValue.jsp" %>
    <input type="hidden" value="${id}" id="inputId"/>
    <input type="hidden" value="${bjdm}" id="bjdm"/>
    <div class="toolbox">
        <!-- ��ť -->
        <div class="buttonbox">

        </div>
        <!-- �������� -->
        <%@ include file="/comm/search/superSearchArea.jsp" %>
        <!-- �������� end-->
    </div>
</html:form>

<div class="main_box">
    <h3 class=datetitle_01>
        <span>��Ա&nbsp;&nbsp;</span>
    </h3>
    <div class="con_overlfow">
        <table id="dataTable"></table>
        <div id="pager"></div>
    </div>
</div>
</body>
</html>
