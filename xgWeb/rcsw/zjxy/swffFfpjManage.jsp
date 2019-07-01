<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/rcsw/rcswFunction.js"></script>
		<script  language="javascript">
			function showPlpj(){
				var RowsStr="!!";
				for (i=0; i<document.getElementsByName("pkV").length; i++){
			    	if(document.getElementsByName("pkV")[i].checked){
			    		RowsStr+=document.getElementsByName("pkV")[i].value+"!!";
			    	}
				}
				if (RowsStr=="!!"){
					alert("请选择要操作的记录！");
					return false;
				}
				viewTempDiv('食物发放评价','plpj',280,160);
			}
			
			function savePj(){
				if($("pjyj").value==""){
					alert("评价意见不能为空!");
					return false;
				}
				
				dataBatch('zjxyRcsw.do?method=swffFfpjManage&doType=savePj');
			}
			
			function modi(url){
				if(curr_row != null){
					showTopWin(url + '&pkValue='+curr_row.getElementsByTagName('input')[0].value,700,500);
					return true;
				}else{
					alert('请选择要修改的数据行！');
					return false;
				}
			}
		</script>
	</head>
	<body onload="xyDisabled('xy');">
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>${title }</a>
			</p>
		</div>

		<html:form action="/zjxyRcsw" method="post">
			<%@ include file="/rcsw/hiddenValue.jsp"%>
			<logic:notEmpty name="msg">
				<div align="center">
					<font color="red" size="6"><bean:write name="msg" />
					</font>
				</div>
			</logic:notEmpty>
			<logic:empty name="msg">
				<div class="toolbox">
					 <!-- 按钮 -->
					 <logic:equal name="writeAble" value="yes">
					 <div class="buttonbox">
					    <ul>
					    	<li> <a href="#" onclick="modi('/xgxt/zjxyRcsw.do?method=swffOnePjManage&doType=modi')" class="btn_sh"> 评价 </a> </li>
							<li> <a href="#" onclick="showPlpj()" class="btn_shtg"> 批量评价 </a> </li>
					    </ul>
					 </div>
					 </logic:equal>
					<div class="searchtab">
						<table width="100%" border="0">
							
							<tfoot>
								<tr>
									<td colspan="6">
										<div class="btn">
											<button type="button" class="btn_cx" id="search_go"
												onclick="BatAlert.showTips('查询数据中，请稍候！');allNotEmpThenGo('/xgxt/zjxyRcsw.do?method=swffFfpjManage&doType=query');">
												查 询
											</button>
											<input type="hidden" name="go" value="a" />
											<button type="button" class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
												重 置
											</button>
										</div>
									</td>
								</tr>
							</tfoot>
							<tbody>
								<tr>
									<th>
										学年
									</th>
									<td>
										<html:select property="xn" style="" onchange="">
											<html:options collection="xnList" property="xn"
												labelProperty="xn" />
										</html:select>
									</td>
									<th>
										学期
									</th>
									<td>
										<html:select property="xq" style="" onchange="">
											<html:option value=""></html:option>
											<html:options collection="xqList" property="xqdm"
												labelProperty="xqmc" />
										</html:select>
									</td>
									<th>
										年度
									</th>
									<td>
										<html:select property="nd" style="" onchange="">
											<html:options collection="ndList" property="nd"
												labelProperty="nd" />
										</html:select>
									</td>
								</tr>
								<tr>
									<th >
										学号
									</th>
									<td>
										<html:text  property="zgh"  value="${userName}" disabled="true" style="width:85px" maxlength="20" />
										<html:hidden property="zgh" value="${userName }"/>
									</td>
									<th>
										项目类型
									</th>
									<td>
										<html:select property="xmlx" style=""
											styleId="xmlx">
											<html:options collection="xmlxList" property="dm"
												labelProperty="mc" />
										</html:select>
									</td>
									<th>
										项目名称
									</th>
									<td>
										<html:text property="xmmc" styleId="xmmc"/> 
									</td>
								</tr>
								<tr>	
									<th >
										办理开始时间
									</th>
									<td >
										 <html:text  property="kssj"   styleId="kssj" style="width:70px"
										onclick="return showCalendar('kssj','y-mm-dd');" 
										onblur="dateFormatChg(this)" />--
										<html:text property="jssj" styleId="jssj"  style="width:70px"
										onclick="return showCalendar('jssj','y-mm-dd');" 
										onblur="dateFormatChg(this)" />
									</td>
									<th >
										办理结束开始
									</th>
									<td>
										 <html:text property="ffjskssj" styleId="ffjskssj" style="width:70px"
										onclick="return showCalendar('ffjskssj','y-mm-dd');" 
										onblur="dateFormatChg(this)" />--
										<html:text property="ffjsjssj" styleId="ffjsjssj"  style="width:70px"
										onclick="return showCalendar('ffjsjssj','y-mm-dd');" 
										onblur="dateFormatChg(this)" />
									</td>
									<td colspan="2"></td>
								</tr>
							</tbody>
						</table>
					</div>
					<div class="formbox">
						<h3 class="datetitle_01">
							<span> 查询结果&nbsp;&nbsp; <logic:empty name="rs">
									<font color="red">未找到任何记录！</font>
								</logic:empty> </span>
								<logic:notEmpty name="rs">
						 		 	<font color="blue">提示：双击一行可以查看详细信息；单击表头可以排序</font>
						 		 </logic:notEmpty>
						</h3>

						<logic:notEmpty name="rs">
							<table summary="" class="dateline" align="" width="100%">
								<thead>
									<tr align="center" style="cursor:hand">
										<td>
											<input type="checkbox"  name="all" value="all" onclick="chec()"/>
											
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
									<logic:iterate name="rs" id="s" indexId="index">
										<tr onclick="rowOnClick(this);" style="cursor:hand"
											ondblclick="modi('/xgxt/zjxyRcsw.do?method=swffOnePjManage&doType=view')">
											<td>
												<input type="checkbox" name="primarykey_cbv" id="pkV" 
											   value="<logic:iterate id="v" name="s" offset="0" length="1">${v}</logic:iterate>"
											   />
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
			</logic:empty>
			<div id="plpj" style="display: none">
				<div class="tab">
					<table class="formlist">
						<tr>
							<th><font color="red">*</font>满意程度</th>
							<td>
								<input type="radio" name="mycd" value="fcmy" checked/>非常满意
								<input type="radio" name="mycd" value="my"/>满意<br/>
								<input type="radio" name="mycd" value="bjmy"/>比较满意
								<input type="radio" name="mycd" value="bmy"/>不满意
							</td>
						</tr>
						<tr>
							<th><font color="red">*</font>评价意见</th>
							<td>
								<textarea  name="pjyj" id="pjyj" style="word-break:break-all;width:99%" onblur="chLeng(this,500);" rows='4' ></textarea>
							</td>
						</tr>
						<tr>
							<td colspan='2' align='right'>
							<button type="button" onclick='savePj()'>确定</button>
							</td>
						</tr>
					</table>
				</div>
			</div>
		</html:form>
		<logic:equal value="true" name="result">
			<script>
				alert("操作成功!");
			</script>
		</logic:equal>
		<logic:equal value="false" name="result">
			<script>
				alert("操作失败");
			</script>
		</logic:equal>
		<script type="text/javascript" src="js/bottomButton.js"></script>
	</body>
</html>
