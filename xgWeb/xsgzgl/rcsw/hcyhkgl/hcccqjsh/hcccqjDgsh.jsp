<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="xsgzgl/xszz/bdpz/js/bdpz.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		
	<script type="text/javascript">
	

	jQuery(function(){
		jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid=${model.ccqjtxid}&tt="+new Date().getTime());
		jQuery("#shjgSpan").load("comm_spl.do?method=shth&lcid=${model.splc}&shid=${model.shid}");
	});
	function saveShzt(){
		
		var shzt = jQuery("#shjg").val();
		if(jQuery("#shjg").val() == "0"){
			showAlertDivLayer("请选择审核状态！");
			return false;
		}
		var shyj = jQuery("#shyj").val();
		if (jQuery.trim(shyj) == ""){
			showAlertDivLayer("请填写审核意见！");
			return false;
		}
		var message;
		if(jQuery("#shjg").val() == "1"){
			message = "通过";
		}
		if(jQuery("#shjg").val() == "2"){
			message = "不通过";
		}
		if(jQuery("#shjg").val() == "3"){
			message = "退回";
		}
		
		showConfirmDivLayer("您确定" + message + "该申请吗？",{"okFun":function(){
			var url = "rcsw_hcyhk_hcccqjshgl.do?method=hcccqjDgsh&type=save";
			ajaxSubFormWithFun("hcccqjshForm",url,function(data){
				showAlertDivLayer(data["message"],{},{"clkFun":function(){
					if (parent.window){
						refershParent();
					}
				}});
			});
		}});
		
	}
	
	</script>
</head>
<body>
	<html:form action="/rcsw_hcyhk_hcccqjshgl" method="post" styleId="hcccqjshForm">
			
		<html:hidden name="model" property="ccqjtxid" styleId="ccqjtxid"/>
		<html:hidden name="model" property="xh" styleId="xh"/>		
		<html:hidden name="model" property="splc" styleId="splc"/>
		<html:hidden name="model" property="shid" styleId="shid"/>
		
		<html:hidden name="model" property="txsj" styleId="txsj"/>		
		<html:hidden name="model" property="xn" styleId="xn"/>
		<html:hidden name="model" property="xq" styleId="xq"/>
		<html:hidden name="model" property="ccqdz" styleId="ccqdz"/>		
		<html:hidden name="model" property="cczdz" styleId="cczdz"/>
		<html:hidden name="model" property="bz" styleId="bz"/>
			
		<div style="height:460px;overflow-x:hidden;overflow-y:auto;">
			
		<table class="formlist" width="95%">
			<thead>
				<tr>
					<th colspan="4">
						<span>学生信息</span>
					</th>
				</tr>
			</thead>
			<%@ include file="/xsgzgl/rcsw/rcxwwh/comm/viewStudent.jsp" %>
			</table>
			<table width="100%" class="formlist">
			<thead>
				<tr>
					<th colspan="4">
						<span>火车乘车区间信息</span>
					</th>
				</tr>
			</thead>
			<tbody>
			<tr>
				<th align="right" width="20%">
					学年
				</th>
				<td align="left" width="30%">
					${rs.xn}
				</td>
				<th align="right" width="20%">
					学期
				</th>
				<td align="left" width="30%">
					${rs.xqmc}
				</td>
			</tr>
			<tr>
				<th align="right" width="20%">
					乘车区间
				</th>
				<td align="left" width="30%" colspan="3">
					${rs.ccqdz}～${rs.cczdz}
				</td>
			</tr>	
			<logic:equal name="xxdm" value="10351">
		    	<th>申请原因</th>
				<td colspan="3">
			    	${rs.hcyhklxmc}
				</td>
		    </logic:equal>
			<tr>	
				<th>
					备注
				</th>
				<td colspan="3" style="word-break:break-all;" width="650px">
					${rs.bz}
				</td>
			</tr>
			</tbody>
			<thead>
				<tr>
					<th colspan="4">
						<span>审核历史</span>
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
						<th>
							<font color="red">*</font>审核结果
						</th>
						<td colspan="3" id="shjgSpan">
							
						</td>
					</tr>
			</tr>
			<tr>
				<th width="20%">
					<font color="red">*&nbsp;</font> 审核意见
					<br />
					<font color="red">限200字</font>
				</th>
				<td colspan="3">
					<jsp:include page="/xsgzgl/comm/shlc/cyyj.jsp?gnid=hcyhk&id=shyj" />
					<textarea id="shyj" rows="5" name="shyj" style="word-break:break-all;width:100%;margin-top:5px" onblur="checkLen(this,200);"></textarea>
				</td>
			</tr>
			</tbody>
			</table>
			
		</div>
		</td>
		</tr>
		</table>
		</div>
		<table width="100%" border="0" class="formlist">	
			<tfoot>
					<tr>
						<td colspan="4">
							<div class="btn">
								<button type="button" name="保存"  onclick="saveShzt();return false;">
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
