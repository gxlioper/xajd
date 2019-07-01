<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="<%=stylePath%>js/lhgdialog/lhgdialog.min.js?self=true" ></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
		jQuery(function(){
				
				jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid="+jQuery("#sbsqid").val()+"&tt="+new Date().getTime());
				
				jQuery("#shjgSpan").load("comm_spl.do?method=shth&lcid="+jQuery("#splcid").val()+"&shid="+jQuery("#shid").val());
				
			});

		/**
		 * 提交审核
		 * @return
		 */
		function submitSh(){
			if (jQuery("#shyj").val() == ""){
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
			
			
			showConfirmDivLayer("您确定“"+message+"”该申请吗？",{"okFun":function(){
				var url = "xljk_xlwygl_xxsbglwh.do?method=shAction";
				ajaxSubFormWithFun("xlwyglxxsbglForm",url,function(data){
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
		<button id="search_go" type="button" style="display:none" onclick="reloadWindow();"></button>
		<html:form action="/xljk_xlwygl_xxsbglwh" method="post" styleId="xlwyglxxsbglForm">
		<html:hidden property="sbsqid" styleId="sbsqid"  value="${sbxxdata.sbsqid}"/>
		<html:hidden property="sbsj"  value="${sbxxdata.sbsj}"/>
		<html:hidden property="splcid" styleId="splcid" value="${sbxxdata.splcid}"/>
		<html:hidden property="shzt"  value="${sbxxdata.shzt}"/>
		<html:hidden property="xh"  value="${sbxxdata.xh}"/>
		<html:hidden property="xtgwid"  value="${sbxxdata.xtgwid}"/>
		<html:hidden property="shid" styleId="shid" value="${sbxxdata.shid}"/>
			<div style='tab;width:100%;height:530px;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>学生基本信息</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/comm/bdpz/viewStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span><font color="blue" style="font-weight: bold;">${sbxxdata.sblxmc}</font>-上报信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<logic:notEqual value="2" name="sbxxdata" property="sblx">
							<tr>
								<th width="100px">
									学年
								</th>
								<td>
									${sbxxdata.xn}
								</td>
								<th width="100px">
									学期
								</th>
								<td>
									${sbxxdata.xqmc}
								</td>
							</tr>
							<tr>
								<th width="100px">
									周次
								</th>
								<td>
									${sbxxdata.zbzc}
								</td>
								<th width="100px">
									起止日期
								</th>
								<td>
									${sbxxdata.zbqzrq}
								</td>
							</tr>
						</logic:notEqual>
						<logic:equal value="2" name="sbxxdata" property="sblx">
							<tr>
								<th width="20%">
									学年
								</th>
								<td width="30%">
									${xn}
								</td>
								<th width="20%">
									学期
								</th>
								<td width="30%">
									${xq}
								</td>	
							</tr>
						</logic:equal>
						<tr>
							<th width="100px">
								总体情况
							</th>
							<td colspan="3" style="word-break: break-all;">
								${sbxxdata. ztqk}
							</td>
						</tr>
						<tr>
							<th>
								心理学生<br />
								详细情况<br />
							</th>
							<td colspan="3" style="word-break: break-all;">
								${sbxxdata. xlxsxxqk}
							</td>
						</tr>
						<tr>
							<th>
								备注
								<br />
							</th>
							<td colspan="3" style="word-break: break-all;">
									${sbxxdata. bz}
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
							<td colspan="4" id="shlccx">
							
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">* </font>审核结果
							</th>
							<td colspan="3" id="shjgSpan">
								
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>
								审核意见
								<br/>
								<font color="red">(限200字)</font>
							</th>
							<td colspan="3">
								<jsp:include page="/xsgzgl/comm/shlc/cyyj.jsp?gnid=sbxxgl&id=shyj" />
								<html:textarea property="shyj" style="width:98%;margin-top:5px" rows="5" 
											   onblur="checkLen(this,200);" styleId="shyj"
								></html:textarea>
							</td>
						</tr>
					</tbody>
					
				</table>
			</div>
			<div>
			<table width="100%" border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"为必填项</div>
								<div class="btn">
									<button type="button" id="btqd" onclick="submitSh();">
										确定
									</button>
									<button type="button" name="关 闭" onclick="iFClose();">
										关 闭
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

