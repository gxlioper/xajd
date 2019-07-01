<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/String.js"></script>
	<script language="javascript" src="js/check.js"></script>
	<script language="javascript" src="js/qtsjFunction.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/interface/systemFunction.js"></script>
	<script	type="text/javascript">
		function checkOne(obj){
			var id = obj.id;
			var ids = id.split('_');
			
			if(ids[1] == 'yy'){
				var oid = ids[0] + "_gl";
				var yhyy = ids[0] + "_gl_yh";
				var yhgl = ids[0] + "_yy_yh";
				
				if($(oid)){
					$(oid).checked = "";
				}
				if($(yhyy)){
					$(yhyy).checked = "";
				}
				if($(yhgl)){
					$(yhgl).checked = "";
				}
			}else{
				var oid = ids[0] + "_yy";
				var yhyy = ids[0] + "_gl_yh";
				var yhgl = ids[0] + "_yy_yh";
				
				if($(oid)){
					$(oid).checked = "";
				}
				if($(yhyy)){
					$(yhyy).checked = "";
				}
				if($(yhgl)){
					$(yhgl).checked = "";
				}
			}
		}
		
		function checkTwo(obj){
			var id = obj.id;
			var ids = id.split('_');
			var glid = ids[0] + "_gl";
			var yyid = ids[0] + "_yy";
						
			if($(glid)){
				$(glid).checked = "";
			}
			
			if($(yyid)){
				$(yyid).checked = "";
			}
		}
	</script>
</head>
<body>
	
	<html:form action="/yhwhManage" method="post">
		<input type="hidden" name="pkValue" value="${rs.yhm }" />
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>�û�����</a>
			</p>
		</div>
		
		<div class="tab">
		<table class="formlist" width="93%">
			<thead>
				<tr style="height:22px">
					<th colspan="4">
						<span>�û���Ϣ</span>
					</th>
				</tr>
			</thead>
			<tbody>
			<tr>
				<th align="right" width="16%">
					<font color="red">*</font>�û���
				</th>
				<td align="left" width="34%" nowrap="nowrap">
					<input type="hidden" name="yhm" value="${rs.yhm }" />
					${rs.yhm }
				</td>
				<th width="16%">
					����
				</th>
				<td width="34%">
					<html:text property="xm" maxlength="10" value="${rs.xm }" />
				</td>
			</tr>
		
			<tr>
				<th>
					<font color="red">*</font>���ڲ���						
				</th>
				<td>
					<html:select property="szbm" styleId="szbm" style="width: 250px" onmouseover="" value="${rs.szbm}">
						<html:option value=""></html:option>
						<html:options collection="bmList" property="bmdm" labelProperty="bmmc"></html:options>
					</html:select>
				</td>
				<th>
					�Ƿ�����
				</th>
				<td>
					<html:radio property="kqzt" value="1">��</html:radio>
					<html:radio property="kqzt" value="0">��</html:radio>
				</td>
			</tr>
			<tr>
				<th>Ĭ�����ݷ�Χ</th>
				<td>
					<html:select property="mrqx" styleId="mrqx" value="${rs.mrqx }">
						<html:options collection="mrqxList" property="jscmdm" labelProperty="jscmmc"/>
					</html:select>
				</td>			
			</tr>
			<tr>
				<th>
					��ע
					<br /><font color="red">(������100����)</font>
				</th>
				<td colspan="3">
					<html:textarea property='bz' style="width:99%" value="${rs.bz}"
						rows='3' onblur="checkLen(this,100)"/>
				</td>
			</tr>
			</tbody>
			<thead>
				<tr>
					<th colspan="4">
						<span>���н�ɫ</span>
					</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<th>ӵ�н�ɫ</th>
					<td colspan="3">
						<logic:iterate name="yyjsList" id="yyjsMap">
							<logic:equal value="��" name="yyjsMap" property="jsqx">
								<input type="checkbox" name="yyjs" value="${yyjsMap.jsdm }_yy" id="${yyjsMap.jsdm }_yy_yh" checked="checked" onclick="checkTwo(this)"/>${yyjsMap.jsmc } &nbsp;&nbsp;
							</logic:equal>
						</logic:iterate>
					</td>
				</tr>
				<tr>
					<th>�ɹ����ɫ</th>
					<td colspan="3">
						<logic:iterate name="yyjsList" id="yyjsMap">
							<logic:equal value="��" name="yyjsMap" property="jsqx">
								<input type="checkbox" name="yyjs" value="${yyjsMap.jsdm }_gl" id="${yyjsMap.jsdm }_gl_yh" checked="checked" onclick="checkTwo(this)"/>${yyjsMap.jsmc } &nbsp;&nbsp;
							</logic:equal>
						</logic:iterate>
					</td>
				</tr>
			</tbody>
			<thead>
				<tr style="height:22px">
					<th colspan="4">
						<span>��ɫ��Ϣ</span>
					</th>
				</tr>
			</thead>
			<logic:iterate id="js" name="jsList" indexId="index">
				<%if(index%2 == 0){%>
					<tr>
				<%} %>
				<th>${js.jsmc }</th>
				<td>
					<input type="checkbox" name="yyjs" value="${js.jsdm }_yy" id="${js.jsdm }_yy" onclick="checkOne(this);"/>ӵ��&nbsp;&nbsp;&nbsp;&nbsp;
					<logic:equal value="admin" name="userType">
						<input type="checkbox" name="yyjs" value="${js.jsdm }_gl" id="${js.jsdm }_gl" onclick="checkOne(this);"/>����
					</logic:equal>
				</td>
				<%if(index%2 ==1){ %>
					</tr>
				<%} %>
			</logic:iterate>
			<tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
			          <div class="btn">
			          	<button type="button" name="�ύ" id="buttonSave" onclick="saveDataShowTips('yhwhManage.do?method=yhwhModi&doType=save','yhm-szbm','���ڱ��棬���Ժ򡣡���');">����</button>
			            <button type="button" name="�ر�" onclick="window.close();return false;">�ر�</button>
			          </div></td>
			      </tr>
			    </tfoot>
		</table>
		</div>
	</html:form>
	<logic:present name="message">
		<input type="hidden" id="message" value="${message }" />
		<script type="text/javascript">
			alert(document.getElementById('message').value);
			Close();
			if(window.dialogArguments && window.dialogArguments.document.all("search_go")){
				window.dialogArguments.document.getElementById('search_go').click();
			}
		</script>
	</logic:present>
</body>
</html>
