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
	<html:form action="/xszcgl" method="post">
		<input type="hidden" name="message" id="message" value="${message }"/>
		<input type="hidden" id="pkValue" name="pkValue" value="${rs.pk }"/>
		<input type="hidden" id="save_xn" name="save_xn" value="${rs.xn }"/>
		<input type="hidden" id="save_xq" name="save_xq" value="${rs.xq }"/>
		<input type="hidden" id="save_xh" name="save_xh" value="${rs.xh }"/>
		<input type="hidden" id="save_zczt" name="save_zczt" value="${rs.type }"/>

		<div class="tab">
		<table class="formlist" width="100%">
			<thead>
		    	<tr>
		        	<th colspan="4"><span>��ϸ���</span></th>
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
					${rs.xq }
				</td>
			</tr>
			<tr>
				<logic:equal value="view" name="rs" property="type">
				<logic:equal value="��ע��" name="rs" property="zczt">
					<th>ע��״̬</th>
					<td colspan="3">
						${rs.zczt }
					</td>
				</logic:equal>
				<logic:notEqual value="��ע��" name="rs" property="zczt">
					<th>ԭ��</th>
					<td colspan="3">
						<html:textarea property="save_yy" cols="40" rows="7" onblur="checkLeng(this,500)" value="${rs.yy}" readonly="true"></html:textarea>
					</td>
				</logic:notEqual>
				</logic:equal>
				<logic:notEqual value="view" name="rs" property="type">
					<th><span class="red">*</span>ԭ��</th>
					<td colspan="3">
						<html:textarea property="save_yy" cols="40" rows="7" onblur="checkLeng(this,500)" value="${rs.yy}"></html:textarea>
					</td>
				</logic:notEqual>
			</tr>
			</tbody>
			<tfoot>
		      <tr>
		        <td colspan="4"><logic:notEqual value="view" name="rs" property="type"><div class="bz">"<span class="red">*</span>"Ϊ������</div></logic:notEqual>
		          <div class="btn">
					<logic:notEqual value="view" name="oper">
					<button type="button" class="button2" id="buttonSave" onclick="saveUpdate('/xgxt/xszcgl.do?method=xszcsqyydetail&doType=save','save_yy-');">
						��&nbsp;&nbsp;��
					</button>
					</logic:notEqual>
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
