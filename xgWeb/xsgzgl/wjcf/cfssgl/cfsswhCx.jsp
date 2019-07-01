<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type='text/javascript' src="js/uicomm.js"></script>
		<script type='text/javascript' src="js/String.js"></script>
		
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript">
		//查询方法
		function searchRs(){
			
			var url = "wjcfCfssgl_cfsswh.do?method=cfsswhCxSj";

			var ie = "ie";

			var otherValue = [ie];

			searchRsByAjax(url,otherValue);
			
			setTimeout("setDivHeight()","3000")
		}
		
		//查看
		function wjcfssglCk(){

			var objs = jQuery("input[type=checkbox][name=checkVal]:checked");
			var pkValue="";
			if(objs.length!=1){
				alertError("请选择一条记录！");
				return false;
			}else{
				pkValue+=objs[0].value;
			}
			var url = 'wjcfCfssgl_cfsswh.do?method=cfsswhCk&pkValue='+pkValue;
			//showTopWin(url,800,520);
			showDialog("", 800,500, url);
		}
		
		//申诉
		function cfss(pkValue){

			var url="wjcfCfssgl_cfsswh.do?method=cfsswhZj&cfid="+pkValue;
			showDialog("", 450,320, url);
		}

		//申诉修改
		function cfssxg(pkValue){
			var url="wjcfCfssgl_cfsswh.do?method=cfsswhXg&cfid="+pkValue;
			showDialog("", 450,320, url);
		}

		function save(){
			var ssfj=jQuery("#ssfj").val();
			var jQssfjmc=jQuery("#ssfjmc");
			var ssfjmc="";
			if(ssfj!=""){
				ssfjmc=ssfj.substring((ssfj.lastIndexOf("\\")+1),ssfj.length);
				if (ssfjmc != null && ssfjmc != "") {
					var hz = ssfjmc.substr(ssfjmc.lastIndexOf(".")+1,ssfjmc.length);
					if (hz!='doc'&&hz!='rar'&&hz!='pdf'){
						alertError("上传文件类型只能为：doc,rar,pdf");
						return false;
					}
				}
				if(ssfjmc.length > 50){
					alertError("文件名过长,请修改文件名后再上传");
					return false;
				}
				jQssfjmc.val(ssfj);
			}
			
			if (jQuery("#sqly").val()=="") {
				alertInfo("请填写申诉理由!");
				return false;
		 	}
			var url="wjcfCfssgl_cfsswh.do?method=cfsswhZj&doType=save";
			refreshForm(url);
		}
		
		//撤销 
		function cfssSc(pkValue){
			var url = 'wjcfCfssgl_cfsswh.do?method=cfssglSc&pkValue='+pkValue;
			document.forms[0].action = url;
			document.forms[0].submit();
		}

		//初始化页面
		function setPage(){
			searchRs();
		}
		//导出数据
		function expData(){
			var url="wjcfCfssgl_cfsswh.do?method=expCfss";
			document.forms[0].action = url;
			document.forms[0].target = "_blank";
			document.forms[0].submit();
			document.forms[0].target = "_self";
		}

		function cfsswhExportConfig() {
			//DCCLBH导出功能编号,执行导出函数 
			//showOpenWindow('configureExportData.do?method=choiceExportFields&tableName='+tableName,1000,600);
			customExport("wjcfCfssgl_cfsswh.do", cfsswhExportData);
			}
			
		
			
		// 导出方法
		function cfsswhExportData() {
			setSearchTj();//设置高级查询条件
			var url = "wjcfCfssgl_cfsswh.do?method=cfsswhExportData&dcclbh=" + "wjcfCfssgl_cfsswh.do";//dcclbh,导出功能编号
			url = addSuperSearchParams(url);//设置高级查询参数
			jQuery("form").eq(0).attr("action", url);
			jQuery("form").eq(0).submit();
		}
		
		</script>
	</head>
	<body onload="setPage();">
		<html:form action="/wjcfCfssgl_cfsswh.do?method=cfsswhCx" method="post" enctype='multipart/form-data'>
			<div class="tab_cur" >
				<p class="location">
					<em>您的当前位置:</em><a>${title }</a>
				</p>
			</div>
			
			<!-- 提示信息 end-->	
			<!-- 模块类型 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<html:hidden property="cfid" styleId="cfId"/>	
			<input type="hidden" name="message" id="message" value="${message }">	
			<input type="hidden" name="ssfjmc" id="ssfjmc" value="" />
			<div class="toolbox" id="dgncz">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>						
						<li><a href="#" onclick="wjcfssglCk();return false;" class="btn_ck"> 查看 </a></li>
						<logic:equal value="yes" name="writeAble">
						<li><a href="#" class="btn_dc" onclick="cfsswhExportConfig();return false;">导出</a></li>
						</logic:equal>
						<%--<li><a href="#" class="btn_dc" onclick="configureExportData();return false;">导出数据</a></li>
					--%></ul>
				</div>
				<!-- 过滤条件 -->
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
			<div class="formbox" id="cxjg">
				<h3 class="datetitle_01">
					<span>
						查询结果&nbsp;&nbsp;<font color="red">已经审核过的申诉不可再操作。</font>
					</span>
				</h3>
				<div id="div_rs" 
					style="width:100%;height:380px;overflow-x:auto;overflow-y:hidden;">
				</div>
				
				<!--分页显示-->
				<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=wjcfCfssglForm"></jsp:include>
				<!--分页显示-->
				
			</div>
			
			<input type="hidden" name="lx" id="lx" />
		<div id="tmpdiv1" style="display: none">
				<div class="open_win01">
					<table align="center" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<font color="red">文件名不能超过50个汉字</font>
								</th>
							</tr>
						</thead>
						<tbody>
						<tr>
							<th style="width:30%">
								证明材料或附件:
								<br/>(限doc,rar,pdf格式)
							</th>
							<td style="width:70%">
							<input type="file" name="ssfj" id="ssfj"/>
							</td>
						</tr>
						<tr>
							<th style="width:30%">
								<font color="red">*</font>申诉理由:<br/>
								<font color="red">(限1000字)</font>
							</th>
							<td  style="width:70%">
								<textarea rows="5" cols="" onblur="checkLen(this,1000)" name="sqly" id="sqly" style="width:250px;height: 90%; margin: 1px;"></textarea>
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
										<button type="button"  onclick="save();return false;">
											保 存
										</button>
										
										<button type="button"  onclick="closeWindown();">
											关 闭
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
