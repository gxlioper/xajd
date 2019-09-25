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
            caption : "辅导教师评价列表",
            pager : "pager",
            url : "xyfd_yytj.do?method=yytjList&type=query",
            colList : [
                { label : 'djh', name : 'djh', index : 'djh',key : true,hidden : true },
                { label : '登记号', name : 'djh', index : 'djh',width:'12%'},
                { label : '职工号', name : 'zgh', index : 'zgh',width:'12%'},
                { label : '辅导人员姓名', name : 'xm', index : 'xm',width:'12%'},
                { label : '辅导人员类型', name : 'fdjslx', index : 'fdjslx',width:'10%'},
                { label : '平均评分', name : 'pjpf', index : 'pjpf',width:'10%'},
                { label : '累计辅导人次', name : 'ljrc', index : 'ljrc',width:'10%'}
            ],
            radioselect:false
        }

        jQuery(function(){
            var map = getSuperSearch();
            map["cxmb"]="jspj";
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
	<input type="hidden" id="cxmb" value="jspj"/>
	<%@ include file="/comm/hiddenValue.jsp"%>
	<div class="toolbox">

		<!-- 按钮 -->
		<div class="buttonbox">
			<ul>

				<li id="li_pjtj" >
					<a href="javascript:void(0);" onclick="grxxpj();return false;"
					   title="选中您要查看的辅导记录，点击该按钮可以打开查看页面。"
					   class="btn_ck">查看辅导记录</a>
				</li>

				<li>
					<a href="javascript:void(0);" onclick="ckfd();return false;" class="btn_dc">导出</a>
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
		<span>活动补录审核列表&nbsp;&nbsp; </span>
	</h3>
	<div class="con_overlfow">
		<table id="dataTable" ></table>
		<div id="pager"></div>
	</div>
</div>
</body>
</html>
