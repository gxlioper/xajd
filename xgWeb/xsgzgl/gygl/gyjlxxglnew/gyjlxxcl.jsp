<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：cq -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<script type='text/javascript' src="js/uicomm.js"></script>
		<%@ include file="/syscommon/head.ini"%>		
		
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		
		
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

		//查询
		function searchRs(){
			var url = "gyjl_gyjlglnew_ajax.do?method=gyjlxxcl";
			var ie = "ie";
			var otherValue = [ie];
			searchRsByAjax(url,otherValue);
		    setTimeout("setDivHeight()","1000")
		}

		//处理
		function gyjlcl(){
		
			var len=jQuery("[name=div_pkValue]:checked").length;
			var flg = true;

			if (len > 0){
				var chbox = jQuery("[name=div_pkValue]:checked");
				for (var i = 0 ; i < chbox.length ; i++){
					var shzt = jQuery(chbox[i]).parents("tr").find("td").eq(8).text();

					if (shzt != '未审核'&&shzt!='退回'){
						flg = false;
						break;
					}
				}
			} else{
				alertInfo("请勾选一条需要处理的记录！");
			}

			if (flg){
				if(len==1){	
					
					var pk_value=jQuery("[name=div_pkValue]:checked").eq(0).val();

					var xh=pk_value.split("!!shen!!")[0];

					var jlsj=pk_value.split("!!shen!!")[2];
					
					var gyjllbdm=pk_value.split("!!shen!!")[1];

					var url="gyglnew_gyjlgl_gyjlclwh.do";
					
					url+="?xh="+xh+"&jlsj="+jlsj+"&gyjllbdm="+gyjllbdm;
					 
					//showTopWin(url,800,600);
					showDialog("公寓纪律信息处理", 800, 500, url);
				} else if (len > 1) {
					//tipsWindown("","id:shDiv","450","250","true","","true","id");
					tipsWindownNew("","id:shDiv",450,250);
				}
			} else {
				alertInfo("已审核的记录不能再次处理！");
			}
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
			showDialog("查看学生公寓纪律信息", 800,500,url);
		}


		//批量保存
		function saveShzt(){
			var cljg=jQuery("#cljg").val();
			if (cljg == null || cljg == "") {
				alertInfo("请选择处理结果!",function(){return false;});
				return false;
			} 
			if (jQuery("#dcqk").val()==null ||jQuery("#dcqk").val()=='') {
				alertInfo("请填写调查情况！",function(){return false;});
				return false;
			}
		//	confirmInfo("确定要处理已勾选的记录吗?",function(tag){

		//		if(tag=="ok"){
					var array = document.getElementsByName("div_pkValue");
					var pk = "";
					for (var i=0;i<array.length;i++) {
						if (array[i].checked) {
							pk+= array[i].value;
							pk+="!!@@";
						}
					}
					var url="gyjl_gyjlglnew_ajax.do?method=gyjlxxPlcl";	
					var parameter={}
					parameter["pkValue"]=pk;
					parameter["cljg"]=escape(jQuery("#cljg").val());
					parameter["dcqk"]=escape(jQuery("#dcqk").val());
					parameter["ylzd1"]=jQuery("#ylzd1").val();						
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

		function cancelCl(){
			var len=jQuery("[name=div_pkValue]:checked").length;
			if(len == 1){	
				var array = jQuery("[name=div_pkValue]:checked");
				var str = "";
				for (var i=0;i<array.length;i++) {
					var temp = jQuery(array[i]).parent().parent().find("td");
					var cljg = temp.eq(7).text();
					var shzt = temp.eq(8).text();
					if (shzt != '未审核'&&shzt!='退回'){
						alertInfo("已审核的记录不能撤销！");
						return false;
					}else if (cljg == '未处理'){
						alertInfo("未处理的记录不能撤销！");
						return false;
					}else{
						var pkValue = temp.eq(0).find("input[type='checkbox']").val();
						str += pkValue+"!!@@";
					}
				}
				confirmInfo("您确定要撤销操作吗？",function(tag){
					if(tag=="ok"){
						var parameter={}
						var url="gyjl_gyjlglnew.do?method=gyjlCancelCl";	
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
			<input type="hidden" name="xxdm" id="xxdm" value="${xxdm }" />
			<!-- 隐藏域 -->
			<!-- 多功能操作区 -->
			<div class="toolbox" id="dgncz">
				<!-- 按钮 -->
				<logic:equal value="yes" name="writeAble">
				<div class="buttonbox">
					<ul>
						<li><a href="#" onclick="gyjlcl();return false;" class="btn_sh"> 处理 </a></li>
						<li><a href="javascript:void(0);" onclick="cancelCl();return false;" class="btn_qxsh">撤销</a></li>
                        <%--传媒个性化按钮  只有zf01拥有删除权限--%>
						<logic:equal name="xxdm" value="11647">
							<logic:equal name="userName" value="zf01">
								<li><a href="#" onclick="del();return false;" class="btn_sh"> 删除 </a></li>
							</logic:equal>
						</logic:equal>
							
					</ul>
				</div>
				</logic:equal>
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
									<span>批量处理公寓纪律信息</span>
								</th>
							</tr>
						</thead>
						<tbody>
						<tr>
								<th width="30%">
									<font color="red">*</font>处理结果
								</th>
								<td width="70%" colspan="3">
									<html:select property="cljg" style="width:150px" styleId="cljg">
										<html:option value="">--请选择--</html:option>
										<html:options collection="cflbList" property="gyjlcfdm" labelProperty="gyjlcfmc" />
									</html:select>
								</td>
								</tr>
							<logic:equal value="13033" name="xxdm">
								<tr>
									<th width="20%">
										赔偿金额
									</th>
									<td align="left" width="30%" colspan="3">
										<html:text property="ylzd1" styleId="ylzd1" style="width:150px" maxlength="10" onkeyup="value=value.replace(/(?:\D*)?(\d*)?(\.)?(\d*)?(?:\d*)?/ig,'$1$2$3')"></html:text>&nbsp;&nbsp;元&nbsp;&nbsp;
									</td>
								</tr>
								</logic:equal>
							<tr>
								<th width="30%">
									<font color="red">*</font>调查情况<br/>(<font color="blue">限录入500字</font>)
								</th>
								<td width="70%" >
									<html:textarea property="dcqk" rows="4" styleId="dcqk" style="word-break:break-all;width:97%" onblur="chLeng(this,500);">
									</html:textarea>
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
										<button type="button" name="确定" onclick="saveShzt();return false;">
											保存
										</button>
										<button type="button" name="取消" onclick="Close()" id="buttonClose">
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