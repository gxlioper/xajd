<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：lt -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
	    <script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" defer="defer">
		
		//初始化
		function onShow(){ 
			searchRs();
		}
		
		//查询结果集
		function searchRs(){
			
			jQuery.ajaxSetup({async:false});
			
			var url = "xsxx_tygl.do?method=cxFzxsxxjg";
			var ie = "ie";
			
			var otherValue = [ie];

			$("divWaiting").style.display="";
			$("divDisable").style.display="";
			
			searchRsByAjax(url,otherValue);

			$("divWaiting").style.display="none";
			$("divDisable").style.display="none";
			
			jQuery.ajaxSetup({async:true});
		}
		
		//查看
		function showzxsxxView(){
			var len=jQuery("[name=primarykey_checkVal]:checked").length;
	
			if(len==1){	
				var pkValue=jQuery("[name=primarykey_checkVal]:checked").eq(0).val();
				var url="xsxx_tygl.do?method=ckZxsxx";
				url+="&xh="+pkValue;
				var width=850;
				showDialog("查看非在校生信息", 850, 500, url);
				//showTopWin(url,850,640);
				var height = 640;
				var left = (screen.width/2) - width/2;
				var top = (screen.height/2) - height/2;
				//var styleStr = 'toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,resizable=yes,copyhistory=yes,width='+width+',height='+height+',left='+left+',top='+top+',screenX='+left+',screenY='+top;
				//window.open(url,"msgWindow", styleStr);
			}else{	
				alertError("请<font color='blue'>勾选一条</font>您希望查看的记录！");	
				return false;
			}
		}

		//查看
		function zxsxxView(xh){
				var pkValue=xh;
				var url="xsxx_tygl.do?method=ckZxsxx";
				url+="&xh="+pkValue;
				var width=850;
				showDialog("查看非在校生信息", 850, 500, url);
		}

		
		jQuery(function(){
			onShow();
		})
		
		
		function fzxsxxExportConfig() {
			customExport("xsxx_tygl_cxfzxs.do", fzxsxxExportData,1000,500);
		}
			
		
			
		// 导出方法
		function fzxsxxExportData() {
			setSearchTj();//设置高级查询条件
			var url = "xsxx_tygl.do?method=fzxsxxExportData&dcclbh=" + "xsxx_tygl_cxfzxs.do";//dcclbh,导出功能编号
			url = addSuperSearchParams(url);//设置高级查询参数
			jQuery("form").eq(0).attr("action", url);
			jQuery("form").eq(0).submit();
		}
		</script>
	</head>
	<body  >
	
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
<!--			<p class="help">-->
<!--				<a href="#" onclick="return false;" -->
<!--					onmouseover ="showHelpDiv()"-->
<!--					onmouseout="showHelpDiv()"-->
<!--				>-->
<!--				使用帮助</a>-->
<!--			</p>-->
		</div>
		<!-- 标题 end-->
		
		<html:form action="/xsxx_tygl" method="post">
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			
			<!-- 多功能操作区 -->
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<li>
							<a href="#" onclick="showzxsxxView();return false;" id="btn_ck" class="btn_ck">
								查看
							</a>
						</li>
						<li><a href="#" class="btn_qx" onclick="fzxsxxExportConfig();return false;">导出</a></li>
						<%--<li><a href="#" class="btn_dc" onclick="configureExportData();return false;">导出数据</a></li>
					--%></ul>
				</div>
				<!-- 按钮 end-->
				
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
			
			<!-- 内容显示区开始 -->
			<div class="main_box">
				<div class="mid_box">
					<div class="title">
						<p>
							<!-- 查询得到的数据量显示区域 -->
						</p>
					</div>
				</div>
				<!-- From内容 -->
				<div id="div_rs" style="height:420px;overflow-x:hidden;overflow-y:auto;">
				</div>
				
				<!--分页显示-->
				<div style="clear:both;">
					<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=xsxxtyglForm"></jsp:include>
					 <script type="text/javascript">
				     //$('choose').className="hide";
				     </script>
				</div>
				<!--分页显示-->
			</div>
			<!-- 内容显示区开始 end-->
			
			<!-- 流程跟踪弹出层 -->
			<div id="div_lcgz" style="display:none">
				<div class="open_win01">
					sss
				</div>
			</div>
			<!-- 流程跟踪弹出层 end-->
			
			<!-- 提示信息 -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>