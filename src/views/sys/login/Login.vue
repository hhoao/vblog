<template>
  <div :class="prefixCls" class="relative w-full h-full px-4">
    <AppLocalePicker
      class="absolute text-white top-4 right-4 enter-x xl:text-gray-600"
      :showText="false"
      v-if="!sessionTimeout && showLocale"
    />
    <AppDarkModeToggle class="absolute top-3 right-7 enter-x" v-if="!sessionTimeout" />
    <div class="absolute w-full h:full inset-0 bg-cover login-bg"></div>
    <span class="-enter-x">
      <AppLogo :alwaysShowTitle="true" />
    </span>
    <div class="bg-cover login-show-bg"> </div>
    <div
      class="left-1/2 absolute rounded-lg bg-white ml-160px"
      style="
        width: 400px;
        top: 50%;
        height: 488px;
        margin-top: -244px;
        box-shadow: 0 2px 6px 0 rgb(0 0 0 / 10%);
      "
    >
      <div style="padding: 0 30px; width: 400px; font-size: 12px">
        <div style="padding: 48px 0">
          <LoginForm />
          <ForgetPasswordForm />
          <RegisterForm />
          <MobileForm />
          <QrCodeForm />
        </div>
      </div>
    </div>
  </div>
</template>
<script lang="ts" setup>
  import { AppDarkModeToggle, AppLocalePicker, AppLogo } from '/@/components/Application';
  import LoginForm from './LoginForm.vue';
  import ForgetPasswordForm from './ForgetPasswordForm.vue';
  import RegisterForm from './RegisterForm.vue';
  import MobileForm from './MobileForm.vue';
  import QrCodeForm from './QrCodeForm.vue';
  import { useDesign } from '/@/hooks/web/useDesign';
  import { useLocaleStore } from '/@/store/modules/locale';

  defineProps({
    sessionTimeout: {
      type: Boolean,
    },
  });
  const { prefixCls } = useDesign('login');
  const localeStore = useLocaleStore();
  const showLocale = localeStore.getShowPicker;
</script>
<style lang="less">
  @prefix-cls: ~'@{namespace}-login';
  @logo-prefix-cls: ~'@{namespace}-app-logo';
  @countdown-prefix-cls: ~'@{namespace}-countdown-input';
  .@{prefix-cls} {
    min-height: 100%;
    overflow: hidden;
    padding-left: 30px;

    .login-bg {
      background-image: url(/@/assets/images/login-bg.png);
    }

    .login-show-bg {
      background-image: url(/@/assets/images/login-show.png);
      position: absolute;
      width: 900px;
      height: 500px;
      top: 50%;
      margin-top: -220px;
      left: 0;
      cursor: pointer;
    }
    .@{logo-prefix-cls} {
      position: absolute;
      top: 12px;

      &__title {
        font-size: 16px;
        color: #413434;
      }

      img {
        width: 44px;
      }
    }

    .ant-form-item-control-input-content {
      .ant-input {
        height: 46px;
        border-radius: 6px;
        font-size: 14px;
        padding: 6px 18px;
      }

      .ant-input-affix-wrapper {
        border-radius: 6px;
        padding: 0 18px;

        .ant-input {
          height: 46px;
          line-height: 46px;
          font-size: 14px;
          padding: 10px 0px;
        }
      }
    }
  }
</style>
