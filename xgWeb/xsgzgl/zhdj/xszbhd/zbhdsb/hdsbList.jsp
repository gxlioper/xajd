<%@ page language="java" contentType="text/html; charset=GBK" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini"%>
    <script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
    <script type="text/javascript" language="javascript" src="js/comm/commFunction.js"></script>
    <script type="text/javascript" src="xsgzgl/zhdj/xszbhd/zbhdsb/js/hdsbList.js"></script>
    <script type="text/javascript">

    </script>

</head>
<body>
<html:form action="/xszbhd_hdfb" method="post">
    <div style='width:100%; height:500px; overflow-y:auto;overflow-x:hidden'>
        <%@ include file="/comm/hiddenValue.jsp" %>
        <html:hidden property="type" styleId="type"  value="zxsb"/>

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
                        <a href="javascript:void(0);" class="btn_zj" onclick="add();return false;">上报</a>
                    </li>
                    <li>
                        <a href="#" class="btn_xg" onclick="update();return false;">修改</a>
                    </li>

                    <li>
                        <a href="javascript:void(0);" class="btn_sc" onclick="del();return false;">删除</a>
                    </li>
                  </logic:equal>
                    <li>
                        <a href="javascript:void(0);" class="btn_ck" onclick="view();return false;">查看结果</a>
                    </li>
                </ul>
            </div>
            <!-- 过滤条件 -->
            <%@ include file="/comm/search/superSearchArea.jsp" %>
            <!-- 过滤条件 end-->
        </div>
        <div class="comp_title" id="comp_title">
            <ul style="width:90%">

                <li class="ha"><a href="javascript:void(0);" onclick="query(this,'zxsb');"><span>自行上报</span></a></li>
                <li><a href="javascript:void(0);" onclick="query(this,'xssb');"><span>限时上报</span></a></li>
            </ul>
        </div>

        <div class="main_box">
            <h3 class=datetitle_01>
                <span>党支部列表&nbsp;&nbsp; </span>
            </h3>
            <div class="con_overlfow">
                <table id="dataTable"></table>
                <div id="pager"></div>
            </div>
        </div>


    </div>

</html:form>
</body>
</html>