<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	</head>
	<script language="javascript" src="js/check.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/jsFunction.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
	<script type="text/javascript" src="js/jxglFunction.js"></script>
	<script type='text/javascript'
		src='/xgxt/dwr/interface/getXmlgSzdwDAO.js'></script>
	<script>
	function Save(){
		jQuery.ajaxSetup({async:false});
		// 得到JSON对象
	    var parameter ={};
	    parameter["lx"]=escape(jQuery("input[type=radio][name=lx]:checked").val());
		var url = "jxgl_cssz_ajax.do?method=save";
        $("divWaiting").style.display="";
		$("divDisable").style.display="";
		jQuery.post(url,parameter,
			function(result){
				$("divWaiting").style.display="none";
				$("divDisable").style.display="none";
				alertInfo(result);
			}
		);
		jQuery.ajaxSetup({async:true});
	}
	</script>
	<body>
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a><bean:write name="title" /> </a>
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
				军训编制控制主要是为了控制军训编制与军训名单生成的先后执行顺序，默认为军训编制环节
			</p>
			<a class="close" title="隐藏"
			   onclick="this.parentNode.style.display='none'"></a>
		</div>
		<!-- 提示信息 end-->	
		<html:form action="/jxgl_cssz" method="post">
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- 隐藏域 end-->
			<div class="tab">
				<table class="formlist" border="0" align="center"
					style="width: 100%">
					<thead>
						<tr>
							<th colspan="2">
								<span>参数设置</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td align="right" style="width: 35%">
								<font class="red">*</font>军训编制阶段
							</td>
							<td align="left" style="width: 60%">
								<html:radio name="rs" property="lx" styleId="lx" value="jxbz" >军训编制</html:radio>
								<html:radio name="rs" property="lx" styleId="lx" value="mdsc" >军训名单生成</html:radio>
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td align="center" colspan="2">							
			        			<div class="bz">"<span class="red">*</span>"为必填项</div>
								<div class="btn">
									<button type="button" onclick="Save();return false;" id="buttonSave">
										保存
									</button>								
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
			<!-- 提示信息 -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>