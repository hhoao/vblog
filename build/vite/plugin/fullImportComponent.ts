import * as path from 'path';
import type { Plugin, ResolvedConfig } from 'vite';

export default function fullImportPlugin() {
  let config: ResolvedConfig;
  return <Plugin>{
    name: 'fullImportAntdPlugin',
    async configResolved(conf) {
      config = conf;
    },
    transform(code, id) {
      // 判断当前处理的是否是 _src/main.ts_
      if (path.join(config.root, 'src/main.ts') === id) {
        const name = 'Antd';

        // 引入 ElementPlus 和 样式
        const prepend = `import ${name} from 'ant-design-vue';\nimport 'ant-design-vue/dist/antd.css';\n`;

        // 通过匹配字符串来使用 ElementPlus （此处替换规则根据 main.ts 的情况而定）
        // 相当于将字符串 `app.use(router).mount('#app')` 替换成 `app.use(router).use(ElementPlus).mount('#app')`
        code = code.replace('.mount(', ($1) => `.use(${name})` + $1);
        return prepend + code;
      }
      return code;
    },
  };
}
