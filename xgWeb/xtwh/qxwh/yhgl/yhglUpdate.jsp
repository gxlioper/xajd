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
				
				if($(oid)){
					$(oid).checked = "";	
				}
			}else{
				var oid = ids[0] + "_yy";
				if($(oid)){
					$(oid).checked = "";
				}
			}
		}
	</script>
</head>
<body>
	
	<html:form action="/yhwhManage" method="post">
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>�û�����</a>
			</p>
		</div>
		<div class="prompt">
	       <h3><span>��ʾ��</span></h3>
	       <p>�����ӵ��û�����Ϊ"888888"<br/></p>
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
					<html:text property="yhm" styleId="zgh" onblur="checkExists('xg_xtwh_yhb','yhm',this,'buttonSave','span_yhm');"/>
					<span id="span_yhm" style="color:red;display: none">���û����Ѵ��ڣ�</span>
				</td>
				<th width="16%">
					����
				</th>
				<td width="34%">
					<html:text property="xm" maxlength="10"/>
				</td>
			</tr>
		
			<tr>
				<th>
					<font color="red">*</font>���ڲ���						
				</th>
				<td>
					<html:select property="szbm" styleId="szbm" style="width: 250px" onmouseover="">
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
					<html:select property="mrqx" styleId="mrqx">
						<html:options collection="mrqxList" property="jscmdm" labelProperty="jscmmc"/>
					</html:select>
				</td>
				<th></th>
				<td></td>		
			</tr>
			<tr>
				<th>
					��ע
					<br /><font color="red">(������100����)</font>
				</th>
				<td colspan="3">
					<html:textarea property='bz' style="width:99%"
						rows='3' onblur="checkLen(this,100)"/>
				</td>
			</tr>
			</tbody>
			<thead>
				<tr>
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
			          	<button type="button" name="�ύ" id="buttonSave" onclick="saveDataShowTips('yhwhManage.do?method=yhwhUpdate&doType=save','yhm-szbm','���ڱ��棬���Ժ򡣡���');">����</button>
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
