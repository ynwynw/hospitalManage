<template>
  <div>
    <el-dialog
      modal-class="edit_form_modal"
      class="edit_form"
      v-model="formVisible"
      :title="formTitle"
      width="80%"
      destroy-on-close
      :fullscreen="false"
    >
      <el-form
        class="formModel_form"
        ref="formRef"
        :model="form"
        :rules="rules"
      >
        <el-row>
          <el-col :span="12">
            <el-form-item label="药品编号" prop="yaopinbianhao">
              <el-input
                class="list_inp"
                v-model="form.yaopinbianhao"
                :readonly="true"
                placeholder="药品编号"
              />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="药品名称" prop="yaopinmingcheng">
              <el-input
                class="list_inp"
                v-model="form.yaopinmingcheng"
                placeholder="药品名称"
                type="text"
                :readonly="
                  !isAdd || disabledForm.yaopinmingcheng ? true : false
                "
              />
            </el-form-item>
          </el-col>

          <el-col :span="12">
            <el-form-item label="价格" prop="jiage">
              <el-input
                class="list_inp"
                v-model.number="form.jiage"
                placeholder="价格"
                type="number"
                :readonly="!isAdd || disabledForm.jiage ? true : false"
              />
            </el-form-item>
          </el-col>

          <el-col :span="12">
            <el-form-item label="数量" prop="kucun">
              <el-input
                class="list_inp"
                v-model.number="form.kucun"
                placeholder="数量"
                type="text"
                :readonly="!isAdd || disabledForm.kucun ? true : false"
              />
            </el-form-item>
          </el-col>

          <el-col :span="12">
            <el-form-item prop="yaopintupian" label="药品图片">
              <uploads
                :disabled="!isAdd || disabledForm.yaopintupian ? true : false"
                action="file/upload"
                tip="请上传药品图片"
                :limit="3"
                style="width: 100%; text-align: left"
                :fileUrls="form.yaopintupian ? form.yaopintupian : ''"
                @change="yaopintupianUploadSuccess"
              >
              </uploads>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="药品类型" prop="yaopinleixing">
              <el-select
                class="list_sel"
                :disabled="!isAdd || disabledForm.yaopinleixing ? true : false"
                v-model="form.yaopinleixing"
                placeholder="请选择药品类型"
              >
                <el-option
                  v-for="(item, index) in yaopinleixingLists"
                  :label="item"
                  :value="item"
                >
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="规格" prop="guige">
              <el-input
                class="list_inp"
                v-model="form.guige"
                placeholder="规格"
                type="text"
                :readonly="!isAdd || disabledForm.guige ? true : false"
              />
            </el-form-item>
          </el-col>

          <el-col :span="12">
            <el-form-item label="生产批号" prop="shengchanpihao">
              <el-input
                class="list_inp"
                v-model="form.shengchanpihao"
                placeholder="生产批号"
                type="text"
                :readonly="!isAdd || disabledForm.shengchanpihao ? true : false"
              />
            </el-form-item>
          </el-col>

          <el-col :span="12">
            <el-form-item label="保质期" prop="baozhiqi">
              <el-input
                class="list_inp"
                v-model="form.baozhiqi"
                placeholder="保质期"
                type="text"
                :readonly="!isAdd || disabledForm.baozhiqi ? true : false"
              />
            </el-form-item>
          </el-col>

          <el-col :span="12">
            <el-form-item label="生产日期" prop="shengchanriqi">
              <el-date-picker
                class="list_date"
                v-model="form.shengchanriqi"
                format="YYYY 年 MM 月 DD 日"
                value-format="YYYY-MM-DD"
                type="datetime"
                :readonly="!isAdd || disabledForm.shengchanriqi ? true : false"
                placeholder="请选择生产日期"
              />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="企业名称" prop="qiyemingcheng">
              <el-input
                class="list_inp"
                v-model="form.qiyemingcheng"
                placeholder="企业名称"
                type="text"
                :readonly="!isAdd || disabledForm.qiyemingcheng ? true : false"
              />
            </el-form-item>
          </el-col>

          <el-col :span="24">
            <el-form-item label="药品详情" prop="yaopinxiangqing">
              <editor
                :value="form.yaopinxiangqing"
                placeholder="请输入药品详情"
                :readonly="
                  !isAdd || disabledForm.yaopinxiangqing ? true : false
                "
                class="list_editor"
                @change="(e) => editorChange(e, 'yaopinxiangqing')"
              ></editor>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <template #footer v-if="isAdd || type == 'logistics' || type == 'reply'">
        <span class="formModel_btn_box">
          <el-button class="cancel_btn" @click="closeClick">取消</el-button>
          <el-button class="confirm_btn" type="primary" @click="save">
            提交
          </el-button>
        </span>
      </template>
    </el-dialog>
  </div>
