<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html>
 <head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript">
         function  submit(){
        	 refreshForm('/xgxt/commXszz.do?method=jgcxUpdate');
         }
	</script>
	</head>
  
  <body>
    <html:form action="/commXszz"  method="post" enctype="multipart/form-data">
			<table width="100%" border="0" class="formlist">
				<tr>
						<th>
									附件上传
						</th>
						<td> 
							<input type="file" name="uploadFile" style="width:60%"/>
							&nbsp;&nbsp;
							<font color="red">(文件大小小于&lt;10M&gt;)</font>
						</td>
						
				</tr>
				<tfoot><tr>
				<td></td>
				<td> <div class="btn">
				<button type="button" id="buttonSave" onclick="submit()" style="width: 80px">
					上传
				</button>
				</div></td></tr>
				</tfoot>
			</table>
</html:form>
  </body>
</html>
