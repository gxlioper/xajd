<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini"%>
    <script type='text/javascript' src="js/xgutil.js"></script>
    <script type='text/javascript' src='dwr/engine.js'></script>
    <script type='text/javascript' src='dwr/util.js'></script>
    <script type='text/javascript' src='dwr/interface/exportData.js'></script>
    <script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
    <script type="text/javascript" src="js/calendar/calendar.js"></script>
    <script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
    <script type="text/javascript">
        jQuery(function(){
            var gridSetting = {
                caption:"",
                pager:"pager",
                url:"szdw_fdy_ywxxypxjg.do?method=ywxxypxJgList&type=query",
                colList:[
                    {label:'jgid',name:'jgid', index: 'jgid',width:'10%',key:true,hidden:true},
                    {label:'职工号',name:'zgh', index: 'zgh',width:'10%',formatter:function(cell,row){
                        return "<a href='javascript:void(0);' onclick='view(\""+row["jgid"]+"\")'>"+cell+"</a>";
                    }},
                    {label:'姓名',name:'xm', index: 'xm',width:'10%'},
                    {label:'性别',name:'xb', index: 'xb',width:'10%'},
                    {label:'所在书院',name:'symc', index: 'symc',width:'10%'},
                    {label:'所在学院',name:'bmmc', index: 'bmmc',width:'10%'},
                    {label:'培训名称',name:'xmmc', index: 'xmmc',width:'10%'},
                    {label:'培训时间',name:'pxsj', index: 'pxsj',width:'10%'},
                    {label:'组织部门',name:'zzbmmc', index: 'zzbmmc',width:'10%'},
                    {label:'学时',name:'pxxs', index: 'pxxs',width:'10%'},
                    {label:'sjly',name:'sjly', index: 'sjly',width:'10%',hidden:true}
                ],
                sortname: "zgh",
                sortorder: "asc"
            }
            jQuery("#dataTable").initGrid(gridSetting);
        });
        function searchRs(){
            var map = getSuperSearch();
            jQuery("#dataTable").reloadGrid(map);
        }
        function add(){
            showDialog("增加培训经历",700,550,"szdw_fdy_ywxxypxjg.do?method=add");
        }
        function update(){
            var rows = jQuery("#dataTable").getSeletRow();
            if(rows.length != 1){
                showAlertDivLayer("请选择一条您要修改的记录！");
                return false;
            }
            if(rows[0]["sjly"] == '1'){
                showAlertDivLayer("审核流来源数据不能修改！");
                return;
            }
            showDialog("修改培训经历",700,550,"szdw_fdy_ywxxypxjg.do?method=update&jgid="+rows[0]["jgid"]);
        }
        function view(val){
            showDialog("培训经历查看",700,550,"szdw_fdy_ywxxypxjg.do?method=view&jgid="+val);
        }
        function del() {
            var ids = jQuery("#dataTable").getSeletIds();
            if(ids == 0){
                showAlertDivLayer("请选择一条您要删除的记录！");
                return false;
            }
            var rows = jQuery("#dataTable").getSeletRow();
            for(var i=0;i<rows.length;i++){
                if(rows[i]["sjly"] == '1'){
                    showAlertDivLayer("审核中数据不能删除，请确认！");
                    return false;
                }
            }
            confirmInfo("您确定要删除"+ids.length +"条记录吗?",function(ty){
                if(ty=="ok"){
                    jQuery.post("szdw_fdy_ywxxypxjg.do?method=del",{values:ids.toString()},function(data){
                        alertInfo(data["message"]);
                        jQuery("#dataTable").reloadGrid();
                    },'json');
                }
            });
        }
        //导入
        function importConfig(){
            toImportDataNew("IMPORT_YWXXYPXJG");
            return false;
        }

        //dcglbh,导出功能编号
        var DCGLBH = "szdw_fdy_ywxxypxjg.do";

        //自定义导出 功能
        function exportConfig() {
            //DCCLBH导出功能编号,执行导出函数
            customExport(DCGLBH, exprotData);
        }

        //导出方法
        function exprotData() {
            setSearchTj();//设置高级查询条件
            var url = "szdw_fdy_ywxxypxjg.do?method=exportData&dcglbh=" + DCGLBH;//dcglbh,导出功能编号
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
<html:form action="/szdw_jfxx.do">
    <%@ include file="/comm/hiddenValue.jsp"%>
    <div class="toolbox">
            <%--<logic:equal value="yes" name="writeAble">--%>
        <div class="buttonbox">
            <ul>
                <li><a href="javascript:void(0);" id="btn_zj" class="btn_zj" onclick="add();return false;">增加</a></li>
                <li><a href="javascript:void(0);" id="btn_xg" class="btn_xg" onclick="update();return false;">修改</a></li>
                <li><a href="javascript:void(0);" id="btn_sc" class="btn_sc" onclick="del();return false;">删除</a></li>
                <li><a href="javascript:void(0);" onclick="importConfig();return false;" class="btn_dr">导入</a></li>
                <li><a href="javascript:void(0);" onclick="exportConfig();return false;" class="btn_dc">导出</a></li>
            </ul>
        </div>
            <%--</logic:equal>--%>
        <!-- 过滤条件 -->
        <%@ include file="/comm/search/superSearchArea.jsp"%>
        <!-- 过滤条件 end-->
    </div>
</html:form>
<div class="formbox">
    <!--标题start-->
    <h3 class="datetitle_01">
        <span></span>
    </h3>

    <table id="dataTable" ></table>
    <div id="pager"></div>

</div>
</body>
</html>
