<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<%@ include file="/syscommon/autocomplete.ini"%>
		<script type="text/javascript" src="<%=stylePath%>js/lhgdialog/lhgdialog.min.js?self=true"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script language="javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/dagl/xzmzdx/dazcjg/js/dazcjg.js"></script>
		<script type="text/javascript">
			jQuery(function(){
				jQuery("#mailedOne").hide();
				jQuery("#mailedTwo").hide();
				jQuery("#mailedThree").hide();
				jQuery("#mailedFour").hide();
				jQuery("#mailedFive").hide();
				jQuery("#mailedSix").hide();
				jQuery("#byoOne").hide();
				jQuery("#byoTwo").hide();
			});
		</script>
	</head>
	<body>
		<html:form action="/dagl_dazcjg" method="post" styleId="dazcjgForm">
			<div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom:0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>ѧ��������Ϣ</span>
							</th>
						</tr>
					</thead>
					<%@include file="/xsgzgl/comm/bdpz/selectStudent.jsp"%>
					<thead>
						<tr>
							<th colspan="4">
								<span>����ת�������Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>
								<font color="red">*</font>ת����ʽ
							</th>
							<td colspan="3">
								<input type="radio" name="zcfs" value="1" onchange="changeZcfs()"/>�ʼ�
								<input type="radio" name="zcfs" value="2" onchange="changeZcfs()"/>�Դ�
							</td>
						</tr>
						<tr id="mailedOne">
							<th>
								<font color="red">*</font>�ʼĵ�ַ
							</th>
							<td>
								<html:text property="yjdz" styleId="yjdz" maxlength="64" style="width:150px" styleClass="text_nor" ></html:text>
							</td>
							<th>
								<font color="red">*</font>��������
							</th>
							<td>
								<html:text property="yzbm" styleId="yzbm" maxlength="6" styleClass="text_nor" onkeyup="checkInputLxfx(this)" style="width:150px"></html:text>
							</td>
						</tr>
						<tr id="mailedTwo">
							<th>
								<font color="red">*</font>�ռ���
							</th>
							<td>
								<html:text property="sjr" styleId="sjr" maxlength="10" style="width:150px" styleClass="text_nor" ></html:text>
							</td>
							<th>
								<font color="red">*</font>�ռ��˵绰
							</th>
							<td>
								<html:text property="sjrdh" styleId="sjrdh" maxlength="12" style="width:150px" onkeyup="checkInputLxfx(this)" styleClass="text_nor" ></html:text>
							</td>
						</tr>
						<tr id="mailedThree">
							<th>
								<font color="red">*</font>��λ����
							</th>
							<td>
								<html:text property="dwmc" styleId="dwmc" maxlength="64" style="width:150px" styleClass="text_nor" ></html:text>
							</td>
							<th>
								<font color="red">*</font>��λ��ַ
							</th>
							<td>
								<html:text property="dwdz" styleId="dwdz" maxlength="64" style="width:150px" styleClass="text_nor" ></html:text>
							</td>
						</tr>
						
						<tr id="mailedFour">
							<th>
								<font color="red">*</font>�ʼ�״̬
							</th>
							<td colspan="3">
								<html:radio property="yjzt" styleId="yjzt" value="1" onchange="changeYjzt()">���ʼ�</html:radio>
								<html:radio property="yjzt" styleId="yjzt" value="2" onchange="changeYjzt()">δ�ʼ�</html:radio>
							</td>
						</tr>
						<tr id="mailedFive">
							<th>
								<font color="red">*</font>��ݷ�ʽ
							</th>
							<td>
								<html:text property="kdfs" styleId="kdfs" maxlength="32" style="width:150px" styleClass="text_nor" ></html:text>
							</td>
							<th>
								<font color="red">*</font>��ݵ���
							</th>
							<td>
								<html:text property="kddh" styleId="kddh" maxlength="32" style="width:150px" styleClass="text_nor" ></html:text>
							</td>
						</tr>
						<tr id="mailedSix">
							<th>
								<font color="red">*</font>�ʼ�ʱ��
							</th>
							<td colspan="3">
								<html:text property="yjsj" styleId="yjsj" onclick="return showCalendar('yjsj','y-mm-dd',true,'');"  
									maxlength="10" style="width:150px" styleClass="text_nor"></html:text>
							</td>
						</tr>
						
						
						<tr id="byoOne">
							<th>
								<font color="red">*</font>�Դ�������ŵ
							</th>
							<td>
								<logic:notEmpty name="dazccsszForm" property="uploadid">
									<input type="checkbox" id="zddacn" name="zddacn" value="1"/>
									�����Ĳ�����
									<a href="javascript:void(0);" onclick="window.open('common_upload.do?method=asyncDownload&fid=${dazccsszForm.uploadid}');return false;" class="name" style="margin-left: 0px;">
										������ת��Э�顷
									</a>
								</logic:notEmpty>
							</td>
							<th>
								<font color="red">*</font>Ԥ���ᵵ����
							</th>
							<td>
								<html:text property="yqtdrq" styleId="yqtdrq" onclick="return showCalendar('yqtdrq','y-mm-dd',true,'');"  
									maxlength="10" style="width:150px" styleClass="text_nor"></html:text>
							</td>
						</tr>
						<tr id="byoTwo">
							<th>
								ʵ���ᵵ����
							</th>
							<td>
								<html:text property="sjtdrq" styleId="sjtdrq" onclick="return showCalendar('sjtdrq','y-mm-dd',true,'');"  
									maxlength="10" style="width:150px" styleClass="text_nor"></html:text>
							</td>
							<th>
								ʵ���ᵵ��
							</th>
							<td>
								<html:text property="sjtdr" styleId="sjtdr" maxlength="16" style="width:150px" styleClass="text_nor" ></html:text>
							</td>
						</tr>
					</tbody>
				</table>
			</div>	
			<div style="height:30px;"></div>
			<div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"<span class="red">*</span>"Ϊ������
								</div>
								<div class="btn">
									<button type="button" onclick="dazcjgSave();">
										����
									</button>
									<button type="button" onclick="iFClose();">
										�ر�
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