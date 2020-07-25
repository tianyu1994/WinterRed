const path=require('path')

function resolve(dir){
    return path.join(__dirname, dir)
}

module.exports = {
    configureWebpack:{
        resolve:{
            alias:{
                '@':resolve('src')
            }
        }
    },
    css: {
        loaderOptions:{
            sass:{
                implementation: require('sass')
            }
        }
    },
    devServer: {
        port: 3050,
        https: false,
        hotOnly: false,
        proxy: null, // 设置代理
        before: app => {}
    }
}
