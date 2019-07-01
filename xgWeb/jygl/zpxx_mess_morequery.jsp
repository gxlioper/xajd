<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	</head>
	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>单位意见反馈</a>
			</p>
		</div>

		<div class="tab" style="margin-top: 0px; padding-top: 0px">
			<table width="100%" border="0" class="formlist">
				<thead>
					<tr>
						<th colspan="4">
							<span>单位意见反馈</span>
						</th>
					</tr>
				</thead>
				<tfoot>
					<tr>
						<td colspan="4">
							<div class="btn">
								<button onclick="expAppTab('yjfk','企业意见反馈','')"
									>
									打 印
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
				<tbody>
					<tr>
						<th>
							单位名称
						</th>
						<td>
							<html:text name="rs" property="gsmc" readonly="true" />
						</td>
						<th>
							单位性质
						</th>
						<td>
							<html:text name="rs" property="dwxz" style="width:30px"
								readonly="true" />
						</td>
					</tr>
					<tr>
						<th>
							行业分类
						</th>
						<td>
							<html:text name="rs" property="hyfl" style="width:230px"
								readonly="true" />
						</td>
						<th>
							时间
						</th>
						<td>
							<html:text name="rs" property="fksj" style="width:100px"
								readonly="true" />
						</td>
					</tr>
					<tr>
						<th></th>
						<td></td>
						<th></th>
						<td></td>
					</tr>
				</tbody>
				<thead>
					<tr>
						<th colspan="4">
							<span>调查条目</span>
						</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td colspan="4">
							<table>
								<tbody>
									<tr>
										<td>
											1、贵单位对本校开设之专业课程的满意度
										</td>
										<td colspan="3">
											&nbsp;&nbsp;&nbsp;&nbsp;
											<html:radio name="rs" property="zyzs1" value="非常满意"></html:radio>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<html:radio name="rs" property="zyzs1" value="满意"></html:radio>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<html:radio name="rs" property="zyzs1" value="一般"></html:radio>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<html:radio name="rs" property="zyzs1" value="不满意"></html:radio>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<html:radio name="rs" property="zyzs1" value="非常不满意"></html:radio>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										</td>
									</tr>
									<tr>
										<td>
											2、贵单位认为我校所授之内容符合业界实际需求
										</td>
										<td colspan="3">
											&nbsp;&nbsp;&nbsp;&nbsp;
											<html:radio name="rs" property="zyzs2" value="非常满意"></html:radio>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<html:radio name="rs" property="zyzs2" value="满意"></html:radio>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<html:radio name="rs" property="zyzs2" value="一般"></html:radio>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<html:radio name="rs" property="zyzs2" value="不满意"></html:radio>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<html:radio name="rs" property="zyzs2" value="非常不满意"></html:radio>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										</td>
									</tr>
									<tr>
										<td>
											3、我校毕业生所学技能和工作实际衔接
										</td>
										<td colspan="3">
											&nbsp;&nbsp;&nbsp;&nbsp;
											<html:radio name="rs" property="gzbx1" value="非常满意"></html:radio>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<html:radio name="rs" property="gzbx1" value="满意"></html:radio>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<html:radio name="rs" property="gzbx1" value="一般"></html:radio>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<html:radio name="rs" property="gzbx1" value="不满意"></html:radio>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<html:radio name="rs" property="gzbx1" value="非常不满意"></html:radio>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										</td>
									</tr>
									<tr>
										<td>
											4、我校毕业生表现符合贵公司的期许
										</td>
										<td colspan="3">
											&nbsp;&nbsp;&nbsp;&nbsp;
											<html:radio name="rs" property="gzbx2" value="非常满意"></html:radio>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<html:radio name="rs" property="gzbx2" value="满意"></html:radio>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<html:radio name="rs" property="gzbx2" value="一般"></html:radio>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<html:radio name="rs" property="gzbx2" value="不满意"></html:radio>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<html:radio name="rs" property="gzbx2" value="非常不满意"></html:radio>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										</td>
									</tr>
									<tr>
										<td>
											5、我校毕业生的表达与沟通能力
										</td>
										<td colspan="3">
											&nbsp;&nbsp;&nbsp;&nbsp;
											<html:radio name="rs" property="jnjq1" value="非常满意"></html:radio>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<html:radio name="rs" property="jnjq1" value="满意"></html:radio>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<html:radio name="rs" property="jnjq1" value="一般"></html:radio>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<html:radio name="rs" property="jnjq1" value="不满意"></html:radio>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<html:radio name="rs" property="jnjq1" value="非常不满意"></html:radio>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										</td>
									</tr>
									<tr>
										<td>
											6、我校毕业生的电脑运用能力
										</td>
										<td colspan="3">
											&nbsp;&nbsp;&nbsp;&nbsp;
											<html:radio name="rs" property="jnjq2" value="非常满意"></html:radio>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<html:radio name="rs" property="jnjq2" value="满意"></html:radio>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<html:radio name="rs" property="jnjq2" value="一般"></html:radio>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<html:radio name="rs" property="jnjq2" value="不满意"></html:radio>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<html:radio name="rs" property="jnjq2" value="非常不满意"></html:radio>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										</td>
									</tr>
									<tr>
										<td>
											7、我校毕业生的创意及思考能力
										</td>
										<td colspan="3">
											&nbsp;&nbsp;&nbsp;&nbsp;
											<html:radio name="rs" property="jnjq3" value="非常满意"></html:radio>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<html:radio name="rs" property="jnjq3" value="满意"></html:radio>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<html:radio name="rs" property="jnjq3" value="一般"></html:radio>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<html:radio name="rs" property="jnjq3" value="不满意"></html:radio>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<html:radio name="rs" property="jnjq3" value="非常不满意"></html:radio>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										</td>
									</tr>
									<tr>
										<td>
											8、我校毕业生的外语会话能力
										</td>
										<td colspan="3">
											&nbsp;&nbsp;&nbsp;&nbsp;
											<html:radio name="rs" property="jnjq4" value="非常满意"></html:radio>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<html:radio name="rs" property="jnjq4" value="满意"></html:radio>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<html:radio name="rs" property="jnjq4" value="一般"></html:radio>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<html:radio name="rs" property="jnjq4" value="不满意"></html:radio>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<html:radio name="rs" property="jnjq4" value="非常不满意"></html:radio>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										</td>
									</tr>
									<tr>
										<td>
											9、我校毕业生的独立思考与分析能力
										</td>
										<td colspan="3">
											&nbsp;&nbsp;&nbsp;&nbsp;
											<html:radio name="rs" property="jnjq5" value="非常满意"></html:radio>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<html:radio name="rs" property="jnjq5" value="满意"></html:radio>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<html:radio name="rs" property="jnjq5" value="一般"></html:radio>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<html:radio name="rs" property="jnjq5" value="不满意"></html:radio>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<html:radio name="rs" property="jnjq5" value="非常不满意"></html:radio>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										</td>
									</tr>
									<tr>
										<td>
											10、贵单位对我校毕业生总体满意度
										</td>
										<td colspan="3">
											&nbsp;&nbsp;&nbsp;&nbsp;
											<html:radio name="rs" property="myd" value="非常满意"></html:radio>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<html:radio name="rs" property="myd" value="满意"></html:radio>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<html:radio name="rs" property="myd" value="一般"></html:radio>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<html:radio name="rs" property="myd" value="不满意"></html:radio>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<html:radio name="rs" property="myd" value="非常不满意"></html:radio>
										</td>
									</tr>
								</tbody>
							</table>
						</td>
					</tr>
				</tbody>
				<thead>
					<tr>
						<td colspan="4">
							<span>详细信息反馈</span>
						</td>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th>
							单位名称
						</th>
						<td colspan="3">
							<bean:write name="rs" property="gsmc" />
						</td>
					</tr>
					<tr>
						<th>
							意见反馈标题
						</th>
						<td colspan="3">
							<html:text name="rs" property="yjfkbt" style="width=100%"
								readonly="true" />
						</td>
					</tr>
					<tr>
						<th>
							意见反馈内容
						</th>
						<td colspan="3">
							<html:textarea name="rs" property="yjfknr" style="width:100%"
								rows="26" readonly="true" />
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</body>
</html>

