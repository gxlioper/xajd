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
                caption : "资产信息列表",
                pager : "pager",
                url : "gygl_zcgl_zcxxgl.do?method=zcxxList&type=query",
                colList : [
                    {label:'id',name:'id',index :'id',key:true,hidden:true,width:'10%'},
                    {label:'名称',name:'mc',index :'mc',width:'10%'},
                    {label:'类型名称',name:'lxmc',index:'lxmc',width:'10%'},
                    {label:'厂家',name:'cjmc',index:'cjmc',width:'10%'},
                    {label:'资产参数(米)',name:'zccs',index:'zccs',width:'10%'},
                    {label:'更新年月',name:'gxny',index:'gxny',width:'5%'},
                    {label:'价格(元)',name:'jg',index:'jg',width:'10%'}
                ],
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

        //导出方法
        function exprotData() {
            setSearchTj();//设置高级查询条件
            var url = "gygl_sfqskh_jgwh.do?method=exportData";//dcglbh,导出功能编号
            url = addSuperSearchParams(url);//设置高级查询参数
            jQuery("form").eq(0).attr("action", url);
            jQuery("form").eq(0).submit();
        }
        function add() {
            showDialog("增加", 700, 300, "gygl_zcgl_zcxxgl.do?method=add");
        }
        function update() {
            var ids = jQuery("#dataTable").getSeletIds();
            if(ids.length != 1){
                showAlert("请选择一条记录！");
                return false;
            }
            showDialog("修改", 700, 300, "gygl_zcgl_zcxxgl.do?method=update&id="+ids[0]);
        }
        function view() {
            var ids = jQuery("#dataTable").getSeletIds();
            if(ids.length != 1){
                showAlert("请选择一条记录！");
                return false;
            }
            showDialog("查看", 700, 300, "gygl_zcgl_zcxxgl.do?method=view&id="+ids[0]);
        }
        function del(){
            var ids = jQuery("#dataTable").getSeletIds();
            if(ids.length == 0){
                showAlert("请至少选择一条记录！");
                return false;
            }
            var url = "gygl_zcgl_zcxxgl.do?method=del&ids="+ids.toString();
            showConfirmDivLayer("您确定要删除选择的记录吗？", {
                "okFun" : function() {
                    jQuery.post(url,{},function(data){
                        showAlertDivLayer(data["message"]);
                        jQuery("#dataTable").reloadGrid();
                    },'json');
                }
            });

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
                <li><a href="javascript:void(0);" onclick="add();return false;" class="btn_zj">增加</a></li>
                <li><a href="javascript:void(0);" onclick="update();return false;" class="btn_xg">修改</a></li>
                <li><a href="javascript:void(0);" onclick="view();return false;" class="btn_ck">查看</a></li>
                <li><a href="javascript:void(0);" onclick="del();return false;" class="btn_sc">删除</a></li>
            </ul>
        </div>
        <!-- 过滤条件 -->
        <%@ include file="/comm/search/superSearchArea.jsp"%>
        <!-- 过滤条件 end-->
    </div>
</html:form>

<div class="main_box">
    <h3 class=datetitle_01>
        <span></span>
    </h3>
    <div class="con_overlfow">
        <table id="dataTable" ></table>
        <div id="pager"></div>
    </div>
</div>
</body>
</html>
