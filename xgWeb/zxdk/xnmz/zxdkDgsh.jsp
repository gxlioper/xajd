<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript">
		function dgsh(shzt){
			refreshForm("/xgxt/zxdk_xnmz.do?method=zxdkDgsh&doType=save&shzt="+shzt);
		}
		
		function displayTbody(id){
			if($(id).style.display=="none"){
				$(id).style.display="";
			}else{
				$(id).style.display="none"
			}
		}
		</script>
	</head>
	<body onload="">
		<html:form action="/zxdk_xnmz" method="post">
			<!-- 隐藏域 -->
			<input type="hidden" name="url" id="url"
				value="/xgxt/zxdk_xnmz.do?method=zxdkSq" />
			<input type="hidden" name="guid" id="guid" value="${guid }" />
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- 隐藏域 -->
			<html:hidden property="shgw" styleId="shgw" />
			<div class="tab">
				<table width="100%" border="0" class="formlist">

					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">

									<button class="button2" id="btn_bc" onclick="dgsh('通过')">
										通 过
									</button>
									<button class="button2" id="btn_bc" onclick="dgsh('不通过')">
										不通过
									</button>
									<button class="button2" id="btn_bc" onclick="dgsh('退回')">
										退 回
									</button>
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
								学号
							</th>
							<td style="width:34%">
								${rs.xh }
								<html:hidden name="rs" property="xh" styleId="xh" />
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
								${rs.jtysr}
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
								${rs.fqxm }
							</td>
							<th style="width:16%">
								父亲身份证号
							</th>
							<td style="width:34%">
								${rs.fqsfzh }
							</td>
						</tr>
						<tr>
							<th style="width:16%">
								父亲工作单位
							</th>
							<td style="width:34%">
								${rs.fqgzdw }
							</td>
							<th style="width:16%">
								父亲联系方式
							</th>
							<td style="width:34%">
								${rs.fqlxfs }
							</td>
						</tr>
						<tr>
							<th style="width:16%">
								母亲姓名
							</th>
							<td style="width:34%">
								${rs.mqxm }
							</td>
							<th style="width:16%">
								母亲身份证号
							</th>
							<td style="width:34%">
								${rs.mqsfzh }
							</td>
						</tr>
						<tr>
							<th style="width:16%">
								母亲工作单位
							</th>
							<td style="width:34%">
								${rs.mqgzdw }
							</td>
							<th style="width:16%">
								母亲联系方式
							</th>
							<td style="width:34%">
								${rs.mqlxfs }
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
							<th style="width:16%">
								联系人姓名
							</th>
							<td style="width:34%">
								${rs.lxrxm }
							</td>
							<th style="width:16%">
								联系人家庭地址
							</th>
							<td style="width:34%">
								${rs.lxrdz }
							</td>
						</tr>
						<tr>
							<th>
								联系人工作单位
							</th>
							<td>
								${rs.lxrgzdw }
							</td>
							<th>
								联系人固定电话
							</th>
							<td>
								${rs.lxrgddh }
							</td>
						</tr>
						<tr>
							<th>
								联系人移动电话
							</th>
							<td>
								${rs.lxryddh }
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
							<th style="width:16%">
								见证人姓名
							</th>
							<td style="width:34%">
								${rs.jzrxm }
							</td>
							<th style="width:16%">
								见证人证件号
							</th>
							<td style="width:34%">
								${rs.jzrzjh }
							</td>
						</tr>
						<tr>
							<th>
								见证人证件类型代码
							</th>
							<td>
								${rs.jzrzjlxdm }
							</td>
							<th>
								见证人家庭地址
							</th>
							<td>
								${rs.jzrdz }
							</td>
						</tr>
						<tr>
							<th>
								见证人与申请人关系
							</th>
							<td>
								${rs.jzrgx }
							</td>
							<th>
								见证人邮编
							</th>
							<td>
								${rs.jzryb }
							</td>
						</tr>
						<tr>
							<th style="width:16%">
								见证人电话
							</th>
							<td style="width:34%">
								${rs.jzrdh }
							</td>
							<th style="width:16%">

							</th>
							<td style="width:34%">
							</td>
						</tr>
						<tr>
							<th style="width:16%">
								见证人意见
							</th>
							<td colspan="3" style="width:34%">
								<html:textarea name='rs' property='jzryj' styleId="jzryj"
									style="word-break:break-all;width:99%" 
									onblur="chLeng(this,250);" rows='4' />
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
							<th style="width:16%">
								第一学年学费贷款
							</th>
							<td style="width:34%">
								${rs.onexnxfje }
							</td>
							<th style="width:16%">
								第一学年寝室费贷款
							</th>
							<td style="width:34%">
								${rs.onexnqsfje }
							</td>

						</tr>
						<tr>
							<th>
								第二学年学费贷款
							</th>
							<td>
								${rs.twoxnxfje }
							</td>
							<th>
								第二学年寝室费贷款
							</th>
							<td>
								${rs.twoxnqsfje }
							</td>
						</tr>
						<tr>
							<th>
								第三学年学费贷款
							</th>
							<td>
								${rs.threexnxfje }
							</td>
							<th>
								第三学年寝室费贷款
							</th>
							<td>
								${rs.threexnqsfje }
							</td>

						</tr>
						<tr>
							<th>
								第四学年学费贷款
							</th>
							<td>
								${rs.fourxnxfje }
							</td>
							<th>
								第四学年寝室费贷款
							</th>
							<td>
								${rs.fourxnqsfje }
							</td>
						</tr>
						<tr>
							<th>
								第五学年学费贷款
							</th>
							<td>
								${rs.fivexnxfje }
							</td>
							<th>
								第五学年寝室费贷款
							</th>
							<td>
								${rs.fivexnqsfje }
							</td>

						</tr>
						<tr>

							<th>
								贷款总金额
							</th>
							<td>
								${rs.jzryb}
							</td>
							<th>

							</th>
							<td>

							</td>
						</tr>

					</tbody>
					<thead>

					</thead>
					<tbody>
						<logic:iterate name="shxxList" id="shxx">
							<logic:notEmpty name="shxx" property="xtgwid">
								<tr>
									<th>
										审核岗位
									</th>
									<td>
										${shxx.mc }
									</td>
									<th>
										审核结果
									</th>
									<td>
										${shxx.shzt }
									</td>
								</tr>
								<tr>
									<th>
										审核意见
									</th>
									<td colspan="3">
										<logic:notEqual name="shxx" property="xtgwid" value="${shgw}">
											<html:textarea name='shxx' property='shyj' styleId="shyj"
												style="word-break:break-all;width:99%" disabled="true"
												onblur="chLeng(this,250);" rows='4' />
										</logic:notEqual>
									
										<logic:equal name="shxx" property="xtgwid" value="${shgw}">
											<html:textarea name='shxx' property='shyj' styleId="shyj"
												style="word-break:break-all;width:99%" 
												onblur="chLeng(this,250);" rows='4' />
										</logic:equal>
									</td>
								</tr>
							</logic:notEmpty>
						</logic:iterate>
					</tbody>
				</table>
			</div>
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>
