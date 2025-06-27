// tailwind.config.js
module.exports = {
    content: [
        './index.html',
        './src/**/*.{vue,js,ts,jsx,tsx}'
    ],
    theme: {
        extend: {
            // 可选：你可以在这里自定义颜色、字体、间距等
            colors: {
                primary: '#409EFF', // Element Plus 默认蓝色
            },
        },
    },
    plugins: [],
}
