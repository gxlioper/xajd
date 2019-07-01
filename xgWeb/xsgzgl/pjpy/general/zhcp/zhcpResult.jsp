<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
			<%@ include file="/syscommon/head.ini"%>
			<script type='text/javascript' src="js/xgutil.js"></script>
		<script type="text/javascript" src="js/xgservice.js"></script>
		<script type='text/javascript' src="js/moveDiv.js"></script>
		<script type="text/javascript" src="js/comm/ymPrompt.js"></script>
		<link rel="stylesheet" type="text/css" href="comm/skin/zfstyle/ymPrompt.css" media="all"/> 
		<script type='text/javascript' src="js/AjaxFunction.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getOtherData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script> 
		<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="js/xsgzgl/pjpy/pjpyComm.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/exportData.js'></script>	
		<script type='text/javascript' src="js/uicomm.js"></script>
		<script language="javascript">
		//初始化
		function onShow(){
			//
			searchRs();
		}
		
		//查询结果集
		function searchRs(){
			//按钮控制

			//controlBtn();
			var url = "general_zhcp_ajax.do?method=searchZhcpResult";
		
			var ie = "ie";

			var otherValue = [ie];
			
			var zczq_num =  jQuery("a[name=a_name_zczq]").length;
			
			if(zczq_num==1 && jQuery("#checks").val()=="no" || jQuery("#checks").val()=="yes"){
				searchRsByAjax(url,otherValue);
				jQuery("#checks").val("no");
			}else{
				alertInfo("必须且只能选择一个学年！");
				return false;
			}
			
			searchRsByAjax(url,otherValue);
			
			setTimeout("setDivHeight()","2000");
			
			if($("operation")){
				if($("operation").value=="no"){
					$("btn_xg").disabled=true;
					$("a_btn_dr").disabled=true;
				}else{
					$("btn_xg").disabled=false;
					$("a_btn_dr").disabled=false;
				}
			}
			
		}	
		
		
		function c(){
			
			jQuery("[name=tj_zczq]").each(function(){
			
				jQuery(this).click( function (){
				
					var len=jQuery(".selectedValue[name=tj_zczq]").length;
					
					if(len!=1){
						disabledBtn();
					}else{
					
						var value=jQuery(".selectedValue[name=tj_zczq]").val();
						
						var array=new Array();
						
						array=(jQuery(".selectedValue[name=tj_zczq]").attr("id")).split("tj_zczq_");
						
						value=array[1];
						if($("bcpjzq").value!=value){
							disabledBtn();
						}else{
							showBtn();
						}
					}
				}); 
			})
			
			jQuery("[name=a_name_zczq]").each(function(){
			
				jQuery(this).click( function (){
					
					var zczq_num=jQuery("a[name=a_name_zczq]").length;
					if(zczq_num==0){
						disabledBtn();
					}
				}); 
			})
		}
		
		function disabledBtn(){
			
			$("a_btn_dc").disabled=true;
			$("a_btn_dcsz").disabled=true;
			$("a_btn_lx").disabled=true;
			$("a_btn_dr").disabled=true;
			
			if($("operation")){
				if($("operation").value=="no"){
					$("btn_xg").disabled=true;
					$("a_btn_dr").disabled=true;
				}else{
					$("btn_xg").disabled=false;
					$("a_btn_dr").disabled=false;
				}
			}
			
			
		}
		
		function showBtn(){
			
			$("a_btn_dc").disabled=false;
			$("a_btn_dcsz").disabled=false;
			$("a_btn_lx").disabled=false;
			$("a_btn_dr").disabled=false;
			
			if($("operation")){
				
				if($("operation").value=="no"){
					$("btn_xg").disabled=true;
					$("a_btn_dr").disabled=true;
				}
			}
			
		}
		
		setTimeout("c()","3000");

		function lxgn(){
			showTopWin("general_pjpy.do?method=kindChoose",800,500);
		}
		
		
		//显示加载页面
		function showLoadPage(){
			//多功能操作
			$("dgncz").style.display="none";
			//查询结果
			$("cxjg").style.display="none";
			//显示
			$("page_loading").style.display="";
			
		}
		
		function showDiv(){
			viewTempDiv("计算方式选择","zcfjsDiv",350,200);
		}
		
		function showPm(){
			//选中排名
			if($("zcpm").value!="1" && $("zcpm").value!="2" && $("zcpm").value!="3"){
			if($("jslx_pm").checked){
				$("pmjs").style.display="";
			}else{
				$("pmjs").style.display="none";
			}
			}
		}
		
		function showZypm(){
			//选中排名
			if($("zypm").value!="1" && $("zypm").value!="2" && $("zypm").value!="3"){
				if($("jslx_zypm").checked){
					$("zypmjs").style.display="";
				}else{
					$("zypmjs").style.display="none";
				}
			}
		}
		
		jQuery(function(){
		
			if($("zd30")){
				var zd30=$("zd30").name;
			
				jQuery("."+zd30).each(function(){
					var text=jQuery(this).text();
						
					var title=jQuery(this).attr("title");
					
					var title=text;
					if(text.length>10){
						title=text;
						text=text.substr(0,10)+"...";
					}
					jQuery(this).attr("title",title);
					jQuery(this).text(text)
				});
			}
			

			onShow();

		});
		
		function expZccj(){
			jQuery('#select_xy').attr('id','xy');
			jQuery('#select_zy').attr('id','zy');
			jQuery('#select_bj').attr('id','bj');
			viewTempDiv("选择打印班级","expZccj",350,200);
		}
		
		function showBm(){
			tipsWindown("系统提示","id:div_bm","350","300","true","","true","id");
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
			
			confirmInfo(" 您将要计算"+message+"所有学生的总分和排名是否继续？",function(ok){
				
				if(ok=="ok"){
				
					$("divWaiting").style.display="";
					$("divDisable").style.display="";
					closeWindown()
				
					setTimeout("zhcpAccount()","1000");
					
				}
			});
		}
		
		function zhcpAccount(){
		
			jQuery.ajaxSetup({async:false});
		
			//创建一个json对象
			var parameter={};
			
			var array=new Array();
			
			//指定获取的控件类型，进行循环
			jQuery("[name=xszdArr]:checked").each(function(i){
				if(jQuery(this).val()!="xh" &&jQuery(this).val()!="xm" ){
					//获取表单控件name
					array[i]=jQuery(this).val();
				}
			});
			
			//构建json对象
			parameter["str_xydm"]=escape(jQuery("#xy").val());
			
			parameter["str_zydm"]=escape(jQuery("#zy").val());
			
			parameter["str_bjdm"]=escape(jQuery("#bj").val());
			
			parameter["str_nj"]=escape(jQuery("#nj").val());
			
			//保存URL
			var url = "general_zhcp_ajax.do?method=zhcpAccount";
			
			
			//------------AJAX保存 begin -------------
			
			jQuery.post(url,
				parameter,
				function(result){
					
					$("divWaiting").style.display="none";
					$("divDisable").style.display="none";
				
					alertInfo(result,function(tag){
				
						if(tag=="ok"){
					
							
							searchRs();
							
						}
					});
				}
			);
			
			jQuery.ajaxSetup({async:true});
			//------------AJAX保存 end -------------
			
		}
		
		function checkItsDis(obj){
			
			if(obj.disabled){
				
				return false;
			}else{
				
				return true;
			}		
		}
		</script>
	</head>
	<body >
		
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
					1.本功能默认展示的是<font color="blue">本评奖周期</font>的信息。 <br/>
					2.<font color="blue">计算功能</font>仅提供<font color="blue">学校(管理员)级别</font>用户进行操作。 <br/>
					3.计算功能仅能计算<font color="blue">本评奖周期</font>的综测分及相关排名。<br/>
					4.如果结果查询中显示的列不满足您的需要，请点击<font color="blue">列选</font>。<br/>
				</span>
			</p>
			<a class="close" title="隐藏"  onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- 提示信息 end-->
		
		<html:form action="/general_pjpy" method="post">
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- 隐藏域 -->
			<input type="hidden"  name="zypm" id="zypm" value="${zypm }" />
			<input type="hidden" name="zcpm" id="zcpm" value="${zcpm }" />
			<input type="hidden" name="xyV" value="" />
			<input type="hidden" name="zyV" value="" />
			<input type="hidden" name="bjV" value="" />
			<input type="hidden" id="cbVal" name="cbVal" value="" />
			<input type="hidden" id="checks" name="checks" value="yes" />
			<input type="hidden" id="bcpjzq" name="bcpjzq" value="${zczq }" />
			<input type="hidden" name="operation" id="operation" value="${operation}"/>
			<input type="hidden" id="xbmc" value="<bean:message key="lable.xb" />"/>
			<!-- 多功能操作区 -->
			<div class="toolbox" id="dgncz">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<!-- 页面来源 -->
						<logic:equal name="forward" value="jbsz">
							<li>
								<a href="#" onclick="goPjpyJbsz();return false;" class="btn_fh">
									返回设置
								</a>
							</li>
						</logic:equal>
						<!-- 页面来源end -->
						
						<logic:equal name="writeAble" value="yes">
							<!-- 学校级别 -->
							<logic:equal name="userType" value="xx">
								<li>
									<a href="#" onclick="if(checkItsDis(this)){showBm();};return false;" class="btn_xg" id="btn_xg">总分计算</a>
								</li>
							</logic:equal>
							<!-- 管理员级别 -->
							<logic:equal name="userType" value="admin">
								<li>
									<a href="#" onclick="if(checkItsDis(this)){showBm();};return false;" class="btn_xg" id="btn_xg">总分计算</a>
								</li>
							</logic:equal>
							<!-- 学院级别-->
							<logic:equal name="userType" value="xy">
								<li>
									<a href="#" onclick="if(checkItsDis(this)){showBm();};return false;" class="btn_xg" id="btn_xg">总分计算</a>
								</li>
							</logic:equal>
						</logic:equal>
						<li>
							<a href="#" id="a_btn_lx" onclick="if(!$('a_btn_lx').disabled && checkItsDis(this)){lxgn();}return false;" class="btn_zt">列选
							</a>
						</li>
						<logic:equal name="writeAble" value="yes">
						<li>
							<a href="#" id="a_btn_dr" onclick="if(!$('a_btn_dr').disabled  && checkItsDis(this)){impAndChkData();}return false;"
								class="btn_dr">导入数据</a>
						</li>
						</logic:equal>
						<li>
							<a href="#" class="btn_qx" id="a_btn_dcsz" onclick="if(!$('a_btn_dcsz').disabled){choiceFields();}return false;">导出设置</a>
						</li>
						<li>
							<a href="#" class="btn_dc" id="a_btn_dc"
								onclick="if(!$('a_btn_dc').disabled){configureExportData();}return false;">导出数据</a>
						</li>
					</ul>
				</div>
				<!-- 过滤条件 -->
				<%@ include file="/comm/search/superSearchArea.jsp"%>
			</div>
			<!-- 多功能操作区 end-->

			<!-- 内容显示区开始 -->
			<div class="main_box">