</template>
<script setup>
import {
  reactive,
  ref,
  getCurrentInstance,
  nextTick,
  computed,
  defineEmits,
} from 'vue'
import { useStore } from 'vuex'
const store = useStore()
const user = computed(() => store.getters['user/session'])
const context = getCurrentInstance()?.appContext.config.globalProperties
const emit = defineEmits(['formModelChange'])
//基础信息
const tableName = 'yaopinxinxi'
const formName = '药品信息'
//基础信息
//form表单
const form = ref({})
const disabledForm = ref({
  yaopinbianhao: false,
  yaopinmingcheng: false,
  jiage: false,
  kucun: false,
  yaopintupian: false,
  yaopinleixing: false,
  guige: false,
  shengchanpihao: false,
  baozhiqi: false,
  shengchanriqi: false,
  qiyemingcheng: false,
  yaopinxiangqing: false,
})
const formVisible = ref(false)
const isAdd = ref(false)
const formTitle = ref('')
//表单验证
//匹配整数
const validateIntNumber = (rule, value, callback) => {
  if (!value) {
    callback()
  } else if (!context?.$toolUtil.isIntNumer(value)) {
    callback(new Error('请输入整数'))
  } else {
    callback()
  }
}
//匹配数字
const validateNumber = (rule, value, callback) => {
  if (!value) {
    callback()
  } else if (!context?.$toolUtil.isNumber(value)) {
    callback(new Error('请输入数字'))
  } else {
    callback()
  }
}
//匹配手机号码
const validateMobile = (rule, value, callback) => {
  if (!value) {
    callback()
  } else if (!context?.$toolUtil.isMobile(value)) {
    callback(new Error('请输入正确的手机号码'))
  } else {
    callback()
  }
}
//匹配电话号码
const validatePhone = (rule, value, callback) => {
  if (!value) {
    callback()
  } else if (!context?.$toolUtil.isPhone(value)) {
    callback(new Error('请输入正确的电话号码'))
  } else {
    callback()
  }
}
//匹配邮箱
const validateEmail = (rule, value, callback) => {
  if (!value) {
    callback()
  } else if (!context?.$toolUtil.isEmail(value)) {
    callback(new Error('请输入正确的邮箱地址'))
  } else {
    callback()
  }
}
//匹配身份证
const validateIdCard = (rule, value, callback) => {
  if (!value) {
    callback()
  } else if (!context?.$toolUtil.checkIdCard(value)) {
    callback(new Error('请输入正确的身份证号码'))
  } else {
    callback()
  }
}
//匹配网站地址
const validateUrl = (rule, value, callback) => {
  if (!value) {
    callback()
  } else if (!context?.$toolUtil.isURL(value)) {
    callback(new Error('请输入正确的URL地址'))
  } else {
    callback()
  }
}
const rules = ref({
  yaopinbianhao: [],
  yaopinmingcheng: [{ required: true, message: '请输入', trigger: 'blur' }],
  jiage: [{ validator: validateNumber, trigger: 'blur' }],
  kucun: [{ validator: validateIntNumber, trigger: 'blur' }],
  yaopintupian: [],
  yaopinleixing: [{ required: true, message: '请输入', trigger: 'blur' }],
  guige: [],
  shengchanpihao: [{ required: true, message: '请输入', trigger: 'blur' }],
  baozhiqi: [],
  shengchanriqi: [{ required: true, message: '请输入', trigger: 'blur' }],
  qiyemingcheng: [],
  yaopinxiangqing: [],
})
//表单验证

const formRef = ref(null)
const id = ref(0)
const type = ref('')
//药品图片上传回调
const yaopintupianUploadSuccess = (e) => {
  form.value.yaopintupian = e
}
//药品类型列表
const yaopinleixingLists = ref([])

