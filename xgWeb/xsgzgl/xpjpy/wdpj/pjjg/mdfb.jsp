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
			var newsTitle = jQuery("#xn").val()+jQuery("#xqmc").val()+jQuery("#xmmc").val()+"��ʾ����";
			
			var editorid="<p><br /></p><div style='text-align:center;'><span style='font-size:medium;'>2016-2017ѧ��"+jQuery("#xmmc").val()+"��ʾ����<span></span></span></div><br />";
			editorid+="</span><p><table style='width:100%;' cellpadding='2' cellspacing='0' border='1' bordercolor='#000000'><tbody><tr><td>���";
			editorid+="</td><td>ѧԺ</td><td>�༶</td><td>ѧ��</td><td>����</td></tr><tr><td>1</td><td>";
			editorid+="<span></span><span style='font-family:tahoma, Arial, ����;background-color:#FFFFFF;'>������ϢѧԺ&nbsp;</span><br />";
			editorid+="</td><td>2015¥�����ܻ����̼���02��</td><td>2015010002<br /></td><td>ף�</td></tr><tr><td>2</td><td>";
			editorid+="<span style='font-family:tahoma, Arial, ����;background-color:#FFFFFF;'>������ϢѧԺ</span><br /></td>";
			editorid+="<td><span style='font-family:tahoma, Arial, ����;background-color:#FFFFFF;'>2015�������Ϣ����01��</span><br /></td>";
			editorid+="<td><span style='font-family:tahoma, Arial, ����;background-color:#FFFFFF;'>2015010007&nbsp;</span><br /></td>";
			editorid+="<td><span style='font-family:tahoma, Arial, ����;background-color:#FFFFFF;'>¥����&nbsp;</span><br /></td></tr>";
			editorid+="</tbody></table><span><span></span><br /></span></p><p><br />��ʾ�����죬���������ߣ�������ʵ���������������ʽ��ѧԺ��ӳ��</p><br />";
			jQuery("#newsTitle").val(newsTitle);
			jQuery("#editorid").val(editorid);
			ajaxSubFormWithFun("xpjPjjgModel", url, function(data) {
				 if(data["message"]=="����ɹ���"){
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
			<input type="hidden" name="sffb" id="toWho" value="��"/>
			<input type="hidden" name="sfzd" id="sfzd" value="��"/>
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
						    <font color="#FF9933">��������</font>
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
									ȷ��
								</button>
								<button type="button" onclick="Close();return false;">
									ȡ��
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

