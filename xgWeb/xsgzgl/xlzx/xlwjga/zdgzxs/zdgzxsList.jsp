<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini"%>
    <script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
    <script type="text/javascript" src="js/calendar/calendar.js"></script>
    <script type="text/javascript" src="xsgzgl/xlzx/xlwjga/zdgzxs/js/zdgzxs.js"></script>
    <script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script><!-- 导入功能需要 -->
    <script type="text/javascript">

        jQuery(function(){
            initGridSetting();
        });
        function initGridSetting(){
            var gridSetting = {
                caption : "重点关注学生列表",
                pager : "pager",
                url : "xlzx_zdgzxs.do?method=getList&type=query",
                colList:[
                    {label : 'id',name : 'id',index : 'id',hidden : true,key : true},
                    {label : '学号',name : 'xh',index : 'xh',formatter : xhLink},
                    {label : '姓名',name : 'xm',index : 'xm'},
                    {label : '性别',name : 'xb',index : 'xb'},
                    {label : '年级',name : 'nj',index : 'nj'},
                    {label : '学院',name : 'xymc',index : 'xymc'},
                    {label : '书院',name : 'symc',index : 'symc'},
                    {label : '班级',name : 'bjmc',index : 'bjmc'},
                    {label : '咨询师',name : 'zxs',index : 'zxs'},
                    {label : '问题类别',name : 'wtlbmc',index : 'wtlbmc'},
                    {label : 'lrsj',name : 'lrsj',index : 'lrsj',hidden : true}
                ],
                sortname : "",
                sortorder : ""
            };

            gridSetting["params"]=getSuperSearch();
            jQuery("#dataTable").initGrid(gridSetting);

        }

        function searchRs() {
            var map = getSuperSearch();

            jQuery("#dataTable").reloadGrid(map);
        }
        function xhLink(cellValue, rowObject) {
            return "<a href=\"javascript:void(0);\" class=\"name\" onclick=\"view('"+ rowObject["id"]+"');\">" + cellValue + "</a>";
        }
    </script>
</head>

<body>

<div class="tab_cur">
    <p class="location">
        <em>您的当前位置：</em><a>${title }</a>
    </p>
</div>
<html:form action="/xlzx_zdgzxs">
    <%@ include file="/comm/hiddenValue.jsp"%>
    <!-- 隐藏域 -->
    <div class="toolbox">
        <!-- 按钮 -->
        <div class="buttonbox">
            <ul>
                <logic:equal name="writeAble" value="yes" >
                    <li>
                        <a href="#" onclick="add();return false;" class="btn_zj">增加</a>
                    </li>
                    <li>
                        <a href="#" onclick="update();return false;" class="btn_xg">修改</a>
                    </li>
                    <li>
                        <a href="#" onclick="del();return false;" class="btn_sc">删除</a>
                    </li>
                </logic:equal>
                <li>
                    <a href="#" onclick="exportConfig();return false;" class="btn_dc">导出</a>
                </li>
            </ul>
        </div>
        <!-- 过滤条件 -->
        <%@ include file="/comm/search/superSearchArea.jsp"%>
        <!-- 过滤条件 end-->
    </div>
</html:form>
<div class="formbox">
    <!--标题start-->
    <h3 class="datetitle_01">
        <span> 列表 </span>
    </h3>

    <table id="dataTable" ></table>
    <div id="pager"></div>

</div>
</body>
</html>
