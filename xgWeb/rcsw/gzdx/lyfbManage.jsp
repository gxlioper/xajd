<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/rcsw/rcswFunction.js"></script>
		<script type="text/javascript" src="js/rcsw/gzdx/lyfbManage.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/sfXzZxs.js'></script>
		<script type="text/javascript" src="js/AjaxFunction.js"></script>
		<script type="text/javascript">	
		function showLyInfo(){
			var userType = $("userType").value;
			if(userType != "stu"){
				$("lydiv").style.height = "400px";
			}
		}
		</script>
	</head>
	<body onload="setAllZxs()">
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>${title }</a>
			</p>
		</div>

		<html:form action="/gzdxRcsw">
			<input type ="hidden" name="xxdm" id="xxdm" value="${xxdm }"/>
			<input type ="hidden" name="userType" id="userType" value="${userType }"/>
			
			<%@ include file="/comm/hiddenValue.jsp"%>


			<div class="formbox">
				<h3 class="datetitle_01">
					<logic:empty name="rs">
						<p align="center">
							<!-- 学生留言 -->
							<logic:equal name="mklx" value="ly">无留言信息</logic:equal>
							<!-- 发布通知 -->
							<logic:equal name="mklx" value="tz">无通知信息</logic:equal>

						</p>
					</logic:empty>
				</h3>

				<logic:notEmpty name="rs">
					<table summary="" class="dateline" align="" width="100%">
						<thead>
							<tr align="center" style="cursor:hand">
								<logic:iterate id="tit" name="topTr" offset="1">
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)" nowrap>
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
								<td>
									执行操作
								</td>
							</tr>
						</thead>
						<tbody>
							<logic:iterate name="rs" id="s" indexId="index">
								<tr onclick="rowOnClick(this);" style="cursor:hand">
									<logic:iterate id="v" name="s" offset="1" length="1">
										<td align="left">
											<input type="hidden" value="<bean:write name="v" />" />
											<bean:write name="v" />
										</td>
									</logic:iterate>
									<logic:iterate id="v" name="s" offset="2">
										<td align="left">
											<bean:write name="v" />
										</td>
									</logic:iterate>
									<td align="center">
										<logic:iterate id="v" name="s" offset="0" length="1">
											<a href="#"
												onclick="showTopWin('/xgxt/gzdxRcsw.do?method=xslyUpdate&doType=view&pk=<bean:write name='v'/>','800','600')">
												查看与评论 </a>
										</logic:iterate>
										&nbsp;&nbsp;
									</td>
								</tr>
							</logic:iterate>
						</tbody>
					</table>
					<!--分页显示-->
					<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=rcswForm"></jsp:include>
					<script type="text/javascript">
						$('choose').className="hide";
					</script>
					<!--分页显示-->
				</logic:notEmpty>
			</div>
			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"为必填项</div>
								<div class="btn">
									<button type="button"
										onclick="saveUpdate('/xgxt/gzdxRcsw.do?method=xslyManage&doType=save','lylx-lybt-lynr')"
										id="buttonSave">
										提交
									</button>
									&nbsp;&nbsp;&nbsp;&nbsp;
									<button type="button" type="reset">
										重置
									</button>
									<!-- 学生留言 -->
									<logic:equal name="mklx" value="ly">
										<button type="button" style="display: none" id="search_go"
											onclick="allNotEmpThenGo('/xgxt/gzdxRcsw.do?method=xslyManage');">
										</button>
									</logic:equal>
									<!-- 发布通知 -->
									<logic:equal name="mklx" value="tz">
										<button type="button" style="display: none" id="search_go"
											onclick="allNotEmpThenGo('/xgxt/gzdxRcsw.do?method=fbtzManage');">
										</button>
									</logic:equal>
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
						<tr>
							<th width="20%">
								<font color="red">*</font>类型
							</th>
							<td width="80%">
								<html:select property="lylx" styleId="lylx" style=""
									onchange="getXzfw()">
									<html:options collection="lylxList" property="dm"
										labelProperty="mc" />
								</html:select>
								<span id="xzhffw" style="display:none"> 
								 <input type="radio" name="hffw" id="hffw" value="0" 	onclick="setAllZxs()" checked />所有 
								 <input type="radio" name="hffw" id="hffw" value="1"   onclick="$('xlzxs').style.display='';$('guolv').style.display=''" />选择 <!-- 心理咨询师列表 --> <html:select
										property="lydx" styleId="xlzxs"  style="display:none">
										<html:options collection="zxsList" property="dm"
											labelProperty="mc" />
									</html:select>
									 <button type="button" name="guolv" id="guolv" value="过滤" style="display:none" onclick="showLyfbDiv();" > 过滤 </button>
								</span>
								<html:select property="bmdm" style="display : none"
									styleId='bmdm' onchange="">
									<html:option value=""></html:option>
									<html:options collection="bmdmList" property="bmdm"
										labelProperty="bmmc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>标题
							</th>
							<td>
								<html:text property="lymc" styleId="lybt" style="width: 100%"
									maxlength="50" />
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>留言内容：
								<br />
								<font color="red">(限输入500字)</font>
							</th>
							<td>
								<html:textarea property="lynr" style="width: 100%;height: 150px"
									onblur="chLeng(this,500)" />
							</td>
						</tr>
					</tbody>

				</table>
			
				<logic:present name="result">
					<logic:equal value="true" name="result">
						<script>
						alert('操作成功！');
					</script>
					</logic:equal>
					<logic:equal value="false" name="result">
						<script>
						alert('操作失败！');
					</script>
					</logic:equal>
				</logic:present>
		</html:form>
	</body>
</html>
