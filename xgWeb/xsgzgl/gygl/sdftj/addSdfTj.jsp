<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/gygl/sdftj/js/sdftj.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
			jQuery(function(){
				lddmChange();
				chChange();
			})
		</script>
		<style type = "text/css">
		</style>
	</head>
	<body>
		<html:form action="/gygl_sdftj" method="post" styleId="sdfTjForm">
			<div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom:0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>ˮ���ͳ��</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th><font color="red">*</font>���</th>
							<td>
								<html:select property="nd" styleId="nd" style="width:98%">
									<html:options collection="ndList" property="nd" labelProperty="nd"/>
								</html:select>								
							</td>
							<th><font color="red">*</font>����</th>
							<td>
								<html:select property="jd" styleId="jd" style="width:98%">
									<html:option value=""></html:option>
									<html:option value="��һ����">��һ����</html:option>																						
									<html:option value="�ڶ�����">�ڶ�����</html:option>																						
									<html:option value="��������">��������</html:option>																						
									<html:option value="���ļ���">���ļ���</html:option>																						
								</html:select>
							</td>
						</tr>
						<tr>
							<th><font color="red">*</font>¥������</th>
							<td>
								<html:select   property="lddm" styleId="lddm"  style="width:98%">
									<html:option value=""></html:option>
									<html:options collection="LddmList" labelProperty="ldmc" property="lddm" />
								</html:select>
							</td>
							<th><font color="red">*</font>���</th>
							<td>
								<select id="ch" name="ch" style="width:98%">
								</select>
							</td>
						</tr>
						<tr>
							<th><font color="red">*</font>���Һ�</th>
							<td>
								<select   name="qsh" id="qsh"  style="width:98%">
								</select>
							</td>
						</tr>
						<tr>
							<th><font color="red">*</font>ˮ�Ѻϼ�(Ԫ)</th>
							<td>
								
								<html:text property="sf" styleId="sf"  maxlength="8" onkeyup="checkMoneyBykeyUp(this)" style="width:98%"/>
								
							</td>
							<th><font color="red">*</font>��Ѻϼ�(Ԫ)</th>
							<td>
								
								<html:text property="df" styleId="df"  maxlength="8" onkeyup="checkMoneyBykeyUp(this)" style="width:98%"/>
								
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
									<button type="button" onclick="saveData('save');">
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