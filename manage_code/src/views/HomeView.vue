<template>
	<div class="home_view">
		<div class="projectTitle">欢迎使用 {{projectName}}</div>
		<div class="count_list">
		</div>
		<div class="card_list">
			<el-collapse-transition v-if="btnAuth('yuangong','首页统计')">
				<el-card v-show="cardTypeList.closeyuangongChartType1" class="card_view chart-item">
					<template #header>
						<div class="index_card_head">
							<div class="card_head_title">
								员工
							</div>
							<div class="card_head_right">
								<el-icon @click="cardTypeClick('hiddenyuangongChartType1')" class="showIcons"
										 :class="cardTypeList.hiddenyuangongChartType1?'showIcons1':''">
									<ArrowUpBold />
								</el-icon>
								<el-icon @click="cardTypeClick('closeyuangongChartType1')" class="closeIcons">
									<CloseBold />
								</el-icon>
							</div>
						</div>
					</template>
					<el-collapse-transition>
						<div class="card_item" v-show="cardTypeList.hiddenyuangongChartType1">
							<div id="yuangongxingbieEchart1" class="Echart" style="width: 100%;height: 400px;"></div>
						</div>
					</el-collapse-transition>
				</el-card>
			</el-collapse-transition>
			<el-collapse-transition v-if="btnAuth('yaopinxinxi','首页统计')">
				<el-card v-show="cardTypeList.closeyaopinxinxiChartType1" class="card_view chart-item">
					<template #header>
						<div class="index_card_head">
							<div class="card_head_title">
								药品信息
							</div>
							<div class="card_head_right">
								<el-icon @click="cardTypeClick('hiddenyaopinxinxiChartType1')" class="showIcons"
										 :class="cardTypeList.hiddenyaopinxinxiChartType1?'showIcons1':''">
									<ArrowUpBold />
								</el-icon>
								<el-icon @click="cardTypeClick('closeyaopinxinxiChartType1')" class="closeIcons">
									<CloseBold />
								</el-icon>
							</div>
						</div>
					</template>
					<el-collapse-transition>
						<div class="card_item" v-show="cardTypeList.hiddenyaopinxinxiChartType1">
							<div id="yaopinxinxiyaopinleixingEchart1" class="Echart" style="width: 100%;height: 400px;"></div>
						</div>
					</el-collapse-transition>
				</el-card>
			</el-collapse-transition>
			<el-collapse-transition v-if="btnAuth('yaopinxinxi','首页统计')">
				<el-card v-show="cardTypeList.closeyaopinxinxiChartType2" class="card_view chart-item">
					<template #header>
						<div class="index_card_head">
							<div class="card_head_title">
								药品信息
							</div>
							<div class="card_head_right">
								<el-icon @click="cardTypeClick('hiddenyaopinxinxiChartType2')" class="showIcons"
										 :class="cardTypeList.hiddenyaopinxinxiChartType2?'showIcons2':''">
									<ArrowUpBold />
								</el-icon>
								<el-icon @click="cardTypeClick('closeyaopinxinxiChartType2')" class="closeIcons">
									<CloseBold />
								</el-icon>
							</div>
						</div>
					</template>
					<el-collapse-transition>
						<div class="card_item" v-show="cardTypeList.hiddenyaopinxinxiChartType2">
							<div id="yaopinxinxikucunEchart2" class="Echart" style="width: 100%;height: 400px;"></div>
						</div>
					</el-collapse-transition>
				</el-card>
			</el-collapse-transition>
			<el-collapse-transition v-if="btnAuth('yaopindingdan','首页统计')">
				<el-card v-show="cardTypeList.closeyaopindingdanChartType1" class="card_view chart-item">
					<template #header>
						<div class="index_card_head">
							<div class="card_head_title">
								药品订单
							</div>
							<div class="card_head_right">
								<el-icon @click="cardTypeClick('hiddenyaopindingdanChartType1')" class="showIcons"
										 :class="cardTypeList.hiddenyaopindingdanChartType1?'showIcons1':''">
									<ArrowUpBold />
								</el-icon>
								<el-icon @click="cardTypeClick('closeyaopindingdanChartType1')" class="closeIcons">
									<CloseBold />
								</el-icon>
							</div>
						</div>
					</template>
					<el-collapse-transition>
						<div class="card_item" v-show="cardTypeList.hiddenyaopindingdanChartType1">
							<div id="yaopindingdanjineEchart1" class="Echart" style="width: 100%;height: 400px;"></div>
						</div>
					</el-collapse-transition>
				</el-card>
			</el-collapse-transition>
		</div>
	</div>
