<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- 头文件 -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<link rel="stylesheet" type="text/css" href="js/jquery/themes/default/easyui.css" />
	<link rel="stylesheet" type="text/css" href="js/jquery/themes/icon.css" />
	<script language="javascript" src="js/comm/commFunction.js"></script>
	<script language="javascript" defer="defer">
	
	//初始化列表
	function onShow(){
		//初始化部门
		defaultBmOption();
		//初始化项目
		setXmList();
	}
	
	//加载项目列表
	function setXmList(){
		jQuery(function(){	
			var xmdm = jQuery('#xmdm');
			
			var url = "pjpyXmsb.do?method=setXmOption";
				
			xmdm.combobox({
				valueField:'dm',
				textField:'mc',
				url:url,
				panelHeight:"auto"
			});
		});
	}
	
	//提交上报范围
	function sbfwCheck(){
		var xmdm = jQuery('#xmdm').combobox('getValue');
		var bjdm = jQuery('#bjdm').combobox('getValue');
		
		//判断项目是否为空
		if(xmdm == ""){
			alertError("项目名称不能为空,请确认");
			return false;
		}
	
		//判断班级是否为空
		if(bjdm == ""){
			alertError("班级不能为空,请确认");
			return false;
		}
			
		//判断项目是否存在
		var existsMessage = checkIsExists();
		if(existsMessage != ""){
			alertError(existsMessage);
			return false;
		}else{
			confirmInfo('请确认你要上报的部门及项目',sbfwSubmit);
		}
	}
	
	//判断是否存在
	function checkIsExists(){
	
		var existsMessage = "";
		
		if(existsMessage == ""){
			//检测项目
			checkXm();
			existsMessage = $("em_xmdm").innerHTML;
		}
			
		if(existsMessage == ""){
			//检测班级
			checkBj();
			existsMessage = $('em_bjdm').innerHTML;
		}

		return existsMessage;
	}
	
	//检测评奖项目
	function checkXm(){
		var url = "xsxx_ajax.do?method=checkIsExists";
		var xmdm = jQuery('#xmdm').combobox('getValue');
		var pk = ['xmdm||pjxn||pjxq||pjnd||sfqy||sqfs','xmmc||pjxn||pjxq||pjnd||sfqy||sqfs']
		var pkValue = escape(xmdm+$("pjxn").value+$("pjxq").value+$("pjnd").value+"是"+"lssb");
		var errMsg = escape("输入项目不存在！");
		
		var message = "";
		
		//参数
	 	var parameter = {
	 		"pk":pk.join("!!@@!!"),
	 		"pkValue":pkValue,
	 		"tableName":"xg_pjpy_pjxmwhb",
			"errMsg":errMsg
		};

		jQuery.ajax({
			type:'post',
			url:url,
			data:parameter,
			async: false,
			success:function(result){
				showErrMessage(result,"xmdm");
			}
		});
	}
		
	//检测班级
	function checkBj(){
		var url = "xsxx_ajax.do?method=checkIsExists";
		var bjdm = jQuery('#bjdm').combobox('getValue');
		var pk = ['bjdm','bjmc']
		var pkValue = escape(bjdm);
		var errMsg = escape("输入班级不存在！");
		
		var message = "";
		
		//参数
	 	var parameter = {
	 		"pk":pk.join("!!@@!!"),
	 		"pkValue":pkValue,
	 		"tableName":"view_njxyzybj",
			"errMsg":errMsg
		};
			
		jQuery.ajax({
			type:'post',
			url:url,
			data:parameter,
			async: false,
			success:function(result){
				showErrMessage(result,"bjdm");
			}
		});
	}
	
	//提交上报范围
	function sbfwSubmit(tag){
	
		if(tag == "ok"){
			var xmdm = jQuery('#xmdm').combobox('getValue');
			var bjdm = jQuery('#bjdm').combobox('getValue');
			
			window.dialogArguments.document.getElementById("xmdm").value = xmdm;
			window.dialogArguments.document.getElementById("bjdm").value = bjdm;
			
			if(window.dialogArguments.document.getElementById("forward")){
				window.dialogArguments.document.getElementById("forward").click();
			}else if(window.dialogArguments.document.getElementById("search_go")){
				window.dialogArguments.document.getElementById("search_go").click();
			}
			
			window.close();
		}
	}
		
		jQuery(function(){
			onShow();
		})
	</script>
