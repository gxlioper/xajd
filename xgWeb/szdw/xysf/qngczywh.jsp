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
	<html:form action="xysf_qngczywh.do" method="post">
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>${title }</a>
			</p>
		</div>
		<input type="hidden" id="url" name="url"
			value="/xysf_qngczywh.do?method=qngczywh" />
		<input type="hidden" id="getStuInfo" name="getStuInfo"
			value="xh-xm" />
		<button type="button" id="disbutton" onclick="autoFillTeaInfo(13);" style="display: none"></button>
		<div class="tab">
		<table class="formlist" width="100%">
			<thead>
				<tr style="height:22px">
					<th colspan="4">
						<span>���깲����������ѧУ����</span>
					</th>
				</tr>
			</thead>
			<tbody>
			<tr>
				<th>
					<font color="red">*</font>ѧ��
				</th>
				<td align="left" width="30%">
					<html:text property="save_xh" styleId="xh" readonly="readonly"
							onchange="checkXhExists($('getStuInfo').value);"
							onkeypress="autoFillStuInfo(event.keyCode,this)" />
						<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
							class="btn_01" id="buttonFindStu">
							ѡ��
						</button>
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
					�꼶
				</th>
				<td>
					${rs.nj }
				</td>
			</tr>
			
			<tr>
				<th>
					<bean:message key="lable.xb" />
				</th>
				<td>
					${rs.xymc }
				</td>
				
				<th>
					רҵ
				</th>
				<td>
					${rs.zymc }
				</td>
			</tr>
			<tr>
				<th>
					�༶
				</th>
				<td>
					${rs.bjmc }
				</td>
				
				<th>
					<font color="red">*</font>��ѵ����
				</th>
				<td>
					<html:text property="save_pxpc" styleId="pxpc" onkeyup="chgPkValue(this);"></html:text>
				</td>
			</tr>
			
			<tr>
				<th>
					���Գɼ�
				</th>
				<td>
					<html:text property="save_kscj" maxlength="4" onblur="checkInputData(this);"></html:text>
				</td>
				
				<th>
					ѧϰ����
				</th>
				<td>
					<html:text property="save_xxpj" styleId="xxpj"></html:text>
				</td>
			</tr>
			
			<tr>
				<th>��ע</th>
				<td colspan="3">
					<html:textarea property="save_bz" rows="5" style="width:99%" onblur="chLeng(this,400)"></html:textarea>
				</td>
			</tr>
			</tbody>
			
			<logic:equal value="yes" name="writeAble">
				<tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
			          <div class="btn">
			          		<button type="button" name="�ύ" onclick="saveDataShowTips('xysf_qngczywh.do?method=qngczywh&doType=save','xh-pxpc');"">����</button>
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
