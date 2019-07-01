<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type='text/javascript'
			src='/xgxt/dwr/interface/getBjlhGyglDAO.js'></script>
		<script language="javascript">	
	function qdbffr(){
		var checkBoxArr = document.getElementsByName("checkVal");
		var flag = false;
		var xhzgh = "";
		if($("ffbz").value==""){
			alert("代领备注不能为空!");
			return false;
		}
		for(var i=0;i<checkBoxArr.length;i++){
			if(checkBoxArr[i].checked==true){
				flag = true;
			}
		}
		if(flag){
			//url+="&doType="+doType;
			if (confirm("确定要勾选人员为发放对象?")) {
				if(checkBoxArr.length>1000){
					alert("数据超过1000，保存速度有点慢，请耐心等待");
				}
				var url = "/xgxt/zjxyRcsw.do?method=swffFfjgUpdate";
				url+="&doType=ff";
				refreshForm(url);
				return true;		
			}
		}else{
			alert("请勾选要发放的人员信息!!");
			return false;
		}
	}
	
	function showSaveXm(){
		var checkBoxArr = document.getElementsByName("checkVal");
		var flag = false;
		for(var i=0;i<checkBoxArr.length;i++){
			if(checkBoxArr[i].checked==true){
				flag = true;
			}
		}
		
		if(flag){
			viewTempDiv('代领备注','divq',360,200);
		}else{
			alert("请勾选要发放的人员信息!");
			return false;
		}
		
	}
	</script>
	</head>
	<body >
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>信息维护 - 被发放者信息</a>
			</p>
		</div>
		<html:form action="/zjxyRcsw" method="post">
				<%@ include file="/comm/hiddenValue.jsp"%>
				<html:hidden property="xn" styleId="xn" value="${xmzj.xn}"/>
				<html:hidden property="xq" styleId="xq"  value="${xmzj.xq}"/>
				<html:hidden property="nd" styleId="nd"  value="${xmzj.nd}"/>
				<html:hidden property="xmlx" styleId="xmlx"  value="${xmzj.xmlx}"/>
				<html:hidden property="ffsj" styleId="ffsj"  value="${xmzj.ffsj}"/>
				
				<html:hidden property="ffr" styleId="ffr" value="${userName }"/>
				<div class="toolbox">
					<!-- 按钮 -->
					<div class="buttonbox">
						<ul>
								<li>
									<a href="#"
										onclick="showSaveXm();return false;"
										class="btn_ccg">保存</a>
								</li>
								<li>
									<a href="#" onclick="allNotEmpThenGo('/xgxt/zjxyRcsw.do?method=swffFfjgManage&doType=query','800','480');" class="btn_fh"> 返回 </a>
								</li>
						</ul>
					</div>
				</div>
				<div class="toolbox">
					<div class="searchtab">
						<table width="100%" border="0">
							<tfoot>
								<tr>
									<td colspan="8">
										<div class="bz">
											<font color="blue">
											项目名称：${xmzj.xmmc}
											&nbsp;&nbsp;&nbsp;学年：${xmzj.xn
											}&nbsp;&nbsp;&nbsp;学期：${xmzj.xqmc
											}&nbsp;&nbsp;&nbsp;年度：${xmzj.nd}&nbsp;&nbsp;&nbsp;办理开始时间：${xmzj.ffsj}
											&nbsp;&nbsp;&nbsp;办理结束时间：${xmzj.ffjssj}</font>
										</div>
										<div class="btn">
											<input type="hidden" name="go" value="a" />
											<button type="button" class="btn_cx" id="search_go"
												onclick="allNotEmpThenGo('/xgxt/zjxyRcsw.do?method=swffFfjgUpdate&doType=query');">
												查 询
											</button>
										</div>
									</td>
								</tr>
							</tfoot>
							<tbody>
									<tr>
										<th>
											年级
										</th>
										<td>
											<html:select property="nj" 	onchange="initZyList();initBjList();">
												<html:option value=""></html:option>
												<html:options collection="njList" property="nj"
													labelProperty="nj" />
											</html:select>
										</td>
										<th>
											学号
										</th>
										<td>
											<html:text property="xh" style="width:85px" maxlength="20" />
										</td>
										<th>
											姓名
										</th>
										<td>
											<html:text property="xm" style="width:85px" maxlength="20" />
										</td>
									</tr>
									<tr>
										<th>
											<bean:message key="lable.xsgzyxpzxy" />
										</th>
										<td>
											<html:select property="xydm" style="width:200px" styleId="xy"
												onchange="initZyList();initBjList();">
												<html:option value=""></html:option>
												<html:options collection="xyList" property="xydm"
													labelProperty="xymc" />
											</html:select>
										</td>
										<th>
											专业
										</th>
										<td>
											<html:select property="zydm" style="width:200px" styleId="zy"
												onchange="initBjList();">
												<html:option value=""></html:option>
												<html:options collection="zyList" property="zydm"
													labelProperty="zymc" />
											</html:select>
										</td>
										<th>
											班级
										</th>
										<td>
												<html:select property="bjdm" style="width:200px" styleId="bj">
													<html:option value=""></html:option>
													<html:options collection="bjList" property="bjdm"
														labelProperty="bjmc" />
												</html:select>
										</td>
									</tr>
									</tbody>
						</table>
					</div>
					<div class="formbox">
						<h3 class="datetitle_01">
							<span> 查询结果&nbsp;&nbsp; <logic:empty name="rsList">
									<font color="red">未找到任何记录！</font>
								</logic:empty> </span>
						</h3>

						<logic:notEmpty name="rsList">
							<table summary="" class="dateline" align="" width="100%">
								<thead>
									<tr align="center" style="cursor:hand">
										<td>
											<input type="checkbox" id="selall" name="selall"
												onclick="selAll()" />
										</td>
										<logic:iterate id="tit" name="topTr" offset="0">
											<td id="<bean:write name="tit" property="en"/>"
												onclick="tableSort(this)" nowrap>
												<bean:write name="tit" property="cn" />
											</td>
										</logic:iterate>
									</tr>
								</thead>
								<tbody>
									<logic:iterate name="rsList" id="s" indexId="index">
										<tr onclick="rowOnClick(this);" style="cursor:hand">
											<td align="center">
												<input type="checkbox" id="checkVal" name="checkVal"
													value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>" />
											</td>
											<logic:iterate id="v" name="s" offset="1">
												<td align="left">
													<bean:write name="v" />
												</td>
											</logic:iterate>
										</tr>
									</logic:iterate>
								</tbody>
							</table>
							<!--分页显示-->
							<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=rcswForm"></jsp:include>
							<!--分页显示-->
						</logic:notEmpty>
					</div>
				</div>
				<div id="divq" style="display: none">
				<div class="tab">
				<table class="formlist">
					
					<tbody>
					<tr>
						<th>
							<font color="red">*</font>代领备注<br/>
							<font color="red">(限500字)</font>
						</th>
						<td>
							<textarea rows="3" id="ffbz" name="ffbz" style="width:200px" ></textarea>	
						</td>
					</tr>
					</tbody>
					<tfoot>
					<tr>
						<td colspan='2' align='right'>
							<button type="button" onclick='qdbffr()'>确定</button>
						</td>
					</tr>
					</tfoot>
				</table>
				</div>
			</div>
			<logic:present name="result">
				<logic:equal value="true" name="result">
					<script>
						 
         				 alert("保存成功！");
       				 </script>
				</logic:equal>
				<logic:equal value="false" name="result">
					<script>
         				 alert("保存失败！");
        			</script>
				</logic:equal>
			</logic:present>
		</html:form>
	</body>
</html>
