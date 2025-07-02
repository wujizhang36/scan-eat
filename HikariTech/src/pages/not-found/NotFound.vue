<template>
  <div class="not-found-container">
    <div class="content">
      <h1 class="error-code">404</h1>
      <p class="message">抱歉，页面找不到了。</p>
      <router-link to="/" class="back-home">返回首页</router-link>
    </div>
  </div>
</template>

<script>
export default {
  name: 'NotFound'
}
</script>

<style scoped>
.not-found-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  background-color: #f7f7f7;
  text-align: center;
}

.content {
  max-width: 500px;
}

.error-code {
  font-size: 96px;
  font-weight: bold;
  color: #ff4d4f;
  margin-bottom: 16px;
}

.message {
  font-size: 18px;
  color: #333;
  margin-bottom: 24px;
}

.back-home {
  display: inline-block;
  padding: 10px 20px;
  background-color: #1890ff;
  color: white;
  text-decoration: none;
  border-radius: 4px;
  transition: background-color 0.3s;
}

.back-home:hover {
  background-color: #40a9ff;
}
</style>
