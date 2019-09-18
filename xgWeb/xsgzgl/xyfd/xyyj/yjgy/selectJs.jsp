<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini"%>
    <script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
    <script type="text/javascript" src="js/calendar/calendar.js"></script>
    <script type="text/javascript">
        var gridSetting;
        jQuery(function(){
            var zjmb = jQuery("#zjmb").val();
            var colList;
            if(zjmb=='xs'){
                colList = [
                    {label:'ѧ��',name:'jsh', index: 'jsh',width:'20%'},
                    {label:'����',name:'xm', index: 'xm',width:'10%'},
                    {label:'�󱲵ǼǺ�',name:'djh', index: 'djh',width:'10%'},
                    {label:'��ϵ�绰',name:'sjhm', index: 'sjhm',width:'10%'}
                ];
            }else{
                colList = [
                    {label:'ְ����',name:'jsh', index: 'jsh',width:'20%'},
                    {label:'����',name:'xm', index: 'xm',width:'10%'},
                    {label:'��ʦ�ǼǺ�',name:'djh', index: 'djh',width:'10%'},
                    {label:'��ϵ�绰',name:'lxdh', index: 'lxdh',width:'10%'}
                ];
            }
            gridSetting = {
                caption:"",
                pager:"pager",
                url:"xyfd_yjgy.do?method=selectJs&type=query&zjmb="+zjmb,
                colList:colList,
                sortname: "jsh",
                sortorder: "asc",
                radioselect:false
            }

            var map = getSuperSearch();
            gridSetting["params"] = map;
            jQuery("#dataTable").initGrid(gridSetting);
        });

        function searchRs(){
            var map = getSuperSearch();
            jQuery("#dataTable").reloadGrid(map);
        }
        function select(){
            var rows = jQuery("#dataTable").getSeletRow();
            if (rows.length != 1) {
                showAlertDivLayer("��ѡ��һ����Ҫת���Ԥ����");
                return false;
            }
            var jsh = rows[0]["jsh"];
            var xm = rows[0]["xm"];
            var api = frameElement.api;
            var parent = api.get('parentDialog');
            parent.jQuery("#jsh").val(jsh);
            parent.jQuery("#xm").val(xm);
            iFClose();
        }
    </script>
</head>

<body>
<html:form action="/xyfd_yjgy">
    <%@ include file="/comm/hiddenValue.jsp"%>
    <div class="toolbox">
        <!-- ��ť -->
        <input type="hidden" id="zjmb" name="zjmb" value="${model.zjmb}"/>
        <button class="btn_01" type="button" onclick="select()">ѡ��</button>
        <!-- �������� -->
        <%@ include file="/comm/search/superSearchArea.jsp"%>
        <!-- �������� end-->
    </div>
</html:form>
<div class="main_box">
    <h3 class=datetitle_01>
        <span>��ѯ���&nbsp;&nbsp; </span>
    </h3>
    <div class="con_overlfow">
        <table id="dataTable" ></table>
        <div id="pager"></div>
    </div>
</div>
</body>
</html>
