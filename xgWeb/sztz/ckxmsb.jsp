<%@ page language="java" pageEncoding="GBK"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/check.js"></script>
		
		<script type="text/javascript">
			
			
		</script>
	</head>
	<body ">
		<html:form action="/sztz" method="post">
			<html:hidden property="id" value="${rs.id }" />
			<input type="hidden" id="hiddenXn" value="${xn}" />
			<input type="hidden" id="hiddenXq" value="${xq}"  />
			<logic:present name="rs">
				<logic:equal value="退回" property="shzt" name="rs">
					<!-- 是否退回后再次提交 -->
					<input type="hidden" name="flg" value="true"/>
				</logic:equal>
				<logic:notEqual value="退回" property="shzt" name="rs">
					<!-- 是否退回后再次提交 -->
					<input type="hidden" name="flg" value="false"/>
				</logic:notEqual>
			</logic:present>
			
			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button id="buttonSave" onclick="window.close();return false;">
										关 闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<thead >
						<tr>
							<td colspan="4" >
								<span id="xmxx">
									项目信息
								</span>
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
								<html:select property="xq" value="${rs.xq }" styleId="xq" disabled="true">
									<html:option value=""></html:option>
									<html:options collection="xqList" property="xqdm" labelProperty="xqmc"/>
								</html:select>
			            	</td>
						</tr>
						<tr>
							<th width="16%">
								年级
							</th>
							<td width="34%">
								<html:select property="nj" styleId="nj" value="${rs.nj }" disabled="true">
									<html:option value="">全部</html:option>
									<html:options collection="njList" property="nj" labelProperty="nj" />
								</html:select>
			            	</td>
			            	<th width="16%">
								院系
							</th>
							<td width="34%">
									<logic:empty name="xydm">
									<html:select property="xydm" style="width:180px" styleId="xydm" value="${rs.xydm }" disabled="true">
										<html:option value="xj">校级</html:option>
										<html:options collection="xyList" property="xydm" labelProperty="xymc" />
									</html:select>
								</logic:empty>
								<logic:notEmpty name="xydm">
									<html:select property="xydmXy" style="width:180px" styleId="xydmXy" value="${xydm }" disabled="true">
										<html:option value="xj">校级</html:option>
										<html:options collection="xyList" property="xydm" labelProperty="xymc" />
									</html:select>
									<input type="hidden" id="xydm" name="xydm" value="${xydm }"/>
								</logic:notEmpty>
			            	</td>
						</tr>
						<tr>
							<th>
								所属科目
							</th>
							<td>
									<html:select property="kmdm" onchange="initHxnl(this)" value="${rs.kmdm }" disabled="true">
									<html:options collection="kmdmList" property="dm" labelProperty="mc"/>
								</html:select>
							</td>
							<th>
								核心能力
							</th>
							<td>
								<html:select property="hxnl" styleId="hxnl" value="${rs.hxnl }" disabled="true">
									<html:options collection="hxnlList" property="dm" labelProperty="mc"/>
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								项目类型
							</th>
							<td>
								<html:select property="xmlx" value="${rs.xmlx }" disabled="true">
									<html:options collection="xmlxList" property="dm" labelProperty="mc"/>
								</html:select>
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
								审核流程
							</th>
							<td>
								<logic:notPresent name="rs">
									<html:select property="shlcid" value="${rs.shlcid }">
										<html:options collection="shlcList" property="dm" labelProperty="mc"/>
									</html:select>
								</logic:notPresent>
								
								<logic:present name="rs">
									<html:select property="shlcid" value="${rs.shlcid }" disabled="true">
										<html:options collection="shlcList" property="dm" labelProperty="mc"/>
									</html:select>
									<html:hidden property="shlcid" value="${rs.shlcid }"/>
								</logic:present>
							</td>
							<th>
								基础分
							</th>
							<td>
								${rs.jcf }
							</td>
						</tr>
						<tr>
							<th>
								举办时间
							</th>
							<td>
								${rs.jbkssj }
								至
								${rs.jbjssj }
							</td>
							<th>人数上限</th>
							<td>
								${rs.rssx }
							</td>
							
						</tr>
						<tr>
							<th>
								主办方
							</th>
							<td>
								${rs.zbf }
							</td>
							<th>
								学时
							</th>
							<td>
								${rs.xs }
							</td>
						</tr>
						<tr>
							<th>
								申报人
							</th>
							<td>
								<logic:empty name="sbr">
									${userNameReal }
								</logic:empty>
								<logic:notEmpty name="sbr">
									${rs.sbr }
								</logic:notEmpty>
								
								<html:hidden property="sbr" value="${userNameReal }"/>
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
							<td align="left" colspan="3" style="word-break:break-all;width:100%">
								${rs.bz }
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							
							<td colspan="4">
								<span>
									设立奖项
								</span>
							</td>
						</tr>
					</thead>
					
					<tbody>
							<tr>
								<td colspan="4">
									
									<table width="100%">
										<thead >
											<tr>
												<td  width="17.5px">
													<input type="checkbox" name="th" onclick="selectAll(this)"/>
												</td>
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
															<input type="checkbox"/>
														</td>
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
									
								</td>
							</tr>
					</tbody>
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
