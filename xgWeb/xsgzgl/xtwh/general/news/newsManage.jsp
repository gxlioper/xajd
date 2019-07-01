<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="js/Basic.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script language="javascript">
		//增加
		
		//初始化
		function onShow(){ 		
			searchRs()
		}
		
		//预览
		function newsyl(){
			var pkValues=document.getElementsByName("primarykey_checkVal");
			var pkValue="";
			var n = 0;
			//alert(n);
			for(i=0;i<pkValues.length;i++){
				if(pkValues[i].checked){
					n++;
					if(pkValue==""){
						pkValue=pkValues[i].value;
					}else{
						alertInfo('请选择一行');
						return false;
					}
				}
			}
			if(n==1 && pkValue!=""){
				//var pkValue = curr_row.getElementsByTagName('input')[0].value;
				var url = 'xtwh_news.do?method=newsInfo&newsId='+pkValue;
				document.forms[0].action = url;
				document.forms[0].target = "_blank";
				document.forms[0].submit();
				document.forms[0].target = "_self";	
			}else{
				alertInfo('请选择一行');
				return false;
			}
		}
		//删除
	function delNews(){
	
	var url = "xtwh_newsAjax.do?method=newsDelete";
	
	var n = jQuery("[name=primarykey_checkVal]:checked").length;
	
	
	if(n>0){
		var i = 0;
		var parameter = {};
		var array = new Array();
		var tArray = new Array();
		jQuery("[name=primarykey_checkVal]:checked").each(function(i){			
				array[i] = escape(jQuery(this).val());
				tArray[i] = escape(jQuery(this).parent().children().eq(-1).val());
		});
		parameter["array_primarykey_checkVal"]=array.join('!!array!!');
		parameter["tableArr"]= tArray.join(',');
			confirmInfo("确定要删除选中的记录吗?",function(ok){
				if(ok=="ok"){		
					jQuery.post(url,parameter,function(result){
						alertInfo(result);
						searchRs();		
					});					
				}
			});
	}else{				
		alertInfo("请勾选需要删除的数据！");
	}
	}
		//查询结果
		function searchRs(){
			jQuery.ajaxSetup({async:false});
			
			var url = "xtwh_newsAjax.do?method=newsSearch";
			var ie = "ie";
		
			var parameter = {"ie":ie}
			var typeid=jQuery("#typeid option:checked").val();
			parameter["typeid"]=typeid;
			jQuery("select,input",jQuery("#tbody_search_query")).each(function(){				
				parameter["str_"+jQuery(this).attr("name")]=escape(jQuery(this).val());
			})
			$("divWaiting").style.display="";
			$("divDisable").style.display="";
			searchGo(url,parameter);
			
			$("divWaiting").style.display="none";
			$("divDisable").style.display="none";
			
			jQuery.ajaxSetup({async:true});
		}
		function fb(){
			var url = "xtwh_newsAjax.do?method=newsFb";
			var n = jQuery("[name=primarykey_checkVal]:checked").length;
			
			if(n>0){
				var flag=false;
				jQuery("[name=primarykey_checkVal]:checked").each(function(){
					if(jQuery(this).parents().children("td").eq(5).html()=="是"){
						flag=true;
					}
				});
				if(flag){
					alertInfo("新闻已发布，不能发布");
					return false;
				}else{
					var i = 0;
					var parameter = {};
					var array = new Array();
					jQuery("[name=primarykey_checkVal]:checked").each(function(i){			
							array[i] = escape(jQuery(this).val());
					});
					parameter["array_primarykey_checkVal"]=array.join('!!array!!');
						confirmInfo("确定要发布吗?",function(ok){
							if(ok=="ok"){		
								jQuery.post(url,parameter,function(result){
									alertInfo(result);
									searchRs();		
								});					
							}
					});
				}
			}else{				
				alertInfo("请勾选需要发布的数据！");
			}
		}
		function qxfb(){
			var url = "xtwh_newsAjax.do?method=newsQxfb";
			var n = jQuery("[name=primarykey_checkVal]:checked").length;
			
			if(n>0){
				var flag=false;
				jQuery("[name=primarykey_checkVal]:checked").each(function(){
					if(jQuery(this).parents().children("td").eq(5).html()=="否"){
						flag=true;
					}
				});
				if(flag){
					alertInfo("新闻未发布，不能取消发布");
					return false;
				}else{
					var i = 0;
					var parameter = {};
					var array = new Array();
					jQuery("[name=primarykey_checkVal]:checked").each(function(i){			
							array[i] = escape(jQuery(this).val());
					});
					parameter["array_primarykey_checkVal"]=array.join('!!array!!');
						confirmInfo("确定要取消发布吗?",function(ok){
							if(ok=="ok"){		
								jQuery.post(url,parameter,function(result){
									alertInfo(result);
									searchRs();		
								});					
							}
					});
				}
			}else{				
				alertInfo("请勾选需要取消发布的数据！");
			}
		}
		function zd(){
			var url = "xtwh_newsAjax.do?method=newsZd";
			var n = jQuery("[name=primarykey_checkVal]:checked").length;
			
			if(n>0){
				var flag=false;
				jQuery("[name=primarykey_checkVal]:checked").each(function(){
					if(jQuery(this).parents().children("td").eq(5).html()=="否"){
						flag=true;
					}
				});
				if(flag){
					alertInfo("未发布新闻不能置顶！");
					return false;
				}else{
					var i = 0;
					var parameter = {};
					var array = new Array();
					jQuery("[name=primarykey_checkVal]:checked").each(function(i){			
							array[i] = escape(jQuery(this).val());
					});
					parameter["array_primarykey_checkVal"]=array.join('!!array!!');
						confirmInfo("确定要置顶吗?",function(ok){
							if(ok=="ok"){		
								jQuery.post(url,parameter,function(result){
									alertInfo(result);
									searchRs();		
								});					
							}
						});
				}
			}else{				
				alertInfo("请勾选需要置顶的数据！");
			}
		}
		function qxzd(){
			var url = "xtwh_newsAjax.do?method=newsQxzd";
			var n = jQuery("[name=primarykey_checkVal]:checked").length;
			
			if(n>0){

				var i = 0;
				var parameter = {};
				var array = new Array();
				jQuery("[name=primarykey_checkVal]:checked").each(function(i){			
						array[i] = escape(jQuery(this).val());
				});
				parameter["array_primarykey_checkVal"]=array.join('!!array!!');
					confirmInfo("确定要取消置顶吗?",function(ok){
						if(ok=="ok"){		
							jQuery.post(url,parameter,function(result){
								alertInfo(result);
								searchRs();		
							});					
						}
					});
			}else{				
				alertInfo("请勾选需要取消置顶的数据！");
			}
		}
		function modNews(){
			var typeid=jQuery("#typeid").val();
			var url="xtwh_newsAjax.do?method=newsUpdate&typeid="+typeid;
			var n = jQuery("[name=primarykey_checkVal]:checked").length;
			if(n==0){
				alertInfo("请勾选需要修改的数据！");
				return false;
			}else if(n>1){
				alertInfo("请勾选一条数据！");
				return false;
			}else{
				var i = 0;
				var array = new Array();
				jQuery("[name=primarykey_checkVal]:checked").each(function(i){			
						array[i] = escape(jQuery(this).val());
				});
				var pk = array[0];
				url+="&newsId="+pk;
				//showTopWin(url,'800','600');
				showDialog('',900,505,url);
				
			}
		}
		function addNews(){
			var typeid=jQuery("#typeid").val();
			var url="xtwh_newsAjax.do?method=newsAdd&typeid="+typeid;
			//showTopWin(url,'900','650');
			showDialog('',900,519,url);
		}
		function yydmdView(newsid,tablename){
			var url="xtwh_news.do?method=yydmdView&newsid="+newsid+"&tablename="+tablename;
			showDialog('已阅读人员名单',700,450,url);
			}

		
		
		jQuery(function(){
			onShow();
		})
		</script>
	</head>
	<body >
		<html:form action="/xtwh_news" method="post">
			<div class="tab_cur" >
				<p class="location">
					<em>您的当前位置:</em><a>${title }</a>
				</p>
			</div>
			
			<!-- 提示信息 end-->	
			<!-- 模块类型 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox" id="dgncz">
				<!-- 按钮 -->
				<logic:equal name="writeAble" value="yes">
				<div class="buttonbox">
					<ul>
						<li><a href="#" onclick="addNews();return false;" class="btn_zj"> 增加 </a></li>
						<li><a href="#" onclick="modNews();return false;" class="btn_xg"> 修改 </a></li>
						<li><a href="#" onclick="delNews();return false;" class="btn_sc"> 删除 </a></li>
						<li><a href="#" class="btn_yl" onclick="newsyl();return false;">预览</a></li>
						<li><a href="#" class="btn_shtg" onclick="fb();return false;">发布</a></li>
						<li><a href="#" onclick="qxfb();return false;" class="btn_shbtg"> 取消发布 </a></li>
						<li><a href="#" onclick="zd();return false;" class="btn_sy"> 置顶</a></li>
						<li><a href="#" onclick="qxzd();return false;" class="btn_xy"> 取消置顶 </a></li>
					</ul>
				</div>
				</logic:equal>
				<!-- 过滤条件 
				include file="/comm/search/superSearchArea.jsp"
				 过滤条件 end-->
				<div id="tbody_search_query" class="searchtab">
				<table width="100%" class="">
					<tbody>
						<tr>
							<td>标题</td>
							<td><input type="text" name="bt" id="bt" onkeypress="if(pressEnter(event)){searchRs();return false;}" style="width: 220px" maxlength="50"/></td>
							<td>
								是否发布
							</td>
							<td>
							<select name="sffb" id="sffb" style="width:100px"  onchange="">
								<option value=""></option>
								<option value="是">是</option>
								<option value="否">否</option>
							</select>
							</td>
							<td>
								是否置顶
							</td>
							<td >
								<select name="sfzd" id="sfzd" style="width:100px"  onchange="">
									<option value=""></option>
									<option value="是">是</option>
									<option value="否">否</option>
								</select>
							</td>
						</tr>
						<tr>
							<td>发布时间</<td>
							<td>
								<input type="text" name="kssj"  id="kssj"  onkeypress="if(pressEnter(event)){searchRs();return false;}" 
									onclick="return showCalendar('kssj','y-mm-dd');" 
									readonly="readonly"  style="width:100px;" />&nbsp;至
								<input type="text" name="jssj" id="jssj"  onkeypress="if(pressEnter(event)){searchRs();return false;}" 
									onclick="return showCalendar('jssj','y-mm-dd');" 
									readonly="readonly" style="width:100px;"  /></td>
							<td>通知类别</td>
							<td >
								<html:select property="typeid" styleId="typeid" onchange="searchRs();return false;" style="width:100px">
									<option value="">全部</option>
									<html:options collection="typeList" property="typeid" labelProperty="typename" />
								</html:select>
							</td>
							<td colspan="2" align="center">
								<div class="btn" style="padding-right: 40px;">
				              		<button type="button" id="search_go" 
				              		onclick="searchRs();return false;">
				              		查 询
				              		</button>
				              		<button type="button" class="btn_cz" id="btn_cz" 
									onclick="searchReset();return false;">
									重 置
									</button>
				            	</div>
							</td>
						</tr>
					</tbody>
				</table>
				</div>
			</div>
			<div class="formbox" id="cxjg"><%--
				<h3 class="datetitle_01">
					<span>
						查询结果&nbsp;&nbsp;
					</span>
				</h3>


					--%><div id="div_rs"
						style="width:100%;height:380px;overflow-x:auto;overflow-y:hidden;">
					</div>

					<!--分页显示-->
					<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=newsManageForm"></jsp:include>
					<!--分页显示-->
				<%--<logic:empty name="rsList">
					<div class="con_overlfow" style="text-align: center; color: red;" >
						当前搜索结果无数据。
					</div>
				</logic:empty>--%>
			</div>
			<!-- 提示信息 -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>

	</body>
</html>
