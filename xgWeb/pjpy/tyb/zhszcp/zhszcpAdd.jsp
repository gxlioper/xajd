<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- ͷ�ļ� -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
	<script type="text/javascript" src="js/check.js"></script>
	<script type="text/javascript">
<!--
	function saveData() {
		var doc = "";
		if (document.all){//IE
             doc = document.frames["zdyFrame"].document;
        }else{//Firefox    
             doc = document.getElementById("zdyFrame").contentDocument;
        }
		
		var len = doc.getElementById('flag').rows.length;
		
		if (len == 0) {
			alert("������Ҫ��������ݣ�");
			return false;
		}
		
		var jsonStr = doc.getElementById('jsonStr').value;
		if (jsonStr != null && jsonStr != '') {
			var array = jsonStr.split("!@");

			for (var i=0;i<array.length;i++) {
				if (array[i] != null && array[i] != "") {
					
				var obj = eval('(' + array[i] + ')');

				//�ǿ��ֶ����ǿռ��	
				if (obj.sfnull == '0') {
					//�ֶ����ƶ�������
					var nameArr = doc.getElementsByName(obj.zdid);
					
					for (var j=0;j<nameArr.length;j++) {
						if (nameArr[j].value == null || nameArr[j].value == "") {
							alert("��*���ֶ�Ϊ������!");
							return false;		
						}
					}
				}
				}
			}
		}
		
		
	}	

//-->
</script>
</head>
<body onload="">
	<html:form action="/pjpyTybZhszcp.do?method=zhszcpAdd" method="post">
		<!-- ������ʾ��Ϣ -->
		<logic:present name="result">
			<logic:equal value="true" name="result">
				<script>
					alert("�����ɹ�!");
					//document.getElementById('zdyFrame').style.display = "none";
					Close();
					window.dialogArguments.document.getElementById('search_go').click();
				</script>
			</logic:equal>
			<logic:equal value="false" name="result">
				<script>
					alert("����ʧ��,�벻Ҫ�����ظ�������!");
					//document.getElementById('zdyFrame').style.display = "none";
					Close();
					window.dialogArguments.document.getElementById('search_go').click();
				</script>
			</logic:equal>
		</logic:present>
	
		<input type="hidden" name="errmsg" id="errmsg" value="${errmsg }"/>
		<input type="hidden" name="userType" id="userType"
			value="${userType }"/>
		<%--		<input type="hidden" name="act" id="act" value="save"/>--%>
		<input type="hidden" id="disableEle" name="disableEle"
			value="xm-xb-xy-nj-zy-bj" />
		<input type="hidden" id="readonlyEle" name="readonlyEle" value="" />
		<input type="hidden" id="getStuInfo" name="getStuInfo"
			value="xm-xb-xymc-nj-zymc-bjmc" />
		<input type="hidden" id="url" name="url" value="/pjpy_tyb_zhszcpAdd.do?bm=${bm}&dmlb=${dmlb}&dm=${dm}" />
		<input type="hidden" name="bm" id="bm" value="${bm }"/>
		<input type="hidden" name="dmlb" id="dmlb" value="${dmlb}"/>
		<input type="hidden" name="dm" id="dm" value="${dm}" />
		
		<input type="hidden" name="content" id="content"/>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>${title }</a>
			</p>
		</div>
		<div class="tab">
		<table class="formlist" width="100%">
			<thead>
				<tr>
					<th colspan="4">
						<span>��������</span>
					</th>
				</tr>
			</thead>
			<tbody>
			<tr>
				<th width="20%"><font color="red">*</font>ѧ��</th>
				<td width="30%">
					<input type='text' name="hidstr" id="hidstr" style="text-align:left;ime-mode:disabled;width:0px;border:0;" readonly="readonly"/>
					<html:text name='rs' property="xh" styleId="xh" onblur="if (this.value=='') {return false;} else {refreshForm('pjpyTybZhszcp.do?method=zhszcpAdd');return false;}"/>
					<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
						class="btn_01" id="buttonFindStu">
						ѡ��
					</button>
				</td>
				<th width="20%">����</th>
				<td width="30%">
					${rs.xm }
				</td>
			</tr>
			<tr>
				<th>�Ա�</th>
				<td>
					${rs.xb }
				</td>
				<th>�꼶</th>
				<td>
					${rs.nj }
				</td>

			</tr>
			<tr>
				<th><bean:message key="lable.xsgzyxpzxy" /></th>
				<td>
					${rs.xymc }
				</td>
				<th>רҵ</th>
				<td>
					${rs.zymc }
				</td>
			</tr>
			<tr>
				<th>�༶</th>
				<td>
					${rs.bjmc }
				</td>
				<th>ѧ��</th>
				<td>
					${rs.xz }
				</td>
			</tr>

			<tr>
				<logic:equal value="nd" name="pjzq">
					<th>���</th>
					<td>
						${rs.nd }
					</td>
					<td></td>
					<td></td>
				</logic:equal>
				<logic:equal value="xn" name="pjzq">
					<th>ѧ��</th>
					<td>
						${rs.xn }
					</td>
					<td></td>
					<td></td>
				</logic:equal>
				<logic:equal value="xq" name="pjzq">
					<th>ѧ��</th>
					<td>
						${rs.xn }
					</td>
					<th>ѧ��</th>
					<td>
						${rs.xqmc }
					</td>
				</logic:equal>
			</tr>
			
			<tr>
				<td colspan="4" style="100%">
					<!-- �Զ����ֶ�չ�� -->
					<IFRAME id="zdyFrame" name="zdyFrame" style="width:100%;height: 400px;"
					marginwidth='0' marginheight='0' framespacing='0' frameborder='0'
					src="pjpy_tyb_zhszcpZdzx.do?bm=${bm}&xh=${rs.xh }&dmlb=${dmlb }&dm=${dm }"></IFRAME>
				</td>
			</tr>
			</tbody>
		</table>
	</html:form>
</body>

