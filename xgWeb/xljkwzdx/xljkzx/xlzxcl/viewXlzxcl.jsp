<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
	  <%@ include file="/syscommon/head.ini"%>
	  <script type="text/javascript" src="xljkwzdx/js/xljkwzdx.js"></script>
  </head>
  
  <body>
    <html:form action="/xljk_xlzxcl" method="post" styleId="xlzxclForm">
    	<table width="100%" border="0" class="formlist">
	    	<thead>
				<tr>
					<th colspan="4">
						<span>学生信息</span>
					</th>
				</tr>
			</thead>
			<%@ include file="/xsgzgl/comm/bdpz/selectStudent.jsp" %>
			<logic:present name="xsyysqForm">
				<thead>
					<tr>
						<th colspan="4">
							<span>心理咨询预约信息</span>
						</th>
					</tr>
				</thead>
				<tbody id="xlzxyyxxviewtbody" style="display: none;">
					<tr>
						<th width="20%">
							咨询预约说明
						</th>
						<td colspan="3" width="80%">
							${zxyysm }
						</td>
					</tr>
					<tr>
						<th>
							预约咨询时间
						</th>
						<td colspan="3">
							<bean:write name="xsyysqForm" property="yyzxsj"/>
						</td>
					</tr>
					<tr>
						<th>
							联系号码
						</th>
						<td colspan="3">
							<bean:write name="xsyysqForm" property="xslxdh"/>
						</td>
					</tr>
					<tr>
						<th>
							问题类型
						</th>
						<td colspan="3">
							${wtlxMcStr }
						</td>
					</tr>
					<tr>
						<th>
							问题简要描述
						</th>
						<td colspan="3">
							<bean:write name="xsyysqForm" property="yyzxzt" filter="false"/>
						</td>
					</tr>
					<tr>
						<th>
							其他备注<br/>
						</th>
						<td colspan="3">
							<bean:write name="xsyysqForm" property="yyzxxq" filter="false"/>
						</td>
					</tr>
				</tbody>
				<tbody>
					<td colspan="4" style="height: 10px;padding: 0px;" align="center">
						<div class="more--item_bottom">
							<p>
								<a href="#" class="down" style="text-decoration: none;" id="moreAndLess" onclick="zkandbxqwdview(this,'xlzxyyxxviewtbody');return false">&nbsp;&nbsp;展开</a>
							</p>
						</div>
					</td>
				</tbody>
				<thead>
					<tr>
						<th colspan="4">
							<span>咨询安排信息</span>
						</th>
					</tr>
				</thead>
				<logic:equal value="2" name="xsyysqForm" property="yyzt" >
					<tbody id="zxapxxviewtbody" style="display: none;">
						<tr>
							<th width="20%">
								预约状态
							</th>
							<td width="30%">
								${yyztmc }
							</td>
							<th width="20%">
								安排咨询师
							</th>
							<td width="30%">
								<a href='javascript:void(0);' style="text-decoration: underline;color: #0f5dc2!important;" onclick="zxsLink('${xlzxclForm.zxs}')">${zxsxm }</a>
							</td>
						</tr>
						<tr>
							<th>
								咨询安排日期
							</th>
							<td>
								<bean:write name="xlzxclForm" property="zzaprq"/>
							</td>
							<th>
								咨询时段
							</th>
							<td>
								<bean:write name="xlzxclForm" property="zxsdkssj"/>
								至
								<bean:write name="xlzxclForm" property="zxsdjssj"/>
							</td>
						</tr>
						<tr>
							<th>
								联系电话
							</th>
							<td colspan="3">
								<bean:write name="xlzxclForm" property="zxslxdh"/>
							</td>
						</tr>
						<tr>
							<th>
								咨询地址
							</th>
							<td colspan="3">
								<bean:write name="xlzxclForm" property="zxdz"/>
							</td>
						</tr>
						<tr>
							<th>
								备注
							</th>
							<td colspan="3">
								<bean:write name="xlzxclForm" property="bz" filter="false"/>
							</td>
						</tr>
					</tbody>
					<tbody>
						<td colspan="4" style="height: 10px;padding: 0px;" align="center">
							<div class="more--item_bottom">
								<p>
									<a href="#" class="down" style="text-decoration: none;" id="moreAndLess" onclick="zkandbxqwdview(this,'zxapxxviewtbody');return false">&nbsp;&nbsp;展开</a>
								</p>
							</div>
						</td>
					</tbody>
				</logic:equal>
				<logic:equal value="4" name="xsyysqForm" property="yyzt" >
					<tbody>
						<tr>
							<th width="20%">
								预约状态
							</th>
							<td width="30%">
								${yyztmc }
							</td>
							<th width="20%">
								安排咨询师
							</th>
							<td width="30%">	
								${zxsxm }
							</td>
						</tr>
						<tr>
							<th>
								咨询安排日期
							</th>
							<td>
								<bean:write name="xlzxclForm" property="zzaprq"/>
							</td>
							<th>
								咨询时段
							</th>
							<td>
								<bean:write name="xlzxclForm" property="zxsdkssj"/>
								至
								<bean:write name="xlzxclForm" property="zxsdjssj"/>
							</td>
						</tr>
						<tr>
							<th>
								联系电话
							</th>
							<td colspan="3">
								<bean:write name="xlzxclForm" property="zxslxdh"/>
							</td>
						</tr>
						<tr>
							<th>
								咨询地址
							</th>
							<td colspan="3">
								<bean:write name="xlzxclForm" property="zxdz"/>
							</td>
						</tr>
						<tr>
							<th>
								备注
							</th>
							<td colspan="3">
								<bean:write name="xlzxclForm" property="bz" filter="false"/>
							</td>
						</tr>
					</tbody>
				</logic:equal>
			</logic:present>
			<logic:notPresent name="xsyysqForm">
				<thead>
					<tr>
						<th colspan="4">
							<span>咨询反馈信息</span>
						</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th width="20%">
							咨询状态
						</th>
						<td colspan="3" width="80%">
							<bean:write name="xlzxclForm" property="zxzt"/>
						</td>
					</tr>
					<logic:equal value="已咨询" name="xlzxclForm" property="zxzt" >
						<tr>
							<th width="20%">
								咨询日期
							</th>
							<td width="30%">
								<bean:write name="xlzxclForm" property="zxrq"/>
							</td>
							<th width="20%">
								咨询时段
							</th>
							<td width="30%">
								<bean:write name="xlzxclForm" property="zxkssj"/>
								至
								<bean:write name="xlzxclForm" property="zxjssj"/>
							</td>
						</tr>
						<tr>
							<th>
								来访者主诉
							</th>
							<td colspan="3">
								<bean:write name="xlzxclForm" property="lfzzs" filter="false"/>
							</td>
						</tr>
						<tr>
							<th>
								咨询过程及主要<br/>的心理互动
							</th>
							<td colspan="3">
								<bean:write name="xlzxclForm" property="xlhd" filter="false"/>
							</td>
						</tr>
						<tr>
							<th>
								咨询后的总结
							</th>
							<td colspan="3">
								<bean:write name="xlzxclForm" property="zxzj" filter="false"/>
							</td>
						</tr>
						<tr>
							<th>
								该生问题类型
							</th>
							<td colspan="3">
								${gswtlxMcStr }
							</td>
						</tr>
						<tr>
							<th>
								咨询师来访者对<br/>
								咨询的接受程度
							</th>
							<td colspan="3">
								${jscdMcStr }
							</td>
						</tr>
						<tr>
							<th>
								访者心理问题<br/>
								严重程度评估
							</th>
							<td colspan="3">
								${yzcdpgMcStr }
							</td>
						</tr>
						<tr>
							<th>
								是否需要转介
							</th>
							<td>
								<bean:write name="xlzxclForm" property="sfxyzj"/>
							</td>
							<th>
								是否预约下次咨询
							</th>
							<td>
								<bean:write name="xlzxclForm" property="sfyyxczx"/>
								<logic:equal value="是" name="xlzxclForm" property="sfyyxczx" >
									<br/>时间：<bean:write name="xlzxclForm" property="xczxsj"/>
								</logic:equal>
							</td>
						</tr>
						<logic:present name="xlzxclForm" property="xszxpj">
							<tr>
								<th >
									咨询效果满意度评分
								</th>
								<td colspan="3" >
									<bean:write name="xlzxclForm" property="zxxgmydpf" filter="false"/>
								</td>
							</tr>
							<tr>
								<th>
									学生咨询评价
								</th>
								<td colspan="3">
									<bean:write name="xlzxclForm" property="xszxpj" filter="false"/>
								</td>
							</tr>
						</logic:present>
					</logic:equal>
				</tbody>
			</logic:notPresent>
			<logic:present name="xsyysqForm">
				<logic:equal value="2" name="xsyysqForm" property="yyzt" >
					<thead>
						<tr>
							<th colspan="4">
								<span>咨询反馈信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="20%">
								咨询状态
							</th>
							<td colspan="3" width="80%">
								<bean:write name="xlzxclForm" property="zxzt"/>
							</td>
						</tr>
						<logic:equal value="已咨询" name="xlzxclForm" property="zxzt" >
							<tr>
								<th width="20%">
									咨询日期
								</th>
								<td width="30%">
									<bean:write name="xlzxclForm" property="zxrq"/>
								</td>
								<th width="20%">
									咨询时段
								</th>
								<td width="30%">
									<bean:write name="xlzxclForm" property="zxkssj"/>
									至
									<bean:write name="xlzxclForm" property="zxjssj"/>
								</td>
							</tr>
							<tr>
								<th>
									来访者主诉
								</th>
								<td colspan="3">
									<bean:write name="xlzxclForm" property="lfzzs" filter="false"/>
								</td>
							</tr>
							<tr>
								<th>
									咨询过程及主要<br/>的心理互动
								</th>
								<td colspan="3">
									<bean:write name="xlzxclForm" property="xlhd" filter="false"/>
								</td>
							</tr>
							<tr>
								<th>
									咨询后的总结
								</th>
								<td colspan="3">
									<bean:write name="xlzxclForm" property="zxzj" filter="false"/>
								</td>
							</tr>
							<tr>
								<th>
									该生问题类型
								</th>
								<td colspan="3">
									${gswtlxMcStr }
								</td>
							</tr>
							<tr>
								<th>
									咨询师来访者对<br/>
									咨询的接受程度
								</th>
								<td colspan="3">
									${jscdMcStr }
								</td>
							</tr>
							<tr>
								<th>
									访者心理问题<br/>
									严重程度评估
								</th>
								<td colspan="3">
									${yzcdpgMcStr }
								</td>
							</tr>
							<tr>
								<th>
									是否需要转介
								</th>
								<td>
									<bean:write name="xlzxclForm" property="sfxyzj"/>
								</td>
								<th>
									是否预约下次咨询
								</th>
								<td>
									<bean:write name="xlzxclForm" property="sfyyxczx"/>
									<logic:equal value="是" name="xlzxclForm" property="sfyyxczx" >
										<br/>时间：<bean:write name="xlzxclForm" property="xczxsj"/>
									</logic:equal>
								</td>
							</tr>
							<logic:present name="xlzxclForm" property="xszxpj">
								<tr>
									<th >
										咨询效果满意度评分
									</th>
									<td colspan="3" >
										<bean:write name="xlzxclForm" property="zxxgmydpf" filter="false"/>
									</td>
								</tr>
								<tr>
									<th>
										学生咨询评价
									</th>
									<td colspan="3">
										<bean:write name="xlzxclForm" property="xszxpj" filter="false"/>
									</td>
								</tr>
							</logic:present>
						</logic:equal>
					</tbody>
				</logic:equal>
			</logic:present>
			<tfoot>
				<tr>
					<td colspan="4">
						<div class="btn">
							<button type="button" name="关 闭" onclick="iFClose();">
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
