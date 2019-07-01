<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script language="javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl/rwgl/rwtw/js/rwxfDgbc.js"></script>
		
	</head>
	<body>
		
		<html:form action="/rwgl_rwxfbcgl" method="post" styleId="rwxfbcglForm">
			<input type="hidden" id="guid" name="guid" value="${mkxxForm.guid }"/>
			<div style="tab;overflow-x:hidden;overflow-y:auto;height:490px;margin-bottom: 0px;" >
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
								<span>������Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
					    <tr>
							<th width="16%">����ѧ��</th>
							<td width="34%">
								${rwxx.rwxn }
							</td>
							<th width="16%">����ʱ��</th>
							<td width="34%">
								${rwxx.rwsj }
							</td>
					    </tr>
					    <tr>
							<th width="16%">�����</th>
							<td colspan="3">
								<!-- ���ݴ�ѧ -->
								<logic:equal name="xxdm" value="10351">	
									${rwxx.rwdwdmc }
								</logic:equal>
								<logic:notEqual name="xxdm" value="10351">	
									${rwxx.rwd }
								</logic:notEqual>
							</td>
					    </tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>����ѧ�Ѳ�����Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
					    <tr>
							<th width="16%"><span class="red">*</span>ѧ��</th>
							<td width="34%">
								<html:select  property="xn" styleId="xn" style="width:155px">
								<html:options collection="xnList" labelProperty="xn" property="xn"/>
								</html:select>
							</td>
							<th width="16%"><span class="red">*</span>ѧ�Ѳ������</th>
							<td width="34%">
								<html:text property="xfbcje" styleId="xfbcje" maxlength="8" onkeyup="checkInputNum(this);"/>
							</td>
					    </tr>
					    <tr>
							<th width="16%"><span class="red">*</span>ѧ�Ѳ���ʱ��</th>
							<td width="34%">
								<html:text property="xfbcsj" styleId="xfbcsj" maxlength="10" onclick="return showCalendar('xfbcsj','y-mm-dd');" readonly="true"/>
							</td>
							<th width="16%"></th>
							<td width="34%">
							</td>
					    </tr>
					    <tr>
							<th width="16%">
								��ע
								<br /><font color="red">(������500����)</font>
							</th>
							<td colspan="4">
								<html:textarea property="bz" style="width:95%" styleId="bz" rows='5' onblur="checkLen(this,500);"/>
							</td>
			     		 </tr>
					</tbody>
				</table>
			</div>
		
			<div>
				<table class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"Ϊ������</div>
								<div class="btn">
									<button type="button" onclick="saveForm();">
										�ύ
									</button>
									<button type="button" onclick="iFClose();">
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