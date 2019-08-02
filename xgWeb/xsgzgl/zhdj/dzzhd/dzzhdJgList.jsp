<!--党组织日常活动管理结果-->
<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini"%>
    <script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
    <script type="text/javascript" src="js/calendar/calendar.js"></script>
    <script type="text/javascript" src="xsgzgl/zhdj/dzzhd/js/dzzhdJgList.js"></script>
    <script type="text/javascript">
    </script>
</head>

<body>
<div class="tab_cur">
    <p class="location">
        <em>您的当前位置：</em><a>党团建设-党组织日常管理-活动发布</a>
    </p>

</div>
<html:form action="/zhdj_dzzhd">
    <input type="hidden" id="status" value="${status}"/>
    <input type="hidden" id="userType" value="${userType}"/>
    <%@ include file="/comm/hiddenValue.jsp"%>

    <div class="toolbox">
        <!-- 按钮 -->
        <div class="buttonbox">
            <ul>
                <logic:notEqual value="stu" name="userType">
                <li>
                    <a href="javascript:void(0);" onclick="hdRygl();return false;" class="btn_ck">人员管理</a>
                </li>
                </logic:notEqual>
                <logic:equal value="stu" name="userType">
                    <li>
                        <a href="javascript:void(0);" onclick="hdXd();return false;" class="btn_tj">活动心得</a>
                    </li>
                </logic:equal>
            </ul>
        </div>
        <!-- 过滤条件 -->
        <%@ include file="/comm/search/superSearchArea.jsp"%>
        <!-- 过滤条件 end-->
    </div>
</html:form>

<div class="main_box">
    <h3 class=datetitle_01>
        <span>已发布活动列表&nbsp;&nbsp; </span>
    </h3>
    <div class="con_overlfow">
        <table id="dataTable" ></table>
        <div id="pager"></div>
    </div>
</div>
</body>
</html>

