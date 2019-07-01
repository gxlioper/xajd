<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/studetailFunction.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getStuDetails.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/dwjlgl.js'></script>
	<script type="text/javascript">
		function getZjlgStuInfo(type){
			var xh = document.getElementById("xh").value;
			if(type == "xxxx"){
				
				//学生成绩信息
				var colList = ["tjxnxq", "xxzlf", "jqpjf", "bjg"];
				getStuDetails.getAllOfInfo(xh,'view_zjlg_xxzlf',colList,getXscjInfo);
				
				//学籍异动记录
				colList = ["ydlbmc", "ydqxymc", "ydqzymc", "ydqbjmc", "ydhxymc", "ydhzymc", "ydhbjmc","ydrq"];
				getStuDetails.getAllOfInfo(xh,'view_xjydjbxx',colList,getYdInfo);
			}else if(type == "jfxx"){
				//缴费信息
				var colList = ["sfnd", "yjxf", "yjzsf", "sjjf","jfzt"];
				getStuDetails.getAllOfInfo(xh,'view_zjlg_jfxx',colList,getJfInfo);
				//缴费总计
				var colList = ["sfnd", "yjxf", "yjzsf", "sjjf","jfzt"];
				getStuDetails.getAllOfInfo(xh,'view_zjlg_jfxxzj',colList,getJfZjInfo);
			}else if(type == "jlxx"){
				//历年奖学金获得情况
				var colList = ["xn", "jxjmc", "jlje", "xxshsj"];
				getStuDetails.getAllOfInfo(xh,'view_xsjxjb',colList,getJxjInfo);
			}else if(type == "cfqk"){
				//历年受处分情况
				colList = ["cfwh", "cflbmc", "cfsj", "cfyymc"];
				getStuDetails.getAllOfInfo(xh,'view_wjcf',colList,getWjInfo);
			}else if(type == "tbxx"){
				//投保信息
				var colList = ["bxxzmc", "tbsj"];
				getStuDetails.getAllOfInfo(xh,'view_xsbxxx',colList,getTbInfo); 
				
				//赔保信息
				colList = ["lpje","slsj" ,"lpyy"];
				getStuDetails.getAllOfInfo(xh,'view_lpxx',colList,getPbInfo); 
			}else if(type == "qgzx"){
				//勤工助学
				var colList = [ "xn", "xqmc","bjmc","gwdm","sqsj"];
				getStuDetails.getAllOfInfo(xh,'view_xsgwxx',colList,getQgzxInfo);	
				
				//酬金发放
				colList = ["xn", "xqmc", "yf","bjmc","cjje","ffsj"];
				getStuDetails.getAllOfInfo(xh,'view_xscjff',colList,getCjffInfo);
			}else if(type == "djxx"){
				//学生培训信息
				var colList = ["nd", "xn", "xqmc","bjmc","pxxmmc", "pxjg"];	
				getStuDetails.getAllOfInfo(xh,'view_xspxxx',colList,getPxxxInfo);
				
				//入党积极分子
				colList = ["nd", "xn", "xqmc", "bjmc","kssj"];
				getStuDetails.getAllOfInfo(xh,'view_rdjjfzxx',colList,getRdjjfzInfo);
				
				//预备党员
				colList = ["nd", "xn", "xqmc", "bjmc","kssj", "jssj"];
				getStuDetails.getAllOfInfo(xh,'view_ybdyxx',colList,getYbdyInfo);
				
				//党员
				colList = ["nd", "xn", "xqmc", "bjmc","rdsj"];
				getStuDetails.getAllOfInfo(xh,'view_dyxx',colList,getDyInfo);	
			}else if(type == "xsgb"){
				//学生干部
				var colList = ["bjgbmc", "kssj", "jssj"];	
				getStuDetails.getAllOfInfo(xh,'view_bjgbxx',colList,getXsgbInfo);
			}else if(type == "cxqk"){
				//诚信情况
				var colList = ["cxjl", "jlsj"];	
				getStuDetails.getAllOfInfo(xh,'view_zjlg_cxqk',colList,getCxqkInfo);
			}else if(type == "dwjl"){
				//对外交流
				var colList = ["xmmc", "cgsj", "hgsj"];	
				getStuDetails.getAllOfInfo(xh,'view_zjlg_dwjl',colList,getDwjlInfo);
			}else if(type == "rwxx"){
				//入伍信息
				var colList = ["rwsj", "twsj","fyqjbx"];	
				getStuDetails.getAllOfInfo(xh,'view_zjlg_rwxx',colList,getRwxxInfo);
			}else if(type == "szxx"){
				//助学金情况
				var colList = ["xn", "sqsj","zxjdj","zxjje"];	
				getStuDetails.getAllOfInfo(xh,'view_xszz_com_gjzxj2',colList,getZxjInfo);
				
				//学费减免
				colList = ["xn", "sqsj","ymcs"];	
				getStuDetails.getAllOfInfo(xh,'view_xszz_com_xfjm1',colList,getXfjmInfo);
			}
		}
		
		//学生成绩
		function getXscjInfo(data){
			var cellMuster=[
				function(data){return data.tjxnxq},
				function(data){return data.xxzlf},
				function(data){return data.jqpjf},
				function(data){return data.bjg}		
			];
			if (data != null && typeof data == 'object') {
				if ($("cjxx").tagName.toLowerCase() =="tbody"){
					DWRUtil.removeAllRows("cjxx");
					DWRUtil.addRows("cjxx",data,cellMuster);
				}
			}else{
				showMsgWin("有错误出现远程数据读取失败！");
			}
		}
		
		//异动信息
		function getYdInfo(data){
		
			var cellMuster=[
				function(data){return data.ydlbmc},
				function(data){return data.ydqxymc},
				function(data){return data.ydqzymc},
				function(data){return data.ydqbjmc},
				function(data){return data.ydhxymc},
				function(data){return data.ydhzymc},
				function(data){return data.ydhbjmc},
				function(data){return data.ydrq}		
			];
			if (data != null && typeof data == 'object') {
				if ($("ydxx").tagName.toLowerCase() =="tbody"){
					DWRUtil.removeAllRows("ydxx");
					DWRUtil.addRows("ydxx",data,cellMuster);
				}
			}else{
				showMsgWin("有错误出现远程数据读取失败！");
			}
		}
		
		//缴费信息
		function getJfInfo(data){
			var cellMuster=[
				function(data){return data.sfnd},
				function(data){return data.yjxf},
				function(data){return data.yjzsf},
				function(data){return data.sjjf},
				function(data){return data.jfzt}				
			];
		
			if (data != null && typeof data == 'object') {
				if ($("jfxx").tagName.toLowerCase() =="tbody"){
					DWRUtil.removeAllRows("jfxx");
					DWRUtil.addRows("jfxx",data,cellMuster);
				}
			}else{
				showMsgWin("有错误出现远程数据读取失败！");
			}
		}
		
		//缴费总计
		function getJfZjInfo(data){
			var cellMuster=[
				function(data){return data.sfnd},
				function(data){return data.yjxf},
				function(data){return data.yjzsf},
				function(data){return data.sjjf},
				function(data){return data.jfzt}				
			];
		
			if (data != null && typeof data == 'object') {
				if ($("jfzj").tagName.toLowerCase() =="tbody"){
					DWRUtil.removeAllRows("jfzj");
					DWRUtil.addRows("jfzj",data,cellMuster);
				}
			}else{
				showMsgWin("有错误出现远程数据读取失败！");
			}
		}
		
		//奖学金获得情况
		function getJxjInfo(data){
		
			var cellMuster=[
				function(data){return data.xn},
				function(data){return data.jxjmc},
				function(data){return data.jlje},
				function(data){return data.xxshsj}		
			];
		
			if (data != null && typeof data == 'object') {
				if ($("jxj").tagName.toLowerCase() =="tbody"){
					DWRUtil.removeAllRows("jxj");
					DWRUtil.addRows("jxj",data,cellMuster);
				}
			}else{
				showMsgWin("有错误出现远程数据读取失败！");
			}
		}
		
		//违纪获得情况
		function getWjInfo(data){
		
			var cellMuster=[
				function(data){return data.cfwh},
				function(data){return data.cflbmc},
				function(data){return data.cfsj},
				function(data){return data.cfyymc}		
			];
		
			if (data != null && typeof data == 'object') {
				if ($("cfqk").tagName.toLowerCase() =="tbody"){
					DWRUtil.removeAllRows("cfqk");
					DWRUtil.addRows("cfqk",data,cellMuster);
				}
			}else{
				showMsgWin("有错误出现远程数据读取失败！");
			}
		}
		
		//投保信息获得情况
		function getTbInfo(data){
		
			var cellMuster=[
				function(data){return data.bxxzmc},
				function(data){return data.tbsj}	
			];
		
			if (data != null && typeof data == 'object') {
				if ($("tbxx").tagName.toLowerCase() =="tbody"){
					DWRUtil.removeAllRows("tbxx");
					DWRUtil.addRows("tbxx",data,cellMuster);
				}
			}else{
				showMsgWin("有错误出现远程数据读取失败！");
			}
		}
		
		//赔保信息获得情况
		function getPbInfo(data){
		
			var cellMuster=[
				function(data){return data.lpje},
				function(data){return data.slsj},
				function(data){return data.lpyy}	
			];
		
			if (data != null && typeof data == 'object') {
				if ($("pbjl").tagName.toLowerCase() =="tbody"){
					DWRUtil.removeAllRows("pbjl");
					DWRUtil.addRows("pbjl",data,cellMuster);
				}
			}else{
				showMsgWin("有错误出现远程数据读取失败！");
			}
		}
		
		//勤工助学信息获得情况
		function getQgzxInfo(data){
			var cellMuster=[
				function(data){return data.xn},
				function(data){return data.xqmc},
				function(data){return data.bjmc},
				function(data){return data.gwdm},	
				function(data){return data.sqsj}
			];
		
			if (data != null && typeof data == 'object') {
				if ($("qgzx").tagName.toLowerCase() =="tbody"){
					DWRUtil.removeAllRows("qgzx");
					DWRUtil.addRows("qgzx",data,cellMuster);
				}
			}else{
				showMsgWin("有错误出现远程数据读取失败！");
			}
		}
		
		//酬金发放信息获得情况
		function getCjffInfo(data){
		
			var cellMuster=[
				function(data){return data.xn},
				function(data){return data.xqmc},
				function(data){return data.yf},
				function(data){return data.bjmc},
				function(data){return data.cjje},	
				function(data){return data.ffsj}
			];
		
			if (data != null && typeof data == 'object') {
				if ($("cjff").tagName.toLowerCase() =="tbody"){
					DWRUtil.removeAllRows("cjff");
					DWRUtil.addRows("cjff",data,cellMuster);
				}
			}else{
				showMsgWin("有错误出现远程数据读取失败！");
			}
		}
		
		//培训
		function getPxxxInfo(data){
			var cellMuster=[
				function(data){return data.nd},
				function(data){return data.xn},
				function(data){return data.xqmc},
				function(data){return data.bjmc},
				function(data){return data.pxxmmc},
				function(data){return data.pxjg}		
			];
			if (data != null && typeof data == 'object') {
				if ($("xspxxx").tagName.toLowerCase() =="tbody"){
					DWRUtil.removeAllRows("xspxxx");			
					DWRUtil.addRows("xspxxx",data,cellMuster);
				}
			}else{
				showMsgWin("有错误出现远程数据读取失败！");
			}
		}
		
		//入党积极分子
		function getRdjjfzInfo(data){
			var cellMuster=[
				function(data){return data.nd},
				function(data){return data.xn},
				function(data){return data.xqmc},
				function(data){return data.bjmc},
				function(data){return data.kssj}		
			];
			if (data != null && typeof data == 'object') {
				if ($("rdjjfz").tagName.toLowerCase() =="tbody"){
					DWRUtil.removeAllRows("rdjjfz");
					DWRUtil.addRows("rdjjfz",data,cellMuster);
				}
			}else{
				alert("出错了!!");
				showMsgWin("有错误出现远程数据读取失败！");
			}
		}
		
		//预备党员
		function getYbdyInfo(data){
			var cellMuster=[
				function(data){return data.nd},
				function(data){return data.xn},
				function(data){return data.xqmc},
				function(data){return data.bjmc},
				function(data){return data.kssj},		
				function(data){return data.jssj}
			];
			if (data != null && typeof data == 'object') {
				if ($("ybdy").tagName.toLowerCase() =="tbody"){
					DWRUtil.removeAllRows("ybdy");
					DWRUtil.addRows("ybdy",data,cellMuster);
				}
			}else{
				showMsgWin("有错误出现远程数据读取失败！");
			}
		}
		
		//党员
		function getDyInfo(data){
			var cellMuster=[
				function(data){return data.nd},
				function(data){return data.xn},
				function(data){return data.xqmc},
				function(data){return data.bjmc},
				function(data){return data.rdsj}		
			];
			if (data != null && typeof data == 'object') {
				if ($("dy").tagName.toLowerCase() =="tbody"){
					DWRUtil.removeAllRows("dy");
					DWRUtil.addRows("dy",data,cellMuster);
				}
			}else{
				showMsgWin("有错误出现远程数据读取失败！");
			}
		}
		
		//学生干部
		function getXsgbInfo(data){
			var cellMuster=[
				function(data){return data.bjgbmc},
				function(data){return data.kssj},
				function(data){return data.jssj}		
			];
			if (data != null && typeof data == 'object') {
				if ($("xsgb").tagName.toLowerCase() =="tbody"){
					DWRUtil.removeAllRows("xsgb");
					DWRUtil.addRows("xsgb",data,cellMuster);
				}
			}else{
				showMsgWin("有错误出现远程数据读取失败！");
			}
		}
		
		//诚信情况
		function getCxqkInfo(data){
			var cellMuster=[
				function(data){return data.cxjl},
				function(data){return data.jlsj}		
			];
			if (data != null && typeof data == 'object') {
				if ($("cxqk").tagName.toLowerCase() =="tbody"){
					DWRUtil.removeAllRows("cxqk");
					DWRUtil.addRows("cxqk",data,cellMuster);
				}
			}else{
				showMsgWin("有错误出现远程数据读取失败！");
			}
		}
		
		//对外交流
		function getDwjlInfo(data){
			var cellMuster=[
				function(data){return data.xmmc},
				function(data){return data.cgsj},	
				function(data){return data.hgsj}
			];
			if (data != null && typeof data == 'object') {
				if ($("dwjl").tagName.toLowerCase() =="tbody"){
					DWRUtil.removeAllRows("dwjl");
					DWRUtil.addRows("dwjl",data,cellMuster);
				}
			}else{
				showMsgWin("有错误出现远程数据读取失败！");
			}
		}
		
		//入伍信息
		function getRwxxInfo(data){
			var cellMuster=[
				function(data){return data.rwsj},
				function(data){return data.twsj},
				function(data){return data.fyqjbx}	
			];
			if (data != null && typeof data == 'object') {
				if ($("rwxx").tagName.toLowerCase() =="tbody"){
					DWRUtil.removeAllRows("rwxx");
					DWRUtil.addRows("rwxx",data,cellMuster);
				}
			}else{
				showMsgWin("有错误出现远程数据读取失败！");
			}
		}
		
		//助学金信息
		function getZxjInfo(data){
			var cellMuster=[
				function(data){return data.xn},
				function(data){return data.sqsj},
				function(data){return data.zxjdj},
				function(data){return data.zxjje}	
			];
			if (data != null && typeof data == 'object') {
				if ($("zxj").tagName.toLowerCase() =="tbody"){
					DWRUtil.removeAllRows("zxj");
					DWRUtil.addRows("zxj",data,cellMuster);
				}
			}else{
				showMsgWin("有错误出现远程数据读取失败！");
			}
		}
		
		//学费减免信息
		function getXfjmInfo(data){
			var cellMuster=[
				function(data){return data.xn},
				function(data){return data.sqsj},
				function(data){return data.ymcs}	
			];
			if (data != null && typeof data == 'object') {
				if ($("xfjm").tagName.toLowerCase() =="tbody"){
					DWRUtil.removeAllRows("xfjm");
					DWRUtil.addRows("xfjm",data,cellMuster);
				}
			}else{
				showMsgWin("有错误出现远程数据读取失败！");
			}
		}
	</script>
	<style type="text/css">
	<!--
	.style1 {font-size: 16px}
	.style2 {font-size: 14px}
	.style3 {
		color: #000000;
		font-size: 15px;
	}
	.style4 {
		font-size: 15px;
		font-weight: bold;
	}
	-->
	</style>
	</head>
	<body>
		<input type="hidden" id="xh" name="xh" value="<bean:write name="xh"/>" />
		<input type="hidden" id="xxdm" name="xxdm" value="<bean:write name="xxdm"/>" />
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>学生详细信息查询</a>
			</p>
		</div>
		<div class="tab">
		<table width="100%" class="formlist">
			<tr>
				<td>
					<table width="100%" class="fomrlist">
						<tr>
							<td bgcolor="#CCCCCC">
								<div id="main1" style="color:blue;cursor:hand"
									onclick="document.all.child0.style.display=(document.all.child0.style.display =='none')?'':'none'">
									<div align="center" class="style1 style3">
										<strong>学生基本信息</strong>
									</div>
								</div>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td>
					<div id="child0">
						<table width="100%" align="center" class="formlist">
							<tr>
								<th width="15%">
									学号
								</th>
								<td align="left" width="25%">
									<bean:write name="rs" property="xh" />
								</td>
								<th width="15%">
									年级
								</th>
								<td align="left" width="30%">
									<bean:write name="rs" property="nj" />
								</td>
							</tr>
							<tr>
								<th>
									姓名
								</th>
								<td align="left">
									<bean:write name="rs" property="xm" />
								</td>
								<th>
									<bean:message key="lable.xsgzyxpzxy" />
								</th>
								<td align="left">
									<bean:write name="rs" property="xymc" />
								</td>
							</tr>
							<tr>
								<th>
									性别
								</th>
								<td align="left">
									<bean:write name="rs" property="xb" />
								</td>
								<th>
									专业
								</th>
								<td align="left">
									<bean:write name="rs" property="zymc" />
								</td>
							</tr>
							<tr>
								<th>
									出生日期
								</th>
								<td align="left">
									<bean:write name="rs" property="csrq" />
								</td>
								<th>
									班级
								</th>
								<td align="left">
									<bean:write name="rs" property="bjmc" />
								</td>
							</tr>
							<tr>
								<th>
									民族
								</th>
								<td align="left">
									<bean:write name="rs" property="mzmc" />
								</td>
								<th>
									学制
								</th>
								<td align="left">
									<bean:write name="rs" property="xz" />
								</td>
							</tr>
							<tr>
								<th>
									政治面貌
								</th>
								<td align="left">
									<bean:write name="rs" property="zzmmmc" />
								</td>
								<th>
									学籍状态
								</th>
								<td align="left">
									<bean:write name="rs" property="xjztm" />
								</td>
							</tr>
							<tr>
								<th>
									身份证号
								</th>
								<td align="left">
									<bean:write name="rs" property="sfzh" />
								</td>
								<th>
									电子邮箱
								</th>
								<td align="left" colspan="2">
									<bean:write name="rs" property="lxdzxx" />
								</td>
							</tr>
							<tr>
								<th>
									来源地区
								</th>
								<td align="left">
									<bean:write name="rs" property="lydq" />
								</td>
								<th>
									联系电话
								</th>
								<td align="left" colspan="2">
									<bean:write name="rs" property="lxdh" />
								</td>
							</tr>
							<tr>
								<th>
									籍贯
								</th>
								<td align="left">
									<bean:write name="rs" property="jg" />
								</td>
								<th>
									手机号码
								</th>
								<td align="left" colspan="2">
									<bean:write name="rs" property="sjhm" />
								</td>
							</tr>
							<tr>
								<th>
									宿舍号
								</th>
								<td align="left">
									<bean:write name="rs" property="ssbh" />
								</td>
								<th>
									宿舍电话
								</th>
								<td align="left" colspan="2">
									<bean:write name="rs" property="qsdh" />
								</td>
							</tr>
							<tr>
								<th>
									辅导员
								</th>
								<td align="left">
									<bean:write name="rs" property="fdyxm" />
								</td>
								<th>
									辅导员电话
								</th>
								<td align="left" colspan="2">
									<bean:write name="rs" property="fdyyddh" />
								</td>
							</tr>
							<tr>
								<th>
									班主任
								</th>
								<td align="left">
									<bean:write name="rs" property="bzrxm" />
								</td>
								<th>
									助理班主任
								</th>
								<td align="left" colspan="2">
									<bean:write name="rs" property="zlbzrxm" />
								</td>
							</tr>
							<tr>
								<th>
									是否困难生
								</th>
								<td align="left">
									<bean:write name="rs" property="isKns" />
								</td>
								<th>
									
								</th>
								<td align="left" colspan="2">
									
								</td>
							</tr>
						</table>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<table width="100%" class="formlist">
						<tr>
							<td bgcolor="#CCCCCC">
								<div id="main1" style="color:blue;cursor:hand"
									onclick="document.all.child1.style.display=(document.all.child1.style.display =='none')?'':'none';getJtxxInfo();">
									<div align="center" class="style1 style3">
										<strong>家庭信息</strong>
									</div>
								</div>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td bgcolor="#FFFFFF">
					<div id="child1" style="display:none">
						<table width="100%" class="formlist">
							<thead>
								<tr>
									<td colspan="8">
										<div align="center" class="style2">
											家庭信息记录
										</div>
									</td>
								</tr>
								<tr>
									<td>
										学号
									</td>
									<td>
										姓名
									</td>
									<td>
										家庭成员1姓名
									</td>
									<td>
										家庭成员1关系
									</td>
									<td>
										家庭成员2姓名
									</td>
									<td>
										家庭成员2关系
									</td>
									<td>
										家庭成员3姓名
									</td>
									<td>
										家庭成员3关系
									</td>
								</tr>
							</thead>
							<tbody class="formlist" id="xsjtxx">
							</tbody>
						</table>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<table width="100%" class="formlist">
						<tr>
							<td bgcolor="#CCCCCC">
								<div id="main2" style="color:blue;cursor:hand"
									onclick="document.all.child2.style.display=(document.all.child2.style.display =='none')?'':'none';getZjlgStuInfo('xxxx')">
									<div align="center" class="style1 style3">
										<strong>学习信息</strong>
									</div>
								</div>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td>
					<div id="child2" style="display:none">
						<table width="100%" align="center" class="formlist">
							<thead>
								<tr>
									<td colspan="9">
										<div align="center" class="style2">
											学生成绩信息
										</div>
									</td>
								</tr>
								<tr>
									<td>
										学年	学期
									</td>
									<td>
										学习质量分
									</td>
									<td>
										学分加权分
									</td>
									<td>
										不及格门次
									</td>
								</tr>
							</thead>
							<tbody class="formlist" id="cjxx">
							</tbody>
						</table>
						<br></br>
						<table width="100%" class="formlist">
							<thead>
								<tr>
									<td colspan="8">
										<div align="center" class="style2">
											学籍异动记录
										</div>
									</td>
								</tr>
								<tr>
									<td>
										学籍异动记录
									</td>
									<td>
										学籍异动前<bean:message key="lable.xsgzyxpzxy" />
									</td>
									<td>
										学籍异动前专业
									</td>
									<td>
										学籍异动前班级
									</td>
									<td>
										学籍异动后<bean:message key="lable.xsgzyxpzxy" />
									</td>
									<td>
										学籍异动后专业
									</td>
									<td>
										学籍异动后班级
									</td>
									<td>
										学籍异动时间
									</td>
								</tr>
							</thead>
							<tbody class="formlist" id="ydxx">
							</tbody>
						</table>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<table width="100%" class="formlist">
						<tr>
							<td bgcolor="#CCCCCC">
								<div id="main3" style="color:blue;cursor:hand"
									onclick="document.all.child3.style.display=(document.all.child3.style.display =='none')?'':'none';getZjlgStuInfo('jfxx')">
									<div align="center" class="style1 style3">
										<strong>缴费情况</strong>
									</div>
								</div>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td>
					<div id="child3" style="display:none">
						<table width="100%" align="center" class="formlist">
							<thead>
								<tr>
									<td colspan="8">
										<div align="center" class="style2">
											缴费进行管理
										</div>
									</td>
								</tr>
								<tr>
									<td>
										收费年度
									</td>
									<td>
										应缴学费
									</td>
									<td>
										应缴住宿费
									</td>
									<td>
										实际缴费
									</td>
									<td>
										缴费状态
									</td>
								</tr>
							</thead>
							<tbody class="formlist" id="jfxx">
							</tbody>
							<tbody class="formlist" id="jfzj">
							</tbody>
						</table>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<table width="100%" class="formlist">
						<tr>
							<td bgcolor="#CCCCCC">
								<div id="main4" style="color:blue;cursor:hand"
									onclick="document.all.child4.style.display=(document.all.child4.style.display =='none')?'':'none';getZjlgStuInfo('jlxx')">
									<div align="center" class="style1 style3">
										<strong>获奖情况</strong>
									</div>
								</div>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td>
					<div id="child4" style="display:none">
						<table width="100%" align="center" class="formlist">
							<thead>
								<tr>
									<td colspan="11">
										<div align="center" class="style2">
											历年奖学金获得情况
										</div>
									</td>
								</tr>
								<tr>
									<td>
										学年
									</td>
									<td>
										获得奖项
									</td>
									<td>
										获奖金额
									</td>
									<td>
										获得时间
									</td>
								</tr>
							</thead>
							<tbody class="formlist" id="jxj">
							</tbody>
						</table>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<table width="100%" class="formlist">
						<tr>
							<td bgcolor="#CCCCCC">
								<div id="main5" style="color:blue;cursor:hand"
									onclick="document.all.child5.style.display=(document.all.child5.style.display =='none')?'':'none';getZjlgStuInfo('cfqk')">
									<div align="center" class="style1 style3">
										<strong>历年受处分情况</strong>
									</div>
								</div>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td bgcolor="#FFFFFF">
					<div id="child5" style="display:none">
						<table width="100%" align="center" class="formlist">
							<thead>
								<tr>
									<td colspan="11">
										<div align="center" class="style2">
											历年受处分情况
										</div>
									</td>
								</tr>
								<tr>
									<td>
										处分文号
									</td>
									<td>
										处分名称
									</td>
									<td>
										处分时间
									</td>
									<td>
										处分原因
									</td>
								</tr>
							</thead>
							<tbody class="formlist" id="cfqk">
							</tbody>
						</table>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<table width="100%" class="formlist">
						<tr>
							<td bgcolor="#CCCCCC">
								<div id="main6" style="color:blue;cursor:hand"
									onclick="document.all.child6.style.display=(document.all.child6.style.display =='none')?'':'none';getZjlgStuInfo('szxx')">
									<div align="center" class="style1 style3">
										<strong>受助信息</strong>
									</div>
								</div>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td bgcolor="#FFFFFF">
					<div id="child6" style="display:none">
						<table width="100%" align="center" class="formlist">
							<thead>
								<tr>
									<td colspan="8">
										<div align="center" class="style2">
											助学金情况
										</div>
									</td>
								</tr>
								<tr>
									<td>
										学年
									</td>
									<td>
										申请时间
									</td>
									<td>
										助学金等级
									</td>
									<td>
										金额
									</td>
								</tr>
							</thead>
							<tbody width="100%" class="formlist" id="zxj">
							</tbody>
						</table>
						<br />
						<br />
						<table width="100%" align="center" class="formlist">
							<thead>
								<tr>
									<td colspan="10">
										<div align="center" class="style2">
											享受减免情况
										</div>
									</td>
								</tr>
								<tr>
									<td>
										学年
									</td>
									<td>
										申请时间
									</td>
									<td>
										已免次数
									</td>
								</tr>
							</thead>
							<tbody width="100%" class="formlist" id="xfjm">
							</tbody>
						</table>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<table width="100%" class="formlist">
						<tr>
							<td bgcolor="#CCCCCC">
								<div id="main7" style="color:blue;cursor:hand"
									onclick="document.all.child7.style.display=(document.all.child7.style.display =='none')?'':'none';getZjlgStuInfo('tbxx')">
									<div align="center" class="style1 style3">
										<strong>投保信息</strong>
									</div>
								</div>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td bgcolor="#FFFFFF">
					<div id="child7" style="display:none">
						<table width="100%" align="center" class="formlist">
							<thead>
								<tr>
									<td colspan="8">
										<div align="center" class="style2">
											投保信息
										</div>
									</td>
								</tr>
								<tr>
									<td>
										投保险种
									</td>
									<td>
										投保时间
									</td>
								</tr>
							</thead>
							<tbody width="100%" class="formlist" id="tbxx">
							</tbody>
						</table>
						<br />
						<br />
						<table width="100%" align="center" class="formlist">
							<thead>
								<tr>
									<td colspan="10">
										<div align="center" class="style2">
											赔保记录
										</div>
									</td>
								</tr>
								<tr>
									<td>
										赔保金额
									</td>
									<td>
										赔保时间
									</td>
									<td>
										赔保原因
									</td>
								</tr>
							</thead>
							<tbody class="formlist" id="pbjl">
							</tbody>
						</table>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<table width="100%" class="formlist">
						<tr>
							<td bgcolor="#CCCCCC">
								<div id="main8" style="color:blue;cursor:hand"
									onclick="document.all.child8.style.display=(document.all.child8.style.display =='none')?'':'none';getZjlgStuInfo('qgzx');">
									<div align="center" class="style1 style3">
										<strong>勤工助学信息</strong>
									</div>
								</div>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td bgcolor="#FFFFFF">
					<div id="child8" style="display:none">
						<table width="100%" align="center" class="formlist">
							<thead>
								<tr>
									<td colspan="8">
										<div align="center" class="style2">
											勤工助学记录
										</div>
									</td>
								</tr>
								<tr>
									<td>
										学年
									</td>
									<td>
										学期
									</td>
									<td>
										班级名称
									</td>
									<td>
										岗位名称
									</td>
									<td>
										申请时间
									</td>
								</tr>
							</thead>
							<tbody class="formlist" id="qgzx">
							</tbody>
						</table>
						<br />
						<table width="100%" align="center" class="formlist">
							<thead>
								<tr>
									<td colspan="10">
										<div align="center" class="style2">
											酬金发放记录
										</div>
									</td>
								</tr>
								<tr>
									<td>
										学年
									</td>
									<td>
										学期
									</td>
									<td>
										月份
									</td>
									<td>
										班级名称
									</td>
									<td>
										酬金金额
									</td>
									<td>
										发放时间
									</td>
								</tr>
							</thead>
							<tbody class="formlist" id="cjff">
							</tbody>
						</table>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<table width="100%" class="formlist">
						<tr>
							<td bgcolor="#CCCCCC">
								<div id="main9" style="color:blue;cursor:hand"
									onclick="document.all.child9.style.display=(document.all.child9.style.display =='none')?'':'none';getZjlgStuInfo('djxx');">
									<div align="center" class="style1 style3">
										<strong>党建信息</strong>
									</div>
								</div>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td>
					<div id="child9" style="display:none">
						<table width="100%" align="center" class="formlist">
							<thead>
								<tr>
									<td colspan="9">
										<div align="center" class="style2">
											培训记录
										</div>
									</td>
								</tr>
								<tr>
									<td>
										年度
									</td>
									<td>
										学年
									</td>
									<td>
										学期
									</td>
									<td>
										班级
									</td>
									<td>
										培训项目
									</td>
									<td>
										培训结果
									</td>
								</tr>
							</thead>
							<tbody class="formlist" id="xspxxx">
							</tbody>
						</table>
						<br></br>
						<table width="100%" align="center" class="formlist">
							<thead>
								<tr>
									<td colspan="8">
										<div align="center" class="style2">
											入党积极分子记录
										</div>
									</td>
								</tr>
								<tr>
									<td>
										年度
									</td>
									<td>
										学年
									</td>
									<td>
										学期
									</td>
									<td>
										班级
									</td>
									<td>
										开始时间
									</td>
								</tr>
							</thead>
							<tbody class="formlist" id="rdjjfz">
							</tbody>
						</table>
						<br></br>
						<table width="100%" align="center" class="formlist">
							<thead>
								<tr>
									<td colspan="9">
										<div align="center" class="style2">
											预备党员记录
										</div>
									</td>
								</tr>
								<tr>
									<td>
										年度
									</td>
									<td>
										学年
									</td>
									<td>
										学期
									</td>
									<td>
										班级
									</td>
									<td>
										开始时间
									</td>
									<td>
										结束时间
									</td>
								</tr>
							</thead>
							<tbody class="formlist" id="ybdy">
							</tbody>
						</table>
						<br></br>
						<table width="100%" align="center" class="formlist">
							<thead>
								<tr>
									<td colspan="8">
										<div align="center" class="style2">
											党员记录
										</div>
									</td>
								</tr>
								<tr>
									<td>
										年度
									</td>
									<td>
										学年
									</td>
									<td>
										学期
									</td>
									<td>
										班级
									</td>
									<td>
										入党时间
									</td>
								</tr>
							</thead>
							<tbody width="100%" class="formlist" id="dy">
							</tbody>
						</table>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<table width="100%" class="formlist">
						<tr>
							<td bgcolor="#CCCCCC">
								<div id="main10" style="color:blue;cursor:hand"
									onclick="document.all.child10.style.display=(document.all.child10.style.display =='none')?'':'none';getZjlgStuInfo('xsgb')">
									<div align="center" class="style1 style3">
										<strong>学生干部信息</strong>
									</div>
								</div>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td bgcolor="#FFFFFF">
					<div id="child10" style="display:none">
						<table width="100%" align="center" class="formlist">
							<thead>
								<tr>
									<td colspan="8">
										<div align="center" class="style2">
											学生干部记录
										</div>
									</td>
								</tr>
								<tr>
									<td>
										职务
									</td>
									<td>
										任职开始时间
									</td>
									<td>
										任职结束时间
									</td>
								</tr>
							</thead>
							<tbody width="100%" class="formlist" id="xsgb">
							</tbody>
						</table>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<table width="100%" class="formlist">
						<tr>
							<td bgcolor="#CCCCCC">
								<div id="main11" style="color:blue;cursor:hand"
									onclick="document.all.child11.style.display=(document.all.child11.style.display =='none')?'':'none';getZjlgStuInfo('cxqk')">
									<div align="center" class="style1 style3">
										<strong>诚信情况</strong>
									</div>
								</div>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td bgcolor="#FFFFFF">
					<div id="child11" style="display:none">
						<table width="100%" align="center" class="formlist">
							<thead>
								<tr>
									<td colspan="8">
										<div align="center" class="style2">
											诚信情况记录
										</div>
									</td>
								</tr>
								<tr>
									<td>
										诚信记录
									</td>
									<td>
										记录时间
									</td>
								</tr>
							</thead>
							<tbody class="formlist" id="cxqk">
							</tbody>
						</table>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<table width="100%" class="formlist">
						<tr>
							<td bgcolor="#CCCCCC">
								<div id="main12" style="color:blue;cursor:hand"
									onclick="document.all.child12.style.display=(document.all.child12.style.display =='none')?'':'none';getZjlgStuInfo('dwjl')">
									<div align="center" class="style1 style3">
										<strong>对外交流</strong>
									</div>
								</div>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td bgcolor="#FFFFFF">
					<div id="child12" style="display:none">
						<table width="100%" align="center" class="formlist">
							<thead>
								<tr>
									<td colspan="8">
										<div align="center" class="style2">
											对外交流信息
										</div>
									</td>
								</tr>
								<tr>
									<td>
										交流项目
									</td>
									<td>
										出国（境）时间
									</td>
									<td>
										回国（境）时间
									</td>
								</tr>
							</thead>
							<tbody class="formlist" id="dwjl">
							</tbody>
						</table>
					</div>
				</td>
			</tr>
						<tr>
				<td>
					<table width="100%" class="formlist">
						<tr>
							<td bgcolor="#CCCCCC">
								<div id="main13" style="color:blue;cursor:hand"
									onclick="document.all.child13.style.display=(document.all.child13.style.display =='none')?'':'none';getZjlgStuInfo('rwxx')">
									<div align="center" class="style1 style3">
										<strong>入伍信息</strong>
									</div>
								</div>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td bgcolor="#FFFFFF">
					<div id="child13" style="display:none">
						<table width="100%" align="center" class="formlist">
							<thead>
								<tr>
									<td colspan="8">
										<div align="center" class="style2">
											入伍信息
										</div>
									</td>
								</tr>
								<tr>
									<td>
										入伍时间
									</td>
									<td>
										退伍回校时间
									</td>
									<td>
										服役期间表现
									</td>
								</tr>
							</thead>
							<tbody width="100%" class="formlist" id="rwxx">
							</tbody>
						</table>
					</div>
				</td>
			</tr>
		</table>
	</body>
</html>
