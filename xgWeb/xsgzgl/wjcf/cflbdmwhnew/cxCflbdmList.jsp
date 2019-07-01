<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript"
			src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript"
			src="xsgzgl/wjcf/cflbdmwhnew/js/cflbdm.js"></script>
		<script type="text/javascript">
			var gridSetting = {
				caption:"处分类别信息列表",
				pager:"pager",
				url:"wjcf_cflbdmwh.do?method=cxCflbdmList&type=query",
				colList:[
			       {label:'pk',name:'cflbdm', index: 'cflbdm',key:true,hidden:true},
				   {label:'名称',name:'cflbmc', index: 'cflbmc',width:'10%'},
				   {label:'审核流程',name:'spl', index: 'spl',width:'12%'},
				   {label:'是否可申诉',name:'sfkss', index: 'sfkss',width:'12%'},
				    <logic:equal name="xxdm" value="14073">
				    	{label:'申诉受理时长',name:'ssslgzr', index: 'ssslgzr',width:'8%'},
				    </logic:equal>
				    <logic:notEqual name="xxdm" value="14073">
				   		{label:'申诉截止日',name:'ssslgzr', index: 'ssslgzr',width:'8%'},
				    </logic:notEqual>
				   	<logic:equal name="xxdm" value="14073">
				   		{label:'是否可申请<bean:message key="wjcf.text" />',name:'sfksqjc', index: 'sfksqjc',width:'18%',formatter:format},
				    </logic:equal>
				   	<logic:notEqual name="xxdm" value="14073">	    
				   		{label:'是否可<bean:message key="wjcf.text" />',name:'sfksqjc', index: 'sfksqjc',width:'18%',formatter:format},
				   	</logic:notEqual>
				    <logic:equal name="xxdm" value="14073">
				    	{label:'<bean:message key="wjcf.text" />受理时长',name:'jsslqsr', index: 'jsslqsr',width:'8%'},
				    </logic:equal>
				    <logic:notEqual name="xxdm" value="14073">
				    	{label:'<bean:message key="wjcf.text" />起始日',name:'jsslqsr', index: 'jsslqsr',width:'8%'},
				    </logic:notEqual>
				   
				   {label:'是否有处分期限',name:'cfqxflag', index: 'cfqxflag',width:'6%'},
                    {label:'创建时间',name:'cjsj', index: 'cjsj',width:'6%'},
				],
				sortname: "cjsj",
			 	sortorder: "asc"
			};
			function format(cellValue, rowObject){
				var sfksqjc = rowObject["sfksqjc"];
				if(!sfksqjc){
					return "";
				}
				var sfksq=sfksqjc.split(",");
				var text="";
				var i=0;

				if("14073" == jQuery("#xxdm").val()) {
					for(var str in sfksq){
						if(sfksq[str]=="no"){
							text+='不可申请';
						}else if(sfksq[str]=="xs"){
							text+='学生可申请';
						}else if(sfksq[str]=="js"){
							text+='教师可申请';
						}
						if(parseInt(str)+1!=sfksq.length){
							text+=",";
						}
					}
				} else {
					for(var str in sfksq){
						if(sfksq[str]=="no"){
							text+='不可<bean:message key="wjcf.text" />';
						}else if(sfksq[str]=="xs"){
							text+='学生可<bean:message key="wjcf.text" />';
						}else if(sfksq[str]=="js"){
							text+='教师可<bean:message key="wjcf.text" />';
						}
						if(parseInt(str)+1!=sfksq.length){
							text+=",";
						}
					}
				}
				return text;
			}
			function dcmcLink(cellValue, rowObject) {
				var qjsqid = rowObject["qjsqid"];
				return "<a href='javascript:void(0);' onclick=\"ckxx('" + qjsqid
						+ "')\" class='name'>" + cellValue + "</a>";
			}
			jQuery(function(){
				jQuery("#dataTable").initGrid(gridSetting);
			});

			function query(){
				var map = {};
				map["cflbmc"] = jQuery("#cflbmc").val();
				jQuery("#dataTable").reloadGrid(map);
			}

			
		</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<input type="hidden" id="text"
			value="<bean:message key="wjcf.text" />" />
		<%@ include file="/comm/hiddenValue.jsp"%>
		<div class="toolbox">
			<!-- 按钮 -->
			<logic:equal value="yes" name="writeAble">
			<div class="buttonbox">
				<ul>
						<li>
							<a href="javascript:void(0);"
								onclick="showDialog('增加处分类别',450,400,'wjcf_cflbdmwh.do?method=cflbdmAdd');"
								class="btn_zj">增加</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="update();" class="btn_xg">修改</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="del();" class="btn_sc">删除</a>
						</li>
				</ul>
			</div>
			</logic:equal>
			<!-- 过滤条件 -->
			<div class="searchtab">
				<table width="100%" border="0">
					<tbody>
						<tr>
							<th width="15%">
								处分类别名称
							</th>
							<td>
								<input type="text" id="cflbmc"
									onkeypress="if(event.keyCode==13){query();}" />
							</td>
							<td>
								<div class="btn">
									<button type="button" class="btn_cx" id="search_go"
										onclick="query()">
										查 询
									</button>
								</div>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>


		<div class="formbox">
			<!--标题start-->
			<h3 class="datetitle_01">
				<span> 处分类别信息列表 </span>
			</h3>

			<table id="dataTable"></table>
			<div id="pager"></div>

		</div>
	</body>
</html>