</template>

<script setup>
	import {
		inject,
		nextTick,
		ref,
		getCurrentInstance
	} from 'vue';
	const context = getCurrentInstance()?.appContext.config.globalProperties;
	const projectName = context.$project.projectName
	//权限验证
	const btnAuth = (e,a)=>{
		return context?.$toolUtil.isAuth(e,a)
	}
	let echarts = inject("echarts")
	const cardTypeClick = (e) =>{
		cardTypeList.value[e] = !cardTypeList.value[e]
		setTimeout(()=>{
			getCardList()
		},1000)
	}
	const cardTypeList = ref({
		closeyuangongChartType1: true,
		hiddenyuangongChartType1: true,
		closeyaopinxinxiChartType1: true,
		hiddenyaopinxinxiChartType1: true,
		closeyaopinxinxiChartType2: true,
		hiddenyaopinxinxiChartType2: true,
		closeyaopindingdanChartType1: true,
		hiddenyaopindingdanChartType1: true,
		closeshouzhimingxiChartType1: true,
		hiddenshouzhimingxiChartType1: true,
	})
	const getCardList = () => {
		if(btnAuth('yuangong','首页统计')){
			getyuangongChart1()
		}
		if(btnAuth('yaopinxinxi','首页统计')){
			getyaopinxinxiChart1()
		}
		if(btnAuth('yaopinxinxi','首页统计')){
			getyaopinxinxiChart2()
		}
		if(btnAuth('yaopindingdan','首页统计')){
			getyaopindingdanChart1()
		}
		if(btnAuth('shouzhimingxi','首页统计')){
			getshouzhimingxiChart1()
		}
	}
	import '@/assets/js/echarts-theme'
	const getyuangongChart1 = () => {
		nextTick(()=>{
			var xingbieEchart1 = echarts.init(document.getElementById("yuangongxingbieEchart1"),'theme');
			context?.$http({
				url: "yuangong/group/xingbie",
				method: "get",
			}).then(obj=>{
				let res = obj.data.data
				let xAxis = [];
				let yAxis = [];
				let dataList = []
				for(let i=0;i<res.length;i++){
				    xAxis.push(res[i].xingbie);
				    yAxis.push(parseFloat((res[i].total)));
					dataList.push({
				        value: parseFloat((res[i].total)),
				        name: res[i].xingbie
				    })
				}
				var option = {};
				option = {
    title:{
        text: '员工统计',
        left: 'center'
    },
    legend: {
        orient: 'vertical',
        left: 'left'
    },
    tooltip: {
        trigger: 'item',
        formatter: '{b} : {c} ({d}%)'
    },
    series: [
        {
            type: 'pie',
            radius: '55%',
            center: ['50%', '60%'],
            data: dataList,
            emphasis: {
                itemStyle: {
                  shadowBlur: 10,
                  shadowOffsetX: 0,
                  shadowColor: 'rgba(0, 0, 0, 0.5)'
                }
            }
        }
    ]
}
				xingbieEchart1.clear()
				// 使用刚指定的配置项和数据显示图表。
				xingbieEchart1.setOption(option);
				//根据窗口的大小变动图表
				xingbieEchart1.resize();
			})
		})
	}
	const getyaopinxinxiChart1 = () => {
		nextTick(()=>{
			var yaopinleixingEchart1 = echarts.init(document.getElementById("yaopinxinxiyaopinleixingEchart1"),'theme');
			context?.$http({
				url: "yaopinxinxi/group/yaopinleixing",
				method: "get",
			}).then(obj=>{
				let res = obj.data.data
				let xAxis = [];
				let yAxis = [];
				let dataList = []
				for(let i=0;i<res.length;i++){
				    xAxis.push(res[i].yaopinleixing);
				    yAxis.push(parseFloat((res[i].total)));
					dataList.push({
				        value: parseFloat((res[i].total)),
				        name: res[i].yaopinleixing
				    })
				}
				var option = {};
				option = {
    title: {
        text: '药品分类统计',
        left: 'center'
    },
    tooltip: {
        trigger: 'item',
        formatter: '{b} : {c}'
    },
    xAxis: {
        data: xAxis,
        type: 'category',
    },
    yAxis: {
        type: 'value'
    },
    series:{
        data: yAxis,
        type: 'bar'
    }
}

				yaopinleixingEchart1.clear()
				// 使用刚指定的配置项和数据显示图表。
				yaopinleixingEchart1.setOption(option);
				//根据窗口的大小变动图表
				yaopinleixingEchart1.resize();
			})
		})
	}
	const getyaopinxinxiChart2 = () => {
		nextTick(()=>{
			var kucunEchart2 = echarts.init(document.getElementById("yaopinxinxikucunEchart2"),'theme');
			context?.$http({
				url: `yaopinxinxi/value/yaopinmingcheng/kucun`,
				method: "get",
			}).then(obj=>{
				let res = obj.data.data
				let xAxis = [];
				let yAxis = [];
				let dataList = []
				for(let i=0;i<res.length;i++){
				    xAxis.push(res[i].yaopinmingcheng);
				    yAxis.push(parseFloat((res[i].total)));
					dataList.push({
				        value: parseFloat((res[i].total)),
				        name: res[i].yaopinmingcheng
				    })
				}
				var option = {};
				option = {
    title: {
        text: '药品库存统计',
        left: 'center'
    },
    tooltip: {
        trigger: 'item',
        formatter: '{b} : {c}'
    },
    xAxis: {
        data: xAxis,
        type: 'category',
    },
    yAxis: {
        type: 'value'
    },
    series:{
        data: yAxis,
        type: 'bar'
    }
}

				kucunEchart2.clear()
				// 使用刚指定的配置项和数据显示图表。
				kucunEchart2.setOption(option);
				//根据窗口的大小变动图表
				kucunEchart2.resize();
			})
		})
	}
	const getyaopindingdanChart1 = () => {
		nextTick(()=>{
			var jineEchart1 = echarts.init(document.getElementById("yaopindingdanjineEchart1"),'theme');
			context?.$http({
				url: `yaopindingdan/value/yaopinmingcheng/jine`,
				method: "get",
			}).then(obj=>{
				let res = obj.data.data
				let xAxis = [];
				let yAxis = [];
				let dataList = []
				for(let i=0;i<res.length;i++){
				    xAxis.push(res[i].yaopinmingcheng);
				    yAxis.push(parseFloat((res[i].total)));
					dataList.push({
				        value: parseFloat((res[i].total)),
				        name: res[i].yaopinmingcheng
				    })
				}
				var option = {};
				option = {
    title: {
        text: '销售额统计',
        left: 'center'
    },
    tooltip: {
        trigger: 'item',
        formatter: '{b} : {c}'
    },
    xAxis: {
        data: xAxis,
        type: 'category',
    },
    yAxis: {
        type: 'value'
    },
    series:{
        data: yAxis,
        type: 'bar'
    }
}

				jineEchart1.clear()
				// 使用刚指定的配置项和数据显示图表。
				jineEchart1.setOption(option);
				//根据窗口的大小变动图表
				jineEchart1.resize();
			})
		})
	}
	const getshouzhimingxiChart1 = () => {
		nextTick(()=>{
		})
	}

	const init=()=>{
		getCardList()
	}
	init()
