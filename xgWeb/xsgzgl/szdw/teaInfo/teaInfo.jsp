<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>
		<script type="text/javascript">
			var j=0;
		
			function uploadTeaPic(){
				jQuery.ajaxFileUpload({
				  url:'szdw_teaInfo.do?method=uploadTeaPic&zgh=${rs.zgh}',//服务器端程序
				  secureuri:false,
				  fileElementId:'teaPic'//input框的ID
				});
				
				//jQuery('#teaImg').empty();
				  	
			  	//var html = '<img src="teaPic.jsp?zgh=${rs.zgh }&flg=';
			  	//	html+= (++j);
			  	//	html+= '" border="0" align="absmiddle" style="width:120px;height:160px" />'
				  	
				//  jQuery("#teaImg").html(html).show();

				document.forms[0].action = window.location.href;
				document.forms[0].submit();
			}
			
			jQuery(function(){
				var zgh = '${rs.zgh}';
				jQuery.ajaxSetup({async:false});
					jQuery.post('szdw_teaInfo.do?method=getOtherInfo',{zgh:zgh},function(data){
						setKzzd(data,['jbxx','lxfs','gzxx','qtxx']);
					},'json');
				jQuery.ajaxSetup({async:true});
			});
			
			
			function setKzzd(data,xsqy){
			
				for (var j = 0; j < xsqy.length ; j++){
			
					var tdArr = [];
					var tdLongArr = [];
					//先把th td 加好放在数组里
					for (var i = 0 ; i < data.length; i++){
						
						if (data[i].xsqy == xsqy[j]){
						
							var html = "";
							var zdz = data[i].zdz==null ? '' : data[i].zdz;
							
							if ('long'==data[i].xslx){
								html+= "<th>"+data[i].zdmc+"</th>";
								html+= "<td colspan='4' >"+zdz+"</td>";
								tdLongArr[tdLongArr.length] = html;
							} else if ('textarea'==data[i].xslx){
								html+= "<th>"+data[i].zdmc;
								html+= "<br/><font color='red'>&lt;限400字&gt;</font>"+"</th>";
								html+= "<td colspan='4' style='word-break:break-all;'><textarea rows='4' onblur='chLeng(this,400)' style='width:95%' name='"+data[i].zdm+"'>"+zdz+"</textarea></td>";
								tdLongArr[tdLongArr.length] = html;
							} else if ('text' == data[i].xslx){
								html+= "<th>"+data[i].zdmc+"</th>";
								html+= "<td><input type='text' maxlength='"+data[i].zdcd+"' style='120px' name='"+data[i].zdm+"' value='"+zdz+"'></td>";
								tdArr[tdArr.length] = html;
							} else if ('time' == data[i].xslx){
		
								html+= "<th>"+data[i].zdmc+"</th>";
								html+= "<td><input type='text' maxlength='"+data[i].zdcd+"' style='120px' name='"+data[i].zdm+"' id='"+data[i].zdm+"' value='"+zdz+"' onclick=\"return showCalendar('"+data[i].zdm+"','y-mm-dd');\"  onblur=\"dateFormatChg(this)\" readonly=\"true\"></td>";
								tdArr[tdArr.length] = html;
							}else if ('select' == data[i].xslx){
								
								var optionHtml = '<option value=""></option>';
								
								if (data[i].select_table == null){
									var optArr = data[i].select_data.split(",");
									
									for (var x = 0 ; x < optArr.length ; x++){
										var opt = optArr[x].split("/");
										optionHtml += '<option value="'+opt[0]+'"';
										
										if (opt[0] == data[i].zdz){
											optionHtml += ' selected="true" ';
										}
										
										optionHtml += '>'+opt[1]+'</option>';
									}
								} else {
								
									jQuery.post('szdw_teaInfo.do?method=getSelectData',{tableName:data[i].select_table},function(d){
										for (var y = 0 ; y < d.length ; y++){
											optionHtml += '<option value="'+d[y].dm+'"';
										
											if (d[y].dm == data[i].zdz){
												optionHtml += ' selected="true" ';
											}
											optionHtml += '>'+d[y].mc+'</option>';
										}
									},'json');
								}
								
								html+= "<th>"+data[i].zdmc+"</th>";
								html+= "<td><select name="+data[i].zdm+" id="+data[i].zdm+">"+optionHtml+"</select></td>";
								tdArr[tdArr.length] = html;
							} 
						}
					}
					
					//把short字段放在行里再加到tbody
					if (tdArr.length > 0){
						var trHtml = "";
						for (var i = 0 ; i <tdArr.length ; i++){
							if (i%2 == 0 && i != 0){
								trHtml +="</tr><tr name=\"xsq_tr\">";
							}
							if (i == 0)	{
								trHtml +="<tr name=\"xsq_tr\">";
							}
							trHtml+=tdArr[i];	
						}
					
						if (tdArr.length%2 != 0){
							trHtml += "<th></th><td></td>";
						}
						trHtml += "</tr>";
						jQuery('#'+xsqy[j]).append(trHtml);
						
					}
					
					//把long textarea字段放在行里再加到tbody
					if (tdLongArr.length > 0){
						var trHtml = "";
						for (var i = 0 ; i <tdLongArr.length ; i++){
							
							trHtml += "<tr>";
							trHtml += tdLongArr[i];
							trHtml += "</tr>";
						}
					
						jQuery('#'+xsqy[j]).append(trHtml);
					}
				}
				
				// --------------------右边的td colspan 属性修改为3个 qlj-------------------------
				jQuery("[name=xsq_tr]").each(function(){
					jQuery(this).children("td").eq(1).attr("colspan","3");
				});
			}
			
		</script>
	</head>
	<body>
		<html:form action="/szdw_teaInfo" method="post" enctype='multipart/form-data'>
			<div class="tab" style="overflow-x:hidden;overflow-y:auto;height:500px" />
				<table class="formlist" width="93%">
					<%@include file="jbxx.jsp" %>
					<jsp:include flush="true" page="lxfs.jsp" />
					<jsp:include flush="true" page="gzxx.jsp" />
					<jsp:include flush="true" page="dbxx.jsp" />
					<jsp:include flush="true" page="qtxx.jsp" />
				</table>
			</div>
			<table class="formlist" width="93%">
				<tfoot>
					<tr>
						<td colspan="5">
							<div class="btn">
								<logic:notEqual value="view" property="doType" name="teaInfoForm">
									<button type="button" name="保存" id="buttonSave"
										onclick="confirmInfo('您确定要保存吗？',function(t){if(t == 'ok'){saveUpdate('szdw_teaInfo.do?method=saveTeaInfo','');}});">
										保&nbsp;&nbsp;存
									</button>
								</logic:notEqual>
								<button type="button" name="关闭" onclick="window.close();return false;">
									关&nbsp;&nbsp;闭
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
			<!-- 完善教师信息 - 上传照片 -->
			<div id="addPic" style="display:none">
				<jsp:include flush="true" page="uploadPic.jsp"></jsp:include>
			</div>
		</html:form>
		<logic:present name="message">
			<script>
				alertInfo('${message}',function(t){
					if (t=="ok") {
						window.close();
						window.dialogArguments.document.getElementById('search_go').click();
					}
				});
			</script>
		</logic:present>
	</body>
</html>
