<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript">
    </script>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/fdyglFunction.js"></script>
    <script type='text/javascript' src='/xgxt/dwr/interface/getSztzData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='dwr/util.js'></script>	
</head>
	<body >
		<html:form action="/wjstxxwh_make" method="post">
		   <input type="hidden" name="xn" id="xn" value="<bean:write name="fdyglForm" property="xn" />"/>
		   <input type="hidden" name="xq" id="xq" value="<bean:write name="fdyglForm" property="xq" />"/>	
		   <div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>����Ա���� - ��Ϣά�� - ����Ա���������ʾ�ά�� - ����ѡ��ά��</a>
				</p>
			</div>
			<input type="hidden" id="id" name="id"
				value="<bean:write name="id" scope="request"/>" />				
			
			<div class="tab">
			 	<table width="100%"  border="0" class="formlist">
			    <thead>
			    	<tr>
			        	<th colspan="4"><span>����ѡ��ά��</span></th>
			        </tr>
			    </thead>
			     <tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
			          <div class="btn"  id="btn">
			          <logic:notEqual name="doType" value="view">
			          		<button name="�ύ" onclick="refreshForm('/xgxt/wjstxxwh_make.do?doType=save');">�� ��</button>
			          </logic:notEqual>
			            <button name="�ر�"  onclick="Close();return false;">�ر�</button>
			          </div></td>
			      </tr>
			    </tfoot>
			    <tbody>
					<tr>
						<th align="right" style="width:80px">
							<font color="red">*</font>ѡ���У�
						</th>
						<logic:equal value="modi" name="doType">
							<td >
								��
								<html:text name="rs" property="xxl" styleId="xxl"
									style="width:30px"  readonly="true"/>
								ѡ��
							</td>
						</logic:equal>
						<logic:notEqual value="modi" name="doType">
							<td>
								��
								<html:text name="rs" property="xxl" styleId="xxl"
									style="width:30px" onkeypress="return onlyNumWordInputValue(this,2)"
									 maxlength="2" onblur="checkXxL(this)"/>
								ѡ��
							</td>
						</logic:notEqual>
					</tr>
					<tr>
						<th align="right" style="width:80px">
							<font color="red">*</font>ѡ�����ݣ�
						</th>
						<td>
							<html:text name="rs" property="xxnr" styleId="xxnr"
								style="width:350px" />
						</td>
					</tr>
					<tr>
						<th align="right" style="width:80px">
							<font color="red">*</font>ѡ���ֵ��
						</th>
						<td>
							<html:text name="rs" property="xxfz" styleId="xxfz"
								style="width:350px" onkeyup="value=value.replace(/[^\d]/g,'')" />
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
	    // window.dialogArguments.Close();
	  	window.dialogArguments.refreshForm("/xgxt/wjstxxwh.do?id=<bean:write name='id' scope='request'/>");
	    Close();
	</script> 
	</logic:equal>
		<logic:equal value="no" name="done">
	<script type="text/javascript" >	   
	    alert("����ʧ�ܣ�");
	    Close();
	    // dialogArgumentsQueryChick();	    	    	    
	</script> 
	</logic:equal>
</html>
