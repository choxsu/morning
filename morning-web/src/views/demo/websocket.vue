<!-- websocket 示例 -->
<script setup lang="ts">
import { useUserStoreHook } from "@/store/modules/user";

const userStore = useUserStoreHook();

const isConnected = ref(false);

const receiver = ref("root");

interface MessageType {
  type?: string; // 消息类型： tip-提示消息
  sender?: string;
  content: string;
}

const messages = ref<MessageType[]>([]);

const topicMessage = ref(
  "亲爱的大冤种们，由于一只史诗级的BUG，系统版本已经被迫回退到了0.0.1。"
); // 广播消息

const queneMessage = ref(
  "hi , " +
    receiver.value +
    " , 我是" +
    userStore.user.username +
    " , 想和你交个朋友 ! "
);

const messageContainer = ref<HTMLElement | null>(null);

function sendToAll() {
  if (!topicMessage.value) {
    ElMessage({
      message: "消息内容不能为空",
      type: "warning",
    });
    return;
  }
  messages.value.push({
    sender: userStore.user.username,
    content: topicMessage.value,
  });
  topicMessage.value = "";
}

function sendToUser() {
  // stompClient.send("/app/sendToUser/" + receiver.value, {}, queneMessage.value);
  messages.value.push({
    sender: userStore.user.username,
    content: queneMessage.value,
  });
}

// let stompClient: Stomp.Client;

function connectWebSocket() {}

function disconnectWebSocket() {}

// 在组件挂载后执行初始化操作
onMounted(() => {
  // 监听消息列表的变化，在消息更新后滚动到底部
  watchMessages();
});

// 监听消息列表的变化，在消息更新后滚动到底部
function watchMessages() {
  watch(messages.value, () => {
    nextTick(() => {
      scrollToBottom();
    });
  });
}

// 滚动到消息列表的底部
function scrollToBottom() {
  const container = messageContainer.value;
  if (container) {
    container.scrollTop = container.scrollHeight;
  }
}
</script>

<template>
  <div class="app-container">
    <el-container class="container">
      <el-main class="container-main">
        <el-row :gutter="24">
          <el-col :span="12" :offset="6">
            <div ref="messageContainer" class="message-container">
              <div v-if="messages.length === 0" class="empty-message">
                欢迎使用聊天室，请开始你的表演！
              </div>
              <div
                v-for="(message, index) in messages"
                :key="index"
                :class="{
                  'tip-message': message.type === 'tip',
                  message: message.type !== 'tip',
                  'message--sent': message.sender === userStore.user.username,
                  'message--received':
                    message.sender !== userStore.user.username,
                }"
              >
                <div v-if="message.type != 'tip'" class="message-content">
                  <div
                    :class="{
                      'message-sender':
                        message.sender === userStore.user.username,
                      'message-receiver':
                        message.sender !== userStore.user.username,
                    }"
                  >
                    {{ message.sender }}
                  </div>
                  <div class="color-#333">{{ message.content }}</div>
                </div>
                <div v-else>{{ message.content }}</div>
              </div>
            </div>
          </el-col>
        </el-row>
      </el-main>
      <el-footer height="180px">
        <el-row :gutter="24">
          <el-col :span="12" :offset="6">
            <el-card>
              <el-form label-width="90px">
                <el-form-item label="消息内容">
                  <el-input
                    type="textarea"
                    v-model="topicMessage"
                    style="max-height: 50px"
                  />
                </el-form-item>
                <el-form-item>
                  <el-button @click="sendToAll" type="primary">发送</el-button>
                </el-form-item>
              </el-form>
            </el-card>
          </el-col>
        </el-row>
      </el-footer>
    </el-container>
  </div>
</template>

<style scoped>
.container {
  height: 90vh;
}

.message-container {
  display: flex;
  flex-direction: column;
  height: 68vh;
  overflow-y: auto;
}

.empty-message {
  text-align: center;
  padding: 20px;
  font-size: 1.5rem;
  color: #1d0a2d;
}

.message {
  padding: 10px;
  margin: 10px;
  border-radius: 5px;
}

.message--sent {
  align-self: flex-start;
  background-color: #dcf8c6;
}

.message--received {
  align-self: flex-start;
  background-color: #e8e8e8;
}

.message-content {
  display: flex;
  flex-direction: column;
}

.message-sender {
  margin-bottom: 5px;
  font-weight: bold;
  text-align: left;
}

.message-receiver {
  margin-bottom: 5px;
  font-weight: bold;
  text-align: left;
}

.tip-message {
  align-self: center;
  padding: 5px 10px;
  margin-bottom: 5px;
  font-style: italic;
  text-align: center;
  background-color: #f0f0f0;
  border-radius: 5px;
}
</style>
