<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- 头文件 -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript">	
		function loadnr(){
			if(document.getElementById('xxdm').value=='11122'){
				document.getElementById('content').innerHTML=document.getElementById("content1").value;
			}	
		}
	</script>
</head>
<body onload="loadnr()">
	<html:form action="/data_search" method="post">
		<input type="hidden" name="content1" value="${nr}" id="content1"/>
		<input type="hidden" name="xxdm" value="${xxdm}" id="xxdm"/>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>学生信息 - 转档管理 - 转档须知</a>
			</p>
		</div>	
		<div class="tab">
		  <table width="100%" border="0" class="formlist">
		    <thead>
		    	<tr>
		        	<th colspan="4"><span>办理档案转移手续须知</span></th>
		        </tr>
		    </thead>
		    <tbody>
		      <tr>
				<th>
					<img src="/xgxt/images/xsxx/zdxz.jpg"/>
				</th>	
			  </tr>	
			</tbody>
			</table>
		</div>
	</html:form>
	</body>
</html>
