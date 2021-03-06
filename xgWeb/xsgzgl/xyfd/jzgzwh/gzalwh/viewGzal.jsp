<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="/syscommon/head.ini"%>
	<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
	<script type="text/javascript" src="js/check.js"></script>
	<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
	<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
	<script type="text/javascript" src="js/calendar/calendar.js"></script>
	<script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>
	<script type="text/javascript" src="xsgzgl/comm/fileUpload/jmf.js"></script>
	<script type="text/javascript" src="xsgzgl/comm/fileUpload/f.js"></script>
	<script type="text/javascript" src="xsgzgl/xyfd/jzgzwh/gzalwh/js/gzal.js"></script>
	<script type="text/javascript">

	</script>

</head>
<body style="width:100%">
<html:form action="/xyfd_gzaljl" method="post" styleId="demoForm">
	<div style='width:100%;height: 500px;overflow-x:hidden;overflow-y:auto;'>
		<table width="100%" border="0" class="formlist">
			<thead></thead><%-- 防止与学生基本信息错位--%>
			<tbody>
				<tr>
					<th>建档日期</th>
					<td>
							${model.jdrq}
					</td>
					<th>特别标注</th>
					<td colspan="3">
							${model.tbbz}
					</td>
				</tr>
			<tr>
				<th>建档原因</th>
				<td colspan="5">
						${model.jdyy}
				</td>
			</tr>
			</tbody>
			<thead>
			<tr>
				<th colspan="6">
					<span>学生基本情况</span>
				</th>
			</tr>
			</thead>
			<tbody>
			<tr>
				<th width="10%">学生姓名</th>
				<td width="20%">
					${xsxx.xm}
				</td>
				<th width="10%">性别</th>
				<td width="20%">
					${xsxx.xbmc}
				</td>
				<th width="10%">学院</th>
				<td width="20%">
					${xsxx.xymc}
				</td>
			</tr>
			<tr>
				<th>班级</th>
				<td>
					${xsxx.zybjmc}
				</td>
				<th>宿舍</th>
				<td>
					${xsxx.ss}
				</td>
				<th>手机</th>
				<td>
					${xsxx.sjhm}
				</td>
			</tr>
			<tr>
				<th>家庭地址</th>
				<td colspan="5">
					${xsxx.jtdz}
				</td>
			</tr>
			<tr>
				<td colspan="6">
					<table style="width: 100%">
						<thead>
							<tr>
								<th style="text-align: center;">学年</th>
								<td colspan="2">
									${model.xn}
								</td>
								<th style="text-align: center;">学期</th>
								<td>
									<logic:equal name="model" property="xq" value="01">
										第一学期
									</logic:equal>
									<logic:equal name="model" property="xq" value="02">
										第二学期
									</logic:equal>
									<logic:equal name="model" property="xq" value="3">
										小学期
									</logic:equal>
								</td>
							</tr>
							<tr>
								<th style="width: 30%;text-align: center;">不及格课程</th>
								<th style="width: 15%;text-align: center;">分数</th>
								<th style="width: 15%;text-align: center;">学分</th>
								<th style="width: 15%;text-align: center;">补考重修</th>
								<th style="width: 25%;text-align: center;">考试时间</th>
							</tr>
						</thead>
						<tbody>
						<logic:iterate id="j" name="bjgkclist" indexId="i">
							<tr>
								<td>${j.kcmc}</td>
								<td>${j.cj}</td>
								<td>${j.xf}</td>
								<td>${j.cxckmc}</td>
								<td>${j.ksrq}</td>
							</tr>
						</logic:iterate>
						</tbody>
					</table>
				</td>
			</tr>
			</tbody>
			<thead>
				<tr>
					<th colspan="6">
						<span>联系人基本情况</span>
					</th>
				</tr>
			</thead>
			<tbody>
				<tr><%--  防止表格异常--%></tr>
				<logic:iterate id="j" name="fdyxx" indexId="i">
				<tr>
					<th>辅导员</th>
					<td>${j.xm}</td>
					<th>所在部门</th>
					<td>${j.bmmc}</td>
					<th>联系电话</th>
					<td>${j.lxdh}</td>
				</tr>
				</logic:iterate>
				<logic:iterate id="j" name="bzrxx" indexId="i">
				<tr>
					<th>班主任</th>
					<td>${j.xm}</td>
					<th>所在部门</th>
					<td>${j.bmmc}</td>
					<th>联系电话</th>
					<td>${j.lxdh}</td>
				</tr>
				</logic:iterate>

			</tbody>
			<thead>
				<tr>
					<th colspan="6">
						<span>分析改进</span>
					</th>
				</tr>
			</thead>
			<tbody>
			<tr>
				<th colspan="1" style="text-align: center">顺序</th>
				<th colspan="2" style="text-align: center">内容参考</th>
				<th colspan="3" style="text-align: center">问题记录</th>
			</tr>
			<tr>
				<td>
					不及格课程分析
				</td>
				<td colspan="2">
					（1）本学期所开设课程的门数；<br>
					（2）本学期总学分数；<br>
					（3）不及格课程学分综合、在总学分中所占比例；<br>
					（4）不及格课程类型和性质；<br>
					（5）是否为最高、最低学分课程；<br>
					（6）不及格课程之间的相关性；
				</td>
				<td colspan="3">
					${model.bjgkcfx}
				</td>
			</tr>
			<tr>
				<td>
					不及格原因分析
				</td>
				<td colspan="2">
					（1）学习态度是否端正；<br>
					（2）对学习是否有兴趣；<br>
					（3）学习时间安排是否合理；<br>
					（4）学习任务是否过重；<br>
					（5）课余时间是否利用充分；<br>
					（6）社会兼职是否过多；<br>
					（7）学习方法是否不当；<br>
					（8）对教师的授课方法是否认同；<br>
					（9）基础课程的学习是否连贯；<br>
					（10）是否沉湎于网络；<br>
					（11）是否因感情原因；<br>
					（12）是否有过奖惩记录；<br>
					（13）是否因积欠的不及格课程过重；
				</td>
				<td colspan="3">
					${model.bjgyyfx}
				</td>
			</tr>
			<tr>
				<td>
					改进措施
				</td>
				<td colspan="2">
					（1）学生自己准备如何补救和改进；<br>
					（2）学习方法、努力程度、时间保证；<br>
					（3）是否减少不必要的活动；<br>
					（4）是否准备结成互助小组；<br>
					（5）是否正被重修、合适重修；<br>
					（6）是否准备制定完备的学习计划；<br>
					（7）补救和改进的具体建议与措施；<br>
					（8）是否告知学号是呢个家长；<br>
					（9）对（有可能）退学警告、退学的认识和态度；
				</td>
				<td colspan="3">
					${model.gjcs}
				</td>
			</tr>
			<tr>
				<td>
					其他原因分析
				</td>
				<td colspan="2">
					（1）有没有学习之外的困难；<br>
					（2）困难产生的原因；<br>
					（3）心里因素；<br>
					（4）性格及人际交往情况；<br>
					（5）经济因素（学费、生活费是否有来源，是否申请贷款）；<br>
					（6）家庭背景（父母职业及家庭收入）；<br>
				</td>
				<td colspan="3">
					${model.qtyyfx}
				</td>
			</tr>
			</tbody>
			<thead>
				<tr>
					<th colspan="6"><span>工作记录</span></th>
				</tr>
			</thead>
			<tbody>
			<logic:iterate id="j" name="gzjlList" indexId="i">
				<tr>
					<th>（${i+1}）工作时间</th>
					<td colspan="2">${j.gzsj}</td>
					<th>工作地点</th>
					<td colspan="2">${j.gzdd}</td>
				</tr>
				<tr>
					<th>下一步建议</th>
					<td colspan="5">${j.jymc}<logic:equal value="06" name="j" property="xybjy">：${j.jtcs}</logic:equal></td>
				</tr>
			</logic:iterate>
			</tbody>
		</table>
	</div>
		<div style="width: 100%;height: 50px;position:fixed;bottom:0;">
			<table width="100%" border="0" class="formlist">
				<tfoot>
				<tr>
					<td colspan="4" >
						<div class="btn">
							<button type="button" name="关 闭" onclick="iFClose();">
								关 闭
							</button>
						</div>
					</td>
				</tr>
				</tfoot>
			</table>
		</div>
</html:form>
</body>
</html>

 