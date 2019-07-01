<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
	<script type="text/javascript">	
	function saveSh(){
<%--		if (jQuery("#gs").val() == "" || jQuery("#gs").val() == null || jQuery("#gsshzt").val() == "" || jQuery("#gsshzt").val() == null || jQuery("#gsshyj").val().trim() == "" || jQuery("#gsshyj").val().trim() == null){--%>
<%--			showAlert("请将必填项填写完整！");--%>
<%--			return false;--%>
<%--		}--%>
		if (jQuery("#gsshzt").val() == "" || jQuery("#gsshzt").val() == null || jQuery("#gsshyj").val().trim() == "" || jQuery("#gsshyj").val().trim() == null){
			showAlert("请将必填项填写完整！");
			return false;
		}
		var url = "zyhdry.do?method=BcDgsh";
		ajaxSubFormWithFun("qnzyryForm",url,function(data){
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

<%--	function xz(obj){--%>
<%--		if(obj.value == '1'){--%>
<%--			jQuery("#jg").css("display","");--%>
<%--		}else{--%>
<%--			jQuery("#jg").css("display","none");--%>
<%--		}--%>
<%--	}	--%>
	</script>
</head>
<body>
	<html:form action="/zyhdry" method="post" styleId="qnzyryForm">
		<input type="hidden" name="id" value="${data.id}"/>
		<input type="hidden" name="jbfwgs" value="${data.jbfwgs}" />
		<div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom: 0px;" >
				<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>学生基本信息</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/comm/bdpz/viewStudent.jsp" %>
				 </table>
		
			<table width="100%" border="0" class="formlist">
			<thead>
					<tr>
						<th colspan="4">
							<span>活动信息</span>
						</th>
					</tr>
			</thead>				
			<tbody>
				<tr>
					<th style="width:16%">
						负责人
					</th>
					<td style="width:34%">
						${data.fzrxm}
					</td>
					<th style="width:16%">
						组织部门
					</th>
					<td style="width:34%">
						${data.zzbm}
					</td>
				</tr>
				<tr>
					<th>
						活动开始时间
					</th>
					<td>
						${data.hdkssj}
					</td>
					<th>
						活动结束时间
					</th>
					<td>
						${data.hdjssj}
					</td>
				</tr>
				<tr>
					<th>
						报名截止时间
					</th>
					<td>
						${data.bmjzsj}
					</td>
					<th>
						活动地点
					</th>
					<td colspan="3">
						${data.hddd}
					</td>
				</tr>
				<tr>
					<th>
						基本服务工时
					</th>
					<td colspan="3">
						${data.jbfwgs}
					</td>
				</tr>
				<tr>
					<th>
						开放学院
					</th>
					<td colspan="3">
						${data.xymc}
					</td>
				</tr>
				<tr>
					<th>
						海报
					</th>
					<td colspan="3">
						<logic:notEmpty name="data" property="fjpath"> 
							<img src="${data.fjpath}" width="170px" height="130px"/>
						</logic:notEmpty>
						<logic:empty name="data" property="fjpath">
							<img src="default_dekt.jpg" width="170px" height="130px"/>
						</logic:empty>
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
						<font color="red">*&nbsp;</font>审核结果
					</th>
					<td colspan="3">
						<select id="gsshzt" name="gsshzt">
<%--							<option value="">---请选择---</option>--%>
							<option value="1">通过</option>
<%--							<option value="2">退回</option>--%>
						</select>
					</td>
				</tr>
			</tbody>
			<tbody>							
					<tr>
						<th>
							<font color="red">*&nbsp;</font>服务结果
						</th>
						<td colspan="3">
							<select name="fwjg" id="fwjg" style="width: 100px;">
								<option value="满意_1">
									满意
								</option>
								<option value="基本满意_0.75">
									基本满意
								</option>
								<option value="一般满意_0.5">
									一般满意
								</option>
								<option value="不满意_0">
									不满意
								</option>
							</select>
						</td>
					</tr>				
			</tbody>
			<tbody>
				<tr>
					<th>
						<font color="red">*&nbsp;</font> 审核意见
						<br />
						<font color="red">(限200字)</font>
					</th>
					<td colspan="3">
						<textarea id="gsshyj" rows="5" name="gsshyj" style="word-break:break-all;width:100%;margin-top: 5px" onblur="checkLen(this,200);"></textarea>
					</td>
				</tr>
			</tbody>
			</table>
		</div>
		<div style="height: 30px"></div>
		<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">	
			<tfoot>
					<tr>
						<td colspan="4">
							<div class="btn">
								<button type="button" name="保存"  onclick="saveSh();return false;">
									保 存
								</button>
								<button type="button" name="关闭" id="buttonClose" onclick="Close();return false;">
									关 闭
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
		</table>
		<!-- 提示信息 -->
		<%@ include file="/comm/other/tsxxNew.jsp"%>
		</html:form>
</body>
</html>
