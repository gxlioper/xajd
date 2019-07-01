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
		<script language="javascript" src="xsgzgl/xtwh/general/mobilemessage/js/mobilemessage.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<link rel="stylesheet" href="<%= stylePath%>/css/public.css" type="text/css" media="all" />
		<link rel="stylesheet" href="<%=stylePath %>/css/module.css" type="text/css" media="all" />
		<script language="javascript">
		jQuery(function() {
			jQuery("#sxrTr").hide();
			
			jQuery(":radio").click(function(){
				  var radioVal=jQuery(this).val();
				  if("tea"==radioVal||"stu"==radioVal){
					  jQuery("#jsrxm").val("");
						jQuery("#sxrTr").show();
					  }else{
						  jQuery("#jsrxm").val(radioVal);
						  jQuery("#sxrTr").hide();
						  }
				  });
	
		});
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
                    	jQuery.each(jQuery("#teagroup > input[name=jsrbh]"),function(i,n){
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
                     	jQuery.each(jQuery("#stugroup > input[name=jsrbh]"),function(i,n){
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
				jQuery("#jsrxm").val("")
				jQuery("#teagroup").empty();
				jQuery("#stugroup").empty();
			}

		</script>
		</head>
	<body>
		<html:form action="/xtwh_mobileMsg"  method="post" styleId="mobileMessageForm">
		<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
			<table width="100%" border="0" class="formlist">
				<tbody>
					<tr>
						<th width="15%">
							<font color="red">*</font>接收对象
						</th>
						<td colspan="3">
						<logic:equal value="xx" name="userStatus">
						<input type="radio" name="mxyh" value="全校师生" checked="checked" id="teaAndstu"/><label for="tea">全校师生</label>
						<input type="radio" name="mxyh" value="全校教师" id="alltea"/><label for="tea">全校教师</label>
						<input type="radio" name="mxyh" value="tea" id="tea"/><label for="tea">指定教师</label>
						<input type="radio" name="mxyh" value="全校学生" id="allstu"/><label for="stu">全校学生</label>
						</logic:equal>
						<input type="radio" name="mxyh" value="stu" id="stu"/><label for="stu">指定学生</label>
						</td>
					</tr>
					<tr id="sxrTr">
						<th width="15%">
							<font color="red">*</font>收件人
						</th>
						<td colspan="3">
						    <%--10位接收人之后在添加以...代替--%>
							<input type="text" name="jsrxm" id="jsrxm" readonly="readonly" style="width:500px;" value="全校师生" />
							<button class="btn_01" id="selsjr" onclick="seljsr();" type="button">选择</button>
							
							<button class="btn_01" id="delsjr" onclick="deljsr();" type="button">重置</button>
						</td>
					</tr>
					</div>
					<tr>
						<th width="15%">
							<font color="red">*</font>发送内容<br/><font color="red">(限200字之内)</font>
						</th>
						<td colspan="3">
							<html:textarea property="fsnr" styleId="fsnr" cols="50" rows="8"
									style="width:100%" onblur="chLeng(this,200)"></html:textarea>
					    </td>
					</tr>
				</tbody>
			</table>
			<%-- 接收人存储隐藏域 --%>
			<div id="ycyz" style="display:none" >
				<div style="display:none" id="teagroup"></div>
				<div style="display:none" id="stugroup"></div>
			</div>
			<div style="display:none" id="deldiv">
				<div id="showadmin"></div>
				<div id="showtea"></div>
				<div id="showdel"></div>
			</div>
		</div>
		<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
				<tfoot>
					<tr>
						<td colspan="4">
							<div class="btn">
								<button type="button" name="发送  " onclick="saveForm();return false;">
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