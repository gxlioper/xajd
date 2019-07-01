<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/xszz/xmwh/js/xmwhCx.js"></script>
		<script type="text/javascript">
			function bbsz(type){
				var rows = jQuery("#dataTable").getSeletRow();
				if (rows.length != 1) {
					showAlertDivLayer("请选择您要设置表的项目！");
				} else {
					document.location.href="xszz_bbwh.do?method=bgylList&bblx="+type+"&xmdm="+rows[0]["xmdm"];
				}
			}
			
			function jfsz(){
				var rows = jQuery("#dataTable").getSeletRow();
				if (rows.length != 1) {
					showAlertDivLayer("请选择一条您要设置的项目！");
					return false;
				}
				
				var url = 'xszz_xmwh.do?method=xmwhJfsz&xmdm=' + rows[0]["xmdm"];
				showDialog("经费设置", 680, 500, url);
			}
			
		</script>
	</head>

	<body>	
		<html:form action="xszz_xmwh.do">
		<input type="hidden" name="currDate" id="currDate" value="${currDate}"/>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
			<p class="help">
				<a href="#" onclick="return false;" onmousedown ="showHelpDiv()">使用帮助</a>
			</p>
		</div>
		<div  id="div_help" class="prompt" style="display: none">
			<h3>
				<span>提示：</span>
			</h3>
			<p>
				<span>
					默认显示当前评奖周期（<font color="red">${currXnXqmc}</font>）的评奖项目数据</br>
					人数设置：此处设置当前项目的获奖<font color="red">人数上限</font>，根据“人数控制范围”设置相应范围内获奖人数上限，在审核中约束获奖人数</br>
					条件设置：此处设置当前项目的<font color="red">获奖条件</font>，对申请项目的学生进行条件约束，符合条件的学生才能获得项目</br>
					不可兼得设置：此处设置当前项目的<font color="red">不可兼得项目</font>,选中项目与当前设置项目不可兼得</br>
					项目调整设置：此处设置当前项目的<font color="red">可调整项目</font>，老师在审核阶段可将学生获奖项目调整为选中项目</br>
					项目复制：可复制非当前周期的评奖项目至当前周期
				</span>
			</p>
			<a class="close" title="隐藏"
			   onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- 提示信息 end-->
		<logic:equal name="writeAble" value="yes">	
			<div class="toolbox">
				<!-- 按钮 -->
					<div class="buttonbox">
						<ul>
							<li><a href="javascript:void(0);" onclick="add();" class="btn_zj">增加</a></li>
							<li><a href="javascript:void(0);" onclick="update();" class="btn_xg">修改</a></li>
							<li><a href="javascript:void(0);" onclick="del();" class="btn_sc">删除</a></li>		
							<li><a href="javascript:void(0);" onclick="bbsz(1);" class="btn_sz">登记表设置</a></li>
							<li><a href="javascript:void(0);" onclick="bbsz(2);" class="btn_sz">上报表设置</a></li>						
							<li><a href="javascript:void(0);" onclick="jfsz();" class="btn_sz">经费设置</a></li>						
						</ul>
					</div>
					<div class="buttonbox">
						<ul>
							<li><a href="javascript:void(0);" onclick="jbsz();" class="btn_sz">基本设置</a></li>						
							<li><a href="javascript:void(0);" onclick="tjsz();" class="btn_sz">条件设置</a></li>						
							<li><a href="javascript:void(0);" onclick="rssz();" class="btn_sz">人数设置</a></li>						
							<li><a href="javascript:void(0);" onclick="jdsz();" class="btn_sz">不可兼得设置</a></li>
							<li><a href="javascript:void(0);" onclick="shsz();" class="btn_sz">审核调整项目设置</a></li>	
							<li><a href="javascript:void(0);" onclick="jxfz();" class="btn_sz">项目复制</a></li>	
						</ul>
					</div>
			</div>
		<div class="searchtab" >
		</logic:equal>
		<logic:notEqual name="writeAble" value="yes">
			<div class="searchtab" >
		</logic:notEqual>
		<!-- 过滤条件 -->
			<table width="100%" border="0">
				<tr>
					<th width="10%">申请周期</th>
					<td width="10%">
						<html:select property="sqzqdm" styleId="sqzqdm">
							<html:options collection="XmzqList" property="zqdm" labelProperty="zqmc"/>
						</html:select>
					</td>
					<th width="10%">项目名称</th>
					<td>
						<input type="text" id="xmmc" name="xmmc" maxleng="50"/>
					</td>
					<th width="10%">项目类别</th>
					<td>
						<html:select property="lbdm" styleId="lbdm" style="width:150px">
						<html:option value=""></html:option>
						<html:options collection="xmlbList" property="dm"
							labelProperty="mc" />
						</html:select>
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
		<div class="formbox">
			<!--标题start-->
			<h3 class="datetitle_01">
				<span> 项目列表 </span>
			</h3>

			<table id="dataTable" ></table>
			<div id="pager"></div>

		</div>
		</html:form>
	</body>
</html>
