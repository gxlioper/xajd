<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script language="javascript">
	function add(){
		    
	   
	      var xslx = document.getElementById("xslx").value; 
	      var xh = document.getElementById("xh").value; 
	      var sqkmc = document.getElementById("sqkmc").value; 
	      var jyr = document.getElementById("jyr").value;
	      var bz = document.getElementById("bz").value; 
	      var jysj = document.getElementById("jysj").value; 
	      var ghsj = document.getElementById("ghsj").value; 
	      if(jysj!=""){
	    	  jysj = jysj.replace(/\-/g, "");    
	      }
	      if(jysj!=""){
	    	  ghsj = ghsj.replace(/\-/g, ""); 
	      }
			if(jysj>ghsj){
				alert("����ʱ�䲻�����ڹ黹ʱ��");
				return false;
			}
	      if(xh==""){
				alert("���Ϊ�������");
				return false;
		    }
		    if(sqkmc==""){
				alert("�顢�ڿ���Ϊ�������");
				return false;
		    }
		    if(jyr==""){
				alert("������Ϊ�������");
				return false;
		    }
		    if(bz!=""){
			    if(bz.length>300){
					alert("��ע���ܳ���300�����֣���");
					return false;
			    }
		    }
	     
	        BatAlert.showTips('�����ύ�����Ժ�...');
	        if(xslx=="add"){
				 document.forms[0].action = "zgdzdxXxwh.do?method=dyhdsgl_save&xxk=zljy&czlx=save&act=save";
	        }
	        if(xslx=="update"){
	        	document.forms[0].action = "zgdzdxXxwh.do?method=dyhdsgl_save&xxk=zljy&czlx=update&act=update";
	        }
			 document.forms[0].submit();
    }
	 function isNumber(s){
			var p = /^(-|\+)?\d+$/;
			return p.test(s); 
		    }  
			
	</script>

	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	</head>
	<body>
		<html:form action="/zgdzdxXxwh.do" method="post">
			<input id="xslx" type="hidden" name="xslx" value="<bean:write name="xslx"/>"/>
			<div class="div">
			<table width="100%" class="formlist">
			<logic:equal value="add" name="xslx">
				<thead>
					<tr>
						<th colspan="4">
							<span>���Ͻ�������</span>
						</th>
					</tr>
				</thead>
				<tbody>	
				<tr>		
					<th>
						<font color="red">*</font>���	
					</th>
					<td>
						<html:text property="xh" maxlength="30"></html:text>
					</td>
					<th nowrap="nowrap">
						<font color="red">*</font>�顢�ڿ�����
					</th>
					<td>  
						<html:text property="sqkmc" maxlength="60"></html:text>
					</td>
				</tr>
				<tr>		
					
					<th nowrap="nowrap">
						<font color="red">*</font>������
					</th>
					<td>
						<html:text property="jyr" maxlength="60"></html:text>
					</td>
					<th>
						�����˰༶
					</th>
					<td>  
						<html:text property="jyrbj" maxlength="60"></html:text>
					</td>
				</tr>
				<tr>		
					
					<th>
						����ʱ��	
					</th>
					<td>
						<html:text property="jysj" maxlength="20" onclick="return showCalendar('jysj','y-mm-dd');"></html:text>
					</td>
					<th>
						�黹ʱ��	
					</th>
					<td>
						<html:text property="ghsj" maxlength="20" onclick="return showCalendar('ghsj','y-mm-dd');"></html:text>
					</td>
				</tr>
				<tr>		
					
					<th>
						��ϵ�绰	
					</th>
					<td>
						<html:text property="lxdh" maxlength="30"></html:text>
					</td>
					<th>
						
					</th>
					<td>
					
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
				</tbody>
				</logic:equal>
				<logic:equal value="update" name="xslx">
				<thead>
					<tr>
						<th colspan="4">
							<span>���Ͻ����޸�</span>
						</th>
					</tr>
				</thead>		
		<tbody>
				<tr>		
					<th>
						<font color="red">*</font>���	
					</th>
					<td>
					<input id="pkValue" type="hidden" name="pkValue" value="<bean:write name="rs" property="id"/>"/> 
						<html:text name="rs" property="xh" maxlength="30"></html:text>
					</td>
					<th nowrap="nowrap">
						<font color="red">*</font>�顢�ڿ�����
					</th>
					<td>  
						<html:text name="rs" property="sqkmc" maxlength="60"></html:text>
					</td>
				</tr>
				<tr>		
					
					<th nowrap="nowrap">
						������
					</th>
					<td>
						<html:text name="rs" property="jyr" maxlength="60"></html:text>
					</td>
					<th>
						�����˰༶
					</th>
					<td>  
						<html:text name="rs" property="jyrbj" maxlength="30"></html:text>
					</td>
				</tr>
				<tr>		
					
					<th>
						����ʱ��	
					</th>
					<td>
						<html:text name="rs" property="jysj" maxlength="20" onclick="return showCalendar('jysj','y-mm-dd');"></html:text>
					</td>
					<th>
						�黹ʱ��	
					</th>
					<td>
						<html:text name="rs" property="ghsj" maxlength="20" onclick="return showCalendar('ghsj','y-mm-dd');"></html:text>
					</td>
				</tr>
				<tr>		
					
					<th>
						��ϵ�绰	
					</th>
					<td>
						<html:text name="rs" property="lxdh" maxlength="20"></html:text>
					</td>
					<th>
						
					</th>
					<td>
					
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
				</tbody>
				</logic:equal>
				<logic:equal value="view" name="xslx">
				<thead>
					<tr>
						<th colspan="4">
							<span>���Ͻ��Ĳ鿴</span>
						</th>
					</tr>
				</thead>
				<tbody>		
				<tr>		
					<th>
						<font color="red">*</font>���	
					</th>
					<td>
						<html:text name="rs" property="xh" maxlength="60" readonly="true"></html:text>
					</td>
					<th nowrap="nowrap">
						<font color="red">*</font>�顢�ڿ�����
					</th>
					<td>  
						<html:text name="rs" property="sqkmc" maxlength="60" readonly="true"></html:text>
					</td>
				</tr>
				<tr>		
					
					<th nowrap="nowrap">
						������
					</th>
					<td>
						<html:text name="rs" property="jyr" maxlength="60" readonly="true"></html:text>
					</td>
					<th>
						�����˰༶
					</th>
					<td>  
						<html:text name="rs" property="jyrbj" maxlength="10" readonly="true"></html:text>
					</td>
				</tr>
				<tr>		
					
					<th>
						����ʱ��	
					</th>
					<td>
						<html:text name="rs" property="jysj" maxlength="90" readonly="true"></html:text>
					</td>
					<th>
						�黹ʱ��	
					</th>
					<td>
						<html:text name="rs" property="ghsj" maxlength="90" readonly="true"></html:text>
					</td>
				</tr>
				<tr>		
					
					<th>
						��ϵ�绰	
					</th>
					<td>
						<html:text name="rs" property="lxdh" maxlength="90" readonly="true"></html:text>
					</td>
					<th>
						
					</th>
					<td>
					
					</td>
				</tr>
				<tr>
					<th>
						��ע
					</th>
					<td colspan="3"><br /><br /><html:textarea name="rs" property="bz" rows="5" style="width :100%" readonly="true"></html:textarea>
					<br /><br /></td>
				</tr>
				</tbody>
				</logic:equal>
				
				<tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
			          <div class="btn">
			         <logic:notEqual value="view" name="xslx">
			          		<button type="button" onclick="add();">
								�� ��
							</button>
							<button type="button" onclick="Close();return false;">
								�� ��
							</button>		
			          </logic:notEqual>
					  
					  <logic:equal value="view" name="xslx">
					  <button type="button" onclick="Close();return false;">
								�� ��
							</button>	
					  </logic:equal>
			          </div></td>
			      </tr>
			    </tfoot>
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

