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
				<em>���ĵ�ǰλ��:</em><a>�û���ɫ����</a>
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
				<th>�û���</th>
				<td>ӵ�н�ɫ</td>
			</tr>
			<logic:iterate name="yhjsMap" id="yhjsEntry"> 
			<tr>
				<th>${yhjsEntry.key }</th>
				<td colspan="3">
					<logic:iterate name="yhjsEntry" property="value" id="jsMap">
						<input type="checkbox" checked="checked" value=/> ${jsMap.jsmc } &nbsp;&nbsp;
					</logic:iterate>
				</td>
			</tr>
			</logic:iterate>
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
