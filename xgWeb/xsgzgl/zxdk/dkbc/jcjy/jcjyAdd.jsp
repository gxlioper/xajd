<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="<%=stylePath%>js/lhgdialog/lhgdialog.min.js?self=true"></script>		
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script language="javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/zxdk/dkbc/jcjy/js/jcjy.js"></script>
	</head>
	<body>
		<html:form action="/jcjy_bcdc" method="post" styleId="jcjyModel">
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
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
								<span>��������</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
				    		<th>�������</th>
				    		<td>
								<html:select property="dclb" styleId="dclb" style="width:150px">
									<option value="">--��ѡ��--</option>
									<option value="ѧ�Ѵ���">ѧ�Ѵ���</option>
									<option value="�������">�������</option>
								</html:select>
							</td>
							<th>�����Ƿ���ȫ</th>
							<td>
				    			<html:select property="clsfqq" styleId="clsfqq" style="width:150px">
				    				<option value="">--��ѡ��--</option>
				    				<option value="��">��</option>
				    				<option value="��">��</option>
				    			</html:select>
				    		</td>
					    </tr>
					    <tr>
				    		<th>��ҵʱ��</th>
				    		<td>
								<html:text property="bysj" styleId="bysj" onfocus="return showCalendar(this.id,'yyyy-MM');" maxlength="7" style="width:150px"></html:text>
							</td>
							
							<th>������ϵ��ʽ</th>
							<td>
								 <html:text property="jrlxfs"  styleId="jrlxfs"maxlength="12" style="width:150px"></html:text>
							</td>
					    </tr>
					    <tr>
					    	<th>��ҵ��λ����</th>
							<td>
								 <html:text property="jydwmc"  styleId="jydwmc"maxlength="12" style="width:150px"></html:text>
							</td>
							<th>��ҵ��λ��ϸ��ַ</th>
							<td>
								 <html:text property="jydwdz"  styleId="jydwdz"maxlength="12" style="width:150px"></html:text>
							</td>		 
					    </tr>
					    <tr>
					    	<th>�Ƿ�Ϊ������<br />���ڵ�</th>
				    		<td>
				    			<html:select property="sfwxzfsfz" styleId="sfwxzfsfz" style="width:150px">
				    				<option value="">--��ѡ��--</option>
				    				<option value="��">��</option>
				    				<option value="��">��</option>
				    			</html:select>
				    		</td>
							<th>��ҵ��� </th>
							<td>
							   	<html:select property="hylb" styleId="hylb" style="width:150px">
									<html:option value="">��ѡ��</html:option>
									<html:options collection="hylbList" property="hylbdm" labelProperty="hylbmc" />
								</html:select>
							</td>
					    </tr>
					    <tr>
					    	<th>��ҵ��λ����<br />������ϵ�绰</th>
				    		<td>
								 <html:text property="jydwlxdh" styleId="jydwlxdh" maxlength="12" style="width:150px" onkeyup="value=value.replace(/[^\d]/g,'')"></html:text>
							</td>
							<th>��ǩ���������� </th>
							<td>
								 <html:text property="qdfwnx" styleId="qdfwnx" maxlength="2" style="width:150px" onkeyup="value=value.replace(/[^\d]/g,'')"></html:text>
							</td>
					    </tr>
					    
					    <tr>
					    	<th>Ӧ����ѧ��<br />��Ԫ��</th>
				    		<td>
								 <html:text property="yjnxf" styleId="yjnxf" maxlength="6" style="width:150px" onkeyup="value=value.replace(/[^\d]/g,'')"></html:text>
							</td>
							<th>ʵ�ʽ���ѧ��<br />��Ԫ��</th>
							<td>
								 <html:text property="sjjnxf" styleId="sjjnxf" maxlength="6" style="width:150px" onkeyup="value=value.replace(/[^\d]/g,'')"></html:text>
							</td>
					    </tr>
					    <tr>
					    	<th>������Ԫ��</th>
				    		<td>
								 <html:text property="dkje" styleId="dkje" maxlength="6" style="width:150px" onkeyup="value=value.replace(/[^\d]/g,'')"></html:text>
							</td>
							<th>������������</th>
							<td>
								 <html:text property="yh" styleId="yh" style="width:150px" ></html:text>
							</td>
					    </tr>
					    <tr>
					    	<th>�����ͬ��</th>
							<td>
								<html:textarea property="hth"  style="width:98%" styleId="hth" rows='3' onblur="checkLen(this,500);" style="width:150px"/>
							</td>
							<th>������ֹʱ��</th>
							<td>
								<html:textarea property='dkkssj' style="width:98%" styleId="dkkssj" rows='3' onblur="checkLen(this,500);" style="width:150px"/>
							</td>
					    </tr>
					    <tr>
					    	<th>���벹����Ԫ��</th>
				    		<td>
								 <html:text property="sqbcje" styleId="sqbcje" maxlength="6" style="width:150px" onkeyup="value=value.replace(/[^\d]/g,'')"></html:text>
							</td>
							<th>��׼��������<br />��Ԫ��</th>
					    	<td>
								 <html:text property="pzbcdcje" styleId="pzbcdcje" maxlength="6" style="width:150px" onkeyup="value=value.replace(/[^\d]/g,'')"></html:text>
							</td>
					    </tr>
					    <tr>
					    	<th>��һ�δ������</th>
					    	<td>
								 <html:text property="dicdc" styleId="dicdc" maxlength="6" style="width:150px" onkeyup="value=value.replace(/[^\d]/g,'')"></html:text>
							</td>
							<th>��һ�δ���ʱ��</th>
				    		<td>
								<html:text property="dicdcsj" styleId="dicdcsj" onfocus="return showCalendar(this.id,'yyyy-MM-dd');" maxlength="7" style="width:150px"></html:text>
							</td>
						</tr>
						<tr>
					    	<th>�ڶ��δ������</th>
					    	<td>
								 <html:text property="decdc" styleId="decdc" maxlength="6" style="width:150px" onkeyup="value=value.replace(/[^\d]/g,'')"></html:text>
							</td>
							<th>�ڶ��δ���ʱ��</th>
				    		<td>
								<html:text property="decdcsj" styleId="decdcsj" onfocus="return showCalendar(this.id,'yyyy-MM-dd');" maxlength="7" style="width:150px"></html:text>
							</td>
						</tr>
						<tr>
					    	<th>�����δ������</th>
					    	<td>
								 <html:text property="dscdc" styleId="dscdc" maxlength="6" style="width:150px" onkeyup="value=value.replace(/[^\d]/g,'')"></html:text>
							</td>
							<th>�����δ���ʱ��</th>
				    		<td>
								<html:text property="dscdcsj" styleId="dscdcsj" onfocus="return showCalendar(this.id,'yyyy-MM-dd');" maxlength="7" style="width:150px"></html:text>
							</td>
						</tr>
						<tr>
							<th>�Ƿ���ְ�ڸ�</th>
							<td>
				    			<html:select property="sfzzzg" styleId="sfzzzg" style="width:150px">
				    				<option value="">--��ѡ��--</option>
				    				<option value="��">��</option>
				    				<option value="��">��</option>
				    			</html:select>
				    		</td>
				    		<th>��ְ����Ƿ�Ϊ����<br />��������Ρ�����<br />��Ҫ����</th>
							<td>
				    			<html:select property="lzsfzc" styleId="lzsfzc" style="width:150px">
				    				<option value="">--��ѡ��--</option>
				    				<option value="��">��</option>
				    				<option value="��">��</option>
				    			</html:select>
				    		</td>
					    </tr>
					    <tr>
					    	<th>������Ϣ�ز����</th>
					    	<td>
								 <html:text property="ylzd1" styleId="ylzd1" maxlength="6" style="width:150px" onkeyup="value=value.replace(/[^\d]/g,'')"></html:text>
							</td>
							<th>������Ϣ�ز�ʱ��</th>
				    		<td>
								<html:text property="ylzd2" styleId="ylzd2" onfocus="return showCalendar(this.id,'yyyy-MM-dd');" maxlength="7" style="width:150px"></html:text>
							</td>
						</tr>
					    <tr>
					    	<th>�����Ƿ�����ȫ����</th>
							<td>
				    			<html:select property="dksfwqch" styleId="dksfwqch" style="width:150px">
				    				<option value="">--��ѡ��--</option>
				    				<option value="��">��</option>
				    				<option value="��">��</option>
				    			</html:select>
				    		</td>
					    </tr>
					    <tr>
							<th>
								��ע
								<br /><font color="red">&lt;��500��&gt;</font>
							</th>
							<td colspan="3">
								<html:textarea property='bz' style="width:98%" styleId="bz" rows='5' onblur="checkLen(this,500);"/>
							</td>
			      		</tr>
					</tbody>
				</table>
			</div>
			<div style="height:30px"></div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"Ϊ������</div>
								<div class="btn">
									<button type="button" id="bc" onclick="saveJcjy('save');">
										����
									</button>
									<button type="button" type="button" onclick="iFClose();">
										�� ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
		</html:form>
	</body>
</html>