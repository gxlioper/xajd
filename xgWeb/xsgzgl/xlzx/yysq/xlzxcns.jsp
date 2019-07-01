<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="/syscommon/head.ini"%>
	<script language="javascript" src="js/check.js"></script>
	<script type="text/javascript" src="js/calendar/calendar.js"></script>
	<script type="text/javascript">
		function zwpg(){
			jQuery("#cns").hide();
			jQuery("#zwpg").show();
		}
		function saveQ(){
			add();
			showDialog("申请预约",750,530,"xlzx_zxspb.do?method=zxsPbbForXs&zxzt="+zxzt);
			Close();
		}
		function save(){
			if(jQuery("input[name='zxzt_ckb'][value=9]").is(":checked")  && jQuery("#qt").val()==""){
				showAlert("请填写咨询主题！");
				return false;
			}
			 var parameter ={};
			var zxzts="";
			jQuery("input[name='zxzt_ckb']:checked").each(function(index){
				zxzts += jQuery(this).val()+",";
			});
			var zxzt = zxzts.substring(0,zxzts.length-1);
			parameter["zxjy"]=jQuery("input[name='zxjy']:checked").val();
			
			parameter["jsyw"]=jQuery("input[name='jsyw']:checked").val();

			parameter["zeg"]=jQuery("#zeg").val();
			
			parameter["rybs"]=jQuery("#rybs").val();
			
			parameter["smbh"]=jQuery("#smbh").val();
			
			parameter["sqqq"]=jQuery("#sqqq").val();
			
			parameter["cdxbh"]=jQuery("#cdxbh").val();
			
			parameter["stwl"]=jQuery("#stwl").val();
			
			parameter["zwpp"]=jQuery("#zwpp").val();
			
			parameter["zwjz"]=jQuery("#zwjz").val();

			parameter["rypy"]=jQuery("#rypy").val();

			parameter["zsnt"]=jQuery("#zsnt").val();

			parameter["qt"]=jQuery("#qt").val();

			parameter["zxzt"]=zxzt;

			var url = "xlzx_zwpg.do?method=add"	;			
			jQuery.post(url,parameter,
					function(result){
						if(result=="true"){
							showDialog("申请预约",750,530,"xlzx_zxspb.do?method=zxsPbbForXs&zxzt="+zxzt);
							Close();
						}else{
							showAlert("保存失败！");
							return false;
						}
					}
				);
		}
		jQuery(function(){
			var zxjy = "${map.zxjy}" == "有" ? "1" : "0";
			var jsyw = "${map.jsyw}" == "有" ? "1" : "0";
			jQuery("#jsyw"+jsyw).attr("checked","checked"); 
			jQuery("#zxjy"+zxjy).attr("checked","checked"); 

			var zxzt = "${map.zxzt}";
			var zxztlist = zxzt.split(",");
			for(var i = 0;i<zxztlist.length;i++){
				jQuery("input[name='zxzt_ckb'][value="+zxztlist[i]+"]").attr("checked","checked")
			}


			if(jQuery("input[name='zxzt_ckb'][value=其他]").is(":checked")) 
				jQuery("#qt").show();
		});
		function hide(object){
			var rs = jQuery(object).is(":checked");
			if(rs) {
				jQuery("#qt").show();
			}else{
				jQuery("#qt").val("");
				jQuery("#qt").hide();
			}
			
		}
	</script>
	<style>
		h1{font-size: 18px;margin-top: 5px;}
		.concent{
			width: 96%;
			margin: 0 auto;
		}
		.concent p {
			font-size: 16px;
		}
		.btn{
			width:124px;
			margin: 20px auto 0;
		}
		.formlist th{
			width: 27.5%;
		}
		select{
			width: 80px;
		}
	</style>
</head>

