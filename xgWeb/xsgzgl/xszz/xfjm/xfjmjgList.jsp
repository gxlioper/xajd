<!--学费减免申请-->
<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini"%>
    <script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
    <script type="text/javascript" src="js/calendar/calendar.js"></script>
    <script type="text/javascript" src="xsgzgl/xszz/xfjm/js/xfjmjglist.js"></script>
    <script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
    <script type="text/javascript">
    </script>
</head>

<body>
<div class="tab_cur">
    <p class="location">
        <em>您的当前位置：</em><a>资助管理-学费减免审核结果</a>
    </p>

</div>
<html:form action="/xszz_new_xfjm">
    <input type="hidden" name="status" id="status" value="${status}"/>
    <%@ include file="/comm/hiddenValue.jsp"%>
    <div class="toolbox">
        <!-- 按钮 -->
        <div class="buttonbox">
            <ul>
                <li>
                    <a href="javascript:void(0);" class="btn_zj" onclick="xfjmzj();return false;"  title="点击该按钮，打开申请表填写页面。">增加</a>
                </li>
                <li>
                    <a href="javascript:void(0);" onclick="xfjmxg();return false;" class="btn_xg" title="选中一条记录，点击该按钮可修改申请表。">修改</a>
                </li>
                <li>
                    <a href="javascript:void(0);" onclick="xfjmsc();return false;" class="btn_sc" title="只能取消未审核的记录，已经在审核的不能取消。" >删除</a>
                </li>
                <li>
                    <a href="javascript:void(0);" onclick="xfjmdr();return false;" class="btn_dr" >导入</a>
                </li>
                <li>
                    <a href="javascript:void(0);" onclick="xfjmdc();return false;" class="btn_dc" >导出</a>
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
        <span>学生资助审核结果列表&nbsp;&nbsp; </span>
    </h3>
    <div class="con_overlfow">
        <table id="dataTable" ></table>
        <div id="pager"></div>
    </div>
</div>
</body>
</html>

