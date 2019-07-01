<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script language="javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<style type="text/css">	
			.demo_data2 {
			 	/*border: 1px solid #DEDEDE;*/
				display: inline;
			    float: left;
			    height: 15px;
			    margin: 0px 0px 0;
			    padding: 0px;
			    width: 160px;
			}
		</style>
		<script type="text/javascript" src="xsgzgl/xlzx/tsxsgl/js/tsxsDetail.js"></script>
	</head>
	<body onload="init();">
		<html:form action="/xlzx_tsxs" method="post">
		<!-- <input type="hidden" id="url" name="url" value="xlzx_tsxs.do?method=tsxsDetail" /> -->
		<input type="hidden" name="id" id="id" value="${tsxsInfoList.id}" />
		<input type="hidden" name="knlxdm" id="knlxdm" value="${tsxsInfoList.knlxdm}" />
		<input type="hidden" name="xxdm" id="xxdm" value="${xxdm}" />
		<input type="hidden" name="doType" id="doType" value="${doType}" />
			<div style='width:100%; overflow-y:auto;overflow-x:hidden'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>学生基本信息</span>
							</th>
						</tr>
					</thead>
					<logic:equal name="doType" value="view">
						<%@ include file="/xsgzgl/comm/bdpz/viewStudent.jsp" %>
					</logic:equal>
					<logic:notEqual name="doType" value="view">
						<logic:equal name="doType" value="update"><%@ include file="/xsgzgl/comm/bdpz/viewStudent.jsp" %></logic:equal>
						<logic:notEqual name="doType" value="update"><%@ include file="/xsgzgl/comm/bdpz/selectStudent.jsp" %></logic:notEqual>
					</logic:notEqual>
					<tbody id="tbody_jbxx">
						<tr>
							<logic:notEqual name="xxdm" value="10704">
								<th width="16%">
									<logic:notEqual name="doType" value="view"><span class="red">*</span></logic:notEqual>困难类型
								</th>
							</logic:notEqual>
							<logic:equal name="xxdm" value="10704">
								<th width="16%">
									<logic:notEqual name="doType" value="view"></span></logic:notEqual>预警程度
								</th>
							</logic:equal>
							<td width="84%" colspan="3">
							<logic:equal name="doType" value="view">
								${tsxsInfoList.knlxmc}
							</logic:equal>
							<logic:notEqual name="doType" value="view">
								<logic:notEmpty name="knlxList">
									<logic:iterate id="s" name="knlxList">
										<div class='demo_data2'>
										<input type="checkbox" name="knlxBoxList" id="knlx<bean:write name="s" property="knlxdm"/>" value="${s.knlxdm}"/>
										<label style="cursor:pointer;" for="knlx<bean:write name="s" property="knlxdm"/>">${s.knlxmc}</label>
										</div>
									</logic:iterate>
								</logic:notEmpty>
							</logic:notEqual>
							</td>
						</tr>
						<logic:notEqual name="xxdm" value="10704">
							<tr>
								<th>			
									<logic:notEqual name="doType" value="view"><span class="red">*</span></logic:notEqual>关注状态
								</th>
								<logic:notEqual name="xxdm" value="11527">
									<td colspan="3">
										<logic:equal name="doType" value="view">
											${tsxsInfoList.gzztmc}
										</logic:equal>
										<logic:notEqual name="doType" value="view">
											<html:select property="gzzt" styleId="gzzt" value="${tsxsInfoList.gzzt}" style="width:100px">
												<html:option value="1">关注</html:option>
												<html:option value="2">取消关注</html:option>
											</html:select>
										</logic:notEqual>
									</td>
								</logic:notEqual>
									<%--湖南城市学院--%>
								<logic:equal name="xxdm" value="11527">
									<td>
										<logic:equal name="doType" value="view">
											${tsxsInfoList.gzztmc}
											<input type="hidden" id="gzzt" value="${tsxsInfoList.gzzt}"/>
										</logic:equal>
										<logic:notEqual name="doType" value="view">
											<html:select property="gzzt" styleId="gzzt" onchange="gz();" value="${tsxsInfoList.gzzt}" style="width:140px">
												<html:option value="1">学校重点关注</html:option>
												<html:option value="2">学院重点关注</html:option>
												<html:option value="3">学院预警对象</html:option>
												<html:option value="0">取消关注</html:option>
											</html:select>
										</logic:notEqual>
									</td>
									<th id="yymsTh">			
										<logic:notEqual name="doType" value="view"><span class="red">*</span></logic:notEqual>原因描述
									</th>
									<td id="yymsTd">
									<logic:notEqual name="doType" value="view">
										<html:select property="yyms" styleId="yyms" value="${tsxsInfoList.yyms}" style="width:100px">
												<html:option value="毕业">毕业</html:option>
												<html:option value="休学">休学</html:option>
												<html:option value="退学">退学</html:option>
												<html:option value="康复">康复</html:option>
											</html:select>
									</logic:notEqual>
									<logic:equal name="doType" value="view">
										${tsxsInfoList.yyms}
										
									</logic:equal>
									</td>
								</logic:equal>
							</tr>
							<logic:equal name="xxdm" value="11527">
								<tr>
									<th>周次</th>
									<td colspan="3">
										<logic:notEqual name="doType" value="view">
											<logic:notEqual name="doType" value="update">
												<html:text  property='zc' styleId="zc"  value="${tsxsInfoList.zc}" maxlength="3" onblur="onlyNumInput(this)" />		
											</logic:notEqual>
										</logic:notEqual>
										<logic:equal name="doType" value="view">
											${tsxsInfoList.zc}
										</logic:equal>
										<logic:equal name="doType" value="update">
											${tsxsInfoList.zc}
										</logic:equal>
									</td>
								</tr>
							</logic:equal>
							<tr style="height:80px;">
								<th>
									情况说明
										<logic:notEqual name="doType" value="view">
											<logic:notEqual name="xxdm" value="11527">
												<br/><font color="red"><B>(限500字)</B></font>
											</logic:notEqual>
											<%--湖南城市学院--%>
											<logic:equal name="xxdm" value="11527">
												<br/><font color="red"><B>(限2000字)</B></font>
											</logic:equal>
										</logic:notEqual>
								</th>
								<td colspan="3">
									<logic:notEqual name="doType" value="view">
										<logic:notEqual name="xxdm" value="11527">
											<html:textarea  property='qksm' styleId="qksm"  style="word-break:break-all;width:99%" value="${tsxsInfoList.qksm}" onblur="chLeng(this,500);"
												rows='4' />
										</logic:notEqual>
										<logic:equal name="xxdm" value="11527">
											<html:textarea  property='qksm' styleId="qksm"  style="word-break:break-all;width:99%" value="${tsxsInfoList.qksm}" onblur="chLeng(this,2000);"
												rows='4' />
										</logic:equal>
									</logic:notEqual>
									<logic:equal name="doType" value="view">
										${tsxsInfoList.qksm}
									</logic:equal>
								</td>
							</tr>
						</logic:notEqual>
						<logic:equal name="xxdm" value="10704">
							<tr style="height:80px;">
								<th>
									所属类型的基本情况描述
										<logic:notEqual name="doType" value="view"><br/><font color="red"><B></B></font></logic:notEqual>
								</th>
								<td colspan="3">
									<logic:notEqual name="doType" value="view">
										<html:textarea  property='jbqkms' styleId="jbqkms" style="word-break:break-all;width:99%" value="${tsxsInfoList.jbqkms}" 
											rows='4' />
									</logic:notEqual>
									<logic:equal name="doType" value="view">
										${tsxsInfoList.jbqkms}
									</logic:equal>
								</td>
							</tr>
							<tr style="height:80px;">
								<th>
									处理措施
										<logic:notEqual name="doType" value="view"><br/><font color="red"><B></B></font></logic:notEqual>
								</th>
								<td colspan="3">
									<logic:notEqual name="doType" value="view">
										<html:textarea  property='clcs' styleId="clcs" style="word-break:break-all;width:99%" value="${tsxsInfoList.clcs}"
											rows='4' />
									</logic:notEqual>
									<logic:equal name="doType" value="view">
										${tsxsInfoList.clcs}
									</logic:equal>
								</td>
							</tr>
							<tr>
								<th >
									录入时间
									<td colspan="3">
										<html:text property="lrsj"  styleId="lrsj" value="${nowTime}" onclick="return showCalendar('lrsj','yyyy-MM-dd HH:mm:ss');"  readonly="true"></html:text>
									</td>
								</th>
							</tr>
							<tr>
								<th>
									跟踪时间
								</th>
								<td colspan="3">
									<html:text property="gzsj" styleId="gzsj" style="width:90%"  maxlength="50"  value="${tsxsInfoList.gzsj}"  />
								</td>
							</tr>
							<tr style="height:80px;">
								<th>
									跟踪内容
										<logic:notEqual name="doType" value="view"><br/><font color="red"><B></B></font></logic:notEqual>
								</th>
								<td colspan="3">
									<logic:notEqual name="doType" value="view">
										<html:textarea  property='gznr' styleId="gznr" style="word-break:break-all;width:99%" value="${tsxsInfoList.gznr}" 
											rows='4' />
									</logic:notEqual>
									<logic:equal name="doType" value="view">
										${tsxsInfoList.gznr}
									</logic:equal>
								</td>
							</tr>
						  <tr>
							<th>
								附件信息
							</th>
							<td colspan="3">
								<html:hidden property="fjid" styleId="fjid"/>
								<%@ include file="/xsgzgl/comm/fileUpload/f.jsp"%>
								<script type="text/javascript">
											//调用附件 
											jQuery(function(){
												jQuery.MultiUploader({
													maxcount : ${xxdm=='10704'?10:3},
													//后缀
													accept : 'png|gif|jpg|zip|rar|doc|docx|pdf',
													//最大文件大小 单位M
													maxsize: ${xxdm=='10704'?30:10},
													//存放附件的隐藏域的id
													elementid : 'fjid'
													});
											});
										</script>
							</td>
						</tr>
								<tr style="height:80px;">
								<th>
									备注
										<logic:notEqual name="doType" value="view"><br/><font color="red"><B></B></font></logic:notEqual>
								</th>
								<td colspan="3">
									<logic:notEqual name="doType" value="view">
										<html:textarea  property='bz' styleId="bz" style="word-break:break-all;width:99%" value="${tsxsInfoList.bz}" 
											rows='4' />
									</logic:notEqual>
									<logic:equal name="doType" value="view">
										${tsxsInfoList.bz}
									</logic:equal>
								</td>
							</tr>
		   					<input type="hidden" id="dqnd" value="${dqnd }" />
		   					<th>
								<span class="red">*</span>录入时间
							</th>
		   					<td >
								<html:text property="lrsj" styleId="lrsj"   style="width:100px" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'${tsxsInfoList.lrsj}'})"  />
							</td>
							<logic:equal name="doType" value="view">
								${tsxsInfoList.lrsj}
							</logic:equal>
							
		   				</logic:equal>
		   			<logic:equal name="xxdm" value="11527">
		   				
			   				<tr>
								<th>
									附件信息
								</th>
								<td colspan="3">
									<logic:notEqual name="doType" value="view">
										<html:hidden property="fjid" styleId="fjid" value="${tsxsInfoList.fjid}"/>
										<%@ include file="/xsgzgl/comm/fileUpload/f.jsp"%>
										<script type="text/javascript">
													//调用附件 
													jQuery(function(){
														jQuery.MultiUploader({
															maxcount : 3,
															//后缀
															accept : 'png|gif|jpg|zip|rar|doc|docx|pdf',
															//最大文件大小 单位M
															maxsize: 10,
															//存放附件的隐藏域的id
															elementid : 'fjid'
															});
													});
												</script>
									</logic:notEqual>
									<logic:equal name="doType" value="view">
										<%@ include file="/xsgzgl/comm/fileUpload/f_q.jsp"%>
										<html:hidden property="filepath" value="${tsxsInfoList.fjid}" styleId="fjid"/>
										<script type="text/javascript">
											//调用附件 
											jQuery(function(){
												var gid = jQuery('#fjid').val();
												jQuery.MultiUploader_q({
													gid : gid
													});
											});
										</script>	
							</logic:equal>
								</td>
							
						</tr>
						
						
					</logic:equal>
					</tbody>
				</table>
			</div>	
			<div style="height: 35px;"></div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="3">
								<div class="bz">
										<logic:notEqual name="doType" value="view">"<span class="red">*</span>"为必填项</logic:notEqual>
								</div>
								<div class="btn">
									<button id="buttonSave" onclick="save();return false;">
										保 存
									</button>
									<button onclick="Close();return false;">
										关 闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>

		</html:form>
	</body>
</html>

