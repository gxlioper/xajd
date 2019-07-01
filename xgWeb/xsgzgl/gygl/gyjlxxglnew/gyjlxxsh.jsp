<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：cq -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type='text/javascript' src="js/uicomm.js"></script>
		<script type='text/javascript' src="js/String.js"></script>		
		<script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" defer="defer">
		//初始化
		jQuery(document).ready(function(){ 
			searchRs();
		});

		function setDivHeight(){
			if($("table_rs")){
				jQuery("#div_rs").height(jQuery("#table_rs").height()+20);	
			}
		}


		//查询
		function searchRs(){
			var url = "gyjl_gyjlglnew_ajax.do?method=gyjlxxsh"; 
			var ie = "ie";
			var otherValue = [ie];
			searchRsByAjax(url,otherValue);
		    setTimeout("setDivHeight()","1000")
		}

		//删除
		function del(){
			var len=jQuery("[name=div_pkValue]:checked").length;
			var blog=true;
			if(len>=1){	
				if(blog){
					confirmInfo("该操作将会删除您所勾选的数据，是否确定继续操作？",function(tag){
						if(tag=="ok"){
							var array = jQuery("[name=div_pkValue]:checked");
							var str = "";
							for (var i=0;i<array.length;i++) {
								var pkValue = jQuery(array[i]).parent().parent().find("td").eq(0).find("input[type='checkbox']").val();
								str += pkValue+"!!@@";
							}
							var parameter={}
							var url="gyjl_gyjlglnew.do?method=gyjlSc";	
							parameter["str"]=str;							
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
			}else{
				alertInfo("请勾选需要删除的数据！",function(tag){
					if(tag=="ok"){
						return false;
					}
				});
			}
		}
		
		//审核
		function gyjlSh(){
		
			var len=jQuery("[name=div_pkValue]:checked").length;
			
			if(len==1){	
				
				var pk_value=jQuery("[name=div_pkValue]:checked").eq(0).val();

				var xh=pk_value.split("!!shen!!")[0];

				var jlsj=pk_value.split("!!shen!!")[2];
				
				var gyjllbdm=pk_value.split("!!shen!!")[1];
				
				var url="gyglnew_gyjlgl_gyjlclsh.do";
				
				url+="?xh="+xh+"&jlsj="+jlsj+"&gyjllbdm="+gyjllbdm;

				//showTopWin(url,800,610);
				if(jQuery("#xxdm").val() == '11799'){
					showDialog("审核公寓奖惩信息", 760, 505, url);
				}else{
					showDialog("审核公寓纪律信息", 760, 505, url);
				}
			
			}else if(len>1){
				//tipsWindown("","id:shDiv","450","250","true","","true","id");
				tipsWindownNew("","id:shDiv",550,250);
			}else{
				
				alertInfo("请勾选需要审核的记录！");
				
				return false;
			}
		}
		
		//批量审核
		function gyjlPlsh(){
				var shzt = jQuery("#shzt").val();
			//	confirmInfo("确定要审核已勾选的记录吗?",function(tag){
			//		if(tag=="ok"){
						var array = document.getElementsByName("div_pkValue");
						var pk = "";
						for (var i=0;i<array.length;i++) {
							if (array[i].checked) {
								pk+= array[i].value;
								pk+="!!@@";
							}
						}
						var url="gyjl_gyjlglnew_ajax.do?method=gyjlxxPlsh";	
						var parameter={}
						parameter["pkValue"]=pk;
						parameter["shzt"]=shzt;	
						parameter["shyj"]=escape(jQuery("#shyj").val());		
						jQuery.ajaxSetup({async:false});	
						jQuery.post(url,
							parameter,
							function(result){
								alertInfo(result,function(tag){
									if(tag=="ok"){
										closeWindown();
										searchRs();
									}
								});
							}
						);
					//}else {
						return false;
					//}
				//});
			}
		
		//双击查看
		function ShowView(){
			
			var pk_value=curr_row.getElementsByTagName('input')[0].value;
			
			var xh=pk_value.split("!!shen!!")[0];
			
			var jlsj=pk_value.split("!!shen!!")[2];
				
			var gyjllbdm=pk_value.split("!!shen!!")[1];
			
			var url="gyglnew_gyjlgl.do?method=gyjlcxView&act=clview";
			
			url+="&xh="+xh+"&wjsj="+jlsj+"&gyjllbdm="+gyjllbdm;

			//showTopWin(url,800,600);
			showDialog("公寓纪律信息审核查看", 800,500,url);
		}
		function gyjltjDc(){
			var url = "gygl_gyjl_wjtj.do?method=getGyjltj";
			document.forms[0].action = url;
			document.forms[0].target = "_blank";
			document.forms[0].submit();
			document.forms[0].target = "_self";	
		}

		function gyjlxxshExportConfig() {
			//DCCLBH导出功能编号,执行导出函数 
			customExport("gyjl_gyjlglnew_ajax.do", gyjlxxshExportData);
			}
			
		
			
		// 导出方法
		function gyjlxxshExportData() {
			setSearchTj();//设置高级查询条件
			var url = "gyjl_gyjlglnew_ajax.do?method=gyjlxxshexportData&dcclbh=" + "gygl_gyjlglnew_gyjlxxsh.do";//dcclbh,导出功能编号
			url = addSuperSearchParams(url);//设置高级查询参数
			jQuery("form").eq(0).attr("action", url);
			jQuery("form").eq(0).submit();
		}

		function cancelSh(){
			var len=jQuery("[name=div_pkValue]:checked").length;
			if(len == 1){	
				var array = jQuery("[name=div_pkValue]:checked");
				var str = "";
				for (var i=0;i<array.length;i++) {
					var temp = jQuery(array[i]).parent().parent().find("td");
					var shzt = temp.eq(8).text();
					if (shzt == '未审核'){
						alertInfo("未审核的记录不能撤销！");
						return false;
					}else{
						var pkValue = temp.eq(0).find("input[type='checkbox']").val();
						str += pkValue+"!!@@";
					}
				}
				confirmInfo("您确定要撤销操作吗？",function(tag){
					if(tag=="ok"){
						var parameter={}
						var url="gyjl_gyjlglnew.do?method=gyjlCancelSh";	
						parameter["str"]=str;							
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
				alertInfo("请选择一条需要撤销的数据！",function(tag){
					if(tag=="ok"){
						return false;
					}
				});
			}
		}
		
		</script>
	</head>
	<body>
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>


		<html:form action="/gyjl_gyjlglnew" method="post">

			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="xmdm" id="xmdm" value="${xmdm }" />
			<input type="hidden" id="xxdm" value="${xxdm}" />
			<!-- 隐藏域 -->
			<!-- 多功能操作区 -->
			<div class="toolbox" id="dgncz">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<logic:equal value="yes" name="writeAble">
							<li><a href="#" onclick="gyjlSh();return false;" class="btn_sh"> 审核 </a></li>
							<li><a href="javascript:void(0);" onclick="cancelSh();return false;" class="btn_qxsh">撤销</a></li>
							<%--传媒个性化按钮  只有zf01拥有删除权限--%>
							<logic:equal name="xxdm" value="11647">
								<logic:equal name="userName" value="zf01">
									<li><a href="#" onclick="del();return false;" class="btn_sh"> 删除 </a></li>
								</logic:equal>
							</logic:equal>
						</logic:equal>
							<li><a href="#" onclick="gyjltjDc();return false;" class="btn_sh"> 纪律汇总导出 </a></li>
							<li><a href="#" class="btn_dc" onclick="gyjlxxshExportConfig();return false;">导出</a></li>
					</ul>
				</div>
				<!-- 按钮 end-->
				
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
					<span> 查询结果&nbsp;&nbsp;<font color="blue">双击记录可查看详细信息;</font></span>
				</h3>
				<div id="div_rs"
					style="width:100%;overflow-x:auto;overflow-y:hidden;">
				</div>

				<!--分页显示-->
				<jsp:include flush="true"
					page="/sjcz/turnpage.jsp?form=gyjlxxglNewForm"></jsp:include>
			</div>
			<div id="div_detail" style="display:none">
			</div>		
			<!-- 提示信息 -->
			<%@ include file="/comm/other/tsxx.jsp"%>
			<div id="shDiv" style="display: none">
				<div class="open_win01">
					<table align="center" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>
										<logic:equal value="11799" name="xxdm">
											审核公寓纪律信息
										</logic:equal>	
										<logic:notEqual value="11799" name="xxdm">						
											审核公寓纪律信息
										</logic:notEqual>										
									</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th width="30%">
									审核状态
								</th>
								<td width="70%">
									<html:select property="shzt" style="width:150px" styleId="shzt">
<%--										<html:option value="wsh">未审核</html:option>--%>
										<html:option value="tg">通过</html:option>
										<html:option value="btg">不通过</html:option>
										<html:option value="th">退回</html:option>
									</html:select>
								</td>
							</tr>
							<tr>
								<th width="30%">
									审核意见<br/>(<font color="blue">限录入500字</font>)
								</th>
								<td width="70%">
									<jsp:include page="/xsgzgl/comm/shlc/cyyj.jsp?gnid=gyjlxx&id=shyj" />
									<textarea rows="4" id="shyj" name="shyj" style="word-break:break-all;width:95%;margin-top: 5px" onblur="chLeng(this,500);"></textarea>
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="2">
									<div class="bz">
										"<span class="red">*</span>"为必填项
									</div>
									<div class="btn">
										<button type="button" name="保存" onclick="gyjlPlsh();return false;">
											保存
										</button>
										<button type="button" name="取消" onclick="Close();return false;">
											取消
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
</html>