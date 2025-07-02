// postcss.config.js (ES Module Syntax)
import tailwindcss from 'tailwindcss';
import autoprefixer from 'autoprefixer';

export default {
    plugins: [
        tailwindcss(),   // 使用 tailwindcss 插件
        autoprefixer(),
    ],
};
