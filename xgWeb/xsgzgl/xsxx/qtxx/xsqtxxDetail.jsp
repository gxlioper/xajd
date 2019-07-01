<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="js/String.js"></script>
		<script language="javascript" src="js/check.js"></script>

		<script type="text/javascript">

		function tipsAndSave(url){
			BatAlert.showTips("正在保存，请稍后！");
			saveData(url,'');
		}
		
		function autoFillTeaInfo(cod){
			if(cod == 13){
				var url = $('url').value;
				document.forms[0].action = url;
				document.forms[0].submit();
			}
		}
	
		
		//保存学生其他信息
		function save(){
			
			var xh=jQuery("#xh").val();
			
			var blog= true;
			if(xh==""){
				alertInfo("学号为必填字段不能为空！",function(tag){
				
				});
				
				blog= false;
				
			}
			
			//保存前提示信息
			if(blog){
				
				confirmInfo("该操作将会保存已修改信息，是否继续？",function(tag){
					//判断是否选择“确定”按钮
					if(tag=="ok"){
					
						//创建一个json对象
						var parameter={};
						
						//指定获取的控件类型，进行循环
						var hid_obj=jQuery("input,select").each(function(){
							
							//获取表单控件name
							var name=jQuery(this).attr("name");
							//构建json对象
							parameter[name]=escape(jQuery(this).val());
						});
						
						//保存URL
						var url = "xsxx_qtxx_ajax.do?method=save";
						
						//------------AJAX保存 begin -------------
						jQuery.ajaxSetup({async:false});
							jQuery.post(url,
							parameter,
							function(result){
								
								$("divWaiting").style.display="none";
								$("divDisable").style.display="none";
								alertInfo(result);
							}
						);
						
						jQuery.ajaxSetup({async:true});
						//------------AJAX保存 end -------------
						
						
					}else {
					
						return false;
					}
				});
			}
		}
		
		//保存学生其他信息
		function update(){
			
			//保存前提示信息
			confirmInfo("该操作将会保存已修改信息，是否继续？",function(tag){
				//判断是否选择“确定”按钮
				if(tag=="ok"){
				
					//创建一个json对象
					var parameter={};
					
					//指定获取的控件类型，进行循环
					var hid_obj=jQuery("input,select").each(function(){
						
						//获取表单控件name
						var name=jQuery(this).attr("name");
						//构建json对象
						parameter[name]=escape(jQuery(this).val());
					});
					
					//保存URL
					var url = "xsxx_qtxx_ajax.do?method=update";
					
					//------------AJAX保存 begin -------------
					jQuery.ajaxSetup({async:false});
						jQuery.post(url,
						parameter,
						function(result){
							
							$("divWaiting").style.display="none";
							$("divDisable").style.display="none";
							alertInfo(result);
						}
					);
					
					jQuery.ajaxSetup({async:true});
					//------------AJAX保存 end -------------
					
					
				}else {
				
					return false;
				}
			});
		}
	</script>
	</head>
	<body>
		<html:form action="/xsxx_qtxx" method="post">
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>${title }</a>
				</p>
			</div>
			<input type="hidden" name="xmdm" value="${xmdm}" />
			<input type="hidden" name="url" id="url"
				value="/xgxt/xsxx_qtxx.do?method=xsqtxxDetail&doType=add" />
			<input type="hidden" name="tableName" id="tableName"
				value="view_xsjbxx" />
			<input type="hidden" name="va" id="va" value="${xmdm}" />
			<div class="tab"
				style="width:100%;height:450px;overflow-x:hidden;overflow-y:auto;">
				<table class="formlist" width="93%">

					<thead onclick="hiddenMk('mk_xsxx')">
						<tr>
							<th colspan="4" style="cursor:hand">
								<span>学生基本信息</span>
							</th>
						</tr>
					</thead>
					<tbody id="mk_xsxx">
						<tr>
							<logic:notEqual name="doType" value="add">
								<th width="20%">
									<font color="red">*</font>学号
								</th>
								<td width="30%">
									<input type="text" name="xh" id="xh" value="${rs.xhv }"
										readonly="readonly" />

								</td>
							</logic:notEqual>
							<logic:equal name="doType" value="add">
								<th width="20%">
									<font style="color: red">*</font>学号
								</th>
								<td width="30%">
									<input type="text" name="xh" id="xh" readonly="readonly"
										value="${rs.xhv }"
										onchange="checkXhExists($('getStuInfo').value);"
										onkeypress="autoFillTeaInfo(event.keyCode)" />
									<button type="button" onclick="sendXx();return false;" class="btn_01" id="buttonFindStu">
										选择
									</button>
								</td>
							</logic:equal>
							<th width="20%">
								姓名
							</th>
							<td width="30%">
								${rs.xm }
							</td>
						</tr>
						<tr>
							<th>
								年级
							</th>
							<td>
								${rs.nj }
							</td>
							<th>
								<bean:message key="lable.xsgzyxpzxy" />
							</th>
							<td>
								${rs.xymc }
							</td>
						</tr>
						<tr>
							<th>
								专业
							</th>
							<td>
								${rs.zymc }
							</td>
							<th>
								班级
							</th>
							<td>
								${rs.bjmc }
							</td>
						</tr>
					</tbody>
					<thead onclick="hiddenMk('mk_lhbx')">
						<tr>
							<th colspan="4" style="cursor:hand">
								<span>联合办学信息</span>
							</th>
						</tr>
					</thead>
					<tbody id="mk_lhbx">
						<tr>
							<th>
								中职院校
							</th>
							<td>
								<html:text name="rs" property="zd1" styleId="zd1" maxlength="50" />
							</td>
							<th>
								成考成绩
							</th>
							<td>
								<html:text name="rs" property="zd2" styleId="zd2" maxlength="50"/>
							</td>
						</tr>
						<tr>
							<th>
								高职院校
							</th>
							<td>
								<html:text name="rs" property="zd3" styleId="zd3" maxlength="50"/>
							</td>
							<th>
								录取成绩
							</th>
							<td>
								<html:text name="rs" property="zd4" styleId="zd4" maxlength="50"/>
							</td>
						</tr>
						<tr>
							<th>
								录取年份
							</th>
							<td>
								<html:text name="rs" property="zd5" styleId="zd5" maxlength="50"/>
							</td>
							<th>
								录取专业
							</th>
							<td>
								<html:text name="rs" property="zd6" styleId="zd6" maxlength="50"/>
							</td>
						</tr>
						<tr>
							<th>
								毕业年份
							</th>
							<td>
								<html:text name="rs" property="zd7" styleId="zd7" maxlength="50"/>
							</td>
							<th>
								录取层次
							</th>
							<td>
								<html:text name="rs" property="zd8" styleId="zd8" maxlength="50"/>
							</td>
						</tr>
						<tr>
							<th>
								中职班级
							</th>
							<td>
								<html:text name="rs" property="zd23" styleId="zd23" maxlength="50"/>
							</td>
							<th>
								中职辅导员
							</th>
							<td>
								<html:text name="rs" property="zd24" styleId="zd24" maxlength="50"/>
							</td>
						</tr>
						<tr>
							<th>
								双跨班级
							</th>
							<td>
								<html:text name="rs" property="zd25" styleId="zd25" maxlength="50"/>
							</td>
							<th>
								双跨辅导员
							</th>
							<td>
								<html:text name="rs" property="zd26" styleId="zd26" maxlength="50"/>
							</td>
						</tr>
						</tbody>
						<thead onclick="hiddenMk('mk_zhxx')">
							<tr>
								<th colspan="4" style="cursor:hand">
									<span>综合信息</span>
								</th>
							</tr>
						</thead>
						<tbody id="mk_zhxx">
						<tr>
							<th>
								二学历
							</th>
							<td>
								<html:select name="rs" property="zd9" styleId="zd9">
									<html:option value=""></html:option>
									<html:option value="是">是</html:option>
									<html:option value="否">否</html:option>
								</html:select>
							</td>
							<th>
								报考年份
							</th>
							<td>
								<html:text name="rs" property="zd10" styleId="zd10" maxlength="50"/>
							</td>
						</tr>
						<tr>
							<th>
								报考学校
							</th>
							<td>
								<html:text name="rs" property="zd11" styleId="zd11" maxlength="50"/>
							</td>
							<th>
								报考专业
							</th>
							<td>
								<html:text name="rs" property="zd12" styleId="zd12" maxlength="50"/>
							</td>
						</tr>
						<tr>
							<th>
								军训
							</th>
							<td>
								<html:select name="rs" property="zd13" styleId="zd13">
									<html:option value=""></html:option>
									<html:option value="是">是</html:option>
									<html:option value="否">否</html:option>
								</html:select>
							</td>
							<th>
								体检
							</th>
							<td>
								<html:select name="rs" property="zd14" styleId="zd14">
									<html:option value=""></html:option>
									<html:option value="是">是</html:option>
									<html:option value="否">否</html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								学生证
							</th>
							<td>
								<html:select name="rs" property="zd15" styleId="zd15">
									<html:option value=""></html:option>
									<html:option value="已交">已交</html:option>
									<html:option value="未交">未交</html:option>
								</html:select>
							</td>
							<th>
								翼机通
							</th>
							<td>
								<html:select name="rs" property="zd16" styleId="zd16">
									<html:option value=""></html:option>
									<html:option value="是">是</html:option>
									<html:option value="否">否</html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								军服发放
							</th>
							<td>
								<html:select name="rs" property="zd17" styleId="zd17">
									<html:option value=""></html:option>
									<html:option value="是">是</html:option>
									<html:option value="否">否</html:option>
								</html:select>
							</td>
							<th>
								军服发放时间
							</th>
							<td>
								 <html:text name="rs"  property="zd18" styleId="zd18" 
								onclick="return showCalendar('zd18','y-mm-dd');" 
								onblur="dateFormatChg(this)" readonly="true" />
								
							</td>
						</tr>
						<tr>
							<th>
								军服票据号
							</th>
							<td>
								<html:text name="rs" property="zd19" styleId="zd19" maxlength="50"/>
							</td>
							<th>
								备品发放
							</th>
							<td>
								<html:select name="rs" property="zd20" styleId="zd20">
									<html:option value=""></html:option>
									<html:option value="是">是</html:option>
									<html:option value="否">否</html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								备品发放时间
							</th>
							<td>
								 <html:text name="rs"  property="zd21" styleId="zd21" 
								onclick="return showCalendar('zd21','y-mm-dd');" 
								onblur="dateFormatChg(this)" readonly="true" />
							</td>
							<th>
								备品票据号
							</th>
							<td>
								<html:text name="rs" property="zd22" styleId="zd22" maxlength="50"/>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<table class="formlist" width="93%">
				<tfoot>
					<tr>
						<td colspan="4">
							<div class="bz">
								"
								<span class="red">*</span>"为必填项
							</div>
							<div class="btn">
								<logic:equal value="add" name="doType">
									<button type="button" name="提交" id="buttonSave" onclick="save();return false;">
										保 存
									</button>
								</logic:equal>
								<logic:equal value="update" name="doType">
									<button type="button" name="提交" onclick="update();return false;">
										修 改
									</button>
								</logic:equal>
								<button type="button" name="关闭" onclick="window.close();return false;">
									关 闭
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
		</html:form>
		<%@include file="/comm/other/tsxx.jsp"%>
	</body>
</html>