</script>
<style lang="scss">
	// 首页盒子
	.home_view {
	}
	.home_view .projectTitle{
	}

	.showIcons {
		transition: transform 0.3s;
		margin-right: 10px;
	}

	.showIcons1 {
		transform: rotate(-180deg);
	}
	
	// 总数盒子
	.count_list{
		// 总数card
		.card_view {
			// card头部
			.el-card__header {
				// 头部盒子
				.index_card_head {
					// 标题
					.card_head_title {
					}
					// 按钮盒子
					.card_head_right {
						// 按钮
						.el-icon {
						}
					}
				}
			}
			// body
			.el-card__body {
				// body盒子
				.count_item{
					// 总数标题
					.count_title{
					}
					// 总数数字
					.count_num{
					}
				}
			}
		}
	}

	// 统计图盒子
	.card_list {
		// 统计图card
		.card_view {
			// 头部
			.el-card__header {
				// 头部盒子
				.index_card_head {
					// 标题
					.card_head_title {
					}
					// 按钮盒子
					.card_head_right {
						// 按钮
						.el-icon{
						}
					}
				}
			}
			// body
			.el-card__body {
				// body盒子
				.card_item{
				}
			}
		}
	}
</style>
<style>
/*总盒子*/
.home_view{
    padding: 20px 20px;
    margin: 0px;
    height: auto;
    min-height: 100vh;
    width: 100%;
}
.home_view .projectTitle{
    width: 100%;
    font-size: 28px;
    font-weight: bold;
    padding: 40px 0px 20px;
    height: auto;
    display: flex;
    align-items: center;
    justify-content: center;
    margin: 0px 0px 20px;
    color: #000;
    display: none;
}

