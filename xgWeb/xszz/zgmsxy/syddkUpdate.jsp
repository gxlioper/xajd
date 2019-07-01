<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
		function saveSyddk(){
			var url="zgmsxy_xszz.do?method=syddkUpdate&doType=save";
			
			if($("xh") && $("xh").value==""){
				alertInfo("ѧ�Ų���Ϊ��!");
				return false;
			}
			showTips("������,���Ժ�...");
			refreshForm(url);
		}
		
		function updateSyddk(){
			var url="zgmsxy_xszz.do?method=syddkOne&doType=save";
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
		<html:form action="/zgmsxy_xszz" method="post">
			<!-- ������ -->
			<input type="hidden" name="url" id="url"
				value="/xgxt/zgmsxy_xszz.do?method=syddkUpdate" />
			<input type="hidden" name="doType" id="doType" value='${doType}' />
			<input type="hidden" name="tableName" id="tableName" value='view_xsjbxx' />
			<input type="hidden" name="pkValue" id="pkValue" value='${rs.pkValue}' />
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
										<button type="button" class="button2" id="btn_bc" onclick="saveSyddk()">
											�� ��
										</button>
									</logic:equal>
									<logic:equal name="doType" value="update">
										<logic:notEqual name='rs' property="sfsh" value="ysh">
										<button type="button" class="button2" id="btn_bc" onclick="updateSyddk()">
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
							<th colspan="4">
								<span>������Ϣ</span>
							</th>
						</tr>
					</thead>

					<tbody>
						<tr>
							<th style="width:16%">
								<font color="red">*</font>ѧ��
							</th>
							<td style="width:34%">
								<html:text property="xh" styleId="xh" readonly="true"
									value="${rs.xh }" />
								<logic:equal name="doType" value="add">
									<button type="button" class="btn_01" id="" onclick="sendXx();return false;">
										ѡ ��
									</button>
								</logic:equal>
							</td>
							<th style="width:16%">
								ѧ��
							</th>
							<td style="width:34%">
								<logic:equal name="doType" value="add">
									${xn}
									<html:hidden property="xn" value="${xn}"/>
								</logic:equal>
								
								<logic:equal name="doType" value="update">
									${xn}
									<html:hidden name="rs" property="xn"/>
								</logic:equal>
								<logic:equal name="doType" value="view">
									${xn}
									<html:hidden name="rs"  property="xn" />
								</logic:equal>
							</td>
						</tr>
						<tr>
							<th style="width:16%">
								����
							</th>
							<td style="width:34%">
								${rs.xm}
							</td>
							<th style="width:16%">
								�Ա�
							</th>
							<td style="width:34%">
								${rs.xb}
							</td>
							
						</tr>
						
						<tr>
							<th style="width:16%">
								�꼶
							</th>
							<td style="width:34%">
								${rs.nj}
							</td>
							<th style="width:16%">
								<bean:message key="lable.xb" />
							</th>
							<td style="width:34%">
								${rs.xymc}
							</td>
						</tr>	
						<tr>
							<th style="width:16%">
								רҵ
							</th>
							<td style="width:34%">
								${rs.zymc}
							</td>
							<th style="width:16%">
								�༶
							</th>
							<td style="width:34%">
								${rs.bjmc}
							</td>
						</tr>	
						<tr>
							<th style="width:16%">
								��ϵ�绰
							</th>
							<td style="width:34%">
								<html:text name="rs" property="lxdh"  onkeyup="value=value.replace(/[^\d]/g,'')"
								maxlength="13"/>
							</td>
							<th style="width:16%">
								�ֻ�����
							</th>
							<td style="width:34%">
								<html:text name="rs" property="sjhm"  onkeyup="value=value.replace(/[^\d]/g,'')"
								maxlength="11"/>
							</td>
						</tr>
						<tr>
							<th style="width:16%">
								ѧ�ѱ�׼
							</th>
							<td style="width:34%">
								
								<html:text name="rs" property="xfbz" onblur="checkInputNum(this);" maxlength="10"/>
							</td>
							<th style="width:16%">
								������
							</th>
							<td style="width:34%">
								<html:text name="rs" property="sqje"  onblur="checkInputNum(this);" maxlength="10"/>
							</td>
						</tr>
						<logic:equal name="userOnLine" value="teacher"	>
						<tr>
							<th style="width:16%">
								���Ž��
							</th>
							<td style="width:34%">
								<html:text name="rs" property="ffje" onblur="checkInputNum(this);" maxlength="10"/>
							</td>
							<th style="width:16%">
								
							</th>
							<td style="width:34%">
								
							</td>
						</tr>		
						</logic:equal>
						<tr>
							<th style="width:16%">
								��ͥ��ַ
							</th>
							<td  colspan="3">
								<html:text name="rs" property="jtdz" maxlength="50" size="70"/>
							</td>
							
						</tr>	
					</tbody>
				</table>
			</div>


			<%@ include file="/comm/other/tsxx.jsp"%>
			
		</html:form>
	</body>
</html>
