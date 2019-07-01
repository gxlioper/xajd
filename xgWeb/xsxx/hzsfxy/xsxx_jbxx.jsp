<%@ page language="java" contentType="text/html; charset=GBK"%>
<div id="xsxx" style="display: none">
	<table align="center" class="formlist breakword">
		<thead>
			<tr>
				<th colspan="5" onclick="deploy('hi_xjxx');">
					<span>学籍信息</span>
				</th>
			</tr>
		</thead>
		<tbody id="hi_xjxx">
			<tr>
				<th width="15%">
					年级
				</th>
				<td width="25%">
					${rs.nj}
				</td>
				<th width="15%">
					学制(年)
				</th>
				<td colspan="2" width="45%">
					${rs.xz}
				</td>
			</tr>
			<tr>
				<th>
					<bean:message key="lable.xsgzyxpzxy" />
				</th>
				<td>
					${rs.xymc}
				</td>
				<th>
					是否注册
				</th>
				<td colspan="2">
					${rs.sfzc}
				</td>
			</tr>
			<tr>
				<th>
					专业
				</th>
				<td>
					${rs.zymc}
				</td>
				<th>
					是否走读生
				</th>
				<td colspan="2">
					${rs.sfzd}
				</td>
			</tr>
			<tr>
				<th>
					<%--中国地质大学 --%>
					<logic:equal value="10491" name="xxdm">当前</logic:equal>
					班级
				</th>
				<td>
					${rs.bjmc}
				</td>
				<th>
					是否在校
				</th>
				<td colspan="2">
					${rs.sfzx}
				</td>
			</tr>
			<tr>
				<th>
					学籍状态
				</th>
				<td>
					${rs.xjztm}
				</td>
				<th>
					是否毕业生
				</th>
				<td colspan="2">
					${rs.sfbys}
				</td>
			</tr>
			<tr>
				<th>
					入学时间
				</th>
				<td align="left">
					${rs.rxrq}
				</td>
				<th>
					是否毕业
				</th>
				<td colspan="2">
					${rs.sfyby}
				</td>
			</tr>
			<logic:notEqual name="xxdm" value="11057">
				<logic:notEqual name="xxdm" value="12752">
					<tr>
						<th>
							毕业时间
						</th>
						<td>
							${rs.byny}
						</td>
						<th>
							校区信息
						</th>
						<td colspan="2">
							${rs.yxdm}
						</td>
					</tr>
				</logic:notEqual>
			</logic:notEqual>
			<logic:equal name="xxdm" value="70001">
			<!-- 北京市工贸技师学院 -->
				<tr>
						<th>
							是否征兵离校
						</th>
						<td colspan="4">
							${rs.sfzblx}
						</td>
					</tr>
			</logic:equal>
			<logic:equal name="xxdm" value="11057">
				<tr>
					<th>
						毕业时间
					</th>
					<td>
						${rs.byny}
					</td>
					<th>
						毕业证号
					</th>
					<td colspan="2">
						${rs.byzh }
					</td>
				</tr>
				<tr>
					<th>
						学位证号
					</th>
					<td colspan="4">
						${rs.xwzsbh }
					</td>
				</tr>
			</logic:equal>
			<logic:equal name="xxdm" value="12752">
				<tr>
					<th>
						毕业时间
					</th>
					<td>
						${rs.byny}
					</td>
					<th>
						考生号
					</th>
					<td colspan="2">
						${rs.ksh }
					</td>
				</tr>
			</logic:equal>
			<logic:equal name="xxdm" value="12578">
				<!-- 广东体育职业技术学院 -->
				<tr>
					<th>
						学籍号
					</th>
					<td>
						${rs.xjh}
					</td>
					<th>
						户口性质
					</th>
					<td colspan="2">
						${rs.hkxz }
					</td>
				</tr>
				<tr>
					<th>
						加入政治面貌日期
					</th>
					<td>
						${rs.jrzzmmrq}
					</td>
					<th>
						是否华侨
					</th>
					<td colspan="2">
						${rs.sfhq }
					</td>
				</tr>
				<tr>
					<th>
						高考考生号
					</th>
					<td>
						${rs.ksh}
					</td>
					<th>
						出生地
					</th>
					<td colspan="2">
						${rs.csds }${rs.csdshi }${rs.csdxian }
					</td>
				</tr>
			</logic:equal>
			<logic:equal name="xxdm" value="10347">
				<tr>
					<th>
						学籍预警
					</th>
					<td>
						${rs.zd1}
					</td>
					<th>
						是否特殊学生
					</th>
					<td colspan="2">
						${rs.sftsxs }
					</td>
				</tr>
			</logic:equal>	
		</tbody>

		<!--学籍异动(已有学籍异动选项卡，yyp)-->
		<%--<%@ include file="/xsxx/common/xsxx_xjyd.jsp"%>--%>
		
		<thead>
			<tr>
				<th colspan="5" onclick="deploy('hi_lxfs');">
					<span>联系方式及证件</span>
				</th>
			</tr>
		</thead>
		<tbody id="hi_lxfs">
			<tr>
				<th width="15%">
					<!-- 金华职业技术学院 -->
					<logic:equal name="xxdm" value="12061">
					短号
					</logic:equal>
					<!-- end金华职业技术学院 -->
					<logic:notEqual name="xxdm" value="12061">
					固定电话
					</logic:notEqual>
				</th>
				<td width="25%">
					${rs.lxdh}
				</td>
				<th width="15%">
					手机号码
				</th>
				<td width="45%" colspan="2">
					${rs.sjhm}
				</td>
			</tr>
			<tr style="display: none;">
				<th>
					宿舍编号
				</th>
				<td>
					${rs.ssbh}
				</td>
				<th>
					寝室电话
				</th>
				<td colspan="2">
					${rs.qsdh}
				</td>
			</tr>
			<tr>
				<th>
					QQ号码
				</th>
				<td>
					${rs.qqhm}
				</td>
				<th>
					电子邮箱
				</th>
				<td colspan="2">
					${rs.lxdzxx}
				</td>
			</tr>
			<tr>
				<th>
					银行名称
				</th>
				<td>
					${rs.yhmc}
				</td>
				<th>
					银行卡号
				</th>
				<td colspan="2">
					${rs.yhkh}
				</td>
			</tr>
			<tr>
				<th>
					辅导员信息
				</th>
				<td colspan="4">
					${rs.fdyxx }
				</td>
			</tr>
			<tr>
				<th>
					班主任信息
				</th>
				<td colspan="4">
					${rs.bzrxx }
				</td>
			</tr>
			<tr>
