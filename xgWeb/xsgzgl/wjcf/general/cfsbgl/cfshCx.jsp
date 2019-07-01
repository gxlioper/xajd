<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<script type='text/javascript' src="js/uicomm.js"></script>
		<script type='text/javascript' src="js/String.js"></script>
		
	    <script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script type="text/javascript" src="js/commit.js"></script>
		<script language="javascript" defer="defer">
		//初始化
		function onShow(){ 

			var spgw=$("spgw").value;
			var id = "left_a_0";
			if (spgw != ""){
				jQuery("input[name=xmdmArr][value="+jQuery("#xmdm").val()+"]").parent().css("background","#dce8f8");
				searchRs();
			} else {
				if($(id)){
					$(id).click();
				}
			}
		}

		//审核操作
		function modCfsh(){
			var objs = jQuery("input[type=checkbox][name=primarykey_checkVal]:checked");
			var cfId="";
			if(objs.length!=1){
				alertError("请选择一条记录！");
				return false;
			}else{
				cfId+=objs[0].value;
			}
			var cflbdm=$("xmdm").value;
			var spgw=$("spgw").value;
			
			var url="general_wjcf_cfsh_ajax.do?method=cfsh&cfId="+cfId+"&cflbdm="+cflbdm+"&spgw="+spgw;
			//showTopWin(url,'780','660');
			showDialog("",780,500,url);
		}
		
		//执行查询操作
		function searchRs(){
			jQuery.ajaxSetup({async:false});
			var v4Path = stylePath;//v4样式路径
			var url = "general_wjcf_cfsh_ajax.do?method=searchWjcfResult";
			var ie = "ie";
			var xmdm=$("xmdm").value;
			var spgw=$("spgw").value;
		
			otherValue=[ie,xmdm,spgw,v4Path];
			searchRsByAjax(url,otherValue);
			setTimeout("setDivHeight()","2000");
			jQuery.ajaxSetup({async:true});
		}

		//审批岗位
		function showSpgw(cflbdm){
			var url="general_wjcf_cfsh_ajax.do?method=showShgwDiv";
			
			//其他数据
		 	var parameter = {
				"cflbdm":cflbdm
			};
		  	
			jQuery("#div_spgw").load(url,parameter,function(){
			
				var len=jQuery("[name=spgws]").length;
		
				if(len>1){
					tipsWindown("系统提示","id:div_spgw","300","170","true","","true","id");
				}else {
					$("xmdm").value=jQuery("#text_xmdm").val();
					$("spgw").value=jQuery("[name=spgws]:checked").val();
					searchRs();
				}
			});
			$("qhspgw").value = cflbdm
		}

		function qhSpgw(){
			var cflbdm=$("qhspgw").value;
				var url="general_wjcf_cfsh_ajax.do?method=showShgwDiv";
				
				//其他数据
			 	var parameter = {
					"cflbdm":cflbdm
				};
			  	
				jQuery("#div_spgw").load(url,parameter,function(){
				
					var len=jQuery("[name=spgws]").length;
			
					if(len>1){
						tipsWindown("系统提示","id:div_spgw","300","170","true","","true","id");
					}else {
						$("xmdm").value=jQuery("#text_xmdm").val();
						$("spgw").value=jQuery("[name=spgws]:checked").val();
						searchRs();
					}
				});
			}

		//点击确定后查询
		function checkSpgw(){
			$("xmdm").value=jQuery("#text_xmdm").val();
			$("spgw").value=jQuery("[name=spgws]:checked").val();
			searchRs();
			closeWindown();
		}

		
		function plCfsh(){
			var cflbdm=$("xmdm").value;
			var spgw=$("spgw").value;
			var objs = jQuery("input[type=checkbox][name=primarykey_checkVal]:checked");
			var RowsStr="";
			if(objs.length>0){
				for (i=0; i<objs.length; i++){
			     RowsStr+=objs[i].value+",";
				}
			}
			if(""==RowsStr){
				alertError("请勾选希望审核的记录！");
				return false;
				}
			var url="general_wjcf_cfsh_ajax.do?method=plCfsh&cfId="+RowsStr+"&cflbdm="+cflbdm+"&spgw="+spgw;
			//showTopWin(url,'600','400');
			showDialog("",780,170,url);
			}

		//提交进入正式库
		function tijiao(){
			var url="general_wjcf_cfsh_ajax.do?method=tongJi";
			//showTopWin(url,'600','400');
			showDialog("",600,400,url);
			}

		
		jQuery(function(){
			onShow();
		})
		</script>
	</head>
	<body  ondrag="return false">
	
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
			
		</div>
		<!-- 标题 end-->
		
	
		
		<html:form action="/general_wjcf_cfsh_ajax" method="post">
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="sfZgj" id="sfZgj"  value="${sfZgj }">	
			<input type="hidden" name="sfZgj" id="sfZgj"  value="${sfZgj }">
			<html:hidden property="xmdm" styleId="xmdm"/>
			<html:hidden property="spgw" styleId="spgw"/>
			<input type="hidden" name="qhspgw" id="qhspgw">	
			<html:hidden styleId="primarykey_checkVal" property="primarykey_checkVal" />
			<!-- 多功能操作区 -->
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<li>
							<a href="#" onclick="modCfsh();return false;" class="btn_sh">
								审核
							</a>
						</li>
						<li>
							<a href="#" onclick="plCfsh();return false;" class="btn_sh">
								批量审核
							</a>
						</li>
						<logic:equal name ="isZgjyh" value="true">
							<li>
								<a href="#" onclick="tijiao();return false;" class="btn_shtg">
									提交
								</a>
							</li>
						</logic:equal>
						<li>
							<a href="#" onclick="qhSpgw();return false;" class="btn_sz">
								切换审批岗位
							</a>
						</li>
					</ul>
				</div>
				<!-- 按钮 end-->
				
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
			
			<table align="center" width="100%">
					<h3 class="datetitle_01">
					<span> 查询结果</span>
				</h3>
					
					<tr>
						<td  valign="top" style="overflow-x: auto;width:120px">
							<!-- 侧边栏 -->
							<div class="listbox">	
								<div class="titlelist" style="height: 352px; width:120px">
									<ul id="left_ul">
										<logic:notEmpty name="cflbList">
											<logic:iterate id="cflbdm" name="cflbList" indexId="index">	
												<li id="li_${index}" >
													<span class="ico"></span>
													<a href="#" name="left_a" id="left_a_${index}" style="width: 70%;text-overflow:ellipsis;white-space:nowrap;overflow:hidden;"
														onclick="setLiClick('${index}');showSpgw('${cflbdm.cflbdm}');return false;">
														${cflbdm.cflbmc}
													</a>
													
													<input type="hidden" name="xmdmArr" id="xmdm_${index }" value="${cflbdm.cflbdm}"/>
													<input type="hidden" id="xmmc_${cflbdm.cflbdm}" value="${cflbdm.cflbmc}"/>
												</li>
											</logic:iterate>
										</logic:notEmpty>
									</ul>
								</div>
							</div>
							<!-- 侧边栏 end-->
						</td>
					<td > 
						<!-- 内容显示区开始 -->
					<div id="div_rs" class="con_overlfow"
						style="width:100%;height:380px;overflow-x:auto;overflow-y:hidden;">
					</div>
					
					</td>
				</tr>
			</table>
				<!--分页显示-->
				<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=wjcfGeneralForm"></jsp:include>
				<!--分页显示-->
			</div>
			
			
			
			<!-- 提示信息 -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
		<logic:present name="message">
			<script>
				alertInfo('${message}');
			</script>
		</logic:present>
		<!-- 内容显示区 end-->
			<div id="div_spgw" style="display:none"></div>
	</body>
</html>