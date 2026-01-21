// pages/edit/edit.js

const {
detail,
option,
update,
add,
list,
follow,
faceMatch,
session,
rubbish,
levelOption,
baiduIdentify
} = require("../../api/index.js")

const des = require('../../utils/des.js')
const utils = require("../../utils/index.js")

Page({

/**
* 页面的初始数据
*/
data: {
imgIcon: "../../static/upload.png",
editStatus: false,
baseURL:'',
sessionReadArr:[],

detailList: null,
id: "",
cross:"",
ruleForm:{},
userid:getApp().globalData.userInfo.id,
userInfo:getApp().globalData.userInfo,
ro:{
},

dingdanbianhao:"",
yaopinmingcheng:"",
jiage:0,
kucun:0,
yaopintupian:"",
yaopinleixingList:"${column.customize}".split(','),
yaopinleixingIndex:0,
guige:"",
goumairenxingming:"",
shoujihao:"",
shenfenzhenghao:"",
yuangongzhanghao:"",
yuangongxingming:"",
},


/**
* 生命周期函数--监听页面加载
*/
async onLoad(options) {
let userid
if (options?.id) {
this.setData({
editStatus: true
})

}
let nowTable = wx.getStorageSync("nowTable");
const res = await session(nowTable)
if(res.data.code==0){
getApp().globalData.userInfo=res?.data
userid = res?.data.id
this.setData({
userid
})

}
let baseURL =wx.getStorageSync('baseURL') + '/'
const id = getApp().globalData.detailId
this.setData({
refid:id,
baseURL
})
//人脸识别
const yaopinleixingres = await option('yaopinleixing/yaopinleixing')
yaopinleixingres.data.unshift('请选择药品类型')
this.setData({
yaopinleixingList: yaopinleixingres.data
})
let  ro=this.data.ro
if(options?.cross){
var obj = wx.getStorageSync('crossObj');
let refobjempty={}
for (var o in obj){
if(o=='dingdanbianhao'){
    refobjempty["dingdanbianhao"]=obj[o]
ro.dingdanbianhao = true;
continue;
}else{
    refobjempty["dingdanbianhao"]=this.getUUID()
}

if(o=='yaopinmingcheng'){
    refobjempty["yaopinmingcheng"]=obj[o]
ro.yaopinmingcheng = true;
continue;
}else{
}

if(o=='jiage'){
    refobjempty["jiage"]=obj[o]
ro.jiage = true;
continue;
}else{
}

if(o=='kucun'){
    refobjempty["kucun"]=obj[o]
ro.kucun = true;
continue;
}else{
}

if(o=='jine'){
    refobjempty["jine"]=obj[o]
ro.jine = true;
continue;
}else{
}

if(o=='yaopintupian'){
 refobjempty['yaopintupian']=obj[o];
 refobjempty['yaopintupianPath']=baseURL+ obj[o].split(",")[0];
ro.yaopintupian = true;
continue;
}else{
}

if(o=='yaopinleixing'){
    refobjempty["yaopinleixing"]=obj[o]
ro.yaopinleixing = true;
continue;
}else{
}

if(o=='guige'){
    refobjempty["guige"]=obj[o]
ro.guige = true;
continue;
}else{
}

if(o=='goumairenxingming'){
    refobjempty["goumairenxingming"]=obj[o]
ro.goumairenxingming = true;
continue;
}else{
}

if(o=='shoujihao'){
    refobjempty["shoujihao"]=obj[o]
ro.shoujihao = true;
continue;
}else{
}

if(o=='shenfenzhenghao'){
    refobjempty["shenfenzhenghao"]=obj[o]
ro.shenfenzhenghao = true;
continue;
}else{
}

if(o=='yuangongzhanghao'){
    refobjempty["yuangongzhanghao"]=obj[o]
ro.yuangongzhanghao = true;
continue;
}else{
}

if(o=='yuangongxingming'){
    refobjempty["yuangongxingming"]=obj[o]
ro.yuangongxingming = true;
continue;
}else{
}

}

let  statusColumnName=wx.getStorageSync('statusColumnName');
statusColumnName=statusColumnName.replace('[',"").replace(']',"");
this.setData({
ro,
cross:options?.cross,
statusColumnName
})
this.setData(refobjempty)
}
if(id && !options?.cross){
// 如果上一级页面传递了id，获取改id数据信息
const   data=getApp().globalData.detailList
yaopinleixingres.data.map((item, index) => {
if (item == data.yaopinleixing) {
 this.setData({
yaopinleixingIndex: index,
yaopinleixing: item
 })
}else if (this.data.yaopinleixingList.length == 1) {
this.setData({
yaopinleixingIndex: 0,
yaopinleixing: v
 })
}
})
const url = wx.getStorageSync("baseURL") + "/"
const detailList = data
let  objtemp= {
detailList,
'ro.dingdanbianhao':true,
 dingdanbianhao: data.dingdanbianhao ? data.dingdanbianhao  : this.getUUID(),
 yaopinmingcheng: data.yaopinmingcheng,
 jiage: data.jiage,
 kucun: data.kucun,
 jine: data.jine,
 yaopintupian:data?.yaopintupian?.split(',')[0],
 yaopintupianPath:baseURL+data?.yaopintupian?.split(',')[0],
 yaopinleixing:this.data.yaopinleixingList[this.data.yaopinleixingIndex],
 guige: data.guige,
 goumairenxingming: data.goumairenxingming,
 shoujihao: data.shoujihao,
 shenfenzhenghao: data.shenfenzhenghao,
 yuangongzhanghao: data.yuangongzhanghao,
 yuangongxingming: data.yuangongxingming,
}
this.setData(objtemp);

//ss读取
let h = this.data
let c = this.data
let d1 = this.data
let d2 = this.data
this.setData({
 jine: (c.jiage*c.kucun).toFixed(2),
});
}else {
this.setData({
dingdanbianhao: this.getUUID(),
})
}
// ss读取
let sessionReadArr=[]
if(getApp().globalData.userInfo.yuangongzhanghao){
let yuangongzhanghao= getApp().globalData.userInfo.yuangongzhanghao
ro.yuangongzhanghao=true
this.setData({
  yuangongzhanghao,
})
sessionReadArr.push('yuangongzhanghao')

}
if(getApp().globalData.userInfo.yuangongxingming){
let yuangongxingming= getApp().globalData.userInfo.yuangongxingming
ro.yuangongxingming=true
this.setData({
  yuangongxingming,
})
sessionReadArr.push('yuangongxingming')

}
this.setData({
cross:options?.cross,
ro,
id,
})

if (wx.getStorageSync('raffleType') ) {
 wx.removeStorageSync('raffleType')
 setTimeout(() => {
  this.submit()
 }, 300)
}
this.jine()













},
getUUID () {
return new Date().getTime();
},
onUnload: function () {
},
onShow() {

this.jine()
},
 commentScore(e){
 if(this.data?.commentScore){
  this.setData({
   commentScore:e.detail
  })
 }
 },


jine() {

//`c.jiage*c.kucun`
let h = this.data
let c = this.data
let d1=this.data
let d2=this.data
let a = c.jiage*c.kucun
this.setData({
jine : a.toFixed(2)=='NaN'?0:a.toFixed(2)
})
},





//yaopinleixing, 0
// 下拉变化
yaopinleixingChange(e) {
this.setData({
 yaopinleixingIndex:   e.detail.value,
 yaopinleixing :this.data.yaopinleixingList[e.detail.value]
})
},

































uploadyaopintupian() {
wx.chooseImage({
count: 1,
sizeType: ['compressed'],
sourceType: ['album', 'camera'],
success: async (res) => {
const tempFilePaths = res.tempFilePaths;
// 本地临时图片的路径
this.setData({
 yaopintupianPath: tempFilePaths[0]
})
let _this = this;
// 上传网络图片
const baseURL = wx.getStorageSync('baseURL')
wx.uploadFile({
url: `${baseURL}/file/upload`,
filePath: res.tempFilePaths[0],
name: 'file',
header: {
 'Token': wx.getStorageSync('token')
},
success: (uploadFileRes) => {
 let result = JSON.parse(uploadFileRes.data);
// result.file是上传成功为网络图片的名称
 if (result.code == 0) {
  this.setData({
    yaopintupian: 'file/' + result.file
  })
 } else {
  wx.showToast({
   title: result.msg,
   icon: 'none',
   duration: 2000
  });
 }
}
})

this.setData({
face: tempFilePaths[0]
});

}
})
},


































async submit() {
let that = this
var query = wx.createSelectorQuery();

const baseURL = wx.getStorageSync('baseURL') + "/"
const regex = new RegExp(baseURL, "g");
const obj={
dingdanbianhao: this.data. dingdanbianhao,
yaopinmingcheng: this.data. yaopinmingcheng,
jiage: this.data. jiage,
kucun: this.data. kucun,
jine: this.data. jine,
yaopintupian:this.data.yaopintupian?.replace(regex, ""),
yaopinleixing:this.data.yaopinleixing?this.data.yaopinleixing:this.data.yaopinleixingList[this.data.yaopinleixingIndex],
guige: this.data. guige,
goumairenxingming: this.data. goumairenxingming,
shoujihao: this.data. shoujihao,
shenfenzhenghao: this.data. shenfenzhenghao,
yuangongzhanghao: this.data. yuangongzhanghao,
yuangongxingming: this.data. yuangongxingming,
}
const detailId= getApp().globalData.detailId
const tableName= `yaopindingdan`
//跨表计算判断
var obj2;
var  ruleForm=obj
obj2 = ruleForm
this.data.refid==""? ruleForm['refid']= getApp().globalData.detailId:""
ruleForm['userid']=getApp().globalData.userInfo.id;
var userInfo=getApp().globalData.userInfo
const sessionReadArr=this.data.sessionReadArr
const phonePattern = /^\(?\d{3}\)?[-.\s]?\d{3}[-.\s]?\d{4}$/;
const mobilePattern = /^(?:\+?86)?1[3-9]\d{9}$/;
const emailPattern = /^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$/;
const idPattern = /^[1-9]\d{5}(18|19|20)\d{2}(0[1-9]|1[0-2])(0[1-9]|[1-2]\d|3[0-1])\d{3}[\dxX]$/;
const urlPattern = /^(http|https):\/\/[\w\-]+(\.[\w\-]+)+[/#?]?.*$/;




if(!this.data.yaopinmingcheng ){
wx.showToast({
icon: "none",
title: `药品名称不能为空`,
})
return
}




const valuejiage = this.data.jiage;
if(isNaN(valuejiage)  && this.data.jiage){
wx.showToast({
icon: "none",
title: `价格应输入数字`,
})
return
}







const valuekucun = this.data.kucun;
const isIntegerkucun = /^[-+]?\d+$/.test(valuekucun);
if(!isIntegerkucun && this.data.kucun){
wx.showToast({
icon: "none",
title: `数量应输入整数`,
})
return
}





const valuejine = this.data.jine;
if(isNaN(valuejine)  && this.data.jine){
wx.showToast({
icon: "none",
title: `金额应输入数字`,
})
return
}











if(!this.data.yaopinleixing ){
wx.showToast({
icon: "none",
title: `药品类型不能为空`,
})
return
}




if(this.data.yaopinleixingList[this.data.yaopinleixingIndex]==undefined && !this.data.yaopinleixing ){
wx.showToast({
icon: "none",
title: `药品类型不能为空`,
})
return
}




if(!this.data.goumairenxingming ){
wx.showToast({
icon: "none",
title: `购买人姓名不能为空`,
})
return
}




if(!this.data.shoujihao ){
wx.showToast({
icon: "none",
title: `手机号不能为空`,
})
return
}

const valueshoujihao = this.data.shoujihao;

if (!mobilePattern.test(valueshoujihao)) {
wx.showToast({
icon: "none",
title: `手机号应输入手机格式`,
})
return
}



if(!this.data.shenfenzhenghao ){
wx.showToast({
icon: "none",
title: `身份证号不能为空`,
})
return
}

const valueshenfenzhenghao = this.data.shenfenzhenghao;
if (!idPattern.test(valueshenfenzhenghao)) {
wx.showToast({
icon: "none",
title: `身份证号应输入身份证格式`,
})
return
}











//更新跨表属性
var crossuserid;
var crossrefid;
var crossoptnum;
if(this.data.cross) {
wx.setStorageSync('crossCleanType', true);
var statusColumnName = wx.getStorageSync('statusColumnName');
var statusColumnValue = wx.getStorageSync('statusColumnValue');
if (statusColumnName != '') {
obj2 = wx.getStorageSync('crossObj');
if (!statusColumnName.startsWith("[")) {
for (var o in obj2) {
if (statusColumnName==o){
obj2[o] = statusColumnValue;
}

}
var table = wx.getStorageSync('crossTable');

await update(table, obj2)
} else {

crossuserid =getApp().globalData.userInfo.id
crossrefid =  this.data.id
crossoptnum = wx.getStorageSync('statusColumnName');
crossoptnum = crossoptnum.replace(/\[/, "").replace(/\]/, "");
}
}
}
this.data.cross ? (crossrefid = obj2.id, crossuserid =getApp().globalData.userInfo.id) : ""
ruleForm?.crossrefid==undefined? ( ruleForm["crossrefid"] = obj2.id, ruleForm["crossuserid"] =getApp().globalData.userInfo.id ): "";
ruleForm?.shhf?ruleForm.shhf=this.data.shhf:''
if(crossrefid && crossuserid) {
ruleForm['crossuserid'] =getApp().globalData.userInfo.id;
ruleForm['crossrefid'] =obj2.id;
this.setData({
ruleForm
})
let params = {
page: 1,
limit: 10,
crossuserid: crossuserid,
crossrefid: crossrefid,
}
const tips = wx.getStorageSync('tips')
let corssRes = await list(`yaopindingdan`, params)
crossoptnum = wx.getStorageSync('statusColumnName');
crossoptnum = crossoptnum.match(/\d+/g);
if (corssRes?.data?.total >= parseInt(crossoptnum)) {
wx.showToast({
icon: "none",
title: tips,
})
wx.removeStorageSync('crossCleanType');
return ;
}
else {
//跨表计算
if (ruleForm.id ) {
await update(`yaopindingdan`, ruleForm)
}
else {
 this.data?.commentScore?ruleForm['score']= this.data.commentScore:'';
 await add(`yaopindingdan`, ruleForm)
}
}
}
else {
//跨表计算
if (ruleForm.id || this.data.editStatus) {
this.data.editStatus?ruleForm['id']= getApp().globalData.detailId:""
await update(`yaopindingdan`, ruleForm)
}
else {
 this.data?.commentScore?ruleForm['score']= this.data.commentScore:'';
await add(`yaopindingdan`, ruleForm)
}
}
getApp().globalData.editorContent=''
wx.showToast({
title: '提交成功',
icon: "none"
})
const preId = getApp().globalData.detailId

if(table){
let res = await detail(table, preId)
if(res.code==0){
getApp().globalData.detailList = res.data
}

}
wx.navigateBack({
delta: 1,
complete: () => {
// 触发事件通知，传递需要更新的数据
const pages = getCurrentPages();
if (pages.length >= 1) {
const prePage = pages[pages.length - 2];
prePage.onLoad(); //
}
}
})

},
onHide() {

},


/**
* 生命周期函数--监听页面卸载
*/
onUnload() {

},


/**
* 页面相关事件处理函数--监听用户下拉动作
*/
onPullDownRefresh() {

},

/**
* 页面上拉触底事件的处理函数
*/
onReachBottom() {

},

/**
* 用户点击右上角分享
*/
onShareAppMessage() {

}
})