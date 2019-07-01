<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript"
			src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsxx/fbgl/fbgl/js/fb.js"></script>
		<script type="text/javascript" src="xsxx/fbgl/gzsd/gzyl.js"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript">
		var gridSetting = {
				caption:"查询结果",
				pager:"pager",
				url:"fbglfbbase.do?method=list&type=query",
				colList:[
				   {label:'pk',name:'pk', index: 'pk',key:true,hidden:true},
				   {label:'年级',name:'nj', index: 'nj'},
				   {label:'<bean:message key="lable.xb" />',name:'bmmc', index: 'bmmc'},
				   {label:'专业',name:'zymc', index: 'zymc'},
				   {label:'层次',name:'pyccmc', index: 'pyccmc'},
				   {label:'学制',name:'xz', index: 'xz'},
				   {label:'人数',name:'rs', index: 'rs'},
				   {label:'男生/女生',name:'nvqk', index: 'nvqk'},
				   {label:'未分班/已分班',name:'fbqk', index: 'fbqk'}
				]
	} 
	 var gridSetting2 = {
				caption:"查询结果",
				pager:"pager",
				url:"fbgl.do?method=yfblist&type=query",
				colList:[
				   {label:'pk',name:'pk', index: 'pk',key:true,hidden:true},
				   {label:'班级代码',name:'bjdm', index: 'bjdm',hidden:true},
				   {label:'考生号',name:'ksh', index: 'ksh'},
				   {label:'姓名',name:'xm', index: 'xm'},
				   {label:'性别',name:'xb', index: 'xb'},
				   {label:'年级',name:'nj', index: 'nj'},
				   {label:'<bean:message key="lable.xb" />',name:'bmmc', index: 'bmmc'},
				   {label:'专业',name:'zymc', index: 'zymc'},
				   {label:'专业',name:'zydm', index: 'zydm',hidden:true},
				   {label:'层次',name:'pyccmc', index: 'pyccmc'},
				   {label:'学制',name:'xz', index: 'xz'},
				   {label:'所在班级',name:'bjmc', index: 'bjmc'},
				   {label:'投档成绩',name:'tdcj', index: 'tdcj'},
				   {label:'生源地',name:'sydmc', index: 'sydmc'},
				   {label:'分班规则id',name:'fbgz', index: 'fbgz',hidden:true},
				   {label:'分班规则',name:'fbgzmc', index: 'fbgzmc',formatter:ckgz}
				]
	} 
			jQuery(function($) {
				$("#tsxx").hide();
				$("#tzfb").hide();
				var map = getSuperSearch();
				map["fbzt"] = $("#fbzt").val();
				gridSetting["params"] = map;
				$("#dataTable").initGrid(gridSetting);
				//是否有对应分班规则
				if (!isHaveGzxx("FBGZ")) {
					$("#tsxx").show();
				}
			});
		</script>
	</head>
	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/fbglbbgl.do?method=list&type=query" styleId="myform">
			<input type="hidden" value="${fbzt}" id="fbzt"/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- 按钮 -->
				<logic:equal name="writeAble" value="yes">
				<div class="buttonbox">
					<ul>
						<li id="fb">
							<a href="javascript:void(0);" onclick="fb();return false;"
								class="btn_xg">分班</a>
						</li>
						<li id="tzfb">
							<a href="javascript:void(0);" onclick="tzbj();return false;"
								class="btn_xg">调整班级</a>
						</li>
						<li id="scfb">
							<a href="javascript:void(0);" onclick="qxfb();return false;"
								class="btn_sc">取消分班</a>
						</li>
					</ul>
				</div>
				</logic:equal>
			<!-- 过滤条件 -->
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			<div class="comp_title" id="comp_title">
			      <ul style="width:90%">
			        <li class="ha"><a href="javascript:void(0);" onclick="selectTab(this,'wfb');"><span>自动分班</span></a></li>
			        <li><a href="javascript:void(0);" onclick="selectTab(this,'yfb');"><span>分班结果</span></a></li>
			        <span id="tsxx" style="text-align: right;color: red;margin-left: 300px;font-size: 12px;display: none;">分班规则未设定或未启用！</span>
			      </ul>
			</div>
			</div>
		</html:form>
		<div class="toolbox">
			<!--标题start-->
			<h3 class="datetitle_01">
				<span style="width: 50%">查询结果 <a id="title"
					href="javascript:;"
					style="float: right; margin-right: 30px; color: blue"></a> </span>
			</h3>
			<table id="dataTable"></table>
			<div id="pager"></div>
		</div>
	</body>
</html>
