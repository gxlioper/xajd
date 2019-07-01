<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="xsgzgl/gygl/gypynew/js/gypy.js"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
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
				<em>���ĵ�ǰλ��:</em><a>������������</a>
			</p>
		</div>
		<html:hidden property="pylx" value="1"/>
		<html:hidden property="xydm" styleId="xydm"/>
		
		<div style='tab'>
			<table class="formlist"  width="95%">
			<tbody>
				<tr>
					<th width="20%" align="right"><font color="red">*</font>¥����</th>
					<td width="30%">
						<html:select property="lddm" styleId="lddm" onchange="loadLdxx();">
							<html:options collection="ldList" labelProperty="ldmc" property="lddm"/>
						</html:select>
					</td>
					<th width="20%">¥�㣺</th>
					<td width="30%">
						<html:select property="ch" styleId="ch" onchange="loadQs();">
						</html:select>
					</td>
				</tr>
				<tr>
					<th><font color="red">*</font>���Һţ�</th>
					<td>
						<html:select property="qsh" styleId="qsh" onchange="loadQsdh();">
						</html:select>
					</td>
					<th>���ҵ绰��</th>
					<td>
						<input id="qsdh" type="text" disabled="disabled" style="width:90px;"/>
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
					<th><font color="red">*</font>��ѡ���ɣ�</th>
					<td colspan="3">
						<html:textarea styleId="pyly" property="pyly" rows="4" style="width:95%" onblur="checkLen(this,100)"/>
					</td>
				</tr>
				</tbody>
				<tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
			          <div class="btn">
			          	<button type="button" name="�ύ" id="buttonSave"  onclick="save('gypy.do?method=add&type=save','lddm-qsh-xn-xqdm-pyly');">����</button>
			            <button type="button" name="�ر�" id="buttonClose" onclick="Close();return false;">�ر�</button>
			          </div></td>
			      </tr>
			   </tfoot>
			</table>
		</div>
		</html:form>
	</body>

</html>