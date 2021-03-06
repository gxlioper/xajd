<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="/syscommon/head.ini"%>
	<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
	<script type="text/javascript" src="js/calendar/calendar.js"></script>
	<script type="text/javascript" src="xsgzgl/xyfd/jzppwh/yytj/js/yytj.js"></script>
	<script type="text/javascript">
        var gridSetting = {
            caption : "预约记录列表",
            pager : "pager",
            url : "xyfd_yytj.do?method=yytjList&type=query",
            colList : [
                { label : 'yyid', name : 'yyid', index : 'yyid',key : true,hidden : true },
                { label : '预约号', name : 'yyh', index : 'yyh',width:'12%'},
                { label : '预约学生', name : 'xh', index : 'xh',hidden:true},
                { label : '预约学生', name : 'xm', index : 'xm',width:'10%'},
                { label : '预约人', name : 'yyr', index : 'yyr',width:'10%'},
                { label : '辅导时间', name : 'fdsj', index : 'fdsj',width:'10%'},
                { label : '课程名称', name : 'kcmc', index : 'kcmc', width : '10%' },
                { label : '辅导教师', name : 'fdjsxm', index : 'fdjsxm', width : '10%' },
                { label : '辅导地点', name : 'fddd', index : 'fddd', width : '15%' },
                { label : '审核状态', name : 'shztmc', index : 'shztmc', width : '10%' },
                { label : '审核状态', name : 'zt', index : 'zt', hidden : true}
            ],
            radioselect:false
        }

        jQuery(function(){
            var map = getSuperSearch();
            map["cxmb"]="yyjl";
            gridSetting["params"] = map;
            jQuery("#dataTable").initGrid(gridSetting);
        });



	</script>
</head>

<body>
<div class="tab_cur">
	<p class="location">
		<em>您的当前位置：</em><a>${title }</a>
	</p>
</div>
<html:form action="/xyfd_yytj">
	<input type="hidden" id="cxmb" value="yyjl"/>
	<%@ include file="/comm/hiddenValue.jsp"%>
	<div class="toolbox">
		<!-- 按钮 -->
		<div class="buttonbox">
			<ul>
				<li>
					<a href="javascript:void(0);" onclick="ckfd();return false;" class="btn_dc">导出</a>
				</li>
			</ul>
		</div>
		<!-- 过滤条件 -->
		<%@ include file="/xsgzgl/xyfd/jzppwh/fdyywh/superSearchAreaforFdyy.jsp"%>
		<!-- 过滤条件 end-->

	</div>
</html:form>
<div class="main_box">
	<h3 class=datetitle_01>
		<span>活动补录审核列表&nbsp;&nbsp; </span>
	</h3>
	<div class="con_overlfow">
		<table id="dataTable" ></table>
		<div id="pager"></div>
	</div>
</div>
</body>
</html>
