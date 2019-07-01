<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：伟大的骆 -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/xszz/xszzComm.js"></script>
		<script language="javascript" src="js/xszz/xszzFunction.js"></script>
		<script language="javascript" src="js/check.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getOtherData.js'></script>
		<script language="javascript">
		function showTsDiv(id){
			if($(id)){
				$(id).style.display = "";
			}
		}
		
		function hiddTsDiv(id){
			if($(id)){
				$(id).style.display = "none";
			}
		}
		</script>
		<script language="javascript">
		//document.onmousedown=click;
		jQuery(function(){
		//	onShow();
		})

		</script> 
	</head>
	<body>
		<!-- 标题 -->
		<!-- 标题 end-->
		<html:form action="/commXszz">
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<html:hidden name="rs" property="xxdm"/>
			<html:hidden name="rs" property="xmdm"/>
			<html:hidden name="rs" property="xmb"/>
			<html:hidden name="rs" property="mrxm"/>	
			<input type="hidden" name="savedXmdm" id="savedXmdm" value="${savedXmdm }"/>		
			<input type="hidden" name="savedRskz" id="savedRskz" value="${savedRskz }"/>	
			<input type="hidden" name="savedKzjb" id="savedKzjb" value="${savedKzjb }"/>			
				
			<input type="hidden" id="xyshCheck" value="${rs.xysh }"/>
			<input type="hidden" id="xxshCheck" value="${rs.xxsh }"/>
			<input type="hidden" id="bzrshCheck" value="${rs.bzrsh }"/>
			<input type="hidden" id="fdyshCheck" value="${rs.fdysh }"/>				
			<input type="hidden" id="bzrkzCheck" value="${rs.bzrkz }"/>
			<input type="hidden" id="fdykzCheck" value="${rs.fdykz }"/>
			<input type="hidden" id="xykzCheck" value="${rs.xykz }"/>
			<input type="hidden" id="xxkzCheck" value="${rs.xxkz }"/>
			<!-- 隐藏域 end-->
	
			<div class="tab">		
			<!-- 项目基本情况 -->
			<table class="formlist" border="0" align="center" style="width: 100%">
				<thead>
					<tr>
						<th colspan="4">
							<span>项目基本情况</span>
						</th>
					</tr>
				</thead>
				<tbody>		
					<tr style="height: 23px">
						<th align="right" width="15%">
							<font color="red">*</font>项目名称：
						</th>
						<td align="left" width="35%">
							<html:text name="rs" property="xmmc" maxlength="25" style="width : 100%" onkeyup="chgPkValue(this)"/>	
						</td>
						<th align="right" width="15%">
							项目类别：
						</th>
						<td align="left" width="35%">
							<html:select name="rs" property="xmlb" style="" styleId="xmlb" onchange="">
								<html:options collection="xmlbList" property="en" labelProperty="cn" />
							</html:select>	
						</td>
					</tr>
					<tr style="height: 23px">
						<th align="right" width="">
							显示顺序：
						</th>
						<td align="left">
							<html:text name="rs" property="xssx"
								onkeydown="return onlyNum(this,5)"
								onmousedown="return onlyNum(this,5)"
								maxlength="5" 
								style="width : 100%;ime-mode:disabled"/>	
						</td>
						<td align="left" colspan="2">
							<span onmousemove="showTsDiv('sxts')" onmouseout="hiddTsDiv('sxts')">
								<font color="blue">提示：</font>
								<font color="blue" id="sxts" style="display : none">
								在项目列表中的显示位置，设置数字越小，排序越靠前。
								</font>
							</span>		
						</td>
					</tr>
					<tr style="height: 23px">
						<th align="right">
							项目说明：
							<br>
							<font color="red">(限制录入500字)</font>
						</th>
						<td colspan="3">
							<html:textarea name="rs" property="xmsm"
								rows="5" style="width : 500px;" onblur="chLeng(this,500)"></html:textarea>
						</td>
					</tr>
				</tbody>
			</table>
			<!-- 项目基本情况 end-->
			<!-- 项目级别 -->
				<%@ include file="other/fjqk.jsp"%>
			<!-- 项目级别 end-->
			<!-- 相关设置 -->
				<%@ include file="other/xgsz.jsp"%>
			<!-- 相关设置 end-->
			<!-- 条件设置 -->
				<%@ include file="other/tjsz.jsp"%>
			<!-- 条件设置 end -->
			<!-- 兼得设置 -->
				<%@ include file="other/jdsz.jsp"%>
			<!-- 兼得设置 end -->
			
			<table border="0" class="formlist" align="center" style="width: 100%">
				<tfoot>
					<tr>
						<td colspan="4">
							<div class="bz">"<span class="red">*</span>"为必填项</div>
							<div class="btn">
								<!-- 非查看 -->
								<logic:notEqual name="doType" value="view">
									<button type="button" onclick="checkZs();return false;" id="buttonSave">
										保 存
									</button>
								&nbsp;&nbsp;&nbsp;&nbsp;
								</logic:notEqual>
								<button type="button" onclick="window.close();return false;" id="buttonClose">
									关 闭
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
			</div>
			<!-- 提示信息 -->
			<logic:present name="message">
				<script defer="defer">
					if($("message") && $("message").value != ""){
					
						var savedRskz = $("savedRskz").value;
						var savedXmdm = $("savedXmdm").value;
						var savedKzjb = $("savedKzjb").value;
						
						if(savedRskz == "需要"){
							confirmInfo("该项目需要控制人数，如果不设置具体人数将会影响项目的申请，请问需要现在去设置人数吗？",goRssz);
						}else{
							alert("保存成功");
							if(opener){
								opener.document.getElementById("search_go").click();
							}else if(window.dialogArguments){
								window.dialogArguments.document.getElementById("search_go").click();
							}
							window.close();
						}
					}
					
					//显示导出成功信息
					function goRssz(tag){
						if(tag == "ok"){
							window.dialogArguments.document.getElementById("savedXmdm").value = savedXmdm;
							window.dialogArguments.document.getElementById("savedKzjb").value = savedKzjb;
							window.dialogArguments.document.getElementById("forward").click();
						}else{
							window.dialogArguments.document.getElementById("search_go").click();
						}
						window.close();
					}
				</script>
			</logic:present>
		</html:form>
	</body>
</html>