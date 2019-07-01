<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript"
			src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsxx/fbgl/bbgl/js/fbglbbgl.js"></script>
		<script type="text/javascript" src="xsxx/fbgl/gzsd/gzyl.js"></script>
		<script type="text/javascript">
		 var gridSetting = {
					caption:"查询结果",
					pager:"pager",
					url:"fbglbbgl.do?method=list&type=query",
					colList:[
					   {label:'pk',name:'pk', index: 'pk',key:true,hidden:true},
					   {label:'年级',name:'nj', index: 'nj'},
					   {label:'<bean:message key="lable.xb" />',name:'xy', index: 'xy'},
					   {label:'专业',name:'zymc', index: 'zymc'},
					   {label:'层次',name:'pyccmc', index: 'pyccmc'},
					   {label:'学制',name:'xz', index: 'xz'},
					   {label:'人数',name:'rs', index: 'rs'},
					   {label:'男生/女生',name:'xbsl', index: 'xbsl'}
					]
		} 
		 var gridSetting2 = {
					caption:"查询结果",
					pager:"pager",
					url:"fbglbbgl.do?method=list&type=query&&bbzt=ybb",
					colList:[
					   {label:'pk',name:'pk', index: 'pk',hidden:true},
					   {label:'班级代码',name:'bjdm', index: 'bjdm' ,key:true,hidden:true},
					   {label:'年级',name:'nj', index: 'nj'},
					   {label:'<bean:message key="lable.xb" />',name:'xy', index: 'xy'},
					   {label:'专业',name:'zymc', index: 'zymc'},
					   {label:'层次',name:'pyccmc', index: 'pyccmc'},
					   {label:'学制',name:'xz', index: 'xz'},
					   {label:'人数',name:'rs', index: 'rs'},
					   {label:'班级代码',name:'bjdm', index: 'bjdm'},
					   {label:'班级',name:'bjmc', index: 'bjmc'},
					   {label:'班级人数上限',name:'bjrssx', index: 'bjrssx'},
					   {label:'学生数',name:'xss', index: 'xss'},
					   {label:'编班规则',name:'pzgzid', index: 'pzgzid',hidden:true},
					   {label:'编班规则',name:'pzgzmc', index: 'pzgzmc',formatter:ckgz}
					]
		} 
			jQuery(function($) {
				$("#tzbb").hide();
				$("#tsxx").hide();
				$("#scbj").hide();
				$("#qxbb").hide();
				var map = getSuperSearch();
				map["bbzt"] = jQuery("#bbzt").val();
				gridSetting["params"] = map;
				$("#dataTable").initGrid(gridSetting);
				//是否有对应编班信息
				if (isHaveGzxx("BBGZ")) {
					$("#tsxx").hide();
				}else{
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
		<div style="" id="div_help" class="prompt">
			<h3>
				<span>提示：</span>
			</h3>
			<p>
				编班前请先设定并启用编班规则。
			</p>
		</div>
		<html:form action="/fbglbbgl.do?method=list&type=query">
			<input type="hidden" value="wbb" id="bbzt"/>
			<input type="hidden" id="xbmc" value="<bean:message key="lable.xb" />"/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- 按钮 -->
				<logic:equal name="writeAble" value="yes">
				<div class="buttonbox">
					<ul>
						<li id="bb">
							<a href="javascript:void(0);" onclick="bb();return false;"
								class="btn_xg">编班</a>
						</li>
						<li id="tzbb">
							<a href="javascript:void(0);" onclick="tzbj();return false;"
								class="btn_xg">调整编班</a>
						</li>
						<li id="qxbb">
							<a href="javascript:void(0);" onclick="scbj();return false;"
								class="btn_xg">取消编班</a>
						</li>
					</ul>
				</div>
				</logic:equal>
			<!-- 过滤条件 -->
				<%@ include file="/comm/search/superSearchArea.jsp"%>
			<!-- 过滤条件 end-->
			<div class="comp_title" id="comp_title">
			      <ul style="width:90%;">
			        <li class="ha"><a href="javascript:void(0);" onclick="selectTab(this,'wbb');"><span>未编班</span></a></li>
			        <li><a id="ybb" href="javascript:void(0);" onclick="selectTab(this,'ybb');"><span>已编班</span></a></li>
				    <span id="tsxx" style="text-align: right;color: red;margin-left: 400px;font-size: 12px;display: none;">编班规则未设定或未启用！</span>
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
			<div id="pager" ></div>
		</div>
	</body>
</html>
