<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- 头文件 -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script type='text/javascript' src='/xgxt/dwr/interface/cqkjFunc.js'></script>
	<script language="javascript">
	function thisCommit(){
		var yesNo = document.getElementById("yesNo").value;
		var userName = document.getElementById("userName").value;
		var xxdm = document.getElementById("xxdm").value;
		var pkString = document.getElementById("pkVal").value + "!!SplitOneSplit!!";
		var userType = document.getElementById("userType").value;
		if(yesNo!=null && yesNo=='通过'){
			if(xxdm == "13742"){//宁波天一职业技术学院
				cqkjFunc.checkFprs(pkString,userType,function(data){
						if(data!=null && data.length>0){
							var message = "";
							for (i=0; i<data.length; i++){
								if(data[i]!=null && data[i]!=""){
									message = message+"\n" + data[i];
								}
							}
							if(message!=""){
								alert("无法审核通过："+message);
								return false;
							}else{
								showTips();
								refreshForm('/xgxt/postStuChkOne.do?act=save');
								if($("buttonSave")){$("buttonSave").disabled=true;}
							}						
						}
					});
			}else if(xxdm=='12703'){ //南通航运
				cqkjFunc.checkXsgwsqqk(pkString,function(mes){
					if(mes!="" && mes !=null){
						cqkjFunc.checkPostStuAudi(pkString,userName,function(data){
							if(data!=null && data.length>0){
								var message = "";
								for (i=0; i<data.length; i++){
									if(data[i]!=null && data[i]!=""){
										message = message+"\n" + data[i];
									}
								}
								if(message!=""){
									if(xxdm == "11057"){
										//浙江科技学院
										if(confirm(message+"\n您确定继续操作吗？")){
											showTips();
											refreshForm('/xgxt/postStuChkOne.do?act=save');
											if($("buttonSave")){$("buttonSave").disabled=true;}
										}							
									}else{
										alert("无法审核通过："+message);
										return false;
									}								
								}else{
									if(confirm(mes + "\n您确定继续操作吗？")){
										showTips();
										refreshForm('/xgxt/postStuChkOne.do?act=save');
										if($("buttonSave")){$("buttonSave").disabled=true;}
									}else{
										return false;
									}
								}						
							}
						});		
					}else{
						cqkjFunc.checkPostStuAudi(pkString,userName,function(data){
							if(data!=null && data.length>0){
								var message = "";
								for (i=0; i<data.length; i++){
									if(data[i]!=null && data[i]!=""){
										message = message+"\n" + data[i];
									}
								}
								if(message!=""){
									if(xxdm == "11057"){
										//浙江科技学院
										if(confirm(message+"\n您确定继续操作吗？")){
											showTips();
											refreshForm('/xgxt/postStuChkOne.do?act=save');
											if($("buttonSave")){$("buttonSave").disabled=true;}
										}							
									}else{
										alert("无法审核通过："+message);
										return false;
									}								
								}else{
									showTips();
									refreshForm('/xgxt/postStuChkOne.do?act=save');
									if($("buttonSave")){$("buttonSave").disabled=true;}
								}						
							}
						});		
					}
				});
			}else{		
				cqkjFunc.checkPostStuAudi(pkString,userName,function(data){
						if(data!=null && data.length>0){
							var message = "";
							for (i=0; i<data.length; i++){
								if(data[i]!=null && data[i]!=""){
									message = message+"\n" + data[i];
								}
							}
							if(message!=""){
								if(xxdm == "11057"){
									//浙江科技学院
									if(confirm(message+"\n您确定继续操作吗？")){
										showTips();
										refreshForm('/xgxt/postStuChkOne.do?act=save');
										if($("buttonSave")){$("buttonSave").disabled=true;}
									}							
								}else{
									alert("无法审核通过："+message);
									return false;
								}								
							}else{
								showTips();
								refreshForm('/xgxt/postStuChkOne.do?act=save');
								if($("buttonSave")){$("buttonSave").disabled=true;}
							}						
						}
					});				
				
			}
		}else{
			showTips('处理数据中，请稍候......');
			refreshForm('/xgxt/postStuChkOne.do?act=save');
			if($("buttonSave")){$("buttonSave").disabled=true;}
		}
	}
	</script>
