<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- ͷ�ļ� -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/interface/pjpyZjsyzy.js"></script>
	<script type="text/javascript" src="js/rcsw/rcswFunction.js"></script>
	<script type="text/javascript" src="js/checkUtils.js"></script>
	<script type="text/javascript">
	function checkZc() {
	
		var sfqf = $('sfqf').value;
		var isLstd = $('isLstd').value;
		var je = Number($('je').value);
		
		if ("��"==sfqf){
			if ($('buttonSave')){
				temp.innerHTML+="<br/><font color='red'>��ط���δ����!</font>";
				$('buttonSave').attachEvent('onclick',function(){
					if (confirm("��ѧ����ط���δ���룬��ȷ��Ҫע����")){
						saveUpdate('/xgxt/bdzcgl.do?method=bdzcOne&doType=modify','');
					}
				});
			}
			
		} else {
			if ($('buttonSave')){
				$('buttonSave').attachEvent('onclick',function(){
					saveUpdate('/xgxt/bdzcgl.do?method=bdzcOne&doType=modify','');
				});
			}
		}
	}
	
	function zc(code) {
		if (code == 13 && $('buttonSave')) {
			if ($('buttonSave')){
				$('buttonSave').click();
			}
		}
	}
	</script>
</head>
<body onload="checkZc();" onkeypress="zc(event.keyCode);">	
	<html:form action="/bdzcgl" method="post">
		<input type="hidden" name="message" id="message" value="${message }"/>
		<input type="hidden" name="pk" id="pkValue" value="${pkValue }"/>
		<input type="hidden" name="ysje" id="ysje" value="${rs.ysje }"/>
		<input type="hidden" name="ssje" id="ssje" value="${rs.ssje }"/>
		<input type="hidden" name="sfqf" id="sfqf" value="${rs.sfqf }"/>
		<input type="hidden" name="isLstd" id="isLstd" value="${rs.islstd }"/>
		<input type="hidden" name="je" id="je" value="${rs.je }"/>

		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>${title }</a>
			</p>
		</div>
		
		<div class="tab">
		<table class="formlist" width="100%">
			<tbody>
			<tr>
				<th></th>
				<th></th>
				<th></th>
				<th></th>
				<th></th>
				<th></th>
			</tr>
			<tr>
				<th>ѧ��</th>
				<td>
					${rs.xh }
					<html:hidden  property="save_xh" styleId="xh" value="${rs.xh}"/>
				</td>
				
				<th>����</th>
				<td>
					${rs.xm }
				</td>				
				<td colspan="2" rowspan="4">
					<img src="<%=request.getContextPath()%>/sjcz/xszptp.jsp?xh=${rs.xh }"
						border="0" align="absmiddle" style="width:140;height:160" />
				</td>
			</tr>
			<tr>
				<th>�Ա�</th>
				<td>${rs.xb }</td>
				<th><bean:message key="lable.xsgzyxpzxy" /></th>
				<td>${rs.xymc }</td>
				
			</tr>
			<tr>
				<th>רҵ</th>
				<td>${rs.zymc }</td>
				<th>�༶</th>
				<td>${rs.bjmc }</td>
				
			</tr>
			<tr>
				<th>ѧ��</th>
				<td>
					${rs.xn }
					<html:hidden property="save_xn" value="${rs.xn }"/>
				</td>
				<th>ѧ�ڣ�</td>
				<td>
					${rs.xqmc }
					<html:hidden property="save_xq" value="${rs.xq }"/>
					<html:hidden property="save_zczt" value="ע��"/>
				</td>
			</tr>
			<tr>
				<th>�Ƿ�Ƿ��</th>
				<td>
					${rs.sfqf }
				</td>
				<th>Ƿ�ѽ��</th>
				<td colspan="4">
					${rs.ysje-rs.ssje-rs.zxdkje }
				</td>
				
			</tr>
			<tr>
				<th>�Ƿ���ɫͨ��</th>
				<td>${rs.islstd }</td>
				<th>������ɫͨ�����</th>
				<td colspan="4">${rs.je}</td>
			</tr>
			<tr>
				<th>�Ƿ�������ѧ����</th>
				<td>${rs.sfzxdk }</td>
				<th>������ѧ������</th>
				<td colspan="4">${rs.zxdkje }</td>
			</tr>
			<tr>
				<td colspan="6">
					<logic:notEqual value="true" name="iszcsj">
						<font color="red">������ע��ʱ��Σ�����ע��!</font>
					</logic:notEqual>
					<div id="temp" align="left"></div>
				</td>
			</tr>
			</tbody>
			<tfoot>
		      <tr>
		        <td colspan="6"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
		          <div class="btn">
		            <logic:equal value="true" name="iszcsj">
						<logic:notEqual value="view" name="doType">
							<button type="button" class="button2" id="buttonSave">
								ע��
							</button>
						</logic:notEqual>
					 </logic:equal>
					 <button type="button" class="button2" onclick="Close();return false;">
						�ر�
					 </button>
		          </div>
		        </td>
		      </tr>
		    </tfoot>
		</table>
	</html:form>
	<logic:present name="result">
		<logic:equal value="true" name="result">
			<script>
				alert('�����ɹ�');
				if (window.dialogArguments) {
					window.close();
					window.dialogArguments.document.getElementById('search_go').click();
				}
			</script>
		</logic:equal>
	</logic:present>
</body>
</html>