/*总数*/
/*总盒子*/
.home_view .count_list {
    display:flex;
    flex-wrap:wrap;
    justify-content:center;
    padding:0px 0px 20px;
    width:100%;
    align-items:flex-start;
    column-gap: 30px;
}
/*卡片盒子*/
.home_view .count_list .card_view {
    width:23%;
    height:auto;
    margin: 0;
    box-sizing:border-box;
    border:0px;
    border-radius:0px;
    box-shadow:0 2px 5px 0 rgba(0,0,0,0.16),0 2px 10px 0 rgba(0,0,0,0.12);
    flex: 1;
    text-align: center;
}

/*head 总盒子*/
.home_view .count_list .card_view .el-card__header{
     width: 100%;
    border: 0px solid rgb(238, 238, 238);
    display: none;
}
/*item*/
.home_view .count_list .card_view .el-card__header .index_card_head{
    display: flex;
    align-items: center;
    justify-content: space-between;
    width: 100%;
    height: 10px;
}
/*标题*/
.home_view .count_list .card_view .el-card__header .index_card_head .card_head_title{
    font-size: 14px;
    color: rgb(51, 51, 51);
}
/*按钮盒子*/
.home_view .count_list .card_view .el-card__header .index_card_head .card_head_right{
    display: flex;
    align-items: center;
}
/*按钮*/
.home_view .count_list .card_view .el-card__header .index_card_head .card_head_right .showIcons{
    color: rgb(102, 102, 102);
    font-size: 20px;
    cursor: pointer;
}
/*body 总盒子*/
.home_view .count_list .card_view .el-card__body{
    padding: 0px;
    background: none;
}
/*item*/
.home_view .count_list .card_view .el-card__body .count_item{
    padding: 20px;
    display: flex;
    flex-direction: column;
    text-align: center;
}
/*标题*/
.home_view .count_list .card_view .el-card__body .count_item .count_title{
    font-size: 14px;
    color: #000;
    line-height: 2;
    display: inline-block;
    margin: 0px 10px 0px 0px;
    font-weight: 400;
}
/*数字*/
.home_view .count_list .card_view .el-card__body .count_item .count_num{
    font-size: 26px;
    color: #000;
    line-height: 2;
    display: inline-block;
    font-weight: 600;
}

