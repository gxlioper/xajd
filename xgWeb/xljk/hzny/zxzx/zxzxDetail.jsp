<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type='text/javascript' src="js/check.js"></script>
		<script type="text/javascript" src="js/xszz/whtl/ybbx/ybbxDetail.js"></script>
		<script language="javascript">	     
     function saveZxzx(){

     	
     	confirmInfo("�Ƿ�Ҫ�������޸ĵ����ݣ�",saveZxzxInfo);
     
     }
     
      function modiZxzx(){
     	
     	
     	confirmInfo("�Ƿ�Ҫ�������޸ĵ����ݣ�",modiZxzxInfo);
     
     }
 
 
     
     function saveZxzxInfo(tag){
     		
			if(tag=="ok"){
				
				//����
				var pkValue=new Array();
	
				jQuery.ajaxSetup({async:false});
				
				// �õ�JSON����
		        var parameter ={};
				
				parameter["xh"]=jQuery("#xh").val();
				
				parameter["zxwt"]=escape(jQuery("#zxwt").val());
				
				var url = "xljk_hzny_ajax.do?method=saveZxzx";
	          	
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
		
		function modiZxzxInfo(tag){
     		
			if(tag=="ok"){
				
				//����
				var pkValue=new Array();
	
				jQuery.ajaxSetup({async:false});
				
				// �õ�JSON����
		        var parameter ={};
		        
		        parameter["guid"]=jQuery("#pkValue").val();
				
				parameter["xh"]=jQuery("#xh").val();
				
				parameter["hfnr"]=escape(jQuery("#hfnr").val());
				
				parameter["zxwt"]=escape(jQuery("#zxwt").val());
				
				var url = "xljk_hzny_ajax.do?method=modiZxzx";
	          	
	          	
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
			
			var userType='${userType}';
			
			if("stu"==userType){
				jQuery("#div_zxzx").attr("style","width:98%;height:320px;overflow-y:auto;overflow-x:hidden");
			}else {
				jQuery("#zxwt").attr("readOnly","true");
			}
			
			if($("doType").value=="view"){
				jQuery("input,textarea").each(function(){
					jQuery(this).attr("readOnly","true");
				})
				
				jQuery("input[type=radio],select").each(function(){
					jQuery(this).attr("disabled","true");
				})
				jQuery("#div_zxzx").attr("style","width:98%;height:480px;overflow-y:auto;overflow-x:hidden");
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
		
			<div id="div_zxzx"  style='width:98%;height:470px;overflow-y:auto;overflow-x:hidden'>
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
							<td align="left">
								${rs.xh}
								<html:hidden property="xh" styleId="xh" value="${rs.xh}" />
							</td>
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
								
							</th>
							<td>
								
							</td>
						</tr>
						
						<tr>
							<th align="right">
								ѧ����ѯ<br/>
								<font color="blue"><B>(��500��)</B></font>
							</th>
							<td colspan="3">
								<html:textarea name='rs' property='zxwt' styleId="zxwt" style="word-break:break-all;width:90%" onblur="chLeng(this,500);"
										rows='6' />
							</td>
						</tr>
				
						
						<logic:equal name="userType" value="stu">
							<logic:equal name="doType" value="view">
							<tr style="height:30px">
								<th align="right" rowspan="2">
									��ѯʦ�ظ�
								</th>
								<td style="word-break:break-all;">
									�ظ��ˣ�${rs.zxsxm}
									
								</td>
								<td style="word-break:break-all;" colspan="2">
									�ظ�ʱ�䣺${rs.hfsj}
									
								</td>
								
							</tr>
							<tr style="height:100px">
							<td colspan="3"  style="word-break:break-all;">
									${rs.hfnr}
									
							</td>
							</tr>
							</logic:equal>
						</logic:equal>
						
						<logic:equal name="userType" value="xx">
							<logic:equal name="doType" value="view">
							<tr style="height:30px">
								<th align="right" rowspan="2">
									��ѯʦ�ظ�
								</th>
								<td style="word-break:break-all;">
									�ظ��ˣ�${rs.zxsxm}
									
								</td>
								<td style="word-break:break-all;" colspan="2">
									�ظ�ʱ�䣺${rs.hfsj}
									
								</td>
								
							</tr>
							<tr style="height:100px">
							<td colspan="3"  style="word-break:break-all;">
									${rs.hfnr}
									
							</td>
							</tr>
							</logic:equal>
						</logic:equal>
						
						<logic:equal name="userType" value="admin">
							<logic:equal name="doType" value="view">
							<tr style="height:30px">
								<th align="right" rowspan="2">
									��ѯʦ�ظ�
								</th>
								<td style="word-break:break-all;">
									�ظ��ˣ�${rs.zxsxm}
									
								</td>
								<td style="word-break:break-all;" colspan="2">
									�ظ�ʱ�䣺${rs.hfsj}
									
								</td>
								
							</tr>
							<tr style="height:100px">
							<td colspan="3"  style="word-break:break-all;">
									${rs.hfnr}
									
							</td>
							</tr>
							</logic:equal>
						</logic:equal>
						
						<logic:notEqual name="userType" value="stu">
							<logic:notEqual name="userType" value="xx">
							<logic:notEqual name="userType" value="admin">
							<tr >
								<th align="right">
									��ѯʦ�ظ�<br/>
								<font color="blue"><B>(��500��)</B></font>
								</th>
								<td colspan="3">
									<html:textarea name='rs' property='hfnr' styleId="hfnr" style="word-break:break-all;width:90%" onblur="chLeng(this,500);"
											rows='6' />
								</td>
							</tr>
							</logic:notEqual>
							</logic:notEqual>
						</logic:notEqual>
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
										<button id="buttonSave" onclick="saveZxzx()">
											�� ��
										</button>
									</logic:equal>
									<logic:equal name="doType" value="modi">
										<button id="buttonSave" onclick="modiZxzx()">
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

