<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="js/check.js"></script>
		<script type="text/javascript">
			//学生岗位申请
			function xsgwsq(obj){
				obj.parentNode.parentNode.click();
				var url = "qgzx_gwsqwh.do?method=xssqUpdate";
				url += "&gwdmsbsj="+curr_row.getElementsByTagName('input')[0].value;
				//showTopWin(url,800,600);
				refreshForm(url);
			}
			
			function bksq() {
				alert("只有困难（贫困）生才可能申请该岗位！");
				return false;
			}
			function hmd() {
				alert("你无权申请该岗位，请联系管理员！");
				return false;
			}
		</script>
	</head>
	<body onload="">
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>${title }</a>
			</p>
			<p class="help">
				<a href="#" onclick="return false;" onmousedown ="showHelpDiv();">使用帮助</a>
			</p>
		</div>			
		<!-- 标题 end-->
		<!-- 提示信息 START-->
		<div class="prompt" id="div_help" style="display: none">
			<h3>
				<span>提示：</span>
			</h3>
			<p>
				1.结果集中列出当前学年，学期，年度下符合申请时间内的项目。</br>
				<logic:equal name="userType" value="stu">
				2.如果您不满足该项目的相关条件，则该项目<font color="blue">不可申请</font>，点击可查看详细。</br>
				
				3.如果您想申请该项目，请点击<font color="blue">申请</font>操作，在展示页面填写相关申请内容。</br>
				4.如果您已经申请了某项目，并且该项目尚未被任一岗位审核，可以点击<font color="blue">修改</font>。
				</logic:equal>
				<logic:notEqual name="userType" value="stu">
				2.如果您想替某学生申请某项目，请点击<font color="blue">申请</font>，在展示页面中挑选学生，完成操作。</br>
				</logic:notEqual>
			</p>
			<a class="close" title="隐藏"  onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- 提示信息 end-->	
		
		<html:form action="/qgzx_gwsqwh" method="post">
			<!-- 黑名单标记 -->
			<input type="hidden" name="hmd" id="hmd" value="${hmd }"/>
			
			<div class="toolbox">
			<div class="searchtab">
				<table>
					<tbody>
						<tr>
							<th>学年</th>
							<td>
								<html:select property="xn" style="width: 150px" styleId="xn" disabled="true">
									<html:options collection="xnList" property="xn" labelProperty="xn" />
								</html:select>
							</td>
							<th>学期</th>
							<td>
								<html:select property="xq" style="width: 150px" styleId="xq" disabled="true">
									<html:option value=""></html:option>
									<html:options collection="xqList" property="xqdm" labelProperty="xqmc" />
								</html:select>
							</td>
							<th>年度</th>
							<td>
								<html:select property="nd" style="width: 150px" styleId="nd" disabled="true">
									<html:options collection="ndList" property="nd" labelProperty="nd" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>岗位性质</th>
							<td>
								<html:select  property="gwxz" style="width:150px" styleId="gwxz">
							<html:option value=""></html:option>	
							<html:options collection="gwxzList" property="gwxzdm" labelProperty="gwxzmc" />
						</html:select>
							</td>	
							<th>用人单位</th>
							<td>
								<html:select  property="sqdw" styleId="sqdw" style="width:150px">
							<html:options collection="yrdwList" property="dm" labelProperty="mc" />
						</html:select>
							</td>
							<th>工作开始时间</th>
							<td>
								<html:text property="gzksrq" styleId="gzksrq"
							onblur="dateFormatChg(this)" style="cursor:hand;width: 145px"
							onclick="return showCalendar('gzksrq','y-mm-dd');" />
							</td>
						</tr>
						<tr>
							<th>工作结束时间</th>
							<td>
								<html:text property="gzjsrq" styleId="gzjsrq"
							onblur="dateFormatChg(this);" style="cursor:hand;width: 145px"
							onclick="return showCalendar('gzjsrq','y-mm-dd');" />
							</td>
							<th></th>
							<td>
								
							</td>
							<th></th>
							<td>
							
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="6">
								<div class="btn">
									<input type="hidden" name="act" value="go" />
									<button type="button" id="search_go" onclick="if (checkSearchTj('gzksrq','gzjsrq')) {refreshForm('qgzx_gwsqwh_xssq.do')}">
										查 询
									</button>
									 <button type="button" class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
										重 置
							 		</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
			</div>
			<div class="formbox">
				<logic:empty name="rs">
				    <h3 class="datetitle_01">
				    <span>
				    	查询结果&nbsp;&nbsp;
							<font color="red">未找到任何记录！</font> 
				    </span>
				    </h3>
				 </logic:empty>
				<logic:notEmpty name="rs">
					<h3 class="datetitle_01">
						<span>
							查询结果&nbsp;&nbsp;<font color="blue">单击表头可以排序;</font> 
						</span>
					</h3>
					<table width="99%" id="rsTable" class="dateline">
						<thead>
							<tr style="cursor:hand" align="left">
								<logic:iterate id="tit" name="topTr" offset="1">
									<td id="${tit.en }/>"
										onclick="tableSort(this)">&nbsp;
										${tit.cn }
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<!-- 学生用户申请页面 begin -->
						<logic:equal value="stu" name="userType"> 
							<logic:iterate name="rs" id="s">
							<tr onclick="rowOnClick(this);" 
								ondblclick="" style="cursor:hand;" align="left">
								<td>
									<input type="hidden" value="${s.pk }" />
									${s.gwxzmc }	
								</td>
								<td>
									${s.yrdwmc }
								</td>
								<td>
									${s.gwdm}
								</td>
								<td>
									${s.sqsyrs }
								</td>
								<td>
									${s.sqsyknss}
								</td>
								<td>
									${s.sqjssj}
								</td>
								<td>
									${s.shzt}
								</td>
								<td>
									<logic:equal value="ksq" name="s" property="cz">
										<button type="button" class="btn_01" onclick="xsgwsq(this);" style="width:80px;height: 20px">
											申&nbsp;&nbsp;&nbsp;&nbsp;请
										</button>
									</logic:equal>
									<logic:equal value="xg" name="s" property="cz">
										<button type="button" class="btn_01" onclick="xsgwsq(this);" style="width:80px;height: 20px">
											修&nbsp;&nbsp;&nbsp;&nbsp;改
										</button>
									</logic:equal>
									<logic:equal value="bksq" name="s" property="cz">
										<button type="button" class="btn_01" onclick="bksq()" style="width:80px;height: 20px">
											不可申请
										</button>
									</logic:equal>
									<logic:equal value="hmd" name="s" property="cz">
										<button type="button" class="btn_01" onclick="hmd()" style="width:80px;height: 20px">
											不可申请
										</button>
									</logic:equal>
									<logic:equal value="ck" name="s" property="cz">
										<button type="button" class="btn_01" onclick="xsgwsq(this);" style="width:80px;height: 20px">
											查&nbsp;&nbsp;&nbsp;&nbsp;看
										</button>
									</logic:equal>
								</td>
							</tr>
						</logic:iterate>
						</logic:equal>
						<!-- end -->
						
						<!-- 非学生用户申请页面     begin -->
						<logic:notEqual value="stu" name="userType">
							<logic:iterate name="rs" id="s">
							<tr onclick="rowOnClick(this);" 
								ondblclick="" style="cursor:hand;" align="left">
								<td>
									<input type="hidden" value="${s.pk }" />
									${s.gwxzmc }	
								</td>
								<td>
									${s.yrdwmc }
								</td>
								<td>
									${s.gwdm}
								</td>
								<td>
									${s.sqsyrs }
								</td>
								<td>
									${s.sqsyknss}
								</td>
								<td>
									${s.sqjssj}
								</td>
								<td>
									<button type="button" class="btn_01" onclick="xsgwsq(this);" style="width:80px;height: 20px">
										申&nbsp;&nbsp;&nbsp;&nbsp;请
									</button>
								</td>
							</tr>
						</logic:iterate>
						</logic:notEqual>
						<!-- end -->
					</table>
				</logic:notEmpty>
			</div>
		</html:form>
	</body>
</html>