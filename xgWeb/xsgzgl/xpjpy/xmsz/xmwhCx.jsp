<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/xpjpy/xmsz/js/xmwhCx.js"></script>
		
		<script type="text/javascript">


            jQuery(function() {
                var xmxz = jQuery("#xmxz").val();
                var czfs =jQuery("#czfs").val();
                var gridSetting = {
                    caption : "项目列表",
                    pager : "pager",
                    url : "xpj_xmwh.do?method=xmwhCx&type=query&xmxz="+xmxz,
                    colList : [
                        {label : '项目代码',name : 'xmdm',index : 'xmdm',key : true,hidden : true},
                        {label : '申请开始时间',name : 'sqkssj',index : 'sqkssj',hidden : true},
                        {label : '申请结束时间',name : 'sqjssj',index : 'sqjssj',hidden : true},
                        {label : '审核开始时间',name : 'shkssj',index : 'shkssj',hidden : true},
                        {label : '审核结束时间',name : 'shjssj',index : 'shjssj',hidden : true},
                        {label : '人数控制范围',name : 'rsfpfs',index : 'rsfpfs',hidden : true},
                        {label : '序号',name : 'xsxh',index : 'xsxh',width : '5%'},
                        {label : '项目名称',name : 'xmmc',index : 'xmmc',width : '15%'},
                        {label : '项目类型',name : 'xmlxmc',index : 'xmlxmc',width : '12%'},
                        {label : '项目性质',name : 'xmxzmc',index : 'xmxzmc',width : '12%'},
                        {label : '金额',name : 'xmje',index : 'to_number(xmje)',width : '8%'},
                        {label : '人数设置',name : 'rssz',index : 'rssz',width : '8%',formatter:setRssz},
                        {label : '条件设置',name : 'tjsz',index : 'tjsz',width : '8%',formatter:setTjsz},
                        {label : '不可兼得设置',name : 'jdsz',index : 'jdsz',width : '8%',formatter:setJdsz},
                        {label : '奖项调整设置',name : 'shsz',index : 'shsz',width : '8%',formatter:setShsz} ,
                        {label : '申请开关',name : 'sqkg',index : 'sqkg',width : '8%',formatter:setSqkg},
                        {label : '审核开关',name : 'shkg',index : 'shkg',width : '8%',formatter:setShkg}
                    ],
                    sortname : "xsxh",
                    sortorder : "asc"
                }

                var gridSetting1 = {
                    caption : "项目列表",
                    pager : "pager",
                    url : "xpj_xmwh.do?method=xmwhCx&type=query&czfs=xyrssz&xmxz="+xmxz,
                    colList : [
                        {label : '项目代码',name : 'xmdm',index : 'xmdm',key : true,hidden : true},
                        {label : '申请开始时间',name : 'sqkssj',index : 'sqkssj',hidden : true},
                        {label : '申请结束时间',name : 'sqjssj',index : 'sqjssj',hidden : true},
                        {label : '审核开始时间',name : 'shkssj',index : 'shkssj',hidden : true},
                        {label : '审核结束时间',name : 'shjssj',index : 'shjssj',hidden : true},
                        {label : '人数控制范围',name : 'rsfpfs',index : 'rsfpfs',hidden : true},
                        {label : '项目名称',name : 'xmmc',index : 'xmmc',width : '20%'},
                        {label : '项目类型',name : 'xmlxmc',index : 'xmlxmc',width : '12%'},
                        {label : '项目性质',name : 'xmxzmc',index : 'xmxzmc',width : '12%'},
                        {label : '金额',name : 'xmje',index : 'to_number(xmje)',width : '8%'},
                        {label : '人数设置',name : 'rssz',index : 'rssz',width : '8%',formatter:setRsszXy}
                    ],
                    sortname : "xsxh",
                    sortorder : "asc"
                }
                if("xyrssz"==czfs){
                    jQuery("#dataTable").initGrid(gridSetting1);
                }else{
                    jQuery("#dataTable").initGrid(gridSetting);
                }
            });
			function bbsz(type){
				var rows = jQuery("#dataTable").getSeletRow();
				if (rows.length != 1) {
					showAlertDivLayer("请选择您要设置登记表的项目！");
				} else {
					document.location.href="xpj_bbwh.do?method=bgylList&xmdm="+rows[0]["xmdm"]+"&bblx="+type;
				}
			}
		</script>
		
	</head>
	<body>	
		<html:form action="xpj_xmwh.do">
		<input type="hidden" name="currDate" id="currDate" value="${currDate}"/>
		<input type="hidden" name="czfs" id="czfs" value="${czfs}"/>
		<input type="hidden" name="xmxz" id="xmxz" value="${xmxz}"/>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
			<p class="help">
				<a href="#" onclick="return false;"	onmousedown ="showHelpDiv();">使用帮助</a>
			</p>
		</div>
		
		<!-- 提示信息 end-->
		<div  id="div_help" class="prompt" style="display: none">
			<h3>
				<span>提示：</span>
			</h3>
			<p>
				<span>
					默认显示当前评奖周期（<font color="red">${currXnXqmc}</font>）的评奖项目数据</br>
					人数设置：此处设置当前项目的获奖<font color="red">人数上限</font>，根据“人数控制范围”设置相应范围内获奖人数上限，在审核中约束获奖人数</br>
					<logic:notEqual name="czfs" value="xyrssz">
						条件设置：此处设置当前项目的<font color="red">获奖条件</font>，对申请奖项的学生进行条件约束，符合条件的学生才能获得奖项</br>
						不可兼得设置：此处设置当前项目的<font color="red">不可兼得项目</font>,选中项目与当前设置项目不可兼得</br>
						奖项调整设置：此处设置当前项目的<font color="red">可调整项目</font>，老师在审核阶段可将学生获奖项目调整为选中项目</br>
						奖项复制：可复制非当前周期的评奖项目至当前周期
					</logic:notEqual>
				</span>
			</p>
			<a class="close" title="隐藏"
			   onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- 提示信息 end-->
		<div class="toolbox">
			<!-- 按钮 -->
			<logic:notEqual name="czfs" value="xyrssz">
				<logic:equal name="writeAble" value="yes">	
				<div class="buttonbox">
					<ul>
						<li><a href="javascript:void(0);" onclick="add();" class="btn_zj">增加</a></li>
						<li><a href="javascript:void(0);" onclick="update();" class="btn_xg">修改</a></li>
						<li><a href="javascript:void(0);" onclick="del();" class="btn_sc">删除</a></li>						
						<li><a href="javascript:void(0);" onclick="rssz();" class="btn_sz">人数设置</a></li>						
						<li><a href="javascript:void(0);" onclick="tjsz();" class="btn_sz">条件设置</a></li>						
						<li><a href="javascript:void(0);" onclick="jdsz();" class="btn_sz">不可兼得设置</a></li>	
					</ul>
				</div>
				<div class="buttonbox">
					<ul>				
						<li><a href="javascript:void(0);" onclick="shsz();" class="btn_sz">奖项调整设置</a></li>		
						<li><a href="javascript:void(0);" onclick="jxfz();" class="btn_sz">奖项复制</a></li>	
						<li><a href="javascript:void(0);" onclick="bbsz(1);" class="btn_sz">登记表设置</a></li>
						<li><a href="javascript:void(0);" onclick="bbsz(2);" class="btn_sz">上报表设置</a></li>
						<li><a href="javascript:void(0);" onclick="exportConfig();" class="btn_dc">导出</a></li>	
					</ul>
				</div>
				</logic:equal>
			</logic:notEqual>
			<!-- 过滤条件 -->
			<div class="searchtab">
				<table width="100%" border="0">
					<tr>
						<th width="10%">项目名称</th>
						<td>
							<input type="text" id="xmmc" name="xmmc" maxleng="50" style="width: 100px"/>
						</td>
						<th>项目类型</th>
						<td>
							<html:select property="lxdm" styleId="lxdm" style="width:100px">
							<html:option value=""></html:option>
							<html:options collection="xmlxList" property="dm"
								labelProperty="mc" />
							</html:select>
						</td>
						<%--<th>项目性质</th>--%>
						<%--<td>--%>
							<%--<html:select property="xzdm" styleId="xzdm" style="width:100px">--%>
							<%--<html:option value=""></html:option>--%>
							<%--<html:options collection="xmxzList" property="dm"--%>
								<%--labelProperty="mc" />--%>
							<%--</html:select>--%>
						<%--</td>--%>
						<th>申请开关</th>
						<td>
							<html:select property="sqkg" styleId="sqkg" style="width:100px">
							<html:option value=""></html:option>
							<html:option value="0">未开启</html:option>
							<html:option value="1">已开启</html:option>
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
		</div>
		<div class="formbox">
			<!--标题start-->
			<h3 class="datetitle_01">
			
				<span> 
				<logic:equal value="xpj_xmwh.do?method=xmwhCx&czfs=xyrssz" name="path">
				<font color="blue">${currXnXqmc}&nbsp;&nbsp;</font>奖学金名额调整
				（学院奖学金总额<font color="red">${jxjjeMap.jxjze}</font>元， 
				已调整金额<font color="red">${jxjjeMap.jxsjze}</font> 元）
				</logic:equal>
				<logic:notEqual value="xpj_xmwh.do?method=xmwhCx&czfs=xyrssz" name="path">
				<font color="blue">${currXnXqmc}&nbsp;&nbsp;</font>项目列表
				</logic:notEqual>
				 </span>
			
			</h3>

			<table id="dataTable" ></table>
			<div id="pager"></div>

		</div>
		</html:form>
	</body>
</html>