</head>

<body  >	
	
	<!-- 提示信息 end-->
	<div class="prompt">
		<h3>
			<span>评奖周期：</span>
		</h3>
		<p>
			评奖学年(${pjxn })&nbsp;&nbsp;
			评奖学期(${pjxqmc })&nbsp;&nbsp;
			评奖年度(${pjnd })&nbsp;&nbsp;		
		</p>
	</div>
	<!-- 提示信息 end-->	
		
    <html:form action="/pjpyXmsb">
    
		<!-- 隐藏域 -->
		<input type="hidden" id="pjxn" value="${pjxn }"/>
		<input type="hidden" id="pjxq" value="${pjxq }"/>
		<input type="hidden" id="pjnd" value="${pjnd }"/>
		<!-- 隐藏域 end-->
		
		<div class="open_win">
			<table class="formlist" border="0" align="center" style="width: 100%;" >	
				<thead>
					<tr>
						<td colspan="2"><span>奖项选择</span></td>
					</tr>
				</thead>
				<tbody>	
					<tr>
						<th width="30%"><font color="red">*</font>项目名称</th>
						<td>
							<html:select property="xmdm" styleId="xmdm" style="width: 200px" title="可录入">
							</html:select>
							<!-- 出错信息 -->
							<span id="sp_xmdm" class="msg_prompt2" style="display: none">
				              	<em class="prompcon" id="em_xmdm">
				                	
				                </em>
			                </span>
			                <!-- 出错信息 end-->
						</td>
					</tr>
				</tbody>
				
				<thead>
					<tr>
						<td colspan="2"><span>上报部门选择</span></td>
					</tr>
				</thead>
				<tbody>	
					<tr>
						<th>年级</th>
						<td>
							<html:select property="nj" styleId="nj" style="width: 200px" title="可录入">
							</html:select>
						</td>
					</tr>
					<tr>
						<th><bean:message key="lable.xsgzyxpzxy" /></th>
						<td>
							<html:select property="xydm" styleId="xydm" style="width: 200px" title="可录入">
							</html:select>
						</td>
					</tr>
					<tr>
						<th>专业</th>
						<td>
							<html:select property="zydm" styleId="zydm" style="width: 200px" title="可录入">
							</html:select>
						</td>
					</tr>
					<tr>
						<th><font color="red">*</font>班级</th>
						<td>
							<html:select property="bjdm" styleId="bjdm" style="width: 200px" title="可录入">
							</html:select>
							<!-- 出错信息 -->
							<span id="sp_bjdm" class="msg_prompt2" style="display: none">
				              	<em class="prompcon" id="em_bjdm">
				                	
				                </em>
			                </span>
			                <!-- 出错信息 end-->
						</td>
					</tr>
				</tbody>
				
				<tfoot>
			    	<tr>
				        <td colspan="2">
				          <div class="btn">
				          	<button type="button" id="btn_save" onclick="sbfwCheck();">
								确 定
							</button>
							
				            <button type="button" id="btn_clo" onclick="Close();return false;">
								关 闭
							</button>
				          </div>
				        </td>
			    	</tr>
			    </tfoot>
			</table>
		</div>
		<!--备注-->
		<div class="readme">
			<h2>模块描述</h2>
<%--			<h3>单个标题的表单</h3>--%>
			<div class="readcon">
				<ul>
					<li>项目仅列出申请方式为老师上报，并且在申请时间范围内的项目。</li>
					<li>下拉框可录入，可下拉。</li>
					<li>若下拉列表中没有您所需要的部门，请先选择其上级部门，以缩小范围。</li>
					<li>若下拉列表中没有您所需要的部门，可以直接录入，系统会帮助您联想。</li>
					<li>项目名称，班级不可为空。</li>
				</ul>  
			</div>
		</div>	
		<!-- 提示信息 -->
		<%@ include file="/comm/other/tsxx.jsp"%>
    </html:form>
  </body>
</html>