</head>
	<body onload="checkWinType();document.all('buttonClose').focus()">
		<html:form action="/data_search" method="post">
			<input type="hidden" name="pkVal" value="<bean:write name="rs" property="xh||gwdm||sqsj"/>" id="pkVal" />
			<input type="hidden" name="gwdm" value="<bean:write name="rs" property="gwdm"/>" />
			<input type="hidden" name="xh" value="<bean:write name="rs" property="xh"/>" />
			<input type="hidden" name="pk" value="<bean:write name="rs" property="pk"/>" id="pk"/>
			<input type="hidden" id="userName" name="userName" value="<bean:write name="userName"/>" />
			<input type="hidden" id="userType" name="userType" value="<bean:write name="userType"/>" />
			<input type="hidden" id="xxdm" name="xxdm" value="${xxdm}" />
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em>
					<a>
						<%--长沙民政--%>
						<logic:equal value="10827" name="xxdm">
							学生义工 - 审核 - 学生申请审核 - 单个审核
						</logic:equal>
						<logic:notEqual value="10827" name="xxdm">
							勤工助学 - 审核 - 学生申请审核 - 单个审核
						</logic:notEqual>
					</a>
				</p>
			</div>
			
			<div class="tab">
		 	<table width="100%" border="0" class="formlist">
				<thead>
					<tr>
						<th colspan="4">
							<span>单个学生申请审核</span>
						</th>
					</tr>
				</thead>
				<tbody>
				<tr>
					<th>学号</th>
					<td>
						<bean:write name="rs" property="xh" />
					</td>
					<th>年度</th>
					<td>
						<bean:write name="rs" property="nd" />
					</td>
				</tr>

				<tr>
					<th>姓名</th>
					<td>
						<bean:write name="rs" property="xm" />
					</td>
					<th>学年</th>
					<td>
						<bean:write name="rs" property="xn" />
					</td>
				</tr>
				<tr>
					<th>性别</th>
					<td>
						<bean:write name="rs" property="xb" />
					</td>
					<th>岗位名称</th>
					<td>
						<bean:write name="rs" property="gwdm" />
					</td>
				</tr>
				<tr>
					<th>年级</th>
					<td>
						<bean:write name="rs" property="nj" />
					</td>
					<th>申请时间</th>
					<td>
						<bean:write name="rs" property="sqsj" />
					</td>
				</tr>
				<tr>
					<th><bean:message key="lable.xsgzyxpzxy" /></th>
					<td>
						<bean:write name="rs" property="xymc" />
					</td>
					<th>
						<%--浙江机电职业技术学院--%>
						<logic:equal value="12861" name="xxdm">
							是否特困生
						</logic:equal>
						<logic:notEqual value="12861" name="xxdm">						
							是否困难生
						</logic:notEqual>
					</th>
					<td>
						<bean:write name="rs" property="sfpks" />
					</td>
				</tr>
				<tr>
					<th>专业</th>
					<td>
						<bean:write name="rs" property="zymc" />
					</td>
					<th>联系电话</th>
					<td>
						<bean:write name="rs" property="lxdh" />
					</td>
				</tr>
				<%--井冈山大学--%>
				<logic:equal value="10419" name="xxdm">
					<tr>
						<th>岗位要求</th>
						<td colspan="3">
							<bean:write name="gwyq" />
						</td>
					</tr>
					<tr>
						<th>学生不符合的要求</th>
						<td colspan="3">
							<bean:write name="rs" property="tsxx" />
						</td>
					</tr>
				</logic:equal>
				<%--end井冈山大学--%>

				<%--重庆科技学院--%>
				<logic:equal value="11551" name="xxdm">
					<tr>
						<th>可参加勤工助学时间</th>
						<td colspan="3">
							<logic:notEmpty name="kxList">
								<table border="0" cellpadding="0" cellspacing="0" class="formlist">
									<thead>
									<tr>
										<th align="center">
											时间
										</th>
										<th>
											星期一
										</th>
										<th>
											星期二
										</th>
										<th>
											星期三
										</th>
										<th>
											星期四
										</th>
										<th>
											星期五
										</th>
										<th>
											星期六
										</th>
										<th>
											星期日
										</th>
									</tr>
									</thead>
									<tbody>
									<logic:iterate id="kxsj" name="kxList">
										<tr>
											<td>
												${kxsj.sj}
											</td>
											<td align="center">
												<input type="checkbox" name="${kxsj.sjIndex}1" value="1" />
												<input type="hidden" name="index${kxsj.sjIndex}1"
													value="${kxsj.mon}" />
											</td>
											<td align="center">
												<input type="checkbox" name="${kxsj.sjIndex}2" value="1" />
												<input type="hidden" name="index${kxsj.sjIndex}2"
													value="${kxsj.tue}" />
											</td>
											<td align="center">
												<input type="checkbox" name="${kxsj.sjIndex}3" value="1" />
												<input type="hidden" name="index${kxsj.sjIndex}3"
													value="${kxsj.wed}" />
											</td>
											<td align="center">
												<input type="checkbox" name="${kxsj.sjIndex}4" value="1" />
												<input type="hidden" name="index${kxsj.sjIndex}4"
													value="${kxsj.thu}" />
											</td>
											<td align="center">
												<input type="checkbox" name="${kxsj.sjIndex}5" value="1" />
												<input type="hidden" name="index${kxsj.sjIndex}5"
													value="${kxsj.fri}" />
											</td>
											<td align="center">
												<input type="checkbox" name="${kxsj.sjIndex}6" value="1" />
												<input type="hidden" name="index${kxsj.sjIndex}6"
													value="${kxsj.sat}" />
											</td>
											<td align="center">
												<input type="checkbox" name="${kxsj.sjIndex}7" value="1" />
												<input type="hidden" name="index${kxsj.sjIndex}7"
													value="${kxsj.sun}" />
											</td>

										</tr>
									</logic:iterate>
									</tbody>
								</table>
									<script>
										for(var i=0;i<7;i++){
											for(var j=1;j<8;j++){
											 	if(document.getElementById("index"+i+j)){
											 		if(document.getElementById("index"+i+j).value==1){
														document.getElementById(i+""+j).checked="checked";
												    }
											 	}
											}
										}
									</script>
							</logic:notEmpty>
						</td>
					</tr>
				</logic:equal>
				<%--end重庆科技学院--%>

				<tr>
					<th>班级</th>
					<td width="30%">
						<bean:write name="rs" property="bjmc" />
					</td>
					<th>手机号码</th>
					<td>
						<bean:write name="rs" property="sjhm"/>
					</td>						
				</tr>
				<tr>
					<th>民族</th>
					<td>
						<bean:write name="rs" property="mzmc"/>
					</td>	
					<th>政治面貌</th>
					<td>
						<bean:write name="rs" property="zzmmmc"/>
					</td>
					
                </tr>
				<!--非浙江科技学院-->
				<logic:notEqual value="11057" name="xxdm">
				<tr>
					<th>月均生活费</th>
					<td>
						<bean:write name="rs" property="yjshf"/>
					</td>
					<th>家庭情况</th>
					<td>
						<html:checkbox property="sfgr" name="rs" value="是" disabled="true">孤儿</html:checkbox>
						<html:checkbox property="sfdq" name="rs" value="是" disabled="true">单亲</html:checkbox>
						<html:checkbox property="sfdbh" name="rs" value="是" disabled="true">低保户</html:checkbox>
						<html:checkbox property="sfyfdx" name="rs" value="是" disabled="true">优扶对象</html:checkbox>
					</td>
                </tr>
				</logic:notEqual>
				<!--end非浙江科技学院-->
				
				<%--广东女子职业技术学院--%>
				<logic:equal value="12742" name="xxdm">
					<tr>
					<th>家庭年收入</th>
					<td colspan="3">
						${rs.jtnsr}
					</td>
					</tr>
				</logic:equal>	
				<%--end广东女子职业技术学院--%>
				<tr>
					<th>有何特长</th>
					<td colspan="3">
						<bean:write name="rs" property="yhtc"/>
					</td>
                </tr>
				<tr>
					<th>申请理由</th>
					<td colspan="3">
						<textarea rows="3" cols="3" style="width: 95%;word-break:break-all;" readonly="readonly" type="_moz">${rs.sqly }</textarea>
					</td>
                </tr>
				<tr>
					<th>备注</th>
					<td colspan="3">
						<textarea rows="3" cols="3" style="width: 95%;word-break:break-all;" readonly="readonly" type="_moz">${rs.bz }</textarea>
					</td>
                </tr>
				<!--西昌学院-->
				<logic:equal value="10628#" name="xxdm">
					<tr>
						<th>饭卡号</th>
						<td>
							<bean:write name="rs" property="kh"/>
						</td>
						<th>一卡通号</th>
						<td>
							<bean:write name="rs" property="ykth"/>
						</td>
					</tr>
					<tr>
						<th>银行卡号</th>
						<td>
							<bean:write name="rs" property="yhkh"/>
						</td>
						<th>申请单位</th>
						<td>
							<bean:write name="rs" property="sqdw"/>
						</td>
					</tr>
					<tr>
						<th>身份证号</th>
						<td colspan="3">
							<bean:write name="rs" property="sfzh"/>
						</td>
					</tr>
				</logic:equal>
				<!--end西昌学院-->

				<%--浙江机电职业技术学院--%>
				<logic:equal value="12861" name="xxdm">
					<tr>
						<th>申请理由</th>
						<td colspan="3">
							<textarea rows="3" cols="3" style="width: 95%;word-break:break-all;" readonly="readonly" type="_moz">${rs.sqly }</textarea>
						</td>
					</tr>
					<tr>
						<th>可参加勤工助学时间</th>
						<td colspan="3">
							<bean:write name="rs" property="kcjqgzxsj"/>
						</td>
					</tr>
				</logic:equal>
				<%--end浙江机电职业技术学院--%>

				<%--西北二民院自定义字段--%>
				<logic:notEmpty name="filedList">
					</tbody>
					<thead>
						<tr>
							<th style="cursor:hand" colspan="4">
								<span>申请附加信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
					<logic:iterate id="filed" name="filedList" indexId="index">
						<tr>
							<td align="right">
								<bean:write name="filed" property="zdmc" />
								：
							</td>
							<td colspan="3">
								<bean:write name="rs" property="${filed.zddm}" />
							</td>
						</tr>
					</logic:iterate>
					</tbody>
					<tbody>
				</logic:notEmpty>
				<%--end西北二民院自定义字段--%>
					
				<%--云南艺术学院--%>
				<logic:equal value="10690" name="xxdm" scope="request">
					<tr>
						<th>政治面貌</th>
						<td>
							${rs.zzmmmc}
						</td>
						<th>家庭地址</th>
						<td>
							${rs.jtdz}
						</td>
					</tr>
					<tr>
						<th>民族</th>
						<td>
							${rs.mzmc}
						</td>
						<th>家庭电话</th>
						<td>
							${rs.jtdh}
						</td>
					</tr>
					<tr>
						<th>国家助学贷款</th>
						<td>
							${rs.gjzxdk}
						</td>
						<th>家庭邮编</th>
						<td>
							${rs.jtyb}
						</td>
					</tr>
					<tr>
						<th>其它资助情况</th>
						<td>
							${rs.zzqk}
						</td>
						<th>家庭成员信息</th>
						<td>
							${rs.jtcy}
						</td>
					</tr>
					<tr>
						<th>贫困等级</th>
						<td>
							${rs.pkdj}
						</td>
						<th>家庭月收入</th>
						<td>
							${rs.jtysr}
						</td>
					</tr>
					<tr>
						<th>性格</th>
						<td>
							${rs.xg}
							<br />
							(内向为1→外向为10)
						</td>
						<th>特长</th>
						<td>
							${rs.yhtc}
						</td>
					</tr>
					<tr>
						<th>劳动经历</th>
						<td>
							${rs.gzjl}
						</td>
						<th>劳动意向</th>
						<td>
							${rs.ldyx}
						</td>
					</tr>
					<tr>
						<th>编号</th>
						<td>
							<html:text property="bh" name="rs" styleId="bh"></html:text>
						</td>
						<th>工号</th>
						<td>
							<html:text property="gh" name="rs" styleId="gh"></html:text>
						</td>
					</tr>
					<tr>
						<th>审核</th>
						<td>
							<html:select name="rs" property="yesNo" styleId="yesNo">
								<html:options collection="chkList" property="en"
									labelProperty="cn" />
							</html:select>
						</td>
						<th></th>
						<td>
						</td>
					</tr>
					<tr>
						<th>学校意见</th>
						<td colspan="3">
							<logic:notEqual value="xy" name="userType">
								<html:textarea name="rs" property="xxshyj"
									style="width:100%;height:45px" styleId="xxshyj"></html:textarea>
							</logic:notEqual>
							<logic:equal value="xy" name="userType">
								<html:textarea name="rs" property="xxshyj"
									style="width:100%;height:45px" readonly="true" styleId="xxshyj"></html:textarea>
							</logic:equal>
						</td>
					</tr>
				</logic:equal>
				<%--end云南艺术学院--%>				

				<!--- 广东白云学院---->
				<logic:equal value="10822" name="xxdm" scope="request">
					<tr>
						<th>家庭主要经济来源</th>
						<td colspan="3">
							${rs.jtzyjjly}
						</td>
					</tr>
					<th>可参加勤工助学时间</th>
					<td colspan="3">
						${rs.kcjqgzxsj}
					</td>
					<tr>
						<th>审核</th>
						<td colspan="3">
							<html:select name="rs" property="yesNo">
								<html:options collection="chkList" property="en"
									labelProperty="cn" />
							</html:select>
						</td>
					</tr>
					<tr>
						<td colspan="4">
							<table class="formlist" width="100%" >
								<tr>
									<th rowspan="5" align="right" width="18">
										家
										<br />
										庭
										<br />
										成
										<br />
										员
										<br />
										信
										<br />
										息
										<br />
									</th>
									<th align="center">
										家庭成员姓名
									</th>
									<th align="center">
										称谓
									</th>
									<th align="center">
										工作单位及职务
									</th>
									<th colspan="2" align="center">
										年收入
									</th>
								</tr>
								<tr align="center" style="height:22px">
									<td align="center">
										${rs.jtcy1_xm}
									</td>
									<td align="center">
										${rs.jtcy1_cw}
									</td>
									<td align="center">
										${rs.jtcy1_gzdwjzw}
									</td>
									<td colspan="2" align="center">
										${rs.jtcy1_nsr}
									</td>
								</tr>
								<tr align="center" style="height:22px">
									<td align="center">
										${rs.jtcy2_xm}
									</td>
									<td align="center">
										${rs.jtcy2_cw}
									</td>
									<td align="center">
										${rs.jtcy2_gzdwjzw}
									</td>
									<td colspan="2" align="center">
										${rs.jtcy2_nsr}
									</td>
								</tr>
								<tr align="center" style="height:22px">
									<td align="center">
										${rs.jtcy3_xm}
									</td>
									<td align="center">
										${rs.jtcy3_cw}
									</td>
									<td align="center">
										${rs.jtcy3_gzdwjzw}
									</td>
									<td colspan="2" align="center">
										${rs.jtcy3_nsr}
									</td>
								</tr>
								<tr align="center" style="height:22px">
									<td align="center">
										${rs.jtcy4_xm}
									</td>
									<td align="center">
										${rs.jtcy4_cw}
									</td>
									<td align="center">
										${rs.jtcy4_gzdwjzw}
									</td>
									<td colspan="2" align="center">
										${rs.jtcy4_nsr}
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</logic:equal>
				<!--- end广东白云学院---->

				<%--广东女子职业技术学院--%>
				<logic:equal value="12742" name="xxdm">
					<tr>
						<th>宿舍</th>
						<td>
							${rs.ssbh}
						</td>
						<th>特长爱好</th>
						<td>
							${rs.yhtc}
						</td>
					</tr>
					<tr>
						<th>本学期是否有不合格科目</th>
						<td>
							${rs.bhgkm}
						</td>
						<th>本学期有无违纪处分</th>
						<td>
							${rs.wjcf}
						</td>
					</tr>
					<tr>
						<th>审核</th>
						<td colspan="3">
							<html:select name="rs" property="yesNo" styleId="yesNo">
								<html:options collection="chkList" property="en"
									labelProperty="cn" />
							</html:select>
						</td>
					</tr>
				</logic:equal>
				<%--end广东女子职业技术学院--%>
				<logic:present name="showshgc">
					<tr>
						<th>审核</th>
						<td>
							<html:select name="rs" property="yesNo" styleId="yesNo">
								<html:options collection="chkList" property="en"
									labelProperty="cn" />
							</html:select>
						</td>
						<th></th>
						<td>
						</td>
					</tr>
				</logic:present>
				<logic:notPresent name="showshgc">
					<logic:notEqual value="12742" name="xxdm"><%--非广东女子职业技术学院--%>
					<logic:notEqual value="10822" name="xxdm"><%--非 广东白云学院--%>
					<logic:notEqual value="10690" name="xxdm"><%--非云南艺术学院--%>	
					<tr>
						<th>审核</th>
						<td colspan="3">
							<html:select name="rs" property="yesNo" styleId="yesNo">
							<html:options collection="chkList" property="en"
								labelProperty="cn" />
							</html:select>
						</td>
					</tr>			
					</logic:notEqual>
					</logic:notEqual>
					</logic:notEqual>
				</logic:notPresent>
				</tbody>
				<tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
			          <div class="btn">
			          <logic:equal value="12036" name="xxdm">
								<logic:equal value="xy" name="userType">
									<logic:equal value="true" name="isFdy">
										<logic:equal value="未审核" name="rs" property="xyyj">
											<button type="button" class="button2" onclick="thisCommit();" style="width:80px"
												id="buttonSave">
												保 存
											</button>
										</logic:equal>
									</logic:equal>
									<logic:notEqual value="true" name="isFdy">
											<logic:equal value="未审核" name="rs" property="xxyj">
												<button type="button" class="button2" onclick="thisCommit();" style="width:80px"
													id="buttonSave">
													保 存
												</button>
											</logic:equal>
									</logic:notEqual>
								</logic:equal>
								<logic:notEqual value="xy" name="userType">
									<button type="button" class="button2" onclick="thisCommit();" style="width:80px"
										id="buttonSave">
										保 存
									</button>
								</logic:notEqual>
							</logic:equal>
							<logic:notEqual value="12036" name="xxdm">
								<button type="button" class="button2" onclick="thisCommit();" style="width:80px"
									id="buttonSave">
									保 存
								</button>
							</logic:notEqual>
			            
						&nbsp;&nbsp;&nbsp;&nbsp;
						<button type="button" class="button2" onclick="Close();return false;" style="width:80px"
							id="buttonClose">
							关 闭
						</button>
			          </div>
			        </td>
			      </tr>
			    </tfoot>
			</table>
		</html:form>

		<!--操作提示信息-->
		<logic:notEmpty name="flag">
			<logic:equal value="true" name="flag">
				<logic:notEmpty name="mes">
					<input type="hidden" id="mes" name="mes" value="${mes}"/>
					<script>
						alert(document.getElementById('mes').value);
					</script>
				</logic:notEmpty>
				<logic:empty name="mes">
					<script language="javascript">
						alert("操作成功！");
					</script>
				</logic:empty>
				<script language="javascript">				
					Close();
					window.dialogArguments.document.getElementById('search_go').click();
				</script>
			</logic:equal>

			<logic:notEqual value="true" name="flag">
				<logic:notEmpty name="mes">
					<input type="hidden" id="mes" name="mes" value="${mes}"/>
					<script>
						alert(document.getElementById('mes').value);
					</script>
				</logic:notEmpty>
			<logic:empty name="mes">
				<script language="javascript">
					alert("操作失败！");
				</script>
			</logic:empty>
			<script language="javascript">
				Close();
				window.dialogArguments.document.getElementById('search_go').click();
			</script>
			</logic:notEqual>
		</logic:notEmpty>
		<!--end操作提示信息-->
		
		<!--审核条件限制提示-->
		<logic:equal value="view" name="result">
			<logic:present name="hasSQ">
				<logic:match value="have" name="hasSQ">
					<script language="javascript">
			         	alert("该学生已有申请项目通过审核！");
			         	Close();
						window.dialogArguments.document.getElementById('search_go').click();
	         		</script>
				</logic:match>
				<logic:match value="notHave" name="hasSQ">
					<script language="javascript">
			         	alert("操作成功！");
						Close();
						window.dialogArguments.document.getElementById('search_go').click();
	         		</script>
				</logic:match>
			</logic:present>
		</logic:equal>
		<!--end审核条件限制提示-->

		<!--人数限制信息提示-->
		<logic:equal value="full" name="result">
			<script>
				alert("人数已满！");
			</script>
		</logic:equal>
		<logic:equal value="knsfull" name="result">
			<script>
				alert("困难生人数已满！");
			</script>
		</logic:equal>
		<!--end人数限制信息提示-->
	</body>
</html>
