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
		jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid=${model.sqid}&tt="+new Date().getTime());
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
			var url = "rcsw_xqybgl_bjxqybgl_bjxqybshgl.do?method=bjxqybDgsh&type=save";
			ajaxSubFormWithFun("bjxqybshForm",url,function(data){
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
	<html:form action="/rcsw_xqybgl_bjxqybgl_bjxqybshgl"  styleId="bjxqybshForm">

		<html:hidden name="model" property="sqid" styleId="sqid"/>
		<html:hidden name="model" property="xn" styleId="xn"/>		
		<html:hidden name="model" property="xq" styleId="xq"/>
		<html:hidden name="model" property="yf" styleId="yf"/>			
		<html:hidden name="model" property="bygzkzqk" styleId="bygzkzqk"/>
		<html:hidden name="model" property="xsgzrd" styleId="xsgzrd"/>		
		<html:hidden name="model" property="xssxdt" styleId="xssxdt"/>
		<html:hidden name="model" property="xstsjgzjy" styleId="xstsjgzjy"/>		
		<html:hidden name="model" property="txsj" styleId="txsj"/>
		<html:hidden name="model" property="txr" styleId="txr"/>		
		<html:hidden name="model" property="shzt" styleId="shzt"/>
		<html:hidden name="model" property="splc" styleId="splc"/>	
		<html:hidden name="model" property="bjdm" styleId="bjdm"/>		
	
			
		<div style="height:460px;overflow-x:hidden;overflow-y:auto;">
			
		
			<table width="100%" class="formlist">
			<thead>
				<tr>
					<th colspan="4">
						<span>月报填写</span>
					</th>
				</tr>
			</thead>
			<tbody>
			
		
			<tr>
				<th align="right" width="20%">
					学年
				</th>
				<td align="left" width="30%">
					${infoList.xn}
				</td>
				<th align="right" width="20%">
					学期
				</th>
				<td align="left" width="30%">
					${infoList.xqmc}
				</td>
			</tr>
			<tr>
				<th align="right" width="20%">
					年级
				</th>
				<td align="left" width="30%" >
					${infoList.xn}
				</td>			
				<th align="right" width="20%">
					学院
				</th>
				<td align="left" width="30%" >
					${infoList.xymc}
				</td>
			</tr>
				
			<tr>
				<th align="right" width="20%">
					专业
				</th>
				<td align="left" width="30%" >
					${infoList.zymc}
				</td>			
				<th align="right" width="20%">
					班级
				</th>
				<td align="left" width="30%" >
					${infoList.bjmc}
				</td>
			</tr>
			
			<tr>
				<th align="right" width="20%">
					月份
				</th>
				<td align="left" width="30%" >
					${infoList.yf}
				</td>			
				<th align="right" width="20%">
					填写人
				</th>
				<td align="left" width="30%" >
					${infoList.lrrxm}
				</td>
			</tr>


		
			<tr>
				<th align="right" width="20%">
					本月工作开展情况
				</th>
				
				<td align="left" width="80%" colspan="3">
					${infoList.bygzkzqk}
				</td>
			</tr>
			
			<tr>
				<th align="right" width="20%">
					学生关注热点
				</th>				
				<td align="left" width="80%" colspan="3">
					${infoList.xsgzrd}	
				</td>
			</tr>
			
			<tr>
				<th align="right" width="20%">
					学生思想动态
				</th>				
				<td align="left" width="80%" colspan="3">
					${infoList.xssxdt}
				</td>
			</tr>
			
			<tr>
				<th align="right" width="20%">
					学生诉求及工作建议
				</th>				
				<td align="left" width="80%" colspan="3">
					${infoList.xstsjgzjy}
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
					<jsp:include page="/xsgzgl/comm/shlc/cyyj.jsp?gnid=bjxqyb&id=shyj" />
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
