<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid=${GypyShForm.sqid}&tt="+new Date().getTime());
			jQuery("#shjgSpan").load("comm_spl.do?method=shth&lcid=${GypyShForm.splc}&shid=${GypyShForm.shid}");
		});
		function saveSh(){
			var shzt = jQuery("#shjg").val();
			if (jQuery("#shjg").val() == "" || jQuery("#shyj").val() == ""){
				showAlert("请将必填项填写完整！");
				return false;
			}
			if(jQuery("#gxsj").length == 1 ){
				if(jQuery("#gxsj").val() == ""){
					showAlert("挂星时间不可为空！");
					return false;
				}
			}
			var url = "gypynew_gypysh.do?method=saveGypySh";
			ajaxSubFormWithFun("GypyShForm",url,function(data){
				 if(data["message"]=="保存成功！"){
		    		 showAlert(data["message"],{},{"clkFun":function(){
							if (parent.window){
								refershParent();
							}
						}});
		    	 }else{
		    		 showAlert(data["message"]);
		    		}
				});
		}
		</script>
	</head>
	<body>
		<html:form action="/gypynew_gypysh" method="post" styleId="GypyShForm">
			<html:hidden  property="sqid" styleId="sqid"/>
			<html:hidden  property="splc" styleId="splc"/>
			<div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom:0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>申请信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="15%">楼栋</th>
							<td width="35%">
								${qsxx.ldmc}
								<!-- 
								<html:select property="lddm" styleId="lddm" style="width:150px;">
								
								</html:select>	 -->							
							</td>
							<th width="15%">层号</th>
							<td width="35%">
								${qsxx.ch}层
								<!-- <html:select property="ch" styleId="ch" style="width:150px;">
									
								</html:select> -->
							</td>
						</tr>
						<tr>
							<th>寝室号</th>
							<td>
								${qsxx.qsh}
							<!-- 
								<html:select   property="qsh" styleId="qsh"  style="width:150px;">
								</html:select>-->
							</td>
							<th>申请星级</th>
							<td>
								${GypyShForm.sqxj}
							</td>
						</tr>
						<tr>
							<th>申请时间</th>
							<td>
								${GypyShForm.sqsj}
							</td>
							<th>是否为再次挂星</th>
							<td>
								${GypyShForm.sfzcgx}
							</td>
						</tr>
						<tr>
							<th>申请理由</th>
							<td colspan="3">
								${GypyShForm.sqly}
							</td>
						</tr>
					</tbody>
					<logic:notEmpty name="wjxx">
						<thead>
						<tr>
							<th colspan="4">
								<span>寝室人员违纪信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th style="text-align:center">学号</th>
							<th style="text-align:center">姓名</th>
							<th style="text-align:center">违纪类别</th>
							<th style="text-align:center">违纪时间</th>
						</tr>
						<logic:iterate name="wjxx" id="i">
							<tr>
								<td style="text-align:center">${i.xh}</td>
								<td style="text-align:center">${i.xm}</td>
								<td style="text-align:center">${i.gyjllbmc}</td>
								<td style="text-align:center">${i.wjsj}</td>
							</tr>
						</logic:iterate>
					</tbody>
					</logic:notEmpty>
					<thead>
						<tr>
							<th colspan="4">
								<span>审批信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td colspan="4" id="shlccx">
							
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>审核信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th >
								审核结果
							</th>
							<td id="shjgSpan">
								
							</td>
						</tr>
						<logic:equal value="true" name="isLast">
							<tr>
							<th>
								<span class="red">*</span>挂星时间
							</th>
							<td colspan="3" >
								<html:text property="gxsj" styleId="gxsj" maxlength="10" onclick="return showCalendar('gxsj','y-mm-dd');" style="width:150px" value="${gxsj}" />
							</td>
							</tr>
						</logic:equal>
						<tr>
							<th width="20%">
								<font color="red">*&nbsp;</font> 审核意见
								<br />
								<font color="red">(限200字)</font>
							</th>
							<td colspan="3">
								<jsp:include page="/xsgzgl/comm/shlc/cyyj.jsp?gnid=gypysh&id=shyj" />
								<textarea id="shyj" rows="5" name="shyj" style="word-break:break-all;width:100%;margin-top: 5px" onblur="checkLen(this,200);"></textarea>
							</td>
						</tr>
					</tbody>	
				</table>
				</div>	
				<div style="height:30px;"></div>
				<%--;height:520px --%>
			<div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button type="button" name="保存"  onclick="saveSh();return false;">
										保存
									</button>
									<button type="button" onclick="iFClose();">
										关闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
		</html:form>
	</body>
	
</html>