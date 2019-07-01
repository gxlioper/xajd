<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type='text/javascript' src="js/check.js"></script>
		<script type="text/javascript" src="js/xszz/whtl/ybbx/ybbxDetail.js"></script>
		<script language="javascript">	     
     function saveZxs(){
     	
     	if($("zgh") && $("zgh").value==""){
     		
     		alertInfo("����ѡ��һ������Ա!",function(tag){
     		
     			if(tag=="ok"){
     			
     				return false;
     			}
     		});
     		return false;
     	}
     	
     	
     	confirmInfo("�Ƿ�Ҫ�������޸ĵ����ݣ�",saveZxsInfo);
     
     }
     
      function modiZxs(){
     	
     	
     	confirmInfo("�Ƿ�Ҫ�������޸ĵ����ݣ�",modiZxsInfo);
     
     }
 
 
     
     function saveZxsInfo(tag){
     		
			if(tag=="ok"){
				
				//����
				var pkValue=new Array();
	
				jQuery.ajaxSetup({async:false});
				
				// �õ�JSON����
		        var parameter ={};
				
				parameter["zgh"]=jQuery("#zgh").val();
				
				parameter["jj"]=escape(jQuery("#jj").val());
				
				parameter["zxszg"]=escape(jQuery("#zxszg").val());
				
				parameter["bz"]=escape(jQuery("#bz").val());
				
				var url = "xljk_hzny_ajax.do?method=saveZxs";
	          	
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
		
		function modiZxsInfo(tag){
     		
			if(tag=="ok"){
				
				//����
				var pkValue=new Array();
	
				jQuery.ajaxSetup({async:false});
				
				// �õ�JSON����
		        var parameter ={};
				
				parameter["zgh"]=jQuery("#zgh").val();
				
				parameter["jj"]=escape(jQuery("#jj").val());
				
				parameter["zxszg"]=escape(jQuery("#zxszg").val());
				
				parameter["bz"]=escape(jQuery("#bz").val());
				
				var url = "xljk_hzny_ajax.do?method=modiZxs";
	          	
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
			}
		}
</script>
	</head>
	<body onload="disabledView();">
	
		<html:form action="/xljk_hzny" method="post">
			<input type="hidden" id="url" name="url"
				value="xljk_hzny.do?method=zxsglDetail" />
			<input type="hidden" id="isFdy" name="isFdy" value="${fdyQx }" />
			<input type="hidden" id="isBzr" name="isBzr" value="${bzrQx }" />
			<input type="hidden" name="message" id="message" value="${message }" />
			<input type="hidden" name="doType" id="doType" value="${doType}" />
			<input type="hidden" name="pkValue" id="pkValue" value="${pkValue }" />
			<input type="hidden" name="userType" id="userType"
				value="${userType }" />
			<button id="disbutton" onclick="autoFillTeaInfo(13);" style="display: none"></button>
			
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${title }</a>
				</p>
			</div>

			<div style='width:98%;height:510px;overflow:hidden;overflow-x:hidden'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr onclick="deploy('tbody_jbxx')">
							<th colspan="5">
								<span>������Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_jbxx">
						<tr style="height:22px">
							<th width="16%">
								<font color="red">*</font>ְ����
							</th>
							
							<td width="34%">

								<logic:equal name="doType" value="add">
									<html:text property="zgh" styleId="zgh" style="width:100px" value="${rs.zgh}" readonly="true"/>
									<html:hidden property="zgh" value="${rs.zgh}" />
								</logic:equal>
								
								<logic:notEqual name="doType" value="add">
									${rs.zgh}
									<html:hidden property="zgh" styleId="zgh" value="${rs.zgh}" />
								
								</logic:notEqual>

								<logic:equal name="doType" value="add">
									<button onclick="showTopWin('xysf_dyjsgl.do?method=getTeaInfo',750,550);" class="btn_01" id="buttonFindStu">
										ѡ��
									</button>
								</logic:equal>
								
							</td>
							
							<th  width="16%">
								����
							</th>
							<td  width="34%">
								${rs.xm}
							</td>
							<td rowspan="4">
								<div id="teaImg">
									<img style="width:120px;height:160px"
										src="<%=request.getContextPath()%>/teaPic.jsp?zgh=${rs.zgh }"
										border="0">
								</div>
							</td>
						</tr>
						<tr style="height:22px">
							<th align="right">
								�Ա�
							</th>
							<td align="left">
								${rs.xb }
							</td>
							<th align="right">
								������ò
							</th>
							<td align="left">
								${rs.zzmm}
							</td>
						</tr>
						<tr style="height:22px">
							<th align="right">
								ѧλ
							</th>
							<td align="left">
								${rs.xw }
							</td>
							<th>
								ѧ��
							</th>
							<td >
								${rs.xl}
							</td>
						</tr>
						<tr style="height:22px">
							<th align="right">
								����
							</th>
							<td align="left">
								${rs.bmmc }
							</td>
							<th>

							</th >
							<td>

							</td>
						</tr>
						<tr style="height:22px">
							<th>
								�ʸ�<br/>
								<font color="blue"><B>(��500��)</B></font>
							</th>
							<td colspan="4">
								<html:textarea name='rs' property='zxszg' styleId="zxszg" style="word-break:break-all;width:99%" onblur="chLeng(this,500);"
										rows='4' />
							</td>
						</tr>
						<tr style="height:22px">
							<th>
								���<br/>
								<font color="blue"><B>(��500��)</B></font>
							</th>
							<td colspan="4">
								<html:textarea name='rs' property='jj' styleId="jj" style="word-break:break-all;width:99%" onblur="chLeng(this,500);"
										rows='4' />
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="5">
								<div class="bz">
									"
									<span class="red">*</span>"Ϊ������
								</div>

								<div class="btn">
									
									<logic:equal name="doType" value="add">
											<button id="buttonSave" onclick="saveZxs();">
												�� ��
											</button>
									</logic:equal>
									<logic:equal name="doType" value="modi">
											<button id="buttonSave" onclick="modiZxs();">
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
			</div>

			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>