<!--				<div class="mid_box">-->
<!--					<div class="title">-->
<!--						<p>-->
<!--							 查询得到的数据量显示区域 -->
<!--						</p>-->
<!--					</div>-->
<!--				</div>-->
<!--				<h3 class="datetitle_01">-->
<!--					<span> 查询结果&nbsp;&nbsp; <logic:notEmpty name="rsList">-->
<!--							<font color="blue">提示：单击表头可以排序；双击记录可查看详细信息</font>-->
<!--						</logic:notEmpty> </span>-->
<!--				</h3>-->
				<div id="div_rs"
					style="width:100%;height:400px;overflow-x:auto;overflow-y:hidden;">
				</div>

				<!--分页显示-->
				<jsp:include flush="true"
					page="/sjcz/turnpage.jsp?form=generalPjpyGeneralForm"></jsp:include>
				<script type="text/javascript">
						$('choose').className="hide";
				</script>
				<!--分页显示-->
			</div>
			<!-- 内容显示区 end-->


			<!-- 部门总分计算选择DIV -->
			<div id="div_bm" style="display:none">
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
										<button type="button"  name="计 算" onclick="save();return false;">
											确 认
										</button>
										<button type="button"  name="取 消" onclick="closeWindown();return false;">
											取 消
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
			</div>
		</html:form>
	</body>
	<%@ include file="/comm/loading.jsp"%>
	<%@ include file="/comm/other/tsxx.jsp"%>
</html>
