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
		<script language="javascript" src="xsgzgl/znxgl/znxgl/js/znxgl.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<link rel="stylesheet" href="<%= stylePath%>/css/public.css" type="text/css" media="all" />
		<link rel="stylesheet" href="<%=stylePath %>/css/module.css" type="text/css" media="all" />
		<script language="javascript">
			jQuery(function(){
		    })
		    //选择接收人
		    function seljsr(){
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
                showDialog(title, 800, 550, url);
                return false;
			}
			//删除接收人
			function deljsr(){
				
			}
		</script>
		</head>
	<body>
		<html:form action="/znxgl"  method="post" styleId="ZnxglForm">
		<input type="hidden" name="xjbh" id="xjbh" value="${xjbh}"/>
		<div class="tab" style="width:100%;overflow-x:hidden;overflow-y:auto;">
			<table width="100%" border="0" class="formlist">
				<tbody>
					<tr>
						<th width="25%">
							主题类别
						</th>
						<td colspan="3">
							${ztlb}
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
							<button class="btn_01" id="delsjr" onclick="deljsr();" type="button">删除</button> -->
						</td>
					</tr>
					<tr>
						<th width="25%"><font color="red">*</font>信件主题</th>
						<td colspan="3">
							<html:text property="xjzt" maxlength="50" styleId="xjzt" value="${xjzt}"
									style="width:650px;"></html:text>
						</td>
					</tr>
					<tr>
						<th width="25%">
							<font color="red">*</font>发送内容
						</th>
						<td colspan="3">
							<textarea id="editorid" name="editorid" style="width:700px;height:280px;">
							  ${fsnr}
							</textarea>
					    </td>
					</tr>
				</tbody>
			</table>
			<%-- 接收人存储隐藏域--%>
			<div id="ycyz" style="display:none" >
				<div style="display:none" id="teagroup"></div>
			</div>
			<div style="display:none" id="deldiv">
				<div id="showtea"></div>
			</div> 
		</div>
		<table width="100%" border="0" class="formlist" style=" margin-bottom: 0;">
				<tfoot>
					<tr>
						<td colspan="4">
							<div class="btn">
								<button type="button" name="发送 " onclick="saveXjForm();return false;">
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