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
	     	
	     	if($("xh") && $("xh").value==""){
	     		
	     		alertInfo("��ѡ��һ��ѧ��!",function(tag){
	     		
	     			if(tag=="ok"){
	     			
	     				return false;
	     			}
	     		});
	     		return false;
	     	}
	     	
	     	if($("zzmc") && $("zzmc").value==""){
	     		
	     		alertInfo("��֯���Ʋ���Ϊ��!",function(tag){
	     		
	     			if(tag=="ok"){
	     			
	     				return false;
	     			}
	     		});
	     		return false;
	     	}
	     	
	     	if($("zzjb") && $("zzjb").value==""){
	     		
	     		alertInfo("��֯������Ϊ��!",function(tag){
	     		
	     			if(tag=="ok"){
	     			
	     				return false;
	     			}
	     		});
	     		return false;
	     	}
	     	
	     	if($("gbmc") && $("gbmc").value==""){
	     		
	     		alertInfo("�ɲ����Ʋ���Ϊ��!",function(tag){
	     		
	     			if(tag=="ok"){
	     			
	     				return false;
	     			}
	     		});
	     		return false;
	     	}
	     	
	     	confirmInfo("�Ƿ�Ҫ�������޸ĵ����ݣ�",saveTxgbInfo);
	     
	     }
	     
	      function modi(){
	     	
	     	
	     	confirmInfo("�Ƿ�Ҫ�������޸ĵ����ݣ�",modiTxgbInfo);
	     
	     }
	 
	 
	     
	     function saveTxgbInfo(tag){
	     		
				if(tag=="ok"){
					
					//����
					var pkValue=new Array();
		
					jQuery.ajaxSetup({async:false});
					
					// �õ�JSON����
			        var parameter ={};
					
					parameter["xh"]=escape(jQuery("#xh").val());
					
					parameter["zzmc"]=escape(jQuery("#zzmc").val());
					
					parameter["zzjb"]=escape(jQuery("#zzjb").val());
					
					parameter["gbmc"]=escape(jQuery("#gbmc").val());
					
					parameter["bz"]=escape(jQuery("#bz").val());
					
					var url = "szdwTxgb_ajax.do?method=save";
		          	
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
			
			function modiTxgbInfo(tag){
	     		
				if(tag=="ok"){
					
					//����
					var pkValue=new Array();
		
					jQuery.ajaxSetup({async:false});
					
					// �õ�JSON����
			        var parameter ={};
			        
			        parameter["pkValue"]=jQuery("#pkValue").val();
					
					parameter["xn"]=jQuery("#xn").val();
					
					parameter["xh"]=escape(jQuery("#xh").val());
					
					parameter["zzmc"]=escape(jQuery("#zzmc").val());
					
					parameter["zzjb"]=escape(jQuery("#zzjb").val());
					
					parameter["gbmc"]=escape(jQuery("#gbmc").val());
					
					parameter["bz"]=escape(jQuery("#bz").val());
					
					var url = "szdwTxgb_ajax.do?method=modi";
		          	
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
	<body >
		<html:form action="/szdwTxgb" method="post">
			<input type="hidden" id="url" name="url"
				value="szdwTxgb.do?method=txgbZj" />
			<input type="hidden" id="tableName" name="tableName"
				value="view_xsjbxx" />
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
		
			<div style='width:98%;height:365px;overflow-y:auto;overflow-x:hidden'>
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
								��֯����
							</th>
							<td>
								${rs.zzmc}
							</td>
						</tr>
						
						<tr style="height:22px">
							<th align="right">
								��֯����
							</th>
							<td align="left">
								${rs.zzjb}
							</td>
							<th>
								��֯����
							</th>
							<td>
								${rs.gbmc}
							</td>
						</tr>
						
						<tr style="height:100px">
							<th align="right">
								��ע<br/>
								<font color="blue"><B>(��500��)</B></font>
							</th>
							<td colspan="3" style="word-break:break-all;width:99%">
								${rs.bz }
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
									<button type="button" onclick="Close();return false;">
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

