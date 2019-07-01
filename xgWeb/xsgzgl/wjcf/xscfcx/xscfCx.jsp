<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%--<%@ include file="/syscommon/pagehead_V4.ini"%>
		
		--%><%@ include file="/syscommon/head.ini"%>
		<script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type='text/javascript' src="js/uicomm.js"></script>
		<script type='text/javascript' src="js/String.js"></script>
		<script type='text/javascript' src="js/xgutil.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script> 
		<script type='text/javascript' src='/xgxt/dwr/interface/exportData.js'></script>
		
		<script language="javascript" defer="defer">
		//初始化
		function onShow(){ 
			searchRs();
		}
		
		
		//查询结果集
		function searchRs(){
		
			var url = "xscfCxAjax.do";

			var ie = "ie";

			var otherValue = [ie];

			searchRsByAjax(url,otherValue);
			
			setTimeout("setDivHeight()","1000")
		}
		
		function setDivHeight(){
			if($("table_rs")){
				jQuery("#div_rs").height(jQuery("#table_rs").height()+20);	
			}
		}
		
		function showView(obj){

				var pkValue=jQuery(obj).parent().parent().find("td").eq(0).find("input").val();
				var url="xscfCk.do?cfid=";
				url+=pkValue;
				showDialog("", 760, 420, url);
		}
		
		
		function cfsscl(obj) {
				
			var pkValue=jQuery(obj).parent().parent().find("td").eq(0).find("input").val();
//			alert(pkValue);
//			jQuery("#cfid").val(pkValue);
//			tipsWindown("处分申诉","id:cfssDiv","460","300","true","","true","id");
			var url="wjcfCfssgl_cfsswh.do?method=cfsswhZj&cfid="+pkValue;
			showDialog("", 450,320, url);
			
			
		}
		function ssjgsave() {
			var pkValue=jQuery("#cfid").val();
			var sqly = jQuery("#sssqly").val();
			var fjmc = $("fj").value;
			jQuery("#fjmc").val(fjmc);
			if (fjmc != null && fjmc != "") {
				var hz = fjmc.substr(fjmc.lastIndexOf(".")+1,fjmc.length);
				if (hz!='doc'&&hz!='rar'&&hz!='pdf'){
					alertError("上传文件类型只能为：doc,rar,pdf");
					return false;
				}
			}
			if(fjmc.length > 50){
				alertError("文件名过长,请修改文件名后再上传");
				return false;
			}
			if (sqly == null || sqly=="") {
				alertError("带*号内容必填！");
				return false;
			}
			refreshForm('xsssSave.do?cfid='+pkValue);
		}
		function cfjccl(obj) {
			tipsWindown("处分解除申请","id:jcssDiv","460","300","true","","true","id");
			var pkValue=jQuery(obj).parent().parent().find("td").eq(0).find("input").val();
			jQuery("#cfid").val(pkValue);
		}
		function jcjgsave() {
			var pkValue=jQuery("#cfid").val();
			var sqly = jQuery("#jcsqly").val();
			if (sqly == null || sqly=="") {
				alertError("带*号内容必填！");
				return false;
			}
			refreshForm('xsjcSave.do?cfid='+pkValue);
		}
		function qxss(obj) {
			confirmInfo("该操作将会删除申诉信息，是否确定继续操作？",function(tag){
						
				if(tag=="ok"){
					var pkValue=jQuery(obj).parent().parent().find("td").eq(0).find("input").val();
					jQuery("#cfid").val(pkValue);
					refreshForm('xsssQx.do?cfid='+pkValue);	
				}
			});
		} 
		function qxjc(obj) {
		confirmInfo("该操作将会删除申请解除信息，是否确定继续操作？",function(tag){
				if(tag=="ok"){
			var pkValue=jQuery(obj).parent().parent().find("td").eq(0).find("input").val();
			jQuery("#cfid").val(pkValue);
			refreshForm('xscfjcQx.do?cfid='+pkValue);
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
		</div>


		<html:form action="/wjcfCfshwh_xscfcx" method="post" enctype='multipart/form-data'>

			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- 隐藏域 -->
			<input type="hidden" name="xmdm" id="xmdm" value="${xmdm }" />
			<input type="hidden" name="cfid" id="cfid"/>
			<input type="hidden" name="fjmc" id="fjmc"/>
			<!-- 多功能操作区 --> 
			<div class="toolbox" id="dgncz">
				<!-- 按钮 -->

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
							<font color="blue">提示：单击表头可以排序；</font>
						</logic:notEmpty> </span>
				</h3>
				<div id="div_rs"
					style="width:100%;height:380px;overflow-x:auto;overflow-y:hidden;">
				</div>

				<!--分页显示-->
				<jsp:include flush="true"
					page="/sjcz/turnpage.jsp?form=wjcfXscfActionForm"></jsp:include>
<%--				<script type="text/javascript">--%>
<%--						$('choose').className="hide";--%>
<%--				</script>--%>
				<!--分页显示-->
			</div>
			<!-- 内容显示区 end-->
			
			
			<div id="div_detail" style="display:none">
			</div>		
		
			<!-- 处分申诉 -->
			<div id="cfssDiv" style="display: none">
				<div class="open_win01">
					<table align="center" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>
									处分申述申请
									</span>
									<font color="red">文件名不能超过50个汉字</font>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr id="">
								<td style="width:25%">
									证明材料或附件
									<br/>(限doc,rar,pdf格式)
								</td>
								<td style="width:75%">
									<html:file  property='fj' styleId ="fj" />
								</td> 
							</tr>
							<tr id="">
								<td align="right" width="25%">
									<span class="red">*</span>申诉理由<br/>
									<font color="red"><B>(限1000字)</B></font>
								</td>
								<td>
									<html:textarea property="sqly" styleId="sssqly" rows="8" style="width:300px" onblur="checkLen(this,1000)"></html:textarea>
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
										<button type="button"  name="保存" onclick="ssjgsave();">
											保 存
										</button>
										<button type="button"  name="取消" onclick="closeWindown();return false;">
											取 消
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
			</div>
			
			<!-- 处分解除 -->
			<div id="jcssDiv" style="display: none">
				<div class="open_win01">
					<table align="center" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>
									处分解除申请
									</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr id="">
								<td align="right" width="25%">
									<font color="red">*</font>申请理由：<br/>
								<font color="red"><B>(限1000字)</B></font>
								</td>
								<td>
									<html:textarea property="jcsqly" styleId="jcsqly" rows="11" style="width:280px" onblur="checkLen(this,1000)"></html:textarea>
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
										<button type="button"  name="保存" onclick="jcjgsave()">
											保 存
										</button>
										<button type="button"  name="取消" onclick="closeWindown();return false;">
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
