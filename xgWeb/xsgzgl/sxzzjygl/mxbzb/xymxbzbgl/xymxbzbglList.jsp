<%@ page language="java" contentType="text/html; charset=GBK" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini"%>
    <script type="text/javascript" src="js/calendar/calendar.js"></script>
    <script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
    <script type="text/javascript" language="javascript" src="js/comm/commFunction.js"></script>
    <script type="text/javascript" src="xsgzgl/sxzzjygl/mxbzb/xymxbzbgl/js/xymxbzbglList.js"></script>
    <script type="text/javascript">

    </script>

</head>
<body>
<html:form action="/sxzzjy_xymxbzbgl" method="post">
    <%@ include file="/comm/hiddenValue.jsp" %>
    <div class="tab_cur">
        <p class="location">
            <em>您的当前位置：</em><a>${title}</a>
        </p>
    </div>

    <div class="toolbox">
        <!-- 按钮 -->
        <div class="buttonbox">
            <ul>
                <logic:equal name="writeAble" value="yes">
                    <li><a href="javascript:void(0);" onclick="addNews();return false;" class="btn_zj"> 增加 </a></li>
                    <li><a href="javascript:void(0);" onclick="updateNews();return false;" class="btn_xg"> 修改 </a></li>
                    <li><a href="javascript:void(0);" onclick="delNews();return false;" class="btn_sc"> 删除 </a></li>
                    <li><a href="javascript:void(0);" class="btn_yl" onclick="newsyl();return false;">预览</a></li>
                    <li><a href="javascript:void(0);" class="btn_shtg" onclick="fb();return false;">发布</a></li>
                    <li><a href="javascript:void(0);" onclick="qxfb();return false;" class="btn_shbtg"> 取消发布 </a></li>
                    <li><a href="javascript:void(0);" onclick="zd();return false;" class="btn_sy"> 置顶</a></li>
                    <li><a href="javascript:void(0);" onclick="qxzd();return false;" class="btn_xy"> 取消置顶 </a></li>
                </logic:equal>

            </ul>
        </div>
        <!-- 过滤条件 -->
        <%@ include file="/comm/search/superSearchArea.jsp" %>
        <!-- 过滤条件 end-->
    </div>
</html:form>
<div class="main_box">
    <h3 class=datetitle_01>
        <span>发布列表&nbsp;&nbsp; </span>
    </h3>
    <div class="con_overlfow">
        <table id="dataTable"></table>
        <div id="pager"></div>
    </div>
</div>
</body>
</html>