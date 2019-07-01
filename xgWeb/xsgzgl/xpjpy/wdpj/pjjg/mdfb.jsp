<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type='text/javascript'>
		function fbmd(){
			var url = "xpj_pjjg.do?method=mdfb&type=save";
			var newsTitle = jQuery("#xn").val()+jQuery("#xqmc").val()+jQuery("#xmmc").val()+"公示名单";
			
			var editorid="<p><br /></p><div style='text-align:center;'><span style='font-size:medium;'>2016-2017学年"+jQuery("#xmmc").val()+"公示名单<span></span></span></div><br />";
			editorid+="</span><p><table style='width:100%;' cellpadding='2' cellspacing='0' border='1' bordercolor='#000000'><tbody><tr><td>序号";
			editorid+="</td><td>学院</td><td>班级</td><td>学号</td><td>姓名</td></tr><tr><td>1</td><td>";
			editorid+="<span></span><span style='font-family:tahoma, Arial, 宋体;background-color:#FFFFFF;'>电子信息学院&nbsp;</span><br />";
			editorid+="</td><td>2015楼宇智能化工程技术02班</td><td>2015010002<br /></td><td>祝楠</td></tr><tr><td>2</td><td>";
			editorid+="<span style='font-family:tahoma, Arial, 宋体;background-color:#FFFFFF;'>电子信息学院</span><br /></td>";
			editorid+="<td><span style='font-family:tahoma, Arial, 宋体;background-color:#FFFFFF;'>2015计算机信息管理01班</span><br /></td>";
			editorid+="<td><span style='font-family:tahoma, Arial, 宋体;background-color:#FFFFFF;'>2015010007&nbsp;</span><br /></td>";
			editorid+="<td><span style='font-family:tahoma, Arial, 宋体;background-color:#FFFFFF;'>楼芳燕&nbsp;</span><br /></td></tr>";
			editorid+="</tbody></table><span><span></span><br /></span></p><p><br />公示期七天，如有异议者，请署真实姓名，以书面的形式向学院反映。</p><br />";
			jQuery("#newsTitle").val(newsTitle);
			jQuery("#editorid").val(editorid);
			ajaxSubFormWithFun("xpjPjjgModel", url, function(data) {
				 if(data["message"]=="保存成功！"){
		    		 showAlert(data["message"],{},{"clkFun":function(){
							if (parent.window){
								refershParent();
							}
						}});
		    	 }else{
		    		 showAlert(data["message"]);
		    		}
				});
			}
		</script>
	</head>
	<body style="width: 100%">
		<html:form action="/xpj_pjjg" method="post" styleId="xpjPjjgModel" onsubmit="return false;">
			<input type="hidden" name="typeid" id="typeid" value="-1"/>
			<input type="hidden" name="toWho" id="toWho" value="all_teastu"/>
			<input type="hidden" name="sffb" id="toWho" value="是"/>
			<input type="hidden" name="sfzd" id="sfzd" value="是"/>
			<input type="hidden" name="selectRadio" id="selectRadio" value="on"/>
			<input type="hidden" name="xn" id="xn" value="${xn}"/>
			<input type="hidden" name="xqmc" id="xqmc" value="${xqmc}"/>
			<input type="hidden" name="newsTitle" id="newsTitle"/>
			<input type="hidden" name="editorid" id="editorid"/>
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<tbody>
						</br>
						<tr>
						    <th>
						    <div align="left">
						    <font color="#FF9933">奖项名称</font>
									<html:select property="xmmc" styleId="xmmc" style="width:130px">
									<html:options collection="jxList" labelProperty="xmmc"
										property="xmmc" />
								</html:select>
						    </div>
							</th>
						</br>
						</tr>
					</tbody>
				
				 </table>
				 </div>
			    <div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
				<tfoot>
					<tr>
						<td colspan="4">
							<div class="btn">
								<button type="button" onclick="fbmd()">
									确定
								</button>
								<button type="button" onclick="Close();return false;">
									取消
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
			</div>
		</html:form>
	</body>
</html>

