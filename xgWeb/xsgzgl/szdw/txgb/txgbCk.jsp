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
	     		
	     		alertInfo("请选择一名学生!",function(tag){
	     		
	     			if(tag=="ok"){
	     			
	     				return false;
	     			}
	     		});
	     		return false;
	     	}
	     	
	     	if($("zzmc") && $("zzmc").value==""){
	     		
	     		alertInfo("组织名称不能为空!",function(tag){
	     		
	     			if(tag=="ok"){
	     			
	     				return false;
	     			}
	     		});
	     		return false;
	     	}
	     	
	     	if($("zzjb") && $("zzjb").value==""){
	     		
	     		alertInfo("组织级别不能为空!",function(tag){
	     		
	     			if(tag=="ok"){
	     			
	     				return false;
	     			}
	     		});
	     		return false;
	     	}
	     	
	     	if($("gbmc") && $("gbmc").value==""){
	     		
	     		alertInfo("干部名称不能为空!",function(tag){
	     		
	     			if(tag=="ok"){
	     			
	     				return false;
	     			}
	     		});
	     		return false;
	     	}
	     	
	     	confirmInfo("是否要保存已修改的数据？",saveTxgbInfo);
	     
	     }
	     
	      function modi(){
	     	
	     	
	     	confirmInfo("是否要保存已修改的数据？",modiTxgbInfo);
	     
	     }
	 
	 
	     
	     function saveTxgbInfo(tag){
	     		
				if(tag=="ok"){
					
					//主键
					var pkValue=new Array();
		
					jQuery.ajaxSetup({async:false});
					
					// 得到JSON对象
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
					
					//主键
					var pkValue=new Array();
		
					jQuery.ajaxSetup({async:false});
					
					// 得到JSON对象
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
					<em>您的当前位置:</em><a>${title }</a>
				</p>
			</div>
		
			<div style='width:98%;height:365px;overflow-y:auto;overflow-x:hidden'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr onclick="deploy('tbody_jbxx')">
							<th colspan="4">
								<span>基本信息</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_jbxx">
						<tr style="height:22px">
							<th align="right" style="width: 10%">
								<font color="red">*</font>学号
							</th>
							<td align="left">
								${rs.xh}
							</td>
							<th align="right" style="width: 10%">
								年级
							</th>
							<td align="left" style="width: 40%">
								${rs.nj}
							</td>
						</tr>
						<tr style="height:22px">
							<th align="right">
								姓名
							</th>
							<td align="left">
								${rs.xm}
							</td>
							<th align="right">
								性别
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
								专业
							</th>
							<td align="left">
								${rs.zymc }
							</td>
						</tr>
						<tr style="height:22px">
							<th align="right">
								班级
							</th>
							<td align="left">
								${rs.bjmc }
							</td>
							<th>
								组织名称
							</th>
							<td>
								${rs.zzmc}
							</td>
						</tr>
						
						<tr style="height:22px">
							<th align="right">
								组织级别
							</th>
							<td align="left">
								${rs.zzjb}
							</td>
							<th>
								组织级别
							</th>
							<td>
								${rs.gbmc}
							</td>
						</tr>
						
						<tr style="height:100px">
							<th align="right">
								备注<br/>
								<font color="blue"><B>(限500字)</B></font>
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
									<span class="red">*</span>"为必填项
								</div>

								<div class="btn">
									<%--						            <logic:equal name="writeAble" value="yes">--%>
									<button type="button" onclick="Close();return false;">
										关 闭
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

