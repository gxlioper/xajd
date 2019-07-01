<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- ͷ�ļ� -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script type="text/javascript" src="js/rcsw/rcswFunction.js"></script>
	<script language="javascript" src="js/xszzFunction.js"></script>
	<script language="javascript" src="js/String.js"></script>
	<script type="text/javascript" src="js/checkUtils.js"></script>
	<script type="text/javascript">
	</script>
</head>

<body>
	<html:form action="/jqlxgl" method="post">
		<input type="hidden" name="message" id="message" value="${message }"/>
		<input type="hidden" name="save_xn" value="${rs.xn }"/>
		<input type="hidden" name="save_xq" value="${rs.xq }"/>
		<input type="hidden" id="pkValue" name="pkValue" value="${rs.pk }"/>

		<div class="tab">
		<table class="formlist" width="100%">
			<thead>
		    	<tr>
		        	<th colspan="4"><span>������У����</span></th>
		        </tr>
		    </thead>
			<tbody>
			<tr>
				<th>ѧ��</th>
				<td>
					${rs.xn }
				</td>
				<th>ѧ��</th>
				<td>
					${rs.xqmc }
				</td>
			</tr>
			<tr>
				<th>ѧ��</th>
				<td>
					${map.xh }
					<input type="hidden" id="save_xh" name="save_xh" value="${map.xh }"/>
				</td>
				<th>����</th>
				<td>
					${map.xm }
				</td>
			</tr>
			<tr>
				<th>�Ա�</th>
				<td>${map.xb }</td>
				<th>����Ա</th>
				<td>
					${map.fdyxm }
				</td>
			</tr>
			<tr>
				<th><bean:message key="lable.xsgzyxpzxy" /></th>
				<td>${map.xymc }</td>
				<th>רҵ</th>
				<td>${map.zymc }</td>
			</tr>
			<tr>
				<th>�༶</th>
				<td>${map.bjmc }</td>
				<th>&nbsp;</th>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<th>��У��ʼʱ��</th>
				<td>
					<html:text property="save_lxkssj"
							onclick="return showCalendar('save_lxkssj','y-mm-dd');" styleClass="text_nor" styleId="save_lxkssj"
							readonly="true" value="${rs.lxkssj}"/>
				</td>
				<th>��У����ʱ��</th>
				<td>
					<html:text property="save_lxjssj"
							onclick="return showCalendar('save_lxjssj','y-mm-dd');" styleClass="text_nor" styleId="save_lxjssj"
							readonly="true" value="${rs.lxjssj}"/>
				</td>
			</tr>
			<tr>
				<th><logic:equal value="ok" name="buttonCtrl"><span class="red">*</span></logic:equal>��ϵ�绰</th>
				<td>
					<html:text property="save_lxdh" maxlength="20" value="${rs.lxdh}"></html:text>
				</td>
				<th>������</th>
				<td>
					<html:text property="save_ssbh" maxlength="20" value="${rs.ssbh}"></html:text>
				</td>
			</tr>
			<tr>
				<th><logic:equal value="ok" name="buttonCtrl"><span class="red">*</span></logic:equal>��������<br/><font color="red">(��������500��)</font></th>
				<td colspan="3">
					<html:textarea property="save_sqly" cols="60" rows="5" onblur="checkLeng(this,500)" value="${rs.sqly}"></html:textarea>
				</td>
			</tr>
			<tr>
				<th>��ע<br/><font color="red">(��������500��)</font></th>
				<td colspan="3">
					<html:textarea property="save_bz" cols="60" rows="5" onblur="checkLeng(this,500)" value="${rs.bz}"></html:textarea>
				</td>
			</tr>
			<logic:notEqual value="ok" name="buttonCtrl">
				<tr>
					<th>
						����Ա���״̬
					</th>
					<td colspan="3">
						${rs.fdysh }
					</td>
				</tr>
				<tr>
					<th>����Ա������</th>
					<td colspan="3">
						<html:textarea property="fdyshyj" cols="60" rows="5" value="${rs.fdyshyj}" readonly="true"></html:textarea>
					</td>
				</tr>
				<tr>
					<th>
						<bean:message key="lable.xb" />���״̬
					</th>
					<td colspan="3">
						${rs.xysh }
					</td>
				</tr>
				<tr>
					<th><bean:message key="lable.xb" />������</th>
					<td colspan="3">
						<html:textarea property="xyshyj" cols="60" rows="5" value="${rs.xyshyj}" readonly="true"></html:textarea>
					</td>
				</tr>
			</logic:notEqual>
			</tbody>
			<tfoot>
		      <tr>
		        <td colspan="4"><logic:equal value="ok" name="buttonCtrl"><div class="bz">"<span class="red">*</span>"Ϊ������</div></logic:equal>
		          <div class="btn">
		            <logic:equal value="ok" name="buttonCtrl">
						<button type="button" class="button2" id="buttonSave" onclick="saveUpdate('/xgxt/jqlxgl.do?method=jqlxsqDetial&doType=save','save_xh-save_lxdh-save_sqly');">
							��&nbsp;&nbsp;��
						</button>
					</logic:equal>
					<button type="button" class="button2" id="buttonSave" onclick="Close();return false;">
						��&nbsp;&nbsp;��
					</button>
		          </div>
		        </td>
		      </tr>
		    </tfoot>
		</table>
	  </div>
		
	</html:form>
	<logic:present name="result">
		<script>
			alert(''+$('message').value);
			if (window.dialogArguments) {
				window.close();
				window.dialogArguments.document.getElementById('search_go').click();
			}
		</script>
	</logic:present>
</body>
</html>
