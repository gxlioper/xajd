<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>		
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script language="javascript" src="js/comm/dateUtils.js"></script>
		<script type="text/javascript" >
		var gridSetting = {
			caption:"预约咨询列表",
			pager:"pager",
			url:"xlzx_yysqnew.do?method=yyfkManage&doType=query",
			colList:[
				{label:'id',name:'id',index:'id',hidden:true, key:true  },
			   {label:'学号',name:'xh', index: 'xh',width:'12%', formatter:xhLinkForYyfk},
			   {label:'姓名',name:'xsxm', index: 'xsxm',width:'9%'},
			   {label:'性别',name:'xsxb', index: 'xsxb',width:'6%'},
			   <logic:equal value="10504" name="xxdm">
			   {label:'危机<br/>个案',name:'sfxlwjgamc', index: 'sfxlwjgamc',width:'6%'},
			   </logic:equal>
			   {label:'预约咨询日期',name:'yyzxrq', index: 'yyzxrq',width:'11%'},
			   {label:'预约咨询师',name:'zxsxm', index: 'zxsxm',width:'9%',formatter:zxsxmLink},
			   {label:'预约状态',name:'status', index: 'status',width:'9%',formatter:statusChange},
			   {label:'预约反馈',name:'yyfkzt', index: 'yyfkzt',width:'9%',formatter:yyfkztChange},
			   {label:'咨询安排日期',name:'zxrq', index: 'zxrq',width:'11%'},
			   <logic:equal value="10026" name="xxdm">
			   {label:'咨询安排时间段',name:'sjddmzxmc', index: 'sjddmzxmc'},
			   </logic:equal>
			   {label:'安排咨询师',name:'apzxsxm', index: 'apzxsxm',width:'9%',formatter:apzxsxmLink},
			   {label:'咨询状态',name:'zxztmc', index: 'zxztmc',width:'9%'},
			   {label:'咨询状态代码',name:'zxzt', index: 'zxzt',hidden:true},
			   {label:'心理危机个案学生代码',name:'sfxlwjga', index: 'sfxlwjga',hidden:true},
			   {label:'预约咨询师职工号',name:'zgh', index: 'zgh',hidden:true},
			   {label:'安排咨询师职工号',name:'apzxs', index: 'apzxs',hidden:true}
			],
			sortname: "sfxlwjga,status,xh",
		 	sortorder: "asc"
		};
		jQuery(function(){
			gridSetting["params"]=getSuperSearch();
			jQuery("#dataTable").initGrid(gridSetting);
		});
		function searchRs() {
			var map = getSuperSearch();
			jQuery("#dataTable").reloadGrid(map);
		}
		// 学号链接（显示预约咨询申请信息及安排咨询信息）
		function xhLinkForYyfk(cellValue, rowObject) {
			return "<a href = 'javascript:void(0);' class='name' onclick=\"showDialog('查看心理咨询' , 750,440 , 'xlzx_yysqnew.do?method=yyfkDetail&doType=view&id=" + rowObject['id'] + "')\" >" + cellValue + "</a>";
		}
		// 预约状态
		function statusChange(cellValue, rowObject) {
			var returnText;
			switch (cellValue) {
				case "1":
					returnText="预约中";
					break;
				case "2":
					returnText="<font color='blue'>预约成功</font>";
					break;
				case "3":
					returnText="预约中<br/>(学生取消)";
					break;
				case "4":
					returnText="预约成功<br/>(学生取消)";
					break;
				case "5":
					returnText="预约失败";
					break;
				default:
					returnText="";
					break;
			}
			return returnText;
		}
		// 反馈状态
		function yyfkztChange(cellValue, rowObject) {
			var returnText;
			var status = rowObject['status'];
			if(status=='2' || status == '4' || status == '5'){
				returnText = "已反馈";
			}else if(status=='1'){
				returnText = "<font color='red'>未反馈</font>";
			}else{
				returnText = "";
			}
			return returnText;
		}
		// 咨询师姓名链接(安排)
		function apzxsxmLink(cellValue, rowObject) {
			return "<a href = 'javascript:void(0);' class='name' onclick=\"showDialog('查看咨询师信息' , 750 , 410 , 'xlzx_zxs.do?method=zxsglDetail&doType=view&zgh=" + rowObject['apzxs'] + "')\" >" + (cellValue==null?" ":cellValue) + "</a>";
		}
		// 咨询师姓名链接(预约)
		function zxsxmLink(cellValue, rowObject) {
			return "<a href = 'javascript:void(0);' class='name' onclick=\"showDialog('查看咨询师信息' , 750 , 410 , 'xlzx_zxs.do?method=zxsglDetail&doType=view&zgh=" + rowObject['zgh'] + "')\" >" + (cellValue==null?" ":cellValue) + "</a>";
		}
		// 预约反馈
		function xlzxyyfk(){
			var rows = jQuery("#dataTable").getSeletRow();
			if (rows.length != 1){
				showAlertDivLayer("请选择一条您要预约反馈的记录！");
				return false;
			} else{
				var zxzt = rows[0]['zxzt'];
				var status = rows[0]['status'];
				if(status == '3'){
					showAlertDivLayer("您不能对预约中（学生取消）的数据进行操作！");
					return false;
				}else if(status == '4'){
					showAlertDivLayer("您不能对预约成功（学生取消）的数据进行操作！");
					return false;
				}
				if(zxzt == '1' || zxzt == '2'){
					showAlertDivLayer("您只能对未反馈的数据进行操作！");
					return false;
				}
				showDialog('预约反馈',750,440,'xlzx_yysqnew.do?method=yyfkDetail&doType=yyfk&id=' + rows[0]['id'] + '&status=' + rows[0]['status']);
			}
		}
		</script>
	</head>

	<body>
	
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/xlzx_yysqnew" method="post">
			<%@ include file="/comm/hiddenValue.jsp"%>
				<div class="toolbox">
					<!-- 按钮 -->
					<div class="buttonbox">
						<ul>
						<logic:equal  name="writeAble" value="yes">
							<li>
								<a href="#" onclick="xlzxyyfk();return false;" class="btn_xg">预约反馈</a>
							</li>
						</logic:equal>
						</ul>
					</div> 
				</div>
		  		<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
		</html:form>
		<div class="formbox">
			<!--标题start-->
			<h3 class="datetitle_01">
				<span> 预约咨询列表 </span>
			</h3>

			<table id="dataTable" ></table>
			<div id="pager"></div>
		</div>
	</body>
</html>
