<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
	  <%@ include file="/syscommon/head.ini"%>
	  <script type="text/javascript" src="xljkwzdx/js/xljkwzdx.js"></script>
  </head>
  
  <body>
    <html:form action="/xljk_xsyyzx" method="post" styleId="xsyysqForm">
    	<table width="100%" border="0" class="formlist">
    		<logic:present name="xsyysqForm" property="sqid">
	    		<thead>
					<tr>
						<th colspan="4">
							<span>心理咨询预约信息</span>
						</th>
					</tr>
				</thead>
				<tbody>
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
							预约咨询师
						</th>
						<td colspan="3">
							<a href='javascript:void(0);' style="text-decoration: underline;color: #0f5dc2!important;" onclick="zxsLink('${xsyysqForm.zxs}')">${yyzxsxm }</a>
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
				<logic:notEqual value="1" name="xsyysqForm" property="yyzt">
					<logic:notEqual value="3" name="xsyysqForm" property="yyzt">
						<thead>
							<tr>
								<th colspan="4">
									<span>咨询安排信息</span>
								</th>
							</tr>
						</thead>
					</logic:notEqual>
				</logic:notEqual>
				<logic:equal value="2" name="xsyysqForm" property="yyzt">
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
								<a href='javascript:void(0);' style="text-decoration: underline;color: #0f5dc2!important;" onclick="zxsLink('${xlzxclMap.zxs}')">${zxsxm }</a>
							</td>
						</tr>
						<tr>
							<th>
								咨询安排日期
							</th>
							<td>
								<bean:write name="xlzxclMap" property="zzaprq"/>
							</td>
							<th>
								咨询时段
							</th>
							<td>
								<bean:write name="xlzxclMap" property="zxsdkssj"/>
								至
								<bean:write name="xlzxclMap" property="zxsdjssj"/>
							</td>
						</tr>
						<tr>
							<th>
								联系电话
							</th>
							<td colspan="3">
								<bean:write name="xlzxclMap" property="zxslxdh"/>
							</td>
						</tr>
						<tr>
							<th>
								咨询地址
							</th>
							<td colspan="3">
								<bean:write name="xlzxclMap" property="zxdz"/>
							</td>
						</tr>
						<tr>
							<th>
								备注
							</th>
							<td colspan="3">
								<bean:write name="xlzxclMap" property="bz" filter="false"/>
							</td>
						</tr>
					</tbody>
				</logic:equal>
				<logic:equal value="4" name="xsyysqForm" property="yyzt">
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
								<bean:write name="xlzxclMap" property="zzaprq"/>
							</td>
							<th>
								咨询时段
							</th>
							<td>
								<bean:write name="xlzxclMap" property="zxsdkssj"/>
								至
								<bean:write name="xlzxclMap" property="zxsdjssj"/>
							</td>
						</tr>
						<tr>
							<th>
								联系电话
							</th>
							<td colspan="3">
								<bean:write name="xlzxclMap" property="zxslxdh"/>
							</td>
						</tr>
						<tr>
							<th>
								咨询地址
							</th>
							<td colspan="3">
								<bean:write name="xlzxclMap" property="zxdz"/>
							</td>
						</tr>
						<tr>
							<th>
								备注
							</th>
							<td colspan="3">
								<bean:write name="xlzxclMap" property="bz" filter="false"/>
							</td>
						</tr>
					</tbody>
				</logic:equal>
				<logic:equal value="5" name="xsyysqForm" property="yyzt">
					<tbody>
						<tr>
							<th width="20%">
								预约状态
							</th>
							<td colspan="3" width="80%">
								${yyztmc }
							</td>
						</tr>
						<tr>
							<th width="20%">
								预约失败原因
							</th>
							<td colspan="3" width="80%">
								<bean:write name="xsyysqForm" property="yysbyy" filter="false"/>
							</td>
						</tr>
					</tbody>
				</logic:equal>
				<logic:present name="xlzxclMap" property="zxid">
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
							咨询情况
						</th>
						<td colspan="3" width="80%">
							<bean:write name="xlzxclMap" property="zxzt"/>
						</td>
					</tr>
					<tr>
						<th width="20%">
							咨询效果满意度评分
						</th>
						<td colspan="3" width="80%">
							<bean:write name="xlzxclMap" property="zxxgmydpf"/>
						</td>
					</tr>
					<tr>
						<th width="20%">
							咨询评价
						</th>
						<td colspan="3" width="80%">
							<bean:write name="xlzxclMap" property="xszxpj" filter="false"/>
						</td>
					</tr>
				</tbody>
				</logic:present>
			</logic:present>
			<logic:notPresent name="xsyysqForm" property="sqid">
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
							咨询情况
						</th>
						<td colspan="3" width="80%">
							<bean:write name="xlzxclMap" property="zxzt"/>
						</td>
					</tr>
					<tr>
						<th width="20%">
							咨询效果满意度评分
						</th>
						<td colspan="3" width="80%">
							<bean:write name="xlzxclMap" property="zxxgmydpf"/>
						</td>
					</tr>
					<tr>
						<th width="20%">
							咨询评价
						</th>
						<td colspan="3" width="80%">
							<bean:write name="xlzxclMap" property="xszxpj" filter="false"/>
						</td>
					</tr>
				</tbody>
			</logic:notPresent>
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
