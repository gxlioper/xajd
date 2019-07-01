<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script type="text/javascript">
		function isNumber(obj){
			var s = obj.value;
			if(s==""){
				alertInfo('分值不能为空',function(t){
					if(t=="ok"){
						obj.focus();
						return false;
					}
				});
			}else{
				var p = /^\d+$/;
				if(p.test(s)){
				}else{
					alertInfo('分值必须输入正整数',function(t){
						if(t=="ok"){
							obj.focus();
							return false;
						}
					});
					
				}
			}
		}
		
		function checkFz(){
			for(var i=0;i<6;i++){
				if($("jcfz_"+i) && $("zgfz_"+i)){
					jcfz=eval($("jcfz_"+i).value);
					zgfz=eval($("zgfz_"+i).value);
					title=$("jcfz_"+i).title;
					if(jcfz>zgfz){
						alertInfo(title+"分,基础分不能高于最高分!",function(t){
							if(t=="ok"){
								$("jcfz_"+i).focus();
							}
						});
						return false;
					}
				}
			}
			
			refreshForm('pjpyszyqwh.do?method=pointSetSave');
		}
		</script>
	</head>
	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>综合素质养成课 - 参数设置 - 分数设置</a>
			</p>
			<p class="help">
				<a href="#" onclick="return false;" onmousedown ="showHelpDiv();">使用帮助</a>
			</p>
		</div>
		
		<!-- 提示信息 end-->
		<div class="prompt" id="div_help" style="display: none">
			<h3>
				<span>提示：</span>
			</h3>
			<p>
				鼠标移动到右上角<font color="blue">帮助中心</font>，可查看本模块的相关说明。</br>
				<span id="div_help" style="display: none">
				1.以下所有操作都是基于<font color="blue">${xn }</font>学年，<font color="blue">${xqmc }</font>学期 展开的。</br>
				</span>
			</p>
			<a class="close" title="隐藏"  onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- 提示信息 end-->

		<html:form action="/pjpyszyqwh">
			<table width="100%" border="0" class="formlist">
				<thead>
					<tr>
						<th>
							<span>分数设置</span>
						</th>
					</tr>
				</thead>
				<tfoot>
					<tr>
						<td>
							<div class="btn">
								<button type="button" id="buttonSave"
									onclick="checkFz()">
									保 存
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
				<tbody>
					<tr>
						<td>
						<div class="formbox">
							<div class="con_overlfow">
								<table summary="" class="dateline tablenowrap" 
									width="100%">
									<thead>
										<tr>
											<td>
												子模块名称
											</td>
											<td>
												子模块基础分
											</td>
											<td>
												子模块最高分
											</td>
										</tr>
									</thead>
									<tbody>

										<logic:iterate id="s" name="rs"  indexId="index">
											<tr>
												<td>
													<input type="hidden" id="mkdm" name="mkdm"
														value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>" />
													<logic:iterate id="v" name="s" offset="1" length="1">
														<bean:write name="v" />
													</logic:iterate>
												</td>
												<logic:iterate id="v" name="s" offset="2" length="1">
													<td>
														<input type="text" name="jcfz"  id="jcfz_${index}" title="<logic:iterate id="w" name="s" offset="1" length="1">${w}</logic:iterate>"
															value="<bean:write name="v"/>" onblur="isNumber(this)"
															maxlength="2" />
													</td>
												</logic:iterate>
												<logic:iterate id="v" name="s" offset="3" length="1">
													<td>
														<input type="text" name="zgfz"  id="zgfz_${index}"
															value="<bean:write name="v"/>" onblur="isNumber(this)"
															maxlength="2" />
													</td>
												</logic:iterate>
											</tr>
										</logic:iterate>
									</tbody>
								</table>
							</div>
							</div>
						</td>
					</tr>
			</table>
			<logic:present name="updated">
				<logic:equal value="yes" name="updated">
					<script>
						alertInfo('操作成功！');
					</script>
				</logic:equal>
				<logic:equal value="no" name="updated">
					<script>
						alertInfo('操作失败！');
					</script>
				</logic:equal>
			</logic:present>
		</html:form>
	</body>
</html>
