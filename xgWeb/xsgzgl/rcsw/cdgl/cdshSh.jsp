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
		<script type="text/javascript" src="xsgzgl/rcsw/cdgl/js/cdsq.js"></script>
		<script type="text/javascript">
		jQuery(function(){
				
				jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid="+jQuery("#sqid").val()+"&tt="+new Date().getTime());
				
				jQuery("#shjgSpan").load("comm_spl.do?method=shth&lcid="+jQuery("#splcid").val()+"&shid="+jQuery("#shid").val());
				
			});
		</script>
	</head>
	<body>
		<button id="search_go" type="button" style="display:none" onclick="reloadWindow();"></button>
		<html:form action="/rcsw_cdgl_cdsh" method="post" styleId="rcswCdshForm">
		<html:hidden property="sqid" styleId="sqid"/>
		<html:hidden property="sqsj" />
		<html:hidden property="splcid" styleId="splcid"/>
		<html:hidden property="shzt" />
		<html:hidden property="xh" />
		<html:hidden property="xtgwid" />
		<html:hidden property="shid" styleId="shid"/>
			<div style='tab;width:100%;height:450px;overflow-x:hidden;overflow-y:auto;'>
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
								<span>场地申请信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="16%">
								场地名称
							</th>
							<td colspan="3">
								${cdsqxx.cdmc}
							</td>
						</tr>
						<tr>
							<th width="16%">
								楼栋
							</th>
							<td width="34%">
								${cdsqxx.ld}
							</td>
							<th width="16%">
								房间
							</th>
							<td width="34%">
								${cdsqxx.fj}
							</td>
						</tr>
						<tr>
							<th width="16%">
								容纳人数
							</th>
							<td width="34%">
								${cdsqxx.rnrs}
							</td>
							<th width="16%">
								收费标准
							</th>
							<td width="34%">
								${cdsqxx.sfbz}
							</td>
						</tr>
						<tr>
							<th width="16%">
								联系人
							</th>
							<td width="34%">
								${cdsqxx.lxr}
							</td>
							<th width="16%">
								联系方式
							</th>
							<td width="34%">
								${cdsqxx.lxfs}
							</td>
						</tr>
						<tr>
							<th width="16%">
								对外开放时间
							</th>
							<td colspan="3">
								${cdsqxx.dwkfsjkssj}
									至
								${cdsqxx.dwkfsjjssj}
							</td>
						</tr>
						<tr>
							<th width="16%">
								用途
							</th>
							<td colspan="3">
								${cdsqxx.yt}
							</td>
						</tr>
						<tr>
							<th width="16%">
								基本设备介绍
							</th>
							<td colspan="3" style="word-break: break-all;">
								${cdsqxx.jbsbjs}
							</td>
						</tr>
						<logic:equal value="10351" name="xxdm">
						<tr>
							<th width="16%">
								幸福工坊使用协议
							</th>
							<td colspan="3" style="word-break: break-all;">
								${cdsqxx.xfgfsyxy}
							</td>
						</tr>
						</logic:equal>
						<logic:equal name="xxdm" value="10351">
						<tr>
							<th align="right" width="10%">
								附件信息
							</th>
							<td colspan="3">
								<%@ include file="/xsgzgl/comm/fileUpload/f_q.jsp"%>
								<html:hidden property="filepath" name="cdsqxx" styleId="fjid"/>
								<script type="text/javascript">
									//调用附件 
									jQuery(function(){
										var gid = jQuery('#fjid').val();
										jQuery.MultiUploader_q({
											gid : gid
											});
									});
								</script>
							</td>
						</tr>
						</logic:equal>
						<tr>
							<th width="16%">
								申请使用时间段
							</th>
							<td colspan="3">
								${cdsqxx.sqsjdkssj}
								至
								${cdsqxx.sqsjdjssj}
							</td>
						</tr>
						<tr>
							<th width="16%">使用部门</th>
							<td colspan="3">
								${cdsqxx.bmmc}
							</td>
						</tr>
						<tr>
							<th width="16%">参与人数</th>
							<td width="34%">
								${cdsqxx.cyrs}
							</td>
							<th width="16%">负责人</th>
							<td width="34%">
								${cdsqxx.fzrxm}
							</td>
						</tr>
						<tr>
							<th width="16%">负责人联系方式</th>
							<td colspan="3">
								${cdsqxx.fzrlxfs}
							</td>
						</tr>
						<tr>
							<th width="16%">
								申请理由
							</th>
							<td colspan="3" style="word-break: break-all;">
								${cdsqxx.sqly}
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
								<jsp:include page="/xsgzgl/comm/shlc/cyyj.jsp?gnid=cdgl&id=shyj" />
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
									<button type="button" id="btqd" onclick="submitCdshSh();">
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

