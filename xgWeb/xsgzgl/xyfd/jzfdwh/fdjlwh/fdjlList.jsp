<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="/syscommon/head.ini"%>
	<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
	<script type="text/javascript" src="js/calendar/calendar.js"></script>
	<script type="text/javascript" src="xsgzgl/xyfd/jzfdwh/fdjlwh/js/fdjl.js"></script>
	<script type="text/javascript">
        var gridSetting = {
            caption : "收到预约列表",
            pager : "pager",
            url : "xyfd_fdjl.do?method=fdjlList&type=query",
            colList : [
                { label : 'yyid', name : 'yyid', index : 'yyid',key : true,hidden : true },
                { label : '预约号', name : 'yyh', index : 'yyh',width:'12%'},
                { label : '预约学生', name : 'xh', index : 'xh',hidden:true},
                { label : '预约学生', name : 'xm', index : 'xm',width:'10%'},
                { label : '预约人', name : 'yyr', index : 'yyr',width:'10%'},
                { label : '辅导时间', name : 'fdsj', index : 'fdsj',width:'10%'},
                { label : '课程名称', name : 'kcmc', index : 'kcmc', width : '10%' },
                { label : '辅导地点', name : 'fdsdd', index : 'fdsdd', width : '15%' },
                { label : '审核状态', name : 'shztmc', index : 'shztmc', width : '10%' },
                { label : '审核状态', name : 'zt', index : 'zt', hidden : true}
            ],

            radioselect:false
        }

        var gridSetting2 = {
            caption : "收到预约列表",
            pager : "pager",
            url : "xyfd_fdjl.do?method=fdjlList&type=query",
            colList : [
                { label : 'yyid', name : 'yyid', index : 'yyid',key : true,hidden : true },
                { label : '预约号', name : 'yyh', index : 'yyh',width:'12%'},
                { label : '预约学生', name : 'xh', index : 'xh',hidden:true},
                { label : '预约学生', name : 'xm', index : 'xm',width:'10%'},
                { label : '预约人', name : 'yyr', index : 'yyr',width:'10%'},
                { label : '辅导时间', name : 'fdsj', index : 'fdsj',width:'10%'},
                { label : '课程名称', name : 'kcmc', index : 'kcmc', width : '10%' },
                { label : '辅导地点', name : 'fdsdd', index : 'fdsdd', width : '15%' },
                { label : '审核状态', name : 'shztmc', index : 'shztmc', width : '10%' },
                { label : '审核状态', name : 'zt', index : 'zt', hidden : true}
            ],

            radioselect:false
        }

        jQuery(function(){
            var map = getSuperSearch();
            map["yyzt"]="dfd";
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
<html:form action="/xyfd_fdjl">
	<input type="hidden" id="yyzt" value="dfd"/>
	<%@ include file="/comm/hiddenValue.jsp"%>
	<div class="toolbox">
		<!-- 按钮 -->
		<div class="buttonbox">
			<ul>
				<logic:equal name="writeAble" value="yes">
					<logic:equal name="isJsOrPb" value="true">
						<li id="li_tj">
							<a href="javascript:void(0);" onclick="qrfd();return false;"
							   title="选中您要添加辅导记录的记录，点击该按钮可以打开记录页面。"
							   class="btn_sh">添加辅导记录</a>
						</li>
						<li id="li_ck" style="display: none">
							<a href="javascript:void(0);" onclick="ckfd();return false;"
							   title="选中您要查看的辅导记录，点击该按钮可以打开查看页面。"
							   class="btn_ck">查看辅导记录</a>
						</li>
					</logic:equal>
				</logic:equal>

			</ul>
		</div>
		<!-- 过滤条件 -->
		<%@ include file="/xsgzgl/xyfd/jzppwh/fdyywh/superSearchAreaforFdyy.jsp"%>
		<!-- 过滤条件 end-->
		<div class="comp_title" id="comp_title">
			<ul style="width:90%">
				<li class="ha"><a href="javascript:void(0);" onclick="selectTab(this,'dfd');"><span>待辅导</span></a></li>
				<li><a href="javascript:void(0);" onclick="selectTab(this,'yfd');"><span>已辅导</span></a></li>
			</ul>
		</div>
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
