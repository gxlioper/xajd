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
			searchRs();
		}

		//查询结果集
		function searchRs(){
			
			jQuery.ajaxSetup({async:false});
			
			var url = "gyglnew_jqlx_ajax.do?method=jqlxsqSearch";
			var ie = "ie";
			
			var otherValue = [ie];

			$("divWaiting").style.display="";
			$("divDisable").style.display="";
			
			searchRsByAjax(url,otherValue);

			$("divWaiting").style.display="none";
			$("divDisable").style.display="none";
			
			jQuery.ajaxSetup({async:true});
		}
		
		
		function deleteLxsq(){
			
			var n=jQuery("[name=div_pkValue]:checked").length;
			
			if(n>0){
				confirmInfo("该操作将会<font color='blue'>取消已勾选的留校申请</font>，是否继续执行该操作？",function(tag){
					
					if(tag=="ok"){
						
						var pkValue=new Array();
						
						var xh=new Array();
						
						jQuery("[name=div_pkValue]:checked").each(function(i){
							
							pkValue[i]=jQuery(this).val();
						
						});
						
						var parameter={}
						
						
						parameter["pkValue"]=escape(pkValue.join("!!array!!"));
						
						var url= "gyglnew_jqlx_ajax.do?method=delete";
						
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
			}else{
				
				alertInfo("请勾选需要取消申请的记录！",function(tag){
					
					if(tag=="ok"){
						return false;
					}
				
				});
			}
		}
		
		
		//显示我的评奖详细
		function showLxsqDiv(){
		
			tipsWindown("系统提示","id:div_lxsq","400","300","true","","true","id");
		}
		
		function saveLxsq(){
			
			
			if($("kssj") && $("kssj").value==""){
				alertInfo("住校开始时间不能为空！");
				return false;
			}
			
			if($("jssj") && $("jssj").value==""){
				alertInfo("住校结束时间不能为空！");
				return false;
			}
			
			if(checkSjTj('kssj',"住校开始时间","jssj","住校结束时间")){
				
				confirmInfo("该操作将会保存假期留校申请信息，是否确定？",saveLxsqInfo);
			}
	     }
		
		function saveLxsqInfo(tag){
     		
     	
			if(tag=="ok"){
				
				//主键
				var pkValue=new Array();
	
				jQuery.ajaxSetup({async:false});
				
				// 得到JSON对象
		        var parameter ={};
				
				parameter["xh"]=jQuery("#xh").val();
				
				parameter["kssj"]=escape(jQuery("#kssj").val());
				
				parameter["jssj"]=escape(jQuery("#jssj").val());
				
				parameter["sqly"]=escape(jQuery("#sqly").val());
				
				parameter["lcid"]=escape(jQuery("#lcid").val());
				
				var url = "gyglnew_jqlx_ajax.do?method=save";
	          	
			 	$("divWaiting").style.display="";
				$("divDisable").style.display="";
				closeWindown()
				jQuery.post(url,parameter,
					function(result){
						$("divWaiting").style.display="none";
						$("divDisable").style.display="none";
						
						alertInfo(result,function(){
						
							searchRs()
						});
						
						
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
	<body >

		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>


		<html:form action="/gyglnew_jqlx" method="post">

			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- 隐藏域 -->
			<input type="hidden" name="xmdm" id="xmdm" value="${xmdm }" />
			<html:hidden property="lcid" styleId="lcid"  />
			<!-- 多功能操作区 -->
			<div class="toolbox" id="dgncz">
				<!-- 按钮 -->
				
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
						<li>
							<a href="#" onclick="showLxsqDiv();return false;" class="btn_zj">申请留校</a>
						</li>
						<li>
							<a href="#" onclick="deleteLxsq();return false;" class="btn_sc">取消申请</a>
						</li>
						</logic:equal>
					</ul>
				</div>
				
				<!-- 过滤条件 -->
				<%@ include file="/comm/search/superSearchArea.jsp"%>
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
				<h3 class="datetitle_01">
					<span> 查询结果&nbsp;&nbsp; <logic:notEmpty name="rsList">
							<font color="blue">提示：单击表头可以排序；双击记录可查看详细信息</font>
						</logic:notEmpty> </span>
				</h3>
				<div id="div_rs"
					style="width:100%;height:380px;overflow-x:auto;overflow-y:hidden;">
				</div>

				<!--分页显示-->
				<jsp:include flush="true"
					page="/sjcz/turnpage.jsp?form=jqlxForm"></jsp:include>
<%--				<script type="text/javascript">--%>
<%--						$('choose').className="hide";--%>
<%--				</script>--%>
				<!--分页显示-->
			</div>
			<!-- 内容显示区 end-->
			
			
			<div id="div_lxsq" style="display:none">
				<table class="formlist">
					<thead>
					<tr>
					<th colspan="2">
					<span>学生申请</span>
					</th>
					</tr>
					</thead>

					<tbody>
					<tr>
					<th width='25%'>
						<font color="red">*</font>预计住校<br/>开始时间
					</th>
					<td style='word-break:break-all;width:96%' >
						 <html:text property="kssj" styleId="kssj" 
							onclick="return showCalendar('kssj','y-mm-dd');" 
							onblur="dateFormatChg(this)" readonly="true" />
					</td>
					</tr>
					<tr>
					<th width='25%'>
						<font color="red">*</font>预计住校<br/>结束时间
					</th>
					<td style='word-break:break-all;width:96%'>
						 <html:text property="jssj" styleId="jssj" 
							onclick="return showCalendar('jssj','y-mm-dd');" 
							onblur="dateFormatChg(this)" readonly="true" />
					</td>
					</tr>
					<tr>
					<th width='25%'>
						申请理由<br/>
						<font color="blue">(限输入500字)</font>
					</th>
					<td style='word-break:break-all;width:96%'>
						 <html:textarea  property="sqly" rows="4" 
						 style="word-break:break-all;width:99%" styleId="sqly" 
									onblur="chLeng(this,500);"></html:textarea>
					</td>
					</tr>
					</tbody>
			
					<tfoot>
					<tr>
					<td  colspan="2">
					<div class="btn">
					<button type="button" id="btn_bc" onclick="saveLxsq()">
					保 存
					</button>
			
					<button type="button" id="btn_gb" onclick="closeWindown();return false;">
					关 闭
					</button>
					</div>
					</td>
					</tr>
					</tfoot>
				</table>
			</div>		
				
			<!-- 提示信息 -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>
