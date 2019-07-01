<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="gygl/gyccgl/ccdj/js/ccdj.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
			jQuery(function(){
				lddmChange();
				chChange();
				changesShcd();
				qshChange();
			})
		</script>
	</head>
	<body>
		<html:form action="/gyccgl_ccdj" method="post" styleId="CcdjForm" onsubmit="return false;">
			<html:select property="shcdmark" styleId="shcd" style="display:none" >
				<html:options collection="shcdList" property="shcddm" labelProperty="je"/>
		    </html:select>
		    <html:hidden property="id" styleId="id" value="" />
			<input type="hidden" name="isexist" id="isexist" value="0" />
			<div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom:0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>������Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="20%"><font color="red">*</font>ѧ��</th>
							<td width="30%">
								<html:select property="xn" styleId="xn" style="width:98%">
									<html:options collection="xnList" property="xn" labelProperty="xn"/>
								</html:select>								
							</td>
							<th width="20%"><font color="red">*</font>ѧ��</th>
							<td width="30%">
								<html:select property="xq" styleId="xq" style="width:98%">
									<html:option value=""></html:option>
									<html:options collection="xqList" property="xqdm" labelProperty="xqmc"/>																					
								</html:select>
							</td>
						</tr>
						<tr>
							<th><font color="red">*</font>¥��</th>
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
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>�Ǽ���Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td colspan="4" >
								<table width="100%">
									<thead>
										<th width="30%" style="text-align:left">��Ʒ����</th>
										<th width="40%" style="text-align:left">�𺦳̶�</th>
										<th width="30%" style="text-align:left">�������(Ԫ)</th>
								    </thead>
								    <tbody id="iterate">
								    	<logic:iterate name="wpList" id="i">
								    		<tr>
								    			<td>${i.wpmc} <input name="wpdms" value="${i.wpdm}" type="hidden" /></td>
								    			<td>
								    				<html:select property="shcds" style="width:98%">
								    					<html:option value=""></html:option>
								    					<html:options collection="shcdList" property="shcddm" labelProperty="shcdmc"/>
								    				</html:select>
								    			</td>
								    			<td name="je" ></td>
								    		</tr>
								    	</logic:iterate>
								    </tbody>
								</table>
							</td>
						</tr>
						<tr>
							<th><font color="red">*</font>�����ܼ�(Ԫ)</th>
							<td colspan="3">
								<html:text property="zje" style="width:65%"  maxlength="8" styleId="zje" onkeyup="checkMoneyBykeyUpByone(this)" />
							</td>
						</tr>
						<tr>
							<th>��ע(��1000��)</th>
							<td colspan="3">
								<html:textarea rows="5" property="bz" styleId="bz" style="width:98%" onblur="checkLen(this,1000)" />
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
									<button type="button" onclick="saveForm();">
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