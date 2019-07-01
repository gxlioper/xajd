<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type='text/javascript' src="js/check.js"></script>
		<script type="text/javascript" src="js/pjpy/comm/pjpy/pjlc/other/qtxxWh.js"></script>
		<script language="javascript">	     
     function save(){
     	
     	if($("xh") && $("xh").value==""){
     		
     		alertInfo("请先选择一名学生!",function(tag){
     		
     			if(tag=="ok"){
     			
     				return false;
     			}
     		});
     		return false;
     	}
     	
     	if($("xn") && $("xn").value==""){
     		
     		alertInfo("学年不能为空!",function(tag){
     		
     			if(tag=="ok"){
     			
     				return false;
     			}
     		});
     		return false;
     	}
     	
     	if(checkSaveInfo()){
			
     		confirmInfo("是否要保存已修改的数据？",saveBatch);
     	}
     }
     
     
     function saveBatch(tag){
			if(tag=="ok"){
				
				//主键
				var pkValue=new Array();
	
				jQuery.ajaxSetup({async:false});
				
				// 得到JSON对象
		        var parameter ={};
			
				//保存的字段
				var save_array=new Array();
				save_array=jQuery("#save_array").val().split("-");
				
			    for(var i=0;i<save_array.length;i++){
						// 获取扩展字段数组的值
						var zczx=save_array[i];
						
						// 创建一个用来存放信息的数组;
						var array=new Array();
						
						jQuery("input[name="+zczx+"],textarea[name="+zczx+"]").each(function(j){
							
								array[j] =escape(jQuery(this).val());
							
						});
					
						//将该综测子项存入JSON对象
						parameter["array_"+zczx]=array.join("!!array!!");
						
				}
				alert(jQuery("#xn").val()+"!!@@!!"+jQuery("#xh").val());
			
				parameter["array_pkValue"]=jQuery("#xn").val()+"!!@@!!"+jQuery("#xh").val();
				
				parameter["str_xh"]=jQuery("#xh").val();
				
				parameter["xh"]=jQuery("#xh").val();
				
				parameter["str_xn"]=jQuery("#xn").val();
				
				parameter["xn"]=jQuery("#xn").val();
				
				var url = "pjpyQtxx_ajax.do?method=saveBatch";
	          	
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
				
		jQuery(function(){
			onShow();
		})
</script>
	</head>
	<body>
		<html:form action="/pjpyQtxx" method="post">
			<input type="hidden" id="url" name="url"
				value="pjpyQtxx.do?method=qtxxZj" />
			<input type="hidden" id="tableName" name="tableName"
				value="view_xsjbxx" />
			<input type="hidden" id="isFdy" name="isFdy" value="${fdyQx }" />
			<input type="hidden" id="isBzr" name="isBzr" value="${bzrQx }" />
			<input type="hidden" name="message" id="message" value="${message }" />
			<input type="hidden" name="doType" id="doType" value="${doType}" />
			<input type="hidden" name="pkValue" id="pkValue" value="${pkValue }" />
			<input type="hidden" name="have" id="have" value="${have}" />
			<input type="hidden" name="userType" id="userType"
				value="${userType }" />
		
			<input type="hidden" name="save_array" id="save_array"
					value="jlnr" />
					
			<input type="hidden" name="shzt" id="shzt" />
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>${title }</a>
				</p>
			</div>
		
			<div style='width:98%;height:510px;overflow:hidden;overflow-x:hidden'>
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
							<logic:equal name="userOnLine" value="teacher" scope="session">
								<td align="left">

									<logic:equal name="doType" value="add">
										<html:text property="xh" styleId="xh" value="${rs.xh}"  style="width:100px" readonly="true"/>
										<html:hidden property="xh" value="${rs.xh}" />
									</logic:equal>
								
									<logic:notEqual name="doType" value="add">
										
										${rs.xh}
										<html:hidden property="xh" styleId="xh" value="${rs.xh}" />
										
									</logic:notEqual>

									<logic:equal name="doType" value="add">
										<button type="button" onclick="sendXx();return false;" class="btn_01" id="buttonFindStu">
											选择
										</button>
									</logic:equal>
									<logic:equal name="doType" value="save">
										<button type="button" onclick="sendXx();return false;" class="btn_01" id="buttonFindStu">
											选择
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
								<font color="red">*</font>学年
							</th>
							<td>
								<logic:equal name="doType" value="add">
									<html:select property="xn" styleId="xn" style="width:150px" value="${xn }">
										<html:options collection="xnList" property="xn"
											labelProperty="xn" />
									</html:select>
								</logic:equal>
								<logic:notEqual name="doType" value="add">
									${xn }
									<input type="hidden" name="xn" id="xn" value="${xn}"/>
								</logic:notEqual>
							</td>
						</tr>
					</tbody>
				</table>


				<div style='width:100%;height:300px;overflow:auto;overflow-x:hidden'>
					<h3 class="datetitle_01">
						<span > 其他主要奖励 </span>
					</h3>

					<table width="100%" border="0" class="formlist">
						<tbody id="tbody_bxxx">

							<p>
								<input type="hidden" name="numAdd" id="numAdd1"
									style="width: 20px" />

								<input type="hidden" name="numDel" id="numDel1"
									style="width: 20px" />
							</p>
							<tr>
								<td>
									<div class="formbox">
										<table summary="" class="datelist" align="" width="100%">
											<!-- 打印时第一行不显示- -->

											<thead>
												<tr>
													<logic:notEqual name="doType" value="sh">
														<logic:notEqual name="doType" value="view">
														<td align="center" nowrap="nowrap" style='width:6%'>
															&nbsp;
														</td>
														</logic:notEqual>
													</logic:notEqual>
													<td nowrap="nowrap" style='width:94%'>
														<div align="center" >
														其他主要奖励信息
														</div>
													</td>
												</tr>
											</thead>
											<tbody width="100%" align="center" class="tbstyle" id="flag1">
											</tbody>
										</table>
									</div>
								</td>
							</tr>
						</tbody>
					</table>
					<logic:equal name="doType" value="sh">
						<table width="100%" border="0" class="formlist">
							<thead>
								<tr onclick="deploy('tbody_shxx')">
									<th colspan="4">
										<span>审核信息</span>
									</th>
								</tr>
							</thead>
							<tbody id="tbody_shxx">

								<tr style="height:22px">
									<th align="right" style="width: 10%">
										<bean:message key="lable.xb" />审核意见
									</th>
									<td align="left" colspan="3">
										<html:textarea name='rs' property='xyshyj' styleId="xyshyj"
											style="word-break:break-all;width:95%"
											onblur="chLeng(this,500);" rows='4' />

									</td>
								</tr>


								<logic:equal name="doType" value="sh">
									<logic:equal name="userType" value="xx">
										<tr style="height:22px">
											<th align="right" style="width: 10%">
												学校审核意见
											</th>
											<td align="left" colspan="3">
												<html:textarea name='rs' property='xxshyj' styleId="xxshyj"
													style="word-break:break-all;width:95%"
													onblur="chLeng(this,500);" rows='4' />
											</td>
										</tr>
									</logic:equal>

									<logic:equal name="userType" value="admin">
										<tr style="height:22px">
											<th align="right" style="width: 10%">
												学校审核意见
											</th>
											<td align="left" colspan="3">
												<html:textarea name='rs' property='xxshyj' styleId="xxshyj"
													style="word-break:break-all;width:95%"
													onblur="chLeng(this,500);" rows='4' />
											</td>
										</tr>
									</logic:equal>
								</logic:equal>
							</tbody>
						</table>
					</logic:equal>
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
									
									<button type="button" onclick="Close();return false;">
										关 闭
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

