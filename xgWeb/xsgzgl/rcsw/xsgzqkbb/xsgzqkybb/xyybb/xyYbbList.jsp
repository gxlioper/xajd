<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini"%>
    <script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
    <script type="text/javascript" src="js/calendar/calendar.js"></script>
    <script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
    <script type="text/javascript" src="xsgzgl/rcsw/xsgzqkbb/xsgzqkybb/xyybb/js/xyYbb.js"></script>
    <script type="text/javascript">
        jQuery(function(){
            var gridSetting = {
                caption : "${gnmkmc}列表",
                pager : "pager",
                url : "rcsw_xsgzqkbb_xyybbgl.do?method=getXyYbbListData",
                colList : [
                    { label : 'key', name : 'id', index : 'id',key : true, hidden : true },
                    { label : '学年', name : 'xn', index : 'xn', width : '11%' },
                    { label : '学期', name : 'xqmc', index : 'xq', width : '11%' },
                    { label : '月份', name : 'yf', index : 'yf', width : '11%' },
                    { label : '学院', name : 'xymc', index : 'xydm', width : '16%' },
                    { label : '学生工作负责人', name : 'xsgzfzrmc', index : 'xsgzfzr', width : '11%' },
                    { label : '填表人', name : 'tbrmc', index : 'tbr', width : '11%' },
                    { label : '填表日期', name : 'tbrq', index : 'tbrq', width : '14%' },
                    { label : '已填/总班级数', name : 'ytbjrsb', index : 'ytbjrsb', width : '15%',formatter:ytbjrsbLink }
                ],
                sortname : "tbrq",
                sortorder : "desc" }
            var map = getSuperSearch();
            gridSetting["params"] = map;
            jQuery("#dataTable").initGrid(gridSetting);
        })
        function printfdyybb(){
        	var check = jQuery("input[type=checkbox][name=grid_key]:checked");
			var ids = jQuery("#dataTable").getSeletIds();
			var len = ids.length;
			if(len == 1){
				var url = "";
				url = "rcsw_xsgzqkbb_xyybbgl.do?method=getJsdj";
				url += "&id=" + ids;
				
			}else{
				showAlertDivLayer("请选择<font color='blue'>一条记录</font>");
				return false;
			}
			
			window.open(url);
		}
    </script>
</head>

<body>
<div class="tab_cur">
    <p class="location">
        <em>您的当前位置：</em><a>${title }</a>
    </p>
</div>

<html:form action="/rcsw_xsgzqkbb_xyybbgl">
    <%@ include file="/comm/hiddenValue.jsp"%>
    <input type="hidden" id="gnmkmc" value="${gnmkmc}"/>

    <div class="toolbox">
        <!-- 按钮 -->
        <div class="buttonbox">
            <ul>
                <logic:equal name="writeAble" value="yes">
                    <li>
                        <a href="javascript:void(0);" onclick="add();return false;" class="btn_zj">增加</a>
                    </li>
                    <li>
                        <a href="javascript:void(0);" onclick="edit();return false;" class="btn_xg" >修改</a>
                    </li>
                    <li>
                        <a href="javascript:void(0);" onclick="del();return false;" class="btn_sc" >删除</a>
                    </li>
                    <li>
                        <a href="javascript:void(0);" onclick="fillin();return false;" class="btn_xg" >填写</a>
                    </li>
                    <logic:equal name="xxdm" value="10364">
						<li>
							<a href="javascript:void(0);"
								onclick="printfdyybb();return false;" class="btn_dy">辅导员工作情况月报表</a>
						</li>
					</logic:equal>
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
        <span>${gnmkmc}列表&nbsp;&nbsp; </span>
    </h3>
    <div class="con_overlfow">
        <table id="dataTable" ></table>
        <div id="pager"></div>
    </div>
</div>
</body>
</html>
