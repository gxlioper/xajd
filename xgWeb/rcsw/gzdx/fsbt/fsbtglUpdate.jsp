<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
			function dataSave(url,mustFill){
				var eles = mustFill.split("-");
				for (i = 0; i < eles.length; i++) {
					if (document.getElementById(eles[i]).value == "") {
						alertInfo("�����ֶ�δ��������");
						return false;
					}
				}
				$('buttonSave').disabled = "disabled";
				document.forms[0].action = url;
				document.forms[0].submit();
			}
			
			jQuery(function(){
				showJe();
				
				jQuery("#btdm").change(function(){
					showJe();
				});	
			});

			function showJe(){
				var btdm = jQuery("#btdm").val();
				if(!btdm){
					jQuery("#span_btje").html("");
				}else {
					jQuery("#span_btje").html(jQuery("#" + btdm + "_je").val());
				}
			}
			
		</script>
		<style type="text/css">
			table{
				border-collapse:collapse;
			}
			
			table th{
				width:20%;
			}
			
			table td{
				width:30%;
			}
			
			table span{
				color:red;
			}
		</style>
	</head>
	<body>
		<html:form action="/rcsw_gzdx_fsbtgl" method="post">
			<input type="hidden" name="pkValue" value="${param.pkValue }"/>
			<input type="hidden" name="doType" value="${doType}"/>
			<input type="hidden" name="url" value="/rcsw_gzdx_fsbtgl.do?method=fsbtglUpdate"/>
			<input type="hidden" id="getStuInfo" name="getStuInfo" value="xm-xh" />
			
				<div class="tab">
					<table width="100%" class="formlist">
						<thead>
							<tr>
								<th colspan="4"><span>��������</span></th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th>
									<span>*</span>���
								</th>
								<td>
									<html:select property="nd">
										<html:options collection="ndList" property="nd" labelProperty="nd"/>
									</html:select>
								</td>
								<th>
									<span>*</span>�·�
								</th>
								<td>
									<html:select property="yf">
										<html:option value=""></html:option>
										<html:options collection="yfList" property="dm" labelProperty="mc"/>
									</html:select>
								</td>
							</tr>
							<tr>
								<th>
									<span>*</span>������Ŀ
								</th>
								<td>
									<html:select property="btdm" styleId="btdm">
										<html:option value=""></html:option>
										<html:options collection="fsbtList" property="dm" labelProperty="mc"/>
									</html:select>
									<logic:iterate id="fsbtMap" name="fsbtList">
										<input type="hidden" id="${fsbtMap.dm }_je" value="${fsbtMap.btje }"/>
									</logic:iterate>
								</td>
								<th>
									�������
								</th>
								<td>
									<font id="span_btje"></font>
								</td>
							</tr>
							<tr>
								<th>
									������
								</th>
								<td>
									${user.realName }
									<input type="hidden" name="jsr" value="${user.realName }"/>
								</td>
								<th>
									����ʱ��
								</th>
								<td>
									<html:text property="ffsj" styleId="ffsj" onkeydown="onlyBackSpace(this,event);" 
									onblur="dateFormatChg(this);"
									onclick="return showCalendar('ffsj','y-mm-dd');" 
									style="cursor:hand" readonly="true"></html:text>
								</td>
							</tr>
						 </tbody>
						 
					     <thead>
							<tr>
								<th colspan="4"><span>����ѧ��</span></th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th>
									<span>*</span>ѧ��
								</th>
								<td>
									<html:text property="xh" styleId="xh" readonly="true"
										onkeypress="autoFillStuInfo(event.keyCode,this);" value="${rs.xh}"/>
									<button type="button" onclick="showTopWin('/xgxt/stu_info.do',800,600);" 
										class="btn_01" id="buttonFindStu" >ѡ��
									</button>
								</td>
								<th>
									����
								</th>
								<td>
									${rs.xm }
								</td>
							</tr>
							<tr>
								<th>
									Ժϵ
								</th>
								<td>
									${rs.xymc }
								</td>
								<th>
									רҵ
								</th>
								<td>
									${rs.zymc }
								</td>
							</tr>
							<tr>
								<th>
									�༶
								</th>
								<td>
									${rs.bjmc }
								</td>
								<th></th>
								<td></td>
							</tr>
						</tbody>

						<tfoot>
					      <tr>
					        <td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
					          <div class="btn">
								  <button type="button" id="buttonSave"
									onclick="dataSave('/xgxt/rcsw_gzdx_fsbtgl.do?method=fsbtglUpdate&doType=save','xh-nd-yf-btdm')">
									����
								</button>
								 <button type="button" id="buttonClose"
									onclick="window.close();return false;">
									�ر�
								</button>
					          </div></td>
					      </tr>
					   </tfoot>
				</table> 
			</div>
		</html:form>
		
		<logic:present name="message">
			<input type="hidden" id="message" value="${message }"/>
			<script type="text/javascript">
				alertInfo($('message').value, function(){
					Close();
					if(window.dialogArguments && window.dialogArguments.document.all("search_go")){
				   	window.dialogArguments.document.getElementById('search_go').click(); 
					}
				});
				
			</script>
		</logic:present>
	</body>
</html>
