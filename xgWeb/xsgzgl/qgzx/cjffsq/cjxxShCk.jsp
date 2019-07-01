<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script language="javascript">
		jQuery(function(){
			for(var i=0;i<jQuery("#listSize").val();i++){
				var len=jQuery("#bz"+i).html().length;
				var bzValue=jQuery("#bz"+i).html();
				if(len>12){
					jQuery("#bz"+i).html(bzValue.substr(0,11)+"...");
					}
				}
			jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid=${rs.sqid}&tt="+new Date().getTime());
			jQuery("#shjgSpan").load("comm_spl.do?method=shth&lcid=${rs.splc}&shid=${shid}");
		});
		
		function queryXmXh(){
			var url ="qgzx_cjffsq.do?method=cjxxCkByxhxm";
			var parameter={
				"xh":	jQuery("#xh").val(),
				"pkValue":jQuery("#pkValue").val()
			};
			jQuery.ajaxSetup({
				 contentType:"application/x-www-form-urlencoded;charset=utf-8"
			});
			jQuery("#tbody_zgryxx").load(
				url,
				parameter,
				function(){}
			);
			
		}
		
		function saveSh(){
			var shzt = jQuery("#shjg").val();
			if (jQuery("#shzt").val() == "" || jQuery("#shyj").val() == ""){
				showAlert("请将必填项填写完整！");
				return false;
			}
			var url = "qgzx_cjffsq_ajax.do?method=dgsh";
			ajaxSubFormWithFun("qgzxCjffsqForm",url,function(data){
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
	<html:form action="/qgzx_cjffsq" method="post" styleId="qgzxCjffsqForm">
			<input type="hidden" name="listSize" id="listSize" value="${rs.listSize }" />
			<input type="hidden" name="pkValue" id="pkValue" value="${pkValue}" />
			<html:hidden name="rs" property="sqid" styleId="sqid"/>
			<html:hidden name="rs" property="splc" styleId="splc"/>
			<input type="hidden" name="csz" id="csz" value="${csz}" />
			<div style="width:100%;height:495px;overflow-x:hidden;overflow-y:auto;">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>岗位信息</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_gwxx">
						<tr>
							<th width="16%">
								学年<logic:equal value="xq" name="cs" property="qgzq">学期
								</logic:equal>
							</th>
							<td width="34%">
								${rs.xn}<logic:equal value="xq" name="cs" property="qgzq">&nbsp;&nbsp;&nbsp;${rs.xqmc}
								</logic:equal>
							</td>
							<th width="16%">
								用人部门
							</th>
							<td width="34%">
								${rs.yrdwmc}
							</td>
						</tr>
						<tr>
							<th>
								发放年月
							</th>
							<td>
								${rs.ffny}
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>酬金明细信息</span>
							</th>
						</tr>
						<tr>
					<td colspan="6">
						<font>&nbsp;&nbsp;学号/姓名</font>
						<input type="text" id="xh" name="xh" maxleng="20">
						<font>
						<button type="button" onclick="queryXmXh();return false;" class="btn_01">查询</button>
						</font>
					</td>
				</tr>
					</thead>
				</table>
				<table width="100%" border="0" class="datelist" style="margin:2px auto;">
					<logic:empty name="rs" property="cjmxHtml">
					<tbody>
						<tr>
							<td>无相关记录！</td>
						</tr>
					</tbody>
					</logic:empty>
					<logic:notEmpty name="rs" property="cjmxHtml">
					<thead>
						<tr>
							<td width='2%'>序号</td>
							<td width='11%'>学号</td>
							<td width='9%'>姓名</td>
							<td width='14%'>岗位名称</td>
							<td width='10%'>岗位性质</td>
							<logic:equal value="1" name="csz">
							 	<td width='7%'>工时</td>
							</logic:equal>
							<td width='5%'>金额</td>
							<td width='13%'>银行卡号</td>
							<td width='15%'>备注</td>
							<td width='14'>酬金发放人</td>
						</tr>
					</thead>
					<tbody id="tbody_zgryxx">
						${rs.cjmxHtml }
					</tbody>
					</logic:notEmpty>
					
					<table width="100%" border="0" class="formlist">
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
								<th>
									<span class="red">*</span>&nbsp;审核结果
								</th>
								<td id="shjgSpan" >
									
								</td>
					</tr>
					<tr>
						<th width="20%">
							<font color="red">*&nbsp;</font> 审核意见
							<br />
							<font color="red">(限200字)</font>
						</th>
						<td colspan="3">
							<jsp:include page="/xsgzgl/comm/shlc/cyyj.jsp?gnid=tsqksh&id=shyj" />
							<textarea id="shyj" rows="5" name="shyj" style="word-break:break-all;width:100%;margin-top: 5px" onblur="checkLen(this,200);"></textarea>
						</td>
					</tr>
					</tbody>
				</table>
			</div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
								<button type="button" name="保存"  onclick="saveSh();return false;">
									保 存
								</button>
									<button type="button" onclick="Close();return false;">
										关 闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
		</html:form>
	</body>
</html>

