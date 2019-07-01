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
				
				//ѧ���ɼ���Ϣ
				var colList = ["tjxnxq", "xxzlf", "jqpjf", "bjg"];
				getStuDetails.getAllOfInfo(xh,'view_zjlg_xxzlf',colList,getXscjInfo);
				
				//ѧ���춯��¼
				colList = ["ydlbmc", "ydqxymc", "ydqzymc", "ydqbjmc", "ydhxymc", "ydhzymc", "ydhbjmc","ydrq"];
				getStuDetails.getAllOfInfo(xh,'view_xjydjbxx',colList,getYdInfo);
			}else if(type == "jfxx"){
				//�ɷ���Ϣ
				var colList = ["sfnd", "yjxf", "yjzsf", "sjjf","jfzt"];
				getStuDetails.getAllOfInfo(xh,'view_zjlg_jfxx',colList,getJfInfo);
				//�ɷ��ܼ�
				var colList = ["sfnd", "yjxf", "yjzsf", "sjjf","jfzt"];
				getStuDetails.getAllOfInfo(xh,'view_zjlg_jfxxzj',colList,getJfZjInfo);
			}else if(type == "jlxx"){
				//���꽱ѧ�������
				var colList = ["xn", "jxjmc", "jlje", "xxshsj"];
				getStuDetails.getAllOfInfo(xh,'view_xsjxjb',colList,getJxjInfo);
			}else if(type == "cfqk"){
				//�����ܴ������
				colList = ["cfwh", "cflbmc", "cfsj", "cfyymc"];
				getStuDetails.getAllOfInfo(xh,'view_wjcf',colList,getWjInfo);
			}else if(type == "tbxx"){
				//Ͷ����Ϣ
				var colList = ["bxxzmc", "tbsj"];
				getStuDetails.getAllOfInfo(xh,'view_xsbxxx',colList,getTbInfo); 
				
				//�Ᵽ��Ϣ
				colList = ["lpje","slsj" ,"lpyy"];
				getStuDetails.getAllOfInfo(xh,'view_lpxx',colList,getPbInfo); 
			}else if(type == "qgzx"){
				//�ڹ���ѧ
				var colList = [ "xn", "xqmc","bjmc","gwdm","sqsj"];
				getStuDetails.getAllOfInfo(xh,'view_xsgwxx',colList,getQgzxInfo);	
				
				//��𷢷�
				colList = ["xn", "xqmc", "yf","bjmc","cjje","ffsj"];
				getStuDetails.getAllOfInfo(xh,'view_xscjff',colList,getCjffInfo);
			}else if(type == "djxx"){
				//ѧ����ѵ��Ϣ
				var colList = ["nd", "xn", "xqmc","bjmc","pxxmmc", "pxjg"];	
				getStuDetails.getAllOfInfo(xh,'view_xspxxx',colList,getPxxxInfo);
				
				//�뵳��������
				colList = ["nd", "xn", "xqmc", "bjmc","kssj"];
				getStuDetails.getAllOfInfo(xh,'view_rdjjfzxx',colList,getRdjjfzInfo);
				
				//Ԥ����Ա
				colList = ["nd", "xn", "xqmc", "bjmc","kssj", "jssj"];
				getStuDetails.getAllOfInfo(xh,'view_ybdyxx',colList,getYbdyInfo);
				
				//��Ա
				colList = ["nd", "xn", "xqmc", "bjmc","rdsj"];
				getStuDetails.getAllOfInfo(xh,'view_dyxx',colList,getDyInfo);	
			}else if(type == "xsgb"){
				//ѧ���ɲ�
				var colList = ["bjgbmc", "kssj", "jssj"];	
				getStuDetails.getAllOfInfo(xh,'view_bjgbxx',colList,getXsgbInfo);
			}else if(type == "cxqk"){
				//�������
				var colList = ["cxjl", "jlsj"];	
				getStuDetails.getAllOfInfo(xh,'view_zjlg_cxqk',colList,getCxqkInfo);
			}else if(type == "dwjl"){
				//���⽻��
				var colList = ["xmmc", "cgsj", "hgsj"];	
				getStuDetails.getAllOfInfo(xh,'view_zjlg_dwjl',colList,getDwjlInfo);
			}else if(type == "rwxx"){
				//������Ϣ
				var colList = ["rwsj", "twsj","fyqjbx"];	
				getStuDetails.getAllOfInfo(xh,'view_zjlg_rwxx',colList,getRwxxInfo);
			}else if(type == "szxx"){
				//��ѧ�����
				var colList = ["xn", "sqsj","zxjdj","zxjje"];	
				getStuDetails.getAllOfInfo(xh,'view_xszz_com_gjzxj2',colList,getZxjInfo);
				
				//ѧ�Ѽ���
				colList = ["xn", "sqsj","ymcs"];	
				getStuDetails.getAllOfInfo(xh,'view_xszz_com_xfjm1',colList,getXfjmInfo);
			}
		}
		
		//ѧ���ɼ�
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
				showMsgWin("�д������Զ�����ݶ�ȡʧ�ܣ�");
			}
		}
		
		//�춯��Ϣ
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
				showMsgWin("�д������Զ�����ݶ�ȡʧ�ܣ�");
			}
		}
		
		//�ɷ���Ϣ
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
				showMsgWin("�д������Զ�����ݶ�ȡʧ�ܣ�");
			}
		}
		
		//�ɷ��ܼ�
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
				showMsgWin("�д������Զ�����ݶ�ȡʧ�ܣ�");
			}
		}
		
		//��ѧ�������
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
				showMsgWin("�д������Զ�����ݶ�ȡʧ�ܣ�");
			}
		}
		
		//Υ�ͻ�����
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
				showMsgWin("�д������Զ�����ݶ�ȡʧ�ܣ�");
			}
		}
		
		//Ͷ����Ϣ������
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
				showMsgWin("�д������Զ�����ݶ�ȡʧ�ܣ�");
			}
		}
		
		//�Ᵽ��Ϣ������
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
				showMsgWin("�д������Զ�����ݶ�ȡʧ�ܣ�");
			}
		}
		
		//�ڹ���ѧ��Ϣ������
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
				showMsgWin("�д������Զ�����ݶ�ȡʧ�ܣ�");
			}
		}
		
		//��𷢷���Ϣ������
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
				showMsgWin("�д������Զ�����ݶ�ȡʧ�ܣ�");
			}
		}
		
		//��ѵ
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
				showMsgWin("�д������Զ�����ݶ�ȡʧ�ܣ�");
			}
		}
		
		//�뵳��������
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
				alert("������!!");
				showMsgWin("�д������Զ�����ݶ�ȡʧ�ܣ�");
			}
		}
		
		//Ԥ����Ա
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
				showMsgWin("�д������Զ�����ݶ�ȡʧ�ܣ�");
			}
		}
		
		//��Ա
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
				showMsgWin("�д������Զ�����ݶ�ȡʧ�ܣ�");
			}
		}
		
		//ѧ���ɲ�
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
				showMsgWin("�д������Զ�����ݶ�ȡʧ�ܣ�");
			}
		}
		
		//�������
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
				showMsgWin("�д������Զ�����ݶ�ȡʧ�ܣ�");
			}
		}
		
		//���⽻��
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
				showMsgWin("�д������Զ�����ݶ�ȡʧ�ܣ�");
			}
		}
		
		//������Ϣ
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
				showMsgWin("�д������Զ�����ݶ�ȡʧ�ܣ�");
			}
		}
		
		//��ѧ����Ϣ
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
				showMsgWin("�д������Զ�����ݶ�ȡʧ�ܣ�");
			}
		}
		
		//ѧ�Ѽ�����Ϣ
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
				showMsgWin("�д������Զ�����ݶ�ȡʧ�ܣ�");
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
				<em>���ĵ�ǰλ��:</em><a>ѧ����ϸ��Ϣ��ѯ</a>
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
										<strong>ѧ��������Ϣ</strong>
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
									ѧ��
								</th>
								<td align="left" width="25%">
									<bean:write name="rs" property="xh" />
								</td>
								<th width="15%">
									�꼶
								</th>
								<td align="left" width="30%">
									<bean:write name="rs" property="nj" />
								</td>
							</tr>
							<tr>
								<th>
									����
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
									�Ա�
								</th>
								<td align="left">
									<bean:write name="rs" property="xb" />
								</td>
								<th>
									רҵ
								</th>
								<td align="left">
									<bean:write name="rs" property="zymc" />
								</td>
							</tr>
							<tr>
								<th>
									��������
								</th>
								<td align="left">
									<bean:write name="rs" property="csrq" />
								</td>
								<th>
									�༶
								</th>
								<td align="left">
									<bean:write name="rs" property="bjmc" />
								</td>
							</tr>
							<tr>
								<th>
									����
								</th>
								<td align="left">
									<bean:write name="rs" property="mzmc" />
								</td>
								<th>
									ѧ��
								</th>
								<td align="left">
									<bean:write name="rs" property="xz" />
								</td>
							</tr>
							<tr>
								<th>
									������ò
								</th>
								<td align="left">
									<bean:write name="rs" property="zzmmmc" />
								</td>
								<th>
									ѧ��״̬
								</th>
								<td align="left">
									<bean:write name="rs" property="xjztm" />
								</td>
							</tr>
							<tr>
								<th>
									���֤��
								</th>
								<td align="left">
									<bean:write name="rs" property="sfzh" />
								</td>
								<th>
									��������
								</th>
								<td align="left" colspan="2">
									<bean:write name="rs" property="lxdzxx" />
								</td>
							</tr>
							<tr>
								<th>
									��Դ����
								</th>
								<td align="left">
									<bean:write name="rs" property="lydq" />
								</td>
								<th>
									��ϵ�绰
								</th>
								<td align="left" colspan="2">
									<bean:write name="rs" property="lxdh" />
								</td>
							</tr>
							<tr>
								<th>
									����
								</th>
								<td align="left">
									<bean:write name="rs" property="jg" />
								</td>
								<th>
									�ֻ�����
								</th>
								<td align="left" colspan="2">
									<bean:write name="rs" property="sjhm" />
								</td>
							</tr>
							<tr>
								<th>
									�����
								</th>
								<td align="left">
									<bean:write name="rs" property="ssbh" />
								</td>
								<th>
									����绰
								</th>
								<td align="left" colspan="2">
									<bean:write name="rs" property="qsdh" />
								</td>
							</tr>
							<tr>
								<th>
									����Ա
								</th>
								<td align="left">
									<bean:write name="rs" property="fdyxm" />
								</td>
								<th>
									����Ա�绰
								</th>
								<td align="left" colspan="2">
									<bean:write name="rs" property="fdyyddh" />
								</td>
							</tr>
							<tr>
								<th>
									������
								</th>
								<td align="left">
									<bean:write name="rs" property="bzrxm" />
								</td>
								<th>
									���������
								</th>
								<td align="left" colspan="2">
									<bean:write name="rs" property="zlbzrxm" />
								</td>
							</tr>
							<tr>
								<th>
									�Ƿ�������
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
										<strong>��ͥ��Ϣ</strong>
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
											��ͥ��Ϣ��¼
										</div>
									</td>
								</tr>
								<tr>
									<td>
										ѧ��
									</td>
									<td>
										����
									</td>
									<td>
										��ͥ��Ա1����
									</td>
									<td>
										��ͥ��Ա1��ϵ
									</td>
									<td>
										��ͥ��Ա2����
									</td>
									<td>
										��ͥ��Ա2��ϵ
									</td>
									<td>
										��ͥ��Ա3����
									</td>
									<td>
										��ͥ��Ա3��ϵ
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
										<strong>ѧϰ��Ϣ</strong>
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
											ѧ���ɼ���Ϣ
										</div>
									</td>
								</tr>
								<tr>
									<td>
										ѧ��	ѧ��
									</td>
									<td>
										ѧϰ������
									</td>
									<td>
										ѧ�ּ�Ȩ��
									</td>
									<td>
										�������Ŵ�
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
											ѧ���춯��¼
										</div>
									</td>
								</tr>
								<tr>
									<td>
										ѧ���춯��¼
									</td>
									<td>
										ѧ���춯ǰ<bean:message key="lable.xsgzyxpzxy" />
									</td>
									<td>
										ѧ���춯ǰרҵ
									</td>
									<td>
										ѧ���춯ǰ�༶
									</td>
									<td>
										ѧ���춯��<bean:message key="lable.xsgzyxpzxy" />
									</td>
									<td>
										ѧ���춯��רҵ
									</td>
									<td>
										ѧ���춯��༶
									</td>
									<td>
										ѧ���춯ʱ��
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
										<strong>�ɷ����</strong>
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
											�ɷѽ��й���
										</div>
									</td>
								</tr>
								<tr>
									<td>
										�շ����
									</td>
									<td>
										Ӧ��ѧ��
									</td>
									<td>
										Ӧ��ס�޷�
									</td>
									<td>
										ʵ�ʽɷ�
									</td>
									<td>
										�ɷ�״̬
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
										<strong>�����</strong>
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
											���꽱ѧ�������
										</div>
									</td>
								</tr>
								<tr>
									<td>
										ѧ��
									</td>
									<td>
										��ý���
									</td>
									<td>
										�񽱽��
									</td>
									<td>
										���ʱ��
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
										<strong>�����ܴ������</strong>
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
											�����ܴ������
										</div>
									</td>
								</tr>
								<tr>
									<td>
										�����ĺ�
									</td>
									<td>
										��������
									</td>
									<td>
										����ʱ��
									</td>
									<td>
										����ԭ��
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
										<strong>������Ϣ</strong>
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
											��ѧ�����
										</div>
									</td>
								</tr>
								<tr>
									<td>
										ѧ��
									</td>
									<td>
										����ʱ��
									</td>
									<td>
										��ѧ��ȼ�
									</td>
									<td>
										���
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
											���ܼ������
										</div>
									</td>
								</tr>
								<tr>
									<td>
										ѧ��
									</td>
									<td>
										����ʱ��
									</td>
									<td>
										�������
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
										<strong>Ͷ����Ϣ</strong>
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
											Ͷ����Ϣ
										</div>
									</td>
								</tr>
								<tr>
									<td>
										Ͷ������
									</td>
									<td>
										Ͷ��ʱ��
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
											�Ᵽ��¼
										</div>
									</td>
								</tr>
								<tr>
									<td>
										�Ᵽ���
									</td>
									<td>
										�Ᵽʱ��
									</td>
									<td>
										�Ᵽԭ��
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
										<strong>�ڹ���ѧ��Ϣ</strong>
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
											�ڹ���ѧ��¼
										</div>
									</td>
								</tr>
								<tr>
									<td>
										ѧ��
									</td>
									<td>
										ѧ��
									</td>
									<td>
										�༶����
									</td>
									<td>
										��λ����
									</td>
									<td>
										����ʱ��
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
											��𷢷ż�¼
										</div>
									</td>
								</tr>
								<tr>
									<td>
										ѧ��
									</td>
									<td>
										ѧ��
									</td>
									<td>
										�·�
									</td>
									<td>
										�༶����
									</td>
									<td>
										�����
									</td>
									<td>
										����ʱ��
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
										<strong>������Ϣ</strong>
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
											��ѵ��¼
										</div>
									</td>
								</tr>
								<tr>
									<td>
										���
									</td>
									<td>
										ѧ��
									</td>
									<td>
										ѧ��
									</td>
									<td>
										�༶
									</td>
									<td>
										��ѵ��Ŀ
									</td>
									<td>
										��ѵ���
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
											�뵳�������Ӽ�¼
										</div>
									</td>
								</tr>
								<tr>
									<td>
										���
									</td>
									<td>
										ѧ��
									</td>
									<td>
										ѧ��
									</td>
									<td>
										�༶
									</td>
									<td>
										��ʼʱ��
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
											Ԥ����Ա��¼
										</div>
									</td>
								</tr>
								<tr>
									<td>
										���
									</td>
									<td>
										ѧ��
									</td>
									<td>
										ѧ��
									</td>
									<td>
										�༶
									</td>
									<td>
										��ʼʱ��
									</td>
									<td>
										����ʱ��
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
											��Ա��¼
										</div>
									</td>
								</tr>
								<tr>
									<td>
										���
									</td>
									<td>
										ѧ��
									</td>
									<td>
										ѧ��
									</td>
									<td>
										�༶
									</td>
									<td>
										�뵳ʱ��
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
										<strong>ѧ���ɲ���Ϣ</strong>
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
											ѧ���ɲ���¼
										</div>
									</td>
								</tr>
								<tr>
									<td>
										ְ��
									</td>
									<td>
										��ְ��ʼʱ��
									</td>
									<td>
										��ְ����ʱ��
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
										<strong>�������</strong>
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
											���������¼
										</div>
									</td>
								</tr>
								<tr>
									<td>
										���ż�¼
									</td>
									<td>
										��¼ʱ��
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
										<strong>���⽻��</strong>
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
											���⽻����Ϣ
										</div>
									</td>
								</tr>
								<tr>
									<td>
										������Ŀ
									</td>
									<td>
										����������ʱ��
									</td>
									<td>
										�ع�������ʱ��
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
										<strong>������Ϣ</strong>
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
											������Ϣ
										</div>
									</td>
								</tr>
								<tr>
									<td>
										����ʱ��
									</td>
									<td>
										�����Уʱ��
									</td>
									<td>
										�����ڼ����
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
