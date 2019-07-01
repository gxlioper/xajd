<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type='text/javascript' src="js/check.js"></script>
		<script type="text/javascript" src="js/xszz/whtl/ybbx/ybbxDetail.js"></script>
		<script language="javascript">	     
     function saveTsxs(){
     	
     	if($("xh") && $("xh").value==""){
     		
     		alertInfo("����ѡ��һ��ѧ��!",function(tag){
     		
     			if(tag=="ok"){
     			
     				return false;
     			}
     		});
     		return false;
     	}
     	
     	if($("tbgxxslb") && $("tbgxxslb").value==""){
     		
     		alertInfo("�������ѧ�����Ϊ�����ֶΣ���ȷ��!",function(tag){
     		
     			if(tag=="ok"){
     			
     				return false;
     			}
     		});
     		return false;
     	}
     	
     	
     	confirmInfo("�Ƿ�Ҫ�������޸ĵ����ݣ�",saveTsxsInfo);
     
     }
     
      function modiTsxs(){
     	
     	
     	confirmInfo("�Ƿ�Ҫ�������޸ĵ����ݣ�",modiTsxsInfo);
     
     }
 
 
     
     function saveTsxsInfo(tag){
     		
			if(tag=="ok"){
				
				//����
				var pkValue=new Array();
	
				jQuery.ajaxSetup({async:false});
				
				// �õ�JSON����
		        var parameter ={};
				
				parameter["xh"]=jQuery("#xh").val();
				
				parameter["tbgxxslb"]=jQuery("#tbgxxslb").val();
				
				parameter["sfzy"]=escape(jQuery("[name=sfzy]:checked").eq(0).val());
				
				parameter["xswjbx"]=escape(jQuery("#xswjbx").val());
				
				parameter["tbgxcs"]=escape(jQuery("#tbgxcs").val());
				
				parameter["xyclgc"]=escape(jQuery("#xyclgc").val());
				
				parameter["xsdqzk"]=escape(jQuery("#xsdqzk").val());
				
				parameter["sbrlxdh"]=escape(jQuery("#sbrlxdh").val());
				
				parameter["bz"]=escape(jQuery("#bz").val());
				
				var url = "xljk_hzny_ajax.do?method=saveTsxs";
	          	
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
		
		function modiTsxsInfo(tag){
     		
			if(tag=="ok"){
				
				//����
				var pkValue=new Array();
	
				jQuery.ajaxSetup({async:false});
				
				// �õ�JSON����
		        var parameter ={};
				
				parameter["xh"]=jQuery("#xh").val();
				
				parameter["tbgxxslb"]=jQuery("#tbgxxslb").val();
				
				parameter["sfzy"]=escape(jQuery("[name=sfzy]:checked").eq(0).val());
				
				parameter["xswjbx"]=escape(jQuery("#xswjbx").val());
				
				parameter["tbgxcs"]=escape(jQuery("#tbgxcs").val());
				
				parameter["xyclgc"]=escape(jQuery("#xyclgc").val());
				
				parameter["xsdqzk"]=escape(jQuery("#xsdqzk").val());
				
				parameter["sbrlxdh"]=escape(jQuery("#sbrlxdh").val());
				
				parameter["bz"]=escape(jQuery("#bz").val());
				
				var url = "xljk_hzny_ajax.do?method=modiTsxs";
	          	
	          	
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
		
		function disabledView(){
			
			if($("doType").value=="view"){
				jQuery("input,textarea").each(function(){
					jQuery(this).attr("readOnly","true");
				})
				
				jQuery("input[type=radio],select").each(function(){
					jQuery(this).attr("disabled","true");
				})
			}
		}
</script>
	</head>
	<body onload="disabledView();">
		<script type="text/javascript" src="js/xszz/whtl/ybbxDetail.js"></script>
		<html:form action="/xszzYbbx" method="post">
			<input type="hidden" id="url" name="url"
				value="xljk_hzny.do?method=tsxsglDetail" />
			<input type="hidden" id="tableName" name="tableName"
				value="view_xsjbxx" />
			<input type="hidden" id="isFdy" name="isFdy" value="${fdyQx }" />
			<input type="hidden" id="isBzr" name="isBzr" value="${bzrQx }" />
			<input type="hidden" name="xn" id="xn" value="${xn}" />
			<input type="hidden" name="message" id="message" value="${message }" />
			<input type="hidden" name="doType" id="doType" value="${doType}" />
			<input type="hidden" name="pkValue" id="pkValue" value="${pkValue }" />
			<input type="hidden" name="have" id="have" value="${have}" />
			<input type="hidden" name="userType" id="userType"
				value="${userType }" />
			
			<input type="hidden" name="shzt" id="shzt" />
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
							<th align="right" style="width: 10%">
								<font color="red">*</font>ѧ��
							</th>
							<logic:equal name="userOnLine" value="teacher" scope="session">
								<td align="left">

									<logic:equal name="doType" value="add">
										<html:text property="xh" styleId="xh" value="${rs.xh}" />
										<html:hidden property="xh" value="${rs.xh}" />
									</logic:equal>
									<logic:equal name="doType" value="save">
										<html:text property="xh" styleId="xh" value="${rs.xh}" />
									<html:hidden property="xh" value="${rs.xh}" />
									</logic:equal>
									<logic:notEqual name="doType" value="add">
										<logic:notEqual name="doType" value="save">
										${rs.xh}
										<html:hidden property="xh" styleId="xh" value="${rs.xh}" />
										</logic:notEqual>
									</logic:notEqual>

									<logic:equal name="doType" value="add">
										<button onclick="sendXx();return false;" class="btn_01" id="buttonFindStu">
											ѡ��
										</button>
									</logic:equal>
									<logic:equal name="doType" value="save">
										<button onclick="sendXx();return false;" class="btn_01" id="buttonFindStu">
											ѡ��
										</button>
									</logic:equal>
								</td>
							</logic:equal>
							<logic:equal name="userOnLine" value="student" scope="session">
								<td align="left">
									${userName }
									<html:hidden name='rs' property="xh" styleId="xh"
										value="${userName}" />

								</td>
							</logic:equal>

							<th align="right" style="width: 10%">
								�꼶
							</th>
							<td align="left" style="width: 40%">
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
								<font color="red">*</font>�ر����ѧ�����
							</th>
							<td>
								<html:select name ="rs" property="tbgxxslb" styleId="tbgxxslb">
									<option value=""></option>
									<html:options collection="gxxslbList" property="dm"
										labelProperty="mc" />
								</html:select>
							</td>
						</tr>
						
						<logic:notEqual name="doType" value="add">
							<tr style="height:22px">
								<th>
									�ϱ���
								</th>
								<td>
									${rs.sbrxm}
								</td>
								<th align="right">
									�ϱ�ʱ��
								</th>
								<td align="left">
									${rs.sbsj}
								</td>
							</tr>
						</logic:notEqual>
						
						<tr style="height:22px">
							<th align="right">
								�Ƿ�סԺ
							</th>
							<td align="left">
								<html:radio name="rs" property="sfzy" styleId="sfzy_0" value="��" />��
								<html:radio name="rs" property="sfzy" styleId="sfzy_1"  value="��" />��
							</td>
							<th>
								�ϱ�����ϵ�绰
							</th>
							<td>
								<html:text name="rs" property="sbrlxdh" styleId="sbrlxdh" onblur="checkPhone(this)" maxlength="20" />
							</td>
						</tr>
						<tr style="height:22px">
							<th align="right">
								ѧ��Σ������<br/>
								<font color="blue"><B>(��500��)</B></font>
							</th>
							<td colspan="3">
								<html:textarea name='rs' property='xswjbx' styleId="xswjbx" style="word-break:break-all;width:99%" onblur="chLeng(this,500);"
										rows='4' />
							</td>
						</tr>
						
						<tr style="height:22px">
							<th align="right">
								�ر���Ĵ�ʩ<br/>
								<font color="blue"><B>(��500��)</B></font>
							</th>
							<td colspan="3">
								<html:textarea name='rs' property='tbgxcs' styleId="tbgxcs" style="word-break:break-all;width:99%" onblur="chLeng(this,500);"
										rows='4' />
							</td>
						</tr>
						
						<tr style="height:22px">
							<th align="right">
								<bean:message key="lable.xb" />�������<br/>
								<font color="blue"><B>(��500��)</B></font>
							</th>
							<td colspan="3">
								<html:textarea name='rs' property='xyclgc' styleId="xyclgc" style="word-break:break-all;width:99%" onblur="chLeng(this,500);"
										rows='4' />
							</td>
						</tr>
						
						<tr style="height:22px">
							<th align="right">
								ѧ����ǰ״��<br/>
								<font color="blue"><B>(��500��)</B></font>
							</th>
							<td colspan="3">
								<html:textarea name='rs' property='xsdqzk' styleId="xsdqzk" style="word-break:break-all;width:99%" onblur="chLeng(this,500);"
										rows='4' />
							</td>
						</tr>
						
						<tr style="height:22px">
							<th align="right">
								��ע<br/>
								<font color="blue"><B>(��500��)</B></font>
							</th>
							<td colspan="3">
								<html:textarea name='rs' property='bz' styleId="bz" style="word-break:break-all;width:99%" onblur="chLeng(this,500);"
										rows='4' />
							</td>
						</tr>
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
										<button id="buttonSave" onclick="saveTsxs()">
											�� ��
										</button>
									</logic:equal>
									<logic:equal name="doType" value="modi">
										<button id="buttonSave" onclick="modiTsxs()">
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

