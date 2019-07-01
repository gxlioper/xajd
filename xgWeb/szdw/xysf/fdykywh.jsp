<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/commanFunction.js"></script>
	<script language="javascript" src="js/String.js"></script>
	<script language="javascript" src="js/check.js"></script>
	<script language="javascript" src="js/qtsjFunction.js"></script>
	<script type="text/javascript">
		function autoFillTeaInfo(cod){
			if(cod == 13){
				var url = $('url').value;
				document.forms[0].action = url;
				document.forms[0].submit();
			}
		}
		
		function tipsAndSave(){
			var zgh = $('zgh').value;
			if(zgh != ""){			
				BatAlert.showTips("���ڱ��棬���Ժ�");
				saveData('xysf_dyjsgl.do?method=addDyjs&doType=save','zgh');
			}else{
				alert("�뽫��\"*\"����Ŀ����������");
			}
		}
	</script>
</head>
<body>
	<html:form action="xysf_fdykywh.do" method="post">
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>${title }</a>
			</p>
		</div>
		<input type="hidden" id="url" name="url"
			value="xysf_fdykywh.do?method=kyxxwh" />
		<input type="hidden" id="getStuInfo" name="getStuInfo"
			value="xh-xm" />
		<button type="button" id="disbutton" onclick="autoFillTeaInfo(13);" style="display: none"></button>
		<div class="tab">
		<table class="formlist" width="100%">
			<thead>
				<tr style="height:22px">
					<th colspan="4">
						<span>����Ա������Ϣ</span>
					</th>
				</tr>
			</thead>
			<tbody>
			<tr>
				<th>
					<font color="red">*</font>ְ����
				</th>
				<td align="left" width="30%">
					<logic:equal value="true" name="isTea">
						<html:text property="save_zgh" styleId="zgh" value="${rs.zgh}" readonly="true"></html:text>
					</logic:equal>
					<logic:notEqual value="true" name="isTea">
					<html:text property="save_zgh" styleId="zgh" readonly="true"
						onchange="checkXhExists($('getStuInfo').value);"
						onkeypress="autoFillTeaInfo(event.keyCode)" />
					<button type="button" onclick="showTopWin('/xgxt/xysf_dyjsgl.do?method=getTeaInfo',750,550);"
						class="btn_01" id="buttonFindStu">
						ѡ��
					</button>
					</logic:notEqual>
				</td>
					
				<th>
					<div align="right">
						����
					</div>
				</th>
				<td width="34%">
					${rs.xm }
				</td>
			</tr>
		
			<tr>
				<th>
					�Ա�
				</th>
				<td>
					${rs.xb }
				</td>
				
				<th>
					ְ��
				</th>
				<td>
					${rs.zwmc }
				</td>
			</tr>
			
			<tr>
				<th>
					������
				</th>
				<td>
					${rs.bmmc }
				</td>
				
				<th>
					ѧ��
				</th>
				<td>
					${rs.xl }
				</td>
			</tr>
			
			<tr>
				<th>
					<font color="red">*</font>��������
				</th>
				<td>
					<html:text property="save_wzmc" styleId="wzmc" maxlength="50" onkeyup="chgPkValue(this);"></html:text>
				</td>
				
				<th>
					<font color="red">*</font>�����ڿ�
				</th>
				<td>
					<html:text property="save_fbqk" styleId="fbqk" maxlength="50" onkeyup="chgPkValue(this);"></html:text>
				</td>
			</tr>
			
			<tr>
				<th>
					�ڿ�����
				</th>
				<td>
					<html:text property="save_qkqs" maxlength="10"></html:text>
				</td>
				
				<th>
					�ڿ�����
				</th>
				<td>
					<html:text property="save_qkjb" maxlength="10"></html:text>
				</td>
			</tr>
			</tbody>
			
			<logic:equal value="yes" name="writeAble">
				<tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
			          <div class="btn">
			          		<button type="button" name="�ύ" onclick="saveDataShowTips('xysf_fdykywh.do?method=kyxxwh&doType=save','zgh-wzmc-fbqk');"">����</button>
		          	  </div>
	          		</td>
			      </tr>
				 </tfoot>
			</logic:equal>
		</table>
		</div>
		
	</html:form>
	<logic:present name="message">
		<input type="hidden" id="message" value="${message }" />
		<script type="text/javascript">
			alert(document.getElementById('message').value);

		</script>
	</logic:present>
</body>
</html>
