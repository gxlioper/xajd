<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/qmlxdj/lxjg/js/lxjg.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/provicecitylocal.js"></script>
		<link rel="stylesheet" href="js/provicecitylocal.css" type="text/css"/>
		<script type="text/javascript">
			jQuery(function(){
				proviceCiyyLocalMain({type:"add",id:"mddssx",flag:"wxxdz"});
			})
		</script>
		<style type = "text/css">
		</style>
	</head>
	<body>
		<html:form action="/qmlxjg" method="post" styleId="LxjgForm">
			<div style="tab;overflow-x:hidden;overflow-y:auto;height:520px;margin-bottom:0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>������Ϣ</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/comm/bdpz/selectStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>��У������Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>ѧ��</th>
							<td>
								<html:select property="xn" styleId="xn" style="width:90%">
									<html:options collection="xnList" labelProperty="xn" property="xn"/>
								</html:select>
							</td>
							<th>ѧ��</th>
							<td>
								<html:select property="xq" styleId="xq" style="width:90%">
									<html:options collection="xqList" labelProperty="xqmc" property="xqdm"/>
								</html:select>
							</td>
						</tr>
						<tr>
							<th>�Ƿ���У</th>
							<td>
								<select name="sflxdm" id="sflxdm">
									<option value="��">��</option>
									<option value="��">��</option>
								</select>
							</td>
							<th><span class="red">*</span>��У����</th>
							<td>
								<html:select property="lxlx" styleId="lxlx" style="width:90%" >
									<option value=""></option>
									<html:options collection="lxlxList" labelProperty="mc" property="dm"/>
								</html:select>
							</td>
						</tr>
						<tr>
							<th><font color="red">*</font>�໤������</th>
							<td>
								<html:text property="jhrxm" styleId="jhrxm" style="width:90%" maxlength="10" />						
							</td>
							<th><font color="red">*</font>�໤����ϵ��ʽ</th>
							<td>
								<html:text property="jhrlxfs" styleId="jhrlxfs" style="width:90%" maxlength="12" onkeyup="checkInputLxfx(this)"/>
							</td>
						</tr>
						<tr>
							<th><font color="red">*</font>�Ƿ���໤����ϵ</th>
							<td>
								<html:radio property="sflx" value="��">��</html:radio>
								<html:radio property="sflx" value="��">��</html:radio>
							</td>
							<th><font color="red">*</font>��Уʱ��</th>
							<td>
								<html:text property="lxsj" styleId="lxsj" style="width:90%" maxlength="10" onclick="return showCalendar('lxsj','y-mm-dd',true,'fxsj');" />
							</td>
						</tr>
						<tr>
							<th><font color="red">*</font>��У��ͨ����</th>
							<td>
								<html:select property="lxjtgjdm" styleId="lxjtgjdm" style="width:90%" >
									<html:options collection="dmList" labelProperty="mc" property="dm"/>
								</html:select>
							</td>
							<th><font color="red">*</font>��У����/����</th>
							<td>
								
								<html:text property="lxcchb" styleId="lxcchb" style="width:90%"  maxlength="25" />
								
							</td>
						</tr>
						<tr>
							<th><font color="red">*</font>Ŀ�ĵ�</th>
							<td colspan="3">
								<html:hidden  property="mddssx" styleId="mddssx"/>
							</td>
						</tr>
						<tr>
							<th><font color="red">*</font>��Уʱ��</th>
							<td>
								<html:text property="fxsj" styleId="fxsj" maxlength="10" style="width:90%" onclick="return showCalendar('fxsj','y-mm-dd',false,'lxsj');" />
							</td>
							<th><font color="red">*</font>��У��ͨ����</th>
							<td>
								<html:select property="fxjtgjdm" styleId="fxjtgjdm" style="width:90%" >
									<html:options collection="dmList" labelProperty="mc" property="dm"/>
								</html:select>
								
							</td>
						</tr>
						<tr>
							<th><font color="red">*</font>��У����/����</th>
							<td>
								<html:text property="fxcchb" styleId="fxcchb" style="width:90%"  maxlength="25" />
							</td>
						</tr>
						<tr>
							<th>��ע
								</br><font color="red">(��500��)</font></th>
							<td colspan="3">
								<html:textarea property="bz" styleId="bz" 
											   onblur="checkLen(this,500);"
											   style="width:90%;" rows="5"></html:textarea>
							</td>
						</tr>
					</tbody>
				</table>
				</div>	
			<div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"<span class="red">*</span>"Ϊ������
								</div>
								<div class="btn">
									<button type="button" onclick="saveJg();">
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