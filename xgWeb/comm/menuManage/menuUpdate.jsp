<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/comm/commFunction.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/menuManage.js'></script>
		<script type="text/javascript">
			function changeBtnSfqy(obj){
				var sfqy;
				var text = obj.value;
				
				if ("应用"==text) {
					obj.value = "取消";
					sfqy = "是";
				} else{
					obj.value = "应用";
					sfqy = "否";
				}
				
				menuManage.changeBtnSfqy($('path').value,obj.id,sfqy,function(data){
					if (data){
						alert(text+"成功");
					} else {
						alert(text+"失败");
					}
				})
				
			}
			
			function setChildList(gnmkdm,id){
				DWRUtil.removeAllOptions(id);
				DWRUtil.addOptions(id,[{dm:"",mc:""}],"dm","mc")
			
				if ("" != gnmkdm){
					menuManage.getChildGnmkList(gnmkdm,function(data){
						DWRUtil.addOptions(id,data,"gnmkdm","gnmkmc");
						$('ejgnmkdm').value = $('tempEjgnmkdm').value;
					});
				} 
				
				if (id=="ejgnmkdm" && $("gnmkdm")){
					setChildList("","gnmkdm");
				}
			}
			
			function changeGnmkdm(text){
				if ("" != text){
					menuManage.getNextGnmkdm(text,function(data){
						$('gnmkdm').value = data;
					})
				} else {
					$('gnmkdm').value = $('pkValue').value;
				}
			}
		</script>
	</head>
	<body onload="<logic:equal value="3" name="rs" property="cdjb">setChildList('${rs.yjgnmkdm }','ejgnmkdm')</logic:equal>">
		<html:form action="/menuManage" method="post">
			<input type="hidden" id="pkValue" name="pkValue" value="${pkValue }" />
			<input type="hidden" id="path" name="path" value="${rs.dyym }" />
			<input type="hidden" id="tempEjgnmkdm"  value="${rs.ejgnmkdm }" />

			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="3">
								<span>功能菜单维护</span>
							</th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<td colspan="3">
								<div class="bz">
									"
									<span class="red">*</span>"为必填项
								</div>
								<div class="btn">
									<logic:notEqual name="doType" value="view">
										<button type="button" name="提交"
											onclick="saveUpdate('/xgxt/menuManage.do?method=menuUpdate&doType=save','save_gnmkmc')">
											保存
										</button>
									</logic:notEqual>
									<button type="button" name="关闭" onclick="window.close();return false;">
										关闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
						<logic:equal value="3" name="rs" property="cdjb">
						<tr>
							<th>一级父菜单</th>
							<td colspan="2">
								<html:select property="queryequals_yjgnmkdm"
										value="${rs.yjgnmkdm }"
										onchange="setChildList(this.value,'ejgnmkdm')"
										style="width:140px">
										<html:option value=""></html:option>
										<html:options collection="gnmkList" property="gnmkdm"
											labelProperty="gnmkmc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>二级父菜单</th>
							<td colspan="2">
								<html:select property="queryequals_ejgnmkdm" 
										value="${rs.ejgnmkdm }" styleId="ejgnmkdm"
										style="width:140px" onchange="changeGnmkdm(this.value);">
									<html:option value=""></html:option>
								</html:select>
							</td>
						</tr>
						</logic:equal>
						<tr>
							<th width="25%">
								<font color="red">*</font>菜单名称
							</th>
							<td width="45%">
								<html:text property="save_gnmkmc" maxlength="40" value="${rs.gnmkmc }"></html:text>
								<html:hidden property="save_gnmkdm" value="${rs.gnmkdm }" styleId="gnmkdm"/>
							</td>
							<td rowspan="5" align="center">
								<logic:equal value="3" name="rs" property="cdjb">
									<img src="<%=stylePath %>/images/blue/54/${rs.picpath }" style="width:54px;height:54px" id="picpath"/>
									<br/><br/>
									<button type="button" class="btn_01" onclick="showTopWin('/xgxt/menuManage.do?method=setShortcutPic',600,400)">
										浏览
									</button>
								</logic:equal>
								<logic:notEqual value="3" name="rs" property="cdjb">
								<font color="blue">非3级菜单不能维护快捷方式</font>
								</logic:notEqual>
							</td>
						</tr>
						<tr>
							<th>
								菜单级别
							</th>
							<td>
								${rs.cdjb }
							</td>
						</tr>
						<tr>
							<th>
								访问路径
							</th>
							<td>
								${rs.dyym }
								<input type="hidden" value="${rs.dyym }" name="dyym"/>
							</td>
						</tr>
						<tr>
							<th>
								显示顺序
							</th>
							<td>
								<html:text property="save_xssx" value="${rs.xssx }"
									maxlength="3" onkeyup="value=value.replace(/[^\d]/g,'')"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								是否启用
							</th>
							<td>
								<html:radio property="save_sfqy" value="是" name="rs">是</html:radio>
								<html:radio property="save_sfqy" value="否" name="rs">否</html:radio>
							</td>
						</tr>
						<tr>
							<th>
								功能描述<br/>
								<font color="red">(限250字)</font>
							</th>
							<td colspan="2" style="word-break:break-all;">
								<html:textarea property="save_gnbz" style="width:98%" rows="5"
									onblur="checkLen(this,250)" value="${rs.gnbz }"></html:textarea>
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="3">
								<span>功能按钮</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td colspan="3">
							<logic:notEmpty name="btnList">
								<logic:iterate id="b" name="btnList">
										${b.btnmc }
										<button type="button" class="btn_01" id="${b.btndm }" onclick="changeBtnSfqy(this);">
											<logic:equal value="是" name="b" property="sfqy">
												取消
											</logic:equal>
											<logic:equal value="否" name="b" property="sfqy">
												应用
											</logic:equal>
										</button>
										&nbsp;&nbsp;
								</logic:iterate>
							</logic:notEmpty>
							<logic:empty name="btnList">
								<font color="blue">无功能按钮</font>
							</logic:empty>
							</td>
						</tr>
					</tbody>
					
				</table>
			</div>
			<logic:present name="message">
				<script defer="defer">
				    alert("${message}");
				    if (window.dialogArguments) {
						dialogArgumentsQueryChick();
						window.close();
					}
				 </script>
			</logic:present>
		</html:form>
	</body>
</html>
