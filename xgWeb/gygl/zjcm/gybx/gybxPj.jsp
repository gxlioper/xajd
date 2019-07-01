<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：伟大的骆 -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/gygl/gyglTyFunction.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getOtherData.js'></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/pclUtil.js"></script>
		<script language="javascript" src="js/gygl/zjcm/gybx.js"></script>
		<script language="javascript">
		function setBxReadOnly(){
			$("sfsf").disabled = true;
			$("wxry").disabled = true;
			$("clfy").disabled = true;
			$("wxfy").disabled = true;
			$("sfwg").disabled = true;
			$("wgsj").disabled = true;
			$("wgsjh").disabled = true;
			$("wgsjm").disabled = true;
			$("cljg").disabled = true;
		}
		
		function saveBxPj(){
		
			var fwtd = $("fwtd").value;
			var fwzl = $("fwzl").value;
			var sfjs = $("sfjs").value;
			var xcql = $("xcql").value;
			
			if(fwtd != "" && fwzl != "" && sfjs != "" && xcql != ""){
				saveUpdate('/xgxt/zjcmGygl.do?method=gybxPj&doType=save','');
			}else{
				alert("请对打'*'的项目打分！");
			}
		
		}
		</script>
	</head>

	<body onload="setGybxclInfo();setBxReadOnly();">
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<!-- 标题 end-->
		<html:form action="/zjcmGygl" >
		
			<!-- 隐藏域 -->
			<html:select name="rs" property="cl" style="display : none" styleId="cl" onchange="">
				<html:options collection="cllxList" property="dm" labelProperty="mc" />
			</html:select>
			<%@ include file="/gygl/hiddenValue.jsp"%>
			<!-- 隐藏域 end-->
			
			<!--学生信息 -->
			<%@ include file="info/xsInfo.jsp"%>
			<!--学生信息 end-->
			
			<!--报修信息 -->
			<%@ include file="info/bxInfo.jsp"%>
			<!--报修信息 end-->
	
			<!--用户评价 -->
			<%@ include file="info/pjInfo.jsp"%>
			<!--用户评价 end-->
			
			<br>
			<!-- 提示信息 -->
			<logic:notEqual name="rs" property="shzt" value="通过">
				<div>
					<font color="red">注：此报修还未审核通过，无法进行评价。</font>
				</div>
			</logic:notEqual>
			<!-- 提示信息 end-->
			
			<!--操作按钮 -->
			<table class="formlist" border="0" align="center" style="width: 100%">
				<tfoot>
					<tr bgcolor="EEF4F9" align="center">
						<td colspan="4">
							<div class="btn">
								<logic:equal name="rs" property="shzt" value="通过">
									<button id="buttonSave" 
										onclick="saveBxPj()"
										style="width: 80px">
										确	定
									</button>
								</logic:equal>
								&nbsp;&nbsp;
								<button class="" id="buttonSave" onclick="Close();return false;" style="width: 80px">
									关	闭
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
			<!--操作按钮 end-->

		<!-- 提示信息 -->
		<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>