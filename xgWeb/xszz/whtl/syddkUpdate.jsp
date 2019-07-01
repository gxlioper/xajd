<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@page import="java.util.HashMap"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
		function saveSyddk(){
			var url="zgdzdx_xszz.do?method=syddkUpdate_whtl&doType=save";

			if($("xh") && $("xh").value==""){
				alertInfo("ѧ�Ų���Ϊ��!");
				return false;
			}
			if($("ffrq").value!=""&&$("dqrq").value!=""){
				if($("ffrq").value>=$("dqrq").value){
					alertInfo("�������ڱ���С�ڵ������ڣ�");
					return false;
				}
			}
			jQuery.post('zgdzdx_xszz.do?method=checkXszz',{xh:jQuery('#xh').val()},function(data){
				if(data&&data!="0"){
					alertInfo("����ѧ��¼�Ѵ��ڣ�");
					return false;
				}else{
					showTips("������,���Ժ�...");
					refreshForm(url);
				}
			});
		}
		
		function updateSyddk(){
			var url="zgdzdx_xszz.do?method=syddkOne_whtl&doType=save";
			if($("ffrq").value!=""&&$("dqrq").value!=""){
				if($("ffrq").value>=$("dqrq").value){
					alertInfo("�������ڱ���С�ڵ������ڣ�");
					return false;
				}
			}
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
				value="/xgxt/zgdzdx_xszz.do?method=syddkUpdate_whtl" />
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
										<button type="button" class="button2"  id="btn_bc" onclick="saveSyddk()">
											�� ��
										</button>
									</logic:equal>
									<logic:equal name="doType" value="update">
										<logic:notEqual name='rs' property="sfsh" value="ysh">
										<button type="button" class="button2"   id="btn_bc" onclick="updateSyddk()">
											�� ��
										</button>
										</logic:notEqual>
									</logic:equal>
									<button type="button" class="button2"  onclick="Close();return false;">
										�� ��
									</button>
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
								���֤��
							</th>
							<td style="width:34%">
								${rs.sfzh}
							</td>
							<th style="width:16%">
								ѧ��
							</th>
							<td style="width:34%">
								${rs.xz}
							</td>
						</tr>	
						<tr>
							<th style="width:16%">
								��ѧ���
							</th>
							<td style="width:34%">
								<%=(request.getAttribute("rs")==null||((HashMap<String,String>)request.getAttribute("rs")).get("rxrq")==null
									||((HashMap<String,String>)request.getAttribute("rs")).get("rxrq").length()<5)
								?"":((HashMap<String,String>)request.getAttribute("rs")).get("rxrq").substring(0,4)%>
							</td>
							<th style="width:16%">
								��ҵ����
							</th>
							<td style="width:34%">
								${rs.bysj}
							</td>
						</tr>	
						<tr>
							<th style="width:16%">
								��ͬ���
							</th>
							<td style="width:34%">
								<html:text name="rs" property="htje" styleId="htje" onkeyup="this.value=this.value.replace(/[^\d]/g,'')" maxlength="10"></html:text>
							</td>
							<th style="width:16%">
								�������
							</th>
							<td style="width:34%">
								<html:text name="rs" property="dkcs" styleId="dkcs"  onkeyup="this.value=this.value.replace(/[^\d]/g,'')" maxlength="2"></html:text>
							</td>
						</tr>
						<tr>
							<th style="width:16%">
								ʡ��������
							</th>
							<td style="width:34%">
								
								<html:text name="rs" property="szzzx" maxlength="75"/>
							</td>
							<th style="width:16%">
								����������
							</th>
							<td style="width:34%">
								<html:text name="rs" property="xzzzx" maxlength="75"/>
							</td>
						</tr>
						<tr>
							<th style="width:16%">
								��ͬ���
							</th>
							<td style="width:34%">
								<html:text name="rs" property="htbh" maxlength="20"/>
							</td>
							<th style="width:16%">
								��ͬ״̬
							</th>
							<td style="width:34%">
								<html:text name="rs" property="htzt" maxlength="10"></html:text>
							</td>
						</tr>		
						<tr>
							<th style="width:16%">
								�������
							</th>
							<td style="width:34%">
								<html:text name="rs" property="dljg" maxlength="75"/>
							</td>
							<th style="width:16%">
								�����������
							</th>
							<td style="width:34%">
								<html:text name="rs" property="dljswd" maxlength="75"></html:text>
							</td>
						</tr>	
						<tr>
							<th style="width:16%">
								�����˺�
							</th>
							<td style="width:34%">
								<html:text name="rs" property="grzh" maxlength="20"/>
							</td>
							<th style="width:16%">
								��������
							</th>
							<td style="width:34%">
								<html:text name="rs" property="dkqx" maxlength="10"></html:text>
							</td>
						</tr>
						<tr>
							<th style="width:16%">
								��������
							</th>
							<td style="width:34%">
								<html:text name="rs" property="ffrq" styleId="ffrq" onclick="return showCalendar(this.id,'y-mm-dd');"
								 maxlength="10"/>
							</td>
							<th style="width:16%">
								��������
							</th>
							<td style="width:34%">
								<html:text name="rs" property="dqrq" styleId="dqrq"  onclick="return showCalendar(this.id,'y-mm-dd');"
								 maxlength="10"></html:text>
							</td>
						</tr>	
						<tr>
							<th style="width:16%">
								��ϵ�绰
							</th>
							<td style="width:34%">
								<html:text name="rs" property="lxdh" styleId="lxdh"  onkeyup="this.value=this.value.replace(/[^\d|-]/g,'')" maxlength="12"></html:text>
							</td>
							<th style="width:16%">
								��ע
							</th>
							<td style="width:34%">
								<html:text name="rs" property="bz" maxlength="100"/>
							</td>
						</tr>		
					</tbody>
				</table>
			</div>
			<logic:present name="message">
			<script language="javascript">
			alertInfo('${message}',function(){
				if(window.dialogArguments){
					if(window.dialogArguments.document.getElementById("search_go")){
						dialogArgumentsQueryChick();
					}
					window.close();
				}
				});
			</script>
		</logic:present>
		</html:form>
	</body>
</html>
