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
					alert("请选择您要统计的项目");
					return false;
				} else if (("002" == text) && ("" == $('xy').value || ""==$('zy').value)){
					alert("请选择<bean:message key="lable.xsgzyxpzxy" />和专业");
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
				<em>您的当前位置:</em><a>${title }</a>
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
								<span>请选择您要统计项目</span>
							</td>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<td colspan="2">
								<div class='btn'>
									<button type="button" onclick='printStuInfoReport();' id="tjbutton">
										确定
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
						<tr>
							<th width="40%">
								项目名称
							</th>
							<td>
								<select name="tjxm" id="tjxm" onchange="setList(this.value);">
									<option></option>
									<option value="000">按<bean:message key="lable.xb" />统计</option>
									<option value="001">按专业统计</option>
									<option value="002">按班级统计</option>
								</select>
							</td>
						</tr>
						<tr id="njtr" style="display:none">
							<th>
								年级
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
								专业
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
								班级
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
							<th>统计类别</th>
							<td>
								<html:checkbox property="xszd" value="xjzt">学籍状态</html:checkbox>
								<html:checkbox property="xszd" value="mzmc">民族</html:checkbox>
								<html:checkbox property="xszd" value="hkxz">户籍</html:checkbox>
								<html:checkbox property="xszd" value="xb">性别</html:checkbox>
								<html:checkbox property="xszd" value="zzmm">政治面貌</html:checkbox>
								<html:checkbox property="xszd" value="kns">困难生</html:checkbox>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</html:form>
	</body>
</html>
