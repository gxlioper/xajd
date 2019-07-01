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
	     	
	     	if($("xn") && $("xn").value==""){
	     		
	     		alertInfo("学年不能为空!",function(tag){
	     		
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
	     	
	     	confirmInfo("是否要保存新增加的数据？",saveTxgbInfo);
	     
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
			        
					parameter["xn"]=escape(jQuery("#xn").val());
					
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
			        
			        parameter["pkValue"]=escape(jQuery("#pkValue").val());
					
					parameter["xn"]=escape(jQuery("#xn").val());
					
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
						<tr >
							<th colspan="4">
								<span>基本信息</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_jbxx">
						<tr style="height:22px">
							<th align="right" style="width: 15%">
								<font color="red">*</font>学号
							</th>
							<logic:equal name="userOnLine" value="teacher" scope="session">
								<td align="left">

									<logic:equal name="doType" value="add">
										<html:text property="xh" styleId="xh" value="${rs.xh}" style="width:100px" readonly="true"/>
										<html:hidden property="xh" value="${rs.xh}" />
									</logic:equal>
									
									<logic:notEqual name="doType" value="add">
										<logic:notEqual name="doType" value="save">
										${rs.xh}
										<html:hidden property="xh" styleId="xh" value="${rs.xh}" />
										</logic:notEqual>
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
								<font color="red">*</font>组织名称
							</th>
							<td>
								<logic:equal  name="doType" value="add">
									<input type="text" name="zzmc" id="zzmc" value="${rs.zzmc}" maxlength="10"/>
								</logic:equal>
								<logic:notEqual name="doType" value="add">
									${rs.zzmc }
									<input type="hidden" name="zzmc" id="zzmc" value="${rs.zzmc}"/>
								</logic:notEqual>
								
							</td>
						</tr>
						
						<tr style="height:22px">
							<th align="right">
								<font color="red">*</font>组织级别
							</th>
							<td align="left">
								<logic:equal  name="doType" value="add">
									<logic:equal name="userType" value="stu">
										班级
										<input type="hidden" name="zzjb" id="zzjb" value="班级"/>
									</logic:equal>
									<logic:notEqual name="userType" value="stu">
									<select name="zzjb" id="zzjb" value="${rs.zzjb }">
										<option value="学校">学校</option>
										<option value="<bean:message key="lable.xb" />"><bean:message key="lable.xb" /></option>
										<option value="班级">班级</option>
									</select>
									</logic:notEqual>
								</logic:equal>
								<logic:notEqual name="doType" value="add">
									${rs.zzjb }
									<input type="hidden" name="zzjb" id="zzjb" value="${rs.zzjb }"/>
								</logic:notEqual>
							</td>
							<th>
								<font color="red">*</font>干部名称
							</th>
							<td>
								<logic:equal  name="doType" value="add">
									<input type="text" name="gbmc" id="gbmc" value="${rs.gbmc}" maxlength="10"/>
								</logic:equal>
								<logic:notEqual name="doType" value="add">
									${rs.gbmc }
									<input type="hidden" name="gbmc" id="gbmc" value="${rs.gbmc}"/>
								</logic:notEqual>
							</td>
						</tr>
						<tr style="height:22px">
							<th align="right">
								<font color="red">*</font>学年
							</th>
							<td align="left">
								<logic:equal name="doType" value="add">
									<html:select property="xn" styleId="xn" style="width:150px" value="${xn }">
										<html:options collection="xnList" property="xn"
											labelProperty="xn" />
									</html:select>
								</logic:equal>
								<logic:notEqual name="doType" value="add">
									${rs.xn }
									<input type="hidden" name="xn" id="xn" value="${rs.xn}"/>
								</logic:notEqual>
							</td>
							<th>
								
							</th>
							<td>
								
							</td>
						</tr>
						<tr style="">
							<th align="right">
								备注<br/>
								<font color="blue"><B>(限500字)</B></font>
							</th>
							<td colspan="3">
								<html:textarea name='rs' property='bz' 
									styleId="bz" style="word-break:break-all;width:90%" 
									onblur="chLeng(this,500);" rows='5' />
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
									<logic:equal name="doType" value="add">
										<button type="button" id="buttonSave" onclick="save();return false;">
											保 存
										</button>
									</logic:equal>
									<logic:equal name="doType" value="modi">
										<button type="button" id="buttonSave" onclick="modi()">
											修 改
										</button>
									</logic:equal>

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