<body style="width: 99%">
	
		<div id="cns" class="concent">
			<h1 align="center" >《心理咨询服务承诺书》</h1>
			<p>咨询师向您慎重承诺：</p>
			<p>1. 一切咨询工作以保护来访者、促进来访者成长为出发点。</p>
			<p>2. 尊重来访者自愿接受服务的权利。</p>
			<p>3. 尊重来访者选择咨询师的权利。</p>
			<p>4. 尊重来访者的宗教信仰、价值观。</p>
			<p>5. 不滥用心理测试，科学、客观的使用测试结果。</p>
			<p>6. 严格遵守保密原则，具体如下：</p>
			<p> （1）对咨询中的有关信息严格保密，包括会谈记录、测试资料、信件、录音、录像、日记等；</p>
			<p> （2）以上资料均不列入学校其他资料之中；</p>
			<p> （3）发现来访者危害自身或他人的情况下，应在保证来访者与他人的人身安全的前提下，将有关信息暴露程度限制在最低范围内，调动一切资源，帮助来访者度过危机</p>
			<p> </p>
			<p>来访者郑重承诺：</p>
			<p>1. 积极配合咨询工作如实地向咨询师介绍自己的相关情况。</p>
			<p>2. 遵守咨询时间，不迟到，如故不能前来咨询应提前一天通知值班助理。</p>
			<p>3. 若同期接受其他咨询师的咨询服务时，应向咨询师说明情况。</p>
			<p>4. 每次咨询时间为50分钟。一般情况下心理咨询不能一次就彻底解决问题或者取得很明显的效果，它必须是一个持续的过程，来访者须对心理咨询有足够的耐心与信心，这是心理咨询获得成功的关键。</p>
			<p>5. 如果对咨询效果不满意，可当面指出，也可要求更换咨询师，办理转介手续。</p>
			<p>6. 成功的心理咨询需要当事人与咨询师的直接交流，因此请不要代替别人咨询。</p>
			<p>7. 如不愿意继续接受咨询，向咨询师提出结束咨询后终止咨询。</p>
			<div class="btn">
				<button id="buttonSave" type="button" onclick="zwpg();return false;">
					确 定
				</button>
				<button type="button" onclick="Close();return false;">
					关 闭
				</button>
			</div>
		</div>
	<html:form action="/xlzx_zwpg" method="post">
		<div class="concent" id="zwpg" style="display: none" >
			<h1 align="center" >《自我评估》</h1>
			<table width="100%" border="0" class="formlist">
				<tr>
					<th style="text-align: left;">1、有无心理咨询经验</th>
					<td>
					<input type="radio" name="zxjy" value="有" id="zxjy1"/><span>有</span>
					<input type="radio" name="zxjy" value="无" id="zxjy0"/><span>无</span>
					</td>
				</tr>
				<tr>
					<th style="text-align: left;">2、曾经或现在是否服用精神药物</th>
					<td>
						<input type="radio" name="jsyw" value="是" id="jsyw1"/><span>是</span>
						<input type="radio" name="jsyw" value="否" id="jsyw0"/><span>否</span>
					</td>
				</tr>
				<tr>
					<td colspan="2"  style="text-align: left;">3、为了了解您目前的状况，更好地展开咨询，请您根据最近两个星期来（包括今天）所感受的情况和想法来填写下列题目：
						<br />&nbsp;&nbsp;&nbsp;<span style="color:red"><b>0  不符合   ；1 有一点符合 ；2 有一半符合 ； 3 完全符合</b></span>
					</td>
				</tr>
				<tr>
					<th>我最近会容易悲伤哭泣</th>
					<td>
						<html:select property="rybs" styleId="rybs" value="${map.rybs}">
							<html:option value="" ></html:option>
							<html:option value="不符合">0</html:option>
							<html:option value="有一点符合">1</html:option>
							<html:option value="有一半符合">2</html:option>
							<html:option value="完全符合">3</html:option>
						</html:select>
					</td>
				</tr>
				<tr>
					<th>我最近睡的变少或变多</th>
					<td>
						<html:select property="smbh" styleId="smbh" value="${map.smbh}">
							<html:option value="" ></html:option>
							<html:option value="不符合">0</html:option>
							<html:option value="有一点符合">1</html:option>
							<html:option value="有一半符合">2</html:option>
							<html:option value="完全符合">3</html:option>
						</html:select>
					</td>
				</tr>
				<tr>
					<th>我最近对很多事情失去情趣</th>
					<td>
						<html:select property="sqqq" styleId="sqqq" value="${map.sqqq}">
							<html:option value="" ></html:option>
							<html:option value="不符合">0</html:option>
							<html:option value="有一点符合">1</html:option>
							<html:option value="有一半符合">2</html:option>
							<html:option value="完全符合">3</html:option>
						</html:select>
					</td>
				</tr>
				<tr>
					<th>我最近吃的东西变多或变少</th>
					<td>
						<html:select property="cdxbh" styleId="cdxbh" value="${map.cdxbh}">
							<html:option value="" ></html:option>
							<html:option value="不符合">0</html:option>
							<html:option value="有一点符合">1</html:option>
							<html:option value="有一半符合">2</html:option>
							<html:option value="完全符合">3</html:option>
						</html:select>
					</td>
				</tr>
				<tr>
					<th>我最近常有罪恶感</th>
					<td>
						<html:select property="zeg" styleId="zeg" value="${map.zeg}">
							<html:option value="" ></html:option>
							<html:option value="不符合">0</html:option>
							<html:option value="有一点符合">1</html:option>
							<html:option value="有一半符合">2</html:option>
							<html:option value="完全符合">3</html:option>
						</html:select>
					</td>
				</tr>
				<tr>
					<th>我觉得身体无力</th>
					<td>
						<html:select property="stwl" styleId="stwl" value="${map.stwl}">
							<html:option value="" ></html:option>
							<html:option value="不符合">0</html:option>
							<html:option value="有一点符合">1</html:option>
							<html:option value="有一半符合">2</html:option>
							<html:option value="完全符合">3</html:option>
						</html:select>
					</td>
				</tr>
				<tr>
					<th>我最近容易自我批评</th>
					<td>
						<html:select property="zwpp" styleId="zwpp" value="${map.zwpp}">
							<html:option value="" ></html:option>
							<html:option value="不符合">0</html:option>
							<html:option value="有一点符合">1</html:option>
							<html:option value="有一半符合">2</html:option>
							<html:option value="完全符合">3</html:option>
						</html:select>
					</td>
				</tr>
				<tr>
					<th>我觉得自己没有价值</th>
					<td>
						<html:select property="zwjz" styleId="zwjz" value="${map.zwjz}">
							<html:option value="" ></html:option>
							<html:option value="不符合">0</html:option>
							<html:option value="有一点符合">1</html:option>
							<html:option value="有一半符合">2</html:option>
							<html:option value="完全符合">3</html:option>
						</html:select>
					</td>
				</tr>
				<tr>
					<th>我最近变得容易疲倦</th>
					<td>
						<html:select property="rypy" styleId="rypy" value="${map.rypy}">
							<html:option value="" ></html:option>
							<html:option value="不符合">0</html:option>
							<html:option value="有一点符合">1</html:option>
							<html:option value="有一半符合">2</html:option>
							<html:option value="完全符合">3</html:option>
						</html:select>
					</td>
				</tr>
				<tr>
					<th>我有想自杀的念头</th>
					<td>
						<html:select property="zsnt" styleId="zsnt" value="${map.zsnt}">
							<html:option value="" ></html:option>
							<html:option value="不符合">0</html:option>
							<html:option value="有一点符合">1</html:option>
							<html:option value="有一半符合">2</html:option>
							<html:option value="完全符合">3</html:option>
						</html:select>
					</td>
				</tr>
				<tr>
					<th style="text-align: left;">4、咨询主题可选，可多选</th>
					<td>
						<label><input type="checkbox" name="zxzt_ckb" value="自我探索" /><span>自我探索</span></label>
						<label><input type="checkbox" name="zxzt_ckb" value="学习" /><span>学习</span></label>
						<label><input type="checkbox" name="zxzt_ckb" value="感情" /><span>感情</span></label>
						<label><input type="checkbox" name="zxzt_ckb" value="人际" /><span>人际</span></label>
						<label><input type="checkbox" name="zxzt_ckb" value="生涯规划" /><span>生涯规划</span></label>
						<label><input type="checkbox" name="zxzt_ckb" value="情绪" /><span>情绪</span></label>
						<label><input type="checkbox" name="zxzt_ckb" value="家庭" /><span>家庭</span></label>
						<label><input type="checkbox" name="zxzt_ckb" value="身心不适" /><span>身心不适</span></label><br />
						<label><input type="checkbox" name="zxzt_ckb" value="其他" onclick="hide(this);" /><span>其他</span><span style="color:red;">（限500字）</span></label><br />
						<html:textarea property="qt" styleId="qt" onblur="checkLen(this,500)" style="width: 500px;display:none;" value="${map.qt}"></html:textarea>
						<input type="hidden" name="zxzt" id="zxzt"  value="${zxzt}"/>
					</td>
				</tr>
				
			</table>
			<div class="btn">
			<button id="buttonSave" type="button" onclick="save();return false;">
				确 定
			</button>
			<button type="button" onclick="Close();return false;">
				关 闭
			</button>
		</div>
		</div>
	</html:form>
</body>
	
</html>
