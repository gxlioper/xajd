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
                url : "gygl_fygl_qsxxgl10698.do?method=qsxxList&type=query",
                colList : [
                    {label:'楼栋代码',name:'lddm',index :'lddm',hidden:true,width:'10%'},
                    {label:'楼栋名称',name:'ldmc',index:'ldmc',width:'10%'},
                    {label:'寝室号',name:'qsh',index:'qsh',width:'10%'},
                    {label:'层号',name:'ch',index:'ch',width:'5%'},
                    {label:'房间类型',name:'fjlx',index:'fjlx',width:'5%',formatter:function(val,row){
                        if(val == "01"){
                            return "宿舍";
                        } else if(val == "02"){
                            return "值班室";
                        } else if(val == "03"){
                            return "厕所";
                        } else {
                            return val;
                        }
                    }},
                    {label:'房间走向',name:'fjzx',index:'fjzx',width:'5%',formatter:function(val,row){
                        if(val == "1"){
                            return "东";
                        } else if(val == "2"){
                            return "南";
                        } else if(val == "3"){
                            return "西";
                        } else if(val == "4"){
                            return "北";
                        } else {
                            return val;
                        }
                    }},
                    {label:'寝室性别',name:'qsxb',index:'qsxb',width:'5%',formatter:function(val,row){
                        if(val == "1"){
                            return "男";
                        } else if(val == "2"){
                            return "女";
                        } else {
                            return "混住";
                        }
                    }},
                    {label:'是否含0层',name:'sfhlc',index:'sfhlc',width:'7%',formatter:function(val,row){
                        if(val == "1"){
                            return "是";
                        } else {
                            return "否";
                        }
                    }},
                    {label:'床位数',name:'cwss',index:'cwss',width:'5%'},
                    {label:'收费标准',name:'sfbz',index:'sfbz',width:'5%'},
                    {label:'寝室电话',name:'qsdh',index:'qsdh',width:'5%'},
                    {label:'是否有空调',name:'sfykt',index:'sfykt',width:'5%',formatter:function(val,row){
                        if(val == "1"){
                            return "是";
                        } else {
                            return "否";
                        }
                    }},
                    {label:'是否有卫生间',name:'sfywsj',index:'sfywsj',width:'5%',formatter:function(val,row){
                        if(val == "1"){
                            return "是";
                        } else {
                            return "否";
                        }
                    }},
                    {label:'卫生间位置',name:'wsjwz',index:'wsjwz',width:'5%',formatter:function(val,row){
                        if(val == "01"){
                            return "室内";
                        } else if(val == "02"){
                            return "阳台";
                        } else {
                            return val;
                        }
                    }},
                    {label:'卫生间走向',name:'wsjzx',index:'wsjzx',width:'5%',formatter:function(val,row){
                        if(val == "1"){
                            return "西南角";
                        } else if(val == "2"){
                            return "东南角";
                        } else if(val == "3"){
                            return "西北角";
                        } else if(val == "4"){
                            return "东北角";
                        } else {
                            return val;
                        }
                    }}
                ],
                sortname: "qsh",
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
        function add(){
            showDialog("增加楼栋",650,450,"gygl_fygl_qsxxgl10698.do?method=add");
        }
        function update(){
            var rows = jQuery("#dataTable").getSeletRow();
            if(rows.length != 1){
                alertInfo("请选择一条您要修改的记录！");
                return false;
            }
            showDialog("修改楼栋",650,450,"gygl_fygl_qsxxgl10698.do?method=update&lddm="+rows[0]["lddm"]+"&qsh="+rows[0]["qsh"]);
        }
        function del(){
            var rows = jQuery("#dataTable").getSeletRow();
            if(rows.length == 0){
                alertInfo("请选择您要删除的记录！");
                return false;
            }
            var a = [];
            for(var i=0;i<rows.length;i++){
                var pk = rows[i]["lddm"]+"@!!!"+rows[i]["qsh"];
                a.push(pk);
            }
            showConfirmDivLayer("您确定要删除选择的记录吗？",{"okFun":function(){
                jQuery.post("gygl_fygl_qsxxgl10698.do?method=del",{values:a.toString()},function(data){
                    showAlert(data["message"]);
                    jQuery("#dataTable").reloadGrid();
                },'json');
            }});
        }
        function plxgqs(){
            var rows = jQuery("#dataTable").getSeletRow();
            if(rows.length == 0){
                alertInfo("请至少选择一条记录！！");
                return false;
            }
            var a = [];
            for(var i=0;i<rows.length;i++){
                var pk = rows[i]["lddm"]+"@!!!"+rows[i]["qsh"];
                a.push(pk);
            }
            showDialog("批量修改寝室",650,300,"gygl_fygl_qsxxgl10698.do?method=plxgqs&pks="+a.toString());
        }

        function xgcw(){
            var rows = jQuery("#dataTable").getSeletRow();
            if(rows.length != 1){
                alertInfo("请选择一条记录！！");
                return false;
            }
            var pk = rows[0]["lddm"]+"@!!!"+rows[0]["qsh"];
            showDialog("批量修改寝室",650,300,"gygl_fygl_qsxxgl10698.do?method=xgcw&pk="+pk);
        }
        function qssscsh(){
            var url = "gygl_fygl_qsxxgl10698.do?method=qssscsh";
            var rows = jQuery("#dataTable").getSeletRow();
            if(rows.length != 0){
                var a = [];
                for(var i=0;i<rows.length;i++){
                    var pk = rows[i]["lddm"]+"@!!!"+rows[i]["qsh"];
                    a.push(pk);
                }
                url= url+"&pks="+a.toString();
            }
            showDialog("寝室所属初始化",650,200,url);
        }
        var DCCLBH = "gygl_fygl_qsxxgl10698.do";
        //自定义导出 功能
        function exportConfig() {
            //DCCLBH导出功能编号,执行导出函数
            customExport(DCCLBH, exportData);
        }

        //导出方法
        function exportData() {
            setSearchTj();//设置高级查询条件
            var url = "gygl_fygl_qsxxgl10698.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
            url = addSuperSearchParams(url);//设置高级查询参数
            jQuery("form").eq(0).attr("action", url);
            jQuery("form").eq(0).submit();
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
                    <li><a href="javascript:void(0);" onclick="add();" class="btn_zj">增加</a></li>
                    <li><a href="javascript:void(0);" onclick="update();" class="btn_xg">修改</a></li>
                    <li><a href="javascript:void(0);" onclick="del();" class="btn_sc">删除</a></li>
                    <li><a href="javascript:void(0);" onclick="plxgqs();" class="btn_xg">批量修改寝室</a></li>
                    <li><a href="javascript:void(0);" onclick="xgcw();" class="btn_xg">修改床位</a></li>
                    <li><a href="javascript:void(0);" onclick="qssscsh();" class="btn_xg">寝室所属初始化</a></li>
                    <li><a href="javascript:void(0);" onclick="" class="btn_dr">导入寝室</a></li>
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
