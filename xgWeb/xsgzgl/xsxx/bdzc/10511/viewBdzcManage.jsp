<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/xsxx/bdzc/10511/js/bdzc.js"></script>
		<script type="text/javascript">
			var gridSetting = {
				caption:"学期<bean:message key="lable.bdzc"/>列表",
				pager:"pager",
				url:"xsxx_xqbdzcgl.do?method=viewBdzcManage&type=query",
				colList:[
					{label:'key',name:'id', index: 'id',key:true ,hidden:true},
					{label:'学年',name:'xn', index: 'xn',width:'12%'},
					{label:'学期',name:'xqmc', index: 'xqmc',width:'3%'},
					{label:'学号',name:'xh', index: 'xh',width:'12%' , formatter:xhLink},
					{label:'姓名',name:'xm', index: 'xm',width:'8%'},
					{label:'性别',name:'xb', index: 'xb',width:'3%'},
					{label:'年级',name:'nj', index: 'nj',width:'7%'},
					{label:'班级',name:'bjmc', index: 'bjdm',width:'18%'},
					{label:'<bean:message key="lable.bdzc"/>状态',name:'zcztmc', index: 'zcztmc',width:'8%'},
					{label:'zczt',name:'zczt', index: 'zczt',width:'16%' , hidden:true},
					{label:'xq',name:'xq', index: 'xq',width:'16%' , hidden:true},
					{label:'未注册类别',name:'wbdlbmc', index: 'wbdlbmc',width:'16%' },
					{label:'<bean:message key="lable.bdzc"/>时间', name:'zcsj', index: 'zcsj',width:'15%'}
				],
				sortname: "xn,xq",
			 	sortorder: "desc"
			};
			
			jQuery(function(){
				gridSetting["params"]=getSuperSearch();
				jQuery("#dataTable").initGrid(gridSetting);
			});


			/**
			 * 学号链接
			 * @param cellValue
			 * @param rowObject
			 * @return
			 */

			function xhLink(cellValue,rowObject){
				//return "<a href='javascript:void(0);' class='name' onclick='knsView(\""+rowObject["id"]+"\",\""+cellValue+"\");'>"+cellValue+"</a>";
				var onclickfn = "onclick=\"" + "showDialog('学生详细信息' , 720 , 480 , 'xsxx_xqbdzcgl.do?method=ckXqbdzc&xh=" + cellValue + "&xq=" + rowObject['xq']+ "&xn=" + rowObject['xn'] + "')" + "\"";
				//var url = "xsxx_xqbdzcgl.do?method=ckXqbdzc&xh=" + xh + "&xn=" + xn + "&xq=" + xq;
				var href = "href = 'javascript:void(0);'";

				var el = "<a " + href + " class='name' " + onclickfn + " >" + cellValue + "</a>";
				
				return el;
			}

<!--			function setWbdlbdm(cellValue,rowObject){-->
<!--				if(null!=cellValue){-->
<!--				return "<span title='"+rowObject["setWbdlbdm"]+"'>"+cellValue+"</span>";-->
<!--				}-->
<!--			}-->

			function getWord(){
				var rows = jQuery("#dataTable").getSeletRow();
				
				 if (rows.length == 0){
					showAlertDivLayer("请选择您要下载的记录！");
				 } else if (rows.length > 1){
					var ids = jQuery("#dataTable").getSeletIds();
					var url="xpj_pjjg.do?method=getDjbZip&value="+ids;
					window.open(url);
				 } else {
					var url="xpj_pjjg.do?method=getDjbWord&id="+rows[0]["id"];
					window.open(url);
			     }
			}
		</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
			<p class="help">
				<a href="#" onclick="return false;" onmousedown ="showHelpDiv()">使用帮助</a>
			</p>
		</div>
		<!-- 提示信息 end-->
		<div class="prompt" id="div_help" style="display: none">
			<h3>
				<span>提示：</span>
			</h3>
			<p>
				<span>
					当不勾选任何学生时，对符合选中查询条件的学生进行学期批量<bean:message key="lable.bdzc"/>,<br/>
					当勾选学生时，对选中的学生进行学期批量<bean:message key="lable.bdzc"/>。
				</span>
			</p>
			<a class="close" title="隐藏" onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- 提示信息 end-->
		<html:form action="/xsxx_xqbdzcgl">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<html:hidden property="searchXn" />
			<html:hidden property="searchXq" />
			<html:hidden property="xn" styleId="xn"/>
			<html:hidden property="xq" styleId="xq"/>
			<input type="hidden" name="zckg" id="zckg" value="${zckg}"/>
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul><%--
						<logic:equal name="writeAble" value="yes">	
						<li><a href="javascript:void(0);" onclick="dgzc('<bean:message key="lable.bdzc"/>');" class="btn_zj">单个<bean:message key="lable.bdzc"/></a></li>
						</logic:equal>
						--%>
						<logic:notEqual value="stu" scope="session" name="userType">
							<logic:equal name="writeAble" value="yes">	
								<li><a href="javascript:void(0);" onclick="plzc('<bean:message key="lable.bdzc"/>');" class="btn_plqy"><bean:message key="lable.bdzc"/></a></li>
								<li><a href="javascript:void(0);" onclick="plcx('<bean:message key="lable.bdzc"/>');" class="btn_sc">撤销<bean:message key="lable.bdzc"/></a></li>
							</logic:equal>
							<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">导出</a></li>
						</logic:notEqual>
						<li><a href="#" onclick="ckzc('<bean:message key="lable.bdzc"/>');return false;" id="btn_ck" class="btn_ck">查看</a></li>
						<logic:notEqual value="stu" scope="session" name="userType">
							<logic:equal name="writeAble" value="yes">	
								<li><a href="javascript:void(0);" onclick="wbdyywh('<bean:message key="lable.bdzc"/>');" class="btn_xg">未<bean:message key="lable.bdzc"/>原因维护</a></li>
							</logic:equal>
						</logic:notEqual>
					</ul>
				</div>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>学期<bean:message key="lable.bdzc"/>列表</span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
