<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		
		
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script language="javascript">
	function add(){
		    
	      var sydw = document.getElementById("sydw").value; 
	      var sqrxm = document.getElementById("sqrxm").value; 
	      var rs = document.getElementById("rs").value; 
	      var xslx = document.getElementById("xslx").value; 
	      var hdnr = document.getElementById("hdnr").value; 
	      var bz = document.getElementById("bz").value; 
	
	     
	      if(sydw==""){
	      alert("ʹ�õ�λ����Ϊ�գ�");
	      return false;
	      }
	      if(sqrxm==""){
	      alert("��������������Ϊ�գ�");
	      return false;
	      }
	     
	      if(rs != ""){
	      if(!isNumber(rs)){
		      alert("����ֻ�������֣�");
		      return false;
		      }
	      }

	      if(hdnr!=""){
		      if(hdnr.length>600){
		    	  alert("����ݲ��ܳ���600������");
			      return false;
		      }
	      }
	      if(bz!=""){
		      if(bz.length>300){
		    	  alert("��ע���ܳ���300������");
			      return false;
		      }
	      }
	        BatAlert.showTips('�����ύ�����Ժ�...');
	        if(xslx=="add"){
				 document.forms[0].action = "zgdzdxXxwh.do?method=dyhdsgl_save&xxk=cdsbsy&czlx=save&act=save";
	        }
	        if(xslx=="update"){
	        	document.forms[0].action = "zgdzdxXxwh.do?method=dyhdsgl_save&xxk=cdsbsy&czlx=update&act=update";
	        }
			 document.forms[0].submit();
    }
	 function isNumber(s){
			var p = /^(-|\+)?\d+$/;
			return p.test(s); 
		    }  
	function sh(){
		BatAlert.showTips('�����ύ�����Ժ�...');
		document.forms[0].action = "zgdzdxXxwh.do?method=dyhdsgl_sh&xxk=cdsbsy&act=sh";
		document.forms[0].submit();
	}	
	</script>
	
	</head>
	<body>
		<html:form action="/zgdzdxXxwh.do" method="post">
			<input id="xslx" type="hidden" name="xslx" value="<bean:write name="xslx"/>"/>

			<div class="tab">
			<table width="100%" class="formlist">
			
			<tfoot>
		      <tr>
		        <td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
		          <div class="btn">
		      <logic:equal value="update" name="xslx">
				<div align="center">
				<button type="button" onclick="add();" >
					�� ��
				</button>
				<button type="button" onclick="Close();return false;">
					�� ��
				</button>
			</div>
			</logic:equal>
			<logic:equal value="add" name="xslx">
			<div align="center">
				<button type="button" onclick="add();">
					�� ��
				</button>
				<button type="button" onclick="Close();window.dialogArguments.document.getElementById('search_go').click();">
					�� ��
				</button>
			</div>
			</logic:equal>
			<logic:equal value="sh" name="xslx">
			<div align="center">
				<button type="button" onclick="sh();">
					�� ��
				</button>
				<button type="button" onclick="Close();window.dialogArguments.document.getElementById('search_go').click();">
					�� ��
				</button>
			</div>
			</logic:equal>
		          </div></td>
		      </tr>
		    </tfoot>
			
			<logic:equal value="add" name="xslx">
				<thead>
					<tr>
						<th colspan="4" height="">
							<span>���ء��豸ʹ������</span>
						</th>
					</tr>
				</thead>
				
				<tbody>	
				<tr>
					<th>
						<font color="red">*</font>���
					</th>
					<td>
						<html:text property="xh" maxlength="20"></html:text>
					</td>
					<th nowrap="nowrap">
						
					</th>
					<td>  
						
					</td>
				</tr>	
				<tr>
					<th>
						<font color="red">*</font>ʹ�õ�λ
					</th>
					<td>
						<html:text property="sydw" maxlength="60"></html:text>
					</td>
					<th nowrap="nowrap">
						<font color="red">*</font>����������
					</th>
					<td>  
						<html:text property="sqrxm" maxlength="60"></html:text>
					</td>
				</tr>
				<tr>		
					
					<th nowrap="nowrap">
						�����˰༶
					</th>
					<td>
						<html:text property="sqrbj" maxlength="60"></html:text>
					</td>
					<th>
						����
					</th>
					<td>  
						<html:text property="rs" maxlength="10"></html:text>
					</td>
				</tr>
				<tr>		
					
					<th>
						ʹ��ʱ��	
					</th>
					<td>
						<html:text property="sysj" maxlength="20" onclick="return showCalendar('sysj','y-mm-dd');"></html:text>
					</td>
					<th>
						�Ƿ�����	
					</th>
					<td>
						<html:select property="sfsp">
							<html:option value=""></html:option>
							<html:option value="������">������</html:option>
							<html:option value="δ����">δ����</html:option>
						</html:select>
					</td>
				</tr>
				<tr>
					<th>
						��ע
					</th>
					<td colspan="3">  
						<html:textarea property="bz" rows="5" style="width :100%"></html:textarea>
					</td>
					
				</tr>
				<tr>
					<th>
						�����
					</th>
					<td colspan="3">  
						<html:textarea property="hdnr" rows="5" style="width :100%"></html:textarea>
					</td>
					
				</tr>
				</tbody>
				</logic:equal>
				<logic:equal value="update" name="xslx">
				<thead>
					<tr>
						<td align="center" colspan="4" height="">
							<b>���ء��豸ʹ���޸�</b>
						</td>
					</tr>
				</thead>
				<tbody>	
					<tr>
					<th>
						<font color="red">*</font>���
					</th>
					<td>
						<html:text name="rs" property="xh" maxlength="20"></html:text>
					</td>
					<th nowrap="nowrap">
						
					</th>
					<td>  
						
					</td>
				</tr>		
				<tr>
					<th>
						<font color="red">*</font>ʹ�õ�λ
					</th>
					<td>
						<input id="pkValue" type="hidden" name="pkValue" value="<bean:write name="rs" property="id"/>"/> 
						<html:text name="rs" property="sydw" maxlength="60"></html:text>
					</td>
					<th nowrap="nowrap">
						<font color="red">*</font>����������
					</th>
					<td>  
						<html:text name="rs" property="sqrxm" maxlength="60"></html:text>
					</td>
				</tr>
				<tr>		
					<th nowrap="nowrap">
						�����˰༶
					</th>
					<td>
						<html:text name="rs" property="sqrbj" maxlength="60"></html:text>
					</td>
					<th>
						����
					</th>
					<td>  
						<html:text name="rs" property="rs" maxlength="10"></html:text>
					</td>
				</tr>
				<tr>		
					<th>
						ʹ��ʱ��	
					</th>
					<td>
						<html:text name="rs" property="sysj" onclick="return showCalendar('sysj','y-mm-dd');" maxlength="20"></html:text>
					</td>
					<th>
						�Ƿ�����	
					</th>
					<td>
						<html:select name="rs" property="sfsp">
							<html:option value=""></html:option>
							<html:option value="������">������</html:option>
							<html:option value="δ����">δ����</html:option>
						</html:select>
					</td>
				</tr>
				<tr>
					<th>
						��ע
					</th>
					<td colspan="3">  
						<html:textarea name="rs" property="bz" rows="5" style="width :100%"></html:textarea>
					</td>
					
				</tr>
				<tr>
					<th>
						�����
					</th>
					<td colspan="3">  
						<html:textarea name="rs" property="hdnr" rows="5" style="width :100%"></html:textarea>
					</td>
				</tr>
				</tbody>
				</logic:equal>
				<logic:equal value="view" name="xslx">
				<thead>
					<tr>
						<th colspan="4">
							<span>���ء��豸ʹ�ò鿴</span>
						</th>
					</tr>
				</thead>
				<tbody>
					<tr>
					<th>
						<font color="red">*</font>���
					</th>
					<td>
						<html:text name="rs" property="xh" maxlength="20" readonly="true"></html:text>
					</td>
					<th nowrap="nowrap">
						
					</th>
					<td>  
						
					</td>
				</tr>		
				<tr>
					<th>
						<font color="red">*</font>ʹ�õ�λ
					</th>
					<td>
						<html:text name="rs" property="sydw" maxlength="60" readonly="true"></html:text>
					</td>
					<th nowrap="nowrap">
						<font color="red">*</font>����������
					</th>
					<td>  
						<html:text name="rs" property="sqrxm" maxlength="60" readonly="true"></html:text>
					</td>
				</tr>
				<tr>		
					
					<th nowrap="nowrap">
						�����˰༶
					</th>
					<td>
						<html:text name="rs" property="sqrbj" maxlength="60" readonly="true"></html:text>
					</td>
					<th>
						����
					</th>
					<td>  
						<html:text name="rs" property="rs" maxlength="10" readonly="true"></html:text>
					</td>
				</tr>
				<tr>		
					
					<th>
						ʹ��ʱ��	
					</th>
					<td>
						<html:text name="rs" property="sysj" maxlength="90" readonly="true"></html:text>
					</td>
					<th>
						�Ƿ�����	
					</th>
					<td>
						<html:text name="rs" property="sfsp" maxlength="90" readonly="true"></html:text>
					</td>
				</tr>
				<tr>
					<th>
						��ע
					</th>
					<td colspan="3">  
						<html:textarea name="rs" property="bz" rows="5" style="width :100%" readonly="true"></html:textarea>
					</td>
					
				</tr>
				<tr>
					<th>
						�����
					</th>
					<td colspan="3">  
						<html:textarea name="rs" property="hdnr" rows="5" style="width :100%" readonly="true"></html:textarea>
					</td>
				</tr>
				</tbody>
				</logic:equal>
				<logic:equal value="sh" name="xslx">
				<thead>
					<tr>
						<th colspan="4">
							<span>���ء��豸ʹ�����</span>
						</th>
					</tr>
				</thead>
				<tbody>
					<tr>
					<th>
						<font color="red">*</font>���
					</th>
					<td>
						<html:text name="rs" property="xh" maxlength="20"></html:text>
					</td>
					<th nowrap="nowrap">
						
					</th>
					<td>  
						
					</td>
				</tr>		
				<tr>
					<th>
						<font color="red">*</font>ʹ�õ�λ
					</th>
					<td>
						<input id="pkValue" type="hidden" name="pkValue" value="<bean:write name="rs" property="id"/>"/> 
						<html:text name="rs" property="sydw" maxlength="60" readonly="true"></html:text>
					</td>
					<th nowrap="nowrap">
						<font color="red">*</font>����������
					</th>
					<td>  
						<html:text name="rs" property="sqrxm" maxlength="60" readonly="true"></html:text>
					</td>
				</tr>
				<tr>		
					
					<th nowrap="nowrap">
						�����˰༶
					</th>
					<td>
						<html:text name="rs" property="sqrbj" maxlength="60" readonly="true"></html:text>
					</td>
					<th>
						����
					</th>
					<td>  
						<html:text name="rs" property="rs" maxlength="10" readonly="true"></html:text>
					</td>
				</tr>
				<tr>		
					
					<th>
						ʹ��ʱ��	
					</th>
					<td>
						<html:text name="rs" property="sysj" maxlength="90" readonly="true"></html:text>
					</td>
					<th>
						�Ƿ�����	
					</th>
					<td>
						<html:text name="rs" property="sfsp" maxlength="90" readonly="true"></html:text>
					</td>
				</tr>
				<tr>
					<th>
						��ע
					</th>
					<td colspan="3">  
						<html:textarea name="rs" property="bz" rows="5" style="width :100%" readonly="true"></html:textarea>
					</td>
				</tr>
				<tr>
					<th>
						�����
					</th>
					<td colspan="3">  
						<html:textarea name="rs" property="hdnr" rows="5" style="width :100%" readonly="true"></html:textarea>
					</td>
				</tr>
				<tr>		
					<th nowrap="nowrap">
						ѧУ���
					</th>
					<td>
						<html:select name="rs" property="xxsh">
							<html:option value=""></html:option>
							<html:option value="ͨ��">ͨ��</html:option>
							<html:option value="��ͨ��">��ͨ��</html:option>
							<html:option value="δ���">δ���</html:option>
						</html:select>
					</td>
					<th>
						���ʱ��
					</th>
					<td>  
						<html:text name="rs" property="shsj" maxlength="10" readonly="true"></html:text>
					</td>
				</tr>
				<tr>
					<th>
						������
					</th>
					<td colspan="3">  
						<html:textarea name="rs" property="shyj" rows="5" style="width :100%"></html:textarea>
					</td>
				</tr>
				</tbody>
				</logic:equal>
			</table>
			</div>
		</html:form>

		<logic:notEmpty name="save">
			<logic:equal name="save" value="ok">
				    <script>
                      alert("�ύ�ɹ���");
                      Close();
			 window.dialogArguments.document.getElementById('search_go').click();
                    </script>
			</logic:equal>
			<logic:equal name="save" value="no">
				<script>
                      alert("�ظ��ύ������ʧ��!");
                      Close();
			 window.dialogArguments.document.getElementById('search_go').click();
                    </script>
			</logic:equal>
		</logic:notEmpty>
	</body>
</html>

