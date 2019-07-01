<%@ page language="java" pageEncoding="GBK"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			var cflbdm ='${cflbdm }';
			showCfqxFlagSh(cflbdm);
			defaultCfdqsj();
		});
			function savePlsh(shzt){
				var cffwqx = jQuery("#cffwqx").val();
				var shyj = jQuery("#shyj").val();
				var cfwh = jQuery("#cfwh").val();
				var cfsj = jQuery("#cfsj").val();
				var cfdqsj = jQuery("#cfdqsj").val();
				var cflbdm = jQuery("#cflbdm").val();//更改后的
				var kzzd1 = jQuery("#kzzd1").val();//更改前的
				if(cffwqx==1){
					if (shyj == ""||cfwh==""||cfsj==""||cflbdm==""){
						showAlert("带*的项不可为空！");
						return false;
					}
				}else{
					if (shyj == ""){
						showAlert("请填写审核意见！");
						return false;
					}
				}
				var api = frameElement.api,W = api.opener;
				W.savePlsh(shzt,shyj,cffwqx,cfwh,cfsj,cflbdm,kzzd1,cfdqsj);
				closeDialog();
			}
			
		function showCfqxFlagSh(cflbdm){
			if(cflbdm==""){return false;}
			jQuery.post("wjcf_cflbdmwh.do?method=getCfqx",{cflbdm:cflbdm},function(data){
				jQuery("#showCfqxsh").html(data["message"]);
			},'json');
		}
		function defaultCfdqsj(){
			var cfsj = jQuery("#cfsj").val();
			var cflbdm = jQuery("#cflbdm").val();
			jQuery.post("wjcf_cfsh.do?method=defaultCfdqsj",{cfsj:cfsj,cflbdm:cflbdm},function(data){
				if(data["message"]!="hidden"){
					var html = "<th><font color=\"red\">*</font>处分到期时间：</th><td colspan=\"3\"><input name=\"cfdqsj\" id=\"cfdqsj\" "
					+" style=\"cursor:hand;\" onclick=\"return showCalendar('cfdqsj','y-mm-dd',false,'cfsj');\" value=\""+data["message"]+"\"/></td>";
					jQuery("#cffw_tr3").html(html);
				}else{
					jQuery("#cffw_tr3").html("");
				}
				
			},'json');
		}
		</script>
	</head>
	<body>
		<html:form action="/wjcf_cfsh" method="post" onsubmit="return false;">
			<input type="hidden" name="cffwqx" id="cffwqx" value="${cffwqx }"/>
			<html:hidden property="kzzd1" name="kzzd1" styleId="kzzd1" value="${cflbdm }"/>
			<div class="tab">
				<table class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button type="button" onclick="savePlsh('1');">
										通过
									</button>
									<button type="button" onclick="savePlsh('2');">
										不通过
									</button>
									<button type="button" name="关 闭" onclick="closeDialog();">
										关 闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<thead>
						<tr>
							<td colspan="4">
								<span>
									批量审核
								</span>
							</td>
						</tr>
					</thead>
					<tbody>
					<logic:equal value="1" name="cffwqx">
						<tr>
							<th>
								<font color="red">*</font>处分文号：
							</th>
							<td>
								<input name="cfwh" id="cfwh" maxlength="30" value=""/>
							<th>
								<font color="red">*</font>处分时间：
							</th>
							<td>
								<input name="cfsj" id="cfsj" style="cursor:hand;" 
								onclick="return showCalendar('cfsj','y-mm-dd','','','','',defaultCfdqsj);" 
								value=""/>
							</td>
						</tr>
						<tr id="cffw_tr2">
								<th align="right">
									<font color="red">*</font>处分类别：
								</th>
								<td align="left" colspan="3">
									<html:select property="cflbdm" styleId="cflbdm"
										style="width:100px" onchange="showCfqxFlagSh(this.value);">
										<html:option value=""></html:option>
										<html:options collection="cflbList" property="dm"
											labelProperty="mc" />
									</html:select>
									&nbsp;&nbsp;&nbsp;&nbsp;<span id="showCfqxsh" style="color: red"></span>
								</td>
							</tr>
							<tr id="cffw_tr3">
							</tr>
						</logic:equal>
						<tr>
							<th width="20%">
								<font color="red">*</font>
								审核意见
								<br/>
								<font color="red">(限200字)</font>
							</th>
							<td colspan="3">
								<jsp:include page="/xsgzgl/comm/shlc/cyyj.jsp?gnid=cfsb&id=shyj" />
								<html:textarea property="shyj" style="width:98%;margin-top:5px" rows="6"
											   onblur="checkLen(this,200);"  styleId="shyj"
								></html:textarea>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</html:form>
	</body>
</html>