/*图表*/
/*总盒子*/
.home_view .card_list{
    width: 100%;
    display: flex;
    flex-wrap: wrap;
    padding: 0px 0px 20px;
    align-items: flex-start;
    column-gap: 30px;
    row-gap: 20px;
}
/*卡片 总盒子*/
.home_view .card_list .card_view{
    min-width: 48%;
    flex:1;
    height: auto;
    margin: 0;
    box-sizing: border-box;
    border: 0px;
    border-radius: 0px;
    padding: 20px 0px 0px;
}
/*head 总盒子*/
.home_view .card_list .card_view .el-card__header{
    width: 100%;
    border: 0px solid rgb(238, 238, 238);
    background: rgb(255, 255, 255);
    display: none;
}
/*item*/
.home_view .card_list .card_view .el-card__header .index_card_head{
    display: flex;
    align-items: center;
    justify-content: space-between;
    width: 100%;
    height: 10px;
}
/*标题*/
.home_view .card_list .card_view .el-card__header .index_card_head .card_head_title{
}
/*按钮盒子*/
.home_view .card_list .card_view .el-card__header .index_card_head .card_head_right{
    display: none;
}
/*按钮*/
.home_view .card_list .card_view .el-card__header .index_card_head .card_head_right .showIcons{
    color: rgb(238, 238, 238);
    font-size: 20px;
    cursor: pointer;
}

/*body 总盒子*/
.home_view .card_list .card_view .el-card__body{
    padding: 0px;
    background: rgb(255, 255, 255);
}
.home_view .card_list .card_view .el-card__body .card_item{
    padding: 10px;
    text-align: center;
}

.home-calendar{
  border: 0px solid rgb(218, 218, 218);
  box-shadow: rgba(0, 0, 0, 0.1) 0px 0px 0px;
  margin: 0px auto 30px;
  border-radius: 0px;
  color: rgb(102, 102, 102);
  background: #fff;
  width: 100%;
  font-size: inherit;
  height: auto;
  padding:0 0 10px;
  order:2;
}
.home-calendar .header{
  padding: 17px 40px;
  border-color: rgba(126, 96, 16, 0.1);
  flex-wrap: wrap;
  background: #fff;
  border-width: 0px 0px 1px;
  display: flex;
  width: 100%;
  border-style: solid;
  justify-content: space-between;
  height: auto;
}
.home-calendar .header .btn{
  cursor: pointer;
  border: 0px solid rgb(153, 153, 153);
  padding: 0px 0px 0px 12px;
  align-items: center;
  color: var(--theme);
  border-radius: 4px;
  display: flex;
  width: auto;
  font-size: 16px;
  justify-content: center;
}
.home-calendar .header .title{
  padding: 0px 10px;
  font-size: 24px;
  align-items: center;
  justify-content: center;
  display: flex;
  color: var(--theme);
}
.home-calendar table{
  width: 100%;
  padding: 0px 0px 20px;
  height: auto;
}
.home-calendar tbody,thead{
  width: 100%;
}
.home-calendar tr{
  width: 100%;
  align-items: center;
  justify-content: center;
  display: flex;
}
.home-calendar th{
  align-items: center;
  flex: 1;
  display: flex;
  line-height: 50px;
  justify-content: center;
}
.home-calendar td{
  cursor: pointer;
  padding: 6px 12px 6px;
  flex: 1;
  display: flex;
  justify-content: center;
  text-align: center;
}
.home-calendar td.today .text{
  width: 80%;
  height: 80%;
  padding:5px 0;
  background: rgb(255 166 106 / 10%);
  color: var(--theme);
  border-radius:0px;
}
.home-calendar td.festival .text{
  width: 80%;
  height: 80%;
  padding:5px 0;
  background: #2f3f5610;
  border-radius:0px;
}

</style>
