<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html>
<head>
    <title>绿色通道结果</title>
    <%@ include file="/syscommon/head.ini"%>
    <script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
    <script type="text/javascript" src="js/check.js"></script>
    <script type="text/javascript" src="xsgzgl/xszz/lstd/js/lstd.js"></script>
    <script type="text/javascript">
        function initGrid(){
            var gridSetting = {
                caption:"绿色通道结果列表",
                pager:"pager",
                url:"xszz_lstd.do?method=lstdjgList&type=query",
                colList:[
                    {label:'key',name:'jgid', index: 'jgid',key:true ,hidden:true},
                    {label:'学号',name:'xh', index: 'xh',width:'13%'},
                    {label:'学年',name:'xn', index: 'xn',width:'8%'},
                    {label:'姓名',name:'xm', index: 'xm',width:'8%'},
                    {label:'性别',name:'xb', index: 'xb',width:'5%'},
                    {label:'学院',name:'xymc', index: 'xydm',width:'13%'},
                    {label:'书院',name:'symc', index: 'symc',width:'13%'},
                    {label:'行政班级',name:'bjmc', index: 'bjdm',width:'13%'},
                    {label:'专业班级',name:'zybjmc', index: 'zybjmc',width:'13%'},
                    {label:'数据来源',name:'sjly', index: 'sjly',width:'13%',hidden:true}
                ],
                sortname: "xh",
                sortorder: "desc"
            };
            gridSetting["params"]=getSuperSearch();
            jQuery("#dataTable").initGrid(gridSetting);
        };
        function searchRs(){
            var map = getSuperSearch();
            jQuery("#dataTable").reloadGrid(map);
        }
        function lstdjgzj(){
            showDialog('绿色通道结果增加',780,450,'xszz_lstd.do?method=lstdjgZj');
        }
        function lstdjgDelete() {
            var ids = jQuery("#dataTable").getSeletIds();
            if (ids.length == 0){
                showAlertDivLayer("请选择您要删除的申请记录！");
            } else {
                var rows = jQuery("#dataTable").getSeletRow();

                for(var i=0;i<ids.length;i++){
                    if(rows[i]['sjly']=='1'){
                        showAlertDivLayer("审核流数据不能删除！");
                        return false;
                    }
                }

                showConfirmDivLayer("您确定要删除该申请吗？",{"okFun":function(){
                    jQuery.post("xszz_lstd.do?method=delLstdjg",{values:ids.toString()},function(data){
                        showAlertDivLayer(data["message"]);
                        jQuery("#dataTable").reloadGrid();
                    },'json');
                }});
            }
        }
        //导出
        function exportConfig(){
            var DCCLBH='xszz_lstd_jg.do';
            customExport(DCCLBH, exportData);
        }

        function exportData(){
            var DCCLBH='xszz_lstd_jg.do';
            setSearchTj();//设置高级查询条件
            var url = "xszz_lstd.do?method=export&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
            url = addSuperSearchParams(url);//设置高级查询参数
            jQuery("form").eq(0).attr("action", url);
            jQuery("form").eq(0).submit();
        }
        jQuery(function(){
            initGrid();
        })
    </script>
</head>
<body>
<html:form action="/xszz_lstd">
    <%@ include file="/comm/hiddenValue.jsp"%>
    <div class="toolbox">
        <!-- 按钮 -->
        <div class="buttonbox">
            <ul>
                <li>
                    <a href="javascript:void(0);" onclick="lstdjgzj();return false;"
                       class="btn_zj">增加</a>
                </li>
                <li>
                    <a href="javascript:void(0);" onclick="lstdjgDelete();return false;"
                       class="btn_sc">删除</a>
                </li>
                <%--<li>
                    <a href="javascript:void(0);" onclick="return false;"
                       class="btn_dr">导入</a>
                </li>--%>
                <li>
                    <a href="javascript:void(0);" onclick="exportConfig();return false;"
                       class="btn_dc">导出</a>
                </li>
                <li>
                    <a href="javascript:void(0);" onclick="print();return false;"
                    class="btn_dy">打印</a>
                </li>
            </ul>
        </div>
        <!-- 过滤条件 -->
        <%@ include file="/comm/search/superSearchArea.jsp"%>
        <!-- 过滤条件 end-->
    </div>
</html:form>

<div class="main_box">
    <h3 class=datetitle_01>
        <span>绿色通道结果列表&nbsp;&nbsp; </span>
    </h3>
    <div class="con_overlfow">
        <table id="dataTable" ></table>
        <div id="pager"></div>
    </div>
</div>
</body>
</html>
