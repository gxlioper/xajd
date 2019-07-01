<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="/xgxt/dwr/interface/jyglDAO.js"></script>
		<script type="text/javascript" src="js/jygl/jygl.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
	</head>
	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>${title }</a>
			</p>
		</div>

		<html:form action="/jygl" method="post">
			<input type="hidden" id="userType" name="userType"value="${userType }" />
			<input type="hidden" id="userName" name="userName"value="${userName }" />
			<input type="hidden" id="message" value="${message }" />
			<input type="hidden" name="njV" id="njV" />
			<input type="hidden" name="xyV" id="xyV" />
			<input type="hidden" name="zyV" id="zyV" />
			<input type="hidden" name="bjV" id="bjV" />

			<input type="hidden" name="xmlb" id="xmlb" />
			<input type="hidden" name="xxdm" id="xxm" value="${xxdm }" />
			<input type="hidden" id="xbmc" value="<bean:message key="lable.xb" />"/>
			<div class="tab">
				<table class="formlist" width="70%">
					<thead>
						<tr>
							<td colspan="2">
								<span>请选择您要统计项目</span>
							</td>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<td colspan="2">
								<div class='btn'>
									<button onclick='' id="tjbutton">
										确定
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
						<tr>
							<th width="40%">
								项目名称
							</th>
							<td>
								<html:select property="tjxmdm" onchange="showSelect(this.value);" styleId="tjxmdm" style="width:180px">
									<html:options collection="tjxmList" property="dm"
										labelProperty="mc" />
								</html:select>
							</td>
						</tr>
						<logic:equal value="10388" name="xxdm" scope="session">
							<tr id="njtr" style="display:none">
								<th>
									毕业年份
								</th>
								<td>
									<html:select property="nf"  styleId="nf" style="width:180px">
										<html:option value=""></html:option>
										<html:options collection="nfList" property="en" labelProperty="cn"/>
									</html:select>
								</td>
							</tr>
						</logic:equal>
						<logic:notEqual value="10388" name="xxdm" scope="session">
							<tr id="njtr" style="display:none">
								<th>
									年级
								</th>
								<td>
									<html:select property="nj" onchange="initZyList();initBjList()" styleId="nj" style="width:180px">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj" labelProperty="nj"/>
									</html:select>
								</td>
							</tr>
							<tr id="cheNj" style="display:none">
								<th>
									年级
								</th>
								<td>
									<logic:iterate id="n" name="njList">
										<input type="checkbox" name="selNj" value="${n.nj }"/> ${n.nj}&nbsp;&nbsp;
									</logic:iterate>
								</td>
							</tr>
						</logic:notEqual>
						<logic:equal value="88888" name="xxdm" scope="session">
							<tr id="cheJyXy" style="display:none">
								<th>
									<bean:message key="lable.xb" />
								</th>
								<td>
									<logic:iterate id="x" name="jyxyList">
										<input type="checkbox" name="selJyXy" value="${x.xydm }"/> ${x.xymc}&nbsp;&nbsp;
									</logic:iterate>
								</td>
							</tr>
						</logic:equal>
							
						<tr id="xytr" style="display:none">
							<th>
								<bean:message key="lable.xsgzyxpzxy" />
							</th>
							<td>
								<html:select property="xydm" onchange="initZyList();initBjList()" styleId="xy" style="width:180px">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
												labelProperty="xymc" />
								</html:select>
							</td>
						</tr>
						<tr id="zytr" style="display:none">
							<th>
								专业
							</th>
							<td>
								<html:select property="zydm" onchange="initBjList()"  styleId="zy" style="width:180px">
									<html:option value=""></html:option>
									<html:options collection="zyList" property="zydm"
												labelProperty="zymc" />
								</html:select>
							</td>
						</tr>
						<tr id="bjtr" style="display:none">
							<th>
								班级
							</th>
							<td>
								<html:select property="bjdm"  styleId="bj" style="width:180px">
									<html:option value=""></html:option>
									<html:options collection="bjList" property="bjdm"
												labelProperty="bjmc" />
								</html:select>
							</td>
						</tr>
						
						
						<!-- 福建工程有个项目备注的说法，还要自己能维护  -->
						<logic:equal value="10388" name="xxdm" scope="session">
							<tr>
								<th>项目备注
									<br/>
									<font color="red"><限500字></font>
								</th>
								<td>
									<html:textarea property="xmbz" styleId="xmbz" style="width:80%" rows="5" onblur="checkLen(this,500)"></html:textarea>
								</td>
							</tr>
						</logic:equal>
					</tbody>
				</table>
			</div>
		</html:form>
	</body>
</html>
