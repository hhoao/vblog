<template>
  <div :class="prefixCls">
    <a-row :class="`${prefixCls}-top`" type="flex" align="middle">
      <a-col :span="4" :class="`${prefixCls}-col`">
        <a-col>
          <div :class="`${prefixCls}-top__avatar`">
            <img width="70" :src="user.avatar || headerImg" />
            <span>{{ user.username }}</span>
          </div>
        </a-col>
      </a-col>
      <a-col :span="6" :class="`${prefixCls}-col`">
        <CollapseContainer title="标签" :canExpan="false">
          <p>{{ user.signature }} </p>
        </CollapseContainer>
        <CollapseContainer title="标签" :canExpan="false">
          <template v-for="tag in user.tags?.map((item) => item.label)" :key="tag">
            <Tag class="mb-2">
              {{ tag }}
            </Tag>
          </template>
        </CollapseContainer>
      </a-col>
      <a-col :span="8">
        <div :class="`${prefixCls}-top__detail`">
          <a-row>
            <a-col span="12">
              <Icon icon="email|svg" />
              邮箱: <p>{{ user.email }}</p> 地址: <p> {{ user.address }}</p> 国家:
              <p> {{ user.country }}</p>
            </a-col>
            <a-col span="12">
              电话号码: <p>{{ user.phone }}</p> 职业: <p> {{ user.title }}</p>
            </a-col>
          </a-row>
        </div>
      </a-col>
    </a-row>
    <div :class="`${prefixCls}-bottom`">
      <Tabs>
        <template v-for="item in achieveList" :key="item.key">
          <TabPane :tab="item.name">
            <component :is="item.component" />
          </TabPane>
        </template>
      </Tabs>
    </div>
  </div>
</template>

<script lang="ts">
  import { Col, Row, Tabs, Tag } from 'ant-design-vue';
  import { computed, defineComponent } from 'vue';
  import { CollapseContainer } from '/@/components/Container';
  import Icon from '/@/components/Icon';
  import Article from './Article.vue';
  import Application from './Application.vue';
  import Project from './Project.vue';

  import headerImg from '/@/assets/images/header.jpg';
  import { achieveList } from './data';
  import { useUserStore } from '/@/store/modules/user';
  import ScrollContainer from '/@/components/Container/src/ScrollContainer.vue';
  import Description from '/@/components/Description/src/Description.vue';

  export default defineComponent({
    components: {
      Description,
      ScrollContainer,
      CollapseContainer,
      Icon,
      Tag,
      Tabs,
      TabPane: Tabs.TabPane,
      Article,
      Application,
      Project,
      [Row.name]: Row,
      [Col.name]: Col,
    },
    setup() {
      const userStore = useUserStore();
      const user = computed(() => userStore.getUserInfo);
      return {
        user,
        prefixCls: 'account-center',
        headerImg,
        // avatar,
        // tags: (() => {
        //   const tagList: string[] = [];
        //   userStore.getUserInfo.tags?.forEach((tag) => {
        //     tagList.push(tag.label);
        //   });
        //   return tagList;
        // })(),
        achieveList,
      };
    },
  });
</script>
<style lang="less" scoped>
  .account-center {
    &-col:not(:last-child) {
      padding: 0 10px;

      &:not(:last-child) {
        border-right: 1px dashed rgb(206 206 206 / 50%);
      }
    }

    &-top {
      padding: 10px;
      margin: 16px 16px 12px;
      background-color: @component-background;
      border-radius: 3px;

      &__avatar {
        text-align: center;

        img {
          margin: auto;
          border-radius: 50%;
        }

        span {
          display: block;
          font-size: 20px;
          font-weight: 500;
        }

        div {
          margin-top: 3px;
          font-size: 12px;
        }
      }

      &__detail {
        padding-left: 20px;
        margin-top: 15px;
      }

      &__team {
        &-item {
          display: inline-block;
          padding: 4px 24px;
        }

        span {
          margin-left: 3px;
        }
      }
    }

    &-bottom {
      padding: 10px;
      margin: 0 16px 16px;
      background-color: @component-background;
      border-radius: 3px;
    }
  }
</style>
