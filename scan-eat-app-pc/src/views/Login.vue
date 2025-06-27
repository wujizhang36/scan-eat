<template>
  <div class="login-wrapper">
    <div class="login-box">
      <!-- 语言切换 -->
      <div class="lang-switch">
        <el-dropdown @command="switchLang">
          <span class="lang-text">
            {{ currentLang === 'zh-CN' ? '中文 (简体)' : 'English' }}
          </span>
          <template #dropdown>
            <el-dropdown-menu>
              <el-dropdown-item command="zh-CN">中文 (简体)</el-dropdown-item>
              <el-dropdown-item command="en">English</el-dropdown-item>
            </el-dropdown-menu>
          </template>
        </el-dropdown>
      </div>

      <!-- 标题 -->
      <h2 class="login-title">{{ $t('login.title') }}</h2>
      <p class="login-subtitle">{{ $t('login.subtitle') }}</p>

      <!-- 表单 -->
      <el-form :model="loginForm" :rules="rules" ref="formRef" label-position="top">
        <el-form-item :label="$t('login.tenant')" prop="tenant">
          <el-input v-model="loginForm.tenant" :placeholder="$t('login.tenantPlaceholder')" />
        </el-form-item>
        <el-form-item :label="$t('login.username')" prop="username">
          <el-input v-model="loginForm.username" :placeholder="$t('login.usernamePlaceholder')" />
        </el-form-item>
        <el-form-item :label="$t('login.password')" prop="password">
          <el-input
              v-model="loginForm.password"
              type="password"
              :placeholder="$t('login.passwordPlaceholder')"
              show-password
          />
        </el-form-item>
        <el-button type="primary" class="login-btn" @click="handleLogin">
          {{ $t('login.button') }}
        </el-button>
      </el-form>

      <div class="login-footer">© 2025 {{ $t('login.footer') }}</div>
    </div>
  </div>
</template>

<script setup>
import { reactive, ref } from 'vue'
import { useRouter } from 'vue-router'
import { useI18n } from 'vue-i18n'
import { ElMessage } from 'element-plus'

const router = useRouter()
const { t, locale } = useI18n()
const currentLang = ref(locale.value)

const loginForm = reactive({
  tenant: '',
  username: '',
  password: ''
})

const rules = {
  tenant: [{ required: true, message: t('login.tenantRequired'), trigger: 'blur' }],
  username: [{ required: true, message: t('login.usernameRequired'), trigger: 'blur' }],
  password: [{ required: true, message: t('login.passwordRequired'), trigger: 'blur' }]
}

const formRef = ref()

function handleLogin() {
  formRef.value.validate(valid => {
    if (!valid) return
    if (loginForm.username === 'admin' && loginForm.password === '123456') {
      localStorage.setItem('token', 'mock-token')
      localStorage.setItem('tenant', loginForm.tenant)
      ElMessage.success(t('login.success'))
      router.push('/dashboard')
    } else {
      ElMessage.error(t('login.error'))
    }
  })
}

function switchLang(lang) {
  locale.value = lang
  currentLang.value = lang
}
</script>

<style scoped>
.login-wrapper {
  min-height: 100vh;
  background-color: #f3f4f6;
  display: flex;
  align-items: center;
  justify-content: center;
  padding: 20px;
  font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif;
}

.login-box {
  width: 100%;
  max-width: 400px;
  background: #fff;
  padding: 30px;
  border-radius: 16px;
  box-shadow: 0 6px 18px rgba(0, 0, 0, 0.1);
}

.lang-switch {
  display: flex;
  justify-content: flex-end;
  margin-bottom: 16px;
}

.lang-text {
  font-size: 14px;
  color: #666;
  cursor: pointer;
}

.login-title {
  font-size: 24px;
  font-weight: 600;
  color: #333;
  margin: 0 0 8px 0;
}

.login-subtitle {
  font-size: 14px;
  color: #888;
  margin-bottom: 24px;
}

.login-btn {
  width: 100%;
  margin-top: 10px;
}

.login-footer {
  margin-top: 20px;
  text-align: center;
  font-size: 12px;
  color: #aaa;
}
</style>
