<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/cdgl/js/cdjg.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" defer="defer">
			jQuery(function(){});
		</script>
	</head>
	<body>
		<button id="search_go" type="button" style="display:none" onclick="reloadWindow();"></button>
		<html:form action="/rcsw_cdgl_cdjg" method="post" styleId="rcswCdjgForm">
			<input type="hidden" id="xxdm" value="${xxdm }" /> 
			<div style='tab;width:100%;height:450px;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>学生基本信息</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/comm/bdpz/selectStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>场地申请信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>
								<span class="red">*</span>场地名称
							</th>
							<td colspan="3">
								<input type="text" name="cdmc" value="${cdxx.cdmc}" readonly="true" id="cdmc" style="width:240px;"/>
								<input type="hidden" name="cdid" value="${cdxx.cdid }" id="cdid"/>
								<input type="hidden" name="splcid" value="${cdxx.splcid }" id="splcid"/>
								<button class="btn_01" type="button"  
										onclick="showDialog('请选择一个场地',700,500,'rcsw_cdgl_cdxxwh.do?method=showCdxx&search_sfkfsq=2&goto=${pathForCd}');">选择</button>
							</td>
						</tr>
						<tr>
							<th>
								楼栋
							</th>
							<td>
								${cdxx.ld}
							</td>
							<th >
								房间
							</th>
							<td>
								${cdxx.fj}
							</td>
						</tr>
						<tr>
							<th>
								容纳人数
							</th>
							<td>
								${cdxx.rnrs}
							</td>
							<th>
								收费标准
							</th>
							<td>
								${cdxx.sfbz}
							</td>
						</tr>
						<tr>
							<th>
								联系人
							</th>
							<td>
								${cdxx.lxr}
							</td>
							<th>
								联系方式
							</th>
							<td>
								${cdxx.lxfs}
							</td>
						</tr>
						<tr>
							<th>
								对外开放时间
							</th>
							<td colspan="3">
								${cdxx.dwkfsjkssj}
									至
								${cdxx.dwkfsjjssj}
							</td>
						</tr>
						<tr>
							<th>
								用途
							</th>
							<td colspan="3">
								${cdxx.yt}
							</td>
						</tr>
						<tr>
							<th>
								基本设备介绍
							</th>
							<td colspan="3" style="word-break: break-all;">
								${cdxx.jbsbjs}
							</td>
						</tr>
						<logic:equal value="10351" name="xxdm">
						<tr>
							<th width="16%">
								幸福工坊使用协议
							</th>
							<td colspan="3" style="word-break: break-all;">
								${cdxx.xfgfsyxy}
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
								<html:hidden property="filepath" name="cdxx" styleId="fjid"/>
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
							<th>
								<span class="red">*</span>申请使用时间段
							</th>
							<td colspan="3">
								<html:text styleId="sqsjdkssj" property="sqsjdkssj" onclick="return showCalendar('sqsjdkssj','yyyy-MM-dd HH:mm',true,'sqsjdjssj');"  readonly="true"></html:text>
								至
								<html:text styleId="sqsjdjssj" property="sqsjdjssj" onclick="return showCalendar('sqsjdjssj','yyyy-MM-dd HH:mm',false,'sqsjdkssj');" readonly="true"></html:text>
							</td>
						</tr>
						<tr>
							<th>使用部门</th>
							<td>
								<html:select property="bmlbdm" styleId="bmlbdm" style="width:200px">
									<html:options collection="bmList" property="bmdm" labelProperty="bmmc" />
								</html:select>
							</td>
							<th>申请时间</th>
							<td>
								<html:text styleId="sqsj" property="sqsj" onclick="return showCalendar('sqsj','yyyy-MM-dd HH:mm:ss','','');" readonly="true"></html:text>
							</td>
						</tr>
						<tr>
							<th width="16%"><span class="red">*</span>参与人数</th>
							<td width="34%">
								<html:text property="cyrs" styleId="cyrs" maxlength="5" onkeyup="checkInputData(this)"></html:text>
							</td>
							<th width="16%"><span class="red">*</span>负责人</th>
							<td width="34%">
								<html:text property="fzrxm" styleId="fzrxm" maxlength="10"></html:text>
							</td>
						</tr>
						<tr>
							<th width="16%"><span class="red">*</span>负责人联系方式</th>
							<td colspan="3">
								<html:text property="fzrlxfs" styleId="fzrlxfs" maxlength="12" onkeyup="checkInputData(this)"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								<span class="red">*</span>申请理由
								<br />
								<font color="red">(
								<logic:equal value="10351" name="xxdm">
									请写明活动简要方案，不少于50字
								</logic:equal>
								<logic:notEqual value="10351" name="xxdm">
									限500字
								</logic:notEqual>
								)</font>
							</th>
							<td colspan="3" style="word-break: break-all;">
								<html:textarea property="sqly" styleId="sqly" style="width:95%;" rows="5"></html:textarea>
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
									<button id="save_button" type="button"  onclick="addCdjgAction();">
										保存
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

