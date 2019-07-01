<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：伟大的骆 -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/xszz/jtcy.js"></script>
		<script language="javascript" src="js/xszz/xszzComm.js"></script>
		<script language="javascript" src="js/xszz/xszzFunction.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getOtherData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getXszzInfo.js'></script>
		<script language="javascript" src="js/xszz/xszzInit.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/interface/getStuDetails.js"></script>
		<script language="javascript" src="js/xszz/xszzOptionList.js"></script>	
		<script language="javascript">
		function uploadZzFile(url){
			var filePath = $('file').value;
			if(filePath==""||filePath==null){
               alert("上传地址不能为空！");    
               return false;   
		    }else{
			   refreshForm(url);
		    }
		}
		</script>
	</head>

	<body onload="">
		<!-- 标题 -->
		<!-- 标题 end-->
		<html:form action="/commXszz" method="post" enctype="multipart/form-data">
		<input type="hidden" id="scdz" name="scdz" value="${filePath }"/>
		<table width="100%" border="0" class="formlist">
		<tr>
		<th height="20px"><div class="btn"></div>
		</th>
		<td></td>
		</tr>
				<tr>
						<th>
						       附件上传
						</th>
						<td> 
							<input type="file" id="file" name="uploadFile" style="width:60%"/>
							&nbsp;&nbsp;
							<font color="red">(文件大小小于&lt;10M&gt;)</font>
						</td>
				</tr>
				<tfoot><tr>
				<th></th>
				<td> <div class="btn">
				<button type="button" id="buttonSave" onclick="uploadZzFile('/xgxt/commXszz.do?method=uploadFile');" style="width: 80px">
					上传
				</button>
				</div></td></tr>
				</tfoot>
			</table>
		</html:form>
	</body>
	<logic:present name="message">
	<logic:equal name="message" value="上传成功">
		<script type="text/javascript">
		var scdz = window.dialogArguments.window.document.getElementById('scdz');
		if(scdz){
			scdz.value = $('scdz').value; 
		}
		Close();
		//window.dialogArguments.window.alert("上传成功！");
	   </script>
	</logic:equal>
	</logic:present>
</html>