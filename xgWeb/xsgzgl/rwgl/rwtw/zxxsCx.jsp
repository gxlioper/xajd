<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：yyp -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>		
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" defer="defer">
		//初始化
		jQuery(document).ready(function(){ 
			searchRs();
		});

		
		function setDivHeight(){
			if($("table_rs")){
				jQuery("#div_rs").height(jQuery("#table_rs").height()+20);	
			}
		}

		function searchRs(){
			var url = "rwgl_rwtwgl_ajax.do?method=zxxsCx";
			var ie = "ie";
			var otherValue = [ie];
			searchRsByAjax(url,otherValue);
		    setTimeout("setDivHeight()","1000")
		}

		function sendXx(){
			var xh = curr_row.getElementsByTagName('input')[0].value;
			var gotoPath = jQuery("#gotoPath").val()+"";
			if (gotoPath.split("?").length > 1){
				gotoPath = gotoPath+"&xh="+xh;
			} else {
				gotoPath = gotoPath+"?xh="+xh;
			}
			var api = frameElement.api;
			if (api){
				if (api.get('childDialog')){
					api.reload(api.get('parentDialog') ,gotoPath);
				} else {
					var W = api.opener;
					W.location=gotoPath;			
				}
			} else if (parent.window){
				parent.window.document.location=gotoPath;						
			}
			
			iFClose();
			
			//window.dialogArguments.document.getElementById("xh").value=xh;
			//window.dialogArguments.document.getElementById("getStuInfo").onclick();
			//window.close();
		}
		</script>
	</head>
	<body>
		<html:form action="/rwgl_rwtwgl" method="post">
		<input type="hidden" value="${gotoPath}" id="gotoPath"/>
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="xmdm" id="xmdm" value="${xmdm }" />
			<!-- 隐藏域 -->
			<!-- 多功能操作区 -->
			<div class="toolbox" id="dgncz">
				<!-- 过滤条件 -->
				<%@ include file="/comm/search/superSearchArea.jsp"%>
			</div>
			<!-- 多功能操作区 end-->

			<!-- 内容显示区开始 -->
			<div class="main_box">
				<div class="mid_box">
					<div class="title">
						<p>
							<!-- 查询得到的数据量显示区域 -->
						</p>
					</div>
				</div>
				<h3 class="datetitle_01">
					<span> 查询结果&nbsp;&nbsp;<font color="blue">双击选择学生信息</font> </span>
				</h3>
				<div id="div_rs"
					style="width:100%;overflow-x:auto;overflow-y:hidden;">
				</div>

				<!--分页显示-->
				<jsp:include flush="true"
					page="/sjcz/turnpage.jsp?form=rwglRwtwForm"></jsp:include>
			</div>
			<div id="div_detail" style="display:none">
			</div>		
			<!-- 提示信息 -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>