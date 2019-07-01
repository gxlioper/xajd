<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<script type="text/javascript" src="/xgxt/dwr/interface/chgJxjlist.js"></script>
		<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
		<script type="text/javascript"
			src="/xgxt/dwr/interface/commWjcfDAO.js"></script>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript">
	function ckinpdata(obj){
		obj.value = obj.value.replace(/[^(\d|\.)]/g,'');
		var inputStr = obj.value;
		
		if(!(inputStr.match(/\d+/g) || inputStr.match(/\d+\.?\d{0,1}/g)) || inputStr > 100){
			alert('数据格式不正确，扣分字段只能是两位数字！');
			obj.value = '';
			return false;
		}
		return true;
	}
	function getRqVal(name)
			{
			var rq=document.getElementById(name).value;
			if (rq!="")
			{
				rqs=rq.split("-");
				rq="";
				for (var i=0;i<rqs.length;i++)
				{
					rq+=rqs[i];
				}
				document.getElementById(name).value=rq;
			}
			}
	function chknotnull1(){
		var xxdm = '';
		var userType = '';
		if ($('xxdm')) {
			xxdm = document.getElementById('xxdm').value;
		}
		if ($('userType')) {
			userType = document.getElementById('userType').value;
		}
		
		if ('11049'==xxdm) {
			if ('xy'==userType) {
				if (document.getElementById('cflb').value==null || document.getElementById('cflb').value==''
				||document.getElementById('cfyy').value==null|| document.getElementById('cfyy').value=='' ) {
					alert('带*号内容为必填项！');
					return false;
				}
			} else {
				if (document.getElementById('cflb').value==null || document.getElementById('cflb').value==''
				||document.getElementById('cfyy').value==null|| document.getElementById('cfyy').value=='') {
					alert('带*号内容为必填项！');
					return false;
				}
			}
		} else {
			if (document.getElementById('cflb').value==null || document.getElementById('cflb').value==''
				||document.getElementById('cfyy').value==null|| document.getElementById('cfyy').value=='' 
				) {
					alert('带*号内容为必填项！');
					return false;
				}
		}
		return true;
	}
	function chkcfwh() {
		var xh = document.getElementById('xh').value;
		var wh="";
		var sj="";
		if ($('cfwh')) {
			wh = document.getElementById('cfwh').value;
		}
		if ($('cfsj')) {
			sj = document.getElementById('cfsj').value;
		}
		var xxdm = document.getElementById('xxdm').value;
		var oldwh;
		if ($('wh')) {
			oldwh = document.getElementById('wh').value;
		}
		if (sj==null || sj=='' ||wh==null||wh=='') {
			alert("处分时间和处分文号必须填写！");
			return false;
		}
		var xxclyj = document.getElementById("xxclyj").value;
		if (xxclyj.length>500) {
			alert("审核意见字数请控制在500字以内!");
			return false;
		}
		if (chknotnull1()) {
			if ('13022'==xxdm) {
				chgJxjlist.chkStucfwh(xh,wh,function(data){
				if (data) {
					if (wh==oldwh) {
						refreshForm('/xgxt/stuPunishAuditOne.do?act=save&cflb='+$('cflb').value + '&cfyy='+$('cfyy').value);BatAlert.showTips('正在操作，请等待...');
					} else {
						alert('该生在该处分文号中已存在处分信息,请修改处分文号!');
					}
					return;
				} else {
					refreshForm('/xgxt/stuPunishAuditOne.do?act=save&cflb='+$('cflb').value + '&cfyy='+$('cfyy').value);BatAlert.showTips('正在操作，请等待...');
				}
			});	
			} else {
				//保存前先判断该生同一处分文号，处分时间是否有数据
				var hidd_cfsj = $('hidd_cfsj').value;
				var hidd_cfwh = $('hidd_cfwh').value;
				commWjcfDAO.checkStuWjcfIsExists(xh,sj,wh,function(data) {
					if (!data && ((hidd_cfsj != sj) || (hidd_cfwh !=wh)) && (sj != null && wh != null)) {
						alert("该生在处分时间为:'" + sj + "',处分文号为:'" + wh+"'的文件中已受过一次处分，\n同一学生同一处分时间与文号下面不能有二次处分，请更换处分时间和文号。");
						return false;
					} else {
						refreshForm('/xgxt/stuPunishAuditOne.do?act=save&cflb='+$('cflb').value + '&cfyy='+$('cfyy').value);BatAlert.showTips('正在操作，请等待...');	
					}
				});
			}
		} else return;
	}
	function chkShzt() {
		var yesNo = document.getElementById('yesNo').value;
		var cfnx = document.getElementById('cfnx').value;
		if (yesNo != '通过' && cfnx != '') {
			alert("处分尚未审核通过,不能填写处分期限!");
			document.getElementById('cfnx').value = "";
			return false;
		}
	}
	function disLxck() {
		if ($('dis') && $('dis').value=='no') {
			if($('lxcksj')){
				$('lxcksj').disabled = true;
			}
			if($('cfsj')){
				$('cfsj').readonly = true;
			}
			if($('cfwh')){
				$('cfwh').readonly = true;
			}
		}
	}
