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
		<script language="javascript" src="js/xgutil.js"></script>
		<script language="javascript" src="js/check.js"></script>
		<script language="javascript">
		//保存报修申请
		function saveBxSq(){
			
			var mklx = $("mklx").value;
			var url = "/xgxt/zjcmGygl.do?method=gybxSq&doType=save";
			url += "&mklx="+mklx;
			if (checkSjTj("bxsj","报修时间","xwsj","希望时间")) {	
				saveUpdate(url,'xh-bxlxdh');	
			}
		}
		
		//检查希望报修时间
		function checkXwsj(){
			var xwrq = $("xwsj").value;
			var kssj = $("xwkssj").value;
			var jssj = $("xwjssj").value;
			
			if(kssj != ""){
			
				if(kssj > 24){
					alert("时间录入不能大于24，请确认！");
					$("xwkssj").value = "";
				 	$("xwkssj").focus();
					return false;
				}
				
				if(kssj.length > 0 && kssj.substring(0,1) == 0){
					$("xwkssj").value = kssj.substring(1,kssj.length);
				}
			}
			
			if(jssj != ""){
			
				if(jssj > 24){
					alert("时间录入不能大于24，请确认！");
					$("xwjssj").value = "";
				 	$("xwjssj").focus();
					return false;
				}
				
				if(jssj.length > 0 && jssj.substring(0,1) == 0){
					$("xwjssj").value = jssj.substring(1,jssj.length);
				}
			}
			
			if(kssj!="" && jssj!="" && parseInt(kssj)>parseInt(jssj) ){
				alert("开始时间不能大于结束时间，请确认！");
				$("xwkssj").value = "";
			 	$("xwjssj").value = "";
			 	$("xwkssj").focus();
				return false;
			}
		}
		</script>
	</head>

	<body onload="">
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<!-- 标题 end-->
		<html:form action="/zjcmGygl" >
		
			<!-- 隐藏域 -->
			<input type="hidden" id="tableName" name="tableName" value="view_xsjbxx"/>
			<input type="hidden" id="lx" name="lx" value="学生信息"/>
			<input type="hidden" id="zd" name="zd" value=""/>
			<input type="hidden" id="va" name="va" value=""/>
			<input type="hidden" id="url" name="url" value="/xgxt/zjcmGygl.do?method=gybxSq"/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- 隐藏域 end-->
			
			<!--学生信息 -->
			<%@ include file="info/xsInfo.jsp"%>
			<!--学生信息 end-->
			
			<!--操作按钮 -->
			<table class="formlist" border="0" align="center" style="width: 100%">
				<tfoot>
					<tr bgcolor="EEF4F9" align="center">
						<td colspan="4">
							<div class="bz">
								<font color="blue">
									注：申请后将提交相关负责人审核，如果审核不通过，请及时查看是否该次报修需要维修费用，请及时缴清。
								</font>
							</div>
							<div class="btn">
								<button id="buttonSave" 
									onclick="saveBxSq()"
									style="width: 80px">
									保	存
								</button>
								<logic:equal name="isjg" value="yes">
								&nbsp;&nbsp;
								<button class="" id="buttonSave" 
									onclick="Close();return false;" style="width: 80px">
									关	闭
								</button>
								</logic:equal>
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