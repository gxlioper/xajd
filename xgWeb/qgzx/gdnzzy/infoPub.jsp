<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript">
		function chgEditer(obj){
			var url = "eWebEditor/eWebEditor.jsp?color=" + obj.value;
			eWebEditor1.location = url;
		}
		function pubInfo(){
			if(document.getElementById("infoTitle").value == ""){
				alert("请填写标题！");
					document.getElementById("infoTitle").focus();
				return false;
			}
			document.getElementById('content1').value = frames('eWebEditor1').getHTML();
			if(document.getElementById("content1").value == ""){
				alert("请填写要发布的信息！");
				return false;
			}
			refreshForm('qgzxLogic.do?method=savePubInfo');
		}
	</script>
	</head>
	<body>
		<form method="post" id="myform"
			action="/xgxt/qgzxLogic.do?method=savePubInfo">
			<input type="hidden" id="gnmkdm" name="gnmkdm" value="${gnmkdm}"/>
			<div class="tab_cur" id="jd">
				<p class="location"  id="title_m">
					<em>您的当前位置:</em><a>${title}</a>
				</p>
			</div>
			<div class="tab">
				<table width="100%"  border="0" class="formlist">
				 <thead>
    				<tr>
        				<th colspan="4"><span>相关信息</span></th>
        			</tr>
   				 </thead>
   				 <tfoot>
   				 	<tr>
						<td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
						          <div class="btn">
						          	<button type="button"  onclick="pubInfo()">
										发 布
									</button>
									<button type="button" type="reset" name=b2 value="重 填" >
										重 填
									</button>		           
						          </div>
						 </td>
					</tr>
   				 </tfoot>
				<tbody>
				<tr>
					<th >
						<span class="red">*</span>标题
					</th>
					<td colspan="3">
						<select name="infoTitle" id="infoTitle" style="width:20%">
							<logic:empty name="gnmkdm">
							<option value=""></option>
							<option value="勤工助学简介">
								勤工助学简介
							</option>
							<option value="勤工助学须知">
								勤工助学须知
							</option>
							<option value="岗位职责信息">
								岗位职责信息
							</option>
							<option value="定薪定岗公示">
								定薪定岗公示
							</option>
							</logic:empty>
							<!--学生信息-->
							<logic:equal value="N11" name="gnmkdm">
							<option value="转档须知">
								转档须知
							</option>
							</logic:equal>
						</select>
					</td>
				</tr>
				<tr>
					<th align=right width="100">
						<span class="red">*</span>编辑内容
					</th>
					<td align=center colspan="3">
						<INPUT type="hidden" name="content1" value="">
						<IFRAME ID="eWebEditor1" src="BatEditor" frameborder="0"
							scrolling="no" width="100%" height="350"></IFRAME>
					</td>
				</tr>
			</TABLE>
			<logic:present name="result">
				<logic:equal value="true" name="result">
					<script>	
	  		alert("发布成功!");
	  	</script>
				</logic:equal>
				<logic:equal value="false" name="result">
					<script>	
	  		alert("发布失败!");
	  	</script>
				</logic:equal>
			</logic:present>
		</FORM>
	</body>
</html>