</script>
	</head>
	<body onload="disLxck()">
		<logic:notPresent name="showXbemy">
			<div class="tab_cur">
				<p class="location">
					<logic:equal value="11049" name="xxdm">
						<logic:equal value="xy" name="userType">
							<em>您的当前位置:</em>
							<a>违纪处理 - 审核 - 系院审核</a>
						</logic:equal>
						<logic:notEqual value="xy" name="userType">
							<em>您的当前位置:</em>
							<a>违纪处理 - 审核 - 学生处审核</a>
						</logic:notEqual>
					</logic:equal>
					<logic:notEqual value="11049" name="xxdm">
						<em>您的当前位置:</em>
						<a>违纪处分 - 审核 - 学校审核 - 单个审核</a>
					</logic:notEqual>
				</p>
			</div>


			<html:form action="/data_search" method="post">
				<input type="hidden" name="xxdm" id="xxdm" value="${xxdm }" />
				<input type="hidden" name="userType" id="userType"
					value="${userType }" />
				<input type="hidden" name="pkVal"
					value="<bean:write name="xh||xn||sbsj"/>" />
				<input type="hidden" name="hidd_cfsj" id="hidd_cfsj"
					value="${cfsj }" />
				<input type="hidden" name="hidd_cfwh" id="hidd_cfwh"
					value="${cfwh }" />


				<div class="tab">
					<table width="100%" border="0" class="formlist">
						<thead>
							<tr>
								<th colspan="4">
									<span>单个审核</span>
								</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<td colspan="4">
									<logic:equal value="no" name="xy_writable">
										<div class="bz">
											<font color="red">该处分由学校进行最后发文!</font>
										</div>
									</logic:equal>
									<input type="hidden" id="dis" value="${xy_writable }" />

									<div class="btn">
										<logic:notEqual value="no" name="czxx_wjcf_writable">
											<logic:notEqual value="true" name="xyNowrite">
												<button type="button" onclick="chkcfwh();" id="buttonSave">
													保 存
												</button>
											</logic:notEqual>
										</logic:notEqual>
										<button type="button" onclick="Close();return false;" id="buttonClose">
											关 闭
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
						<tbody>

							<tr>
								<th>
									学号
								</th>
								<td align="left">
									<input type="text" name="xh" id="xh" value="${XH }"
										readonly="readonly" />
								</td>
								<th>
									年度
								</th>
								<td align="left">
									<bean:write name="ND" />
								</td>
							</tr>
							<tr>
								<th>
									姓名
								</th>
								<td align="left">
									<bean:write name="XM" />
								</td>
								<th>
									学年
								</th>
								<td align="left">
									<bean:write name="XN" />
									<input type="hidden" name="xn" id="xn" value="${XN }" />
									<input type="hidden" name="sbsj" id="sbsj" value="${sbsj }" />
								</td>
							</tr>
							<tr>
								<th>
									性别
								</th>
								<td align="left">
									<bean:write name="XB" />
								</td>
								<th>
									<font color="red">*</font>处分类别
								</th>
								<td align="left">
									<html:select property="cflb" styleId="cflb" style="width:150"
										styleClass="select" disabled="true">
										<html:options collection="cflbList" property="cflbdm"
											labelProperty="cflbmc" />
									</html:select>
									<logic:notEmpty name="fjList">									
									<logic:iterate id="s" name="fjList">

											&nbsp;&nbsp;<a href="downloadfilewj.do?wjsclj=${s.fjsclj }"
												target="_self">处分文件下载</a>
					
									</logic:iterate>
						
					</logic:notEmpty>
								</td>
							</tr>
							<tr>
								<th>
									年级
								</th>
								<td align="left">
									<bean:write name="NJ" />
								</td>
								<th>
									<font color="red">*</font>处分原因
								</th>
								<td align="left">
									<html:select property="cfyy" styleId="cfyy" style="width:150px"
										styleClass="select" disabled="true">
										<html:options collection="cfyyList" property="cfyydm"
											labelProperty="cfyymc" />
									</html:select>

								</td>
							</tr>
							<tr>
								<th>
									<bean:message key="lable.xsgzyxpzxy" />
								</th>
								<td align="left">
									<bean:write name="XYMC" />
								</td>
								<th>
									审核
								</th>
								<td align="left">
									<html:select property="yesNo" styleId="yesNo">
										<html:options collection="chkList" property="en"
											labelProperty="cn" />
									</html:select>
								</td>
							</tr>
							<tr>
								<th>
									专业
								</th>
								<td align="left">
									<bean:write name="ZYMC" />
								</td>
								<logic:equal value="11049" name="xxdm">
									<td></td>
									<td></td>
								</logic:equal>
								<logic:notEqual value="11049" name="xxdm">
									<th>
										<font color="red">*</font>处分时间
									</th>
									<td align="left">
										<!-- 控制对于由校审的<bean:message key="lable.xsgzyxpzxy" />是不能进行发文的 -->
										<logic:notEqual value="no" name="xy_writable">
											<html:text property="cfsj" styleId="cfsj"
												onblur="dateFormatChg(this)" style="cursor:hand;"
												readonly="true"
												onclick="return showCalendar('cfsj','y-mm-dd');" />
										</logic:notEqual>
										<logic:equal value="no" name="xy_writable">
											<html:text property="cfsj" styleId="cfsj"
												style="cursor:hand;" readonly="true" />
										</logic:equal>
									</td>
								</logic:notEqual>
							</tr>
							<tr>
								<th>
									班级
								</th>
								<td align="left">
									<bean:write name="BJMC" />
								</td>
								<logic:equal value="11049" name="xxdm">
									<td></td>
									<td></td>
								</logic:equal>
								<logic:notEqual value="11049" name="xxdm">
									<th>

										<logic:equal value="11078" name="xxdm">
																				<font color="red">*</font>违纪处分决定书文号
										</logic:equal>
										<logic:notEqual value="11078" name="xxdm">
																			<font color="red">*</font>处分文号
									</logic:notEqual>
									</th>
									<td align="left">
										<!-- 控制对于由校审的<bean:message key="lable.xsgzyxpzxy" />是不能进行发文的 -->
										<logic:notEqual value="no" name="xy_writable">
											<html:text property="cfwh" styleId="cfwh" maxlength="30" />
											<input type="hidden" id="wh" value="${cfwh }" />
										</logic:notEqual>
										<logic:equal value="no" name="xy_writable">
											<html:text property="cfwh" styleId="cfwh" maxlength="30"
												readonly="true" />
										</logic:equal>
									</td>
								</logic:notEqual>
							</tr>

							<tr>
								<th>
									违纪时间
								</th>
								<td align="left">
									${wjsj }
								</td>
								<logic:notEqual name="xxdm" value="11078">
									<logic:equal name="userType" value="xx">
										<th>
											评议时间
										</th>
										<td align="left">
											<html:text property="pysj" styleId="pysj" value="${pysj}"
												onblur="dateFormatChg(this)" style="cursor:hand;"
												readonly="true"
												onclick="return showCalendar('pysj','y-mm-dd');" />
										</td>
									</logic:equal>
									<logic:equal name="userType" value="admin">
										<th>
											评议时间
										</th>
										<td align="left">
											<html:text property="pysj" styleId="pysj" value="${pysj}"
												onblur="dateFormatChg(this)" style="cursor:hand;"
												readonly="true"
												onclick="return showCalendar('pysj','y-mm-dd');" />
										</td>
									</logic:equal>
									<logic:notEqual name="userType" value="admin">
										<logic:notEqual name="userType" value="xx">
											<th>

											</th>
											<td align="left">

											</td>
										</logic:notEqual>
									</logic:notEqual>
								</logic:notEqual>
								<logic:equal name="xxdm" value="11078">
									<th>
										副书记
									</th>
									<td align="left">
										<logic:empty name="fsjname">
									${fsjnames }
									</logic:empty>
										<logic:notEmpty name="fsjnam">
									${fsjname }
									</logic:notEmpty>
									</td>
								</logic:equal>
							</tr>

							<%--武汉理工大学华夏<bean:message key="lable.xsgzyxpzxy" />--%>
							<logic:equal value="1049701" name="xxdm">
								<tr>
									<th>
										扣分
									</th>
									<td colspan="4" align="left">
										<html:text property="kf" styleId="kf"
											onkeyup="ckinpdata(this)" maxlength="10"></html:text>
									</td>
								</tr>
							</logic:equal>
