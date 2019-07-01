<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
    <%@ include file="/syscommon/pagehead_V4.ini"%>
    <script type="text/javascript">
		function zpdr(url){
			var fileValue = document.getElementById("file").value;
			if(fileValue == null || fileValue == ""){
				alert("请先选择要导入的文件！");
				return false;
			}
			var fileType =".zip";
			if(-1==fileType.indexOf(fileValue.substr(fileValue.lastIndexOf('.')))){
				alert('导入文件格式不正确，请重新选择！');
				return false;
			}
		
			document.forms[0].action = url;
			document.forms[0].target = "_blank";
			document.forms[0].submit();
			document.forms[0].target = "_self";
		}
		</script>
  	</head>
  
  <body style="">
	<div class="tab">
  	<form action="/xtwhZpgl" method="post" enctype="multipart/form-data">
  	<input type="hidden" name="zpType" value="${zpType}"/>
    <table class="formlist" width="100%" align="center">
    	<thead>
			<tr>
				<th colspan="4">
					<span>照片批量导入</span>
				</th>
			</tr>
		</thead>
		<tbody>
		<tr>
			<td align="center">
			照片命名类型：<SELECT id="photoNameType" name="photoNameType" style="width:120px">
					 	 <option value="xh">学号</option>
					 	 <option value="sfzh">身份证号</option>
					 	 <option value="ksh">考生号</option>
					 </SELECT>&nbsp;&nbsp;&nbsp;&nbsp;
			上传文件：<input type="file" name="file" id="file" value="" contenteditable="false"/>
			</td>
		</tr>
		<tr><td>
			<div class="readme">
			  <h2>说明：</h2>
			  <div class="readcon">
			    <ul>
			      <li>只能导入zip格式的压缩文件（不能直接使用rar修改后缀为zip）。</li>
			      <li>照片的格式为"编号.jpg"（编号指学号、录取号、考生号或身份证号）。</li>
			      <li>单张照片大小请不要超过100k,zip包大小不要超过50M。</li>
			      <li>如果学生的照片已存在，会将该生的照片覆盖。</li>
			    </ul>  
			
			
			  </div>
			</div>
			</td></tr>
		</tbody>
		<tfoot>
		<tr>
			<td align="right" id="dr">
			<button type="button"  class="button" onclick="zpdr('xtwhZpgl.do?method=xszpdrManage&doType=save');">导入</button>
			<button type="button"  class="button" onclick="Close();return false;">关闭</button>
			</td>
		</tr>
		</tfoot>
		
			
		
	</table>
	</form>
	</div>
	 
	
  </body>
</html>
