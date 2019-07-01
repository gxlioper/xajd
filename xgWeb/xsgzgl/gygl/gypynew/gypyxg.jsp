<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="xsgzgl/gygl/gypynew/js/gypy.js"></script>
				<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script>
			jQuery(function(){
				loadLdxx();
			});
		</script>
	</head>
	<body>
		<html:form action="/gypy" method="post" styleId="form">
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>�޸���������</a>
			</p>
		</div>
		<html:hidden property="pylx" value="${data.pylx}"/>
		<html:hidden property="xydm" styleId="${data.xydm}"/>
		<html:hidden property="gypyid" value="${data.gypyid}"/>
		<div style='tab'>
			<table class="formlist"  width="95%">
			<tbody>
				<tr>
					<th width="20%" align="right">¥����</th>
					<td width="30%">
						${data.ldmc}
					</td>
					<th width="20%">¥�㣺</th>
					<td width="30%">
						${data.ch}
					</td>
				</tr>
				<tr>
					<th>���Һţ�</th>
					<td>
						${data.qsh}
					</td>
					<th>���ҵ绰��</th>
					<td>
						${data.qsdh}
					</td>
				</tr>
				<tr>
					<th>ѧ�꣺</th>
					<td>
						${data.xn}
					</td>
					<th>ѧ��:</th>
					<td>
						${data.xqmc}
					</td>
				</tr>
				<tr>
					<th><font color="red">*</font>��ѡ���ɣ�</th>
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