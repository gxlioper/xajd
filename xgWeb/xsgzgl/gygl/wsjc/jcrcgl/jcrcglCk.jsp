<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type='text/javascript' src="js/check.js"></script>
		<script type="text/javascript" src="js/xszz/whtl/ybbx/ybbxDetail.js"></script>
		<script language="javascript">	     
	     function save(){
	     	
	     	if($("xn") && $("xn").value==""){
	     		
	     		alertInfo("ѧ�겻��Ϊ��!",function(tag){
	     		
	     			if(tag=="ok"){
	     			
	     				return false;
	     			}
	     		});
	     		return false;
	     	}
	     	
	     	if($("xq") && $("xq").value==""){
	     		
	     		alertInfo("ѧ�ڲ���Ϊ��!",function(tag){
	     		
	     			if(tag=="ok"){
	     			
	     				return false;
	     			}
	     		});
	     		return false;
	     	}
	     	
	     	confirmInfo("�Ƿ�Ҫ�������޸ĵ����ݣ�",saveJcrcglInfo);
	     
	     }
	     
	      function modi(){
	     	
	     	
	     	confirmInfo("�Ƿ�Ҫ�������޸ĵ����ݣ�",modiJcrcglInfo);
	     
	     }
	 
	 
	     
	     function saveJcrcglInfo(tag){
	     		
				if(tag=="ok"){
					
					//����
					var pkValue=new Array();
		
					jQuery.ajaxSetup({async:false});
					
					// �õ�JSON����
			        var parameter ={};
					
					parameter["xn"]=jQuery("#xn").val();
					
					parameter["xq"]=escape(jQuery("#xq").val());
					
					parameter["kssj"]=escape(jQuery("#kssj").val());
					
					parameter["jssj"]=escape(jQuery("#jssj").val());
					
					parameter["mc"]=escape(jQuery("#mc").val());
					
					parameter["bz"]=escape(jQuery("#bz").val());
					
					var url = "gyglnew_jcrcgl_ajax.do?method=save";
		          	
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
			
			function modiJcrcglInfo(tag){
	     		
				if(tag=="ok"){
					
					//����
					var pkValue=new Array();
		
					jQuery.ajaxSetup({async:false});
					
					// �õ�JSON����
			        var parameter ={};
			        
			        parameter["pkValue"]=jQuery("#pkValue").val();
					
					parameter["xn"]=jQuery("#xn").val();
					
					parameter["xq"]=escape(jQuery("#xq").val());
					
					parameter["kssj"]=escape(jQuery("#kssj").val());
					
					parameter["jssj"]=escape(jQuery("#jssj").val());
					
					parameter["mc"]=escape(jQuery("#mc").val());
					
					parameter["bz"]=escape(jQuery("#bz").val());
					
					var url = "gyglnew_jcrcgl_ajax.do?method=modi";
		          	
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
</script>
	</head>
	<body onload="">
	
		<html:form action="/gyglnew_jcrcgl" method="post">
			<% String xxdm = (String) request.getAttribute("xxdm"); %>
			<input type="hidden" id="url" name="url"
				value="xljk_hzny.do?method=zxsglDetail" />
			<input type="hidden" id="isFdy" name="isFdy" value="${fdyQx }" />
			<input type="hidden" id="isBzr" name="isBzr" value="${bzrQx }" />
			<input type="hidden" name="message" id="message" value="${message }" />
			<input type="hidden" name="doType" id="doType" value="${doType}" />
			<input type="hidden" name="pkValue" id="pkValue" value="${pkValue }" />
			<input type="hidden" name="userType" id="userType"
				value="${userType }" />
			

			<div style='width:98%;overflow:hidden;overflow-x:hidden'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr onclick="deploy('tbody_jbxx')">
							<th colspan="4">
								<span>����ճ���Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_jbxx">
						<tr style="height:22px">
							<th width="16%">
								ѧ��
							</th>
							
							<td width="34%" colspan="3" >
								${rs.xn }
							</td>
						</tr>
						<tr style="height:22px">
							<th width="16%">
								ѧ��
							</th>
							
							<td width="34%" colspan="3">
								${rs.xqmc }
							</td>
						</tr>
						<tr style="height:22px">
							<th align="right">
								����
							</th>
							<td align="left" colspan="3">
								${rs.mc }
							</td>
						</tr>
						<% if("18180".equals(xxdm) || "13696".equals(xxdm)){ %>
						<tr style="height:22px">
							<th align="right">
								�·�
							</th>
							<td align="left" colspan="3">
								${rs.jcyf }
							</td>
						</tr>
						<% } %>
						<logic:equal value="true" name="xjqs">
							<th align="right">
								����
							</th>
							<td align="left" colspan="3">
								<logic:equal value="0" name="rs" property="jclx">
									����
								</logic:equal>
								<logic:equal value="1" name="rs" property="jclx">
									�ȼ�
								</logic:equal>
								<logic:equal value="2" name="rs" property="jclx">
									�Ǽ�
								</logic:equal>
							</td>
						</logic:equal>
						
						<tr style="height:22px">
							<th align="right">
								��ֹʱ��
							</th>
							<td align="left" colspan="3">
								${rs.kssj }��${rs.jssj }
							</td>
						</tr>
						<tr style="height:90px">
							<th align="right">
								��ע
							</th>
							<td colspan="3" style="word-break:break-all;width:99%">
								${rs.bz }
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									
									<button type="button" onclick="Close();return false;">
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

