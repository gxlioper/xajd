<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	</head>
	<base target="_self"/>
	<script language="javascript">
    </script>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/fdyglFunction.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getSztzData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='dwr/util.js'></script>	
	<body >
		<html:form action="/wjstwh_make.do" method="post">		
		   <input type="hidden" name="xn" id="xn" value="<bean:write name="fdyglForm" property="xn" />"/>
		   <input type="hidden" name="xq" id="xq" value="<bean:write name="fdyglForm" property="xq" />"/>
		   	<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>����Ա���� - ��Ϣά�� - ����Ա���������ʾ�ά�� - ����ά��</a>
				</p>
			</div>
		   	<div class="tab">
			  <table width="100%"  border="0" class="formlist">
			    <thead>
			    	<tr>
			        	<th colspan="4"><span>����ά��</span></th>
			        </tr>
			    </thead>
			     <tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
			          <div class="btn">
			          <logic:notEqual name="doType" value="view">
			          		<button name="����" onclick="refreshForm('/xgxt/wjstwh_make.do?doType=save');">����</button>
			          </logic:notEqual>
			            <button name="�ر�" onclick="Close();return false;">�ر�</button>
			          </div></td>
			      </tr>
			    </tfoot>
			    <tbody>
					<tr>
						<th align="right" style="width:80px">
							<font color="red">*</font>�����У�
						</th>
						<logic:equal value="modi" name="doType">
							<td>
								��
								<html:text name="rs" property="idIndex" styleId="idIndex"
									style="width:30px"  readonly="true"/>
								������
							</td>
						</logic:equal>
						<logic:notEqual value="modi" name="doType">
							<td>
								��
								<html:text name="rs" property="idIndex" styleId="idIndex"
									style="width:30px" onkeypress="return onlyNumInputValue(this,3)" onblur="checkStL(this);"/>
								������
							</td>
						</logic:notEqual>
					</tr>
					<tr>
						<th align="right" style="width:80px">
							<font color="red">*</font>�������ƣ�
						</th>
						<td>
							<html:text name="rs" property="stmc" styleId="stmc"
								style="width:400px" onblur="chLeng(this,500)"/>
						</td>
					</tr>
					<tr>
						<th align="right">
							��ע��
						</th>
						<td>
							<html:textarea name="rs" property="bz" rows="6"
								style="width:95% " onblur="chLeng(this,500)"></html:textarea>
						</td>
					</tr>
					</tbody>
				</table>
				</div>
		</html:form>
	</body>	
		<logic:equal value="yes" name="done">
	<script type="text/javascript" >	    
	    alert("�����ɹ���");
	    Close();
	    dialogArgumentsQueryChick();	    	    
	</script> 
	</logic:equal>
		<logic:equal value="no" name="done">
	<script type="text/javascript" >	   
	    alert("����ʧ�ܣ�");
	    Close();
	    dialogArgumentsQueryChick();	    	    	    
	</script> 
	</logic:equal>
</html>
