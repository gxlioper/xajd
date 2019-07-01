<%@ page language="java" pageEncoding="GBK"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/rcsw/rcswFunction.js"></script>
		<script>
			jQuery(function(){
				var xmdm = jQuery('#xmdm').val();
				jQuery.ajax({
					type:'post',
					url:'pjpy_ty_ajax.do?method=getTzfw&xmdm='+xmdm,
					dataType:'json',
					success:function(data){
						if (null != data){
							jQuery.each(data,function(i,n){
								jQuery('input[value="'+n+'"]').attr('checked',true);
							});
						}
					}
				})
			});
			
			function saveCheck(){
				var tzxmdms=document.getElementsByName("tzxmdm");
				if(tzxmdms&&tzxmdms.length){
					for(var i=0;i<tzxmdms.length;i++){
						if(tzxmdms[i].checked){
							return true;
						}
					}
				}
				alert("奖学金或荣誉称号至少选择其中一项！");
				return false;
			}
			function confim(){
				if(!saveCheck()){
                   return false;
				}else{
				   confirmInfo("项目调整范围设置已完成,此项设置不可返回,如果需要修改请到项目调整范围设置中直接修改,是否要继续下一步的设置?",mbDownLoad);
				}
				
			}
			function mbDownLoad(tag){
				if(tag=='ok'){
					saveUpdate('pjpy_ty_tzfw.do?method=tzfwszFlow&doType=save','');
				}
			}

			//显示帮助层信息
			function showHelpDiv(){

				if($("div_help")){
					if($("div_help").style.display == "none"){
						$("div_help").style.display = "";
					}else{
						$("div_help").style.display = "none";
					}
				}
				
			}
		</script>
	</head>

	<body>
	  <div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>评奖评优-项目设置-项目调整范围设置</a>
				</p>
				
				<p class="help">
					<a href="#" onclick="return false;" onmousedown ="showHelpDiv();">使用帮助</a>
				</p>
	  </div>
	<!-- 流程按钮控制 -->
		 <div class="flow-steps">
			  <ol class="num5">
			    <li class="done current-prev" style = "width:18%"><span class="first">1. 项目兼得设置</span></li>
			    <li class="current" style = "width:18%"><span>2. 项目调整范围设置</span></li>
			    <li class="current-next" style = "width:18%"><span>3. 项目条件设置</span></li>
			    <li class="current-next" style = "width:18%"><span>4. 项目时间设置</span></li>
			    <li class="last" style = "width:18%"><span>5. 项目人数设置</span></li>
			  </ol>
        </div>
        
        <!-- 提示信息 end-->
		<div class="prompt" id="div_help" style="display: none">
			<h3>
				<span>提示：</span>
			</h3>
			<p>
				1.调整范围设置指的是当前项目人数满的时候<font color='blue'>可以调整</font>到下面的奖学金或是荣誉称号
			</p>
			<a class="close" title="隐藏"  onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- 提示信息 end-->		
        
		<html:form action="/pjpy_ty_tzfw" method="post">
			<html:hidden property="xmdm" styleId="xmdm"/>
		
			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="2">
								<div class="bz">
									"
									<span class="red">*</span>"为必填项
								</div>
								<div class="btn">
									<!-- 
									<button type="button" onclick="refresh('pjpy_ty_jdsz.do?method=jdszFlow&doType=')">
										上一步
									</button> 
									-->
									<button type="button" type ="reset" onclick="refresh('pjpy_ty_tjsz.do?method=tjszFlowAdd&doType=')">
										   跳过
									</button>
									<button type="button" id="buttonSave" onclick="confim()">
										保存
									</button>
									<button type="button" id="buttonSave" onclick="winClose();">
										关闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
						<tr>
							<th>
								项目名称
							</th>
							<td>
								<html:select property="xmdm" style="width:200px" disabled="true">
									<html:option value=""></html:option>
									<html:options collection="xmList" property="xmdm"
										labelProperty="xmmc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>奖学金</th>
							<td>
								<logic:iterate id="x" name="xmList">
									<logic:notEqual value="02" name="x" property="xmlx">
										<logic:notEqual value="${pjpyTzfwszForm.xmdm }" name="x" property="xmdm">
											<input type="checkbox" name="tzxmdm" value="${x.xmdm }"/> ${x.xmmc }
										</logic:notEqual>									
									</logic:notEqual>
								</logic:iterate>
							</td>
						</tr>
						<tr>
							<th>荣誉称号</th>
							<td>
								<logic:iterate id="x" name="xmList">
									<logic:notEqual value="01" name="x" property="xmlx">
										<logic:notEqual value="${pjpyTzfwszForm.xmdm }" name="x" property="xmdm">
											<input type="checkbox" name="tzxmdm" value="${x.xmdm }"/> ${x.xmmc }
										</logic:notEqual>									
									</logic:notEqual>
								</logic:iterate>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</html:form>
	</body>
</html>
