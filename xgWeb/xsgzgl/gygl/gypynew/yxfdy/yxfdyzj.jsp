<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="xsgzgl/gygl/gypynew/yxfdy/js/yxfdy.js"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
				
	</head>
	<body>
		<html:form action="/gypy" method="post" styleId="form">
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>�������㸨��Ա</a>
			</p>
		</div>
		<html:hidden property="pylx" value="2"/>
		<div style='tab'>
			<table class="formlist"  width="95%">
			<tbody>
				<tr>
					<th width="20%" align="right"><font color="red">*</font>ְ���ţ�</th>
					<td width="30%">
					<html:text property="gldm" readonly="true" styleId="gldm" style="width:60%" />
					<html:hidden property="gldm" styleId="gldm" />
						<button class="btn_01" type="button"  
										onclick="showDialog('��ѡ��һ������Ա',680,480,'szdw_fdyjtff.do?method=showFdys&goto=${path}');return false;">ѡ��</button>
					</td>
					<th width="20%">������</th>
					<td width="30%">
							<logic:present name="jbxx">
									<bean:write name="jbxx" property="xm"/>
							</logic:present>
					</td>
				</tr>
				<tr>
					<th>�Ա�</th>
					<td>
							<logic:present name="jbxx">
									<bean:write name="jbxx" property="xbmc"/>
							</logic:present>
					</td>
					<th>�������ţ�</th>
					<td>
						<logic:present name="jbxx">
									<bean:write name="jbxx" property="bmmc"/>
						</logic:present>
					</td>
				</tr>
				<tr>
					<th><font color="red">*</font>ѧ�꣺</th>
					<td>
						<html:select property="xn" styleId="xn" >
							<html:options collection="xnList" labelProperty="xn" property="xn"/>
						</html:select>
					</td>
					<th><font color="red">*</font>ѧ��:</th>
					<td>
						<html:select property="xqdm" styleId="xqdm">
							<html:options collection="xqList" labelProperty="xqmc" property="xqdm"/>
						</html:select>
					</td>
				</tr>
				<tr>
					<th><font color="red">*</font> ��ѡ���ɣ�</th>
					<td colspan="3">
						<html:textarea styleId="pyly" property="pyly" rows="4" style="width:95%" onblur="checkLen(this,100)"/>
					</td>
				</tr>
				</tbody>
				<tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
			          <div class="btn">
			          	<button type="button" name="�ύ" id="buttonSave"  onclick="save('gypy.do?method=add&type=save','gldm-xn-xqdm-pyly');">����</button>
			            <button type="button" name="�ر�" id="buttonClose" onclick="Close();return false;">�ر�</button>
			          </div></td>
			      </tr>
			   </tfoot>
			</table>
		</div>
		</html:form>
	</body>

</html>