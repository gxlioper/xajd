<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：伟大的骆 -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="js/xsgzgl/pjpy/pjpyComm.js"></script>
		<script language="javascript" defer="defer">	
		//查询结果集
		function searchRs(){
			
			jQuery.ajaxSetup({async:false});
			
			
			
			var url = "general_wdpj_jgcx_ajax.do?method=searchPjpyJgcx";
			var ie = "ie";
			
			var currentPage = "1";
			if($("currentPage")){
				currentPage = $("currentPage").value;
			}
			
			var editPageSize = "";
			if($("editPageSize")){
				editPageSize = $("editPageSize").value;	
			}
				
			var pagesize = "";
			if($("pagesize")){
				pagesize = $("pagesize").value;
			}
			
			var str_xh=jQuery("#xh").val();
			var str_xm=jQuery("#xm").val();
			var str_nj=jQuery("#nj").val();
			var str_xydm=jQuery("#xy").val();
			var str_zydm=jQuery("#zy").val();
			var str_bjdm=jQuery("#bj").val();
			var str_xn=jQuery("#xn").val();
			var str_xq=jQuery("#xq").val();
			var str_xmmc=jQuery("#xmmc").val();
			 
			var parameter = {};
			parameter["currentPage"]=currentPage;
			parameter["editPageSize"]= editPageSize;
			parameter["pagesize"]=pagesize;
			parameter["str_xh"]=str_xh;
			parameter["str_xm"]= escape(str_xm);
			parameter["str_nj"]=str_nj;
			parameter["str_xydm"]=str_xydm;
			parameter["str_zydm"]=str_zydm;
			parameter["str_bjdm"]=str_bjdm;
			parameter["str_xn"]=str_xn;
			parameter["str_xq"]=str_xq;
			parameter["str_xmmc"]= escape(str_xmmc);
			parameter["stylePath"]=stylePath;
			parameter["ie"]=ie;
		
			$("divWaiting").style.display="";
			$("divDisable").style.display="";
			
			jQuery("#div_rs").load(url,parameter,function(){
				setTimeout("setPageInfo()",500);
				$("divWaiting").style.display="none";
				$("divDisable").style.display="none";
			});
			
			
			jQuery.ajaxSetup({async:true});
		}

		//显示学生申请
		function showWdpjXssq(xmdm,opera){
			
			var url = "general_pjpy.do?method=wdpjXssqDetail&xmdm="+xmdm;
			url+="&opera="+opera;
			showTopWin(url,"800","600");
		}
		
		//初始化
		function onShow(){ 
			searchRs();
		}
		
		
		function deleteXssqInfo(){
			 var pkArr=document.getElementsByName("pkValue");
			 var pkValue=new Array();
			 var flag=true;
			 jQuery("input[name=pkValue]:checked").each(function(i){
			 	
			 	pkValue[i]=jQuery(this).val();
			 	
			 });
			 
			 for(i=0;i<pkArr.length;i++){
			 	if(pkArr[i].checked){
			 		var children = pkArr[i].parentNode.childNodes;
				 	var  shInfo= children[3].value;
				 	if(shInfo!="未审核"){
				 		flag=false;
				 		alertInfo("勾选的记录中存在已审核记录，无法删除，请确认！");
				 		return false;
				 	}
			 	}
			 }
			 
			 if(pkValue.length==0){
			
			 	alertInfo("请勾选需要删除的记录！");
			
			 }else{
		
				confirmInfo("此操作将会删除您所勾选的记录，是否确定？",function(tag){
				
					if(tag=="ok"){
					
						var url = "general_wdpj_jgcx_ajax.do?method=deleteXssqInfo";
						
						 
						 var parameter ={};
			          	
						 parameter["array_pkValue"]=pkValue.join(" !!array!! ");
			
					 	 $("divWaiting").style.display="";
						 $("divDisable").style.display="";
						
						 jQuery.post(url,
							parameter,
							function(result){
								
								$("divWaiting").style.display="none";
								$("divDisable").style.display="none";
								alertInfo(result);
								
								searchRs();
							}
						 );
						
						 jQuery.ajaxSetup({async:true});
						 
						 return false;
					
					}
				});
			 }
		}
		
		//显示学生申请
		function showWdpjView(xmdm,xh){
			var url = "general_pjpy.do?method=wdpjXmshDetail&xmdm="+xmdm;
			url+="&opera=view";
			url+="&xh="+xh
			showTopWin(url,"800","600");
		}
		
		//打印报表
		function printPj(url){
			var num = document.getElementsByName("pkValue").length;
			var flag = false;
			var n = 0;
			var jxjName = "";
			var xh = "";
			for(var i=0;i<num;i++){
				var obj = document.getElementsByName("pkValue")[i];
				if(obj.checked == true){
					flag = true;
					var children = obj.parentNode.childNodes;
					jxjName = children[1].value;
					xh = children[2].value;
					n++;
				}
			}
			
			if(flag){
				if(n==1){
				
					 document.forms[0].action = url+"&jxjName="+jxjName+"&xh="+xh;
					 document.forms[0].target = "_blank";
					 document.forms[0].submit();
					 document.forms[0].target = "_self";
				}else{
					alertInfo("请勾选一条记录！");
				}
			}else{
				alertInfo("请勾选一条需要打印记录！");
			}	
	    }
	    
		
		jQuery(function(){
			onShow();
		})
		</script>
	</head>
	<body >

		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>评奖评优 - 我的评奖 - 结果查询</a>
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
				鼠标移动到右上角
				<font color="blue">帮助中心</font>，可查看本模块的相关说明。
				</br>
				<span id="div_help" style="display: none">
					1.本功能默认展示的是本评奖学年学期的数据。 </span>
			</p>
			<a class="close" title="隐藏"  onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- 提示信息 end-->

		<html:form action="/general_pjpy" method="post">
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			

			<!-- 多功能操作区 -->
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<li>
							<a href="#" onclick="goPjpyWdpj();return false;" class="btn_fh">
								返回我的评奖 </a>
						</li>
						<li>
							<a href="#" onclick="deleteXssqInfo();return false;"
								class="btn_sc"> 删除 </a>
						</li>
						<li>
							<a href="#" class="btn_dy"
								onclick="printPj('pjpyCommPrint.do?method=printJsp')"
								id="btn_dy">打印</a>
						</li>
					</ul>
				</div>
				<!-- 按钮 end-->

				<div class="searchtab">
					<table width="100%" border="0">
						<tbody>
							<tr>
								<th>
									学号
								</th>
								<td>
									<input type="text" name="xh" id="xh" value=""
										style="width:150px" />
								</td>
								<th>
									姓名
								</th>
								<td>
									<input type="text" name="xm" id="xm" value=""
										style="width:150px" />
								</td>
								<th>
									年级
								</th>
								<td>
									<html:select property="nj" styleId="nj" style="width:150px"
										onchange="initZyList();initBjList();">
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
											value="${userDep}" style="width:150px"
											onchange="initZyList();initBjList();">
											<html:option value=""></html:option>
											<html:options collection="xyList" property="xydm"
												labelProperty="xymc" />
										</html:select>
										<html:hidden property="queryequals_xydm" value="${userDep }" />
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
							<tr>
								<th>
									学年
								</th>
								<td>
									<html:select property="pjxn" styleId="xn" style="width:150px">
										<html:options collection="xnList" property="xn"
											labelProperty="xn" />
									</html:select>
								</td>
								<th>
									学期
								</th>
								<td>
									<html:select property="pjxq" styleId="xq" style="width:150px">
										<html:option value=""></html:option>
										<html:options collection="xqList" property="xqdm"
											labelProperty="xqmc" />
									</html:select>
								</td>
								<th>
									评奖项目
								</th>
								<td>
									<html:text property="xmmc" styleId="xmmc" style="width:150px" />
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="6">
									<div class="btn">
										<input type="hidden" name="go" value="a" />
										<button type="button" class="btn_cx" id="search_go" onclick="searchRs();return false;">
											查 询
										</button>
										<button type="button" class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
											重 置
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>

			</div>

			<h3 class="datetitle_01">
				<span> 查询结果&nbsp;&nbsp; </span>
			</h3>

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
				<div id="div_rs"
					style="height:380px;overflow-x:auto;overflow-y:auto;">
				</div>

				<!--分页显示-->
				<div style="clear:both;">
					<jsp:include flush="true"
						page="/sjcz/turnpage.jsp?form=generalPjpyGeneralForm"></jsp:include>
					<script type="text/javascript">
				     //$('choose').className="hide";
				     </script>
				</div>
				<!--分页显示-->
			</div>
			<!-- 内容显示区开始 end-->
			<!-- 提示信息 -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>
