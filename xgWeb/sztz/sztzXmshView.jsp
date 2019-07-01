<%@ page language="java" pageEncoding="GBK"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
			function setThjsr(shzt){
				
				if (shzt == '退回'){
					jQuery('#thjsrText').attr('style','display:block');
					jQuery('#thjsrZj').attr('style','visibility:');
					jQuery('#thjsr').attr('disabled',false);
				} else {
					jQuery('#thjsrText').attr('style','display:none');
					jQuery('#thjsrZj').attr('style','visibility:hidden');
					jQuery('#thjsr').attr('disabled',true);
				}
			
			}
		</script>
	</head>
	<body>
		<html:form action="/sztz" method="post">
			<html:hidden property="shlcid" value="${rs.shlcid }" />
			<input type="hidden" name="id" value="${rs.id }" />
			<input type="hidden" name="pkValue" value="${rs.id }" />

			<div class="tab">
				<table width="100%" border="0" class="formlist">
					
					<thead>
						<tr>
							<td colspan="4">
								<span> 项目信息 </span>
							</td>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="16%">
								学年
							</th>
							<td width="34%">
								${rs.xn }
							</td>
							<th width="16%">
								学期
							</th>
							<td width="34%">
								${rs.xqmc }
							</td>
						</tr>
						<tr>
							<th>
								所属科目
							</th>
							<td>
								${rs.kmmc }
							</td>
							<th>
								核心能力
							</th>
							<td>
								${rs.hxnlmc }
							</td>
						</tr>

						<tr>
							<th>
								项目类型
							</th>
							<td>
								${rs.xmlxmc }
							</td>
							<th>
								项目名称
							</th>
							<td>
								${rs.xmmc }
							</td>
						</tr>
						<tr>
							<th>
								基础分
							</th>
							<td>
								${rs.jcf }
							</td>
							<th>
								人数上限
							</th>
							<td>
								${rs.rssx }
							</td>
						</tr>
						<tr>
							<th>
								举办时间
							</th>
							<td colspan="3">
								${rs.jbkssj } 至 ${rs.jbjssj }
							</td>
						</tr>
						<tr>
							<th>
								主办方
							</th>
							<td colspan="3">
								${rs.zbf }
							</td>
						</tr>
						<tr>
							<th>
								申报人
							</th>
							<td>
								${rs.sbr }
							</td>
							<th>
								负责人
							</th>
							<td>
								${rs.fzr }
							</td>
						</tr>
						<tr>
							<th>
								备注
							</th>
							<td colspan="3" style="word-break:break-all;">
								${rs.bz }
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>

							<td colspan="4">
								<span> 设立奖项 </span>
							</td>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td colspan="4">
								<div
									style="width:100%;height:150px;overflow-x:hidden;overflow-y:auto;">
									<table width="100%">
										<thead>
											<tr>
												<td>
													奖项代码
												</td>
												<td>
													奖项名称
												</td>
												<td>
													学分
												</td>
											</tr>
										</thead>
										<tbody id="sljxTab">
											<logic:present name="jxList">
												<logic:iterate id="j" name="jxList">
													<tr>
														<td>
															${j.jxdm }
														</td>
														<td>
															${j.jxmc }
														</td>
														<td>
															${j.jxfs }
														</td>
													</tr>
												</logic:iterate>
											</logic:present>
										</tbody>
									</table>
								</div>
							</td>
						</tr>
					</tbody>

					<thead>
						<tr>
							<td colspan="4">
								<span> 审核信息 </span>
							</td>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>
								审核人
							</th>
							<td>
								${userNameReal }
							</td>
							<th>
								审核时间
							</th>
							<td>
								${sysdate }
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>审核状态
							</th>
							<td>
								<html:select property="shzt" style="width:120px" styleId="shzt"
									value="${rs.shzt }" onchange="setThjsr(this.value)">
									<html:option value=""></html:option>
									<html:options collection="shztList" property="en"
										labelProperty="cn" />
								</html:select>
							</td>
							<th>
								<span id="thjsrText" style="display:none;"> <font
									color="red">*</font>接收人 </span>
							</th>
							<td>
								<span id="thjsrZj" style="visibility:hidden"> <html:select
										property="thjsr" styleId="thjsr" disabled="true">
										<html:option value="Applicant">申请人</html:option>
										<logic:present name="xtgwList">
											<html:options collection="xtgwList" property="id"
												labelProperty="mc" />
										</logic:present>
									</html:select> </span>
							</td>
						</tr>
						<tr>
							<th>
								审核意见
								<br />
								<font color="red"><限500字>
								</font>
							</th>
							<td colspan="3">
								<html:textarea property="shyj" style="width:95%" rows="5"
									onblur="chLeng(this,500)" value="${rs.shyj }"></html:textarea>
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"
									<span class="red">*</span>"为必填项
								</div>
								<div class="btn">
									<button
										onclick="saveUpdate('sztz.do?method=sztzXmshSave&sftj=是','xh-shzt-temp_xmid')">
										提 交
									</button>
									<button onclick="window.close();return false;">
										关 闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>

			<logic:present name="message">
				<script defer>
					alertInfo('${message}',function(){
						if (window.dialogArguments) {
							window.close();
							window.dialogArguments.document.getElementById('search_go').click();
						}
					});
				</script>
			</logic:present>
		</html:form>
	</body>
</html>
