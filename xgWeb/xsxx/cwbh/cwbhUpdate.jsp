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
	
	<html:form action="/stuGxh" method="post">
		<input type="hidden" id="url" name="url" value="/stuGxh.do?method=cwbhUpdate" />
		<input type="hidden" id="getStuInfo" name="getStuInfo" value="xh" />
		
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>������ά��</a>
			</p>
		</div>

		<div class="tab">
		<table class="formlist" width="93%">
			<thead>
				<tr style="height:22px">
					<th colspan="4">
						<span>ѧ����Ϣ</span>
					</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<th align="right" width="16%">
						<font color="red">*</font>ѧ��
					</th>
					<td align="left" width="34%" nowrap="nowrap">
						<logic:notEqual value="modi" name="doType">
						<logic:equal value="stu" name="userType">
							<html:text styleId="xh" property="xh" value="${rs.xh}" readonly="true" />
						</logic:equal>

						<logic:notEqual value="stu" name="userType">
							<html:text property="xh" styleId="xh" readonly="readonly" value="${rs.xh}"
								onchange="checkXhExists($('getStuInfo').value);"
								onkeypress="autoFillStuInfo(event.keyCode,this)" />
							<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
								class="btn_01" id="buttonFindStu">
								ѡ��
							</button>
						</logic:notEqual>
						</logic:notEqual>
						
						<logic:equal value="modi" name="doType">
							<input type="hidden" name="xh" value="${rs.xh }" />
							${rs.xh }
						</logic:equal>
					</td>
					<th width="16%">
						����
					</th>
					<td width="34%">
						${rs.xm }
					</td>
				</tr>
				<tr>
					<th>�꼶</th>
					<td>${rs.nj }</td>
					<th><bean:message key="lable.xb" />����</th>
					<td>${rs.xymc }</td>
					
				</tr>
				
				<tr>
					<th>רҵ����</th>
					<td>${rs.zymc }</td>
					<th>�༶����</th>
					<td>${rs.bjmc }</td>
				</tr>
				<tr>
					<th><font color="red">*</font>������</th>
					<td><html:text property="cwbh" value="${rs.cwbh }" styleId="cwbh" maxlength="30"></html:text></td>
					<th></th>
					<td></td>
				</tr>
			</tbody>
		
			<tfoot>
		      <tr>
		        <td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
		          <div class="btn">
		          	<button type="button" name="�ύ" id="buttonSave" onclick="saveDataShowTips('stuGxh.do?method=cwbhUpdate&doType=save','xh-cwbh','���ڱ��棬���Ժ򡣡���');">����</button>
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
