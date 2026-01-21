<template>
  <div>
    <div class="login_view">
      <el-form :model="loginForm" class="login_form">
        <div class="title_view">康美社区医院人员管理系统登录</div>
        <div class="list_item" v-if="loginType == 1">
          <div class="list_label">账号：</div>
          <input
            class="list_inp"
            v-model="loginForm.username"
            placeholder="请输入账号"
            name="username"
          />
        </div>
        <div class="list_item" v-if="loginType == 1">
          <div class="list_label">密码：</div>
          <input
            class="list_inp"
            v-model="loginForm.password"
            type="password"
            placeholder="请输入密码"
            @keydown.enter.native="handleLogin"
          />
        </div>
        <div class="list_type" v-if="userList.length > 1">
          <div class="list_label">用户类型：</div>
          <el-select v-model="loginForm.role" placeholder="请选择用户类型">
            <el-option
              v-for="(item, index) in userList"
              :label="item.roleName"
              :value="item.roleName"
            ></el-option>
          </el-select>
        </div>
        <div class="btn_view">
          <el-button
            class="login"
            v-if="loginType == 1"
            type="success"
            @click="handleLogin"
            >登录</el-button
          >
        </div>
      </el-form>
    </div>
    <Vcode
      :show="isShow"
      @success="success"
      @close="close"
      @fail="fail"
    ></Vcode>
  </div>
</template>
<script setup>
import { ref, getCurrentInstance, nextTick, onMounted } from 'vue'
import { useStore } from 'vuex'
const store = useStore()
const userList = ref([])
const menus = ref([])
const loginForm = ref({
  role: '',
  username: '',
  password: '',
})
const tableName = ref('')
const loginType = ref(1)
const context = getCurrentInstance()?.appContext.config.globalProperties
const handleLogin = () => {
  if (!loginForm.value.username) {
    context?.$toolUtil.message('请输入用户名', 'error')

    return
  }
  if (!loginForm.value.password) {
    context?.$toolUtil.message('请输入密码', 'error')
    return
  }
  if (userList.value.length > 1) {
    if (!loginForm.value.role) {
      context?.$toolUtil.message('请选择角色', 'error')
      verifySlider.value.reset()
      return
    }
    for (let i = 0; i < menus.value.length; i++) {
      if (menus.value[i].roleName == loginForm.value.role) {
        tableName.value = menus.value[i].pathName || menus.value[i].tableName
      }
    }
  } else {
    tableName.value = userList.value[0].pathName || userList.value[0].tableName
    loginForm.value.role = userList.value[0].roleName
  }
  login()
}
const login = () => {
  context
    ?.$http({
      url: `${tableName.value}/login?username=${loginForm.value.username}&password=${loginForm.value.password}`,
      method: 'post',
    })
    .then(
      (res) => {
        context?.$toolUtil.storageSet('Token', res.data.token)
        context?.$toolUtil.storageSet('role', loginForm.value.role)
        context?.$toolUtil.storageSet('sessionTable', tableName.value)
        context?.$toolUtil.storageSet('adminName', loginForm.value.username)
        store.dispatch('user/getSession') //vuex中获取用户信息并保存
        context?.$router.push('/')
      },
      (err) => {}
    )
}
//获取菜单
const getMenu = () => {
  let params = {
    page: 1,
    limit: 1,
    sort: 'id',
  }

  context
    ?.$http({
      url: 'menu/list',
      method: 'get',
      params: params,
    })
    .then((res) => {
      menus.value = JSON.parse(res.data.data.list[0].menujson)
      for (let i = 0; i < menus.value.length; i++) {
        if (menus.value[i].hasBackLogin == '是') {
          userList.value.push(menus.value[i])
        }
      }
      loginForm.value.role = userList.value[0].roleName
      context?.$toolUtil.storageSet('menus', JSON.stringify(menus.value))
    })
}
//初始化
const init = () => {
  getMenu()
}
onMounted(() => {
  init()
})
</script>

<style lang="scss" scoped>
.login_view {
  background-image: url('http://localhost:8080/hospitalDrugManage/file/33cefbfd9c7a4b76a40be61cd680f10c.webp');
  // 表单盒子
  .login_form {
  }
  .title_view {
  }
  // item盒子
  .list_item {
    // label
    .list_label {
    }
    // 输入框
    .list_inp {
    }
  }
  .list_type {
    .list_label {
    }
    // 下拉框样式
    :deep(.el-select) {
      //去掉默认样式
      .select-trigger {
        height: 100%;
        .el-input {
          height: 100%;
        }
      }
    }
  }
  // 按钮盒子
  .btn_view {
    // 登录
    .login {
    }
  }
}
</style>
<style>
.login_view .tips {
  font-size: 18px;
  color: #ffffff;
  line-height: 50px;
  background: #007ccf;
  position: absolute;
  bottom: 0;
  width: 100%;
  text-align: center;
}

