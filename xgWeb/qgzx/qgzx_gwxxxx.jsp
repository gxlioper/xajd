<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- 头文件 -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/xgutil.js"></script>
</head>
<body onload="stringFormat(['bz','sqdwyj','sqbg','ryyq','gznr'],40)">		
	<html:form action="/qgzxLogic" method="post">
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em>
				<a>
					<%--长沙民政--%>
					<logic:equal value="10827" name="xxdm">
						学生义工 - 岗位发布 - 岗位详细信息
					</logic:equal>
					<logic:notEqual value="10827" name="xxdm">
						勤工助学 - 岗位发布 - 岗位详细信息
					</logic:notEqual>
				</a>
			</p>
		</div>
		
		<logic:empty name="rs">
			<br />
			<br />
			<p align="center">
				有错误发生！
			</p>
		</logic:empty>
		<logic:notEmpty name="rs">
			<div class="tab">
		  	<table width="100%" border="0" class="formlist">
				<thead>
					<tr align="center">
						<th colspan="4">
							<span>岗位信息</span>
						</th>
					</tr>
				</thead>
				<tbody>
				<tr>
					<th>校区</th>
					<td>
						${rs.xqmc}
					</td>
					<th>岗位名称</th>
					<td>
						${rs.gwdm}
					</td>
				</tr>
				<tr>
					<th>岗位性质</th>
					<td>
						${rs.gwxzmc}
					</td>
					<th>用人单位</th>
					<td>
						${rs.yrdwmc}
					</td>
				</tr>
				<tr>
					<th>学年</th>
					<td>
						${rs.xn}
					</td>
					<th>年度</th>
					<td>
						${rs.nd}
					</td>

				</tr>
				<tr>
					<th>工作开始日期</th>
					<td>
						${rs.gzksrq}
					</td>
					<th>工作结束日期</th>
					<td>
						${rs.gzjsrq}
					</td>
				</tr>
				<!-- 浙江工业大学之江学院 -->

					<tr>
						<th>
							申请开始时间
						</th>
						<td>
							${rs.sqkssj }
						</td>
						<th>
							申请结束时间
						</th>
						<td>
							${rs.sqjssj }
						</td>
					</tr>

				<tr>
					<th>需求人数</th>
					<td>
						<!-------------重庆科技学院----------------->
						<logic:equal value="11551" name="xxdm">								
							${rs.sqsyrs}
						</logic:equal>
						<logic:notEqual value="11551" name="xxdm">
							${rs.xyrs}
						</logic:notEqual>
					</td>
					<th>使用困难生数</th>
					<td height="22" align="left">
						<!-------------重庆科技学院----------------->
						<logic:equal value="11551" name="xxdm">
							${rs.sqsyknss}
						</logic:equal>
						<logic:notEqual value="11551" name="xxdm">
							<!-- 长沙民政 -->
							<logic:equal value="10827" name="xxdm">
								${xyknsrs}
							</logic:equal>
							<logic:notEqual value="10827" name="xxdm">
								${rs.syknss}
							</logic:notEqual>
						</logic:notEqual>
					</td>
				</tr>
				<tr>
					<th>计酬方式</th>
					<td>
						<logic:notEqual value="11417" name="xxdm">
							${rs.jcfsmc}
						</logic:notEqual>
						<logic:equal value="11417" name="xxdm">								
							按小时
						</logic:equal>
					</td>
					<logic:notEqual value="11417" name="xxdm">
						<th>建议报酬标准</th>
						<td>
							<!-- 重庆科技学院 -->
							<logic:equal value="11551" name="xxdm">
									${rs.spbcbz}
							</logic:equal>
							<logic:notEqual value="11551" name="xxdm">
								${rs.jybcbz}
							</logic:notEqual>
							元
						</td>
					</logic:notEqual>
					<logic:equal value="11417" name="xxdm">
						<th>报酬标准</th>
						<td>
							${mxsbc}元/小时
						</td>
					</logic:equal>
				</tr>
				<%--非重庆科技学院--%>
				<logic:notEqual value="11551" name="xxdm">
					<%--非浙江机电职业技术学院--%>
					<logic:notEqual value="12861" name="xxdm">
						<tr>
							<th>工作时间</th>
							<td>
								${rs.gzsj}
							</td>
							<%--非北京联合大学--%>
							<logic:notEqual value="11417" name="xxdm">
								<th>联系电话</th>
								<td >
									${rs.lxdh}
								</td>
							</logic:notEqual>
							<%--北京联合大学--%>
							<logic:equal value="11417" name="xxdm">
								<th>联系人</th>
								<td>
									${rs.lxr}
								</td>
							</logic:equal>
						</tr>
					</logic:notEqual>

					<!--中国地质大学-->
					<logic:equal value="10491" name="xxdm">
						<tr>
							<th>负责人</th>
							<td colspan="3">
								${rs.fzr}
							</td>
						</tr>
					</logic:equal>
					<!--end中国地质大学-->

					<%--浙江机电职业技术学院--%>
					<logic:equal value="12861" name="xxdm">
						<tr>
							<th>联系电话</th>
							<td colspan="3">
								${rs.lxdh}
							</td>
						</tr>
						<tr>
							<th>工作时间</th>
							<tdcolspan="3">
								${rs.gzsj}	
							</td>
						</tr>
					</logic:equal>
					<%--中国地质大学--%>
					<logic:equal value="10491" name="xxdm">
						<tr>
							<th>工作地点</th>
							<td colspan="3">
								${rs.gzdd}
							</td>
						</tr>
					</logic:equal>
					<!-- 井冈山大学 -->
					<logic:equal value="10419" name="xxdm">
						<tr>
							<th>工作地点</th>
							<td colspan="3">
								${rs.gzdd}
							</td>
						</tr>
						<tr>
							<th>学生审核条件设置</th>
							<td colspan="3">
								<logic:iterate id="jgsrs" name="jgsshList" indexId="index">
									<input type="checkbox" value=""
										id="<bean:write name="jgsrs"  property="key"/>"
										name="<bean:write name="jgsrs" property="key"/>" />
									<bean:write name="jgsrs" property="columnName" />
								</logic:iterate>
							</td>
						</tr>
					</logic:equal>
					<!-- 井冈山大学 (是否是显示详细数据) -->
					<logic:present name="jgsTempSQL">
						<input type="hidden" id="jgsTempSQLNum" value="${jgsTempSQLNum}" />
						<logic:iterate id="jgsSql" name="jgsTempSQL" indexId="index">
							<input type="hidden" id="tmpkey${index}"
								value="<bean:write name="jgsSql"/>" />
						</logic:iterate>
						<script>
						var length = document.getElementById('jgsTempSQLNum').value;
						for(var i=0; i<parseInt(length); i++){
							var id =  document.getElementById("tmpkey"+i).value;
							if(id == "1"){
								document.getElementById("key" + i).checked="checked";
							}
							
						}
					</script>
					</logic:present>
					<%--浙江大学宁波理工学院--%>
					<logic:equal value="13022" name="xxdm">
						<tr>
							<th>每月勤工助学时间</th>
							<td>
								${rs.myqgzxsj}
							</td>
							<th>考核人</th>
							<td>
								${rs.fzr}
							</td>
						</tr>
						<tr>
							<th>特殊要求</th>
							<td>
								${rs.tsyq}
							</td>
							<th>是否需学生处勤工助学中心代为招聘</th>
							<td>
								${rs.dwzp}
							</td>
						</tr>
						<tr>
							<th>招聘面试时间</th>
							<td colspan="3">
								${rs.mssj}
							</td>
						</tr>
						<tr>
							<th>人员落实情况</th>
							<td colspan="3">
								${rs.rqlsqk}
							</td>
						</tr>
					</logic:equal>
					<tr>
						<th>工作内容</th>
						<td colspan="3" id="gznr">
							${rs.gznr}
						</td>
					</tr>
				</logic:notEqual>

				<logic:equal value="11551" name="xxdm">
					<tr>
						<th>联系人</th>
						<td>
							${rs.lxr}
						</td>
						<th>联系电话</th>
						<td>
							${rs.lxdh}
						</td>
					</tr>
					<tr>
						<th>自筹经费</th>
						<td>
							${rs.zcjf}
						</td>
						<th></th>
						<td>
						</td>
					</tr>

					<tr>
						<th>工作时间</th>
							<td colspan="3">
								<table id="tbSj" class="formlist">
									<tbody>									
									<tr>
										<td align="center">
											时间
										</td>
										<td>
											星期一
										</td>
										<td>
											星期二
										</td>
										<td>
											星期三
										</td>
										<td>
											星期四
										</td>
										<td>
											星期五
										</td>
										<td>
											星期六
										</td>
										<td>
											星期日
										</td>
									</tr>
									<logic:iterate id="kxsj" name="whkxList">
										<tr>
											<td>
												${kxsj.sj}
											</td>
											<td>
												<input type="checkbox" name="${kxsj.sjIndex}1" value="1" id="${kxsj.sjIndex}1"/>
											</td>
											<td>
												<input type="checkbox" name="${kxsj.sjIndex}2" value="1" id="${kxsj.sjIndex}2"/>
											</td>
											<td>
												<input type="checkbox" name="${kxsj.sjIndex}3" value="1" />
											</td>
											<td>
												<input type="checkbox" name="${kxsj.sjIndex}4" value="1" />
											</td>
											<td>
												<input type="checkbox" name="${kxsj.sjIndex}5" value="1" />
											</td>
											<td>
												<input type="checkbox" name="${kxsj.sjIndex}6" value="1" />
											</td>
											<td>
												<input type="checkbox" name="${kxsj.sjIndex}7" value="1" />
											</td>
										</tr>
									</logic:iterate>
								</table>
							</td>
							<!-- begin 获取已经存在的空闲时间信息 -->
							<logic:present name="kxbz">
								<input type="hidden" id="kxbzNum" value="<bean:write name="kxbzNum"/>" />
								<logic:iterate id="kxinfo" name="kxbz" indexId="index">
									<input type="hidden" id="kxinfo${index}" value="${kxinfo.xq}${kxinfo.sj}" />
								</logic:iterate>
								<script>
									var length = document.getElementById('kxbzNum').value;
									for(var i=0; i<parseInt(length); i++){
										var id =  document.getElementById("kxinfo"+i).value;
										document.getElementById(id).checked="checked";
									}
								</script>
							</logic:present>
							<!-- end 获取已经存在的空闲时间信息 -->
					</tr>
				</logic:equal>
				<tr>
					<th>人员要求</th>
					<td colspan="3" id="ryyq">
						${rs.ryyq}
					</td>
				</tr>
				<logic:notPresent name="showshgc">
					<%--重庆科技学院--%>
					<logic:notEqual value="11551" name="xxdm">
						<%--浙江机电职业技术学院--%>
						<logic:notEqual value="12861" name="xxdm">
							<tr>
								<th>申请报告</th>
								<td colspan="3" id="sqbg">
									${rs.sqbg}
								</td>
							</tr>
						</logic:notEqual>
					</logic:notEqual>
				</logic:notPresent>
				<%--重庆科技学院--%>
				<logic:equal value="11551" name="xxdm">
					<tr>
						<th>申请原因及单位意见</th>
						<td colspan="3"/>
							${rs.sqdwyj}
						</td>
					</tr>
				</logic:equal>
				<%--end重庆科技学院--%>
				<!--非重庆科技学院-->
				<logic:notEqual value="11551" name="xxdm">
					<!--非淮阴工学院-->
					<logic:notEqual value="11049" name="xxdm">
						<tr>
							<th>申请单位意见</th>
							<td colspan="3" id="sqdwyj">
								${rs.sqdwyj}
							</td>
						</tr>
					</logic:notEqual>
					<!--end非淮阴工学院-->	
				</logic:notEqual>
				<!--end非重庆科技学院-->
				<tr>
					<th>备注</th>
					<td colspan="3" id="bz">
						${rs.bz}
					</td>
				</tr>
				</tbody>
				<!--西北二民院自定义字段信息-->
				<logic:notEmpty name="filedList">				
				<thead>
				<tr>
					<th style="cursor:hand" colspan="4">
						<span>申请附加信息</span>
					</th>
				</tr>
				</thead>
				<tbody>
				<input type="hidden" id="rsNum" name="rsNum" value="<bean:write name="rsNum"/>"/>
				<logic:iterate id="filed" name="filedList" indexId="index">
					<tr>
						<td>								
							<bean:write name="filed" property="zdmc"/>：
						</td>
						<td colspan="3">
							<bean:write name="rs" property="${filed.zddm}"/>
						</td>							
					</tr>
				</logic:iterate>
				
				</tbody>
				</logic:notEmpty>
				<!--end西北二民院自定义字段信息-->

				<!--显示参加岗位的学生列表-->
				<%@ include file="/qgzx/gwxsxx.jsp"%>			
			</table>
		</logic:notEmpty>
	</html:form>
</body>
</html>
