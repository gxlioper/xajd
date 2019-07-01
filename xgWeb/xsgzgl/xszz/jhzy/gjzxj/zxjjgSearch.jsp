<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：伟大的骆 -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
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
			
			var url = "jhzy_gjzxj.do?method=searchZxjjg";
			var ie = "ie";
			
			var otherValue = [ie];

			$("divWaiting").style.display="";
			$("divDisable").style.display="";
			
			searchRsByAjax(url,otherValue);

			$("divWaiting").style.display="none";
			$("divDisable").style.display="none";
			
			jQuery.ajaxSetup({async:true});
		}
		
		//详细
		function showZxjsqDetail(){
			var len=jQuery("[name=primarykey_checkVal]:checked").length;
	
			if(len==1){	
				var pkValue=jQuery("[name=primarykey_checkVal]:checked").eq(0).val();
				var url="jhzy_gjzxj.do?method=zxjjgDetail";
					url+="&pkValue="+pkValue;
					showTopWin(url,800,600);
			}else{	
				alertError("请<font color='blue'>勾选一条</font>您希望查看的记录！");	
				return false;
			}
		}
		
		function showPrint(){
		
			var n=jQuery("[name=primarykey_checkVal]:checked").length;
			
			if(n==1){
				var pk=jQuery("[name=primarykey_checkVal]:checked").eq(0).val();

				var pkArr=pk.split("luojw");
				var xn=pkArr[0];
				var xh=pkArr[1];
				showOpenWindow("jhzy_gjzxj.do?method=gjzxjb&xh="+xh+"&xn="+xn);
			}else{
				
				alertInfo("请勾选一条需要打印的记录！");
				return false;
			}
		}
		
		jQuery(function(){
			onShow();
		})
		</script>
	</head>
	<body  >
	
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
			<p class="help">
				<a href="#" onclick="return false;" 
					onmouseover ="showHelpDiv()"
					onmouseout="showHelpDiv()"
				>
				使用帮助</a>
			</p>
		</div>
		<!-- 标题 end-->
		
		<html:form action="/general_pjpy" method="post">
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<!-- 多功能操作区 -->
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<!-- 读写权 -->
						<logic:equal name="writeAble" value="yes">
							<li>
								<a href="#" onclick="showZxjsqDetail();return false;" id="btn_shtg" class="btn_shtg">
									查看
								</a>
							</li>
							<li><a href="#" class="btn_qx" onclick="choiceFields();return false;">导出设置</a></li>
							<li><a href="#" class="btn_dc" onclick="configureExportData();return false;">导出数据</a></li>
							<li><a href="#" class="btn_dc" onclick="showPrint();return false;">导出数据</a></li>
							<li>
								<a href="#" class="btn_dy" onclick="showPrint();return false;" onclick="return false;">打印报表</a>
							</li>
						</logic:equal>
					</ul>
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
				<div id="div_rs" style="height:380px;overflow-x:auto;overflow-y:auto;">
				</div>
				
				<!--分页显示-->
				<div style="clear:both;">
					<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=jhzyXszzGjzxjForm"></jsp:include>
					 <script type="text/javascript">
				     //$('choose').className="hide";
				     </script>
				</div>
				<!--分页显示-->
			</div>
			<!-- 内容显示区开始 end-->
			
			<!-- 提示信息 -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>