<%--							<logic:equal value="yes" name="lxck">--%>
<%--								<tr>--%>
<%--									<th>--%>
<%--										留校察看解除时间--%>
<%--									</th>--%>
<%--									<td colspan="4" align="left">--%>
<%--										<html:text property="lxcksj" styleId="lxcksj"--%>
<%--											onblur="dateFormatChg(this)" style="cursor:hand;"--%>
<%--											readonly="true"--%>
<%--											onclick="return showCalendar('lxcksj','y-mm-dd');"--%>
<%--											value="${lxcksj}"></html:text>--%>
<%--									</td>--%>
<%--								</tr>--%>
<%--							</logic:equal>--%>


							<!-- 宁波城市单独 -->
							<logic:equal value="12645" name="xxdm">
								<tr>
									<th>
										处分期限
									</th>
									<td colspan="" align="left">


										<logic:notEqual value="no" name="xy_writable">
											<html:text property="cfnx" styleId="cfnx" maxlength="20"
												onkeyup="chkShzt()" value="${cfnx }"></html:text>
										</logic:notEqual>
										<logic:equal value="no" name="xy_writable">
											<html:text property="cfnx" styleId="cfnx" value="${cfnx }"
												maxlength="30" readonly="true" />
										</logic:equal>
									</td>
									<th>
										当事人是否申辩
									</th>
									<td>
										${sfsb }
									</td>
								</tr>
								<tr>
									<th>
										申辩事由
									</th>
									<td colspan="4">
										<html:textarea rows="3" style="width:500px;overflow:auto"
											property="sbsy" styleId="a" readonly="true"></html:textarea>
									</td>
								</tr>
							</logic:equal>
