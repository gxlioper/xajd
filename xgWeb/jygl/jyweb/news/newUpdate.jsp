<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/jyweb.ini"%>
		<script type="text/javascript" src="js/String.js"></script>
		<script language="javascript">
		function chgEditer(obj){
					var url = "eWebEditor/eWebEditor.jsp?color=" + obj.value;
					eWebEditor1.location = url;
				}
				
		function pubNews(){
			if(frames('eWebEditor1').getHTML()){
				$('content1').value = frames('eWebEditor1').getHTML();
				$('wjnr').value = $('content1').value; 
			}
		}
		
		function setTpNew(text){
			if ("2" == text){
				$('jpg').style.display="";
			} else {
				$('jpg').style.display="none";
			}
		}
		
		function jpgYz(filePath){
			var path = filePath.substr(filePath.length-3,filePath.length);
			if ("jpg"!=path && "JPG"!=path){
				alert("请选择jpg格式的图片");
				$('buttonSave').disabled=true;
				return false;
			} else {
				$('buttonSave').disabled=false;
			}
		}
		
	</script>
	</head>
	<body>
		<html:form action="/jyweb" method="post" enctype="multipart/form-data">
			<input type="hidden" name="pkValue" value="${pkValue }" />

			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>新闻维护</span>
							</th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"
									<span class="red">*</span>"为必填项
								</div>
								<div class="btn">
									<logic:present name="doType">
										<button name="提交" id="buttonSave"
											onclick="pubNews();saveUpdate1('jywebUseCheckSession.do?method=newUpdate&type=wjbt&doType=modify','wjbt-wjnr');">
											保存
										</button>
									</logic:present>
									<logic:notPresent name="doType">
										<button name="提交" id="buttonSave"
											onclick="pubNews();saveUpdate1('jywebUseCheckSession.do?method=newUpdate&doType=save','wjbt-wjnr');">
											保存
										</button>
									</logic:notPresent>
									<button onclick="window.close();return false;">
										关闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
						<tr>
							<th width="14%">
								<span class="red">*</span>新闻类型
							</th>
							<td width="86%">
								<html:select property="wjlx" value="${rs.wjlx }"  onchange="setTpNew(this.value);">
									<html:options collection="newLxList" property="en"
										labelProperty="cn" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								<span class="red">*</span>标题
							</th>
							<td>
								<html:text styleId="wjbt" property="wjbt" maxlength="200"
									style="width:95%" value="${rs.wjbt }"
									onkeyup="value=value.replace(/[+&%# ]/g,'')" />
							</td>
						</tr>


						<logic:present name="rs" property="tplj">
							<tr id="tpyl">
								<th>
									图片预览
								</th>
								<td>
									<img
										src="/xgxt/jygl/jyweb/download.jsp?
										filePath=<%=request.getRealPath("batEditor/WEB-INF/upLoad") %>/${rs.tplj }"
										style="width:100px;height: 100px" />
								</td>
							</tr>
							<tr id="jpg">
						</logic:present>
						<logic:notPresent name="rs" property="tplj">
							<tr id="jpg" style="display:none">
						</logic:notPresent>
						<th>
							<span class="red">*<限jpg格式>
							</span>图片
						</th>
						<td>
							<html:file property="file" onchange="jpgYz(this.value);"
								style="width:80%"></html:file>
						</td>
						</tr>



						<tr>
							<th>
								<span class="red">*</span>内容
							</th>
							<td>
								<input type="hidden" name="content1" value="${rs.wjnr }" />
								<input type="hidden" name="wjnr" id="wjnr" value="${rs.wjnr }" />
								<iframe id="eWebEditor1" src="BatEditor" frameborder="0"
									scrolling="no" width="96%" height="350"></iframe>

								<input type="hidden" name="fbr" value="${jyweb_realName }" />
								<input type="hidden" name="fbsj" value="${nowTime }" />
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</html:form>

		<logic:present name="message" scope="request">
			<script>
 			alert("${message}");
 			if(window.dialogArguments){
 				window.close();
 				dialogArgumentsQueryChick();
 			}
 			
 		</script>
		</logic:present>

	</body>
</html>
