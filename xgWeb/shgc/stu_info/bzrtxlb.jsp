<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- ͷ�ļ� -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/check.js"></script>
	<script language="javascript" src="js/jsFunction.js"></script>	
	<script language="javascript" src="js/stuinfoFunction.js"></script>
</head>
	<body>
		<html:form action="/address_book">
			<input type="hidden" value="xh-xm-zddwmc-zddwdz-zdsj" id="notnull" name="notnull" />
			<input type="hidden" name="xyV" value="" />
			<input type="hidden" name="zyV" value="" />
			<input type="hidden" name="bjV" value="" />
			<input type="hidden" id="cbVal" name="cbVal" value="" />

			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>ѧ����Ϣ - ͨѶ¼ - ������ͨѶ¼��Ϣά��</a>
				</p>
			</div>

			<div class="tab">
			<table width="100%" class="formlist">
				<thead>
					<tr>
						<th colspan="4">
							<span>������ͨѶ��Ϣ</span>
						</th>
					</tr>
				</thead>	
				<tbody>	
					<tr>
						<th>�꼶</th>
						<td>
							<html:select property="nj" style="width:180px" onchange="initZyList();initBjList();" name="rs">
								<html:option value=""></html:option>
								<html:options collection="njList" property="nj"
									labelProperty="nj" />
							</html:select>
						</td>					
						<th><bean:message key="lable.xsgzyxpzxy" /></th>
						<td>
							<html:select property="xydm" style="width:180px" name="rs"
								onchange="initZyList();initBjList();" styleId="xy">
								<html:option value=""></html:option>
								<html:options collection="xyList" property="xydm"
									labelProperty="xymc" />
							</html:select>	
						</td>		
					</tr>
					<tr>
						<th>רҵ</th>
						<td>
							<html:select property="zydm" style="width:180px" styleId="zy" name="rs"
								onchange="initBjList();">
								<html:option value=""></html:option>
								<html:options collection="zyList" property="zydm"
									labelProperty="zymc" />
							</html:select>
						</td>	
						<th><span class="red">*</span>�༶����</th>
						<td>
							<html:select name="rs" property="bjdm" styleId="bj"
								style="width:180px">
								<html:option value=""></html:option>
								<html:options collection="bjList" property="bjdm"
									labelProperty="bjmc" />
							</html:select>
						</td>	
					</tr>
					<tr>					
						<th><span class="red">*</span>����</th>
						<td>
							<html:text name="rs" property="xm" styleId="xm"/>
						</td>
					
						<th>�ֻ�����</th>
						<td>
							<html:text name="rs" property="sjhm" styleId="sjhm" onkeyup="value=value.replace(/[^\d]/g,'') " maxlength="11"/>
						</td>	
					</tr>
					<tr>					
						<th>��λ�绰</th>
						<td>
							<html:text name="rs" property="dwdh" styleId="dwdh" onkeyup="value=value.replace(/[^\d]/g,'') " maxlength="11"/>
						</td>
					
						<th>��ͥ�绰</th>
						<td>
							<html:text property="jtdh" name="rs" onkeyup="value=value.replace(/[^\d]/g,'') " maxlength="11"></html:text>
						</td>	
					</tr>						
					<tr>					
						<th>�����绰</th>
						<td>
							<html:text property="qtdh" name="rs" styleId="qtdh" onkeyup="value=value.replace(/[^\d]/g,'') " maxlength="11"/>
						</td>						
					
						<th>��������</th>
						<td>
							<div class="pos" style="z-index:1">
					        	<html:text property="email" name="rs" styleId="email"
					        	onkeypress="checkEmaile(this)" 
					        	onblur="checkEmaile(this)"
					        	maxlength="30" value="${rs.email}"></html:text>
					       		<div id="emaliErrow" class="hide" >
							        <p>�����ʽ����ȷ</p>
							    </div>
					        </div>
						</td>	
					</tr>
					<tr>
						<th>��ע</th>
						<td colspan="3" style="word-break:break-all;">
							<html:textarea property="bz"  name="rs" styleId="bz" style="width:90%" rows="3"/>
						</td>
					</tr>
				</tbody>
				<tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
			          <div class="btn">
			            <button
							onclick="commSave('/xgxt/address_book.do?doType=save','bjdm-xm')" id="saveButton"
							>
							�� ��
						</button>
						<button onclick="window.close();return false;">
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
				alert("�����ɹ�!");
				window.close();				 	
			 	dialogArgumentsQueryChick();
			</script>
			</logic:equal>
			<logic:equal value="false" name="result">
			<script>
			 	alert("����ʧ��!");
			</script>
			</logic:equal>
			</logic:notEmpty>
		</html:form>
	</body>
</html>
