<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- ͷ�ļ� -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script type="text/javascript" src="pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
	<script language="javascript">
		function commit(){	
			if(document.getElementById('xh').value == ''){
				alert('�뽫��\*�ŵ���Ŀ��д����!') ;
			}else{
				refreshForm('qgzxZgdzdx.do?method=saveHmd');
			}
		}
	</script>
</head>
<body>	
	<html:form action="/qgzxZgdzdx.do" method="post">	
		<input type="hidden" id="url" name="url" value="/qgzx/zgdzdx/addHmd.jsp"/>
		<input type="hidden" id="getStuInfo" name="getStuInfo" value="xm-xb-xymc-nj-zymc-bjmc" />
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>�ڹ���ѧ - �������� - �ڹ���ѧ������ - ������ά��</a>
			</p>
		</div>
		<div class="tab">
		  <table width="100%" class="formlist">
			 <thead>			
				<tr>
					<th colspan="4">
						<span>��������Ϣ</span>
					</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<th width="20%"><span class="red">*</span>ѧ��</th>
					<td width="25%">
						<html:text name='rs' property="xh" readonly="true"
							styleId="xh"
							onkeypress="autoFillStuInfo(event.keyCode,this);" />
						<logic:notPresent name="type">
						<button type="button"
							onclick="showTopWin('/xgxt/stu_info.do',750,550);"
							class="btn_01" id="buttonFindStu" >
							ѡ��
						</button>
						</logic:notPresent>
					</td>
					<th width="20%">����</th>
					<td width="35%">
						${rs.xm}
					</td>
				</tr>
				<tr>
					<th><bean:message key="lable.xsgzyxpzxy" /></th>
					<td>
						${rs.xymc}
					</td>
					<th>רҵ����</th>
					<td>
						${rs.zymc}
					</td>
				</tr>
				<tr>
					<th>�༶����</th>
					<td>
						${rs.bjmc}
					</td>
					<th>�꼶</th>
					<td>
						${rs.nj}
					</td>
				</tr>
				<tr>
					<th>��ע(������0~250)</th>
					<td colspan="3" >
						<html:textarea property="bz" style="width:385px" name="rs" cols="40" rows="4" onkeyup="checkLen(this,250)"/>
					</td>
				</tr>
				</tbody>
				<tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
			          <div class="btn">
					   <logic:notEqual value="view" name="doType">
			           <button type="button"
							onclick="commit()"
							style="width:80px" id="buttonSave">
							�� ��
						</button>
						</logic:notEqual>
						<button type="button" onclick="Close();return false;" style="width:80px"
							id="buttonClose">
							�� ��
						</button>
			          </div>
			        </td>
			      </tr>
			    </tfoot>
			</table>
			</div>
			<logic:present name="result">
			<logic:equal value="true" name="result">
			<script>
				alert('�����ɹ�!');
				Close();
				window.dialogArguments.document.getElementById('search_go').click();
			</script>
			</logic:equal>
			<logic:equal value="false" name="result">				
			<script>
				alert('����ʧ�ܣ�');
				Close();
			</script>
			</logic:equal>
			</logic:present> 
	</html:form>
</body>
</html>
