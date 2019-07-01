<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：伟大的骆 -->
<jsp:directive.page import="java.util.ArrayList" />
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_xg.ini"%>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/xgutil.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="js/xszz/xszzFunction.js"></script>
		<script type='text/javascript'
			src='/xgxt/dwr/interface/rcswZjxyDAO.js'></script>
		<!-- 扫描接口相关处理页面 -->
		<jsp:include page="/comm/planarCode.jsp"></jsp:include>
		<script language="javascript">
			function goSearch(){
				$("sfzh").value="";
				if(event.keyCode=="13"){
					$("search_go").onclick();
				}
			}
			
			function goSearchBySfzh(){
				$("zgh").value="";
				if(event.keyCode=="13"){
					$("search_go").onclick();
				}
			}
			
			function xsxmff(){
				var cbox=document.getElementsByName('checkVal');
				var blog=false;
				if(cbox.length>0){
					for(i=0;i<cbox.length;i++){
						if(cbox[i].checked){
							blog=true;
							break;
						}
					}
				}
				if(blog){
					refreshForm('/xgxt/zjxyRcsw.do?method=swffFfryqrManage&doType=save');
				}else{
					alert("请选择要操作的记录!");
					return false;
				}
			}
			
			function getXh(){
				if($("planarCode")){
					var sfzh=$("planarCode").value;
					rcswZjxyDAO.getXhBySfzh(sfzh,function(data){
						
						if(data!=null && data.xh!=null && data.xh!=""){
							var xh=data.xh;
							$("zgh").value=xh;
							allNotEmpThenGo('/xgxt/zjxyRcsw.do?method=swffFfryqrManage&doType=query');
						}else{
							alert("系统内不存在该身份证号!");
						}
					});
				}
			}
			
			function zghFoc(){
				$('zgh').focus();
			}
		</script>
	</head>
	<body onload="zghFoc()" onunload="sfzy();">
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a><bean:write name="title" /> </a>
			</p>
		</div>
		<!-- 标题 end-->
		<html:form action="/zjxyRcsw">

			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<html:hidden property="xmdm" styleId="xmdm" />
			<input type="hidden" id="sffj" name="sffj" value="${sffj }" />
			<input type="hidden" id="rsNum" name="rsNum" value="${rsNum}" />
			<input type="hidden" id="mklx" name="mklx" value="${mklx}" />
			<!-- 隐藏域 end-->

			<div class="toolbox">
				<!-- 按钮 -->
				<logic:equal value="yes" name="writeAble">
					<div class="buttonbox">
						<ul>
							<li>
								<a href="#" onclick="xsxmff();return false" class="btn_ccg">
									办理 </a>
							</li>
						</ul>
					</div>
				</logic:equal>
			</div>
			<!-- 多功能操作区 -->
			<div class="toolbox">
				<div class="searchtab">
					<table width="100%" border="0">
						<tfoot>
							<tr>
								<td colspan="6">
									<div class="btn">
										<input type="hidden" name="go" value="a" />
										<button type="button" class="btn_cx" id="search_go"
											onclick="if($('zgh').value=='' && $('sfzh').value=='' ){alert('请输学号或身份证号!');return false;}else{allNotEmpThenGo('/xgxt/zjxyRcsw.do?method=swffFfryqrManage&doType=query');}">
											查 询
										</button>
										&nbsp;&nbsp;&nbsp;&nbsp;
										<button type="button" class="btn_cz" id="btn_cz"
											onclick="czSearchCond('zgh-kssj-jssj-ffjskssj-ffjsjssj');return false;">
											重 置
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
						<tbody>
							<tr>
								<th>
									身份证号
								</th>
								<td>
									<html:text property="sfzh" styleId="sfzh"
										onkeyup="goSearchBySfzh()" />
								</td>
								<th>
									学号
								</th>
								<td>
									<html:text property="zgh" styleId="zgh" style="" maxlength="20"
										onkeyup="goSearch()" />
									<input type="text" name="jd" id="jd" style="display:none" />
								</td>
								<th>
									办理开始时间
								</th>
								<td>
									<html:text property="kssj" styleId="kssj"
										onblur="dateFormatChg(this)" style="cursor:hand;width:80px"
										onclick="return showCalendar('kssj','y-mm-dd');"
										onkeypress="return onlyNum(this,8)" />
									--
									<html:text property="jssj" styleId="jssj"
										onblur="dateFormatChg(this)" style="cursor:hand;width:80px"
										onclick="return showCalendar('jssj','y-mm-dd');"
										onkeypress="return onlyNum(this,8)" />
								</td>
							</tr>
							<tr>
								<th>
									办理结束时间
								</th>
								<td>
									<html:text property="ffjskssj" styleId="ffjskssj"
										onblur="dateFormatChg(this)" style="cursor:hand;width:80px"
										onclick="return showCalendar('ffjskssj','y-mm-dd');"
										onkeypress="return onlyNum(this,8)" />
									--
									<html:text property="ffjsjssj" styleId="ffjsjssj"
										onblur="dateFormatChg(this)" style="cursor:hand;width:80px"
										onclick="return showCalendar('ffjsjssj','y-mm-dd');"
										onkeypress="return onlyNum(this,8)" />
								</td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
							</tr>
						</tbody>
					</table>

				</div>
				<logic:notEmpty name="xsxx" property="xh">
					<div class="tab">
				<table width="100%" border="0" class="formlist">
					<tbody>
						<tr>
							<th style="width:16%">
								学号
							</th>
							<td style="width:50%">
								${xsxx.xh}
							</td>
							<th style="width:10%" rowspan="4">
								照片
							</th>
							<td style="width:24%;"  rowspan="4" >
							<div align="center">
								<img src="<%=request.getContextPath()%>/stuPic.jsp?xh=${xsxx.xh}" width="96" height="128"/>
							</div>
							</td>
						</tr>
						<tr>
							
							<th style="width:16%">
								姓名
							</th>
							<td id="" style="width:34%">
								${xsxx.xm}
							</td>
						</tr>
						<tr>
							
							<th style="width:16%">
								年级
							</th>
							<td id="" style="width:34%">
								${xsxx.nj}
							</td>
						</tr>
						<tr>
							
							<th style="width:16%">
								班级名称
							</th>
							<td id="" style="width:34%">
								${xsxx.bjmc}
							</td>
						</tr>
					</tbody>
				</table>
			</div>
				</logic:notEmpty>
				
				<h3 class="datetitle_01">
					<span> 查询结果&nbsp;&nbsp; <logic:notEmpty name="xsxx">
							<logic:empty name="rs">
								<font color="red">提示：未找到需要办理的项目</font>
							</logic:empty>
							<logic:notEmpty name="rs">
								<font color="blue">提示：单击表头可以排序</font>
							</logic:notEmpty>
						</logic:notEmpty> <logic:empty name="xsxx">
							<font color="red">提示：未找到任何记录</font>
						</logic:empty> </span>
				</h3>
				<logic:notEmpty name="rs">
				
					<div class="con_overlfow">
						<table width="100%" class="dateline tablenowrap">
							<!-- 表头 -->
							<thead>
								<tr align="center" style="cursor:hand">
									<td>
										<input type="checkbox" name="all" value="all" onclick="chec()" />

									</td>
									<logic:iterate id="tit" name="topTr" offset="0" indexId="index">
										<td id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this)" nowrap>
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
								</tr>
							</thead>
							<tbody>
								<logic:iterate name="rs" id="s">
									<tr onclick="rowMoreClick(this,'',event);" style="cursor:hand">
										<td>
											<input type="hidden" name="xhzgh" id="xhzgh"
												value="<logic:iterate id="v" name="s" offset="1" length="1">${v}</logic:iterate>" />
											<input type="checkbox" name="checkVal" id="pkV"
												checked="checked"
												value="<logic:iterate id="v" name="s" offset="0" length="1">${v}</logic:iterate>" />
										</td>
										<logic:iterate id="v" name="s" offset="1">
											<td nowrap>
												<bean:write name="v" />
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>
							</tbody>
							<!--内容 end -->
						</table>
					</div>
					<!--分页-->
					<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=rcswForm"></jsp:include>
					<!--分页end-->
					<script type="text/javascript">
					$('choose').className="hide";
					</script>
				</logic:notEmpty>
			</div>
			<logic:present name="result">
				<logic:equal value="true" name="result">
					<script>
					alert('操作成功！');
				</script>
				</logic:equal>
				<logic:notEqual value="true" name="result">

					<script>
					alert('操作失败！');
				</script>
				</logic:notEqual>
			</logic:present>
			<logic:present name="message">
				<script>
				alert('${message}');
				</script>
			</logic:present>
		</html:form>
	</body>
</html>
