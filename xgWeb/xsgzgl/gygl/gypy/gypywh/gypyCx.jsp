<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：yyp -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>	
		<script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		
		<script type='text/javascript' src="js/comm/message.js"></script>
		<script type="text/javascript" src="<%=stylePath%>js/lhgdialog/lhgdialog.min.js?skin=iblue"></script>	
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script><!-- 导入功能需要 -->
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

		function searchRs(){
			var url = "gygl_gypywh_ajax.do?method=gypyCx";
			var ie = "10.0";
			var otherValue = [ie];
			searchRsByAjax(url,otherValue);
		    setTimeout("setDivHeight()","1000")
		}

		
		//增加寝室信息
		function gypyDivSave(){
			if($("xn") && $("xn").value==""){
		 		alertInfo("学年不能为空!",function(tag){
		 			if(tag=="ok"){
		 				return false;
		 			}
		 		});
		 		return false;
			}
			if($("xqdm") && $("xqdm").value==""){
		 		alertInfo("学期不能为空!",function(tag){
		 			if(tag=="ok"){
		 				return false;
		 			}
		 		});
		 		return false;
			}
			if($("pylbdm") && $("pylbdm").value==""){
		 		alertInfo("评优类别不能为空!",function(tag){
		 			if(tag=="ok"){
		 				return false;
		 			}
		 		});
		 		return false;
			}

			if($("pydx") && $("pydx").value==""){
		 		alertInfo("评优对象不能为空!",function(tag){
		 			if(tag=="ok"){
		 				return false;
		 			}
		 		});
		 		return false;
			}
			
			if($("pysj") && $("pysj").value==""){
		 		alertInfo("评优时间不能为空!",function(tag){
		 			if(tag=="ok"){
		 				return false;
		 			}
		 		});
		 		return false;
			}
			var xn=jQuery("#xn").val();
			var xqdm=jQuery("#xqdm").val();
			var pylbdm=jQuery("#pylbdm").val();
			var pydx = jQuery("#pydx").val();
			var pysj=jQuery("#pysj").val();
			var url="gygl_gypywh.do?method=pyqsCx";
			url+="&xn="+xn;
			url+="&xqdm="+xqdm;
			url+="&pylbdm="+pylbdm;
			url+="&pydx="+pydx;
			url+="&pysj="+pysj;
<%--			showTopWin(url,800,600);--%>
<%--			closeWindown();--%>
			showDialog("公寓评优", 800, 560, url);
		}

		function showModi(){
			var len=jQuery("[name=div_pkValue]:checked").length;
			if(len>=1){
				confirmInfo("是否确定删除勾选的记录？",function(tag){
					if(tag=="ok"){
						var array = jQuery("[name=div_pkValue]:checked");
						var str = "";
						for (var i=0;i<array.length;i++) {
							var pkValue = jQuery(array[i]).parent().parent().find("td").eq(0).find("input[type='checkbox']").val();
							str += pkValue+"!!@@!!";
						}
						var parameter={}
						var url="gygl_gypywh_ajax.do?method=gypySc";
						parameter["pkValue"]=str;
						jQuery.ajaxSetup({async:false});	
						jQuery.post(url,
							parameter,
							function(result){
									alertInfo(result);
									searchRs();
							}
						);
						jQuery.ajaxSetup({async:true});
					}
				});
			}else{
				alertInfo("请勾选需要删除的寝室信息！",function(tag){
					if(tag=="ok"){
						return false;
					}
				});
			}
		}
		var DCCLBH = "gygl_gypywh_ajax.do";//dcclbh,导出功能编号
		//自定义导出 功能
		function exportConfig() {
			//DCCLBH导出功能编号,执行导出函数 
			customExport(DCCLBH, exportData);
		}

		// 导出方法
		function exportData() {
			setSearchTj();//设置高级查询条件
			var url = "gygl_gypywh_ajax.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
			url = addSuperSearchParams(url);//设置高级查询参数
			jQuery("form").eq(0).attr("action", url);
			jQuery("form").eq(0).submit();
		}

		/**
		 * 导入
		 */
		function importConfig() {
			toImportData("IMPORT_N381101");
			return false;
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


		<html:form action="/gygl_gypywh" method="post">

			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="xmdm" id="xmdm" value="${xmdm }" />
			<!-- 隐藏域 -->
			<!-- 多功能操作区 -->
			<div class="toolbox" id="dgncz">
				<!-- 按钮 -->
				
				<logic:notEqual name="userType"  value="stu">
					<logic:equal name="writeAble" value="yes">	
						<div class="buttonbox">
							<ul>
								<li>
									<a href="#" class="btn_zj" onclick="tipsWindownX('增加公寓评优','id:tempDiv','380','250','true','','true','id');return false;">增加</a>
								</li>
								<li>
									<a href="#" class="btn_sc" onclick="showModi();return false;">删除</a>
								</li><%--
								 <li><a href="#" onclick="choiceFields();return false;" class="btn_qx">导出设置</a></li>
								<li><a href="#" onclick="configureExportData();return false;" class="btn_dc">导出数据</a></li>
								
								--%>
								<li><a href="javascript:void(0);" onclick="importConfig();return false;" class="btn_dr">导入</a></li>
								<li><a href="javascript:void(0);" class="btn_dc" onclick="exportConfig();return false;">导出</a></li>
								
							</ul>
						</div>
					</logic:equal>
				</logic:notEqual>
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
					<span> 查询结果&nbsp;&nbsp; </span>
				</h3>
				<div id="div_rs"
					style="width:100%;overflow-x:auto;overflow-y:hidden;">
				</div>

				<!--分页显示-->
				<jsp:include flush="true"
					page="/sjcz/turnpage.jsp?form=gypywhForm"></jsp:include>
			</div>
			<div id="div_detail" style="display:none">
			</div>		
			<!-- 提示信息 -->
			<%@ include file="/comm/other/tsxx.jsp"%>
			<div id="tempDiv" style="display: none">
				<table align="center" class="formlist">
					<thead>
						<tr>
							<th colspan="2">
								<span>增加公寓评优信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>
								<span class="red">*</span>学年
							</th>
							<td>
								<html:select name="rs" property="xn" styleId="xn" style="width:150px">
									<html:options collection="xnList" property="xn" labelProperty="xn"/>
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								<span class="red">*</span>学期
							</th>
							<td>
								<html:select name="rs" property="xqdm" styleId="xqdm" style="width:150px">
									<html:options collection="xqList" property="xqdm" labelProperty="xqmc"/>
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								<span class="red">*</span>评优类别
							</th>
							<td>
								<html:select property="pylbdm" styleId="pylbdm" style="width:150px">
									<html:option value="">--请选择--</html:option>
									<html:options collection="pylbList" property="pylbdm" labelProperty="pylbmc"/>
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								<span class="red">*</span>评优对象
							</th>
							<td>
								<html:select property="pydx" styleId="pydx" style="width:150px">
									<html:option value="">--请选择--</html:option>
									<html:option value="0">楼栋</html:option>
									<html:option value="1">寝室</html:option>
									<html:option value="2">楼长</html:option>
									<html:option value="3">层长</html:option>
									<html:option value="4">寝室长</html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								<span class="red">*</span>评优时间
							</th>
							<td>
								<html:text property="pysj" styleId="pysj" onclick="return showCalendar('pysj','y-mm-dd');" onblur="dateFormatChg(this)" readonly="true"></html:text>
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
									<button type="button" id="bcBtn" name="保存" onclick="gypyDivSave();">
										下一步
									</button>
									<button id="gb" type="button" name="关闭" onclick="closeWindown();return false;">
										关闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
		</html:form>
	</body>
</html>
