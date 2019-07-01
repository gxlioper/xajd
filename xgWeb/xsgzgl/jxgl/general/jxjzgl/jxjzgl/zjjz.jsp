<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	    <script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="js/xsgzgl/jxjzgl/jxjzgl.js"></script>
		<script language="javascript" defer="defer">
		
		//初始化
		function onShow(){ 
			searchRs();
		}
		
		//查询结果集
		function searchRs(){
			
			jQuery.ajaxSetup({async:false});
			
			var url = "general_pjsz_bjdl_ajax.do?method=searchPjszBjdl";
			var ie = "10.0";
			
			var otherValue = [ie];

			$("divWaiting").style.display="";
			$("divDisable").style.display="";
			
			searchRsByAjax(url,otherValue);

			$("divWaiting").style.display="none";
			$("divDisable").style.display="none";
			
			jQuery.ajaxSetup({async:true});
			
			if($("operation")){
				if($("operation").value=="no"){
					$("btn_sc").disabled=true;
					$("btn_ccg").disabled=true;
				}else{
					$("btn_sc").disabled=false;
					$("btn_ccg").disabled=false;
				}
			}
		}

		</script>
	</head>
	<body onload="" >
	
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
			<p class="help">
				<a href="#" onclick="return false;" onmousedown ="showHelpDiv();">使用帮助</a>
			</p>
		</div>			
		<!-- 标题 end-->
		<!-- 提示信息 START-->
		<div class="prompt" id="div_help" style="display: none">
			<h3>
				<span>提示：</span>
			</h3>
			<p>
				<span>
				1.建立军训编制
				</span>
			</p>
			<a class="close" title="隐藏"  onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- 提示信息 end-->
		
		<html:form action="/jxjzgl_cxJxjz" method="post">
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="operation" id="operation" value="${operation}"/>
			
			<!-- 多功能操作区 -->
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<li>
							<a href="#" onclick="cxJzwh();return false;" class="btn_zj">
								建制维护
							</a>
						</li>
						<li>
							<a href="#" onclick="showBjdlDetail();return false;" class="btn_ck">
								名单查看
							</a>
						</li>
						<li>
							<a href="#" onclick="showBjdlDetail();return false;" class="btn_dc">
								导出配置表
							</a>
						</li>
					</ul>
				</div>
				<!-- 按钮 end-->
				
			</div>
			<!--标题start-->
			    <h3 class="datetitle_01">
			    <span>军训名称：  &nbsp;参训人数：&nbsp;已编制：&nbsp;尚未编制：</span>
			    </h3>
			<!--标题end-->
			
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
					<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=jxjzglFrom"></jsp:include>
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