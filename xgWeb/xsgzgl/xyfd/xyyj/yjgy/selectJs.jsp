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
                    {label:'学号',name:'jsh', index: 'jsh',width:'20%'},
                    {label:'姓名',name:'xm', index: 'xm',width:'10%'},
                    {label:'朋辈登记号',name:'djh', index: 'djh',width:'10%'},
                    {label:'联系电话',name:'sjhm', index: 'sjhm',width:'10%'}
                ];
            }else{
                colList = [
                    {label:'职工号',name:'jsh', index: 'jsh',width:'20%'},
                    {label:'姓名',name:'xm', index: 'xm',width:'10%'},
                    {label:'教师登记号',name:'djh', index: 'djh',width:'10%'},
                    {label:'联系电话',name:'lxdh', index: 'lxdh',width:'10%'}
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
                showAlertDivLayer("请选择一条您要转介的预警！");
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
        <!-- 按钮 -->
        <input type="hidden" id="zjmb" name="zjmb" value="${model.zjmb}"/>
        <button class="btn_01" type="button" onclick="select()">选择</button>
        <!-- 过滤条件 -->
        <%@ include file="/comm/search/superSearchArea.jsp"%>
        <!-- 过滤条件 end-->
    </div>
</html:form>
<div class="main_box">
    <h3 class=datetitle_01>
        <span>查询结果&nbsp;&nbsp; </span>
    </h3>
    <div class="con_overlfow">
        <table id="dataTable" ></table>
        <div id="pager"></div>
    </div>
</div>
</body>
</html>
