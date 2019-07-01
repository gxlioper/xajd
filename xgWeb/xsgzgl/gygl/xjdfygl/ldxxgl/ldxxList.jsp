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
                url : "gygl_fygl_ldxxgl10698.do?method=ldxxList&type=query",
                colList : [
                    {label:'楼栋代码',name:'lddm',index :'lddm',key:true,width:'10%'},
                    {label:'楼栋名称',name:'ldmc',index:'ldmc',width:'10%'},
                    {label:'楼栋简称',name:'ldjc',index:'ldjc',width:'10%'},
                    {label:'楼栋性别',name:'ldxb',index:'ldxb',width:'5%',formatter:function(val,row){
                        if(val == "1"){
                            return "男";
                        } else if(val == "2"){
                            return "女";
                        } else {
                            return "混住";
                        }
                    }},
                    {label:'楼栋走向',name:'ldzx',index:'ldzx',width:'5%',formatter:function(val,row){
                        if(val == "1"){
                            return "东";
                        } else if(val == "2"){
                            return "南";
                        } else if(val == "3"){
                            return "西";
                        } else {
                            return "北";
                        }
                    }},
                    {label:'楼栋层数',name:'ldcs',index:'ldcs',width:'5%'},
                    {label:'起始层号',name:'qsch',index:'qsch',width:'15%'},
                    {label:'是否含0层',name:'sfhlc',index:'sfhlc',width:'7%',formatter:function(val,row){
                        if(val == "1"){
                            return "是";
                        } else {
                            return "否";
                        }
                    }},
                    {label:'学生类型',name:'pyccmc',index:'pyccmc',width:'7%'}
                ],
                sortname: "lddm",
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
            showDialog("增加楼栋",600,400,"gygl_fygl_ldxxgl10698.do?method=add");
        }
        function update(){
            var rows = jQuery("#dataTable").getSeletRow();
            if(rows.length != 1){
                alertInfo("请选择一条您要修改的记录！");
                return false;
            }
            showDialog("修改楼栋",600,400,"gygl_fygl_ldxxgl10698.do?method=update&lddm="+rows[0]["lddm"]);
        }
        function del(){
            var ids = jQuery("#dataTable").getSeletIds();
            if(ids.length == 0){
                alertInfo("请选择您要删除的记录！");
                return false;
            }
            showConfirmDivLayer("您确定要删除选择的记录吗？",{"okFun":function(){
                jQuery.post("gygl_fygl_ldxxgl10698.do?method=del",{values:ids.toString()},function(data){
                    showAlert(data["message"]);
                    jQuery("#dataTable").reloadGrid();
                },'json');
            }});
        }
        function qssc(){
            var ids = jQuery("#dataTable").getSeletIds();
            if(ids.length != 1){
                alertInfo("请选择一条记录！");
                return false;
            }
            showDialog("修改楼栋",600,550,"gygl_fygl_ldxxgl10698.do?method=qssc&lddm="+ids.toString());
        }
        var DCCLBH = "gygl_fygl_ldxxgl10698.do";
        //自定义导出 功能
        function exportConfig() {
            //DCCLBH导出功能编号,执行导出函数
            customExport(DCCLBH, exportData);
        }

        //导出方法
        function exportData() {
            setSearchTj();//设置高级查询条件
            var url = "gygl_fygl_ldxxgl10698.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
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
                    <li><a href="javascript:void(0);" onclick="qssc();" class="btn_xg">寝室生成</a></li>
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
