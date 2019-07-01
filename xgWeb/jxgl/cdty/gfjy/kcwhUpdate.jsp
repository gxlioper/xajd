<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
		function saveKcxx(){
			var url="cdtyGfjy.do?method=kcwhAdd&doType=save";
			var flag=true;
			if($("kcmc") && $("kcmc").value==""){
				alertError("�γ�������Ϊ��!");	
				flag=false;
				return flag;
			}
			if($("bl") && $("bl").value==""){
				alertError("��������Ϊ��!");
				flag=false;
				return flag;
			}
			
			if(flag){
				showTips("������,���Ժ�...");
				refreshForm(url);
			}
		}
		
		function updateKcxx(){
			var url="cdtyGfjy.do?method=kcwhUpdate&doType=save";
			showTips("������,���Ժ�...");
			refreshForm(url);
		}
		
		function setTextRed(){
			if($("doType") && $("doType").value=="view"){
			 jQuery('input[type=text]').attr('readonly',true);
			}
		}
		</script>
	</head>
	<body onload="setTextRed()">
		<html:form action="/cdtyGfjy.do" method="post">
			<!-- ������ -->
			<input type="hidden" name="url" id="url"
				value="/xgxt/zgmsxy_xszz.do?method=syddkUpdate" />
			<input type="hidden" name="doType" id="doType" value='${doType}' />
			<input type="hidden" name="tableName" id="tableName" value='view_xsjbxx' />
			<input type="hidden" name="pkValue" id="pkValue" value='${rs.guid}' />
			<input type="hidden" name="message" id="message" value='${message}' />
			<!-- ������ -->
			
			<div class="tab_cur" id="jd">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${title }</a>
				</p>
			</div>
			
			<div class="tab">
				<table width="100%" border="0" class="formlist">

					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<logic:equal name="doType" value="add">
										<button type="button" class="button2" id="btn_bc" onclick="saveKcxx()">
											�� ��
										</button>
									</logic:equal>
									<logic:equal name="doType" value="update">
										<logic:notEqual name='rs' property="sfsh" value="ysh">
										<button type="button" class="button2" id="btn_bc" onclick="updateKcxx()">
											�� ��
										</button>
										</logic:notEqual>
									</logic:equal>
								</div>
							</td>
						</tr>
					</tfoot>
					<thead >
						<tr>
							<th colspan="2">
								<span>���������γ�ά��</span>
							</th>
						</tr>
					</thead>

					<tbody>
						<tr>
							<th style="width:16%">
								<font color="red">*</font>�γ�����
							</th>
							<td style="width:84%">
								<html:text name="rs" property="kcmc"  maxlength="15"/>
							</td>
						</tr>
						<tr>
							<th style="width:16%">
								���
							</th>
							</th>
							<td style="width:84%">
								<logic:equal name="doType" value="add">
									${nd}
									<html:hidden property="nd" value="${nd}"/>
								</logic:equal>
								
								<logic:equal name="doType" value="update">
									${rs.nd}
									<html:hidden name="rs" property="nd"/>
								</logic:equal>
								<logic:equal name="doType" value="view">
									${rs.nd}
									<html:hidden name="rs"  property="nd" />
								</logic:equal>
							</td>
						</tr>
						
						<tr>
							<th style="width:16%">
								<font color="red">*</font>����
							</th>
							<td style="width:84%">
								<html:text name="rs" property="bl" onkeydown="return onlyNum(this,3)"
								onmousedown="return onlyNum(this,3)"
								maxlength="3" style="ime-mode:disabled"/>%
							</td>
						</tr>	
					</tbody>
				</table>
			</div>


			<%@ include file="/comm/other/tsxx.jsp"%>
			
		</html:form>
	</body>
</html>
