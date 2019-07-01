<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini"%>
    <script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
    <script type="text/javascript" src="js/calendar/calendar.js"></script>
    <script type="text/javascript">
        jQuery(function(){
            var gridSetting = {
                caption:"待岗学生库列表",
                pager:"pager",
                url:"qgzx_dgxsk.do?method=dgxskManage&type=query",
                colList:[
                    { label : '学号', name : 'xh', index : 'xh' ,width : '10%',formatter:xhLink},
                    { label : '姓名', name : 'xm', index : 'xm', width : '10%'},
                    { label : '性别', name : 'xb', index : 'xb', width : '7%'},
                    { label : '学院', name : 'xymc', index : 'xymc', width : '16%'},
                    { label : '专业', name : 'zymc', index : 'zymc', width : '16%'},
                    { label : '班级', name : 'bjmc', index : 'bjmc', width : '16%'},
                    { label : '岗位', name : 'gwmc', index : 'gwmc', width : '15%'},
                    { label : '状态', name : 'ztmc', index : 'ztmc', width : '10%'},
                    { label : '岗位代码', name : 'gwdm', index : 'gwdm', hidden:true},
                    { label : '申请编号', name : 'sqbh', index : 'sqbh', hidden:true}
                ],
                sortname : "xh",
                sortorder : "asc" };
            var map = getSuperSearch();
            gridSetting["params"] = map;
            jQuery("#dataTable").initGrid(gridSetting);
        });
        function searchRs(){
            var map = getSuperSearch();
            jQuery("#dataTable").reloadGrid(map);
        }
        function xhLink(cellValue,rowObject){
            return "<a href='javascript:void(0);' class='name' onclick=\"viewKysj('"+rowObject["xh"]+"','"+rowObject["sqbh"]+"');\">"+cellValue+"</a>";
        }
        function viewKysj(xh,sqbh){
            if("" != sqbh && null != sqbh && "null" != sqbh){
                showDialog("查看",765,500,"qgzx_xsgwsh.do?method=xsgwSh&type=ck&xh="+xh+"&sqbh="+sqbh);
            }else{
                var url = "qgzx_dgxsk.do?method=dgxskView&xh="+xh;
                var title = "查看";
                showDialog(title, 765,600, url);
            }

        }
        //导出
        function exportConfig(){
            var DCCLBH='qgzx_dgxsk.do';
            customExport(DCCLBH, exportData);
        }
        function exportData(){
            var DCCLBH='qgzx_dgxsk.do';
            setSearchTj();//设置高级查询条件
            var url = "qgzx_dgxsk.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
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
<html:form action="/qgzx_dgxsk">
    <%@ include file="/comm/hiddenValue.jsp"%>
    <div class="toolbox">
        <!-- 按钮 -->
        <div class="buttonbox">
            <ul>
                <li>
                    <a href="#" class="btn_dc" onclick="exportConfig();return false;">导出</a>
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
        <span>待岗学生库列表&nbsp;&nbsp; </span>
    </h3>
    <div class="con_overlfow">
        <table id="dataTable" ></table>
        <div id="pager"></div>
    </div>
</div>
</body>
</html>