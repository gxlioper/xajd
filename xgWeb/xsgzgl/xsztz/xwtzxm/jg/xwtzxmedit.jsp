<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/xsztz/xwtzxm/jg/js/xwtzxm.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
	
			jQuery(function(){
			});
		</script>
		<style type = "text/css">
		</style>
	</head>
	<body>
		<html:form action="/xwtzgl_xmjg" method="post" styleId="XwTzXmJgForm">
			<input name="sqid" value="${hdmap.sqid}" type="hidden" />
			<input name="xh" value="${xmjbxx.xh}" type="hidden" />
			<div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom:0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>������Ϣ</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/comm/bdpz/viewStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>��Ŀ��Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th><font color="red">*</font>��Ŀ����</th>
							<td>
								<html:text property="xmmc" styleId="xmmc" ></html:text>
							</td>
							<th><font color="red">*</font>��Ŀ����</th>
							<td >
								<html:select   property="xmjbdm" styleId="xmjbdm" style="width:154px" >
									<html:options collection="xmjb" property="xmjbdm" labelProperty="xmjbmc" />
								</html:select>
                            </td>
						</tr>
						<tr>
							<th><font color="red">*</font>ѧ��</th>
							<td>
								<html:select property="xn" styleId="xn" style="width:154px">
									<html:options collection="xnList" property="xn" labelProperty="xn" />
								</html:select>
							</td>
							<th><font color="red">*</font>ѧ��</th>
							<td>
								<html:select   property="xq" styleId="xq" style="width:154px">
									<html:options collection="xqList" property="xqdm" labelProperty="xqmc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th><font color="red">*</font>������Ŀ</th>
							<td>
								<html:select   property="sskmdm" styleId="sskmdm" style="width:154px" >
									<html:options collection="sskm" property="sskmdm" labelProperty="sskmmc" />
								</html:select>
							</td>
							<th>��Ŀ��ʼʱ��</th>
							<td>
								<html:text property="xmkssj" styleId="xmkssj" onclick="return showCalendar('xmkssj','y-mm-dd');"  maxlength="10" onblur="dateFormatChg(this);"></html:text>
							</td>
						</tr>
						<tr>
							<th>�μӵص�</th>
							<td >
								<html:text property="cjdd" styleId="cjdd" ></html:text>
							</td>
							<th>��ϵ��ʽ
							</th>
							<td>
								<html:text property="lxfs" styleId="lxfs" maxlength="11" onkeyup="checkInput(this)" ></html:text>
							</td>
						</tr>
						<tr>
							<th>��ý���</th>
							<td >
								<html:text property="hdjx" styleId="hdjx" ></html:text>
							</td>
							<th><font color="red">*</font>���ѧ��</th>
							<td >
								<html:text property="zxf" styleId="zxf"  maxlength="3" onkeyup="checkInputNum(this)"></html:text>
							</td>
						</tr>
						<tr>
							<th>��������</br><font color="red">(��500��)</font></th>
							<td colspan="3">
								<html:textarea property="sqly" styleId="sqly" 
								   onkeyup=";" 
								   style="width:99%;" rows="3"></html:textarea>
							</td>
						</tr>
					</tbody>
				</table>
				</div>	
				<div style="height:30px;"></div>
				<%--;height:520px --%>
			<div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"<span class="red">*</span>"Ϊ������
								</div>
								<div class="btn">
									<button type="button" id="bc" onclick="saveSqjgUpdate('update');">
										��    ��
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