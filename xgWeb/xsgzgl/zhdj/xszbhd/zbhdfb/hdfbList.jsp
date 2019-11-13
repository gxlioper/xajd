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
            url: "xszbhd_hdfb.do?method=getHdfbList&type=query",
            colList: [
                {label: 'hdid', name: 'hdid', index: 'hdid',hidden:true,key:true},
                {label: '学年', name: 'xn', index: 'xn', width: '10%'},
                {label: '学期', name: 'xqmc', index: 'xqmc', width: '10%'},
                {label: '主题', name: 'hdzt', index: 'hdzt', width: '10%'},
                //{label: '参加人员', name: 'cjry', index: 'cjry', width: '10%'},
                {label: '开始时间', name: 'kssj', index: 'kssj', width: '10%'},
                {label: '截止时间', name: 'jzsj', index: 'jzsj', width: '10%'},
                {label: '详情', width: '5%',formatter:xqLink},
                {label: '进度', width: '5%',formatter:jdLink}

            ],
            sortname: "kssj",
            sortorder: "asc"
        }
        jQuery(function () {
            jQuery("#dataTable").initGrid(gridSetting);
        });
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
                 <logic:equal name="writeAble" value="yes">

                    <li>
                        <a href="javascript:void(0);" class="btn_zj" onclick="add();return false;">增加</a>
                    </li>
                    <li>
                        <a href="javascript:void(0);" class="btn_xg" onclick="update();return false;">修改</a>
                    </li>
                    <li>
                        <a href="#" class="btn_zj" onclick="selectDzb();return false;">选择面向党支部</a>
                    </li>

                    <li>
                        <a href="javascript:void(0);" class="btn_sc" onclick="del();return false;">删除</a>
                    </li>

                 </logic:equal>


                     <%--<logic:equal value="zf01" name="userName">--%>
                        <%--<li>--%>
                            <%--<a href="#" class="btn_dc" onclick="exportConfig();return false;">导出</a>--%>
                        <%--</li>--%>
                    <%--</logic:equal>--%>
                </ul>
            </div>
            <!-- 过滤条件 -->
            <%@ include file="/comm/search/superSearchArea.jsp" %>
            <!-- 过滤条件 end-->
        </div>
    </html:form>
    <div class="main_box">
        <h3 class=datetitle_01>
            <span>活动列表&nbsp;&nbsp; </span>
        </h3>
        <div class="con_overlfow">
            <table id="dataTable"></table>
            <div id="pager"></div>
        </div>
    </div>
</body>
</html>