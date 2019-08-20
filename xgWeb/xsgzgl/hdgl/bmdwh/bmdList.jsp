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
                caption:"",
                pager:"pager",
                url:"hdgl_bmdwh.do?method=bmdList&type=query",
                colList:[
                    {label:'id',name:'id', index: 'id',key:true,hidden:true },
                    {label:'ip',name:'ip', index: 'ip',width:'10%'},
                    {label:'描述',name:'ms', index: 'ms',width:'10%'}
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
            showDialog("新增白名单ip",700,350,"hdgl_bmdwh.do?method=addBmd");
        }
        function update(){
            var rows = jQuery("#dataTable").getSeletRow();
            if(rows.length!=1){
                showAlertDivLayer("请选择一条您要修改的记录");
                return false;
            }
            showDialog("重置密码",500,200,"hdgl_bmdwh.do?method=updateBmd&id="+rows[0].id );

        }
        function delBmd(){
            var ids = jQuery("#dataTable").getSeletIds();
            if (ids.length == 0) {
                showAlertDivLayer("请选择您要删除的记录！");
            } else {
                var rows = jQuery("#dataTable").getSeletRow();
                showConfirmDivLayer("您确定要删除选择的记录吗？", {
                    "okFun" : function() {
                        jQuery.post("hdgl_bmdwh.do?method=delBmd", {
                            values : ids.toString()
                        }, function(data) {
                            var mes="成功删除了<font color='green'>&nbsp;"+data["num"]+"&nbsp;</font>条数据";
                            showAlertDivLayer(mes);
                            jQuery("#dataTable").reloadGrid();
                        }, 'json');
                    }
                });
            }
        }
    </script>
</head>
<body>
<div class="tab_cur">
    <p class="location">
        <em>您的当前位置：</em><a>${title }</a>
    </p>
</div>
<html:form action="/hdgl_hdgl_tj">
    <%@ include file="/comm/hiddenValue.jsp"%>
    <div class="toolbox">
        <!-- 按钮 -->
        <div class="buttonbox">
            <ul>
                <li><a href="#" class="btn_zj" onclick="add();return false;">增加</a></li>
                <li><a href="#" class="btn_xg" onclick="update();return false;">重置密码</a></li>
                <li><a href="#" class="btn_sc" onclick="delBmd();return false;">删除</a></li>
                <%--<li><a href="#" class="btn_dr" onclick="importConfig();return false;">导入</a></li>--%>
                <%--<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">导出</a></li>--%>
                    <%--<li><a href="#" class="btn_dc" onclick="return false;">推送报名数据</a></li>--%>
            </ul>
        </div>
        <!-- 过滤条件 -->
        <%@ include file="/comm/search/superSearchArea.jsp"%>
        <!-- 过滤条件 end-->
    </div>
</html:form>

<div class="main_box">
    <h3 class=datetitle_01>
        <span>活动签到&nbsp;&nbsp; </span>
    </h3>
    <div class="con_overlfow" style="text-align: center;width: 80%">
        <table id="dataTable" ></table>
        <div id="pager"></div>
    </div>
</div>
</body>
</html>
