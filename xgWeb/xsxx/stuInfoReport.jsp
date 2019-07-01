<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript">
			function printStuInfoReport(){
				
				var text = $('tjxm').value;
				
				if (text == ""){
					alert("��ѡ����Ҫͳ�Ƶ���Ŀ");
					return false;
				} else if (("002" == text) && ("" == $('xy').value || ""==$('zy').value)){
					alert("��ѡ��<bean:message key="lable.xsgzyxpzxy" />��רҵ");
					return false;
				}
				
				var url =  "/xgxt/stuInfoReport.do?method=getStuInfoReport&flg="+text;
				document.forms[0].action = url;
				document.forms[0].target = "_blank";
				document.forms[0].submit();
				document.forms[0].target = "_self";
			}
			
			function setList(text){
				if (002 == text){
					$('njtr').style.display = "";
					$('xytr').style.display = "";
					$('zytr').style.display = "";
				} else {
					$('njtr').style.display = "none";
					$('xytr').style.display = "none";
					$('zytr').style.display = "none";
				}
			}
			
		</script>
	</head>
	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>${title }</a>
			</p>
		</div>

		<html:form action="/stuInfoReport" method="post">
			<input type="hidden" id="userType" name="userType"value="${userType }" />
			<input type="hidden" id="userName" name="userName"value="${userName }" />
			<input type="hidden" id="message" value="${message }" />
			<input type="hidden" name="njV" id="njV" />
			<input type="hidden" name="xyV" id="xyV" />
			<input type="hidden" name="zyV" id="zyV" />
			<input type="hidden" name="bjV" id="bjV" />
			
			<div class="tab">
				<table class="formlist" width="70%">
					<thead>
						<tr>
							<td colspan="2">
								<span>��ѡ����Ҫͳ����Ŀ</span>
							</td>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<td colspan="2">
								<div class='btn'>
									<button type="button" onclick='printStuInfoReport();' id="tjbutton">
										ȷ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
						<tr>
							<th width="40%">
								��Ŀ����
							</th>
							<td>
								<select name="tjxm" id="tjxm" onchange="setList(this.value);">
									<option></option>
									<option value="000">��<bean:message key="lable.xb" />ͳ��</option>
									<option value="001">��רҵͳ��</option>
									<option value="002">���༶ͳ��</option>
								</select>
							</td>
						</tr>
						<tr id="njtr" style="display:none">
							<th>
								�꼶
							</th>
							<td>
								<html:select property="nj" onchange="initZyList();initBjList()" styleId="nj" style="width:180px">
									<html:option value=""></html:option>
									<html:options collection="njList" property="nj" labelProperty="nj"/>
								</html:select>
							</td>
						</tr>
						
						<tr id="xytr"style="display:none">
							<th>
								<bean:message key="lable.xsgzyxpzxy" />
							</th>
							<td>
								<html:select property="xydm" onchange="initZyList();initBjList()" styleId="xy" style="width:180px">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
												labelProperty="xymc" />
								</html:select>
							</td>
						</tr>
						<tr id="zytr" style="display:none">
							<th>
								רҵ
							</th>
							<td>
								<html:select property="zydm" onchange="initBjList()"  styleId="zy" style="width:180px">
									<html:option value=""></html:option>
									<html:options collection="zyList" property="zydm"
												labelProperty="zymc" />
								</html:select>
							</td>
						</tr>
						<tr id="bjtr" style="display:none">
							<th>
								�༶
							</th>
							<td>
								<html:select property="bjdm"  styleId="bj" style="width:180px">
									<html:option value=""></html:option>
									<html:options collection="bjList" property="bjdm"
												labelProperty="bjmc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>ͳ�����</th>
							<td>
								<html:checkbox property="xszd" value="xjzt">ѧ��״̬</html:checkbox>
								<html:checkbox property="xszd" value="mzmc">����</html:checkbox>
								<html:checkbox property="xszd" value="hkxz">����</html:checkbox>
								<html:checkbox property="xszd" value="xb">�Ա�</html:checkbox>
								<html:checkbox property="xszd" value="zzmm">������ò</html:checkbox>
								<html:checkbox property="xszd" value="kns">������</html:checkbox>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</html:form>
	</body>
</html>
