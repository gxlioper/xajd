<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/Basic.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="comm/editor/kindeditor.js"></script>
		<script language="javascript" src="comm/editor/zh_CN.js"></script>
		<script language="javascript" src="comm/editor/editor.js"></script>
		<script language="javascript" src="xsgzgl/znxgl/wdznx/js/wdznx.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<link rel="stylesheet" href="<%= stylePath%>/css/public.css" type="text/css" media="all" />
		<link rel="stylesheet" href="<%=stylePath %>/css/module.css" type="text/css" media="all" />
		<script language="javascript">
			jQuery(function(){
				/*
				jQuery("input[name='mxyh']:radio").change(function(){
					var mxyh = jQuery("input[name='mxyh']:checked").val();
					if(mxyh == 'admin'){
						document.getElementById("selsjr").disabled=true;
						var jsr = document.getElementById("jsrxm").value;
						if(jsr == null || jsr == '' ){
							document.getElementById("jsrxm").value="系统管理员;"
							jQuery("#admingroup").append("<input name='jsrbh' id='admin_01' value='系统管理员'>");
						}else{
							var jsrsz = jsr.split(";")
							//是否存在系统管理员标志
							var flag = false;
							for(var i=0;i<jsrsz.length;i++){
								if(jsrsz[i] == '系统管理员'){
									flag = true;
								}
							}
							//如果存在，赋予原来的值
							if(flag == true){
								document.getElementById("jsrxm").value = jsr;
							}else{
								//不存在进行拼接
								document.getElementById("jsrxm").value = jsr+"系统管理员;";
								jQuery("#admingroup").append("<input name='jsrbh' id='admin_01' value='系统管理员'>");
							}
						}
						
					}else if(mxyh == 'tea'){
						document.getElementById("selsjr").disabled=false;
					}else if(mxyh == 'stu'){
						document.getElementById("selsjr").disabled=false;
					}
				})*/
		    })
		    //选择接收人
		    function seljsr(){
			    var mxyh =  jQuery("input[name='mxyh']:checked").val();
			    if(mxyh == null || mxyh == ''){
				    showAlert("请先选择面向用户！");
				    return false;
                }
                var url = '';
                var title = '';
                if(mxyh == 'tea'){
                    var teaArr = new Array();
                    var flag = jQuery("#teagroup input").length;
                    if(flag != 0){
                    	jQuery.each(jQuery("#teagroup input"),function(i,n){
                			var zgh = jQuery(n).val();
                			teaArr.push(zgh);
                	    });
                    }
                     url = "wdznx.do?method=getTea&teaArr="+teaArr;
                     title = "教师";
                }else if(mxyh == 'stu'){
                	 var xhArr = new Array();
                     var flag = jQuery("#stugroup input").length;
                     if(flag != 0){
                     	jQuery.each(jQuery("#stugroup > input"),function(i,n){
                 			var xh = jQuery(n).val();
                 			xhArr.push(xh);
                 	    });
                     }
                      url = "wdznx.do?method=getStu&xhArr="+xhArr;
                      title = "学生";
                }
                showDialog(title, 800, 550, url);
                return false;
			}
			//删除接收人
			function deljsr(){
				
			}
		</script>
		</head>
	<body>
		<html:form action="/wdznx"  method="post" styleId="WdznxForm">
		<input type="hidden" name="xhArr" id="xhArr" value="${xhArr}"/>
		<div class="tab" style="width:100%;height:460px;overflow-x:hidden;overflow-y:auto;">
			<table width="100%" border="0" class="formlist">
				<tbody>
					<tr>
						<th width="25%">
							<font color="red">*</font>面向用户
						</th>
						<td colspan="3">
						<!-- 
						<input type="radio" name="mxyh" value="admin" id="admin"/><label for="admin">管理员</label>
						&nbsp;&nbsp;&nbsp; -->
						<input type="radio" name="mxyh" value="tea" checked="checked" id="tea"/><label for="tea">教师</label>
						&nbsp;&nbsp;&nbsp;
						<input type="radio" name="mxyh" value="stu" id="stu"/><label for="stu">学生</label>
						</td>
					</tr>
					<tr>
						<th width="25%">
							<font color="red">*</font>收件人
						</th>
						<td colspan="3">
						    <%--10位接收人之后在添加以...代替--%>
							<input type="text" name="jsrxm" id="jsrxm" readonly="readonly" style="width:500px;" value="" />
							<button class="btn_01" id="selsjr" onclick="seljsr();" type="button">选择</button>
							<!--  
							<button class="btn_01" id="delsjr" onclick="deljsr();" type="button">删除</button>-->
						</td>
					</tr>
					<tr>
						<th width="25%"><font color="red">*</font>信件主题</th>
						<td colspan="3">
							<html:text property="xjzt" maxlength="50" styleId="xjzt"
									style="width:650px;"></html:text>
						</td>
					</tr>
					<tr>
						<th width="25%">
							<font color="red">*</font>发送内容<br/><font color="red">(限1000之内)</font>
						</th>
						<td colspan="3">
							<textarea id="editorid" name="editorid" style="width:700px;height:280px;">
							</textarea>
					    </td>
					</tr>
				</tbody>
			</table>
			<%-- 接收人存储隐藏域 --%>
			<div id="ycyz" style="display:none" >
				<div style="display:none" id="admingroup"></div>
				<div style="display:none" id="teagroup"></div>
				<div style="display:none" id="stugroup"></div>
			</div>
			<div style="display:none" id="deldiv">
				<div id="showadmin"></div>
				<div id="showtea"></div>
				<div id="showdel"></div>
			</div>
		</div>
		<table width="100%" border="0" class="formlist" style=" margin-bottom: 0;">
				<tfoot>
					<tr>
						<td colspan="4">
							<div class="btn">
								<button type="button" name="发送  " onclick="saveTeaXXForm();return false;">
									发送 
								</button>
								<button type="button" name="关闭" onclick="iFClose();">
									关 闭
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
		</table>
		</html:form>
	</body>
</html>