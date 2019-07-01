<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- ͷ�ļ� -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/stuinfoFunction.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/interface/getXjydInfo.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/interface/chkExists.js"></script>
	<script>
		function doSave(){
			var xh = document.getElementById('xh').value;
			var hkztdm = document.getElementById("hkztdm").value;
			
			if(xh==null || xh==""){
				alert("�뽫��*�ŵ���Ŀ��д����!");
				return false;
			}
			if(hkztdm==null || hkztdm==""){
				alert("�뽫��*�ŵ���Ŀ��д����!");
				return false;
			}
			chkExists.chkExist("hkztb","xh",xh,function(data){
			if(data==true){
				refreshForm('/xgxt/studentHkzt.do?method=modHkztInfo&doType=mod');			
			}else{		
				alert("�����ڴ�ѧ���Ļ���״̬��¼��");
				return false;
			}		
			});
		}
		
		function change(){
			var hkzt = document.getElementById("hkztdm").value;
			if(hkzt== "001"){
				document.getElementById("hkqcsj").value = "";
				document.getElementById("sfjf").value = "";
				document.getElementById("hkqcsj").disabled = "true";
				document.getElementById("sfjf").disabled = "true";
			}else{
				document.getElementById("hkqcsj").disabled = "";
				document.getElementById("sfjf").disabled = "";
			}
		}
	</script>
</head>
<body onload="change()">
	<html:form action="/studentHkzt.do">
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>ѧ����Ϣ - �������� - �������� - ��Ϣ�޸�</a>
			</p>
		</div>
		<div class="tab">
		<table width="100%" border="0" class="formlist">
		<thead>
			<tr>
				<th colspan="4"><span>ѧ��������Ϣ</span></th>
			 </tr>
		</thead>
		<tbody>
		<tr>
			<th><span class="red">*</span>ѧ��</th>
			<td>
				<input type="hidden" name="xh" id="xh" value="${rs.xh}"/>
				${rs.xh}
			</td>
			<th>����</th>
			<td>
				${rs.xm}
			</td>
		</tr>
		
		<tr>
			<th>�Ա�</th>
			<td>
				${rs.xb}
			</td>
			
			<th><bean:message key="lable.xsgzyxpzxy" /></th>
			<td>
				${rs.xymc}
			</td>
		</tr>
		
		<tr>
			<th>רҵ</th>
			<td>
				${rs.zymc}
			</td>
			<th>�༶</th>
			<td>
				${rs.bjmc}
			</td>
		</tr>
		
		<tr>
			<th>�꼶</th>
			<td>
				${rs.nj}
			</td>		
			<th>��Դ��</th>
			<td>
				${rs.syd}
			</td>
		</tr>

		<tr>
			<th>���֤��</th>
			<td>
				${rs.sfzh}
			</td>		
			<th>��ϵ�绰</th>
			<td>
				${rs.lxdh}
			</td>
		</tr>
		
		<tr>
			<th><span class="red">*</span>����״̬</th>
			<td>
				<html:select property="hkztdm" styleId="hkztdm" onchange="change()" name="rs">
					<html:options collection="hkztList" property="hkztdm" labelProperty="hkztmc"/>
			    </html:select>
			</td>
			<th>����Ǩ��ʱ��</th>
			<td>
				<html:text property="hkqrsj" readonly="true" onclick="return showCalendar('hkqrsj','y-mm-dd');" styleId="hkqrsj" name="rs"/>
			</td>
		</tr>
		
		<tr>
			<th>��ҵǨ�Ƶ�</th>
			<td>
				<html:text property="hkqcd" name="rs" styleId="hkqcd" maxlength="50"/>					
			</td>
			<th>����Ǩ��ʱ��</th>
			<td>
				<html:text property="hkqcsj" readonly="true" onclick="return showCalendar('hkqcsj','y-mm-dd');" styleId="hkqcsj" name="rs"/>
			</td>
		</tr>
		<tr>
			<th>�Ƿ�ɷ�</th>
			<td colspan="3">
				<html:select property="sfjf" styleId="sfjf" name="rs">
				<html:option value=""></html:option>
				<html:option value="�ѽ�">�ѽ�</html:option>
				<html:option value="δ��">δ��</html:option>	
				</html:select>
			</td>			
		</tr>
		<tr>
			<th>��ע</th>
			<td colspan="3">
				<html:textarea property="bz" name="rs" rows="3" cols="60" onclick="chLeng(this,300)"> </html:textarea>
			</td>			
		</tr>
		</tbody>
		<tfoot>
		      <tr>
		        <td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
				  <div class="btn">
		          <logic:notEqual value="student" name="user">
				  <logic:notEqual value="view" name="doType">
					<button type="button"
						onclick="doSave()"
						style="width:80px">
						�� ��
					</button>
					</logic:notEqual>
					</logic:notEqual>						
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" onclick="window.close();return false;"
						style="width:80px">
						�� ��
					</button>	
		          </div>
		        </td>
		      </tr>
		    </tfoot>		
		</table>

		<logic:notEmpty name="result">
			<logic:equal value="true" name="result">
				<script>
					alert("�����ɹ���");
					Close();
					if(window.dialogArguments){
						window.dialogArguments.document.getElementById('search_go').click();
					}
				</script>
			</logic:equal>
			<logic:equal value="false" name="result">
				<script>
					alert("����ʧ�ܣ�");
				</script>
			</logic:equal>
		</logic:notEmpty>
	</html:form>
</body>
</html>