<%--				<th>--%>
<%--					助理班主任--%>
<%--				</th>--%>
<%--				<td>--%>
<%--					${rs.zlbzrxm}--%>
<%--				</td>--%>
				<th>
					一卡通号
				</th>
				<td colspan="4">
					<!--西昌学院-->
					<logic:equal value="10628#" name="xxdm">
							${rs.ykth}
						</logic:equal>
					<logic:notEqual value="10628#" name="xxdm">
							${rs.kh}
						</logic:notEqual>
				</td>
			</tr>
		</tbody>
		<thead>
			<tr>
				<th colspan="5" onclick="deploy('hi_jtxx');">
					<span>学生家庭信息</span>
				</th>
			</tr>
		</thead>
		<tbody id="hi_jtxx">
			<th width="10%">家庭电话</th>
			<td width="30%">
				${rs.jtdh }
			</td>	
			<th width="10%">邮政编码</th>
			<td colspan="2">
				${rs.yb }
			</td>
			<tr>
				<th>家庭地址</th>
				<td colspan="4">
					${rs.jtszd }
				</td>
			</tr>
			<tr>
				<th>家庭经济状况</th>
				<td colspan="4" style="word-break:break-all;width:100%">
					${rs.jjzk }
				</td>
			</tr>
		</tbody>
		<thead>
			<tr>
				<th colspan="5" onclick="deploy('hi_jtcy1');">
					<span>学生家庭成员信息1</span>
				</th>
			</tr>
		</thead>
		<tbody id="hi_jtcy1">
			<tr>
				<th width="10%">姓名</th>
				<td width="30%">
					${rs.jtcy1_xm }
				</td>	
				<th width="10%">与本人关系</th>
				<td colspan="2">
					${rs.jtcy1_gx }
				</td>
			</tr>
			<tr>
				<th width="10%">出生日期</th>
				<td width="30%">
					${rs.jtcy1_nl }
				</td>
				<th width="10%">身份证号</th>
				<td colspan="2">
					${rs.jtcy1_sfzh }
				</td>
			</tr>
			<tr>
				<th width="10%">民族</th>
				<td width="30%">
					${rs.jtcy1_mzmc }
				</td>	
				<th width="10%">政治面貌</th>
				<td colspan="2">
					${rs.jtcy1_zzmmmc }
				</td>
			</tr>
			<tr>
				<th width="10%">职业</th>
				<td width="30%">
					${rs.jtcy1_zy }
				</td>	
				<th width="10%">职务</th>
				<td colspan="2">
					${rs.jtcy1_zw }
				</td>
			</tr>
			<tr>
				<th width="10%">工作单位电话</th>
				<td width="30%">
					${rs.jtcy1_lxdh2 }
				</td>	
				<th width="10%">个人电话</th>
				<td colspan="2">
					${rs.jtcy1_lxdh1 }
				</td>
			</tr>
			<tr>
				<th>工作地址</th>
				<td colspan="4" style="word-break:break-all;width:100%">
					${rs.jtcy1_gzdz }
				</td>
			</tr>
		</tbody>
		<thead>
			<tr>
				<th colspan="5" onclick="deploy('hi_jtcy2');">
					<span>学生家庭成员信息2</span>
				</th>
			</tr>
		</thead>
		<tbody id="hi_jtcy2">
			<tr>
				<th width="10%">姓名</th>
				<td width="30%">
					${rs.jtcy2_xm }
				</td>	
				<th width="10%">与本人关系</th>
				<td colspan="2">
					${rs.jtcy2_gx }
				</td>
			</tr>
			<tr>
				<th width="10%">出生日期</th>
				<td width="30%">
					${rs.jtcy2_nl }
				</td>
				<th width="10%">身份证号</th>
				<td colspan="2">
					${rs.jtcy2_sfzh }
				</td>
			</tr>
			<tr>
				<th width="10%">民族</th>
				<td width="30%">
					${rs.jtcy2_mzmc }
				</td>	
				<th width="10%">政治面貌</th>
				<td colspan="2">
					${rs.jtcy2_zzmmmc }
				</td>
			</tr>
			<tr>
				<th width="10%">职业</th>
				<td width="30%">
					${rs.jtcy2_zy }
				</td>	
				<th width="10%">职务</th>
				<td colspan="2">
					${rs.jtcy2_zw }
				</td>
			</tr>
			<tr>
				<th width="10%">工作单位电话</th>
				<td width="30%">
					${rs.jtcy2_lxdh2 }
				</td>	
				<th width="10%">个人电话</th>
				<td colspan="2">
					${rs.jtcy2_lxdh1 }
				</td>
			</tr>
			<tr>
				<th>工作地址</th>
				<td colspan="4" style="word-break:break-all;width:100%">
					${rs.jtcy2_gzdz }
				</td>
			</tr>
		</tbody>
		<thead>
			<tr>
				<th colspan="5" onclick="deploy('hi_jtcy3');">
					<span>学生家庭成员信息3</span>
				</th>
			</tr>
		</thead>
		<tbody id="hi_jtcy3">
			<tr>
				<th width="10%">姓名</th>
				<td width="30%">
					${rs.jtcy3_xm }
				</td>	
				<th width="10%">与本人关系</th>
				<td colspan="2">
					${rs.jtcy3_gx }
				</td>
			</tr>
			<tr>
				<th width="10%">出生日期</th>
				<td width="30%">
					${rs.jtcy3_nl }
				</td>
				<th width="10%">身份证号</th>
				<td colspan="2">
					${rs.jtcy3_sfzh }
				</td>
			</tr>
			<tr>
				<th width="10%">民族</th>
				<td width="30%">
					${rs.jtcy3_mzmc }
				</td>	
				<th width="10%">政治面貌</th>
				<td colspan="2">
					${rs.jtcy3_zzmmmc }
				</td>
			</tr>
			<tr>
				<th width="10%">职业</th>
				<td width="30%">
					${rs.jtcy3_zy }
				</td>	
				<th width="10%">职务</th>
				<td colspan="2">
					${rs.jtcy3_zw }
				</td>
			</tr>
			<tr>
				<th width="10%">工作单位电话</th>
				<td width="30%">
					${rs.jtcy3_lxdh2 }
				</td>	
				<th width="10%">个人电话</th>
				<td colspan="2">
					${rs.jtcy3_lxdh1 }
				</td>
			</tr>
			<tr>
				<th>工作地址</th>
				<td colspan="4" style="word-break:break-all;width:100%">
					${rs.jtcy3_gzdz }
				</td>
			</tr>
		</tbody>
		<thead>
			<tr>
				<th colspan="5" onclick="deploy('hi_qtxx');">
					<span>其它信息</span>
				</th>
			</tr>
		</thead>
		<tbody id="hi_qtxx">
			<tr>
				<th width="15%">
					姓名拼音
				</th>
				<td width="25%">
					${rs.xmpy}
				</td>
				<th width="15%">
					曾用名
				</th>
				<td align="left" colspan="2" width="45%">
					${rs.cym}
				</td>

			</tr>
			<tr>
				<th>
					身高(cm)
				</th>
				<td align="left">
					${rs.sg}
				</td>
				<th>
					体重(kg)
				</th>
				<td colspan="2">
					${rs.tz}
				</td>
			</tr>
			<logic:equal name="xxdm" value="12862">
				<tr>
					<th>
						胸围(cm)
					</th>
					<td align="left">
						${rs.xsxw}
					</td>
					<th>
						鞋子尺码(码)
					</th>
					<td colspan="2">
						${rs.xzcm}
					</td>
				</tr>
			</logic:equal>

			<logic:notEqual value="10491" name="xxdm">
				<tr>
					<th>
						特长
					</th>
					<td>
						${rs.tc}
					</td>
					<th>
						考生类别
					</th>
					<td align="left" colspan="2">
						<logic:equal value="new" name="edition">
							${rs.kslbmc}
						</logic:equal>
						<logic:notEqual value="new" name="edition">
							${rs.kslb}
						</logic:notEqual>
					</td>
				</tr>
				<tr>
					<th>
						入学方式
					</th>
					<td align="left">
						<logic:equal value="new" name="edition">
							${rs.rxfsmc}
						</logic:equal>
						<logic:notEqual value="new" name="edition">
							${rs.rxfs}
						</logic:notEqual>
					</td>
					<th>
						培养方式
					</th>
					<td align="left" colspan="2">
						<logic:equal value="new" name="edition">
							${rs.pyfsmc}
						</logic:equal>
						<logic:notEqual value="new" name="edition">
							${rs.pyfs}
						</logic:notEqual>
					</td>
				</tr>
			</logic:notEqual>
			<!-- 南京技师 -->
			<logic:equal name="xxdm" value="8001">
				<tr>
					<th>
						户口性质
					</th>
					<td>
						${rs.hkxz}
					</td>
					<th>
						入学前文化程度
					</th>
					<td colspan="2">
						${rs.rxqwhcd}
					</td>
				</tr>
				<tr>
					<th>
						注册顺序号码
					</th>
					<td>
						${rs.zcsxhm}
					</td>
					<th></th>
					<td colspan="2">

					</td>
				</tr>
			</logic:equal>

			<%--乐山师范学院--%>
			<logic:equal value="10649" name="xxdm">
				<tr>
					<th>
						入校前的毕业学校
					</th>
					<td colspan="4">
						${rs.rxqdw}
					</td>
				</tr>
			</logic:equal>
			<%--end乐山师范学院--%>

			<!-- 北京 理工大学珠海学院 -->
			<logic:equal value="13675" name="xxdm" scope="session">
				<tr>
					<th>
						是否报到
					</th>
					<td>
						${rs.sfbd}
					</td>
					<th>
						缴费情况
					</th>
					<td colspan="2">
						${rs.jfqk }
					</td>
				</tr>
			</logic:equal>
			<!-- end北京 理工大学珠海学院 -->
			<%--湖南工业大学--%>
			<logic:equal value="11535" name="xxdm">
				<tr>
					<th>
						档案是否遗留
					</th>
					<td>
						${rs.dasfyl}
					</td>
					<th>
						档案遗留原因
					</th>
					<td colspan="2">
						${rs.daylyy}
					</td>
				</tr>
			</logic:equal>

			<%-- 长沙民政--%>
			<logic:equal value="10827" name="xxdm">
				<tr>
					<th>
						毕业证发放状态
					</th>
					<td colspan="4">
						${rs.byzffztmc}
					</td>
				</tr>
			</logic:equal>
			<%--北京联合大学--%>
			<logic:equal value="11417" name="xxdm">
				<tr>
					<th>
						是否在分校
					</th>
					<td>
						${rs.sfzfx}
					</td>
					<th>
						宗教信息
					</th>
					<td colspan="2">
						${rs.zjmc}
					</td>
				</tr>
			</logic:equal>
			<%--end北京联合大学--%>
			<%--湖北交通职业技术学院--%>
			<logic:equal value="12752" name="xxdm">
				<tr>
					<th>
						档案号
					</th>
					<td>
						${rs.dah}
					</td>
					<th>
						医疗保险号
					</th>
					<td colspan="2">
						${rs.ylbxh}
					</td>
				</tr>
			</logic:equal>
			<%--end湖北交通职业技术学院--%>

			<!--天津交通职业学院-->
			<logic:equal value="12883" name="xxdm">
				<tr>
					<td colspan="4">
						<%@ include file="/xsxx/xxjlxxb.jsp"%>
					</td>
				</tr>
			</logic:equal>
			<!--end天津交通职业学院-->

			<%--南宁职业技术学院--%>
			<logic:equal value="11355" name="xxdm">
				<tr>
					<th>
						考生号
					</th>
					<td>
						${rs.ksh}
					</td>
					<th></th>
					<td colspan="2">

					</td>
				</tr>
			</logic:equal>
			<%--end南宁职业技术学院--%>
			<%--武汉理工大学华夏学院--%>
			<logic:equal value="1049701" name="xxdm">
				<tr>
					<th>
						邮编
					</th>
					<td>
						${rs.yb}
					</td>
					<th>
						乘车区间
					</th>
					<td colspan="2">
						${rs.ccqj}
					</td>
				</tr>
			</logic:equal>
			<%--云南艺术学院--%>
			<logic:equal value="10690" name="xxdm">
				<tr>
					<th>
						职务
					</th>
					<td colspan="4">
						${rs.zw}
					</td>
				</tr>
			</logic:equal>
			<%--end云南艺术学院--%>
			<%--长沙民政职业技术学院--%>
			<logic:equal value="10827" name="xxdm">
				<tr>
					<th>
						户口迁出时间
					</th>
					<td>
						${rs.hkqcsj}
					</td>
					<th>
						档案是否在校
					</th>
					<td colspan="2">
						${rs.dasfzx}
					</td>
				</tr>
			</logic:equal>
			<%--end长沙民政职业技术学院--%>
			<!--西昌学院-->
			<logic:equal value="10628#" name="xxdm">
				<tr>
					<th>
						饭卡号
					</th>
					<td align="left" colspan="4">
						${rs.kh}
					</td>
				</tr>
			</logic:equal>
			<!--end西昌学院-->
			<%--中国地质大学 --%>
			<logic:equal value="10491" name="xxdm">
				<tr>
					<th>
						特长
					</th>
					<td>
						${rs.tc}
					</td>
					<th>
						考生类别
					</th>
					<td align="left" colspan="2">
						${rs.kslb}
					</td>
				</tr>
				<tr>
					<th>
						入学年级
					</th>
					<td>
						${rs.rxnj}
					</td>
					<th>
						入学方式
					</th>
					<td colspan="2">
						${rs.rxfs}
					</td>
				</tr>
				<tr>
					<th>
						学生类别
					</th>
					<td>
						${rs.xslbmc}
					</td>
					<th>
						学生类型
					</th>
					<td colspan="2">
						${rs.xslxmc}
					</td>
				</tr>
				<tr>
					<th>
						培养方式
					</th>
					<td>
						${rs.pyfs}
					</td>
					<th>
						考生号
					</th>
					<td colspan="2">
						${rs.ksh}
					</td>
				</tr>
				<tr>
					<th>
						毕业中学
					</th>
					<td>
						${rs.rxqdw}
					</td>
					<th>
						异动类别
					</th>
					<td colspan="2">
						${rs.ydlbmc}
					</td>
				</tr>
				<tr>
					<th>
						备注
					</th>
					<td colspan="4" id="bz" class="breakword">
						${rs.bz}
					</td>
				</tr>
			</logic:equal>
			<%--end中国地质大学 --%>
			<%--北京青年政治学院--%>
			<logic:equal value="11626" name="xxdm">
				<tr>
					<th>
						原毕业学院
					</th>
					<td>
						${rs.rxqdw}
					</td>
					<th>
						原毕业学院邮编
					</th>
					<td colspan="2">
						${rs.rxqdwyb}
					</td>
				</tr>
				<tr>
					<th>
						原毕业学院通信地址
					</th>
					<td colspan="4">
						${rs.rxqdwdz}
					</td>
				</tr>
				<tr>
					<th>
						高中阶段表现情况
					</th>
					<td colspan="4">
						${rs.gzbx}
					</td>
				</tr>
			</logic:equal>
			<%--福建工程学院--%>
			<logic:equal value="10388" name="xxdm">
				<tr>
					<th>
						是否港澳台胞
					</th>
					<td>
						${rs.sfgat}
					</td>
					<th>
						是否少数民族
					</th>
					<td colspan="2">
						${rs.sfssmz}
					</td>
				</tr>
				<tr>
					<th>
						高考考生号
					</th>
					<td>
						${rs.ksh}
					</td>
					<th>
						毕业中学
					</th>
					<td colspan="2">
						${rs.rxqdw}
					</td>
				</tr>
			</logic:equal>
			<tr>
				<th>
					户口所在地
				</th>
				<td>
					${rs.hkszd}
				</td>
				<th>是否进修生</th>
				<td colspan="2">
					${rs.sfhq }
				</td>
			</tr>
			<tr>
				<th>
					家庭地址
				</th>
				<td colspan="4">
					${rs.jtszd}
				</td>
			</tr>
			<tr>
				<th>
					家庭电话
				</th>
				<td>
					${rs.jtdh}
				</td>
				<logic:notEqual value="10657" name="xxdm">
					<logic:notEqual name="xxdm" value="11654">
						<th>
							健康状况
						</th>
						<td colspan="2">
							${rs.jkzk }
						</td>
					</logic:notEqual>
					<logic:equal name="xxdm" value="11654">
						<th>
							有无病史
						</th>
						<td colspan="2">
							${rs.jkzk }
						</td>
					</logic:equal>
				</logic:notEqual>
				<logic:equal name="xxdm" value="10657">
					<th></th>
					<td></td>
				</logic:equal>
			</tr>
			<tr>
				<th>
					备注
				</th>
				<td colspan="4" style="word-break:break-all;width:99%">
					${rs.bz}
				</td>
				
			</tr>
		</tbody>
	</table>
</div>
