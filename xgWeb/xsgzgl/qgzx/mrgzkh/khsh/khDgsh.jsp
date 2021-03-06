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
	jQuery(function(){
		jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid=${model.sqid}&tt="+new Date().getTime());
		jQuery("#shjgSpan").load("comm_spl.do?method=shth&lcid=${model.splc}&shid=${model.shid}");
	});
	function saveSh(){
		var shzt = jQuery("#shjg").val();
		/*if("0" == jQuery("#yxgs").val()) {
			showAlert("有效工时不能为零！");
			return false;
		}*/
		if (jQuery("#shzt").val() == "" || jQuery("#shyj").val() == ""){
			showAlert("请将必填项填写完整！");
			return false;
		}
		var url = "mrgzkhKhsh.do?method=khDgsh&type=save";
		ajaxSubFormWithFun("GzkhKhshForm",url,function(data){
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
	<html:form action="/mrgzkhKhsh" method="post" styleId="GzkhKhshForm">
		<html:hidden name="model" property="sqid" styleId="sqid"/>
		<html:hidden name="model" property="xh" styleId="xh"/>		
		<html:hidden name="model" property="splc" styleId="splc"/>
		<html:hidden name="model" property="sqsj" styleId="sqsj"/>
		<html:hidden name="model" property="shid" styleId="shid"/>
		<html:hidden name="model" property="cjbz" styleId="cjbz"/>
		<html:hidden name="model" property="gzrq" styleId="gzrq"/>
		<html:hidden name="model" property="xn" styleId="xn"/>
		<html:hidden name="model" property="gs" styleId="gs" value="${rs.yxgs}"/>
		<html:hidden name="model" property="gwdm" styleId="gwdm"/>
		<div style="overflow-x:hidden;overflow-y:auto;margin-bottom: 0px;" >
				<table width="100%" border="0" class="formlist">			
			<thead>
				<tr>
					<th colspan="4">
						<span>学生信息</span>
					</th>
				</tr>
			</thead>
			<%@ include file="/xsgzgl/comm/bdpz/viewStudent.jsp" %>
			<thead>
				<tr>
					<th colspan="4">
						<span>填写信息</span>
					</th>
				</tr>
			</thead>
			<tbody>
			<tr>
				<th>用人单位</th>
				<td>
						${rs.yrdwmc}
				<th>学年</th>
				<td>
						${rs.xn}
				</td>
			</tr>
			<tr>
				<th>岗位</th>
				<td>
						${rs.gwmc}
				</td>
				<th>工作日期</th>
				<td>
						${rs.gzrq}
				</td>
			</tr>
			<tr>
				<th>工时</th>
				<td>
						${rs.gs}（小时）
				</td>
				<th>工作时间段</th>
				<td>
						${rs.gzkssj}到${rs.gzjssj}
				</td>
			</tr>
					</tbody>
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
					<tr>
						<th >
							审核结果
						</th>
						<td id="shjgSpan" colspan="3">
							
						</td>
					</tr>
			</tr>
			<tr>
				<th width="20%">
					<font color="red">*&nbsp;</font> 审核意见
					<br />
					<font color="red">(限200字)</font>
				</th>
				<td colspan="3">
					<jsp:include page="/xsgzgl/comm/shlc/cyyj.jsp?gnid=mrgzkh&id=shyj" />
					<textarea id="shyj" rows="5" name="shyj" style="word-break:break-all;width:100%;margin-top: 5px" onblur="checkLen(this,200);"></textarea>
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
