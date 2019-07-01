<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini"%>
    <script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
    <script type="text/javascript" src="js/calendar/calendar.js"></script>
    <script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
    <script type="text/javascript">
        jQuery(function(){
            var gridSetting = {
                caption : "楼栋信息列表",
                pager : "pager",
                url : "gygl_fygl_cwxxgl10698.do?method=cwxxList&type=query",
                colList : [
                    {label:'pk',name:'pk',index :'pk',key:true,hidden:true,width:'10%'},
                    {label:'楼栋名称',name:'ldmc',index:'ldmc',width:'10%'},
                    {label:'寝室号',name:'qsh',index:'qsh',width:'10%'},
                    {label:'床位号',name:'cwh',index:'cwh',width:'5%'},
                    {label:'所属年级',name:'nj',index:'nj',width:'5%'},
                    {label:'xh',name:'xh',index:'xh',width:'5%',hidden:true},
                    {label:'现住学生',name:'xm',index:'xm',width:'5%'},
                    {label:'入住时间',name:'rzsj',index:'rzsj',width:'10%'},
                    {label:'备注',name:'bz',index:'bz',width:'10%'},
                    {label:'是否保留床位',name:'sfbl',index:'sfbl',width:'10%',formatter:function(val,row){
                        if(val == "1"){
                            return "是";
                        } else if(val == "0"){
                            return "否";
                        } else {
                            return val;
                        }
                    }}
                ],
                sortname: "lddm,qsh,cwh",
                sortorder: "asc"
            }
            var map = getSuperSearch();
            gridSetting["params"] = map;
            jQuery("#dataTable").initGrid(gridSetting);
        });
        function searchRs() {
            var map = getSuperSearch();
            jQuery("#dataTable").reloadGrid(map);
        }
        function plbl(){
            var row = jQuery("#dataTable").getSeletRow();
            var ids = jQuery("#dataTable").getSeletIds();
            if(row.length == 0){
                showAlert("请至少选定一条记录!");
                return false;
            }
            for(var i = 0;i<row.length;i++){
                if(row[i]["xm"] != null || row[i]["nj"] != null){
                    showAlert("请选择未分配且未入住的床位操作!");
                    return false;
                }
            }
            showDialog("批量保留床位",600,300,"gygl_fygl_cwxxgl10698.do?method=plbl&pks="+ids.toString());
        }
        function cwsscsh(){
            var url = "gygl_fygl_cwxxgl10698.do?method=cwsscsh";
            var rows = jQuery("#dataTable").getSeletRow();
            if(rows.length != 0){
                var ids = jQuery("#dataTable").getSeletIds();
                url= url+"&pks="+ids.toString();
            }
            showDialog("床位所属初始化",650,200,url);
        }

        function rz(){
            var rows = jQuery("#dataTable").getSeletRow();
            if(rows.length != 1){
                showAlert("请选定一条记录!");
                return false;
            }
            if(rows[0]["xm"] != null){
                showAlert("床位已入住，请先退宿!");
                return false;
            }
            var url = "gygl_fygl_cwxxgl10698.do?method=rz&pk="+rows[0]["pk"];
            showDialog("学生入住",650,500,url);
        }

        function ts(){
            var ids = jQuery("#dataTable").getSeletIds();
            var rows = jQuery("#dataTable").getSeletRow();
            if(ids.length == 0){
                showAlert("请选择一条记录！");
                return false;
            }
            var xhs = [];
            for(var i=0;i<rows.length;i++){
                if(rows[i]["xm"] == null){
                    showAlert("选择记录中包含未入住床位，请选择已入住床位！");
                    return false;
                }
                xhs.push(rows[i]["xh"]);
            }
            var url = "gygl_fygl_cwxxgl10698.do?method=ts&pks="+ids.toString() +"&xhs="+xhs.toString();
            showDialog("学生退宿",650,400,url);
        }
    </script>
</head>

<body>
<div class="tab_cur">
    <p class="location">
        <em>您的当前位置：</em><a>${title }</a>
    </p>
</div>
<html:form action="/gyglnew_wmqsxsmd_12688">
    <%@ include file="/comm/hiddenValue.jsp"%>
    <div class="toolbox">
        <!-- 按钮 -->
        <div class="buttonbox">
            <ul>
                <logic:equal name="writeAble" value="yes">
                    <li><a href="javascript:void(0);" onclick="plbl();" class="btn_xg">批量保留</a></li>
                    <li><a href="javascript:void(0);" onclick="rz()" class="btn_xg">入住</a></li>
                    <li><a href="javascript:void(0);" onclick="ts()" class="btn_xg">退宿</a></li>
                    <li><a href="javascript:void(0);" onclick="cwsscsh();" class="btn_xg">床位所属初始化</a></li>
                    <li><a href="javascript:void(0);" onclick="" class="btn_dr">导入入住信息</a></li>
                </logic:equal>
                <li><a href="#" class="btn_dc" onclick="exportConfig();return false;">导出</a></li>
            </ul>
        </div>
        <!-- 过滤条件 -->
        <%@ include file="/comm/search/superSearchArea.jsp"%>
        <!-- 过滤条件 end-->
    </div>
</html:form>

<div class="main_box">
    <h3 class=datetitle_01>
        <span>文明寝室学生名单</span>
    </h3>
    <div class="con_overlfow">
        <table id="dataTable" ></table>
        <div id="pager"></div>
    </div>
</div>
</body>
</html>
