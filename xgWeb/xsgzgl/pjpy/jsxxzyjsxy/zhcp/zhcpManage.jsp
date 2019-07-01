<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：伟大的骆 -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	    <script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" defer="defer">
		//初始化
		function onShow(){
			
			var id = "left_a_0";
			if($(id)){
				$(id).click();
			}
			
			
		}

		//查询结果集
		function searchRs(){
			//按钮控制 
			controlBtn();
			var url = "general_zhcp_ajax.do?method=searchZhcpZcxx";
			var xmdm = $("shxm").value;
			var xmmc = escape($("xmmc_"+xmdm).value);
			var lyb = escape($("lyb_"+xmdm).value);
			var xmjb = escape($("xmjb_"+xmdm).value);
			var ie = "ie";

			var otherValue = [ie,xmdm,xmmc,lyb,xmjb];

			//showWaitingDiv("1000");
			
			if(checkSearch()){
				searchRsByAjax(url,otherValue);
				$("is_default").value = "no";
			}
		}
		
		
		//按钮控制
		function controlBtn(){
			
			var xmdm = $("shxm").value;
			if(xmdm == "zd1"){
				$("buttonbox").style.display="";
				if($("li_js")){//计算
					$("li_js").style.display="";
				}
				if($("li_bc")){//保存
					$("li_bc").style.display="none";
				}
			}else if(xmdm=="zd2"){
				$("buttonbox").style.display="";
				if($("li_bc")){//保存
					$("li_bc").style.display="";
				}
				if($("li_js")){//计算
					$("li_js").style.display="none";
				}
			}else {
				$("buttonbox").style.display="none";
				if($("li_js")){//计算
					$("li_js").style.display="none";
				}
				if($("li_bc")){//保存
					$("li_bc").style.display="none";
				}
			}
		}

		//检验可否查询
		function checkSearch(){
		
			var flag = true;
			var is_default = $("is_default").value;
			
			if(is_default!=""){
				
				var xn_num =  jQuery("a[name=a_name_xn]").length;
				
				if(xn_num != "1"){
					alertError("学年条件不可为空，且只能选择一个！");
					flag = false;
				}
			}
			return flag;
		}
		
		//显示字段修改
		function showEditDiv(zd,zdid){
			
			var url="czzy_zhcp_ajax.do?method=showZdxgDiv";
			
			//其他数据
		 	var parameter = {
				"zd":zd,
				"zdid":zdid
			};
		  	
			jQuery("#div_zdxg").load(url,parameter,function(){
			
				var sqqx = jQuery("#sqqx").val();
				
				if(sqqx == "no"){
					$("btn_bc").style.display = "none";
				}
				
				jQuery("#"+zd+"_id").val(jQuery("#"+zdid).val());
				
				tipsWindown("系统提示","id:div_zdxg","350","250","true","","true","id");
			});
		}
		
		
		function affirmValue(zd,zdid){
			jQuery("#"+zdid).val(jQuery("#"+zd+"_id").val());
			closeWindown();
		}
		
		//保存综合测评信息
		function saveZhcpInfo(){
			
			confirmInfo("该操作将会保存已修改信息，是否继续？",function(tag){
				if(tag=="ok"){
						var kzzd=document.getElementsByName("kzzdArr");
						var xmdm=jQuery("#shxm").val();
						var pkValue=new Array();
						var fs=new Array();
						jQuery.ajaxSetup({async:false});
							
						var joinArr=new Array();
							//遍历扩展字段数组
						var kzzdArr=jQuery("input[name=kzzdArr]").each(function(i){
								// 获取扩展字段数组的值
								var kzzd=jQuery(this).val();
								
								// 创建一个用来存放信息的数组;
								var array=new Array();
								
								jQuery("input[name="+kzzd+"],select[name="+kzzd+"]").each(function(j){
									
										array[j] =escape(jQuery(this).val());
									
								});
							
								joinArr[i]=array.join(" !!@@!! ");
								
						});
						
						jQuery("[name=div_pkValue]").each(function(i){
									
							pkValue[i] =escape(jQuery(this).val());
									
						});
						
						jQuery("[name=fs]").each(function(i){
									
							fs[i] =escape(jQuery(this).val());
									
						});
						
		
						var url = "general_zhcp_ajax.do?method=saveZhcpInfo";
						
		             // 得到JSON对象
		             var parameter ={};
		          	 
					 for(i=0;i<joinArr.length;i++){
					 	
					 	parameter[kzzd[i].value]=joinArr[i];
					 		
				 	 }
				 	 parameter["xmdm"]=xmdm;
					 parameter["pkValue"]=pkValue.join(" !!@@!! ");
					 parameter["fs"]=fs.join(" !!@@!! ");
		
				 	$("divWaiting").style.display="";
					$("divDisable").style.display="";
					
					jQuery.post(url,
						parameter,
						function(result){
							$("divWaiting").style.display="none";
							$("divDisable").style.display="none";
							alertInfo(result);
							
							searchRs();
							closeWindown();	
						}
					);
					
					jQuery.ajaxSetup({async:true});
				}
			});
			
		}
		
		function showJsfw(){
			tipsWindown("系统提示","id:plszDiv","350","300","true","","true","id");
		}
		
		function save(){
			var xy=jQuery("#xy option:selected").text();
			var zy=jQuery("#zy option:selected").text();
			var bj=jQuery("#bj option:selected").text();
			var nj=jQuery("#nj option:selected").text();
			var message="";
			if(jQuery("#nj").val()!=""){
				message+=nj+"级";
			}
			
			if(jQuery("#xy").val()!=""){
				message+=xy+jQuery("#xbmc").val();
			}
			
			if(jQuery("#zy").val()!=""){
				message+=zy+"专业";
			}
			
			if(jQuery("#bj").val()!=""){
				message+=bj+"班";
			}
			
			confirmInfo(" 您将要同步"+message+"所有学生的智育分、体育分、平时体育分，是否继续？",function(ok){
				if(ok=="ok"){
					var url = "general_zhcp_ajax.do?method=accountZhcp";
					
					var parameter ={};
					parameter["xydm"]=$("xy").value;
					parameter["zydm"]=$("zy").value;
					parameter["bjdm"]=$("bj").value;
					parameter["nj"]=$("nj").value;
	
					jQuery.ajaxSetup({async:false});
					jQuery.post(url,
						parameter,
						function(result){
							$("divWaiting").style.display="none";
							$("divDisable").style.display="none";
							alertInfo(result);
							searchRs();
							closeWindown();	
						}
					);
					jQuery.ajaxSetup({async:true});
				}
			});
		}
		
		jQuery(function(){
			onShow();
		})
		</script>
	</head>
	<body  >
	
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
			<p class="help">
				<a href="#" onclick="return false;" onmousedown ="showHelpDiv();">使用帮助</a>
			</p>
		</div>
		<!-- 标题 end-->
		
		<!-- 提示信息 end-->
		<div class="prompt" id="div_help" style="display: none">
			<h3>
				<span>提示：</span>
			</h3>
			<p>
				<span>
				1.本功能默认展示的是本评奖学年学期的数据。
				</span>
			</p>
			<a class="close" title="隐藏"  onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- 提示信息 end-->
		
		<html:form action="/general_pjpy" method="post">

			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- 是否初始化  -->
			<input type="hidden" name="is_default" id="is_default" value=""/>
			<input type="hidden" name="shxm" id="shxm" value="${czxm }"/>
			<input type="hidden" name="pjxn" id="pjxn" value="${pjxn }"/>
			<input type="hidden" name="pjxq" id="pjxq" value="${pjxq }"/>
			<input type="hidden" id="xbmc" value="<bean:message key="lable.xb" />"/>
			
			<!-- 隐藏域 end-->
			
			<!-- 多功能操作区 -->
			<div class="toolbox" >
				<!-- 按钮 -->
				<div class="buttonbox" id="buttonbox">
					<ul>
						<li >
							<a href="#" id="li_bc" onclick="saveZhcpInfo();return false;" class="btn_yl">
								保 存
							</a>
						</li>
						<li >
							<a href="#" id="li_js" onclick="showJsfw();return false;" class="btn_tj">
								同 步 
							</a>
						</li>
					</ul>
				</div>
				<!-- 按钮 end-->
		
				<!-- 过滤条件 -->
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
			<!-- 多功能操作区 end-->
			
			<!-- 内容显示区开始 -->
			<div class="main_box">
				<div class="mid_box">
					<div class="title">
						<p>
							<!-- 查询得到的数据量显示区域 -->
						</p>
					</div>
				</div>
				<!-- From内容 -->
				<table align="center" width="100%">
					<tr>
						<td colspan="2">
							<h3 class="datetitle_01">
								<span>
									
								</span>
							</h3>
						</td>
					</tr>
					<tr>
						<td width="15%" valign="top" style="overflow-x: auto;">
							<!-- 侧边栏 -->
							<div class="listbox">	
								<div class="titlelist" style="height: 352px;">
									<ul id="left_ul">
										<logic:notEmpty name="cshXmList">
											<logic:iterate id="xmnr" name="cshXmList" indexId="index">	
												<li id="li_${index}" class="Child">
													<a href="#" name="left_a" id="left_a_${index}" 
														onclick="if(checkHadEdit()){setLiClick('${index}');searchRs();};return false;">
														<span class="ico"></span>${xmnr.xmmc}
													</a>
													<input type="hidden" id="xmdm_${index }" value="${xmnr.xmdm}"/>
													<input type="hidden" id="xmmc_${xmnr.xmdm}" value="${xmnr.xmmc}"/>
													<input type="hidden" id="xmjb_${xmnr.xmdm}" value="${xmnr.xmjb}"/>
													<input type="hidden" id="lyb_${xmnr.xmdm}" value="${xmnr.lyb}"/>
												</li>
											</logic:iterate>
										</logic:notEmpty>
									</ul>
								</div>
							</div>
							<!-- 侧边栏 end-->
						</td>
						<td width="85%" valign="top" style="position: relative;">
							<div id="div_rs" style="width:100%;height:360px;overflow-x:auto;overflow-y:auto;">
							</div>
						</td>
					</tr>
				</table>
				<!--分页显示-->
				<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=generalPjpyGeneralForm"></jsp:include>
				<script type="text/javascript">
						$('choose').className="hide";
				</script>
				<!--分页显示-->
			</div>
			<!-- 内容显示区 end-->
			
			<!-- 字段修改弹出层 -->
			<div id="div_zdxg" style="display:none">
			</div>
			
			<!-- 总分计算选择DIV -->
			<div id="plszDiv" style="display:none">
				<div class="open_win01">
					<table align="center" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>总分计算</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th>
									年级
								</th>
								<td>
									<html:select property="nj" styleId="nj"
										style="width:150px" onchange="initZyList();initBjList();">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj"
											labelProperty="nj" />
									</html:select>
								</td>
							</tr>
							<tr>
								<th>
									<bean:message key="lable.xb" />
								</th>
								<td>
									<logic:equal name="userType" value="xy">
										<html:select property="xydm" styleId="xy" disabled="true"
											value="${userDep }" style="width:150px"
											onchange="initZyList();initBjList();">
											<html:option value=""></html:option>
											<html:options collection="xyList" property="xydm"
												labelProperty="xymc" />
										</html:select>
										<html:hidden property="xydm" value="${userDep }" />
									</logic:equal>
									<logic:notEqual name="userType" value="xy">
										<html:select property="xydm" styleId="xy" style="width:150px"
											onchange="initZyList();initBjList();">
											<html:option value=""></html:option>
											<html:options collection="xyList" property="xydm"
												labelProperty="xymc" />
										</html:select>
									</logic:notEqual>
								</td>
							</tr>
							<tr>
								<th>
									专业
								</th>
								<td>
									<html:select property="zydm" styleId="zy" style="width:150px"
										onchange="initBjList();">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zydm"
											labelProperty="zymc" />
									</html:select>
								</td>
							</tr>
							<tr>
								<th>
									班级
								</th>
								<td>
									<html:select property="bjdm" styleId="bj" style="width:150px">
										<html:option value=""></html:option>
										<html:options collection="bjList" property="bjdm"
											labelProperty="bjmc" />
									</html:select>
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="2">
									<div class="bz">
										"
										<span class="red">*</span>"为必填项
									</div>
									<div class="btn">
										<button type="button" name="计 算" onclick="save();return false;">
											确 认
										</button>
										<button type="button" name="取 消" onclick="closeWindown();return false;">
											取 消
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
			</div>
			
			<!-- 提示信息 -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>