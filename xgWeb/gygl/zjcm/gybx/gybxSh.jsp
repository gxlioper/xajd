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
		<script language="javascript" src="js/xgutil.js"></script>
		<script language="javascript" src="js/pclUtil.js"></script>
		<script language="javascript" src="js/gygl/zjcm/gybx.js"></script>
		<script language="javascript">
		//检查提交时间
		function checkTjsj(){
			var wgsjm = $("wgsjm").value;//分
			var wgsjh = $("wgsjh").value;//时
			
			if(wgsjh != ""){
			
				if(wgsjh > 24){
					alert("小时录入不能大于24，请确认！");
					$("wgsjh").value = "";
				 	$("wgsjh").focus();
					return false;
				}
				
				if(wgsjh.length > 0 && wgsjh.substring(0,1) == 0){
					$("wgsjh").value = wgsjh.substring(1,wgsjh.length);
				}
			}
			
			if(wgsjm != ""){
			
				if(wgsjm > 59){
					alert("分钟录入不能大于59，请确认！");
					$("wgsjm").value = "";
				 	$("wgsjm").focus();
					return false;
				}
				
				if(wgsjm.length > 0 && wgsjm.substring(0,1) == 0){
					$("wgsjm").value = wgsjm.substring(1,wgsjm.length);
				}
			}
		}
	</script>
	</head>

	<body onload="setGybxclInfo()">
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<!-- 标题 end-->
		<html:form action="/zjcmGygl" >
			<!-- 隐藏域 -->
			<html:hidden name="rs" property="shzt" styleId="shzt"/>
			<html:select name="rs" property="cl" style="display : none" styleId="cl" onchange="">
				<html:options collection="cllxList" property="dm" labelProperty="mc" />
			</html:select>
			<%@ include file="/gygl/hiddenValue.jsp"%>
			<!-- 隐藏域 end-->

			<!-- 学生信息 -->
			<%@ include file="info/xsInfo.jsp"%>
			<!-- 学生信息 end-->
			
			<!-- 报修信息 -->
			<%@ include file="info/bxInfo.jsp"%>
			<!-- 报修信息 end-->
			
			<!--操作按钮 -->
			<table class="formlist" border="0" align="center" style="width: 100%">
				<tfoot>
					<tr bgcolor="EEF4F9" align="center">
						<td colspan="4">
						<div class="bz">					
							<font color="blue">
								<!-- 提示信息 -->
								<logic:equal name="doType" value="update">
									<logic:notEqual name="rs" property="shzt" value="未审核">
										<div>
											<font color="red">注：已经审核过该次报修，不可再修改。</font>
										</div>
									</logic:notEqual>
								</logic:equal>
								<!-- 提示信息 end -->
							</font>
						</div>
							<div class="btn">
								<logic:equal name="doType" value="sh">
									<button class="" id="buttonSave" onclick="saveBxSh('tg')" style="width: 80px">
										通	过
									</button>
									&nbsp;&nbsp;
									<button class="" id="buttonSave" onclick="saveBxSh('btg')" style="width: 80px">
										不通过
									</button>
									&nbsp;&nbsp;
								</logic:equal>
								<logic:equal name="doType" value="update">
									<logic:equal name="rs" property="shzt" value="未审核">
										<button class="" id="buttonSave" onclick="saveBxEdit()" style="width: 80px">
											保	存
										</button>
										&nbsp;&nbsp;
									</logic:equal>
								</logic:equal>
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
	