<%--							<tr>--%>
<%--								<th>--%>
<%--									学生签收--%>
<%--								</th>--%>
<%--								<td align="left">--%>
<%--									${sfqs}--%>
<%--								</td>--%>
<%--								<logic:equal name="xxdm" value="11654">--%>
<%--								<th>--%>
<%--									是否告知学生--%>
<%--								</th>--%>
<%--								</logic:equal>--%>
<%--								<logic:notEqual name="xxdm" value="11654">--%>
<%--								<th>--%>
<%--									学生处分确认--%>
<%--								</th>--%>
<%--								</logic:notEqual>--%>
<%--								<td align="left">--%>
<%--									${xsqr}--%>
<%--								</td>--%>
<%--							</tr>--%>
<%--							<tr>--%>
<%--								<th>--%>
<%--									确认时间--%>
<%--								</th>--%>
<%--								<td align="left">--%>
<%--									${qrsj }--%>
<%--								</td>--%>
<%--								<th>--%>
<%--								</th>--%>
<%--								<td>--%>
<%--								</td>--%>
<%--							</tr>--%>

							<tr>
								<th>
									违纪事实
								</th>
								<td colspan="4" align="left">
									<html:textarea rows="6" style="width:500px;overflow:auto"
										property="bz" styleId="a" readonly="true"></html:textarea>
								</td>
							</tr>
							<logic:equal value="11049" name="xxdm">
								<tr>
									<th>
										系院意见
									</th>
									<td colspan="4" align="left">
										<logic:equal value="xy" name="userType">
											<html:textarea rows="6" style="width:500px" property="xyclyj"
												styleId="a"></html:textarea>
										</logic:equal>
										<logic:notEqual value="xy" name="userType">
											<html:textarea rows="6" style="width:500px" property="xyclyj"
												styleId="a" readonly="true"></html:textarea>
										</logic:notEqual>
									</td>
								</tr>
								<logic:notEqual value="xy" name="userType">
									<tr>
										<th>
											审核意见
										</th>
										<td colspan="4" align="left">
											<html:textarea rows="5" style="width:500px" property="XXCLYJ"
												styleId="a" />
										</td>
									</tr>
								</logic:notEqual>
							</logic:equal>
							<logic:notEqual value="11049" name="xxdm">
								<logic:notEqual value="xy" name="userType">
	
								</logic:notEqual>
								<tr>
									<th>
										审核意见
										<br />
										<font color="red">(500字以内)</font>
									</th>
									<td colspan="4" align="left">
										<html:textarea rows="5" style="width:500px" property="XXCLYJ"
											onkeyup="checkLen(this,500)" styleId="xxclyj" />
									</td>
								</tr>
							</logic:notEqual>
					</table>
				</div>
			</html:form>
		</logic:notPresent>
		<logic:equal value="showXbemy" name="showXbemy">
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>违纪处分 - 审核 - 学校审核 - 单个审核</a>
				</p>
			</div>
			</div>
			<input type="hidden" name="pkVal"
				value="<bean:write name="xh||cfrq||cfwh"/>" />


			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="5">
								<span>单个审核</span>
							</th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<td colspan="5">
								<div class="btn">
									<button type="button"
										onclick="refreshForm('/xgxt/stuPunishAuditOne.do?act=save"
										);"
											id="buttonSave">
										保 存
									</button>
									<button type="button" onclick="Close();return false;" id="buttonClose">
										关 闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>

					<tbody>
						<tr>
							<th>
								学号
							</th>
							<td align="left">
								<bean:write name="XH" />
							</td>
							<th>
								年度
							</th>
							<td align="left">
								<bean:write name="ND" />
							</td>
							<td rowspan="5" align="center" width="15%">
								<img align="center" border="0" style="height:133px;width:100px;"
									src="/xgxt/pictures/<bean:write name="XH" />.jpg" id="pic" />
							</td>
						</tr>
						<tr>
							<th>
								姓名
							</th>
							<td align="left">
								<bean:write name="XM" />
							</td>
							<th>
								学年
							</th>
							<td align="left">
								<bean:write name="XN" />
							</td>
						</tr>
						<tr>
							<th>
								性别
							</th>
							<td align="left">
								<bean:write name="XB" />
							</td>
							<th>
								处分类别
							</th>
							<td align="left">
								<bean:write name="cflbmc" />
							</td>
						</tr>
						<tr>
							<th>
								年级
							</th>
							<td align="left">
								<bean:write name="NJ" />
							</td>
							<th>
								处分原因
							</td>
							<td align="left">
								<bean:write name="cfyymc" />
							</td>
						</tr>
						<tr>
							<th>
								<bean:message key="lable.xsgzyxpzxy" />

							</th>
							<td align="left">
								<bean:write name="XYMC" />
							</td>
							<th>
								审核
							</th>
							<td align="left">
								<html:select property="yesNo">
									<html:options collection="chkList" property="en"
										labelProperty="cn" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								专业
							</th>
							<td align="left">
								<bean:write name="ZYMC" />
							</td>
							<th>
								违纪时间
							</th>
							<td align="left" colspan="2">
								<bean:write name="wjsj" />
							</td>
						</tr>
						<tr>
							<th>
								班级
							</th>
							<td align="left">
								<bean:write name="BJMC" />
							</td>
							<th>
								审核时间
							</th>
							<td align="left" colspan="2">
								<input type="text" name="shsj" id="shsj"
									value="<bean:write name="shsj"/>"
									onclick="return showCalendar('shsj','y-mm-dd');"
									onblur="getRqVal('shsj')" />
							</td>
						</tr>
						<tr>
							<th>
								违纪事实
							</th>
							<td colspan="4" align="left">
								<html:textarea rows="5" style="width:98%" property="bz"
									styleId="a"></html:textarea>
							</td>
						</tr>
						<tr>
							<th>
								审核意见
							</th>
							<td colspan="4" align="left">
								<html:textarea rows="5" style="width:98%" property="XXCLYJ"
									styleId="a" />
							</td>
						</tr>
					</tbody>
				</table>
		</logic:equal>
		<logic:equal value="view" name="result">
			<script>
				alert("操作成功");
				if(window.dialogArguments){
			 		window.dialogArguments.document.getElementById('search_go').onclick();
			 	}
				Close();
			</script>
		</logic:equal>
		<logic:equal value="noview" name="result">
			<script>
				alert("文号已存在！操作失败");
				if(window.dialogArguments){
			 		window.dialogArguments.document.getElementById('search_go').onclick();
			 	}
				Close();
			</script>
		</logic:equal>
	</body>
</html>
