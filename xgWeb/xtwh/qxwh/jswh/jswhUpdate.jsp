<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>	
	<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/systemFunction.js'></script>
	<script language="javascript">
		function getJsczsm(){
			var jscmsm = $("jssm"+$("jscmdm").value).value;
			$("jsczfwsm").innerText = jscmsm;
		}
		function saveJs(){
			saveUpdate('jswhManage.do?method=jswhUpdate','jsmc-jsczfwdm');
		}
	</script>
	</head>
	<body onload="getJsczsm()">
		
		<html:form action="/jswhManage" method="post">
	
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>��ɫά��</a>
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
				<input type="hidden" id="doType" name="doType"
					value="update" />
				<input type="hidden" id="pkValue" name="pkValue"
					value="<bean:write name='rs' property="jsdm" scope="request"/>" />
				<fieldset>
					
					<div class="tab">
					<table width="100%" class="formlist" border="0">
						<thead>
			    			<tr>
			        		<th colspan="4"><span>������ɫά��</span></th>
			        		</tr>
			    		</thead>
						<tfoot>
					      <tr>
					        <td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
					          <div class="btn">
								<button type="button" 
									onclick="saveJs()"
									 id="buttonSave">
									�� ��
								</button>
								<button type="button" onclick="Close();return false;" 
									id="buttonClose">
									�� ��
								</button>
					          </div></td>
					      </tr>
					    </tfoot>
						
						<%--�����ɫû���û������--%>
						<logic:equal name="rs" property="xyyy" value="0">
						<tbody>
						<tr>
							<th colspan="2" width="30%" >
								<font color="red">*</font>��ɫ��
							</th>
							<td colspan="2" align="left">
								<input type="hidden" id="jsmcbf" value="<bean:write name="rs" property="jsmc"/>"/>
								<html:text name='rs' property="jsmc" onblur="checkExists('xg_xtwh_jswhb','jsmc',this,'buttonSave','span_jsmc');chLeng(this,'25')" 
									styleId="jsmc"  style="width:180px"/>
								<span style="color: red;display: none" id="span_jsmc">�ý�ɫ���Ѿ���ʹ���ˣ��������ɫ����</span>
							</td>
						</tr>
						<tr>
							<th colspan="2">
								<font color="red">*</font>��ɫ����
							</th>
							<td align="left" colspan="2">
								<html:select name ="rs" property="jslxdm" styleId="jslxdm" style="width:190px" >
									<html:option value=""></html:option>
									<html:options collection="jslxList" property="jslxdm" 
										labelProperty="jslxmc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th colspan="2">
								<font color="red">*</font>��ɫ������Χ
							</th>
							<td align="left" colspan="2">
								<html:select name ="rs" property="jscmdm" styleId="jscmdm" style="width:190px" onchange="getJsczsm()">
									<html:option value=""></html:option>
									<html:options collection="jsczfwList" property="jscmdm"
										labelProperty="jscmmc" />
								</html:select>
								<logic:iterate name="jsczfwList" id="s">
									<input id="jssm<bean:write name ="s" property="jscmdm"/>" value="<bean:write name ="s" property="jscmsm"/>" style="display: none;"/>
								</logic:iterate>	
							</td>
						</tr>
						<tr>
							<th colspan="2">
								��ɫ������Χ˵��
							</th>
							<td id="jsczfwsm" align="left" colspan="2" style="color: blue">
															
							</td>
						</tr>
						<tr>
							<th colspan="2">
								�Ƿ�����
							</th>
							<td align="left" colspan="2">
								<logic:notEmpty name = "rs" property="sfqy">
								<html:radio name='rs' property="sfqy" value="��" /> ��
								<html:radio name='rs' property="sfqy" value="��" /> ��
								</logic:notEmpty>
								<logic:empty name = "rs" property="sfqy">
								<input type="radio" name="sfqy" value="��" checked="checked" /> ��
								<input type="radio" name="sfqy" value="��" /> ��
								</logic:empty>
							</td>
						</tr>
						<tr>
							<th colspan="2">
								��ɫ˵��
							</th>
							<td align="left" colspan="2">
								<html:textarea name='rs' property="jssm" styleId="jssm" style="width: 80%" rows="8" onblur="chLeng(this,'200')" />
							</td>
						</tr>
					</tbody>
					</logic:equal>
					
					<%--�����ɫ���û������--%>
					<logic:notEqual name="rs" property="xyyy" value="0">
						<tbody>
						<tr>
							<th colspan="2" width="30%" >
								<font color="red">*</font>��ɫ��
							</th>
							<td colspan="2" align="left">
								<input type="hidden" id="jsmcbf" value="<bean:write name="rs" property="jsmc"/>"/>
								<html:text name='rs' property="jsmc" onblur="chLeng(this,'25')"
									styleId="jsmc"  style="width:180px"/>
							</td>
						</tr>
						<tr>
							<th colspan="2">
								<font color="red">*</font>��ɫ����
							</th>
							<td align="left" colspan="2">
								<input type="hidden" name="jslxdm" id="jslxdm" value="<bean:write name ="rs" property="jslxdm" />" />
								<bean:write name ="rs" property="jslxmc" />
							</td>
						</tr>
						<tr>
							<th colspan="2">
								<font color="red">*</font>��ɫ������Χ
							</th>
							<td align="left" colspan="2">
								<input type="hidden" name="jscmdm" id="jscmdm" value="<bean:write name ="rs" property="jscmdm" />" />
								<bean:write name ="rs" property="jscmmc" />
								<logic:iterate name="jsczfwList" id="s">
									<input id="jssm<bean:write name ="s" property="jscmdm"/>" value="<bean:write name ="s" property="jscmsm"/>" style="display: none;"/>
								</logic:iterate>	
							</td>
						</tr>
						<tr>
							<th colspan="2">
								��ɫ������Χ˵��
							</th>
							<td id="jsczfwsm" align="left" colspan="2" style="color: blue">
															
							</td>
						</tr>
						<tr>
							<th colspan="2">
								�Ƿ�����
							</th>
							<td align="left" colspan="2">
								<logic:notEmpty name = "rs" property="sfqy">
								<html:radio name='rs' property="sfqy" value="��" /> ��
								<html:radio name='rs' property="sfqy" value="��" /> ��
								</logic:notEmpty>
								<logic:empty name = "rs" property="sfqy">
								<input type="radio" name="sfqy" value="��" checked="checked" /> ��
								<input type="radio" name="sfqy" value="��" /> ��
								</logic:empty>
							</td>
						</tr>
						<tr>
							<th colspan="2">
								��ɫ˵��
							</th>
							<td align="left" colspan="2">
								<html:textarea name='rs' property="jssm" styleId="jssm" style="width: 80%" rows="8" onblur="chLeng(this,'200')" />
							</td>
						</tr>
					</tbody>
					</logic:notEqual>
					</table>
					</div>
					
				</fieldset>
				<div class="buttontool">
				</div>
			</logic:notEmpty>
			<logic:notEmpty name="message"> 
			<input type="hidden" id="message" name="message" value="<bean:write name="message" />"  />
			</logic:notEmpty>
		</html:form>
		<!-- ��ʾ��Ϣ -->
		<%@ include file="/comm/other/tsxx.jsp"%>
		<!-- ��ʾ��Ϣ end-->
	</body>
</html>
