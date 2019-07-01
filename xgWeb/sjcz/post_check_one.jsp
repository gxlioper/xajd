<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- 头文件 -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script type="text/javascript" src="js/xgutil.js"></script>
	<script language="javascript">
		function save(){
			if(filedNotNull(['spbcbz','sqsyrs','sqsyknss'])){
				//判断困难生是否低于标准	
				if (Math.round(parseFloat(val('sqsyrs'))*parseFloat(val('knsbl'))/100)>parseFloat(val('sqsyknss'))) {
					alert("使用困难生数低于标准！");
					return false;
				}
				if(parseInt(val('sqsyrs'))<parseInt(val('sqsyknss'))){
					alert("使用困难生数多于需要人数！");
					return false;
				}	
				refreshForm('/xgxt/postChkOne.do?act=save');
			}else{
				alert('请将带*号的项目填写完整！');
				return false;
			}
		}
	</script>
</head>
	<body onload="checkWinType();document.all('buttonClose').focus()">
		<html:form action="/data_search" method="post">
			<input type="hidden" name="pkVal" value="<bean:write name="rs" property="gwdm||gwsbsj"/>" />
			<input type="hidden" name="xhStr" value="<bean:write name="xhStr" />" />
			<input type="hidden" name="xxyjStr" value="" />
			<input type="hidden" name="knsbl" id="knsbl" value="${knsbl}" />
			<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em>
				<a>
					<%--长沙民政--%>
					<logic:equal value="10827" name="xxdm">
						学生义工 - 审核 - 岗位审核 - 单个审核
					</logic:equal>
					<logic:notEqual value="10827" name="xxdm">
						勤工助学 - 审核 - 岗位审核 - 单个审核
					</logic:notEqual>
				</a>
			</p>
		</div>
		<div class="tab">
		  <table width="100%" border="0" class="formlist">
				<thead>
					<tr>
						<th colspan="4">
							<span>单个岗位审核</span>
						</th>
					</tr>
				</thead>
				<tbody>
				<tr>
					<th>校区</th>
					<td>
						<bean:write name="rs" property="xqmc" />
					</td>
					<th>年度</th>
					<td>
						<bean:write name="rs" property="nd" />
					</td>
				</tr>
				<tr>
					<th>岗位名称</th>
					<td>
						<bean:write name="rs" property="gwdm" />
					</td>
					<th>学年</th>
					<td>
						<bean:write name="rs" property="xn" />
					</td>
				</tr>
				<tr>
					<th>用人单位</th>
					<td>
						<bean:write name="rs" property="yrdwmc" />
					</td>
					<th>学期</th>
					<td>
						<bean:write name="rs" property="xueqimc" />
					</td>
				</tr>
				<tr>
					<th>申报时间</th>
					<td>
						<bean:write name="rs" property="gwsbsj" />
					</td>
					<th>岗位性质</th>
					<td>
						<bean:write name="rs" property="gwxzmc" />
					</td>
				</tr>
				<tr>
					<th>工作开始时间</th>
					<td>
						<bean:write name="rs" property="gzksrq" />
					</td>
					<th>计酬标准</th>
					<td>
						<bean:write name="rs" property="jybcbz" />元
						(<bean:write name="rs" property="jcfsmc" />)
					</td>
				</tr>
				<tr>
					<th>工作结束时间</th>
					<td>
						<bean:write name="rs" property="gzjsrq" />
					</td>
					<th>需要人数</th>
					<td>
						<bean:write name="rs" property="xyrs" />
					</td>
				</tr>
				<!-- 浙江工业大学之江学院 -->
				<logic:equal value="13275" name="xxdm">
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
				</logic:equal>
				<tr>
					<th>使用困难生数</th>
					<td>
						<bean:write name="rs" property="syknss" />
					</td>
					<th>工作时间</th>
					<td>
						<bean:write name="rs" property="gzsj" />
					</td>
				</tr>
				<!-- 重庆科技学院 -->
				<logic:equal value="11551" name="xxdm">
					<tr>
						<th>工作时间</th>
						<td colspan="3">
							<logic:present name="whkxList">
								<table id="tbSj" class="formlist">
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
											<td align="center">
												<input type="checkbox" name="${kxsj.sjIndex}1" value="1" />
											</td>
											<td align="center">
												<input type="checkbox" name="${kxsj.sjIndex}2" value="1" />
											</td>
											<td align="center">
												<input type="checkbox" name="${kxsj.sjIndex}3" value="1" />
											</td>
											<td align="center">
												<input type="checkbox" name="${kxsj.sjIndex}4" value="1" />
											</td>
											<td align="center">
												<input type="checkbox" name="${kxsj.sjIndex}5" value="1" />
											</td>
											<td align="center">
												<input type="checkbox" name="${kxsj.sjIndex}6" value="1" />
											</td>
											<td align="center">
												<input type="checkbox" name="${kxsj.sjIndex}7" value="1" />
											</td>
										</tr>
									</logic:iterate>
								</table>
								<!-- begin 获取已经存在的空闲时间信息 -->
								<logic:present name="kxbz">
									<input type="hidden" id="kxbzNum"
										value="<bean:write name="kxbzNum"/>" />
									<logic:iterate id="kxinfo" name="kxbz" indexId="index">
										<input type="hidden" id="kxinfo${index}"
											value="${kxinfo.xq}${kxinfo.sj}" />
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
							</logic:present>
						</td>
					</tr>
					<tr>
						<th>总经费</th>
						<td colspan="3">
							<bean:write name="rs" property="zjf" />
						</td>
					</tr>
					<tr>
						<th>自筹经费</th>
						<td colspan="3">
							<bean:write name="rs" property="zcjf" />
						</td>
					</tr>
				</logic:equal>
				<%--非重庆科技--%>
				<logic:notEqual value="11551" name="xxdm">
					<%--上海工程技术大学--%>
					<logic:equal value="10856" name="xxdm">
						<tr>
						<th>工作时间</th>
						<td colspan="3">
							<logic:present name="whkxList">
								<table id="tbSj" class="formlist">
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
											<td align="center">
												<input type="checkbox" name="${kxsj.sjIndex}1" value="□" />
											</td>
											<td align="center">
												<input type="checkbox" name="${kxsj.sjIndex}2" value="□" />
											</td>
											<td align="center">
												<input type="checkbox" name="${kxsj.sjIndex}3" value="□" />
											</td>
											<td align="center">
												<input type="checkbox" name="${kxsj.sjIndex}4" value="□" />
											</td>
											<td align="center">
												<input type="checkbox" name="${kxsj.sjIndex}5" value="□" />
											</td>
											<td align="center">
												<input type="checkbox" name="${kxsj.sjIndex}6" value="□" />
											</td>
											<td align="center">
												<input type="checkbox" name="${kxsj.sjIndex}7" value="□" />
											</td>
										</tr>
									</logic:iterate>
								</table>
								<!-- begin 获取已经存在的空闲时间信息 -->
								<logic:present name="kxbz">
									<input type="hidden" id="kxbzNum"
										value="<bean:write name="kxbzNum"/>" />
									<logic:iterate id="kxinfo" name="kxbz" indexId="index">
										<input type="hidden" id="kxinfo${index}"
											value="${kxinfo.xq}${kxinfo.sj}" />
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
							</logic:present>
						</td>
					</tr>
						<tr>
							<th>总经费</th>
							<td colspan="3">
								<bean:write name="rs" property="zjf" />
							</td>
						</tr>
					</logic:equal>
					<%--非上海工程技术大学--%>
					<logic:equal value="10491" name="xxdm">
						<tr>
		<%--							<th>总经费</th>--%>
		<%--							<td>--%>
		<%--								<bean:write name="rs" property="zjf" />--%>
		<%--							</td>--%>
							<th>负责人</th>
							<td colspan="3">
								<bean:write name="rs" property="fzr" />
							</td>
						</tr>
					</logic:equal>
					</logic:notEqual>
				<tr>
					<th><span class="red">*</span>审批报酬标准</th>
					<td>
						<html:text name="rs" property="spbcbz" style="width:130px" maxlength="20" onkeyup="value=value.replace(/[^(\d|\.)]/g,'') "></html:text>
						元
						(<bean:write name="rs" property="jcfsmc" />)
					</td>
					<th><span class="red">*</span>审批使用人数</th>
					<td>
						<html:text name="rs" property="sqsyrs" style="width:130px" maxlength="4" onkeyup="value=value.replace(/[^\d]/g,'') "></html:text>
						人
					</td>
				</tr>
				<tr>
					<th><span class="red">*</span>审批困难生数</th>
					<td>
						<html:text name="rs" property="sqsyknss" style="width:130px" maxlength="4" onkeyup="value=value.replace(/[^\d]/g,'') "></html:text>
						人
					</td>
					<th>审核</th>
					<td>
						<html:select name="rs" property="yesNo">
							<html:options collection="chkList" property="en"
								labelProperty="cn" />
						</html:select>
					</td>
				</tr>
				<tr>
					<th>工作内容</th>
					<td colspan="3">
						<bean:write name="rs" property="gznr" />
					</td>
				</tr>
				<%--中国地质大学--%>
				<logic:equal value="10491" name="xxdm">
					<tr>
						<th>工作地址</th>
						<td colspan="3">
							<bean:write name="rs" property="gzdd" />
						</td>
					</tr>
				</logic:equal>
				<!--非淮阴工学院-->
				<logic:notEqual value="11049" name="xxdm">
					<tr>
						<th>用人单位意见</th>
						<td colspan="3">
							<bean:write name="rs" property="sqdwyj" />
						</td>
					</tr>
				</logic:notEqual>
				<!--end非淮阴工学院-->
				<%--井冈山大学--%>
				<logic:equal value="10419" name="xxdm">
					<tr>
						<th>岗位要求</th>
						<td colspan="3">
							<bean:write name="rs" property="gwyq" />
						</td>
					</tr>
				</logic:equal>
				<%--西北二民院自定义字段--%>
				<logic:notEmpty name="filedList">
					<thead>
						<tr>
							<th style="cursor:hand" colspan="4">
								<span>申请附加信息</span>
							</th>
						</tr>
					</thead>
					<input type="hidden" id="rsNum" name="rsNum" value="<bean:write name="rsNum"/>" />
					<logic:iterate id="filed" name="filedList" indexId="index">
						<tr>
							<td>
								<bean:write name="filed" property="zdmc" />
								：
							</td>
							<td colspan="3">
								<bean:write name="rs" property="${filed.zddm}" />
							</td>
						</tr>
					</logic:iterate>
				</logic:notEmpty>
				<tr>
					<th>学生处意见</th>
					<td colspan="3">
						<html:textarea name="rs" property="xgbyj" cols="60" rows="3" styleId="xscyj" onblur="chLeng(this,'150')"></html:textarea>
					</td>
				</tr>
				</tbody>
				<tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项;<span class="red">困难生比例不能低于${knsbl}%</span></div>
			          <div class="btn">
			            <button type="button" class="button2"
							onclick="save();return false;"
							style="width:80px" id="buttonSave">
							保 存
						</button>
						<button type="button" class="button2" onclick="Close();return false;" style="width:80px"
							id="buttonClose">
							关 闭
						</button>
			          </div>
			        </td>
			      </tr>
			    </tfoot>
			</table>
			</div>

		</html:form>
		<logic:equal value="view" name="result">
			<script>
				alert("操作成功!");
				//tipsWindown("弹出层标题","id:testID","340","120","true","","true","id");
				Close();
				if(window.dialogArguments){
					window.dialogArguments.document.getElementById('search_go').click();
				}
			</script>
		</logic:equal>
	</body>
</html>
