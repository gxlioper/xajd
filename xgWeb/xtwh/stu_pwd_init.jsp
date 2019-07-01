<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript">
	function showInput(type){
		$('tName').value = type;
		if(type == "single"){			
			document.getElementById('xh').disabled=false;
			document.getElementById('nj').disabled=true;
			document.getElementById('xy').disabled=true;
			document.getElementById('zy').disabled=true;
			document.getElementById('bj').disabled=true;
			document.getElementById('xhTr').style.display="";
			if(document.getElementById('njTr')){
				document.getElementById('njTr').style.display="none";
			}
			document.getElementById('xyTr').style.display="none";
			document.getElementById('zyTr').style.display="none";
			if(document.getElementById('bjTr')){
				document.getElementById('bjTr').style.display="none";
			}
			document.getElementById('single').className="ha";
			if (document.getElementById('batch')){
				document.getElementById('batch').className="";
			}
			
		}else if(type == "batch"){
			document.getElementById('xhTr').style.display="none";
			document.getElementById('xh').disabled=true;
			document.getElementById('nj').disabled=false;
			document.getElementById('xy').disabled=false;
			document.getElementById('zy').disabled=false;
			document.getElementById('bj').disabled=false;
			document.getElementById('xhTr').style.display="none";
			document.getElementById('njTr').style.display="";
			document.getElementById('xyTr').style.display="";
			document.getElementById('zyTr').style.display="";
			document.getElementById('bjTr').style.display="";
			document.getElementById('single').className="";
			document.getElementById('batch').className="ha";
		}
	}
	
	function commit(){
		if(document.getElementById('single').className=="ha"){
			if(document.forms[0].xh.value==null||document.forms[0].xh.value==''){ 
				alert('请输入学号!'); 
			}else if(confirm('确定要初始化密码吗?')){
				document.forms[0].submit();
			}
		}else{
			var ele = ['xy','zy','bj','nj'];
			var message = "";
			for(var i=0; i<ele.length; i++){
				message += document.getElementById(ele[i]).value;				
			}
			if(message==''){
				alert('请选择条件！');
				return false;
			}
			if(confirm('确定要初始化密码吗?')){
				document.forms[0].submit();
			}
		}
	}
	</script>
	<style>
	body {
		width:100%;
		min-height:500px;
		_height:500px;
		background:url(<%=stylePath %>images/blue/lanmu_conbg.gif) no-repeat right bottom;
		background-attachment: fixed;} 
	</style>
	</head>
	<body onload="showInput($('tName').value)" >
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>系统维护 - 口令维护 - 学生密码初始化</a>
			</p>
		</div>

		<html:form action="/stu_pwd_init" method="post">
			<input type="hidden" id="writeAble" name="writeAble"
				value="<bean:write name="writeAble" scope="request"/>" />
			<input type="hidden" name="doflag" value="doflag" />
			<input type="hidden" name="userType" value="${userType}"
				id="userType" />
			<input type="hidden" name="xyV" id="xyV" />
			<input type="hidden" name="zyV" id="zyV" />
			<input type="hidden" name="bjV" id="bjV" />
			<input type="hidden" name="tName" id="tName" value="${tName }"/>

				<div class="compTab" id="card">
					<div class="comp_title">
						<ul>
							<li id="single">
								<a onclick="showInput('single')"><span>单个初始化</span>
								</a>
							</li>
							<logic:equal value="admin" name="userType">
								<li id="batch">
									<a onclick="showInput('batch')"><span>批量初始化</span>
									</a>
								</li>
							</logic:equal>
						</ul>
					</div>
				</div>
				<div class="tab">
					<table width="100%" border="0" class="formlist">
						<tfoot>
							<tr>
								<td colspan="4">
									<div class="bz">
										<font color="red">初始化后默认密码为888888</font>
									</div>
									<div class="btn">
										<button type="button" name="Submit1" onclick="commit();">
											提交
										</button>
										<button type="button" name="重置" type="reset" onclick="reset();">
											重置
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
						<tbody>
							<tr>
								<th width="34%"></th>
								<td width="66%"></td>
							</tr>
							<tr id="xhTr">
								<th>
									输入学号
								</th>
								<td>
									<input type="input" name="xh" style="width:180px" id="xh" />
								</td>
							</tr>
							<tr id="njTr">
								<th>
									输入年级
								</th>
								<td>
									<html:select property="nj" disabled="true" styleId="nj"
										onchange="initZyList();initBj();">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj"
											labelProperty="nj" />
									</html:select>
								</td>
							</tr>
							<tr id="xyTr">
								<th>
									输入<bean:message key="lable.xsgzyxpzxy" />
								</th>
								<td>
									<html:select property="xydm" disabled="true" styleId="xy"
										onchange="initZyList();initBj();">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
								</td>
							</tr>
							<tr id="zyTr">
								<th>
									输入专业
								</th>
								<td>
									<html:select property="zydm" disabled="true" styleId="zy"
										onchange="initBj();">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zydm"
											labelProperty="zymc" />
									</html:select>
								</td>
							</tr>
							<tr id="bjTr">
								<th>
									输入班级
								</th>
								<td>
									<html:select property="bjdm" disabled="true" styleId="bj">
										<html:option value=""></html:option>
										<html:options collection="bjList" property="bjdm"
											labelProperty="bjmc" />
									</html:select>
								</td>
							</tr>
						</tbody>
					</table>
				</div>				
				<logic:notEmpty name="commanForm" property="changed" scope="request">
					<script language="javascript">
						alert("<bean:write name='commanForm' property='changed' scope='request' />");
					</script>
				</logic:notEmpty>
				</html:form>
	</body>
</html>
