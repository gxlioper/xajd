<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript">	     
     function saveZxjl(){
     	
     	if($("xh") && $("xh").value==""){
     		
     		alertInfo("����ѡ��һ��ѧ��!",function(tag){
     		
     			if(tag=="ok"){
     			
     				return false;
     			}
     		});
     		return false;
     	}
     	
     	
     	confirmInfo("�Ƿ�Ҫ�������޸ĵ����ݣ�",saveZxjlInfo);
     
     }
     
      function modiZxjl(){
     	
     	
     	confirmInfo("�Ƿ�Ҫ�������޸ĵ����ݣ�",modiZxjlInfo);
     
     }
 
 
     
     function saveZxjlInfo(tag){
     		
			if(tag=="ok"){
				
				//����
				var pkValue=new Array();
	
				jQuery.ajaxSetup({async:false});
				
				// �õ�JSON����
		        var parameter ={};
				
				parameter["xh"]=jQuery("#xh").val();
				
				parameter["zxsj"]=jQuery("#zxsj").val();
				
				parameter["zxnr"]=escape(jQuery("#zxnr").val());
				
				parameter["zxshf"]=escape(jQuery("#zxshf").val());
				
				var url = "xljk_hzny_ajax.do?method=saveZxjl";
	          	
			 	$("divWaiting").style.display="";
				$("divDisable").style.display="";
				
				jQuery.post(url,parameter,
					function(result){
						$("divWaiting").style.display="none";
						$("divDisable").style.display="none";
						alertInfo(result);
						
						
					}
				);
				
				jQuery.ajaxSetup({async:true});
			}
		}
		
		function modiZxjlInfo(tag){
     		
			if(tag=="ok"){
				
				//����
				var pkValue=new Array();
	
				jQuery.ajaxSetup({async:false});
				
				// �õ�JSON����
		        var parameter ={};
		        
		        
				parameter["guid"]=jQuery("#pkValue").val();
				
				parameter["zxsj"]=jQuery("#zxsj").val();
				
				parameter["zxnr"]=escape(jQuery("#zxnr").val());
				
				parameter["zxshf"]=escape(jQuery("#zxshf").val());
				
				var url = "xljk_hzny_ajax.do?method=modiZxjl";
	          	
	          	
			 	$("divWaiting").style.display="";
				$("divDisable").style.display="";
				
				jQuery.post(url,parameter,
					function(result){
						$("divWaiting").style.display="none";
						$("divDisable").style.display="none";
						alertInfo(result);
						
						
					}
				);
				
				jQuery.ajaxSetup({async:true});
			}
		}
	
		
		function deploy(id){
			document.getElementById(id).style.display = (document.getElementById(id).style.display == 'none') ? '' : 'none';  
		}
		
		function autoFillTeaInfo(cod){
			if(cod == 13){
				var url = $('url').value;
				document.forms[0].action = url;
				document.forms[0].submit();
			}
		}
		
