<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript">	     
     function saveZxjl(){
     	
     	if($("xh") && $("xh").value==""){
     		
     		alertInfo("请先选择一名学生!",function(tag){
     		
     			if(tag=="ok"){
     			
     				return false;
     			}
     		});
     		return false;
     	}
     	
     	
     	confirmInfo("是否要保存已修改的数据？",saveZxjlInfo);
     
     }
     
      function modiZxjl(){
     	
     	
     	confirmInfo("是否要保存已修改的数据？",modiZxjlInfo);
     
     }
 
 
     
     function saveZxjlInfo(tag){
     		
			if(tag=="ok"){
				
				//主键
				var pkValue=new Array();
	
				jQuery.ajaxSetup({async:false});
				
				// 得到JSON对象
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
				
				//主键
				var pkValue=new Array();
	
				jQuery.ajaxSetup({async:false});
				
				// 得到JSON对象
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
					<em>您的当前位置:</em><a>${title }</a>
				</p>
			</div>
		
			<div style='width:98%;height:450px;overflow-y:auto;overflow-x:hidden'>
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
							<th align="right" style="width: 16%">
								<font color="red">*</font>学号
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
										选择
									</button>
								</logic:equal>
							</td>
							
							<th style="width: 16%">
								年级
							</th>
							<td style="width: 34%">
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
								咨询时间
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
								学生咨询问题<br/>
								<font color="blue"><B>(限500字)</B></font>
							</th>
							<td colspan="3">
								<html:textarea name='rs' property='zxnr' styleId='zxnr' style="word-break:break-all;width:99%" onblur="chLeng(this,500);"
										rows='5' />
							</td>
						</tr>
						
						<tr style="height:100px">
							<th align="right">
								咨询师回复<br/>
								<font color="blue"><B>(限500字)</B></font>
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
								学生咨询问题
							</th>
							<td colspan="3" style="word-break:break-all;width:99%">
								${rs.zxnr }
							</td>
						</tr>
						
						<tr style="height:100px">
							<th align="right">
								咨询师回复
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
									<span class="red">*</span>"为必填项
								</div>

								<div class="btn">
									<%--						            <logic:equal name="writeAble" value="yes">--%>
									<logic:equal name="doType" value="add">
										<button id="buttonSave" onclick="saveZxjl()">
											保 存
										</button>
									</logic:equal>
									<logic:equal name="doType" value="modi">
										<button id="buttonSave" onclick="modiZxjl()">
											修 改
										</button>
									</logic:equal>

									<button onclick="Close();return false;">
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

