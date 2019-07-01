<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/sharedFunction.js"></script>
		<script type="text/javascript" src="js/dataImp.js"></script>
		<script type="text/javascript" src="js/xgutil.js"></script>
		<script type="text/javascript" src="js/BatAlert.js"></script>
		<script type="text/javascript">
			function repval(obj) {
				obj.value = "";
			}
		</script>
	</head>
	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>${title}</a>
			</p>
		</div>
	
	
		<html:form action='/xtwhOther' method='post'enctype='multipart/form-data'>
			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>照片批量导入</span>
							</th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button type="button" name="上 传" onclick="checkFile1()">
										上 传
									</button>
									
									<button type="button" name="取 消" onclick="Close();return false;" id="btn_close" style="display:none">
										关 闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
						<tr>
							<th width="20%">
								请选择要导入的文件
							</th>
							<td width="80%">
								<input type="file" name="file" id="file" class="text_nor" value="" contenteditable="false" />
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<font color="red">
									说明:    只能导入zip格式的压缩文件，文件大小不能超过50M。
									并且必需把照片放在压缩的根目录下,照片的格式为"学号.jpg",
									单张图片大小请不要超过100k,如果学生的照片已存在，会把该生的照片覆盖
								</font>
							</td>
						</tr>
					</tbody>
				</table>
				</div>
				<script  language="javascript">
					function checkFile1(){
						var tmp = $("file").value.toLowerCase();
						if(tmp==""){
						$("file").value = "";
						alert("您选择的路径不符合，请重新选择！");
						return false;
						}
						if(tmp.substring(tmp.length-3,tmp.length)!="zip"){
							alert("上传文件的格式必须是.zip格式的！");
							return false;
						}
						tmp = tmp.substring(0,tmp.lastIndexOf("\\"))+"\\";
						//alert(tmp);
						BatAlert.showTips("文件上传中，请稍候...");
						refreshForm("picpldr.do?isFile=yes&filepath="+tmp);
					}
				</script>
			<logic:present name="rsMap">
			  <table id="tabPri" class="tbstyle" width="100%">
  				<thead>
					<tr ><td align ="center">导入情况：</td></tr>
					
				</thead>	
				<tr><td width="100%"><span style="color:blue">成功信息:</span><br/>
						<textarea rows="5" cols="73" style="width: 100%" ><bean:write name="sb"/></textarea>
						</td>
				</tr>
				<tr >
						<td><span style="color:red">失败信息:</span><br />
						<textarea rows="5" cols="73" style="width: 100%" ><bean:write name="sb1"/></textarea>
						</td>
				</tr>
				<tr >
						<td><span style="color:red">错误信息(支持格式：.jpg .gif .png .bmp):</span><br/>
						<textarea rows="5" cols="73" style="width: 100%" ><bean:write name="sb2"/></textarea>
						</td>
				</tr>
				</table>
  </logic:present>
	<script>
		if(window.dialogArguments){
			ele('btn_close').display ="";
		}
	</script>
</html:form>
</body>
</html>