.login_view {
  height: 100vh;
  display: flex;
  align-items: center;
  font-size: 14px;
  justify-content: center;
}
.login_view input {
  border: none;
}

.login_view .login_form {
  width: 450px;
  height: 600px;
  padding: 70px 30px;
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
  position: relative;
  margin-left: 570px;
  background: rgba(0, 0, 0, 0.25);
}

.login_view .title_view {
  position: absolute;
  top: 30%;
  right: calc(50% + 370px);
  text-align: center;
  width: 100%;
  color: #000000;
  font-size: 26px;
}

.login_view .list_item {
  color: #fff;
  line-height: 40px;
  display: flex;
  margin-bottom: 30px;
  width: 100%;
  order: 1;
  border: 1px solid #727272;
  background: #fff;
  height: 50px;
}

.login_view .list_inp {
  flex: 1;
}

.login_view .el-radio.is-checked .el-radio__inner {
  border: 1px solid #fff;
  background: #007ccf;
}

.login_view .el-radio.is-checked .el-radio__inner::after {
  background: #fff;
}

.login_view .el-radio.is-checked .el-radio__label {
  color: #fff;
}

.login_view span.el-radio__label {
  color: #969696;
}

.login_view span.el-radio__inner {
  background: #007ccf;
}

.login_view .list_type {
  width: 100%;
  text-align: center;
  margin-bottom: 20px;
  order: 0;
  display: flex;
  border: 1px solid #727272;
  background: #fff;
  height: 50px;
  padding-right: 10px;
}

.login_view .listCode_view {
  display: flex;
  line-height: 40px;
  width: 100%;
  margin-bottom: 20px;
  order: 2;
}

.login_view input.listCode_inp {
  flex: 1;
}

.login_view .listCode_label {
  width: 60px;
  color: #fff;
}

.login_view .listCode_btn {
  width: 100px;
  background: var(--theme);
  text-align: center;
  margin-left: 10px;
}

.login_view .list_label {
  width: 80px;
  color: #808080;
  margin-right: 10px;
  text-align: center;
  position: relative;
  line-height: 48px;
  flex-shrink: 0;
}

.login_view .btn_view {
  order: 4;
  width: 100%;
  display: flex;
  flex-wrap: wrap;
  row-gap: 20px;
  column-gap: 10px;
  justify-content: center;
}

.login_view .remember_view {
}

.login_view .face {
  order: 3;
  color: #fff;
  margin-bottom: 20px;
}

.login_view button.el-button.login {
  color: #fff;
  background: var(--theme);
  border: none;
  width: 100%;
}

.login_view .btn_view .el-button {
  margin: 0;
  background: none;
  height: 40px;
}

.login_view button.el-button.forget {
  background: none;
  width: 100%;
  color: #fff;
  border: none;
}

.login_view .list_label:after {
  content: '';
  position: absolute;
  height: 30px;
  width: 1px;
  top: 8px;
  right: 0;
  background: #d8d8d8;
}

.login_view .el-select {
  border: none !important;
  width: 100%;
}

.login_view .el-button.register {
  position: absolute;
  right: calc(100% + 305px);
  top: 50%;
  width: 140px;
  border: 1px solid #fff;
  background: #fff;
  color: var(--theme);
}
.login_view .el-button.register:nth-child(3) {
  right: calc(100% + 154px);
}
.login_view .el-button.register:nth-child(2) {
  right: calc(100% + 476px);
}
.login_view .el-button.register:nth-child(4) {
  right: calc(100% + 315px);
}
.login_view .el-button.register:nth-child(5) {
  right: calc(100% + 154px);
  top: 70%;
}
.login_view .el-button.register:nth-child(6) {
  right: calc(100% + 476px);
  top: 70%;
}
.login_view .el-button.register:nth-child(7) {
  right: calc(100% + 315px);
  top: 70%;
}

.login_view:before {
  content: '';
  height: 600px;
  width: 100%;
  position: absolute;
  top: calc(50% - 300px);
  background: rgba(0, 0, 0, 0.2);
}
</style>
