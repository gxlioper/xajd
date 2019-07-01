<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/check.js"></script>   
		<script language="javascript" src="js/function.js"></script>      
		<script language="javascript">
		function to_save(){
		    var jlhz=$("zyzbjlhz").value;
		    var clqk=$("xgbmclqk").value;
		    var ldps=$("yldps").value;
		    if(jlhz.length>2000){
		       alert("主要值班记录汇总字数过长！");
		       return false;
		    }
		    if(clqk.length>2000){
		       alert("相关部门处理情况字数过长！");
		       return false;
		    }
		    if(ldps.length>50){
		       alert("院领导批示字数过长！");
		       return false;
		    }
		    if(checkSearchTj("qssj","jssj")){
		    	 dataDoSave('qssj-jssj');
		    }
		   
		}
		</script>
	</head>

	<body onload="checkWinType();">
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>公寓管理 - 值班记录 - 一周汇总</a>
			</p>
		</div>
		<!-- 标题 end-->
		<html:form action="/data_search" method="post">
							<logic:empty name="rs">
								<br />
								<br />
								<p align="center">
									<!-- 有错误发生！ -->
								</p>
							</logic:empty>
							<logic:notEmpty name="rs">
								<input type="hidden" id="doType" name="doType"
									value="<bean:write name="doType" scope="request"/>" />
								<input type="hidden" id="pkValue" name="pkValue"
									value="<bean:write name="pkValue" scope="request"/>" />
								<input type="hidden" id="disableEle" name="disableEle" value="" />
								<input type="hidden" id="readonlyEle" name="readonlyEle"
									value="" />
								<input type="hidden" id="url" name="url" value="/sjcz/yzzbhzb.jsp" />
								<table class="formlist" border="0" align="center" style="width: 100%">
								<thead>
									<tr>
										<th colspan="4">
											<span>一周值班汇总维护</span>
										</th>
									</tr>
								</thead>
								<tbody>	
										<tr>
											<th align="right" width="20%">
												<font color="red">*</font>时间：
											</th>
											<td align="left"><html:text name='rs' property="qssj" styleId="qssj" onblur="dateFormatChg(this)" style="cursor:hand;width:80px" onclick="return showCalendar('qssj','y-mm-dd');" /> - <html:text name='rs' property="jssj" styleId="jssj" onblur="dateFormatChg(this)" style="cursor:hand;width:80px" onclick="return showCalendar('jssj','y-mm-dd');" />
											</td>
											<logic:notPresent name="syzy">
											<th align="right"  width="15%">
												<font color="red">*</font>楼栋名称：
											</th>
											<td align="left">
												<html:select name="rs" property="lddm" style="width:150px"
													styleId="lddm">
													<html:option value=""></html:option>
													<html:options collection="ldList" property="lddm"
														labelProperty="ldmc" />
												</html:select>
											</td>
											</logic:notPresent>
											<logic:present name="syzy">
												<th align="right">
												<font color="red">*</font>汇总人：
											</th>
											<td align="left">
												<html:text name="rs" property="hzr"/>
											</td>
											</logic:present>
										</tr>
										<logic:present name="syzy">
										<tr>
											<th align=right>校区：</th>
											<td>
												<html:select name="rs" property="xqdm" style="width:150px">
													<html:option value=""></html:option>
													<html:options collection="xiaoqquList" property="dm"
														labelProperty="xqmc" />
												</html:select>
											</td>
											<th></th>
											<td></td>
										</tr>
										</logic:present>
										<tr>
											<th align="right">
												主要值班记录汇总：<br>
												<font color="red"><限2000字内></font>
											</th>
											<td colspan="3">
												<html:textarea name='rs' property='zyzbjlhz' style="width:95%"
													rows='6' />
											</td>
										</tr>
										<tr>
											<th align="right">
												相关部门处理情况：<br>
												<font color="red"><限2000字内></font>
											</th>
											<td colspan="3">
												<html:textarea name='rs' property='xgbmclqk' style="width:95%"
													rows='6' />
											</td>
										</tr>
										<tr>
											<th align="right">
												院领导批示：<br>
												<font color="red"><限50字内></font>
											</th>
											<td colspan="3">
												<html:textarea name='rs' property='yldps' style="width:95%"
													rows='5' />
											</td>
										</tr>
										</tbody>
										<tfoot>
											<tr bgcolor="EEF4F9" align="center">
												<td colspan="4">
													<div class="btn">
															<button type="button" id="buttonSave" 
															onclick="to_save()"
																style="width: 80px">
																保	存
															</button>
														&nbsp;&nbsp;
														<button type="button" id="buttonClose" onclick="Close();return false;"
															style="width: 80px">
															关	闭
														</button>
													</div>
												</td>
											</tr>
										</tfoot>
									</table>
							</logic:notEmpty>
			<logic:present name="result">
				<logic:equal value="true" name="result">
					<script>
						alert('操作成功！');
						dialogArgumentsQueryChick();
						window.close();
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
