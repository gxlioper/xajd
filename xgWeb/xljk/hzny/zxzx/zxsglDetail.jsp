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
     		
     		alertInfo("请先选择一名辅导员!",function(tag){
     		
     			if(tag=="ok"){
     			
     				return false;
     			}
     		});
     		return false;
     	}
     	
     	
     	confirmInfo("是否要保存已修改的数据？",saveZxsInfo);
     
     }
     
      function modiZxs(){
     	
     	
     	confirmInfo("是否要保存已修改的数据？",modiZxsInfo);
     
     }
 
 
     
     function saveZxsInfo(tag){
     		
			if(tag=="ok"){
				
				//主键
				var pkValue=new Array();
	
				jQuery.ajaxSetup({async:false});
				
				// 得到JSON对象
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
				
				//主键
				var pkValue=new Array();
	
				jQuery.ajaxSetup({async:false});
				
				// 得到JSON对象
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
					<em>您的当前位置:</em><a>${title }</a>
				</p>
			</div>

			<div style='width:98%;height:510px;overflow:hidden;overflow-x:hidden'>
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
									<button onclick="showTopWin('xysf_dyjsgl.do?method=getTeaInfo',750,550);" class="btn_01" id="buttonFindStu">
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
										border="0">
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

							</th >
							<td>

							</td>
						</tr>
						<tr style="height:22px">
							<th>
								资格<br/>
								<font color="blue"><B>(限500字)</B></font>
							</th>
							<td colspan="4">
								<html:textarea name='rs' property='zxszg' styleId="zxszg" style="word-break:break-all;width:99%" onblur="chLeng(this,500);"
										rows='4' />
							</td>
						</tr>
						<tr style="height:22px">
							<th>
								简介<br/>
								<font color="blue"><B>(限500字)</B></font>
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
									<span class="red">*</span>"为必填项
								</div>

								<div class="btn">
									
									<logic:equal name="doType" value="add">
											<button id="buttonSave" onclick="saveZxs();">
												保 存
											</button>
									</logic:equal>
									<logic:equal name="doType" value="modi">
											<button id="buttonSave" onclick="modiZxs();">
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
			</div>

			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>

