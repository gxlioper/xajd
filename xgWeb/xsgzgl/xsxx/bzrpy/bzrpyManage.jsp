<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" defer="defer">
		function searchRs(){
			
			var url = "/xgxt/xsxx_bzrpy.do?method=bzrpyManage";
			
			showTips('数据查询中，请稍候......');
			
			allNotEmpThenGo(url);
		}
		
		function showDgpy(xh){
		
			var url="xsxx_bzrpy_ajax.do?method=showDiv";
			
			//其他数据
		 	var parameter = {
				"xh":xh,
				"writeAble":"${writeAble}"
			};
		  	
			jQuery("#div_py").load(url,parameter,function(){
			
				var sqqx = jQuery("#sqqx").val();
				
				if(sqqx == "no"){
					$("btn_bc").style.display = "none";
				}
				
				tipsWindown("系统提示","id:div_py","350","250","true","","true","id");
			});
			
		
		
			// 打开窗口时将DIV中的隐藏学号值置为传入学号
			jQuery("#div_xh").val(xh);
			tipsWindown("系统提示","id:div_py","350","200","true","","true","id");
		}
		
		function showPlpy(){
			
			var len=jQuery("[name=pkValue]:checked").length;
			
			if(!len>0){
				alertInfo("请勾选需要评价的学生!")
				return false;
			}
			
			var url="xsxx_bzrpy_ajax.do?method=showDiv";
			
			var parameter = {
				"pylx":"plpy",
				"writeAble":"${writeAble}"
			};
			
			jQuery("#div_py").load(url,parameter,function(){
			
				tipsWindown("系统提示","id:div_py","350","250","true","","true","id");
			});
			
			// 打开批量评议窗口
			tipsWindown("系统提示","id:div_py","350","200","true","","true","id");
		}
		
		
		function saveDgpy(pyr){
			
			confirmInfo("该操作将会修改您所选记录的班主<br/>任评语信息，是否确认修改？",function(tag){
				
				if(tag=="ok"){
					var xh=jQuery("#div_xh").val();
					var pyyj=jQuery("#pyyj").val();
					
					var parameter={}
					
					parameter["xh"]=xh;
					
					parameter["pyyj"]=escape(pyyj);
					
					var url="";
					if(pyr==""){
						url = "xsxx_bzrpy_ajax.do?method=saveOnePy";
					}else{
						url = "xsxx_bzrpy_ajax.do?method=updateOnePy";
					}
					jQuery.ajaxSetup({async:false});	
					
					jQuery.post(url,
						parameter,
						function(result){
						
							alertInfo(result,function(tag){
								if(tag=="ok"){
		
									searchRs();
												
								}
							});
							
							
						}
					);
					
					jQuery.ajaxSetup({async:true});
			
				
				}
				
			});
			
		}
		
		function savePlpy(){
			
			confirmInfo("该操作将会修改您所勾选记录的班主<br/>任评语信息，是否确认修改？",function(tag){
				
				if(tag=="ok"){
					var pyyj=jQuery("#pyyj").val();
					
					var pkValue=new Array();
					
					var xh=new Array();
					
					jQuery("[name=pkValue]:checked").each(function(i){
						
						pkValue[i]=jQuery(this).val();
						
		 				xh[i]=jQuery(this).parents("tr").children().find("[name=xh]").val();
					
					});
					
					var parameter={}
					
					parameter["str_pyyj"]=escape(pyyj);
					
					parameter["array_xh"]=xh.join("!!array!!");
					
					parameter["array_pkValue"]=pkValue.join("!!array!!");
					
					var url= "xsxx_bzrpy_ajax.do?method=saveBatch";
					
					jQuery.ajaxSetup({async:false});	
					jQuery.post(url,
						parameter,
						function(result){
						
							alertInfo(result);
							
							searchRs();
							
							closeWindown();	
						}
					);
					
					jQuery.ajaxSetup({async:true});
				}
			});
		}
		</script>
	</head>
	<body>
		<html:form action="/xsxx_bzrpy" method="post">
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div class="tab_cur" id="jd">
				<p class="location">
					<em>您的当前位置:</em><a>${title }</a>
				</p>
				<p class="help">
					<a href="#" onclick="return false;" onmousedown ="showHelpDiv();">使用帮助</a>
				</p>
			</div>			
			<!-- 标题 end-->
			<!-- 提示信息 START-->
			<div class="prompt" id="div_help" style="display: none">
				<h3>
					<span>提示：</span>
				</h3>
				<p>
					<logic:notEmpty name="pysjsz" property="pysj">
					本学年班主任评议截止时间：${pysjsz.pysj}
					</logic:notEmpty>
					<logic:empty  name="pysjsz" property="pysj">
						当前学年尚未设置班主任评议时间
					</logic:empty>
					<br />
				</p>
				<a class="close" title="隐藏"
					onclick="this.parentNode.style.display='none';"></a>
			</div>
			<!-- 提示信息 end-->
			
			<!-- 多功能操作区 -->
			<div class="toolbox">
			
				<!-- 按钮 -->
				<logic:equal name="writeAble" value="yes">
				<div class="buttonbox">
					<ul>
						<li>
							<a href="#" id="btn_sh"
								onclick="showPlpy();return false;"
								class="btn_sh">
								批量评价
							</a>
						</li>
						<li>
							<a href="#" onclick="impAndChkData();return false;" class="btn_dr">
								导入
							</a>
						</li>
						<li>
							<a href="#" class="btn_qx" onclick="choiceFields();return false;">
								导出设置
							</a>
						</li>
						<li>
							<a href="#" onclick="configureExportData();return false;" class="btn_dc">
								导出
							</a>
						</li>
					</ul>
				</div>
				</logic:equal>
				<!-- 按钮 end-->
				
				<!-- 过滤条件 -->
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
			<!-- 多功能操作区 end-->
			
			<div class="formbox">
				<h3 class="datetitle_01">
					<span> 查询结果&nbsp;&nbsp; <logic:empty name="rsArrList">
							<font color="red">未找到任何记录！</font>
						</logic:empty> <logic:notEmpty name="rsArrList">
							<font color="blue">提示：单击表头可以排序;鼠标移至评语上可查看详细</font>
						</logic:notEmpty> </span>
				</h3>


				<div class="con_overlfow">
					<table summary="" id="rsTable" class="dateline" width="100%">
						<thead>
							<tr align="center" style="cursor:hand">
								<td width="15px">
									<input type="checkbox" name="all" value="all" onclick="chec()" />
								</td>
								<logic:iterate id="tit" name="topTr" offset="1" indexId="index">
									<td 
										onclick="tableSort(this)" nowrap>
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
								<td id="cz" >
									操作
								</td>
							</tr>
						</thead>
						<tbody>
							<logic:notEmpty name="rsArrList">
								<logic:iterate name="rsArrList" id="s">
									<tr onclick="rowMoreClick(this,'',event);" ondblclick=""
										style="cursor:hand">
										<td width="15px">
											<input type="checkBox" name="pkValue" id="pkV"
												value="<logic:iterate id="v" name="s" offset="0" length="1">${v}</logic:iterate>"
												<logic:iterate id="v" name="s" offset="0" length="1">${v}</logic:iterate> />
											<input type="hidden" name="xh" id="xh"
												value="<logic:iterate id="v" name="s" offset="1" length="1">${v}</logic:iterate>"
												 />
										</td>
										<logic:iterate id="v" name="s" offset="1" length="4">
											<td nowrap>
												<bean:write name="v" />
											</td>
										</logic:iterate>
										<logic:iterate id="v" name="s" offset="6" length="1">
											<td nowrap title="<logic:iterate id="m" name="s" offset="5" length="1">${m }</logic:iterate>">
												<bean:write name="v" />
											</td>
										</logic:iterate>
										<logic:iterate id="v" name="s" offset="7" length="2">
											<td nowrap title="<logic:iterate id="m" name="s" offset="5" length="1">${m }</logic:iterate>">
												<bean:write name="v" />
											</td>
										</logic:iterate>
										<td>
											<a href="#" onclick="showDgpy('<logic:iterate id="v" name="s" offset="1" length="1">${v}</logic:iterate>');return false"><font color="blue">评价</font></a>
										</td>
									</tr>
								</logic:iterate>
							</logic:notEmpty>
							<%@ include file="/comm/noRows.jsp"%>

						</tbody>
					</table>
					
				</div>
				<jsp:include flush="true"
						page="/sjcz/turnpage.jsp?form=xsxxBzrpyForm"></jsp:include>
				<!-- 评议信息DIV begin -->
				<div id="div_py" style="display:none">

				</div>
				<!-- 评议信息DIV end -->
		</html:form>
		<script type="text/javascript" src="js/bottomButton.js"></script>
		<logic:present name="result">
			<script>
				alertInfo('${message}');
			</script>
		</logic:present>
	</body>
</html>
