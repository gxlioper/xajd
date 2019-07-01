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
								<span>ѧ��������Ϣ</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/comm/bdpz/selectStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>����������Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>
								<span class="red">*</span>��������
							</th>
							<td colspan="3">
								<input type="text" name="cdmc" value="${cdxx.cdmc}" readonly="true" id="cdmc" style="width:240px;"/>
								<input type="hidden" name="cdid" value="${cdxx.cdid }" id="cdid"/>
								<input type="hidden" name="splcid" value="${cdxx.splcid }" id="splcid"/>
								<button class="btn_01" type="button"  
										onclick="showDialog('��ѡ��һ������',700,500,'rcsw_cdgl_cdxxwh.do?method=showCdxx&search_sfkfsq=2&goto=${pathForCd}');">ѡ��</button>
							</td>
						</tr>
						<tr>
							<th>
								¥��
							</th>
							<td>
								${cdxx.ld}
							</td>
							<th >
								����
							</th>
							<td>
								${cdxx.fj}
							</td>
						</tr>
						<tr>
							<th>
								��������
							</th>
							<td>
								${cdxx.rnrs}
							</td>
							<th>
								�շѱ�׼
							</th>
							<td>
								${cdxx.sfbz}
							</td>
						</tr>
						<tr>
							<th>
								��ϵ��
							</th>
							<td>
								${cdxx.lxr}
							</td>
							<th>
								��ϵ��ʽ
							</th>
							<td>
								${cdxx.lxfs}
							</td>
						</tr>
						<tr>
							<th>
								���⿪��ʱ��
							</th>
							<td colspan="3">
								${cdxx.dwkfsjkssj}
									��
								${cdxx.dwkfsjjssj}
							</td>
						</tr>
						<tr>
							<th>
								��;
							</th>
							<td colspan="3">
								${cdxx.yt}
							</td>
						</tr>
						<tr>
							<th>
								�����豸����
							</th>
							<td colspan="3" style="word-break: break-all;">
								${cdxx.jbsbjs}
							</td>
						</tr>
						<logic:equal value="10351" name="xxdm">
						<tr>
							<th width="16%">
								�Ҹ�����ʹ��Э��
							</th>
							<td colspan="3" style="word-break: break-all;">
								${cdxx.xfgfsyxy}
							</td>
						</tr>
						</logic:equal>
						<logic:equal name="xxdm" value="10351">
						<tr>
							<th align="right" width="10%">
								������Ϣ
							</th>
							<td colspan="3">
								<%@ include file="/xsgzgl/comm/fileUpload/f_q.jsp"%>
								<html:hidden property="filepath" name="cdxx" styleId="fjid"/>
								<script type="text/javascript">
									//���ø��� 
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
								<span class="red">*</span>����ʹ��ʱ���
							</th>
							<td colspan="3">
								<html:text styleId="sqsjdkssj" property="sqsjdkssj" onclick="return showCalendar('sqsjdkssj','yyyy-MM-dd HH:mm',true,'sqsjdjssj');"  readonly="true"></html:text>
								��
								<html:text styleId="sqsjdjssj" property="sqsjdjssj" onclick="return showCalendar('sqsjdjssj','yyyy-MM-dd HH:mm',false,'sqsjdkssj');" readonly="true"></html:text>
							</td>
						</tr>
						<tr>
							<th>ʹ�ò���</th>
							<td>
								<html:select property="bmlbdm" styleId="bmlbdm" style="width:200px">
									<html:options collection="bmList" property="bmdm" labelProperty="bmmc" />
								</html:select>
							</td>
							<th>����ʱ��</th>
							<td>
								<html:text styleId="sqsj" property="sqsj" onclick="return showCalendar('sqsj','yyyy-MM-dd HH:mm:ss','','');" readonly="true"></html:text>
							</td>
						</tr>
						<tr>
							<th width="16%"><span class="red">*</span>��������</th>
							<td width="34%">
								<html:text property="cyrs" styleId="cyrs" maxlength="5" onkeyup="checkInputData(this)"></html:text>
							</td>
							<th width="16%"><span class="red">*</span>������</th>
							<td width="34%">
								<html:text property="fzrxm" styleId="fzrxm" maxlength="10"></html:text>
							</td>
						</tr>
						<tr>
							<th width="16%"><span class="red">*</span>��������ϵ��ʽ</th>
							<td colspan="3">
								<html:text property="fzrlxfs" styleId="fzrlxfs" maxlength="12" onkeyup="checkInputData(this)"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								<span class="red">*</span>��������
								<br />
								<font color="red">(
								<logic:equal value="10351" name="xxdm">
									��д�����Ҫ������������50��
								</logic:equal>
								<logic:notEqual value="10351" name="xxdm">
									��500��
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
								<div class="bz">"<span class="red">*</span>"Ϊ������</div>
								<div class="btn">
									<button id="save_button" type="button"  onclick="addCdjgAction();">
										����
									</button>
									<button type="button" name="�� ��" onclick="iFClose();">
										�� ��
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

