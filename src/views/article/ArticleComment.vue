<template>
  <div class="text-lg">
    评论列表
    <a-list
      v-if="comments.length"
      :data-source="comments"
      :header="`${comments.length} ${comments.length > 1 ? 'replies' : 'reply'}`"
      item-layout="horizontal"
    >
      <template #renderItem="{ item }">
        <a-list-item>
          <a-comment
            :author="item.author"
            :avatar="item.avatar"
            :content="item.content"
            :datetime="item.datetime"
          />
        </a-list-item>
      </template>
    </a-list>
    <a-divider />
    <div class="text-lg">发表评论</div>
    <a-comment>
      <template #avatar>
        <a-avatar src="" alt="Han Solo" />
      </template>
      <template #content>
        <a-form ref="commentFormRef" :model="commentFormData">
          <div class="inline-block w-2/5 mr-8px">
            <a-form-item required name="nickname">
              <a-input v-model:value="commentFormData.nickname" size="md" placeholder="昵称(必填)">
                <template #prefix>
                  <UserOutlined class="site-form-item-icon" />
                </template>
              </a-input>
            </a-form-item>
          </div>
          <div class="inline-block w-2/5">
            <a-form-item :rules="[{ type: 'email' }]" name="email">
              <a-input v-model:value="commentFormData.email" size="md" placeholder="邮箱">
                <template #prefix>
                  <MailOutlined class="site-form-item-icon" />
                </template>
              </a-input>
            </a-form-item>
          </div>
          <a-form-item name="content" :rules="[{ required: true, min: 6 }]">
            <a-textarea v-model:value="commentFormData.content" />
          </a-form-item>
          <a-form-item>
            <a-button html-type="submit" :loading="submitting" type="primary" @click="handleSubmit">
              Add Comment
            </a-button>
          </a-form-item>
        </a-form>
      </template>
    </a-comment>
    <a-divider />
  </div>
</template>

<script setup lang="ts">
  import { UserOutlined, MailOutlined } from '@ant-design/icons-vue';
  import dayjs from 'dayjs';
  import { reactive, ref } from 'vue';

  const commentFormRef = ref();
  const commentFormData = reactive({
    nickname: '',
    email: '',
    content: '',
  });
  const comments = ref<Comment[]>([]);
  const submitting = ref<boolean>(false);
  const handleSubmit = () => {
    commentFormRef.value.validateFields().then(() => {
      if (!commentFormData.content) {
        return;
      }
      submitting.value = true;

      setTimeout(() => {
        submitting.value = false;
        comments.value = [
          {
            author: commentFormData.nickname,
            avatar: '',
            content: commentFormData.content,
            datetime: dayjs().date(),
          },
          ...comments.value,
        ];
        commentFormData.content = '';
      }, 1000);
    });
  };
</script>

<style scoped></style>
