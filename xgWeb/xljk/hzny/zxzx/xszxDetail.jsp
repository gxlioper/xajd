<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type='text/javascript' src="js/check.js"></script>
		<script type="text/javascript" src="js/xszz/whtl/ybbx/ybbxDetail.js"></script>
		<script language="javascript">	     
     function saveXszx(){

     	if($("zgh") && $("zgh").value==""){
     		
     		alertInfo("请先选择一名心理咨询师!",function(tag){
     		
     			if(tag=="ok"){
     			
     				return false;
     			}
     		});
     		return false;
     	}
     	
     	confirmInfo("是否要保存已修改的数据？",saveXszxInfo);
     
     }
     
      function modiXszx(){
     	
     	
     	confirmInfo("是否要保存已修改的数据？",modiXszxInfo);
     
     }
 
 
     
     function saveXszxInfo(tag){
     		
			if(tag=="ok"){
				
				//主键
				var pkValue=new Array();
	
				jQuery.ajaxSetup({async:false});
				
				// 得到JSON对象
		        var parameter ={};
				
				parameter["zgh"]=jQuery("#zgh").val();
				
				parameter["zxsj"]=escape(jQuery("#zxsj").val());
				
				parameter["zxnrjyj"]=escape(jQuery("#zxnrjyj").val());
				
				var url = "xljk_hzny_ajax.do?method=saveXszx";
	          	
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
		
		function modiXszxInfo(tag){
     		
			if(tag=="ok"){
				
				//主键
				var pkValue=new Array();
	
				jQuery.ajaxSetup({async:false});
				
				// 得到JSON对象
		        var parameter ={};
		        
		        parameter["guid"]=jQuery("#pkValue").val();
				
				parameter["zxsj"]=escape(jQuery("#zxsj").val());
				
				parameter["zxnrjyj"]=escape(jQuery("#zxnrjyj").val());
				
				parameter["zxsfk"]=escape(jQuery("#zxsfk").val());
				
				var url = "xljk_hzny_ajax.do?method=modiXszx";
	          	
	          	
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
				jQuery("#div_xszx").attr("style","width:98%;height:340px;overflow-y:auto;overflow-x:hidden");
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
				jQuery("#div_xszx").attr("style","width:98%;height:480px;overflow-y:auto;overflow-x:hidden");
			}
		}
</script>
	</head>
	<body onload="disabledView();">
		<script type="text/javascript" src="js/xszz/whtl/ybbxDetail.js"></script>
		<html:form action="/xszzYbbx" method="post">
			<input type="hidden" id="url" name="url"
				value="xljk_hzny.do?method=xszxDetail" />
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
			<button id="disbutton" onclick="autoFillTeaInfo(13);" style="display: none"></button>
			<input type="hidden" name="shzt" id="shzt" />
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>${title }</a>
				</p>
			</div>
		
			<div id="div_xszx" style='width:98%;height:480px;overflow-y:auto;overflow-x:hidden'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr onclick="deploy('tbody_jbxx')">
							<th colspan="5">
								<span>基本信息</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_jbxx">
						<tr style="height:22px">
							<th width="16%">
								<font color="red">*</font>职工号
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
										<button onclick="showTopWin('xysf_dyjsgl.do?method=getTeaInfo&isZxs=yes',750,550);" class="btn_01" id="buttonFindStu">
											选择
										</button>
									</logic:equal>
									
								</td>
							

							<th  width="16%">
								姓名
							</th>
							<td  width="34%">
								${rs.xm}
							</td>
							<td rowspan="4">
								<div id="teaImg">
									<img style="width:120px;height:160px"
										src="<%=request.getContextPath()%>/teaPic.jsp?zgh=${rs.zgh }"
										border="0"/>
								</div>
							</td>
						</tr>
						<tr style="height:22px">
							<th align="right">
								性别
							</th>
							<td align="left">
								${rs.xb }
							</td>
							<th align="right">
								政治面貌
							</th>
							<td align="left">
								${rs.zzmm}
							</td>
						</tr>
						<tr style="height:22px">
							<th align="right">
								学位
							</th>
							<td align="left">
								${rs.xw }
							</td>
							<th>
								学历
							</th>
							<td >
								${rs.xl}
							</td>
						</tr>
						<tr style="height:22px">
							<th align="right">
								部门
							</th>
							<td align="left">
								${rs.bmmc }
							</td>
							<th>
								咨询时间
							</th >
							<logic:equal name="userType" value="stu">
								<td>
								<logic:equal name="doType" value="view">
									${rs.zxsj }
								</logic:equal>
								<logic:notEqual name="doType" value="view">
									 <html:text name = "rs" property="zxsj" styleId="zxsj" 
									onclick="return showCalendar('zxsj','y-mm-dd');" 
									onblur="dateFormatChg(this)" readonly="true" />
								</logic:notEqual>
								</td> 
							</logic:equal>
							<logic:notEqual name="userType" value="stu">
							<td>
								 ${rs.zxsj}
							</td> 
							</logic:notEqual>
						</tr>
						
						<logic:equal name="userType" value="stu">
							<logic:notEqual name="doType" value="view">
							<tr style="height:85px">
								<th>
									咨询内容及意见<br/>
									<font color="blue"><B>(限500字)</B></font>
								</th>
								<td colspan="4">
									<html:textarea name='rs' property='zxnrjyj' styleId="zxnrjyj" style="word-break:break-all;width:99%" onblur="chLeng(this,500);"
											rows='6' />
								</td>
							</tr>
							</logic:notEqual>
							<logic:equal name="doType" value="view">
								<tr style="height:100px">
									<th>
										咨询内容及意见
									</th>
									<td colspan="4"  style="word-break:break-all;width:99%">
										${rs.zxnrjyj }
									</td>
								</tr>
							</logic:equal>
						</logic:equal>
						
						<logic:notEqual name="userType" value="stu">
							<logic:equal name="userType" value="xx">
								<tr >
								<th>
									咨询人学号
								</th>
								<td >
									${rs.xh }
								</td>
								<th>
									咨询人姓名
								</th>
								<td colspan="2" >
									${rs.xsxm }
								</td>
								</tr>
							</logic:equal>
							<logic:equal name="userType" value="admin">
								<tr >
								<th>
									咨询人学号
								</th>
								<td >
									${rs.xh }
								</td>
								<th>
									咨询人姓名
								</th>
								<td colspan="2">
									${rs.xsxm }
								</td>
								</tr>
							</logic:equal>
						
							<tr style="height:100px">
								<th>
									咨询内容及意见
								</th>
								<td colspan="4"  style="word-break:break-all;width:99%">
									${rs.zxnrjyj }
								</td>
							</tr>
							<logic:notEqual name="userType" value="xx">
							<logic:notEqual name="userType" value="admin">
							<logic:notEqual name="doType" value="view">
							<tr style="height:100px">
								<th>
									咨询师反馈<br/>
									<font color="blue"><B>(限500字)</B></font>
								</th>
								<td colspan="4">
									<html:textarea name='rs' property='zxsfk' styleId='zxsfk' style="word-break:break-all;width:90%" onblur="chLeng(this,500);"
											rows='6' />
								</td>
							</tr>
							</logic:notEqual>
							</logic:notEqual>
							</logic:notEqual>
						</logic:notEqual>
						
					
						<logic:equal name="doType" value="view">
							<tr style="height:30px">
								<th align="right" rowspan="2">
									咨询师反馈
								</th>
								<td style="word-break:break-all;" colspan="2">
									反馈人：${rs.zxsxm}
									
								</td>
								<td style="word-break:break-all;" colspan="2">
									反馈时间：${rs.fksj}
									
								</td>
								
							</tr>
							<tr style="height:100px">
							<td colspan="4"  style="word-break:break-all;">
									${rs.zxsfk}
									
							</td>
							</tr>
						</logic:equal>
					</tbody>
				</table>
			</div>
			<table width="100%" border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="5">
								<div class="bz">
									"
									<span class="red">*</span>"为必填项
								</div>

								<div class="btn">
									<%--						            <logic:equal name="writeAble" value="yes">--%>
									<logic:equal name="doType" value="add">
											<button id="buttonSave" onclick="saveXszx();">
												保 存
											</button>
									</logic:equal>
									<logic:equal name="doType" value="modi">
											<button id="buttonSave" onclick="modiXszx();">
												保 存
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

