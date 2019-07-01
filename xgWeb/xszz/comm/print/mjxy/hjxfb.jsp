<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>
<script type='text/javascript' src='/xgxt/dwr/interface/moneyFormat.js'></script>
<script type="text/javascript">
<!--
	function getDxje(){
		var sqzje = $('sqzje').value;
		
		if (0 != sqzje) {
			moneyFormat.format(sqzje,function(data){
				temp.innerHTML += data+"&nbsp;&nbsp;(￥&nbsp;&nbsp;"+sqzje+"&nbsp;&nbsp;元)";
			})
		} else {
			temp.innerHTML += "零元"+"&nbsp;&nbsp;(￥&nbsp;&nbsp;"+sqzje+"&nbsp;&nbsp;元)";
		}
		
	}
	
//-->
</script>
<!-- 头文件 -->
<html:html>
<body onload="getDxje();">
	<!-- 打印控件begin -->
		<object id="WebBrowser" width=0 height=0
			classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
		<style media='print'>
			.noPrin{display:none;}
		</style>
	<!-- end -->
	<html:form action="/typj" method="post">
		<input type="hidden" name="sqzje" id="sqzje" value="${rs.sqzje }"/>
		
		
		<table width="95%" border="0" id="theTable" align="center">
			<tr>
				<td align="center">
					<h2>
						闽江学院学生缓交学费审批表V1.2
					</h2>
				</td>
			</tr>
			<tr>
				<td align="center">
					<table width="100%" class="tbstyle">
						<tr>
							<td width="7%"></td>
							<td width="7%"></td>
							<td width="8%"></td>
							<td width="8%"></td>
							<td width="4%"></td>
							<td width="6%"></td>
							<td width="5%"></td>
							<td width="5%"></td>
							<td width="8%"></td>
							<td width="9%"></td>
							<td width="8%"></td>
							<td width="10%"></td>
							<td width="8%"></td>
							<td width="8%"></td>
						</tr>
						<tr height="40px">
							<td align="center">姓名</td>
							<td align="center" colspan="2">${rs.xm }</td>
							<td align="center">性别</td>
							<td align="center" colspan="2">${rs.xb }</td>
							<td align="center" colspan="2">院系</td>
							<td align="center" colspan="2">${rs.xymc }</td>
							<td align="center">年级<br/>班级</td>
							<td align="center" colspan="3">${rs.nj }<br/>
								${rs.bjmc }
							</td>
						</tr>
						<tr height="40px">
							<td align="center">宿舍</td>
							<td align="center"colspan="2">${rs.ssbh }</td>
							<td align="center">联系电话</td>
							<td align="center"colspan="7">${rs.lxdh }</td>
							<td align="center">月收入（元）</td>
							<td align="center"colspan="2">${rs.jtyzsr }</td>
						</tr>
						<tr height="40px">
							<td align="center">家庭<br/>地址</td>
							<td align="center" colspan="10">${rs.jtdz }</td>
							<td align="center">总人口（人）</td>
							<td align="center"colspan="2">${rs.jtrs }</td>
						</tr>
						<tr style="height:40px">
							<td rowspan="${cyNum+1 }" align="center">
									家<br>
									庭<br>
									成<br>
									员<br>
									情<br>
									况
							</td>
							<td align="center" colspan="2">
									姓名
							</td>
							<td align="center" colspan="2">
									称谓
							</td>
							<td align="center" colspan="2">
									年龄
							</td>
							<td align="center" colspan="2">
									月收入（元）
							</td>
							<td align="center" colspan="5">
									工作（学习）单位
							</td>
						</tr>
						<logic:iterate name="cyList" id="cy">
						<tr style="height:30px">
							<td align="center"  colspan="2">
									${cy.cyxm }&nbsp;
							</td>
							<td align="center"  colspan="2">
									${cy.mc }&nbsp;
							</td>
							<td align="center" colspan="2">
									${cy.cynl }&nbsp;
							</td>
							<td align="center" colspan="2">
								<logic:present name="cy" property="cynsr">
									<script>
										document.write(Math.round(Number(${cy.cynsr })/12));
									</script>
								</logic:present>
								&nbsp;
							</td>
							<td align="center"  colspan="5">
									${cy.cygzdw }&nbsp;
							</td>
						</tr>
						</logic:iterate>
						<tr style="height:40px">
							<td  align="center"colspan="2">应缴总费用（元）</td>
							<td  align="center"colspan="2">${rs.yjzfy }</td>
							<td  align="center"colspan="3">已缴总费用（元）</td>
							<td  align="center"colspan="3">${rs.sjzfy }</td>
							<td  align="center"colspan="2">欠缴总费用（元）</td>
							<td  align="center"colspan="2">${rs.qjzfy }</td>
						</tr>
						<tr style="height:40px">
							<td align="center" colspan="3">申请缴费方式（选择<br/>其中一项打“√”）</td>
							<td colspan="11">
								<logic:equal name="rs" property="sqjffs" value="减免">
									1√．减（免）<u>&nbsp;${rs.sqzje }&nbsp;</u>元  2．贷款（借款）_____元  3．缓交_______元
								</logic:equal>
								<logic:equal name="rs" property="sqjffs" value="贷款">
									1．减（免）____元  2√．贷款（借款）<u>&nbsp;${rs.sqzje }&nbsp;</u>元  3．缓交_______元
								</logic:equal>
								<logic:equal name="rs" property="sqjffs" value="缓交">
									1．减（免）____元  2．贷款（借款）_____元  3√．缓交<u>&nbsp;${rs.sqzje }&nbsp;</u>元
								</logic:equal>
								<logic:notPresent name="rs" property="sqjffs">
									1．减（免）____元  2．贷款（借款）_____元  3．缓交_______元
								</logic:notPresent>
							</td>
						</tr>
						<tr style="height:40px">
							<td colspan="2" align="center">申请总金额</td>
							<td colspan="12">
								<div id="temp" align="center">
									
								</div>
							</td>
						</tr>
						<tr>
							<td align="center">申请人<br/>承诺</td>
							<td colspan="13">
								<p style="height:180px">
									<br/><br/><br/><br/><br/>
									&nbsp;&nbsp;&nbsp;&nbsp;本人所提供家庭情况属实，若弄虚作假，愿意接受学校处罚。
								</p>
								<div align="right">
									申请人签名：
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<br/>
									年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									日&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;									
								</div>
							</td>
						</tr>
						<tr>
							<td align="center">院系学<br/>生资助<br/>工作组<br/>意见</td>
							<td colspan="13">
								<p style="height:180px">
									<br/><br/><br/>
									经研究决定同意该生：<br/>
									<logic:equal name="rs" property="sqjffs" value="减免">
									1．减（免）<u>&nbsp;${rs.sqzje }&nbsp;</u>元  2．贷款（借款）_____元  3．缓交_______元
								</logic:equal>
								<logic:equal name="rs" property="sqjffs" value="贷款">
									1．减（免）____元  2．贷款（借款）<u>&nbsp;${rs.sqzje }&nbsp;</u>元  3．缓交_______元
								</logic:equal>
								<logic:equal name="rs" property="sqjffs" value="缓交">
									1．减（免）____元  2．贷款（借款）_____元  3．缓交<u>&nbsp;${rs.sqzje }&nbsp;</u>元
								</logic:equal>
								
								<logic:notPresent name="rs" property="sqjffs">
									1．减（免）____元  2．贷款（借款）_____元  3．缓交_______元
								</logic:notPresent>
									
								</p>
								<div align="right">
									辅导员签章：
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									 院（系）领导签章：
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<br/>
									年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									日&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;		
									年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									日&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;									
								</div>
							</td>
						</tr>
					</table>
					<div align="right">
						闽江学院学生资助管理中心制表
					</div>
				</td>
			</tr>
		</table>
	</html:form>
	<div align="center" class='noPrin'>
		<button type="button"  class='button2' onclick="WebBrowser.ExecWB(8,1);return false;">
			页面设置
		</button>
		<button type="button"  class='button2' onclick="WebBrowser.ExecWB(7,1);return false;">
			打印预览
		</button>
		<button type="button"  class='button2' onclick="WebBrowser.ExecWB(6,6);return false;">
			直接打印
		</button>
	</div>
</body>
</html:html>
