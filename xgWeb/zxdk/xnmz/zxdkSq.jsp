<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
		function saveZxdkSq(){
			if($("xh") && $("xh").value==""){
				 $("xh").focus();
				alertInfo("学号不能为空！");
				return false;
			}
			
			jszje();
			
			if($("dkzje").value=="0"){
				alertInfo("请填写贷款信息!");
				return false;
			}
			var sfysq=$("sfysq").value;
			var url="zxdk_xnmz.do?method=zxdkSq&doType=save&sfysq="+sfysq;
			refreshForm(url);
		}
		
		function updateZxdkSq(){
			
			var url="zxdk_xnmz.do?method=zxdkModi&doType=save";
			refreshForm(url);
		}
		
		function displayTbody(id){
			if($(id).style.display=="none"){
				$(id).style.display="";
			}else{
				$(id).style.display="none"
			}
		}

		function jszje() {
			var dkzje=jQuery("#dkzje").val();dkzje=0;
			jQuery("#tbody_dkxx").find("input").each(function(){
				if(jQuery(this).attr("name")!="dkzje"){
					dkzje=parseInt(dkzje)+parseInt(jQuery(this).val() != "" ? jQuery(this).val() : 0);
				}
			});
			jQuery("#dkzje").val(dkzje);
		}
		
