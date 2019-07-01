<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/syscommon/pagehead_V4.ini"%>
<!-- 头文件 -->
<script type="text/javascript" src="js/xgutil.js"></script>
<script>
	function save(){
		//必填字段是否填写
		if(filedNotNull(['bjmc','zydm','nj'])){
			//提交
			refreshForm('xxcshgl.do?method=bjxxUpdate&doType=save');
		}else{
			alert('请将带*号的项目填写完整！');
			return false;
		}	
	}
	
</script>
</head>

<body>
	<html:form action="/xxcshgl.do?method=bjxxUpdate" method="post">
		<input type="hidden" name="pkValue" value="${pkValue }" id="pkValue"/>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>${title}</a>
			</p>
		</div>

		<div class="tab">
			<table class="formlist" width="100%">
				<thead>
					<tr>
						<th colspan="4"> 
							<span>基本信息</span>
						</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th width="24%"><font color="red">*</font>班级代码</th>
						<td width="26%">
							${rs.bjdm}
							<input type="hidden" name="save_bjdm" value="${rs.bjdm}"/>						
						</td>
						<th><font color="red">*</font>班级名称</th>
						<td>
							<input type="text" name="save_bjmc" value="${rs.bjmc}" id="bjmc" maxlength="32"/>
						</td>
					</tr>
					<tr>
						<th width="24%">部门代码</th>
						<td width="26%">							
							<input type="text" name="save_bmdm" value="${rs.bmdm}" onkeyup="value=value.replace(/[^a-zA-Z\d]/g,'') " id="bmdm" maxlength="8"/>						
						</td>
						<th>班级简称</th>
						<td>
							<input type="text" name="save_bjjc" value="${rs.bjjc}" maxlength="32"/>
						</td>
					</tr>
					<tr>
						<th width="24%"><font color="red">*</font>专业代码</th>
						<td width="26%">
							<input type="text" name="save_zydm" value="${rs.zydm}" onkeyup="value=value.replace(/[^a-zA-Z\d|-]/g,'') " maxlength="6" id="zydm"/>
						</td>
						<th><font color="red">*</font>年级</th>
						<td><input type="text" name="save_nj" value="${rs.nj}" onkeyup="value=value.replace(/[^\d]/g,'') " id="nj" maxlength="4"/></td>
					</tr>		
				</tbody>
				<tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
			          <div class="btn">
						<div class="buttontool">
			            <logic:notEqual value="view" name="doType">
							<button type="button" class="" onclick="save();return false;" >
								保&nbsp;&nbsp;存
							</button>
			            </logic:notEqual>
						<button type="button" class="" onclick="Close();return false;">
								关&nbsp;&nbsp;闭
						</button>
						</div>
			          </div>
			        </td>
			      </tr>
			    </tfoot>
			</table>		
		</div>	

		<logic:present name="result">
			<intput type="hidden" id="message" value="${message}"/>
			<script>
				alert(document.getElementById('message').value);
				if(window.dialogArguments){
			 		window.dialogArguments.document.getElementById('search_go').onclick();
			 	}
				Close();
			</script>
		</logic:present>
	</html:form>
</body>
</html>