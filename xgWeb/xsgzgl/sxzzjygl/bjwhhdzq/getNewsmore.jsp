<%@ page language="java" contentType="text/html; charset=GBK" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini"%>
    <script type="text/javascript" src="js/calendar/calendar.js"></script>
    <script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
    <script type="text/javascript" language="javascript" src="js/comm/commFunction.js"></script>
    <script type="text/javascript">


        jQuery(function () {
            var hdlx = jQuery("#hdlx").val();
            var map = getSuperSearch();
            var gridSetting = {
                caption: "发布列表",
                pager: "pager",
                url: "bjwhhdzq_bjwh.do?method=getNewsmore&type=query&hdlx="+hdlx,
                colList: [
                    { label : 'key', name : 'jgid', index : 'jgid',key : true, hidden : true },
                    { label : '活动名称', name : 'hdmcbjmc', index : 'hdmcbjmc', width : '60%',formatter: hdLink},
                    { label : '活动日期', name : 'hdrq', index : 'hdrq', width : '40%' },
                ],
                sortname: "hdrq",
                sortorder: "desc"
            };
            gridSetting["params"] = map;
            jQuery("#dataTable").initGrid(gridSetting);
        });

        function searchRs() {
            var map = getSuperSearch();
            jQuery("#dataTable").reloadGrid(map);
        }


        //活动查看
        function hdLink(cellValue, rowObject) {
            return "<a href='javascript:void(0);' class='name' onclick='View(\""
                + rowObject['jgid'] + "\");'>" + cellValue
                + "</a>";
        }


        function View(jgid) {
            var hdlx = jQuery("#hdlx").val();
            if(hdlx == "001")
            {
                showDialog("活动信息", 900, 450, "ztbhgl_ztbhjg.do?method=getHdInfo&jgid=" + jgid );
            }
            else{
                showDialog("活动信息", 900, 450, "bjhdgl_bjhdjg.do?method=getHdInfo&jgid=" + jgid );
            }

        }
    </script>

</head>
<body>
<html:form action="/bjwhhdzq_bjwh" method="post">
    <%@ include file="/comm/hiddenValue.jsp" %>
    <input type="hidden" id="hdlx" value="${hdlx}"/>
    <div class="tab_cur">
        <p class="location">
            <em>您的当前位置：</em><a>${title}</a>
        </p>
    </div>

    <div class="toolbox">
        <!-- 按钮 -->
        <div class="buttonbox">
            <ul>
                <a href="sxzzjy_bjwhhdzq_bjwh.do">
                    <button type="button" onclick="iFClose();">
                    返回
                    </button>
                </a>
            </ul>
        </div>
        <!-- 过滤条件 -->
        <%@ include file="/comm/search/superSearchArea.jsp" %>
        <!-- 过滤条件 end-->
    </div>
</html:form>
<div class="main_box">
    <h3 class=datetitle_01>
        <span>新闻列表&nbsp;&nbsp; </span>
    </h3>
    <div class="con_overlfow">
        <table id="dataTable"></table>
        <div id="pager"></div>
    </div>
</div>
</body>
</html>