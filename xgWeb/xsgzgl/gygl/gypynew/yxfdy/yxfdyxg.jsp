<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="xsgzgl/gygl/gypynew/yxfdy/js/yxfdy.js"></script>
				<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
	</head>
	<body>
		<html:form action="/gypy" method="post" styleId="form">
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>�޸����㸨��Ա</a>
			</p>
		</div>
		<html:hidden property="gypyid" value="${data.gypyid}"/>
		<div style='tab'>
			<table class="formlist"  width="95%">
			<tbody>
				<tr>
					<th width="20%" align="right">ְ���ţ�</th>
					<td width="30%">
						${data.zgh}
					</td>
					<th width="20%">������</th>
					<td width="30%">
						${data.xm}
					</td>
				</tr>
				<tr>
					<th>�Ա�</th>
					<td>
						${data.xbmc}
					</td>
					<th>�������ţ�</th>
					<td>
						${data.bmmc}
					</td>
				</tr>
				<tr>
					<th>ѧ�꣺</th>
					<td>
					${data.xn }
						<%--<html:select property="xn" styleId="xn" >
							<html:options collection="xnList" labelProperty="xn" property="xn"/>
						</html:select>
					--%></td>
					<th>ѧ��:</th>
					<td>
					${data.xqmc }
						<%--<html:select property="xqdm" styleId="xqdm">
							<html:options collection="xqList" labelProperty="xqmc" property="xqdm"/>
						</html:select>
					--%></td>
				</tr>
				<tr>
					<th><font color="red">*</font> ��ѡ���ɣ�</th>
					<td colspan="3">
						<html:textarea styleId="pyly" property="pyly" rows="4" style="width:95%" onblur="checkLength(this,100)" value="${data.pyly}"/>
					</td>
				</tr>
				</tbody>
				<tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
			          <div class="btn">
			          	<button type="button" name="�ύ" id="buttonSave"  onclick="save('gypy.do?method=update&type=save','pyly');">����</button>
			            <button type="button" name="�ر�" id="buttonClose" onclick="Close();return false;">�ر�</button>
			          </div></td>
			      </tr>
			   </tfoot>
			</table>
		</div>
		</html:form>
	</body>

</html>