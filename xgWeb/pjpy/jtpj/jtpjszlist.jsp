<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
			<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="pjpy/jtpj/js/jtpjsz.js"></script>
		<script type="text/javascript">
			jQuery(function(){
				var sqzq=jQuery("#sqzq").val();
				var sqxn=sqzq.split(",")[0];
				var sqxq=sqzq.split(",")[1];
			     var gridSetting =  {
						pager:"pager",
						url:"jtpjsz.do?method=list&type=query",
						colList:[
						   {label:'奖项配置id',name:'jxid', index: 'jxid',key:true,hidden:true},
						   {label:'奖项名称 ',name:'jxmc', index: 'jxmc',formatter:jxmc},
						   {label:'评奖单位',name:'jtpjdw', index: 'jtpjdw',formatter:pjdwmc},
						   {label:'评定学年',name:'pdxn', index: 'pdxn',hidden:true},
						   {label:'评定学期名称',name:'pdxqmc', index: 'pdxqmc',hidden:true},
						   {label:'奖项评定周期',name:'jxpdzq', index: 'pdxn,pdxq',formatter:jxpdzq},
						   {label:'条件设置',name:'tjsz', index: 'tjsz',formatter:setTjsz},
						   {label:'申请开关',name:'sfkfsq', index: 'sfkfsq',formatter:sqkg},
						   {label:'申请开放时间',name:'sqqzsj', index: 'sqqzsj'},
						   {label:'审核流程',name:'lcxx', index: 'lcxx',hidden:true}
						],
						params:{sqxn:sqxn,sqxq:sqxq},
						sortname: "jxmc",
					 	sortorder: "asc"
					}
					jQuery("#dataTable").initGrid(gridSetting);
			});
			
			function djbsz(){
				var rows = jQuery("#dataTable").getSeletRow();
				if (rows.length != 1) {
					showAlertDivLayer("请选择您要设置登记表的项目！");
				} else {
					document.location.href="jtpjsz.do?method=bgylList&jxid="+rows[0]["jxid"];
				}
			}
		</script>
	</head>
	<body>
		<input id="xbmc" type="hidden" value='<bean:message key="lable.xb" />'/>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
	<html:form action="/jtpjsz.do?method=list&type=query">
		<div class="toolbox">
			<logic:equal name="writeAble" value="yes">	
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<li>
							<a href="javascript:void(0);" onclick="add();return false;" class="btn_zj">增加</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="update();return false;" class="btn_xg">修改</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="del();return false;" class="btn_sc">删除</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="copy();return false;" class="btn_tj">复制</a>
						</li>
						<logic:notEqual name="xxdm" value="11458">	
						<logic:notEqual name="xxdm" value="11067">	
						<li><a href="javascript:void(0);" onclick="djbsz();" class="btn_sz">登记表设置</a></li>	
						</logic:notEqual>
						</logic:notEqual>
					</ul>
				</div>
			</logic:equal>
		</div>
					<!-- 过滤条件 -->
			<div class="searchtab">
			<input type="hidden" id="iscanCopy" value="${iscanCopy}"/> 
				<table width="100%" border="0">
					<tr>
						<th width="10%">申请周期</th>
						<td>
							<html:select property="jxsm" styleId="sqzq"	value="${sqzq}">
								<html:options collection="sqzqList" property="dm" labelProperty="mc" />
							</html:select>
						</td>
						<th width="10%">奖项名称</th>
						<td>
							<input type="text" id="jxmc" name="jxmc" maxlength="20" />
						</td>
						<td>
							<div class="btn">
								<button type="button" class="btn_cx" id="search_go" onclick="query()">
									查 询
								</button>
							</div>
						</td>
					</tr>					
				</table>
			</div>
		</html:form>
		<div class="toolbox">
			<!--标题start-->
			<h3 class="datetitle_01">
				<span style="width: 50%">集体评奖设置信息</span>
			</h3>
			<table id="dataTable"></table>
			<div id="pager"></div>
		</div>
	</body>
</html>
