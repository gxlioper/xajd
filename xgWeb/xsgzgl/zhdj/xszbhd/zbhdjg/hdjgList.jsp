<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/5/30
  Time: 14:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=GBK" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini"%>
    <script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
    <script type="text/javascript" language="javascript" src="js/comm/commFunction.js"></script>
    <script type="text/javascript" src="xsgzgl/zhdj/xszbhd/zbhdfb/js/hdfbList.js"></script>
    <script type="text/javascript">
        var gridSetting = {
            caption: "活动列表",
            pager: "pager",
            url: "xszbhd_hdjg.do?method=getJgList&type=query",
            colList: [
                {label: 'hdsbid', name: 'hdsbid', index: 'hdsbid',hidden:true,key:true},
                {label: '类型', name: 'lx', index: 'lx',hidden:true},
                {label: '学年', name: 'xn', index: 'xn', width: '5%'},
                {label: '学期', name: 'xqmc', index: 'xqmc', width: '5%'},
                {label: '学院', name: 'xymc', index: 'xymc', width: '10%'},
                //{label: '参加人员', name: 'cjry', index: 'cjry', width: '10%'},
                {label: '支部', name: 'dzbmc', index: 'dzbmc', width: '10%'},
                {label: '三会一课/党日活动', name: 'shykdrhdmc', index: 'shykdrhdmc', width: '10%'},
                {label: '活动类型', name: 'hdlxmc', index: 'hdlxmc', width: '10%'},
                {label: '主题', name: 'hdzt', index: 'hdzt', width: '10%',formatter:viewLink}

            ],
            sortname: "hdzt",
            sortorder: "asc"
        };
        function viewLink(cellValue, rowObject) {
            return "<a href='javascript:void(0);' class='name' onclick='view(\""
                + rowObject["hdsbid"] + "\",\"" + rowObject["lx"] + "\");'>" + cellValue
                + "</a>";
        }
        function view(hdsbid,type){
            var url = "xszbhd_hdjg.do?method=view&hdsbid="+hdsbid+"&type="+type;
            var title = "查看";
            showDialog(title, 750, 420, url);
        }
        jQuery(function () {
            jQuery("#dataTable").initGrid(gridSetting);
        });

        //导出
        function exportConfig(){
            var DCCLBH='zhdj_xszbhdjg.do';
            customExport(DCCLBH, exportData);
        }
        function exportData(){
            var shzt=jQuery("#shzt").val();
            var DCCLBH='zhdj_xszbhdjg.do';
            setSearchTj();//设置高级查询条件
            var url = "xszbhd_hdjg.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
            url = addSuperSearchParams(url);//设置高级查询参数
            jQuery("form").eq(0).attr("action", url);
            jQuery("form").eq(0).submit();
        }
    </script>

</head>
<body>
<html:form action="/xszbhd_hdfb" method="post">
    <%@ include file="/comm/hiddenValue.jsp" %>
    <div class="tab_cur">
        <p class="location">
            <em>您的当前位置：</em><a>${title }</a>
        </p>
    </div>

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
        <%@ include file="/comm/search/superSearchArea.jsp" %>
        <!-- 过滤条件 end-->
    </div>
</html:form>
<div class="main_box">
    <h3 class=datetitle_01>
        <span>活动上报结果列表&nbsp;&nbsp; </span>
    </h3>
    <div class="con_overlfow">
        <table id="dataTable"></table>
        <div id="pager"></div>
    </div>
</div>
</body>
</html>