//获取唯一标识
const getUUID = () => {
  return new Date().getTime()
}
//重置
const resetForm = () => {
  form.value = {
    yaopinbianhao: getUUID(),
    yaopinmingcheng: '',
    jiage: '',
    kucun: '',
    yaopintupian: '',
    yaopinleixing: '',
    guige: '',
    shengchanpihao: '',
    baozhiqi: '',
    shengchanriqi: '',
    qiyemingcheng: '',
    yaopinxiangqing: '',
  }
}
//获取info
const getInfo = () => {
  context
    ?.$http({
      url: `${tableName}/info/${id.value}`,
      method: 'get',
    })
    .then((res) => {
      let reg = new RegExp('../../../file', 'g')
      res.data.data.yaopinxiangqing = res.data.data.yaopinxiangqing
        ? res.data.data.yaopinxiangqing.replace(
            reg,
            '../../../hospitalDrugManage/file'
          )
        : ''
      form.value = res.data.data
      formVisible.value = true
    })
}
const crossRow = ref('')
const crossTable = ref('')
const crossTips = ref('')
const crossColumnName = ref('')
const crossColumnValue = ref('')
//初始化
const init = (
  formId = null,
  formType = 'add',
  formNames = '',
  row = null,
  table = null,
  statusColumnName = null,
  tips = null,
  statusColumnValue = null
) => {
  resetForm()
  if (formId) {
    id.value = formId
    type.value = formType
  }
  if (formType == 'add') {
    isAdd.value = true
    formTitle.value = '新增' + formName
    formVisible.value = true
  } else if (formType == 'info') {
    isAdd.value = false
    formTitle.value = '查看' + formName
    getInfo()
  } else if (formType == 'edit') {
    isAdd.value = true
    formTitle.value = '修改' + formName
    getInfo()
  } else if (formType == 'cross') {
    isAdd.value = true
    formTitle.value = formNames
    // getInfo()
    for (let x in row) {
      if (x == 'yaopinbianhao') {
        form.value.yaopinbianhao = row[x]
        disabledForm.value.yaopinbianhao = true
        continue
      }
      if (x == 'yaopinmingcheng') {
        form.value.yaopinmingcheng = row[x]
        disabledForm.value.yaopinmingcheng = true
        continue
      }
      if (x == 'jiage') {
        form.value.jiage = row[x]
        disabledForm.value.jiage = true
        continue
      }
      if (x == 'kucun') {
        form.value.kucun = row[x]
        disabledForm.value.kucun = true
        continue
      }
      if (x == 'yaopintupian') {
        form.value.yaopintupian = row[x]
        disabledForm.value.yaopintupian = true
        continue
      }
      if (x == 'yaopinleixing') {
        form.value.yaopinleixing = row[x]
        disabledForm.value.yaopinleixing = true
        continue
      }
      if (x == 'guige') {
        form.value.guige = row[x]
        disabledForm.value.guige = true
        continue
      }
      if (x == 'shengchanpihao') {
        form.value.shengchanpihao = row[x]
        disabledForm.value.shengchanpihao = true
        continue
      }
      if (x == 'baozhiqi') {
        form.value.baozhiqi = row[x]
        disabledForm.value.baozhiqi = true
        continue
      }
      if (x == 'shengchanriqi') {
        form.value.shengchanriqi = row[x]
        disabledForm.value.shengchanriqi = true
        continue
      }
      if (x == 'qiyemingcheng') {
        form.value.qiyemingcheng = row[x]
        disabledForm.value.qiyemingcheng = true
        continue
      }
      if (x == 'yaopinxiangqing') {
        form.value.yaopinxiangqing = row[x]
        disabledForm.value.yaopinxiangqing = true
        continue
      }
    }
    if (row) {
      crossRow.value = row
    }
    if (table) {
      crossTable.value = table
    }
    if (tips) {
      crossTips.value = tips
    }
    if (statusColumnName) {
      crossColumnName.value = statusColumnName
    }
    if (statusColumnValue) {
      crossColumnValue.value = statusColumnValue
    }
    formVisible.value = true
  }

  context
    ?.$http({
      url: `${context?.$toolUtil.storageGet('sessionTable')}/session`,
      method: 'get',
    })
    .then((res) => {
      var json = res.data.data
      if (
        json.hasOwnProperty('qiyemingcheng') &&
        context?.$toolUtil.storageGet('role') != '管理员'
      ) {
        form.value.qiyemingcheng = json.qiyemingcheng
        disabledForm.value.qiyemingcheng = true
      }
    })
  context
    ?.$http({
      url: `option/yaopinleixing/yaopinleixing`,
      method: 'get',
    })
    .then((res) => {
      yaopinleixingLists.value = res.data.data
    })
}
//初始化
//声明父级调用
defineExpose({
  init,
})
//关闭
const closeClick = () => {
  formVisible.value = false
}
//富文本
const editorChange = (e, name) => {
  form.value[name] = e
}
//提交
const save = async () => {
  if (form.value.yaopintupian != null) {
    form.value.yaopintupian = form.value.yaopintupian.replace(
      new RegExp(context?.$config.url, 'g'),
      ''
    )
  }
  var table = crossTable.value
  var objcross = JSON.parse(JSON.stringify(crossRow.value))
  let crossUserId = ''
  let crossRefId = ''
  let crossOptNum = ''
  if (type.value == 'cross') {
    if (crossColumnName.value != '') {
      if (!crossColumnName.value.startsWith('[')) {
        for (let o in objcross) {
          if (o == crossColumnName.value) {
            objcross[o] = crossColumnValue.value
          }
        }
        //修改跨表数据
        changeCrossData(objcross)
      } else {
        crossUserId = user.value.id
        crossRefId = objcross['id']
        crossOptNum = crossColumnName.value.replace(/\[/, '').replace(/\]/, '')
      }
    }
  }
  formRef.value.validate((valid) => {
    if (valid) {
      if (crossUserId && crossRefId) {
        form.value.crossuserid = crossUserId
        form.value.crossrefid = crossRefId
        let params = {
          page: 1,
          limit: 1000,
          crossuserid: form.value.crossuserid,
          crossrefid: form.value.crossrefid,
        }
        context
          ?.$http({
            url: `${tableName}/page`,
            method: 'get',
            params: params,
          })
          .then((res) => {
            if (res.data.data.total >= crossOptNum) {
              context?.$toolUtil.message(`${crossTips.value}`, 'error')
              return false
            } else {
              context
                ?.$http({
                  url: `${tableName}/${!form.value.id ? 'save' : 'update'}`,
                  method: 'post',
                  data: form.value,
                })
                .then(async (res) => {
                  emit('formModelChange')
                  context?.$toolUtil.message(`操作成功`, 'success')
                  formVisible.value = false
                })
            }
          })
      } else {
        context
          ?.$http({
            url: `${tableName}/${!form.value.id ? 'save' : 'update'}`,
            method: 'post',
            data: form.value,
          })
          .then(async (res) => {
            emit('formModelChange')
            context?.$toolUtil.message(`操作成功`, 'success')
            formVisible.value = false
          })
      }
    } else {
      context.$message.error('请完善信息')
    }
  })
}
//修改跨表数据
const changeCrossData = async (row) => {
  if (type.value == 'cross') {
    await context
      ?.$http({
        url: `${crossTable.value}/update`,
        method: 'post',
        data: row,
      })
      .then((res) => {})
  }
}
</script>
<style lang="scss" scoped>
// 表单
.formModel_form {
  // form item
  :deep(.el-form-item) {
    //label
    .el-form-item__label {
    }
    // 内容盒子
    .el-form-item__content {
      // 输入框
      .list_inp {
      }
      //日期选择器
      .list_date {
      }
      // 下拉框
      .list_sel {
        //去掉默认样式
        .select-trigger {
          height: 100%;
          .el-input {
            height: 100%;
          }
        }
      }
      // 富文本
      .list_editor {
      }
      //图片上传样式
      .el-upload-list {
        //提示语
        .el-upload__tip {
        }
        //外部盒子
        .el-upload--picture-card {
          //图标
          .el-icon {
          }
        }
        .el-upload-list__item {
        }
      }
    }
  }
}
// 按钮盒子
.formModel_btn_box {
  .cancel_btn {
  }
  .cancel_btn:hover {
  }

  .confirm_btn {
  }
  .confirm_btn:hover {
  }
}
</style>
