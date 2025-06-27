<template>
  <PageLayout title="个人信息">
    <div class="p-6">
      <!-- 基本信息卡片 -->
      <el-card class="mt-4">
        <div class="grid grid-cols-1 md:grid-cols-2 gap-8">
          <!-- 右侧：头像 -->
          <div class="flex flex-col items-center justify-center">
            <!-- 将头像作为上传按钮 -->
            <el-upload
                :show-file-list="false"
                :before-upload="beforeAvatarUpload"
                :on-success="handleAvatarSuccess"
                class="cursor-pointer"
            >
              <el-avatar :size="100" :src="profile.avatar" />
            </el-upload>
          </div>
          <!-- 左侧：信息表单 -->
          <div>
            <el-form :model="profile" label-width="100px" class="w-full">
              <el-form-item label="用户名">
                <el-input v-model="profile.username" disabled/>
              </el-form-item>
              <el-form-item label="姓名">
                <el-input v-model="profile.name"/>
              </el-form-item>
              <el-form-item label="手机号">
                <el-input v-model="profile.phone"/>
              </el-form-item>
              <el-form-item label="岗位">
                <el-input v-model="profile.roleName" disabled/>
              </el-form-item>
              <el-form-item label="门店">
                <el-input v-model="profile.storeName" disabled/>
              </el-form-item>
              <el-form-item label="租户">
                <el-input v-model="profile.tenantName" disabled/>
              </el-form-item>
              <el-form-item>
                <el-button type="primary" @click="saveInfo">保存修改</el-button>
              </el-form-item>
            </el-form>
          </div>


        </div>
      </el-card>

      <!-- 修改密码 -->
      <el-card class="mt-6">
        <h3 class="text-lg font-bold mb-4">修改密码</h3>
        <el-form :model="passwordForm" label-width="100px" ref="pwdFormRef" :rules="pwdRules">
          <el-form-item label="原密码" prop="oldPwd">
            <el-input v-model="passwordForm.oldPwd" type="password" show-password/>
          </el-form-item>
          <el-form-item label="新密码" prop="newPwd">
            <el-input v-model="passwordForm.newPwd" type="password" show-password/>
          </el-form-item>
          <el-form-item label="确认密码" prop="confirmPwd">
            <el-input v-model="passwordForm.confirmPwd" type="password" show-password/>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="changePassword">修改密码</el-button>
          </el-form-item>
        </el-form>
      </el-card>

      <!-- 邮箱绑定 -->
      <el-card class="mt-6">
        <h3 class="text-lg font-bold mb-4">邮箱绑定</h3>
        <el-form :inline="true" :model="emailForm" class="items-center">
          <el-form-item label="邮箱地址">
            <el-input v-model="emailForm.email" placeholder="请输入邮箱地址"/>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="sendEmailCode">发送验证码</el-button>
          </el-form-item>
          <el-form-item label="验证码">
            <el-input v-model="emailForm.code" placeholder="请输入验证码"/>
          </el-form-item>
          <el-form-item>
            <el-button type="success" @click="bindEmail">绑定邮箱</el-button>
          </el-form-item>
        </el-form>
      </el-card>

      <!-- 二步验证 -->
      <el-card class="mt-6">
        <h3 class="text-lg font-bold mb-4">二步验证（2FA）</h3>
        <el-switch
            v-model="enable2FA"
            active-text="已启用"
            inactive-text="未启用"
            @change="toggle2FA"
        />
        <p class="text-gray-500 mt-2">启用后，登录时需要输入动态验证码。</p>
      </el-card>

      <!-- 注销账号 -->
      <el-card class="mt-6">
        <h3 class="text-lg font-bold mb-4">账号注销</h3>
        <el-button type="danger" @click="logout" plain>退出登录</el-button>
      </el-card>
    </div>
  </PageLayout>
</template>

<script setup>
import {ref, reactive} from 'vue';
import {ElMessage} from 'element-plus';
import {useRouter} from 'vue-router';
import PageLayout from "@/components/layout/PageLayout.vue";

const router = useRouter();

// 用户基本信息
const profile = reactive({
  username: 'admin001',
  name: '管理员张三',
  phone: '13800001111',
  avatar: 'https://api.dicebear.com/7.x/initials/svg?seed=张三',
  roleName: '店长',
  storeName: '张记 - 总店',
  tenantName: '张记餐饮',
  email: ''
});

// 更换头像
function beforeAvatarUpload(file) {
  const isImage = file.type === 'image/jpeg' || file.type === 'image/png';
  if (!isImage) {
    ElMessage.error('只支持 JPG/PNG 格式图片');
  }
  return isImage;
}

function handleAvatarSuccess(response, file) {
  profile.avatar = URL.createObjectURL(file.raw);
  ElMessage.success('头像更新成功');
}

// 保存信息
function saveInfo() {
  ElMessage.success('信息已保存（模拟）');
}

// 修改密码
const pwdFormRef = ref();
const passwordForm = reactive({
  oldPwd: '',
  newPwd: '',
  confirmPwd: ''
});

const pwdRules = {
  oldPwd: [{required: true, message: '请输入原密码', trigger: 'blur'}],
  newPwd: [{required: true, message: '请输入新密码', trigger: 'blur'}],
  confirmPwd: [
    {required: true, message: '请确认密码', trigger: 'blur'},
    {
      validator: (_, val) => {
        if (val !== passwordForm.newPwd) return new Error('两次输入不一致');
        return true;
      },
      trigger: 'blur'
    }
  ]
};

function changePassword() {
  pwdFormRef.value.validate(valid => {
    if (valid) {
      ElMessage.success('密码修改成功（模拟）');
      passwordForm.oldPwd = '';
      passwordForm.newPwd = '';
      passwordForm.confirmPwd = '';
    }
  });
}

// 邮箱绑定
const emailForm = reactive({
  email: '',
  code: ''
});

function sendEmailCode() {
  if (!emailForm.email || !emailForm.email.includes('@')) {
    ElMessage.warning('请输入有效的邮箱地址');
    return;
  }
  ElMessage.success(`验证码已发送至 ${emailForm.email}（模拟）`);
}

function bindEmail() {
  if (emailForm.code !== '123456') {
    ElMessage.error('验证码错误（示例中固定为123456）');
    return;
  }
  profile.email = emailForm.email;
  ElMessage.success('邮箱绑定成功');
}

// 二步验证
const enable2FA = ref(false);

function toggle2FA(val) {
  ElMessage.success(val ? '2FA 已启用' : '2FA 已关闭');
}

// 注销账号
function logout() {
  localStorage.removeItem('token')
  localStorage.removeItem('tenant')
  ElMessage.success('已退出登录');
  localStorage.clear();
  router.push('/login');
}


</script>

<style scoped>
/* 可以根据需要进行样式调整 */
.flex {
  display: flex;
  justify-content: center; /* 水平居中 */
  align-items: center; /* 垂直居中 */
  padding-bottom: 20px;
}

.cursor-pointer {
  cursor: pointer;
}
</style>
