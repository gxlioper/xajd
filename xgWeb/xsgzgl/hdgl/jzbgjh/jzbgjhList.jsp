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
                caption:"书单列表",
                pager:"pager",
                url:"hdgl_hdgl_jzbgjh.do?method=jzbgjhList&type=query",
                colList:[
                    {label:'key',name:'jzjhid', index: 'jzjhid',key:true ,hidden:true},
                    {label:'主题',name:'jzzt', index: 'jzzt',width:'8%'},
                    {label:'主讲人',name:'jzzjr', index: 'jzzjr',width:'8%'},
                    {label:'拟定时间',name:'jzndsj', index: 'jzndsj',width:'10%'},
                    {label:'拟定地点',name:'jznddd', index: 'jznddd',width:'8%'},
                    {label:'主办单位',name:'jzzbdw', index: 'jzzbdw',width:'8%'},
                    {label:'主讲人介绍',name:'jzzjrjs', index: 'jzzjrjs',width:'15%'}
                ]
            }
            gridSetting["params"]=getSuperSearch();
            jQuery("#dataTable").initGrid(gridSetting);
        });

        function searchRs(){
            var map = getSuperSearch();
            jQuery("#dataTable").reloadGrid(map);

        }
        function add(){
            var url = "hdgl_hdgl_jzjh.do?method=add";
            var title = "增加讲座信息";
            showDialog(title,700,350,url);

        }

        function update() {
            var rows = jQuery("#dataTable").getSeletRow();
            if (rows.length != 1) {
                showAlertDivLayer("请选择一条您要修改的记录！");
            } else {
                var url = 'hdgl_hdgl_jzjh.do?method=update&jzjhid=' + rows[0]["jzjhid"];
                showDialog("修改讲座信息", 700,350, url);
            }
        }


        //删除
        function del() {
            var ids = jQuery("#dataTable").getSeletIds();
            if (ids.length == 0) {
                alertInfo("请选择您要删除的记录！");
            } else {
                showConfirmDivLayer("您确定要删除选择的记录吗？", {
                    "okFun" : function() {
                        jQuery.post("hdgl_hdgl_jzjh.do?method=del", {
                            values : ids.toString()
                        }, function(data) {
                            alertInfo(data["message"]);
                            jQuery("#dataTable").reloadGrid();
                        }, 'json');
                    }
                });
            }
        }

        //新版导入
        function dr() {
            toImportDataNew("IMPORT_JZJHBG");
            return false;
        }

        var DCCLBH = "hdgl_hdgl_jzbgjh.do";//dcclbh,导出功能编号

        //自定义导出 功能
        function exportConfig() {
            //DCCLBH导出功能编号,执行导出函数
            customExport(DCCLBH, ExportData);
        }

        // 导出方法
        function ExportData() {
            setSearchTj();//设置高级查询条件
            var url = "hdgl_hdgl_jzjh.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
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
<html:form action="/hdgl_hdgl_jzjh">
    <%@ include file="/comm/hiddenValue.jsp"%>
    <div class="toolbox">
        <!-- 按钮 -->
        <div class="buttonbox">
            <ul>
                <%--<logic:equal name="writeAble" value="yes">--%>
                    <li>
                        <a href="javascript:void(0);" class="btn_zj" onclick="add();return false;">增加</a>
                    </li>
                    <li>
                        <a href="javascript:void(0);" onclick="update();return false;" class="btn_xg">修改</a>
                    </li>
                    <li>
                        <a href="javascript:void(0);" onclick="del();return false;" class="btn_sc">删除</a>
                    </li>
                    <li>
                        <a href="javascript:void(0);" onclick="dr();return false;" class="btn_dr">导入</a>
                    </li>
                <%--</logic:equal>--%>
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
        <span>讲座报告计划列表&nbsp;&nbsp; </span>
    </h3>
    <div class="con_overlfow">
        <table id="dataTable" ></table>
        <div id="pager"></div>
    </div>
</div>
</body>
</html>