<%--		function jszje(){--%>
<%--			$("onexnzje").value=0;--%>
<%--			$("twoxnzje").value=0;--%>
<%--			$("threexnzje").value=0;--%>
<%--			$("fourxnzje").value=0;--%>
<%--			$("fivexnzje").value=0;--%>
<%--		--%>
<%--			if($("onexnxfje").value!=""){--%>
<%--				$("onexnzje").value=eval($("onexnzje").value)+eval($("onexnxfje").value);--%>
<%--			}--%>
<%--			--%>
<%--			if($("onexnqsfje").value!=""){--%>
<%--				$("onexnzje").value=eval($("onexnzje").value)+eval($("onexnqsfje").value);--%>
<%--			}--%>
<%--			--%>
<%--			if($("onexnshf").value!=""){--%>
<%--				$("onexnzje").value=eval($("onexnzje").value)+eval($("onexnshf").value);--%>
<%--			}--%>
<%--			--%>
<%--			--%>
<%--			if($("twoxnxfje").value!=""){--%>
<%--				$("twoxnzje").value=eval($("twoxnzje").value)+eval($("twoxnxfje").value);--%>
<%--			}--%>
<%--	--%>
<%--			if($("twoxnqsfje").value!=""){--%>
<%--				$("twoxnzje").value=eval($("twoxnzje").value)+eval($("twoxnqsfje").value);--%>
<%--			}--%>
<%--			--%>
<%--			if($("twoxnshf").value!=""){--%>
<%--				$("twoxnzje").value=eval($("twoxnzje").value)+eval($("twoxnshf").value);--%>
<%--			}--%>
<%--			--%>
<%--			--%>
<%--			if($("threexnxfje").value!=""){--%>
<%--				$("threexnzje").value=eval($("threexnzje").value)+eval($("threexnxfje").value);--%>
<%--			}--%>
<%--			--%>
<%--			if($("threexnqsfje").value!=""){--%>
<%--				$("threexnzje").value=eval($("threexnzje").value)+eval($("threexnqsfje").value);--%>
<%--			}--%>
<%--			--%>
<%--			if($("threexnshf").value!=""){--%>
<%--				$("threexnzje").value=eval($("threexnzje").value)+eval($("threexnshf").value);--%>
<%--			}--%>
<%--			--%>
<%--			if($("fourxnxfje").value!=""){--%>
<%--				$("fourxnzje").value=eval($("fourxnzje").value)+eval($("fourxnxfje").value);--%>
<%--			}--%>
<%--			if($("fourxnqsfje").value!=""){--%>
<%--				$("fourxnzje").value=eval($("fourxnzje").value)+eval($("fourxnqsfje").value);--%>
<%--			}--%>
<%--			if($("fourxnshf").value!=""){--%>
<%--				$("fourxnzje").value=eval($("fourxnzje").value)+eval($("fourxnshf").value);--%>
<%--			}--%>
<%--			--%>
<%--			if($("fivexnxfje").value!=""){--%>
<%--				$("fivexnzje").value=eval($("fivexnzje").value)+eval($("fivexnxfje").value);--%>
<%--			}--%>
<%--			if($("fivexnqsfje").value!=""){--%>
<%--				$("fivexnzje").value=eval($("fivexnzje").value)+eval($("fivexnqsfje").value);--%>
<%--			}--%>
<%--			if($("fivexnshf").value!=""){--%>
<%--				$("fivexnzje").value=eval($("fivexnzje").value)+eval($("fivexnshf").value);--%>
<%--			}--%>
<%--			--%>
<%--			$("dkzje").value=0;--%>
<%--			if($("onexnzje").value!=""){--%>
<%--				$("dkzje").value=eval($("dkzje").value)+eval($("onexnzje").value);--%>
<%--			}--%>
<%--			if($("twoxnzje").value!=""){--%>
<%--				$("dkzje").value=eval($("dkzje").value)+eval($("twoxnzje").value);--%>
<%--			}--%>
<%--			if($("threexnzje").value!=""){--%>
<%--				$("dkzje").value=eval($("dkzje").value)+eval($("threexnzje").value);--%>
<%--			}--%>
<%--			if($("fourxnzje").value!=""){--%>
<%--				$("dkzje").value=eval($("dkzje").value)+eval($("fourxnzje").value);--%>
<%--			}--%>
<%--			if($("fivexnzje").value!=""){--%>
<%--				$("dkzje").value=eval($("dkzje").value)+eval($("fivexnzje").value);--%>
<%--			}--%>
<%--			--%>
<%--		}--%>
		</script>
	</head>
	<body onload="">
		<html:form action="/zxdk_xnmz" method="post">
			<!-- 隐藏域 -->
			<input type="hidden" name="url" id="url"
				value="zxdk_xnmz.do?method=zxdkSq" />
			<input type="hidden" name="sfysq" id="sfysq" value='${rs.sfysq}' />
			<!-- 修改时 -->
			<input type="hidden" name="shzt" id="shzt" value='${rs.shzt}' />
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- 隐藏域 -->
			<div class="tab_cur" id="jd">
				<p class="location">
					<em>您的当前位置:</em><a>${title }</a>
				</p>
			</div>
			<logic:equal name="doType" value="add">
				<logic:equal name="rs" property="sfysq" value="ysq">
					<div id="xxPrompt" class="prompt">
						<h3>
							<span>提示：</span>
						</h3>
						<p>
							已有申请信息，无法重复申请！
						</p>
						<a class="close" title="隐藏"
							onclick="this.parentNode.style.display='none';"></a>
					</div>
				</logic:equal>
			</logic:equal>
			<logic:equal name="doType" value="update">
				<logic:equal name="rs" property="sfsh" value="ysh">
					<div id="xxPrompt" class="prompt">
						<h3>
							<span>提示：</span>
						</h3>
						<p>
							该学生的助学贷款申请信息已审核不能再做修改！
						</p>
						<a class="close" title="隐藏"
							onclick="this.parentNode.style.display='none';"></a>
					</div>
				</logic:equal>
			</logic:equal>
			<div class="tab">
				<table width="100%" border="0" class="formlist">

					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<logic:equal name="doType" value="add">
										<button class="button2" id="btn_bc" onclick="saveZxdkSq();return false;" >
											保 存
										</button>
									</logic:equal>
									<logic:equal name="doType" value="update">
										<input type="hidden" name="guid" id="guid" value="${rs.guid }"/>
										<logic:notEqual name='rs' property="sfsh" value="ysh">
										<button class="button2" id="btn_bc" onclick="updateZxdkSq();return false;">
											保 存
										</button>
										</logic:notEqual>
									</logic:equal>
								</div>
							</td>
						</tr>
					</tfoot>
					<thead onclick="displayTbody('tbody_jbxx')">
						<tr>
							<th colspan="4">
								<span>基本信息</span>
							</th>
						</tr>
					</thead>

					<tbody id="tbody_jbxx">
						<tr>
							<th style="width:16%">
								<font color="red">*</font>学号
							</th>
							<td style="width:34%">
								<html:text property="xh" styleId="xh" readonly="true"
									value="${rs.xh }" />
								<logic:equal name="doType" value="add">
									<logic:notEqual name="userType"  value="stu">
									<button class="btn_01" id="" onclick="sendXx();return false;">
										选 择
									</button>
									</logic:notEqual>
								</logic:equal>
							</td>
							<th style="width:16%">
								姓名
							</th>
							<td style="width:34%">
								${rs.xm}
							</td>
						</tr>
						<tr>
							<th style="width:16%">
								性别
							</th>
							<td style="width:34%">
								${rs.xb }
							</td>
							<th style="width:16%">
								年级
							</th>
							<td style="width:34%">
								${rs.nj }
							</td>
						</tr>
						<tr>
							<th style="width:16%">
								<bean:message key="lable.xb" />
							</th>
							<td id="" style="width:34%">
								${rs.xymc }
							</td>

							<th>
								专业
							</th>
							<td id="" style="width:34%">
								${rs.zymc }
							</td>
						</tr>
						<tr>
							<th style="width:16%">
								班级
							</th>
							<td style="width:34%">
								${rs.zymc }
							</td>
							<th style="width:16%">
								身份证号
							</th>
							<td style="width:34%">
								${rs.sfzh }
							</td>
						</tr>
						<tr>
							<th style="width:16%">
								民族
							</th>
							<td style="width:34%">
								${rs.mzmc }
							</td>
							<th style="width:16%">
								出生年月
							</th>
							<td style="width:34%">
								${rs.csrq }
							</td>
						</tr>
						<tr>
							<th style="width:16%">
								年龄
							</th>
							<td style="width:34%">
								${rs.nl }
							</td>
							<th style="width:16%">
								学制
							</th>
							<td style="width:34%">
								${rs.xz }
							</td>
						</tr>
						<tr>
							<th style="width:16%">
								毕业日期
							</th>
							<td style="width:34%">
								${rs.byny }
							</td>
							<th style="width:16%">
								联系电话
							</th>
							<td style="width:34%">
								${rs.lxdh }
							</td>
						</tr>
						<tr>
							<th style="width:16%">
								OICQ号
							</th>
							<td style="width:34%">
								${rs.qqhm }
							</td>
							<th style="width:16%">
								电子邮箱
							</th>
							<td style="width:34%">
								${rs.dzyx }
							</td>
						</tr>
						<tr>
							<th style="width:16%">
								学生家庭地址
							</th>
							<td style="width:34%">
								${rs.jtdz }
							</td>
							<th style="width:16%">
								学生家庭联系电话
							</th>
							<td style="width:34%">
								${rs.jtdh }
							</td>
						</tr>
						<tr>
							<th style="width:16%">
								学生家庭邮政编码
							</th>
							<td style="width:34%">
								${rs.jtyb }
							</td>
							<th style="width:16%">
								家庭人均月收入
							</th>
							<td style="width:34%">
								<html:text name="rs" property="jtysr"
									onblur="checkInputNum(this)" maxlength="10" />
							</td>
						</tr>
						<tr>
							<th style="width:16%">
								户口类型
							</th>
							<td style="width:34%">
								${rs.hkxz }
							</td>
							<th style="width:16%">

							</th>
							<td style="width:34%">

							</td>
						</tr>

					</tbody>
					<thead onclick="displayTbody('tbody_jtxx')">
						<tr>
							<th colspan="4">
								<span>家庭信息</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_jtxx">
						<tr>
							<th style="width:16%">
								父亲姓名
							</th>
							<td style="width:34%">
								<html:text name="rs" property="fqxm" maxlength="25" />
							</td>
							<th style="width:16%">
								父亲身份证号
							</th>
							<td style="width:34%">
								<html:text name="rs" property="fqsfzh" maxlength="18"
									onblur="if(!checkSfzh(this)){this.focus();}" />
							</td>
						</tr>
						<tr>
							<th style="width:16%">
								父亲工作单位
							</th>
							<td style="width:34%">
								<html:text name="rs" property="fqgzdw" maxlength="100" />
							</td>
							<th style="width:16%">
								父亲联系方式
							</th>
							<td style="width:34%">
								<html:text name="rs" property="fqlxfs" maxlength="25" />
							</td>
						</tr>
						<tr>
							<th style="width:16%">
								母亲姓名
							</th>
							<td style="width:34%">
								<html:text name="rs" property="mqxm" maxlength="25" />
							</td>
							<th style="width:16%">
								母亲身份证号
							</th>
							<td style="width:34%">
								<html:text name="rs" property="mqsfzh" maxlength="18"
									onblur="if(!checkSfzh(this)){this.focus();}" />
							</td>
						</tr>
						<tr>
							<th style="width:16%">
								母亲工作单位
							</th>
							<td style="width:34%">
								<html:text name="rs" property="mqgzdw" maxlength="100" />
							</td>
							<th style="width:16%">
								母亲联系方式
							</th>
							<td style="width:34%">
								<html:text name="rs" property="mqlxfs" maxlength="25" />
							</td>
						</tr>
					</tbody>
					<thead onclick="displayTbody('tbody_lxrxx')">
						<tr>
							<th colspan="4">
								<span>联系人</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_lxrxx">
						<tr>
							<th>
								联系人姓名
							</th>
							<td>
								<html:text name="rs" property="lxrxm" maxlength="25" />
							</td>
							<th>
								联系人家庭地址
							</th>
							<td>
								<html:text name="rs" property="lxrjtzz" maxlength="50" />
							</td>
						</tr>
						<tr>
							<th>
								联系人工作单位
							</th>
							<td>
								<html:text name="rs" property="lxrgzdw" maxlength="50" />
							</td>
							<th>
								联系人固定电话
							</th>
							<td>
								<html:text name="rs" property="lxrgddh"
									onkeydown="return onlyNum(this,15)"
									onmousedown="return onlyNum(this,15)" style="ime-mode:disabled" />
							</td>
						</tr>
						<tr>
							<th>
								联系人移动电话
							</th>
							<td>
								<html:text name="rs" property="lxryddh" maxlength="15"
									onkeydown="return onlyNum(this,15)"
									onmousedown="return onlyNum(this,15)" style="ime-mode:disabled" />
							</td>
							<th>

							</th>
							<td>
							</td>
						</tr>
					</tbody>
					<thead onclick="displayTbody('tbody_jzrxx')">
						<tr>
							<th colspan="4">
								<span>见证人</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_jzrxx">
						<tr>
							<th>
								见证人姓名
							</th>
							<td>
								<html:text name="rs" property="jzrxm" maxlength="25" />
							</td>
							<th>
								见证人证件号
							</th>
							<td>
								<html:text name="rs" property="jzrzjh" maxlength="18"
									onblur="if(!checkSfzh(this)){this.focus();}" />
							</td>
						</tr>
						<tr>
							<th>
								见证人证件类型代码
							</th>
							<td>
								<html:select name="rs"  property="jzrzjlxdm" styleId="rychdm">
									<option value=""></option>
									<html:options collection="zjlxList" property="zjlxdm"
										labelProperty="zjlxmc" />
								</html:select>
							</td>
							<th>
								见证人家庭地址
							</th>
							<td>
								<html:text name="rs" property="jzrdz" maxlength="50" />
							</td>
						</tr>
						<tr>
							<th>
								见证人与申请人关系
							</th>
							<td>
								<html:text name="rs" property="jzrgx" maxlength="20" />
							</td>
							<th>
								见证人邮编
							</th>
							<td>
								<html:text name="rs" property="jzryb" maxlength="20" />
							</td>
						</tr>
						<tr>
							<th>
								见证人电话
							</th>
							<td>
								<html:text name="rs" property="jzrdh" maxlength="20" />
							</td>
							<th>
								见证人意见
							</th>
							<td>
								<html:text name="rs" property="jzryj" maxlength="50" />
							</td>
						</tr>
					</tbody>
					<thead onclick="displayTbody('tbody_dkxx')">
						<tr>
							<th colspan="4">
								<span>贷款信息</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_dkxx">
						<tr>
							<th>
								第一学年学费贷款
							</th>
							<td>
								<html:hidden name="rs" property="onexnzje" styleId="onxnzje" />
								<html:text name="rs" property="onexnxfje" styleId="onexnxfje"
									onblur="checkInputNum(this);jszje()" maxlength="10" />
							</td>
							<th>
								第一学年寝室费贷款
							</th>
							<td>
								<html:text name="rs" property="onexnqsfje" styleId="onexnqsfje"
									onblur="checkInputNum(this);jszje()" maxlength="10" />
							</td>

						</tr>
						<tr>
							<th>
								第一学年生活费贷款
							</th>
							<td>
								<html:text name="rs" property="onexnshf" styleId="onexnshf"
									onblur="checkInputNum(this);jszje()" maxlength="10" />
							</td>
							<th>
								第二学年学费贷款
							</th>
							<td>
								<html:hidden name="rs" property="twoxnzje" styleId="twoxnzje" />
								<html:text name="rs" property="twoxnxfje" styleId="twoxnxfje"
									onblur="checkInputNum(this);jszje()" maxlength="10" />
							</td>

						</tr>
						<tr>
							<th>
								第二学年寝室费贷款
							</th>
							<td>
								<html:text name="rs" property="twoxnqsfje" styleId="twoxnqsfje"
									onblur="checkInputNum(this);jszje()" maxlength="10" />
							</td>
							<th>
								第二学年生活费贷款
							</th>
							<td>
								<html:text name="rs" property="twoxnshf" styleId="twoxnshf"
									onblur="checkInputNum(this);jszje()" maxlength="10" />
							</td>
						</tr>
						<tr>
							<th>
								第三学年学费贷款
							</th>
							<td>
								<html:hidden name="rs" property="threexnzje" 
									styleId="threexnzje" />
								<html:text name="rs" property="threexnxfje" styleId="threexnxfje"
									onblur="checkInputNum(this);jszje()" maxlength="10" />
							</td>
							<th>
								第三学年寝室费贷款
							</th>
							<td>
								<html:text name="rs" property="threexnqsfje" styleId="threexnqsfje"
									onblur="checkInputNum(this);jszje()" maxlength="10" />
							</td>

						</tr>
						<tr>
							<th>
								第三学年生活费贷款
							</th>
							<td>
								<html:text name="rs" property="threexnshf" styleId="threexnshf"
									onblur="checkInputNum(this);jszje()" maxlength="10" />
							</td>
							<th>
								第四学年学费贷款
							</th>
							<td>
								<html:hidden name="rs" property="fourxnzje" styleId="fourxnzje" />
								<html:text name="rs" property="fourxnxfje" styleId="fourxnxfje"
									onblur="checkInputNum(this);jszje()" maxlength="10" />
							</td>

						</tr>
						<tr>
							<th>
								第四学年寝室费贷款
							</th>
							<td>
								<html:text name="rs" property="fourxnqsfje" styleId="fourxnqsfje"
									onblur="checkInputNum(this);jszje()" maxlength="10" />
							</td>
							<th>
								第四学年生活费贷款
							</th>
							<td>
								<html:text name="rs" property="fourxnshf" styleId="fourxnshf"
									onblur="checkInputNum(this);jszje()" maxlength="10" />
							</td>
						</tr>
						<tr>

							<th>
								第五学年学费贷款
							</th>
							<td>
								<html:hidden name="rs" property="fivexnzje" styleId="fivexnzje" />
								<html:text name="rs" property="fivexnxfje" styleId="fivexnxfje"
									onblur="checkInputNum(this);jszje()" maxlength="10" />
							</td>
							<th>
								第五学年寝室费贷款
							</th>
							<td>
								<html:text name="rs" property="fivexnqsfje" styleId="fivexnqsfje"
									onblur="checkInputNum(this);jszje()" maxlength="10" />
							</td>

						</tr>
						<tr>
							<th>
								第五学年生活费贷款
							</th>
							<td>
								<html:text name="rs" property="fivexnshf" styleId="fivexnshf"
									onblur="checkInputNum(this);jszje()" maxlength="10" />
							</td>
							<th>
								贷款总金额
							</th>
							<td>
								<html:text name="rs" property="dkzje" styleId="dkzje"
									readonly="true" />
							</td>
						</tr>

					</tbody>
				</table>
			</div>


			<%@ include file="/comm/other/tsxx.jsp"%>
			<logic:equal name="doType" value="add">
				<logic:equal name="rs" property="sfysq" value="ysq">
					<script type="text/javascript">
						$("btn_bc").disabled="true";
					</script>
				</logic:equal>
			</logic:equal>
		</html:form>
	</body>
</html>
