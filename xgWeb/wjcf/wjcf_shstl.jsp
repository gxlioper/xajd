<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/wjcfFuction.js"></script>
	<script language="javascript" src="pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
	<body>	   
	    <html:form action="/wjcf_shscheck" method="post">
	    <input type="hidden" id="pkValue" name="pkValue"
				value="<bean:write name="pkValue" scope="request"/>"/>
		<input type="hidden" id="act" name="act"
				value="<bean:write name="act" scope="request"/>" />	
		<input type="hidden" id="xh" name="xh"
				value="<bean:write name="xh" scope="request"/>" />
		<input type="hidden" id="act" name="act"
				value="<bean:write name="act" scope="request"/>" />	
		<input type="hidden" id="realTable" name="realTable"
				value="<bean:write name="realTable" scope="request"/>" />									
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>
						<logic:equal value="11049" name="xxdm">
							Υ�ʹ��� - ���ߴ��� - ίԱ������
						</logic:equal>
						<logic:notEqual value="11049" name="xxdm">
							Υ�ʹ��� - ����������� - ίԱ������
						</logic:notEqual>
					</a>
				</p>
			</div>
			<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					�д�������
				</p>
			</logic:empty>	
			<logic:notEmpty name="rs">
			       <logic:notEmpty name="rswj"> 	
  					<table border="0" id="rsTable" width="100%" class="formlist"> 
  						<thead><tr><th colspan="5"><span>�� �� �� ֤ �� �� �� �� ��</span></th></tr></thead>
    					<logic:iterate id="list" name="rswj"> 
    						<tr onmouseover="rowOnClick(this)"> 
      							<td> <a href="downloadfilewj.do?len=<bean:write name="list" property="len"/>&wjsclj=<bean:write name="list" property="wjsclj" />"  target="_blank">����     							
      							</a> </td> 
      							<td > <bean:write name="list" property="cfwh" /> </td> 
      							<td > <bean:write name="list" property="cflbmc" /> </td> 
       							<td > <bean:write name="list" property="cfyymc" /> </td>
       							<td > <bean:write name="list" property="sssj" /> </td>
