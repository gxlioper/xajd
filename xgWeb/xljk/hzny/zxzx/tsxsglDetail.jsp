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
     		
     		alertInfo("请先选择一名学生!",function(tag){
     		
     			if(tag=="ok"){
     			
     				return false;
     			}
     		});
     		return false;
     	}
     	
     	if($("tbgxxslb") && $("tbgxxslb").value==""){
     		
     		alertInfo("特殊关心学生类别为必填字段，请确认!",function(tag){
     		
     			if(tag=="ok"){
     			
     				return false;
     			}
     		});
     		return false;
     	}
     	
     	
     	confirmInfo("是否要保存已修改的数据？",saveTsxsInfo);
     
     }
     
      function modiTsxs(){
     	
     	
     	confirmInfo("是否要保存已修改的数据？",modiTsxsInfo);
     
     }
 
 
     
     function saveTsxsInfo(tag){
     		
			if(tag=="ok"){
				
				//主键
				var pkValue=new Array();
	
				jQuery.ajaxSetup({async:false});
				
				// 得到JSON对象
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
				
				//主键
				var pkValue=new Array();
	
				jQuery.ajaxSetup({async:false});
				
				// 得到JSON对象
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
							<th align="right" style="width: 10%">
								<font color="red">*</font>学号
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
											选择
										</button>
									</logic:equal>
									<logic:equal name="doType" value="save">
										<button onclick="sendXx();return false;" class="btn_01" id="buttonFindStu">
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
								<font color="red">*</font>特别关心学生类别
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
									上报人
								</th>
								<td>
									${rs.sbrxm}
								</td>
								<th align="right">
									上报时间
								</th>
								<td align="left">
									${rs.sbsj}
								</td>
							</tr>
						</logic:notEqual>
						
						<tr style="height:22px">
							<th align="right">
								是否住院
							</th>
							<td align="left">
								<html:radio name="rs" property="sfzy" styleId="sfzy_0" value="是" />是
								<html:radio name="rs" property="sfzy" styleId="sfzy_1"  value="否" />否
							</td>
							<th>
								上报人联系电话
							</th>
							<td>
								<html:text name="rs" property="sbrlxdh" styleId="sbrlxdh" onblur="checkPhone(this)" maxlength="20" />
							</td>
						</tr>
						<tr style="height:22px">
							<th align="right">
								学生危机表现<br/>
								<font color="blue"><B>(限500字)</B></font>
							</th>
							<td colspan="3">
								<html:textarea name='rs' property='xswjbx' styleId="xswjbx" style="word-break:break-all;width:99%" onblur="chLeng(this,500);"
										rows='4' />
							</td>
						</tr>
						
						<tr style="height:22px">
							<th align="right">
								特别关心措施<br/>
								<font color="blue"><B>(限500字)</B></font>
							</th>
							<td colspan="3">
								<html:textarea name='rs' property='tbgxcs' styleId="tbgxcs" style="word-break:break-all;width:99%" onblur="chLeng(this,500);"
										rows='4' />
							</td>
						</tr>
						
						<tr style="height:22px">
							<th align="right">
								<bean:message key="lable.xb" />处理过程<br/>
								<font color="blue"><B>(限500字)</B></font>
							</th>
							<td colspan="3">
								<html:textarea name='rs' property='xyclgc' styleId="xyclgc" style="word-break:break-all;width:99%" onblur="chLeng(this,500);"
										rows='4' />
							</td>
						</tr>
						
						<tr style="height:22px">
							<th align="right">
								学生当前状况<br/>
								<font color="blue"><B>(限500字)</B></font>
							</th>
							<td colspan="3">
								<html:textarea name='rs' property='xsdqzk' styleId="xsdqzk" style="word-break:break-all;width:99%" onblur="chLeng(this,500);"
										rows='4' />
							</td>
						</tr>
						
						<tr style="height:22px">
							<th align="right">
								备注<br/>
								<font color="blue"><B>(限500字)</B></font>
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
									<span class="red">*</span>"为必填项
								</div>

								<div class="btn">
									<%--						            <logic:equal name="writeAble" value="yes">--%>
									<logic:equal name="doType" value="add">
										<button id="buttonSave" onclick="saveTsxs()">
											保 存
										</button>
									</logic:equal>
									<logic:equal name="doType" value="modi">
										<button id="buttonSave" onclick="modiTsxs()">
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

