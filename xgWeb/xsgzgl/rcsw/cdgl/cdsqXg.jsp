<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/cdgl/js/cdsq.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/f_q.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" defer="defer">
			jQuery(function(){
				});
		</script>
	</head>
	<body>
		<button id="search_go" type="button" style="display:none" onclick="reloadWindow();"></button>
		<html:form action="/rcsw_cdgl_cdsq" method="post" styleId="rcswCdsqForm">
			<html:hidden property="sqid"/>
			<html:hidden property="shzt" styleId="shzt"/>
			<html:hidden property="cdid" styleId="cdid"/>
			<input type="hidden" id="xxdm" value="${xxdm }" /> 
			<div style='tab;width:100%;height:455px;overflow-x:hidden;overflow-y:auto;'>
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
								<span>场地申请信息&nbsp;&nbsp;&nbsp;&nbsp;
									<a onclick="cdxxck();" class="btn_xg"
											href="javascript:void(0);"> <font color="blue">查看场地申请情况</font>
									</a></span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="16%">
								场地名称
							</th>
							<td colspan="3">
								${cdxx.cdmc}
							</td>
						</tr>
						<tr>
							<th width="16%">
								楼栋
							</th>
							<td width="34%">
								${cdxx.ld}
							</td>
							<th width="16%">
								房间
							</th>
							<td width="34%">
								${cdxx.fj}
							</td>
						</tr>
						<tr>
							<th width="16%">
								容纳人数
							</th>
							<td width="34%">
								${cdxx.rnrs}
							</td>
							<th width="16%">
								收费标准
							</th>
							<td width="34%">
								${cdxx.sfbz}
							</td>
						</tr>
						<tr>
							<th width="16%">
								联系人
							</th>
							<td width="34%">
								${cdxx.lxr}
							</td>
							<th width="16%">
								联系方式
							</th>
							<td width="34%">
								${cdxx.lxfs}
							</td>
						</tr>
						<tr>
							<th width="16%">
								对外开放时间
							</th>
							<td colspan="3">
								${cdxx.dwkfsjkssj}
									至
								${cdxx.dwkfsjjssj}
							</td>
						</tr>
						<tr>
							<th width="16%">
								用途
							</th>
							<td colspan="3">
								${cdxx.yt}
							</td>
						</tr>
						<tr>
							<th width="16%">
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
								<div id="commonfileupload-list-0" style="padding: 5px;"></div>
								<script type="text/javascript">
									//调用附件 
									jQuery(function(){
										var gid = "${cdxx.filepath}";
										jQuery.MultiUploader_q({
											gid : gid,
											targetEl : 'commonfileupload-list-0'
											});
									});
								</script>
							</td>
						</tr>
						</logic:equal>
						<tr>
							<th width="16%">
								<span class="red">*</span>申请使用时间段
							</th>
							<td colspan="3">
								<html:text styleId="sqsjdkssj" property="sqsjdkssj" onclick="return showCalendar('sqsjdkssj','yyyy-MM-dd HH:mm',true,'sqsjdjssj');"  readonly="true"></html:text>
								至
								<html:text styleId="sqsjdjssj" property="sqsjdjssj" onclick="return showCalendar('sqsjdjssj','yyyy-MM-dd HH:mm',false,'sqsjdkssj');" readonly="true"></html:text>
							</td>
						</tr>
						<tr>
							<th width="16%">使用部门</th>
							<td colspan="3">
								<html:select property="bmlbdm" styleId="bmlbdm" style="width:96%;">
									<html:options collection="bmList" property="bmdm" labelProperty="bmmc" />
								</html:select>
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
							<th width="16%">
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
									<input type="hidden" id="xysfilepath" value="${cdxx.xysfilepath }" />
									<logic:notEmpty name="cdxx" property="xysfilepath">
										<input type="checkbox" id="xys_checkbox"/>我已阅读并接受<a href="javascript:void(0);" onclick="window.open('common_upload.do?method=asyncDownload&fid=${cdxx.xysfilepathfid}');return false;" class="name" style="margin-left: 0px;">《场地申请协议》</a>
										&nbsp;&nbsp;&nbsp;
									</logic:notEmpty>
									<button id="save_button" type="button"  onclick="updateCdsqAction('save');">
										保存草稿
									</button>
									<button  id="submit_button" type="button" onclick="updateCdsqAction('submit');">
										提交申请
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