</script>
	</head>
	<body onload="">
		<html:form action="/xljk_hzny" method="post">
			<input type="hidden" id="url" name="url" value="xljk_hzny.do?method=zxjlDetail" />
			<input type="hidden" id="tableName" name="tableName" value="view_xsjbxx" />
			<input type="hidden" id="isFdy" name="isFdy" value="${fdyQx }" />
			<input type="hidden" id="isBzr" name="isBzr" value="${bzrQx }" />
			<input type="hidden" name="message" id="message" value="${message }" />
			<input type="hidden" name="doType" id="doType" value="${doType}" />
			<input type="hidden" name="pkValue" id="pkValue" value="${pkValue }" />
			<input type="hidden" name="userType" id="userType"
				value="${userType }" />
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${title }</a>
				</p>
			</div>
		
			<div style='width:98%;height:450px;overflow-y:auto;overflow-x:hidden'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr onclick="deploy('tbody_jbxx')">
							<th colspan="4">
								<span>������Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_jbxx">
						<tr style="height:22px">
							<th align="right" style="width: 16%">
								<font color="red">*</font>ѧ��
							</th>
							
							<td  style="width: 34%">
								<logic:equal name="doType" value="add">
									<html:text property="xh" styleId="xh" value="${rs.xh}" readonly="true"/>
									<html:hidden property="xh" value="${rs.xh}" />
								</logic:equal>
								<logic:notEqual name="doType" value="add">
									${rs.xh}
									<html:hidden property="xh" styleId="xh" value="${rs.xh}" />
								</logic:notEqual>

								<logic:equal name="doType" value="add">
									<button onclick="sendXx();return false;" class="btn_01" id="buttonFindStu">
										ѡ��
									</button>
								</logic:equal>
							</td>
							
							<th style="width: 16%">
								�꼶
							</th>
							<td style="width: 34%">
								${rs.nj}
							</td>
						</tr>
						<tr style="height:22px">
							<th align="right">
								����
							</th>
							<td align="left">
								${rs.xm}
							</td>
							<th align="right">
								�Ա�
							</th>
							<td align="left">
								${rs.xb }
							</td>
						</tr>

						<tr style="height:22px">
							<th align="right">
								<bean:message key="lable.xsgzyxpzxy" />
							</th>
							<td align="left">
								${rs.xymc}
							</td>
							<th align="right">
								רҵ
							</th>
							<td align="left">
								${rs.zymc }
							</td>
						</tr>
						<tr style="height:22px">
							<th align="right">
								�༶
							</th>
							<td align="left">
								${rs.bjmc }
							</td>
							<th>
								��ѯʱ��
							</th>
							<logic:notEqual name="doType" value="view">
							<td>
								<html:text name="rs" property="zxsj" styleId="zxsj" 
									onclick="return showCalendar('zxsj','y-mm-dd');" 
									onblur="dateFormatChg(this)" readonly="true" />
							</td>
							</logic:notEqual>
							<logic:equal name="doType" value="view">
							<td style="word-break:break-all;width:99%">
								${rs.zxsj }
							</td>
							</logic:equal>
						</tr>
						
						<logic:notEqual name="doType" value="view">
						<tr  style="height:100px">
							<th align="right">
								ѧ����ѯ����<br/>
								<font color="blue"><B>(��500��)</B></font>
							</th>
							<td colspan="3">
								<html:textarea name='rs' property='zxnr' styleId='zxnr' style="word-break:break-all;width:99%" onblur="chLeng(this,500);"
										rows='5' />
							</td>
						</tr>
						
						<tr style="height:100px">
							<th align="right">
								��ѯʦ�ظ�<br/>
								<font color="blue"><B>(��500��)</B></font>
							</th>
							<td colspan="3">
								<html:textarea name='rs' property='zxshf' styleId="zxshf" style="word-break:break-all;width:99%" onblur="chLeng(this,500);"
										rows='5' />
							</td>
						</tr>
						</logic:notEqual>
						<logic:equal name="doType" value="view">
						<tr  style="height:100px">
							<th align="right">
								ѧ����ѯ����
							</th>
							<td colspan="3" style="word-break:break-all;width:99%">
								${rs.zxnr }
							</td>
						</tr>
						
						<tr style="height:100px">
							<th align="right">
								��ѯʦ�ظ�
							</th>
							<td colspan="3" style="word-break:break-all;width:99%">
								${rs.zxshf }
							</td>
						</tr>
						</logic:equal>
					</tbody>
				</table>
			</div>
			<table width="100%" border="0" class="formlist">
					<tfoot>
						<tr>
							<td>
								<div class="bz">
									"
									<span class="red">*</span>"Ϊ������
								</div>

								<div class="btn">
									<%--						            <logic:equal name="writeAble" value="yes">--%>
									<logic:equal name="doType" value="add">
										<button id="buttonSave" onclick="saveZxjl()">
											�� ��
										</button>
									</logic:equal>
									<logic:equal name="doType" value="modi">
										<button id="buttonSave" onclick="modiZxjl()">
											�� ��
										</button>
									</logic:equal>

									<button onclick="Close();return false;">
										�� ��
									</button>
									
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>

