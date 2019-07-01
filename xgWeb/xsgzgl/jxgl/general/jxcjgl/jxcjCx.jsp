<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：yyp -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%--<%@ include file="/syscommon/pagehead_V4.ini"%>	
		--%>
		<%@ include file="/syscommon/head.ini"%>
		<script type='text/javascript' src="js/xgutil.js"></script>
		<script type='text/javascript' src="js/uicomm.js"></script>
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
			var url = "jxgl_jxcjgl_ajax.do?method=jxcjCx";
			var ie = "10.0";
			var otherValue = [ie,jQuery("#jxid").val()];
			searchRsByAjax(url,otherValue);
		    setTimeout("setDivHeight()","1000")
		}

		//保存
		function jxcjBc(){
			var len=jQuery("[name=div_pkValue]:checked").length;
			
			if(len>=1){	
				var arr=jQuery("[name=div_pkValue]");
				var str = "";
				var xhs="";
				for(var i =0; i < arr.length; i++){
					var flag=false;
					if(jQuery(arr[i]).prop("checked")==true){
						var td = jQuery(arr[i]).parents("tr").find("td");
						var xh = jQuery(arr[i]).val();
						var cj = "";
						var flag = false;
						for(var j = 6; j < td.length;j++){
							var cjj = jQuery("[name=fz_"+i+"_"+j+"]").val();
							if(cjj!=null&&cjj!=""){
								flag=true;
							}
							cj+=cjj+"!!@@!!";
						}
					if(!flag){
						if(i!=0){
							xhs+=","
							}
						xhs+=xh;
						}
						
						str+=xh+"!!@@!!"+cj+"end!!splitOne!!";
					}
				}
				if(""!=xhs&&"14073"==jQuery("#xxdm").val()){
					alertInfo("学号<font class='blue'>["+xhs+"]</font>"+",请至少录入一项军训成绩！");
					return false;
					}
				var parameter={}
				var url="jxgl_jxcjgl_ajax.do?method=jxcjBc";
				parameter["pkValue"]=str;
				parameter["jzid"]=jQuery("#jxid").val();
				jQuery.ajaxSetup({async:false});	
				jQuery.post(url,
					parameter,
					function(result){
							alertInfo(result);
					}
				);
				jQuery.ajaxSetup({async:true});
			}else{
				alertInfo("请勾选需要保存的记录！");
				return false;
			}
			
		}

		//跳到下一个
		function skipNext(obj){
			//
		}

		//自动勾选操作
		function checkIn(obj){
			jQuery(obj).parent().parent().find("td").eq(0).find("input[type='checkbox']").attr("checked", true);
		}

		function jxcjDc(){
			var url = "jxgl_jxcjgl_ajax.do?method=jxcjDc";
			document.forms[0].action = url;
			document.forms[0].target = "_blank";
			document.forms[0].submit();
			document.forms[0].target = "_self";	
		}
		
		//维护历史成绩
		function whlscjDiv(){
			tipsWindown("军训信息","id:tempDiv","320","120","true","","true","id");
		}
		function whlsdcjSave(){
			var url = "jxgl_jxkhgl_jxcjgl.do?jxid="+jQuery("#jxxxdm").val();
			document.forms[0].action = url;
			document.forms[0].submit();
		}
		
		function jxcjglExportConfig() {
			//DCCLBH导出功能编号,执行导出函数 
			customExport("jxgl_jxkhgl_jxcjgl.do", jxcjglExportData);
		}
			
		
			
		// 导出方法
		function jxcjglExportData() {
			var tid = jQuery("#jxid").val();
			setSearchTj();//设置高级查询条件
			var url = "jxgl_jxcjgl_ajax.do?method=jxcjglExportData&jxid="+tid+"&dcclbh=" + "jxgl_jxkhgl_jxcjgl.do";//dcclbh,导出功能编号
			url = addSuperSearchParams(url);//设置高级查询参数
			jQuery("form").eq(0).attr("action", url);
			jQuery("form").eq(0).submit();
		}
		</script>
	</head>
	<body>

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
		<!-- 提示信息 START-->
		<div class="prompt" id="div_help" style="display: none">
			<h3>
				<span>提示：</span>
			</h3>
			<p>
				<span>
				1.军训成绩管理默认对<font color="blue">运行中的军训信息</font>进行成绩管理<br/>
				2.对军训成绩<font color="blue">填写后勾选保存</font>即可<br/>
				3.维护为<font color="blue">空数据</font>勾选保存相当于<font color="blue">删除</font>该成绩信息，请慎重操作<br/>
				4.点击<font color="blue">维护历史成绩</font>可对历史的军训成绩进行维护
				</span>
			</p>
			<a class="close" title="隐藏"  onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- 提示信息 end-->

		<html:form action="/jxgl_jxcjgl" method="post">

			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="xmdm" id="xmdm" value="${xmdm }" />
			<input type="hidden" id="jxid" name="jxid" value="${rs.jxid }"/>
			<!-- 隐藏域 -->
			<!-- 多功能操作区 -->
			<div class="toolbox" id="dgncz">
				<!-- 按钮 -->
				<logic:equal name="writeAble" value="yes">
				<div class="buttonbox">
					<ul>
						<li>
							<a href="#" class="btn_ccg" onclick="jxcjBc();return false;">保存</a>
							<a href="#" class="btn_dc" onclick="jxcjglExportConfig();return false;">自定义导出</a>
							<a href="#" class="btn_xg" onclick="whlscjDiv();return false;">维护历史成绩</a>
						</li>
					</ul>
				</div>
				</logic:equal>
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
					<span>
						军训名称:<font class="blue">${rs.jxmc }</font> 
					</span>
				</h3>
				<div id="div_rs"
					style="width:100%;overflow-x:auto;overflow-y:hidden;">
				</div>

				<!--分页显示-->
				<jsp:include flush="true"
					page="/sjcz/turnpage.jsp?form=jxglJxcjglForm"></jsp:include>
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
								<span>军训信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>
								军训名称
							</th>
							<td>
								<html:select name="rs" property="jxid" styleId="jxxxdm">
									<html:options collection="jxxxList" property="jxid" labelProperty="jxmc"/>
								</html:select>
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="2">
								<div class="btn">
									<button type="button" name="提交" onclick="whlsdcjSave();">
										提交
									</button>
									<button type="button" name="关闭" onclick="closeWindown();return false;">
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