<%--       							<td>--%>
<%--        							<a href="#" onclick="if(confirm('ȷʵҪɾ����ǰ�ļ���'))location='fileDel.do?wjh=<bean:write name="list" property="wjh"/>';" >ɾ��</a> </td>--%>
    						</tr> 
    					</logic:iterate> 
  					</table> 
  				</logic:notEmpty> 
  				<logic:empty name="rswj"> 
  	
  				<table border="0" id="rsTable" width="100%" class="formlist"> 
  						<thead><tr><th colspan="5"><span>�� �� �� ֤ �� �� �� �� ��</span></th></tr></thead>
    					<tr><td colspan="5" align="center">���޲��ϻ�֤������</td></tr> 
  				</table>
  				</logic:empty> 
  				<div class="tab">
					<table width="100%" class="formlist">
						<thead>
							<tr>
								<th colspan="5">
									<span>����������������</span>
								</th>
							</tr>
						</thead>
						<tbody>
						<tr>
							<th align="right" width="15%">
								ѧ��
							</th>
							<td align="left" width="30%">
								<bean:write name="rs" property="xh" />
							</td>
							<th align="right" width="18%">
								�����ļ���
							</th>
							<td align="left">
								<bean:write name="rs" property="cfwh" />
							</td>
							<td align="left" width="15%" rowspan="5">
								<img border="0" style="height:133px;width:100px;"
									src="/xgxt/pictures/<bean:write name="rs" property="xh" />.jpg">
							</td>
						</tr>
						<tr>
							<th align="right">
								����
							</th>
							<td align="left">
								<bean:write name="rs" property="xm" />
							</td>
							<th align="right">
								���
							</th>
							<td align="left">
								<bean:write name="rs" property="nd" />
							</td>
						</tr>
						<tr>
							<th align="right">
								�Ա�
							</th>
							<td align="left">
								<bean:write name="rs" property="xb" />
							</td>
							<th align="right">
								ѧ��
							</th>
							<td align="left">
								<bean:write name="rs" property="xn" />
							</td>
						</tr>
						<tr>
							<th align="right">
								�꼶
							</th>
							<td align="left">
								<bean:write name="rs" property="nj" />
							</td>
							<th align="right">
								ѧ��
							</th>
							<td align="left">
								<bean:write name="rs" property="xq" />
							</td>
						</tr>
						<tr>
							<td align="right">
								<bean:message key="lable.xsgzyxpzxy" />
							</td>
							<td align="left">
								<bean:write name="rs" property="xymc" />
							</td>
							<th align="right">
								����ʱ��
							</th>
							<td align="left">
								<bean:write name="rs" property="cfsj" />
							</td>
						</tr>
						<tr>
							<th align="right">
								רҵ
							</th>
							<td align="left">
								<bean:write name="rs" property="zymc" />
							</td>
							<th align="right">
								�������
							</th>
							<td align="left" colspan="2">
								<bean:write name="rs" property="cflbmc" />
							</td>
						</tr>
						<tr>
							<th align="right">
								�༶
							</th>
							<td align="left">
								<bean:write name="rs" property="bjmc" />
							</td>
							<th align="right">
								��������
							</th>
							<td align="left" colspan="2">
								<bean:write name="rs" property="cfyymc" />
							</td>
						</tr>
						<tr>
							<th align="right">
								��ϵ��ַ
							</th>
							<td align="left">
								<bean:write name="rs" property="dz" />
							</td>
							<th align="right">
								<logic:present name="isZJJDZYJSXY">
					  			  	����/�������ʱ��
					    		</logic:present>
								<logic:notPresent name="isZJJDZYJSXY">
					      	 		����ʱ��
					     		</logic:notPresent>
							</th>
							<td align="left" colspan="2">
								<bean:write name="rs" property="sssj" />
							</td>
						</tr>
						<tr>
							<th align="right">
								��������
							</th>
							<td align="left">
								<bean:write name="rs" property="yb" />
							</td>
							<th align="right">
								ίԱ������
							</th>
							<td align="left" colspan="2">
								<html:select name="rs" property="sh" style="width:100px"
									styleId="sh">
									<html:options collection="shList" property="sh"
										labelProperty="sh" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th align="right">
								��ϵ�绰
							</th>
							<td align="left">
								<bean:write name="rs" property="lxdh" />
							</td>
							<td align="right">

							</td>
							<td align="left" colspan="2">

							</td>
						</tr>
						<logic:present name="isZJJDZYJSXY">
							<tr>
								<th align="right">
									���봦�ָ�Ϊ
								</th>
								<td align="left" colspan="4">
									<bean:write name="rs" property="cfxg" />
								</td>
							</tr>
						</logic:present>
						<tr>
							<th align="right">
								<logic:present name="isZJJDZYJSXY">
					  				  ����/���<br/>��������<br/>
								</logic:present>
								<logic:notPresent name="isZJJDZYJSXY">
					   				�ı�<bean:message key="lable.xsgzyxpzxy" />����Ҫ��
								</logic:notPresent>
							</th>
							<td align="left" colspan="4">
								<bean:write name="rs" property="yq" />
							</td>
						</tr>
						<tr>
							<th align="right">
								���۽����
								<br/>
								���ݻ�����
								<br/>
								<font color="red">(������500������)</font>
							</th>
							<td align="left" colspan="4">
								<html:textarea name="rs" property="tlly" rows="7"
									style="width:550px" styleId="tlly" onkeyup="checkLen(this,500)">
								</html:textarea>
							</td>
						</tr>
						</tbody>
						<tfoot>
					      <tr>
					        <td colspan="5"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
					          <div class="btn">
								 <button type="button" onclick="refreshForm('/xgxt/wjcf_shscheck.do?doType=save');"
									id="buttonSave">
									����
								</button>
								  <button type="button" name="�ر�" onclick="window.close();return false;" id="buttonClose">�ر�</button>
					          </div></td>
					      </tr>
					    </tfoot>
					</table>
				</div>
			</logic:notEmpty>
		  <logic:equal value="yes" name="done">
			<script>
				alert("�����ɹ�!");
				Close();
				if(window.dialogArguments && window.dialogArguments.document.all("search_go")){
				    window.dialogArguments.document.getElementById('search_go').click(); 
				   }
			</script>
		   </logic:equal>
		   <logic:equal value="no" name="done">
			<script>
				alert("����ʧ��!");
				Close();
				if(window.dialogArguments && window.dialogArguments.document.all("search_go")){
				    window.dialogArguments.document.getElementById('search_go').click(); 
				   }
			</script>
		   </logic:equal>
	  </html:form>	
	</body>
</html>
