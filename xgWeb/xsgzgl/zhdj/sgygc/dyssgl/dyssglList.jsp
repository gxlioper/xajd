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
    <script type="text/javascript" src="xsgzgl/zhdj/sgygc/dyssgl/js/dyssglList.js"></script>
    <script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
    <script type="text/javascript">


    </script>

</head>
<body>
<html:form action="/zhdj_dyssgl" method="post">
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
                <logic:equal name="writeAble" value="yes">

                    <li>
                        <a href="javascript:void(0);" class="btn_zj" onclick="add();return false;">新增联系宿舍</a>
                    </li>
                    <li>
                        <a href="javascript:void(0);" class="btn_xg" onclick="update();return false;">修改</a>
                    </li>

                    <li>
                        <a href="javascript:void(0);" class="btn_sc" onclick="del();return false;">删除</a>
                    </li>
                    <li>
                        <a href="javascript:void(0);" class="btn_xg" onclick="tjzj();return false;">提交总结情况表</a>
              </li>
                    <li>
                        <a href="#" class="btn_dr" onclick="importConfig();return false;">导入</a>
                    </li>
                </logic:equal>
                <li>
                    <a href="#" class="btn_dr" onclick="view();return false;">查看</a>
                </li>
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
        <span>党员宿舍管理列表&nbsp;&nbsp; </span>
    </h3>
    <div class="con_overlfow">
        <table id="dataTable"></table>
        <div id="pager"></div>
    </div>
</div>
</body>
</html>